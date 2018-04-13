package okhttp3.internal.http2;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.sina.weibo.sdk.register.mobile.MobileRegisterActivity;
import com.tencent.connect.common.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import okhttp3.internal.c;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Source;
import tv.danmaku.ijk.media.player.FFmpegMediaMetadataRetriever;

final class b {
    static final a[] a = new a[]{new a(a.f, ""), new a(a.c, Constants.HTTP_GET), new a(a.c, Constants.HTTP_POST), new a(a.d, "/"), new a(a.d, "/index.html"), new a(a.e, "http"), new a(a.e, "https"), new a(a.b, "200"), new a(a.b, "204"), new a(a.b, "206"), new a(a.b, "304"), new a(a.b, "400"), new a(a.b, "404"), new a(a.b, "500"), new a("accept-charset", ""), new a("accept-encoding", "gzip, deflate"), new a("accept-language", ""), new a("accept-ranges", ""), new a("accept", ""), new a("access-control-allow-origin", ""), new a("age", ""), new a("allow", ""), new a("authorization", ""), new a("cache-control", ""), new a("content-disposition", ""), new a("content-encoding", ""), new a("content-language", ""), new a("content-length", ""), new a("content-location", ""), new a("content-range", ""), new a("content-type", ""), new a("cookie", ""), new a(FFmpegMediaMetadataRetriever.METADATA_KEY_DATE, ""), new a("etag", ""), new a("expect", ""), new a(MobileRegisterActivity.RESPONSE_EXPIRES, ""), new a("from", ""), new a("host", ""), new a("if-match", ""), new a("if-modified-since", ""), new a("if-none-match", ""), new a("if-range", ""), new a("if-unmodified-since", ""), new a("last-modified", ""), new a("link", ""), new a(RequestParameters.SUBRESOURCE_LOCATION, ""), new a("max-forwards", ""), new a("proxy-authenticate", ""), new a("proxy-authorization", ""), new a("range", ""), new a(RequestParameters.SUBRESOURCE_REFERER, ""), new a("refresh", ""), new a("retry-after", ""), new a("server", ""), new a("set-cookie", ""), new a("strict-transport-security", ""), new a("transfer-encoding", ""), new a("user-agent", ""), new a("vary", ""), new a("via", ""), new a("www-authenticate", "")};
    static final Map<ByteString, Integer> b = a();

    static final class a {
        a[] a;
        int b;
        int c;
        int d;
        private final List<a> e;
        private final BufferedSource f;
        private final int g;
        private int h;

        a(int i, Source source) {
            this(i, i, source);
        }

        a(int i, int i2, Source source) {
            this.e = new ArrayList();
            this.a = new a[8];
            this.b = this.a.length - 1;
            this.c = 0;
            this.d = 0;
            this.g = i;
            this.h = i2;
            this.f = Okio.buffer(source);
        }

        private void d() {
            if (this.h >= this.d) {
                return;
            }
            if (this.h == 0) {
                e();
            } else {
                a(this.d - this.h);
            }
        }

        private void e() {
            Arrays.fill(this.a, null);
            this.b = this.a.length - 1;
            this.c = 0;
            this.d = 0;
        }

        private int a(int i) {
            int i2 = 0;
            if (i > 0) {
                for (int length = this.a.length - 1; length >= this.b && i > 0; length--) {
                    i -= this.a[length].i;
                    this.d -= this.a[length].i;
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
                    g();
                } else if ((readByte & 64) == 64) {
                    e(a(readByte, 63) - 1);
                } else if ((readByte & 32) == 32) {
                    this.h = a(readByte, 31);
                    if (this.h < 0 || this.h > this.g) {
                        throw new IOException("Invalid dynamic table size update " + this.h);
                    }
                    d();
                } else if (readByte == 16 || readByte == 0) {
                    f();
                } else {
                    d(a(readByte, 15) - 1);
                }
            }
        }

        public List<a> b() {
            List arrayList = new ArrayList(this.e);
            this.e.clear();
            return arrayList;
        }

        private void b(int i) throws IOException {
            if (g(i)) {
                this.e.add(b.a[i]);
                return;
            }
            int c = c(i - b.a.length);
            if (c < 0 || c > this.a.length - 1) {
                throw new IOException("Header index too large " + (i + 1));
            }
            this.e.add(this.a[c]);
        }

        private int c(int i) {
            return (this.b + 1) + i;
        }

        private void d(int i) throws IOException {
            this.e.add(new a(f(i), c()));
        }

        private void f() throws IOException {
            this.e.add(new a(b.a(c()), c()));
        }

