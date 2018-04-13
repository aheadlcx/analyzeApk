package android.support.v7.view.menu;

import android.view.View;
import android.view.View.OnAttachStateChangeListener;

class d implements OnAttachStateChangeListener {
    final /* synthetic */ CascadingMenuPopup a;

    d(CascadingMenuPopup cascadingMenuPopup) {
        this.a = cascadingMenuPopup;
    }

    public void onViewAttachedToWindow(View view) {
    }

    public void onViewDetachedFromWindow(View view) {
        if (this.a.y != null) {
            if (!this.a.y.isAlive()) {
                this.a.y = view.getViewTreeObserver();
            }
            this.a.y.removeGlobalOnLayoutListener(this.a.k);
        }
        view.removeOnAttachStateChangeListener(this);
    }
}
