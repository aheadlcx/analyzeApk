package com.meizu.cloud.pushsdk.networking.http;

import com.meizu.cloud.pushsdk.networking.common.ANLog;
import com.meizu.cloud.pushsdk.networking.http.Response.Builder;
import com.meizu.cloud.pushsdk.networking.okio.BufferedSink;
import com.meizu.cloud.pushsdk.networking.okio.BufferedSource;
import com.meizu.cloud.pushsdk.networking.okio.Okio;
import com.tencent.connect.common.Constants;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionCall implements Call {
    Request originalRequest;

    public HttpURLConnectionCall(Request request) {
        this.originalRequest = request;
    }

    public Request request() {
        return this.originalRequest;
    }

    public Response execute() throws IOException {
        HttpURLConnection openConnection = openConnection(this.originalRequest);
        for (String str : this.originalRequest.headers().names()) {
            String header = this.originalRequest.header(str);
            ANLog.i("current header name " + str + " value " + header);
            openConnection.addRequestProperty(str, header);
        }
        setConnectionParametersForRequest(openConnection, this.originalRequest);
        int responseCode = openConnection.getResponseCode();
        return new Builder().code(responseCode).headers(this.originalRequest.headers()).message(openConnection.getResponseMessage()).request(this.originalRequest).body(createOkBody(openConnection)).build();
    }

    private static ResponseBody createOkBody(final HttpURLConnection httpURLConnection) throws IOException {
        if (!httpURLConnection.getDoInput()) {
            return null;
        }
        final BufferedSource buffer = Okio.buffer(Okio.source(isSuccessfulSend(httpURLConnection.getResponseCode()) ? httpURLConnection.getInputStream() : httpURLConnection.getErrorStream()));
        return new ResponseBody() {
            public MediaType contentType() {
                String contentType = httpURLConnection.getContentType();
                return contentType == null ? null : MediaType.parse(contentType);
            }

            public long contentLength() {
                return HttpURLConnectionCall.stringToLong(httpURLConnection.getHeaderField("Content-Length"));
            }

            public BufferedSource source() {
                return buffer;
            }
        };
    }

    protected static boolean isSuccessfulSend(int i) {
        return i >= 200 && i < 300;
    }

    private static long stringToLong(String str) {
        long j = -1;
        if (str != null) {
            try {
                j = Long.parseLong(str);
            } catch (NumberFormatException e) {
            }
        }
        return j;
    }

    public void cancel() {
    }

    public boolean isExecuted() {
        return false;
    }

    public boolean isCanceled() {
        return false;
    }

    private HttpURLConnection openConnection(Request request) throws IOException {
        String httpUrl = request.url().toString();
        HttpURLConnection createConnection = createConnection(new URL(httpUrl));
        createConnection.setConnectTimeout(60000);
        createConnection.setReadTimeout(60000);
        createConnection.setUseCaches(false);
        createConnection.setDoInput(true);
        return (!request.isHttps() || httpUrl.startsWith("https://push.statics")) ? createConnection : createConnection;
    }

    protected HttpURLConnection createConnection(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
        return httpURLConnection;
    }

    static void setConnectionParametersForRequest(HttpURLConnection httpURLConnection, Request request) throws IOException {
        switch (request.getmethod()) {
            case 0:
                httpURLConnection.setRequestMethod(Constants.HTTP_GET);
                return;
            case 1:
                httpURLConnection.setRequestMethod(Constants.HTTP_POST);
                addBodyIfExists(httpURLConnection, request);
                return;
            case 2:
                httpURLConnection.setRequestMethod("PUT");
                addBodyIfExists(httpURLConnection, request);
                return;
            case 3:
                httpURLConnection.setRequestMethod("DELETE");
                return;
            case 4:
                httpURLConnection.setRequestMethod("HEAD");
                return;
            case 5:
                httpURLConnection.setRequestMethod("PATCH");
                addBodyIfExists(httpURLConnection, request);
                return;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }

    private static void addBodyIfExists(HttpURLConnection httpURLConnection, Request request) throws IOException {
        RequestBody body = request.body();
        if (body != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("Content-Type", body.contentType().toString());
            BufferedSink buffer = Okio.buffer(Okio.sink(httpURLConnection.getOutputStream()));
            body.writeTo(buffer);
            buffer.close();
        }
    }
}
