package com.danikula.videocache.a;

import com.danikula.videocache.ProxyCacheException;
import com.danikula.videocache.a;
import java.io.File;
import java.io.RandomAccessFile;

public class b implements a {
    public File a;
    private final a b;
    private RandomAccessFile c;

    public b(File file, a aVar) throws ProxyCacheException {
        if (aVar == null) {
            try {
                throw new NullPointerException();
            } catch (Throwable e) {
                throw new ProxyCacheException("Error using file " + file + " as disc cache", e);
            }
        }
        this.b = aVar;
        d.a(file.getParentFile());
        boolean exists = file.exists();
        this.a = exists ? file : new File(file.getParentFile(), file.getName() + ".download");
        this.c = new RandomAccessFile(this.a, exists ? "r" : "rw");
    }

    public synchronized long a() throws ProxyCacheException {
        try {
        } catch (Throwable e) {
            throw new ProxyCacheException("Error reading length of file " + this.a, e);
        }
        return (long) ((int) this.c.length());
    }

    public synchronized int a(byte[] bArr, long j, int i) throws ProxyCacheException {
        try {
            this.c.seek(j);
        } catch (Throwable e) {
            throw new ProxyCacheException(String.format("Error reading %d bytes with offset %d from file[%d bytes] to buffer[%d bytes]", new Object[]{Integer.valueOf(i), Long.valueOf(j), Long.valueOf(a()), Integer.valueOf(bArr.length)}), e);
        }
        return this.c.read(bArr, 0, i);
    }

    public synchronized void a(byte[] bArr, int i) throws ProxyCacheException {
        try {
            if (d()) {
                throw new ProxyCacheException("Error append cache: cache file " + this.a + " is completed!");
            }
            this.c.seek(a());
            this.c.write(bArr, 0, i);
        } catch (Throwable e) {
            throw new ProxyCacheException(String.format("Error writing %d bytes to %s from buffer with size %d", new Object[]{Integer.valueOf(i), this.c, Integer.valueOf(bArr.length)}), e);
        }
    }

    public synchronized void b() throws ProxyCacheException {
        try {
            this.c.close();
            this.b.a(this.a);
        } catch (Throwable e) {
            throw new ProxyCacheException("Error closing file " + this.a, e);
        }
    }

    public synchronized void c() throws ProxyCacheException {
        if (!d()) {
            b();
            File file = new File(this.a.getParentFile(), this.a.getName().substring(0, this.a.getName().length() - ".download".length()));
            if (this.a.renameTo(file)) {
                this.a = file;
                try {
                    this.c = new RandomAccessFile(this.a, "r");
                    this.b.a(this.a);
                } catch (Throwable e) {
                    throw new ProxyCacheException("Error opening " + this.a + " as disc cache", e);
                }
            }
            throw new ProxyCacheException("Error renaming file " + this.a + " to " + file + " for completion!");
        }
    }

    public synchronized boolean d() {
        return !a(this.a);
    }

    public File e() {
        return this.a;
    }

    private boolean a(File file) {
        return file.getName().endsWith(".download");
    }
}
