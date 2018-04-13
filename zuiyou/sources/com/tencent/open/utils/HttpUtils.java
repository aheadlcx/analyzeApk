package com.tencent.open.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.tencent.bugly.BuglyStrategy$a;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.open.a.f;
import com.tencent.tauth.IRequestListener;
import java.io.ByteArrayOutputStream;
import java.io.CharConversionException;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.InvalidObjectException;
import java.io.NotActiveException;
import java.io.NotSerializableException;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.io.SyncFailedException;
import java.io.UTFDataFormatException;
import java.io.UnsupportedEncodingException;
import java.io.WriteAbortedException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.HttpRetryException;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.PortUnreachableException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileLockInterruptionException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.UnmappableCharacterException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.InvalidPropertiesFormatException;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLKeyException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.apache.http.ConnectionClosedException;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.MalformedChunkCodingException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.json.JSONObject;

public class HttpUtils {

    public static class HttpStatusException extends Exception {
        public static final String ERROR_INFO = "http status code error:";

        public HttpStatusException(String str) {
            super(str);
        }
    }

    public static class NetworkUnavailableException extends Exception {
        public static final String ERROR_INFO = "network unavailable";

        public NetworkUnavailableException(String str) {
            super(str);
        }
    }

    public static class a extends SSLSocketFactory {
        private final SSLContext a = SSLContext.getInstance("TLS");

        public a(KeyStore keyStore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
            b bVar;
            super(keyStore);
            try {
                bVar = new b();
            } catch (Exception e) {
                bVar = null;
            }
            this.a.init(null, new TrustManager[]{bVar}, null);
        }

        public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException, UnknownHostException {
            return this.a.getSocketFactory().createSocket(socket, str, i, z);
        }

        public Socket createSocket() throws IOException {
            return this.a.getSocketFactory().createSocket();
        }
    }

    public static class b implements X509TrustManager {
        X509TrustManager a;

