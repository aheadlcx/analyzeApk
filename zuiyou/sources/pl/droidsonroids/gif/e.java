package pl.droidsonroids.gif;

import android.annotation.SuppressLint;
import android.content.Context;

public class e {
    @SuppressLint({"StaticFieldLeak"})
    private static Context a;

    private static Context a() {
        if (a == null) {
            try {
                a = (Context) Class.forName("android.app.ActivityThread").getDeclaredMethod("currentApplication", new Class[0]).invoke(null, new Object[0]);
            } catch (Throwable e) {
                throw new IllegalStateException("LibraryLoader not initialized. Call LibraryLoader.initialize() before using library classes.", e);
            }
        }
        return a;
    }

    static void a(Context context) {
        try {
            System.loadLibrary("pl_droidsonroids_gif");
        } catch (UnsatisfiedLinkError e) {
            if (context == null) {
                context = a();
            }
            f.a(context);
        }
    }
}
