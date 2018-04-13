package com.spriteapp.booklibrary.util;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.bumptech.glide.i;
import java.io.File;
import java.math.BigDecimal;

public class GlideCacheUtil {
    private static GlideCacheUtil inst;

    public static GlideCacheUtil getInstance() {
        if (inst == null) {
            inst = new GlideCacheUtil();
        }
        return inst;
    }

    public void clearImageDiskCache(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new GlideCacheUtil$1(this, context)).start();
            } else {
                i.a(context).j();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearImageMemoryCache(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                i.a(context).i();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearImageAllCache(Context context) {
        clearImageDiskCache(context);
        clearImageMemoryCache(context);
        deleteFolderFile(context.getExternalCacheDir() + "image_manager_disk_cache", true);
    }

    public String getCacheSize(Context context) {
        try {
            return getFormatSize((double) getFolderSize(new File(context.getCacheDir() + "/" + "image_manager_disk_cache")));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private long getFolderSize(File file) {
        long j;
        Exception e;
        try {
            File[] listFiles = file.listFiles();
            int length = listFiles.length;
            j = 0;
            int i = 0;
            while (i < length) {
                try {
                    File file2 = listFiles[i];
                    if (file2.isDirectory()) {
                        j += getFolderSize(file2);
                    } else {
                        j += file2.length();
                    }
                    i++;
                } catch (Exception e2) {
                    e = e2;
                }
            }
        } catch (Exception e3) {
            Exception exception = e3;
            j = 0;
            e = exception;
            e.printStackTrace();
            return j;
        }
        return j;
    }

    private void deleteFolderFile(String str, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            try {
                File file = new File(str);
                if (file.isDirectory()) {
                    for (File absolutePath : file.listFiles()) {
                        deleteFolderFile(absolutePath.getAbsolutePath(), true);
                    }
                }
                if (!z) {
                    return;
                }
                if (!file.isDirectory()) {
                    file.delete();
                } else if (file.listFiles().length == 0) {
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static String getFormatSize(double d) {
        double d2 = d / 1024.0d;
        if (d2 < 1.0d) {
            return d + "KB";
        }
        double d3 = d2 / 1024.0d;
        if (d3 < 1.0d) {
            return new BigDecimal(Double.toString(d2)).setScale(2, 4).toPlainString() + "KB";
        }
        d2 = d3 / 1024.0d;
        if (d2 < 1.0d) {
            return new BigDecimal(Double.toString(d3)).setScale(2, 4).toPlainString() + "MB";
        }
        d3 = d2 / 1024.0d;
        if (d3 < 1.0d) {
            return new BigDecimal(Double.toString(d2)).setScale(2, 4).toPlainString() + "GB";
        }
        return new BigDecimal(d3).setScale(2, 4).toPlainString() + "TB";
    }
}
