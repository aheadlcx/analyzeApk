package okhttp3;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import okhttp3.Response.Builder;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheRequest;
import okhttp3.internal.cache.CacheStrategy;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.internal.cache.DiskLruCache$Editor;
import okhttp3.internal.cache.DiskLruCache.Snapshot;
import okhttp3.internal.cache.InternalCache;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.StatusLine;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Source;

public final class Cache implements Closeable, Flushable {
    final InternalCache a;
    final DiskLruCache b;
    int c;
    int d;
    private int e;
    private int f;
    private int g;

    private static class b extends ResponseBody {
        final Snapshot a;
        private final BufferedSource b;
        @Nullable
        private final String c;
        @Nullable
        private final String d;

        b(Snapshot snapshot, String str, String str2) {
            this.a = snapshot;
            this.c = str;
            this.d = str2;
            this.b = Okio.buffer(new e(this, snapshot.getSource(1), snapshot));
        }

        public MediaType contentType() {
            return this.c != null ? MediaType.parse(this.c) : null;
        }

        public long contentLength() {
            long j = -1;
            try {
                if (this.d != null) {
                    j = Long.parseLong(this.d);
                }
            } catch (NumberFormatException e) {
            }
            return j;
        }

        public BufferedSource source() {
            return this.b;
        }
    }

    private static final class c {
        private static final String a = (Platform.get().getPrefix() + "-Sent-Millis");
        private static final String b = (Platform.get().getPrefix() + "-Received-Millis");
        private final String c;
        private final Headers d;
        private final String e;
        private final Protocol f;
        private final int g;
        private final String h;
        private final Headers i;
        @Nullable
        private final Handshake j;
        private final long k;
        private final long l;

        c(Source source) throws IOException {
            long j = 0;
            int i = 0;
            try {
                int i2;
                long parseLong;
                BufferedSource buffer = Okio.buffer(source);
                this.c = buffer.readUtf8LineStrict();
                this.e = buffer.readUtf8LineStrict();
                Headers$Builder headers$Builder = new Headers$Builder();
                int a = Cache.a(buffer);
                for (i2 = 0; i2 < a; i2++) {
                    headers$Builder.a(buffer.readUtf8LineStrict());
                }
                this.d = headers$Builder.build();
                StatusLine parse = StatusLine.parse(buffer.readUtf8LineStrict());
                this.f = parse.protocol;
                this.g = parse.code;
                this.h = parse.message;
                headers$Builder = new Headers$Builder();
                i2 = Cache.a(buffer);
                while (i < i2) {
                    headers$Builder.a(buffer.readUtf8LineStrict());
                    i++;
                }
                String str = headers$Builder.get(a);
                String str2 = headers$Builder.get(b);
                headers$Builder.removeAll(a);
                headers$Builder.removeAll(b);
                if (str != null) {
                    parseLong = Long.parseLong(str);
                } else {
                    parseLong = 0;
                }
                this.k = parseLong;
                if (str2 != null) {
                    j = Long.parseLong(str2);
                }
                this.l = j;
                this.i = headers$Builder.build();
                if (a()) {
                    String readUtf8LineStrict = buffer.readUtf8LineStrict();
                    if (readUtf8LineStrict.length() > 0) {
                        throw new IOException("expected \"\" but was \"" + readUtf8LineStrict + "\"");
                    }
                    TlsVersion tlsVersion;
                    CipherSuite forJavaName = CipherSuite.forJavaName(buffer.readUtf8LineStrict());
                    List a2 = a(buffer);
                    List a3 = a(buffer);
                    if (buffer.exhausted()) {
                        tlsVersion = TlsVersion.SSL_3_0;
                    } else {
                        tlsVersion = TlsVersion.forJavaName(buffer.readUtf8LineStrict());
                    }
                    this.j = Handshake.get(tlsVersion, forJavaName, a2, a3);
                } else {
                    this.j = null;
                }
                source.close();
            } catch (Throwable th) {
                source.close();
            }
        }

