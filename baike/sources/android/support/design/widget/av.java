package android.support.design.widget;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

class av {
    private static av a;
    private final Object b = new Object();
    private final Handler c = new Handler(Looper.getMainLooper(), new aw(this));
    private b d;
    private b e;

    interface a {
        void dismiss(int i);

        void show();
    }

    private static class b {
        final WeakReference<a> a;
        int b;
        boolean c;

        b(int i, a aVar) {
            this.a = new WeakReference(aVar);
            this.b = i;
        }

        boolean a(a aVar) {
            return aVar != null && this.a.get() == aVar;
        }
    }

    static av a() {
        if (a == null) {
            a = new av();
        }
        return a;
    }

    private av() {
    }

    public void show(int i, a aVar) {
        synchronized (this.b) {
            if (a(aVar)) {
                this.d.b = i;
                this.c.removeCallbacksAndMessages(this.d);
                b(this.d);
                return;
            }
            if (b(aVar)) {
                this.e.b = i;
            } else {
                this.e = new b(i, aVar);
            }
            if (this.d == null || !a(this.d, 4)) {
                this.d = null;
                b();
                return;
            }
        }
    }

    public void dismiss(a aVar, int i) {
        synchronized (this.b) {
            if (a(aVar)) {
                a(this.d, i);
            } else if (b(aVar)) {
                a(this.e, i);
            }
        }
    }

    public void onDismissed(a aVar) {
        synchronized (this.b) {
            if (a(aVar)) {
                this.d = null;
                if (this.e != null) {
                    b();
                }
            }
        }
    }

    public void onShown(a aVar) {
        synchronized (this.b) {
            if (a(aVar)) {
                b(this.d);
            }
        }
    }

    public void pauseTimeout(a aVar) {
        synchronized (this.b) {
            if (a(aVar) && !this.d.c) {
                this.d.c = true;
                this.c.removeCallbacksAndMessages(this.d);
            }
        }
    }

    public void restoreTimeoutIfPaused(a aVar) {
        synchronized (this.b) {
            if (a(aVar) && this.d.c) {
                this.d.c = false;
                b(this.d);
            }
        }
    }

    public boolean isCurrent(a aVar) {
        boolean a;
        synchronized (this.b) {
            a = a(aVar);
        }
        return a;
    }

    public boolean isCurrentOrNext(a aVar) {
        boolean z;
        synchronized (this.b) {
            z = a(aVar) || b(aVar);
        }
        return z;
    }

    private void b() {
        if (this.e != null) {
            this.d = this.e;
            this.e = null;
            a aVar = (a) this.d.a.get();
            if (aVar != null) {
                aVar.show();
            } else {
                this.d = null;
            }
        }
    }

    private boolean a(b bVar, int i) {
        a aVar = (a) bVar.a.get();
        if (aVar == null) {
            return false;
        }
        this.c.removeCallbacksAndMessages(bVar);
        aVar.dismiss(i);
        return true;
    }

    private boolean a(a aVar) {
        return this.d != null && this.d.a(aVar);
    }

    private boolean b(a aVar) {
        return this.e != null && this.e.a(aVar);
    }

    private void b(b bVar) {
        if (bVar.b != -2) {
            int i = 2750;
            if (bVar.b > 0) {
                i = bVar.b;
            } else if (bVar.b == -1) {
                i = 1500;
            }
            this.c.removeCallbacksAndMessages(bVar);
            this.c.sendMessageDelayed(Message.obtain(this.c, 0, bVar), (long) i);
        }
    }

    void a(b bVar) {
        synchronized (this.b) {
            if (this.d == bVar || this.e == bVar) {
                a(bVar, 2);
            }
        }
    }
}
