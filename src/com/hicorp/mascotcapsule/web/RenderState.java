package com.hicorp.mascotcapsule.web;

import java.io.InputStream;

public final class RenderState {
   public static final int HEADER_MAGIC = 4;
   private long version;
   private static final Vector3f DEFAULT_LIGHT_DIR = new Vector3f(0.0F, 0.0F, 1.0F);
   protected final Class_5a9 rootNode = new Class_5a9();
   protected int boneCount = 0;
   protected Class_13f[] bones = null;
   protected int vertexCount = 0;
   protected Vector3f[] vertices = null;
   protected Vector3f[] normals = null;
   protected int polygonCount = 0;
   protected Class_12f[] polygons = null;

   protected int readBones(int boneIndex, int parentIndex, Class_613 reader) {
      boolean hasSibling;
      do {
         Class_13f bone = new Class_13f();
         this.bones[boneIndex] = bone;
         boolean hasChild;
         bone.hasChild = hasChild = reader.readByte() != 0;
         bone.hasSibling = hasSibling = reader.readByte() != 0;
         bone.name = reader.readCString();
         bone.restTransform.m00 = reader.readFloat();
         bone.restTransform.m01 = reader.readFloat();
         bone.restTransform.m02 = reader.readFloat();
         bone.restTransform.m10 = reader.readFloat();
         bone.restTransform.m11 = reader.readFloat();
         bone.restTransform.m12 = reader.readFloat();
         bone.restTransform.m20 = reader.readFloat();
         bone.restTransform.m21 = reader.readFloat();
         bone.restTransform.m22 = reader.readFloat();
         bone.restTransform.m03 = reader.readFloat();
         bone.restTransform.m13 = reader.readFloat();
         bone.restTransform.m23 = reader.readFloat();
         bone.boneIndex = reader.readShort();
         bone.setParent((Class_5a9)(boneIndex == 0 ? this.rootNode : this.bones[parentIndex]));
         boneIndex++;
         if (hasChild) {
            boneIndex = this.readBones(boneIndex, boneIndex - 1, reader);
         }
      } while (hasSibling);

      return boneIndex;
   }

