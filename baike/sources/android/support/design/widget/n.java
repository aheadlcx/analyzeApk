package android.support.design.widget;

import android.view.View;

class n implements c {
    final /* synthetic */ BaseTransientBottomBar a;

    n(BaseTransientBottomBar baseTransientBottomBar) {
        this.a = baseTransientBottomBar;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4) {
        this.a.b.setOnLayoutChangeListener(null);
        if (this.a.d()) {
            this.a.b();
        } else {
            this.a.c();
        }
    }
}
