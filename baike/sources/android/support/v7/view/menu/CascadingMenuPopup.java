package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.view.menu.MenuPresenter.Callback;
import android.support.v7.widget.MenuItemHoverListener;
import android.support.v7.widget.MenuPopupWindow;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

final class CascadingMenuPopup extends j implements MenuPresenter, OnKeyListener, OnDismissListener {
    final Handler a;
    final List<a> b = new ArrayList();
    View c;
    boolean d;
    private final Context e;
    private final int f;
    private final int g;
    private final int h;
    private final boolean i;
    private final List<MenuBuilder> j = new LinkedList();
    private final OnGlobalLayoutListener k = new c(this);
    private final OnAttachStateChangeListener l = new d(this);
    private final MenuItemHoverListener m = new e(this);
    private int n = 0;
    private int o = 0;
    private View p;
    private int q;
    private boolean r;
    private boolean s;
    private int t;
    private int u;
    private boolean v;
    private boolean w;
    private Callback x;
    private ViewTreeObserver y;
    private OnDismissListener z;

    @Retention(RetentionPolicy.SOURCE)
    public @interface HorizPosition {
    }

    private static class a {
        public final MenuBuilder menu;
        public final int position;
        public final MenuPopupWindow window;

        public a(@NonNull MenuPopupWindow menuPopupWindow, @NonNull MenuBuilder menuBuilder, int i) {
            this.window = menuPopupWindow;
            this.menu = menuBuilder;
            this.position = i;
        }

        public ListView getListView() {
            return this.window.getListView();
        }
    }

    public CascadingMenuPopup(@NonNull Context context, @NonNull View view, @AttrRes int i, @StyleRes int i2, boolean z) {
        this.e = context;
        this.p = view;
        this.g = i;
        this.h = i2;
        this.i = z;
        this.v = false;
        this.q = c();
        Resources resources = context.getResources();
        this.f = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.a = new Handler();
    }

    public void setForceShowIcon(boolean z) {
        this.v = z;
    }

    private MenuPopupWindow b() {
        MenuPopupWindow menuPopupWindow = new MenuPopupWindow(this.e, null, this.g, this.h);
        menuPopupWindow.setHoverListener(this.m);
        menuPopupWindow.setOnItemClickListener(this);
        menuPopupWindow.setOnDismissListener(this);
        menuPopupWindow.setAnchorView(this.p);
        menuPopupWindow.setDropDownGravity(this.o);
        menuPopupWindow.setModal(true);
        menuPopupWindow.setInputMethodMode(2);
        return menuPopupWindow;
    }

    public void show() {
        if (!isShowing()) {
            for (MenuBuilder b : this.j) {
                b(b);
            }
            this.j.clear();
            this.c = this.p;
            if (this.c != null) {
                Object obj = this.y == null ? 1 : null;
                this.y = this.c.getViewTreeObserver();
                if (obj != null) {
                    this.y.addOnGlobalLayoutListener(this.k);
                }
                this.c.addOnAttachStateChangeListener(this.l);
            }
        }
    }

