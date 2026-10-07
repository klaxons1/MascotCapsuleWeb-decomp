package com.hicorp.mascotcapsule.web;

import java.io.InputStream;

public final class TraAnimation {
   private static final Vector3f DEFAULT_SCALE = new Vector3f(1.0F, 1.0F, 1.0F);
   public static final int TRACK_POS_X = 0;
   public static final int TRACK_POS_Y = 1;
   public static final int TRACK_POS_Z = 2;
   public static final int TRACK_SCALE_X = 3;
   public static final int TRACK_SCALE_Y = 4;
   public static final int TRACK_SCALE_Z = 5;
   public static final int TRACK_ROT_AXIS_X = 6;
   public static final int TRACK_ROT_AXIS_Y = 7;
   public static final int TRACK_ROT_AXIS_Z = 8;
   public static final int TRACK_ROT_ANGLE = 9;
   public static final int NUM_TRACKS = 10;

   protected int duration = 0;
   protected int boneCount = 0;
   protected AnimatedBone[] boneChannels = null;
   protected int modelBoneCount = 0;
   protected BoneTrackEvaluator[] bonePoses = null;
   protected int totalKeyframes = 0;
   protected InterpolatedKeyframe[] allKeyframes = null;
   public BacModel bacModel = null;

   protected void reset() {
      this.duration = 0;
      this.boneCount = 0;
      this.boneChannels = null;
      this.modelBoneCount = 0;
      this.bonePoses = null;
      this.totalKeyframes = 0;
      this.allKeyframes = null;
      this.bacModel = null;
   }

   protected void initKeyframeBuffer() {
      this.totalKeyframes = 0;
      this.allKeyframes = null;

      for (int i = 0; i < this.modelBoneCount; i++) {
         int idx = 0;
         while (idx < this.boneCount && !this.boneChannels[idx].name.equalsIgnoreCase(this.bacModel.getBone(i).getName())) {
            idx++;
         }

         if (idx != this.boneCount) {
            for (int t = 0; t < NUM_TRACKS; t++) {
               this.totalKeyframes += this.boneChannels[idx].tracks[t].getCount();
            }
         } else {
            this.totalKeyframes += NUM_TRACKS;
         }
      }

      this.allKeyframes = new InterpolatedKeyframe[this.totalKeyframes];
      for (int i = 0; i < this.totalKeyframes; i++) {
         this.allKeyframes[i] = new InterpolatedKeyframe(null);
      }
   }

   public boolean load(InputStream in) {
      this.reset();
      if (in == null) {
         return true;
      } else {
         BinaryReader reader = new BinaryReader(in);
         byte m0 = reader.readByte();
         byte m1 = reader.readByte();
         byte m2 = reader.readByte();
         byte m3 = reader.readByte();
         if (m0 == 'H' && m1 == 'I' && m2 == 'J' && m3 == 'T') {
            int ver = reader.readInt();
            if (ver != 1) {
               return false;
            } else {
               reader.skipBytes(12);
               byte[] headerPad = new byte[64];
               for (int i = 0; i < 64; i++) {
                  headerPad[i] = reader.readByte();
               }

               short frames = reader.readShort();
               this.boneCount = reader.readShort();
               this.duration = frames < 1 ? 0 : frames - 1;
               this.boneChannels = new AnimatedBone[this.boneCount];

               for (int i = 0; i < this.boneCount; i++) {
                  this.boneChannels[i] = new AnimatedBone();
                  this.boneChannels[i].name = reader.readCString();
                  this.boneChannels[i].translation.x = reader.readFloat();
                  this.boneChannels[i].translation.y = reader.readFloat();
                  this.boneChannels[i].translation.z = reader.readFloat();
                  this.boneChannels[i].rotationAxis.x = reader.readFloat();
                  this.boneChannels[i].rotationAxis.y = reader.readFloat();
                  this.boneChannels[i].rotationAxis.z = reader.readFloat();
                  this.boneChannels[i].rotationAngles.x = reader.readFloat();
                  this.boneChannels[i].rotationAngles.y = reader.readFloat();
                  this.boneChannels[i].rotationAngles.z = reader.readFloat();
                  this.boneChannels[i].scale.x = reader.readFloat();
                  this.boneChannels[i].scale.y = reader.readFloat();
                  this.boneChannels[i].scale.z = reader.readFloat();

                  for (int t = 0; t < NUM_TRACKS; t++) {
                     short count = reader.readShort();
                     this.boneChannels[i].tracks[t].allocate(count);

                     for (int k = 0; k < count; k++) {
                        short time = reader.readShort();
                        this.boneChannels[i].tracks[t].keyframes[k].time = time;
                        float val = reader.readFloat();
                        this.boneChannels[i].tracks[t].keyframes[k].value = val;
                     }
                  }
               }

               return true;
            }
         } else {
            return false;
         }
      }
   }

