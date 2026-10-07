import java.io.*;
import java.util.Random;
import com.hicorp.mascotcapsule.web.WebRasterizerBridge;
import com.mascotcapsule.micro3d.v3.MascotMERasterizerBridge;

public class Benchmark {
    static final int WIDTH = 320;
    static final int HEIGHT = 240;
    static final int TOTAL_PIXELS = WIDTH * HEIGHT;
    static final int WARMUP_RUNS = 15;
    static final int BENCHMARK_RUNS = 30;

    static final int[] TEST_POLYCOUNTS = { 100, 1000, 1488, 10000 };

    public static byte[] generate8bppBMP(int w, int h) {
        int palSize = 256 * 4;
        int headerSize = 14 + 40;
        int offset = headerSize + palSize;
        int rowSize = (w + 3) & ~3;
        int imgSize = rowSize * h;
        int fileSize = offset + imgSize;
        byte[] b = new byte[fileSize];

        b[0] = 'B'; b[1] = 'M';
        writeInt(b, 2, fileSize);
        writeInt(b, 10, offset);

        writeInt(b, 14, 40);
        writeInt(b, 18, w);
        writeInt(b, 22, h);
        writeShort(b, 26, 1);
        writeShort(b, 28, 8);
        writeInt(b, 30, 0);
        writeInt(b, 34, imgSize);
        writeInt(b, 46, 256);
        writeInt(b, 50, 256);

        for (int i = 0; i < 256; i++) {
            b[54 + i*4 + 0] = (byte)(i);
            b[54 + i*4 + 1] = (byte)(i * 2);
            b[54 + i*4 + 2] = (byte)(255 - i);
            b[54 + i*4 + 3] = 0;
        }

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                b[offset + y * rowSize + x] = (byte)((x ^ y) & 0xFF);
            }
        }
        return b;
    }

    private static void writeInt(byte[] b, int off, int v) {
        b[off] = (byte)(v & 0xFF);
        b[off+1] = (byte)((v >> 8) & 0xFF);
        b[off+2] = (byte)((v >> 16) & 0xFF);
        b[off+3] = (byte)((v >> 24) & 0xFF);
    }
    private static void writeShort(byte[] b, int off, int v) {
        b[off] = (byte)(v & 0xFF);
        b[off+1] = (byte)((v >> 8) & 0xFF);
    }

    static class BatchData {
        final int count;
        final int[][] inCoords;
        final int[][] clipCoords;
        final int[] colors;
        final int[][] texCoords;
        final int[][] uvs;
        final int[][] litValues;

        BatchData(int count, long seed) {
            this.count = count;
            Random rnd = new Random(seed);
            inCoords = new int[count][6];
            clipCoords = new int[count][6];
            colors = new int[count];
            texCoords = new int[count][6];
            uvs = new int[count][6];
            litValues = new int[count][3];

            for (int i = 0; i < count; i++) {
                // Strictly in-screen triangles
                int cx = 30 + rnd.nextInt(WIDTH - 60);
                int cy = 30 + rnd.nextInt(HEIGHT - 60);
                int sz = 16;
                inCoords[i][0] = cx + rnd.nextInt(sz) - sz/2;
                inCoords[i][1] = cy + rnd.nextInt(sz) - sz/2;
                inCoords[i][2] = cx + rnd.nextInt(sz) - sz/2;
                inCoords[i][3] = cy + rnd.nextInt(sz) - sz/2;
                inCoords[i][4] = cx + rnd.nextInt(sz) - sz/2;
                inCoords[i][5] = cy + rnd.nextInt(sz) - sz/2;

                // Boundary-crossing triangles
                int bcx = rnd.nextInt(WIDTH);
                int bcy = rnd.nextInt(HEIGHT);
                int bsz = 40;
                clipCoords[i][0] = bcx + rnd.nextInt(bsz) - bsz/2;
                clipCoords[i][1] = bcy + rnd.nextInt(bsz) - bsz/2;
                clipCoords[i][2] = bcx + rnd.nextInt(bsz) - bsz/2;
                clipCoords[i][3] = bcy + rnd.nextInt(bsz) - bsz/2;
                clipCoords[i][4] = bcx + rnd.nextInt(bsz) - bsz/2;
                clipCoords[i][5] = bcy + rnd.nextInt(bsz) - bsz/2;

                colors[i] = 0xFF000000 | rnd.nextInt(0x00FFFFFF);

                texCoords[i][0] = inCoords[i][0];
                texCoords[i][1] = inCoords[i][1];
                texCoords[i][2] = inCoords[i][2];
                texCoords[i][3] = inCoords[i][3];
                texCoords[i][4] = inCoords[i][4];
                texCoords[i][5] = inCoords[i][5];

                uvs[i][0] = rnd.nextInt(256);
                uvs[i][1] = rnd.nextInt(256);
                uvs[i][2] = rnd.nextInt(256);
                uvs[i][3] = rnd.nextInt(256);
                uvs[i][4] = rnd.nextInt(256);
                uvs[i][5] = rnd.nextInt(256);

                litValues[i][0] = rnd.nextInt(256);
                litValues[i][1] = rnd.nextInt(256);
                litValues[i][2] = rnd.nextInt(256);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("=======================================================================================");
        System.out.println("   MascotCapsule 3D Software Rasterizer Benchmark: Multi-Polycount Comparison         ");
        System.out.println("   Polycounts: 100, 1000, 1488, 10000 | Resolution: 320x240 (QVGA)                     ");
        System.out.println("=======================================================================================");
        System.out.println("Warmup iterations: " + WARMUP_RUNS + " | Benchmark iterations: " + BENCHMARK_RUNS);
        System.out.println();

        byte[] bmpBytes = generate8bppBMP(256, 256);

        // Setup MascotME
        int[] fbMascotME = new int[TOTAL_PIXELS];
        com.mascotcapsule.micro3d.v3.Texture texMascotME = new com.mascotcapsule.micro3d.v3.Texture(bmpBytes, true);
        MascotMERasterizerBridge.prepareLitTexture(texMascotME);

        // Setup MascotCapsuleWeb
        int[] fbWeb = new int[TOTAL_PIXELS];
        WebRasterizerBridge bridgeWeb = new WebRasterizerBridge(WIDTH, HEIGHT, fbWeb);
        com.hicorp.mascotcapsule.web.Texture texWeb = new com.hicorp.mascotcapsule.web.Texture();
        com.hicorp.mascotcapsule.web.BmpDecoder decoder = new com.hicorp.mascotcapsule.web.BmpDecoder();
        decoder.readImage(new ByteArrayInputStream(bmpBytes), texWeb);
        bridgeWeb.setTexture(texWeb);

        // Pre-generate data for each polycount
        BatchData[] batches = new BatchData[TEST_POLYCOUNTS.length];
        for (int i = 0; i < TEST_POLYCOUNTS.length; i++) {
            batches[i] = new BatchData(TEST_POLYCOUNTS[i], 1337 + TEST_POLYCOUNTS[i]);
        }

        // Global JIT warm up with a large run to compile hot loops
        System.out.print("Warming up JIT compiler across all shaders... ");
        BatchData warmBatch = new BatchData(10000, 9999);
        for (int i = 0; i < 25; i++) {
            for (int k = 0; k < warmBatch.count; k++) {
                int[] c = warmBatch.inCoords[k];
                bridgeWeb.drawFlatFast(c[0], c[1], c[2], c[3], c[4], c[5], warmBatch.colors[k]);
                MascotMERasterizerBridge.drawFlat(fbMascotME, WIDTH, 0, 0, WIDTH, HEIGHT, c[0], c[1], c[2], c[3], c[4], c[5], warmBatch.colors[k]);
                bridgeWeb.drawTexturedFast(c[0], c[1], c[2], c[3], c[4], c[5], 0, 0, 10, 0, 0, 10);
                MascotMERasterizerBridge.drawTextured(fbMascotME, WIDTH, 0, 0, WIDTH, HEIGHT, c[0], c[1], c[2], c[3], c[4], c[5], 0, 0, 10, 0, 0, 10, texMascotME);
                bridgeWeb.drawLitFast(c[0], c[1], c[2], c[3], c[4], c[5], 0, 0, 10, 0, 0, 10, 128, 128, 128);
                MascotMERasterizerBridge.drawLit(fbMascotME, WIDTH, 0, 0, WIDTH, HEIGHT, c[0], c[1], c[2], c[3], c[4], c[5], 0, 0, 10, 0, 0, 10, 128, 128, 128, texMascotME);
            }
        }
        System.out.println("Done.\n");

        for (BatchData batch : batches) {
            int poly = batch.count;
            System.out.println("#######################################################################################");
            System.out.printf("  POLYCOUNT: %d TRIANGLES%n", poly);
            System.out.println("#######################################################################################");

            // Scenario 1: Flat Shaded (Unclipped Fast Path)
            runScenario("1. Flat Shaded (Unclipped)", poly,
                () -> {
                    for (int i = 0; i < poly; i++) {
                        int[] c = batch.inCoords[i];
                        bridgeWeb.drawFlatFast(c[0], c[1], c[2], c[3], c[4], c[5], batch.colors[i]);
                    }
                },
                () -> {
                    for (int i = 0; i < poly; i++) {
                        int[] c = batch.inCoords[i];
                        MascotMERasterizerBridge.drawFlat(fbMascotME, WIDTH, 0, 0, WIDTH, HEIGHT,
                            c[0], c[1], c[2], c[3], c[4], c[5], batch.colors[i]);
                    }
                }
            );

            // Scenario 2: Flat Shaded (Clipped Path)
            runScenario("2. Flat Shaded (Clipped)", poly,
                () -> {
                    for (int i = 0; i < poly; i++) {
                        int[] c = batch.clipCoords[i];
                        bridgeWeb.drawFlat(c[0], c[1], c[2], c[3], c[4], c[5], batch.colors[i]);
                    }
                },
                () -> {
                    for (int i = 0; i < poly; i++) {
                        int[] c = batch.clipCoords[i];
                        MascotMERasterizerBridge.drawFlat(fbMascotME, WIDTH, 0, 0, WIDTH, HEIGHT,
                            c[0], c[1], c[2], c[3], c[4], c[5], batch.colors[i]);
                    }
                }
            );

            // Scenario 3: Textured (Unclipped Fast Path)
            runScenario("3. Textured 256x256 (Unclipped)", poly,
                () -> {
                    for (int i = 0; i < poly; i++) {
                        int[] c = batch.texCoords[i];
                        int[] uv = batch.uvs[i];
                        bridgeWeb.drawTexturedFast(c[0], c[1], c[2], c[3], c[4], c[5],
                            uv[0], uv[1], uv[2], uv[3], uv[4], uv[5]);
                    }
                },
                () -> {
                    for (int i = 0; i < poly; i++) {
                        int[] c = batch.texCoords[i];
                        int[] uv = batch.uvs[i];
                        MascotMERasterizerBridge.drawTextured(fbMascotME, WIDTH, 0, 0, WIDTH, HEIGHT,
                            c[0], c[1], c[2], c[3], c[4], c[5],
                            uv[0], uv[1], uv[2], uv[3], uv[4], uv[5],
                            texMascotME);
                    }
                }
            );

            // Scenario 4: Textured (Clipped Path)
            runScenario("4. Textured 256x256 (Clipped)", poly,
                () -> {
                    for (int i = 0; i < poly; i++) {
                        int[] c = batch.clipCoords[i];
                        int[] uv = batch.uvs[i];
                        bridgeWeb.drawTextured(c[0], c[1], c[2], c[3], c[4], c[5],
                            uv[0], uv[1], uv[2], uv[3], uv[4], uv[5]);
                    }
                },
                () -> {
                    for (int i = 0; i < poly; i++) {
                        int[] c = batch.clipCoords[i];
                        int[] uv = batch.uvs[i];
                        MascotMERasterizerBridge.drawTextured(fbMascotME, WIDTH, 0, 0, WIDTH, HEIGHT,
                            c[0], c[1], c[2], c[3], c[4], c[5],
                            uv[0], uv[1], uv[2], uv[3], uv[4], uv[5],
                            texMascotME);
                    }
                }
            );

            // Scenario 5: Lit Textured (Unclipped Fast Path)
            runScenario("5. Lit Textured (Unclipped)", poly,
                () -> {
                    for (int i = 0; i < poly; i++) {
                        int[] c = batch.texCoords[i];
                        int[] uv = batch.uvs[i];
                        int[] l = batch.litValues[i];
                        bridgeWeb.drawLitFast(c[0], c[1], c[2], c[3], c[4], c[5],
                            uv[0], uv[1], uv[2], uv[3], uv[4], uv[5],
                            l[0], l[1], l[2]);
                    }
                },
                () -> {
                    for (int i = 0; i < poly; i++) {
                        int[] c = batch.texCoords[i];
                        int[] uv = batch.uvs[i];
                        int[] l = batch.litValues[i];
                        MascotMERasterizerBridge.drawLit(fbMascotME, WIDTH, 0, 0, WIDTH, HEIGHT,
                            c[0], c[1], c[2], c[3], c[4], c[5],
                            uv[0], uv[1], uv[2], uv[3], uv[4], uv[5],
                            l[0], l[1], l[2],
                            texMascotME);
                    }
                }
            );

            // Scenario 6: Semi-Transparent / Blended
            runScenario("6. Semi-Transparent / Blended", poly,
                () -> {
                    for (int i = 0; i < poly; i++) {
                        int[] c = batch.inCoords[i];
                        bridgeWeb.drawBlendFast(c[0], c[1], c[2], c[3], c[4], c[5], batch.colors[i]);
                    }
                },
                () -> {
                    for (int i = 0; i < poly; i++) {
                        int[] c = batch.inCoords[i];
                        MascotMERasterizerBridge.drawBlend(fbMascotME, WIDTH, 0, 0, WIDTH, HEIGHT,
                            c[0], c[1], c[2], c[3], c[4], c[5], batch.colors[i]);
                    }
                }
            );
            System.out.println();
        }
    }

    interface Action {
        void run();
    }

    static void runScenario(String name, int polycount, Action webAction, Action meAction) {
        // Warmup MascotCapsuleWeb
        for (int i = 0; i < WARMUP_RUNS; i++) {
            webAction.run();
        }
        long bestWeb = Long.MAX_VALUE;
        long totalWeb = 0;
        for (int i = 0; i < BENCHMARK_RUNS; i++) {
            long t0 = System.nanoTime();
            webAction.run();
            long t1 = System.nanoTime();
            long dt = t1 - t0;
            if (dt < bestWeb) bestWeb = dt;
            totalWeb += dt;
        }
        double bestWebUs = bestWeb / 1_000.0;
        double avgWebUs = (totalWeb / (double)BENCHMARK_RUNS) / 1_000.0;
        double webMTrisSec = (polycount / (bestWeb / 1_000_000_000.0)) / 1_000_000.0;

        // Warmup MascotME
        for (int i = 0; i < WARMUP_RUNS; i++) {
            meAction.run();
        }
        long bestME = Long.MAX_VALUE;
        long totalME = 0;
        for (int i = 0; i < BENCHMARK_RUNS; i++) {
            long t0 = System.nanoTime();
            meAction.run();
            long t1 = System.nanoTime();
            long dt = t1 - t0;
            if (dt < bestME) bestME = dt;
            totalME += dt;
        }
        double bestMEUs = bestME / 1_000.0;
        double avgMEUs = (totalME / (double)BENCHMARK_RUNS) / 1_000.0;
        double meMTrisSec = (polycount / (bestME / 1_000_000_000.0)) / 1_000_000.0;

        double ratio = (double)bestME / (double)bestWeb;
        String winner = ratio >= 1.0 ? String.format("Web is %.2fx faster", ratio)
                                     : String.format("MascotME is %.2fx faster", 1.0 / ratio);

        System.out.printf("  %-32s | Web: %7.1f us (%5.2f M/s) | ME: %7.1f us (%5.2f M/s) | %s%n",
            name, bestWebUs, webMTrisSec, bestMEUs, meMTrisSec, winner);
    }
}