    public void dismiss() {
        int size = this.b.size();
        if (size > 0) {
            a[] aVarArr = (a[]) this.b.toArray(new a[size]);
            for (size--; size >= 0; size--) {
                a aVar = aVarArr[size];
                if (aVar.window.isShowing()) {
                    aVar.window.dismiss();
                }
            }
        }
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    private int c() {
        if (ViewCompat.getLayoutDirection(this.p) == 1) {
            return 0;
        }
        return 1;
    }

    private int a(int i) {
        ListView listView = ((a) this.b.get(this.b.size() - 1)).getListView();
        int[] iArr = new int[2];
        listView.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        this.c.getWindowVisibleDisplayFrame(rect);
        if (this.q == 1) {
            if ((listView.getWidth() + iArr[0]) + i > rect.right) {
                return 0;
            }
            return 1;
        } else if (iArr[0] - i < 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public void addMenu(MenuBuilder menuBuilder) {
        menuBuilder.addMenuPresenter(this, this.e);
        if (isShowing()) {
            b(menuBuilder);
        } else {
            this.j.add(menuBuilder);
        }
    }

    private void b(@NonNull MenuBuilder menuBuilder) {
        View a;
        LayoutInflater from = LayoutInflater.from(this.e);
        Object menuAdapter = new MenuAdapter(menuBuilder, from, this.i);
        if (!isShowing() && this.v) {
            menuAdapter.setForceShowIcon(true);
        } else if (isShowing()) {
            menuAdapter.setForceShowIcon(j.a(menuBuilder));
        }
        int a2 = j.a(menuAdapter, null, this.e, this.f);
        MenuPopupWindow b = b();
        b.setAdapter(menuAdapter);
        b.setContentWidth(a2);
        b.setDropDownGravity(this.o);
        if (this.b.size() > 0) {
            a aVar = (a) this.b.get(this.b.size() - 1);
            a = a(aVar, menuBuilder);
            a aVar2 = aVar;
        } else {
            a = null;
            Object obj = null;
        }
        if (a != null) {
            boolean z;
            int i;
            int i2;
            b.setTouchModal(false);
            b.setEnterTransition(null);
            int a3 = a(a2);
            if (a3 == 1) {
                z = true;
            } else {
                z = false;
            }
            this.q = a3;
            if (VERSION.SDK_INT >= 26) {
                b.setAnchorView(a);
                a3 = 0;
                i = 0;
            } else {
                int[] iArr = new int[2];
                this.p.getLocationOnScreen(iArr);
                int[] iArr2 = new int[2];
                a.getLocationOnScreen(iArr2);
                i = iArr2[0] - iArr[0];
                a3 = iArr2[1] - iArr[1];
            }
            if ((this.o & 5) == 5) {
                if (z) {
                    i2 = i + a2;
                } else {
                    i2 = i - a.getWidth();
                }
            } else if (z) {
                i2 = a.getWidth() + i;
            } else {
                i2 = i - a2;
            }
            b.setHorizontalOffset(i2);
            b.setOverlapAnchor(true);
            b.setVerticalOffset(a3);
        } else {
            if (this.r) {
                b.setHorizontalOffset(this.t);
            }
            if (this.s) {
                b.setVerticalOffset(this.u);
            }
            b.setEpicenterBounds(getEpicenterBounds());
        }
        this.b.add(new a(b, menuBuilder, this.q));
        b.show();
        ViewGroup listView = b.getListView();
        listView.setOnKeyListener(this);
        if (aVar2 == null && this.w && menuBuilder.getHeaderTitle() != null) {
            FrameLayout frameLayout = (FrameLayout) from.inflate(R.layout.abc_popup_menu_header_item_layout, listView, false);
            TextView textView = (TextView) frameLayout.findViewById(16908310);
            frameLayout.setEnabled(false);
            textView.setText(menuBuilder.getHeaderTitle());
            listView.addHeaderView(frameLayout, null, false);
            b.show();
        }
    }

    private MenuItem a(@NonNull MenuBuilder menuBuilder, @NonNull MenuBuilder menuBuilder2) {
        int size = menuBuilder.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = menuBuilder.getItem(i);
            if (item.hasSubMenu() && menuBuilder2 == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }

    @Nullable
    private View a(@NonNull a aVar, @NonNull MenuBuilder menuBuilder) {
        int i = 0;
        MenuItem a = a(aVar.menu, menuBuilder);
        if (a == null) {
            return null;
        }
        int headersCount;
        MenuAdapter menuAdapter;
        int i2;
        ListView listView = aVar.getListView();
        ListAdapter adapter = listView.getAdapter();
        if (adapter instanceof HeaderViewListAdapter) {
            HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
            headersCount = headerViewListAdapter.getHeadersCount();
            menuAdapter = (MenuAdapter) headerViewListAdapter.getWrappedAdapter();
        } else {
            menuAdapter = (MenuAdapter) adapter;
            headersCount = 0;
        }
        int count = menuAdapter.getCount();
        while (i < count) {
            if (a == menuAdapter.getItem(i)) {
                i2 = i;
                break;
            }
            i++;
        }
        i2 = -1;
        if (i2 == -1) {
            return null;
        }
        i2 = (i2 + headersCount) - listView.getFirstVisiblePosition();
        if (i2 < 0 || i2 >= listView.getChildCount()) {
            return null;
        }
        return listView.getChildAt(i2);
    }

    public boolean isShowing() {
        return this.b.size() > 0 && ((a) this.b.get(0)).window.isShowing();
    }

    public void onDismiss() {
        a aVar;
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            aVar = (a) this.b.get(i);
            if (!aVar.window.isShowing()) {
                break;
            }
        }
        aVar = null;
        if (aVar != null) {
            aVar.menu.close(false);
        }
    }

    public void updateMenuView(boolean z) {
        for (a listView : this.b) {
            j.a(listView.getListView().getAdapter()).notifyDataSetChanged();
        }
    }

    public void setCallback(Callback callback) {
        this.x = callback;
    }

    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        for (a aVar : this.b) {
            if (subMenuBuilder == aVar.menu) {
                aVar.getListView().requestFocus();
                return true;
            }
        }
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        addMenu(subMenuBuilder);
        if (this.x != null) {
            this.x.onOpenSubMenu(subMenuBuilder);
        }
        return true;
    }

    private int c(@NonNull MenuBuilder menuBuilder) {
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            if (menuBuilder == ((a) this.b.get(i)).menu) {
                return i;
            }
        }
        return -1;
    }

    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        int c = c(menuBuilder);
        if (c >= 0) {
            int i = c + 1;
            if (i < this.b.size()) {
                ((a) this.b.get(i)).menu.close(false);
            }
            a aVar = (a) this.b.remove(c);
            aVar.menu.removeMenuPresenter(this);
            if (this.d) {
                aVar.window.setExitTransition(null);
                aVar.window.setAnimationStyle(0);
            }
            aVar.window.dismiss();
            c = this.b.size();
            if (c > 0) {
                this.q = ((a) this.b.get(c - 1)).position;
            } else {
                this.q = c();
            }
            if (c == 0) {
                dismiss();
                if (this.x != null) {
                    this.x.onCloseMenu(menuBuilder, true);
                }
                if (this.y != null) {
                    if (this.y.isAlive()) {
                        this.y.removeGlobalOnLayoutListener(this.k);
                    }
                    this.y = null;
                }
                this.c.removeOnAttachStateChangeListener(this.l);
                this.z.onDismiss();
            } else if (z) {
                ((a) this.b.get(0)).menu.close(false);
            }
        }
    }

    public boolean flagActionItems() {
        return false;
    }

    public Parcelable onSaveInstanceState() {
        return null;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
    }

    public void setGravity(int i) {
        if (this.n != i) {
            this.n = i;
            this.o = GravityCompat.getAbsoluteGravity(i, ViewCompat.getLayoutDirection(this.p));
        }
    }

    public void setAnchorView(@NonNull View view) {
        if (this.p != view) {
            this.p = view;
            this.o = GravityCompat.getAbsoluteGravity(this.n, ViewCompat.getLayoutDirection(this.p));
        }
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.z = onDismissListener;
    }

    public ListView getListView() {
        if (this.b.isEmpty()) {
            return null;
        }
        return ((a) this.b.get(this.b.size() - 1)).getListView();
    }

    public void setHorizontalOffset(int i) {
        this.r = true;
        this.t = i;
    }

    public void setVerticalOffset(int i) {
        this.s = true;
        this.u = i;
    }

    public void setShowTitle(boolean z) {
        this.w = z;
    }

    protected boolean a() {
        return false;
    }
}
