package android.support.v7.widget;

import java.util.ArrayList;
import java.util.Iterator;

class ad implements Runnable {
    final /* synthetic */ ArrayList a;
    final /* synthetic */ DefaultItemAnimator b;

    ad(DefaultItemAnimator defaultItemAnimator, ArrayList arrayList) {
        this.b = defaultItemAnimator;
        this.a = arrayList;
    }

    public void run() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            this.b.a((a) it.next());
        }
        this.a.clear();
        this.b.c.remove(this.a);
    }
}
