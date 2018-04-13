package rx.internal.schedulers;

import rx.b.a;
import rx.g;

class j implements a {
    private final a a;
    private final g.a b;
    private final long c;

    public j(a aVar, g.a aVar2, long j) {
        this.a = aVar;
        this.b = aVar2;
        this.c = j;
    }

    public void call() {
        if (!this.b.isUnsubscribed()) {
            long a = this.c - this.b.a();
            if (a > 0) {
                try {
                    Thread.sleep(a);
                } catch (Throwable e) {
                    Thread.currentThread().interrupt();
                    rx.exceptions.a.a(e);
                }
            }
            if (!this.b.isUnsubscribed()) {
                this.a.call();
            }
        }
    }
}
