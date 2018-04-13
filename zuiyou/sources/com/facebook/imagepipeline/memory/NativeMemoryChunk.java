package com.facebook.imagepipeline.memory;

import android.util.Log;
import com.facebook.common.internal.d;
import com.facebook.common.internal.g;
import com.facebook.imagepipeline.nativecode.a;
import java.io.Closeable;

@d
public class NativeMemoryChunk implements Closeable {
    private static final String TAG = "NativeMemoryChunk";
    private boolean mClosed;
    private final long mNativePtr;
    private final int mSize;

    @d
    private static native long nativeAllocate(int i);

    @d
    private static native void nativeCopyFromByteArray(long j, byte[] bArr, int i, int i2);

    @d
    private static native void nativeCopyToByteArray(long j, byte[] bArr, int i, int i2);

    @d
    private static native void nativeFree(long j);

    @d
    private static native void nativeMemcpy(long j, long j2, int i);

    @d
    private static native byte nativeReadByte(long j);

    static {
        a.a();
    }

    public NativeMemoryChunk(int i) {
        boolean z;
        if (i > 0) {
            z = true;
        } else {
            z = false;
        }
        g.a(z);
        this.mSize = i;
        this.mNativePtr = nativeAllocate(this.mSize);
        this.mClosed = false;
    }

    public NativeMemoryChunk() {
        this.mSize = 0;
        this.mNativePtr = 0;
        this.mClosed = true;
    }

    public synchronized void close() {
        if (!this.mClosed) {
            this.mClosed = true;
            nativeFree(this.mNativePtr);
        }
    }

    public synchronized boolean isClosed() {
        return this.mClosed;
    }

    public int getSize() {
        return this.mSize;
    }

    public synchronized int write(int i, byte[] bArr, int i2, int i3) {
        int adjustByteCount;
        g.a((Object) bArr);
        g.b(!isClosed());
        adjustByteCount = adjustByteCount(i, i3);
        checkBounds(i, bArr.length, i2, adjustByteCount);
        nativeCopyFromByteArray(this.mNativePtr + ((long) i), bArr, i2, adjustByteCount);
        return adjustByteCount;
    }

    public synchronized int read(int i, byte[] bArr, int i2, int i3) {
        int adjustByteCount;
        g.a((Object) bArr);
        g.b(!isClosed());
        adjustByteCount = adjustByteCount(i, i3);
        checkBounds(i, bArr.length, i2, adjustByteCount);
        nativeCopyToByteArray(this.mNativePtr + ((long) i), bArr, i2, adjustByteCount);
        return adjustByteCount;
    }

    public synchronized byte read(int i) {
        byte nativeReadByte;
        boolean z = true;
        synchronized (this) {
            boolean z2;
            if (isClosed()) {
                z2 = false;
            } else {
                z2 = true;
            }
            g.b(z2);
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
            nativeReadByte = nativeReadByte(this.mNativePtr + ((long) i));
        }
        return nativeReadByte;
    }

    public void copy(int i, NativeMemoryChunk nativeMemoryChunk, int i2, int i3) {
        g.a((Object) nativeMemoryChunk);
        if (nativeMemoryChunk.mNativePtr == this.mNativePtr) {
            Log.w(TAG, "Copying from NativeMemoryChunk " + Integer.toHexString(System.identityHashCode(this)) + " to NativeMemoryChunk " + Integer.toHexString(System.identityHashCode(nativeMemoryChunk)) + " which share the same address " + Long.toHexString(this.mNativePtr));
            g.a(false);
        }
        if (nativeMemoryChunk.mNativePtr < this.mNativePtr) {
            synchronized (nativeMemoryChunk) {
                synchronized (this) {
                    doCopy(i, nativeMemoryChunk, i2, i3);
                }
            }
            return;
        }
        synchronized (this) {
            synchronized (nativeMemoryChunk) {
                doCopy(i, nativeMemoryChunk, i2, i3);
            }
        }
    }

    public long getNativePtr() {
        return this.mNativePtr;
    }

    private void doCopy(int i, NativeMemoryChunk nativeMemoryChunk, int i2, int i3) {
        boolean z;
        boolean z2 = true;
        if (isClosed()) {
            z = false;
        } else {
            z = true;
        }
        g.b(z);
        if (nativeMemoryChunk.isClosed()) {
            z2 = false;
        }
        g.b(z2);
        checkBounds(i, nativeMemoryChunk.mSize, i2, i3);
        nativeMemcpy(nativeMemoryChunk.mNativePtr + ((long) i2), this.mNativePtr + ((long) i), i3);
    }

    protected void finalize() throws Throwable {
        if (!isClosed()) {
            Log.w(TAG, "finalize: Chunk " + Integer.toHexString(System.identityHashCode(this)) + " still active. Underlying address = " + Long.toHexString(this.mNativePtr));
            try {
                close();
            } finally {
                super.finalize();
            }
        }
    }

    private int adjustByteCount(int i, int i2) {
        return Math.min(Math.max(0, this.mSize - i), i2);
    }

    private void checkBounds(int i, int i2, int i3, int i4) {
        boolean z;
        boolean z2 = true;
        g.a(i4 >= 0);
        if (i >= 0) {
            z = true;
        } else {
            z = false;
        }
        g.a(z);
        if (i3 >= 0) {
            z = true;
        } else {
            z = false;
        }
        g.a(z);
        if (i + i4 <= this.mSize) {
            z = true;
        } else {
            z = false;
        }
        g.a(z);
        if (i3 + i4 > i2) {
            z2 = false;
        }
        g.a(z2);
    }
}
