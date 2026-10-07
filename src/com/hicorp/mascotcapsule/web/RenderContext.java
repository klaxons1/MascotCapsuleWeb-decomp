package com.hicorp.mascotcapsule.web;

final class RenderContext {
   public static final int var_63 = 1;
   public static final int var_84 = 2;
   public static final int var_e3 = 4096;
   public static final int var_110 = 8192;
   public static final int var_15f = 16384;
   public static final int var_183 = 32768;
   public static final int var_194 = 255;
   public static final int var_1eb = 16;
   public static final int var_240 = 255;
   public static final int var_2ef = 24;
   public static final int var_309 = 0;
   public static final int var_333 = 1;
   private static final boolean var_390 = true;
   private static final boolean var_3d2 = true;
   private static final boolean var_404 = false;
   protected int[] screenCoords;
   protected float[] vertexDepths;
   protected static final int var_4b9 = 256;
   protected boolean packetTableEmpty = true;
   protected RenderCommand[] packetTable;
   protected PolygonRenderCommand freeCommandHead = null;
   protected float[] vertexLighting;
   protected static final int var_5a1 = 3;
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
   float lightIntensity;
   private float focalLength;
   private boolean perspectiveEnabled;
   private float perspectiveScale;
   private static final float var_96e = 640.0F;
   private static final float DEFAULT_FOV = MatrixUtils.toRadians(60.0F);
   private float fov;
   private float viewportWidth;
   private static final Vector3f VIEW_DIR_Z = new Vector3f(0.0F, 0.0F, -1.0F);

   private final float computeFocalLength() {
      return (float)(this.viewportWidth / 2.0 / Math.tan(this.fov / 2.0));
   }

