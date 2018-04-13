package okhttp3.internal.http2;

import android.support.v4.view.ViewCompat;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okio.ByteString;
import okio.c;
import okio.e;
import okio.r;
import okio.s;

final class f implements Closeable {
    static final Logger a = Logger.getLogger(c.class.getName());
    final a b = new a(4096, this.d);
    private final e c;
    private final a d = new a(this.c);
    private final boolean e;

    interface b {
        void a();

        void a(int i, int i2, int i3, boolean z);

        void a(int i, int i2, List<a> list) throws IOException;

        void a(int i, long j);

        void a(int i, ErrorCode errorCode);

        void a(int i, ErrorCode errorCode, ByteString byteString);

        void a(boolean z, int i, int i2);

        void a(boolean z, int i, int i2, List<a> list);

        void a(boolean z, int i, e eVar, int i2) throws IOException;

        void a(boolean z, l lVar);
    }

    static final class a implements r {
        int a;
        byte b;
        int c;
        int d;
        short e;
        private final e f;

        public a(e eVar) {
            this.f = eVar;
        }

        public long a(c cVar, long j) throws IOException {
            while (this.d == 0) {
                this.f.g((long) this.e);
                this.e = (short) 0;
                if ((this.b & 4) != 0) {
                    return -1;
                }
                b();
            }
            long a = this.f.a(cVar, Math.min(j, (long) this.d));
            if (a == -1) {
                return -1;
            }
            this.d = (int) (((long) this.d) - a);
            return a;
        }

        public s a() {
            return this.f.a();
        }

        public void close() throws IOException {
        }

        private void b() throws IOException {
            int i = this.c;
            int a = f.a(this.f);
            this.d = a;
            this.a = a;
            byte i2 = (byte) (this.f.i() & 255);
            this.b = (byte) (this.f.i() & 255);
            if (f.a.isLoggable(Level.FINE)) {
                f.a.fine(c.a(true, this.c, this.a, i2, this.b));
            }
            this.c = this.f.k() & Integer.MAX_VALUE;
            if (i2 != (byte) 9) {
                throw c.b("%s != TYPE_CONTINUATION", Byte.valueOf(i2));
            } else if (this.c != i) {
                throw c.b("TYPE_CONTINUATION streamId changed", new Object[0]);
            }
        }
    }

    public f(e eVar, boolean z) {
        this.c = eVar;
        this.e = z;
    }

    public void a(b bVar) throws IOException {
        if (!this.e) {
            ByteString c = this.c.c((long) c.a.size());
            if (a.isLoggable(Level.FINE)) {
                a.fine(okhttp3.internal.c.a("<< CONNECTION %s", c.hex()));
            }
            if (!c.a.equals(c)) {
                throw c.b("Expected a connection header but was %s", c.utf8());
            }
        } else if (!a(true, bVar)) {
            throw c.b("Required SETTINGS preface not received", new Object[0]);
        }
    }

    public boolean a(boolean z, b bVar) throws IOException {
        try {
            this.c.a(9);
            int a = a(this.c);
            if (a < 0 || a > 16384) {
                throw c.b("FRAME_SIZE_ERROR: %s", Integer.valueOf(a));
            }
            byte i = (byte) (this.c.i() & 255);
            if (!z || i == (byte) 4) {
                byte i2 = (byte) (this.c.i() & 255);
                int k = this.c.k() & Integer.MAX_VALUE;
                if (a.isLoggable(Level.FINE)) {
                    a.fine(c.a(true, k, a, i, i2));
                }
                switch (i) {
                    case (byte) 0:
                        b(bVar, a, i2, k);
                        return true;
                    case (byte) 1:
                        a(bVar, a, i2, k);
                        return true;
                    case (byte) 2:
                        c(bVar, a, i2, k);
                        return true;
                    case (byte) 3:
                        d(bVar, a, i2, k);
                        return true;
                    case (byte) 4:
                        e(bVar, a, i2, k);
                        return true;
                    case (byte) 5:
                        f(bVar, a, i2, k);
                        return true;
                    case (byte) 6:
                        g(bVar, a, i2, k);
                        return true;
                    case (byte) 7:
                        h(bVar, a, i2, k);
                        return true;
                    case (byte) 8:
                        i(bVar, a, i2, k);
                        return true;
                    default:
                        this.c.g((long) a);
                        return true;
                }
            }
            throw c.b("Expected a SETTINGS frame but was %s", Byte.valueOf(i));
        } catch (IOException e) {
            return false;
        }
    }

