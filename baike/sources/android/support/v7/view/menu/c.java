package android.support.v7.view.menu;

import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

class c implements OnGlobalLayoutListener {
    final /* synthetic */ CascadingMenuPopup a;

    c(CascadingMenuPopup cascadingMenuPopup) {
        this.a = cascadingMenuPopup;
    }

    public void onGlobalLayout() {
        if (this.a.isShowing() && this.a.b.size() > 0 && !((a) this.a.b.get(0)).window.isModal()) {
            View view = this.a.c;
            if (view == null || !view.isShown()) {
                this.a.dismiss();
                return;
            }
            for (a aVar : this.a.b) {
                aVar.window.show();
            }
        }
    }
}
