package android.support.v7.view.menu;

import android.widget.PopupWindow.OnDismissListener;

class k implements OnDismissListener {
    final /* synthetic */ MenuPopupHelper a;

    k(MenuPopupHelper menuPopupHelper) {
        this.a = menuPopupHelper;
    }

    public void onDismiss() {
        this.a.a();
    }
}
