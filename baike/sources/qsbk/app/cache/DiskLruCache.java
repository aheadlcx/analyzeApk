package qsbk.app.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import qsbk.app.QsbkApp;
import qsbk.app.api.BigCoverHelper;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.image.Utils;

public class DiskLruCache {
    private static final FilenameFilter c = new a();
    protected int a;
    protected int b;
    private final File d;
    private final int e = 64;
    private final Map<String, String> f = Collections.synchronizedMap(new LinkedHashMap(32, 0.75f, true));
    private int g = 0;
    private int h = 0;
    private long i = 5242880;
    private CompressFormat j = CompressFormat.JPEG;
    private int k = 100;

    private DiskLruCache(File file, long j) {
        this.d = file;
        this.i = j;
    }

    public static DiskLruCache openCache(Context context, File file, long j, int i) {
        if (!file.exists()) {
            file.mkdirs();
        }
        if (file.isDirectory() && file.canWrite() && Utils.getUsableSpace(file) > j) {
            DiskLruCache diskLruCache = new DiskLruCache(file, j);
            diskLruCache.setImageSize(i);
            return diskLruCache;
        }
        LogUtil.d("cacheDir.isDirectory():" + file.isDirectory());
        LogUtil.d("cacheDir.canWrite():" + file.canWrite());
        LogUtil.d("Utils.getUsableSpace(cacheDir):" + Utils.getUsableSpace(file));
        LogUtil.d("Utils.getUsableSpace(cacheDir):" + j);
        return null;
    }

    public static int calculateInSampleSize(Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            if (i4 > i3) {
                i5 = Math.round(((float) i4) / ((float) i));
            } else {
                i5 = Math.round(((float) i3) / ((float) i2));
            }
            float f = (float) (i3 * i4);
            while (f / ((float) (i5 * i5)) > ((float) ((i * i2) * 6))) {
                i5++;
            }
        }
        return i5;
    }

    public static void clearCache(Context context, String str) {
        a(getDiskCacheDir(context, str));
    }

    private static void a(File file) {
        File[] listFiles = file.listFiles(c);
        for (File delete : listFiles) {
            delete.delete();
        }
    }

    public static File getDiskCacheDir(Context context, String str) {
        String str2 = "";
        try {
            if (!Environment.getExternalStorageState().equals("mounted") || Utils.isExternalStorageRemovable()) {
                str2 = context.getCacheDir().getPath();
                return new File(str2 + File.separator + str);
            }
            str2 = Utils.getExternalCacheDir(context).getPath();
            return new File(str2 + File.separator + str);
        } catch (Exception e) {
            Exception exception = e;
            str2 = context.getCacheDir().getPath();
            exception.printStackTrace();
        }
    }

    public static String createFilePath(File file, String str) {
        try {
            return file.getAbsolutePath() + File.separator + "cache_" + URLEncoder.encode(str.replace("*", ""), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e("DiskLruCache", "createFilePath - " + e);
            return null;
        }
    }

    public void put(String str, Bitmap bitmap) {
        synchronized (this.f) {
            if (this.f.get(str) == null) {
                try {
                    String createFilePath = createFilePath(this.d, str);
                    if (a(bitmap, createFilePath)) {
                        a(str, createFilePath);
                        a();
                    }
                } catch (FileNotFoundException e) {
                    Log.e("DiskLruCache", "Error in put: " + e.getMessage());
                } catch (IOException e2) {
                    Log.e("DiskLruCache", "Error in put: " + e2.getMessage());
                }
            }
        }
    }

    public File getCacheDirectory() {
        return this.d;
    }

    private void a(String str, String str2) {
        this.f.put(str, str2);
        this.g = this.f.size();
        this.h = (int) (((long) this.h) + new File(str2).length());
    }

    private void a() {
        int i = 0;
        while (i < 4) {
            if (this.g > 64 || ((long) this.h) > this.i) {
                Entry entry = (Entry) this.f.entrySet().iterator().next();
                File file = new File((String) entry.getValue());
                long length = file.length();
                this.f.remove(entry.getKey());
                file.delete();
                this.g = this.f.size();
                this.h = (int) (((long) this.h) - length);
                int i2 = i + 1;
                if (DebugUtil.DEBUG) {
                    Log.d("DiskLruCache", "flushCache - Removed cache file, " + file + ", " + length);
                    i = i2;
                } else {
                    i = i2;
                }
            } else {
                return;
            }
        }
    }

    @Deprecated
    public Bitmap get(String str) {
        Bitmap decodeFile;
        synchronized (this.f) {
            String str2 = (String) this.f.get(str);
            if (str2 == null) {
                str2 = createFilePath(this.d, str);
                if (new File(str2).exists()) {
                    if (DebugUtil.DEBUG) {
                        Log.d("DiskLruCache", "Disk cache hit (existing file) " + str);
                    }
                    a(str, str2);
                }
            }
            if (str2 != null) {
                if (DebugUtil.DEBUG) {
                    Log.d("DiskLruCache", "Disk cache hit (existing file or memory) " + str);
                }
                if (new File(str2).exists()) {
                    Options options = new Options();
                    options.inJustDecodeBounds = true;
                    options.inPreferredConfig = Config.RGB_565;
                    BitmapFactory.decodeFile(str2, options);
                    options.inSampleSize = calculateInSampleSize(options, this.a, this.b);
                    options.inJustDecodeBounds = false;
                    if (!Build.FINGERPRINT.toLowerCase().contains("huawei")) {
                        options.inDensity = BigCoverHelper.REQCODE_CAREMA;
                    }
                    options.inTargetDensity = QsbkApp.mContext.getResources().getDisplayMetrics().densityDpi;
                    options.inScaled = true;
                    try {
                        decodeFile = BitmapFactory.decodeFile(str2, options);
                    } catch (OutOfMemoryError e) {
                        decodeFile = null;
                        System.gc();
                    }
                }
            }
            decodeFile = null;
        }
        return decodeFile;
    }

    public boolean containsKey(String str) {
        if (this.f.containsKey(str)) {
            return true;
        }
        String createFilePath = createFilePath(this.d, str);
        if (!new File(createFilePath).exists()) {
            return false;
        }
        a(str, createFilePath);
        return true;
    }

    public void clearCache() {
        a(this.d);
    }

    public String createFilePath(String str) {
        return createFilePath(this.d, str);
    }

    public void setCompressParams(CompressFormat compressFormat, int i) {
        this.j = compressFormat;
        this.k = i;
    }

    private boolean a(Bitmap bitmap, String str) throws IOException, FileNotFoundException {
        Throwable th;
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(str);
            try {
                boolean z;
                if (bitmap.compress(this.j, this.k, fileOutputStream)) {
                    fileOutputStream.flush();
                    z = true;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } else {
                    z = false;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                }
                return z;
            } catch (Throwable th2) {
                th = th2;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    public void setImageSize(int i) {
        this.a = i;
        this.b = i;
    }
}
