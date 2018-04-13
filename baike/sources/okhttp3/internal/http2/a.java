package okhttp3.internal.http2;

import com.alipay.android.phone.mrpc.core.Headers;
import com.alipay.sdk.cons.c;
import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase.IndexEntry;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Source;
import org.eclipse.paho.client.mqttv3.MqttTopic;

final class a {
    static final Header[] a = new Header[]{new Header(Header.TARGET_AUTHORITY, ""), new Header(Header.TARGET_METHOD, "GET"), new Header(Header.TARGET_METHOD, "POST"), new Header(Header.TARGET_PATH, MqttTopic.TOPIC_LEVEL_SEPARATOR), new Header(Header.TARGET_PATH, "/index.html"), new Header(Header.TARGET_SCHEME, "http"), new Header(Header.TARGET_SCHEME, "https"), new Header(Header.RESPONSE_STATUS, "200"), new Header(Header.RESPONSE_STATUS, "204"), new Header(Header.RESPONSE_STATUS, "206"), new Header(Header.RESPONSE_STATUS, "304"), new Header(Header.RESPONSE_STATUS, "400"), new Header(Header.RESPONSE_STATUS, "404"), new Header(Header.RESPONSE_STATUS, "500"), new Header("accept-charset", ""), new Header("accept-encoding", "gzip, deflate"), new Header("accept-language", ""), new Header(Headers.ACCEPT_RANGES, ""), new Header("accept", ""), new Header("access-control-allow-origin", ""), new Header("age", ""), new Header("allow", ""), new Header("authorization", ""), new Header(Headers.CACHE_CONTROL, ""), new Header(Headers.CONTENT_DISPOSITION, ""), new Header(Headers.CONTENT_ENCODING, ""), new Header("content-language", ""), new Header(Headers.CONTENT_LEN, ""), new Header("content-location", ""), new Header("content-range", ""), new Header("content-type", ""), new Header("cookie", ""), new Header(IndexEntry.COLUMN_NAME_DATE, ""), new Header("etag", ""), new Header("expect", ""), new Header("expires", ""), new Header("from", ""), new Header(c.f, ""), new Header("if-match", ""), new Header("if-modified-since", ""), new Header("if-none-match", ""), new Header("if-range", ""), new Header("if-unmodified-since", ""), new Header(Headers.LAST_MODIFIED, ""), new Header("link", ""), new Header("location", ""), new Header("max-forwards", ""), new Header(Headers.PROXY_AUTHENTICATE, ""), new Header("proxy-authorization", ""), new Header("range", ""), new Header("referer", ""), new Header(Headers.REFRESH, ""), new Header("retry-after", ""), new Header("server", ""), new Header(Headers.SET_COOKIE, ""), new Header("strict-transport-security", ""), new Header(Headers.TRANSFER_ENCODING, ""), new Header("user-agent", ""), new Header("vary", ""), new Header("via", ""), new Header(Headers.WWW_AUTHENTICATE, "")};
    static final Map<ByteString, Integer> b = a();

    static final class a {
        Header[] a;
        int b;
        int c;
        int d;
        private final List<Header> e;
        private final BufferedSource f;
        private final int g;
        private int h;

        a(int i, Source source) {
            this(i, i, source);
        }

        a(int i, int i2, Source source) {
            this.e = new ArrayList();
            this.a = new Header[8];
            this.b = this.a.length - 1;
            this.c = 0;
            this.d = 0;
            this.g = i;
            this.h = i2;
            this.f = Okio.buffer(source);
        }

        private void c() {
            if (this.h >= this.d) {
                return;
            }
            if (this.h == 0) {
                d();
            } else {
                a(this.d - this.h);
            }
        }

        private void d() {
            Arrays.fill(this.a, null);
            this.b = this.a.length - 1;
            this.c = 0;
            this.d = 0;
        }

        private int a(int i) {
            int i2 = 0;
            if (i > 0) {
                for (int length = this.a.length - 1; length >= this.b && i > 0; length--) {
                    i -= this.a[length].a;
                    this.d -= this.a[length].a;
                    this.c--;
                    i2++;
                }
                System.arraycopy(this.a, this.b + 1, this.a, (this.b + 1) + i2, this.c);
                this.b += i2;
            }
            return i2;
        }

