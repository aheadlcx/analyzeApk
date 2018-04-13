package android.support.v7.widget;

import android.support.v7.widget.ActionMenuView.OnMenuItemClickListener;
import android.view.MenuItem;

class cv implements OnMenuItemClickListener {
    final /* synthetic */ Toolbar a;

    cv(Toolbar toolbar) {
        this.a = toolbar;
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        if (this.a.d != null) {
            return this.a.d.onMenuItemClick(menuItem);
        }
        return false;
    }
}
