package com.facebook.imagepipeline.producers;

import java.util.concurrent.atomic.AtomicBoolean;

class MediaVariationsFallbackProducer$3 extends BaseProducerContextCallbacks {
    final /* synthetic */ MediaVariationsFallbackProducer this$0;
    final /* synthetic */ AtomicBoolean val$isCancelled;

    MediaVariationsFallbackProducer$3(MediaVariationsFallbackProducer mediaVariationsFallbackProducer, AtomicBoolean atomicBoolean) {
        this.this$0 = mediaVariationsFallbackProducer;
        this.val$isCancelled = atomicBoolean;
    }

    public void onCancellationRequested() {
        this.val$isCancelled.set(true);
    }
}
