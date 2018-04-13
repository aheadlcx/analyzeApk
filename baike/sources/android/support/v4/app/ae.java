package android.support.v4.app;

import android.support.v4.view.ViewCompat;
import android.view.View;
import java.util.ArrayList;

class ae implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ ArrayList b;
    final /* synthetic */ ArrayList c;
    final /* synthetic */ ArrayList d;
    final /* synthetic */ ArrayList e;
    final /* synthetic */ FragmentTransitionImpl f;

    ae(FragmentTransitionImpl fragmentTransitionImpl, int i, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4) {
        this.f = fragmentTransitionImpl;
        this.a = i;
        this.b = arrayList;
        this.c = arrayList2;
        this.d = arrayList3;
        this.e = arrayList4;
    }

    public void run() {
        for (int i = 0; i < this.a; i++) {
            ViewCompat.setTransitionName((View) this.b.get(i), (String) this.c.get(i));
            ViewCompat.setTransitionName((View) this.d.get(i), (String) this.e.get(i));
        }
    }
}
