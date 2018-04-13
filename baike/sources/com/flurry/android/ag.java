package com.flurry.android;

import android.os.Handler;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class ag {
    private List a = new ArrayList();
    private Handler b;
    private Handler c;
    private int d;
    private Runnable e;

    ag(Handler handler, int i) {
        this.b = handler;
        this.c = new Handler();
        this.d = i;
        this.e = new k(this);
        b();
    }

    final synchronized void a(o oVar) {
        oVar.a();
        this.a.add(new WeakReference(oVar));
    }

    private synchronized void a() {
        List arrayList = new ArrayList();
        for (WeakReference weakReference : this.a) {
            o oVar = (o) weakReference.get();
            if (oVar != null) {
                arrayList.add(oVar);
            }
        }
        this.c.post(new j(arrayList));
        b();
    }

    private synchronized void b() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            if (((WeakReference) it.next()).get() == null) {
                it.remove();
            }
        }
        this.b.removeCallbacks(this.e);
        this.b.postDelayed(this.e, (long) this.d);
    }
}