        c(Response response) {
            this.c = response.request().url().toString();
            this.d = HttpHeaders.varyHeaders(response);
            this.e = response.request().method();
            this.f = response.protocol();
            this.g = response.code();
            this.h = response.message();
            this.i = response.headers();
            this.j = response.handshake();
            this.k = response.sentRequestAtMillis();
            this.l = response.receivedResponseAtMillis();
        }

        public void writeTo(DiskLruCache$Editor diskLruCache$Editor) throws IOException {
            int i;
            int i2 = 0;
            BufferedSink buffer = Okio.buffer(diskLruCache$Editor.newSink(0));
            buffer.writeUtf8(this.c).writeByte(10);
            buffer.writeUtf8(this.e).writeByte(10);
            buffer.writeDecimalLong((long) this.d.size()).writeByte(10);
            int size = this.d.size();
            for (i = 0; i < size; i++) {
                buffer.writeUtf8(this.d.name(i)).writeUtf8(": ").writeUtf8(this.d.value(i)).writeByte(10);
            }
            buffer.writeUtf8(new StatusLine(this.f, this.g, this.h).toString()).writeByte(10);
            buffer.writeDecimalLong((long) (this.i.size() + 2)).writeByte(10);
            i = this.i.size();
            while (i2 < i) {
                buffer.writeUtf8(this.i.name(i2)).writeUtf8(": ").writeUtf8(this.i.value(i2)).writeByte(10);
                i2++;
            }
            buffer.writeUtf8(a).writeUtf8(": ").writeDecimalLong(this.k).writeByte(10);
            buffer.writeUtf8(b).writeUtf8(": ").writeDecimalLong(this.l).writeByte(10);
            if (a()) {
                buffer.writeByte(10);
                buffer.writeUtf8(this.j.cipherSuite().javaName()).writeByte(10);
                a(buffer, this.j.peerCertificates());
                a(buffer, this.j.localCertificates());
                buffer.writeUtf8(this.j.tlsVersion().javaName()).writeByte(10);
            }
            buffer.close();
        }

        private boolean a() {
            return this.c.startsWith("https://");
        }

        private List<Certificate> a(BufferedSource bufferedSource) throws IOException {
            int a = Cache.a(bufferedSource);
            if (a == -1) {
                return Collections.emptyList();
            }
            try {
                CertificateFactory instance = CertificateFactory.getInstance("X.509");
                List<Certificate> arrayList = new ArrayList(a);
                for (int i = 0; i < a; i++) {
                    String readUtf8LineStrict = bufferedSource.readUtf8LineStrict();
                    Buffer buffer = new Buffer();
                    buffer.write(ByteString.decodeBase64(readUtf8LineStrict));
                    arrayList.add(instance.generateCertificate(buffer.inputStream()));
                }
                return arrayList;
            } catch (CertificateException e) {
                throw new IOException(e.getMessage());
            }
        }

        private void a(BufferedSink bufferedSink, List<Certificate> list) throws IOException {
            try {
                bufferedSink.writeDecimalLong((long) list.size()).writeByte(10);
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    bufferedSink.writeUtf8(ByteString.of(((Certificate) list.get(i)).getEncoded()).base64()).writeByte(10);
                }
            } catch (CertificateEncodingException e) {
                throw new IOException(e.getMessage());
            }
        }

        public boolean matches(Request request, Response response) {
            return this.c.equals(request.url().toString()) && this.e.equals(request.method()) && HttpHeaders.varyMatches(response, this.d, request);
        }

