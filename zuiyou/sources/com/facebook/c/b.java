package com.facebook.c;

public final class b {
    public static final c a = new c("JPEG", "jpeg");
    public static final c b = new c("PNG", "png");
    public static final c c = new c("GIF", "gif");
    public static final c d = new c("BMP", "bmp");
    public static final c e = new c("WEBP_SIMPLE", "webp");
    public static final c f = new c("WEBP_LOSSLESS", "webp");
    public static final c g = new c("WEBP_EXTENDED", "webp");
    public static final c h = new c("WEBP_EXTENDED_WITH_ALPHA", "webp");
    public static final c i = new c("WEBP_ANIMATED", "webp");

    public static boolean a(c cVar) {
        return b(cVar) || cVar == i;
    }

    public static boolean b(c cVar) {
        return cVar == e || cVar == f || cVar == g || cVar == h;
    }
}
