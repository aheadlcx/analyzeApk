package com.qiniu.android.http;

import com.facebook.common.util.UriUtil;
import com.qiniu.android.dns.DnsManager;
import com.qiniu.android.storage.UpCancellationSignal;
import com.qiniu.android.storage.UpToken;
import com.qiniu.android.utils.AsyncRun;
import com.qiniu.android.utils.StringMap;
import com.qiniu.android.utils.StringUtils;
import com.umeng.analytics.pro.b;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Request$Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONObject;
import qsbk.app.thirdparty.net.HttpManager;

public final class Client {
    public static final String ContentTypeHeader = "Content-Type";
    public static final String DefaultMime = "application/octet-stream";
    public static final String FormMime = "application/x-www-form-urlencoded";
    public static final String JsonMime = "application/json";
    private final UrlConverter a;
    private OkHttpClient b;

    private static class a {
        public long duration;
        public String ip;

        private a() {
            this.ip = "";
            this.duration = -1;
        }
    }

    public Client() {
        this(null, 10, 30, null, null);
    }

    public Client(ProxyConfiguration proxyConfiguration, int i, int i2, UrlConverter urlConverter, DnsManager dnsManager) {
        this.a = urlConverter;
        Builder builder = new Builder();
        if (proxyConfiguration != null) {
            builder.proxy(proxyConfiguration.a());
            if (!(proxyConfiguration.user == null || proxyConfiguration.password == null)) {
                builder.proxyAuthenticator(proxyConfiguration.b());
            }
        }
        if (dnsManager != null) {
            builder.dns(new a(this, dnsManager));
        }
        builder.networkInterceptors().add(new b(this));
        builder.connectTimeout((long) i, TimeUnit.SECONDS);
        builder.readTimeout((long) i2, TimeUnit.SECONDS);
        builder.writeTimeout(0, TimeUnit.SECONDS);
        this.b = builder.build();
    }

    private static String a(Response response) {
        String header = response.header("X-Via", "");
        if (header.equals("")) {
            header = response.header("X-Px", "");
            if (header.equals("")) {
                header = response.header("Fw-Via", "");
                if (!header.equals("")) {
                }
            }
        }
        return header;
    }

    private static String b(Response response) {
        MediaType contentType = response.body().contentType();
        if (contentType == null) {
            return "";
        }
        return contentType.type() + MqttTopic.TOPIC_LEVEL_SEPARATOR + contentType.subtype();
    }

    private static JSONObject a(byte[] bArr) throws Exception {
        String str = new String(bArr, "utf-8");
        if (StringUtils.isNullOrEmpty(str)) {
            return new JSONObject();
        }
        return new JSONObject(str);
    }

    private static ResponseInfo a(Response response, String str, long j, UpToken upToken) {
        JSONObject jSONObject;
        String str2;
        Exception exception;
        Exception exception2;
        HttpUrl url;
        int code = response.code();
        String header = response.header("X-Reqid");
        String trim = header == null ? null : header.trim();
        byte[] bArr = null;
        header = null;
        try {
            bArr = response.body().bytes();
        } catch (IOException e) {
            header = e.getMessage();
        }
        JSONObject jSONObject2 = null;
        if (!b(response).equals(JsonMime) || bArr == null) {
            header = bArr == null ? "null body" : new String(bArr);
            jSONObject = jSONObject2;
            str2 = header;
        } else {
            try {
                jSONObject2 = a(bArr);
                try {
                    if (response.code() != 200) {
                        header = jSONObject2.optString(b.J, new String(bArr, "utf-8"));
                    }
                    jSONObject = jSONObject2;
                    str2 = header;
                } catch (Exception e2) {
                    exception = e2;
                    jSONObject = jSONObject2;
                    exception2 = exception;
                    if (response.code() < 300) {
                        header = exception2.getMessage();
                    }
                    str2 = header;
                    url = response.request().url();
                    return ResponseInfo.create(jSONObject, code, trim, response.header("X-Log"), a(response), url.host(), url.encodedPath(), str, url.port(), (double) j, c(response), str2, upToken);
                }
            } catch (Exception e22) {
                exception = e22;
                jSONObject = jSONObject2;
                exception2 = exception;
                if (response.code() < 300) {
                    header = exception2.getMessage();
                }
                str2 = header;
                url = response.request().url();
                return ResponseInfo.create(jSONObject, code, trim, response.header("X-Log"), a(response), url.host(), url.encodedPath(), str, url.port(), (double) j, c(response), str2, upToken);
            }
        }
        url = response.request().url();
        return ResponseInfo.create(jSONObject, code, trim, response.header("X-Log"), a(response), url.host(), url.encodedPath(), str, url.port(), (double) j, c(response), str2, upToken);
    }

    private static long c(Response response) {
        try {
            RequestBody body = response.request().body();
            if (body == null) {
                return 0;
            }
            return body.contentLength();
        } catch (Throwable th) {
            return -1;
        }
    }

    private static void b(Response response, String str, long j, UpToken upToken, CompletionHandler completionHandler) {
        AsyncRun.runInMain(new c(completionHandler, a(response, str, j, upToken)));
    }

    public void asyncSend(Request$Builder request$Builder, StringMap stringMap, UpToken upToken, CompletionHandler completionHandler) {
        if (stringMap != null) {
            stringMap.forEach(new d(this, request$Builder));
        }
        request$Builder.header("User-Agent", UserAgent.instance().getUa(upToken.accessKey));
        a aVar = new a();
        this.b.newCall(request$Builder.tag(aVar).build()).enqueue(new e(this, aVar, upToken, completionHandler));
    }

