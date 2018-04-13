package com.facebook.common.soloader;

public class SoLoaderShim {
    private static volatile Handler sHandler = new DefaultHandler();

    public interface Handler {
        void loadLibrary(String str);
    }

    public static class DefaultHandler implements Handler {
        public void loadLibrary(String str) {
            System.loadLibrary(str);
        }
    }

    public static void setHandler(Handler handler) {
        if (handler == null) {
            throw new NullPointerException("Handler cannot be null");
        }
        sHandler = handler;
    }

    public static void loadLibrary(String str) {
        sHandler.loadLibrary(str);
    }

    public static void setInTestMode() {
        setHandler(new Handler() {
            public void loadLibrary(String str) {
            }
        });
    }
}
