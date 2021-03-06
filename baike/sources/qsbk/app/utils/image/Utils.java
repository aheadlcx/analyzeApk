package qsbk.app.utils.image;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import com.tencent.bugly.Bugly;
import java.io.File;

public class Utils {
    public static final int IO_BUFFER_SIZE = 8192;

    private Utils() {
    }

    public static void disableConnectionReuseIfNecessary() {
        if (hasHttpConnectionBug()) {
            System.setProperty("http.keepAlive", Bugly.SDK_IS_DEV);
        }
    }

    public static int getBitmapSize(Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    @SuppressLint({"NewApi"})
    public static boolean isExternalStorageRemovable() {
        if (VERSION.SDK_INT >= 9) {
            return Environment.isExternalStorageRemovable();
        }
        return true;
    }

    @SuppressLint({"NewApi"})
    public static File getExternalCacheDir(Context context) {
        if (hasExternalCacheDir()) {
            return context.getExternalCacheDir();
        }
        return new File(Environment.getExternalStorageDirectory().getPath() + ("/Android/data/" + context.getPackageName() + "/cache/"));
    }

    @SuppressLint({"NewApi"})
    public static long getUsableSpace(File file) {
        if (VERSION.SDK_INT >= 9) {
            return file.getUsableSpace();
        }
        StatFs statFs = new StatFs(file.getPath());
        return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
    }

    public static int getMemoryClass(Context context) {
        return ((ActivityManager) context.getSystemService("activity")).getMemoryClass();
    }

    public static boolean hasHttpConnectionBug() {
        return VERSION.SDK_INT < 8;
    }

    public static boolean hasExternalCacheDir() {
        return VERSION.SDK_INT >= 8;
    }

    public static boolean hasActionBar() {
        return VERSION.SDK_INT >= 11;
    }
}
