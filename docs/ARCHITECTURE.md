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
|   ModelLoaderThread   |     |        AnimationThread        |
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
|       (Projection, Depth Sort, Culling, Lighting)           |
+-------------------------------------------------------------+
                               |
                               v
+-------------------------------------------------------------+
|                   Config (Rasterizer)                       |
|         (16.16 Fixed-Point Triangle Rasterization)          |
+-------------------------------------------------------------+
                               |
                               v
+-------------------------------------------------------------+
|                      FrameBuffer                            |
|             (Double-Buffered ARGB Integer Array)            |
+-------------------------------------------------------------+
```

---

## Subsystem Breakdown

### 1. Presentation & Input Subsystem
- **`MascotCapsule.java`**: The root `Applet` class. Parses `<param>` tags (`WIDTH`, `HEIGHT`, `BACFILE`, `TRAFILE`, `TEXTURE`, `SPHERE`, `BACKGROUND`, `ZIPFILE`), coordinates initialization, and exposes public script methods (`bacScale`, `bacMove`, `bacRotate`, `traSpeed`, `showDrawStatus`, `enableLighting`, `setSphere`, `setModel`, `setBG`).
- **`MascotCapsuleCanvas.java`**: Custom AWT `Canvas` subclass. Handles interactive user controls:
  - **Mouse Drag (Mode 1)**: Interactive pitch and yaw Euler rotation.
  - **Meta/Right Mouse Drag (Mode 2)**: Viewport translation / panning (offsetting Center X/Y).
  - **Alt Mouse Drag (Mode 3)**: Interactive scaling / zoom.
  - **Keyboard**: Arrow keys for translation, number pad for rotation, X/Z for scale, Esc to reset transform.
  - **Background & Logo**: Displays animated progress bar with corporate logo while streaming assets.
- **`MainCanvas.java`**: Base engine canvas managing dirty-rectangle damage accumulation, frame buffer flushing, rasterizer configuration, and real-time FPS/polygon/vertex count overlay banner.

### 2. Threading & Asset Streaming Subsystem
- **`AnimationThread.java`**: Thread executing at priority 1 (`"MascotCapsule - Animation"`). Manages continuous delta-time animation advancing, skeletal pose updates, model rotation, and frame rate throttling (`Thread.sleep`).
- **`ModelLoaderThread.java`**: Background worker thread executing at priority 5 (`"MascotCapsule - ModelLoader"`). Downloads and decompresses assets over HTTP/URL connections without freezing the browser UI.

### 3. Math & Scene Graph Subsystem
- **`Vector3f.java`**: Standard 3-element float vector supporting dot products, cross products, normalization, and affine transformations.
- **`Transform3D.java`**: 4x3 row-major affine transformation matrix with fast perspective and orthographic vertex projection loops.
- **`MatrixUtils.java`**: Math helper providing Euler rotation matrices, scaling matrices, camera LookAt transforms, and fast integer log2 lookup tables.
- **`SceneNode.java`**: Hierarchical scene graph node supporting parent-child relationships and lazy-evaluated world coordinate transformation caching.
- **`CameraNode.java`**: Camera scene node computing inverse view matrices and model-view compound transformations.
- **`BoneNode.java`**: Skeletal bone joint node maintaining rest transforms and hierarchy traversal indices.

### 4. 3D Asset Subsystem
- **`BacModel.java`**: Parses the proprietary binary `"HIJB"` model format containing vertices, vertex normals, polygon tables, and bone definitions.
- **`TraAnimation.java`**: Parses the binary `"HIJT"` skeletal animation track format, evaluating up to 10 interpolation channels per bone (translation, rotation, scale, visibility).
- **`Texture.java`**: 32-bit ARGB texture manager generating up to 12 mipmap levels with 2x2 box filtering and power-of-two bit-shift UV sampling.
- **`BmpDecoder.java`**: Custom BMP decoder handling 1-bit, 4-bit, 8-bit paletted, 16-bit RGB 555/565, 24-bit RGB, and 32-bit RGBA uncompressed images.
- **`AwtImageDecoder.java`**: Fallback AWT image decoder for GIF, PNG, and JPEG files using `PixelGrabber`.

### 5. Rasterization Pipeline Subsystem
- **`RenderContext.java`**: Coordinates vertex transformations, directional Gouraud lighting calculations, sphere map reflection generation, Cohen-Sutherland outcode clipping, and depth bucket sorting.
- **`Config.java`**: Software scanline triangle rasterizer using 16.16 fixed-point DDA edge steppers, a 512-entry saturation table, and 78 specialized span drawer classes.
- **`FrameBuffer.java`**: Double-buffered integer raster target managing dirty-rect blits directly to AWT `Graphics`.
