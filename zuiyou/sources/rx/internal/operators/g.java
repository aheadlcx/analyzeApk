package rx.internal.operators;

import rx.d$b;
import rx.e.c;
import rx.exceptions.a;
import rx.f;
import rx.internal.producers.SingleDelayedProducer;
import rx.j;

public final class g<T> implements d$b<Boolean, T> {
    final rx.b.g<? super T, Boolean> a;
    final boolean b;

    public /* synthetic */ Object call(Object obj) {
        return a((j) obj);
    }

    public g(rx.b.g<? super T, Boolean> gVar, boolean z) {
        this.a = gVar;
        this.b = z;
    }

    public j<? super T> a(final j<? super Boolean> jVar) {
        final f singleDelayedProducer = new SingleDelayedProducer(jVar);
        Object anonymousClass1 = new j<T>(this) {
            boolean a;
            boolean b;
            final /* synthetic */ g e;

            public void onNext(T t) {
                if (!this.b) {
                    this.a = true;
                    try {
                        if (((Boolean) this.e.a.call(t)).booleanValue()) {
                            this.b = true;
                            singleDelayedProducer.setValue(Boolean.valueOf(!this.e.b));
                            unsubscribe();
                        }
                    } catch (Throwable th) {
                        a.a(th, this, t);
                    }
                }
            }

            public void onError(Throwable th) {
                if (this.b) {
                    c.a(th);
                    return;
                }
                this.b = true;
                jVar.onError(th);
            }

            public void onCompleted() {
                if (!this.b) {
                    this.b = true;
                    if (this.a) {
                        singleDelayedProducer.setValue(Boolean.valueOf(false));
                    } else {
                        singleDelayedProducer.setValue(Boolean.valueOf(this.e.b));
                    }
                }
            }
        };
        jVar.add(anonymousClass1);
        jVar.setProducer(singleDelayedProducer);
        return anonymousClass1;
    }
}
