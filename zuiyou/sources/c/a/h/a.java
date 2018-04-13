package c.a.h;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;

public class a {
    public static String a(Context context) {
        File file = new File(b(context), "skins");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    private static String b(Context context) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            File externalCacheDir = context.getExternalCacheDir();
            if (externalCacheDir != null && (externalCacheDir.exists() || externalCacheDir.mkdirs())) {
                return externalCacheDir.getAbsolutePath();
            }
        }
        return context.getCacheDir().getAbsolutePath();
    }

    public static boolean a(String str) {
        return !TextUtils.isEmpty(str) && new File(str).exists();
    }
}
