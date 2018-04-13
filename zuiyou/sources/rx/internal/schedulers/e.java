package rx.internal.schedulers;

import java.util.concurrent.TimeUnit;
import rx.g;
import rx.k;

public final class e extends g {
    public static final e a = new e();

    final class a extends rx.g.a implements k {
        final rx.g.a a = new rx.g.a();
        final /* synthetic */ e b;

        a(e eVar) {
            this.b = eVar;
        }

        public k a(rx.b.a aVar, long j, TimeUnit timeUnit) {
            return a(new j(aVar, this, this.b.b() + timeUnit.toMillis(j)));
        }

        public k a(rx.b.a aVar) {
            aVar.call();
            return rx.g.e.a();
        }

        public void unsubscribe() {
            this.a.unsubscribe();
        }

        public boolean isUnsubscribed() {
            return this.a.isUnsubscribed();
        }
    }

    private e() {
    }

    public rx.g.a a() {
        return new a(this);
    }
}
