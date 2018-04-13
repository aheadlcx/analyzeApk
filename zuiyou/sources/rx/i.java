package rx;

import rx.internal.util.f;

public abstract class i<T> implements k {
    private final f a = new f();

    public abstract void a(T t);

    public abstract void a(Throwable th);

    public final void a(k kVar) {
        this.a.a(kVar);
    }

    public final void unsubscribe() {
        this.a.unsubscribe();
    }

    public final boolean isUnsubscribed() {
        return this.a.isUnsubscribed();
    }
}
