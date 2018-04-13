package com.sina.weibo.sdk.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.exception.WeiboHttpException;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.NetworkHelper;
import com.sina.weibo.sdk.utils.Utility;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URI;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;

public class HttpManager {
    private static final String BOUNDARY = getBoundry();
    private static final int BUFFER_SIZE = 8192;
    private static final int CONNECTION_TIMEOUT = 25000;
    private static final String END_MP_BOUNDARY = ("--" + BOUNDARY + "--");
    private static final String HTTP_METHOD_GET = "GET";
    private static final String HTTP_METHOD_POST = "POST";
    private static final String MP_BOUNDARY = ("--" + BOUNDARY);
    private static final String MULTIPART_FORM_DATA = "multipart/form-data";
    private static final int SOCKET_TIMEOUT = 20000;
    private static final String TAG = "HttpManager";
    private static SSLSocketFactory sSSLSocketFactory;

    private static native String calcOauthSignNative(Context context, String str, String str2);

    static {
        System.loadLibrary("weibosdkcore");
    }

    public static String openUrl(Context context, String str, String str2, WeiboParameters weiboParameters) throws WeiboException {
        String readRsponse = readRsponse(requestHttpExecute(context, str, str2, weiboParameters));
        LogUtil.d(TAG, "Response : " + readRsponse);
        return readRsponse;
    }

