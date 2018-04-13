package com.danikula.videocache;

import android.content.Context;
import android.os.Environment;
import com.izuiyou.a.a.b;
import java.io.File;

final class p {
    public static File a(Context context) {
        return new File(a(context, true), "video-cache");
    }

    private static File a(Context context, boolean z) {
        File file = null;
        Object externalStorageState;
        try {
            externalStorageState = Environment.getExternalStorageState();
        } catch (NullPointerException e) {
            externalStorageState = "";
        }
        if (z && "mounted".equals(r1)) {
            File file2 = new File(Environment.getExternalStorageDirectory(), context.getPackageName());
            if (!file2.exists()) {
                file2.mkdirs();
            }
            file = new File(file2, "pic/picLargeVideo");
            if (!file.exists()) {
                file.mkdirs();
            }
            if (!(file.exists() && file.canWrite())) {
                file = b(context);
            }
        }
        if (file == null) {
            file = context.getCacheDir();
        }
        if (file != null) {
            return file;
        }
        String str = "/data/data/" + context.getPackageName() + "/cache/";
        b.d("Can't define system cache directory! '" + str + "%s' will be used.");
        return new File(str);
    }

    private static File b(Context context) {
        File file = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data"), context.getPackageName()), "cache");
        if (file.exists() || file.mkdirs()) {
            return file;
        }
        b.d("Unable to create external cache directory");
        return null;
    }
}
