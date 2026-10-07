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
| `TexturedSpanDrawer` | `Class_1279` | 16 variants | Affine texture-mapped triangle spans |
| `UnlitSpanDrawer` | `ModelLoader` | 16 variants | Affine textured spans with constant/ambient color |
| `LitSpanDrawer` | `MeshLoader` | 16 variants | Affine textured spans with Gouraud-interpolated lighting |
| `SphereMapSpanDrawer` | `Class_15d5` | 16 variants | Environment sphere-mapped reflection spans |
| `FlatSpanDrawer` | `Class_eda` | 4 variants | Solid color filled polygon spans |
| `LineSpanDrawer` | `Class_d00` | 2 variants | Wireframe edge rendering spans |
| `LitColorSpanDrawer` | `Class_1279` (sub) | 2 variants | Untextured Gouraud-shaded spans |

Total concrete drawer implementations: $16 + 16 + 16 + 16 + 4 + 2 + 2 = 72$.

---

## 3D Rasterizer Array Indexing

In `Config.java` / `SoftwareRasterizer`:
- `var_81c[4][2][2]`: Textured drawers
- `var_83b[4][2][2]`: Unlit textured drawers
- `var_85a[4][2][2]`: Lit textured drawers
- `var_88e[4][2][2]`: Sphere map drawers

The 3 array indices are:
- `index 0` (0..3): **Blend Mode**
  - `0`: Opaque (`dest = src`)
  - `1`: 50% Alpha Blend (`dest = ((src & 0xFEFEFE) >> 1) + ((dest & 0xFEFEFE) >> 1)`)
  - `2`: Variable Alpha Blend using 8-bit alpha factor
  - `3`: Additive Blend (`dest = saturate(src + dest)`)
- `index 1` (0..1): **Masking / Transparency Key**
  - `0`: Opaque texels
  - `1`: Transparent texels (skips writing if texel color matches key)
- `index 2` (0..1): **Clipping**
  - `0`: Unclipped (interior triangles within screen bounds)
  - `1`: Clipped (triangles intersecting viewport boundaries)

---

## Fixed-Point DDA Scanline Algorithm

For any triangle:
1. Vertices are sorted by vertical screen coordinate ($y_0 \le y_1 \le y_2$).
2. The triangle is split at $y_1$ into a flat-bottom upper triangle and a flat-top lower triangle.
3. Slopes are calculated using 16.16 fixed point:
   - $dx_{left} / dy$, $dx_{right} / dy$
   - $du / dy$, $dv / dy$, $dz / dy$, $dI / dy$
4. On each horizontal scanline $y \in [y_{start}, y_{end}]$:
   - Left edge $x_{left}$ and right edge $x_{right}$ define the horizontal span $[x_{left}, x_{right}]$.
   - $u, v, I$ are stepped horizontally by $du/dx, dv/dx, dI/dx$.
   - Texels are sampled from the mipmapped texture buffer:
     $$\text{texelIndex} = \text{mipOffset} + ((v \ \& \ \text{vMask}) \gg \text{vShift}) + ((u \ \& \ \text{uMask}) \gg \text{uShift})$$
   - The computed pixel is written to `framebuffer[scanlineOffset + x]`.

---

## Mipmap Texture Sampling

The `Texture` class maintains up to 12 mipmap levels. The mip level $L$ is selected based on screen-space derivatives:
$$\Delta = |du/dx| + |dv/dx|$$
The rasterizer shifts coordinate lookups according to the selected mip level to minimize aliasing artifacts without requiring hardware trilinear filtering.
