package com.sina.weibo.sdk.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.exception.WeiboHttpException;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.Utility;
import cz.msebera.android.httpclient.HttpHeaders;
import cz.msebera.android.httpclient.HttpStatus;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.util.Set;

public class HttpManager {
    private static final String a = getBoundry();
    private static final String b = ("--" + a);
    private static final String c = ("--" + a + "--");

    private static native String calcOauthSignNative(Context context, String str, String str2);

    static {
        System.loadLibrary("weibosdkcore");
    }

    public static String openUrl(Context context, String str, String str2, WeiboParameters weiboParameters) throws WeiboException {
        String a = a(context, str, str2, weiboParameters);
        LogUtil.d(com.alipay.android.phone.mrpc.core.HttpManager.TAG, "Response : " + a);
        return a;
    }

    private static String a(Context context, String str, String str2, WeiboParameters weiboParameters) {
        try {
            HttpURLConnection createConnect;
            a(context, weiboParameters);
            if ("GET".equals(str2)) {
                createConnect = ConnectionFactory.createConnect(str + "?" + weiboParameters.encodeUrl(), context);
                createConnect.setRequestMethod("GET");
                createConnect.setInstanceFollowRedirects(true);
                createConnect.connect();
            } else {
                createConnect = ConnectionFactory.createConnect(str, context);
                createConnect.setInstanceFollowRedirects(true);
                createConnect.connect();
                if (weiboParameters.hasBinaryData()) {
                    OutputStream dataOutputStream = new DataOutputStream(createConnect.getOutputStream());
                    buildParams(dataOutputStream, weiboParameters);
                    dataOutputStream.flush();
                    dataOutputStream.close();
                } else {
                    DataOutputStream dataOutputStream2 = new DataOutputStream(createConnect.getOutputStream());
                    dataOutputStream2.write(weiboParameters.encodeUrl().getBytes("UTF-8"));
                    dataOutputStream2.flush();
                    dataOutputStream2.close();
                }
            }
            int responseCode = createConnect.getResponseCode();
            if (responseCode == 200) {
                return a(createConnect);
            }
            throw new WeiboHttpException(a(createConnect), responseCode);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new WeiboException(e);
        }
    }

