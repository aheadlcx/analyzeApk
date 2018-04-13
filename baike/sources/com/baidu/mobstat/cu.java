package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import qsbk.app.utils.HttpUtils;

public final class cu {
    private static final Proxy a = new Proxy(Type.HTTP, new InetSocketAddress(HttpUtils.PROXY_IP, 80));
    private static final Proxy b = new Proxy(Type.HTTP, new InetSocketAddress("10.0.0.200", 80));

    public static String a() {
        String str = null;
        try {
            str = Environment.getExternalStorageState();
        } catch (Exception e) {
        }
        return str;
    }

    public static File a(String str) {
        if (!"mounted".equals(a())) {
            return null;
        }
        File externalStorageDirectory;
        try {
            externalStorageDirectory = Environment.getExternalStorageDirectory();
        } catch (Exception e) {
            externalStorageDirectory = null;
        }
        if (externalStorageDirectory != null) {
            return new File(externalStorageDirectory, str);
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r4, java.lang.String r5, java.lang.String r6, boolean r7) {
        /*
        if (r4 != 0) goto L_0x0003;
    L_0x0002:
        return;
    L_0x0003:
        r0 = 0;
        if (r7 == 0) goto L_0x001f;
    L_0x0006:
        r1 = 32768; // 0x8000 float:4.5918E-41 double:1.61895E-319;
    L_0x0009:
        r0 = r4.openFileOutput(r5, r1);	 Catch:{ Exception -> 0x0021, all -> 0x0026 }
        r1 = new java.io.ByteArrayInputStream;	 Catch:{ Exception -> 0x0021, all -> 0x002e }
        r2 = "utf-8";
        r2 = r6.getBytes(r2);	 Catch:{ Exception -> 0x0021, all -> 0x002e }
        r1.<init>(r2);	 Catch:{ Exception -> 0x0021, all -> 0x002e }
        com.baidu.mobstat.da.a(r1, r0);	 Catch:{ Exception -> 0x0021, all -> 0x002e }
        com.baidu.mobstat.da.a(r0);
        goto L_0x0002;
    L_0x001f:
        r1 = 0;
        goto L_0x0009;
    L_0x0021:
        r1 = move-exception;
        com.baidu.mobstat.da.a(r0);
        goto L_0x0002;
    L_0x0026:
        r1 = move-exception;
        r3 = r1;
        r1 = r0;
        r0 = r3;
    L_0x002a:
        com.baidu.mobstat.da.a(r1);
        throw r0;
    L_0x002e:
        r1 = move-exception;
        r3 = r1;
        r1 = r0;
        r0 = r3;
        goto L_0x002a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.cu.a(android.content.Context, java.lang.String, java.lang.String, boolean):void");
    }

    public static void a(String str, String str2, boolean z) {
        Throwable th;
        Closeable closeable = null;
        try {
            Closeable fileOutputStream;
            File a = a(str);
            if (a != null) {
                if (!a.exists()) {
                    File parentFile = a.getParentFile();
                    if (parentFile != null) {
                        parentFile.mkdirs();
                    }
                }
                fileOutputStream = new FileOutputStream(a, z);
                try {
                    da.a(new ByteArrayInputStream(str2.getBytes("utf-8")), fileOutputStream);
                } catch (Exception e) {
                    closeable = fileOutputStream;
                    da.a(closeable);
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    closeable = fileOutputStream;
                    th = th3;
                    da.a(closeable);
                    throw th;
                }
            }
            fileOutputStream = null;
            da.a(fileOutputStream);
        } catch (Exception e2) {
            da.a(closeable);
        } catch (Throwable th4) {
            th = th4;
            da.a(closeable);
            throw th;
        }
    }

    public static String a(Context context, String str) {
        Throwable th;
        Closeable closeable = null;
        Closeable openFileInput;
        try {
            openFileInput = context.openFileInput(str);
            try {
                byte[] a = a((InputStream) openFileInput);
                if (a != null) {
                    String str2 = new String(a, "utf-8");
                    da.a(openFileInput);
                    return str2;
                }
                da.a(openFileInput);
                return "";
            } catch (Exception e) {
                closeable = openFileInput;
                da.a(closeable);
                return "";
            } catch (Throwable th2) {
                th = th2;
                da.a(openFileInput);
                throw th;
            }
        } catch (Exception e2) {
            da.a(closeable);
            return "";
        } catch (Throwable th3) {
            Throwable th4 = th3;
            openFileInput = null;
            th = th4;
            da.a(openFileInput);
            throw th;
        }
    }

    public static String b(String str) {
        Closeable fileInputStream;
        Throwable th;
        File a = a(str);
        if (a != null && a.exists()) {
            Closeable closeable = null;
            try {
                fileInputStream = new FileInputStream(a);
                try {
                    byte[] a2 = a((InputStream) fileInputStream);
                    if (a2 != null) {
                        String str2 = new String(a2, "utf-8");
                        da.a(fileInputStream);
                        return str2;
                    }
                    da.a(fileInputStream);
                } catch (Exception e) {
                    closeable = fileInputStream;
                    da.a(closeable);
                    return "";
                } catch (Throwable th2) {
                    th = th2;
                    da.a(fileInputStream);
                    throw th;
                }
            } catch (Exception e2) {
                da.a(closeable);
                return "";
            } catch (Throwable th3) {
                Throwable th4 = th3;
                fileInputStream = null;
                th = th4;
                da.a(fileInputStream);
                throw th;
            }
        }
        return "";
    }

    private static byte[] a(InputStream inputStream) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        return da.a(inputStream, byteArrayOutputStream) ? byteArrayOutputStream.toByteArray() : null;
    }

    public static boolean b(Context context, String str) {
        return context.deleteFile(str);
    }

    public static boolean c(String str) {
        File a = a(str);
        if (a == null || !a.isFile()) {
            return false;
        }
        return a.delete();
    }

    public static boolean c(Context context, String str) {
        return context.getFileStreamPath(str).exists();
    }

    public static HttpURLConnection d(Context context, String str) {
        return a(context, str, 50000, 50000);
    }

    @SuppressLint({"DefaultLocale"})
    public static HttpURLConnection a(Context context, String str, int i, int i2) {
        HttpURLConnection httpURLConnection;
        URL url = new URL(str);
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(1);
        if (networkInfo2 == null || !networkInfo2.isAvailable()) {
            if (networkInfo != null && networkInfo.isAvailable()) {
                String extraInfo = networkInfo.getExtraInfo();
                extraInfo = extraInfo != null ? extraInfo.toLowerCase() : "";
                db.a(extraInfo);
                if (extraInfo.startsWith("cmwap") || extraInfo.startsWith("uniwap") || extraInfo.startsWith("3gwap")) {
                    httpURLConnection = (HttpURLConnection) url.openConnection(a);
                } else if (extraInfo.startsWith("ctwap")) {
                    httpURLConnection = (HttpURLConnection) url.openConnection(b);
                }
            }
            httpURLConnection = null;
        } else {
            db.a("WIFI is available");
            httpURLConnection = (HttpURLConnection) url.openConnection();
        }
        if (httpURLConnection == null) {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        }
        httpURLConnection.setConnectTimeout(i);
        httpURLConnection.setReadTimeout(i2);
        return httpURLConnection;
    }

    public static boolean e(Context context, String str) {
        try {
            return context.checkCallingOrSelfPermission(str) != -1;
        } catch (Exception e) {
            db.b("Check permission failed.");
            return false;
        }
    }
}
