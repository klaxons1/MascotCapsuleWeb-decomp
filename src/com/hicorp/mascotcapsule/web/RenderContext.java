package com.hicorp.mascotcapsule.web;

public class RenderContext {
   public static final int FLAG_DOUBLE_SIDED = 1;
   public static final int FLAG_BLEND_TRANSPARENT = 2;
   public static final int FLAG_ALPHA_BLEND = 4096;
   public static final int FLAG_LIGHTING = 8192;
   public static final int FLAG_SPECULAR = 16384;
   public static final int FLAG_SPHERE_MAP = 32768;
   public static final int MAX_ALPHA = 255;
   public static final int ALPHA_SHIFT_16 = 16;
   public static final int COLOR_MAX_255 = 255;
   public static final int SHIFT_24 = 24;
   public static final int COMMAND_TYPE_POLYGON = 0;
   public static final int COMMAND_TYPE_LINE = 1;
   private static final boolean PERSPECTIVE_DEFAULT = true;
   private static final boolean LIGHTING_DEFAULT = true;
   private static final boolean CLIP_DEFAULT = false;
   protected int[] screenCoords;
   protected float[] vertexDepths;
   protected static final int INITIAL_CAPACITY = 256;
   protected boolean packetTableEmpty = true;
   protected RenderCommand[] packetTable;
   protected PolygonRenderCommand freeCommandHead = null;
   protected float[] vertexLighting;
   protected static final int LIGHTING_STRIDE = 3;
   protected float nearZ;
   protected float farZ;
   protected float depthBucketSize;
   protected int viewportOffsetX;
   protected int viewportOffsetY;
   protected final BoundingBox renderBounds = new BoundingBox();
   protected Config rasterizer;
   protected Texture diffuseTexture = null;
   protected Texture sphereMapTexture = null;
   protected boolean lightingEnabled;
   protected float ambientIntensity;
   protected final Vector3f lightDirection = new Vector3f();
   protected float lightIntensity;
   private float focalLength;
   private boolean perspectiveEnabled;
   private float perspectiveScale;
   private static final float DEFAULT_VIEWPORT_WIDTH = 640.0F;
   private static final float DEFAULT_FOV = MatrixUtils.toRadians(60.0F);
   private float fov;
   private float viewportWidth;
   private static final Vector3f VIEW_DIR_Z = new Vector3f(0.0F, 0.0F, -1.0F);

   private final float computeFocalLength() {
      return (float)(this.viewportWidth / 2.0 / Math.tan(this.fov / 2.0));
   }

   public RenderContext(Config rasterizer) {
      this.screenCoords = new int[512];
      this.vertexDepths = new float[256];
      this.vertexLighting = new float[0];
      this.packetTable = null;
      this.viewportOffsetX = 0;
      this.viewportOffsetY = 0;
      this.renderBounds.resetEmpty();
      this.diffuseTexture = null;
      this.rasterizer = rasterizer;
      this.lightingEnabled = false;
      this.ambientIntensity = 0.4F;
      this.lightDirection.set(1.0F, -1.0F, 0.0F);
      this.lightDirection.normalize();
      this.lightIntensity = 1.0F;
      this.fov = DEFAULT_FOV;
      this.viewportWidth = 640.0F;
      this.focalLength = this.computeFocalLength();
      this.perspectiveEnabled = false;
      this.perspectiveScale = 1.0F;
   }

   public final void enablePerspective(float scale) {
      this.perspectiveEnabled = true;
      this.perspectiveScale = scale;
   }

   public final void disablePerspective() {
      this.perspectiveEnabled = false;
      this.perspectiveScale = 1.0F;
   }

   protected PolygonRenderCommand obtainPolygonCommand() {
      PolygonRenderCommand cmd = this.freeCommandHead;
      if (cmd != null) {
         this.freeCommandHead = (PolygonRenderCommand)cmd.next;
         return cmd;
      } else {
         return new PolygonRenderCommand(this);
      }
   }

   protected void recyclePolygonCommand(PolygonRenderCommand command) {
      command.next = this.freeCommandHead;
      this.freeCommandHead = command;
   }

