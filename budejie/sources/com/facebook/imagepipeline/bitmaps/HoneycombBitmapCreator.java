package com.facebook.imagepipeline.bitmaps;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.webp.BitmapCreator;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.FlexByteArrayPool;
import com.facebook.imagepipeline.memory.PoolFactory;

public class HoneycombBitmapCreator implements BitmapCreator {
    private final FlexByteArrayPool mFlexByteArrayPool;
    private final EmptyJpegGenerator mJpegGenerator;

    public HoneycombBitmapCreator(PoolFactory poolFactory) {
        this.mFlexByteArrayPool = poolFactory.getFlexByteArrayPool();
        this.mJpegGenerator = new EmptyJpegGenerator(poolFactory.getPooledByteBufferFactory());
    }

    @TargetApi(12)
    public Bitmap createNakedBitmap(int i, int i2, Config config) {
        Throwable th;
        EncodedImage encodedImage;
        CloseableReference closeableReference = null;
        CloseableReference generate = this.mJpegGenerator.generate((short) i, (short) i2);
        try {
            EncodedImage encodedImage2 = new EncodedImage(generate);
            try {
                encodedImage2.setImageFormat(DefaultImageFormats.JPEG);
                Options bitmapFactoryOptions = getBitmapFactoryOptions(encodedImage2.getSampleSize(), config);
                int size = ((PooledByteBuffer) generate.get()).size();
                PooledByteBuffer pooledByteBuffer = (PooledByteBuffer) generate.get();
                CloseableReference closeableReference2 = this.mFlexByteArrayPool.get(size + 2);
                try {
                    byte[] bArr = (byte[]) closeableReference2.get();
                    pooledByteBuffer.read(0, bArr, 0, size);
                    Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, size, bitmapFactoryOptions);
                    decodeByteArray.setHasAlpha(true);
                    decodeByteArray.eraseColor(0);
                    CloseableReference.closeSafely(closeableReference2);
                    EncodedImage.closeSafely(encodedImage2);
                    CloseableReference.closeSafely(generate);
                    return decodeByteArray;
                } catch (Throwable th2) {
                    th = th2;
                    closeableReference = closeableReference2;
                    encodedImage = encodedImage2;
                    CloseableReference.closeSafely(closeableReference);
                    EncodedImage.closeSafely(encodedImage);
                    CloseableReference.closeSafely(generate);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                encodedImage = encodedImage2;
                CloseableReference.closeSafely(closeableReference);
                EncodedImage.closeSafely(encodedImage);
                CloseableReference.closeSafely(generate);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            encodedImage = null;
            CloseableReference.closeSafely(closeableReference);
            EncodedImage.closeSafely(encodedImage);
            CloseableReference.closeSafely(generate);
            throw th;
        }
    }

    private static Options getBitmapFactoryOptions(int i, Config config) {
        Options options = new Options();
        options.inDither = true;
        options.inPreferredConfig = config;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = i;
        if (VERSION.SDK_INT >= 11) {
            options.inMutable = true;
        }
        return options;
    }
}