        void a() throws IOException {
            while (!this.f.exhausted()) {
                int readByte = this.f.readByte() & 255;
                if (readByte == 128) {
                    throw new IOException("index == 0");
                } else if ((readByte & 128) == 128) {
                    b(a(readByte, 127) - 1);
                } else if (readByte == 64) {
                    f();
                } else if ((readByte & 64) == 64) {
                    e(a(readByte, 63) - 1);
                } else if ((readByte & 32) == 32) {
                    this.h = a(readByte, 31);
                    if (this.h < 0 || this.h > this.g) {
                        throw new IOException("Invalid dynamic table size update " + this.h);
                    }
                    c();
                } else if (readByte == 16 || readByte == 0) {
                    e();
                } else {
                    d(a(readByte, 15) - 1);
                }
            }
        }

        public List<Header> getAndResetHeaderList() {
            List arrayList = new ArrayList(this.e);
            this.e.clear();
            return arrayList;
        }

        private void b(int i) throws IOException {
            if (g(i)) {
                this.e.add(a.a[i]);
                return;
            }
            int c = c(i - a.a.length);
            if (c < 0 || c > this.a.length - 1) {
                throw new IOException("Header index too large " + (i + 1));
            }
            this.e.add(this.a[c]);
        }

        private int c(int i) {
            return (this.b + 1) + i;
        }

        private void d(int i) throws IOException {
            this.e.add(new Header(f(i), b()));
        }

        private void e() throws IOException {
            this.e.add(new Header(a.a(b()), b()));
        }

        private void e(int i) throws IOException {
            a(-1, new Header(f(i), b()));
        }

        private void f() throws IOException {
            a(-1, new Header(a.a(b()), b()));
        }

        private ByteString f(int i) {
            if (g(i)) {
                return a.a[i].name;
            }
            return this.a[c(i - a.a.length)].name;
        }

        private boolean g(int i) {
            return i >= 0 && i <= a.a.length - 1;
        }

        private void a(int i, Header header) {
            this.e.add(header);
            int i2 = header.a;
            if (i != -1) {
                i2 -= this.a[c(i)].a;
            }
            if (i2 > this.h) {
                d();
                return;
            }
            int a = a((this.d + i2) - this.h);
            if (i == -1) {
                if (this.c + 1 > this.a.length) {
                    Object obj = new Header[(this.a.length * 2)];
                    System.arraycopy(this.a, 0, obj, this.a.length, this.a.length);
                    this.b = this.a.length - 1;
                    this.a = obj;
                }
                a = this.b;
                this.b = a - 1;
                this.a[a] = header;
                this.c++;
            } else {
                this.a[(a + c(i)) + i] = header;
            }
            this.d = i2 + this.d;
        }

        private int g() throws IOException {
            return this.f.readByte() & 255;
        }

        int a(int i, int i2) throws IOException {
            int i3 = i & i2;
            if (i3 < i2) {
                return i3;
            }
            i3 = 0;
            while (true) {
                int g = g();
                if ((g & 128) == 0) {
                    return (g << i3) + i2;
                }
                i2 += (g & 127) << i3;
                i3 += 7;
            }
        }

        ByteString b() throws IOException {
            int g = g();
            Object obj = (g & 128) == 128 ? 1 : null;
            g = a(g, 127);
            if (obj != null) {
                return ByteString.of(o.get().a(this.f.readByteArray((long) g)));
            }
            return this.f.readByteString((long) g);
        }
    }

    static final class b {
        int a;
        int b;
        Header[] c;
        int d;
        int e;
        int f;
        private final Buffer g;
        private final boolean h;
        private int i;
        private boolean j;

        b(Buffer buffer) {
            this(4096, true, buffer);
        }

        b(int i, boolean z, Buffer buffer) {
            this.i = Integer.MAX_VALUE;
            this.c = new Header[8];
            this.d = this.c.length - 1;
            this.e = 0;
            this.f = 0;
            this.a = i;
            this.b = i;
            this.h = z;
            this.g = buffer;
        }

        private void a() {
            Arrays.fill(this.c, null);
            this.d = this.c.length - 1;
            this.e = 0;
            this.f = 0;
        }

        private int b(int i) {
            int i2 = 0;
            if (i > 0) {
                for (int length = this.c.length - 1; length >= this.d && i > 0; length--) {
                    i -= this.c[length].a;
                    this.f -= this.c[length].a;
                    this.e--;
                    i2++;
                }
                System.arraycopy(this.c, this.d + 1, this.c, (this.d + 1) + i2, this.e);
                Arrays.fill(this.c, this.d + 1, (this.d + 1) + i2, null);
                this.d += i2;
            }
            return i2;
        }

