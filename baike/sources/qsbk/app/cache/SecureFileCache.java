package qsbk.app.cache;

import android.content.Context;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import qsbk.app.exception.CrashHandler;
import qsbk.app.utils.FileUtils;

public class SecureFileCache {
    private static final String a = SecureFileCache.class.getSimpleName();
    private static final a b = new a();
    private static int c = 0;
    private static ExecutorService d = Executors.newSingleThreadExecutor();
    private static SecureFileCache e;
    private File f;
    private File g;

    public interface Callback {
        void onFinished(File file, String str);
    }

    private static final class a implements Comparator<File> {
        private a() {
        }

        public int compare(File file, File file2) {
            long lastModified = file.lastModified() - file2.lastModified();
            if (lastModified == 0) {
                return 0;
            }
            return lastModified > 0 ? 1 : -1;
        }
    }

    private SecureFileCache(Context context) {
        this.f = new File(context.getCacheDir(), "user");
        this.g = new File(context.getCacheDir(), "article");
    }

    public static synchronized SecureFileCache getInstance(Context context) {
        SecureFileCache secureFileCache;
        synchronized (SecureFileCache.class) {
            if (e == null) {
                e = new SecureFileCache(context);
            }
            secureFileCache = e;
        }
        return secureFileCache;
    }

    private static String b(File file) {
        int read;
        Throwable e;
        Throwable th;
        String str = "";
        if (file.exists()) {
            byte[] bArr = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            InputStream bufferedInputStream;
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                while (true) {
                    try {
                        read = bufferedInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    } catch (Exception e2) {
                        e = e2;
                    }
                }
                bufferedInputStream.close();
                str = byteArrayOutputStream.toString("utf8");
                byteArrayOutputStream.close();
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
                    read = c;
                    c = read + 1;
                    if (read < 20) {
                        CrashHandler.getInstance().reportGuessException(Thread.currentThread(), e, 2, "read file cache");
                    }
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    }
                    return str;
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
        }
        return str;
    }

    private static void c(File file) {
        File[] listFiles = file.listFiles();
        long fileSize = FileUtils.getFileSize(file, null);
        if (fileSize > PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
            Arrays.sort(listFiles, b);
            int length = listFiles.length - 1;
            while (fileSize > PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
                File file2 = listFiles[length];
                length--;
                fileSize -= file2.length();
                file2.delete();
            }
        }
    }

    private static void d(File file) {
        if (file != null && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File delete : listFiles) {
                    delete.delete();
                }
            }
        }
    }

    public boolean makeSureDirectoryExist(File file) {
        if (file.exists()) {
            return true;
        }
        return file.mkdirs();
    }

    public void cacheUserToDisk(String str, String str2) {
        d.submit(new d(this, str2, str));
    }

    public void cacheArticlesToDisk(String str, String str2) {
        d.submit(new e(this, str2, str));
    }

    private File a(String str) {
        makeSureDirectoryExist(this.f);
        return new File(this.f, str);
    }

    private File b(String str) {
        makeSureDirectoryExist(this.g);
        return new File(this.g, str);
    }

    private String c(String str) {
        int length = str.length() / 2;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.valueOf(str.substring(0, length).hashCode()));
        stringBuffer.append(String.valueOf(str.substring(length).hashCode()));
        return stringBuffer.toString();
    }

    public void getUserString(String str, Callback callback) {
        if (callback == null) {
            Log.e(a, " Use getUserString(String) instead .");
        } else {
            d.submit(new f(this, str, callback));
        }
    }

    public String getUserString(String str) {
        return b(a(c(str)));
    }

    public void getArticlesString(String str, Callback callback) {
        if (callback == null) {
            Log.e(a, " Use getArticleString(String) instead .");
        } else {
            d.submit(new g(this, str, callback));
        }
    }

    public String getArticlesString(String str) {
        return b(b(c(str)));
    }

    public void clearUser() {
        d(this.f);
    }

    public boolean clearUser(String str) {
        File b = b(c(str));
        if (b.exists()) {
            return b.delete();
        }
        return false;
    }

    public void clearArticle() {
        d(this.g);
    }

    public void clearOlderUserIfNeed() {
        c(this.f);
    }

    public void clearOlderArticleIfNeed() {
        c(this.g);
    }
}
