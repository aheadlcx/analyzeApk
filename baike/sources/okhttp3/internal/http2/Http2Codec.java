package okhttp3.internal.http2;

import com.alipay.android.phone.mrpc.core.Headers;
import com.alipay.sdk.cons.c;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers$Builder;
import okhttp3.Interceptor$Chain;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.ResponseBody;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.RealResponseBody;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okio.Buffer;
import okio.ByteString;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

public final class Http2Codec implements HttpCodec {
    private static final ByteString b = ByteString.encodeUtf8(Headers.CONN_DIRECTIVE);
    private static final ByteString c = ByteString.encodeUtf8(c.f);
    private static final ByteString d = ByteString.encodeUtf8("keep-alive");
    private static final ByteString e = ByteString.encodeUtf8(Headers.PROXY_CONNECTION);
    private static final ByteString f = ByteString.encodeUtf8(Headers.TRANSFER_ENCODING);
    private static final ByteString g = ByteString.encodeUtf8("te");
    private static final ByteString h = ByteString.encodeUtf8("encoding");
    private static final ByteString i = ByteString.encodeUtf8("upgrade");
    private static final List<ByteString> j = Util.immutableList(new ByteString[]{b, c, d, e, g, f, h, i, Header.TARGET_METHOD, Header.TARGET_PATH, Header.TARGET_SCHEME, Header.TARGET_AUTHORITY});
    private static final List<ByteString> k = Util.immutableList(new ByteString[]{b, c, d, e, g, f, h, i});
    final StreamAllocation a;
    private final OkHttpClient l;
    private final Interceptor$Chain m;
    private final Http2Connection n;
    private Http2Stream o;

    class a extends ForwardingSource {
        boolean a = false;
        long b = 0;
        final /* synthetic */ Http2Codec c;

        a(Http2Codec http2Codec, Source source) {
            this.c = http2Codec;
            super(source);
        }

        public long read(Buffer buffer, long j) throws IOException {
            try {
                long read = delegate().read(buffer, j);
                if (read > 0) {
                    this.b += read;
                }
                return read;
            } catch (IOException e) {
                a(e);
                throw e;
            }
        }

        public void close() throws IOException {
            super.close();
            a(null);
        }

        private void a(IOException iOException) {
            if (!this.a) {
                this.a = true;
                this.c.a.streamFinished(false, this.c, this.b, iOException);
            }
        }
    }

    public Http2Codec(OkHttpClient okHttpClient, Interceptor$Chain interceptor$Chain, StreamAllocation streamAllocation, Http2Connection http2Connection) {
        this.l = okHttpClient;
        this.m = interceptor$Chain;
        this.a = streamAllocation;
        this.n = http2Connection;
    }

    public Sink createRequestBody(Request request, long j) {
        return this.o.getSink();
    }

    public void writeRequestHeaders(Request request) throws IOException {
        if (this.o == null) {
            this.o = this.n.newStream(http2HeadersList(request), request.body() != null);
            this.o.readTimeout().timeout((long) this.m.readTimeoutMillis(), TimeUnit.MILLISECONDS);
            this.o.writeTimeout().timeout((long) this.m.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
        }
    }

    public void flushRequest() throws IOException {
        this.n.flush();
    }

    public void finishRequest() throws IOException {
        this.o.getSink().close();
    }

    public Builder readResponseHeaders(boolean z) throws IOException {
        Builder readHttp2HeadersList = readHttp2HeadersList(this.o.takeResponseHeaders());
        if (z && Internal.instance.code(readHttp2HeadersList) == 100) {
            return null;
        }
        return readHttp2HeadersList;
    }

    public static List<Header> http2HeadersList(Request request) {
        okhttp3.Headers headers = request.headers();
        List<Header> arrayList = new ArrayList(headers.size() + 4);
        arrayList.add(new Header(Header.TARGET_METHOD, request.method()));
        arrayList.add(new Header(Header.TARGET_PATH, RequestLine.requestPath(request.url())));
        String header = request.header("Host");
        if (header != null) {
            arrayList.add(new Header(Header.TARGET_AUTHORITY, header));
        }
        arrayList.add(new Header(Header.TARGET_SCHEME, request.url().scheme()));
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            ByteString encodeUtf8 = ByteString.encodeUtf8(headers.name(i).toLowerCase(Locale.US));
            if (!j.contains(encodeUtf8)) {
                arrayList.add(new Header(encodeUtf8, headers.value(i)));
            }
        }
        return arrayList;
    }

    public static Builder readHttp2HeadersList(List<Header> list) throws IOException {
        Headers$Builder headers$Builder = new Headers$Builder();
        int size = list.size();
        int i = 0;
        StatusLine statusLine = null;
        while (i < size) {
            Headers$Builder headers$Builder2;
            StatusLine statusLine2;
            Header header = (Header) list.get(i);
            if (header == null) {
                if (statusLine != null && statusLine.code == 100) {
                    headers$Builder2 = new Headers$Builder();
                    statusLine2 = null;
                }
                headers$Builder2 = headers$Builder;
                statusLine2 = statusLine;
            } else {
                ByteString byteString = header.name;
                String utf8 = header.value.utf8();
                if (byteString.equals(Header.RESPONSE_STATUS)) {
                    Headers$Builder headers$Builder3 = headers$Builder;
                    statusLine2 = StatusLine.parse("HTTP/1.1 " + utf8);
                    headers$Builder2 = headers$Builder3;
                } else {
                    if (!k.contains(byteString)) {
                        Internal.instance.addLenient(headers$Builder, byteString.utf8(), utf8);
                    }
                    headers$Builder2 = headers$Builder;
                    statusLine2 = statusLine;
                }
            }
            i++;
            statusLine = statusLine2;
            headers$Builder = headers$Builder2;
        }
        if (statusLine != null) {
            return new Builder().protocol(Protocol.HTTP_2).code(statusLine.code).message(statusLine.message).headers(headers$Builder.build());
        }
        throw new ProtocolException("Expected ':status' header not present");
    }

    public ResponseBody openResponseBody(Response response) throws IOException {
        this.a.eventListener.responseBodyStart(this.a.call);
        return new RealResponseBody(response.header("Content-Type"), HttpHeaders.contentLength(response), Okio.buffer(new a(this, this.o.getSource())));
    }

    public void cancel() {
        if (this.o != null) {
            this.o.closeLater(ErrorCode.CANCEL);
        }
    }
}
