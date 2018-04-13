package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.StyleRes;
import android.support.design.R;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.view.menu.ListMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuPresenter.Callback;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

@RestrictTo({Scope.LIBRARY_GROUP})
public class NavigationMenuPresenter implements MenuPresenter {
    LinearLayout a;
    MenuBuilder b;
    b c;
    LayoutInflater d;
    int e;
    boolean f;
    ColorStateList g;
    ColorStateList h;
    Drawable i;
    int j;
    final OnClickListener k = new d(this);
    private NavigationMenuView l;
    private Callback m;
    private int n;
    private int o;

    private static abstract class j extends ViewHolder {
        public j(View view) {
            super(view);
        }
    }

    private static class a extends j {
        public a(View view) {
            super(view);
        }
    }

    private class b extends Adapter<j> {
        final /* synthetic */ NavigationMenuPresenter a;
        private final ArrayList<d> b = new ArrayList();
        private MenuItemImpl c;
        private boolean d;

        b(NavigationMenuPresenter navigationMenuPresenter) {
            this.a = navigationMenuPresenter;
            a();
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public int getItemCount() {
            return this.b.size();
        }

        public int getItemViewType(int i) {
            d dVar = (d) this.b.get(i);
            if (dVar instanceof e) {
                return 2;
            }
            if (dVar instanceof c) {
                return 3;
            }
            if (!(dVar instanceof f)) {
                throw new RuntimeException("Unknown item type.");
            } else if (((f) dVar).getMenuItem().hasSubMenu()) {
                return 1;
            } else {
                return 0;
            }
        }

        public j onCreateViewHolder(ViewGroup viewGroup, int i) {
            switch (i) {
                case 0:
                    return new g(this.a.d, viewGroup, this.a.k);
                case 1:
                    return new i(this.a.d, viewGroup);
                case 2:
                    return new h(this.a.d, viewGroup);
                case 3:
                    return new a(this.a.a);
                default:
                    return null;
            }
        }

        public void onBindViewHolder(j jVar, int i) {
            switch (getItemViewType(i)) {
                case 0:
                    NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) jVar.itemView;
                    navigationMenuItemView.setIconTintList(this.a.h);
                    if (this.a.f) {
                        navigationMenuItemView.setTextAppearance(this.a.e);
                    }
                    if (this.a.g != null) {
                        navigationMenuItemView.setTextColor(this.a.g);
                    }
                    ViewCompat.setBackground(navigationMenuItemView, this.a.i != null ? this.a.i.getConstantState().newDrawable() : null);
                    f fVar = (f) this.b.get(i);
                    navigationMenuItemView.setNeedsEmptyIcon(fVar.a);
                    navigationMenuItemView.initialize(fVar.getMenuItem(), 0);
                    return;
                case 1:
                    ((TextView) jVar.itemView).setText(((f) this.b.get(i)).getMenuItem().getTitle());
                    return;
                case 2:
                    e eVar = (e) this.b.get(i);
                    jVar.itemView.setPadding(0, eVar.getPaddingTop(), 0, eVar.getPaddingBottom());
                    return;
                default:
                    return;
            }
        }

        public void onViewRecycled(j jVar) {
            if (jVar instanceof g) {
                ((NavigationMenuItemView) jVar.itemView).recycle();
            }
        }

        public void update() {
            a();
            notifyDataSetChanged();
        }

        private void a() {
            if (!this.d) {
                this.d = true;
                this.b.clear();
                this.b.add(new c());
                int i = -1;
                int i2 = 0;
                boolean z = false;
                int size = this.a.b.getVisibleItems().size();
                int i3 = 0;
                while (i3 < size) {
                    int i4;
                    MenuItemImpl menuItemImpl = (MenuItemImpl) this.a.b.getVisibleItems().get(i3);
                    if (menuItemImpl.isChecked()) {
                        setCheckedItem(menuItemImpl);
                    }
                    if (menuItemImpl.isCheckable()) {
                        menuItemImpl.setExclusiveCheckable(false);
                    }
                    int i5;
                    if (menuItemImpl.hasSubMenu()) {
                        SubMenu subMenu = menuItemImpl.getSubMenu();
                        if (subMenu.hasVisibleItems()) {
                            if (i3 != 0) {
                                this.b.add(new e(this.a.j, 0));
                            }
                            this.b.add(new f(menuItemImpl));
                            Object obj = null;
                            int size2 = this.b.size();
                            int size3 = subMenu.size();
                            for (i5 = 0; i5 < size3; i5++) {
                                MenuItemImpl menuItemImpl2 = (MenuItemImpl) subMenu.getItem(i5);
                                if (menuItemImpl2.isVisible()) {
                                    if (obj == null && menuItemImpl2.getIcon() != null) {
                                        obj = 1;
                                    }
                                    if (menuItemImpl2.isCheckable()) {
                                        menuItemImpl2.setExclusiveCheckable(false);
                                    }
                                    if (menuItemImpl.isChecked()) {
                                        setCheckedItem(menuItemImpl);
                                    }
                                    this.b.add(new f(menuItemImpl2));
                                }
                            }
                            if (obj != null) {
                                a(size2, this.b.size());
                            }
                        }
                        i4 = i;
                    } else {
                        int size4;
                        boolean z2;
                        i5 = menuItemImpl.getGroupId();
                        if (i5 != i) {
                            size4 = this.b.size();
                            z2 = menuItemImpl.getIcon() != null;
                            if (i3 != 0) {
                                size4++;
                                this.b.add(new e(this.a.j, this.a.j));
                            }
                        } else if (z || menuItemImpl.getIcon() == null) {
                            z2 = z;
                            size4 = i2;
                        } else {
                            z2 = true;
                            a(i2, this.b.size());
                            size4 = i2;
                        }
                        f fVar = new f(menuItemImpl);
                        fVar.a = z2;
                        this.b.add(fVar);
                        z = z2;
                        i2 = size4;
                        i4 = i5;
                    }
                    i3++;
                    i = i4;
                }
                this.d = false;
            }
        }

