package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.k;
import com.facebook.common.memory.g;
import com.facebook.common.memory.j;
import com.facebook.common.references.a;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class NativePooledByteBufferFactory implements g {
    private final NativeMemoryChunkPool mPool;
    private final j mPooledByteStreams;

    public NativePooledByteBufferFactory(NativeMemoryChunkPool nativeMemoryChunkPool, j jVar) {
        this.mPool = nativeMemoryChunkPool;
        this.mPooledByteStreams = jVar;
    }

    public NativePooledByteBuffer newByteBuffer(int i) {
        com.facebook.common.internal.g.a(i > 0);
        a a = a.a(this.mPool.get(i), this.mPool);
        try {
            NativePooledByteBuffer nativePooledByteBuffer = new NativePooledByteBuffer(a, i);
            return nativePooledByteBuffer;
        } finally {
            a.close();
        }
    }

    public NativePooledByteBuffer newByteBuffer(InputStream inputStream) throws IOException {
        NativePooledByteBufferOutputStream nativePooledByteBufferOutputStream = new NativePooledByteBufferOutputStream(this.mPool);
        try {
            NativePooledByteBuffer newByteBuf = newByteBuf(inputStream, nativePooledByteBufferOutputStream);
            return newByteBuf;
        } finally {
            nativePooledByteBufferOutputStream.close();
        }
    }

    public NativePooledByteBuffer newByteBuffer(byte[] bArr) {
        NativePooledByteBufferOutputStream nativePooledByteBufferOutputStream = new NativePooledByteBufferOutputStream(this.mPool, bArr.length);
        try {
            nativePooledByteBufferOutputStream.write(bArr, 0, bArr.length);
            NativePooledByteBuffer toByteBuffer = nativePooledByteBufferOutputStream.toByteBuffer();
            nativePooledByteBufferOutputStream.close();
            return toByteBuffer;
        } catch (Throwable e) {
            throw k.b(e);
        } catch (Throwable th) {
            nativePooledByteBufferOutputStream.close();
        }
    }

    public NativePooledByteBuffer newByteBuffer(InputStream inputStream, int i) throws IOException {
        NativePooledByteBufferOutputStream nativePooledByteBufferOutputStream = new NativePooledByteBufferOutputStream(this.mPool, i);
        try {
            NativePooledByteBuffer newByteBuf = newByteBuf(inputStream, nativePooledByteBufferOutputStream);
            return newByteBuf;
        } finally {
            nativePooledByteBufferOutputStream.close();
        }
    }

    NativePooledByteBuffer newByteBuf(InputStream inputStream, NativePooledByteBufferOutputStream nativePooledByteBufferOutputStream) throws IOException {
        this.mPooledByteStreams.a(inputStream, nativePooledByteBufferOutputStream);
        return nativePooledByteBufferOutputStream.toByteBuffer();
    }

    public NativePooledByteBufferOutputStream newOutputStream() {
        return new NativePooledByteBufferOutputStream(this.mPool);
    }

    public NativePooledByteBufferOutputStream newOutputStream(int i) {
        return new NativePooledByteBufferOutputStream(this.mPool, i);
    }
}
