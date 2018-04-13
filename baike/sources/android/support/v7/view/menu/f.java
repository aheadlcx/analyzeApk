package android.support.v7.view.menu;

import android.view.MenuItem;

class f implements Runnable {
    final /* synthetic */ a a;
    final /* synthetic */ MenuItem b;
    final /* synthetic */ MenuBuilder c;
    final /* synthetic */ e d;

    f(e eVar, a aVar, MenuItem menuItem, MenuBuilder menuBuilder) {
        this.d = eVar;
        this.a = aVar;
        this.b = menuItem;
        this.c = menuBuilder;
    }

    public void run() {
        if (this.a != null) {
            this.d.a.d = true;
            this.a.menu.close(false);
            this.d.a.d = false;
        }
        if (this.b.isEnabled() && this.b.hasSubMenu()) {
            this.c.performItemAction(this.b, 4);
        }
    }
}
