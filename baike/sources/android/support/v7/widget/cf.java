package android.support.v7.widget;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

class cf implements OnKeyListener {
    final /* synthetic */ SearchView a;

    cf(SearchView searchView) {
        this.a = searchView;
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (this.a.h == null) {
            return false;
        }
        if (this.a.a.isPopupShowing() && this.a.a.getListSelection() != -1) {
            return this.a.a(view, i, keyEvent);
        }
        if (this.a.a.a() || !keyEvent.hasNoModifiers() || keyEvent.getAction() != 1 || i != 66) {
            return false;
        }
        view.cancelLongPress();
        this.a.a(0, null, this.a.a.getText().toString());
        return true;
    }
}