   public boolean load(InputStream in) {
      Class_613 reader = new Class_613(in);
      byte m0 = reader.readByte();
      byte m1 = reader.readByte();
      byte m2 = reader.readByte();
      byte m3 = reader.readByte();
      if (m0 == 'H' && m1 == 'I' && m2 == 'J' && m3 == 'B') {
         this.version = reader.readInt();
         if (this.version != 1L) {
            return false;
         } else {
            reader.skipBytes(12);
            byte[] headerPad = new byte[64];
            byte padSum = 0;

            for (int i = 0; i < 64; i++) {
               headerPad[i] = reader.readByte();
               padSum += headerPad[i];
            }

            this.vertexCount = reader.readInt();
            this.vertices = new Vector3f[this.vertexCount];

            for (int i = 0; i < this.vertexCount; i++) {
               this.vertices[i] = new Vector3f();
               this.vertices[i].x = reader.readFloat();
               this.vertices[i].y = reader.readFloat();
               this.vertices[i].z = reader.readFloat();
            }

            int normalCount = reader.readInt();
            if (normalCount > 0) {
               if (normalCount != this.vertexCount) {
                  return false;
               }

               this.normals = new Vector3f[normalCount];

               for (int i = 0; i < this.vertexCount; i++) {
                  this.normals[i] = new Vector3f();
                  this.normals[i].x = reader.readFloat();
                  this.normals[i].y = reader.readFloat();
                  this.normals[i].z = reader.readFloat();
               }
            } else {
               this.normals = null;
            }

            this.polygonCount = reader.readInt();
            if (padSum == 0 && this.polygonCount >= 500) {
               return false;
            } else {
               this.polygons = new Class_12f[this.polygonCount];
               int triCount = reader.readInt();

               for (int i = 0; i < triCount; i++) {
                  this.polygons[i] = new Class_12f();
                  this.polygons[i].flags = reader.readInt();
                  this.polygons[i].vertexCount = 3;
                  this.polygons[i].v0 = reader.readShort();
                  this.polygons[i].u0 = reader.readShort();
                  this.polygons[i].v0_coord = reader.readShort();
                  this.polygons[i].v1 = reader.readShort();
                  this.polygons[i].u1 = reader.readShort();
                  this.polygons[i].v1_coord = reader.readShort();
                  this.polygons[i].v2 = reader.readShort();
                  this.polygons[i].u2 = reader.readShort();
                  this.polygons[i].v2_coord = reader.readShort();
               }

               int quadCount = reader.readInt();

               for (int i = triCount; i < this.polygonCount; i++) {
                  this.polygons[i] = new Class_12f();
                  this.polygons[i].flags = reader.readInt();
                  this.polygons[i].vertexCount = 4;
                  this.polygons[i].v0 = reader.readShort();
                  this.polygons[i].u0 = reader.readShort();
                  this.polygons[i].v0_coord = reader.readShort();
                  this.polygons[i].v1 = reader.readShort();
                  this.polygons[i].u1 = reader.readShort();
                  this.polygons[i].v1_coord = reader.readShort();
                  this.polygons[i].v2 = reader.readShort();
                  this.polygons[i].u2 = reader.readShort();
                  this.polygons[i].v2_coord = reader.readShort();
                  this.polygons[i].v3 = reader.readShort();
                  this.polygons[i].u3 = reader.readShort();
                  this.polygons[i].v3_coord = reader.readShort();
               }

               this.boneCount = reader.readInt();
               this.bones = new Class_13f[this.boneCount];
               return this.boneCount == this.readBones(0, 0, reader);
            }
         }
      } else {
         return false;
      }
   }

   public final int getBoneCount() {
      return this.boneCount;
   }

   public final Class_5a9 getRootNode() {
      return this.rootNode;
   }

   public final Class_13f getBone(int index) {
      return this.bones[index];
   }

   public final int getVertexCount() {
      return this.vertexCount;
   }

   public final Vector3f[] getVertices() {
      return this.vertices;
   }

   public final Vector3f[] getNormals() {
      return this.normals;
   }

   public final int getPolygonCount() {
      return this.polygonCount;
   }

   public final Class_12f getPolygon(int index) {
      return this.polygons[index];
   }

   public final Class_12f getFirstPolygon() {
      return this.polygons[0];
   }

   public final boolean hasBones() {
      return this.bones != null;
   }

   public final void generateNormals() {
      if (this.normals == null) {
         this.normals = new Vector3f[this.vertexCount];
         Vector3f edge1 = new Vector3f();
         Vector3f edge2 = new Vector3f();

         for (int i = this.vertexCount - 1; i >= 0; i--) {
            this.normals[i] = new Vector3f(0.0F, 0.0F, 0.0F);
         }

         for (int i = 0; i < this.polygonCount; i++) {
            Class_12f poly = this.polygons[i];
            edge1.setDifference(this.vertices[poly.v1], this.vertices[poly.v0]);
            edge2.setDifference(this.vertices[poly.v2], this.vertices[poly.v0]);
            edge2.cross(edge1);
            this.normals[poly.v0].add(edge2);
            this.normals[poly.v1].add(edge2);
            this.normals[poly.v2].add(edge2);
            if (poly.vertexCount == 4) {
               this.normals[poly.v3].add(edge2);
            }

            poly.flags |= 32768;
            poly.flags &= 65535;
            poly.flags |= 1077936128;
         }

         for (int i = this.vertexCount - 1; i >= 0; i--) {
            float len = this.normals[i].length();
            if (len != 0.0F) {
               this.normals[i].scale(1.0F / len);
            } else {
               this.normals[i].set(1.0F, 0.0F, 0.0F);
            }
         }
      }
   }
}
