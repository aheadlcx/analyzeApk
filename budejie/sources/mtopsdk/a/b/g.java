package mtopsdk.a.b;

import com.taobao.tao.remotebusiness.listener.c;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import mtopsdk.common.util.m;

public abstract class g {
    private byte[] a = null;

    private byte[] d() {
        Closeable dataInputStream;
        Closeable byteArrayOutputStream;
        Throwable e;
        Throwable th;
        long a = a();
        if (a > 2147483647L) {
            throw new IOException("Cannot buffer entire body for content length: " + a);
        }
        byte[] bArr;
        try {
            dataInputStream = new DataInputStream(b());
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Exception e2) {
                e = e2;
                byteArrayOutputStream = null;
                try {
                    m.b("mtopsdk.ResponseBody", "[readBytes] read bytes from byteStream error.", e);
                    c.a(dataInputStream);
                    c.a(byteArrayOutputStream);
                    bArr = null;
                    if (bArr != null) {
                        return null;
                    }
                    if (a != -1) {
                    }
                    return bArr;
                } catch (Throwable th2) {
                    th = th2;
                    c.a(dataInputStream);
                    c.a(byteArrayOutputStream);
                    throw th;
                }
            } catch (Throwable e3) {
                byteArrayOutputStream = null;
                th = e3;
                c.a(dataInputStream);
                c.a(byteArrayOutputStream);
                throw th;
            }
            try {
                bArr = new byte[1024];
                while (true) {
                    int read = dataInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byteArrayOutputStream.flush();
                bArr = byteArrayOutputStream.toByteArray();
                c.a(dataInputStream);
                c.a(byteArrayOutputStream);
            } catch (Exception e4) {
                e3 = e4;
                m.b("mtopsdk.ResponseBody", "[readBytes] read bytes from byteStream error.", e3);
                c.a(dataInputStream);
                c.a(byteArrayOutputStream);
                bArr = null;
                if (bArr != null) {
                    return null;
                }
                if (a != -1) {
                }
                return bArr;
            }
        } catch (Exception e5) {
            e3 = e5;
            byteArrayOutputStream = null;
            dataInputStream = null;
            m.b("mtopsdk.ResponseBody", "[readBytes] read bytes from byteStream error.", e3);
            c.a(dataInputStream);
            c.a(byteArrayOutputStream);
            bArr = null;
            if (bArr != null) {
                return null;
            }
            if (a != -1) {
            }
            return bArr;
        } catch (Throwable e32) {
            byteArrayOutputStream = null;
            dataInputStream = null;
            th = e32;
            c.a(dataInputStream);
            c.a(byteArrayOutputStream);
            throw th;
        }
        if (bArr != null) {
            return null;
        }
        if (a != -1 || a == ((long) bArr.length)) {
            return bArr;
        }
        throw new IOException("Content-Length and stream length disagree");
    }

    public abstract long a();

    public abstract InputStream b();

    public final byte[] c() {
        if (this.a == null) {
            this.a = d();
        }
        return this.a;
    }
}
