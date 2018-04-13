package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.util.TriState;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.JobScheduler.JobRunnable;
import com.facebook.imagepipeline.request.ImageRequest;
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

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void doTransform(com.facebook.imagepipeline.image.EncodedImage r10, int r11) {
            /*
            r9 = this;
            r7 = 0;
            r0 = r9.mProducerContext;
            r0 = r0.getListener();
            r1 = r9.mProducerContext;
            r1 = r1.getId();
            r2 = "ResizeAndRotateProducer";
            r0.onProducerStart(r1, r2);
            r0 = r9.mProducerContext;
            r2 = r0.getImageRequest();
            r0 = com.facebook.imagepipeline.producers.ResizeAndRotateProducer.this;
            r0 = r0.mPooledByteBufferFactory;
            r8 = r0.newOutputStream();
            r0 = com.facebook.imagepipeline.producers.ResizeAndRotateProducer.this;	 Catch:{ Exception -> 0x00cc, all -> 0x00c1 }
            r0 = r0.mResizingEnabled;	 Catch:{ Exception -> 0x00cc, all -> 0x00c1 }
            r5 = com.facebook.imagepipeline.producers.ResizeAndRotateProducer.getSoftwareNumerator(r2, r10, r0);	 Catch:{ Exception -> 0x00cc, all -> 0x00c1 }
            r0 = com.facebook.imagepipeline.producers.DownsampleUtil.determineSampleSize(r2, r10);	 Catch:{ Exception -> 0x00cc, all -> 0x00c1 }
            r4 = com.facebook.imagepipeline.producers.ResizeAndRotateProducer.calculateDownsampleNumerator(r0);	 Catch:{ Exception -> 0x00cc, all -> 0x00c1 }
            r0 = com.facebook.imagepipeline.producers.ResizeAndRotateProducer.this;	 Catch:{ Exception -> 0x00cc, all -> 0x00c1 }
            r0 = r0.mUseDownsamplingRatio;	 Catch:{ Exception -> 0x00cc, all -> 0x00c1 }
            if (r0 == 0) goto L_0x008e;
        L_0x003c:
            r3 = r4;
        L_0x003d:
            r0 = r2.getRotationOptions();	 Catch:{ Exception -> 0x00cc, all -> 0x00c1 }
            r6 = com.facebook.imagepipeline.producers.ResizeAndRotateProducer.getRotationAngle(r0, r10);	 Catch:{ Exception -> 0x00cc, all -> 0x00c1 }
            r0 = r9;
            r1 = r10;
            r2 = r0.getExtraMap(r1, r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x00cc, all -> 0x00c1 }
            r1 = r10.getInputStream();	 Catch:{ Exception -> 0x00cf, all -> 0x00c1 }
            r0 = 85;
            com.facebook.imagepipeline.nativecode.JpegTranscoder.transcodeJpeg(r1, r8, r6, r3, r0);	 Catch:{ Exception -> 0x009a }
            r0 = r8.toByteBuffer();	 Catch:{ Exception -> 0x009a }
            r3 = com.facebook.common.references.CloseableReference.of(r0);	 Catch:{ Exception -> 0x009a }
            r4 = new com.facebook.imagepipeline.image.EncodedImage;	 Catch:{ all -> 0x0095 }
            r4.<init>(r3);	 Catch:{ all -> 0x0095 }
            r0 = com.facebook.imageformat.DefaultImageFormats.JPEG;	 Catch:{ all -> 0x0095 }
            r4.setImageFormat(r0);	 Catch:{ all -> 0x0095 }
            r4.parseMetaData();	 Catch:{ all -> 0x0090 }
            r0 = r9.mProducerContext;	 Catch:{ all -> 0x0090 }
            r0 = r0.getListener();	 Catch:{ all -> 0x0090 }
            r5 = r9.mProducerContext;	 Catch:{ all -> 0x0090 }
            r5 = r5.getId();	 Catch:{ all -> 0x0090 }
            r6 = "ResizeAndRotateProducer";
            r0.onProducerFinishWithSuccess(r5, r6, r2);	 Catch:{ all -> 0x0090 }
            r0 = r9.getConsumer();	 Catch:{ all -> 0x0090 }
            r0.onNewResult(r4, r11);	 Catch:{ all -> 0x0090 }
            com.facebook.imagepipeline.image.EncodedImage.closeSafely(r4);	 Catch:{ all -> 0x0095 }
            com.facebook.common.references.CloseableReference.closeSafely(r3);	 Catch:{ Exception -> 0x009a }
            com.facebook.common.internal.Closeables.closeQuietly(r1);
            r8.close();
        L_0x008d:
            return;
        L_0x008e:
            r3 = r5;
            goto L_0x003d;
        L_0x0090:
            r0 = move-exception;
            com.facebook.imagepipeline.image.EncodedImage.closeSafely(r4);	 Catch:{ all -> 0x0095 }
            throw r0;	 Catch:{ all -> 0x0095 }
        L_0x0095:
            r0 = move-exception;
            com.facebook.common.references.CloseableReference.closeSafely(r3);	 Catch:{ Exception -> 0x009a }
            throw r0;	 Catch:{ Exception -> 0x009a }
        L_0x009a:
            r0 = move-exception;
            r7 = r2;
        L_0x009c:
            r2 = r9.mProducerContext;	 Catch:{ all -> 0x00ca }
            r2 = r2.getListener();	 Catch:{ all -> 0x00ca }
            r3 = r9.mProducerContext;	 Catch:{ all -> 0x00ca }
            r3 = r3.getId();	 Catch:{ all -> 0x00ca }
            r4 = "ResizeAndRotateProducer";
            r2.onProducerFinishWithFailure(r3, r4, r0, r7);	 Catch:{ all -> 0x00ca }
            r2 = com.facebook.imagepipeline.producers.BaseConsumer.isLast(r11);	 Catch:{ all -> 0x00ca }
            if (r2 == 0) goto L_0x00ba;
        L_0x00b3:
            r2 = r9.getConsumer();	 Catch:{ all -> 0x00ca }
            r2.onFailure(r0);	 Catch:{ all -> 0x00ca }
        L_0x00ba:
            com.facebook.common.internal.Closeables.closeQuietly(r1);
            r8.close();
            goto L_0x008d;
        L_0x00c1:
            r0 = move-exception;
            r1 = r7;
        L_0x00c3:
            com.facebook.common.internal.Closeables.closeQuietly(r1);
            r8.close();
            throw r0;
        L_0x00ca:
            r0 = move-exception;
            goto L_0x00c3;
        L_0x00cc:
            r0 = move-exception;
            r1 = r7;
            goto L_0x009c;
        L_0x00cf:
            r0 = move-exception;
            r1 = r7;
            r7 = r2;
            goto L_0x009c;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.ResizeAndRotateProducer.TransformingConsumer.doTransform(com.facebook.imagepipeline.image.EncodedImage, int):void");
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
        int height;
        int rotationAngle = getRotationAngle(imageRequest.getRotationOptions(), encodedImage);
        Object obj = (rotationAngle == 90 || rotationAngle == 270) ? 1 : null;
        if (obj != null) {
            height = encodedImage.getHeight();
        } else {
            height = encodedImage.getWidth();
        }
        if (obj != null) {
            rotationAngle = encodedImage.getWidth();
        } else {
            rotationAngle = encodedImage.getHeight();
        }
        rotationAngle = roundNumerator(determineResizeRatio(resizeOptions, height, rotationAngle), resizeOptions.roundUpFraction);
        if (rotationAngle > 8) {
            return 8;
        }
        return rotationAngle < 1 ? 1 : rotationAngle;
    }

    private static int getRotationAngle(RotationOptions rotationOptions, EncodedImage encodedImage) {
        if (!rotationOptions.rotationEnabled()) {
            return 0;
        }
        int extractOrientationFromMetadata = extractOrientationFromMetadata(encodedImage);
        return !rotationOptions.useImageMetadata() ? (extractOrientationFromMetadata + rotationOptions.getForcedAngle()) % 360 : extractOrientationFromMetadata;
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
        return (rotationOptions.canDeferUntilRendered() || getRotationAngle(rotationOptions, encodedImage) == 0) ? false : true;
    }

    @VisibleForTesting
    static int calculateDownsampleNumerator(int i) {
        return Math.max(1, 8 / i);
    }
}
