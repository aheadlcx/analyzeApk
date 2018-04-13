package org.greenrobot.eventbus;

import android.util.Log;

final class b implements Runnable {
    private final g a = new g();
    private final EventBus b;
    private volatile boolean c;

    b(EventBus eventBus) {
        this.b = eventBus;
    }

    public void enqueue(i iVar, Object obj) {
        f a = f.a(iVar, obj);
        synchronized (this) {
            this.a.a(a);
            if (!this.c) {
                this.c = true;
                this.b.a().execute(this);
            }
        }
    }

    public void run() {
        while (true) {
            try {
                f a = this.a.a(1000);
                if (a == null) {
                    synchronized (this) {
                        a = this.a.a();
                        if (a == null) {
                            this.c = false;
                            this.c = false;
                            return;
                        }
                    }
                }
                this.b.a(a);
            } catch (Throwable e) {
                Log.w("Event", Thread.currentThread().getName() + " was interruppted", e);
                this.c = false;
                return;
            } catch (Throwable th) {
                this.c = false;
            }
        }
    }
}
