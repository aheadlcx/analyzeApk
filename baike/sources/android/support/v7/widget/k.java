package android.support.v7.widget;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;

class k implements OnGlobalLayoutListener {
    final /* synthetic */ ActivityChooserView a;

    k(ActivityChooserView activityChooserView) {
        this.a = activityChooserView;
    }

    public void onGlobalLayout() {
        if (!this.a.isShowingPopup()) {
            return;
        }
        if (this.a.isShown()) {
            this.a.getListPopupWindow().show();
            if (this.a.d != null) {
                this.a.d.subUiVisibilityChanged(true);
                return;
            }
            return;
        }
        this.a.getListPopupWindow().dismiss();
    }
}