   protected void ensureCapacity(BacModel model, boolean hasLighting) {
      if (this.vertexDepths.length < model.getVertexCount()) {
         this.screenCoords = new int[model.getVertexCount() * 2];
         this.vertexDepths = new float[model.getVertexCount()];
      }

      if (hasLighting && this.vertexLighting.length < model.getVertexCount() * 3) {
         this.vertexLighting = new float[model.getVertexCount() * 3];
      }
   }

   public void initPacketTable(int capacity, float nearZ, float farZ) {
      Debug.assertTrue(this.packetTableEmpty);
      Debug.assertTrue(capacity > 0);
      Debug.assertTrue(nearZ > 0.0F && nearZ < farZ);
      this.packetTable = new RenderCommand[capacity];
      this.nearZ = nearZ;
      this.farZ = farZ;
      this.depthBucketSize = (this.farZ - this.nearZ) / this.packetTable.length;
   }

   public void setDepthRange(float nearZ, float farZ) {
      Debug.assertTrue(nearZ > 0.0F && nearZ < farZ);
      Debug.assertTrue(this.packetTable.length > 0);
      this.nearZ = nearZ;
      this.farZ = farZ;
      this.depthBucketSize = (this.farZ - this.nearZ) / this.packetTable.length;
   }

   public void setViewportOffset(int offsetX, int offsetY) {
      this.viewportOffsetX = offsetX;
      this.viewportOffsetY = offsetY;
   }

   private final int computeDepthBucket(float depth) {
      return !(depth < this.nearZ) && !(depth >= this.farZ) ? (int)((depth - this.nearZ) / this.depthBucketSize) : -1;
   }

   public final void resetRenderBounds() {
      this.renderBounds.resetEmpty();
   }

   public final void getRenderBounds(BoundingBox outBounds) {
      outBounds.setBounds(this.renderBounds);
   }

   public void setDiffuseTexture(Texture texture) {
      this.diffuseTexture = texture;
   }

   public void setSphereMapTexture(Texture texture) {
      this.sphereMapTexture = texture;
   }

