package android.support.v7.view.menu;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.appcompat.R;
import android.support.v7.view.menu.MenuView.ItemView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

@RestrictTo({Scope.LIBRARY_GROUP})
public class MenuAdapter extends BaseAdapter {
    static final int a = R.layout.abc_popup_menu_item_layout;
    MenuBuilder b;
    private int c = -1;
    private boolean d;
    private final boolean e;
    private final LayoutInflater f;

    public MenuAdapter(MenuBuilder menuBuilder, LayoutInflater layoutInflater, boolean z) {
        this.e = z;
        this.f = layoutInflater;
        this.b = menuBuilder;
        a();
    }

    public boolean getForceShowIcon() {
        return this.d;
    }

    public void setForceShowIcon(boolean z) {
        this.d = z;
    }

    public int getCount() {
        ArrayList nonActionItems = this.e ? this.b.getNonActionItems() : this.b.getVisibleItems();
        if (this.c < 0) {
            return nonActionItems.size();
        }
        return nonActionItems.size() - 1;
    }

    public MenuBuilder getAdapterMenu() {
        return this.b;
    }

    public MenuItemImpl getItem(int i) {
        ArrayList nonActionItems = this.e ? this.b.getNonActionItems() : this.b.getVisibleItems();
        if (this.c >= 0 && i >= this.c) {
            i++;
        }
        return (MenuItemImpl) nonActionItems.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate;
        if (view == null) {
            inflate = this.f.inflate(a, viewGroup, false);
        } else {
            inflate = view;
        }
        ItemView itemView = (ItemView) inflate;
        if (this.d) {
            ((ListMenuItemView) inflate).setForceShowIcon(true);
        }
        itemView.initialize(getItem(i), 0);
        return inflate;
    }

    void a() {
        MenuItemImpl expandedItem = this.b.getExpandedItem();
        if (expandedItem != null) {
            ArrayList nonActionItems = this.b.getNonActionItems();
            int size = nonActionItems.size();
            for (int i = 0; i < size; i++) {
                if (((MenuItemImpl) nonActionItems.get(i)) == expandedItem) {
                    this.c = i;
                    return;
                }
            }
        }
        this.c = -1;
    }

    public void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
