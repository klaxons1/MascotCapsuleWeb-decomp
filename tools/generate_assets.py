#!/usr/bin/env python3
import struct, math, zipfile, os

def make_bmp(width, height, color_func):
    row_bytes = width * 3
    padding = (4 - (row_bytes % 4)) % 4
    image_size = (row_bytes + padding) * height
    file_size = 54 + image_size
    
    header = bytearray(54)
    header[0:2] = b"BM"
    struct.pack_into("<I", header, 2, file_size)
    struct.pack_into("<I", header, 10, 54) # pixel offset
    struct.pack_into("<I", header, 14, 40) # DIB header size (WIN_V3_HEADER_SIZE)
    struct.pack_into("<i", header, 18, width)
    struct.pack_into("<i", header, 22, height)
    struct.pack_into("<H", header, 26, 1) # planes
    struct.pack_into("<H", header, 28, 24) # 24 bpp
    struct.pack_into("<I", header, 30, 0) # BI_RGB
    struct.pack_into("<I", header, 34, image_size)
    
    pixels = bytearray()
    # BMP is stored bottom-up
    for y in range(height):
        for x in range(width):
            b, g, r = color_func(x, y, width, height)
            pixels.extend([b & 0xFF, g & 0xFF, r & 0xFF])
        pixels.extend(b"\x00" * padding)
    return bytes(header + pixels)

def generate_textures():
    # 1. model.bmp: colorful checkerboard with border
    def model_color(x, y, w, h):
        cx = x // 32
        cy = y // 32
        border = (x % 32 < 2) or (y % 32 < 2)
        if border:
            return (30, 30, 30) # dark charcoal grid border (B, G, R)
        if (cx + cy) % 2 == 0:
            return (40, 160, 245)  # vibrant warm orange/gold (B, G, R)
        else:
            return (230, 120, 30)   # vibrant cyan/azure (B, G, R)
            
    # 2. sphere.bmp: metallic shiny spherical reflection
    def sphere_color(x, y, w, h):
        nx = (x - w / 2) / (w / 2)
        ny = (y - h / 2) / (h / 2)
        r2 = nx * nx + ny * ny
        if r2 > 1.0:
            return (20, 20, 30)
        nz = math.sqrt(max(0.0, 1.0 - r2))
        # light from (-0.5, 0.5, 0.7)
        lx, ly, lz = -0.4, 0.4, 0.8
        dot = max(0.0, nx * lx + ny * ly + nz * lz)
        spec = math.pow(dot, 8.0)
        val = int(min(255, 60 + 140 * dot + 255 * spec))
        return (val, int(val * 0.9), int(val * 0.7))

    # 3. wall.bmp: stylish dark tech gradient
    def wall_color(x, y, w, h):
        diag = (x + y) / (w + h)
        r = int(25 + 40 * diag)
        g = int(35 + 50 * diag)
        b = int(60 + 90 * diag)
        if (x % 64 == 0) or (y % 64 == 0):
            r += 25
            g += 25
            b += 35
        return (b, g, r)

    return (
        make_bmp(256, 256, model_color),
        make_bmp(256, 256, sphere_color),
        make_bmp(256, 256, wall_color)
    )

def generate_jbac():
    out = bytearray()
    out.extend(b"HIJB")
    out.extend(struct.pack("<I", 1)) # version = 1
    out.extend(b"\x00" * 12) # skip 12
    out.extend(b"\x01" * 64) # headerPad (padSum = 64 != 0)
    
    # 8 vertices of a cube (-60 to +60)
    verts = [
        (-60.0, -60.0, -60.0), # 0
        ( 60.0, -60.0, -60.0), # 1
        ( 60.0,  60.0, -60.0), # 2
        (-60.0,  60.0, -60.0), # 3
        (-60.0, -60.0,  60.0), # 4
        ( 60.0, -60.0,  60.0), # 5
        ( 60.0,  60.0,  60.0), # 6
        (-60.0,  60.0,  60.0), # 7
    ]
    out.extend(struct.pack("<I", len(verts)))
    for vx, vy, vz in verts:
        out.extend(struct.pack("<fff", vx, vy, vz))
        
    # normals (8 normals)
    out.extend(struct.pack("<I", len(verts)))
    inv_sqrt3 = 1.0 / math.sqrt(3.0)
    for vx, vy, vz in verts:
        nx = inv_sqrt3 if vx > 0 else -inv_sqrt3
        ny = inv_sqrt3 if vy > 0 else -inv_sqrt3
        nz = inv_sqrt3 if vz > 0 else -inv_sqrt3
        out.extend(struct.pack("<fff", nx, ny, nz))
        
    # 12 triangles (6 faces * 2 triangles)
    # faces definition: (v0, v1, v2), (v0, v2, v3)
    faces = [
        # Front face (z = +60): verts 4, 5, 6, 7
        ((4, 0, 0), (5, 255, 0), (6, 255, 255)),
        ((4, 0, 0), (6, 255, 255), (7, 0, 255)),
        # Back face (z = -60): verts 1, 0, 3, 2
        ((1, 0, 0), (0, 255, 0), (3, 255, 255)),
        ((1, 0, 0), (3, 255, 255), (2, 0, 255)),
        # Top face (y = +60): verts 7, 6, 2, 3
        ((7, 0, 0), (6, 255, 0), (2, 255, 255)),
        ((7, 0, 0), (2, 255, 255), (3, 0, 255)),
        # Bottom face (y = -60): verts 0, 1, 5, 4
        ((0, 0, 0), (1, 255, 0), (5, 255, 255)),
        ((0, 0, 0), (5, 255, 255), (4, 0, 255)),
        # Right face (x = +60): verts 5, 1, 2, 6
        ((5, 0, 0), (1, 255, 0), (2, 255, 255)),
        ((5, 0, 0), (2, 255, 255), (6, 0, 255)),
        # Left face (x = -60): verts 0, 4, 7, 3
        ((0, 0, 0), (4, 255, 0), (7, 255, 255)),
        ((0, 0, 0), (7, 255, 255), (3, 0, 255)),
    ]
    
    tri_count = len(faces)
    quad_count = 0
    polygon_count = tri_count + quad_count
    
    out.extend(struct.pack("<I", polygon_count))
    out.extend(struct.pack("<I", tri_count))
    
    flags = 8192 | 1 # FLAG_LIGHTING | FLAG_DOUBLE_SIDED
    for (v0, u0, v0_coord), (v1, u1, v1_coord), (v2, u2, v2_coord) in faces:
        out.extend(struct.pack("<I", flags))
        out.extend(struct.pack("<hhh", v0, u0, v0_coord))
        out.extend(struct.pack("<hhh", v1, u1, v1_coord))
        out.extend(struct.pack("<hhh", v2, u2, v2_coord))
        
    out.extend(struct.pack("<I", quad_count))
    
    # 1 Bone: root
    bone_count = 1
    out.extend(struct.pack("<I", bone_count))
    out.extend(struct.pack("bb", 0, 0)) # hasChild=0, hasSibling=0
    out.extend(b"root\x00")
    # restTransform 3x4: identity
    # m00, m01, m02, m10, m11, m12, m20, m21, m22, m03, m13, m23
    out.extend(struct.pack("<ffffffffffff",
        1.0, 0.0, 0.0,
        0.0, 1.0, 0.0,
        0.0, 0.0, 1.0,
        0.0, 0.0, 0.0
    ))
    out.extend(struct.pack("<h", len(verts))) # vertexCount for this bone
    
    return bytes(out)

