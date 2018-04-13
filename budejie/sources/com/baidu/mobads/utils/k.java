package com.baidu.mobads.utils;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Environment;
import android.text.TextUtils;
import com.baidu.mobads.interfaces.utils.IXAdIOUtils;
import com.umeng.update.UpdateConfig;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class k implements IXAdIOUtils {
    public static String a(Context context) {
        if (!m.a(context)) {
            return a(context, IXAdIOUtils.DEFAULT_SD_CARD_PATH);
        }
        if (m.c(context)) {
            return a(context, IXAdIOUtils.DEFAULT_SD_CARD_PATH);
        }
        return a(context.getExternalFilesDir(null).getPath());
    }

    @Deprecated
    public String getStoreagePath(Context context, String str, String str2) {
        try {
            return getExternalFilesDir(context).getPath() + str2;
        } catch (Exception e) {
            return str + str2;
        }
    }

    public String getStoreagePath(Context context) {
        return a(context, IXAdIOUtils.DEFAULT_SD_CARD_PATH);
    }

    public static String a(Context context, String str) {
        try {
            return a(b(context).getPath());
        } catch (Exception e) {
            return a(str);
        }
    }

    public static String a(String str) {
        return str + IXAdIOUtils.DEFAULT_CACHE_PATH;
    }

    public File getExternalFilesDir(Context context) {
        return b(context);
    }

    public static File b(Context context) {
        try {
            if (!"mounted".equals(Environment.getExternalStorageState())) {
                return context.getFilesDir();
            }
            if (d.b(context, UpdateConfig.f)) {
                return a();
            }
            if (VERSION.SDK_INT >= 19) {
                return context.getExternalFilesDir(null);
            }
            return context.getFilesDir();
        } catch (Exception e) {
            XAdSDKFoundationFacade.getInstance().getAdLogger().e("XAdIOUtils", e.getMessage());
            return null;
        }
    }

    private static File a() {
        return Environment.getExternalStorageDirectory();
    }

    public File deleteFileRecursive(File file) {
        File file2 = null;
        try {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                for (File deleteFileRecursive : listFiles) {
                    File deleteFileRecursive2 = deleteFileRecursive(deleteFileRecursive2);
                    if (deleteFileRecursive2 != null) {
                        return deleteFileRecursive2;
                    }
                }
            }
            if (file.delete()) {
                return null;
            }
            return file;
        } catch (Exception e) {
            if (!file.delete()) {
                file2 = file;
            }
            return file2;
        }
    }

    public File deleteFileRecursive(String str) {
        return deleteFileRecursive(new File(str));
    }

    public boolean renameFile(String str, String str2) {
        boolean z = false;
        try {
            File file = new File(str);
            File file2 = new File(str2);
            if (file != null && file.exists()) {
                z = file.renameTo(file2);
            }
        } catch (Exception e) {
        }
        return z;
    }

    public void copyFileInputStream(InputStream inputStream, String str) {
        Throwable th;
        OutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(str);
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                inputStream.close();
                fileOutputStream.close();
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Throwable th2) {
                th = th2;
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            if (inputStream != null) {
                inputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    public void copyFileFromAssetsTo(Context context, String str, String str2) {
        try {
            XAdSDKFoundationFacade.getInstance().getIoUtils().copyFileInputStream(context.getAssets().open(str), str2);
        } catch (Throwable e) {
            XAdSDKFoundationFacade.getInstance().getAdLogger().d(e);
        }
    }

    public static boolean a(File file) {
        if (file == null) {
            return false;
        }
        try {
            return file.exists() && file.canRead() && file.length() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean b(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            File file = new File(str);
            if (file == null || !file.exists() || !file.canRead() || file.length() <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
