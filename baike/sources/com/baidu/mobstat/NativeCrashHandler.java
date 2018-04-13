package com.baidu.mobstat;

import android.content.Context;
import android.util.Log;
import java.io.File;

public final class NativeCrashHandler {
    private static boolean a;
    private static Context b;

    private static native void nativeException();

    private static native void nativeInit(String str);

    private static native void nativeProcess(String str);

    private static native void nativeUnint();

    static {
        a = false;
        try {
            System.loadLibrary("crash_analysis");
            a = true;
        } catch (Throwable th) {
            Log.w("NativeCrashHandler", "Load library failed.");
        }
    }

    private NativeCrashHandler() {
    }

    public static void doNativeCrash() {
        if (a) {
            try {
                nativeException();
            } catch (Throwable th) {
                Log.w("NativeCrashHandler", "Invoke method nativeException failed.");
            }
        }
    }

    public static void init(Context context) {
        if (context != null) {
            b = context.getApplicationContext();
            if (a) {
                File cacheDir = context.getCacheDir();
                if (cacheDir.exists() && cacheDir.isDirectory()) {
                    try {
                        nativeInit(cacheDir.getAbsolutePath());
                    } catch (Throwable th) {
                        Log.w("NativeCrashHandler", "Invoke method nativeInit failed.");
                    }
                }
            }
        }
    }

    public static void uninit() {
        if (a) {
            try {
                nativeUnint();
            } catch (Throwable th) {
                Log.w("NativeCrashHandler", "Invoke method nativeUnint failed.");
            }
        }
    }

    public static void process(String str) {
        if (str != null && str.length() != 0 && a) {
            File file = new File(str);
            if (file.exists() && file.isFile()) {
                try {
                    nativeProcess(str);
                } catch (Throwable th) {
                    Log.w("NativeCrashHandler", "Invoke method nativeProcess failed.");
                }
            }
        }
    }

    public static void onCrashCallbackFromNative(String str) {
        Log.w("NativeCrashHandler", "crash: " + str);
        bl.a().a(System.currentTimeMillis(), str, "NativeException", 1);
    }
}
