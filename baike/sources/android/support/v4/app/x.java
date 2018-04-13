package android.support.v4.app;

import android.graphics.Rect;
import android.support.v4.util.ArrayMap;
import android.view.View;

final class x implements Runnable {
    final /* synthetic */ Fragment a;
    final /* synthetic */ Fragment b;
    final /* synthetic */ boolean c;
    final /* synthetic */ ArrayMap d;
    final /* synthetic */ View e;
    final /* synthetic */ FragmentTransitionImpl f;
    final /* synthetic */ Rect g;

    x(Fragment fragment, Fragment fragment2, boolean z, ArrayMap arrayMap, View view, FragmentTransitionImpl fragmentTransitionImpl, Rect rect) {
        this.a = fragment;
        this.b = fragment2;
        this.c = z;
        this.d = arrayMap;
        this.e = view;
        this.f = fragmentTransitionImpl;
        this.g = rect;
    }

    public void run() {
        u.b(this.a, this.b, this.c, this.d, false);
        if (this.e != null) {
            this.f.a(this.e, this.g);
        }
    }
}
