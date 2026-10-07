package com.hicorp.mascotcapsule.web;

public class WebRasterizerBridge {
    public final Config config;
    public final RasterVertex v0 = new RasterVertex();
    public final RasterVertex v1 = new RasterVertex();
    public final RasterVertex v2 = new RasterVertex();

    // Fast unclipped drawers (index 0)
    public final FlatDrawer flatFast;
    public final FlatDrawer blendFast;
    public final TexturedDrawer texFast;
    public final LitDrawer litFast;

    // Clipped drawers (index 1)
    public final FlatDrawer flatClipped;
    public final FlatDrawer blendClipped;
    public final TexturedDrawer texClipped;
    public final LitDrawer litClipped;

    public WebRasterizerBridge(int width, int height, int[] fb) {
        config = new Config();
        config.setClipRect(0, 0, width, height);
        config.setRenderTarget(width, fb);

        flatFast     = config.flatDrawers[0][0];
        flatClipped  = config.flatDrawers[0][1];
        blendFast    = config.flatDrawers[1][0];
        blendClipped = config.flatDrawers[1][1];

        texFast      = config.texturedDrawers[0][0][0];
        texClipped   = config.texturedDrawers[0][0][1];
        litFast      = config.litDrawers[0][0][0];
        litClipped   = config.litDrawers[0][0][1];
    }

    public void setTexture(Texture tex) {
        config.setDiffuseTexture(tex);
    }

    public void drawFlat(int x0, int y0, int x1, int y1, int x2, int y2, int color) {
        config.setFillColor(color);
        v0.x = x0; v0.y = y0;
        v1.x = x1; v1.y = y1;
        v2.x = x2; v2.y = y2;
        config.rasterizeFlatTriangle(flatClipped, v0, v1, v2);
    }

    public void drawFlatFast(int x0, int y0, int x1, int y1, int x2, int y2, int color) {
        config.setFillColor(color);
        v0.x = x0; v0.y = y0;
        v1.x = x1; v1.y = y1;
        v2.x = x2; v2.y = y2;
        config.rasterizeFlatTriangle(flatFast, v0, v1, v2);
    }

    public void drawTextured(int x0, int y0, int x1, int y1, int x2, int y2,
                             int u0, int v0Val, int u1, int v1Val, int u2, int v2Val) {
        v0.x = x0; v0.y = y0; v0.u = u0 << 16; v0.v = v0Val << 16;
        v1.x = x1; v1.y = y1; v1.u = u1 << 16; v1.v = v1Val << 16;
        v2.x = x2; v2.y = y2; v2.u = u2 << 16; v2.v = v2Val << 16;
        config.rasterizeTexturedTriangle(texClipped, v0, v1, v2);
    }

    public void drawTexturedFast(int x0, int y0, int x1, int y1, int x2, int y2,
                                 int u0, int v0Val, int u1, int v1Val, int u2, int v2Val) {
        v0.x = x0; v0.y = y0; v0.u = u0 << 16; v0.v = v0Val << 16;
        v1.x = x1; v1.y = y1; v1.u = u1 << 16; v1.v = v1Val << 16;
        v2.x = x2; v2.y = y2; v2.u = u2 << 16; v2.v = v2Val << 16;
        config.rasterizeTexturedTriangle(texFast, v0, v1, v2);
    }

    public void drawLit(int x0, int y0, int x1, int y1, int x2, int y2,
                        int u0, int v0Val, int u1, int v1Val, int u2, int v2Val,
                        int l0, int l1, int l2) {
        v0.x = x0; v0.y = y0; v0.u = u0 << 16; v0.v = v0Val << 16; v0.light = l0 << 16;
        v1.x = x1; v1.y = y1; v1.u = u1 << 16; v1.v = v1Val << 16; v1.light = l1 << 16;
        v2.x = x2; v2.y = y2; v2.u = u2 << 16; v2.v = v2Val << 16; v2.light = l2 << 16;
        config.rasterizeLitTriangle(litClipped, v0, v1, v2);
    }

    public void drawLitFast(int x0, int y0, int x1, int y1, int x2, int y2,
                            int u0, int v0Val, int u1, int v1Val, int u2, int v2Val,
                            int l0, int l1, int l2) {
        v0.x = x0; v0.y = y0; v0.u = u0 << 16; v0.v = v0Val << 16; v0.light = l0 << 16;
        v1.x = x1; v1.y = y1; v1.u = u1 << 16; v1.v = v1Val << 16; v1.light = l1 << 16;
        v2.x = x2; v2.y = y2; v2.u = u2 << 16; v2.v = v2Val << 16; v2.light = l2 << 16;
        config.rasterizeLitTriangle(litFast, v0, v1, v2);
    }

    public void drawBlend(int x0, int y0, int x1, int y1, int x2, int y2, int color) {
        config.setFillColor(color);
        v0.x = x0; v0.y = y0;
        v1.x = x1; v1.y = y1;
        v2.x = x2; v2.y = y2;
        config.rasterizeFlatTriangle(blendClipped, v0, v1, v2);
    }

    public void drawBlendFast(int x0, int y0, int x1, int y1, int x2, int y2, int color) {
        config.setFillColor(color);
        v0.x = x0; v0.y = y0;
        v1.x = x1; v1.y = y1;
        v2.x = x2; v2.y = y2;
        config.rasterizeFlatTriangle(blendFast, v0, v1, v2);
    }
}
