package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;

public final class a {
    private static char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String a(Context context) {
        FileInputStream fileInputStream;
        Exception e;
        Throwable th;
        String str = null;
        File b = b(context);
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            fileInputStream = new FileInputStream(b);
            try {
                byte[] bArr = new byte[2048];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    instance.update(bArr, 0, read);
                }
                str = a(instance.digest());
                try {
                    fileInputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception e4) {
            e = e4;
            fileInputStream = null;
            try {
                e.printStackTrace();
                try {
                    fileInputStream.close();
                } catch (IOException e22) {
                    e22.printStackTrace();
                }
                return str;
            } catch (Throwable th2) {
                th = th2;
                try {
                    fileInputStream.close();
                } catch (IOException e222) {
                    e222.printStackTrace();
                }
                throw th;
            }
        } catch (Throwable th3) {
            fileInputStream = null;
            th = th3;
            fileInputStream.close();
            throw th;
        }
        return str;
    }

    private static String a(byte[] bArr) {
        int i = 0;
        char[] cArr = new char[32];
        int i2 = 0;
        while (i < 16) {
            byte b = bArr[i];
            int i3 = i2 + 1;
            cArr[i2] = a[(b >>> 4) & 15];
            i2 = i3 + 1;
            cArr[i3] = a[b & 15];
            i++;
        }
        return new String(cArr);
    }

    private static File b(Context context) {
        try {
            String str = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir;
            Log.i("MainActivity", str);
            return new File(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
