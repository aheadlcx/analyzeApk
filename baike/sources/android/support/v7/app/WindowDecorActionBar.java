package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.ActionBar.OnMenuVisibilityListener;
import android.support.v7.app.ActionBar.OnNavigationListener;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.appcompat.R;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.ActionBarPolicy;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.ViewPropertyAnimatorCompatSet;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback;
import android.support.v7.widget.DecorToolbar;
import android.support.v7.widget.ScrollingTabContainerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.SpinnerAdapter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

@RestrictTo({Scope.LIBRARY_GROUP})
public class WindowDecorActionBar extends ActionBar implements ActionBarVisibilityCallback {
    static final /* synthetic */ boolean s = (!WindowDecorActionBar.class.desiredAssertionStatus());
    private static final Interpolator t = new AccelerateInterpolator();
    private static final Interpolator u = new DecelerateInterpolator();
    private int A = -1;
    private boolean B;
    private boolean C;
    private ArrayList<OnMenuVisibilityListener> D = new ArrayList();
    private boolean E;
    private int F = 0;
    private boolean G;
    private boolean H = true;
    private boolean I;
    Context a;
    ActionBarOverlayLayout b;
    ActionBarContainer c;
    DecorToolbar d;
    ActionBarContextView e;
    View f;
    ScrollingTabContainerView g;
    ActionModeImpl h;
    ActionMode i;
    Callback j;
    boolean k = true;
    boolean l;
    boolean m;
    ViewPropertyAnimatorCompatSet n;
    boolean o;
    final ViewPropertyAnimatorListener p = new aj(this);
    final ViewPropertyAnimatorListener q = new ak(this);
    final ViewPropertyAnimatorUpdateListener r = new al(this);
    private Context v;
    private Activity w;
    private Dialog x;
    private ArrayList<TabImpl> y = new ArrayList();
    private TabImpl z;

    @RestrictTo({Scope.LIBRARY_GROUP})
    public class ActionModeImpl extends ActionMode implements MenuBuilder.Callback {
        final /* synthetic */ WindowDecorActionBar a;
        private final Context b;
        private final MenuBuilder c;
        private Callback d;
        private WeakReference<View> e;

        public ActionModeImpl(WindowDecorActionBar windowDecorActionBar, Context context, Callback callback) {
            this.a = windowDecorActionBar;
            this.b = context;
            this.d = callback;
            this.c = new MenuBuilder(context).setDefaultShowAsAction(1);
            this.c.setCallback(this);
        }

        public MenuInflater getMenuInflater() {
            return new SupportMenuInflater(this.b);
        }

        public Menu getMenu() {
            return this.c;
        }

        public void finish() {
            if (this.a.h == this) {
                if (WindowDecorActionBar.a(this.a.l, this.a.m, false)) {
                    this.d.onDestroyActionMode(this);
                } else {
                    this.a.i = this;
                    this.a.j = this.d;
                }
                this.d = null;
                this.a.animateToMode(false);
                this.a.e.closeMode();
                this.a.d.getViewGroup().sendAccessibilityEvent(32);
                this.a.b.setHideOnContentScrollEnabled(this.a.o);
                this.a.h = null;
            }
        }

        public void invalidate() {
            if (this.a.h == this) {
                this.c.stopDispatchingItemsChanged();
                try {
                    this.d.onPrepareActionMode(this, this.c);
                } finally {
                    this.c.startDispatchingItemsChanged();
                }
            }
        }

        public boolean dispatchOnCreate() {
            this.c.stopDispatchingItemsChanged();
            try {
                boolean onCreateActionMode = this.d.onCreateActionMode(this, this.c);
                return onCreateActionMode;
            } finally {
                this.c.startDispatchingItemsChanged();
            }
        }

        public void setCustomView(View view) {
            this.a.e.setCustomView(view);
            this.e = new WeakReference(view);
        }

        public void setSubtitle(CharSequence charSequence) {
            this.a.e.setSubtitle(charSequence);
        }

        public void setTitle(CharSequence charSequence) {
            this.a.e.setTitle(charSequence);
        }

        public void setTitle(int i) {
            setTitle(this.a.a.getResources().getString(i));
        }

        public void setSubtitle(int i) {
            setSubtitle(this.a.a.getResources().getString(i));
        }

        public CharSequence getTitle() {
            return this.a.e.getTitle();
        }

        public CharSequence getSubtitle() {
            return this.a.e.getSubtitle();
        }

