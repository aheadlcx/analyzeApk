package android.support.design.internal;

import android.support.v7.view.menu.MenuItemImpl;
import android.view.View;
import android.view.View.OnClickListener;

class d implements OnClickListener {
    final /* synthetic */ NavigationMenuPresenter a;

    d(NavigationMenuPresenter navigationMenuPresenter) {
        this.a = navigationMenuPresenter;
    }

    public void onClick(View view) {
        NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) view;
        this.a.setUpdateSuspended(true);
        MenuItemImpl itemData = navigationMenuItemView.getItemData();
        boolean performItemAction = this.a.b.performItemAction(itemData, this.a, 0);
        if (itemData != null && itemData.isCheckable() && performItemAction) {
            this.a.c.setCheckedItem(itemData);
        }
        this.a.setUpdateSuspended(false);
        this.a.updateMenuView(false);
    }
}
