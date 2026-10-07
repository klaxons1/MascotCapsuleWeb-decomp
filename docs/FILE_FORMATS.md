# MascotCapsule 3D Web - Binary File Formats

This document describes the binary formats parsed by the MascotCapsule Web 3D software rendering engine. All binary data is stored in Little-Endian byte order.

---

## 1. JBAC Format (.jbac) - 3D Model Format

The JBAC format is the binary container for 3D polygon meshes and skeletal hierarchies.

### Header
| Offset | Type | Field | Description |
|---|---|---|---|
| 0x00 | `byte[4]` | `magic` | Magic header bytes: `0x48, 0x49, 0x4A, 0x42` ("HIJB" / HI Japan BAC) |
| 0x04 | `int32` | `version` | Format version (must equal `1`) |

### Geometry Section
| Field | Type | Description |
|---|---|---|
| `hasNormals` | `int32` | Flag indicating if explicit vertex normals are present (`1` = yes, `0` = generate) |
| `vertexCount` | `int32` | Total number of 3D vertices |
| `vertices` | `float[vertexCount * 3]` | (x, y, z) coordinates for each vertex |
| `normals` | `float[vertexCount * 3]` | (nx, ny, nz) coordinates for each normal (if `hasNormals == 1`) |

### Polygon Section
| Field | Type | Description |
|---|---|---|
| `polyCount` | `int32` | Total number of polygons (triangles + quads) |
| `triangleCount`| `int32` | Number of triangle polygons |

#### Triangle Face Structure (11 fields per triangle)
- `flags` (`int32`): Material ID and rendering flags
- `v0` (`int16`): Index of vertex 0
- `u0` (`int16`): U texture coordinate 0
- `v0_coord` (`int16`): V texture coordinate 0
- `v1` (`int16`): Index of vertex 1
- `u1` (`int16`): U texture coordinate 1
- `v1_coord` (`int16`): V texture coordinate 1
- `v2` (`int16`): Index of vertex 2
- `u2` (`int16`): U texture coordinate 2
- `v2_coord` (`int16`): V texture coordinate 2
- `normalIndex` (`int16`): Normal index or bone weighting

#### Quad Face Structure (15 fields per quad)
- `flags` (`int32`): Material ID and rendering flags
- Triangles fields (v0, u0, v0_coord, v1, u1, v1_coord, v2, u2, v2_coord)
- `v3` (`int16`): Index of vertex 3
- `u3` (`int16`): U texture coordinate 3
- `v3_coord` (`int16`): V texture coordinate 3

### Skeletal Bone Section
| Field | Type | Description |
|---|---|---|
| `boneCount` | `int32` | Total number of bones in the skeleton hierarchy |

#### Bone Node Structure (Recursive tree)
Each bone node is deserialized sequentially:
- `hasChild` (`byte`): `0` or `1` (if 1, child node follows)
- `hasSibling` (`byte`): `0` or `1` (if 1, sibling node follows)
- `name` (`null-terminated ASCII string`): Bone identifier (e.g., "root", "arm_l")
- `transform` (`float[12]`): 3x4 local affine matrix:
  - 3x3 rotation/scale matrix (`m00, m01, m02, m10, m11, m12, m20, m21, m22`)
  - 3 translation coordinates (`tx, ty, tz`)
- `id` (`int16`): Bone index

---

## 2. JTRA Format (.jtra) - Skeletal Animation Format

The JTRA format stores skeletal keyframe animation data.

### Header
| Offset | Type | Field | Description |
|---|---|---|---|
| 0x00 | `byte[4]` | `magic` | Magic header bytes: `0x48, 0x49, 0x4A, 0x54` ("HIJT" / HI Japan TRA) |
| 0x04 | `int32` | `version` | Format version (must equal `1`) |
| 0x08 | `int32` | `duration` | Total animation duration / number of frames |
| 0x0C | `int32` | `boneCount` | Number of animated bones in this animation |

### Bone Animation Channels
For each bone (0 to `boneCount - 1`):
- `boneName` (`null-terminated ASCII string`): Name matching a bone in the JBAC model
- `channels[10]` (`Channel`): 10 independent keyframe channels:
  1. Translation X
  2. Translation Y
  3. Translation Z
  4. Rotation Axis X
  5. Rotation Axis Y
  6. Rotation Axis Z
  7. Rotation Angle
  8. Scale X
  9. Scale Y
  10. Scale Z

#### Channel Structure
- `keyframeCount` (`int32`): Number of keyframes in this track
- For each keyframe:
  - `time` (`int32`): Keyframe frame index
  - `value` (`float`): Channel value at this frame

During animation playback, linear interpolation is performed:
$$\text{value}(t) = v_0 + \frac{t - t_0}{t_1 - t_0} \times (v_1 - v_0)$$
The 10 channel values at time $t$ are then composed into translation, rotation (axis-angle), and scaling matrices which are multiplied to reconstruct the bone's local transformation matrix for that frame.
