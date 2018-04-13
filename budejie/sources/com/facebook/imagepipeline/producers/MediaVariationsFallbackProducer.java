package com.facebook.imagepipeline.producers;

import bolts.f;
import bolts.g;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MediaIdExtractor;
import com.facebook.imagepipeline.cache.MediaVariationsIndex;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest$CacheChoice;
import com.facebook.imagepipeline.request.MediaVariations;
import com.facebook.imagepipeline.request.MediaVariations.Builder;
import com.facebook.imagepipeline.request.MediaVariations.Variant;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

public class MediaVariationsFallbackProducer implements Producer<EncodedImage> {
    public static final String EXTRA_CACHED_VALUE_FOUND = "cached_value_found";
    public static final String EXTRA_CACHED_VALUE_USED_AS_LAST = "cached_value_used_as_last";
    public static final String EXTRA_VARIANTS_COUNT = "variants_count";
    public static final String EXTRA_VARIANTS_SOURCE = "variants_source";
    public static final String PRODUCER_NAME = "MediaVariationsFallbackProducer";
    private final CacheKeyFactory mCacheKeyFactory;
    private final BufferedDiskCache mDefaultBufferedDiskCache;
    private final Producer<EncodedImage> mInputProducer;
    @Nullable
    private MediaIdExtractor mMediaIdExtractor;
    private final MediaVariationsIndex mMediaVariationsIndex;
    private final BufferedDiskCache mSmallImageBufferedDiskCache;

    public MediaVariationsFallbackProducer(BufferedDiskCache bufferedDiskCache, BufferedDiskCache bufferedDiskCache2, CacheKeyFactory cacheKeyFactory, MediaVariationsIndex mediaVariationsIndex, @Nullable MediaIdExtractor mediaIdExtractor, Producer<EncodedImage> producer) {
        this.mDefaultBufferedDiskCache = bufferedDiskCache;
        this.mSmallImageBufferedDiskCache = bufferedDiskCache2;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mMediaVariationsIndex = mediaVariationsIndex;
        this.mMediaIdExtractor = mediaIdExtractor;
        this.mInputProducer = producer;
    }

    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        ImageRequest imageRequest = producerContext.getImageRequest();
        ResizeOptions resizeOptions = imageRequest.getResizeOptions();
        MediaVariations mediaVariations = imageRequest.getMediaVariations();
        if (!imageRequest.isDiskCacheEnabled() || resizeOptions == null || resizeOptions.height <= 0 || resizeOptions.width <= 0 || imageRequest.getBytesRange() != null) {
            startInputProducerWithExistingConsumer(consumer, producerContext);
            return;
        }
        String mediaId;
        String str;
        if (mediaVariations != null) {
            mediaId = mediaVariations.getMediaId();
            str = MediaVariations.SOURCE_INDEX_DB;
        } else if (this.mMediaIdExtractor == null) {
            mediaId = null;
            str = null;
        } else {
            mediaId = this.mMediaIdExtractor.getMediaIdFrom(imageRequest.getSourceUri());
            str = MediaVariations.SOURCE_ID_EXTRACTOR;
        }
        if (mediaVariations == null && mediaId == null) {
            startInputProducerWithExistingConsumer(consumer, producerContext);
            return;
        }
        producerContext.getListener().onProducerStart(producerContext.getId(), PRODUCER_NAME);
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        if (mediaVariations == null || mediaVariations.getVariantsCount() <= 0) {
            Builder newBuilderForMediaId = MediaVariations.newBuilderForMediaId(mediaId);
            boolean z = mediaVariations != null && mediaVariations.shouldForceRequestForSpecifiedUri();
            this.mMediaVariationsIndex.getCachedVariants(mediaId, newBuilderForMediaId.setForceRequestForSpecifiedUri(z).setSource(str)).a(new MediaVariationsFallbackProducer$1(this, consumer, producerContext, mediaId, imageRequest, resizeOptions, atomicBoolean));
        } else {
            chooseFromVariants(consumer, producerContext, imageRequest, mediaVariations, resizeOptions, atomicBoolean);
        }
        subscribeTaskForRequestCancellation(atomicBoolean, producerContext);
    }

    private g chooseFromVariants(Consumer<EncodedImage> consumer, ProducerContext producerContext, ImageRequest imageRequest, MediaVariations mediaVariations, ResizeOptions resizeOptions, AtomicBoolean atomicBoolean) {
        if (mediaVariations.getVariantsCount() == 0) {
            return g.a((EncodedImage) null).a(onFinishDiskReads(consumer, producerContext, imageRequest, mediaVariations, Collections.emptyList(), 0, atomicBoolean));
        }
        return attemptCacheReadForVariant(consumer, producerContext, imageRequest, mediaVariations, mediaVariations.getSortedVariants(new MediaVariationsFallbackProducer$VariantComparator(resizeOptions)), 0, atomicBoolean);
    }

    private g attemptCacheReadForVariant(Consumer<EncodedImage> consumer, ProducerContext producerContext, ImageRequest imageRequest, MediaVariations mediaVariations, List<Variant> list, int i, AtomicBoolean atomicBoolean) {
        ImageRequest$CacheChoice cacheChoice;
        Variant variant = (Variant) list.get(i);
        CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, variant.getUri(), producerContext.getCallerContext());
        if (variant.getCacheChoice() == null) {
            cacheChoice = imageRequest.getCacheChoice();
        } else {
            cacheChoice = variant.getCacheChoice();
        }
        return (cacheChoice == ImageRequest$CacheChoice.SMALL ? this.mSmallImageBufferedDiskCache : this.mDefaultBufferedDiskCache).get(encodedCacheKey, atomicBoolean).a(onFinishDiskReads(consumer, producerContext, imageRequest, mediaVariations, list, i, atomicBoolean));
    }

    private static boolean isBigEnoughForRequestedSize(Variant variant, ResizeOptions resizeOptions) {
        return variant.getWidth() >= resizeOptions.width && variant.getHeight() >= resizeOptions.height;
    }

    private f<EncodedImage, Void> onFinishDiskReads(Consumer<EncodedImage> consumer, ProducerContext producerContext, ImageRequest imageRequest, MediaVariations mediaVariations, List<Variant> list, int i, AtomicBoolean atomicBoolean) {
        return new MediaVariationsFallbackProducer$2(this, producerContext.getListener(), producerContext.getId(), consumer, producerContext, mediaVariations, list, i, imageRequest, atomicBoolean);
    }

    private void startInputProducerWithExistingConsumer(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        this.mInputProducer.produceResults(consumer, producerContext);
    }

    private void startInputProducerWithWrappedConsumer(Consumer<EncodedImage> consumer, ProducerContext producerContext, String str) {
        this.mInputProducer.produceResults(new MediaVariationsFallbackProducer$MediaVariationsConsumer(this, consumer, producerContext, str), producerContext);
    }

    private static boolean isTaskCancelled(g<?> gVar) {
        return gVar.c() || (gVar.d() && (gVar.f() instanceof CancellationException));
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

    private void subscribeTaskForRequestCancellation(AtomicBoolean atomicBoolean, ProducerContext producerContext) {
        producerContext.addCallbacks(new MediaVariationsFallbackProducer$3(this, atomicBoolean));
    }
}
