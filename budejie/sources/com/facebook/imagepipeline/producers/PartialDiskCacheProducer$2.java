package com.facebook.imagepipeline.producers;

import java.util.concurrent.atomic.AtomicBoolean;

class PartialDiskCacheProducer$2 extends BaseProducerContextCallbacks {
    final /* synthetic */ PartialDiskCacheProducer this$0;
    final /* synthetic */ AtomicBoolean val$isCancelled;

    PartialDiskCacheProducer$2(PartialDiskCacheProducer partialDiskCacheProducer, AtomicBoolean atomicBoolean) {
        this.this$0 = partialDiskCacheProducer;
        this.val$isCancelled = atomicBoolean;
    }

    public void onCancellationRequested() {
        this.val$isCancelled.set(true);
    }
}
