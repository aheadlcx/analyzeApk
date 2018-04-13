package com.crashlytics.android.internal;

import com.facebook.cache.disk.DefaultDiskStorage.FileType;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: com.crashlytics.android.internal.aq */
public class C0010aq implements Closeable {
    private static final Logger a = Logger.getLogger(C0010aq.class.getName());
    private final RandomAccessFile b;
    private int c;
    private int d;
    private bw e;
    private bw f;
    private final byte[] g = new byte[16];

    public C0010aq(File file) throws IOException {
        if (!file.exists()) {
            File file2 = new File(file.getPath() + FileType.TEMP);
            RandomAccessFile a = C0010aq.a(file2);
            try {
                a.setLength(4096);
                a.seek(0);
                byte[] bArr = new byte[16];
                C0010aq.a(bArr, 4096, 0, 0, 0);
                a.write(bArr);
                if (!file2.renameTo(file)) {
                    throw new IOException("Rename failed!");
                }
            } finally {
                a.close();
            }
        }
        this.b = C0010aq.a(file);
        this.b.seek(0);
        this.b.readFully(this.g);
        this.c = C0010aq.a(this.g, 0);
        if (((long) this.c) > this.b.length()) {
            throw new IOException("File is truncated. Expected length: " + this.c + ", Actual length: " + this.b.length());
        }
        this.d = C0010aq.a(this.g, 4);
        int a2 = C0010aq.a(this.g, 8);
        int a3 = C0010aq.a(this.g, 12);
        this.e = a(a2);
        this.f = a(a3);
    }

    private static void a(byte[] bArr, int i, int i2) {
        bArr[i] = i2 >> 24;
        bArr[i + 1] = (byte) (i2 >> 16);
        bArr[i + 2] = (byte) (i2 >> 8);
        bArr[i + 3] = (byte) i2;
    }

    private static void a(byte[] bArr, int... iArr) {
        int i = 0;
        int length = iArr.length;
        int i2 = 0;
        while (i < length) {
            C0010aq.a(bArr, i2, iArr[i]);
            i2 += 4;
            i++;
        }
    }

    private static int a(byte[] bArr, int i) {
        return ((((bArr[i] & 255) << 24) + ((bArr[i + 1] & 255) << 16)) + ((bArr[i + 2] & 255) << 8)) + (bArr[i + 3] & 255);
    }

    private void a(int i, int i2, int i3, int i4) throws IOException {
        C0010aq.a(this.g, i, i2, i3, i4);
        this.b.seek(0);
        this.b.write(this.g);
    }

    private bw a(int i) throws IOException {
        if (i == 0) {
            return bw.a;
        }
        this.b.seek((long) i);
        return new bw(i, this.b.readInt());
    }

    private static RandomAccessFile a(File file) throws FileNotFoundException {
        return new RandomAccessFile(file, "rwd");
    }

    private int b(int i) {
        return i < this.c ? i : (i + 16) - this.c;
    }

    private void a(int i, byte[] bArr, int i2, int i3) throws IOException {
        int b = b(i);
        if (b + i3 <= this.c) {
            this.b.seek((long) b);
            this.b.write(bArr, i2, i3);
            return;
        }
        int i4 = this.c - b;
        this.b.seek((long) b);
        this.b.write(bArr, i2, i4);
        this.b.seek(16);
        this.b.write(bArr, i2 + i4, i3 - i4);
    }

    private void b(int i, byte[] bArr, int i2, int i3) throws IOException {
        int b = b(i);
        if (b + i3 <= this.c) {
            this.b.seek((long) b);
            this.b.readFully(bArr, i2, i3);
            return;
        }
        int i4 = this.c - b;
        this.b.seek((long) b);
        this.b.readFully(bArr, i2, i4);
        this.b.seek(16);
        this.b.readFully(bArr, i2 + i4, i3 - i4);
    }

    public final void a(byte[] bArr) throws IOException {
        b(bArr, 0, bArr.length);
    }

