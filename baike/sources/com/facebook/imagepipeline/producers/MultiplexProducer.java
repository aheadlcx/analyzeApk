package com.facebook.imagepipeline.producers;

import android.util.Pair;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Sets;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.common.Priority;
import java.io.Closeable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class MultiplexProducer<K, T extends Closeable> implements Producer<T> {
    private final Producer<T> mInputProducer;
    @GuardedBy("this")
    @VisibleForTesting
    final Map<K, Multiplexer> mMultiplexers = new HashMap();

    @VisibleForTesting
    class Multiplexer {
        private final CopyOnWriteArraySet<Pair<Consumer<T>, ProducerContext>> mConsumerContextPairs = Sets.newCopyOnWriteArraySet();
        @GuardedBy("Multiplexer.this")
        @Nullable
        private com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer.ForwardingConsumer mForwardingConsumer;
        private final K mKey;
        @GuardedBy("Multiplexer.this")
        @Nullable
        private T mLastIntermediateResult;
        @GuardedBy("Multiplexer.this")
        private float mLastProgress;
        @GuardedBy("Multiplexer.this")
        private int mLastStatus;
        @GuardedBy("Multiplexer.this")
        @Nullable
        private BaseProducerContext mMultiplexProducerContext;

        private class ForwardingConsumer extends BaseConsumer<T> {
            private ForwardingConsumer() {
            }

            protected void onNewResultImpl(T t, int i) {
                Multiplexer.this.onNextResult(this, t, i);
            }

            protected void onFailureImpl(Throwable th) {
                Multiplexer.this.onFailure(this, th);
            }

            protected void onCancellationImpl() {
                Multiplexer.this.onCancelled(this);
            }

            protected void onProgressUpdateImpl(float f) {
                Multiplexer.this.onProgressUpdate(this, f);
            }
        }

        public Multiplexer(K k) {
            this.mKey = k;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean addNewConsumer(com.facebook.imagepipeline.producers.Consumer<T> r8, com.facebook.imagepipeline.producers.ProducerContext r9) {
            /*
            r7 = this;
            r1 = android.util.Pair.create(r8, r9);
            monitor-enter(r7);
            r0 = com.facebook.imagepipeline.producers.MultiplexProducer.this;	 Catch:{ all -> 0x0051 }
            r2 = r7.mKey;	 Catch:{ all -> 0x0051 }
            r0 = r0.getExistingMultiplexer(r2);	 Catch:{ all -> 0x0051 }
            if (r0 == r7) goto L_0x0012;
        L_0x000f:
            r0 = 0;
            monitor-exit(r7);	 Catch:{ all -> 0x0051 }
        L_0x0011:
            return r0;
        L_0x0012:
            r0 = r7.mConsumerContextPairs;	 Catch:{ all -> 0x0051 }
            r0.add(r1);	 Catch:{ all -> 0x0051 }
            r2 = r7.updateIsPrefetch();	 Catch:{ all -> 0x0051 }
            r3 = r7.updatePriority();	 Catch:{ all -> 0x0051 }
            r4 = r7.updateIsIntermediateResultExpected();	 Catch:{ all -> 0x0051 }
            r0 = r7.mLastIntermediateResult;	 Catch:{ all -> 0x0051 }
            r5 = r7.mLastProgress;	 Catch:{ all -> 0x0051 }
            r6 = r7.mLastStatus;	 Catch:{ all -> 0x0051 }
            monitor-exit(r7);	 Catch:{ all -> 0x0051 }
            com.facebook.imagepipeline.producers.BaseProducerContext.callOnIsPrefetchChanged(r2);
            com.facebook.imagepipeline.producers.BaseProducerContext.callOnPriorityChanged(r3);
            com.facebook.imagepipeline.producers.BaseProducerContext.callOnIsIntermediateResultExpectedChanged(r4);
            monitor-enter(r1);
            monitor-enter(r7);	 Catch:{ all -> 0x0060 }
            r2 = r7.mLastIntermediateResult;	 Catch:{ all -> 0x005d }
            if (r0 == r2) goto L_0x0054;
        L_0x0039:
            r0 = 0;
        L_0x003a:
            monitor-exit(r7);	 Catch:{ all -> 0x005d }
            if (r0 == 0) goto L_0x004b;
        L_0x003d:
            r2 = 0;
            r2 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1));
            if (r2 <= 0) goto L_0x0045;
        L_0x0042:
            r8.onProgressUpdate(r5);	 Catch:{ all -> 0x0060 }
        L_0x0045:
            r8.onNewResult(r0, r6);	 Catch:{ all -> 0x0060 }
            r7.closeSafely(r0);	 Catch:{ all -> 0x0060 }
        L_0x004b:
            monitor-exit(r1);	 Catch:{ all -> 0x0060 }
            r7.addCallbacks(r1, r9);
            r0 = 1;
            goto L_0x0011;
        L_0x0051:
            r0 = move-exception;
            monitor-exit(r7);	 Catch:{ all -> 0x0051 }
            throw r0;
        L_0x0054:
            if (r0 == 0) goto L_0x003a;
        L_0x0056:
            r2 = com.facebook.imagepipeline.producers.MultiplexProducer.this;	 Catch:{ all -> 0x005d }
            r0 = r2.cloneOrNull(r0);	 Catch:{ all -> 0x005d }
            goto L_0x003a;
        L_0x005d:
            r0 = move-exception;
            monitor-exit(r7);	 Catch:{ all -> 0x005d }
            throw r0;	 Catch:{ all -> 0x0060 }
        L_0x0060:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0060 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.MultiplexProducer.Multiplexer.addNewConsumer(com.facebook.imagepipeline.producers.Consumer, com.facebook.imagepipeline.producers.ProducerContext):boolean");
        }

        private void addCallbacks(final Pair<Consumer<T>, ProducerContext> pair, ProducerContext producerContext) {
            producerContext.addCallbacks(new BaseProducerContextCallbacks() {
                public void onCancellationRequested() {
                    List list;
                    List list2;
                    BaseProducerContext baseProducerContext;
                    List list3 = null;
                    synchronized (Multiplexer.this) {
                        boolean remove = Multiplexer.this.mConsumerContextPairs.remove(pair);
                        if (!remove) {
                            list = null;
                            list2 = null;
                            baseProducerContext = null;
                        } else if (Multiplexer.this.mConsumerContextPairs.isEmpty()) {
                            list2 = null;
                            baseProducerContext = Multiplexer.this.mMultiplexProducerContext;
                            list = null;
                        } else {
                            List access$400 = Multiplexer.this.updateIsPrefetch();
                            list2 = Multiplexer.this.updatePriority();
                            list = list2;
                            list2 = access$400;
                            baseProducerContext = null;
                            list3 = Multiplexer.this.updateIsIntermediateResultExpected();
                        }
                    }
                    BaseProducerContext.callOnIsPrefetchChanged(list2);
                    BaseProducerContext.callOnPriorityChanged(list);
                    BaseProducerContext.callOnIsIntermediateResultExpectedChanged(list3);
                    if (baseProducerContext != null) {
                        baseProducerContext.cancel();
                    }
                    if (remove) {
                        ((Consumer) pair.first).onCancellation();
                    }
                }

                public void onIsPrefetchChanged() {
                    BaseProducerContext.callOnIsPrefetchChanged(Multiplexer.this.updateIsPrefetch());
                }

                public void onIsIntermediateResultExpectedChanged() {
                    BaseProducerContext.callOnIsIntermediateResultExpectedChanged(Multiplexer.this.updateIsIntermediateResultExpected());
                }

                public void onPriorityChanged() {
                    BaseProducerContext.callOnPriorityChanged(Multiplexer.this.updatePriority());
                }
            });
        }

        private void startInputProducerIfHasAttachedConsumers() {
            boolean z = true;
            synchronized (this) {
                Preconditions.checkArgument(this.mMultiplexProducerContext == null);
                if (this.mForwardingConsumer != null) {
                    z = false;
                }
                Preconditions.checkArgument(z);
                if (this.mConsumerContextPairs.isEmpty()) {
                    MultiplexProducer.this.removeMultiplexer(this.mKey, this);
                    return;
                }
                ProducerContext producerContext = (ProducerContext) ((Pair) this.mConsumerContextPairs.iterator().next()).second;
                this.mMultiplexProducerContext = new BaseProducerContext(producerContext.getImageRequest(), producerContext.getId(), producerContext.getListener(), producerContext.getCallerContext(), producerContext.getLowestPermittedRequestLevel(), computeIsPrefetch(), computeIsIntermediateResultExpected(), computePriority());
                this.mForwardingConsumer = new ForwardingConsumer();
                ProducerContext producerContext2 = this.mMultiplexProducerContext;
                Consumer consumer = this.mForwardingConsumer;
                MultiplexProducer.this.mInputProducer.produceResults(consumer, producerContext2);
            }
        }

        @Nullable
        private synchronized List<ProducerContextCallbacks> updateIsPrefetch() {
            List<ProducerContextCallbacks> list;
            if (this.mMultiplexProducerContext == null) {
                list = null;
            } else {
                list = this.mMultiplexProducerContext.setIsPrefetchNoCallbacks(computeIsPrefetch());
            }
            return list;
        }

        private synchronized boolean computeIsPrefetch() {
            boolean z;
            Iterator it = this.mConsumerContextPairs.iterator();
            while (it.hasNext()) {
                if (!((ProducerContext) ((Pair) it.next()).second).isPrefetch()) {
                    z = false;
                    break;
                }
            }
            z = true;
            return z;
        }

        @Nullable
        private synchronized List<ProducerContextCallbacks> updateIsIntermediateResultExpected() {
            List<ProducerContextCallbacks> list;
            if (this.mMultiplexProducerContext == null) {
                list = null;
            } else {
                list = this.mMultiplexProducerContext.setIsIntermediateResultExpectedNoCallbacks(computeIsIntermediateResultExpected());
            }
            return list;
        }

        private synchronized boolean computeIsIntermediateResultExpected() {
            boolean z;
            Iterator it = this.mConsumerContextPairs.iterator();
            while (it.hasNext()) {
                if (((ProducerContext) ((Pair) it.next()).second).isIntermediateResultExpected()) {
                    z = true;
                    break;
                }
            }
            z = false;
            return z;
        }

        @Nullable
        private synchronized List<ProducerContextCallbacks> updatePriority() {
            List<ProducerContextCallbacks> list;
            if (this.mMultiplexProducerContext == null) {
                list = null;
            } else {
                list = this.mMultiplexProducerContext.setPriorityNoCallbacks(computePriority());
            }
            return list;
        }

        private synchronized Priority computePriority() {
            Priority priority;
            Priority priority2 = Priority.LOW;
            Iterator it = this.mConsumerContextPairs.iterator();
            priority = priority2;
            while (it.hasNext()) {
                priority = Priority.getHigherPriority(priority, ((ProducerContext) ((Pair) it.next()).second).getPriority());
            }
            return priority;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onFailure(com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer.ForwardingConsumer r4, java.lang.Throwable r5) {
            /*
            r3 = this;
            monitor-enter(r3);
            r0 = r3.mForwardingConsumer;	 Catch:{ all -> 0x003c }
            if (r0 == r4) goto L_0x0007;
        L_0x0005:
            monitor-exit(r3);	 Catch:{ all -> 0x003c }
        L_0x0006:
            return;
        L_0x0007:
            r0 = r3.mConsumerContextPairs;	 Catch:{ all -> 0x003c }
            r2 = r0.iterator();	 Catch:{ all -> 0x003c }
            r0 = r3.mConsumerContextPairs;	 Catch:{ all -> 0x003c }
            r0.clear();	 Catch:{ all -> 0x003c }
            r0 = com.facebook.imagepipeline.producers.MultiplexProducer.this;	 Catch:{ all -> 0x003c }
            r1 = r3.mKey;	 Catch:{ all -> 0x003c }
            r0.removeMultiplexer(r1, r3);	 Catch:{ all -> 0x003c }
            r0 = r3.mLastIntermediateResult;	 Catch:{ all -> 0x003c }
            r3.closeSafely(r0);	 Catch:{ all -> 0x003c }
            r0 = 0;
            r3.mLastIntermediateResult = r0;	 Catch:{ all -> 0x003c }
            monitor-exit(r3);	 Catch:{ all -> 0x003c }
        L_0x0022:
            r0 = r2.hasNext();
            if (r0 == 0) goto L_0x0006;
        L_0x0028:
            r0 = r2.next();
            r1 = r0;
            r1 = (android.util.Pair) r1;
            monitor-enter(r1);
            r0 = r1.first;	 Catch:{ all -> 0x0039 }
            r0 = (com.facebook.imagepipeline.producers.Consumer) r0;	 Catch:{ all -> 0x0039 }
            r0.onFailure(r5);	 Catch:{ all -> 0x0039 }
            monitor-exit(r1);	 Catch:{ all -> 0x0039 }
            goto L_0x0022;
        L_0x0039:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0039 }
            throw r0;
        L_0x003c:
            r0 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x003c }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.MultiplexProducer.Multiplexer.onFailure(com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer$ForwardingConsumer, java.lang.Throwable):void");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onNextResult(com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer.ForwardingConsumer r4, T r5, int r6) {
            /*
            r3 = this;
            monitor-enter(r3);
            r0 = r3.mForwardingConsumer;	 Catch:{ all -> 0x004d }
            if (r0 == r4) goto L_0x0007;
        L_0x0005:
            monitor-exit(r3);	 Catch:{ all -> 0x004d }
        L_0x0006:
            return;
        L_0x0007:
            r0 = r3.mLastIntermediateResult;	 Catch:{ all -> 0x004d }
            r3.closeSafely(r0);	 Catch:{ all -> 0x004d }
            r0 = 0;
            r3.mLastIntermediateResult = r0;	 Catch:{ all -> 0x004d }
            r0 = r3.mConsumerContextPairs;	 Catch:{ all -> 0x004d }
            r2 = r0.iterator();	 Catch:{ all -> 0x004d }
            r0 = com.facebook.imagepipeline.producers.BaseConsumer.isNotLast(r6);	 Catch:{ all -> 0x004d }
            if (r0 == 0) goto L_0x0040;
        L_0x001b:
            r0 = com.facebook.imagepipeline.producers.MultiplexProducer.this;	 Catch:{ all -> 0x004d }
            r0 = r0.cloneOrNull(r5);	 Catch:{ all -> 0x004d }
            r3.mLastIntermediateResult = r0;	 Catch:{ all -> 0x004d }
            r3.mLastStatus = r6;	 Catch:{ all -> 0x004d }
        L_0x0025:
            monitor-exit(r3);	 Catch:{ all -> 0x004d }
        L_0x0026:
            r0 = r2.hasNext();
            if (r0 == 0) goto L_0x0006;
        L_0x002c:
            r0 = r2.next();
            r1 = r0;
            r1 = (android.util.Pair) r1;
            monitor-enter(r1);
            r0 = r1.first;	 Catch:{ all -> 0x003d }
            r0 = (com.facebook.imagepipeline.producers.Consumer) r0;	 Catch:{ all -> 0x003d }
            r0.onNewResult(r5, r6);	 Catch:{ all -> 0x003d }
            monitor-exit(r1);	 Catch:{ all -> 0x003d }
            goto L_0x0026;
        L_0x003d:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x003d }
            throw r0;
        L_0x0040:
            r0 = r3.mConsumerContextPairs;	 Catch:{ all -> 0x004d }
            r0.clear();	 Catch:{ all -> 0x004d }
            r0 = com.facebook.imagepipeline.producers.MultiplexProducer.this;	 Catch:{ all -> 0x004d }
            r1 = r3.mKey;	 Catch:{ all -> 0x004d }
            r0.removeMultiplexer(r1, r3);	 Catch:{ all -> 0x004d }
            goto L_0x0025;
        L_0x004d:
            r0 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x004d }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.MultiplexProducer.Multiplexer.onNextResult(com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer$ForwardingConsumer, java.io.Closeable, int):void");
        }

        public void onCancelled(com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer.ForwardingConsumer forwardingConsumer) {
            synchronized (this) {
                if (this.mForwardingConsumer != forwardingConsumer) {
                    return;
                }
                this.mForwardingConsumer = null;
                this.mMultiplexProducerContext = null;
                closeSafely(this.mLastIntermediateResult);
                this.mLastIntermediateResult = null;
                startInputProducerIfHasAttachedConsumers();
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onProgressUpdate(com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer.ForwardingConsumer r4, float r5) {
            /*
            r3 = this;
            monitor-enter(r3);
            r0 = r3.mForwardingConsumer;	 Catch:{ all -> 0x002a }
            if (r0 == r4) goto L_0x0007;
        L_0x0005:
            monitor-exit(r3);	 Catch:{ all -> 0x002a }
        L_0x0006:
            return;
        L_0x0007:
            r3.mLastProgress = r5;	 Catch:{ all -> 0x002a }
            r0 = r3.mConsumerContextPairs;	 Catch:{ all -> 0x002a }
            r2 = r0.iterator();	 Catch:{ all -> 0x002a }
            monitor-exit(r3);	 Catch:{ all -> 0x002a }
        L_0x0010:
            r0 = r2.hasNext();
            if (r0 == 0) goto L_0x0006;
        L_0x0016:
            r0 = r2.next();
            r1 = r0;
            r1 = (android.util.Pair) r1;
            monitor-enter(r1);
            r0 = r1.first;	 Catch:{ all -> 0x0027 }
            r0 = (com.facebook.imagepipeline.producers.Consumer) r0;	 Catch:{ all -> 0x0027 }
            r0.onProgressUpdate(r5);	 Catch:{ all -> 0x0027 }
            monitor-exit(r1);	 Catch:{ all -> 0x0027 }
            goto L_0x0010;
        L_0x0027:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0027 }
            throw r0;
        L_0x002a:
            r0 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x002a }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.MultiplexProducer.Multiplexer.onProgressUpdate(com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer$ForwardingConsumer, float):void");
        }

        private void closeSafely(Closeable closeable) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    protected abstract T cloneOrNull(T t);

    protected abstract K getKey(ProducerContext producerContext);

    protected MultiplexProducer(Producer<T> producer) {
        this.mInputProducer = producer;
    }

    public void produceResults(Consumer<T> consumer, ProducerContext producerContext) {
        Object key = getKey(producerContext);
        Multiplexer existingMultiplexer;
        do {
            Object obj = null;
            synchronized (this) {
                existingMultiplexer = getExistingMultiplexer(key);
                if (existingMultiplexer == null) {
                    existingMultiplexer = createAndPutNewMultiplexer(key);
                    obj = 1;
                }
            }
        } while (!existingMultiplexer.addNewConsumer(consumer, producerContext));
        if (obj != null) {
            existingMultiplexer.startInputProducerIfHasAttachedConsumers();
        }
    }

    private synchronized Multiplexer getExistingMultiplexer(K k) {
        return (Multiplexer) this.mMultiplexers.get(k);
    }

    private synchronized Multiplexer createAndPutNewMultiplexer(K k) {
        Multiplexer multiplexer;
        multiplexer = new Multiplexer(k);
        this.mMultiplexers.put(k, multiplexer);
        return multiplexer;
    }

    private synchronized void removeMultiplexer(K k, Multiplexer multiplexer) {
        if (this.mMultiplexers.get(k) == multiplexer) {
            this.mMultiplexers.remove(k);
        }
    }
}
