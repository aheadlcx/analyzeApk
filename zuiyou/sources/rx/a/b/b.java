package rx.a.b;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.TimeUnit;
import rx.e.f;
import rx.exceptions.OnErrorNotImplementedException;
import rx.g;
import rx.g.e;
import rx.k;

class b extends g {
    private final Handler a;

    static class a extends rx.g.a {
        private final Handler a;
        private final rx.a.a.b b = rx.a.a.a.a().b();
        private volatile boolean c;

        a(Handler handler) {
            this.a = handler;
        }

        public void unsubscribe() {
            this.c = true;
            this.a.removeCallbacksAndMessages(this);
        }

        public boolean isUnsubscribed() {
            return this.c;
        }

        public k a(rx.b.a aVar, long j, TimeUnit timeUnit) {
            if (this.c) {
                return e.a();
            }
            k bVar = new b(this.b.a(aVar), this.a);
            Message obtain = Message.obtain(this.a, bVar);
            obtain.obj = this;
            this.a.sendMessageDelayed(obtain, timeUnit.toMillis(j));
            if (!this.c) {
                return bVar;
            }
            this.a.removeCallbacks(bVar);
            return e.a();
        }

        public k a(rx.b.a aVar) {
            return a(aVar, 0, TimeUnit.MILLISECONDS);
        }
    }

    static final class b implements Runnable, k {
        private final rx.b.a a;
        private final Handler b;
        private volatile boolean c;

        b(rx.b.a aVar, Handler handler) {
            this.a = aVar;
            this.b = handler;
        }

        public void run() {
            try {
                this.a.call();
            } catch (Throwable th) {
                Throwable th2;
                Throwable th3 = th2;
                if (th3 instanceof OnErrorNotImplementedException) {
                    th2 = new IllegalStateException("Exception thrown on Scheduler.Worker thread. Add `onError` handling.", th3);
                } else {
                    th2 = new IllegalStateException("Fatal Exception thrown on Scheduler.Worker thread.", th3);
                }
                f.a().b().a(th2);
                Thread currentThread = Thread.currentThread();
                currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th2);
            }
        }

        public void unsubscribe() {
            this.c = true;
            this.b.removeCallbacks(this);
        }

        public boolean isUnsubscribed() {
            return this.c;
        }
    }

    b(Looper looper) {
        this.a = new Handler(looper);
    }

    public rx.g.a a() {
        return new a(this.a);
    }
}
