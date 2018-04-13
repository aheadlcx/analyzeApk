package com.nostra13.universalimageloader.b;

import android.content.Context;
import android.os.Environment;
import com.umeng.update.UpdateConfig;
import java.io.File;
import java.io.IOException;

public final class g {
    public static File a(Context context) {
        return a(context, true);
    }

    public static File a(Context context, boolean z) {
        File file = null;
        if (z && "mounted".equals(Environment.getExternalStorageState()) && d(context)) {
            file = c(context);
        }
        if (file == null) {
            file = context.getCacheDir();
        }
        if (file != null) {
            return file;
        }
        e.c("Can't define system cache directory! '%s' will be used.", new Object[]{"/data/data/" + context.getPackageName() + "/cache/"});
        return new File("/data/data/" + context.getPackageName() + "/cache/");
    }

    public static File b(Context context) {
        File a = a(context);
        File file = new File(a, "uil-images");
        return (file.exists() || file.mkdir()) ? file : a;
    }

    private static File c(Context context) {
        File file = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data"), context.getPackageName()), "cache");
        if (file.exists()) {
            return file;
        }
        if (file.mkdirs()) {
            try {
                new File(file, ".nomedia").createNewFile();
                return file;
            } catch (IOException e) {
                e.b("Can't create \".nomedia\" file in application external cache directory", new Object[0]);
                return file;
            }
        }
        e.c("Unable to create external cache directory", new Object[0]);
        return null;
    }

    private static boolean d(Context context) {
        return context.checkCallingOrSelfPermission(UpdateConfig.f) == 0;
    }
}
