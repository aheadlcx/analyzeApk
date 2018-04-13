package com.facebook.imagepipeline.nativecode;

public class d {
    public static boolean a;
    private static c b;

    static {
        a = false;
        try {
            b = (c) Class.forName("com.facebook.imagepipeline.nativecode.WebpTranscoderImpl").newInstance();
            a = true;
        } catch (Throwable th) {
            a = false;
        }
    }

    public static c a() {
        return b;
    }
}
