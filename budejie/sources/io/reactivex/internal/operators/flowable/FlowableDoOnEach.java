package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.annotations.Nullable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.subscribers.BasicFuseableConditionalSubscriber;
import io.reactivex.internal.subscribers.BasicFuseableSubscriber;
import io.reactivex.plugins.RxJavaPlugins;
import org.a.c;

public final class FlowableDoOnEach<T> extends AbstractFlowableWithUpstream<T, T> {
    final Action onAfterTerminate;
    final Action onComplete;
    final Consumer<? super Throwable> onError;
    final Consumer<? super T> onNext;

    static final class DoOnEachConditionalSubscriber<T> extends BasicFuseableConditionalSubscriber<T, T> {
        final Action onAfterTerminate;
        final Action onComplete;
        final Consumer<? super Throwable> onError;
        final Consumer<? super T> onNext;

        DoOnEachConditionalSubscriber(ConditionalSubscriber<? super T> conditionalSubscriber, Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, Action action, Action action2) {
            super(conditionalSubscriber);
            this.onNext = consumer;
            this.onError = consumer2;
            this.onComplete = action;
            this.onAfterTerminate = action2;
        }

        public void onNext(T t) {
            if (!this.done) {
                if (this.sourceMode != 0) {
                    this.actual.onNext(null);
                    return;
                }
                try {
                    this.onNext.accept(t);
                    this.actual.onNext(t);
                } catch (Throwable th) {
                    fail(th);
                }
            }
        }

        public boolean tryOnNext(T t) {
            if (this.done) {
                return false;
            }
            try {
                this.onNext.accept(t);
                return this.actual.tryOnNext(t);
            } catch (Throwable th) {
                fail(th);
                return false;
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            boolean z;
            this.done = true;
            try {
                this.onError.accept(th);
                z = true;
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                this.actual.onError(new CompositeException(th, th2));
                z = false;
            }
            if (z) {
                this.actual.onError(th);
            }
            try {
                this.onAfterTerminate.run();
            } catch (Throwable th22) {
                Exceptions.throwIfFatal(th22);
                RxJavaPlugins.onError(th22);
            }
        }

        public void onComplete() {
            if (!this.done) {
                try {
                    this.onComplete.run();
                    this.done = true;
                    this.actual.onComplete();
                    try {
                        this.onAfterTerminate.run();
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        RxJavaPlugins.onError(th);
                    }
                } catch (Throwable th2) {
                    fail(th2);
                }
            }
        }

        public int requestFusion(int i) {
            return transitiveBoundaryFusion(i);
        }

        @Nullable
        public T poll() throws Exception {
            T poll = this.qs.poll();
            if (poll != null) {
                try {
                    this.onNext.accept(poll);
                } finally {
                    this.onAfterTerminate.run();
                }
            } else if (this.sourceMode == 1) {
                this.onComplete.run();
                this.onAfterTerminate.run();
            }
            return poll;
        }
    }

    static final class DoOnEachSubscriber<T> extends BasicFuseableSubscriber<T, T> {
        final Action onAfterTerminate;
        final Action onComplete;
        final Consumer<? super Throwable> onError;
        final Consumer<? super T> onNext;

        DoOnEachSubscriber(c<? super T> cVar, Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, Action action, Action action2) {
            super(cVar);
            this.onNext = consumer;
            this.onError = consumer2;
            this.onComplete = action;
            this.onAfterTerminate = action2;
        }

        public void onNext(T t) {
            if (!this.done) {
                if (this.sourceMode != 0) {
                    this.actual.onNext(null);
                    return;
                }
                try {
                    this.onNext.accept(t);
                    this.actual.onNext(t);
                } catch (Throwable th) {
                    fail(th);
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            boolean z;
            this.done = true;
            try {
                this.onError.accept(th);
                z = true;
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                this.actual.onError(new CompositeException(th, th2));
                z = false;
            }
            if (z) {
                this.actual.onError(th);
            }
            try {
                this.onAfterTerminate.run();
            } catch (Throwable th22) {
                Exceptions.throwIfFatal(th22);
                RxJavaPlugins.onError(th22);
            }
        }

        public void onComplete() {
            if (!this.done) {
                try {
                    this.onComplete.run();
                    this.done = true;
                    this.actual.onComplete();
                    try {
                        this.onAfterTerminate.run();
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        RxJavaPlugins.onError(th);
                    }
                } catch (Throwable th2) {
                    fail(th2);
                }
            }
        }

        public int requestFusion(int i) {
            return transitiveBoundaryFusion(i);
        }

        @Nullable
        public T poll() throws Exception {
            T poll = this.qs.poll();
            if (poll != null) {
                try {
                    this.onNext.accept(poll);
                } finally {
                    this.onAfterTerminate.run();
                }
            } else if (this.sourceMode == 1) {
                this.onComplete.run();
                this.onAfterTerminate.run();
            }
            return poll;
        }
    }

    public FlowableDoOnEach(Flowable<T> flowable, Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, Action action, Action action2) {
        super(flowable);
        this.onNext = consumer;
        this.onError = consumer2;
        this.onComplete = action;
        this.onAfterTerminate = action2;
    }

    protected void subscribeActual(c<? super T> cVar) {
        if (cVar instanceof ConditionalSubscriber) {
            this.source.subscribe(new DoOnEachConditionalSubscriber((ConditionalSubscriber) cVar, this.onNext, this.onError, this.onComplete, this.onAfterTerminate));
            return;
        }
        this.source.subscribe(new DoOnEachSubscriber(cVar, this.onNext, this.onError, this.onComplete, this.onAfterTerminate));
    }
}