   public RenderContext(Config var1) {
      this.screenCoords = new int[512];
      this.vertexDepths = new float[256];
      this.vertexLighting = new float[0];
      this.packetTable = null;
      this.viewportOffsetX = 0;
      this.viewportOffsetY = 0;
      this.renderBounds.resetEmpty();
      this.diffuseTexture = null;
      this.rasterizer = var1;
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

   public final void enablePerspective(float var1) {
      this.perspectiveEnabled = true;
      this.perspectiveScale = var1;
   }

   public final void disablePerspective() {
      this.perspectiveEnabled = false;
      this.perspectiveScale = 1.0F;
   }

   protected PolygonRenderCommand obtainPolygonCommand() {
      PolygonRenderCommand var1 = this.freeCommandHead;
      if (var1 != null) {
         this.freeCommandHead = (PolygonRenderCommand)var1.next;
         return var1;
      } else {
         return new PolygonRenderCommand(this);
      }
   }

   protected void recyclePolygonCommand(PolygonRenderCommand var1) {
      var1.next = this.freeCommandHead;
      this.freeCommandHead = var1;
   }

   protected void ensureCapacity(BacModel var1, boolean var2) {
      if (this.vertexDepths.length < var1.getVertexCount()) {
         this.screenCoords = new int[var1.getVertexCount() * 2];
         this.vertexDepths = new float[var1.getVertexCount()];
      }

      if (var2 && this.vertexLighting.length < var1.getVertexCount() * 3) {
         this.vertexLighting = new float[var1.getVertexCount() * 3];
      }
   }

   public void initPacketTable(int var1, float var2, float var3) {
      Debug.assertTrue(this.packetTableEmpty);
      Debug.assertTrue(var1 > 0);
      Debug.assertTrue(var2 > 0.0F && var2 < var3);
      this.packetTable = new RenderCommand[var1];
      this.nearZ = var2;
      this.farZ = var3;
      this.depthBucketSize = (this.farZ - this.nearZ) / this.packetTable.length;
   }

   public void setDepthRange(float var1, float var2) {
      Debug.assertTrue(var1 > 0.0F && var1 < var2);
      Debug.assertTrue(this.packetTable.length > 0);
      this.nearZ = var1;
      this.farZ = var2;
      this.depthBucketSize = (this.farZ - this.nearZ) / this.packetTable.length;
   }

   public void setViewportOffset(int var1, int var2) {
      this.viewportOffsetX = var1;
      this.viewportOffsetY = var2;
   }

   private final int computeDepthBucket(float var1) {
      return !(var1 < this.nearZ) && !(var1 >= this.farZ) ? (int)((var1 - this.nearZ) / this.depthBucketSize) : -1;
   }

   public final void resetRenderBounds() {
      this.renderBounds.resetEmpty();
   }

   public final void getRenderBounds(BoundingBox var1) {
      var1.setBounds(this.renderBounds);
   }

   public void setDiffuseTexture(Texture var1) {
      this.diffuseTexture = var1;
   }

   public void setSphereMapTexture(Texture var1) {
      this.sphereMapTexture = var1;
   }

   public void setProjection(BacModel var1, CameraNode var2) {
      try {
         this.packetTableEmpty = false;
         boolean var3 = this.lightingEnabled && var1.getNormals() != null;
         this.ensureCapacity(var1, var3);
         Transform3D var4 = new Transform3D();
         Transform3D var5 = new Transform3D();
         Transform3D var6 = new Transform3D();
         Transform3D var7 = new Transform3D();
         Transform3D var8 = new Transform3D();
         Transform3D var9 = new Transform3D();
         Transform3D var10 = new Transform3D();
         Vector3f var11 = new Vector3f();
         Vector3f var12 = new Vector3f();
         Vector3f var13 = new Vector3f();
         int[] var14 = this.screenCoords;
         float[] var15 = this.vertexDepths;
         var2.computeModelViewTransform(var1.getRootNode(), var4);
         int var16 = var1.getBoneCount();
         int var17 = 0;
         new Vector3f();

         for (int var19 = 0; var19 < var16; var19++) {
            BoneNode var20 = var1.getBone(var19);
            var20.getTransformRelativeToRoot(var7);
            var5.multiply(var4, var7);
            if (this.perspectiveEnabled) {
               var5.transformAndProjectPerspective(var1.getVertices(), var14, var15, var17, var20.getIndex(), this.perspectiveScale);
            } else {
               var5.transformAndProjectOrthographic(var1.getVertices(), var14, var15, var17, var20.getIndex(), this.focalLength);
            }

            if (var3) {
               var1.getRootNode().getWorldTransform(var6);
               var6.multiply(var7);
               var8.invert(var6);
               var8.normalizeColumns();
               var8.rotateVector(this.lightDirection, var11);
               var12.setScaled(var11, this.lightIntensity);
               var9.invert(var5);
               var9.normalizeColumns();
               var9.rotateVector(VIEW_DIR_Z, var13);
               var10.set(var5);
               var10.normalizeColumns();
               if (this.sphereMapTexture != null) {
                  this.computeSphereMapLighting(var12, var10, var1.getNormals(), var17, var20.getIndex());
               } else {
                  this.computeVertexLighting(var11, var12, var13, var1.getNormals(), var17, var20.getIndex());
               }
            }

            var17 += var20.getIndex();
         }

         int var48 = this.sphereMapTexture != null ? this.sphereMapTexture.getWidth() : 0;
         int var21 = this.sphereMapTexture != null ? this.sphereMapTexture.getHeight() : 0;
         RasterVertex[] var22 = new RasterVertex[4];
         PolygonRenderCommand var23 = this.obtainPolygonCommand();
         var22[0] = var23.v0;
         var22[1] = var23.v1;
         var22[2] = var23.v2;
         var22[3] = var23.v3;

         for (int var24 = 0; var24 < var1.getPolygonCount(); var24++) {
            ModelPolygon var25 = var1.getPolygon(var24);
            int var26 = var25.vertexCount;
            Debug.assertTrue(var26 >= 3 && var26 <= 4);
            int var27 = var25.flags;
            int var28 = var25.vert0 * 2;
            int var29 = var25.vert1 * 2;
            int var30 = var25.vert2 * 2;
            if ((var27 & 1) == 0) {
               int var31 = (var14[var29 + 0] - var14[var28 + 0]) * (var14[var30 + 1] - var14[var29 + 1])
                  - (var14[var29 + 1] - var14[var28 + 1]) * (var14[var30 + 0] - var14[var29 + 0]);
               if (var26 == 3) {
                  if (var31 <= 0) {
                     continue;
                  }
               } else {
                  int var32 = var25.vert3 * 2;
                  int var33 = (var14[var30 + 0] - var14[var29 + 0]) * (var14[var32 + 1] - var14[var30 + 1])
                     - (var14[var30 + 1] - var14[var29 + 1]) * (var14[var32 + 0] - var14[var30 + 0]);
                  if (var31 - var33 <= 0) {
                     continue;
                  }
               }
            }

            float var49 = Float.MAX_VALUE;
            float var50 = -Float.MAX_VALUE;
            var22[0].x = var14[var28 + 0] + this.viewportOffsetX;
            var22[0].y = var14[var28 + 1] + this.viewportOffsetY;
            var22[1].x = var14[var29 + 0] + this.viewportOffsetX;
            var22[1].y = var14[var29 + 1] + this.viewportOffsetY;
            var22[2].x = var14[var30 + 0] + this.viewportOffsetX;
            var22[2].y = var14[var30 + 1] + this.viewportOffsetY;
            if (var15[var25.vert0] < var49) {
               var49 = var15[var25.vert0];
            }

            if (var15[var25.vert1] < var49) {
               var49 = var15[var25.vert1];
            }

            if (var15[var25.vert2] < var49) {
               var49 = var15[var25.vert2];
            }

            if (var15[var25.vert0] > var50) {
               var50 = var15[var25.vert0];
            }

            if (var15[var25.vert1] > var50) {
               var50 = var15[var25.vert1];
            }

            if (var15[var25.vert2] > var50) {
               var50 = var15[var25.vert2];
            }

            int var35 = this.rasterizer.computeOutcode(var22[0]);
            int var36 = this.rasterizer.computeOutcode(var22[1]);
            int var37 = this.rasterizer.computeOutcode(var22[2]);
            int var51 = var35 | var36 | var37;
            int var34 = var35 & var36 & var37;
            if (var26 == 4) {
               var35 = var25.vert3 * 2;
               var22[3].x = var14[var35 + 0] + this.viewportOffsetX;
               var22[3].y = var14[var35 + 1] + this.viewportOffsetY;
               if (var15[var25.vert3] < var49) {
                  var49 = var15[var25.vert3];
               }

               if (var15[var25.vert3] > var50) {
                  var50 = var15[var25.vert3];
               }

               var36 = this.rasterizer.computeOutcode(var22[3]);
               var51 |= var36;
               var34 &= var36;
            }

            var35 = this.computeDepthBucket((var49 + var50) / 2.0F);
            if (var34 == 0 && var35 >= 0) {
               boolean var55;
               if (!var3) {
                  var27 &= -32769;
                  var55 = false;
               } else {
                  var55 = (var27 & 32768) != 0;
               }

               if (!var55) {
                  var22[0].u = var25.u0;
                  var22[0].v = var25.vert0;
                  var22[1].u = var25.u1;
                  var22[1].v = var25.vert1;
                  var22[2].u = var25.u2;
                  var22[2].v = var25.vert2;
                  if (var26 == 4) {
                     var22[3].u = var25.u3;
                     var22[3].v = var25.vert3;
                  }
               } else {
                  var37 = var27 >>> 16 & 0xFF;
                  int var38 = 255 - var37;
                  if (var37 <= 0) {
                     var23.sphereMapTexture = null;
                     var22[0].u = var25.u0;
                     var22[0].v = var25.vert0;
                     var22[1].u = var25.u1;
                     var22[1].v = var25.vert1;
                     var22[2].u = var25.u2;
                     var22[2].v = var25.vert2;
                     var22[0].normalZ = var22[1].normalZ = var22[2].normalZ = 0;
                     int var39 = (int)(this.vertexLighting[var25.vert0 * 3] * var38);
                     if (var39 > 255) {
                        var39 = 255;
                     }

                     var22[0].light = var39;
                     var39 = (int)(this.vertexLighting[var25.vert1 * 3] * var38);
                     if (var39 > 255) {
                        var39 = 255;
                     }

                     var22[1].light = var39;
                     var39 = (int)(this.vertexLighting[var25.vert2 * 3] * var38);
                     if (var39 > 255) {
                        var39 = 255;
                     }

                     var22[2].light = var39;
                     if (var26 == 4) {
                        var22[3].u = var25.u3;
                        var22[3].v = var25.vert3;
                        var22[3].normalZ = 0;
                        var39 = (int)(this.vertexLighting[var25.vert3 * 3] * var38);
                        if (var39 > 255) {
                           var39 = 255;
                        }

                        var22[3].light = var39;
                     }
                  } else if (this.sphereMapTexture != null) {
                     var23.sphereMapTexture = this.sphereMapTexture;
                     var22[0].u = var25.u0;
                     var22[0].v = var25.vert0;
                     var22[1].u = var25.u1;
                     var22[1].v = var25.vert1;
                     var22[2].u = var25.u2;
                     var22[2].v = var25.vert2;
                     int var40 = var25.vert0 * 3;
                     int var41 = var25.vert1 * 3;
                     int var42 = var25.vert2 * 3;
                     int var60 = (int)(this.vertexLighting[var40 + 0] * var38);
                     if (var60 > 255) {
                        var60 = 255;
                     }

                     var22[0].light = var60;
                     var60 = (int)(this.vertexLighting[var41 + 0] * var38);
                     if (var60 > 255) {
                        var60 = 255;
                     }

                     var22[1].light = var60;
                     var60 = (int)(this.vertexLighting[var42 + 0] * var38);
                     if (var60 > 255) {
                        var60 = 255;
                     }

                     var22[2].light = var60;
                     var22[0].normalZ = (int)(this.vertexLighting[var40 + 1] * var48);
                     var22[0].sphereV = (int)(this.vertexLighting[var40 + 2] * var21);
                     var22[1].normalZ = (int)(this.vertexLighting[var41 + 1] * var48);
                     var22[1].sphereV = (int)(this.vertexLighting[var41 + 2] * var21);
                     var22[2].normalZ = (int)(this.vertexLighting[var42 + 1] * var48);
                     var22[2].sphereV = (int)(this.vertexLighting[var42 + 2] * var21);
                     if (var26 == 4) {
                        var22[3].u = var25.u3;
                        var22[3].v = var25.vert3;
                        int var43 = var25.vert3 * 3;
                        var60 = (int)(this.vertexLighting[var43 + 0] * var38);
                        if (var60 > 255) {
                           var60 = 255;
                        }

                        var22[3].light = var60;
                        var22[3].normalZ = (int)(this.vertexLighting[var43 + 1] * var48);
                        var22[3].sphereV = (int)(this.vertexLighting[var43 + 2] * var21);
                     }
                  } else {
                     var23.sphereMapTexture = null;
                     var22[0].u = var25.u0;
                     var22[0].v = var25.vert0;
                     var22[1].u = var25.u1;
                     var22[1].v = var25.vert1;
                     var22[2].u = var25.u2;
                     var22[2].v = var25.vert2;
                     int var76 = var25.vert0 * 3;
                     int var77 = var25.vert1 * 3;
                     int var44 = var25.vert2 * 3;
                     int var64 = (int)(this.vertexLighting[var76 + 0] * var38);
                     if (var64 > 255) {
                        var64 = 255;
                     }

                     var22[0].light = var64;
                     var64 = (int)(this.vertexLighting[var77 + 0] * var38);
                     if (var64 > 255) {
                        var64 = 255;
                     }

                     var22[1].light = var64;
                     var64 = (int)(this.vertexLighting[var44 + 0] * var38);
                     if (var64 > 255) {
                        var64 = 255;
                     }

                     var22[2].light = var64;
                     float var72 = this.vertexLighting[var76 + 1];
                     int var68;
                     if (var72 > 0.0F) {
                        int var45 = var27 >>> 24 & 0xFF;
                        var68 = (int)((float)Math.pow(var72, 0.25 * var45) * this.lightIntensity * var37);
                        if (var68 > 255) {
                           var68 = 255;
                        }

                        if (var68 < 0) {
                           var68 = 0;
                        }
                     } else {
                        var68 = 0;
                     }

                     var22[0].normalZ = var68;
                     var72 = this.vertexLighting[var77 + 1];
                     if (var72 > 0.0F) {
                        int var78 = var27 >>> 24 & 0xFF;
                        var68 = (int)((float)Math.pow(var72, 0.25 * var78) * this.lightIntensity * var37);
                        if (var68 > 255) {
                           var68 = 255;
                        }

                        if (var68 < 0) {
                           var68 = 0;
                        }
                     } else {
                        var68 = 0;
                     }

                     var22[1].normalZ = var68;
                     var72 = this.vertexLighting[var44 + 1];
                     if (var72 > 0.0F) {
                        int var79 = var27 >>> 24 & 0xFF;
                        var68 = (int)((float)Math.pow(var72, 0.25 * var79) * this.lightIntensity * var37);
                        if (var68 > 255) {
                           var68 = 255;
                        }

                        if (var68 < 0) {
                           var68 = 0;
                        }
                     } else {
                        var68 = 0;
                     }

                     var22[2].normalZ = var68;
                     if (var26 == 4) {
                        var22[3].u = var25.u3;
                        var22[3].v = var25.vert3;
                        int var80 = var25.vert3 * 3;
                        var64 = (int)(this.vertexLighting[var80 + 0] * var38);
                        if (var64 > 255) {
                           var64 = 255;
                        }

                        var22[3].light = var64;
                        var72 = this.vertexLighting[var80 + 1];
                        if (var72 > 0.0F) {
                           int var46 = var27 >>> 24 & 0xFF;
                           var68 = (int)((float)Math.pow(var72, 0.25 * var46) * this.lightIntensity * var37);
                           if (var68 > 255) {
                              var68 = 255;
                           }

                           if (var68 < 0) {
                              var68 = 0;
                           }
                        } else {
                           var68 = 0;
                        }

                        var22[3].normalZ = var68;
                     }
                  }
               }

               var23.vertexCount = var26;
               var23.clipped = var51;
               var23.renderFlags = var27;
               var23.diffuseTexture = this.diffuseTexture;
               var23.updateBounds(this.renderBounds);
               var23.next = this.packetTable[var35];
               this.packetTable[var35] = var23;
               var23 = this.obtainPolygonCommand();
               var22[0] = var23.v0;
               var22[1] = var23.v1;
               var22[2] = var23.v2;
               var22[3] = var23.v3;
            }
         }

         this.recyclePolygonCommand(var23);
      } catch (NullPointerException var47) {
      }
   }

   public void clearPacketTable() {
      if (!this.packetTableEmpty) {
         for (int var1 = this.packetTable.length - 1; var1 >= 0; var1--) {
            RenderCommand var2 = this.packetTable[var1];

            while (var2 != null) {
               RenderCommand var3 = var2.next;
               switch (var2.commandType) {
                  case 1:
                     this.drawPolygon((PolygonRenderCommand)var2);
                     this.recyclePolygonCommand((PolygonRenderCommand)var2);
                  default:
                     var2 = var3;
               }
            }

            this.packetTable[var1] = null;
         }

         this.packetTableEmpty = true;
      }
   }

   public void flush() {
      if (!this.packetTableEmpty) {
         for (int var1 = this.packetTable.length - 1; var1 >= 0; var1--) {
            RenderCommand var2 = this.packetTable[var1];

            while (var2 != null) {
               RenderCommand var3 = var2.next;
               switch (var2.commandType) {
                  case 1:
                     this.recyclePolygonCommand((PolygonRenderCommand)var2);
                  default:
                     var2 = var3;
               }
            }

            this.packetTable[var1] = null;
         }

         this.packetTableEmpty = true;
      }
   }

   protected void drawPolygon(PolygonRenderCommand var1) {
      int var2 = var1.renderFlags;
      this.rasterizer.setDiffuseTexture(var1.diffuseTexture);
      int var3 = (var2 & 2) != 0 ? 1 : 0;
      int var4 = var1.clipped != 0 ? 1 : 0;
      int var5 = (var2 & 4080) >>> 4;
      int var6;
      if ((var2 & 4096) != 0 && var5 != 255) {
         var6 = var5 != 0 && var5 != 128 ? 2 : 1;
      } else if ((var2 & 8192) != 0) {
         var6 = 3;
      } else {
         var6 = 0;
      }

      this.rasterizer.setAlpha(var5);

      try {
         if ((var2 & 32768) != 0) {
            if (var1.sphereMapTexture != null) {
               this.rasterizer.setSphereMapTexture(var1.sphereMapTexture);
               SphereMapDrawer var7 = this.rasterizer.sphereMapDrawers[var6][var3][var4];
               this.rasterizer.rasterizeSphereMapTriangle(var7, var1.v0, var1.v1, var1.v2);
               if (var1.vertexCount == 4) {
                  this.rasterizer.rasterizeSphereMapTriangle(var7, var1.v1, var1.v2, var1.v3);
               }
            } else {
               int var9 = var1.v1.light + var1.v2.light;
               if (var1.v0.light + var9 == 0) {
                  this.rasterizer.rasterizeUnlitTriangle(this.rasterizer.unlitDrawers[var6][var3][var4], var1.v0, var1.v1, var1.v2);
               } else {
                  this.rasterizer.rasterizeLitTriangle(this.rasterizer.litDrawers[var6][var3][var4], var1.v0, var1.v1, var1.v2);
               }

               if (var1.vertexCount == 4) {
                  if (var1.v3.light + var9 == 0) {
                     this.rasterizer.rasterizeUnlitTriangle(this.rasterizer.unlitDrawers[var6][var3][var4], var1.v1, var1.v2, var1.v3);
                  } else {
                     this.rasterizer.rasterizeLitTriangle(this.rasterizer.litDrawers[var6][var3][var4], var1.v1, var1.v2, var1.v3);
                  }
               }
            }
         } else {
            TexturedDrawer var10 = this.rasterizer.texturedDrawers[var6][var3][var4];
            this.rasterizer.rasterizeTexturedTriangle(var10, var1.v0, var1.v1, var1.v2);
            if (var1.vertexCount == 4) {
               this.rasterizer.rasterizeTexturedTriangle(var10, var1.v1, var1.v2, var1.v3);
            }
         }
      } catch (ArrayIndexOutOfBoundsException var8) {
      }
   }

   public void setLightingEnabled(boolean var1) {
      this.lightingEnabled = var1;
   }

   public void setAmbientIntensity(float var1) {
      this.ambientIntensity = var1;
   }

   public void setDirectionalLight(Vector3f var1, float var2) {
      this.lightDirection.set(var1);
      this.lightDirection.normalize();
      this.lightDirection.negate();
      this.lightIntensity = var2;
   }

   protected void computeVertexLighting(Vector3f var1, Vector3f var2, Vector3f var3, Vector3f[] var4, int var5, int var6) {
      float var7 = var1.x;
      float var8 = var1.y;
      float var9 = var1.z;
      float var10 = var7 + var3.x;
      float var11 = var8 + var3.y;
      float var12 = var9 + var3.z;
      float var13 = 1.0F / (float)Math.sqrt(var10 * var10 + var11 * var11 + var12 * var12);
      var10 *= var13;
      var11 *= var13;
      var12 *= var13;
      int var14 = var5;
      int var15 = var5 * 3;

      for (int var16 = 0; var16 < var6; var16++) {
         Vector3f var17 = var4[var14];
         float var18 = this.ambientIntensity;
         float var19 = var17.dot(var2);
         if (var19 > 0.0F) {
            var18 += var19;
         }

         this.vertexLighting[var15 + 0] = var18;
         float var20 = 0.0F;
         if (var19 > 0.0F) {
            float var21 = var17.x * var10 + var17.y * var11 + var17.z * var12;
            if (var21 > 0.0F) {
               var20 = var21;
            }
         }

         this.vertexLighting[var15 + 1] = var20;
         var14++;
         var15 += 3;
      }
   }

   protected void computeSphereMapLighting(Vector3f var1, Transform3D var2, Vector3f[] var3, int var4, int var5) {
      float var6 = var1.x;
      float var7 = var1.y;
      float var8 = var1.z;
      float var9 = var2.m00 * 0.5F;
      float var10 = var2.m01 * 0.5F;
      float var11 = var2.m02 * 0.5F;
      float var12 = var2.m10 * 0.5F;
      float var13 = var2.m11 * 0.5F;
      float var14 = var2.m12 * 0.5F;
      int var15 = var4;
      int var16 = var4 * 3;

      for (int var17 = 0; var17 < var5; var17++) {
         Vector3f var18 = var3[var15];
         float var19 = this.ambientIntensity;
         float var20 = var6 * var18.x + var7 * var18.y + var8 * var18.z;
         if (var20 > 0.0F) {
            var19 += var20;
         }

         this.vertexLighting[var16 + 0] = var19;
         this.vertexLighting[var16 + 1] = var9 * var18.x + var10 * var18.y + var11 * var18.z + 0.5F;
         this.vertexLighting[var16 + 2] = var12 * var18.x + var13 * var18.y + var14 * var18.z + 0.5F;
         var15++;
         var16 += 3;
      }
   }
}
