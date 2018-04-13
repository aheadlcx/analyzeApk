package rx.g;

import java.util.concurrent.atomic.AtomicReference;
import rx.k;

public final class a implements k {
    static final rx.b.a b = new rx.b.a() {
        public void call() {
        }
    };
    final AtomicReference<rx.b.a> a;

    public a() {
        this.a = new AtomicReference();
    }

    private a(rx.b.a aVar) {
        this.a = new AtomicReference(aVar);
    }

    public static a a(rx.b.a aVar) {
        return new a(aVar);
    }

    public boolean isUnsubscribed() {
        return this.a.get() == b;
    }

    public void unsubscribe() {
        if (((rx.b.a) this.a.get()) != b) {
            rx.b.a aVar = (rx.b.a) this.a.getAndSet(b);
            if (aVar != null && aVar != b) {
                aVar.call();
            }
        }
    }
}
