package android.support.design.widget;

import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.view.MenuItem;

class aq implements Callback {
    final /* synthetic */ NavigationView a;

    aq(NavigationView navigationView) {
        this.a = navigationView;
    }

    public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
        return this.a.c != null && this.a.c.onNavigationItemSelected(menuItem);
    }

    public void onMenuModeChange(MenuBuilder menuBuilder) {
    }
}
