package okhttp3.internal.http2;

import android.support.v4.view.ViewCompat;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.c;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

final class f implements Closeable {
    static final Logger a = Logger.getLogger(c.class.getName());
    final a b = new a(4096, this.d);
    private final BufferedSource c;
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

        void a(boolean z, int i, BufferedSource bufferedSource, int i2) throws IOException;

        void a(boolean z, l lVar);
    }

    static final class a implements Source {
        int a;
        byte b;
        int c;
        int d;
        short e;
        private final BufferedSource f;

        a(BufferedSource bufferedSource) {
            this.f = bufferedSource;
        }

        public long read(Buffer buffer, long j) throws IOException {
            while (this.d == 0) {
                this.f.skip((long) this.e);
                this.e = (short) 0;
                if ((this.b & 4) != 0) {
                    return -1;
                }
                a();
            }
            long read = this.f.read(buffer, Math.min(j, (long) this.d));
            if (read == -1) {
                return -1;
            }
            this.d = (int) (((long) this.d) - read);
            return read;
        }

        public Timeout timeout() {
            return this.f.timeout();
        }

        public void close() throws IOException {
        }

        private void a() throws IOException {
            int i = this.c;
            int a = f.a(this.f);
            this.d = a;
            this.a = a;
            byte readByte = (byte) (this.f.readByte() & 255);
            this.b = (byte) (this.f.readByte() & 255);
            if (f.a.isLoggable(Level.FINE)) {
                f.a.fine(c.a(true, this.c, this.a, readByte, this.b));
            }
            this.c = this.f.readInt() & Integer.MAX_VALUE;
            if (readByte != (byte) 9) {
                throw c.b("%s != TYPE_CONTINUATION", Byte.valueOf(readByte));
            } else if (this.c != i) {
                throw c.b("TYPE_CONTINUATION streamId changed", new Object[0]);
            }
        }
    }

    f(BufferedSource bufferedSource, boolean z) {
        this.c = bufferedSource;
        this.e = z;
    }

    public void a(b bVar) throws IOException {
        if (!this.e) {
            ByteString readByteString = this.c.readByteString((long) c.a.size());
            if (a.isLoggable(Level.FINE)) {
                a.fine(c.a("<< CONNECTION %s", readByteString.hex()));
            }
            if (!c.a.equals(readByteString)) {
                throw c.b("Expected a connection header but was %s", readByteString.utf8());
            }
        } else if (!a(true, bVar)) {
            throw c.b("Required SETTINGS preface not received", new Object[0]);
        }
    }

    public boolean a(boolean z, b bVar) throws IOException {
        try {
            this.c.require(9);
            int a = a(this.c);
            if (a < 0 || a > 16384) {
                throw c.b("FRAME_SIZE_ERROR: %s", Integer.valueOf(a));
            }
            byte readByte = (byte) (this.c.readByte() & 255);
            if (!z || readByte == (byte) 4) {
                byte readByte2 = (byte) (this.c.readByte() & 255);
                int readInt = this.c.readInt() & Integer.MAX_VALUE;
                if (a.isLoggable(Level.FINE)) {
                    a.fine(c.a(true, readInt, a, readByte, readByte2));
                }
                switch (readByte) {
                    case (byte) 0:
                        b(bVar, a, readByte2, readInt);
                        return true;
                    case (byte) 1:
                        a(bVar, a, readByte2, readInt);
                        return true;
                    case (byte) 2:
                        c(bVar, a, readByte2, readInt);
                        return true;
                    case (byte) 3:
                        d(bVar, a, readByte2, readInt);
                        return true;
                    case (byte) 4:
                        e(bVar, a, readByte2, readInt);
                        return true;
                    case (byte) 5:
                        f(bVar, a, readByte2, readInt);
                        return true;
                    case (byte) 6:
                        g(bVar, a, readByte2, readInt);
                        return true;
                    case (byte) 7:
                        h(bVar, a, readByte2, readInt);
                        return true;
                    case (byte) 8:
                        i(bVar, a, readByte2, readInt);
                        return true;
                    default:
                        this.c.skip((long) a);
                        return true;
                }
            }
            throw c.b("Expected a SETTINGS frame but was %s", Byte.valueOf(readByte));
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
            s = (short) (this.c.readByte() & 255);
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
        short s = (short) 1;
        short s2 = (short) 0;
        if (i2 == 0) {
            throw c.b("PROTOCOL_ERROR: TYPE_DATA streamId == 0", new Object[0]);
        }
        boolean z;
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
            s2 = (short) (this.c.readByte() & 255);
        }
        bVar.a(z, i2, this.c, a(i, b, s2));
        this.c.skip((long) s2);
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
        int readInt = this.c.readInt();
        bVar.a(i, readInt & Integer.MAX_VALUE, (this.c.readByte() & 255) + 1, (Integer.MIN_VALUE & readInt) != 0);
    }

    private void d(b bVar, int i, byte b, int i2) throws IOException {
        if (i != 4) {
            throw c.b("TYPE_RST_STREAM length: %d != 4", Integer.valueOf(i));
        } else if (i2 == 0) {
            throw c.b("TYPE_RST_STREAM streamId == 0", new Object[0]);
        } else {
            ErrorCode fromHttp2 = ErrorCode.fromHttp2(this.c.readInt());
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
                int readShort = this.c.readShort();
                int readInt = this.c.readInt();
                switch (readShort) {
                    case 2:
                        if (!(readInt == 0 || readInt == 1)) {
                            throw c.b("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                        }
                    case 3:
                        readShort = 4;
                        break;
                    case 4:
                        readShort = 7;
                        if (readInt >= 0) {
                            break;
                        }
                        throw c.b("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                    case 5:
                        if (readInt >= 16384 && readInt <= ViewCompat.MEASURED_SIZE_MASK) {
                            break;
                        }
                        throw c.b("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", Integer.valueOf(readInt));
                        break;
                    default:
                        break;
                }
                lVar.a(readShort, readInt);
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
            s = (short) (this.c.readByte() & 255);
        }
        bVar.a(i2, this.c.readInt() & Integer.MAX_VALUE, a(a(i - 4, b, s), s, b, i2));
    }

    private void g(b bVar, int i, byte b, int i2) throws IOException {
        boolean z = true;
        if (i != 8) {
            throw c.b("TYPE_PING length != 8: %s", Integer.valueOf(i));
        } else if (i2 != 0) {
            throw c.b("TYPE_PING streamId != 0", new Object[0]);
        } else {
            int readInt = this.c.readInt();
            int readInt2 = this.c.readInt();
            if ((b & 1) == 0) {
                z = false;
            }
            bVar.a(z, readInt, readInt2);
        }
    }

    private void h(b bVar, int i, byte b, int i2) throws IOException {
        if (i < 8) {
            throw c.b("TYPE_GOAWAY length < 8: %s", Integer.valueOf(i));
        } else if (i2 != 0) {
            throw c.b("TYPE_GOAWAY streamId != 0", new Object[0]);
        } else {
            int readInt = this.c.readInt();
            int i3 = i - 8;
            ErrorCode fromHttp2 = ErrorCode.fromHttp2(this.c.readInt());
            if (fromHttp2 == null) {
                throw c.b("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(r0));
            }
            ByteString byteString = ByteString.EMPTY;
            if (i3 > 0) {
                byteString = this.c.readByteString((long) i3);
            }
            bVar.a(readInt, fromHttp2, byteString);
        }
    }

    private void i(b bVar, int i, byte b, int i2) throws IOException {
        if (i != 4) {
            throw c.b("TYPE_WINDOW_UPDATE length !=4: %s", Integer.valueOf(i));
        }
        long readInt = ((long) this.c.readInt()) & 2147483647L;
        if (readInt == 0) {
            throw c.b("windowSizeIncrement was 0", Long.valueOf(readInt));
        } else {
            bVar.a(i2, readInt);
        }
    }

    public void close() throws IOException {
        this.c.close();
    }

    static int a(BufferedSource bufferedSource) throws IOException {
        return (((bufferedSource.readByte() & 255) << 16) | ((bufferedSource.readByte() & 255) << 8)) | (bufferedSource.readByte() & 255);
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
