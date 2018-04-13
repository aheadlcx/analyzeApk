package android.support.v7.view.menu;

import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

class n implements OnGlobalLayoutListener {
    final /* synthetic */ m a;

    n(m mVar) {
        this.a = mVar;
    }

    public void onGlobalLayout() {
        if (this.a.isShowing() && !this.a.a.isModal()) {
            View view = this.a.b;
            if (view == null || !view.isShown()) {
                this.a.dismiss();
            } else {
                this.a.a.show();
            }
        }
    }
}