   public int bindModel(BacModel model) {
      Debug.assertTrue(model != null);
      Debug.assertTrue(model.hasBones());
      if (model == null || !model.hasBones()) {
         return 1;
      } else {
         this.bacModel = model;
         this.modelBoneCount = model.getBoneCount();
         this.bonePoses = new BoneTrackEvaluator[this.modelBoneCount];

         for (int i = 0; i < this.modelBoneCount; i++) {
            this.bonePoses[i] = new BoneTrackEvaluator();
            for (int t = 0; t < NUM_TRACKS; t++) {
               this.bonePoses[i].tracks[t] = new AnimationTrack(this, null);
            }
         }

         this.initKeyframeBuffer();
         int keyframeOffset = 0;

         for (int i = 0; i < this.modelBoneCount; i++) {
            BoneTrackEvaluator pose = this.bonePoses[i];
            int matchIdx = 0;

            while (matchIdx < this.boneCount && !this.boneChannels[matchIdx].name.equalsIgnoreCase(model.getBone(i).getName())) {
               matchIdx++;
            }

            pose.rotationMatrix = new Transform3D();
            if (matchIdx == this.boneCount) {
               for (int t = 0; t < NUM_TRACKS; t++) {
                  AnimationTrack track = pose.tracks[t];
                  keyframeOffset = track.bindKeyframes(keyframeOffset, 1);
                  track.keyframes[0].time = 0;
                  track.keyframes[0].duration = 0.0F;
                  track.keyframes[0].value = 0.0F;
                  track.keyframes[0].deltaValue = 0.0F;
               }

               pose.tracks[3].keyframes[0].value = 1.0F;
               pose.tracks[4].keyframes[0].value = 1.0F;
               pose.tracks[5].keyframes[0].value = 1.0F;
               pose.tracks[8].keyframes[0].value = 1.0F;
               pose.isAnimated = true;
               pose.rotationMatrix.setIdentity();
            } else {
               AnimatedBone channel = this.boneChannels[matchIdx];
               pose.scaleMatrix = model.getBone(i).getRestTransform();
               pose.transformMatrix = computeInverseRestTransform(model, model.getBone(i), channel);
               boolean hasVariation = false;

               for (int t = 0; t < NUM_TRACKS; t++) {
                  float minVal = Float.MAX_VALUE;
                  float maxVal = -Float.MAX_VALUE;
                  KeyframeTrack raw = channel.tracks[t];
                  AnimationTrack track = pose.tracks[t];
                  int count = raw.getCount();
                  keyframeOffset = track.bindKeyframes(keyframeOffset, count);

                  for (int k = 0; k < count; k++) {
                     int time = raw.keyframes[k].time;
                     float dt = k == count - 1 ? 0.0F : raw.keyframes[k + 1].time - time;
                     float val = raw.keyframes[k].value;
                     float dVal = k == count - 1 ? 0.0F : raw.keyframes[k + 1].value - val;
                     track.keyframes[k].time = time;
                     track.keyframes[k].duration = dt;
                     track.keyframes[k].value = val;
                     track.keyframes[k].deltaValue = dVal;
                     minVal = minVal > val ? val : minVal;
                     maxVal = maxVal < val ? val : maxVal;
                  }

                  if (maxVal - minVal > 1.0E-5F) {
                     hasVariation = true;
                  }
               }

               for (int t = 3; t <= 5; t++) {
                  AnimationTrack track = pose.tracks[t];
                  for (int k = 0; k < track.getKeyframeCount(); k++) {
                     track.keyframes[k].value /= 100.0F;
                     track.keyframes[k].deltaValue /= 100.0F;
                  }
               }

               AnimationTrack angleTrack = pose.tracks[9];
               for (int k = 0; k < angleTrack.getKeyframeCount(); k++) {
                  angleTrack.keyframes[k].value = MatrixUtils.toRadians(angleTrack.keyframes[k].value);
                  angleTrack.keyframes[k].deltaValue = MatrixUtils.toRadians(angleTrack.keyframes[k].deltaValue);
               }

               if (!hasVariation) {
                  this.computeBoneTransform(i, 0.0F, pose.rotationMatrix);
                  pose.isAnimated = true;
               }
            }
         }

         this.boneCount = 0;
         this.boneChannels = null;
         Debug.assertTrue(keyframeOffset == this.totalKeyframes);
         return 0;
      }
   }

