package android.support.design.widget;

import android.support.design.widget.SwipeDismissBehavior.OnDismissListener;
import android.view.View;

class k implements OnDismissListener {
    final /* synthetic */ BaseTransientBottomBar a;

    k(BaseTransientBottomBar baseTransientBottomBar) {
        this.a = baseTransientBottomBar;
    }

    public void onDismiss(View view) {
        view.setVisibility(8);
        this.a.a(0);
    }

    public void onDragStateChanged(int i) {
        switch (i) {
            case 0:
                av.a().restoreTimeoutIfPaused(this.a.c);
                return;
            case 1:
            case 2:
                av.a().pauseTimeout(this.a.c);
                return;
            default:
                return;
        }
    }
}
