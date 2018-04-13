package android.support.v4.view;

import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;

final class f implements OnActionExpandListener {
    final /* synthetic */ MenuItemCompat.OnActionExpandListener a;

    f(MenuItemCompat.OnActionExpandListener onActionExpandListener) {
        this.a = onActionExpandListener;
    }

    public boolean onMenuItemActionExpand(MenuItem menuItem) {
        return this.a.onMenuItemActionExpand(menuItem);
    }

    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
        return this.a.onMenuItemActionCollapse(menuItem);
    }
}
