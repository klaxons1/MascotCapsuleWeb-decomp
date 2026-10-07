# MascotCapsule Web Deobfuscation & Verification Report

## Executive Summary
This report summarizes the complete deobfuscation, correction, asset format reconstruction, and live software rasterization verification of **MascotCapsule Web**, the pure-Java 3D software rendering engine originally developed by HI Corporation for applet/web deployment.

Starting from the initial decompilation commit (`27a7698`), all 115 Java source classes have been completely deobfuscated and semantically audited. Critical hallucinated class and method names from previous automated decompilation passes have been corrected, the binary formats (`.jbac`, `.jtra`, and textures) have been programmatically reconstructed, and the 3D pipeline has been tested to verify full rasterization, shading, lighting, and texture mapping.

---

## Key Reverse-Engineering Findings & Bug Corrections

### 1. Hallucinated Drawer Classes in Initial Decompilation
In commit `27a7698`, several automated passes hallucinated standard 3D names onto rasterizer drawers:
- `AnimationSet` was actually `SphereMapDrawer_T2_Alpha_Triangle` (reflection mapping span drawer).
- `ColorRGBA` was actually `SphereMapDrawer_T0_Alpha_Quad`.
- `Light` was actually `LitColorDrawer_Opaque`.
- `Mesh` was actually `LineDrawer_Alpha`.
- `MeshLoader` was actually `LitDrawer_T3_Alpha_Quad`.
- `ModelLoader` was actually `UnlitDrawer_T1_Opaque_Triangle`.
- `TextureLoader` was actually `LitDrawer_T1_Opaque_Quad`.
- `ResourceEntry` was actually `TexturedDrawer_T1_Alpha_Quad`.
- `Material` was actually the `ImageDecoder` interface (`BmpDecoder` implementer).
- `VertexWeight` was actually `KeyframePoint` (spline animation control point).
- The 3D model container was `BacModel` (previously named `Model`), and the animation controller was `TraAnimation` (previously named `RenderState`).

### 2. The `RasterVertex` Field Shift Bug
One of the most consequential bugs discovered during the rasterizer audit was an extra phantom field in `RasterVertex` (`Class_ae`):
- `Class_ae` in bytecode has exactly 7 integer fields:
  ```java
  int var_59;  // 0: screen X
  int var_9f;  // 1: screen Y
  int var_100; // 2: U texture coordinate (or light in LitColor)
  int var_114; // 3: V texture coordinate
  int var_165; // 4: light intensity
  int var_181; // 5: normal Z / reflection U
  int var_1a4; // 6: sphere map V
  ```
- The previous decompiler hallucinated an `int z;` field at index 2 between `y` and `u`.
- Consequently, `Config.java` (the core rasterizer) renamed `var_100` to `z`, `var_114` to `u`, `var_165` to `v`, `var_181` to `light`, and `var_1a4` to `normalZ`.
- Meanwhile, `RenderContext.java` correctly assigned texture coordinates to `u` (`var_100`) and `v` (`var_114`).
- Because `RasterVertex.z` was never written by `RenderContext`, `Config.java` read `top.z = 0`, causing `uFixed` to be zero on every single scanline and shifting all attributes by one!
- **Resolution**: Removed the phantom `z` field from `RasterVertex` and restored the exact 1-to-1 attribute mapping in `Config.java`.

### 3. Inverted Perspective vs. Parallel (Orthographic) Projection
In `RenderContext.java`:
- Bytecode method `sub_249` divided transformed coordinates by $Z$ using focal length (`sub_1f` = `(height / 2) / tan(fov / 2)`), which is **perspective projection**.
- Bytecode method `sub_212` applied a constant scale multiplier without dividing by $Z$, which is **parallel (orthographic) projection**.
- The initial decompilation swapped these two methods, naming `sub_73` `enablePerspective` (which was actually enabling parallel projection) and passing a unit scale of `1.0F`, causing all perspective coordinates to truncate to `(0, 0)`.
- **Resolution**: Corrected the method semantics to `enableParallelProjection(float scale)` and `disableParallelProjection()`, ensuring that perspective rendering correctly applies the camera focal length (~346 pixels for a 400x400 canvas at 60° FOV).

### 4. Bone Node Hierarchy & Rest Pose Initialization
In `BacModel.java`:
- When bone nodes were parsed in `readBones()`, `bone.restTransform` was read, but `bone.setLocalTransform(bone.restTransform)` was never initialized.
- If an animation did not immediately override all bone transforms or when rendering in rest pose, `SceneNode.computeTransformRelativeToRoot()` would fail its assertion `Debug.assertTrue(this.hasLocalTransform)`.
- **Resolution**: Initialized `hasLocalTransform = true` and `localTransform.setIdentity()` by default in `SceneNode`, and set `bone.setLocalTransform(bone.restTransform)` during BAC model loading.

### 5. UV Mapping Assignment
In `BacModel.readPolygons()`:
- The previous decompilation had written polygon $U$ and $V$ texture coordinates to `vert0..vert3` vertex index fields instead of `u0..u3` and `v0..v3`.
- **Resolution**: Corrected the byte parsing loop so texture coordinates are written directly to `poly.u` and `poly.v`.

---

## Binary Asset Format Specification Reconstructed

The binary formats required by MascotCapsule Web were fully decoded:
1. **`.jbac` (MascotCapsule 3D Model)**:
   - Header: Magic `"HIJB"`, 4-byte version `1`, 12 reserved bytes, 64-byte non-zero verification pad (`padSum != 0`).
   - Vertices: 4-byte count, followed by $N \times 12$ bytes of 32-bit float $(X, Y, Z)$ coordinates.
   - Normals: 4-byte count, followed by $N \times 12$ bytes of 32-bit float $(N_x, N_y, N_z)$ vectors.
   - Polygons: 4-byte total count, 4-byte triangle count, 4-byte quad count.
     - Triangles: 4-byte render flags, followed by $3 \times$ (short vertIndex, short u, short v).
     - Quads: 4-byte render flags, followed by $4 \times$ (short vertIndex, short u, short v).
   - Bones: 4-byte count. For each bone: child flag (byte), sibling flag (byte), null-terminated ASCII name, $3 \times 4$ rest transform matrix (12 floats), vertex attachment count (short), and parent bone index (short).
2. **`.jtra` (MascotCapsule Skeletal Animation)**:
   - Header: Magic `"HIJT"`, 4-byte version `1`, 12 reserved bytes, 64-byte non-zero verification pad.
   - Tracks: Frame count (short), bone count (short).
   - Keyframe curves: Translation $(X, Y, Z)$, Rotation angles $(\theta_x, \theta_y, \theta_z)$ or quaternions, Scaling $(S_x, S_y, S_z)$.
3. **Textures**:
   - 24-bit uncompressed Windows V3 BMP format, power-of-two dimensions ($256 \times 256$), with mipmaps generated dynamically at runtime (`Texture.generateMipmaps()`).

---

## Verification & Artifacts

- **Asset Generator**: `tools/generate_assets.py` programmatically builds valid `.jbac`, `.jtra`, and textures into `docs/sample.zip`.
- **Headless Test Runner**: `tools/TestRunner.java` loads the archive, initializes the 3D pipeline with camera, lighting, and animation, steps the simulation, and captures the rendered framebuffer.
- **Render Output**: Verified high-fidelity rendering of the animated, shaded, textured 3D cube model against the MascotCapsule Web canvas background, saved to `docs/screenshot.png`.
- **Build System**: `build.sh` produces a clean build under JDK 1.6:
  ```bash
  ./build.sh
  # Produces build/MascotCapsule.jar (115 compiled classes + resources)
  ```
