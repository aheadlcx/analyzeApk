package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest$CacheChoice;

@VisibleForTesting
class MediaVariationsFallbackProducer$MediaVariationsConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
    private final String mMediaId;
    private final ProducerContext mProducerContext;
    final /* synthetic */ MediaVariationsFallbackProducer this$0;

    public MediaVariationsFallbackProducer$MediaVariationsConsumer(MediaVariationsFallbackProducer mediaVariationsFallbackProducer, Consumer<EncodedImage> consumer, ProducerContext producerContext, String str) {
        this.this$0 = mediaVariationsFallbackProducer;
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
            ImageRequest$CacheChoice imageRequest$CacheChoice;
            if (imageRequest.getCacheChoice() == null) {
                imageRequest$CacheChoice = ImageRequest$CacheChoice.DEFAULT;
            } else {
                imageRequest$CacheChoice = imageRequest.getCacheChoice();
            }
            MediaVariationsFallbackProducer.access$600(this.this$0).saveCachedVariant(this.mMediaId, imageRequest$CacheChoice, MediaVariationsFallbackProducer.access$500(this.this$0).getEncodedCacheKey(imageRequest, this.mProducerContext.getCallerContext()), encodedImage);
        }
    }
}