   public void blendAnimations(TraAnimation animA, float timeA, TraAnimation animB, float timeB, int frames) {
      Debug.assertTrue(frames >= 2);
      Debug.assertTrue(this != animA && this != animB);
      Debug.assertTrue(animA.modelBoneCount == animB.modelBoneCount);
      float[] valsA = new float[NUM_TRACKS];
      float[] valsB = new float[NUM_TRACKS];
      Vector3f normAxis = new Vector3f();
      this.reset();
      this.duration = frames - 1;
      this.modelBoneCount = animA.modelBoneCount;
      this.bonePoses = new BoneTrackEvaluator[this.modelBoneCount];
      this.totalKeyframes = 20 * this.modelBoneCount;
      this.allKeyframes = new InterpolatedKeyframe[this.totalKeyframes];

      for (int i = 0; i < this.totalKeyframes; i++) {
         this.allKeyframes[i] = new InterpolatedKeyframe(null);
      }

      int keyOffset = 0;
      for (int i = 0; i < this.modelBoneCount; i++) {
         this.bonePoses[i] = new BoneTrackEvaluator();
         BoneTrackEvaluator pose = this.bonePoses[i];
         BoneTrackEvaluator poseA = animA.bonePoses[i];
         BoneTrackEvaluator poseB = animB.bonePoses[i];
         poseA.evaluate(timeA, valsA);
         poseB.evaluate(timeB, valsB);

         normAxis.set(valsA[6], valsA[7], valsA[8]);
         normAxis.normalize();
         valsA[6] = normAxis.x;
         valsA[7] = normAxis.y;
         valsA[8] = normAxis.z;

         normAxis.set(valsB[6], valsB[7], valsB[8]);
         normAxis.normalize();
         valsB[6] = normAxis.x;
         valsB[7] = normAxis.y;
         valsB[8] = normAxis.z;

         float dot = Vector3f.dot(normAxis, normAxis);
         if (dot < 0.0F) {
            valsB[6] = -valsB[6];
            valsB[7] = -valsB[7];
            valsB[8] = -valsB[8];
            valsB[9] = -valsB[9];
         }

         pose.rotationMatrix = new Transform3D();
         pose.scaleMatrix = poseA.scaleMatrix;
         pose.transformMatrix = poseA.transformMatrix;

         for (int t = 0; t < NUM_TRACKS; t++) {
            pose.tracks[t] = new AnimationTrack(this, null);
            AnimationTrack track = pose.tracks[t];
            keyOffset = track.bindKeyframes(keyOffset, 2);
            track.keyframes[0].time = 0;
            track.keyframes[0].duration = frames - 1;
            track.keyframes[0].value = valsA[t];
            track.keyframes[0].deltaValue = valsB[t] - valsA[t];
            track.keyframes[1].time = frames - 1;
            track.keyframes[1].duration = 0.0F;
            track.keyframes[1].value = valsB[t];
            track.keyframes[1].deltaValue = 0.0F;
         }
      }

      Debug.assertTrue(keyOffset == this.totalKeyframes);
   }

