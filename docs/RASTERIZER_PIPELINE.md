# MascotCapsule 3D Web - Software Rasterizer Pipeline

## Overview

The MascotCapsule Web software rasterizer is an integer fixed-point (16.16) scanline triangle rasterizer designed to execute at high speed on JVMs lacking JIT compilers. 

To eliminate branching and polymorphism overhead inside innermost pixel loops, the rasterizer utilizes **ahead-of-time specialized inner classes** covering all permutations of:
1. Shading technique (Textured, Unlit Textured, Gouraud Lit Textured, Sphere Mapped, Flat Colored, Wireframe Line)
2. Alpha Blending Mode (Opaque, 50% Semi-Transparent, Arbitrary Alpha Blend, Additive Blend)
3. Transparency Keying (Opaque texture vs. Color-Keyed Masked texture)
4. Screen Clipping (Unclipped fast path vs. Clipped safe path)

---

## The 6 Span Drawer Hierarchies

In the original obfuscated bytecode, these span drawer classes were inner classes of `o.class` (`Config.java`).

| Base Class | Original Name | Span Count | Rendering Operation |
|---|---|---|---|
| `TexturedDrawer` | `Class_1279` | 16 variants | Affine texture-mapped triangle spans |
| `UnlitDrawer` | `ModelLoader` | 16 variants | Affine textured spans with constant/ambient color |
| `LitDrawer` | `MeshLoader` | 16 variants | Affine textured spans with Gouraud-interpolated lighting |
| `SphereMapDrawer` | `Class_15d5` | 16 variants | Environment sphere-mapped reflection spans |
| `FlatDrawer` | `Class_eda` | 4 variants | Solid color filled polygon spans |
| `LineDrawer` | `Class_d00` | 2 variants | Wireframe edge rendering spans |
| `LitColorDrawer` | Sub of `LineDrawer` | 2 variants | Untextured Gouraud-shaded spans |

Total concrete drawer implementations: $16 + 16 + 16 + 16 + 4 + 2 + 2 = 72$.

---

## 3D Rasterizer Array Indexing

In `Config.java` (`SoftwareRasterizer`):
- `texturedDrawers[4][2][2]`: Textured drawers (T0..T3, Opaque/Alpha, Tri/Quad)
- `unlitDrawers[4][2][2]`: Unlit textured drawers (T0..T3, Opaque/Alpha, Tri/Quad)
- `litDrawers[4][2][2]`: Lit textured drawers (T0..T3, Opaque/Alpha, Tri/Quad)
- `sphereMapDrawers[4][2][2]`: Sphere map reflection drawers (T0..T3, Opaque/Alpha, Tri/Quad)
- `flatDrawers[2][2]`: Flat color drawers (Opaque/Alpha, Tri/Quad)
- `lineDrawers[2]`: 3D line drawers (Opaque/Alpha)
- `litColorDrawers[2]`: Gouraud-shaded lit color drawers (Opaque/Alpha)

---

## 16.16 Fixed-Point Math in Scanline Drawing

The rasterizer converts floating-point screen coordinates and texture UVs into 16.16 fixed-point representation:

$$X_{fixed} = (X \ll 16) + 32768$$
$$dX_{fixed} = \frac{(X_2 - X_1) \ll 16}{Y_2 - Y_1}$$

To avoid integer divide instructions inside raster loops, a fast 16.16 reciprocal table method (`fixedReciprocal(int delta)`) is used:

$$\text{fixedReciprocal}(\Delta) = \frac{65536}{\Delta}$$

For every scanline $Y$, the left and right span edge coordinates ($X_{left}, X_{right}$) and interpolated parameters (texture $U, V$, light intensity, normal $Z$) are stepped:

$$X_{left} \mathrel{+}= dX_{left}, \quad U \mathrel{+}= dU, \quad V \mathrel{+}= dV, \quad \text{Light} \mathrel{+}= d\text{Light}$$

Pixel blending uses bitwise masks to process red and blue channels together in a single 32-bit integer register:
- `RB_MASK = 0x00FF00FF`
- `G_MASK = 0x0000FF00`
- `COLOR_MASK = 0x00FEFEFE` (prevents overflow during parallel channel addition)
- 512-entry `blendTable` for branchless lighting saturation clamping.
