package android.support.v4.view;

import android.view.View;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.WindowInsets;

class j implements OnApplyWindowInsetsListener {
    final /* synthetic */ OnApplyWindowInsetsListener a;
    final /* synthetic */ f b;

    j(f fVar, OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        this.b = fVar;
        this.a = onApplyWindowInsetsListener;
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        return (WindowInsets) WindowInsetsCompat.a(this.a.onApplyWindowInsets(view, WindowInsetsCompat.a((Object) windowInsets)));
    }
}
