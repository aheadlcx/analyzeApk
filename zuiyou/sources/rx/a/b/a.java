package rx.a.b;

import android.os.Looper;
import java.util.concurrent.atomic.AtomicReference;
import rx.g;

public final class a {
    private static final AtomicReference<a> a = new AtomicReference();
    private final g b;

    private static a b() {
        a aVar;
        do {
            aVar = (a) a.get();
            if (aVar != null) {
                break;
            }
            aVar = new a();
        } while (!a.compareAndSet(null, aVar));
        return aVar;
    }

    private a() {
        g b = rx.a.a.a.a().b().b();
        if (b != null) {
            this.b = b;
        } else {
            this.b = new b(Looper.getMainLooper());
        }
    }

    public static g a() {
        return b().b;
    }
}
