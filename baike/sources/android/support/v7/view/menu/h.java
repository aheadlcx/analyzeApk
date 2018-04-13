package android.support.v7.view.menu;

import android.support.v4.view.ActionProvider.VisibilityListener;

class h implements VisibilityListener {
    final /* synthetic */ MenuItemImpl a;

    h(MenuItemImpl menuItemImpl) {
        this.a = menuItemImpl;
    }

    public void onActionProviderVisibilityChanged(boolean z) {
        this.a.a.a(this.a);
    }
}
