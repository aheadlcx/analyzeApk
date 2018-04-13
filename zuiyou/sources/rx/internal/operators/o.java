package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.d$b;
import rx.f;
import rx.j;

public final class o<T> implements d$b<T, T> {
    final int a;

    public /* synthetic */ Object call(Object obj) {
        return a((j) obj);
    }

    public o(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("limit >= 0 required but it was " + i);
        }
        this.a = i;
    }

    public j<? super T> a(final j<? super T> jVar) {
        Object anonymousClass1 = new j<T>(this) {
            int a;
            boolean b;
            final /* synthetic */ o d;

            public void onCompleted() {
                if (!this.b) {
                    this.b = true;
                    jVar.onCompleted();
                }
            }

            public void onError(Throwable th) {
                if (!this.b) {
                    this.b = true;
                    try {
                        jVar.onError(th);
                    } finally {
                        unsubscribe();
                    }
                }
            }

            public void onNext(T t) {
                if (!isUnsubscribed()) {
                    int i = this.a;
                    this.a = i + 1;
                    if (i < this.d.a) {
                        boolean z = this.a == this.d.a;
                        jVar.onNext(t);
                        if (z && !this.b) {
                            this.b = true;
                            try {
                                jVar.onCompleted();
                            } finally {
                                unsubscribe();
                            }
                        }
                    }
                }
            }

            public void setProducer(final f fVar) {
                jVar.setProducer(new f(this) {
                    final AtomicLong a = new AtomicLong(0);
                    final /* synthetic */ AnonymousClass1 c;

                    public void request(long j) {
                        if (j > 0 && !this.c.b) {
                            long min;
                            long j2;
                            do {
                                j2 = this.a.get();
                                min = Math.min(j, ((long) this.c.d.a) - j2);
                                if (min == 0) {
                                    return;
                                }
                            } while (!this.a.compareAndSet(j2, j2 + min));
                            fVar.request(min);
                        }
                    }
                });
            }
        };
        if (this.a == 0) {
            jVar.onCompleted();
            anonymousClass1.unsubscribe();
        }
        jVar.add(anonymousClass1);
        return anonymousClass1;
    }
}
