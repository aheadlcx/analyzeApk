package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;

public class BranchOnSeparateImagesProducer implements Producer<EncodedImage> {
    private final Producer<EncodedImage> mInputProducer1;
    private final Producer<EncodedImage> mInputProducer2;

    private class OnFirstImageConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        private ProducerContext mProducerContext;

        private OnFirstImageConsumer(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
            super(consumer);
            this.mProducerContext = producerContext;
        }

        protected void onNewResultImpl(EncodedImage encodedImage, int i) {
            ImageRequest imageRequest = this.mProducerContext.getImageRequest();
            boolean isLast = BaseConsumer.isLast(i);
            boolean isImageBigEnough = ThumbnailSizeChecker.isImageBigEnough(encodedImage, imageRequest.getResizeOptions());
            if (encodedImage != null && (isImageBigEnough || imageRequest.getLocalThumbnailPreviewsEnabled())) {
                if (isLast && isImageBigEnough) {
                    getConsumer().onNewResult(encodedImage, i);
                } else {
                    getConsumer().onNewResult(encodedImage, BaseConsumer.turnOffStatusFlag(i, 1));
                }
            }
            if (isLast && !isImageBigEnough) {
                EncodedImage.closeSafely(encodedImage);
                BranchOnSeparateImagesProducer.this.mInputProducer2.produceResults(getConsumer(), this.mProducerContext);
            }
        }

        protected void onFailureImpl(Throwable th) {
            BranchOnSeparateImagesProducer.this.mInputProducer2.produceResults(getConsumer(), this.mProducerContext);
        }
    }

    public BranchOnSeparateImagesProducer(Producer<EncodedImage> producer, Producer<EncodedImage> producer2) {
        this.mInputProducer1 = producer;
        this.mInputProducer2 = producer2;
    }

    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        this.mInputProducer1.produceResults(new OnFirstImageConsumer(consumer, producerContext), producerContext);
    }
}
