package android.support.design.widget;

import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.WindowInsetsCompat;
import android.view.View;

class ag implements OnApplyWindowInsetsListener {
    final /* synthetic */ CoordinatorLayout a;

    ag(CoordinatorLayout coordinatorLayout) {
        this.a = coordinatorLayout;
    }

    public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        return this.a.a(windowInsetsCompat);
    }
}
