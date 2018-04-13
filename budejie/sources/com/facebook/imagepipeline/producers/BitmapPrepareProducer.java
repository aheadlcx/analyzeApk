package com.facebook.imagepipeline.producers;

import android.graphics.Bitmap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;

public class BitmapPrepareProducer implements Producer<CloseableReference<CloseableImage>> {
    public static final String PRODUCER_NAME = "BitmapPrepareProducer";
    private final Producer<CloseableReference<CloseableImage>> mInputProducer;

    private static class BitmapPrepareConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> {
        BitmapPrepareConsumer(Consumer<CloseableReference<CloseableImage>> consumer) {
            super(consumer);
        }

        protected void onNewResultImpl(CloseableReference<CloseableImage> closeableReference, int i) {
            internalPrepareBitmap(closeableReference);
            getConsumer().onNewResult(closeableReference, i);
        }

        private static void internalPrepareBitmap(CloseableReference<CloseableImage> closeableReference) {
            if (closeableReference != null && closeableReference.isValid()) {
                CloseableImage closeableImage = (CloseableImage) closeableReference.get();
                if (closeableImage != null && !closeableImage.isClosed() && (closeableImage instanceof CloseableStaticBitmap)) {
                    Bitmap underlyingBitmap = ((CloseableStaticBitmap) closeableImage).getUnderlyingBitmap();
                    if (underlyingBitmap != null) {
                        underlyingBitmap.prepareToDraw();
                    }
                }
            }
        }
    }

    public BitmapPrepareProducer(Producer<CloseableReference<CloseableImage>> producer) {
        this.mInputProducer = (Producer) Preconditions.checkNotNull(producer);
    }

    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext) {
        if (producerContext.isPrefetch()) {
            this.mInputProducer.produceResults(consumer, producerContext);
        } else {
            this.mInputProducer.produceResults(new BitmapPrepareConsumer(consumer), producerContext);
        }
    }
}
