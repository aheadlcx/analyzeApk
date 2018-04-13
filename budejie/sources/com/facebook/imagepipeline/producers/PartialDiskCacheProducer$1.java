package com.facebook.imagepipeline.producers;

import bolts.f;
import bolts.g;
import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

class PartialDiskCacheProducer$1 implements f<EncodedImage, Void> {
    final /* synthetic */ PartialDiskCacheProducer this$0;
    final /* synthetic */ Consumer val$consumer;
    final /* synthetic */ ProducerListener val$listener;
    final /* synthetic */ CacheKey val$partialImageCacheKey;
    final /* synthetic */ ProducerContext val$producerContext;
    final /* synthetic */ String val$requestId;

    PartialDiskCacheProducer$1(PartialDiskCacheProducer partialDiskCacheProducer, ProducerListener producerListener, String str, Consumer consumer, ProducerContext producerContext, CacheKey cacheKey) {
        this.this$0 = partialDiskCacheProducer;
        this.val$listener = producerListener;
        this.val$requestId = str;
        this.val$consumer = consumer;
        this.val$producerContext = producerContext;
        this.val$partialImageCacheKey = cacheKey;
    }

    public Void then(g<EncodedImage> gVar) throws Exception {
        if (PartialDiskCacheProducer.access$000(gVar)) {
            this.val$listener.onProducerFinishWithCancellation(this.val$requestId, PartialDiskCacheProducer.PRODUCER_NAME, null);
            this.val$consumer.onCancellation();
        } else if (gVar.d()) {
            this.val$listener.onProducerFinishWithFailure(this.val$requestId, PartialDiskCacheProducer.PRODUCER_NAME, gVar.f(), null);
            PartialDiskCacheProducer.access$100(this.this$0, this.val$consumer, this.val$producerContext, this.val$partialImageCacheKey, null);
        } else {
            EncodedImage encodedImage = (EncodedImage) gVar.e();
            if (encodedImage != null) {
                this.val$listener.onProducerFinishWithSuccess(this.val$requestId, PartialDiskCacheProducer.PRODUCER_NAME, PartialDiskCacheProducer.getExtraMap(this.val$listener, this.val$requestId, true, encodedImage.getSize()));
                BytesRange toMax = BytesRange.toMax(encodedImage.getSize() - 1);
                encodedImage.setBytesRange(toMax);
                int size = encodedImage.getSize();
                ImageRequest imageRequest = this.val$producerContext.getImageRequest();
                if (toMax.contains(imageRequest.getBytesRange())) {
                    this.val$listener.onUltimateProducerReached(this.val$requestId, PartialDiskCacheProducer.PRODUCER_NAME, true);
                    this.val$consumer.onNewResult(encodedImage, 9);
                } else {
                    this.val$consumer.onNewResult(encodedImage, 8);
                    PartialDiskCacheProducer.access$100(this.this$0, this.val$consumer, new SettableProducerContext(ImageRequestBuilder.fromRequest(imageRequest).setBytesRange(BytesRange.from(size - 1)).build(), this.val$producerContext), this.val$partialImageCacheKey, encodedImage);
                }
            } else {
                this.val$listener.onProducerFinishWithSuccess(this.val$requestId, PartialDiskCacheProducer.PRODUCER_NAME, PartialDiskCacheProducer.getExtraMap(this.val$listener, this.val$requestId, false, 0));
                PartialDiskCacheProducer.access$100(this.this$0, this.val$consumer, this.val$producerContext, this.val$partialImageCacheKey, encodedImage);
            }
        }
        return null;
    }
}
