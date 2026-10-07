# MascotCapsule 3D Web - Binary File Formats

This document describes the binary formats parsed by the MascotCapsule Web 3D software rendering engine. All binary data is stored in Little-Endian byte order.

---

## 1. JBAC Format (.jbac) - 3D Model Format

The JBAC format is the binary container for 3D polygon meshes and skeletal hierarchies parsed by `BacModel.java`.

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

#### Triangle Face Structure (11 fields per triangle, `ModelPolygon.java`)
- `flags` (`int32`): Material ID, double-sided, lighting, and blend flags
- `vert0` (`int16`): Index of vertex 0
- `u0` (`int16`): U texture coordinate 0
- `v0` (`int16`): V texture coordinate 0
- `vert1` (`int16`): Index of vertex 1
- `u1` (`int16`): U texture coordinate 1
- `v1` (`int16`): V texture coordinate 1
- `vert2` (`int16`): Index of vertex 2
- `u2` (`int16`): U texture coordinate 2
- `v2` (`int16`): V texture coordinate 2

#### Quad Face Structure (14 fields per quad)
- `flags` (`int32`): Material and blend flags
- `vert0` (`int16`): Index of vertex 0
- `u0` (`int16`): U texture coordinate 0
- `v0` (`int16`): V texture coordinate 0
- `vert1` (`int16`): Index of vertex 1
- `u1` (`int16`): U texture coordinate 1
- `v1` (`int16`): V texture coordinate 1
- `vert2` (`int16`): Index of vertex 2
- `u2` (`int16`): U texture coordinate 2
- `v2` (`int16`): V texture coordinate 2
- `vert3` (`int16`): Index of vertex 3
- `u3` (`int16`): U texture coordinate 3
- `v3` (`int16`): V texture coordinate 3

### Skeleton Section
| Field | Type | Description |
|---|---|---|
| `boneCount` | `int32` | Total number of skeletal bones |
| Bone Nodes | Recursive Tree | Hierarchy of `BoneNode` structures |

#### Bone Node Structure
- `name` (`CString`): Bone joint name (ASCII null-terminated)
- `restTransform` (`float[12]`): 4x3 affine transform matrix in rest pose
- `hasChild` (`int32`): 1 if bone has child joint, 0 otherwise
- `hasSibling` (`int32`): 1 if bone has sibling joint, 0 otherwise

---

## 2. JTRA Format (.jtra) - Skeletal Animation Format

The JTRA format contains skeletal keyframe animation data parsed by `TraAnimation.java`.

### Header
| Offset | Type | Field | Description |
|---|---|---|---|
| 0x00 | `byte[4]` | `magic` | Magic header bytes: `0x48, 0x49, 0x4A, 0x54` ("HIJT" / HI Japan TRA) |
| 0x04 | `int32` | `version` | Format version (must equal `1`) |
| 0x08 | `float` | `duration` | Total duration of animation in seconds |
| 0x0C | `int32` | `boneCount` | Number of bones animated in this track |

### 10-Channel Bone Track
Each bone has up to 10 independent animation channels evaluated over time:
1. `Rotation X` (Euler degrees)
2. `Rotation Y` (Euler degrees)
3. `Rotation Z` (Euler degrees)
4. `Translation X` (world units)
5. `Translation Y` (world units)
6. `Translation Z` (world units)
7. `Scale X` (dimension factor)
8. `Scale Y` (dimension factor)
9. `Scale Z` (dimension factor)
10. `Visibility` (binary toggle)

Each channel stores an array of timestamped keyframes (`KeyframePoint.java`), interpolated at runtime using piecewise linear and Hermite cubic spline interpolation (`InterpolatedKeyframe.java`).
