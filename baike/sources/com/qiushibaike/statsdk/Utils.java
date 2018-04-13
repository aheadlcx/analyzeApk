package com.qiushibaike.statsdk;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.util.Locale;
import java.util.zip.GZIPOutputStream;
import qsbk.app.utils.HttpUtils;

public final class Utils {
    private static final Proxy a = new Proxy(Type.HTTP, new InetSocketAddress(HttpUtils.PROXY_IP, 80));
    private static final Proxy b = new Proxy(Type.HTTP, new InetSocketAddress("10.0.0.200", 80));
    private static final String c = Utils.class.getSimpleName();

    public static HttpURLConnection getHttpURLConnection(Context context, String str, int i, int i2) throws IOException {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(1);
        try {
            HttpURLConnection httpURLConnection;
            URL url = new URL(str);
            if (networkInfo2 != null && networkInfo2.isAvailable()) {
                L.d("", "WIFI is available");
                httpURLConnection = (HttpURLConnection) url.openConnection();
            } else if (networkInfo == null || !networkInfo.isAvailable()) {
                L.d("", "getConnection:not wifi and mobile");
                httpURLConnection = (HttpURLConnection) url.openConnection();
            } else {
                String extraInfo = networkInfo.getExtraInfo();
                if (extraInfo != null) {
                    extraInfo = extraInfo.toLowerCase(Locale.ENGLISH);
                } else {
                    extraInfo = "";
                }
                L.d("current APN", extraInfo);
                if (extraInfo.startsWith("cmwap") || extraInfo.startsWith("uniwap") || extraInfo.startsWith("3gwap")) {
                    httpURLConnection = (HttpURLConnection) url.openConnection(a);
                } else if (extraInfo.startsWith("ctwap")) {
                    httpURLConnection = (HttpURLConnection) url.openConnection(b);
                } else {
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                }
            }
            httpURLConnection.setConnectTimeout(i);
            httpURLConnection.setReadTimeout(i2);
            return httpURLConnection;
        } catch (Throwable e) {
            L.d(e);
            throw new IOException(e);
        }
    }