        private void a(int i, int i2) {
            while (i < i2) {
                ((f) this.b.get(i)).a = true;
                i++;
            }
        }

        public void setCheckedItem(MenuItemImpl menuItemImpl) {
            if (this.c != menuItemImpl && menuItemImpl.isCheckable()) {
                if (this.c != null) {
                    this.c.setChecked(false);
                }
                this.c = menuItemImpl;
                menuItemImpl.setChecked(true);
            }
        }

        public Bundle createInstanceState() {
            Bundle bundle = new Bundle();
            if (this.c != null) {
                bundle.putInt("android:menu:checked", this.c.getItemId());
            }
            SparseArray sparseArray = new SparseArray();
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                d dVar = (d) this.b.get(i);
                if (dVar instanceof f) {
                    MenuItemImpl menuItem = ((f) dVar).getMenuItem();
                    View actionView = menuItem != null ? menuItem.getActionView() : null;
                    if (actionView != null) {
                        SparseArray parcelableSparseArray = new ParcelableSparseArray();
                        actionView.saveHierarchyState(parcelableSparseArray);
                        sparseArray.put(menuItem.getItemId(), parcelableSparseArray);
                    }
                }
            }
            bundle.putSparseParcelableArray("android:menu:action_views", sparseArray);
            return bundle;
        }

        public void restoreInstanceState(Bundle bundle) {
            d dVar;
            MenuItemImpl menuItem;
            int i = 0;
            int i2 = bundle.getInt("android:menu:checked", 0);
            if (i2 != 0) {
                this.d = true;
                int size = this.b.size();
                for (int i3 = 0; i3 < size; i3++) {
                    dVar = (d) this.b.get(i3);
                    if (dVar instanceof f) {
                        menuItem = ((f) dVar).getMenuItem();
                        if (menuItem != null && menuItem.getItemId() == i2) {
                            setCheckedItem(menuItem);
                            break;
                        }
                    }
                }
                this.d = false;
                a();
            }
            SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:action_views");
            if (sparseParcelableArray != null) {
                i2 = this.b.size();
                while (i < i2) {
                    dVar = (d) this.b.get(i);
                    if (dVar instanceof f) {
                        menuItem = ((f) dVar).getMenuItem();
                        if (menuItem != null) {
                            View actionView = menuItem.getActionView();
                            if (actionView != null) {
                                ParcelableSparseArray parcelableSparseArray = (ParcelableSparseArray) sparseParcelableArray.get(menuItem.getItemId());
                                if (parcelableSparseArray != null) {
                                    actionView.restoreHierarchyState(parcelableSparseArray);
                                }
                            }
                        }
                    }
                    i++;
                }
            }
        }

