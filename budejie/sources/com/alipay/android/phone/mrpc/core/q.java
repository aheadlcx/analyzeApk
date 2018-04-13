package com.alipay.android.phone.mrpc.core;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.ali.auth.third.core.model.Constants;
import com.ali.auth.third.login.LoginConstants;
import com.alipay.sdk.util.h;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.Callable;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.mime.MIME;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public final class q implements Callable<u> {
    private static final HttpRequestRetryHandler e = new ad();
    protected l a;
    protected Context b;
    protected o c;
    String d;
    private HttpUriRequest f;
    private HttpContext g = new BasicHttpContext();
    private CookieStore h = new BasicCookieStore();
    private CookieManager i;
    private AbstractHttpEntity j;
    private HttpHost k;
    private URL l;
    private int m = 0;
    private boolean n = false;
    private boolean o = false;
    private String p = null;
    private String q;

    public q(l lVar, o oVar) {
        this.a = lVar;
        this.b = this.a.a;
        this.c = oVar;
    }

    private static long a(String[] strArr) {
        int i = 0;
        while (i < strArr.length) {
            if ("max-age".equalsIgnoreCase(strArr[i]) && strArr[i + 1] != null) {
                try {
                    return Long.parseLong(strArr[i + 1]);
                } catch (Exception e) {
                }
            }
            i++;
        }
        return 0;
    }

    private static HttpUrlHeader a(HttpResponse httpResponse) {
        HttpUrlHeader httpUrlHeader = new HttpUrlHeader();
        for (Header header : httpResponse.getAllHeaders()) {
            httpUrlHeader.setHead(header.getName(), header.getValue());
        }
        return httpUrlHeader;
    }

    private u a(HttpResponse httpResponse, int i, String str) {
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        String str2 = null;
        new StringBuilder("开始handle，handleResponse-1,").append(Thread.currentThread().getId());
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null && httpResponse.getStatusLine().getStatusCode() == 200) {
            new StringBuilder("200，开始处理，handleResponse-2,threadid = ").append(Thread.currentThread().getId());
            try {
                OutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                try {
                    String str3;
                    long currentTimeMillis = System.currentTimeMillis();
                    a(entity, byteArrayOutputStream2);
                    byte[] toByteArray = byteArrayOutputStream2.toByteArray();
                    this.o = false;
                    this.a.c(System.currentTimeMillis() - currentTimeMillis);
                    this.a.a((long) toByteArray.length);
                    new StringBuilder("res:").append(toByteArray.length);
                    u pVar = new p(a(httpResponse), i, str, toByteArray);
                    currentTimeMillis = b(httpResponse);
                    Header contentType = httpResponse.getEntity().getContentType();
                    if (contentType != null) {
                        HashMap a = a(contentType.getValue());
                        str2 = (String) a.get("charset");
                        str3 = (String) a.get(MIME.CONTENT_TYPE);
                    } else {
                        str3 = null;
                    }
                    pVar.b(str3);
                    pVar.a(str2);
                    pVar.a(System.currentTimeMillis());
                    pVar.b(currentTimeMillis);
                    try {
                        byteArrayOutputStream2.close();
                        return pVar;
                    } catch (IOException e) {
                        throw new RuntimeException("ArrayOutputStream close error!", e.getCause());
                    }
                } catch (Throwable th2) {
                    th = th2;
                    OutputStream outputStream = byteArrayOutputStream2;
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e2) {
                            throw new RuntimeException("ArrayOutputStream close error!", e2.getCause());
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                byteArrayOutputStream = null;
                th = th4;
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw th;
            }
        } else if (entity != null) {
            return null;
        } else {
            httpResponse.getStatusLine().getStatusCode();
            return null;
        }
    }

    private static HashMap<String, String> a(String str) {
        HashMap<String, String> hashMap = new HashMap();
        for (String str2 : str.split(h.b)) {
            String[] split = str2.indexOf(61) == -1 ? new String[]{MIME.CONTENT_TYPE, str2} : str2.split(LoginConstants.EQUAL);
            hashMap.put(split[0], split[1]);
        }
        return hashMap;
    }

    private void a(HttpEntity httpEntity, OutputStream outputStream) {
        Closeable a = b.a(httpEntity);
        long contentLength = httpEntity.getContentLength();
        try {
            byte[] bArr = new byte[2048];
            while (true) {
                int read = a.read(bArr);
                if (read == -1 || this.c.h()) {
                    outputStream.flush();
                } else {
                    outputStream.write(bArr, 0, read);
                    if (this.c.f() != null && contentLength > 0) {
                        this.c.f();
                        o oVar = this.c;
                    }
                }
            }
            outputStream.flush();
            r.a(a);
        } catch (Exception e) {
            e.getCause();
            throw new IOException("HttpWorker Request Error!" + e.getLocalizedMessage());
        } catch (Throwable th) {
            r.a(a);
        }
    }

    private static long b(HttpResponse httpResponse) {
        long j = 0;
        Header firstHeader = httpResponse.getFirstHeader("Cache-Control");
        if (firstHeader != null) {
            String[] split = firstHeader.getValue().split(LoginConstants.EQUAL);
            if (split.length >= 2) {
                try {
                    return a(split);
                } catch (NumberFormatException e) {
                }
            }
        }
        firstHeader = httpResponse.getFirstHeader("Expires");
        return firstHeader != null ? b.b(firstHeader.getValue()) - System.currentTimeMillis() : j;
    }

    private URI b() {
        String a = this.c.a();
        if (this.d != null) {
            a = this.d;
        }
        if (a != null) {
            return new URI(a);
        }
        throw new RuntimeException("url should not be null");
    }

    private HttpUriRequest c() {
        if (this.f != null) {
            return this.f;
        }
        if (this.j == null) {
            byte[] b = this.c.b();
            CharSequence b2 = this.c.b("gzip");
            if (b != null) {
                if (TextUtils.equals(b2, Constants.SERVICE_SCOPE_FLAG_VALUE)) {
                    this.j = b.a(b);
                } else {
                    this.j = new ByteArrayEntity(b);
                }
                this.j.setContentType(this.c.c());
            }
        }
        HttpEntity httpEntity = this.j;
        if (httpEntity != null) {
            HttpUriRequest httpPost = new HttpPost(b());
            httpPost.setEntity(httpEntity);
            this.f = httpPost;
        } else {
            this.f = new HttpGet(b());
        }
        return this.f;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.alipay.android.phone.mrpc.core.u d() {
        /*
        r15 = this;
        r14 = 6;
        r13 = 3;
        r12 = 2;
        r4 = 1;
        r5 = 0;
    L_0x0005:
        r2 = r15.b;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = "connectivity";
        r2 = r2.getSystemService(r3);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = (android.net.ConnectivityManager) r2;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = r2.getAllNetworkInfo();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r3 != 0) goto L_0x0047;
    L_0x0015:
        r2 = r5;
    L_0x0016:
        if (r2 != 0) goto L_0x0060;
    L_0x0018:
        r2 = new com.alipay.android.phone.mrpc.core.HttpException;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = 1;
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = "The network is not available";
        r2.<init>(r3, r6);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        throw r2;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
    L_0x0025:
        r2 = move-exception;
        r15.e();
        r3 = r15.c;
        r3 = r3.f();
        if (r3 == 0) goto L_0x003e;
    L_0x0031:
        r3 = r15.c;
        r3.f();
        r3 = r15.c;
        r2.getCode();
        r2.getMsg();
    L_0x003e:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        throw r2;
    L_0x0047:
        r6 = r3.length;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r5;
    L_0x0049:
        if (r2 >= r6) goto L_0x04ec;
    L_0x004b:
        r7 = r3[r2];	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r7 == 0) goto L_0x005d;
    L_0x004f:
        r8 = r7.isAvailable();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r8 == 0) goto L_0x005d;
    L_0x0055:
        r7 = r7.isConnectedOrConnecting();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r7 == 0) goto L_0x005d;
    L_0x005b:
        r2 = r4;
        goto L_0x0016;
    L_0x005d:
        r2 = r2 + 1;
        goto L_0x0049;
    L_0x0060:
        r2 = r15.c;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r2.f();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r2 == 0) goto L_0x006f;
    L_0x0068:
        r2 = r15.c;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2.f();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r15.c;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
    L_0x006f:
        r2 = r15.c;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r2.d();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r2 == 0) goto L_0x00a2;
    L_0x0077:
        r3 = r2.isEmpty();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r3 != 0) goto L_0x00a2;
    L_0x007d:
        r3 = r2.iterator();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
    L_0x0081:
        r2 = r3.hasNext();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r2 == 0) goto L_0x00a2;
    L_0x0087:
        r2 = r3.next();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = (org.apache.http.Header) r2;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = r15.c();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6.addHeader(r2);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        goto L_0x0081;
    L_0x0095:
        r2 = move-exception;
        r3 = new java.lang.RuntimeException;
        r4 = "Url parser error!";
        r2 = r2.getCause();
        r3.<init>(r4, r2);
        throw r3;
    L_0x00a2:
        r2 = r15.c();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        com.alipay.android.phone.mrpc.core.b.a(r2);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r15.c();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        com.alipay.android.phone.mrpc.core.b.b(r2);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r15.c();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = "cookie";
        r6 = r15.i();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r7 = r15.c;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r7 = r7.a();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = r6.getCookie(r7);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2.addHeader(r3, r6);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r15.g;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = "http.cookie-store";
        r6 = r15.h;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2.setAttribute(r3, r6);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r15.a;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r2.a();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = e;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2.a(r3);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = new java.lang.StringBuilder;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = "By Http/Https to request. operationType=";
        r2.<init>(r3);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = r15.f();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r2.append(r3);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = " url=";
        r2 = r2.append(r3);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = r15.f;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = r3.getURI();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = r3.toString();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2.append(r3);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r15.a;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r2.a();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r8 = r2.getParams();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r9 = "http.route.default-proxy";
        r2 = r15.b;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = 0;
        r10 = "connectivity";
        r2 = r2.getSystemService(r10);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = (android.net.ConnectivityManager) r2;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r2.getActiveNetworkInfo();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r2 == 0) goto L_0x04e9;
    L_0x011e:
        r2 = r2.isAvailable();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r2 == 0) goto L_0x04e9;
    L_0x0124:
        r10 = android.net.Proxy.getDefaultHost();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r11 = android.net.Proxy.getDefaultPort();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r10 == 0) goto L_0x04e9;
    L_0x012e:
        r2 = new org.apache.http.HttpHost;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2.<init>(r10, r11);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
    L_0x0133:
        if (r2 == 0) goto L_0x014a;
    L_0x0135:
        r3 = r2.getHostName();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r10 = "127.0.0.1";
        r3 = android.text.TextUtils.equals(r3, r10);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r3 == 0) goto L_0x014a;
    L_0x0141:
        r3 = r2.getPort();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r10 = 8087; // 0x1f97 float:1.1332E-41 double:3.9955E-320;
        if (r3 != r10) goto L_0x014a;
    L_0x0149:
        r2 = 0;
    L_0x014a:
        r8.setParameter(r9, r2);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r15.k;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r2 == 0) goto L_0x0231;
    L_0x0151:
        r2 = r15.k;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
    L_0x0153:
        r3 = r15.g();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r8 = 80;
        if (r3 != r8) goto L_0x0168;
    L_0x015b:
        r2 = new org.apache.http.HttpHost;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = r15.h();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = r3.getHost();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2.<init>(r3);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
    L_0x0168:
        r3 = r15.a;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = r3.a();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r8 = r15.f;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r9 = r15.g;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = r3.execute(r2, r8, r9);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r8 = java.lang.System.currentTimeMillis();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r15.a;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = r8 - r6;
        r2.b(r6);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r15.h;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r2.getCookies();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = r15.c;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = r6.e();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r6 == 0) goto L_0x0196;
    L_0x018f:
        r6 = r15.i();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6.removeAllCookie();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
    L_0x0196:
        r6 = r2.isEmpty();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r6 != 0) goto L_0x024f;
    L_0x019c:
        r6 = r2.iterator();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
    L_0x01a0:
        r2 = r6.hasNext();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r2 == 0) goto L_0x024f;
    L_0x01a6:
        r2 = r6.next();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = (org.apache.http.cookie.Cookie) r2;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r7 = r2.getDomain();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r7 == 0) goto L_0x01a0;
    L_0x01b2:
        r7 = new java.lang.StringBuilder;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r7.<init>();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r8 = r2.getName();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r7 = r7.append(r8);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r8 = "=";
        r7 = r7.append(r8);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r8 = r2.getValue();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r7 = r7.append(r8);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r8 = "; domain=";
        r7 = r7.append(r8);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r8 = r2.getDomain();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r7 = r7.append(r8);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r2.isSecure();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r2 == 0) goto L_0x024c;
    L_0x01e1:
        r2 = "; Secure";
    L_0x01e3:
        r2 = r7.append(r2);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r2.toString();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r7 = r15.i();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r8 = r15.c;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r8 = r8.a();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r7.setCookie(r8, r2);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = android.webkit.CookieSyncManager.getInstance();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2.sync();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        goto L_0x01a0;
    L_0x0200:
        r2 = move-exception;
        r15.e();
        r3 = r15.c;
        r3 = r3.f();
        if (r3 == 0) goto L_0x021b;
    L_0x020c:
        r3 = r15.c;
        r3.f();
        r3 = r15.c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x021b:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r12);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x0231:
        r2 = r15.h();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = new org.apache.http.HttpHost;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r8 = r2.getHost();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r9 = r15.g();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r2.getProtocol();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3.<init>(r8, r9, r2);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r15.k = r3;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r15.k;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        goto L_0x0153;
    L_0x024c:
        r2 = "";
        goto L_0x01e3;
    L_0x024f:
        r2 = r15.c;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r3.getStatusLine();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = r2.getStatusCode();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r3.getStatusLine();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r7 = r2.getReasonPhrase();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r6 == r2) goto L_0x02b9;
    L_0x0265:
        r2 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        if (r6 != r2) goto L_0x02b7;
    L_0x0269:
        r2 = r4;
    L_0x026a:
        if (r2 != 0) goto L_0x02b9;
    L_0x026c:
        r2 = new com.alipay.android.phone.mrpc.core.HttpException;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = r3.getStatusLine();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = r6.getStatusCode();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = r3.getStatusLine();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = r3.getReasonPhrase();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2.<init>(r6, r3);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        throw r2;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
    L_0x0286:
        r2 = move-exception;
        r15.e();
        r3 = r15.c;
        r3 = r3.f();
        if (r3 == 0) goto L_0x02a1;
    L_0x0292:
        r3 = r15.c;
        r3.f();
        r3 = r15.c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x02a1:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r12);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x02b7:
        r2 = r5;
        goto L_0x026a;
    L_0x02b9:
        r3 = r15.a(r3, r6, r7);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = -1;
        if (r3 == 0) goto L_0x02cd;
    L_0x02c1:
        r2 = r3.b();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r2 == 0) goto L_0x02cd;
    L_0x02c7:
        r2 = r3.b();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r2.length;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = (long) r2;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
    L_0x02cd:
        r8 = -1;
        r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r2 != 0) goto L_0x02e8;
    L_0x02d3:
        r2 = r3 instanceof com.alipay.android.phone.mrpc.core.p;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r2 == 0) goto L_0x02e8;
    L_0x02d7:
        r0 = r3;
        r0 = (com.alipay.android.phone.mrpc.core.p) r0;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r0;
        r2 = r2.a();	 Catch:{ Exception -> 0x04e6, HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497 }
        r6 = "Content-Length";
        r2 = r2.getHead(r6);	 Catch:{ Exception -> 0x04e6, HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497 }
        java.lang.Long.parseLong(r2);	 Catch:{ Exception -> 0x04e6, HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497 }
    L_0x02e8:
        r2 = r15.c;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r2.a();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r2 == 0) goto L_0x0310;
    L_0x02f0:
        r6 = r15.f();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = android.text.TextUtils.isEmpty(r6);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r6 != 0) goto L_0x0310;
    L_0x02fa:
        r6 = new java.lang.StringBuilder;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6.<init>();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r6.append(r2);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = "#";
        r2 = r2.append(r6);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = r15.f();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2.append(r6);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
    L_0x0310:
        return r3;
    L_0x0311:
        r2 = move-exception;
        r15.e();
        r3 = r15.c;
        r3 = r3.f();
        if (r3 == 0) goto L_0x032c;
    L_0x031d:
        r3 = r15.c;
        r3.f();
        r3 = r15.c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x032c:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r14);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x0342:
        r2 = move-exception;
        r15.e();
        r3 = r15.c;
        r3 = r3.f();
        if (r3 == 0) goto L_0x035d;
    L_0x034e:
        r3 = r15.c;
        r3.f();
        r3 = r15.c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x035d:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r13);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x0373:
        r2 = move-exception;
        r15.e();
        r3 = r15.c;
        r3 = r3.f();
        if (r3 == 0) goto L_0x038e;
    L_0x037f:
        r3 = r15.c;
        r3.f();
        r3 = r15.c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x038e:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r13);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x03a4:
        r2 = move-exception;
        r15.e();
        r3 = r15.c;
        r3 = r3.f();
        if (r3 == 0) goto L_0x03bf;
    L_0x03b0:
        r3 = r15.c;
        r3.f();
        r3 = r15.c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x03bf:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = 4;
        r4 = java.lang.Integer.valueOf(r4);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x03d6:
        r2 = move-exception;
        r15.e();
        r3 = r15.c;
        r3 = r3.f();
        if (r3 == 0) goto L_0x03f1;
    L_0x03e2:
        r3 = r15.c;
        r3.f();
        r3 = r15.c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x03f1:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = 5;
        r4 = java.lang.Integer.valueOf(r4);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x0408:
        r2 = move-exception;
        r15.e();
        r3 = r15.c;
        r3 = r3.f();
        if (r3 == 0) goto L_0x0423;
    L_0x0414:
        r3 = r15.c;
        r3.f();
        r3 = r15.c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x0423:
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = 8;
        r4 = java.lang.Integer.valueOf(r4);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x0433:
        r2 = move-exception;
        r15.e();
        r3 = r15.c;
        r3 = r3.f();
        if (r3 == 0) goto L_0x044e;
    L_0x043f:
        r3 = r15.c;
        r3.f();
        r3 = r15.c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x044e:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = 9;
        r4 = java.lang.Integer.valueOf(r4);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x0466:
        r2 = move-exception;
        r15.e();
        r3 = r15.c;
        r3 = r3.f();
        if (r3 == 0) goto L_0x0481;
    L_0x0472:
        r3 = r15.c;
        r3.f();
        r3 = r15.c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x0481:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r14);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x0497:
        r2 = move-exception;
        r15.e();
        r3 = r15.m;
        if (r3 > 0) goto L_0x04a7;
    L_0x049f:
        r2 = r15.m;
        r2 = r2 + 1;
        r15.m = r2;
        goto L_0x0005;
    L_0x04a7:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r5);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x04bd:
        r2 = move-exception;
        r15.e();
        r3 = r15.c;
        r3 = r3.f();
        if (r3 == 0) goto L_0x04d8;
    L_0x04c9:
        r3 = r15.c;
        r3.f();
        r3 = r15.c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x04d8:
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r5);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x04e6:
        r2 = move-exception;
        goto L_0x02e8;
    L_0x04e9:
        r2 = r3;
        goto L_0x0133;
    L_0x04ec:
        r2 = r5;
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.android.phone.mrpc.core.q.d():com.alipay.android.phone.mrpc.core.u");
    }

    private void e() {
        if (this.f != null) {
            this.f.abort();
        }
    }

    private String f() {
        if (!TextUtils.isEmpty(this.q)) {
            return this.q;
        }
        this.q = this.c.b("operationType");
        return this.q;
    }

    private int g() {
        URL h = h();
        return h.getPort() == -1 ? h.getDefaultPort() : h.getPort();
    }

    private URL h() {
        if (this.l != null) {
            return this.l;
        }
        this.l = new URL(this.c.a());
        return this.l;
    }

    private CookieManager i() {
        if (this.i != null) {
            return this.i;
        }
        this.i = CookieManager.getInstance();
        return this.i;
    }

    public final o a() {
        return this.c;
    }

    public final /* synthetic */ Object call() {
        return d();
    }
}
