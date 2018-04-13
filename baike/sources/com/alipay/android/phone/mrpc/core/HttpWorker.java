package com.alipay.android.phone.mrpc.core;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.alipay.sdk.util.h;
import cz.msebera.android.httpclient.conn.params.ConnRoutePNames;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class HttpWorker implements Callable<Response> {
    private static final HttpRequestRetryHandler e = new ZHttpRequestRetryHandler();
    protected HttpManager a;
    protected Context b;
    protected HttpUrlRequest c;
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

    public HttpWorker(HttpManager httpManager, HttpUrlRequest httpUrlRequest) {
        this.a = httpManager;
        this.b = this.a.a;
        this.c = httpUrlRequest;
    }

    private HttpUriRequest d() {
        if (this.f != null) {
            return this.f;
        }
        HttpEntity b = b();
        if (b != null) {
            HttpUriRequest httpPost = new HttpPost(a());
            httpPost.setEntity(b);
            this.f = httpPost;
        } else {
            this.f = new HttpGet(a());
        }
        return this.f;
    }

    private void e() {
        if (this.f != null) {
            this.f.abort();
        }
    }

    private TransportCallback f() {
        return this.c.getCallback();
    }

    private HttpResponse g() {
        return h();
    }

    private HttpResponse h() {
        new StringBuilder("By Http/Https to request. operationType=").append(i()).append(" url=").append(this.f.getURI().toString());
        j().getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, o());
        HttpHost l = l();
        if (m() == 80) {
            l = new HttpHost(n().getHost());
        }
        return j().execute(l, this.f, this.g);
    }

    private String i() {
        if (!TextUtils.isEmpty(this.q)) {
            return this.q;
        }
        this.q = this.c.getTag("operationType");
        return this.q;
    }

    private AndroidHttpClient j() {
        return this.a.getHttpClient();
    }

    private void k() {
        ArrayList c = c();
        if (!(c == null || c.isEmpty())) {
            Iterator it = c.iterator();
            while (it.hasNext()) {
                d().addHeader((Header) it.next());
            }
        }
        AndroidHttpClient.modifyRequestToAcceptGzipResponse(d());
        AndroidHttpClient.modifyRequestToKeepAlive(d());
        d().addHeader("cookie", p().getCookie(this.c.getUrl()));
    }

    private HttpHost l() {
        if (this.k != null) {
            return this.k;
        }
        URL n = n();
        this.k = new HttpHost(n.getHost(), m(), n.getProtocol());
        return this.k;
    }

    private int m() {
        URL n = n();
        return n.getPort() == -1 ? n.getDefaultPort() : n.getPort();
    }

    private URL n() {
        if (this.l != null) {
            return this.l;
        }
        this.l = new URL(this.c.getUrl());
        return this.l;
    }

    private HttpHost o() {
        HttpHost proxy = NetworkUtils.getProxy(this.b);
        return (proxy != null && TextUtils.equals(proxy.getHostName(), "127.0.0.1") && proxy.getPort() == 8087) ? null : proxy;
    }

    private CookieManager p() {
        if (this.i != null) {
            return this.i;
        }
        this.i = CookieManager.getInstance();
        return this.i;
    }

    protected long a(String[] strArr) {
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

    protected HttpUrlHeader a(HttpResponse httpResponse) {
        HttpUrlHeader httpUrlHeader = new HttpUrlHeader();
        for (Header header : httpResponse.getAllHeaders()) {
            httpUrlHeader.setHead(header.getName(), header.getValue());
        }
        return httpUrlHeader;
    }

    protected Response a(HttpResponse httpResponse, int i, String str) {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        Response response = null;
        new StringBuilder("开始handle，handleResponse-1,").append(Thread.currentThread().getId());
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null && httpResponse.getStatusLine().getStatusCode() == 200) {
            new StringBuilder("200，开始处理，handleResponse-2,threadid = ").append(Thread.currentThread().getId());
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    long currentTimeMillis = System.currentTimeMillis();
                    a(entity, 0, (OutputStream) byteArrayOutputStream);
                    byte[] toByteArray = byteArrayOutputStream.toByteArray();
                    this.o = false;
                    this.a.addSocketTime(System.currentTimeMillis() - currentTimeMillis);
                    this.a.addDataSize((long) toByteArray.length);
                    new StringBuilder("res:").append(toByteArray.length);
                    response = new HttpUrlResponse(a(httpResponse), i, str, toByteArray);
                    a((HttpUrlResponse) response, httpResponse);
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e) {
                        throw new RuntimeException("ArrayOutputStream close error!", e.getCause());
                    }
                } catch (Throwable th2) {
                    th = th2;
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
        } else if (entity == null) {
            httpResponse.getStatusLine().getStatusCode();
        }
        return response;
    }

    protected URI a() {
        String url = this.c.getUrl();
        if (this.d != null) {
            url = this.d;
        }
        if (url != null) {
            return new URI(url);
        }
        throw new RuntimeException("url should not be null");
    }

    protected HashMap<String, String> a(String str) {
        HashMap<String, String> hashMap = new HashMap();
        for (String str2 : str.split(h.b)) {
            String[] split = str2.indexOf(61) == -1 ? new String[]{"Content-Type", str2} : str2.split("=");
            hashMap.put(split[0], split[1]);
        }
        return hashMap;
    }

    protected void a(HttpUrlResponse httpUrlResponse, HttpResponse httpResponse) {
        String str;
        String str2 = null;
        long b = b(httpResponse);
        Header contentType = httpResponse.getEntity().getContentType();
        if (contentType != null) {
            HashMap a = a(contentType.getValue());
            str = (String) a.get("charset");
            str2 = (String) a.get("Content-Type");
        } else {
            str = null;
        }
        httpUrlResponse.setContentType(str2);
        httpUrlResponse.setCharset(str);
        httpUrlResponse.setCreateTime(System.currentTimeMillis());
        httpUrlResponse.setPeriod(b);
    }

    protected void a(HttpEntity httpEntity, long j, OutputStream outputStream) {
        if (outputStream == null) {
            httpEntity.consumeContent();
            throw new IllegalArgumentException("Output stream may not be null");
        }
        Closeable ungzippedContent = AndroidHttpClient.getUngzippedContent(httpEntity);
        long contentLength = httpEntity.getContentLength();
        try {
            byte[] bArr = new byte[2048];
            while (true) {
                int read = ungzippedContent.read(bArr);
                if (read == -1 || this.c.isCanceled()) {
                    break;
                }
                outputStream.write(bArr, 0, read);
                j += (long) read;
                if (f() != null && contentLength > 0) {
                    f().onProgressUpdate(this.c, ((double) j) / ((double) contentLength));
                }
            }
            outputStream.flush();
            IOUtil.closeStream(ungzippedContent);
        } catch (Exception e) {
            e.getCause();
            throw new IOException("HttpWorker Request Error!" + e.getLocalizedMessage());
        } catch (Throwable th) {
            IOUtil.closeStream(ungzippedContent);
        }
    }

    protected boolean a(int i, String str) {
        return i == 304;
    }

    protected long b(HttpResponse httpResponse) {
        long j = 0;
        Header firstHeader = httpResponse.getFirstHeader("Cache-Control");
        if (firstHeader != null) {
            String[] split = firstHeader.getValue().split("=");
            if (split.length >= 2) {
                try {
                    return a(split);
                } catch (NumberFormatException e) {
                }
            }
        }
        firstHeader = httpResponse.getFirstHeader("Expires");
        return firstHeader != null ? AndroidHttpClient.parseDate(firstHeader.getValue()) - System.currentTimeMillis() : j;
    }

    protected AbstractHttpEntity b() {
        if (this.j != null) {
            return this.j;
        }
        byte[] reqData = this.c.getReqData();
        CharSequence tag = this.c.getTag("gzip");
        if (reqData != null) {
            if (TextUtils.equals(tag, "true")) {
                this.j = AndroidHttpClient.getCompressedEntity(reqData, null);
            } else {
                this.j = new ByteArrayEntity(reqData);
            }
            this.j.setContentType(this.c.getContentType());
        }
        return this.j;
    }

    protected ArrayList<Header> c() {
        return this.c.getHeaders();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.alipay.android.phone.mrpc.core.Response call() {
        /*
        r13 = this;
        r12 = 4;
        r11 = 0;
        r10 = 6;
        r9 = 3;
        r8 = 2;
        r2 = r13.b;	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r2 = com.alipay.android.phone.mrpc.core.NetworkUtils.isNetworkAvailable(r2);	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        if (r2 != 0) goto L_0x003e;
    L_0x000d:
        r2 = new com.alipay.android.phone.mrpc.core.HttpException;	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r3 = 1;
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r4 = "The network is not available";
        r2.<init>(r3, r4);	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        throw r2;	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
    L_0x001a:
        r2 = move-exception;
        r13.e();
        r3 = r13.f();
        if (r3 == 0) goto L_0x0035;
    L_0x0024:
        r3 = r13.f();
        r4 = r13.c;
        r5 = r2.getCode();
        r6 = r2.getMsg();
        r3.onFailed(r4, r5, r6);
    L_0x0035:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        throw r2;
    L_0x003e:
        r2 = r13.f();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        if (r2 == 0) goto L_0x004d;
    L_0x0044:
        r2 = r13.f();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r3 = r13.c;	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r2.onPreExecute(r3);	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
    L_0x004d:
        r13.k();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r2 = r13.g;	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r3 = "http.cookie-store";
        r4 = r13.h;	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r2.setAttribute(r3, r4);	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r2 = r13.j();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r3 = e;	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r2.setHttpRequestRetryHandler(r3);	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r4 = r13.g();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r5 = r13.a;	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r2 = r6 - r2;
        r5.addConnectTime(r2);	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r2 = r13.h;	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r2 = r2.getCookies();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r3 = r13.c;	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r3 = r3.isResetCookie();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        if (r3 == 0) goto L_0x008a;
    L_0x0083:
        r3 = r13.p();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r3.removeAllCookie();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
    L_0x008a:
        r3 = r2.isEmpty();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        if (r3 != 0) goto L_0x0104;
    L_0x0090:
        r3 = r2.iterator();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
    L_0x0094:
        r2 = r3.hasNext();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        if (r2 == 0) goto L_0x0104;
    L_0x009a:
        r2 = r3.next();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r2 = (org.apache.http.cookie.Cookie) r2;	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r5 = r2.getDomain();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        if (r5 == 0) goto L_0x0094;
    L_0x00a6:
        r5 = new java.lang.StringBuilder;	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r5.<init>();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r6 = r2.getName();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r5 = r5.append(r6);	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r6 = "=";
        r5 = r5.append(r6);	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r6 = r2.getValue();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r5 = r5.append(r6);	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r6 = "; domain=";
        r5 = r5.append(r6);	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r6 = r2.getDomain();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r5 = r5.append(r6);	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r2 = r2.isSecure();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        if (r2 == 0) goto L_0x0101;
    L_0x00d5:
        r2 = "; Secure";
    L_0x00d7:
        r2 = r5.append(r2);	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r2 = r2.toString();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r5 = r13.p();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r6 = r13.c;	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r6 = r6.getUrl();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r5.setCookie(r6, r2);	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r2 = android.webkit.CookieSyncManager.getInstance();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r2.sync();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        goto L_0x0094;
    L_0x00f4:
        r2 = move-exception;
        r3 = new java.lang.RuntimeException;
        r4 = "Url parser error!";
        r2 = r2.getCause();
        r3.<init>(r4, r2);
        throw r3;
    L_0x0101:
        r2 = "";
        goto L_0x00d7;
    L_0x0104:
        r2 = r13.c;	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r3 = r13.processResponse(r4, r2);	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r4 = -1;
        if (r3 == 0) goto L_0x011a;
    L_0x010e:
        r2 = r3.getResData();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        if (r2 == 0) goto L_0x011a;
    L_0x0114:
        r2 = r3.getResData();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r2 = r2.length;	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r4 = (long) r2;	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
    L_0x011a:
        r6 = -1;
        r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r2 != 0) goto L_0x0135;
    L_0x0120:
        r2 = r3 instanceof com.alipay.android.phone.mrpc.core.HttpUrlResponse;	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        if (r2 == 0) goto L_0x0135;
    L_0x0124:
        r0 = r3;
        r0 = (com.alipay.android.phone.mrpc.core.HttpUrlResponse) r0;	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r2 = r0;
        r2 = r2.getHeader();	 Catch:{ Exception -> 0x0371, HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322 }
        r4 = "Content-Length";
        r2 = r2.getHead(r4);	 Catch:{ Exception -> 0x0371, HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322 }
        java.lang.Long.parseLong(r2);	 Catch:{ Exception -> 0x0371, HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322 }
    L_0x0135:
        r2 = r13.c;	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r2 = r2.getUrl();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        if (r2 == 0) goto L_0x015d;
    L_0x013d:
        r4 = r13.i();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r4 = android.text.TextUtils.isEmpty(r4);	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        if (r4 != 0) goto L_0x015d;
    L_0x0147:
        r4 = new java.lang.StringBuilder;	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r4.<init>();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r2 = r4.append(r2);	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r4 = "#";
        r2 = r2.append(r4);	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r4 = r13.i();	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
        r2.append(r4);	 Catch:{ HttpException -> 0x001a, URISyntaxException -> 0x00f4, SSLHandshakeException -> 0x015e, SSLPeerUnverifiedException -> 0x018b, SSLException -> 0x01b8, ConnectionPoolTimeoutException -> 0x01e5, ConnectTimeoutException -> 0x0212, SocketTimeoutException -> 0x023f, NoHttpResponseException -> 0x026c, HttpHostConnectException -> 0x029b, UnknownHostException -> 0x02c4, IOException -> 0x02f5, NullPointerException -> 0x0322, Exception -> 0x034c }
    L_0x015d:
        return r3;
    L_0x015e:
        r2 = move-exception;
        r13.e();
        r3 = r13.f();
        if (r3 == 0) goto L_0x0175;
    L_0x0168:
        r3 = r13.f();
        r4 = r13.c;
        r5 = java.lang.String.valueOf(r2);
        r3.onFailed(r4, r8, r5);
    L_0x0175:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r8);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x018b:
        r2 = move-exception;
        r13.e();
        r3 = r13.f();
        if (r3 == 0) goto L_0x01a2;
    L_0x0195:
        r3 = r13.f();
        r4 = r13.c;
        r5 = java.lang.String.valueOf(r2);
        r3.onFailed(r4, r8, r5);
    L_0x01a2:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r8);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x01b8:
        r2 = move-exception;
        r13.e();
        r3 = r13.f();
        if (r3 == 0) goto L_0x01cf;
    L_0x01c2:
        r3 = r13.f();
        r4 = r13.c;
        r5 = java.lang.String.valueOf(r2);
        r3.onFailed(r4, r10, r5);
    L_0x01cf:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r10);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x01e5:
        r2 = move-exception;
        r13.e();
        r3 = r13.f();
        if (r3 == 0) goto L_0x01fc;
    L_0x01ef:
        r3 = r13.f();
        r4 = r13.c;
        r5 = java.lang.String.valueOf(r2);
        r3.onFailed(r4, r9, r5);
    L_0x01fc:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r9);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x0212:
        r2 = move-exception;
        r13.e();
        r3 = r13.f();
        if (r3 == 0) goto L_0x0229;
    L_0x021c:
        r3 = r13.f();
        r4 = r13.c;
        r5 = java.lang.String.valueOf(r2);
        r3.onFailed(r4, r9, r5);
    L_0x0229:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r9);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x023f:
        r2 = move-exception;
        r13.e();
        r3 = r13.f();
        if (r3 == 0) goto L_0x0256;
    L_0x0249:
        r3 = r13.f();
        r4 = r13.c;
        r5 = java.lang.String.valueOf(r2);
        r3.onFailed(r4, r12, r5);
    L_0x0256:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r12);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x026c:
        r2 = move-exception;
        r13.e();
        r3 = r13.f();
        if (r3 == 0) goto L_0x0284;
    L_0x0276:
        r3 = r13.f();
        r4 = r13.c;
        r5 = 5;
        r6 = java.lang.String.valueOf(r2);
        r3.onFailed(r4, r5, r6);
    L_0x0284:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = 5;
        r4 = java.lang.Integer.valueOf(r4);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x029b:
        r2 = move-exception;
        r13.e();
        r3 = r13.f();
        if (r3 == 0) goto L_0x02b4;
    L_0x02a5:
        r3 = r13.f();
        r4 = r13.c;
        r5 = 8;
        r6 = java.lang.String.valueOf(r2);
        r3.onFailed(r4, r5, r6);
    L_0x02b4:
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = 8;
        r4 = java.lang.Integer.valueOf(r4);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x02c4:
        r2 = move-exception;
        r13.e();
        r3 = r13.f();
        if (r3 == 0) goto L_0x02dd;
    L_0x02ce:
        r3 = r13.f();
        r4 = r13.c;
        r5 = 9;
        r6 = java.lang.String.valueOf(r2);
        r3.onFailed(r4, r5, r6);
    L_0x02dd:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = 9;
        r4 = java.lang.Integer.valueOf(r4);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x02f5:
        r2 = move-exception;
        r13.e();
        r3 = r13.f();
        if (r3 == 0) goto L_0x030c;
    L_0x02ff:
        r3 = r13.f();
        r4 = r13.c;
        r5 = java.lang.String.valueOf(r2);
        r3.onFailed(r4, r10, r5);
    L_0x030c:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r10);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x0322:
        r2 = move-exception;
        r13.e();
        r3 = r13.m;
        if (r3 > 0) goto L_0x0336;
    L_0x032a:
        r2 = r13.m;
        r2 = r2 + 1;
        r13.m = r2;
        r3 = r13.call();
        goto L_0x015d;
    L_0x0336:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r11);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x034c:
        r2 = move-exception;
        r13.e();
        r3 = r13.f();
        if (r3 == 0) goto L_0x0363;
    L_0x0356:
        r3 = r13.f();
        r4 = r13.c;
        r5 = java.lang.String.valueOf(r2);
        r3.onFailed(r4, r11, r5);
    L_0x0363:
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r11);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x0371:
        r2 = move-exception;
        goto L_0x0135;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.android.phone.mrpc.core.HttpWorker.call():com.alipay.android.phone.mrpc.core.Response");
    }

    public HttpUrlRequest getRequest() {
        return this.c;
    }

    public Response processResponse(HttpResponse httpResponse, HttpUrlRequest httpUrlRequest) {
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        String reasonPhrase = httpResponse.getStatusLine().getReasonPhrase();
        if (statusCode == 200 || a(statusCode, reasonPhrase)) {
            return a(httpResponse, statusCode, reasonPhrase);
        }
        throw new HttpException(Integer.valueOf(httpResponse.getStatusLine().getStatusCode()), httpResponse.getStatusLine().getReasonPhrase());
    }
}
