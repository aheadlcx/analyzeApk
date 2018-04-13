package com.facebook.common.e;

public class a {
    private static volatile b a = new a();

    public interface b {
        void a(String str);
    }

    public static class a implements b {
        public void a(String str) {
            System.loadLibrary(str);
        }
    }

    public static void a(String str) {
        a.a(str);
    }
}
