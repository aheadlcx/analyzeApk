package com.facebook.imagepipeline.producers;

import bolts.Continuation;
import bolts.Task;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MediaVariationsIndex;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest.CacheChoice;
import com.facebook.imagepipeline.request.MediaVariations;
import com.facebook.imagepipeline.request.MediaVariations.Variant;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;

public class MediaVariationsFallbackProducer implements Producer<EncodedImage> {
    public static final String EXTRA_CACHED_VALUE_FOUND = "cached_value_found";
    public static final String EXTRA_CACHED_VALUE_USED_AS_LAST = "cached_value_used_as_last";
    public static final String EXTRA_VARIANTS_COUNT = "variants_count";
    public static final String EXTRA_VARIANTS_SOURCE = "variants_source";
    public static final String PRODUCER_NAME = "MediaVariationsFallbackProducer";
    private final CacheKeyFactory mCacheKeyFactory;
    private final BufferedDiskCache mDefaultBufferedDiskCache;
    private final Producer<EncodedImage> mInputProducer;
    private final MediaVariationsIndex mMediaVariationsIndex;
    private final BufferedDiskCache mSmallImageBufferedDiskCache;

    @VisibleForTesting
    class MediaVariationsConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        private final String mMediaId;
        private final ProducerContext mProducerContext;

        public MediaVariationsConsumer(Consumer<EncodedImage> consumer, ProducerContext producerContext, String str) {
            super(consumer);
            this.mProducerContext = producerContext;
            this.mMediaId = str;
        }

        protected void onNewResultImpl(EncodedImage encodedImage, int i) {
            if (!(!BaseConsumer.isLast(i) || encodedImage == null || BaseConsumer.statusHasFlag(i, 8))) {
                storeResultInDatabase(encodedImage);
            }
            getConsumer().onNewResult(encodedImage, i);
        }