        public void setTitleOptionalHint(boolean z) {
            super.setTitleOptionalHint(z);
            this.a.e.setTitleOptional(z);
        }

        public boolean isTitleOptional() {
            return this.a.e.isTitleOptional();
        }

        public View getCustomView() {
            return this.e != null ? (View) this.e.get() : null;
        }

        public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            if (this.d != null) {
                return this.d.onActionItemClicked(this, menuItem);
            }
            return false;
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        }

        public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
            if (this.d == null) {
                return false;
            }
            if (!subMenuBuilder.hasVisibleItems()) {
                return true;
            }
            new MenuPopupHelper(this.a.getThemedContext(), subMenuBuilder).show();
            return true;
        }

        public void onCloseSubMenu(SubMenuBuilder subMenuBuilder) {
        }

        public void onMenuModeChange(MenuBuilder menuBuilder) {
            if (this.d != null) {
                invalidate();
                this.a.e.showOverflowMenu();
            }
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public class TabImpl extends Tab {
        final /* synthetic */ WindowDecorActionBar a;
        private TabListener b;
        private Object c;
        private Drawable d;
        private CharSequence e;
        private CharSequence f;
        private int g = -1;
        private View h;

        public TabImpl(WindowDecorActionBar windowDecorActionBar) {
            this.a = windowDecorActionBar;
        }

        public Object getTag() {
            return this.c;
        }

        public Tab setTag(Object obj) {
            this.c = obj;
            return this;
        }

        public TabListener getCallback() {
            return this.b;
        }

        public Tab setTabListener(TabListener tabListener) {
            this.b = tabListener;
            return this;
        }

        public View getCustomView() {
            return this.h;
        }

        public Tab setCustomView(View view) {
            this.h = view;
            if (this.g >= 0) {
                this.a.g.updateTab(this.g);
            }
            return this;
        }

        public Tab setCustomView(int i) {
            return setCustomView(LayoutInflater.from(this.a.getThemedContext()).inflate(i, null));
        }

        public Drawable getIcon() {
            return this.d;
        }

        public int getPosition() {
            return this.g;
        }

        public void setPosition(int i) {
            this.g = i;
        }

        public CharSequence getText() {
            return this.e;
        }

        public Tab setIcon(Drawable drawable) {
            this.d = drawable;
            if (this.g >= 0) {
                this.a.g.updateTab(this.g);
            }
            return this;
        }

        public Tab setIcon(int i) {
            return setIcon(AppCompatResources.getDrawable(this.a.a, i));
        }

        public Tab setText(CharSequence charSequence) {
            this.e = charSequence;
            if (this.g >= 0) {
                this.a.g.updateTab(this.g);
            }
            return this;
        }

        public Tab setText(int i) {
            return setText(this.a.a.getResources().getText(i));
        }

        public void select() {
            this.a.selectTab(this);
        }

        public Tab setContentDescription(int i) {
            return setContentDescription(this.a.a.getResources().getText(i));
        }

        public Tab setContentDescription(CharSequence charSequence) {
            this.f = charSequence;
            if (this.g >= 0) {
                this.a.g.updateTab(this.g);
            }
            return this;
        }

        public CharSequence getContentDescription() {
            return this.f;
        }
    }

    public WindowDecorActionBar(Activity activity, boolean z) {
        this.w = activity;
        View decorView = activity.getWindow().getDecorView();
        a(decorView);
        if (!z) {
            this.f = decorView.findViewById(16908290);
        }
    }

    public WindowDecorActionBar(Dialog dialog) {
        this.x = dialog;
        a(dialog.getWindow().getDecorView());
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public WindowDecorActionBar(View view) {
        if (s || view.isInEditMode()) {
            a(view);
            return;
        }
        throw new AssertionError();
    }

    private void a(View view) {
        this.b = (ActionBarOverlayLayout) view.findViewById(R.id.decor_content_parent);
        if (this.b != null) {
            this.b.setActionBarVisibilityCallback(this);
        }
        this.d = b(view.findViewById(R.id.action_bar));
        this.e = (ActionBarContextView) view.findViewById(R.id.action_context_bar);
        this.c = (ActionBarContainer) view.findViewById(R.id.action_bar_container);
        if (this.d == null || this.e == null || this.c == null) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with a compatible window decor layout");
        }
        this.a = this.d.getContext();
        boolean z = (this.d.getDisplayOptions() & 4) != 0;
        if (z) {
            this.B = true;
        }
        ActionBarPolicy actionBarPolicy = ActionBarPolicy.get(this.a);
        if (actionBarPolicy.enableHomeButtonByDefault() || z) {
            z = true;
        } else {
            z = false;
        }
        setHomeButtonEnabled(z);
        a(actionBarPolicy.hasEmbeddedTabs());
        TypedArray obtainStyledAttributes = this.a.obtainStyledAttributes(null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
        if (obtainStyledAttributes.getBoolean(R.styleable.ActionBar_hideOnContentScroll, false)) {
            setHideOnContentScrollEnabled(true);
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            setElevation((float) dimensionPixelSize);
        }
        obtainStyledAttributes.recycle();
    }

    private DecorToolbar b(View view) {
        if (view instanceof DecorToolbar) {
            return (DecorToolbar) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        throw new IllegalStateException(new StringBuilder().append("Can't make a decor toolbar out of ").append(view).toString() != null ? view.getClass().getSimpleName() : "null");
    }

    public void setElevation(float f) {
        ViewCompat.setElevation(this.c, f);
    }

    public float getElevation() {
        return ViewCompat.getElevation(this.c);
    }

    public void onConfigurationChanged(Configuration configuration) {
        a(ActionBarPolicy.get(this.a).hasEmbeddedTabs());
    }

    private void a(boolean z) {
        boolean z2;
        boolean z3;
        boolean z4 = true;
        this.E = z;
        if (this.E) {
            this.c.setTabContainer(null);
            this.d.setEmbeddedTabView(this.g);
        } else {
            this.d.setEmbeddedTabView(null);
            this.c.setTabContainer(this.g);
        }
        if (getNavigationMode() == 2) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (this.g != null) {
            if (z2) {
                this.g.setVisibility(0);
                if (this.b != null) {
                    ViewCompat.requestApplyInsets(this.b);
                }
            } else {
                this.g.setVisibility(8);
            }
        }
        DecorToolbar decorToolbar = this.d;
        if (this.E || !z2) {
            z3 = false;
        } else {
            z3 = true;
        }
        decorToolbar.setCollapsible(z3);
        ActionBarOverlayLayout actionBarOverlayLayout = this.b;
        if (this.E || !z2) {
            z4 = false;
        }
        actionBarOverlayLayout.setHasNonEmbeddedTabs(z4);
    }

    private void c() {
        if (this.g == null) {
            ScrollingTabContainerView scrollingTabContainerView = new ScrollingTabContainerView(this.a);
            if (this.E) {
                scrollingTabContainerView.setVisibility(0);
                this.d.setEmbeddedTabView(scrollingTabContainerView);
            } else {
                if (getNavigationMode() == 2) {
                    scrollingTabContainerView.setVisibility(0);
                    if (this.b != null) {
                        ViewCompat.requestApplyInsets(this.b);
                    }
                } else {
                    scrollingTabContainerView.setVisibility(8);
                }
                this.c.setTabContainer(scrollingTabContainerView);
            }
            this.g = scrollingTabContainerView;
        }
    }

    void b() {
        if (this.j != null) {
            this.j.onDestroyActionMode(this.i);
            this.i = null;
            this.j = null;
        }
    }

    public void onWindowVisibilityChanged(int i) {
        this.F = i;
    }

    public void setShowHideAnimationEnabled(boolean z) {
        this.I = z;
        if (!z && this.n != null) {
            this.n.cancel();
        }
    }

    public void addOnMenuVisibilityListener(OnMenuVisibilityListener onMenuVisibilityListener) {
        this.D.add(onMenuVisibilityListener);
    }

    public void removeOnMenuVisibilityListener(OnMenuVisibilityListener onMenuVisibilityListener) {
        this.D.remove(onMenuVisibilityListener);
    }

    public void dispatchMenuVisibilityChanged(boolean z) {
        if (z != this.C) {
            this.C = z;
            int size = this.D.size();
            for (int i = 0; i < size; i++) {
                ((OnMenuVisibilityListener) this.D.get(i)).onMenuVisibilityChanged(z);
            }
        }
    }

    public void setCustomView(int i) {
        setCustomView(LayoutInflater.from(getThemedContext()).inflate(i, this.d.getViewGroup(), false));
    }

    public void setDisplayUseLogoEnabled(boolean z) {
        setDisplayOptions(z ? 1 : 0, 1);
    }

    public void setDisplayShowHomeEnabled(boolean z) {
        setDisplayOptions(z ? 2 : 0, 2);
    }

    public void setDisplayHomeAsUpEnabled(boolean z) {
        setDisplayOptions(z ? 4 : 0, 4);
    }

    public void setDisplayShowTitleEnabled(boolean z) {
        setDisplayOptions(z ? 8 : 0, 8);
    }

    public void setDisplayShowCustomEnabled(boolean z) {
        setDisplayOptions(z ? 16 : 0, 16);
    }

    public void setHomeButtonEnabled(boolean z) {
        this.d.setHomeButtonEnabled(z);
    }

    public void setTitle(int i) {
        setTitle(this.a.getString(i));
    }

    public void setSubtitle(int i) {
        setSubtitle(this.a.getString(i));
    }

    public void setSelectedNavigationItem(int i) {
        switch (this.d.getNavigationMode()) {
            case 1:
                this.d.setDropdownSelectedPosition(i);
                return;
            case 2:
                selectTab((Tab) this.y.get(i));
                return;
            default:
                throw new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");
        }
    }

    public void removeAllTabs() {
        d();
    }

    private void d() {
        if (this.z != null) {
            selectTab(null);
        }
        this.y.clear();
        if (this.g != null) {
            this.g.removeAllTabs();
        }
        this.A = -1;
    }

    public void setTitle(CharSequence charSequence) {
        this.d.setTitle(charSequence);
    }

    public void setWindowTitle(CharSequence charSequence) {
        this.d.setWindowTitle(charSequence);
    }

    public boolean requestFocus() {
        ViewGroup viewGroup = this.d.getViewGroup();
        if (viewGroup == null || viewGroup.hasFocus()) {
            return false;
        }
        viewGroup.requestFocus();
        return true;
    }

    public void setSubtitle(CharSequence charSequence) {
        this.d.setSubtitle(charSequence);
    }

    public void setDisplayOptions(int i) {
        if ((i & 4) != 0) {
            this.B = true;
        }
        this.d.setDisplayOptions(i);
    }

    public void setDisplayOptions(int i, int i2) {
        int displayOptions = this.d.getDisplayOptions();
        if ((i2 & 4) != 0) {
            this.B = true;
        }
        this.d.setDisplayOptions((displayOptions & (i2 ^ -1)) | (i & i2));
    }

    public void setBackgroundDrawable(Drawable drawable) {
        this.c.setPrimaryBackground(drawable);
    }

    public void setStackedBackgroundDrawable(Drawable drawable) {
        this.c.setStackedBackground(drawable);
    }

    public void setSplitBackgroundDrawable(Drawable drawable) {
    }

    public View getCustomView() {
        return this.d.getCustomView();
    }

    public CharSequence getTitle() {
        return this.d.getTitle();
    }

    public CharSequence getSubtitle() {
        return this.d.getSubtitle();
    }

    public int getNavigationMode() {
        return this.d.getNavigationMode();
    }

    public int getDisplayOptions() {
        return this.d.getDisplayOptions();
    }

    public ActionMode startActionMode(Callback callback) {
        if (this.h != null) {
            this.h.finish();
        }
        this.b.setHideOnContentScrollEnabled(false);
        this.e.killMode();
        ActionMode actionModeImpl = new ActionModeImpl(this, this.e.getContext(), callback);
        if (!actionModeImpl.dispatchOnCreate()) {
            return null;
        }
        this.h = actionModeImpl;
        actionModeImpl.invalidate();
        this.e.initForMode(actionModeImpl);
        animateToMode(true);
        this.e.sendAccessibilityEvent(32);
        return actionModeImpl;
    }

    private void a(Tab tab, int i) {
        TabImpl tabImpl = (TabImpl) tab;
        if (tabImpl.getCallback() == null) {
            throw new IllegalStateException("Action Bar Tab must have a Callback");
        }
        tabImpl.setPosition(i);
        this.y.add(i, tabImpl);
        int size = this.y.size();
        for (int i2 = i + 1; i2 < size; i2++) {
            ((TabImpl) this.y.get(i2)).setPosition(i2);
        }
    }

    public void addTab(Tab tab) {
        addTab(tab, this.y.isEmpty());
    }

    public void addTab(Tab tab, int i) {
        addTab(tab, i, this.y.isEmpty());
    }

    public void addTab(Tab tab, boolean z) {
        c();
        this.g.addTab(tab, z);
        a(tab, this.y.size());
        if (z) {
            selectTab(tab);
        }
    }

    public void addTab(Tab tab, int i, boolean z) {
        c();
        this.g.addTab(tab, i, z);
        a(tab, i);
        if (z) {
            selectTab(tab);
        }
    }

    public Tab newTab() {
        return new TabImpl(this);
    }

    public void removeTab(Tab tab) {
        removeTabAt(tab.getPosition());
    }

    public void removeTabAt(int i) {
        if (this.g != null) {
            int position = this.z != null ? this.z.getPosition() : this.A;
            this.g.removeTabAt(i);
            TabImpl tabImpl = (TabImpl) this.y.remove(i);
            if (tabImpl != null) {
                tabImpl.setPosition(-1);
            }
            int size = this.y.size();
            for (int i2 = i; i2 < size; i2++) {
                ((TabImpl) this.y.get(i2)).setPosition(i2);
            }
            if (position == i) {
                Tab tab;
                if (this.y.isEmpty()) {
                    tab = null;
                } else {
                    tabImpl = (TabImpl) this.y.get(Math.max(0, i - 1));
                }
                selectTab(tab);
            }
        }
    }

    public void selectTab(Tab tab) {
        int i = -1;
        if (getNavigationMode() != 2) {
            int position;
            if (tab != null) {
                position = tab.getPosition();
            } else {
                position = -1;
            }
            this.A = position;
            return;
        }
        FragmentTransaction fragmentTransaction;
        if (!(this.w instanceof FragmentActivity) || this.d.getViewGroup().isInEditMode()) {
            fragmentTransaction = null;
        } else {
            fragmentTransaction = ((FragmentActivity) this.w).getSupportFragmentManager().beginTransaction().disallowAddToBackStack();
        }
        if (this.z != tab) {
            ScrollingTabContainerView scrollingTabContainerView = this.g;
            if (tab != null) {
                i = tab.getPosition();
            }
            scrollingTabContainerView.setTabSelected(i);
            if (this.z != null) {
                this.z.getCallback().onTabUnselected(this.z, fragmentTransaction);
            }
            this.z = (TabImpl) tab;
            if (this.z != null) {
                this.z.getCallback().onTabSelected(this.z, fragmentTransaction);
            }
        } else if (this.z != null) {
            this.z.getCallback().onTabReselected(this.z, fragmentTransaction);
            this.g.animateToTab(tab.getPosition());
        }
        if (fragmentTransaction != null && !fragmentTransaction.isEmpty()) {
            fragmentTransaction.commit();
        }
    }

    public Tab getSelectedTab() {
        return this.z;
    }

    public int getHeight() {
        return this.c.getHeight();
    }

    public void enableContentAnimations(boolean z) {
        this.k = z;
    }

    public void show() {
        if (this.l) {
            this.l = false;
            b(false);
        }
    }

    private void e() {
        if (!this.G) {
            this.G = true;
            if (this.b != null) {
                this.b.setShowingForActionMode(true);
            }
            b(false);
        }
    }

    public void showForSystem() {
        if (this.m) {
            this.m = false;
            b(true);
        }
    }

    public void hide() {
        if (!this.l) {
            this.l = true;
            b(false);
        }
    }

    private void f() {
        if (this.G) {
            this.G = false;
            if (this.b != null) {
                this.b.setShowingForActionMode(false);
            }
            b(false);
        }
    }

    public void hideForSystem() {
        if (!this.m) {
            this.m = true;
            b(true);
        }
    }

    public void setHideOnContentScrollEnabled(boolean z) {
        if (!z || this.b.isInOverlayMode()) {
            this.o = z;
            this.b.setHideOnContentScrollEnabled(z);
            return;
        }
        throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
    }

    public boolean isHideOnContentScrollEnabled() {
        return this.b.isHideOnContentScrollEnabled();
    }

    public int getHideOffset() {
        return this.b.getActionBarHideOffset();
    }

    public void setHideOffset(int i) {
        if (i == 0 || this.b.isInOverlayMode()) {
            this.b.setActionBarHideOffset(i);
            return;
        }
        throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to set a non-zero hide offset");
    }

    static boolean a(boolean z, boolean z2, boolean z3) {
        if (z3) {
            return true;
        }
        if (z || z2) {
            return false;
        }
        return true;
    }

    private void b(boolean z) {
        if (a(this.l, this.m, this.G)) {
            if (!this.H) {
                this.H = true;
                doShow(z);
            }
        } else if (this.H) {
            this.H = false;
            doHide(z);
        }
    }

    public void doShow(boolean z) {
        if (this.n != null) {
            this.n.cancel();
        }
        this.c.setVisibility(0);
        if (this.F == 0 && (this.I || z)) {
            this.c.setTranslationY(0.0f);
            float f = (float) (-this.c.getHeight());
            if (z) {
                int[] iArr = new int[]{0, 0};
                this.c.getLocationInWindow(iArr);
                f -= (float) iArr[1];
            }
            this.c.setTranslationY(f);
            ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = new ViewPropertyAnimatorCompatSet();
            ViewPropertyAnimatorCompat translationY = ViewCompat.animate(this.c).translationY(0.0f);
            translationY.setUpdateListener(this.r);
            viewPropertyAnimatorCompatSet.play(translationY);
            if (this.k && this.f != null) {
                this.f.setTranslationY(f);
                viewPropertyAnimatorCompatSet.play(ViewCompat.animate(this.f).translationY(0.0f));
            }
            viewPropertyAnimatorCompatSet.setInterpolator(u);
            viewPropertyAnimatorCompatSet.setDuration(250);
            viewPropertyAnimatorCompatSet.setListener(this.q);
            this.n = viewPropertyAnimatorCompatSet;
            viewPropertyAnimatorCompatSet.start();
        } else {
            this.c.setAlpha(1.0f);
            this.c.setTranslationY(0.0f);
            if (this.k && this.f != null) {
                this.f.setTranslationY(0.0f);
            }
            this.q.onAnimationEnd(null);
        }
        if (this.b != null) {
            ViewCompat.requestApplyInsets(this.b);
        }
    }

    public void doHide(boolean z) {
        if (this.n != null) {
            this.n.cancel();
        }
        if (this.F == 0 && (this.I || z)) {
            this.c.setAlpha(1.0f);
            this.c.setTransitioning(true);
            ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = new ViewPropertyAnimatorCompatSet();
            float f = (float) (-this.c.getHeight());
            if (z) {
                int[] iArr = new int[]{0, 0};
                this.c.getLocationInWindow(iArr);
                f -= (float) iArr[1];
            }
            ViewPropertyAnimatorCompat translationY = ViewCompat.animate(this.c).translationY(f);
            translationY.setUpdateListener(this.r);
            viewPropertyAnimatorCompatSet.play(translationY);
            if (this.k && this.f != null) {
                viewPropertyAnimatorCompatSet.play(ViewCompat.animate(this.f).translationY(f));
            }
            viewPropertyAnimatorCompatSet.setInterpolator(t);
            viewPropertyAnimatorCompatSet.setDuration(250);
            viewPropertyAnimatorCompatSet.setListener(this.p);
            this.n = viewPropertyAnimatorCompatSet;
            viewPropertyAnimatorCompatSet.start();
            return;
        }
        this.p.onAnimationEnd(null);
    }

    public boolean isShowing() {
        int height = getHeight();
        return this.H && (height == 0 || getHideOffset() < height);
    }

    public void animateToMode(boolean z) {
        if (z) {
            e();
        } else {
            f();
        }
        if (g()) {
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat;
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat2;
            if (z) {
                viewPropertyAnimatorCompat = this.d.setupAnimatorToVisibility(4, 100);
                viewPropertyAnimatorCompat2 = this.e.setupAnimatorToVisibility(0, 200);
            } else {
                viewPropertyAnimatorCompat2 = this.d.setupAnimatorToVisibility(0, 200);
                viewPropertyAnimatorCompat = this.e.setupAnimatorToVisibility(8, 100);
            }
            ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = new ViewPropertyAnimatorCompatSet();
            viewPropertyAnimatorCompatSet.playSequentially(viewPropertyAnimatorCompat, viewPropertyAnimatorCompat2);
            viewPropertyAnimatorCompatSet.start();
        } else if (z) {
            this.d.setVisibility(4);
            this.e.setVisibility(0);
        } else {
            this.d.setVisibility(0);
            this.e.setVisibility(8);
        }
    }

    private boolean g() {
        return ViewCompat.isLaidOut(this.c);
    }

    public Context getThemedContext() {
        if (this.v == null) {
            TypedValue typedValue = new TypedValue();
            this.a.getTheme().resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                this.v = new ContextThemeWrapper(this.a, i);
            } else {
                this.v = this.a;
            }
        }
        return this.v;
    }

    public boolean isTitleTruncated() {
        return this.d != null && this.d.isTitleTruncated();
    }

    public void setHomeAsUpIndicator(Drawable drawable) {
        this.d.setNavigationIcon(drawable);
    }

    public void setHomeAsUpIndicator(int i) {
        this.d.setNavigationIcon(i);
    }

    public void setHomeActionContentDescription(CharSequence charSequence) {
        this.d.setNavigationContentDescription(charSequence);
    }

    public void setHomeActionContentDescription(int i) {
        this.d.setNavigationContentDescription(i);
    }

    public void onContentScrollStarted() {
        if (this.n != null) {
            this.n.cancel();
            this.n = null;
        }
    }

    public void onContentScrollStopped() {
    }

    public boolean collapseActionView() {
        if (this.d == null || !this.d.hasExpandedActionView()) {
            return false;
        }
        this.d.collapseActionView();
        return true;
    }

    public void setCustomView(View view) {
        this.d.setCustomView(view);
    }

    public void setCustomView(View view, LayoutParams layoutParams) {
        view.setLayoutParams(layoutParams);
        this.d.setCustomView(view);
    }

    public void setListNavigationCallbacks(SpinnerAdapter spinnerAdapter, OnNavigationListener onNavigationListener) {
        this.d.setDropdownParams(spinnerAdapter, new ac(onNavigationListener));
    }

    public int getSelectedNavigationIndex() {
        switch (this.d.getNavigationMode()) {
            case 1:
                return this.d.getDropdownSelectedPosition();
            case 2:
                if (this.z != null) {
                    return this.z.getPosition();
                }
                return -1;
            default:
                return -1;
        }
    }

    public int getNavigationItemCount() {
        switch (this.d.getNavigationMode()) {
            case 1:
                return this.d.getDropdownItemCount();
            case 2:
                return this.y.size();
            default:
                return 0;
        }
    }

    public int getTabCount() {
        return this.y.size();
    }

    public void setNavigationMode(int i) {
        boolean z;
        boolean z2 = true;
        int navigationMode = this.d.getNavigationMode();
        switch (navigationMode) {
            case 2:
                this.A = getSelectedNavigationIndex();
                selectTab(null);
                this.g.setVisibility(8);
                break;
        }
        if (!(navigationMode == i || this.E || this.b == null)) {
            ViewCompat.requestApplyInsets(this.b);
        }
        this.d.setNavigationMode(i);
        switch (i) {
            case 2:
                c();
                this.g.setVisibility(0);
                if (this.A != -1) {
                    setSelectedNavigationItem(this.A);
                    this.A = -1;
                    break;
                }
                break;
        }
        DecorToolbar decorToolbar = this.d;
        if (i != 2 || this.E) {
            z = false;
        } else {
            z = true;
        }
        decorToolbar.setCollapsible(z);
        ActionBarOverlayLayout actionBarOverlayLayout = this.b;
        if (i != 2 || this.E) {
            z2 = false;
        }
        actionBarOverlayLayout.setHasNonEmbeddedTabs(z2);
    }

    public Tab getTabAt(int i) {
        return (Tab) this.y.get(i);
    }

    public void setIcon(int i) {
        this.d.setIcon(i);
    }

    public void setIcon(Drawable drawable) {
        this.d.setIcon(drawable);
    }

    public boolean hasIcon() {
        return this.d.hasIcon();
    }

    public void setLogo(int i) {
        this.d.setLogo(i);
    }

    public void setLogo(Drawable drawable) {
        this.d.setLogo(drawable);
    }

    public boolean hasLogo() {
        return this.d.hasLogo();
    }

    public void setDefaultDisplayHomeAsUpEnabled(boolean z) {
        if (!this.B) {
            setDisplayHomeAsUpEnabled(z);
        }
    }

    public boolean onKeyShortcut(int i, KeyEvent keyEvent) {
        if (this.h == null) {
            return false;
        }
        Menu menu = this.h.getMenu();
        if (menu == null) {
            return false;
        }
        boolean z;
        if (KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1) {
            z = true;
        } else {
            z = false;
        }
        menu.setQwertyMode(z);
        return menu.performShortcut(i, keyEvent, 0);
    }
}
