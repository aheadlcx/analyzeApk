package android.support.v7.widget;

import android.support.v7.view.menu.ShowableListMenu;
import android.view.View;

class m extends ForwardingListener {
    final /* synthetic */ ActivityChooserView a;

    m(ActivityChooserView activityChooserView, View view) {
        this.a = activityChooserView;
        super(view);
    }

    public ShowableListMenu getPopup() {
        return this.a.getListPopupWindow();
    }

    protected boolean onForwardingStarted() {
        this.a.showPopup();
        return true;
    }

    protected boolean onForwardingStopped() {
        this.a.dismissPopup();
        return true;
    }
}
