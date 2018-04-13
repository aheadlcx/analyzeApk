package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Preconditions;

public class ThreadHandoffProducer<T> implements Producer<T> {
    public static final String PRODUCER_NAME = "BackgroundThreadHandoffProducer";
    private final Producer<T> mInputProducer;
    private final ThreadHandoffProducerQueue mThreadHandoffProducerQueue;

    public ThreadHandoffProducer(Producer<T> producer, ThreadHandoffProducerQueue threadHandoffProducerQueue) {
        this.mInputProducer = (Producer) Preconditions.checkNotNull(producer);
        this.mThreadHandoffProducerQueue = threadHandoffProducerQueue;
    }

    public void produceResults(Consumer<T> consumer, ProducerContext producerContext) {
        ProducerListener listener = producerContext.getListener();
        String id = producerContext.getId();
        final ProducerListener producerListener = listener;
        final String str = id;
        final Consumer<T> consumer2 = consumer;
        final ProducerContext producerContext2 = producerContext;
        final Runnable anonymousClass1 = new StatefulProducerRunnable<T>(consumer, listener, PRODUCER_NAME, id) {
            protected void onSuccess(T t) {
                producerListener.onProducerFinishWithSuccess(str, ThreadHandoffProducer.PRODUCER_NAME, null);
                ThreadHandoffProducer.this.mInputProducer.produceResults(consumer2, producerContext2);
            }

            protected void disposeResult(T t) {
            }

            protected T getResult() throws Exception {
                return null;
            }
        };
        producerContext.addCallbacks(new BaseProducerContextCallbacks() {
            public void onCancellationRequested() {
                anonymousClass1.cancel();
                ThreadHandoffProducer.this.mThreadHandoffProducerQueue.remove(anonymousClass1);
            }
        });
        this.mThreadHandoffProducerQueue.addToQueueOrExecute(anonymousClass1);
    }
}
