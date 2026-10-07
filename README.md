# MascotCapsule Web Decompilation & Reverse Engineering

A complete decompilation, semantic reverse engineering, and deobfuscation of HI Corporation's **MascotCapsule Web** 3D software rendering engine.

## Overview
MascotCapsule was one of the earliest and most widespread 3D engines for mobile and web environments in the early 2000s (prominently on J2ME and web applets). This repository contains the complete decompilation of the pure-Java MascotCapsule Web software renderer (115 classes), fully audited, deobfuscated, fixed, and verified.

## Architecture Highlights
- **Core Software Rasterizer (`Config.java`)**: Pure software fixed-point 16.16 rasterizer with 64 specialized span drawers (Textured, Lit, SphereMap, and Flat in Triangle and Quad variants).
- **Scene & Pipeline (`RenderContext.java`, `SceneNode.java`, `CameraNode.java`)**: Hierarchical skeletal node transformations, look-at camera calculation, depth-bucket packet sorting, perspective/parallel projection, directional lighting, and specular highlights.
- **Model & Animation (`BacModel.java`, `TraAnimation.java`)**: Binary parser for `.jbac` (HIJB) meshes and `.jtra` (HIJT) skeletal animations.
- **Canvas & Framebuffer (`MascotCapsuleCanvas.java`, `MainCanvas.java`, `FrameBuffer.java`)**: AWT Canvas integration, double buffering, damage region tracking, and interactive controls.

## Building the Project

Ensure you have a Java 6+ JDK available (e.g. Oracle JDK 1.6 or OpenJDK):
```bash
./build.sh
```
This compiles all 115 source files and packages the final library to `build/MascotCapsule.jar`.

## Generating Sample Assets & Live Test

To regenerate the binary test assets (`.jbac`, `.jtra`, and BMP textures packaged into `sample.zip`):
```bash
python3 tools/generate_assets.py
```

To run the headless test renderer and capture the 3D output screenshot:
```bash
javac -cp build/classes:build/MascotCapsule.jar -d build/classes tools/TestRunner.java
java -Djava.awt.headless=true -cp build/classes:build/MascotCapsule.jar TestRunner
```
The output image will be saved to `docs/screenshot.png`.

## Documentation
Detailed reverse engineering notes and mapping tables are available in the [`docs/`](docs/) directory:
- [`docs/DEOBFUSCATION_REPORT.md`](docs/DEOBFUSCATION_REPORT.md): Comprehensive project audit, bug fixes, and findings.
- [`docs/OBFUSCATION_MAPPING.md`](docs/OBFUSCATION_MAPPING.md): Mapping of all 115 classes from obfuscated names to semantic names.
- [`docs/ARCHITECTURE.md`](docs/ARCHITECTURE.md): Structural breakdown of packages and engine subsystems.
- [`docs/FILE_FORMATS.md`](docs/FILE_FORMATS.md): Binary specifications for `.jbac` and `.jtra`.
- [`docs/RASTERIZER_PIPELINE.md`](docs/RASTERIZER_PIPELINE.md): In-depth walkthrough of the scanline rasterizer and drawers.
- [`docs/screenshot.png`](docs/screenshot.png): Captured 3D software rendering of the textured rotating cube.
