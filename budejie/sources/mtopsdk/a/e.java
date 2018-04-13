package mtopsdk.a;

import java.util.concurrent.CancellationException;
import mtopsdk.a.b.b;
import mtopsdk.common.util.m;

final class e implements Runnable {
    private f a;
    private /* synthetic */ c b;

    public e(c cVar, b bVar, f fVar) {
        this.b = cVar;
        this.a = fVar;
    }

    public final void run() {
        try {
            if (this.b.a) {
                m.a("mtopsdk.DefaultCallImpl", "call task is canceled.");
                this.a.a(this.b);
                return;
            }
            mtopsdk.a.b.e b = this.b.b();
            if (b == null) {
                this.a.a(this.b, new Exception("response is null"));
            } else {
                this.a.a(this.b, b);
            }
        } catch (CancellationException e) {
            this.a.a(this.b);
        } catch (InterruptedException e2) {
            this.a.a(this.b);
        } catch (Throwable e3) {
            this.a.a(this.b, (Exception) e3);
            m.b("mtopsdk.DefaultCallImpl", "do call.execute failed.", e3);
        }
    }
}
