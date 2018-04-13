package com.ak.android.bridge;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class e {
    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (file.exists()) {
            return true;
        }
        return file.mkdirs();
    }

    public static boolean a(File file) {
        int i = 1;
        if (file == null) {
            return true;
        }
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                int i2 = 0;
                while (i2 < listFiles.length) {
                    int a = a(listFiles[i2]) & i;
                    i2++;
                    i = a;
                }
            }
        }
        return i & file.delete();
    }

    public static int a(Context context, String str, File file) throws IOException {
        BufferedOutputStream bufferedOutputStream;
        Throwable th;
        BufferedOutputStream bufferedOutputStream2;
        FileOutputStream fileOutputStream = null;
        InputStream open;
        try {
            file.createNewFile();
            open = context.getAssets().open(str);
            try {
                int available = open.available();
                OutputStream fileOutputStream2 = new FileOutputStream(file, false);
                try {
                    bufferedOutputStream = new BufferedOutputStream(fileOutputStream2);
                } catch (Throwable th2) {
                    th = th2;
                    OutputStream outputStream = fileOutputStream2;
                    bufferedOutputStream2 = null;
                    OutputStream outputStream2 = outputStream;
                    if (bufferedOutputStream2 != null) {
                        bufferedOutputStream2.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    }
                    if (open != null) {
                        open.close();
                    }
                    throw th;
                }
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = open.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        bufferedOutputStream.write(bArr, 0, read);
                    }
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                    fileOutputStream2.flush();
                    fileOutputStream2.close();
                    if (open != null) {
                        open.close();
                    }
                    return available;
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = fileOutputStream2;
                    bufferedOutputStream2 = bufferedOutputStream;
                    if (bufferedOutputStream2 != null) {
                        bufferedOutputStream2.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    }
                    if (open != null) {
                        open.close();
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                bufferedOutputStream2 = null;
                if (bufferedOutputStream2 != null) {
                    bufferedOutputStream2.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
                if (open != null) {
                    open.close();
                }
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            open = null;
            bufferedOutputStream2 = null;
            if (bufferedOutputStream2 != null) {
                bufferedOutputStream2.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
            if (open != null) {
                open.close();
            }
            throw th;
        }
    }

    public static void a(String str, Throwable th) {
        if (b.m) {
            Log.d(b.l, str, th);
        }
    }

    public static void b(String str) {
        if (b.m) {
            Log.d(b.l, str);
        }
    }

    public static void c(String str) {
        if (b.m) {
            Log.d(b.l, str);
        }
    }
}