        public Response response(Snapshot snapshot) {
            String str = this.i.get("Content-Type");
            String str2 = this.i.get("Content-Length");
            return new Builder().request(new Request$Builder().url(this.c).method(this.e, null).headers(this.d).build()).protocol(this.f).code(this.g).message(this.h).headers(this.i).body(new b(snapshot, str, str2)).handshake(this.j).sentRequestAtMillis(this.k).receivedResponseAtMillis(this.l).build();
        }
    }

    public Cache(File file, long j) {
        this(file, j, FileSystem.SYSTEM);
    }

    Cache(File file, long j, FileSystem fileSystem) {
        this.a = new b(this);
        this.b = DiskLruCache.create(fileSystem, file, 201105, 2, j);
    }

    public static String key(HttpUrl httpUrl) {
        return ByteString.encodeUtf8(httpUrl.toString()).md5().hex();
    }

    @Nullable
    Response a(Request request) {
        try {
            Closeable closeable = this.b.get(key(request.url()));
            if (closeable == null) {
                return null;
            }
            try {
                c cVar = new c(closeable.getSource(0));
                Response response = cVar.response(closeable);
                if (cVar.matches(request, response)) {
                    return response;
                }
                Util.closeQuietly(response.body());
                return null;
            } catch (IOException e) {
                Util.closeQuietly(closeable);
                return null;
            }
        } catch (IOException e2) {
            return null;
        }
    }

    @Nullable
    CacheRequest a(Response response) {
        DiskLruCache$Editor diskLruCache$Editor;
        String method = response.request().method();
        if (HttpMethod.invalidatesCache(response.request().method())) {
            try {
                b(response.request());
                return null;
            } catch (IOException e) {
                return null;
            }
        } else if (!method.equals("GET") || HttpHeaders.hasVaryAll(response)) {
            return null;
        } else {
            c cVar = new c(response);
            try {
                DiskLruCache$Editor edit = this.b.edit(key(response.request().url()));
                if (edit == null) {
                    return null;
                }
                try {
                    cVar.writeTo(edit);
                    return new Cache$a(this, edit);
                } catch (IOException e2) {
                    diskLruCache$Editor = edit;
                    a(diskLruCache$Editor);
                    return null;
                }
            } catch (IOException e3) {
                diskLruCache$Editor = null;
                a(diskLruCache$Editor);
                return null;
            }
        }
    }

    void b(Request request) throws IOException {
        this.b.remove(key(request.url()));
    }

    void a(Response response, Response response2) {
        c cVar = new c(response2);
        try {
            DiskLruCache$Editor edit = ((b) response.body()).a.edit();
            if (edit != null) {
                cVar.writeTo(edit);
                edit.commit();
            }
        } catch (IOException e) {
            a(null);
        }
    }

    private void a(@Nullable DiskLruCache$Editor diskLruCache$Editor) {
        if (diskLruCache$Editor != null) {
            try {
                diskLruCache$Editor.abort();
            } catch (IOException e) {
            }
        }
    }

    public void initialize() throws IOException {
        this.b.initialize();
    }

    public void delete() throws IOException {
        this.b.delete();
    }

    public void evictAll() throws IOException {
        this.b.evictAll();
    }

    public Iterator<String> urls() throws IOException {
        return new c(this);
    }

    public synchronized int writeAbortCount() {
        return this.d;
    }

    public synchronized int writeSuccessCount() {
        return this.c;
    }

    public long size() throws IOException {
        return this.b.size();
    }

    public long maxSize() {
        return this.b.getMaxSize();
    }

    public void flush() throws IOException {
        this.b.flush();
    }

    public void close() throws IOException {
        this.b.close();
    }

    public File directory() {
        return this.b.getDirectory();
    }

    public boolean isClosed() {
        return this.b.isClosed();
    }

    synchronized void a(CacheStrategy cacheStrategy) {
        this.g++;
        if (cacheStrategy.networkRequest != null) {
            this.e++;
        } else if (cacheStrategy.cacheResponse != null) {
            this.f++;
        }
    }

    synchronized void a() {
        this.f++;
    }

    public synchronized int networkCount() {
        return this.e;
    }

    public synchronized int hitCount() {
        return this.f;
    }

    public synchronized int requestCount() {
        return this.g;
    }

    static int a(BufferedSource bufferedSource) throws IOException {
        try {
            long readDecimalLong = bufferedSource.readDecimalLong();
            String readUtf8LineStrict = bufferedSource.readUtf8LineStrict();
            if (readDecimalLong >= 0 && readDecimalLong <= 2147483647L && readUtf8LineStrict.isEmpty()) {
                return (int) readDecimalLong;
            }
            throw new IOException("expected an int but was \"" + readDecimalLong + readUtf8LineStrict + "\"");
        } catch (NumberFormatException e) {
            throw new IOException(e.getMessage());
        }
    }
}
