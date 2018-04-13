package rx.a;

import android.os.Looper;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.k;

public abstract class a implements k {
    private final AtomicBoolean a = new AtomicBoolean();

    protected abstract void a();

    public static void b() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("Expected to be called on the main thread but was " + Thread.currentThread().getName());
        }
    }

    public final boolean isUnsubscribed() {
        return this.a.get();
    }

    public final void unsubscribe() {
        if (!this.a.compareAndSet(false, true)) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            a();
        } else {
            rx.a.b.a.a().a().a(new rx.b.a(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void call() {
                    this.a.a();
                }
            });
        }
    }
}