        private void storeResultInDatabase(EncodedImage encodedImage) {
            ImageRequest imageRequest = this.mProducerContext.getImageRequest();
            if (imageRequest.isDiskCacheEnabled() && this.mMediaId != null) {
                CacheChoice cacheChoice;
                if (imageRequest.getCacheChoice() == null) {
                    cacheChoice = CacheChoice.DEFAULT;
                } else {
                    cacheChoice = imageRequest.getCacheChoice();
                }
                MediaVariationsFallbackProducer.this.mMediaVariationsIndex.saveCachedVariant(this.mMediaId, cacheChoice, MediaVariationsFallbackProducer.this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, this.mProducerContext.getCallerContext()), encodedImage);
            }
        }
    }

    @VisibleForTesting
    static class VariantComparator implements Comparator<Variant> {
        private final ResizeOptions mResizeOptions;

        VariantComparator(ResizeOptions resizeOptions) {
            this.mResizeOptions = resizeOptions;
        }

        public int compare(Variant variant, Variant variant2) {
            boolean access$300 = MediaVariationsFallbackProducer.isBigEnoughForRequestedSize(variant, this.mResizeOptions);
            boolean access$3002 = MediaVariationsFallbackProducer.isBigEnoughForRequestedSize(variant2, this.mResizeOptions);
            if (access$300 && access$3002) {
                return variant.getWidth() - variant2.getWidth();
            }
            if (access$300) {
                return -1;
            }
            if (access$3002) {
                return 1;
            }
            return variant2.getWidth() - variant.getWidth();
        }
    }

    public MediaVariationsFallbackProducer(BufferedDiskCache bufferedDiskCache, BufferedDiskCache bufferedDiskCache2, CacheKeyFactory cacheKeyFactory, MediaVariationsIndex mediaVariationsIndex, Producer<EncodedImage> producer) {
        this.mDefaultBufferedDiskCache = bufferedDiskCache;
        this.mSmallImageBufferedDiskCache = bufferedDiskCache2;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mMediaVariationsIndex = mediaVariationsIndex;
        this.mInputProducer = producer;
    }

    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        ImageRequest imageRequest = producerContext.getImageRequest();
        ResizeOptions resizeOptions = imageRequest.getResizeOptions();
        MediaVariations mediaVariations = imageRequest.getMediaVariations();
        if (!imageRequest.isDiskCacheEnabled() || resizeOptions == null || resizeOptions.height <= 0 || resizeOptions.width <= 0 || imageRequest.getBytesRange() != null) {
            startInputProducerWithExistingConsumer(consumer, producerContext);
        } else if (mediaVariations == null) {
            startInputProducerWithExistingConsumer(consumer, producerContext);
        } else {
            producerContext.getListener().onProducerStart(producerContext.getId(), PRODUCER_NAME);
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            if (mediaVariations.getVariantsCount() > 0) {
                chooseFromVariants(consumer, producerContext, imageRequest, mediaVariations, resizeOptions, atomicBoolean);
            } else {
                final Consumer<EncodedImage> consumer2 = consumer;
                final ProducerContext producerContext2 = producerContext;
                final MediaVariations mediaVariations2 = mediaVariations;
                final ImageRequest imageRequest2 = imageRequest;
                final ResizeOptions resizeOptions2 = resizeOptions;
                final AtomicBoolean atomicBoolean2 = atomicBoolean;
                this.mMediaVariationsIndex.getCachedVariants(mediaVariations.getMediaId(), MediaVariations.newBuilderForMediaId(mediaVariations.getMediaId()).setForceRequestForSpecifiedUri(mediaVariations.shouldForceRequestForSpecifiedUri()).setSource(MediaVariations.SOURCE_INDEX_DB)).continueWith(new Continuation<MediaVariations, Object>() {
                    public Object then(Task<MediaVariations> task) throws Exception {
                        if (task.isCancelled() || task.isFaulted()) {
                            return task;
                        }
                        try {
                            if (task.getResult() != null) {
                                return MediaVariationsFallbackProducer.this.chooseFromVariants(consumer2, producerContext2, imageRequest2, (MediaVariations) task.getResult(), resizeOptions2, atomicBoolean2);
                            }
                            MediaVariationsFallbackProducer.this.startInputProducerWithWrappedConsumer(consumer2, producerContext2, mediaVariations2.getMediaId());
                            return null;
                        } catch (Exception e) {
                            return null;
                        }
                    }
                });
            }
            subscribeTaskForRequestCancellation(atomicBoolean, producerContext);
        }
    }

    private Task chooseFromVariants(Consumer<EncodedImage> consumer, ProducerContext producerContext, ImageRequest imageRequest, MediaVariations mediaVariations, ResizeOptions resizeOptions, AtomicBoolean atomicBoolean) {
        if (mediaVariations.getVariantsCount() == 0) {
            return Task.forResult((EncodedImage) null).continueWith(onFinishDiskReads(consumer, producerContext, imageRequest, mediaVariations, Collections.emptyList(), 0, atomicBoolean));
        }
        return attemptCacheReadForVariant(consumer, producerContext, imageRequest, mediaVariations, mediaVariations.getSortedVariants(new VariantComparator(resizeOptions)), 0, atomicBoolean);
    }

    private Task attemptCacheReadForVariant(Consumer<EncodedImage> consumer, ProducerContext producerContext, ImageRequest imageRequest, MediaVariations mediaVariations, List<Variant> list, int i, AtomicBoolean atomicBoolean) {
        CacheChoice cacheChoice;
        Variant variant = (Variant) list.get(i);
        CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, variant.getUri(), producerContext.getCallerContext());
        if (variant.getCacheChoice() == null) {
            cacheChoice = imageRequest.getCacheChoice();
        } else {
            cacheChoice = variant.getCacheChoice();
        }
        return (cacheChoice == CacheChoice.SMALL ? this.mSmallImageBufferedDiskCache : this.mDefaultBufferedDiskCache).get(encodedCacheKey, atomicBoolean).continueWith(onFinishDiskReads(consumer, producerContext, imageRequest, mediaVariations, list, i, atomicBoolean));
    }

    private static boolean isBigEnoughForRequestedSize(Variant variant, ResizeOptions resizeOptions) {
        return variant.getWidth() >= resizeOptions.width && variant.getHeight() >= resizeOptions.height;
    }

    private Continuation<EncodedImage, Void> onFinishDiskReads(Consumer<EncodedImage> consumer, ProducerContext producerContext, ImageRequest imageRequest, MediaVariations mediaVariations, List<Variant> list, int i, AtomicBoolean atomicBoolean) {
        final String id = producerContext.getId();
        final ProducerListener listener = producerContext.getListener();
        final Consumer<EncodedImage> consumer2 = consumer;
        final ProducerContext producerContext2 = producerContext;
        final MediaVariations mediaVariations2 = mediaVariations;
        final List<Variant> list2 = list;
        final int i2 = i;
        final ImageRequest imageRequest2 = imageRequest;
        final AtomicBoolean atomicBoolean2 = atomicBoolean;
        return new Continuation<EncodedImage, Void>() {
            public Void then(Task<EncodedImage> task) throws Exception {
                boolean z;
                boolean z2 = true;
                if (MediaVariationsFallbackProducer.isTaskCancelled(task)) {
                    listener.onProducerFinishWithCancellation(id, MediaVariationsFallbackProducer.PRODUCER_NAME, null);
                    consumer2.onCancellation();
                    z2 = false;
                    z = false;
                } else if (task.isFaulted()) {
                    listener.onProducerFinishWithFailure(id, MediaVariationsFallbackProducer.PRODUCER_NAME, task.getError(), null);
                    MediaVariationsFallbackProducer.this.startInputProducerWithWrappedConsumer(consumer2, producerContext2, mediaVariations2.getMediaId());
                    z = true;
                } else {
                    EncodedImage encodedImage = (EncodedImage) task.getResult();
                    if (encodedImage != null) {
                        boolean z3 = !mediaVariations2.shouldForceRequestForSpecifiedUri() && MediaVariationsFallbackProducer.isBigEnoughForRequestedSize((Variant) list2.get(i2), imageRequest2.getResizeOptions());
                        listener.onProducerFinishWithSuccess(id, MediaVariationsFallbackProducer.PRODUCER_NAME, MediaVariationsFallbackProducer.getExtraMap(listener, id, true, list2.size(), mediaVariations2.getSource(), z3));
                        if (z3) {
                            listener.onUltimateProducerReached(id, MediaVariationsFallbackProducer.PRODUCER_NAME, true);
                            consumer2.onProgressUpdate(1.0f);
                        }
                        int turnOnStatusFlag = BaseConsumer.turnOnStatusFlag(BaseConsumer.simpleStatusForIsLast(z3), 2);
                        if (!z3) {
                            turnOnStatusFlag = BaseConsumer.turnOnStatusFlag(turnOnStatusFlag, 4);
                        }
                        consumer2.onNewResult(encodedImage, turnOnStatusFlag);
                        encodedImage.close();
                        if (z3) {
                            z2 = false;
                        }
                        z = z2;
                        z2 = false;
                    } else if (i2 < list2.size() - 1) {
                        MediaVariationsFallbackProducer.this.attemptCacheReadForVariant(consumer2, producerContext2, imageRequest2, mediaVariations2, list2, i2 + 1, atomicBoolean2);
                        z2 = false;
                        z = false;
                    } else {
                        listener.onProducerFinishWithSuccess(id, MediaVariationsFallbackProducer.PRODUCER_NAME, MediaVariationsFallbackProducer.getExtraMap(listener, id, false, list2.size(), mediaVariations2.getSource(), false));
                        z = true;
                    }
                }
                if (z) {
                    ProducerContext producerContext;
                    if (!producerContext2.isIntermediateResultExpected() || r2) {
                        producerContext = producerContext2;
                    } else {
                        producerContext = new SettableProducerContext(producerContext2);
                        producerContext.setIsIntermediateResultExpected(false);
                    }
                    MediaVariationsFallbackProducer.this.startInputProducerWithWrappedConsumer(consumer2, producerContext, mediaVariations2.getMediaId());
                }
                return null;
            }
        };
    }

    private void startInputProducerWithExistingConsumer(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        this.mInputProducer.produceResults(consumer, producerContext);
    }

    private void startInputProducerWithWrappedConsumer(Consumer<EncodedImage> consumer, ProducerContext producerContext, String str) {
        this.mInputProducer.produceResults(new MediaVariationsConsumer(consumer, producerContext, str), producerContext);
    }

    private static boolean isTaskCancelled(Task<?> task) {
        return task.isCancelled() || (task.isFaulted() && (task.getError() instanceof CancellationException));
    }

    @VisibleForTesting
    static Map<String, String> getExtraMap(ProducerListener producerListener, String str, boolean z, int i, String str2, boolean z2) {
        if (!producerListener.requiresExtraMap(str)) {
            return null;
        }
        if (z) {
            return ImmutableMap.of("cached_value_found", String.valueOf(true), EXTRA_CACHED_VALUE_USED_AS_LAST, String.valueOf(z2), EXTRA_VARIANTS_COUNT, String.valueOf(i), EXTRA_VARIANTS_SOURCE, str2);
        }
        return ImmutableMap.of("cached_value_found", String.valueOf(false), EXTRA_VARIANTS_COUNT, String.valueOf(i), EXTRA_VARIANTS_SOURCE, str2);
    }

    private void subscribeTaskForRequestCancellation(final AtomicBoolean atomicBoolean, ProducerContext producerContext) {
        producerContext.addCallbacks(new BaseProducerContextCallbacks() {
            public void onCancellationRequested() {
                atomicBoolean.set(true);
            }
        });
    }
}