        public void setUpdateSuspended(boolean z) {
            this.d = z;
        }
    }

    private interface d {
    }

    private static class c implements d {
        c() {
        }
    }

    private static class e implements d {
        private final int a;
        private final int b;

        public e(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        public int getPaddingTop() {
            return this.a;
        }

        public int getPaddingBottom() {
            return this.b;
        }
    }

    private static class f implements d {
        boolean a;
        private final MenuItemImpl b;

        f(MenuItemImpl menuItemImpl) {
            this.b = menuItemImpl;
        }

        public MenuItemImpl getMenuItem() {
            return this.b;
        }
    }

    private static class g extends j {
        public g(LayoutInflater layoutInflater, ViewGroup viewGroup, OnClickListener onClickListener) {
            super(layoutInflater.inflate(R.layout.design_navigation_item, viewGroup, false));
            this.itemView.setOnClickListener(onClickListener);
        }
    }

    private static class h extends j {
        public h(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.design_navigation_item_separator, viewGroup, false));
        }
    }

    private static class i extends j {
        public i(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.design_navigation_item_subheader, viewGroup, false));
        }
    }

    public void initForMenu(Context context, MenuBuilder menuBuilder) {
        this.d = LayoutInflater.from(context);
        this.b = menuBuilder;
        this.j = context.getResources().getDimensionPixelOffset(R.dimen.design_navigation_separator_vertical_padding);
    }

    public MenuView getMenuView(ViewGroup viewGroup) {
        if (this.l == null) {
            this.l = (NavigationMenuView) this.d.inflate(R.layout.design_navigation_menu, viewGroup, false);
            if (this.c == null) {
                this.c = new b(this);
            }
            this.a = (LinearLayout) this.d.inflate(R.layout.design_navigation_item_header, this.l, false);
            this.l.setAdapter(this.c);
        }
        return this.l;
    }

    public void updateMenuView(boolean z) {
        if (this.c != null) {
            this.c.update();
        }
    }

    public void setCallback(Callback callback) {
        this.m = callback;
    }

    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        return false;
    }

    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        if (this.m != null) {
            this.m.onCloseMenu(menuBuilder, z);
        }
    }

    public boolean flagActionItems() {
        return false;
    }

    public boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public int getId() {
        return this.n;
    }

    public void setId(int i) {
        this.n = i;
    }

    public Parcelable onSaveInstanceState() {
        if (VERSION.SDK_INT < 11) {
            return null;
        }
        Parcelable bundle = new Bundle();
        if (this.l != null) {
            SparseArray sparseArray = new SparseArray();
            this.l.saveHierarchyState(sparseArray);
            bundle.putSparseParcelableArray(ListMenuPresenter.VIEWS_TAG, sparseArray);
        }
        if (this.c != null) {
            bundle.putBundle("android:menu:adapter", this.c.createInstanceState());
        }
        if (this.a == null) {
            return bundle;
        }
        sparseArray = new SparseArray();
        this.a.saveHierarchyState(sparseArray);
        bundle.putSparseParcelableArray("android:menu:header", sparseArray);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            SparseArray sparseParcelableArray = bundle.getSparseParcelableArray(ListMenuPresenter.VIEWS_TAG);
            if (sparseParcelableArray != null) {
                this.l.restoreHierarchyState(sparseParcelableArray);
            }
            Bundle bundle2 = bundle.getBundle("android:menu:adapter");
            if (bundle2 != null) {
                this.c.restoreInstanceState(bundle2);
            }
            sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:header");
            if (sparseParcelableArray != null) {
                this.a.restoreHierarchyState(sparseParcelableArray);
            }
        }
    }

    public void setCheckedItem(MenuItemImpl menuItemImpl) {
        this.c.setCheckedItem(menuItemImpl);
    }

    public View inflateHeaderView(@LayoutRes int i) {
        View inflate = this.d.inflate(i, this.a, false);
        addHeaderView(inflate);
        return inflate;
    }

    public void addHeaderView(@NonNull View view) {
        this.a.addView(view);
        this.l.setPadding(0, 0, 0, this.l.getPaddingBottom());
    }

    public void removeHeaderView(@NonNull View view) {
        this.a.removeView(view);
        if (this.a.getChildCount() == 0) {
            this.l.setPadding(0, this.o, 0, this.l.getPaddingBottom());
        }
    }

    public int getHeaderCount() {
        return this.a.getChildCount();
    }

    public View getHeaderView(int i) {
        return this.a.getChildAt(i);
    }

    @Nullable
    public ColorStateList getItemTintList() {
        return this.h;
    }

    public void setItemIconTintList(@Nullable ColorStateList colorStateList) {
        this.h = colorStateList;
        updateMenuView(false);
    }

    @Nullable
    public ColorStateList getItemTextColor() {
        return this.g;
    }

    public void setItemTextColor(@Nullable ColorStateList colorStateList) {
        this.g = colorStateList;
        updateMenuView(false);
    }

    public void setItemTextAppearance(@StyleRes int i) {
        this.e = i;
        this.f = true;
        updateMenuView(false);
    }

    @Nullable
    public Drawable getItemBackground() {
        return this.i;
    }

    public void setItemBackground(@Nullable Drawable drawable) {
        this.i = drawable;
        updateMenuView(false);
    }

    public void setUpdateSuspended(boolean z) {
        if (this.c != null) {
            this.c.setUpdateSuspended(z);
        }
    }

    public void dispatchApplyWindowInsets(WindowInsetsCompat windowInsetsCompat) {
        int systemWindowInsetTop = windowInsetsCompat.getSystemWindowInsetTop();
        if (this.o != systemWindowInsetTop) {
            this.o = systemWindowInsetTop;
            if (this.a.getChildCount() == 0) {
                this.l.setPadding(0, this.o, 0, this.l.getPaddingBottom());
            }
        }
        ViewCompat.dispatchApplyWindowInsets(this.a, windowInsetsCompat);
    }
}
