package android.support.v7.widget;

import android.view.View;

class bc extends ForwardingListener {
    final /* synthetic */ ListPopupWindow a;

    bc(ListPopupWindow listPopupWindow, View view) {
        this.a = listPopupWindow;
        super(view);
    }

    public ListPopupWindow getPopup() {
        return this.a;
    }
}
