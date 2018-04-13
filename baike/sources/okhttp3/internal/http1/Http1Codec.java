package okhttp3.internal.http1;

import android.support.v4.media.session.PlaybackStateCompat;
import com.alipay.sdk.util.h;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.Headers$Builder;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.ResponseBody;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.RealResponseBody;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingTimeout;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class Http1Codec implements HttpCodec {
    final OkHttpClient a;
    final StreamAllocation b;
    final BufferedSource c;
    final BufferedSink d;
    int e = 0;
    private long f = PlaybackStateCompat.ACTION_SET_REPEAT_MODE;

    private abstract class a implements Source {
        protected final ForwardingTimeout a;
        protected boolean b;
        protected long c;
        final /* synthetic */ Http1Codec d;

        private a(Http1Codec http1Codec) {
            this.d = http1Codec;
            this.a = new ForwardingTimeout(this.d.c.timeout());
            this.c = 0;
        }

        public Timeout timeout() {
            return this.a;
        }

        public long read(Buffer buffer, long j) throws IOException {
            try {
                long read = this.d.c.read(buffer, j);
                if (read > 0) {
                    this.c += read;
                }
                return read;
            } catch (IOException e) {
                a(false, e);
                throw e;
            }
        }

        protected final void a(boolean z, IOException iOException) throws IOException {
            if (this.d.e != 6) {
                if (this.d.e != 5) {
                    throw new IllegalStateException("state: " + this.d.e);
                }
                this.d.a(this.a);
                this.d.e = 6;
                if (this.d.b != null) {
                    this.d.b.streamFinished(!z, this.d, this.c, iOException);
                }
            }
        }
    }

    private final class b implements Sink {
        final /* synthetic */ Http1Codec a;
        private final ForwardingTimeout b = new ForwardingTimeout(this.a.d.timeout());
        private boolean c;

        b(Http1Codec http1Codec) {
            this.a = http1Codec;
        }

        public Timeout timeout() {
            return this.b;
        }

        public void write(Buffer buffer, long j) throws IOException {
            if (this.c) {
                throw new IllegalStateException("closed");
            } else if (j != 0) {
                this.a.d.writeHexadecimalUnsignedLong(j);
                this.a.d.writeUtf8("\r\n");
                this.a.d.write(buffer, j);
                this.a.d.writeUtf8("\r\n");
            }
        }

        public synchronized void flush() throws IOException {
            if (!this.c) {
                this.a.d.flush();
            }
        }

        public synchronized void close() throws IOException {
            if (!this.c) {
                this.c = true;
                this.a.d.writeUtf8("0\r\n\r\n");
                this.a.a(this.b);
                this.a.e = 3;
            }
        }
    }

    private class c extends a {
        final /* synthetic */ Http1Codec e;
        private final HttpUrl f;
        private long g = -1;
        private boolean h = true;

        c(Http1Codec http1Codec, HttpUrl httpUrl) {
            this.e = http1Codec;
            super();
            this.f = httpUrl;
        }

        public long read(Buffer buffer, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.b) {
                throw new IllegalStateException("closed");
            } else if (!this.h) {
                return -1;
            } else {
                if (this.g == 0 || this.g == -1) {
                    a();
                    if (!this.h) {
                        return -1;
                    }
                }
                long read = super.read(buffer, Math.min(j, this.g));
                if (read == -1) {
                    IOException protocolException = new ProtocolException("unexpected end of stream");
                    a(false, protocolException);
                    throw protocolException;
                }
                this.g -= read;
                return read;
            }
        }

        private void a() throws IOException {
            if (this.g != -1) {
                this.e.c.readUtf8LineStrict();
            }
            try {
                this.g = this.e.c.readHexadecimalUnsignedLong();
                String trim = this.e.c.readUtf8LineStrict().trim();
                if (this.g < 0 || !(trim.isEmpty() || trim.startsWith(h.b))) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.g + trim + "\"");
                } else if (this.g == 0) {
                    this.h = false;
                    HttpHeaders.receiveHeaders(this.e.a.cookieJar(), this.f, this.e.readHeaders());
                    a(true, null);
                }
            } catch (NumberFormatException e) {
                throw new ProtocolException(e.getMessage());
            }
        }

        public void close() throws IOException {
            if (!this.b) {
                if (this.h && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                    a(false, null);
                }
                this.b = true;
            }
        }
    }

    private final class d implements Sink {
        final /* synthetic */ Http1Codec a;
        private final ForwardingTimeout b = new ForwardingTimeout(this.a.d.timeout());
        private boolean c;
        private long d;

        d(Http1Codec http1Codec, long j) {
            this.a = http1Codec;
            this.d = j;
        }

        public Timeout timeout() {
            return this.b;
        }

        public void write(Buffer buffer, long j) throws IOException {
            if (this.c) {
                throw new IllegalStateException("closed");
            }
            Util.checkOffsetAndCount(buffer.size(), 0, j);
            if (j > this.d) {
                throw new ProtocolException("expected " + this.d + " bytes but received " + j);
            }
            this.a.d.write(buffer, j);
            this.d -= j;
        }

        public void flush() throws IOException {
            if (!this.c) {
                this.a.d.flush();
            }
        }

        public void close() throws IOException {
            if (!this.c) {
                this.c = true;
                if (this.d > 0) {
                    throw new ProtocolException("unexpected end of stream");
                }
                this.a.a(this.b);
                this.a.e = 3;
            }
        }
    }

    private class e extends a {
        final /* synthetic */ Http1Codec e;
        private long f;

        e(Http1Codec http1Codec, long j) throws IOException {
            this.e = http1Codec;
            super();
            this.f = j;
            if (this.f == 0) {
                a(true, null);
            }
        }

        public long read(Buffer buffer, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.b) {
                throw new IllegalStateException("closed");
            } else if (this.f == 0) {
                return -1;
            } else {
                long read = super.read(buffer, Math.min(this.f, j));
                if (read == -1) {
                    IOException protocolException = new ProtocolException("unexpected end of stream");
                    a(false, protocolException);
                    throw protocolException;
                }
                this.f -= read;
                if (this.f == 0) {
                    a(true, null);
                }
                return read;
            }
        }

        public void close() throws IOException {
            if (!this.b) {
                if (!(this.f == 0 || Util.discard(this, 100, TimeUnit.MILLISECONDS))) {
                    a(false, null);
                }
                this.b = true;
            }
        }
    }

    private class f extends a {
        final /* synthetic */ Http1Codec e;
        private boolean f;

        f(Http1Codec http1Codec) {
            this.e = http1Codec;
            super();
        }

        public long read(Buffer buffer, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.b) {
                throw new IllegalStateException("closed");
            } else if (this.f) {
                return -1;
            } else {
                long read = super.read(buffer, j);
                if (read != -1) {
                    return read;
                }
                this.f = true;
                a(true, null);
                return -1;
            }
        }

        public void close() throws IOException {
            if (!this.b) {
                if (!this.f) {
                    a(false, null);
                }
                this.b = true;
            }
        }
    }

    public Http1Codec(OkHttpClient okHttpClient, StreamAllocation streamAllocation, BufferedSource bufferedSource, BufferedSink bufferedSink) {
        this.a = okHttpClient;
        this.b = streamAllocation;
        this.c = bufferedSource;
        this.d = bufferedSink;
    }

    public Sink createRequestBody(Request request, long j) {
        if (HTTP.CHUNK_CODING.equalsIgnoreCase(request.header("Transfer-Encoding"))) {
            return newChunkedSink();
        }
        if (j != -1) {
            return newFixedLengthSink(j);
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    public void cancel() {
        RealConnection connection = this.b.connection();
        if (connection != null) {
            connection.cancel();
        }
    }

    public void writeRequestHeaders(Request request) throws IOException {
        writeRequest(request.headers(), RequestLine.get(request, this.b.connection().route().proxy().type()));
    }

    public ResponseBody openResponseBody(Response response) throws IOException {
        this.b.eventListener.responseBodyStart(this.b.call);
        String header = response.header("Content-Type");
        if (!HttpHeaders.hasBody(response)) {
            return new RealResponseBody(header, 0, Okio.buffer(newFixedLengthSource(0)));
        }
        if (HTTP.CHUNK_CODING.equalsIgnoreCase(response.header("Transfer-Encoding"))) {
            return new RealResponseBody(header, -1, Okio.buffer(newChunkedSource(response.request().url())));
        }
        long contentLength = HttpHeaders.contentLength(response);
        if (contentLength != -1) {
            return new RealResponseBody(header, contentLength, Okio.buffer(newFixedLengthSource(contentLength)));
        }
        return new RealResponseBody(header, -1, Okio.buffer(newUnknownLengthSource()));
    }

    public boolean isClosed() {
        return this.e == 6;
    }

    public void flushRequest() throws IOException {
        this.d.flush();
    }

    public void finishRequest() throws IOException {
        this.d.flush();
    }

    public void writeRequest(Headers headers, String str) throws IOException {
        if (this.e != 0) {
            throw new IllegalStateException("state: " + this.e);
        }
        this.d.writeUtf8(str).writeUtf8("\r\n");
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            this.d.writeUtf8(headers.name(i)).writeUtf8(": ").writeUtf8(headers.value(i)).writeUtf8("\r\n");
        }
        this.d.writeUtf8("\r\n");
        this.e = 1;
    }

    public Builder readResponseHeaders(boolean z) throws IOException {
        if (this.e == 1 || this.e == 3) {
            try {
                StatusLine parse = StatusLine.parse(a());
                Builder headers = new Builder().protocol(parse.protocol).code(parse.code).message(parse.message).headers(readHeaders());
                if (z && parse.code == 100) {
                    return null;
                }
                this.e = 4;
                return headers;
            } catch (Throwable e) {
                IOException iOException = new IOException("unexpected end of stream on " + this.b);
                iOException.initCause(e);
                throw iOException;
            }
        }
        throw new IllegalStateException("state: " + this.e);
    }

    private String a() throws IOException {
        String readUtf8LineStrict = this.c.readUtf8LineStrict(this.f);
        this.f -= (long) readUtf8LineStrict.length();
        return readUtf8LineStrict;
    }

    public Headers readHeaders() throws IOException {
        Headers$Builder headers$Builder = new Headers$Builder();
        while (true) {
            String a = a();
            if (a.length() == 0) {
                return headers$Builder.build();
            }
            Internal.instance.addLenient(headers$Builder, a);
        }
    }

    public Sink newChunkedSink() {
        if (this.e != 1) {
            throw new IllegalStateException("state: " + this.e);
        }
        this.e = 2;
        return new b(this);
    }

    public Sink newFixedLengthSink(long j) {
        if (this.e != 1) {
            throw new IllegalStateException("state: " + this.e);
        }
        this.e = 2;
        return new d(this, j);
    }

    public Source newFixedLengthSource(long j) throws IOException {
        if (this.e != 4) {
            throw new IllegalStateException("state: " + this.e);
        }
        this.e = 5;
        return new e(this, j);
    }

    public Source newChunkedSource(HttpUrl httpUrl) throws IOException {
        if (this.e != 4) {
            throw new IllegalStateException("state: " + this.e);
        }
        this.e = 5;
        return new c(this, httpUrl);
    }

    public Source newUnknownLengthSource() throws IOException {
        if (this.e != 4) {
            throw new IllegalStateException("state: " + this.e);
        } else if (this.b == null) {
            throw new IllegalStateException("streamAllocation == null");
        } else {
            this.e = 5;
            this.b.noNewStreams();
            return new f(this);
        }
    }

    void a(ForwardingTimeout forwardingTimeout) {
        Timeout delegate = forwardingTimeout.delegate();
        forwardingTimeout.setDelegate(Timeout.NONE);
        delegate.clearDeadline();
        delegate.clearTimeout();
    }
}