def generate_jtra():
    out = bytearray()
    out.extend(b"HIJT")
    out.extend(struct.pack("<I", 1)) # ver = 1
    out.extend(b"\x00" * 12) # skip 12
    out.extend(b"\x00" * 64) # headerPad
    
    frames = 60
    bone_count = 1
    out.extend(struct.pack("<hh", frames, bone_count))
    
    # Bone 0
    out.extend(b"root\x00")
    out.extend(struct.pack("<fff", 0.0, 0.0, 0.0)) # translation
    out.extend(struct.pack("<fff", 0.577, 0.816, 0.0)) # rotationAxis
    out.extend(struct.pack("<fff", 0.0, 0.0, 0.0)) # rotationAngles
    out.extend(struct.pack("<fff", 100.0, 100.0, 100.0)) # scale
    
    # 10 tracks:
    # 0: pos X, 1: pos Y, 2: pos Z
    # 3: scale X, 4: scale Y, 5: scale Z
    # 6: rotAxis X, 7: rotAxis Y, 8: rotAxis Z
    # 9: rotAngle (degrees)
    
    tracks_data = {
        0: [(0, 0.0), (59, 0.0)],
        1: [(0, 0.0), (30, 20.0), (59, 0.0)], # bob up and down
        2: [(0, 0.0), (59, 0.0)],
        3: [(0, 100.0), (59, 100.0)],
        4: [(0, 100.0), (59, 100.0)],
        5: [(0, 100.0), (59, 100.0)],
        6: [(0, 0.577), (59, 0.577)],
        7: [(0, 0.816), (59, 0.816)],
        8: [(0, 0.0), (59, 0.0)],
        9: [(0, 0.0), (59, 360.0)], # 360 degree spin
    }
    
    for t in range(10):
        kfs = tracks_data[t]
        out.extend(struct.pack("<h", len(kfs)))
        for frame_time, val in kfs:
            out.extend(struct.pack("<hf", frame_time, val))
            
    return bytes(out)

def build_archive():
    tex_model, tex_sphere, tex_wall = generate_textures()
    jbac_data = generate_jbac()
    jtra_data = generate_jtra()
    
    # Write separate files in docs/assets/ as well
    os.makedirs("docs/sample_assets", exist_ok=True)
    with open("docs/sample_assets/model.jbac", "wb") as f:
        f.write(jbac_data)
    with open("docs/sample_assets/motion.jtra", "wb") as f:
        f.write(jtra_data)
    with open("docs/sample_assets/model.bmp", "wb") as f:
        f.write(tex_model)
    with open("docs/sample_assets/sphere.bmp", "wb") as f:
        f.write(tex_sphere)
    with open("docs/sample_assets/wall.bmp", "wb") as f:
        f.write(tex_wall)
        
    zip_path = "docs/sample.zip"
    with zipfile.ZipFile(zip_path, "w", compression=zipfile.ZIP_DEFLATED) as zf:
        zf.writestr("model.jbac", jbac_data)
        zf.writestr("motion.jtra", jtra_data)
        zf.writestr("model.bmp", tex_model)
        zf.writestr("sphere.bmp", tex_sphere)
        zf.writestr("wall.bmp", tex_wall)
        
    print(f"Archive built successfully -> {zip_path} ({os.path.getsize(zip_path)} bytes)")

if __name__ == "__main__":
    build_archive()