    private static String a(HttpURLConnection httpURLConnection) {
        Throwable e;
        InputStream inputStream = null;
        InputStream inputStream2;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byte[] bArr = new byte[8192];
            inputStream2 = httpURLConnection.getInputStream();
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    try {
                        int read = inputStream2.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    } catch (IOException e2) {
                        e = e2;
                        inputStream = inputStream2;
                    } catch (Throwable th) {
                        e = th;
                    }
                }
                String str = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                if (inputStream2 != null) {
                    try {
                        inputStream2.close();
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
                inputStream = inputStream2;
                try {
                    throw new WeiboException(e);
                } catch (Throwable th2) {
                    e = th2;
                    inputStream2 = inputStream;
                    if (inputStream2 != null) {
                        try {
                            inputStream2.close();
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
                if (inputStream2 != null) {
                    inputStream2.close();
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
            inputStream2 = null;
            if (inputStream2 != null) {
                inputStream2.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw e;
        }
    }

    private static void a(Context context, WeiboParameters weiboParameters) {
        String str = "";
        if (!TextUtils.isEmpty(weiboParameters.getAppKey())) {
            str = Utility.getAid(context, weiboParameters.getAppKey());
            if (!TextUtils.isEmpty(str)) {
                weiboParameters.put("aid", str);
            }
        }
        String str2 = str;
        String a = a();
        weiboParameters.put("oauth_timestamp", a);
        String str3 = "";
        Object obj = weiboParameters.get("access_token");
        Object obj2 = weiboParameters.get("refresh_token");
        Object obj3 = weiboParameters.get("phone");
        str = (obj == null || !(obj instanceof String)) ? (obj2 == null || !(obj2 instanceof String)) ? (obj3 == null || !(obj3 instanceof String)) ? str3 : (String) obj3 : (String) obj2 : (String) obj;
        weiboParameters.put("oauth_sign", a(context, str2, str, weiboParameters.getAppKey(), a));
    }

    public static String openRedirectUrl4LocationUri(Context context, String str, String str2, WeiboParameters weiboParameters) {
        HttpURLConnection createConnect;
        if (str2.equals("GET")) {
            str = str + "?" + weiboParameters.encodeUrl();
            createConnect = ConnectionFactory.createConnect(str, context);
        } else {
            createConnect = ConnectionFactory.createConnect(str, context);
        }
        String str3 = "";
        try {
            createConnect.setInstanceFollowRedirects(false);
            createConnect.connect();
            int responseCode = createConnect.getResponseCode();
            if (responseCode == 302 || responseCode == 301) {
                return createConnect.getHeaderField(HttpHeaders.LOCATION);
            }
            if (responseCode != 200) {
                return str3;
            }
            return str;
        } catch (Exception e) {
            return str3;
        }
    }

    public static synchronized String downloadFile(Context context, String str, String str2, String str3) throws WeiboException {
        String path;
        synchronized (HttpManager.class) {
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, str3);
            if (file2.exists()) {
                path = file2.getPath();
            } else if (URLUtil.isValidUrl(str)) {
                File file3 = new File(str2, str3 + "_temp");
                HttpURLConnection createConnect = ConnectionFactory.createConnect(str, context);
                createConnect.setConnectTimeout(300000);
                createConnect.setReadTimeout(300000);
                try {
                    createConnect.setRequestMethod("GET");
                } catch (Exception e) {
                }
                try {
                    long length;
                    if (file3.exists()) {
                        length = file3.length();
                    } else {
                        file3.createNewFile();
                        length = 0;
                    }
                    createConnect.setRequestProperty("RANGE", "bytes=" + length);
                    int responseCode = createConnect.getResponseCode();
                    if (responseCode == HttpStatus.SC_PARTIAL_CONTENT) {
                        length = 0;
                    } else if (responseCode == 200) {
                        length = (long) createConnect.getContentLength();
                    } else {
                        throw new WeiboHttpException(a(createConnect), responseCode);
                    }
                    InputStream inputStream = createConnect.getInputStream();
                    RandomAccessFile randomAccessFile = new RandomAccessFile(file3, "rw");
                    randomAccessFile.seek(0);
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        randomAccessFile.write(bArr, 0, read);
                    }
                    randomAccessFile.close();
                    inputStream.close();
                    if (length == 0 || file3.length() < length) {
                        file3.delete();
                        path = "";
                    } else {
                        file3.renameTo(file2);
                        path = file2.getPath();
                    }
                } catch (Exception e2) {
                }
            } else {
                path = "";
            }
        }
        return path;
    }

    public static void buildParams(OutputStream outputStream, WeiboParameters weiboParameters) throws WeiboException {
        try {
            StringBuilder stringBuilder;
            Set<String> keySet = weiboParameters.keySet();
            for (String str : keySet) {
                if (weiboParameters.get(str) instanceof String) {
                    stringBuilder = new StringBuilder(100);
                    stringBuilder.setLength(0);
                    stringBuilder.append(b).append("\r\n");
                    stringBuilder.append("content-disposition: form-data; name=\"").append(str).append("\"\r\n\r\n");
                    stringBuilder.append(weiboParameters.get(str)).append("\r\n");
                    outputStream.write(stringBuilder.toString().getBytes());
                }
            }
            for (String str2 : keySet) {
                Object obj = weiboParameters.get(str2);
                if (obj instanceof Bitmap) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(b).append("\r\n");
                    stringBuilder.append("content-disposition: form-data; name=\"").append(str2).append("\"; filename=\"file\"\r\n");
                    stringBuilder.append("Content-Type: application/octet-stream; charset=utf-8\r\n\r\n");
                    outputStream.write(stringBuilder.toString().getBytes());
                    Bitmap bitmap = (Bitmap) obj;
                    OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
                    outputStream.write(byteArrayOutputStream.toByteArray());
                    outputStream.write("\r\n".getBytes());
                } else if (obj instanceof ByteArrayOutputStream) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(b).append("\r\n");
                    stringBuilder.append("content-disposition: form-data; name=\"").append(str2).append("\"; filename=\"file\"\r\n");
                    stringBuilder.append("Content-Type: application/octet-stream; charset=utf-8\r\n\r\n");
                    outputStream.write(stringBuilder.toString().getBytes());
                    ByteArrayOutputStream byteArrayOutputStream2 = (ByteArrayOutputStream) obj;
                    outputStream.write(byteArrayOutputStream2.toByteArray());
                    outputStream.write("\r\n".getBytes());
                    byteArrayOutputStream2.close();
                }
            }
            outputStream.write(("\r\n" + c).getBytes());
        } catch (Throwable e) {
            throw new WeiboException(e);
        }
    }

    public static String getBoundry() {
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

    private static String a() {
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
