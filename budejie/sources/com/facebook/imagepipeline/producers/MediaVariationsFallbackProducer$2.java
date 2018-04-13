package com.facebook.imagepipeline.producers;

import bolts.f;
import bolts.g;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.MediaVariations;
import com.facebook.imagepipeline.request.MediaVariations.Variant;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

class MediaVariationsFallbackProducer$2 implements f<EncodedImage, Void> {
    final /* synthetic */ MediaVariationsFallbackProducer this$0;
    final /* synthetic */ Consumer val$consumer;
    final /* synthetic */ ImageRequest val$imageRequest;
    final /* synthetic */ AtomicBoolean val$isCancelled;
    final /* synthetic */ ProducerListener val$listener;
    final /* synthetic */ MediaVariations val$mediaVariations;
    final /* synthetic */ ProducerContext val$producerContext;
    final /* synthetic */ String val$requestId;
    final /* synthetic */ List val$sortedVariants;
    final /* synthetic */ int val$variantsIndex;

    MediaVariationsFallbackProducer$2(MediaVariationsFallbackProducer mediaVariationsFallbackProducer, ProducerListener producerListener, String str, Consumer consumer, ProducerContext producerContext, MediaVariations mediaVariations, List list, int i, ImageRequest imageRequest, AtomicBoolean atomicBoolean) {
        this.this$0 = mediaVariationsFallbackProducer;
        this.val$listener = producerListener;
        this.val$requestId = str;
        this.val$consumer = consumer;
        this.val$producerContext = producerContext;
        this.val$mediaVariations = mediaVariations;
        this.val$sortedVariants = list;
        this.val$variantsIndex = i;
        this.val$imageRequest = imageRequest;
        this.val$isCancelled = atomicBoolean;
    }

    public Void then(g<EncodedImage> gVar) throws Exception {
        boolean z;
        boolean z2 = true;
        if (MediaVariationsFallbackProducer.access$200(gVar)) {
            this.val$listener.onProducerFinishWithCancellation(this.val$requestId, MediaVariationsFallbackProducer.PRODUCER_NAME, null);
            this.val$consumer.onCancellation();
            z2 = false;
            z = false;
        } else if (gVar.d()) {
            this.val$listener.onProducerFinishWithFailure(this.val$requestId, MediaVariationsFallbackProducer.PRODUCER_NAME, gVar.f(), null);
            MediaVariationsFallbackProducer.access$000(this.this$0, this.val$consumer, this.val$producerContext, this.val$mediaVariations.getMediaId());
            z = true;
        } else {
            EncodedImage encodedImage = (EncodedImage) gVar.e();
            if (encodedImage != null) {
                boolean z3 = !this.val$mediaVariations.shouldForceRequestForSpecifiedUri() && MediaVariationsFallbackProducer.access$300((Variant) this.val$sortedVariants.get(this.val$variantsIndex), this.val$imageRequest.getResizeOptions());
                this.val$listener.onProducerFinishWithSuccess(this.val$requestId, MediaVariationsFallbackProducer.PRODUCER_NAME, MediaVariationsFallbackProducer.getExtraMap(this.val$listener, this.val$requestId, true, this.val$sortedVariants.size(), this.val$mediaVariations.getSource(), z3));
                if (z3) {
                    this.val$listener.onUltimateProducerReached(this.val$requestId, MediaVariationsFallbackProducer.PRODUCER_NAME, true);
                    this.val$consumer.onProgressUpdate(1.0f);
                }
                int turnOnStatusFlag = BaseConsumer.turnOnStatusFlag(BaseConsumer.simpleStatusForIsLast(z3), 2);
                if (!z3) {
                    turnOnStatusFlag = BaseConsumer.turnOnStatusFlag(turnOnStatusFlag, 4);
                }
                this.val$consumer.onNewResult(encodedImage, turnOnStatusFlag);
                encodedImage.close();
                if (z3) {
                    z2 = false;
                }
                z = z2;
                z2 = false;
            } else if (this.val$variantsIndex < this.val$sortedVariants.size() - 1) {
                MediaVariationsFallbackProducer.access$400(this.this$0, this.val$consumer, this.val$producerContext, this.val$imageRequest, this.val$mediaVariations, this.val$sortedVariants, this.val$variantsIndex + 1, this.val$isCancelled);
                z2 = false;
                z = false;
            } else {
                this.val$listener.onProducerFinishWithSuccess(this.val$requestId, MediaVariationsFallbackProducer.PRODUCER_NAME, MediaVariationsFallbackProducer.getExtraMap(this.val$listener, this.val$requestId, false, this.val$sortedVariants.size(), this.val$mediaVariations.getSource(), false));
                z = true;
            }
        }
        if (z) {
            ProducerContext producerContext;
            if (!this.val$producerContext.isIntermediateResultExpected() || r2) {
                producerContext = this.val$producerContext;
            } else {
                producerContext = new SettableProducerContext(this.val$producerContext);
                producerContext.setIsIntermediateResultExpected(false);
            }
            MediaVariationsFallbackProducer.access$000(this.this$0, this.val$consumer, producerContext, this.val$mediaVariations.getMediaId());
        }
        return null;
    }
}