        b() throws Exception {
            KeyStore instance;
            Throwable th;
            FileInputStream fileInputStream;
            try {
                instance = KeyStore.getInstance("JKS");
            } catch (Exception e) {
                instance = null;
            }
            TrustManager[] trustManagerArr = new TrustManager[0];
            if (instance != null) {
                try {
                    InputStream fileInputStream2 = new FileInputStream("trustedCerts");
                    try {
                        instance.load(fileInputStream2, "passphrase".toCharArray());
                        TrustManagerFactory instance2 = TrustManagerFactory.getInstance("SunX509", "SunJSSE");
                        instance2.init(instance);
                        TrustManager[] trustManagers = instance2.getTrustManagers();
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        InputStream inputStream = fileInputStream2;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    fileInputStream = null;
                    th = th4;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
            }
            TrustManagerFactory instance3 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance3.init((KeyStore) null);
            trustManagers = instance3.getTrustManagers();
            for (int i = 0; i < trustManagers.length; i++) {
                if (trustManagers[i] instanceof X509TrustManager) {
                    this.a = (X509TrustManager) trustManagers[i];
                    return;
                }
            }
            throw new Exception("Couldn't initialize");
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            this.a.checkClientTrusted(x509CertificateArr, str);
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            this.a.checkServerTrusted(x509CertificateArr, str);
        }

        public X509Certificate[] getAcceptedIssuers() {
            return this.a.getAcceptedIssuers();
        }
    }

    public static class c {
        public final String a;
        public final int b;

        private c(String str, int i) {
            this.a = str;
            this.b = i;
        }
    }

    private HttpUtils() {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONObject request(com.tencent.connect.auth.QQToken r20, android.content.Context r21, java.lang.String r22, android.os.Bundle r23, java.lang.String r24) throws java.io.IOException, org.json.JSONException, com.tencent.open.utils.HttpUtils.NetworkUnavailableException, com.tencent.open.utils.HttpUtils.HttpStatusException {
        /*
        r4 = "openSDK_LOG.HttpUtils";
        r5 = "OpenApi request";
        com.tencent.open.a.f.a(r4, r5);
        r4 = r22.toLowerCase();
        r5 = "http";
        r4 = r4.startsWith(r5);
        if (r4 != 0) goto L_0x01d2;
    L_0x0016:
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = com.tencent.open.utils.f.a();
        r6 = "https://openmobile.qq.com/";
        r0 = r21;
        r5 = r5.a(r0, r6);
        r4 = r4.append(r5);
        r0 = r22;
        r4 = r4.append(r0);
        r4 = r4.toString();
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = com.tencent.open.utils.f.a();
        r7 = "https://openmobile.qq.com/";
        r0 = r21;
        r6 = r6.a(r0, r7);
        r5 = r5.append(r6);
        r0 = r22;
        r5 = r5.append(r0);
        r5 = r5.toString();
    L_0x0056:
        r0 = r21;
        r1 = r20;
        r2 = r22;
        a(r0, r1, r2);
        r10 = 0;
        r8 = android.os.SystemClock.elapsedRealtime();
        r7 = 0;
        r6 = r20.getAppId();
        r0 = r21;
        r6 = com.tencent.open.utils.e.a(r0, r6);
        r11 = "Common_HttpRetryCount";
        r6 = r6.a(r11);
        r11 = "OpenConfig_test";
        r12 = new java.lang.StringBuilder;
        r12.<init>();
        r13 = "config 1:Common_HttpRetryCount            config_value:";
        r12 = r12.append(r13);
        r12 = r12.append(r6);
        r13 = "   appid:";
        r12 = r12.append(r13);
        r13 = r20.getAppId();
        r12 = r12.append(r13);
        r13 = "     url:";
        r12 = r12.append(r13);
        r12 = r12.append(r5);
        r12 = r12.toString();
        com.tencent.open.a.f.a(r11, r12);
        if (r6 != 0) goto L_0x010e;
    L_0x00ac:
        r6 = 3;
        r13 = r6;
    L_0x00ae:
        r6 = "OpenConfig_test";
        r11 = new java.lang.StringBuilder;
        r11.<init>();
        r12 = "config 1:Common_HttpRetryCount            result_value:";
        r11 = r11.append(r12);
        r11 = r11.append(r13);
        r12 = "   appid:";
        r11 = r11.append(r12);
        r12 = r20.getAppId();
        r11 = r11.append(r12);
        r12 = "     url:";
        r11 = r11.append(r12);
        r11 = r11.append(r5);
        r11 = r11.toString();
        com.tencent.open.a.f.a(r6, r11);
        r18 = r7;
        r6 = r8;
        r8 = r18;
        r9 = r10;
    L_0x00e8:
        r14 = r8 + 1;
        r0 = r21;
        r1 = r24;
        r2 = r23;
        r10 = openUrl2(r0, r4, r1, r2);	 Catch:{ ConnectTimeoutException -> 0x01c8, SocketTimeoutException -> 0x01c2, HttpStatusException -> 0x015a, NetworkUnavailableException -> 0x0184, MalformedURLException -> 0x0189, IOException -> 0x019b, JSONException -> 0x01b0 }
        r8 = r10.a;	 Catch:{ ConnectTimeoutException -> 0x01c8, SocketTimeoutException -> 0x01c2, HttpStatusException -> 0x015a, NetworkUnavailableException -> 0x0184, MalformedURLException -> 0x0189, IOException -> 0x019b, JSONException -> 0x01b0 }
        r15 = com.tencent.open.utils.i.d(r8);	 Catch:{ ConnectTimeoutException -> 0x01c8, SocketTimeoutException -> 0x01c2, HttpStatusException -> 0x015a, NetworkUnavailableException -> 0x0184, MalformedURLException -> 0x0189, IOException -> 0x019b, JSONException -> 0x01b0 }
        r8 = "ret";
        r12 = r15.getInt(r8);	 Catch:{ JSONException -> 0x0110, ConnectTimeoutException -> 0x0113, SocketTimeoutException -> 0x0139, HttpStatusException -> 0x015a, NetworkUnavailableException -> 0x0184, MalformedURLException -> 0x0189, IOException -> 0x019b }
    L_0x0101:
        r8 = r10.b;	 Catch:{ ConnectTimeoutException -> 0x0113, SocketTimeoutException -> 0x0139, HttpStatusException -> 0x015a, NetworkUnavailableException -> 0x0184, MalformedURLException -> 0x0189, IOException -> 0x019b, JSONException -> 0x01b0 }
        r10 = r10.c;	 Catch:{ ConnectTimeoutException -> 0x0113, SocketTimeoutException -> 0x0139, HttpStatusException -> 0x015a, NetworkUnavailableException -> 0x0184, MalformedURLException -> 0x0189, IOException -> 0x019b, JSONException -> 0x01b0 }
        r13 = r15;
    L_0x0106:
        r4 = com.tencent.open.b.g.a();
        r4.a(r5, r6, r8, r10, r12);
        return r13;
    L_0x010e:
        r13 = r6;
        goto L_0x00ae;
    L_0x0110:
        r8 = move-exception;
        r12 = -4;
        goto L_0x0101;
    L_0x0113:
        r8 = move-exception;
        r16 = r15;
        r15 = r8;
    L_0x0117:
        r15.printStackTrace();
        r12 = -7;
        r8 = 0;
        r10 = 0;
        if (r14 >= r13) goto L_0x0131;
    L_0x0121:
        r6 = android.os.SystemClock.elapsedRealtime();
        r18 = r8;
        r8 = r16;
        r16 = r18;
    L_0x012b:
        if (r14 < r13) goto L_0x01ce;
    L_0x012d:
        r13 = r8;
        r8 = r16;
        goto L_0x0106;
    L_0x0131:
        r4 = com.tencent.open.b.g.a();
        r4.a(r5, r6, r8, r10, r12);
        throw r15;
    L_0x0139:
        r8 = move-exception;
        r16 = r15;
        r15 = r8;
    L_0x013d:
        r15.printStackTrace();
        r12 = -8;
        r8 = 0;
        r10 = 0;
        if (r14 >= r13) goto L_0x0152;
    L_0x0147:
        r6 = android.os.SystemClock.elapsedRealtime();
        r18 = r8;
        r8 = r16;
        r16 = r18;
        goto L_0x012b;
    L_0x0152:
        r4 = com.tencent.open.b.g.a();
        r4.a(r5, r6, r8, r10, r12);
        throw r15;
    L_0x015a:
        r4 = move-exception;
        r13 = r4;
        r13.printStackTrace();
        r4 = r13.getMessage();
        r8 = "http status code error:";
        r9 = "";
        r4 = r4.replace(r8, r9);	 Catch:{ Exception -> 0x017d }
        r12 = java.lang.Integer.parseInt(r4);	 Catch:{ Exception -> 0x017d }
    L_0x0171:
        r8 = 0;
        r10 = 0;
        r4 = com.tencent.open.b.g.a();
        r4.a(r5, r6, r8, r10, r12);
        throw r13;
    L_0x017d:
        r4 = move-exception;
        r4.printStackTrace();
        r12 = -9;
        goto L_0x0171;
    L_0x0184:
        r4 = move-exception;
        r4.printStackTrace();
        throw r4;
    L_0x0189:
        r4 = move-exception;
        r13 = r4;
        r13.printStackTrace();
        r12 = -3;
        r8 = 0;
        r10 = 0;
        r4 = com.tencent.open.b.g.a();
        r4.a(r5, r6, r8, r10, r12);
        throw r13;
    L_0x019b:
        r4 = move-exception;
        r13 = r4;
        r13.printStackTrace();
        r12 = getErrorCodeFromException(r13);
        r8 = 0;
        r10 = 0;
        r4 = com.tencent.open.b.g.a();
        r4.a(r5, r6, r8, r10, r12);
        throw r13;
    L_0x01b0:
        r4 = move-exception;
        r13 = r4;
        r13.printStackTrace();
        r12 = -4;
        r8 = 0;
        r10 = 0;
        r4 = com.tencent.open.b.g.a();
        r4.a(r5, r6, r8, r10, r12);
        throw r13;
    L_0x01c2:
        r8 = move-exception;
        r15 = r8;
        r16 = r9;
        goto L_0x013d;
    L_0x01c8:
        r8 = move-exception;
        r15 = r8;
        r16 = r9;
        goto L_0x0117;
    L_0x01ce:
        r9 = r8;
        r8 = r14;
        goto L_0x00e8;
    L_0x01d2:
        r5 = r22;
        r4 = r22;
        goto L_0x0056;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.utils.HttpUtils.request(com.tencent.connect.auth.QQToken, android.content.Context, java.lang.String, android.os.Bundle, java.lang.String):org.json.JSONObject");
    }

    public static void requestAsync(QQToken qQToken, Context context, String str, Bundle bundle, String str2, IRequestListener iRequestListener) {
        f.a("openSDK_LOG.HttpUtils", "OpenApi requestAsync");
        final QQToken qQToken2 = qQToken;
        final Context context2 = context;
        final String str3 = str;
        final Bundle bundle2 = bundle;
        final String str4 = str2;
        final IRequestListener iRequestListener2 = iRequestListener;
        new Thread() {
            public void run() {
                try {
                    JSONObject request = HttpUtils.request(qQToken2, context2, str3, bundle2, str4);
                    if (iRequestListener2 != null) {
                        iRequestListener2.onComplete(request);
                        f.b("openSDK_LOG.HttpUtils", "OpenApi onComplete");
                    }
                } catch (Throwable e) {
                    if (iRequestListener2 != null) {
                        iRequestListener2.onMalformedURLException(e);
                        f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync MalformedURLException", e);
                    }
                } catch (Throwable e2) {
                    if (iRequestListener2 != null) {
                        iRequestListener2.onConnectTimeoutException(e2);
                        f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onConnectTimeoutException", e2);
                    }
                } catch (Throwable e22) {
                    if (iRequestListener2 != null) {
                        iRequestListener2.onSocketTimeoutException(e22);
                        f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onSocketTimeoutException", e22);
                    }
                } catch (Throwable e222) {
                    if (iRequestListener2 != null) {
                        iRequestListener2.onNetworkUnavailableException(e222);
                        f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onNetworkUnavailableException", e222);
                    }
                } catch (Throwable e2222) {
                    if (iRequestListener2 != null) {
                        iRequestListener2.onHttpStatusException(e2222);
                        f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onHttpStatusException", e2222);
                    }
                } catch (Throwable e22222) {
                    if (iRequestListener2 != null) {
                        iRequestListener2.onIOException(e22222);
                        f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync IOException", e22222);
                    }
                } catch (Throwable e222222) {
                    if (iRequestListener2 != null) {
                        iRequestListener2.onJSONException(e222222);
                        f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync JSONException", e222222);
                    }
                } catch (Throwable e2222222) {
                    if (iRequestListener2 != null) {
                        iRequestListener2.onUnknowException(e2222222);
                        f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onUnknowException", e2222222);
                    }
                }
            }
        }.start();
    }

    private static void a(Context context, QQToken qQToken, String str) {
        if (str.indexOf("add_share") > -1 || str.indexOf("upload_pic") > -1 || str.indexOf("add_topic") > -1 || str.indexOf("set_user_face") > -1 || str.indexOf("add_t") > -1 || str.indexOf("add_pic_t") > -1 || str.indexOf("add_pic_url") > -1 || str.indexOf("add_video") > -1) {
            com.tencent.connect.a.a.a(context, qQToken, "requireApi", str);
        }
    }

    public static int getErrorCodeFromException(IOException iOException) {
        if (iOException instanceof CharConversionException) {
            return -20;
        }
        if (iOException instanceof MalformedInputException) {
            return -21;
        }
        if (iOException instanceof UnmappableCharacterException) {
            return -22;
        }
        if (iOException instanceof HttpResponseException) {
            return -23;
        }
        if (iOException instanceof ClosedChannelException) {
            return -24;
        }
        if (iOException instanceof ConnectionClosedException) {
            return -25;
        }
        if (iOException instanceof EOFException) {
            return -26;
        }
        if (iOException instanceof FileLockInterruptionException) {
            return -27;
        }
        if (iOException instanceof FileNotFoundException) {
            return -28;
        }
        if (iOException instanceof HttpRetryException) {
            return -29;
        }
        if (iOException instanceof ConnectTimeoutException) {
            return -7;
        }
        if (iOException instanceof SocketTimeoutException) {
            return -8;
        }
        if (iOException instanceof InvalidPropertiesFormatException) {
            return -30;
        }
        if (iOException instanceof MalformedChunkCodingException) {
            return -31;
        }
        if (iOException instanceof MalformedURLException) {
            return -3;
        }
        if (iOException instanceof NoHttpResponseException) {
            return -32;
        }
        if (iOException instanceof InvalidClassException) {
            return -33;
        }
        if (iOException instanceof InvalidObjectException) {
            return -34;
        }
        if (iOException instanceof NotActiveException) {
            return -35;
        }
        if (iOException instanceof NotSerializableException) {
            return -36;
        }
        if (iOException instanceof OptionalDataException) {
            return -37;
        }
        if (iOException instanceof StreamCorruptedException) {
            return -38;
        }
        if (iOException instanceof WriteAbortedException) {
            return -39;
        }
        if (iOException instanceof ProtocolException) {
            return -40;
        }
        if (iOException instanceof SSLHandshakeException) {
            return -41;
        }
        if (iOException instanceof SSLKeyException) {
            return -42;
        }
        if (iOException instanceof SSLPeerUnverifiedException) {
            return -43;
        }
        if (iOException instanceof SSLProtocolException) {
            return -44;
        }
        if (iOException instanceof BindException) {
            return -45;
        }
        if (iOException instanceof ConnectException) {
            return -46;
        }
        if (iOException instanceof NoRouteToHostException) {
            return -47;
        }
        if (iOException instanceof PortUnreachableException) {
            return -48;
        }
        if (iOException instanceof SyncFailedException) {
            return -49;
        }
        if (iOException instanceof UTFDataFormatException) {
            return -50;
        }
        if (iOException instanceof UnknownHostException) {
            return -51;
        }
        if (iOException instanceof UnknownServiceException) {
            return -52;
        }
        if (iOException instanceof UnsupportedEncodingException) {
            return -53;
        }
        if (iOException instanceof ZipException) {
            return -54;
        }
        return -2;
    }

    public static com.tencent.open.utils.i.a openUrl2(Context context, String str, String str2, Bundle bundle) throws MalformedURLException, IOException, NetworkUnavailableException, HttpStatusException {
        Bundle bundle2;
        HttpUriRequest httpUriRequest;
        int i;
        int size;
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                    throw new NetworkUnavailableException(NetworkUnavailableException.ERROR_INFO);
                }
            }
        }
        if (bundle != null) {
            bundle2 = new Bundle(bundle);
        } else {
            bundle2 = new Bundle();
        }
        String str3 = "";
        str3 = bundle2.getString("appid_for_getting_config");
        bundle2.remove("appid_for_getting_config");
        HttpClient httpClient = getHttpClient(context, str3, str);
        int length;
        if (str2.equals(Constants.HTTP_GET)) {
            String encodeUrl = encodeUrl(bundle2);
            length = 0 + encodeUrl.length();
            f.a("openSDK_LOG.HttpUtils", "-->openUrl2 before url =" + str);
            if (str.indexOf("?") == -1) {
                str3 = str + "?";
            } else {
                str3 = str + "&";
            }
            f.a("openSDK_LOG.HttpUtils", "-->openUrl2 encodedParam =" + encodeUrl + " -- url = " + str3);
            HttpUriRequest httpGet = new HttpGet(str3 + encodeUrl);
            httpGet.addHeader("Accept-Encoding", "gzip");
            int i2 = length;
            httpUriRequest = httpGet;
            i = i2;
        } else if (str2.equals(Constants.HTTP_POST)) {
            Object obj;
            HttpPost httpPost = new HttpPost(str);
            httpPost.addHeader("Accept-Encoding", "gzip");
            Bundle bundle3 = new Bundle();
            for (String str32 : bundle2.keySet()) {
                obj = bundle2.get(str32);
                if (obj instanceof byte[]) {
                    bundle3.putByteArray(str32, (byte[]) obj);
                }
            }
            if (!bundle2.containsKey(PushConstants.MZ_PUSH_MESSAGE_METHOD)) {
                bundle2.putString(PushConstants.MZ_PUSH_MESSAGE_METHOD, str2);
            }
            httpPost.setHeader("Content-Type", "multipart/form-data; boundary=3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f");
            httpPost.setHeader("Connection", "Keep-Alive");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(i.i("--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n"));
            byteArrayOutputStream.write(i.i(encodePostBody(bundle2, "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f")));
            if (!bundle3.isEmpty()) {
                size = bundle3.size();
                byteArrayOutputStream.write(i.i("\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n"));
                length = -1;
                for (String str322 : bundle3.keySet()) {
                    length++;
                    byteArrayOutputStream.write(i.i("Content-Disposition: form-data; name=\"" + str322 + "\"; filename=\"" + str322 + "\"" + "\r\n"));
                    byteArrayOutputStream.write(i.i("Content-Type: content/unknown\r\n\r\n"));
                    byte[] byteArray = bundle3.getByteArray(str322);
                    if (byteArray != null) {
                        byteArrayOutputStream.write(byteArray);
                    }
                    if (length < size - 1) {
                        byteArrayOutputStream.write(i.i("\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n"));
                    }
                }
            }
            byteArrayOutputStream.write(i.i("\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f--\r\n"));
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            i = toByteArray.length + 0;
            byteArrayOutputStream.close();
            httpPost.setEntity(new ByteArrayEntity(toByteArray));
            obj = httpPost;
        } else {
            httpUriRequest = null;
            i = 0;
        }
        HttpResponse execute = httpClient.execute(httpUriRequest);
        size = execute.getStatusLine().getStatusCode();
        if (size == 200) {
            return new com.tencent.open.utils.i.a(a(execute), i);
        }
        throw new HttpStatusException(HttpStatusException.ERROR_INFO + size);
    }

    private static String a(HttpResponse httpResponse) throws IllegalStateException, IOException {
        InputStream inputStream;
        String str = "";
        InputStream content = httpResponse.getEntity().getContent();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Header firstHeader = httpResponse.getFirstHeader("Content-Encoding");
        if (firstHeader == null || firstHeader.getValue().toLowerCase().indexOf("gzip") <= -1) {
            inputStream = content;
        } else {
            inputStream = new GZIPInputStream(content);
        }
        byte[] bArr = new byte[512];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                String str2 = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                inputStream.close();
                return str2;
            }
        }
    }

    public static HttpClient getHttpClient(Context context, String str, String str2) {
        e a;
        int a2;
        int i = 0;
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        if (VERSION.SDK_INT < 16) {
            try {
                KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
                instance.load(null, null);
                SocketFactory aVar = new a(instance);
                aVar.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
                schemeRegistry.register(new Scheme("https", aVar, 443));
            } catch (Exception e) {
                schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
            }
        } else {
            schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        }
        HttpParams basicHttpParams = new BasicHttpParams();
        if (context != null) {
            a = e.a(context, str);
        } else {
            a = null;
        }
        if (a != null) {
            a2 = a.a("Common_HttpConnectionTimeout");
            i = a.a("Common_SocketConnectionTimeout");
        } else {
            a2 = 0;
        }
        if (a2 == 0) {
            a2 = 15000;
        }
        if (i == 0) {
            i = BuglyStrategy$a.MAX_USERDATA_VALUE_LENGTH;
        }
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, a2);
        HttpConnectionParams.setSoTimeout(basicHttpParams, i);
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(basicHttpParams, "UTF-8");
        HttpProtocolParams.setUserAgent(basicHttpParams, "AndroidSDK_" + VERSION.SDK + "_" + Build.DEVICE + "_" + VERSION.RELEASE);
        HttpClient defaultHttpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
        c proxy = getProxy(context);
        if (proxy != null) {
            defaultHttpClient.getParams().setParameter("http.route.default-proxy", new HttpHost(proxy.a, proxy.b));
        }
        return defaultHttpClient;
    }

    public static String encodeUrl(Bundle bundle) {
        if (bundle == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (String str : bundle.keySet()) {
            Object obj2 = bundle.get(str);
            if ((obj2 instanceof String) || (obj2 instanceof String[])) {
                Object obj3;
                if (obj2 instanceof String[]) {
                    if (obj != null) {
                        obj = null;
                    } else {
                        stringBuilder.append("&");
                    }
                    stringBuilder.append(URLEncoder.encode(str) + "=");
                    String[] stringArray = bundle.getStringArray(str);
                    if (stringArray != null) {
                        for (int i = 0; i < stringArray.length; i++) {
                            if (i == 0) {
                                stringBuilder.append(URLEncoder.encode(stringArray[i]));
                            } else {
                                stringBuilder.append(URLEncoder.encode("," + stringArray[i]));
                            }
                        }
                        obj3 = obj;
                    }
                } else {
                    if (obj != null) {
                        obj = null;
                    } else {
                        stringBuilder.append("&");
                    }
                    stringBuilder.append(URLEncoder.encode(str) + "=" + URLEncoder.encode(bundle.getString(str)));
                    obj3 = obj;
                }
                obj = obj3;
            }
        }
        return stringBuilder.toString();
    }

    public static String encodePostBody(Bundle bundle, String str) {
        if (bundle == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        int size = bundle.size();
        int i = -1;
        for (String str2 : bundle.keySet()) {
            int i2 = i + 1;
            Object obj = bundle.get(str2);
            if (obj instanceof String) {
                stringBuilder.append("Content-Disposition: form-data; name=\"" + str2 + "\"" + "\r\n" + "\r\n" + ((String) obj));
                if (i2 < size - 1) {
                    stringBuilder.append("\r\n--" + str + "\r\n");
                }
                i = i2;
            } else {
                i = i2;
            }
        }
        return stringBuilder.toString();
    }

    public static c getProxy(Context context) {
        if (context == null) {
            return null;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return null;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return null;
        }
        if (activeNetworkInfo.getType() == 0) {
            Object b = b(context);
            int a = a(context);
            if (!TextUtils.isEmpty(b) && a >= 0) {
                return new c(b, a);
            }
        }
        return null;
    }

    private static int a(Context context) {
        int i = -1;
        if (VERSION.SDK_INT >= 11) {
            Object property = System.getProperty("http.proxyPort");
            if (TextUtils.isEmpty(property)) {
                return i;
            }
            try {
                return Integer.parseInt(property);
            } catch (NumberFormatException e) {
                return i;
            }
        } else if (context == null) {
            return Proxy.getDefaultPort();
        } else {
            i = Proxy.getPort(context);
            if (i < 0) {
                return Proxy.getDefaultPort();
            }
            return i;
        }
    }

    private static String b(Context context) {
        if (VERSION.SDK_INT >= 11) {
            return System.getProperty("http.proxyHost");
        }
        if (context == null) {
            return Proxy.getDefaultHost();
        }
        String host = Proxy.getHost(context);
        if (TextUtils.isEmpty(host)) {
            return Proxy.getDefaultHost();
        }
        return host;
    }
}
