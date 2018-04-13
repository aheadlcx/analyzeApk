package android.support.design.widget;

import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.view.MenuItem;

class r implements Callback {
    final /* synthetic */ BottomNavigationView a;

    r(BottomNavigationView bottomNavigationView) {
        this.a = bottomNavigationView;
    }

    public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
        if (this.a.h != null && menuItem.getItemId() == this.a.getSelectedItemId()) {
            this.a.h.onNavigationItemReselected(menuItem);
            return true;
        } else if (this.a.g == null || this.a.g.onNavigationItemSelected(menuItem)) {
            return false;
        } else {
            return true;
        }
    }

    public void onMenuModeChange(MenuBuilder menuBuilder) {
    }
}
