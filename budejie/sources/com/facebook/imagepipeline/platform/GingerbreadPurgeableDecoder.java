package com.facebook.imagepipeline.platform;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.os.MemoryFile;
import com.facebook.common.internal.ByteStreams;
import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferInputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.streams.LimitedInputStream;
import com.facebook.common.webp.WebpSupportStatus;
import com.facebook.imagepipeline.image.EncodedImage;
import java.io.Closeable;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

public class GingerbreadPurgeableDecoder extends DalvikPurgeableDecoder {
    private static Method sGetFileDescriptorMethod;

    public /* bridge */ /* synthetic */ CloseableReference decodeFromEncodedImage(EncodedImage encodedImage, Config config) {
        return super.decodeFromEncodedImage(encodedImage, config);
    }

    public /* bridge */ /* synthetic */ CloseableReference decodeJPEGFromEncodedImage(EncodedImage encodedImage, Config config, int i) {
        return super.decodeJPEGFromEncodedImage(encodedImage, config, i);
    }

    public /* bridge */ /* synthetic */ CloseableReference pinBitmap(Bitmap bitmap) {
        return super.pinBitmap(bitmap);
    }

    protected Bitmap decodeByteArrayAsPurgeable(CloseableReference<PooledByteBuffer> closeableReference, Options options) {
        return decodeFileDescriptorAsPurgeable(closeableReference, ((PooledByteBuffer) closeableReference.get()).size(), null, options);
    }

    protected Bitmap decodeJPEGByteArrayAsPurgeable(CloseableReference<PooledByteBuffer> closeableReference, int i, Options options) {
        return decodeFileDescriptorAsPurgeable(closeableReference, i, DalvikPurgeableDecoder.endsWithEOI(closeableReference, i) ? null : EOI, options);
    }

    private static MemoryFile copyToMemoryFile(CloseableReference<PooledByteBuffer> closeableReference, int i, @Nullable byte[] bArr) throws IOException {
        InputStream limitedInputStream;
        Closeable outputStream;
        Throwable th;
        InputStream inputStream = null;
        MemoryFile memoryFile = new MemoryFile(null, (bArr == null ? 0 : bArr.length) + i);
        memoryFile.allowPurging(false);
        try {
            InputStream pooledByteBufferInputStream = new PooledByteBufferInputStream((PooledByteBuffer) closeableReference.get());
            try {
                limitedInputStream = new LimitedInputStream(pooledByteBufferInputStream, i);
                try {
                    outputStream = memoryFile.getOutputStream();
                } catch (Throwable th2) {
                    th = th2;
                    outputStream = null;
                    inputStream = limitedInputStream;
                    limitedInputStream = pooledByteBufferInputStream;
                    CloseableReference.closeSafely(closeableReference);
                    Closeables.closeQuietly(limitedInputStream);
                    Closeables.closeQuietly(inputStream);
                    Closeables.close(outputStream, true);
                    throw th;
                }
                try {
                    ByteStreams.copy(limitedInputStream, outputStream);
                    if (bArr != null) {
                        memoryFile.writeBytes(bArr, 0, i, bArr.length);
                    }
                    CloseableReference.closeSafely(closeableReference);
                    Closeables.closeQuietly(pooledByteBufferInputStream);
                    Closeables.closeQuietly(limitedInputStream);
                    Closeables.close(outputStream, true);
                    return memoryFile;
                } catch (Throwable th3) {
                    th = th3;
                    inputStream = limitedInputStream;
                    limitedInputStream = pooledByteBufferInputStream;
                    CloseableReference.closeSafely(closeableReference);
                    Closeables.closeQuietly(limitedInputStream);
                    Closeables.closeQuietly(inputStream);
                    Closeables.close(outputStream, true);
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                outputStream = null;
                limitedInputStream = pooledByteBufferInputStream;
                CloseableReference.closeSafely(closeableReference);
                Closeables.closeQuietly(limitedInputStream);
                Closeables.closeQuietly(inputStream);
                Closeables.close(outputStream, true);
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            outputStream = null;
            limitedInputStream = null;
            CloseableReference.closeSafely(closeableReference);
            Closeables.closeQuietly(limitedInputStream);
            Closeables.closeQuietly(inputStream);
            Closeables.close(outputStream, true);
            throw th;
        }
    }

    private synchronized Method getFileDescriptorMethod() {
        if (sGetFileDescriptorMethod == null) {
            try {
                sGetFileDescriptorMethod = MemoryFile.class.getDeclaredMethod("getFileDescriptor", new Class[0]);
            } catch (Throwable e) {
                throw Throwables.propagate(e);
            }
        }
        return sGetFileDescriptorMethod;
    }

    private FileDescriptor getMemoryFileDescriptor(MemoryFile memoryFile) {
        try {
            return (FileDescriptor) getFileDescriptorMethod().invoke(memoryFile, new Object[0]);
        } catch (Throwable e) {
            throw Throwables.propagate(e);
        }
    }

    protected Bitmap decodeFileDescriptorAsPurgeable(CloseableReference<PooledByteBuffer> closeableReference, int i, byte[] bArr, Options options) {
        MemoryFile memoryFile = null;
        try {
            memoryFile = copyToMemoryFile(closeableReference, i, bArr);
            Bitmap bitmap = (Bitmap) Preconditions.checkNotNull(WebpSupportStatus.sWebpBitmapFactory.decodeFileDescriptor(getMemoryFileDescriptor(memoryFile), null, options), "BitmapFactory returned null");
            if (memoryFile != null) {
                memoryFile.close();
            }
            return bitmap;
        } catch (Throwable e) {
            throw Throwables.propagate(e);
        } catch (Throwable th) {
            if (memoryFile != null) {
                memoryFile.close();
            }
        }
    }
}
