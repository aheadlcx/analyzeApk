package pl.droidsonroids.gif;

import android.graphics.Bitmap;
import android.support.annotation.IntRange;
import android.view.Surface;
import java.io.FileDescriptor;
import java.io.InputStream;
import java.nio.ByteBuffer;

final class GifInfoHandle {
    private volatile long a;

    private static native void bindSurface(long j, Surface surface, long[] jArr);

    private static native void free(long j);

    private static native long getAllocationByteCount(long j);

    private static native String getComment(long j);

    private static native int getCurrentFrameIndex(long j);

    private static native int getCurrentLoop(long j);

    private static native int getCurrentPosition(long j);

    private static native int getDuration(long j);

    private static native int getFrameDuration(long j, int i);

    private static native int getHeight(long j);

    private static native int getLoopCount(long j);

    private static native long getMetadataByteCount(long j);

    private static native int getNativeErrorCode(long j);

    private static native int getNumberOfFrames(long j);

    private static native long[] getSavedState(long j);

    private static native long getSourceLength(long j);

    private static native int getWidth(long j);

    private static native void glTexImage2D(long j, int i, int i2);

    private static native void glTexSubImage2D(long j, int i, int i2);

    private static native void initTexImageDescriptor(long j);

    private static native boolean isAnimationCompleted(long j);

    private static native boolean isOpaque(long j);

    static native long openByteArray(byte[] bArr) throws GifIOException;

    static native long openDirectByteBuffer(ByteBuffer byteBuffer) throws GifIOException;

    static native long openFd(FileDescriptor fileDescriptor, long j) throws GifIOException;

    static native long openFile(String str) throws GifIOException;

    static native long openStream(InputStream inputStream) throws GifIOException;

    private static native void postUnbindSurface(long j);

    private static native long renderFrame(long j, Bitmap bitmap);

    private static native boolean reset(long j);

    private static native long restoreRemainder(long j);

    private static native int restoreSavedState(long j, long[] jArr, Bitmap bitmap);

    private static native void saveRemainder(long j);

    private static native void seekToFrame(long j, int i, Bitmap bitmap);

    private static native void seekToFrameGL(long j, int i);

    private static native void seekToTime(long j, int i, Bitmap bitmap);

    private static native void setLoopCount(long j, char c);

    private static native void setOptions(long j, char c, boolean z);

    private static native void setSpeedFactor(long j, float f);

    private static native void startDecoderThread(long j);

    private static native void stopDecoderThread(long j);

    static {
        e.a(null);
    }

    GifInfoHandle() {
    }

    GifInfoHandle(String str) throws GifIOException {
        this.a = openFile(str);
    }

    synchronized long a(Bitmap bitmap) {
        return renderFrame(this.a, bitmap);
    }

    synchronized void a() {
        free(this.a);
        this.a = 0;
    }

    synchronized long b() {
        return restoreRemainder(this.a);
    }

    synchronized boolean c() {
        return reset(this.a);
    }

    synchronized void d() {
        saveRemainder(this.a);
    }

    synchronized int e() {
        return getLoopCount(this.a);
    }

    synchronized int f() {
        return getNativeErrorCode(this.a);
    }

    synchronized int g() {
        return getDuration(this.a);
    }

    synchronized int h() {
        return getCurrentPosition(this.a);
    }

    synchronized int i() {
        return getCurrentFrameIndex(this.a);
    }

    synchronized int j() {
        return getCurrentLoop(this.a);
    }

    synchronized void a(@IntRange(from = 0, to = 2147483647L) int i, Bitmap bitmap) {
        seekToTime(this.a, i, bitmap);
    }

    synchronized boolean k() {
        return this.a == 0;
    }

    protected void finalize() throws Throwable {
        try {
            a();
        } finally {
            super.finalize();
        }
    }

    synchronized int a(@IntRange(from = 0) int i) {
        b(i);
        return getFrameDuration(this.a, i);
    }

    synchronized int l() {
        return getWidth(this.a);
    }

    synchronized int m() {
        return getHeight(this.a);
    }

    synchronized int n() {
        return getNumberOfFrames(this.a);
    }

    synchronized boolean o() {
        return isOpaque(this.a);
    }

    private void b(@IntRange(from = 0) int i) {
        float numberOfFrames = (float) getNumberOfFrames(this.a);
        if (i < 0 || ((float) i) >= numberOfFrames) {
            throw new IndexOutOfBoundsException("Frame index is not in range <0;" + numberOfFrames + '>');
        }
    }
}
