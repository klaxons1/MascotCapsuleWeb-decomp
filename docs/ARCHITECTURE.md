# MascotCapsule 3D Web Software Renderer - Architecture

## Overview

MascotCapsule Web is a pure-Java, software-rendered 3D graphics engine developed by HI Corporation (Tokyo, Japan) for web applets (circa 2000–2003). MascotCapsule was the dominant 3D graphics middleware for early mobile phones (NTT DoCoMo DoJa/Star, Sony Ericsson, Vodafone/J-Phone, au/BREW), and this web applet edition allowed games and 3D avatars to be previewed and played in desktop web browsers using pure Java without requiring hardware acceleration (OpenGL or DirectX).

The engine executes entirely on the CPU using integer fixed-point arithmetic for the inner scanline rasterizer and floating-point arithmetic for geometry, vertex transformations, skeletal bone hierarchies, and lighting calculations.

---

## High-Level Architecture

The engine is organized into several distinct subsystems:

```
+-------------------------------------------------------------+
|                      MascotCapsule                          |
|             (AWT Applet / Browser Entry Point)              |
+-------------------------------------------------------------+
                               |
                               v
+-------------------------------------------------------------+
|                   MascotCapsuleCanvas                       |
|       (AWT Canvas, Event Handling, Animation Loop)          |
+-------------------------------------------------------------+
         |                              |
         v                              v
+-----------------------+     +-------------------------------+
|     ModelLoader       |     |        AnimationThread        |
|  (Background Thread)  |     |   (Continuous Rendering Loop) |
+-----------------------+     +-------------------------------+
         |
         +--> Loads ZIP Container:
              - .jbac : BacModel (3D Geometry, Skeleton, Normals, Polys)
              - .jtra : TraAnimation (Skeletal Animation Keyframe Tracks)
              - .bmp, .png, .jpg : Texture & Sphere Environment Maps
                               |
                               v
+-------------------------------------------------------------+
|                      RenderContext                          |
|  - Camera & View Matrix Transform                           |
|  - Directional & Ambient Lighting                           |
|  - Perspective Projection & 3D Frustum Clipping             |
|  - Display List / Depth-Sorted Bucket Queue ("Packet Table")|
+-------------------------------------------------------------+
                               |
                               v
+-------------------------------------------------------------+
|                   SoftwareRasterizer                        |
|  - Top/Bottom Triangle Scanline Decomposition (DDA)         |
|  - 6 Span Drawer Hierarchies (78 Specialized Drawer Classes)|
|  - Sub-pixel Fixed-Point Coordinate Interpolation           |
|  - Mipmapped Texture Sampling (12 Mip Levels)               |
|  - Gouraud / Normal-based Lighting                          |
|  - Environment Sphere Mapping                               |
|  - Alpha Blending (Opaque, 50% Blend, Alpha, Additive)      |
+-------------------------------------------------------------+
                               |
                               v
+-------------------------------------------------------------+
|                        FrameBuffer                          |
|  - int[] ARGB Screen Buffer                                 |
|  - Implements java.awt.image.ImageProducer                  |
|  - Dirty-Rect / Bounding Box Incremental Screen Blitting    |
+-------------------------------------------------------------+
```

---

## Core Components

### 1. MascotCapsule (Applet) & MascotCapsuleCanvas
- Reads applet parameters (`WIDTH`, `HEIGHT`, `SCALE`, `FRAME_PER_SEC`, `MAX_FPS`, `PKTTBL_NUM`, `DIRECTION_LIGHT_*`, `ANGLE_*`, `ZIPFILE`, `BACFILE`, etc.).
- Initializes the `MascotCapsuleCanvas`, which hosts mouse and keyboard interaction (rotation, panning, zooming).
- Spawns two dedicated worker threads:
  - `ModelLoader`: Downloads and parses ZIP archives containing 3D models, textures, and animation files asynchronously.
  - `AnimationThread`: Drives the real-time animation clock and issues canvas repaints at target frame rates.

### 2. Math & Scene Graph
- `Vector3f`: Standard 3D floating-point vector with dot product, cross product, normalize, scale, and linear operations.
- `Transform3D`: 3x4 affine matrix representation `[R | T]` storing 9 rotation/scale floats and 3 translation floats.
- `MatrixUtils`: Fast trigonometry, rotation matrix builders (Euler angles, arbitrary axis rotation), fixed-point log2, and look-at calculations.
- `TransformNode`: Hierarchical scene graph node supporting parent-child matrix chaining and lazy world matrix caching.
- `Camera`: Extends `TransformNode`, computing the view matrix as the inverse of camera world transform.

### 3. BacModel (.jbac format)
- Represents rigged 3D models with a skeletal bone tree, vertex positions, vertex normals, and triangle/quad faces with UV texture coordinates.
- Supports automatic normal generation via cross-product summation across polygon faces.

### 4. TraAnimation (.jtra format)
- Skeletal animation system controlling model bones over time.
- Each bone possesses up to 10 animation channels (translation X/Y/Z, rotation axis X/Y/Z, rotation angle, and scale X/Y/Z).
- Performs linear interpolation between keyframes with time delta evaluation.

### 5. RenderContext & Packet Table
- Coordinates the rendering pipeline.
- Vertices are transformed by the combined model-view-projection matrix.
- Faces are clipped against the view frustum.
- Primitives are bucket-sorted into the `Packet Table` (an integer-indexed display list providing depth sorting / painter's algorithm without needing a hardware Z-buffer).

### 6. SoftwareRasterizer & Span Drawers
- Converts screen-space triangles and quadrilaterals into horizontal scanlines.
- Employs 72 specialized scanline drawer classes generated to maximize performance on Java 1.1 / 1.2 virtual machines without JIT compilers.
- Writes 32-bit ARGB pixels directly into `FrameBuffer`'s internal integer array.