    private synchronized void b(byte[] bArr, int i, int i2) throws IOException {
        C0010aq.b(bArr, "buffer");
        if ((i2 | 0) < 0 || i2 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        int i3;
        c(i2);
        boolean b = b();
        if (b) {
            i3 = 16;
        } else {
            i3 = b((this.f.b + 4) + this.f.c);
        }
        bw bwVar = new bw(i3, i2);
        C0010aq.a(this.g, 0, i2);
        a(bwVar.b, this.g, 0, 4);
        a(bwVar.b + 4, bArr, 0, i2);
        a(this.c, this.d + 1, b ? bwVar.b : this.e.b, bwVar.b);
        this.f = bwVar;
        this.d++;
        if (b) {
            this.e = this.f;
        }
    }

    public final int a() {
        if (this.d == 0) {
            return 16;
        }
        if (this.f.b >= this.e.b) {
            return (((this.f.b - this.e.b) + 4) + this.f.c) + 16;
        }
        return (((this.f.b + 4) + this.f.c) + this.c) - this.e.b;
    }

    public final synchronized boolean b() {
        return this.d == 0;
    }

    private void c(int i) throws IOException {
        int i2 = i + 4;
        int a = this.c - a();
        if (a < i2) {
            int i3 = this.c;
            do {
                a += i3;
                i3 <<= 1;
            } while (a < i2);
            d(i3);
            i2 = b((this.f.b + 4) + this.f.c);
            if (i2 < this.e.b) {
                FileChannel channel = this.b.getChannel();
                channel.position((long) this.c);
                int i4 = i2 - 4;
                if (channel.transferTo(16, (long) i4, channel) != ((long) i4)) {
                    throw new AssertionError("Copied insufficient number of bytes!");
                }
            }
            if (this.f.b < this.e.b) {
                a = (this.c + this.f.b) - 16;
                a(i3, this.d, this.e.b, a);
                this.f = new bw(a, this.f.c);
            } else {
                a(i3, this.d, this.e.b, this.f.b);
            }
            this.c = i3;
        }
    }

    private void d(int i) throws IOException {
        this.b.setLength((long) i);
        this.b.getChannel().force(true);
    }

    public final synchronized void a(C0000au c0000au) throws IOException {
        synchronized (this) {
            int i = this.e.b;
            for (int i2 = 0; i2 < this.d; i2++) {
                bw a = a(i);
                c0000au.a(new bx(this, a, (byte) 0), a.c);
                i = b(a.c + (a.b + 4));
            }
        }
    }

    private static <T> T b(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public final synchronized void c() throws IOException {
        if (b()) {
            throw new NoSuchElementException();
        } else if (this.d == 1) {
            d();
        } else {
            int b = b((this.e.b + 4) + this.e.c);
            b(b, this.g, 0, 4);
            int a = C0010aq.a(this.g, 0);
            a(this.c, this.d - 1, b, this.f.b);
            this.d--;
            this.e = new bw(b, a);
        }
    }

    private synchronized void d() throws IOException {
        a(4096, 0, 0, 0);
        this.d = 0;
        this.e = bw.a;
        this.f = bw.a;
        if (this.c > 4096) {
            d(4096);
        }
        this.c = 4096;
    }

    public synchronized void close() throws IOException {
        this.b.close();
    }

    public final boolean a(int i, int i2) {
        return (a() + 4) + i <= i2;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName()).append('[');
        stringBuilder.append("fileLength=").append(this.c);
        stringBuilder.append(", size=").append(this.d);
        stringBuilder.append(", first=").append(this.e);
        stringBuilder.append(", last=").append(this.f);
        stringBuilder.append(", element lengths=[");
        try {
            a(new bv(this, stringBuilder));
        } catch (Throwable e) {
            a.log(Level.WARNING, "read error", e);
        }
        stringBuilder.append("]]");
        return stringBuilder.toString();
    }
}
