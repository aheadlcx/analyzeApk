package okhttp3.internal.http;

import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.io.IOException;
import java.net.ProtocolException;
import okhttp3.Interceptor;
import okhttp3.Interceptor$Chain;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.StreamAllocation;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

public final class CallServerInterceptor implements Interceptor {
    private final boolean a;

    static final class a extends ForwardingSink {
        long a;

        a(Sink sink) {
            super(sink);
        }

        public void write(Buffer buffer, long j) throws IOException {
            super.write(buffer, j);
            this.a += j;
        }
    }

    public CallServerInterceptor(boolean z) {
        this.a = z;
    }

    public Response intercept(Interceptor$Chain interceptor$Chain) throws IOException {
        Builder builder;
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) interceptor$Chain;
        HttpCodec httpStream = realInterceptorChain.httpStream();
        StreamAllocation streamAllocation = realInterceptorChain.streamAllocation();
        RealConnection realConnection = (RealConnection) realInterceptorChain.connection();
        Request request = realInterceptorChain.request();
        long currentTimeMillis = System.currentTimeMillis();
        realInterceptorChain.eventListener().requestHeadersStart(realInterceptorChain.call());
        httpStream.writeRequestHeaders(request);
        realInterceptorChain.eventListener().requestHeadersEnd(realInterceptorChain.call(), request);
        Builder builder2 = null;
        if (!HttpMethod.permitsRequestBody(request.method()) || request.body() == null) {
            builder = null;
        } else {
            if (HTTP.EXPECT_CONTINUE.equalsIgnoreCase(request.header("Expect"))) {
                httpStream.flushRequest();
                realInterceptorChain.eventListener().responseHeadersStart(realInterceptorChain.call());
                builder2 = httpStream.readResponseHeaders(true);
            }
            if (builder2 == null) {
                realInterceptorChain.eventListener().requestBodyStart(realInterceptorChain.call());
                Sink aVar = new a(httpStream.createRequestBody(request, request.body().contentLength()));
                BufferedSink buffer = Okio.buffer(aVar);
                request.body().writeTo(buffer);
                buffer.close();
                realInterceptorChain.eventListener().requestBodyEnd(realInterceptorChain.call(), aVar.a);
                builder = builder2;
            } else {
                if (!realConnection.isMultiplexed()) {
                    streamAllocation.noNewStreams();
                }
                builder = builder2;
            }
        }
        httpStream.finishRequest();
        if (builder == null) {
            realInterceptorChain.eventListener().responseHeadersStart(realInterceptorChain.call());
            builder = httpStream.readResponseHeaders(false);
        }
        Response build = builder.request(request).handshake(streamAllocation.connection().handshake()).sentRequestAtMillis(currentTimeMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
        realInterceptorChain.eventListener().responseHeadersEnd(realInterceptorChain.call(), build);
        int code = build.code();
        if (this.a && code == 101) {
            build = build.newBuilder().body(Util.EMPTY_RESPONSE).build();
        } else {
            build = build.newBuilder().body(httpStream.openResponseBody(build)).build();
        }
        if ("close".equalsIgnoreCase(build.request().header("Connection")) || "close".equalsIgnoreCase(build.header("Connection"))) {
            streamAllocation.noNewStreams();
        }
        if ((code != HttpStatus.SC_NO_CONTENT && code != HttpStatus.SC_RESET_CONTENT) || build.body().contentLength() <= 0) {
            return build;
        }
        throw new ProtocolException("HTTP " + code + " had non-zero Content-Length: " + build.body().contentLength());
    }
}
