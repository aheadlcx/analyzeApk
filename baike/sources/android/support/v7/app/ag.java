package android.support.v7.app;

import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.MenuItem;

class ag implements OnMenuItemClickListener {
    final /* synthetic */ ae a;

    ag(ae aeVar) {
        this.a = aeVar;
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        return this.a.c.onMenuItemSelected(0, menuItem);
    }
}