    public static String post(Context context, String str, String str2, int i, int i2) throws IOException {
        BufferedWriter bufferedWriter;
        IOException e;
        BufferedReader bufferedReader;
        Throwable e2;
        BufferedReader bufferedReader2 = null;
        HttpURLConnection httpURLConnection = getHttpURLConnection(context, str, i, i2);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setRequestProperty("Content-Encoding", "gzip");
        StringBuilder stringBuilder = new StringBuilder();
        try {
            httpURLConnection.connect();
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new GZIPOutputStream(httpURLConnection.getOutputStream())));
            try {
                bufferedWriter.write(str2);
                bufferedWriter.flush();
                bufferedWriter.close();
                if (httpURLConnection.getResponseCode() != 200) {
                    throw new IOException("http code =" + httpURLConnection.getResponseCode() + "& contentResponse=" + stringBuilder);
                }
                BufferedWriter bufferedWriter2 = null;
                BufferedReader bufferedReader3 = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                while (true) {
                    try {
                        String readLine = bufferedReader3.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuilder.append(readLine);
                    } catch (IOException e3) {
                        e = e3;
                        bufferedReader = bufferedReader3;
                        bufferedWriter = null;
                        bufferedReader2 = bufferedReader;
                    } catch (Exception e4) {
                        e2 = e4;
                        bufferedReader = bufferedReader3;
                        bufferedWriter = null;
                        bufferedReader2 = bufferedReader;
                    } catch (Throwable th) {
                        e2 = th;
                        bufferedReader = bufferedReader3;
                        bufferedWriter = null;
                        bufferedReader2 = bufferedReader;
                    }
                }
                bufferedReader3.close();
                bufferedReader3 = null;
                httpURLConnection.disconnect();
                if (null != null) {
                    try {
                        bufferedReader3.close();
                    } catch (Exception e5) {
                    }
                }
                if (null != null) {
                    bufferedWriter2.close();
                }
                httpURLConnection.disconnect();
                return stringBuilder.toString();
            } catch (IOException e6) {
                e = e6;
                try {
                    throw e;
                } catch (Throwable th2) {
                    e2 = th2;
                }
            } catch (Exception e7) {
                e2 = e7;
                L.d(e2);
                throw new IOException(e2);
            }
        } catch (IOException e8) {
            e = e8;
            bufferedWriter = null;
            throw e;
        } catch (Exception e9) {
            e2 = e9;
            bufferedWriter = null;
            L.d(e2);
            throw new IOException(e2);
        } catch (Throwable th3) {
            e2 = th3;
            bufferedWriter = null;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (Exception e10) {
                    httpURLConnection.disconnect();
                    throw e2;
                }
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            httpURLConnection.disconnect();
            throw e2;
        }
    }

    public static String getInternalFile(Context context, String str) {
        byte[] a = a(context, str);
        String str2 = "";
        if (a != null) {
            try {
                return new String(a, "utf-8");
            } catch (Throwable e) {
                L.d(c, e);
            }
        }
        return str2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] a(android.content.Context r5, java.lang.String r6) {
        /*
        r2 = 0;
        r1 = r5.openFileInput(r6);	 Catch:{ FileNotFoundException -> 0x001e, IOException -> 0x0033 }
        if (r1 == 0) goto L_0x0071;
    L_0x0007:
        r0 = r1.available();	 Catch:{ FileNotFoundException -> 0x0065, IOException -> 0x0059, all -> 0x0056 }
        r2 = new byte[r0];	 Catch:{ FileNotFoundException -> 0x0065, IOException -> 0x0059, all -> 0x0056 }
        r1.read(r2);	 Catch:{ FileNotFoundException -> 0x006b, IOException -> 0x005f, all -> 0x0056 }
        r0 = r2;
    L_0x0011:
        if (r1 == 0) goto L_0x0016;
    L_0x0013:
        r1.close();	 Catch:{ IOException -> 0x0017 }
    L_0x0016:
        return r0;
    L_0x0017:
        r1 = move-exception;
        r2 = c;
        com.qiushibaike.statsdk.L.d(r2, r1);
        goto L_0x0016;
    L_0x001e:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
    L_0x0021:
        r3 = c;	 Catch:{ all -> 0x0048 }
        com.qiushibaike.statsdk.L.d(r3, r1);	 Catch:{ all -> 0x0048 }
        if (r2 == 0) goto L_0x0016;
    L_0x0028:
        r2.close();	 Catch:{ IOException -> 0x002c }
        goto L_0x0016;
    L_0x002c:
        r1 = move-exception;
        r2 = c;
        com.qiushibaike.statsdk.L.d(r2, r1);
        goto L_0x0016;
    L_0x0033:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
    L_0x0036:
        r3 = c;	 Catch:{ all -> 0x0048 }
        com.qiushibaike.statsdk.L.d(r3, r1);	 Catch:{ all -> 0x0048 }
        if (r2 == 0) goto L_0x0016;
    L_0x003d:
        r2.close();	 Catch:{ IOException -> 0x0041 }
        goto L_0x0016;
    L_0x0041:
        r1 = move-exception;
        r2 = c;
        com.qiushibaike.statsdk.L.d(r2, r1);
        goto L_0x0016;
    L_0x0048:
        r0 = move-exception;
    L_0x0049:
        if (r2 == 0) goto L_0x004e;
    L_0x004b:
        r2.close();	 Catch:{ IOException -> 0x004f }
    L_0x004e:
        throw r0;
    L_0x004f:
        r1 = move-exception;
        r2 = c;
        com.qiushibaike.statsdk.L.d(r2, r1);
        goto L_0x004e;
    L_0x0056:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0049;
    L_0x0059:
        r0 = move-exception;
        r4 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r4;
        goto L_0x0036;
    L_0x005f:
        r0 = move-exception;
        r4 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r4;
        goto L_0x0036;
    L_0x0065:
        r0 = move-exception;
        r4 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r4;
        goto L_0x0021;
    L_0x006b:
        r0 = move-exception;
        r4 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r4;
        goto L_0x0021;
    L_0x0071:
        r0 = r2;
        goto L_0x0011;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qiushibaike.statsdk.Utils.a(android.content.Context, java.lang.String):byte[]");
    }

    public static String getExternalFile(String str) {
        Throwable e;
        String externalStorageState = Environment.getExternalStorageState();
        if (!"mounted".equals(externalStorageState) && !"mounted_ro".equals(externalStorageState)) {
            return "";
        }
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + str);
        String str2 = "";
        if (!file.exists()) {
            return str2;
        }
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                byte[] bArr = new byte[fileInputStream.available()];
                fileInputStream.read(bArr);
                externalStorageState = new String(bArr, "utf-8");
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable e2) {
                        L.d(c, e2);
                    }
                }
            } catch (FileNotFoundException e3) {
                e = e3;
                try {
                    L.d(c, e);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable e4) {
                            L.d(c, e4);
                            externalStorageState = str2;
                        }
                    }
                    externalStorageState = str2;
                    return externalStorageState;
                } catch (Throwable th) {
                    e4 = th;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable e22) {
                            L.d(c, e22);
                        }
                    }
                    throw e4;
                }
            } catch (IOException e5) {
                e4 = e5;
                L.d(c, e4);
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable e42) {
                        L.d(c, e42);
                        externalStorageState = str2;
                    }
                }
                externalStorageState = str2;
                return externalStorageState;
            }
        } catch (FileNotFoundException e6) {
            e42 = e6;
            fileInputStream = null;
            L.d(c, e42);
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            externalStorageState = str2;
            return externalStorageState;
        } catch (IOException e7) {
            e42 = e7;
            fileInputStream = null;
            L.d(c, e42);
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            externalStorageState = str2;
            return externalStorageState;
        } catch (Throwable th2) {
            e42 = th2;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw e42;
        }
        return externalStorageState;
    }

    public static void writeFileInternal(Context context, String str, String str2, boolean z) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput(str, z ? 32768 : 0);
            if (fileOutputStream != null) {
                fileOutputStream.write(str2.getBytes("utf-8"));
            } else {
                L.d(c, "WriteFileInternal fout is null");
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                }
            }
        } catch (Throwable e2) {
            L.d(c, "WriteFileInternal", e2);
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e3) {
                }
            }
        } catch (Throwable th) {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e4) {
                }
            }
        }
    }

    public static void writeFileExternal(String str, String str2, boolean z) {
        Throwable e;
        if ("mounted".equals(Environment.getExternalStorageState())) {
            FileOutputStream fileOutputStream = null;
            try {
                File file = new File(Environment.getExternalStorageDirectory() + File.separator + str);
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(file, z);
                try {
                    fileOutputStream2.write(str2.getBytes("utf-8"));
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (Throwable e2) {
                            L.d(c, "WriteFileExt", e2);
                        }
                    }
                } catch (FileNotFoundException e3) {
                    e2 = e3;
                    fileOutputStream = fileOutputStream2;
                    try {
                        L.d(c, "WriteFileExt", e2);
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Throwable e22) {
                                L.d(c, "WriteFileExt", e22);
                            }
                        }
                    } catch (Throwable th) {
                        e22 = th;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Throwable e4) {
                                L.d(c, "WriteFileExt", e4);
                            }
                        }
                        throw e22;
                    }
                } catch (IOException e5) {
                    e22 = e5;
                    fileOutputStream = fileOutputStream2;
                    L.d(c, "WriteFileExt", e22);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable e222) {
                            L.d(c, "WriteFileExt", e222);
                        }
                    }
                } catch (Throwable th2) {
                    e222 = th2;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw e222;
                }
            } catch (FileNotFoundException e6) {
                e222 = e6;
                L.d(c, "WriteFileExt", e222);
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e7) {
                e222 = e7;
                L.d(c, "WriteFileExt", e222);
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            }
        }
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}
