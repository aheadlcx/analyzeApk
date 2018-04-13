package qsbk.app.core.net.OkHttp;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache.Entry;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ByteArrayPool;
import com.android.volley.toolbox.PoolingByteArrayOutputStream;
import com.baidu.mobstat.Config;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.client.utils.DateUtils;
import cz.msebera.android.httpclient.entity.BasicHttpEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.message.BasicHttpResponse;
import cz.msebera.android.httpclient.message.BasicStatusLine;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.Headers;
import okhttp3.Headers$Builder;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request$Builder;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpNetwork implements Network {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    protected static final boolean a = VolleyLog.DEBUG;
    private static int c = 3000;
    private static int d = 4096;
    private static final OkHttpClient e = a().connectTimeout(Config.BPLUS_DELAY_TIME, TimeUnit.MILLISECONDS).readTimeout(Config.BPLUS_DELAY_TIME, TimeUnit.MILLISECONDS).writeTimeout(Config.BPLUS_DELAY_TIME, TimeUnit.MILLISECONDS).dns(new OkhttpDns()).build();
    protected final ByteArrayPool b;

    private static Builder a() {
        try {
            TrustManager[] trustManagerArr = new TrustManager[]{new b()};
            SSLContext instance = SSLContext.getInstance("SSL");
            instance.init(null, trustManagerArr, new SecureRandom());
            return new Builder().sslSocketFactory(instance.getSocketFactory(), (X509TrustManager) trustManagerArr[0]).hostnameVerifier(new c());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public OkhttpNetwork() {
        this(new ByteArrayPool(d));
    }

    public OkhttpNetwork(ByteArrayPool byteArrayPool) {
        this.b = byteArrayPool;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.volley.NetworkResponse performRequest(com.android.volley.Request<?> r19) throws com.android.volley.VolleyError {
        /*
        r18 = this;
        r16 = android.os.SystemClock.elapsedRealtime();
    L_0x0004:
        r3 = 0;
        r14 = 0;
        r6 = java.util.Collections.emptyMap();
        r2 = new java.util.HashMap;	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x00dc }
        r2.<init>();	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x00dc }
        r4 = r19.getCacheEntry();	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x00dc }
        r0 = r18;
        r0.a(r2, r4);	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x00dc }
        r0 = r18;
        r1 = r19;
        r15 = r0.a(r1, r2);	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x00dc }
        r12 = r15.getStatusLine();	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x0135 }
        r4 = r12.getStatusCode();	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x0135 }
        r2 = r15.getAllHeaders();	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x0135 }
        r6 = a(r2);	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x0135 }
        r2 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        if (r4 != r2) goto L_0x0063;
    L_0x0034:
        r2 = r19.getCacheEntry();	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x0135 }
        if (r2 != 0) goto L_0x004a;
    L_0x003a:
        r3 = new com.android.volley.NetworkResponse;	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x0135 }
        r4 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        r5 = 0;
        r7 = 1;
        r8 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x0135 }
        r8 = r8 - r16;
        r3.<init>(r4, r5, r6, r7, r8);	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x0135 }
    L_0x0049:
        return r3;
    L_0x004a:
        r3 = r2.responseHeaders;	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x0135 }
        r3.putAll(r6);	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x0135 }
        r7 = new com.android.volley.NetworkResponse;	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x0135 }
        r8 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        r9 = r2.data;	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x0135 }
        r10 = r2.responseHeaders;	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x0135 }
        r11 = 1;
        r2 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x0135 }
        r12 = r2 - r16;
        r7.<init>(r8, r9, r10, r11, r12);	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x0135 }
        r3 = r7;
        goto L_0x0049;
    L_0x0063:
        r2 = r15.getEntity();	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x0135 }
        if (r2 == 0) goto L_0x009d;
    L_0x0069:
        r2 = r15.getEntity();	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x0135 }
        r0 = r18;
        r11 = r0.a(r2);	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x0135 }
    L_0x0073:
        r2 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x0139 }
        r8 = r2 - r16;
        r7 = r18;
        r10 = r19;
        r7.a(r8, r10, r11, r12);	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x0139 }
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r4 < r2) goto L_0x0088;
    L_0x0084:
        r2 = 299; // 0x12b float:4.19E-43 double:1.477E-321;
        if (r4 <= r2) goto L_0x00a1;
    L_0x0088:
        r2 = new java.io.IOException;	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x0139 }
        r2.<init>();	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x0139 }
        throw r2;	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x0139 }
    L_0x008e:
        r2 = move-exception;
        r2 = "socket";
        r3 = new com.android.volley.TimeoutError;
        r3.<init>();
        r0 = r19;
        a(r2, r0, r3);
        goto L_0x0004;
    L_0x009d:
        r2 = 0;
        r11 = new byte[r2];	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x0135 }
        goto L_0x0073;
    L_0x00a1:
        r3 = new com.android.volley.NetworkResponse;	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x0139 }
        r7 = 0;
        r8 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x0139 }
        r8 = r8 - r16;
        r5 = r11;
        r3.<init>(r4, r5, r6, r7, r8);	 Catch:{ SocketTimeoutException -> 0x008e, ConnectTimeoutException -> 0x00af, MalformedURLException -> 0x00be, IOException -> 0x0139 }
        goto L_0x0049;
    L_0x00af:
        r2 = move-exception;
        r2 = "connection";
        r3 = new com.android.volley.TimeoutError;
        r3.<init>();
        r0 = r19;
        a(r2, r0, r3);
        goto L_0x0004;
    L_0x00be:
        r2 = move-exception;
        r3 = new java.lang.RuntimeException;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "Bad URL ";
        r4 = r4.append(r5);
        r5 = r19.getUrl();
        r4 = r4.append(r5);
        r4 = r4.toString();
        r3.<init>(r4, r2);
        throw r3;
    L_0x00dc:
        r2 = move-exception;
        r5 = r14;
    L_0x00de:
        r7 = 0;
        if (r3 == 0) goto L_0x0123;
    L_0x00e1:
        r2 = r3.getStatusLine();
        r4 = r2.getStatusCode();
        r2 = "Unexpected response code %d for %s";
        r3 = 2;
        r3 = new java.lang.Object[r3];
        r8 = 0;
        r9 = java.lang.Integer.valueOf(r4);
        r3[r8] = r9;
        r8 = 1;
        r9 = r19.getUrl();
        r3[r8] = r9;
        com.android.volley.VolleyLog.e(r2, r3);
        if (r5 == 0) goto L_0x012f;
    L_0x0101:
        r3 = new com.android.volley.NetworkResponse;
        r7 = 0;
        r8 = android.os.SystemClock.elapsedRealtime();
        r8 = r8 - r16;
        r3.<init>(r4, r5, r6, r7, r8);
        r2 = 401; // 0x191 float:5.62E-43 double:1.98E-321;
        if (r4 == r2) goto L_0x0115;
    L_0x0111:
        r2 = 403; // 0x193 float:5.65E-43 double:1.99E-321;
        if (r4 != r2) goto L_0x0129;
    L_0x0115:
        r2 = "auth";
        r4 = new com.android.volley.AuthFailureError;
        r4.<init>(r3);
        r0 = r19;
        a(r2, r0, r4);
        goto L_0x0004;
    L_0x0123:
        r3 = new com.android.volley.NoConnectionError;
        r3.<init>(r2);
        throw r3;
    L_0x0129:
        r2 = new com.android.volley.ServerError;
        r2.<init>(r3);
        throw r2;
    L_0x012f:
        r2 = new com.android.volley.NetworkError;
        r2.<init>(r7);
        throw r2;
    L_0x0135:
        r2 = move-exception;
        r5 = r14;
        r3 = r15;
        goto L_0x00de;
    L_0x0139:
        r2 = move-exception;
        r5 = r11;
        r3 = r15;
        goto L_0x00de;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.core.net.OkHttp.OkhttpNetwork.performRequest(com.android.volley.Request):com.android.volley.NetworkResponse");
    }

    private static boolean a(int i, int i2) {
        return (i == 4 || ((100 <= i2 && i2 < 200) || i2 == HttpStatus.SC_NO_CONTENT || i2 == 304)) ? false : true;
    }

    private HttpResponse a(Request<?> request, Map<String, String> map) throws IOException, AuthFailureError {
        Response execute;
        RequestBody requestBody = null;
        Map hashMap = new HashMap();
        hashMap.putAll(request.getHeaders());
        hashMap.putAll(map);
        Headers$Builder headers$Builder = new Headers$Builder();
        for (String str : hashMap.keySet()) {
            headers$Builder.add(str, (String) hashMap.get(str));
        }
        Headers build = headers$Builder.build();
        if (request.getMethod() == 0) {
            execute = e.newCall(new Request$Builder().url(request.getUrl()).headers(build).build()).execute();
        } else if (request.getMethod() == 1) {
            okhttp3.Request build2;
            if (request.getBody() != null) {
                requestBody = RequestBody.create(JSON, request.getBody());
            }
            if (requestBody != null) {
                build2 = new Request$Builder().url(request.getUrl()).post(requestBody).headers(build).build();
            } else {
                build2 = new Request$Builder().url(request.getUrl()).headers(build).build();
            }
            execute = e.newCall(build2).execute();
        } else {
            execute = null;
        }
        HttpResponse basicHttpResponse = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion(HttpVersion.HTTP, 1, 1), execute.code(), execute.message()));
        if (a(request.getMethod(), execute.code())) {
            basicHttpResponse.setEntity(a(execute));
        }
        Headers headers = execute.headers();
        for (int i = 0; i < headers.size(); i++) {
            basicHttpResponse.addHeader(new BasicHeader(headers.name(i), headers.value(i)));
        }
        return basicHttpResponse;
    }

    private void a(long j, Request<?> request, byte[] bArr, StatusLine statusLine) {
        if (a || j > ((long) c)) {
            String str = "HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]";
            Object[] objArr = new Object[5];
            objArr[0] = request;
            objArr[1] = Long.valueOf(j);
            objArr[2] = bArr != null ? Integer.valueOf(bArr.length) : "null";
            objArr[3] = Integer.valueOf(statusLine.getStatusCode());
            objArr[4] = Integer.valueOf(request.getRetryPolicy().getCurrentRetryCount());
            VolleyLog.d(str, objArr);
        }
    }

    private static void a(String str, Request<?> request, VolleyError volleyError) throws VolleyError {
        RetryPolicy retryPolicy = request.getRetryPolicy();
        int timeoutMs = request.getTimeoutMs();
        try {
            retryPolicy.retry(volleyError);
            request.addMarker(String.format("%s-retry [timeout=%s]", new Object[]{str, Integer.valueOf(timeoutMs)}));
        } catch (VolleyError e) {
            request.addMarker(String.format("%s-timeout-giveup [timeout=%s]", new Object[]{str, Integer.valueOf(timeoutMs)}));
            throw e;
        }
    }

    private static HttpEntity a(Response response) {
        HttpEntity basicHttpEntity = new BasicHttpEntity();
        basicHttpEntity.setContent(response.body().byteStream());
        basicHttpEntity.setContentLength(response.body().contentLength());
        basicHttpEntity.setContentEncoding(response.header("Content-Encoding"));
        if (response.body().contentType() != null) {
            basicHttpEntity.setContentType(response.body().contentType().type());
        }
        return basicHttpEntity;
    }

    private void a(Map<String, String> map, Entry entry) {
        if (entry != null) {
            if (entry.etag != null) {
                map.put("If-None-Match", entry.etag);
            }
            if (entry.lastModified > 0) {
                map.put("If-Modified-Since", DateUtils.formatDate(new Date(entry.lastModified)));
            }
        }
    }

    private byte[] a(HttpEntity httpEntity) throws IOException, ServerError {
        InputStream content;
        Throwable th;
        PoolingByteArrayOutputStream poolingByteArrayOutputStream = new PoolingByteArrayOutputStream(this.b, (int) httpEntity.getContentLength());
        try {
            content = httpEntity.getContent();
            if (content == null) {
                try {
                    throw new ServerError();
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        httpEntity.consumeContent();
                    } catch (IOException e) {
                        VolleyLog.v("Error occured when calling consumingContent", new Object[0]);
                    }
                    this.b.returnBuf(null);
                    poolingByteArrayOutputStream.close();
                    if (content != null) {
                        content.close();
                    }
                    throw th;
                }
            }
            byte[] buf = this.b.getBuf(1024);
            while (true) {
                int read = content.read(buf);
                if (read == -1) {
                    break;
                }
                poolingByteArrayOutputStream.write(buf, 0, read);
            }
            byte[] toByteArray = poolingByteArrayOutputStream.toByteArray();
            try {
                httpEntity.consumeContent();
            } catch (IOException e2) {
                VolleyLog.v("Error occured when calling consumingContent", new Object[0]);
            }
            this.b.returnBuf(buf);
            poolingByteArrayOutputStream.close();
            if (content != null) {
                content.close();
            }
            return toByteArray;
        } catch (Throwable th3) {
            th = th3;
            content = null;
            httpEntity.consumeContent();
            this.b.returnBuf(null);
            poolingByteArrayOutputStream.close();
            if (content != null) {
                content.close();
            }
            throw th;
        }
    }

    protected static Map<String, String> a(Header[] headerArr) {
        Map<String, String> treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < headerArr.length; i++) {
            treeMap.put(headerArr[i].getName(), headerArr[i].getValue());
        }
        return treeMap;
    }
}