        private void e(int i) throws IOException {
            a(-1, new a(f(i), c()));
        }

        private void g() throws IOException {
            a(-1, new a(b.a(c()), c()));
        }

        private ByteString f(int i) {
            if (g(i)) {
                return b.a[i].g;
            }
            return this.a[c(i - b.a.length)].g;
        }

        private boolean g(int i) {
            return i >= 0 && i <= b.a.length - 1;
        }

        private void a(int i, a aVar) {
            this.e.add(aVar);
            int i2 = aVar.i;
            if (i != -1) {
                i2 -= this.a[c(i)].i;
            }
            if (i2 > this.h) {
                e();
                return;
            }
            int a = a((this.d + i2) - this.h);
            if (i == -1) {
                if (this.c + 1 > this.a.length) {
                    Object obj = new a[(this.a.length * 2)];
                    System.arraycopy(this.a, 0, obj, this.a.length, this.a.length);
                    this.b = this.a.length - 1;
                    this.a = obj;
                }
                a = this.b;
                this.b = a - 1;
                this.a[a] = aVar;
                this.c++;
            } else {
                this.a[(a + c(i)) + i] = aVar;
            }
            this.d = i2 + this.d;
        }

        private int h() throws IOException {
            return this.f.readByte() & 255;
        }

        int a(int i, int i2) throws IOException {
            int i3 = i & i2;
            if (i3 < i2) {
                return i3;
            }
            i3 = 0;
            while (true) {
                int h = h();
                if ((h & 128) == 0) {
                    return (h << i3) + i2;
                }
                i2 += (h & 127) << i3;
                i3 += 7;
            }
        }

        ByteString c() throws IOException {
            int h = h();
            Object obj = (h & 128) == 128 ? 1 : null;
            h = a(h, 127);
            if (obj != null) {
                return ByteString.of(i.a().a(this.f.readByteArray((long) h)));
            }
            return this.f.readByteString((long) h);
        }
    }

    static final class b {
        int a;
        int b;
        a[] c;
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
            this.c = new a[8];
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
                    i -= this.c[length].i;
                    this.f -= this.c[length].i;
                    this.e--;
                    i2++;
                }
                System.arraycopy(this.c, this.d + 1, this.c, (this.d + 1) + i2, this.e);
                Arrays.fill(this.c, this.d + 1, (this.d + 1) + i2, null);
                this.d += i2;
            }
            return i2;
        }

        private void a(a aVar) {
            int i = aVar.i;
            if (i > this.b) {
                a();
                return;
            }
            b((this.f + i) - this.b);
            if (this.e + 1 > this.c.length) {
                Object obj = new a[(this.c.length * 2)];
                System.arraycopy(this.c, 0, obj, this.c.length, this.c.length);
                this.d = this.c.length - 1;
                this.c = obj;
            }
            int i2 = this.d;
            this.d = i2 - 1;
            this.c[i2] = aVar;
            this.e++;
            this.f = i + this.f;
        }

        void a(List<a> list) throws IOException {
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
                a aVar = (a) list.get(i);
                ByteString toAsciiLowercase = aVar.g.toAsciiLowercase();
                ByteString byteString = aVar.h;
                Integer num = (Integer) b.b.get(toAsciiLowercase);
                if (num != null) {
                    intValue = num.intValue() + 1;
                    if (intValue > 1 && intValue < 8) {
                        if (c.a(b.a[intValue - 1].h, (Object) byteString)) {
                            i2 = intValue;
                        } else if (c.a(b.a[intValue].h, (Object) byteString)) {
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
                        if (c.a(this.c[i3].g, (Object) toAsciiLowercase)) {
                            if (c.a(this.c[i3].h, (Object) byteString)) {
                                i2 = (i3 - this.d) + b.a.length;
                                break;
                            } else if (intValue == -1) {
                                intValue = (i3 - this.d) + b.a.length;
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
                    a(aVar);
                } else if (!toAsciiLowercase.startsWith(a.a) || a.f.equals(toAsciiLowercase)) {
                    a(intValue, 63, 64);
                    a(byteString);
                    a(aVar);
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
            if (!this.h || i.a().a(byteString) >= byteString.size()) {
                a(byteString.size(), 127, 0);
                this.g.write(byteString);
                return;
            }
            Object buffer = new Buffer();
            i.a().a(byteString, buffer);
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
            if (!linkedHashMap.containsKey(a[i].g)) {
                linkedHashMap.put(a[i].g, Integer.valueOf(i));
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
