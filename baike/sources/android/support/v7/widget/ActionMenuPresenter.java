package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.ActionProvider.SubUiVisibilityListener;
import android.support.v4.view.GravityCompat;
import android.support.v7.appcompat.R;
import android.support.v7.view.ActionBarPolicy;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.ActionMenuItemView.PopupCallback;
import android.support.v7.view.menu.BaseMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.MenuPresenter.Callback;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.MenuView.ItemView;
import android.support.v7.view.menu.ShowableListMenu;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.widget.ActionMenuView.ActionMenuChildView;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import java.util.ArrayList;

class ActionMenuPresenter extends BaseMenuPresenter implements SubUiVisibilityListener {
    private b A;
    d g;
    e h;
    a i;
    c j;
    final f k = new f(this);
    int l;
    private Drawable m;
    private boolean n;
    private boolean o;
    private boolean p;
    private int q;
    private int r;
    private int s;
    private boolean t;
    private boolean u;
    private boolean v;
    private boolean w;
    private int x;
    private final SparseBooleanArray y = new SparseBooleanArray();
    private View z;

    private static class SavedState implements Parcelable {
        public static final Creator<SavedState> CREATOR = new i();
        public int openSubMenuId;

        SavedState() {
        }

        SavedState(Parcel parcel) {
            this.openSubMenuId = parcel.readInt();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.openSubMenuId);
        }
    }

    private class a extends MenuPopupHelper {
        final /* synthetic */ ActionMenuPresenter a;

        public a(ActionMenuPresenter actionMenuPresenter, Context context, SubMenuBuilder subMenuBuilder, View view) {
            this.a = actionMenuPresenter;
            super(context, subMenuBuilder, view, false, R.attr.actionOverflowMenuStyle);
            if (!((MenuItemImpl) subMenuBuilder.getItem()).isActionButton()) {
                setAnchorView(actionMenuPresenter.g == null ? (View) actionMenuPresenter.f : actionMenuPresenter.g);
            }
            setPresenterCallback(actionMenuPresenter.k);
        }

        protected void a() {
            this.a.i = null;
            this.a.l = 0;
            super.a();
        }
    }

    private class b extends PopupCallback {
        final /* synthetic */ ActionMenuPresenter a;

        b(ActionMenuPresenter actionMenuPresenter) {
            this.a = actionMenuPresenter;
        }

        public ShowableListMenu getPopup() {
            return this.a.i != null ? this.a.i.getPopup() : null;
        }
    }

    private class c implements Runnable {
        final /* synthetic */ ActionMenuPresenter a;
        private e b;

        public c(ActionMenuPresenter actionMenuPresenter, e eVar) {
            this.a = actionMenuPresenter;
            this.b = eVar;
        }

        public void run() {
            if (this.a.c != null) {
                this.a.c.changeMenuMode();
            }
            View view = (View) this.a.f;
            if (!(view == null || view.getWindowToken() == null || !this.b.tryShow())) {
                this.a.h = this.b;
            }
            this.a.j = null;
        }
    }

    private class d extends AppCompatImageView implements ActionMenuChildView {
        final /* synthetic */ ActionMenuPresenter a;
        private final float[] b = new float[2];

        public d(ActionMenuPresenter actionMenuPresenter, Context context) {
            this.a = actionMenuPresenter;
            super(context, null, R.attr.actionOverflowButtonStyle);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            TooltipCompat.setTooltipText(this, getContentDescription());
            setOnTouchListener(new h(this, this, actionMenuPresenter));
        }

        public boolean performClick() {
            if (!super.performClick()) {
                playSoundEffect(0);
                this.a.showOverflowMenu();
            }
            return true;
        }

        public boolean needsDividerBefore() {
            return false;
        }

        public boolean needsDividerAfter() {
            return false;
        }

        protected boolean setFrame(int i, int i2, int i3, int i4) {
            boolean frame = super.setFrame(i, i2, i3, i4);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (!(drawable == null || background == null)) {
                int width = getWidth();
                int height = getHeight();
                int max = Math.max(width, height) / 2;
                width = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                height = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                DrawableCompat.setHotspotBounds(background, width - max, height - max, width + max, height + max);
            }
            return frame;
        }
    }

    private class e extends MenuPopupHelper {
        final /* synthetic */ ActionMenuPresenter a;

        public e(ActionMenuPresenter actionMenuPresenter, Context context, MenuBuilder menuBuilder, View view, boolean z) {
            this.a = actionMenuPresenter;
            super(context, menuBuilder, view, z, R.attr.actionOverflowMenuStyle);
            setGravity(GravityCompat.END);
            setPresenterCallback(actionMenuPresenter.k);
        }

        protected void a() {
            if (this.a.c != null) {
                this.a.c.close();
            }
            this.a.h = null;
            super.a();
        }
    }

    private class f implements Callback {
        final /* synthetic */ ActionMenuPresenter a;

        f(ActionMenuPresenter actionMenuPresenter) {
            this.a = actionMenuPresenter;
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            if (menuBuilder == null) {
                return false;
            }
            this.a.l = ((SubMenuBuilder) menuBuilder).getItem().getItemId();
            Callback callback = this.a.getCallback();
            return callback != null ? callback.onOpenSubMenu(menuBuilder) : false;
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            if (menuBuilder instanceof SubMenuBuilder) {
                menuBuilder.getRootMenu().close(false);
            }
            Callback callback = this.a.getCallback();
            if (callback != null) {
                callback.onCloseMenu(menuBuilder, z);
            }
        }
    }

    public ActionMenuPresenter(Context context) {
        super(context, R.layout.abc_action_menu_layout, R.layout.abc_action_menu_item_layout);
    }

    public void initForMenu(@NonNull Context context, @Nullable MenuBuilder menuBuilder) {
        super.initForMenu(context, menuBuilder);
        Resources resources = context.getResources();
        ActionBarPolicy actionBarPolicy = ActionBarPolicy.get(context);
        if (!this.p) {
            this.o = actionBarPolicy.showsOverflowMenuButton();
        }
        if (!this.v) {
            this.q = actionBarPolicy.getEmbeddedMenuWidthLimit();
        }
        if (!this.t) {
            this.s = actionBarPolicy.getMaxActionButtons();
        }
        int i = this.q;
        if (this.o) {
            if (this.g == null) {
                this.g = new d(this, this.a);
                if (this.n) {
                    this.g.setImageDrawable(this.m);
                    this.m = null;
                    this.n = false;
                }
                int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
                this.g.measure(makeMeasureSpec, makeMeasureSpec);
            }
            i -= this.g.getMeasuredWidth();
        } else {
            this.g = null;
        }
        this.r = i;
        this.x = (int) (56.0f * resources.getDisplayMetrics().density);
        this.z = null;
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (!this.t) {
            this.s = ActionBarPolicy.get(this.b).getMaxActionButtons();
        }
        if (this.c != null) {
            this.c.onItemsChanged(true);
        }
    }

    public void setWidthLimit(int i, boolean z) {
        this.q = i;
        this.u = z;
        this.v = true;
    }

    public void setReserveOverflow(boolean z) {
        this.o = z;
        this.p = true;
    }

    public void setItemLimit(int i) {
        this.s = i;
        this.t = true;
    }

    public void setExpandedActionViewsExclusive(boolean z) {
        this.w = z;
    }

    public void setOverflowIcon(Drawable drawable) {
        if (this.g != null) {
            this.g.setImageDrawable(drawable);
            return;
        }
        this.n = true;
        this.m = drawable;
    }

    public Drawable getOverflowIcon() {
        if (this.g != null) {
            return this.g.getDrawable();
        }
        if (this.n) {
            return this.m;
        }
        return null;
    }

    public MenuView getMenuView(ViewGroup viewGroup) {
        MenuView menuView = this.f;
        MenuView menuView2 = super.getMenuView(viewGroup);
        if (menuView != menuView2) {
            ((ActionMenuView) menuView2).setPresenter(this);
        }
        return menuView2;
    }

    public View getItemView(MenuItemImpl menuItemImpl, View view, ViewGroup viewGroup) {
        View actionView = menuItemImpl.getActionView();
        if (actionView == null || menuItemImpl.hasCollapsibleActionView()) {
            actionView = super.getItemView(menuItemImpl, view, viewGroup);
        }
        actionView.setVisibility(menuItemImpl.isActionViewExpanded() ? 8 : 0);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.a(layoutParams));
        }
        return actionView;
    }

    public void bindItemView(MenuItemImpl menuItemImpl, ItemView itemView) {
        itemView.initialize(menuItemImpl, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) itemView;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.f);
        if (this.A == null) {
            this.A = new b(this);
        }
        actionMenuItemView.setPopupCallback(this.A);
    }

    public boolean shouldIncludeItem(int i, MenuItemImpl menuItemImpl) {
        return menuItemImpl.isActionButton();
    }

    public void updateMenuView(boolean z) {
        int i;
        int i2 = 1;
        int i3 = 0;
        super.updateMenuView(z);
        ((View) this.f).requestLayout();
        if (this.c != null) {
            ArrayList actionItems = this.c.getActionItems();
            int size = actionItems.size();
            for (i = 0; i < size; i++) {
                ActionProvider supportActionProvider = ((MenuItemImpl) actionItems.get(i)).getSupportActionProvider();
                if (supportActionProvider != null) {
                    supportActionProvider.setSubUiVisibilityListener(this);
                }
            }
        }
        ArrayList nonActionItems = this.c != null ? this.c.getNonActionItems() : null;
        if (this.o && nonActionItems != null) {
            i = nonActionItems.size();
            if (i == 1) {
                int i4;
                if (((MenuItemImpl) nonActionItems.get(0)).isActionViewExpanded()) {
                    i4 = 0;
                } else {
                    i4 = 1;
                }
                i3 = i4;
            } else {
                if (i <= 0) {
                    i2 = 0;
                }
                i3 = i2;
            }
        }
        if (i3 != 0) {
            if (this.g == null) {
                this.g = new d(this, this.a);
            }
            ViewGroup viewGroup = (ViewGroup) this.g.getParent();
            if (viewGroup != this.f) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.g);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.f;
                actionMenuView.addView(this.g, actionMenuView.generateOverflowButtonLayoutParams());
            }
        } else if (this.g != null && this.g.getParent() == this.f) {
            ((ViewGroup) this.f).removeView(this.g);
        }
        ((ActionMenuView) this.f).setOverflowReserved(this.o);
    }

    public boolean filterLeftoverView(ViewGroup viewGroup, int i) {
        if (viewGroup.getChildAt(i) == this.g) {
            return false;
        }
        return super.filterLeftoverView(viewGroup, i);
    }

    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        SubMenuBuilder subMenuBuilder2 = subMenuBuilder;
        while (subMenuBuilder2.getParentMenu() != this.c) {
            subMenuBuilder2 = (SubMenuBuilder) subMenuBuilder2.getParentMenu();
        }
        View a = a(subMenuBuilder2.getItem());
        if (a == null) {
            return false;
        }
        boolean z;
        this.l = subMenuBuilder.getItem().getItemId();
        int size = subMenuBuilder.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = subMenuBuilder.getItem(i);
            if (item.isVisible() && item.getIcon() != null) {
                z = true;
                break;
            }
        }
        z = false;
        this.i = new a(this, this.b, subMenuBuilder, a);
        this.i.setForceShowIcon(z);
        this.i.show();
        super.onSubMenuSelected(subMenuBuilder);
        return true;
    }

    private View a(MenuItem menuItem) {
        ViewGroup viewGroup = (ViewGroup) this.f;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if ((childAt instanceof ItemView) && ((ItemView) childAt).getItemData() == menuItem) {
                return childAt;
            }
        }
        return null;
    }

    public boolean showOverflowMenu() {
        if (!this.o || isOverflowMenuShowing() || this.c == null || this.f == null || this.j != null || this.c.getNonActionItems().isEmpty()) {
            return false;
        }
        this.j = new c(this, new e(this, this.b, this.c, this.g, true));
        ((View) this.f).post(this.j);
        super.onSubMenuSelected(null);
        return true;
    }

    public boolean hideOverflowMenu() {
        if (this.j == null || this.f == null) {
            MenuPopupHelper menuPopupHelper = this.h;
            if (menuPopupHelper == null) {
                return false;
            }
            menuPopupHelper.dismiss();
            return true;
        }
        ((View) this.f).removeCallbacks(this.j);
        this.j = null;
        return true;
    }

    public boolean dismissPopupMenus() {
        return hideOverflowMenu() | hideSubMenus();
    }

    public boolean hideSubMenus() {
        if (this.i == null) {
            return false;
        }
        this.i.dismiss();
        return true;
    }

    public boolean isOverflowMenuShowing() {
        return this.h != null && this.h.isShowing();
    }

    public boolean isOverflowMenuShowPending() {
        return this.j != null || isOverflowMenuShowing();
    }

    public boolean isOverflowReserved() {
        return this.o;
    }

    public boolean flagActionItems() {
        int size;
        ArrayList arrayList;
        int i;
        if (this.c != null) {
            ArrayList visibleItems = this.c.getVisibleItems();
            size = visibleItems.size();
            arrayList = visibleItems;
        } else {
            size = 0;
            arrayList = null;
        }
        int i2 = this.s;
        int i3 = this.r;
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) this.f;
        int i4 = 0;
        int i5 = 0;
        Object obj = null;
        int i6 = 0;
        while (i6 < size) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) arrayList.get(i6);
            if (menuItemImpl.requiresActionButton()) {
                i4++;
            } else if (menuItemImpl.requestsActionButton()) {
                i5++;
            } else {
                obj = 1;
            }
            if (this.w && menuItemImpl.isActionViewExpanded()) {
                i = 0;
            } else {
                i = i2;
            }
            i6++;
            i2 = i;
        }
        if (this.o && (r4 != null || i4 + i5 > i2)) {
            i2--;
        }
        i6 = i2 - i4;
        SparseBooleanArray sparseBooleanArray = this.y;
        sparseBooleanArray.clear();
        i = 0;
        if (this.u) {
            i = i3 / this.x;
            i5 = ((i3 % this.x) / i) + this.x;
        } else {
            i5 = 0;
        }
        int i7 = 0;
        i2 = 0;
        int i8 = i;
        while (i7 < size) {
            menuItemImpl = (MenuItemImpl) arrayList.get(i7);
            int i9;
            if (menuItemImpl.requiresActionButton()) {
                View itemView = getItemView(menuItemImpl, this.z, viewGroup);
                if (this.z == null) {
                    this.z = itemView;
                }
                if (this.u) {
                    i8 -= ActionMenuView.a(itemView, i5, i8, makeMeasureSpec, 0);
                } else {
                    itemView.measure(makeMeasureSpec, makeMeasureSpec);
                }
                i4 = itemView.getMeasuredWidth();
                i9 = i3 - i4;
                if (i2 != 0) {
                    i4 = i2;
                }
                i2 = menuItemImpl.getGroupId();
                if (i2 != 0) {
                    sparseBooleanArray.put(i2, true);
                }
                menuItemImpl.setIsActionButton(true);
                i = i9;
                i2 = i6;
            } else if (menuItemImpl.requestsActionButton()) {
                boolean z;
                int groupId = menuItemImpl.getGroupId();
                boolean z2 = sparseBooleanArray.get(groupId);
                boolean z3 = (i6 > 0 || z2) && i3 > 0 && (!this.u || i8 > 0);
                if (z3) {
                    View itemView2 = getItemView(menuItemImpl, this.z, viewGroup);
                    if (this.z == null) {
                        this.z = itemView2;
                    }
                    boolean z4;
                    if (this.u) {
                        int a = ActionMenuView.a(itemView2, i5, i8, makeMeasureSpec, 0);
                        i9 = i8 - a;
                        if (a == 0) {
                            i8 = 0;
                        } else {
                            z4 = z3;
                        }
                        i4 = i9;
                    } else {
                        itemView2.measure(makeMeasureSpec, makeMeasureSpec);
                        boolean z5 = z3;
                        i4 = i8;
                        z4 = z5;
                    }
                    i9 = itemView2.getMeasuredWidth();
                    i3 -= i9;
                    if (i2 == 0) {
                        i2 = i9;
                    }
                    if (this.u) {
                        z = i8 & (i3 >= 0 ? 1 : 0);
                        i9 = i2;
                        i2 = i4;
                    } else {
                        z = i8 & (i3 + i2 > 0 ? 1 : 0);
                        i9 = i2;
                        i2 = i4;
                    }
                } else {
                    z = z3;
                    i9 = i2;
                    i2 = i8;
                }
                if (z && groupId != 0) {
                    sparseBooleanArray.put(groupId, true);
                    i8 = i6;
                } else if (z2) {
                    sparseBooleanArray.put(groupId, false);
                    i4 = i6;
                    for (i6 = 0; i6 < i7; i6++) {
                        MenuItemImpl menuItemImpl2 = (MenuItemImpl) arrayList.get(i6);
                        if (menuItemImpl2.getGroupId() == groupId) {
                            if (menuItemImpl2.isActionButton()) {
                                i4++;
                            }
                            menuItemImpl2.setIsActionButton(false);
                        }
                    }
                    i8 = i4;
                } else {
                    i8 = i6;
                }
                if (z) {
                    i8--;
                }
                menuItemImpl.setIsActionButton(z);
                i4 = i9;
                i = i3;
                int i10 = i2;
                i2 = i8;
                i8 = i10;
            } else {
                menuItemImpl.setIsActionButton(false);
                i4 = i2;
                i = i3;
                i2 = i6;
            }
            i7++;
            i3 = i;
            i6 = i2;
            i2 = i4;
        }
        return true;
    }

    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        dismissPopupMenus();
        super.onCloseMenu(menuBuilder, z);
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState();
        savedState.openSubMenuId = this.l;
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            if (savedState.openSubMenuId > 0) {
                MenuItem findItem = this.c.findItem(savedState.openSubMenuId);
                if (findItem != null) {
                    onSubMenuSelected((SubMenuBuilder) findItem.getSubMenu());
                }
            }
        }
    }

    public void onSubUiVisibilityChanged(boolean z) {
        if (z) {
            super.onSubMenuSelected(null);
        } else if (this.c != null) {
            this.c.close(false);
        }
    }

    public void setMenuView(ActionMenuView actionMenuView) {
        this.f = actionMenuView;
        actionMenuView.initialize(this.c);
    }
}
