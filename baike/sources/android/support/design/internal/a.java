package android.support.design.internal;

import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

class a implements OnClickListener {
    final /* synthetic */ BottomNavigationMenuView a;

    a(BottomNavigationMenuView bottomNavigationMenuView) {
        this.a = bottomNavigationMenuView;
    }

    public void onClick(View view) {
        MenuItem itemData = ((BottomNavigationItemView) view).getItemData();
        if (!this.a.q.performItemAction(itemData, this.a.p, 0)) {
            itemData.setChecked(true);
        }
    }
}
