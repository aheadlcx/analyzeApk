package rx.internal.util;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.b.g;
import rx.d;
import rx.d$a;
import rx.e;
import rx.f;
import rx.internal.producers.SingleProducer;
import rx.j;
import rx.k;

public final class ScalarSynchronousObservable<T> extends d<T> {
    static final boolean c = Boolean.valueOf(System.getProperty("rx.just.strong-mode", "false")).booleanValue();
    final T b;

    static final class ScalarAsyncProducer<T> extends AtomicBoolean implements rx.b.a, f {
        private static final long serialVersionUID = -2466317989629281651L;
        final j<? super T> actual;
        final g<rx.b.a, k> onSchedule;
        final T value;

        public ScalarAsyncProducer(j<? super T> jVar, T t, g<rx.b.a, k> gVar) {
            this.actual = jVar;
            this.value = t;
            this.onSchedule = gVar;
        }

        public void request(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + j);
            } else if (j != 0 && compareAndSet(false, true)) {
                this.actual.add((k) this.onSchedule.call(this));
            }
        }

        public void call() {
            e eVar = this.actual;
            if (!eVar.isUnsubscribed()) {
                Object obj = this.value;
                try {
                    eVar.onNext(obj);
                    if (!eVar.isUnsubscribed()) {
                        eVar.onCompleted();
                    }
                } catch (Throwable th) {
                    rx.exceptions.a.a(th, eVar, obj);
                }
            }
        }

        public String toString() {
            return "ScalarAsyncProducer[" + this.value + ", " + get() + "]";
        }
    }

    static final class a<T> implements d$a<T> {
        final T a;

        public /* synthetic */ void call(Object obj) {
            a((j) obj);
        }

        a(T t) {
            this.a = t;
        }

        public void a(j<? super T> jVar) {
            jVar.setProducer(ScalarSynchronousObservable.a(jVar, this.a));
        }
    }

    static final class b<T> implements d$a<T> {
        final T a;
        final g<rx.b.a, k> b;

        public /* synthetic */ void call(Object obj) {
            a((j) obj);
        }

        b(T t, g<rx.b.a, k> gVar) {
            this.a = t;
            this.b = gVar;
        }

        public void a(j<? super T> jVar) {
            jVar.setProducer(new ScalarAsyncProducer(jVar, this.a, this.b));
        }
    }

    static final class c<T> implements f {
        final j<? super T> a;
        final T b;
        boolean c;

        public c(j<? super T> jVar, T t) {
            this.a = jVar;
            this.b = t;
        }

        public void request(long j) {
            if (!this.c) {
                if (j < 0) {
                    throw new IllegalStateException("n >= required but it was " + j);
                } else if (j != 0) {
                    this.c = true;
                    e eVar = this.a;
                    if (!eVar.isUnsubscribed()) {
                        Object obj = this.b;
                        try {
                            eVar.onNext(obj);
                            if (!eVar.isUnsubscribed()) {
                                eVar.onCompleted();
                            }
                        } catch (Throwable th) {
                            rx.exceptions.a.a(th, eVar, obj);
                        }
                    }
                }
            }
        }
    }

    static <T> f a(j<? super T> jVar, T t) {
        if (c) {
            return new SingleProducer(jVar, t);
        }
        return new c(jVar, t);
    }

    public static <T> ScalarSynchronousObservable<T> b(T t) {
        return new ScalarSynchronousObservable(t);
    }

    protected ScalarSynchronousObservable(T t) {
        super(rx.e.c.a(new a(t)));
        this.b = t;
    }

    public T h() {
        return this.b;
    }

    public d<T> d(final rx.g gVar) {
        g anonymousClass1;
        if (gVar instanceof rx.internal.schedulers.b) {
            final rx.internal.schedulers.b bVar = (rx.internal.schedulers.b) gVar;
            anonymousClass1 = new g<rx.b.a, k>(this) {
                final /* synthetic */ ScalarSynchronousObservable b;

                public /* synthetic */ Object call(Object obj) {
                    return a((rx.b.a) obj);
                }

                public k a(rx.b.a aVar) {
                    return bVar.a(aVar);
                }
            };
        } else {
            anonymousClass1 = new g<rx.b.a, k>(this) {
                final /* synthetic */ ScalarSynchronousObservable b;

                public /* synthetic */ Object call(Object obj) {
                    return a((rx.b.a) obj);
                }

                public k a(final rx.b.a aVar) {
                    final k a = gVar.a();
                    a.a(new rx.b.a(this) {
                        final /* synthetic */ AnonymousClass2 c;

                        public void call() {
                            try {
                                aVar.call();
                            } finally {
                                a.unsubscribe();
                            }
                        }
                    });
                    return a;
                }
            };
        }
        return b(new b(this.b, anonymousClass1));
    }

    public <R> d<R> f(final g<? super T, ? extends d<? extends R>> gVar) {
        return b(new d$a<R>(this) {
            final /* synthetic */ ScalarSynchronousObservable b;

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super R> jVar) {
                d dVar = (d) gVar.call(this.b.b);
                if (dVar instanceof ScalarSynchronousObservable) {
                    jVar.setProducer(ScalarSynchronousObservable.a(jVar, ((ScalarSynchronousObservable) dVar).b));
                } else {
                    dVar.a(rx.d.d.a(jVar));
                }
            }
        });
    }
}
