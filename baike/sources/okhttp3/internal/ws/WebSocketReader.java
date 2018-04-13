package okhttp3.internal.ws;

import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

final class WebSocketReader {
    final boolean a;
    final BufferedSource b;
    final FrameCallback c;
    boolean d;
    int e;
    long f;
    long g;
    boolean h;
    boolean i;
    boolean j;
    final byte[] k = new byte[4];
    final byte[] l = new byte[8192];

    public interface FrameCallback {
        void onReadClose(int i, String str);

        void onReadMessage(String str) throws IOException;

        void onReadMessage(ByteString byteString) throws IOException;

        void onReadPing(ByteString byteString);

        void onReadPong(ByteString byteString);
    }

    WebSocketReader(boolean z, BufferedSource bufferedSource, FrameCallback frameCallback) {
        if (bufferedSource == null) {
            throw new NullPointerException("source == null");
        } else if (frameCallback == null) {
            throw new NullPointerException("frameCallback == null");
        } else {
            this.a = z;
            this.b = bufferedSource;
            this.c = frameCallback;
        }
    }

    void a() throws IOException {
        c();
        if (this.i) {
            d();
        } else {
            e();
        }
    }

    private void c() throws IOException {
        BufferedSource readByte;
        boolean z = true;
        TimeUnit timeUnit = null;
        if (this.d) {
            throw new IOException("closed");
        }
        boolean z2;
        long timeoutNanos = this.b.timeout().timeoutNanos();
        this.b.timeout().clearTimeout();
        try {
            readByte = this.b.readByte();
            int i = readByte & 255;
        } finally {
            BufferedSource bufferedSource = readByte;
            z = this.b.timeout();
            timeUnit = TimeUnit.NANOSECONDS;
            z.timeout(timeoutNanos, timeUnit);
        }
        if (readByte != null) {
            z2 = z;
        } else {
            z2 = timeUnit;
        }
        this.h = z2;
        if ((i & 8) != 0) {
            z2 = z;
        } else {
            z2 = timeUnit;
        }
        this.i = z2;
        if (!this.i || this.h) {
            boolean z3 = (i & 64) != 0 ? z : timeUnit;
            boolean z4;
            if ((i & 32) != 0) {
                z4 = z;
            } else {
                z4 = timeUnit;
            }
            if ((i & 16) != 0) {
                z2 = z;
            } else {
                z2 = timeUnit;
            }
            if (z3 || r3 || r0) {
                throw new ProtocolException("Reserved flags are unsupported.");
            }
            int readByte2 = this.b.readByte() & 255;
            if ((readByte2 & 128) == 0) {
                z = timeUnit;
            }
            this.j = z;
            if (this.j == this.a) {
                String str;
                if (this.a) {
                    str = "Server-sent frames must not be masked.";
                } else {
                    str = "Client-sent frames must be masked.";
                }
                throw new ProtocolException(str);
            }
            this.f = (long) (readByte2 & 127);
            if (this.f == 126) {
                this.f = ((long) this.b.readShort()) & 65535;
            } else if (this.f == 127) {
                this.f = this.b.readLong();
                if (this.f < 0) {
                    throw new ProtocolException("Frame length 0x" + Long.toHexString(this.f) + " > 0x7FFFFFFFFFFFFFFF");
                }
            }
            this.g = 0;
            if (this.i && this.f > 125) {
                throw new ProtocolException("Control frame must be less than 125B.");
            } else if (this.j) {
                this.b.readFully(this.k);
                return;
            } else {
                return;
            }
        }
        throw new ProtocolException("Control frames must be final.");
    }

    private void d() throws IOException {
        Buffer buffer = new Buffer();
        if (this.g < this.f) {
            if (this.a) {
                this.b.readFully(buffer, this.f);
            } else {
                while (this.g < this.f) {
                    int read = this.b.read(this.l, 0, (int) Math.min(this.f - this.g, (long) this.l.length));
                    if (read == -1) {
                        throw new EOFException();
                    }
                    WebSocketProtocol.a(this.l, (long) read, this.k, this.g);
                    buffer.write(this.l, 0, read);
                    this.g += (long) read;
                }
            }
        }
        switch (this.e) {
            case 8:
                int i = 1005;
                String str = "";
                long size = buffer.size();
                if (size == 1) {
                    throw new ProtocolException("Malformed close payload length of 1.");
                }
                if (size != 0) {
                    i = buffer.readShort();
                    str = buffer.readUtf8();
                    String a = WebSocketProtocol.a(i);
                    if (a != null) {
                        throw new ProtocolException(a);
                    }
                }
                this.c.onReadClose(i, str);
                this.d = true;
                return;
            case 9:
                this.c.onReadPing(buffer.readByteString());
                return;
            case 10:
                this.c.onReadPong(buffer.readByteString());
                return;
            default:
                throw new ProtocolException("Unknown control opcode: " + Integer.toHexString(this.e));
        }
    }

    private void e() throws IOException {
        int i = this.e;
        if (i == 1 || i == 2) {
            Buffer buffer = new Buffer();
            a(buffer);
            if (i == 1) {
                this.c.onReadMessage(buffer.readUtf8());
                return;
            } else {
                this.c.onReadMessage(buffer.readByteString());
                return;
            }
        }
        throw new ProtocolException("Unknown opcode: " + Integer.toHexString(i));
    }

    void b() throws IOException {
        while (!this.d) {
            c();
            if (this.i) {
                d();
            } else {
                return;
            }
        }
    }

    private void a(Buffer buffer) throws IOException {
        while (!this.d) {
            long read;
            if (this.g == this.f) {
                if (!this.h) {
                    b();
                    if (this.e != 0) {
                        throw new ProtocolException("Expected continuation opcode. Got: " + Integer.toHexString(this.e));
                    } else if (this.h && this.f == 0) {
                        return;
                    }
                }
                return;
            }
            long j = this.f - this.g;
            if (this.j) {
                read = (long) this.b.read(this.l, 0, (int) Math.min(j, (long) this.l.length));
                if (read == -1) {
                    throw new EOFException();
                }
                WebSocketProtocol.a(this.l, read, this.k, this.g);
                buffer.write(this.l, 0, (int) read);
            } else {
                read = this.b.read(buffer, j);
                if (read == -1) {
                    throw new EOFException();
                }
            }
            this.g += read;
        }
        throw new IOException("closed");
    }
}
