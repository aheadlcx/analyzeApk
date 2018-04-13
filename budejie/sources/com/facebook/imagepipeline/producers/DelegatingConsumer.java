package com.facebook.imagepipeline.producers;

public abstract class DelegatingConsumer<I, O> extends BaseConsumer<I> {
    private final Consumer<O> mConsumer;

    public DelegatingConsumer(Consumer<O> consumer) {
        this.mConsumer = consumer;
    }

    public Consumer<O> getConsumer() {
        return this.mConsumer;
    }

    protected void onFailureImpl(Throwable th) {
        this.mConsumer.onFailure(th);
    }

    protected void onCancellationImpl() {
        this.mConsumer.onCancellation();
    }

    protected void onProgressUpdateImpl(float f) {
        this.mConsumer.onProgressUpdate(f);
    }
}
