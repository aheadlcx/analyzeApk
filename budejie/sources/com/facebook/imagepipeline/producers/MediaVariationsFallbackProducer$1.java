package com.facebook.imagepipeline.producers;

import bolts.f;
import bolts.g;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.MediaVariations;
import java.util.concurrent.atomic.AtomicBoolean;

class MediaVariationsFallbackProducer$1 implements f<MediaVariations, Object> {
    final /* synthetic */ MediaVariationsFallbackProducer this$0;
    final /* synthetic */ Consumer val$consumer;
    final /* synthetic */ ImageRequest val$imageRequest;
    final /* synthetic */ AtomicBoolean val$isCancelled;
    final /* synthetic */ String val$mediaId;
    final /* synthetic */ ProducerContext val$producerContext;
    final /* synthetic */ ResizeOptions val$resizeOptions;

    MediaVariationsFallbackProducer$1(MediaVariationsFallbackProducer mediaVariationsFallbackProducer, Consumer consumer, ProducerContext producerContext, String str, ImageRequest imageRequest, ResizeOptions resizeOptions, AtomicBoolean atomicBoolean) {
        this.this$0 = mediaVariationsFallbackProducer;
        this.val$consumer = consumer;
        this.val$producerContext = producerContext;
        this.val$mediaId = str;
        this.val$imageRequest = imageRequest;
        this.val$resizeOptions = resizeOptions;
        this.val$isCancelled = atomicBoolean;
    }

    public Object then(g<MediaVariations> gVar) throws Exception {
        if (gVar.c() || gVar.d()) {
            return gVar;
        }
        try {
            if (gVar.e() != null) {
                return MediaVariationsFallbackProducer.access$100(this.this$0, this.val$consumer, this.val$producerContext, this.val$imageRequest, (MediaVariations) gVar.e(), this.val$resizeOptions, this.val$isCancelled);
            }
            MediaVariationsFallbackProducer.access$000(this.this$0, this.val$consumer, this.val$producerContext, this.val$mediaId);
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
