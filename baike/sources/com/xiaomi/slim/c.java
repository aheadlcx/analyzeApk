package com.xiaomi.slim;

import android.text.TextUtils;
import com.alipay.sdk.util.h;
import com.xiaomi.channel.commonutils.misc.d;
import com.xiaomi.push.protobuf.b.b;
import com.xiaomi.push.protobuf.b.f;
import com.xiaomi.push.service.ak;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.zip.Adler32;

class c {
    private ByteBuffer a = ByteBuffer.allocate(2048);
    private ByteBuffer b = ByteBuffer.allocate(4);
    private Adler32 c = new Adler32();
    private e d;
    private InputStream e;
    private f f;
    private volatile boolean g;

    c(InputStream inputStream, f fVar) {
        this.e = new BufferedInputStream(inputStream);
        this.f = fVar;
        this.d = new e();
    }

    private void a(ByteBuffer byteBuffer, int i) {
        int position = byteBuffer.position();
        do {
            int read = this.e.read(byteBuffer.array(), position, i);
            if (read != -1) {
                i -= read;
                position += read;
            } else {
                throw new EOFException();
            }
        } while (i > 0);
        byteBuffer.position(position);
    }

    private void d() {
        boolean z = false;
        this.g = false;
        b c = c();
        if ("CONN".equals(c.a())) {
            f b = f.b(c.k());
            if (b.e()) {
                this.f.a(b.d());
                z = true;
            }
            if (b.h()) {
                b i = b.i();
                b bVar = new b();
                bVar.a("SYNC", "CONF");
                bVar.a(i.c(), null);
                this.f.a(bVar);
            }
            com.xiaomi.channel.commonutils.logger.b.a("[Slim] CONN: host = " + b.f());
        }
        if (z) {
            while (!this.g) {
                c = c();
                this.f.n();
                switch (c.m()) {
                    case (short) 1:
                        this.f.a(c);
                        break;
                    case (short) 2:
                        if (!"SECMSG".equals(c.a()) || !TextUtils.isEmpty(c.b())) {
                            this.f.a(c);
                            break;
                        }
                        try {
                            this.f.b(this.d.a(c.d(ak.a().b(Integer.valueOf(c.c()).toString(), c.j()).i), this.f));
                            break;
                        } catch (Exception e) {
                            com.xiaomi.channel.commonutils.logger.b.a("[Slim] Parse packet from Blob " + c.toString() + " failure:" + e.getMessage());
                            break;
                        }
                        break;
                    case (short) 3:
                        try {
                            this.f.b(this.d.a(c.k(), this.f));
                            break;
                        } catch (Exception e2) {
                            com.xiaomi.channel.commonutils.logger.b.a("[Slim] Parse packet from Blob " + c.toString() + " failure:" + e2.getMessage());
                            break;
                        }
                    default:
                        com.xiaomi.channel.commonutils.logger.b.a("[Slim] unknow blob type " + c.m());
                        break;
                }
            }
            return;
        }
        com.xiaomi.channel.commonutils.logger.b.a("[Slim] Invalid CONN");
        throw new IOException("Invalid Connection");
    }

    private ByteBuffer e() {
        if (this.a.capacity() > 4096) {
            this.a = ByteBuffer.allocate(2048);
        }
        this.a.clear();
        a(this.a, b.n());
        int c = b.c(this.a.asReadOnlyBuffer());
        if (c > 32768) {
            throw new IOException("Blob size too large");
        }
        if (c + 4 > this.a.remaining()) {
            ByteBuffer allocate = ByteBuffer.allocate(b.n() + c);
            allocate.put(this.a.array(), 0, this.a.arrayOffset() + this.a.position());
            this.a = allocate;
        }
        a(this.a, c);
        this.b.clear();
        a(this.b, 4);
        this.b.position(0);
        c = this.b.getInt();
        this.c.reset();
        this.c.update(this.a.array(), 0, this.a.position());
        if (c == ((int) this.c.getValue())) {
            return this.a;
        }
        com.xiaomi.channel.commonutils.logger.b.a("CRC = " + ((int) this.c.getValue()) + " and " + c);
        throw new IOException("Corrupted Blob bad CRC");
    }

    void a() {
        try {
            d();
        } catch (IOException e) {
            if (!this.g) {
                throw e;
            }
        }
    }

    void b() {
        this.g = true;
    }

    b c() {
        int i;
        IOException iOException;
        StringBuilder append;
        byte[] array;
        try {
            ByteBuffer e = e();
            int position = e.position();
            try {
                e.flip();
                b b = b.b(e);
                com.xiaomi.channel.commonutils.logger.b.c("[Slim] Read {cmd=" + b.a() + ";chid=" + b.c() + ";len=" + position + h.d);
                return b;
            } catch (IOException e2) {
                IOException iOException2 = e2;
                i = position;
                iOException = iOException2;
                if (i == 0) {
                    i = this.a.position();
                }
                append = new StringBuilder().append("[Slim] read Blob [");
                array = this.a.array();
                if (i > b.n()) {
                    i = b.n();
                }
                com.xiaomi.channel.commonutils.logger.b.a(append.append(d.a(array, 0, i)).append("] Err:").append(iOException.getMessage()).toString());
                throw iOException;
            }
        } catch (IOException e22) {
            iOException = e22;
            i = 0;
            if (i == 0) {
                i = this.a.position();
            }
            append = new StringBuilder().append("[Slim] read Blob [");
            array = this.a.array();
            if (i > b.n()) {
                i = b.n();
            }
            com.xiaomi.channel.commonutils.logger.b.a(append.append(d.a(array, 0, i)).append("] Err:").append(iOException.getMessage()).toString());
            throw iOException;
        }
    }
}
