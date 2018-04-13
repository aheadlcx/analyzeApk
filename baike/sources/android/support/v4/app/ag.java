package android.support.v4.app;

import android.support.v4.view.ViewCompat;
import android.view.View;
import java.util.ArrayList;
import java.util.Map;

class ag implements Runnable {
    final /* synthetic */ ArrayList a;
    final /* synthetic */ Map b;
    final /* synthetic */ FragmentTransitionImpl c;

    ag(FragmentTransitionImpl fragmentTransitionImpl, ArrayList arrayList, Map map) {
        this.c = fragmentTransitionImpl;
        this.a = arrayList;
        this.b = map;
    }

    public void run() {
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            View view = (View) this.a.get(i);
            ViewCompat.setTransitionName(view, (String) this.b.get(ViewCompat.getTransitionName(view)));
        }
    }
}
