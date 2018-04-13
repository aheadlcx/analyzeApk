package com.facebook.imagepipeline.platform;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.support.v4.util.Pools.SynchronizedPool;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.streams.LimitedInputStream;
import com.facebook.common.streams.TailAppendingInputStream;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.BitmapPool;
import com.facebook.imageutils.BitmapUtil;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import org.msgpack.core.MessagePack.Code;

@TargetApi(21)
@ThreadSafe
public class ArtDecoder implements PlatformDecoder {
    private static final int DECODE_BUFFER_SIZE = 16384;
    private static final byte[] EOI_TAIL = new byte[]{(byte) -1, Code.STR8};
    private static final Class<?> TAG = ArtDecoder.class;
    private final BitmapPool mBitmapPool;
    @VisibleForTesting
    final SynchronizedPool<ByteBuffer> mDecodeBuffers;

    public ArtDecoder(BitmapPool bitmapPool, int i, SynchronizedPool synchronizedPool) {
        this.mBitmapPool = bitmapPool;
        this.mDecodeBuffers = synchronizedPool;
        for (int i2 = 0; i2 < i; i2++) {
            this.mDecodeBuffers.release(ByteBuffer.allocate(16384));
        }
    }

    public CloseableReference<Bitmap> decodeFromEncodedImage(EncodedImage encodedImage, Config config, @Nullable Rect rect) {
        Options decodeOptionsForStream = getDecodeOptionsForStream(encodedImage, config);
        Object obj = decodeOptionsForStream.inPreferredConfig != Config.ARGB_8888 ? 1 : null;
        try {
            return decodeStaticImageFromStream(encodedImage.getInputStream(), decodeOptionsForStream, rect);
        } catch (RuntimeException e) {
            if (obj != null) {
                return decodeFromEncodedImage(encodedImage, Config.ARGB_8888, rect);
            }
            throw e;
        }
    }

    public CloseableReference<Bitmap> decodeJPEGFromEncodedImage(EncodedImage encodedImage, Config config, @Nullable Rect rect, int i) {
        InputStream limitedInputStream;
        boolean isCompleteAt = encodedImage.isCompleteAt(i);
        Options decodeOptionsForStream = getDecodeOptionsForStream(encodedImage, config);
        InputStream inputStream = encodedImage.getInputStream();
        Preconditions.checkNotNull(inputStream);
        if (encodedImage.getSize() > i) {
            limitedInputStream = new LimitedInputStream(inputStream, i);
        } else {
            limitedInputStream = inputStream;
        }
        if (isCompleteAt) {
            inputStream = limitedInputStream;
        } else {
            inputStream = new TailAppendingInputStream(limitedInputStream, EOI_TAIL);
        }
        Object obj = decodeOptionsForStream.inPreferredConfig != Config.ARGB_8888 ? 1 : null;
        try {
            return decodeStaticImageFromStream(inputStream, decodeOptionsForStream, rect);
        } catch (RuntimeException e) {
            if (obj != null) {
                return decodeFromEncodedImage(encodedImage, Config.ARGB_8888, rect);
            }
            throw e;
        }
    }

