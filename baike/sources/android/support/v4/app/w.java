package android.support.v4.app;

import android.view.View;
import java.util.ArrayList;

final class w implements Runnable {
    final /* synthetic */ Object a;
    final /* synthetic */ FragmentTransitionImpl b;
    final /* synthetic */ View c;
    final /* synthetic */ Fragment d;
    final /* synthetic */ ArrayList e;
    final /* synthetic */ ArrayList f;
    final /* synthetic */ ArrayList g;
    final /* synthetic */ Object h;

    w(Object obj, FragmentTransitionImpl fragmentTransitionImpl, View view, Fragment fragment, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
        this.a = obj;
        this.b = fragmentTransitionImpl;
        this.c = view;
        this.d = fragment;
        this.e = arrayList;
        this.f = arrayList2;
        this.g = arrayList3;
        this.h = obj2;
    }

    public void run() {
        if (this.a != null) {
            this.b.removeTarget(this.a, this.c);
            this.f.addAll(u.b(this.b, this.a, this.d, this.e, this.c));
        }
        if (this.g != null) {
            if (this.h != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(this.c);
                this.b.replaceTargets(this.h, this.g, arrayList);
            }
            this.g.clear();
            this.g.add(this.c);
        }
    }
}
