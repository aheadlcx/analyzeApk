package android.support.design.widget;

import android.view.View;

class l implements b {
    final /* synthetic */ BaseTransientBottomBar a;

    l(BaseTransientBottomBar baseTransientBottomBar) {
        this.a = baseTransientBottomBar;
    }

    public void onViewAttachedToWindow(View view) {
    }

    public void onViewDetachedFromWindow(View view) {
        if (this.a.isShownOrQueued()) {
            BaseTransientBottomBar.a.post(new m(this));
        }
    }
}
