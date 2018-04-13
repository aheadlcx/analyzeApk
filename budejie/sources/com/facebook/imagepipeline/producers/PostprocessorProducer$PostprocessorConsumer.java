package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.request.Postprocessor;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

class PostprocessorProducer$PostprocessorConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> {
    @GuardedBy("PostprocessorConsumer.this")
    private boolean mIsClosed;
    @GuardedBy("PostprocessorConsumer.this")
    private boolean mIsDirty = false;
    @GuardedBy("PostprocessorConsumer.this")
    private boolean mIsPostProcessingRunning = false;
    private final ProducerListener mListener;
    private final Postprocessor mPostprocessor;
    private final String mRequestId;
    @GuardedBy("PostprocessorConsumer.this")
    @Nullable
    private CloseableReference<CloseableImage> mSourceImageRef = null;
    @GuardedBy("PostprocessorConsumer.this")
    private int mStatus = 0;
    final /* synthetic */ PostprocessorProducer this$0;

    public PostprocessorProducer$PostprocessorConsumer(PostprocessorProducer postprocessorProducer, Consumer<CloseableReference<CloseableImage>> consumer, ProducerListener producerListener, String str, Postprocessor postprocessor, ProducerContext producerContext) {
        this.this$0 = postprocessorProducer;
        super(consumer);
        this.mListener = producerListener;
        this.mRequestId = str;
        this.mPostprocessor = postprocessor;
        producerContext.addCallbacks(new PostprocessorProducer$PostprocessorConsumer$1(this, postprocessorProducer));
    }

    protected void onNewResultImpl(CloseableReference<CloseableImage> closeableReference, int i) {
        if (CloseableReference.isValid(closeableReference)) {
            updateSourceImageRef(closeableReference, i);
        } else if (isLast(i)) {
            maybeNotifyOnNewResult(null, i);
        }
    }

    protected void onFailureImpl(Throwable th) {
        maybeNotifyOnFailure(th);
    }

    protected void onCancellationImpl() {
        maybeNotifyOnCancellation();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateSourceImageRef(@javax.annotation.Nullable com.facebook.common.references.CloseableReference<com.facebook.imagepipeline.image.CloseableImage> r3, int r4) {
        /*
        r2 = this;
        monitor-enter(r2);
        r0 = r2.mIsClosed;	 Catch:{ all -> 0x0022 }
        if (r0 == 0) goto L_0x0007;
    L_0x0005:
        monitor-exit(r2);	 Catch:{ all -> 0x0022 }
    L_0x0006:
        return;
    L_0x0007:
        r0 = r2.mSourceImageRef;	 Catch:{ all -> 0x0022 }
        r1 = com.facebook.common.references.CloseableReference.cloneOrNull(r3);	 Catch:{ all -> 0x0022 }
        r2.mSourceImageRef = r1;	 Catch:{ all -> 0x0022 }
        r2.mStatus = r4;	 Catch:{ all -> 0x0022 }
        r1 = 1;
        r2.mIsDirty = r1;	 Catch:{ all -> 0x0022 }
        r1 = r2.setRunningIfDirtyAndNotRunning();	 Catch:{ all -> 0x0022 }
        monitor-exit(r2);	 Catch:{ all -> 0x0022 }
        com.facebook.common.references.CloseableReference.closeSafely(r0);
        if (r1 == 0) goto L_0x0006;
    L_0x001e:
        r2.submitPostprocessing();
        goto L_0x0006;
    L_0x0022:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0022 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.PostprocessorProducer$PostprocessorConsumer.updateSourceImageRef(com.facebook.common.references.CloseableReference, int):void");
    }

    private void submitPostprocessing() {
        PostprocessorProducer.access$800(this.this$0).execute(new PostprocessorProducer$PostprocessorConsumer$2(this));
    }

    private void clearRunningAndStartIfDirty() {
        synchronized (this) {
            this.mIsPostProcessingRunning = false;
            boolean runningIfDirtyAndNotRunning = setRunningIfDirtyAndNotRunning();
        }
        if (runningIfDirtyAndNotRunning) {
            submitPostprocessing();
        }
    }

    private synchronized boolean setRunningIfDirtyAndNotRunning() {
        boolean z = true;
        synchronized (this) {
            if (this.mIsClosed || !this.mIsDirty || this.mIsPostProcessingRunning || !CloseableReference.isValid(this.mSourceImageRef)) {
                z = false;
            } else {
                this.mIsPostProcessingRunning = true;
            }
        }
        return z;
    }

    private void doPostprocessing(CloseableReference<CloseableImage> closeableReference, int i) {
        Preconditions.checkArgument(CloseableReference.isValid(closeableReference));
        if (shouldPostprocess((CloseableImage) closeableReference.get())) {
            this.mListener.onProducerStart(this.mRequestId, PostprocessorProducer.NAME);
            CloseableReference closeableReference2 = null;
            try {
                closeableReference2 = postprocessInternal((CloseableImage) closeableReference.get());
                this.mListener.onProducerFinishWithSuccess(this.mRequestId, PostprocessorProducer.NAME, getExtraMap(this.mListener, this.mRequestId, this.mPostprocessor));
                maybeNotifyOnNewResult(closeableReference2, i);
            } catch (Throwable e) {
                this.mListener.onProducerFinishWithFailure(this.mRequestId, PostprocessorProducer.NAME, e, getExtraMap(this.mListener, this.mRequestId, this.mPostprocessor));
                maybeNotifyOnFailure(e);
            } finally {
                CloseableReference.closeSafely(closeableReference2);
            }
        } else {
            maybeNotifyOnNewResult(closeableReference, i);
        }
    }

    private Map<String, String> getExtraMap(ProducerListener producerListener, String str, Postprocessor postprocessor) {
        if (producerListener.requiresExtraMap(str)) {
            return ImmutableMap.of("Postprocessor", postprocessor.getName());
        }
        return null;
    }

    private boolean shouldPostprocess(CloseableImage closeableImage) {
        return closeableImage instanceof CloseableStaticBitmap;
    }

    private CloseableReference<CloseableImage> postprocessInternal(CloseableImage closeableImage) {
        CloseableStaticBitmap closeableStaticBitmap = (CloseableStaticBitmap) closeableImage;
        CloseableReference process = this.mPostprocessor.process(closeableStaticBitmap.getUnderlyingBitmap(), PostprocessorProducer.access$900(this.this$0));
        try {
            CloseableReference<CloseableImage> of = CloseableReference.of(new CloseableStaticBitmap(process, closeableImage.getQualityInfo(), closeableStaticBitmap.getRotationAngle()));
            return of;
        } finally {
            CloseableReference.closeSafely(process);
        }
    }

    private void maybeNotifyOnNewResult(CloseableReference<CloseableImage> closeableReference, int i) {
        boolean isLast = isLast(i);
        if ((!isLast && !isClosed()) || (isLast && close())) {
            getConsumer().onNewResult(closeableReference, i);
        }
    }

    private void maybeNotifyOnFailure(Throwable th) {
        if (close()) {
            getConsumer().onFailure(th);
        }
    }

    private void maybeNotifyOnCancellation() {
        if (close()) {
            getConsumer().onCancellation();
        }
    }

    private synchronized boolean isClosed() {
        return this.mIsClosed;
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