   public void setProjection(BacModel model, CameraNode camera) {
      try {
         this.packetTableEmpty = false;
         boolean hasLighting = this.lightingEnabled && model.getNormals() != null;
         this.ensureCapacity(model, hasLighting);
         Transform3D modelView = new Transform3D();
         Transform3D boneTransform = new Transform3D();
         Transform3D worldTransform = new Transform3D();
         Transform3D relBoneTransform = new Transform3D();
         Transform3D invWorld = new Transform3D();
         Transform3D invModelView = new Transform3D();
         Transform3D normalMatrix = new Transform3D();
         Vector3f localLightDir = new Vector3f();
         Vector3f scaledLightDir = new Vector3f();
         Vector3f localViewDir = new Vector3f();
         int[] coords = this.screenCoords;
         float[] depths = this.vertexDepths;
         camera.computeModelViewTransform(model.getRootNode(), modelView);
         int boneCount = model.getBoneCount();
         int vertOffset = 0;

         for (int i = 0; i < boneCount; i++) {
            BoneNode bone = model.getBone(i);
            bone.getTransformRelativeToRoot(relBoneTransform);
            boneTransform.multiply(modelView, relBoneTransform);
            if (this.perspectiveEnabled) {
               boneTransform.transformAndProjectPerspective(model.getVertices(), coords, depths, vertOffset, bone.getIndex(), this.perspectiveScale);
            } else {
               boneTransform.transformAndProjectOrthographic(model.getVertices(), coords, depths, vertOffset, bone.getIndex(), this.focalLength);
            }

            if (hasLighting) {
               model.getRootNode().getWorldTransform(worldTransform);
               worldTransform.multiply(relBoneTransform);
               invWorld.invert(worldTransform);
               invWorld.normalizeColumns();
               invWorld.rotateVector(this.lightDirection, localLightDir);
               scaledLightDir.setScaled(localLightDir, this.lightIntensity);
               invModelView.invert(boneTransform);
               invModelView.normalizeColumns();
               invModelView.rotateVector(VIEW_DIR_Z, localViewDir);
               normalMatrix.set(boneTransform);
               normalMatrix.normalizeColumns();
               if (this.sphereMapTexture != null) {
                  this.computeSphereMapLighting(scaledLightDir, normalMatrix, model.getNormals(), vertOffset, bone.getIndex());
               } else {
                  this.computeVertexLighting(localLightDir, scaledLightDir, localViewDir, model.getNormals(), vertOffset, bone.getIndex());
               }
            }

            vertOffset += bone.getIndex();
         }

         int sphereW = this.sphereMapTexture != null ? this.sphereMapTexture.getWidth() : 0;
         int sphereH = this.sphereMapTexture != null ? this.sphereMapTexture.getHeight() : 0;
         RasterVertex[] tempVerts = new RasterVertex[4];
         PolygonRenderCommand cmd = this.obtainPolygonCommand();
         tempVerts[0] = cmd.v0;
         tempVerts[1] = cmd.v1;
         tempVerts[2] = cmd.v2;
         tempVerts[3] = cmd.v3;

         for (int pIdx = 0; pIdx < model.getPolygonCount(); pIdx++) {
            ModelPolygon poly = model.getPolygon(pIdx);
            int vCount = poly.vertexCount;
            Debug.assertTrue(vCount >= 3 && vCount <= 4);
            int flags = poly.flags;
            int p0 = poly.vert0 * 2;
            int p1 = poly.vert1 * 2;
            int p2 = poly.vert2 * 2;
            if ((flags & FLAG_DOUBLE_SIDED) == 0) {
               int cross = (coords[p1 + 0] - coords[p0 + 0]) * (coords[p2 + 1] - coords[p1 + 1])
                  - (coords[p1 + 1] - coords[p0 + 1]) * (coords[p2 + 0] - coords[p1 + 0]);
               if (vCount == 3) {
                  if (cross <= 0) {
                     continue;
                  }
               } else {
                  int p3 = poly.vert3 * 2;
                  int cross2 = (coords[p2 + 0] - coords[p1 + 0]) * (coords[p3 + 1] - coords[p2 + 1])
                     - (coords[p2 + 1] - coords[p1 + 1]) * (coords[p3 + 0] - coords[p2 + 0]);
                  if (cross - cross2 <= 0) {
                     continue;
                  }
               }
            }

            float minZ = Float.MAX_VALUE;
            float maxZ = -Float.MAX_VALUE;
            tempVerts[0].x = coords[p0 + 0] + this.viewportOffsetX;
            tempVerts[0].y = coords[p0 + 1] + this.viewportOffsetY;
            tempVerts[1].x = coords[p1 + 0] + this.viewportOffsetX;
            tempVerts[1].y = coords[p1 + 1] + this.viewportOffsetY;
            tempVerts[2].x = coords[p2 + 0] + this.viewportOffsetX;
            tempVerts[2].y = coords[p2 + 1] + this.viewportOffsetY;
            if (depths[poly.vert0] < minZ) {
               minZ = depths[poly.vert0];
            }

            if (depths[poly.vert1] < minZ) {
               minZ = depths[poly.vert1];
            }

            if (depths[poly.vert2] < minZ) {
               minZ = depths[poly.vert2];
            }

            if (depths[poly.vert0] > maxZ) {
               maxZ = depths[poly.vert0];
            }

            if (depths[poly.vert1] > maxZ) {
               maxZ = depths[poly.vert1];
            }

            if (depths[poly.vert2] > maxZ) {
               maxZ = depths[poly.vert2];
            }

            int code0 = this.rasterizer.computeOutcode(tempVerts[0]);
            int code1 = this.rasterizer.computeOutcode(tempVerts[1]);
            int code2 = this.rasterizer.computeOutcode(tempVerts[2]);
            int outcodeOr = code0 | code1 | code2;
            int outcodeAnd = code0 & code1 & code2;
            if (vCount == 4) {
               int p3 = poly.vert3 * 2;
               tempVerts[3].x = coords[p3 + 0] + this.viewportOffsetX;
               tempVerts[3].y = coords[p3 + 1] + this.viewportOffsetY;
               if (depths[poly.vert3] < minZ) {
                  minZ = depths[poly.vert3];
               }

               if (depths[poly.vert3] > maxZ) {
                  maxZ = depths[poly.vert3];
               }

               int code3 = this.rasterizer.computeOutcode(tempVerts[3]);
               outcodeOr |= code3;
               outcodeAnd &= code3;
            }

            int bucket = this.computeDepthBucket((minZ + maxZ) / 2.0F);
            if (outcodeAnd == 0 && bucket >= 0) {
               boolean litMode;
               if (!hasLighting) {
                  flags &= ~FLAG_SPHERE_MAP;
                  flags &= ~FLAG_LIGHTING;
                  flags &= ~FLAG_SPECULAR;
                  litMode = false;
               } else {
                  litMode = (flags & FLAG_LIGHTING) != 0;
               }

               if (!litMode) {
                  tempVerts[0].u = poly.u0;
                  tempVerts[0].v = poly.v0;
                  tempVerts[1].u = poly.u1;
                  tempVerts[1].v = poly.v1;
                  tempVerts[2].u = poly.u2;
                  tempVerts[2].v = poly.v2;
                  if (vCount == 4) {
                     tempVerts[3].u = poly.u3;
                     tempVerts[3].v = poly.v3;
                  }
               } else {
                  int l0 = poly.vert0 * 3;
                  int l1 = poly.vert1 * 3;
                  int l2 = poly.vert2 * 3;
                  int l3 = vCount == 4 ? poly.vert3 * 3 : 0;
                  if ((flags & FLAG_SPHERE_MAP) == 0) {
                     tempVerts[0].u = poly.u0;
                     tempVerts[0].v = poly.v0;
                     tempVerts[1].u = poly.u1;
                     tempVerts[1].v = poly.v1;
                     tempVerts[2].u = poly.u2;
                     tempVerts[2].v = poly.v2;
                     tempVerts[0].normalZ = tempVerts[1].normalZ = tempVerts[2].normalZ = 0;
                     int lightVal = (int)(this.vertexLighting[l0 + 0] * 255.0F);
                     if (lightVal < 0) {
                        lightVal = 0;
                     } else if (lightVal > 255) {
                        lightVal = 255;
                     }

                     tempVerts[0].light = lightVal;
                     lightVal = (int)(this.vertexLighting[l1 + 0] * 255.0F);
                     if (lightVal < 0) {
                        lightVal = 0;
                     } else if (lightVal > 255) {
                        lightVal = 255;
                     }

                     tempVerts[1].light = lightVal;
                     lightVal = (int)(this.vertexLighting[l2 + 0] * 255.0F);
                     if (lightVal < 0) {
                        lightVal = 0;
                     } else if (lightVal > 255) {
                        lightVal = 255;
                     }

                     tempVerts[2].light = lightVal;
                     if (vCount == 4) {
                        tempVerts[3].u = poly.u3;
                        tempVerts[3].v = poly.v3;
                        tempVerts[3].normalZ = 0;
                        lightVal = (int)(this.vertexLighting[l3 + 0] * 255.0F);
                        if (lightVal < 0) {
                           lightVal = 0;
                        } else if (lightVal > 255) {
                           lightVal = 255;
                        }

                        tempVerts[3].light = lightVal;
                     }
                  } else if (this.sphereMapTexture != null) {
                     tempVerts[0].u = poly.u0;
                     tempVerts[0].v = poly.v0;
                     tempVerts[1].u = poly.u1;
                     tempVerts[1].v = poly.v1;
                     tempVerts[2].u = poly.u2;
                     tempVerts[2].v = poly.v2;
                     int lightVal = (int)(this.vertexLighting[l0 + 0] * 255.0F);
                     if (lightVal < 0) {
                        lightVal = 0;
                     } else if (lightVal > 255) {
                        lightVal = 255;
                     }

                     tempVerts[0].light = lightVal;
                     lightVal = (int)(this.vertexLighting[l1 + 0] * 255.0F);
                     if (lightVal < 0) {
                        lightVal = 0;
                     } else if (lightVal > 255) {
                        lightVal = 255;
                     }

                     tempVerts[1].light = lightVal;
                     lightVal = (int)(this.vertexLighting[l2 + 0] * 255.0F);
                     if (lightVal < 0) {
                        lightVal = 0;
                     } else if (lightVal > 255) {
                        lightVal = 255;
                     }

                     tempVerts[2].light = lightVal;
                     tempVerts[0].normalZ = (int)(this.vertexLighting[l0 + 1] * sphereW);
                     tempVerts[0].sphereV = (int)(this.vertexLighting[l0 + 2] * sphereH);
                     tempVerts[1].normalZ = (int)(this.vertexLighting[l1 + 1] * sphereW);
                     tempVerts[1].sphereV = (int)(this.vertexLighting[l1 + 2] * sphereH);
                     tempVerts[2].normalZ = (int)(this.vertexLighting[l2 + 1] * sphereW);
                     tempVerts[2].sphereV = (int)(this.vertexLighting[l2 + 2] * sphereH);
                     if (vCount == 4) {
                        tempVerts[3].u = poly.u3;
                        tempVerts[3].v = poly.v3;
                        lightVal = (int)(this.vertexLighting[l3 + 0] * 255.0F);
                        if (lightVal < 0) {
                           lightVal = 0;
                        } else if (lightVal > 255) {
                           lightVal = 255;
                        }

                        tempVerts[3].light = lightVal;
                        tempVerts[3].normalZ = (int)(this.vertexLighting[l3 + 1] * sphereW);
                        tempVerts[3].sphereV = (int)(this.vertexLighting[l3 + 2] * sphereH);
                     }
                  } else {
                     tempVerts[0].u = poly.u0;
                     tempVerts[0].v = poly.v0;
                     tempVerts[1].u = poly.u1;
                     tempVerts[1].v = poly.v1;
                     tempVerts[2].u = poly.u2;
                     tempVerts[2].v = poly.v2;
                     int lightVal = (int)(this.vertexLighting[l0 + 0] * 255.0F);
                     if (lightVal < 0) {
                        lightVal = 0;
                     } else if (lightVal > 255) {
                        lightVal = 255;
                     }

                     tempVerts[0].light = lightVal;
                     lightVal = (int)(this.vertexLighting[l1 + 0] * 255.0F);
                     if (lightVal < 0) {
                        lightVal = 0;
                     } else if (lightVal > 255) {
                        lightVal = 255;
                     }

                     tempVerts[1].light = lightVal;
                     lightVal = (int)(this.vertexLighting[l2 + 0] * 255.0F);
                     if (lightVal < 0) {
                        lightVal = 0;
                     } else if (lightVal > 255) {
                        lightVal = 255;
                     }

                     tempVerts[2].light = lightVal;
                     int specVal = 0;
                     if ((flags & FLAG_SPECULAR) != 0) {
                        specVal = (int)(this.vertexLighting[l0 + 1] * 255.0F);
                        if (specVal < 0) {
                           specVal = 0;
                        } else if (specVal > 255) {
                           specVal = 255;
                        }
                     }

                     tempVerts[0].normalZ = specVal;
                     specVal = 0;
                     if ((flags & FLAG_SPECULAR) != 0) {
                        specVal = (int)(this.vertexLighting[l1 + 1] * 255.0F);
                        if (specVal < 0) {
                           specVal = 0;
                        } else if (specVal > 255) {
                           specVal = 255;
                        }
                     }

                     tempVerts[1].normalZ = specVal;
                     specVal = 0;
                     if ((flags & FLAG_SPECULAR) != 0) {
                        specVal = (int)(this.vertexLighting[l2 + 1] * 255.0F);
                        if (specVal < 0) {
                           specVal = 0;
                        } else if (specVal > 255) {
                           specVal = 255;
                        }
                     }

                     tempVerts[2].normalZ = specVal;
                     if (vCount == 4) {
                        tempVerts[3].u = poly.u3;
                        tempVerts[3].v = poly.v3;
                        lightVal = (int)(this.vertexLighting[l3 + 0] * 255.0F);
                        if (lightVal < 0) {
                           lightVal = 0;
                        } else if (lightVal > 255) {
                           lightVal = 255;
                        }

                        tempVerts[3].light = lightVal;
                        specVal = 0;
                        if ((flags & FLAG_SPECULAR) != 0) {
                           specVal = (int)(this.vertexLighting[l3 + 1] * 255.0F);
                           if (specVal < 0) {
                              specVal = 0;
                           } else if (specVal > 255) {
                              specVal = 255;
                           }
                        }

                        tempVerts[3].normalZ = specVal;
                     }
                  }
               }

               cmd.vertexCount = vCount;
               cmd.diffuseTexture = this.diffuseTexture;
               cmd.sphereMapTexture = this.sphereMapTexture;
               cmd.renderFlags = flags;
               cmd.clipped = outcodeOr;
               cmd.next = this.packetTable[bucket];
               cmd.updateBounds(this.renderBounds);
               this.packetTable[bucket] = cmd;
               cmd = this.obtainPolygonCommand();
               tempVerts[0] = cmd.v0;
               tempVerts[1] = cmd.v1;
               tempVerts[2] = cmd.v2;
               tempVerts[3] = cmd.v3;
            }
         }

         this.recyclePolygonCommand(cmd);
      } catch (NullPointerException e) {
      }
   }

