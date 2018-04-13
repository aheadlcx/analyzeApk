package android.support.v7.widget;

import android.support.v7.view.menu.ShowableListMenu;
import android.view.View;

class bl extends ForwardingListener {
    final /* synthetic */ PopupMenu a;

    bl(PopupMenu popupMenu, View view) {
        this.a = popupMenu;
        super(view);
    }

    protected boolean onForwardingStarted() {
        this.a.show();
        return true;
    }

    protected boolean onForwardingStopped() {
        this.a.dismiss();
        return true;
    }

    public ShowableListMenu getPopup() {
        return this.a.a.getPopup();
    }
}
