package android.support.v7.widget;

import android.support.v7.view.menu.ShowableListMenu;
import android.view.View;

class t extends ForwardingListener {
    final /* synthetic */ b a;
    final /* synthetic */ AppCompatSpinner b;

    t(AppCompatSpinner appCompatSpinner, View view, b bVar) {
        this.b = appCompatSpinner;
        this.a = bVar;
        super(view);
    }

    public ShowableListMenu getPopup() {
        return this.a;
    }

    public boolean onForwardingStarted() {
        if (!this.b.g.isShowing()) {
            this.b.g.show();
        }
        return true;
    }
}
