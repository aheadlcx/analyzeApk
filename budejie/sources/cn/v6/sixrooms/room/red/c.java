package cn.v6.sixrooms.room.red;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

final class c implements AnimationListener {
    final /* synthetic */ DragRedPackagePopupWindow a;

    c(DragRedPackagePopupWindow dragRedPackagePopupWindow) {
        this.a = dragRedPackagePopupWindow;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationRepeat(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        DragRedPackagePopupWindow.j(this.a);
        if (DragRedPackagePopupWindow.k(this.a) || DragRedPackagePopupWindow.l(this.a) != 1) {
            DragRedPackagePopupWindow.n(this.a);
            DragRedPackagePopupWindow.i(this.a)[2].setAlpha(0.0f);
            DragRedPackagePopupWindow.i(this.a)[3].setAlpha(0.0f);
            if (!DragRedPackagePopupWindow.k(this.a) && DragRedPackagePopupWindow.o(this.a) == 2) {
                DragRedPackagePopupWindow.a(DragRedPackagePopupWindow.i(this.a)[1]);
                return;
            }
            return;
        }
        DragRedPackagePopupWindow.g(this.a).postDelayed(new d(this), 1000);
    }
}
