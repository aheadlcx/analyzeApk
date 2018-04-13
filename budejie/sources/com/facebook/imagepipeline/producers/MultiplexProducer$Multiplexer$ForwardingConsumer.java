package com.facebook.imagepipeline.producers;

class MultiplexProducer$Multiplexer$ForwardingConsumer extends BaseConsumer<T> {
    final /* synthetic */ MultiplexProducer$Multiplexer this$1;

    private MultiplexProducer$Multiplexer$ForwardingConsumer(MultiplexProducer$Multiplexer multiplexProducer$Multiplexer) {
        this.this$1 = multiplexProducer$Multiplexer;
    }

    protected void onNewResultImpl(T t, int i) {
        this.this$1.onNextResult(this, t, i);
    }

    protected void onFailureImpl(Throwable th) {
        this.this$1.onFailure(this, th);
    }

    protected void onCancellationImpl() {
        this.this$1.onCancelled(this);
    }

    protected void onProgressUpdateImpl(float f) {
        this.this$1.onProgressUpdate(this, f);
    }
}
