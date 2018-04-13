package com.facebook.imagepipeline.producers;

class LocalExifThumbnailProducer$2 extends BaseProducerContextCallbacks {
    final /* synthetic */ LocalExifThumbnailProducer this$0;
    final /* synthetic */ StatefulProducerRunnable val$cancellableProducerRunnable;

    LocalExifThumbnailProducer$2(LocalExifThumbnailProducer localExifThumbnailProducer, StatefulProducerRunnable statefulProducerRunnable) {
        this.this$0 = localExifThumbnailProducer;
        this.val$cancellableProducerRunnable = statefulProducerRunnable;
    }

    public void onCancellationRequested() {
        this.val$cancellableProducerRunnable.cancel();
    }
}