    private void a(b bVar, int i, byte b, int i2) throws IOException {
        short s = (short) 0;
        if (i2 == 0) {
            throw c.b("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
        }
        boolean z = (b & 1) != 0;
        if ((b & 8) != 0) {
            s = (short) (this.c.i() & 255);
        }
        if ((b & 32) != 0) {
            a(bVar, i2);
            i -= 5;
        }
        bVar.a(z, i2, -1, a(a(i, b, s), s, b, i2));
    }

    private List<a> a(int i, short s, byte b, int i2) throws IOException {
        a aVar = this.d;
        this.d.d = i;
        aVar.a = i;
        this.d.e = s;
        this.d.b = b;
        this.d.c = i2;
        this.b.a();
        return this.b.b();
    }

    private void b(b bVar, int i, byte b, int i2) throws IOException {
        boolean z;
        short s = (short) 1;
        short s2 = (short) 0;
        if ((b & 1) != 0) {
            z = true;
        } else {
            z = false;
        }
        if ((b & 32) == 0) {
            s = (short) 0;
        }
        if (s != (short) 0) {
            throw c.b("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
        }
        if ((b & 8) != 0) {
            s2 = (short) (this.c.i() & 255);
        }
        bVar.a(z, i2, this.c, a(i, b, s2));
        this.c.g((long) s2);
    }

    private void c(b bVar, int i, byte b, int i2) throws IOException {
        if (i != 5) {
            throw c.b("TYPE_PRIORITY length: %d != 5", Integer.valueOf(i));
        } else if (i2 == 0) {
            throw c.b("TYPE_PRIORITY streamId == 0", new Object[0]);
        } else {
            a(bVar, i2);
        }
    }

    private void a(b bVar, int i) throws IOException {
        int k = this.c.k();
        bVar.a(i, k & Integer.MAX_VALUE, (this.c.i() & 255) + 1, (Integer.MIN_VALUE & k) != 0);
    }

    private void d(b bVar, int i, byte b, int i2) throws IOException {
        if (i != 4) {
            throw c.b("TYPE_RST_STREAM length: %d != 4", Integer.valueOf(i));
        } else if (i2 == 0) {
            throw c.b("TYPE_RST_STREAM streamId == 0", new Object[0]);
        } else {
            ErrorCode fromHttp2 = ErrorCode.fromHttp2(this.c.k());
            if (fromHttp2 == null) {
                throw c.b("TYPE_RST_STREAM unexpected error code: %d", Integer.valueOf(r0));
            } else {
                bVar.a(i2, fromHttp2);
            }
        }
    }

    private void e(b bVar, int i, byte b, int i2) throws IOException {
        if (i2 != 0) {
            throw c.b("TYPE_SETTINGS streamId != 0", new Object[0]);
        } else if ((b & 1) != 0) {
            if (i != 0) {
                throw c.b("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
            }
            bVar.a();
        } else if (i % 6 != 0) {
            throw c.b("TYPE_SETTINGS length %% 6 != 0: %s", Integer.valueOf(i));
        } else {
            l lVar = new l();
            for (int i3 = 0; i3 < i; i3 += 6) {
                int j = this.c.j();
                int k = this.c.k();
                switch (j) {
                    case 2:
                        if (!(k == 0 || k == 1)) {
                            throw c.b("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                        }
                    case 3:
                        j = 4;
                        break;
                    case 4:
                        j = 7;
                        if (k >= 0) {
                            break;
                        }
                        throw c.b("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                    case 5:
                        if (k >= 16384 && k <= ViewCompat.MEASURED_SIZE_MASK) {
                            break;
                        }
                        throw c.b("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", Integer.valueOf(k));
                    default:
                        break;
                }
                lVar.a(j, k);
            }
            bVar.a(false, lVar);
        }
    }

    private void f(b bVar, int i, byte b, int i2) throws IOException {
        short s = (short) 0;
        if (i2 == 0) {
            throw c.b("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
        }
        if ((b & 8) != 0) {
            s = (short) (this.c.i() & 255);
        }
        bVar.a(i2, this.c.k() & Integer.MAX_VALUE, a(a(i - 4, b, s), s, b, i2));
    }

    private void g(b bVar, int i, byte b, int i2) throws IOException {
        boolean z = true;
        if (i != 8) {
            throw c.b("TYPE_PING length != 8: %s", Integer.valueOf(i));
        } else if (i2 != 0) {
            throw c.b("TYPE_PING streamId != 0", new Object[0]);
        } else {
            int k = this.c.k();
            int k2 = this.c.k();
            if ((b & 1) == 0) {
                z = false;
            }
            bVar.a(z, k, k2);
        }
    }

    private void h(b bVar, int i, byte b, int i2) throws IOException {
        if (i < 8) {
            throw c.b("TYPE_GOAWAY length < 8: %s", Integer.valueOf(i));
        } else if (i2 != 0) {
            throw c.b("TYPE_GOAWAY streamId != 0", new Object[0]);
        } else {
            int k = this.c.k();
            int i3 = i - 8;
            ErrorCode fromHttp2 = ErrorCode.fromHttp2(this.c.k());
            if (fromHttp2 == null) {
                throw c.b("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(r0));
            }
            ByteString byteString = ByteString.EMPTY;
            if (i3 > 0) {
                byteString = this.c.c((long) i3);
            }
            bVar.a(k, fromHttp2, byteString);
        }
    }

    private void i(b bVar, int i, byte b, int i2) throws IOException {
        if (i != 4) {
            throw c.b("TYPE_WINDOW_UPDATE length !=4: %s", Integer.valueOf(i));
        }
        long k = ((long) this.c.k()) & 2147483647L;
        if (k == 0) {
            throw c.b("windowSizeIncrement was 0", Long.valueOf(k));
        } else {
            bVar.a(i2, k);
        }
    }

    public void close() throws IOException {
        this.c.close();
    }

    static int a(e eVar) throws IOException {
        return (((eVar.i() & 255) << 16) | ((eVar.i() & 255) << 8)) | (eVar.i() & 255);
    }

    static int a(int i, byte b, short s) throws IOException {
        if ((b & 8) != 0) {
            short s2 = i - 1;
        }
        if (s <= s2) {
            return (short) (s2 - s);
        }
        throw c.b("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s), Integer.valueOf(s2));
    }
}
