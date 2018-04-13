package com.meizu.cloud.a;

import android.content.Context;
import android.os.Environment;
import com.meizu.cloud.pushsdk.base.Logger;

public class a {
    public static boolean a = false;

    public static void a(Context context) {
        Logger.get().init(context);
        Logger.get().setFilePath(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/pushSdk/" + context.getPackageName());
    }

    public static void a(boolean z) {
        Logger.get().setDebugMode(z);
    }

    public static void a() {
        Logger.get().flush(false);
    }

    public static void a(String str, String str2) {
        Logger.get().i(str, str2);
    }

    public static void b(String str, String str2) {
        Logger.get().d(str, str2);
    }

    public static void c(String str, String str2) {
        Logger.get().w(str, str2);
    }

    public static void d(String str, String str2) {
        Logger.get().e(str, str2);
    }
}