   public void clearPacketTable() {
      if (!this.packetTableEmpty) {
         for (int i = this.packetTable.length - 1; i >= 0; i--) {
            RenderCommand cmd = this.packetTable[i];

            while (cmd != null) {
               RenderCommand next = cmd.next;
               switch (cmd.commandType) {
                  case COMMAND_TYPE_LINE:
                     this.drawPolygon((PolygonRenderCommand)cmd);
                     this.recyclePolygonCommand((PolygonRenderCommand)cmd);
                  default:
                     cmd = next;
               }
            }

            this.packetTable[i] = null;
         }

         this.packetTableEmpty = true;
      }
   }

   public void flush() {
      if (!this.packetTableEmpty) {
         for (int i = this.packetTable.length - 1; i >= 0; i--) {
            RenderCommand cmd = this.packetTable[i];

            while (cmd != null) {
               RenderCommand next = cmd.next;
               switch (cmd.commandType) {
                  case COMMAND_TYPE_LINE:
                     this.recyclePolygonCommand((PolygonRenderCommand)cmd);
                  default:
                     cmd = next;
               }
            }

            this.packetTable[i] = null;
         }

         this.packetTableEmpty = true;
      }
   }

   protected void drawPolygon(PolygonRenderCommand command) {
      int flags = command.renderFlags;
      this.rasterizer.setDiffuseTexture(command.diffuseTexture);
      int blendIndex = (flags & FLAG_BLEND_TRANSPARENT) != 0 ? 1 : 0;
      int clipIndex = command.clipped != 0 ? 1 : 0;
      int alpha = (flags & 4080) >>> 4;
      int modMode;
      if ((flags & FLAG_ALPHA_BLEND) != 0 && alpha != MAX_ALPHA) {
         modMode = alpha != 0 && alpha != 128 ? 2 : 1;
      } else if ((flags & FLAG_LIGHTING) != 0) {
         modMode = 3;
      } else {
         modMode = 0;
      }

      this.rasterizer.setBlendAlpha(alpha);

      try {
         if ((flags & FLAG_SPHERE_MAP) != 0) {
            if (command.sphereMapTexture != null) {
               this.rasterizer.setSphereMapTexture(command.sphereMapTexture);
               SphereMapDrawer drawer = this.rasterizer.sphereMapDrawers[modMode][blendIndex][clipIndex];
               this.rasterizer.rasterizeSphereMapTriangle(drawer, command.v0, command.v1, command.v2);
               if (command.vertexCount == 4) {
                  this.rasterizer.rasterizeSphereMapTriangle(drawer, command.v1, command.v2, command.v3);
               }
            } else {
               int totalLight = command.v1.light + command.v2.light;
               if (command.v0.light + totalLight == 0) {
                  this.rasterizer.rasterizeUnlitTriangle(this.rasterizer.unlitDrawers[modMode][blendIndex][clipIndex], command.v0, command.v1, command.v2);
               } else {
                  this.rasterizer.rasterizeLitTriangle(this.rasterizer.litDrawers[modMode][blendIndex][clipIndex], command.v0, command.v1, command.v2);
               }

               if (command.vertexCount == 4) {
                  if (command.v3.light + totalLight == 0) {
                     this.rasterizer.rasterizeUnlitTriangle(this.rasterizer.unlitDrawers[modMode][blendIndex][clipIndex], command.v1, command.v2, command.v3);
                  } else {
                     this.rasterizer.rasterizeLitTriangle(this.rasterizer.litDrawers[modMode][blendIndex][clipIndex], command.v1, command.v2, command.v3);
                  }
               }
            }
         } else {
            TexturedDrawer drawer = this.rasterizer.texturedDrawers[modMode][blendIndex][clipIndex];
            this.rasterizer.rasterizeTexturedTriangle(drawer, command.v0, command.v1, command.v2);
            if (command.vertexCount == 4) {
               this.rasterizer.rasterizeTexturedTriangle(drawer, command.v1, command.v2, command.v3);
            }
         }
      } catch (ArrayIndexOutOfBoundsException e) {
      }
   }