    private static HttpResponse requestHttpExecute(Context context, String str, String str2, WeiboParameters weiboParameters) {
        HttpClient newHttpClient;
        Throwable e;
        HttpClient httpClient;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            newHttpClient = getNewHttpClient();
            try {
                HttpUriRequest httpGet;
                newHttpClient.getParams().setParameter("http.route.default-proxy", NetStateManager.getAPN());
                setHttpCommonParam(context, weiboParameters);
                if (str2.equals("GET")) {
                    String stringBuilder = new StringBuilder(String.valueOf(str)).append("?").append(weiboParameters.encodeUrl()).toString();
                    httpGet = new HttpGet(stringBuilder);
                    LogUtil.d(TAG, "requestHttpExecute GET Url : " + stringBuilder);
                } else if (str2.equals("POST")) {
                    LogUtil.d(TAG, "requestHttpExecute POST Url : " + str);
                    HttpPost httpPost = new HttpPost(str);
                    OutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    OutputStream outputStream;
                    try {
                        Object obj;
                        if (weiboParameters.hasBinaryData()) {
                            httpPost.setHeader("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
                            buildParams(byteArrayOutputStream2, weiboParameters);
                        } else {
                            obj = weiboParameters.get("content-type");
                            if (obj == null || !(obj instanceof String)) {
                                httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
                            } else {
                                weiboParameters.remove("content-type");
                                httpPost.setHeader("Content-Type", (String) obj);
                            }
                            String encodeUrl = weiboParameters.encodeUrl();
                            LogUtil.d(TAG, "requestHttpExecute POST postParam : " + encodeUrl);
                            byteArrayOutputStream2.write(encodeUrl.getBytes("UTF-8"));
                        }
                        httpPost.setEntity(new ByteArrayEntity(byteArrayOutputStream2.toByteArray()));
                        obj = httpPost;
                        outputStream = byteArrayOutputStream2;
                    } catch (IOException e2) {
                        e = e2;
                        outputStream = byteArrayOutputStream2;
                        httpClient = newHttpClient;
                        try {
                            e.printStackTrace();
                            throw new WeiboException(e);
                        } catch (Throwable th) {
                            e = th;
                            newHttpClient = httpClient;
                            if (byteArrayOutputStream != null) {
                                try {
                                    byteArrayOutputStream.close();
                                } catch (IOException e3) {
                                }
                            }
                            shutdownHttpClient(newHttpClient);
                            throw e;
                        }
                    } catch (Throwable th2) {
                        e = th2;
                        outputStream = byteArrayOutputStream2;
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                        shutdownHttpClient(newHttpClient);
                        throw e;
                    }
                } else if (str2.equals("DELETE")) {
                    httpGet = new HttpDelete(str);
                } else {
                    httpGet = null;
                }
                HttpResponse execute = newHttpClient.execute(httpGet);
                int statusCode = execute.getStatusLine().getStatusCode();
                if (statusCode != 200) {
                    throw new WeiboHttpException(readRsponse(execute), statusCode);
                }
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e4) {
                    }
                }
                shutdownHttpClient(newHttpClient);
                return execute;
            } catch (IOException e5) {
                e = e5;
                httpClient = newHttpClient;
                e.printStackTrace();
                throw new WeiboException(e);
            } catch (Throwable th3) {
                e = th3;
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                shutdownHttpClient(newHttpClient);
                throw e;
            }
        } catch (IOException e6) {
            e = e6;
            Object obj2 = null;
            e.printStackTrace();
            throw new WeiboException(e);
        } catch (Throwable th4) {
            e = th4;
            newHttpClient = null;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            shutdownHttpClient(newHttpClient);
            throw e;
        }
    }

    private static void setHttpCommonParam(Context context, WeiboParameters weiboParameters) {
        String str = "";
        if (!TextUtils.isEmpty(weiboParameters.getAppKey())) {
            str = Utility.getAid(context, weiboParameters.getAppKey());
            if (!TextUtils.isEmpty(str)) {
                weiboParameters.put("aid", str);
            }
        }
        String str2 = str;
        String timestamp = getTimestamp();
        weiboParameters.put("oauth_timestamp", timestamp);
        String str3 = "";
        Object obj = weiboParameters.get("access_token");
        Object obj2 = weiboParameters.get(Oauth2AccessToken.KEY_REFRESH_TOKEN);
        Object obj3 = weiboParameters.get("phone");
        str = (obj == null || !(obj instanceof String)) ? (obj2 == null || !(obj2 instanceof String)) ? (obj3 == null || !(obj3 instanceof String)) ? str3 : (String) obj3 : (String) obj2 : (String) obj;
        weiboParameters.put("oauth_sign", getOauthSign(context, str2, str, weiboParameters.getAppKey(), timestamp));
    }

    public static void shutdownHttpClient(HttpClient httpClient) {
        if (httpClient != null) {
            try {
                httpClient.getConnectionManager().closeExpiredConnections();
            } catch (Exception e) {
            }
        }
    }

    public static String openUrl4RdirectURL(Context context, String str, String str2, WeiboParameters weiboParameters) throws WeiboException {
        Throwable th;
        Throwable th2;
        HttpClient httpClient = null;
        String str3 = "";
        try {
            DefaultHttpClient defaultHttpClient = (DefaultHttpClient) getNewHttpClient();
            try {
                HttpUriRequest httpGet;
                defaultHttpClient.setRedirectHandler(new RedirectHandler() {
                    public boolean isRedirectRequested(HttpResponse httpResponse, HttpContext httpContext) {
                        LogUtil.d(HttpManager.TAG, "openUrl4RdirectURL isRedirectRequested method");
                        return false;
                    }

                    public URI getLocationURI(HttpResponse httpResponse, HttpContext httpContext) throws ProtocolException {
                        LogUtil.d(HttpManager.TAG, "openUrl4RdirectURL getLocationURI method");
                        return null;
                    }
                });
                setHttpCommonParam(context, weiboParameters);
                defaultHttpClient.getParams().setParameter("http.route.default-proxy", NetStateManager.getAPN());
                if (str2.equals("GET")) {
                    String stringBuilder = new StringBuilder(String.valueOf(str)).append("?").append(weiboParameters.encodeUrl()).toString();
                    LogUtil.d(TAG, "openUrl4RdirectURL GET url : " + stringBuilder);
                    httpGet = new HttpGet(stringBuilder);
                } else if (str2.equals("POST")) {
                    httpGet = new HttpPost(str);
                    LogUtil.d(TAG, "openUrl4RdirectURL POST url : " + str);
                }
                HttpResponse execute = defaultHttpClient.execute(httpGet);
                int statusCode = execute.getStatusLine().getStatusCode();
                String value;
                if (statusCode == 301 || statusCode == 302) {
                    value = execute.getFirstHeader("Location").getValue();
                    LogUtil.d(TAG, "RedirectURL = " + value);
                    shutdownHttpClient(defaultHttpClient);
                    return value;
                } else if (statusCode == 200) {
                    value = readRsponse(execute);
                    shutdownHttpClient(defaultHttpClient);
                    return value;
                } else {
                    throw new WeiboHttpException(readRsponse(execute), statusCode);
                }
            } catch (Throwable e) {
                th = e;
                httpClient = defaultHttpClient;
                th2 = th;
                try {
                    throw new WeiboException(th2);
                } catch (Throwable th3) {
                    th2 = th3;
                    shutdownHttpClient(httpClient);
                    throw th2;
                }
            } catch (Throwable e2) {
                th = e2;
                Object obj = defaultHttpClient;
                th2 = th;
                shutdownHttpClient(httpClient);
                throw th2;
            }
        } catch (IOException e3) {
            th2 = e3;
            throw new WeiboException(th2);
        }
    }

    public static String openRedirectUrl4LocationUri(Context context, String str, String str2, WeiboParameters weiboParameters) {
        Throwable th;
        Throwable th2;
        HttpClient httpClient = null;
        try {
            Object anonymousClass2 = new CustomRedirectHandler() {
                public boolean shouldRedirectUrl(String str) {
                    return true;
                }

                public void onReceivedException() {
                }
            };
            DefaultHttpClient defaultHttpClient = (DefaultHttpClient) getNewHttpClient();
            try {
                HttpUriRequest httpGet;
                defaultHttpClient.setRedirectHandler(anonymousClass2);
                setHttpCommonParam(context, weiboParameters);
                defaultHttpClient.getParams().setParameter("http.route.default-proxy", NetStateManager.getAPN());
                if (str2.equals("GET")) {
                    httpGet = new HttpGet(new StringBuilder(String.valueOf(str)).append("?").append(weiboParameters.encodeUrl()).toString());
                } else if (str2.equals("POST")) {
                    httpGet = new HttpPost(str);
                }
                httpGet.setHeader("User-Agent", NetworkHelper.generateUA(context));
                defaultHttpClient.execute(httpGet);
                String redirectUrl = anonymousClass2.getRedirectUrl();
                shutdownHttpClient(defaultHttpClient);
                return redirectUrl;
            } catch (Throwable e) {
                th = e;
                httpClient = defaultHttpClient;
                th2 = th;
                try {
                    throw new WeiboException(th2);
                } catch (Throwable th3) {
                    th2 = th3;
                    shutdownHttpClient(httpClient);
                    throw th2;
                }
            } catch (Throwable e2) {
                th = e2;
                Object obj = defaultHttpClient;
                th2 = th;
                shutdownHttpClient(httpClient);
                throw th2;
            }
        } catch (IOException e3) {
            th2 = e3;
            throw new WeiboException(th2);
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
                HttpClient newHttpClient = getNewHttpClient();
                long j = 0;
                File file3 = new File(str2, new StringBuilder(String.valueOf(str3)).append("_temp").toString());
                try {
                    long parseLong;
                    long j2;
                    InputStream content;
                    if (file3.exists()) {
                        j = file3.length();
                    } else {
                        file3.createNewFile();
                    }
                    HttpUriRequest httpGet = new HttpGet(str);
                    httpGet.setHeader("RANGE", "bytes=" + j + "-");
                    HttpResponse execute = newHttpClient.execute(httpGet);
                    int statusCode = execute.getStatusLine().getStatusCode();
                    if (statusCode == 206) {
                        Header[] headers = execute.getHeaders("Content-Range");
                        if (!(headers == null || headers.length == 0)) {
                            String value = headers[0].getValue();
                            parseLong = Long.parseLong(value.substring(value.indexOf(47) + 1));
                            j2 = j;
                        }
                        parseLong = 0;
                        j2 = j;
                    } else if (statusCode == 200) {
                        j = 0;
                        Header firstHeader = execute.getFirstHeader("Content-Length");
                        if (firstHeader != null) {
                            parseLong = (long) Integer.valueOf(firstHeader.getValue()).intValue();
                            j2 = 0;
                        }
                        parseLong = 0;
                        j2 = j;
                    } else {
                        throw new WeiboHttpException(readRsponse(execute), statusCode);
                    }
                    HttpEntity entity = execute.getEntity();
                    Header firstHeader2 = execute.getFirstHeader("Content-Encoding");
                    if (firstHeader2 == null || firstHeader2.getValue().toLowerCase().indexOf("gzip") <= -1) {
                        content = entity.getContent();
                    } else {
                        content = new GZIPInputStream(entity.getContent());
                    }
                    RandomAccessFile randomAccessFile = new RandomAccessFile(file3, "rw");
                    randomAccessFile.seek(j2);
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = content.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        randomAccessFile.write(bArr, 0, read);
                    }
                    randomAccessFile.close();
                    content.close();
                    if (parseLong == 0 || file3.length() < parseLong) {
                        file3.delete();
                        if (newHttpClient != null) {
                            newHttpClient.getConnectionManager().closeExpiredConnections();
                            newHttpClient.getConnectionManager().closeIdleConnections(300, TimeUnit.SECONDS);
                        }
                        path = "";
                    } else {
                        file3.renameTo(file2);
                        path = file2.getPath();
                        if (newHttpClient != null) {
                            newHttpClient.getConnectionManager().closeExpiredConnections();
                            newHttpClient.getConnectionManager().closeIdleConnections(300, TimeUnit.SECONDS);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    file3.delete();
                    if (newHttpClient != null) {
                        newHttpClient.getConnectionManager().closeExpiredConnections();
                        newHttpClient.getConnectionManager().closeIdleConnections(300, TimeUnit.SECONDS);
                    }
                } catch (Throwable th) {
                    if (newHttpClient != null) {
                        newHttpClient.getConnectionManager().closeExpiredConnections();
                        newHttpClient.getConnectionManager().closeIdleConnections(300, TimeUnit.SECONDS);
                    }
                }
            } else {
                path = "";
            }
        }
        return path;
    }

    public static HttpClient getNewHttpClient() {
        try {
            HttpParams basicHttpParams = new BasicHttpParams();
            HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(basicHttpParams, "UTF-8");
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme("https", getSSLSocketFactory(), 443));
            ClientConnectionManager threadSafeClientConnManager = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
            HttpConnectionParams.setConnectionTimeout(basicHttpParams, 25000);
            HttpConnectionParams.setSoTimeout(basicHttpParams, SOCKET_TIMEOUT);
            return new DefaultHttpClient(threadSafeClientConnManager, basicHttpParams);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }

    public static void buildParams(OutputStream outputStream, WeiboParameters weiboParameters) throws WeiboException {
        try {
            StringBuilder stringBuilder;
            Set<String> keySet = weiboParameters.keySet();
            for (String str : keySet) {
                if (weiboParameters.get(str) instanceof String) {
                    stringBuilder = new StringBuilder(100);
                    stringBuilder.setLength(0);
                    stringBuilder.append(MP_BOUNDARY).append("\r\n");
                    stringBuilder.append("content-disposition: form-data; name=\"").append(str).append("\"\r\n\r\n");
                    stringBuilder.append(weiboParameters.get(str)).append("\r\n");
                    outputStream.write(stringBuilder.toString().getBytes());
                }
            }
            for (String str2 : keySet) {
                Object obj = weiboParameters.get(str2);
                if (obj instanceof Bitmap) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(MP_BOUNDARY).append("\r\n");
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
                    stringBuilder.append(MP_BOUNDARY).append("\r\n");
                    stringBuilder.append("content-disposition: form-data; name=\"").append(str2).append("\"; filename=\"file\"\r\n");
                    stringBuilder.append("Content-Type: application/octet-stream; charset=utf-8\r\n\r\n");
                    outputStream.write(stringBuilder.toString().getBytes());
                    ByteArrayOutputStream byteArrayOutputStream2 = (ByteArrayOutputStream) obj;
                    outputStream.write(byteArrayOutputStream2.toByteArray());
                    outputStream.write("\r\n".getBytes());
                    byteArrayOutputStream2.close();
                }
            }
            outputStream.write(("\r\n" + END_MP_BOUNDARY).getBytes());
        } catch (Throwable e) {
            throw new WeiboException(e);
        }
    }

    public static String readRsponse(HttpResponse httpResponse) throws WeiboException {
        InputStream content;
        Throwable e;
        Throwable th;
        String str = null;
        if (httpResponse != null) {
            HttpEntity entity = httpResponse.getEntity();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                content = entity.getContent();
                try {
                    Header firstHeader = httpResponse.getFirstHeader("Content-Encoding");
                    if (firstHeader != null && firstHeader.getValue().toLowerCase().indexOf("gzip") > -1) {
                        content = new GZIPInputStream(content);
                    }
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int read = content.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    str = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                    LogUtil.d(TAG, "readRsponse result : " + str);
                    if (content != null) {
                        try {
                            content.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                }
            } catch (Throwable e4) {
                th = e4;
                content = null;
                e = th;
                try {
                    throw new WeiboException(e);
                } catch (Throwable th2) {
                    e = th2;
                    if (content != null) {
                        try {
                            content.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e2222) {
                            e2222.printStackTrace();
                        }
                    }
                    throw e;
                }
            } catch (Throwable e42) {
                th = e42;
                content = null;
                e = th;
                if (content != null) {
                    content.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw e;
            }
        }
        return str;
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

    private static SSLSocketFactory getSSLSocketFactory() {
        if (sSSLSocketFactory == null) {
            try {
                KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
                instance.load(null, null);
                Certificate certificate = getCertificate("cacert_cn.cer");
                Certificate certificate2 = getCertificate("cacert_com.cer");
                instance.setCertificateEntry("cnca", certificate);
                instance.setCertificateEntry("comca", certificate2);
                sSSLSocketFactory = new SSLSocketFactoryEx(instance);
                LogUtil.d(TAG, "getSSLSocketFactory noraml !!!!!");
            } catch (Exception e) {
                e.printStackTrace();
                sSSLSocketFactory = SSLSocketFactory.getSocketFactory();
                LogUtil.d(TAG, "getSSLSocketFactory error default !!!!!");
            }
        }
        return sSSLSocketFactory;
    }

    private static Certificate getCertificate(String str) throws CertificateException, IOException {
        CertificateFactory instance = CertificateFactory.getInstance("X.509");
        InputStream resourceAsStream = HttpManager.class.getResourceAsStream(str);
        try {
            Certificate generateCertificate = instance.generateCertificate(resourceAsStream);
            return generateCertificate;
        } finally {
            if (resourceAsStream != null) {
                resourceAsStream.close();
            }
        }
    }

    private static String getTimestamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    private static String getOauthSign(Context context, String str, String str2, String str3, String str4) {
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
