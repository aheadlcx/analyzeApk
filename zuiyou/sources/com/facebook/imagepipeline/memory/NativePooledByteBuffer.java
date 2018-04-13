package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.g;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBuffer.ClosedException;
import com.facebook.common.references.a;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class NativePooledByteBuffer implements PooledByteBuffer {
    @GuardedBy
    a<NativeMemoryChunk> mBufRef;
    private final int mSize;

    public NativePooledByteBuffer(a<NativeMemoryChunk> aVar, int i) {
        g.a((Object) aVar);
        boolean z = i >= 0 && i <= ((NativeMemoryChunk) aVar.a()).getSize();
        g.a(z);
        this.mBufRef = aVar.b();
        this.mSize = i;
    }

    public synchronized int size() {
        ensureValid();
        return this.mSize;
    }

    public synchronized byte read(int i) {
        byte read;
        boolean z = true;
        synchronized (this) {
            boolean z2;
            ensureValid();
            if (i >= 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            g.a(z2);
            if (i >= this.mSize) {
                z = false;
            }
            g.a(z);
            read = ((NativeMemoryChunk) this.mBufRef.a()).read(i);
        }
        return read;
    }

    public synchronized void read(int i, byte[] bArr, int i2, int i3) {
        ensureValid();
        g.a(i + i3 <= this.mSize);
        ((NativeMemoryChunk) this.mBufRef.a()).read(i, bArr, i2, i3);
    }

    public synchronized long getNativePtr() {
        ensureValid();
        return ((NativeMemoryChunk) this.mBufRef.a()).getNativePtr();
    }

    public synchronized boolean isClosed() {
        return !a.a(this.mBufRef);
    }

    public synchronized void close() {
        a.c(this.mBufRef);
        this.mBufRef = null;
    }

    synchronized void ensureValid() {
        if (isClosed()) {
            throw new ClosedException();
        }
    }
}
