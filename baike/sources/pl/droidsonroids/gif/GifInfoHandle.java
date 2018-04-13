package pl.droidsonroids.gif;

import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

final class GifInfoHandle {
    final int a;
    final int b;
    final int c;
    private volatile long d;

    private static native void free(long j);

    private static native long getAllocationByteCount(long j);

    private static native String getComment(long j);

    private static native int getCurrentPosition(long j);

    private static native int getDuration(long j);

    private static native int getLoopCount(long j);

    private static native int getNativeErrorCode(long j);

    static native GifInfoHandle openByteArray(byte[] bArr, boolean z) throws GifIOException;

    static native GifInfoHandle openDirectByteBuffer(ByteBuffer byteBuffer, boolean z) throws GifIOException;

    static native GifInfoHandle openFd(FileDescriptor fileDescriptor, long j, boolean z) throws GifIOException;

    static native GifInfoHandle openFile(String str, boolean z) throws GifIOException;

    static native GifInfoHandle openStream(InputStream inputStream, boolean z) throws GifIOException;

    private static native long renderFrame(Bitmap bitmap, long j);

    private static native void reset(long j);

    private static native void restoreRemainder(long j);

    private static native void saveRemainder(long j);

    private static native void seekToFrame(long j, int i, Bitmap bitmap);

    private static native void seekToTime(long j, int i, Bitmap bitmap);

    private static native void setSpeedFactor(long j, float f);

    GifInfoHandle(long j, int i, int i2, int i3) {
        this.d = j;
        this.a = i;
        this.b = i2;
        this.c = i3;
    }

    static {
        System.loadLibrary("gif");
    }

    static GifInfoHandle a(InputStream inputStream, boolean z) throws GifIOException {
        if (inputStream.markSupported()) {
            return openStream(inputStream, z);
        }
        throw new IllegalArgumentException("InputStream does not support marking");
    }

    static GifInfoHandle a(AssetFileDescriptor assetFileDescriptor, boolean z) throws IOException {
        try {
            GifInfoHandle openFd = openFd(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), z);
            return openFd;
        } finally {
            assetFileDescriptor.close();
        }
    }

    synchronized long a(Bitmap bitmap) {
        return renderFrame(bitmap, this.d);
    }

    synchronized void a() {
        free(this.d);
        this.d = 0;
    }

    synchronized void b() {
        restoreRemainder(this.d);
    }

    synchronized void c() {
        reset(this.d);
    }

    synchronized void d() {
        saveRemainder(this.d);
    }

    synchronized String e() {
        return getComment(this.d);
    }

    synchronized int f() {
        return getLoopCount(this.d);
    }

    synchronized int g() {
        return getNativeErrorCode(this.d);
    }

    synchronized void a(float f) {
        setSpeedFactor(this.d, f);
    }

    synchronized int h() {
        return getDuration(this.d);
    }

    synchronized int i() {
        return getCurrentPosition(this.d);
    }

    synchronized void a(int i, Bitmap bitmap) {
        seekToTime(this.d, i, bitmap);
    }

    synchronized void b(int i, Bitmap bitmap) {
        seekToFrame(this.d, i, bitmap);
    }

    synchronized long j() {
        return getAllocationByteCount(this.d);
    }

    synchronized boolean k() {
        return this.d == 0;
    }

    protected void finalize() throws Throwable {
        try {
            a();
        } finally {
            super.finalize();
        }
    }
}
