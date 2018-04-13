package com.facebook.imagepipeline.producers;

import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.RepeatedPostprocessor;
import com.facebook.imagepipeline.request.RepeatedPostprocessorRunner;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

class PostprocessorProducer$RepeatedPostprocessorConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> implements RepeatedPostprocessorRunner {
    @GuardedBy("RepeatedPostprocessorConsumer.this")
    private boolean mIsClosed;
    @GuardedBy("RepeatedPostprocessorConsumer.this")
    @Nullable
    private CloseableReference<CloseableImage> mSourceImageRef;
    final /* synthetic */ PostprocessorProducer this$0;

    private PostprocessorProducer$RepeatedPostprocessorConsumer(PostprocessorProducer postprocessorProducer, PostprocessorProducer$PostprocessorConsumer postprocessorProducer$PostprocessorConsumer, RepeatedPostprocessor repeatedPostprocessor, ProducerContext producerContext) {
        this.this$0 = postprocessorProducer;
        super(postprocessorProducer$PostprocessorConsumer);
        this.mIsClosed = false;
        this.mSourceImageRef = null;
        repeatedPostprocessor.setCallback(this);
        producerContext.addCallbacks(new PostprocessorProducer$RepeatedPostprocessorConsumer$1(this, postprocessorProducer));
    }

    protected void onNewResultImpl(CloseableReference<CloseableImage> closeableReference, int i) {
        if (!isNotLast(i)) {
            setSourceImageRef(closeableReference);
            updateInternal();
        }
    }

    protected void onFailureImpl(Throwable th) {
        if (close()) {
            getConsumer().onFailure(th);
        }
    }

    protected void onCancellationImpl() {
        if (close()) {
            getConsumer().onCancellation();
        }
    }

    public synchronized void update() {
        updateInternal();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateInternal() {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = r3.mIsClosed;	 Catch:{ all -> 0x001a }
        if (r0 == 0) goto L_0x0007;
    L_0x0005:
        monitor-exit(r3);	 Catch:{ all -> 0x001a }
    L_0x0006:
        return;
    L_0x0007:
        r0 = r3.mSourceImageRef;	 Catch:{ all -> 0x001a }
        r1 = com.facebook.common.references.CloseableReference.cloneOrNull(r0);	 Catch:{ all -> 0x001a }
        monitor-exit(r3);	 Catch:{ all -> 0x001a }
        r0 = r3.getConsumer();	 Catch:{ all -> 0x001d }
        r2 = 0;
        r0.onNewResult(r1, r2);	 Catch:{ all -> 0x001d }
        com.facebook.common.references.CloseableReference.closeSafely(r1);
        goto L_0x0006;
    L_0x001a:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x001a }
        throw r0;
    L_0x001d:
        r0 = move-exception;
        com.facebook.common.references.CloseableReference.closeSafely(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.PostprocessorProducer$RepeatedPostprocessorConsumer.updateInternal():void");
    }

    private void setSourceImageRef(CloseableReference<CloseableImage> closeableReference) {
        synchronized (this) {
            if (this.mIsClosed) {
                return;
            }
            CloseableReference closeableReference2 = this.mSourceImageRef;
            this.mSourceImageRef = CloseableReference.cloneOrNull((CloseableReference) closeableReference);
            CloseableReference.closeSafely(closeableReference2);
        }
    }

    private boolean close() {
        boolean z = true;
        synchronized (this) {
            if (this.mIsClosed) {
                z = false;
            } else {
                CloseableReference closeableReference = this.mSourceImageRef;
                this.mSourceImageRef = null;
                this.mIsClosed = true;
                CloseableReference.closeSafely(closeableReference);
            }
        }
        return z;
    }
}
