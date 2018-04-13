package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.ViewHolder;
import java.util.ArrayList;
import java.util.Iterator;

class ae implements Runnable {
    final /* synthetic */ ArrayList a;
    final /* synthetic */ DefaultItemAnimator b;

    ae(DefaultItemAnimator defaultItemAnimator, ArrayList arrayList) {
        this.b = defaultItemAnimator;
        this.a = arrayList;
    }

    public void run() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            this.b.a((ViewHolder) it.next());
        }
        this.a.clear();
        this.b.a.remove(this.a);
    }
}