        private void a(Header header) {
            int i = header.a;
            if (i > this.b) {
                a();
                return;
            }
            b((this.f + i) - this.b);
            if (this.e + 1 > this.c.length) {
                Object obj = new Header[(this.c.length * 2)];
                System.arraycopy(this.c, 0, obj, this.c.length, this.c.length);
                this.d = this.c.length - 1;
                this.c = obj;
            }
            int i2 = this.d;
            this.d = i2 - 1;
            this.c[i2] = header;
            this.e++;
            this.f = i + this.f;
        }

        void a(List<Header> list) throws IOException {
            if (this.j) {
                if (this.i < this.b) {
                    a(this.i, 31, 32);
                }
                this.j = false;
                this.i = Integer.MAX_VALUE;
                a(this.b, 31, 32);
            }
            int size = list.size();
            for (int i = 0; i < size; i++) {
                int intValue;
                int i2;
                Header header = (Header) list.get(i);
                ByteString toAsciiLowercase = header.name.toAsciiLowercase();
                ByteString byteString = header.value;
                Integer num = (Integer) a.b.get(toAsciiLowercase);
                if (num != null) {
                    intValue = num.intValue() + 1;
                    if (intValue > 1 && intValue < 8) {
                        if (Util.equal(a.a[intValue - 1].value, byteString)) {
                            i2 = intValue;
                        } else if (Util.equal(a.a[intValue].value, byteString)) {
                            i2 = intValue + 1;
                        }
                    }
                    i2 = -1;
                } else {
                    intValue = -1;
                    i2 = -1;
                }
                if (i2 == -1) {
                    int length = this.c.length;
                    for (int i3 = this.d + 1; i3 < length; i3++) {
                        if (Util.equal(this.c[i3].name, toAsciiLowercase)) {
                            if (Util.equal(this.c[i3].value, byteString)) {
                                i2 = (i3 - this.d) + a.a.length;
                                break;
                            } else if (intValue == -1) {
                                intValue = (i3 - this.d) + a.a.length;
                            }
                        }
                    }
                }
                if (i2 != -1) {
                    a(i2, 127, 128);
                } else if (intValue == -1) {
                    this.g.writeByte(64);
                    a(toAsciiLowercase);
                    a(byteString);
                    a(header);
                } else if (!toAsciiLowercase.startsWith(Header.PSEUDO_PREFIX) || Header.TARGET_AUTHORITY.equals(toAsciiLowercase)) {
                    a(intValue, 63, 64);
                    a(byteString);
                    a(header);
                } else {
                    a(intValue, 15, 0);
                    a(byteString);
                }
            }
        }

        void a(int i, int i2, int i3) {
            if (i < i2) {
                this.g.writeByte(i3 | i);
                return;
            }
            this.g.writeByte(i3 | i2);
            int i4 = i - i2;
            while (i4 >= 128) {
                this.g.writeByte((i4 & 127) | 128);
                i4 >>>= 7;
            }
            this.g.writeByte(i4);
        }

        void a(ByteString byteString) throws IOException {
            if (!this.h || o.get().a(byteString) >= byteString.size()) {
                a(byteString.size(), 127, 0);
                this.g.write(byteString);
                return;
            }
            Object buffer = new Buffer();
            o.get().a(byteString, buffer);
            ByteString readByteString = buffer.readByteString();
            a(readByteString.size(), 127, 128);
            this.g.write(readByteString);
        }

        void a(int i) {
            this.a = i;
            int min = Math.min(i, 16384);
            if (this.b != min) {
                if (min < this.b) {
                    this.i = Math.min(this.i, min);
                }
                this.j = true;
                this.b = min;
                b();
            }
        }

        private void b() {
            if (this.b >= this.f) {
                return;
            }
            if (this.b == 0) {
                a();
            } else {
                b(this.f - this.b);
            }
        }
    }

    private static Map<ByteString, Integer> a() {
        Map linkedHashMap = new LinkedHashMap(a.length);
        for (int i = 0; i < a.length; i++) {
            if (!linkedHashMap.containsKey(a[i].name)) {
                linkedHashMap.put(a[i].name, Integer.valueOf(i));
            }
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }

    static ByteString a(ByteString byteString) throws IOException {
        int i = 0;
        int size = byteString.size();
        while (i < size) {
            byte b = byteString.getByte(i);
            if (b < (byte) 65 || b > (byte) 90) {
                i++;
            } else {
                throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: " + byteString.utf8());
            }
        }
        return byteString;
    }
}