   public void computeBoneTransform(int boneIndex, float frame, Transform3D outTransform) {
      Debug.assertTrue(boneIndex < this.modelBoneCount);
      BoneTrackEvaluator pose = this.bonePoses[boneIndex];
      if (pose.isAnimated) {
         outTransform.set(pose.rotationMatrix);
      } else {
         float[] values = new float[NUM_TRACKS];
         int frameInt;
         if (frame < 0.0F) {
            frame = 0.0F;
            frameInt = 0;
         } else {
            frameInt = (int)frame;
         }

         for (int t = 0; t < NUM_TRACKS; t++) {
            int kCount = pose.tracks[t].getKeyframeCount();
            InterpolatedKeyframe[] kfs = pose.tracks[t].keyframes;
            int k = 1;

            while (k < kCount && frameInt >= kfs[k].time) {
               k++;
            }

            int prev = k - 1;
            if (k < kCount) {
               float alpha = (frame - kfs[prev].time) / kfs[prev].duration;
               values[t] = kfs[prev].value + alpha * kfs[prev].deltaValue;
            } else {
               values[t] = kfs[prev].value;
            }
         }

         buildBoneMatrix(values, outTransform);
         outTransform.multiply(pose.transformMatrix, outTransform);
         outTransform.multiply(pose.scaleMatrix);
      }
   }

   public void applyPose(float frame) {
      Transform3D t = new Transform3D();
      for (int i = 0; i < this.modelBoneCount; i++) {
         this.computeBoneTransform(i, frame, t);
         this.bacModel.getBone(i).setLocalTransform(t);
      }
   }

   public final int getDuration() {
      return this.duration;
   }

   public final int getBoneCount() {
      return this.modelBoneCount;
   }

   private static final Transform3D computeInverseRestTransform(BacModel model, BoneNode bone, AnimatedBone channel) {
      Transform3D inv = new Transform3D(bone.getRestTransform());
      inv.invert();
      return inv;
   }

   private static final void buildBoneMatrix(float[] values, Transform3D out) {
      float ax = values[6];
      float ay = values[7];
      float az = values[8];
      float invLen = 1.0F / (float)Math.sqrt(ax * ax + ay * ay + az * az);
      ax *= invLen;
      ay *= invLen;
      az *= invLen;
      float xx = ax * ax;
      float yy = ay * ay;
      if (xx == 0.0F && yy == 0.0F) {
         out.setRotation(1.0F, 0.0F, 0.0F, 0.0F, az, 0.0F, 0.0F, 0.0F, az);
      } else {
         float factor = (1.0F - az) / (xx + yy);
         float xyFactor = -ax * ay * factor;
         out.setRotation(yy * factor + az, xyFactor, ax, xyFactor, xx * factor + az, ay, -ax, -ay, az);
      }

      if (values[9] != 0.0F) {
         Transform3D rotZ = new Transform3D();
         MatrixUtils.setRotationZ(values[9], rotZ);
         out.multiplyRotation(rotZ);
      }

      float sx = values[3];
      float sy = values[4];
      float sz = values[5];
      if (sx != 1.0F || sy != 1.0F || sz != 1.0F) {
         out.setRotation(
            out.m00 * sx,
            out.m01 * sy,
            out.m02 * sz,
            out.m10 * sx,
            out.m11 * sy,
            out.m12 * sz,
            out.m20 * sx,
            out.m21 * sy,
            out.m22 * sz
         );
      }

      out.m03 = values[0];
      out.m13 = values[1];
      out.m23 = values[2];
   }
}
