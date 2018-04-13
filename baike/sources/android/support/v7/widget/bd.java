package android.support.v7.widget;

import android.view.View;

class bd implements Runnable {
    final /* synthetic */ ListPopupWindow a;

    bd(ListPopupWindow listPopupWindow) {
        this.a = listPopupWindow;
    }

    public void run() {
        View anchorView = this.a.getAnchorView();
        if (anchorView != null && anchorView.getWindowToken() != null) {
            this.a.show();
        }
    }
}
