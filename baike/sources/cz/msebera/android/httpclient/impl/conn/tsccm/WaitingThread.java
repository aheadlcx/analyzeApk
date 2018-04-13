package cz.msebera.android.httpclient.impl.conn.tsccm;

import cz.msebera.android.httpclient.util.Args;
import java.util.Date;
import java.util.concurrent.locks.Condition;

@Deprecated
public class WaitingThread {
    private final Condition a;
    private final RouteSpecificPool b;
    private Thread c;
    private boolean d;

    public WaitingThread(Condition condition, RouteSpecificPool routeSpecificPool) {
        Args.notNull(condition, "Condition");
        this.a = condition;
        this.b = routeSpecificPool;
    }

    public final Condition getCondition() {
        return this.a;
    }

    public final RouteSpecificPool getPool() {
        return this.b;
    }

    public final Thread getThread() {
        return this.c;
    }

    public boolean await(Date date) throws InterruptedException {
        if (this.c != null) {
            throw new IllegalStateException("A thread is already waiting on this object.\ncaller: " + Thread.currentThread() + "\nwaiter: " + this.c);
        } else if (this.d) {
            throw new InterruptedException("Operation interrupted");
        } else {
            boolean awaitUntil;
            this.c = Thread.currentThread();
            if (date != null) {
                try {
                    awaitUntil = this.a.awaitUntil(date);
                } catch (Throwable th) {
                    this.c = null;
                }
            } else {
                this.a.await();
                awaitUntil = true;
            }
            if (this.d) {
                throw new InterruptedException("Operation interrupted");
            }
            this.c = null;
            return awaitUntil;
        }
    }

    public void wakeup() {
        if (this.c == null) {
            throw new IllegalStateException("Nobody waiting on this object.");
        }
        this.a.signalAll();
    }

    public void interrupt() {
        this.d = true;
        this.a.signalAll();
    }
}