    public void asyncPost(String str, byte[] bArr, StringMap stringMap, UpToken upToken, ProgressHandler progressHandler, CompletionHandler completionHandler, UpCancellationSignal upCancellationSignal) {
        asyncPost(str, bArr, 0, bArr.length, stringMap, upToken, progressHandler, completionHandler, upCancellationSignal);
    }

    public void asyncPost(String str, byte[] bArr, int i, int i2, StringMap stringMap, UpToken upToken, ProgressHandler progressHandler, CompletionHandler completionHandler, CancellationHandler cancellationHandler) {
        RequestBody create;
        RequestBody countingRequestBody;
        if (this.a != null) {
            str = this.a.convert(str);
        }
        if (bArr == null || bArr.length <= 0) {
            create = RequestBody.create(null, new byte[0]);
        } else {
            create = RequestBody.create(MediaType.parse("application/octet-stream"), bArr, i, i2);
        }
        if (progressHandler != null) {
            countingRequestBody = new CountingRequestBody(create, progressHandler, cancellationHandler);
        } else {
            countingRequestBody = create;
        }
        asyncSend(new Request$Builder().url(str).post(countingRequestBody), stringMap, upToken, completionHandler);
    }

    public void asyncMultipartPost(String str, PostArgs postArgs, UpToken upToken, ProgressHandler progressHandler, CompletionHandler completionHandler, CancellationHandler cancellationHandler) {
        RequestBody create;
        if (postArgs.file != null) {
            create = RequestBody.create(MediaType.parse(postArgs.mimeType), postArgs.file);
        } else {
            create = RequestBody.create(MediaType.parse(postArgs.mimeType), postArgs.data);
        }
        a(str, postArgs.params, upToken, progressHandler, postArgs.fileName, create, completionHandler, cancellationHandler);
    }

    private void a(String str, StringMap stringMap, UpToken upToken, ProgressHandler progressHandler, String str2, RequestBody requestBody, CompletionHandler completionHandler, CancellationHandler cancellationHandler) {
        if (this.a != null) {
            str = this.a.convert(str);
        }
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart(UriUtil.LOCAL_FILE_SCHEME, str2, requestBody);
        stringMap.forEach(new f(this, builder));
        builder.setType(MediaType.parse(HttpManager.MULTIPART_FORM_DATA));
        RequestBody build = builder.build();
        if (!(progressHandler == null && cancellationHandler == null)) {
            build = new CountingRequestBody(build, progressHandler, cancellationHandler);
        }
        asyncSend(new Request$Builder().url(str).post(build), null, upToken, completionHandler);
    }

    public void asyncGet(String str, StringMap stringMap, UpToken upToken, CompletionHandler completionHandler) {
        asyncSend(new Request$Builder().get().url(str), stringMap, upToken, completionHandler);
    }

    public ResponseInfo send(Request$Builder request$Builder, StringMap stringMap, UpToken upToken) {
        if (stringMap != null) {
            stringMap.forEach(new g(this, request$Builder));
        }
        request$Builder.header("User-Agent", UserAgent.instance().getUa(upToken.accessKey));
        System.currentTimeMillis();
        a aVar = new a();
        Request build = request$Builder.tag(aVar).build();
        try {
            return a(this.b.newCall(build).execute(), aVar.ip, aVar.duration, upToken);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseInfo.create(null, -1, "", "", "", build.url().host(), build.url().encodedPath(), aVar.ip, build.url().port(), (double) aVar.duration, -1, e.getMessage(), upToken);
        }
    }

    public ResponseInfo syncMultipartPost(String str, PostArgs postArgs, UpToken upToken) {
        RequestBody create;
        if (postArgs.file != null) {
            create = RequestBody.create(MediaType.parse(postArgs.mimeType), postArgs.file);
        } else {
            create = RequestBody.create(MediaType.parse(postArgs.mimeType), postArgs.data);
        }
        return a(str, postArgs.params, upToken, postArgs.fileName, create);
    }

    private ResponseInfo a(String str, StringMap stringMap, UpToken upToken, String str2, RequestBody requestBody) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart(UriUtil.LOCAL_FILE_SCHEME, str2, requestBody);
        stringMap.forEach(new h(this, builder));
        builder.setType(MediaType.parse(HttpManager.MULTIPART_FORM_DATA));
        return syncSend(new Request$Builder().url(str).post(builder.build()), null, upToken);
    }

    public ResponseInfo syncSend(Request$Builder request$Builder, StringMap stringMap, UpToken upToken) {
        if (stringMap != null) {
            stringMap.forEach(new i(this, request$Builder));
        }
        request$Builder.header("User-Agent", UserAgent.instance().getUa(upToken.accessKey));
        a aVar = new a();
        Request request = null;
        try {
            request = request$Builder.tag(aVar).build();
            return a(this.b.newCall(request).execute(), aVar.ip, aVar.duration, upToken);
        } catch (Exception e) {
            e.printStackTrace();
            int i = -1;
            String message = e.getMessage();
            if (e instanceof UnknownHostException) {
                i = ResponseInfo.UnknownHost;
            } else if (message != null && message.indexOf("Broken pipe") == 0) {
                i = ResponseInfo.NetworkConnectionLost;
            } else if (e instanceof SocketTimeoutException) {
                i = ResponseInfo.TimedOut;
            } else if (e instanceof ConnectException) {
                i = ResponseInfo.CannotConnectToHost;
            }
            HttpUrl url = request.url();
            return ResponseInfo.create(null, i, "", "", "", url.host(), url.encodedPath(), "", url.port(), 0.0d, 0, e.getMessage(), upToken);
        }
    }
}
