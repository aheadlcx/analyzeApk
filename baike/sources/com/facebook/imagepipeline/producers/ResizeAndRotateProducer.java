package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.ImmutableList;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.TriState;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.nativecode.JpegTranscoder;
import com.facebook.imagepipeline.producers.JobScheduler.JobRunnable;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public class ResizeAndRotateProducer implements Producer<EncodedImage> {
    @VisibleForTesting
    static final int DEFAULT_JPEG_QUALITY = 85;
    private static final String DOWNSAMPLE_ENUMERATOR_KEY = "downsampleEnumerator";
    private static final String FRACTION_KEY = "Fraction";
    private static final int FULL_ROUND = 360;
    private static final ImmutableList<Integer> INVERTED_EXIF_ORIENTATIONS = ImmutableList.of(Integer.valueOf(2), Integer.valueOf(7), Integer.valueOf(4), Integer.valueOf(5));
    @VisibleForTesting
    static final int MAX_JPEG_SCALE_NUMERATOR = 8;
    @VisibleForTesting
    static final int MIN_TRANSFORM_INTERVAL_MS = 100;
    private static final String ORIGINAL_SIZE_KEY = "Original size";
    public static final String PRODUCER_NAME = "ResizeAndRotateProducer";
    private static final String REQUESTED_SIZE_KEY = "Requested size";
    private static final String ROTATION_ANGLE_KEY = "rotationAngle";
    private static final String SOFTWARE_ENUMERATOR_KEY = "softwareEnumerator";
    private final Executor mExecutor;
    private final Producer<EncodedImage> mInputProducer;
    private final PooledByteBufferFactory mPooledByteBufferFactory;
    private final boolean mResizingEnabled;
    private final boolean mUseDownsamplingRatio;

    private class TransformingConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        private boolean mIsCancelled = false;
        private final JobScheduler mJobScheduler;
        private final ProducerContext mProducerContext;

        public TransformingConsumer(final Consumer<EncodedImage> consumer, ProducerContext producerContext) {
            super(consumer);
            this.mProducerContext = producerContext;
            this.mJobScheduler = new JobScheduler(ResizeAndRotateProducer.this.mExecutor, new JobRunnable(ResizeAndRotateProducer.this) {
                public void run(EncodedImage encodedImage, int i) {
                    TransformingConsumer.this.doTransform(encodedImage, i);
                }
            }, 100);
            this.mProducerContext.addCallbacks(new BaseProducerContextCallbacks(ResizeAndRotateProducer.this) {
                public void onIsIntermediateResultExpectedChanged() {
                    if (TransformingConsumer.this.mProducerContext.isIntermediateResultExpected()) {
                        TransformingConsumer.this.mJobScheduler.scheduleJob();
                    }
                }

                public void onCancellationRequested() {
                    TransformingConsumer.this.mJobScheduler.clearJob();
                    TransformingConsumer.this.mIsCancelled = true;
                    consumer.onCancellation();
                }
            });
        }

        protected void onNewResultImpl(@Nullable EncodedImage encodedImage, int i) {
            if (!this.mIsCancelled) {
                boolean isLast = BaseConsumer.isLast(i);
                if (encodedImage != null) {
                    TriState access$600 = ResizeAndRotateProducer.shouldTransform(this.mProducerContext.getImageRequest(), encodedImage, ResizeAndRotateProducer.this.mResizingEnabled);
                    if (!isLast && access$600 == TriState.UNSET) {
                        return;
                    }
                    if (access$600 != TriState.YES) {
                        if (!(this.mProducerContext.getImageRequest().getRotationOptions().canDeferUntilRendered() || encodedImage.getRotationAngle() == 0 || encodedImage.getRotationAngle() == -1)) {
                            encodedImage = moveImage(encodedImage);
                            encodedImage.setRotationAngle(0);
                        }
                        getConsumer().onNewResult(encodedImage, i);
                    } else if (!this.mJobScheduler.updateJob(encodedImage, i)) {
                    } else {
                        if (isLast || this.mProducerContext.isIntermediateResultExpected()) {
                            this.mJobScheduler.scheduleJob();
                        }
                    }
                } else if (isLast) {
                    getConsumer().onNewResult(null, 1);
                }
            }
        }

        private EncodedImage moveImage(EncodedImage encodedImage) {
            EncodedImage cloneOrNull = EncodedImage.cloneOrNull(encodedImage);
            encodedImage.close();
            return cloneOrNull;
        }

        private void doTransform(EncodedImage encodedImage, int i) {
            Throwable e;
            InputStream inputStream;
            Map map = null;
            this.mProducerContext.getListener().onProducerStart(this.mProducerContext.getId(), ResizeAndRotateProducer.PRODUCER_NAME);
            ImageRequest imageRequest = this.mProducerContext.getImageRequest();
            OutputStream newOutputStream = ResizeAndRotateProducer.this.mPooledByteBufferFactory.newOutputStream();
            InputStream inputStream2;
            try {
                int i2;
                int access$800 = ResizeAndRotateProducer.getSoftwareNumerator(imageRequest, encodedImage, ResizeAndRotateProducer.this.mResizingEnabled);
                int determineSampleSize = DownsampleUtil.determineSampleSize(imageRequest, encodedImage);
                int calculateDownsampleNumerator = ResizeAndRotateProducer.calculateDownsampleNumerator(determineSampleSize);
                if (ResizeAndRotateProducer.this.mUseDownsamplingRatio) {
                    i2 = calculateDownsampleNumerator;
                } else {
                    i2 = access$800;
                }
                inputStream2 = encodedImage.getInputStream();
                try {
                    if (ResizeAndRotateProducer.INVERTED_EXIF_ORIENTATIONS.contains(Integer.valueOf(encodedImage.getExifOrientation()))) {
                        int access$1100 = ResizeAndRotateProducer.getForceRotatedInvertedExifOrientation(imageRequest.getRotationOptions(), encodedImage);
                        map = getExtraMap(encodedImage, imageRequest, i2, calculateDownsampleNumerator, access$800, 0);
                        JpegTranscoder.transcodeJpegWithExifOrientation(inputStream2, newOutputStream, access$1100, i2, 85);
                    } else {
                        int access$1200 = ResizeAndRotateProducer.getRotationAngle(imageRequest.getRotationOptions(), encodedImage);
                        map = getExtraMap(encodedImage, imageRequest, i2, calculateDownsampleNumerator, access$800, access$1200);
                        JpegTranscoder.transcodeJpeg(inputStream2, newOutputStream, access$1200, i2, 85);
                    }
                    CloseableReference of = CloseableReference.of(newOutputStream.toByteBuffer());
                    EncodedImage encodedImage2;
                    try {
                        encodedImage2 = new EncodedImage(of);
                        encodedImage2.setImageFormat(DefaultImageFormats.JPEG);
                        encodedImage2.parseMetaData();
                        this.mProducerContext.getListener().onProducerFinishWithSuccess(this.mProducerContext.getId(), ResizeAndRotateProducer.PRODUCER_NAME, map);
                        if (determineSampleSize != 1) {
                            i |= 16;
                        }
                        getConsumer().onNewResult(encodedImage2, i);
                        EncodedImage.closeSafely(encodedImage2);
                        CloseableReference.closeSafely(of);
                        Closeables.closeQuietly(inputStream2);
                        newOutputStream.close();
                    } catch (Throwable th) {
                        CloseableReference.closeSafely(of);
                    }
                } catch (Exception e2) {
                    e = e2;
                    inputStream = inputStream2;
                    try {
                        this.mProducerContext.getListener().onProducerFinishWithFailure(this.mProducerContext.getId(), ResizeAndRotateProducer.PRODUCER_NAME, e, map);
                        if (BaseConsumer.isLast(i)) {
                            getConsumer().onFailure(e);
                        }
                        Closeables.closeQuietly(inputStream);
                        newOutputStream.close();
                    } catch (Throwable th2) {
                        e = th2;
                        inputStream2 = inputStream;
                        Closeables.closeQuietly(inputStream2);
                        newOutputStream.close();
                        throw e;
                    }
                } catch (Throwable th3) {
                    e = th3;
                    Closeables.closeQuietly(inputStream2);
                    newOutputStream.close();
                    throw e;
                }
            } catch (Exception e3) {
                e = e3;
                inputStream = null;
                this.mProducerContext.getListener().onProducerFinishWithFailure(this.mProducerContext.getId(), ResizeAndRotateProducer.PRODUCER_NAME, e, map);
                if (BaseConsumer.isLast(i)) {
                    getConsumer().onFailure(e);
                }
                Closeables.closeQuietly(inputStream);
                newOutputStream.close();
            } catch (Throwable th4) {
                e = th4;
                inputStream2 = null;
                Closeables.closeQuietly(inputStream2);
                newOutputStream.close();
                throw e;
            }
        }

        private Map<String, String> getExtraMap(EncodedImage encodedImage, ImageRequest imageRequest, int i, int i2, int i3, int i4) {
            if (!this.mProducerContext.getListener().requiresExtraMap(this.mProducerContext.getId())) {
                return null;
            }
            Object obj;
            String str = encodedImage.getWidth() + "x" + encodedImage.getHeight();
            if (imageRequest.getResizeOptions() != null) {
                obj = imageRequest.getResizeOptions().width + "x" + imageRequest.getResizeOptions().height;
            } else {
                String str2 = "Unspecified";
            }
            Object obj2 = i > 0 ? i + "/8" : "";
            Map hashMap = new HashMap();
            hashMap.put(ResizeAndRotateProducer.ORIGINAL_SIZE_KEY, str);
            hashMap.put(ResizeAndRotateProducer.REQUESTED_SIZE_KEY, obj);
            hashMap.put(ResizeAndRotateProducer.FRACTION_KEY, obj2);
            hashMap.put("queueTime", String.valueOf(this.mJobScheduler.getQueuedTime()));
            hashMap.put(ResizeAndRotateProducer.DOWNSAMPLE_ENUMERATOR_KEY, Integer.toString(i2));
            hashMap.put(ResizeAndRotateProducer.SOFTWARE_ENUMERATOR_KEY, Integer.toString(i3));
            hashMap.put(ResizeAndRotateProducer.ROTATION_ANGLE_KEY, Integer.toString(i4));
            return ImmutableMap.copyOf(hashMap);
        }
    }

    public ResizeAndRotateProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, boolean z, Producer<EncodedImage> producer, boolean z2) {
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
        this.mPooledByteBufferFactory = (PooledByteBufferFactory) Preconditions.checkNotNull(pooledByteBufferFactory);
        this.mResizingEnabled = z;
        this.mInputProducer = (Producer) Preconditions.checkNotNull(producer);
        this.mUseDownsamplingRatio = z2;
    }

    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        this.mInputProducer.produceResults(new TransformingConsumer(consumer, producerContext), producerContext);
    }

    private static TriState shouldTransform(ImageRequest imageRequest, EncodedImage encodedImage, boolean z) {
        if (encodedImage == null || encodedImage.getImageFormat() == ImageFormat.UNKNOWN) {
            return TriState.UNSET;
        }
        if (encodedImage.getImageFormat() != DefaultImageFormats.JPEG) {
            return TriState.NO;
        }
        boolean z2 = shouldRotate(imageRequest.getRotationOptions(), encodedImage) || shouldResize(getSoftwareNumerator(imageRequest, encodedImage, z));
        return TriState.valueOf(z2);
    }

    @VisibleForTesting
    static float determineResizeRatio(ResizeOptions resizeOptions, int i, int i2) {
        if (resizeOptions == null) {
            return 1.0f;
        }
        float max = Math.max(((float) resizeOptions.width) / ((float) i), ((float) resizeOptions.height) / ((float) i2));
        if (((float) i) * max > resizeOptions.maxBitmapSize) {
            max = resizeOptions.maxBitmapSize / ((float) i);
        }
        if (((float) i2) * max > resizeOptions.maxBitmapSize) {
            return resizeOptions.maxBitmapSize / ((float) i2);
        }
        return max;
    }

    @VisibleForTesting
    static int roundNumerator(float f, float f2) {
        return (int) ((8.0f * f) + f2);
    }

    private static int getSoftwareNumerator(ImageRequest imageRequest, EncodedImage encodedImage, boolean z) {
        if (!z) {
            return 8;
        }
        ResizeOptions resizeOptions = imageRequest.getResizeOptions();
        if (resizeOptions == null) {
            return 8;
        }
        int forceRotatedInvertedExifOrientation;
        int width;
        int rotationAngle = getRotationAngle(imageRequest.getRotationOptions(), encodedImage);
        if (INVERTED_EXIF_ORIENTATIONS.contains(Integer.valueOf(encodedImage.getExifOrientation()))) {
            forceRotatedInvertedExifOrientation = getForceRotatedInvertedExifOrientation(imageRequest.getRotationOptions(), encodedImage);
        } else {
            forceRotatedInvertedExifOrientation = 0;
        }
        Object obj = (rotationAngle == 90 || rotationAngle == 270 || forceRotatedInvertedExifOrientation == 5 || forceRotatedInvertedExifOrientation == 7) ? 1 : null;
        if (obj != null) {
            forceRotatedInvertedExifOrientation = encodedImage.getHeight();
        } else {
            forceRotatedInvertedExifOrientation = encodedImage.getWidth();
        }
        if (obj != null) {
            width = encodedImage.getWidth();
        } else {
            width = encodedImage.getHeight();
        }
        width = roundNumerator(determineResizeRatio(resizeOptions, forceRotatedInvertedExifOrientation, width), resizeOptions.roundUpFraction);
        if (width > 8) {
            return 8;
        }
        return width < 1 ? 1 : width;
    }

    private static int getRotationAngle(RotationOptions rotationOptions, EncodedImage encodedImage) {
        if (!rotationOptions.rotationEnabled()) {
            return 0;
        }
        int extractOrientationFromMetadata = extractOrientationFromMetadata(encodedImage);
        return !rotationOptions.useImageMetadata() ? (extractOrientationFromMetadata + rotationOptions.getForcedAngle()) % FULL_ROUND : extractOrientationFromMetadata;
    }

    private static int getForceRotatedInvertedExifOrientation(RotationOptions rotationOptions, EncodedImage encodedImage) {
        int indexOf = INVERTED_EXIF_ORIENTATIONS.indexOf(Integer.valueOf(encodedImage.getExifOrientation()));
        if (indexOf < 0) {
            throw new IllegalArgumentException("Only accepts inverted exif orientations");
        }
        int i = 0;
        if (!rotationOptions.useImageMetadata()) {
            i = rotationOptions.getForcedAngle();
        }
        return ((Integer) INVERTED_EXIF_ORIENTATIONS.get(((i / 90) + indexOf) % INVERTED_EXIF_ORIENTATIONS.size())).intValue();
    }

    private static int extractOrientationFromMetadata(EncodedImage encodedImage) {
        switch (encodedImage.getRotationAngle()) {
            case 90:
            case 180:
            case 270:
                return encodedImage.getRotationAngle();
            default:
                return 0;
        }
    }

    private static boolean shouldResize(int i) {
        return i < 8;
    }

    private static boolean shouldRotate(RotationOptions rotationOptions, EncodedImage encodedImage) {
        return !rotationOptions.canDeferUntilRendered() && (getRotationAngle(rotationOptions, encodedImage) != 0 || shouldRotateUsingExifOrientation(rotationOptions, encodedImage));
    }

    private static boolean shouldRotateUsingExifOrientation(RotationOptions rotationOptions, EncodedImage encodedImage) {
        if (rotationOptions.rotationEnabled() && !rotationOptions.canDeferUntilRendered()) {
            return INVERTED_EXIF_ORIENTATIONS.contains(Integer.valueOf(encodedImage.getExifOrientation()));
        }
        encodedImage.setExifOrientation(0);
        return false;
    }

    @VisibleForTesting
    static int calculateDownsampleNumerator(int i) {
        return Math.max(1, 8 / i);
    }
}
