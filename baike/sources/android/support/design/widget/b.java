package android.support.design.widget;

import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.WindowInsetsCompat;
import android.view.View;

class b implements OnApplyWindowInsetsListener {
    final /* synthetic */ AppBarLayout a;

    b(AppBarLayout appBarLayout) {
        this.a = appBarLayout;
    }

    public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        return this.a.a(windowInsetsCompat);
    }
}
