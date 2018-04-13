package android.support.v7.view.menu;

import android.view.View;
import android.view.View.OnAttachStateChangeListener;

class o implements OnAttachStateChangeListener {
    final /* synthetic */ m a;

    o(m mVar) {
        this.a = mVar;
    }

    public void onViewAttachedToWindow(View view) {
    }

    public void onViewDetachedFromWindow(View view) {
        if (this.a.o != null) {
            if (!this.a.o.isAlive()) {
                this.a.o = view.getViewTreeObserver();
            }
            this.a.o.removeGlobalOnLayoutListener(this.a.j);
        }
        view.removeOnAttachStateChangeListener(this);
    }
}