   public void setLightingEnabled(boolean enabled) {
      this.lightingEnabled = enabled;
   }

   public void setAmbientIntensity(float intensity) {
      this.ambientIntensity = intensity;
   }

   public void setDirectionalLight(Vector3f direction, float intensity) {
      this.lightDirection.setNegative(direction);
      this.lightDirection.normalize();
      this.lightIntensity = intensity;
   }

   protected void computeVertexLighting(Vector3f lightDir, Vector3f scaledLightDir, Vector3f viewDir, Vector3f[] normals, int normalOffset, int count) {
      float lx = lightDir.x;
      float ly = lightDir.y;
      float lz = lightDir.z;
      float hx = lx + viewDir.x;
      float hy = ly + viewDir.y;
      float hz = lz + viewDir.z;
      float invLen = 1.0F / (float)Math.sqrt(hx * hx + hy * hy + hz * hz);
      hx *= invLen;
      hy *= invLen;
      hz *= invLen;
      int srcIdx = normalOffset;
      int dstIdx = normalOffset * 3;

      for (int i = 0; i < count; i++) {
         Vector3f normal = normals[srcIdx];
         float diffuse = this.ambientIntensity;
         float dotLight = normal.dot(scaledLightDir);
         if (dotLight > 0.0F) {
            diffuse += dotLight;
         }

         this.vertexLighting[dstIdx + 0] = diffuse;
         float specular = 0.0F;
         if (dotLight > 0.0F) {
            float dotHalf = normal.x * hx + normal.y * hy + normal.z * hz;
            if (dotHalf > 0.0F) {
               specular = dotHalf;
            }
         }

         this.vertexLighting[dstIdx + 1] = specular;
         srcIdx++;
         dstIdx += 3;
      }
   }

