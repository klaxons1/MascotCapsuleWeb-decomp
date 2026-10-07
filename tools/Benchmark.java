import java.io.*;
import java.util.Random;
import com.hicorp.mascotcapsule.web.WebRasterizerBridge;
import com.mascotcapsule.micro3d.v3.MascotMERasterizerBridge;

public class Benchmark {
    static final int WIDTH = 320;
    static final int HEIGHT = 240;
    static final int TOTAL_PIXELS = WIDTH * HEIGHT;
    static final int WARMUP_RUNS = 8;
    static final int BENCHMARK_RUNS = 15;
    static final int NUM_TRIANGLES = 25000;

    // Small triangles (in-screen)
    static int[][] inCoords;
    static int[] flatColors;
    static int[][] texCoords;
    static int[][] uvs;
    static int[][] litValues;

    // Boundary / clipping triangles
    static int[][] clipCoords;

    // Large fill-rate triangles
    static int[][] largeCoords;

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

    public static void generateData() {
        Random rnd = new Random(1337);
        inCoords = new int[NUM_TRIANGLES][6];
        clipCoords = new int[NUM_TRIANGLES][6];
        flatColors = new int[NUM_TRIANGLES];
        texCoords = new int[NUM_TRIANGLES][6];
        uvs = new int[NUM_TRIANGLES][6];
        litValues = new int[NUM_TRIANGLES][3];

        for (int i = 0; i < NUM_TRIANGLES; i++) {
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

            // Boundary crossing triangles
            int bcx = rnd.nextInt(WIDTH);
            int bcy = rnd.nextInt(HEIGHT);
            int bsz = 40;
            clipCoords[i][0] = bcx + rnd.nextInt(bsz) - bsz/2;
            clipCoords[i][1] = bcy + rnd.nextInt(bsz) - bsz/2;
            clipCoords[i][2] = bcx + rnd.nextInt(bsz) - bsz/2;
            clipCoords[i][3] = bcy + rnd.nextInt(bsz) - bsz/2;
            clipCoords[i][4] = bcx + rnd.nextInt(bsz) - bsz/2;
            clipCoords[i][5] = bcy + rnd.nextInt(bsz) - bsz/2;

            flatColors[i] = 0xFF000000 | rnd.nextInt(0x00FFFFFF);

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

        // Large triangles
        largeCoords = new int[5000][6];
        for (int i = 0; i < 5000; i++) {
            largeCoords[i][0] = 10 + rnd.nextInt(WIDTH - 20);
            largeCoords[i][1] = 10 + rnd.nextInt(HEIGHT - 20);
            largeCoords[i][2] = 10 + rnd.nextInt(WIDTH - 20);
            largeCoords[i][3] = 10 + rnd.nextInt(HEIGHT - 20);
            largeCoords[i][4] = 10 + rnd.nextInt(WIDTH - 20);
            largeCoords[i][5] = 10 + rnd.nextInt(HEIGHT - 20);
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("================================================================================");
        System.out.println("     COMPREHENSIVE SOFTWARE RASTERIZER BENCHMARK: WEB vs MASCOTME               ");
        System.out.println("================================================================================");
        System.out.println("Target Resolution  : " + WIDTH + "x" + HEIGHT + " (" + TOTAL_PIXELS + " pixels)");
        System.out.println("Micro-triangles    : " + NUM_TRIANGLES + " per iteration");
        System.out.println("Warmup iterations  : " + WARMUP_RUNS + " | Benchmark iterations: " + BENCHMARK_RUNS);
        System.out.println();

        generateData();

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

        // 1. Flat Shaded Triangles (In-Screen, Fast Unclipped vs Clipped)
        benchmarkScenario("1A. Flat Shaded Triangles (Unclipped Fast Path)", NUM_TRIANGLES,
            () -> {
                for (int i = 0; i < NUM_TRIANGLES; i++) {
                    int[] c = inCoords[i];
                    bridgeWeb.drawFlatFast(c[0], c[1], c[2], c[3], c[4], c[5], flatColors[i]);
                }
            },
            () -> {
                for (int i = 0; i < NUM_TRIANGLES; i++) {
                    int[] c = inCoords[i];
                    MascotMERasterizerBridge.drawFlat(fbMascotME, WIDTH, 0, 0, WIDTH, HEIGHT,
                        c[0], c[1], c[2], c[3], c[4], c[5], flatColors[i]);
                }
            }
        );

        benchmarkScenario("1B. Flat Shaded Triangles (Clipped Path)", NUM_TRIANGLES,
            () -> {
                for (int i = 0; i < NUM_TRIANGLES; i++) {
                    int[] c = clipCoords[i];
                    bridgeWeb.drawFlat(c[0], c[1], c[2], c[3], c[4], c[5], flatColors[i]);
                }
            },
            () -> {
                for (int i = 0; i < NUM_TRIANGLES; i++) {
                    int[] c = clipCoords[i];
                    MascotMERasterizerBridge.drawFlat(fbMascotME, WIDTH, 0, 0, WIDTH, HEIGHT,
                        c[0], c[1], c[2], c[3], c[4], c[5], flatColors[i]);
                }
            }
        );

        // 2. Textured Triangles (Fast Unclipped vs Clipped)
        benchmarkScenario("2A. Textured Triangles 256x256 (Unclipped Fast Path)", NUM_TRIANGLES,
            () -> {
                for (int i = 0; i < NUM_TRIANGLES; i++) {
                    int[] c = texCoords[i];
                    int[] uv = uvs[i];
                    bridgeWeb.drawTexturedFast(c[0], c[1], c[2], c[3], c[4], c[5],
                        uv[0], uv[1], uv[2], uv[3], uv[4], uv[5]);
                }
            },
            () -> {
                for (int i = 0; i < NUM_TRIANGLES; i++) {
                    int[] c = texCoords[i];
                    int[] uv = uvs[i];
                    MascotMERasterizerBridge.drawTextured(fbMascotME, WIDTH, 0, 0, WIDTH, HEIGHT,
                        c[0], c[1], c[2], c[3], c[4], c[5],
                        uv[0], uv[1], uv[2], uv[3], uv[4], uv[5],
                        texMascotME);
                }
            }
        );

        benchmarkScenario("2B. Textured Triangles 256x256 (Clipped Path)", NUM_TRIANGLES,
            () -> {
                for (int i = 0; i < NUM_TRIANGLES; i++) {
                    int[] c = clipCoords[i];
                    int[] uv = uvs[i];
                    bridgeWeb.drawTextured(c[0], c[1], c[2], c[3], c[4], c[5],
                        uv[0], uv[1], uv[2], uv[3], uv[4], uv[5]);
                }
            },
            () -> {
                for (int i = 0; i < NUM_TRIANGLES; i++) {
                    int[] c = clipCoords[i];
                    int[] uv = uvs[i];
                    MascotMERasterizerBridge.drawTextured(fbMascotME, WIDTH, 0, 0, WIDTH, HEIGHT,
                        c[0], c[1], c[2], c[3], c[4], c[5],
                        uv[0], uv[1], uv[2], uv[3], uv[4], uv[5],
                        texMascotME);
                }
            }
        );

        // 3. Lit Textured Triangles (Fast Unclipped vs Clipped)
        benchmarkScenario("3A. Lit Textured Triangles (Unclipped Fast Path)", NUM_TRIANGLES,
            () -> {
                for (int i = 0; i < NUM_TRIANGLES; i++) {
                    int[] c = texCoords[i];
                    int[] uv = uvs[i];
                    int[] l = litValues[i];
                    bridgeWeb.drawLitFast(c[0], c[1], c[2], c[3], c[4], c[5],
                        uv[0], uv[1], uv[2], uv[3], uv[4], uv[5],
                        l[0], l[1], l[2]);
                }
            },
            () -> {
                for (int i = 0; i < NUM_TRIANGLES; i++) {
                    int[] c = texCoords[i];
                    int[] uv = uvs[i];
                    int[] l = litValues[i];
                    MascotMERasterizerBridge.drawLit(fbMascotME, WIDTH, 0, 0, WIDTH, HEIGHT,
                        c[0], c[1], c[2], c[3], c[4], c[5],
                        uv[0], uv[1], uv[2], uv[3], uv[4], uv[5],
                        l[0], l[1], l[2],
                        texMascotME);
                }
            }
        );

        // 4. Alpha Blended Triangles
        benchmarkScenario("4. Semi-Transparent / Blended Triangles", NUM_TRIANGLES,
            () -> {
                for (int i = 0; i < NUM_TRIANGLES; i++) {
                    int[] c = inCoords[i];
                    bridgeWeb.drawBlendFast(c[0], c[1], c[2], c[3], c[4], c[5], flatColors[i]);
                }
            },
            () -> {
                for (int i = 0; i < NUM_TRIANGLES; i++) {
                    int[] c = inCoords[i];
                    MascotMERasterizerBridge.drawBlend(fbMascotME, WIDTH, 0, 0, WIDTH, HEIGHT,
                        c[0], c[1], c[2], c[3], c[4], c[5], flatColors[i]);
                }
            }
        );

        // 5. Fill-rate Stress Test (Large Triangles)
        benchmarkScenario("5. High Fill-Rate Stress Test (Large Overdrawn Triangles)", 5000,
            () -> {
                for (int i = 0; i < 5000; i++) {
                    int[] c = largeCoords[i];
                    bridgeWeb.drawFlat(c[0], c[1], c[2], c[3], c[4], c[5], flatColors[i]);
                }
            },
            () -> {
                for (int i = 0; i < 5000; i++) {
                    int[] c = largeCoords[i];
                    MascotMERasterizerBridge.drawFlat(fbMascotME, WIDTH, 0, 0, WIDTH, HEIGHT,
                        c[0], c[1], c[2], c[3], c[4], c[5], flatColors[i]);
                }
            }
        );
    }

    interface Action {
        void run();
    }

    static void benchmarkScenario(String name, int count, Action webAction, Action meAction) {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Scenario: " + name);
        System.out.println("--------------------------------------------------------------------------------");

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
        double avgWebMs = (totalWeb / (double)BENCHMARK_RUNS) / 1_000_000.0;
        double bestWebMs = bestWeb / 1_000_000.0;
        double webTriPerSec = (count / (bestWebMs / 1000.0));

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
        double avgMEMs = (totalME / (double)BENCHMARK_RUNS) / 1_000_000.0;
        double bestMEMs = bestME / 1_000_000.0;
        double meTriPerSec = (count / (bestMEMs / 1000.0));

        System.out.printf("  MascotCapsuleWeb : Best: %7.2f ms | Avg: %7.2f ms | Throughput: %,10.0f tris/sec%n",
            bestWebMs, avgWebMs, webTriPerSec);
        System.out.printf("  MascotME         : Best: %7.2f ms | Avg: %7.2f ms | Throughput: %,10.0f tris/sec%n",
            bestMEMs, avgMEMs, meTriPerSec);

        double ratio = bestMEMs / bestWebMs;
        if (ratio >= 1.0) {
            System.out.printf("  => MascotCapsuleWeb is %.2fx FASTER than MascotME%n%n", ratio);
        } else {
            System.out.printf("  => MascotME is %.2fx FASTER than MascotCapsuleWeb%n%n", 1.0 / ratio);
        }
    }
}
