package com.sina.weibo.sdk.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.text.TextUtils;
import cn.v6.sixrooms.socket.common.SocketUtil;
import com.sina.weibo.sdk.a.d;
import com.sina.weibo.sdk.a.j;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.exception.WeiboHttpException;
import com.tencent.connect.common.Constants;
import com.tencent.stat.DeviceInfo;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Set;

public class HttpManager {
    private static final String a = a();
    private static final String b = ("--" + a);
    private static final String c = ("--" + a + "--");

    private static native String calcOauthSignNative(Context context, String str, String str2);

    static {
        System.loadLibrary("weibosdkcore");
    }

    public static String a(Context context, String str, String str2, e eVar) throws WeiboException {
        String b = b(context, str, str2, eVar);
        d.a("HttpManager", "Response : " + b);
        return b;
    }

    private static String b(Context context, String str, String str2, e eVar) {
        HttpURLConnection httpURLConnection = null;
        try {
            a(context, eVar);
            if ("GET".equals(str2)) {
                httpURLConnection = b.a(str + "?" + eVar.c(), context);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setInstanceFollowRedirects(true);
                httpURLConnection.connect();
            } else {
                httpURLConnection = b.a(str, context);
                httpURLConnection.setInstanceFollowRedirects(true);
                httpURLConnection.connect();
                if (eVar.d()) {
                    OutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    a(dataOutputStream, eVar);
                    dataOutputStream.flush();
                    dataOutputStream.close();
                } else {
                    DataOutputStream dataOutputStream2 = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream2.write(eVar.c().getBytes("UTF-8"));
                    dataOutputStream2.flush();
                    dataOutputStream2.close();
                }
            }
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != 200) {
                throw new WeiboHttpException(a(httpURLConnection), responseCode);
            }
            String a = a(httpURLConnection);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return a;
        } catch (Throwable e) {
            e.printStackTrace();
            throw new WeiboException(e);
        } catch (Throwable th) {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }

    private static String a(HttpURLConnection httpURLConnection) {
        InputStream inputStream;
        Throwable e;
        InputStream inputStream2 = null;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byte[] bArr = new byte[8192];
            inputStream = httpURLConnection.getInputStream();
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    try {
                        int read = inputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    } catch (IOException e2) {
                        e = e2;
                        inputStream2 = inputStream;
                    } catch (Throwable th) {
                        e = th;
                    }
                }
                String str = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e3) {
                    }
                }
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e4) {
                    }
                }
                return str;
            } catch (IOException e5) {
                e = e5;
                byteArrayOutputStream = null;
                inputStream2 = inputStream;
                try {
                    throw new WeiboException(e);
                } catch (Throwable th2) {
                    e = th2;
                    inputStream = inputStream2;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e6) {
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception e7) {
                        }
                    }
                    throw e;
                }
            } catch (Throwable th3) {
                e = th3;
                byteArrayOutputStream = null;
                if (inputStream != null) {
                    inputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw e;
            }
        } catch (IOException e8) {
            e = e8;
            byteArrayOutputStream = null;
            throw new WeiboException(e);
        } catch (Throwable th4) {
            e = th4;
            byteArrayOutputStream = null;
            inputStream = null;
            if (inputStream != null) {
                inputStream.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw e;
        }
    }

    private static void a(Context context, e eVar) {
        String str = "";
        if (!TextUtils.isEmpty(eVar.a())) {
            str = j.b(context, eVar.a());
            if (!TextUtils.isEmpty(str)) {
                eVar.a(DeviceInfo.TAG_ANDROID_ID, str);
            }
        }
        String str2 = str;
        String b = b();
        eVar.a("oauth_timestamp", b);
        String str3 = "";
        Object a = eVar.a(Constants.PARAM_ACCESS_TOKEN);
        Object a2 = eVar.a("refresh_token");
        Object a3 = eVar.a("phone");
        str = (a == null || !(a instanceof String)) ? (a2 == null || !(a2 instanceof String)) ? (a3 == null || !(a3 instanceof String)) ? str3 : (String) a3 : (String) a2 : (String) a;
        eVar.a("oauth_sign", a(context, str2, str, eVar.a(), b));
    }

    public static void a(OutputStream outputStream, e eVar) throws WeiboException {
        try {
            StringBuilder stringBuilder;
            Set<String> b = eVar.b();
            for (String str : b) {
                if (eVar.a(str) instanceof String) {
                    stringBuilder = new StringBuilder(100);
                    stringBuilder.setLength(0);
                    stringBuilder.append(b).append(SocketUtil.CRLF);
                    stringBuilder.append("content-disposition: form-data; name=\"").append(str).append("\"\r\n\r\n");
                    stringBuilder.append(eVar.a(str)).append(SocketUtil.CRLF);
                    outputStream.write(stringBuilder.toString().getBytes());
                }
            }
            for (String str2 : b) {
                Object a = eVar.a(str2);
                if (a instanceof Bitmap) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(b).append(SocketUtil.CRLF);
                    stringBuilder.append("content-disposition: form-data; name=\"").append(str2).append("\"; filename=\"file\"\r\n");
                    stringBuilder.append("Content-Type: application/octet-stream; charset=utf-8\r\n\r\n");
                    outputStream.write(stringBuilder.toString().getBytes());
                    Bitmap bitmap = (Bitmap) a;
                    OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
                    outputStream.write(byteArrayOutputStream.toByteArray());
                    outputStream.write(SocketUtil.CRLF.getBytes());
                } else if (a instanceof ByteArrayOutputStream) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(b).append(SocketUtil.CRLF);
                    stringBuilder.append("content-disposition: form-data; name=\"").append(str2).append("\"; filename=\"file\"\r\n");
                    stringBuilder.append("Content-Type: application/octet-stream; charset=utf-8\r\n\r\n");
                    outputStream.write(stringBuilder.toString().getBytes());
                    ByteArrayOutputStream byteArrayOutputStream2 = (ByteArrayOutputStream) a;
                    outputStream.write(byteArrayOutputStream2.toByteArray());
                    outputStream.write(SocketUtil.CRLF.getBytes());
                    byteArrayOutputStream2.close();
                }
            }
            outputStream.write((SocketUtil.CRLF + c).getBytes());
        } catch (Throwable e) {
            throw new WeiboException(e);
        }
    }

    public static String a() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 1; i < 12; i++) {
            long currentTimeMillis = System.currentTimeMillis() + ((long) i);
            if (currentTimeMillis % 3 == 0) {
                stringBuffer.append(((char) ((int) currentTimeMillis)) % 9);
            } else if (currentTimeMillis % 3 == 1) {
                stringBuffer.append((char) ((int) ((currentTimeMillis % 26) + 65)));
            } else {
                stringBuffer.append((char) ((int) ((currentTimeMillis % 26) + 97)));
            }
        }
        return stringBuffer.toString();
    }

    private static String b() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    private static String a(Context context, String str, String str2, String str3, String str4) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (!TextUtils.isEmpty(str)) {
            stringBuilder.append(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            stringBuilder.append(str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            stringBuilder.append(str3);
        }
        return calcOauthSignNative(context, stringBuilder.toString(), str4);
    }
}
