package com.xiaomi.channel.commonutils.android;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.file.a;
import com.xiaomi.channel.commonutils.string.d;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class c {
    public static boolean a(Context context, String str, String str2) {
        InputStream open;
        InputStream inputStream;
        Exception e;
        Throwable th;
        OutputStream outputStream = null;
        InputStream fileInputStream;
        try {
            byte[] b;
            File file;
            open = context.getAssets().open(str);
            try {
                b = a.b(open);
                file = new File(str2);
                if (file.exists()) {
                    fileInputStream = new FileInputStream(file);
                    try {
                        Object a = d.a(a.b(fileInputStream));
                        String a2 = d.a(b);
                        if (TextUtils.isEmpty(a) || !a.equals(a2)) {
                            inputStream = fileInputStream;
                        } else {
                            a.a(open);
                            a.a(fileInputStream);
                            a.a(null);
                            return false;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        inputStream = open;
                        try {
                            e.printStackTrace();
                            a.a(inputStream);
                            a.a(fileInputStream);
                            a.a(outputStream);
                            return false;
                        } catch (Throwable th2) {
                            th = th2;
                            open = inputStream;
                            a.a(open);
                            a.a(fileInputStream);
                            a.a(outputStream);
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        a.a(open);
                        a.a(fileInputStream);
                        a.a(outputStream);
                        throw th;
                    }
                }
                inputStream = null;
            } catch (Exception e3) {
                e = e3;
                fileInputStream = null;
                inputStream = open;
                e.printStackTrace();
                a.a(inputStream);
                a.a(fileInputStream);
                a.a(outputStream);
                return false;
            } catch (Throwable th4) {
                th = th4;
                fileInputStream = null;
                a.a(open);
                a.a(fileInputStream);
                a.a(outputStream);
                throw th;
            }
            try {
                OutputStream fileOutputStream = new FileOutputStream(file);
                try {
                    fileOutputStream.write(b);
                    fileOutputStream.flush();
                    a.a(open);
                    a.a(inputStream);
                    a.a(fileOutputStream);
                    return true;
                } catch (Exception e4) {
                    e = e4;
                    outputStream = fileOutputStream;
                    fileInputStream = inputStream;
                    inputStream = open;
                    e.printStackTrace();
                    a.a(inputStream);
                    a.a(fileInputStream);
                    a.a(outputStream);
                    return false;
                } catch (Throwable th5) {
                    th = th5;
                    outputStream = fileOutputStream;
                    fileInputStream = inputStream;
                    a.a(open);
                    a.a(fileInputStream);
                    a.a(outputStream);
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
                fileInputStream = inputStream;
                inputStream = open;
                e.printStackTrace();
                a.a(inputStream);
                a.a(fileInputStream);
                a.a(outputStream);
                return false;
            } catch (Throwable th6) {
                th = th6;
                fileInputStream = inputStream;
                a.a(open);
                a.a(fileInputStream);
                a.a(outputStream);
                throw th;
            }
        } catch (Exception e6) {
            e = e6;
            fileInputStream = null;
            inputStream = null;
            e.printStackTrace();
            a.a(inputStream);
            a.a(fileInputStream);
            a.a(outputStream);
            return false;
        } catch (Throwable th7) {
            th = th7;
            fileInputStream = null;
            open = null;
            a.a(open);
            a.a(fileInputStream);
            a.a(outputStream);
            throw th;
        }
    }
}
