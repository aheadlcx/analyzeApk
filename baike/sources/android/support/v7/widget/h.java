package android.support.v7.widget;

import android.support.v7.view.menu.ShowableListMenu;
import android.view.View;

class h extends ForwardingListener {
    final /* synthetic */ ActionMenuPresenter a;
    final /* synthetic */ d b;

    h(d dVar, View view, ActionMenuPresenter actionMenuPresenter) {
        this.b = dVar;
        this.a = actionMenuPresenter;
        super(view);
    }

    public ShowableListMenu getPopup() {
        if (this.b.a.h == null) {
            return null;
        }
        return this.b.a.h.getPopup();
    }

    public boolean onForwardingStarted() {
        this.b.a.showOverflowMenu();
        return true;
    }

    public boolean onForwardingStopped() {
        if (this.b.a.j != null) {
            return false;
        }
        this.b.a.hideOverflowMenu();
        return true;
    }
}
