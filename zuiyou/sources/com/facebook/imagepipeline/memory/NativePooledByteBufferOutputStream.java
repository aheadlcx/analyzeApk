package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.g;
import com.facebook.common.memory.i;
import com.facebook.common.references.a;
import java.io.IOException;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class NativePooledByteBufferOutputStream extends i {
    private a<NativeMemoryChunk> mBufRef;
    private int mCount;
    private final NativeMemoryChunkPool mPool;

    public static class InvalidStreamException extends RuntimeException {
        public InvalidStreamException() {
            super("OutputStream no longer valid");
        }
    }

    public NativePooledByteBufferOutputStream(NativeMemoryChunkPool nativeMemoryChunkPool) {
        this(nativeMemoryChunkPool, nativeMemoryChunkPool.getMinBufferSize());
    }

    public NativePooledByteBufferOutputStream(NativeMemoryChunkPool nativeMemoryChunkPool, int i) {
        boolean z;
        if (i > 0) {
            z = true;
        } else {
            z = false;
        }
        g.a(z);
        this.mPool = (NativeMemoryChunkPool) g.a((Object) nativeMemoryChunkPool);
        this.mCount = 0;
        this.mBufRef = a.a(this.mPool.get(i), this.mPool);
    }

    public NativePooledByteBuffer toByteBuffer() {
        ensureValid();
        return new NativePooledByteBuffer(this.mBufRef, this.mCount);
    }

    public int size() {
        return this.mCount;
    }

    public void write(int i) throws IOException {
        write(new byte[]{(byte) i});
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new ArrayIndexOutOfBoundsException("length=" + bArr.length + "; regionStart=" + i + "; regionLength=" + i2);
        }
        ensureValid();
        realloc(this.mCount + i2);
        ((NativeMemoryChunk) this.mBufRef.a()).write(this.mCount, bArr, i, i2);
        this.mCount += i2;
    }

    public void close() {
        a.c(this.mBufRef);
        this.mBufRef = null;
        this.mCount = -1;
        super.close();
    }

    void realloc(int i) {
        ensureValid();
        if (i > ((NativeMemoryChunk) this.mBufRef.a()).getSize()) {
            NativeMemoryChunk nativeMemoryChunk = (NativeMemoryChunk) this.mPool.get(i);
            ((NativeMemoryChunk) this.mBufRef.a()).copy(0, nativeMemoryChunk, 0, this.mCount);
            this.mBufRef.close();
            this.mBufRef = a.a(nativeMemoryChunk, this.mPool);
        }
    }

    private void ensureValid() {
        if (!a.a(this.mBufRef)) {
            throw new InvalidStreamException();
        }
    }
}
