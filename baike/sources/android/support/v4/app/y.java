package android.support.v4.app;

import android.graphics.Rect;
import android.support.v4.util.ArrayMap;
import android.view.View;
import java.util.ArrayList;

final class y implements Runnable {
    final /* synthetic */ FragmentTransitionImpl a;
    final /* synthetic */ ArrayMap b;
    final /* synthetic */ Object c;
    final /* synthetic */ a d;
    final /* synthetic */ ArrayList e;
    final /* synthetic */ View f;
    final /* synthetic */ Fragment g;
    final /* synthetic */ Fragment h;
    final /* synthetic */ boolean i;
    final /* synthetic */ ArrayList j;
    final /* synthetic */ Object k;
    final /* synthetic */ Rect l;

    y(FragmentTransitionImpl fragmentTransitionImpl, ArrayMap arrayMap, Object obj, a aVar, ArrayList arrayList, View view, Fragment fragment, Fragment fragment2, boolean z, ArrayList arrayList2, Object obj2, Rect rect) {
        this.a = fragmentTransitionImpl;
        this.b = arrayMap;
        this.c = obj;
        this.d = aVar;
        this.e = arrayList;
        this.f = view;
        this.g = fragment;
        this.h = fragment2;
        this.i = z;
        this.j = arrayList2;
        this.k = obj2;
        this.l = rect;
    }

    public void run() {
        ArrayMap a = u.c(this.a, this.b, this.c, this.d);
        if (a != null) {
            this.e.addAll(a.values());
            this.e.add(this.f);
        }
        u.b(this.g, this.h, this.i, a, false);
        if (this.c != null) {
            this.a.swapSharedElementTargets(this.c, this.j, this.e);
            View a2 = u.b(a, this.d, this.k, this.i);
            if (a2 != null) {
                this.a.a(a2, this.l);
            }
        }
    }
}