   protected void computeSphereMapLighting(Vector3f scaledLightDir, Transform3D normalMatrix, Vector3f[] normals, int normalOffset, int count) {
      float lx = scaledLightDir.x;
      float ly = scaledLightDir.y;
      float lz = scaledLightDir.z;
      float m00Half = normalMatrix.m00 * 0.5F;
      float m01Half = normalMatrix.m01 * 0.5F;
      float m02Half = normalMatrix.m02 * 0.5F;
      float m10Half = normalMatrix.m10 * 0.5F;
      float m11Half = normalMatrix.m11 * 0.5F;
      float m12Half = normalMatrix.m12 * 0.5F;
      int srcIdx = normalOffset;
      int dstIdx = normalOffset * 3;

      for (int i = 0; i < count; i++) {
         Vector3f normal = normals[srcIdx];
         float diffuse = this.ambientIntensity;
         float dotLight = lx * normal.x + ly * normal.y + lz * normal.z;
         if (dotLight > 0.0F) {
            diffuse += dotLight;
         }

         this.vertexLighting[dstIdx + 0] = diffuse;
         this.vertexLighting[dstIdx + 1] = m00Half * normal.x + m01Half * normal.y + m02Half * normal.z + 0.5F;
         this.vertexLighting[dstIdx + 2] = m10Half * normal.x + m11Half * normal.y + m12Half * normal.z + 0.5F;
         srcIdx++;
         dstIdx += 3;
      }
   }
}