    protected CloseableReference<Bitmap> decodeStaticImageFromStream(InputStream inputStream, Options options, @Nullable Rect rect) {
        int height;
        int i;
        ByteBuffer allocate;
        BitmapRegionDecoder newInstance;
        Object obj;
        Bitmap decodeStream;
        Throwable th;
        BitmapRegionDecoder bitmapRegionDecoder = null;
        Preconditions.checkNotNull(inputStream);
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        if (rect != null) {
            i2 = rect.width();
            height = rect.height();
            i = i2;
        } else {
            height = i3;
            i = i2;
        }
        Bitmap bitmap = (Bitmap) this.mBitmapPool.get(BitmapUtil.getSizeInByteForBitmap(i, height, options.inPreferredConfig));
        if (bitmap == null) {
            throw new NullPointerException("BitmapPool.get returned null");
        }
        options.inBitmap = bitmap;
        ByteBuffer byteBuffer = (ByteBuffer) this.mDecodeBuffers.acquire();
        if (byteBuffer == null) {
            allocate = ByteBuffer.allocate(16384);
        } else {
            allocate = byteBuffer;
        }
        try {
            options.inTempStorage = allocate.array();
            if (rect != null) {
                try {
                    bitmap.reconfigure(i, height, options.inPreferredConfig);
                    newInstance = BitmapRegionDecoder.newInstance(inputStream, true);
                } catch (IOException e) {
                    newInstance = bitmapRegionDecoder;
                    try {
                        FLog.e(TAG, "Could not decode region %s, decoding full bitmap instead.", rect);
                        if (newInstance != null) {
                            newInstance.recycle();
                            obj = bitmapRegionDecoder;
                            if (decodeStream == null) {
                                decodeStream = BitmapFactory.decodeStream(inputStream, null, options);
                            }
                            this.mDecodeBuffers.release(allocate);
                            if (bitmap != decodeStream) {
                                return CloseableReference.of(decodeStream, this.mBitmapPool);
                            }
                            this.mBitmapPool.release(bitmap);
                            decodeStream.recycle();
                            throw new IllegalStateException();
                        }
                        obj = bitmapRegionDecoder;
                        if (decodeStream == null) {
                            decodeStream = BitmapFactory.decodeStream(inputStream, null, options);
                        }
                        this.mDecodeBuffers.release(allocate);
                        if (bitmap != decodeStream) {
                            return CloseableReference.of(decodeStream, this.mBitmapPool);
                        }
                        this.mBitmapPool.release(bitmap);
                        decodeStream.recycle();
                        throw new IllegalStateException();
                    } catch (Throwable th2) {
                        bitmapRegionDecoder = newInstance;
                        th = th2;
                        if (bitmapRegionDecoder != null) {
                            bitmapRegionDecoder.recycle();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (bitmapRegionDecoder != null) {
                        bitmapRegionDecoder.recycle();
                    }
                    throw th;
                }
                try {
                    Bitmap decodeRegion = newInstance.decodeRegion(rect, options);
                    if (newInstance != null) {
                        newInstance.recycle();
                        decodeStream = decodeRegion;
                    } else {
                        decodeStream = decodeRegion;
                    }
                } catch (IOException e2) {
                    FLog.e(TAG, "Could not decode region %s, decoding full bitmap instead.", rect);
                    if (newInstance != null) {
                        newInstance.recycle();
                        obj = bitmapRegionDecoder;
                        if (decodeStream == null) {
                            decodeStream = BitmapFactory.decodeStream(inputStream, null, options);
                        }
                        this.mDecodeBuffers.release(allocate);
                        if (bitmap != decodeStream) {
                            return CloseableReference.of(decodeStream, this.mBitmapPool);
                        }
                        this.mBitmapPool.release(bitmap);
                        decodeStream.recycle();
                        throw new IllegalStateException();
                    }
                    obj = bitmapRegionDecoder;
                    if (decodeStream == null) {
                        decodeStream = BitmapFactory.decodeStream(inputStream, null, options);
                    }
                    this.mDecodeBuffers.release(allocate);
                    if (bitmap != decodeStream) {
                        return CloseableReference.of(decodeStream, this.mBitmapPool);
                    }
                    this.mBitmapPool.release(bitmap);
                    decodeStream.recycle();
                    throw new IllegalStateException();
                }
                if (decodeStream == null) {
                    decodeStream = BitmapFactory.decodeStream(inputStream, null, options);
                }
                this.mDecodeBuffers.release(allocate);
                if (bitmap != decodeStream) {
                    return CloseableReference.of(decodeStream, this.mBitmapPool);
                }
                this.mBitmapPool.release(bitmap);
                decodeStream.recycle();
                throw new IllegalStateException();
            }
            obj = bitmapRegionDecoder;
            if (decodeStream == null) {
                decodeStream = BitmapFactory.decodeStream(inputStream, null, options);
            }
            this.mDecodeBuffers.release(allocate);
            if (bitmap != decodeStream) {
                return CloseableReference.of(decodeStream, this.mBitmapPool);
            }
            this.mBitmapPool.release(bitmap);
            decodeStream.recycle();
            throw new IllegalStateException();
        } catch (RuntimeException e3) {
            this.mBitmapPool.release(bitmap);
            throw e3;
        } catch (Throwable th4) {
            this.mDecodeBuffers.release(allocate);
        }
    }

    private static Options getDecodeOptionsForStream(EncodedImage encodedImage, Config config) {
        Options options = new Options();
        options.inSampleSize = encodedImage.getSampleSize();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(encodedImage.getInputStream(), null, options);
        if (options.outWidth == -1 || options.outHeight == -1) {
            throw new IllegalArgumentException();
        }
        options.inJustDecodeBounds = false;
        options.inDither = true;
        options.inPreferredConfig = config;
        options.inMutable = true;
        return options;
    }
}
