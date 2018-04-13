package android.support.v7.widget;

import android.widget.PopupWindow.OnDismissListener;

class bk implements OnDismissListener {
    final /* synthetic */ PopupMenu a;

    bk(PopupMenu popupMenu) {
        this.a = popupMenu;
    }

    public void onDismiss() {
        if (this.a.c != null) {
            this.a.c.onDismiss(this.a);
        }
    }
}
