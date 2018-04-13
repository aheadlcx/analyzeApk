package qsbk.app.cache;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import qsbk.app.QsbkApp;
import qsbk.app.exception.CrashHandler;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.StorageUtils;
import qsbk.app.utils.image.Utils;
import qsbk.app.widget.RefreshTipView;

public class FileCache {
    private static int a = 0;

    public static void cacheTextToDisk(Context context, String str, String str2) {
        if (context != null && !TextUtils.isEmpty(str)) {
            new b("qbk-FileCache", context, str, str2).start();
        }
    }

    public static void cacheTextToDiskImmediately(Context context, String str, String str2) {
        if (context == null) {
            context = QsbkApp.mContext;
        }
        new c("qbk-FileCache", context, str, str2).start();
    }

    public static void cacheTextToDiskSync(Context context, String str, String str2) {
        File diskCacheDir = getDiskCacheDir(context, str);
        if (diskCacheDir.exists()) {
            diskCacheDir.delete();
        }
        writeFile(context, str, str2);
    }

    public static String getContentFromDisk(Context context, String str) {
        InputStream bufferedInputStream;
        Throwable e;
        Throwable th;
        String str2 = "";
        File diskCacheDir = getDiskCacheDir(context, str);
        if (diskCacheDir.exists()) {
            byte[] bArr = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(diskCacheDir));
                while (true) {
                    try {
                        int read = bufferedInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    } catch (Exception e2) {
                        e = e2;
                    }
                }
                bufferedInputStream.close();
                str2 = byteArrayOutputStream.toString("utf8");
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
            } catch (Exception e4) {
                e = e4;
                bufferedInputStream = null;
                try {
                    int i = a;
                    a = i + 1;
                    if (i < 20) {
                        CrashHandler.getInstance().reportGuessException(Thread.currentThread(), e, 2, "read file cache");
                    }
                    e.printStackTrace();
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    }
                    return str2;
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e322) {
                            e322.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedInputStream = null;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                throw th;
            }
        } else if (DebugUtil.DEBUG) {
            Log.d("---FileCache---", "未发现-" + str + "-的缓存");
        }
        return str2;
    }

    public static void writeFile(Context context, String str, String str2) {
        FileOutputStream fileOutputStream;
        Throwable e;
        int i;
        File diskCacheDir = getDiskCacheDir(context, str);
        try {
            fileOutputStream = new FileOutputStream(diskCacheDir, true);
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf8");
                outputStreamWriter.write(str2);
                outputStreamWriter.flush();
                outputStreamWriter.close();
                fileOutputStream.close();
                if (DebugUtil.DEBUG) {
                    Log.d("---FileCache---", "文件缓存成功" + str);
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    i = a;
                    a = i + 1;
                    if (i < 20) {
                        CrashHandler.getInstance().reportGuessException(Thread.currentThread(), e, 2, "write file cache");
                    }
                    Log.e("FileCache", "文件缓存出错 path:" + diskCacheDir.getPath());
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (Throwable th) {
                    e = th;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw e;
                }
            }
        } catch (Exception e5) {
            e = e5;
            fileOutputStream = null;
            i = a;
            a = i + 1;
            if (i < 20) {
                CrashHandler.getInstance().reportGuessException(Thread.currentThread(), e, 2, "write file cache");
            }
            Log.e("FileCache", "文件缓存出错 path:" + diskCacheDir.getPath());
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (Throwable th2) {
            e = th2;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw e;
        }
    }

    public static File getDiskCacheDir(Context context, String str) {
        return new File(StorageUtils.getCacheDirectory(context).getPath() + File.separator + str);
    }

    public static String getDiskCacheDirPath(Context context) {
        String str = "";
        try {
            if (!Environment.getExternalStorageState().equals("mounted") || Utils.isExternalStorageRemovable()) {
                return context.getCacheDir().getPath();
            }
            return Utils.getExternalCacheDir(context).getPath();
        } catch (Exception e) {
            return context.getCacheDir().getPath();
        }
    }

    private static boolean b(Context context, String str) {
        File diskCacheDir = getDiskCacheDir(context, str);
        if (!diskCacheDir.exists()) {
            return true;
        }
        if (Math.abs(System.currentTimeMillis() - diskCacheDir.lastModified()) <= RefreshTipView.SECOND_REFRESH_INTERVAL) {
            return false;
        }
        if (DebugUtil.DEBUG) {
            Log.d("---FileCache---", "缓存文件过老，删除:" + str);
        }
        diskCacheDir.delete();
        return true;
    }
}
