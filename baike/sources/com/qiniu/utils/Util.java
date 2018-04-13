package com.qiniu.utils;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.util.Base64;
import com.qiniu.conf.Conf;
import com.qiniu.rs.CallRet;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.conn.HttpHostConnectException;
import cz.msebera.android.httpclient.util.EntityUtils;
import java.io.File;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.PortUnreachableException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.util.Random;
import org.apache.http.conn.ConnectTimeoutException;

public class Util {
    private static String a;
    private static String b = a();

    public static byte[] toByte(String str) {
        try {
            return str.getBytes("utf-8");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static String urlsafeBase64(String str) {
        return Base64.encodeToString(toByte(str), 10);
    }

    public static String urlsafeBase64(byte[] bArr) {
        return Base64.encodeToString(bArr, 10);
    }

    public static CallRet handleResult(HttpResponse httpResponse) {
        String str;
        Exception exception;
        String str2 = null;
        try {
            StatusLine statusLine = httpResponse.getStatusLine();
            Header firstHeader = httpResponse.getFirstHeader("X-Reqid");
            if (firstHeader != null) {
                str2 = firstHeader.getValue();
            }
            try {
                return new CallRet(statusLine.getStatusCode(), str2, EntityUtils.toString(httpResponse.getEntity(), "utf-8"));
            } catch (Exception e) {
                str = str2;
                exception = e;
            }
        } catch (Exception e2) {
            str = null;
            exception = e2;
            return new CallRet(0, str, exception);
        }
    }

    public static HttpPost newPost(String str) {
        HttpPost httpPost = new HttpPost(str);
        httpPost.setHeader("User-Agent", getUserAgent());
        return httpPost;
    }

    public static boolean needChangeUpAdress(CallRet callRet) {
        if (callRet.getException() == null) {
            return false;
        }
        try {
            throw callRet.getException();
        } catch (UnknownHostException e) {
            return true;
        } catch (NoRouteToHostException e2) {
            return true;
        } catch (PortUnreachableException e3) {
            return true;
        } catch (HttpHostConnectException e4) {
            return true;
        } catch (ConnectException e5) {
            return true;
        } catch (UnknownServiceException e6) {
            return true;
        } catch (ConnectTimeoutException e7) {
            return true;
        } catch (SocketTimeoutException e8) {
            return true;
        } catch (InterruptedIOException e9) {
            return true;
        } catch (Exception e10) {
            return false;
        }
    }

    public static File getSDPath(Context context) {
        File cacheDir = context.getCacheDir();
        if (Environment.getExternalStorageState().equals("mounted")) {
            return Environment.getExternalStorageDirectory();
        }
        return cacheDir;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.io.File storeToFile(android.content.Context r6, java.io.InputStream r7) throws java.io.IOException {
        /*
        r0 = 0;
        if (r7 != 0) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r1 = getSDPath(r6);	 Catch:{ IOException -> 0x004c, all -> 0x0048 }
        r2 = "qiniu-";
        r3 = "";
        r1 = java.io.File.createTempFile(r2, r3, r1);	 Catch:{ IOException -> 0x004c, all -> 0x0048 }
        r2 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x0052, all -> 0x0048 }
        r2.<init>(r1);	 Catch:{ IOException -> 0x0052, all -> 0x0048 }
        r0 = 65536; // 0x10000 float:9.18355E-41 double:3.2379E-319;
        r0 = new byte[r0];	 Catch:{ IOException -> 0x0025 }
    L_0x0019:
        r3 = r7.read(r0);	 Catch:{ IOException -> 0x0025 }
        r4 = -1;
        if (r3 == r4) goto L_0x0036;
    L_0x0020:
        r4 = 0;
        r2.write(r0, r4, r3);	 Catch:{ IOException -> 0x0025 }
        goto L_0x0019;
    L_0x0025:
        r0 = move-exception;
    L_0x0026:
        if (r1 == 0) goto L_0x002b;
    L_0x0028:
        r1.delete();	 Catch:{ all -> 0x002c }
    L_0x002b:
        throw r0;	 Catch:{ all -> 0x002c }
    L_0x002c:
        r0 = move-exception;
    L_0x002d:
        r7.close();	 Catch:{ Exception -> 0x0044 }
    L_0x0030:
        if (r2 == 0) goto L_0x0035;
    L_0x0032:
        r2.close();	 Catch:{ IOException -> 0x0046 }
    L_0x0035:
        throw r0;
    L_0x0036:
        r7.close();	 Catch:{ Exception -> 0x0040 }
    L_0x0039:
        if (r2 == 0) goto L_0x003e;
    L_0x003b:
        r2.close();	 Catch:{ IOException -> 0x0042 }
    L_0x003e:
        r0 = r1;
        goto L_0x0003;
    L_0x0040:
        r0 = move-exception;
        goto L_0x0039;
    L_0x0042:
        r0 = move-exception;
        goto L_0x003e;
    L_0x0044:
        r1 = move-exception;
        goto L_0x0030;
    L_0x0046:
        r1 = move-exception;
        goto L_0x0035;
    L_0x0048:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x002d;
    L_0x004c:
        r1 = move-exception;
        r2 = r0;
        r5 = r0;
        r0 = r1;
        r1 = r5;
        goto L_0x0026;
    L_0x0052:
        r2 = move-exception;
        r5 = r2;
        r2 = r0;
        r0 = r5;
        goto L_0x0026;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qiniu.utils.Util.storeToFile(android.content.Context, java.io.InputStream):java.io.File");
    }

    public static String getUserAgent() {
        if (Conf.USER_AGENT != null) {
            return Conf.USER_AGENT;
        }
        if (a == null) {
            return "QiniuAndroid/6.1.0 (" + VERSION.RELEASE + "; " + Build.MODEL + "; " + b + ")";
        }
        return a;
    }

    private static String a() {
        return System.currentTimeMillis() + "" + new Random().nextInt(999);
    }
}
