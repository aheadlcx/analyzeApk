package android.support.v7.widget;

import java.util.ArrayList;
import java.util.Iterator;

class ac implements Runnable {
    final /* synthetic */ ArrayList a;
    final /* synthetic */ DefaultItemAnimator b;

    ac(DefaultItemAnimator defaultItemAnimator, ArrayList arrayList) {
        this.b = defaultItemAnimator;
        this.a = arrayList;
    }

    public void run() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            this.b.a(bVar.holder, bVar.fromX, bVar.fromY, bVar.toX, bVar.toY);
        }
        this.a.clear();
        this.b.b.remove(this.a);
    }
}
