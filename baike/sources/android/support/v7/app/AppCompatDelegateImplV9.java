package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NavUtils;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.appcompat.R;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.view.StandaloneActionMode;
import android.support.v7.view.menu.ListMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.DecorContentParent;
import android.support.v7.widget.FitWindowsViewGroup;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.VectorEnabledTintResources;
import android.support.v7.widget.ViewStubCompat;
import android.support.v7.widget.ViewUtils;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.LayoutInflater.Factory2;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import org.xmlpull.v1.XmlPullParser;

@RequiresApi(14)
class AppCompatDelegateImplV9 extends l implements Callback, Factory2 {
    private static final boolean t = (VERSION.SDK_INT < 21);
    private View A;
    private boolean B;
    private boolean C;
    private boolean D;
    private PanelFeatureState[] E;
    private PanelFeatureState F;
    private boolean G;
    private final Runnable H = new s(this);
    private boolean I;
    private Rect J;
    private Rect K;
    private ab L;
    ActionMode m;
    ActionBarContextView n;
    PopupWindow o;
    Runnable p;
    ViewPropertyAnimatorCompat q = null;
    boolean r;
    int s;
    private DecorContentParent u;
    private a v;
    private d w;
    private boolean x;
    private ViewGroup y;
    private TextView z;

    protected static final class PanelFeatureState {
        int a;
        int b;
        int c;
        int d;
        int e;
        int f;
        ViewGroup g;
        View h;
        View i;
        MenuBuilder j;
        ListMenuPresenter k;
        Context l;
        boolean m;
        boolean n;
        boolean o;
        boolean p = false;
        boolean q;
        public boolean qwertyMode;
        Bundle r;

        private static class SavedState implements Parcelable {
            public static final Creator<SavedState> CREATOR = new aa();
            int a;
            boolean b;
            Bundle c;

            SavedState() {
            }

            public int describeContents() {
                return 0;
            }

            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.a);
                parcel.writeInt(this.b ? 1 : 0);
                if (this.b) {
                    parcel.writeBundle(this.c);
                }
            }

            static SavedState a(Parcel parcel, ClassLoader classLoader) {
                boolean z = true;
                SavedState savedState = new SavedState();
                savedState.a = parcel.readInt();
                if (parcel.readInt() != 1) {
                    z = false;
                }
                savedState.b = z;
                if (savedState.b) {
                    savedState.c = parcel.readBundle(classLoader);
                }
                return savedState;
            }
        }

        PanelFeatureState(int i) {
            this.a = i;
        }

        public boolean hasPanelItems() {
            if (this.h == null) {
                return false;
            }
            if (this.i != null || this.k.getAdapter().getCount() > 0) {
                return true;
            }
            return false;
        }

        public void clearMenuPresenters() {
            if (this.j != null) {
                this.j.removeMenuPresenter(this.k);
            }
            this.k = null;
        }

        void a(Context context) {
            TypedValue typedValue = new TypedValue();
            Theme newTheme = context.getResources().newTheme();
            newTheme.setTo(context.getTheme());
            newTheme.resolveAttribute(R.attr.actionBarPopupTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                newTheme.applyStyle(typedValue.resourceId, true);
            }
            newTheme.resolveAttribute(R.attr.panelMenuListTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                newTheme.applyStyle(typedValue.resourceId, true);
            } else {
                newTheme.applyStyle(R.style.Theme_AppCompat_CompactMenu, true);
            }
            Context contextThemeWrapper = new ContextThemeWrapper(context, 0);
            contextThemeWrapper.getTheme().setTo(newTheme);
            this.l = contextThemeWrapper;
            TypedArray obtainStyledAttributes = contextThemeWrapper.obtainStyledAttributes(R.styleable.AppCompatTheme);
            this.b = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTheme_panelBackground, 0);
            this.f = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTheme_android_windowAnimationStyle, 0);
            obtainStyledAttributes.recycle();
        }

        void a(MenuBuilder menuBuilder) {
            if (menuBuilder != this.j) {
                if (this.j != null) {
                    this.j.removeMenuPresenter(this.k);
                }
                this.j = menuBuilder;
                if (menuBuilder != null && this.k != null) {
                    menuBuilder.addMenuPresenter(this.k);
                }
            }
        }

        MenuView a(MenuPresenter.Callback callback) {
            if (this.j == null) {
                return null;
            }
            if (this.k == null) {
                this.k = new ListMenuPresenter(this.l, R.layout.abc_list_menu_item_layout);
                this.k.setCallback(callback);
                this.j.addMenuPresenter(this.k);
            }
            return this.k.getMenuView(this.g);
        }
    }

    private final class a implements MenuPresenter.Callback {
        final /* synthetic */ AppCompatDelegateImplV9 a;

        a(AppCompatDelegateImplV9 appCompatDelegateImplV9) {
            this.a = appCompatDelegateImplV9;
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            Window.Callback d = this.a.d();
            if (d != null) {
                d.onMenuOpened(108, menuBuilder);
            }
            return true;
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            this.a.a(menuBuilder);
        }
    }

    class b implements ActionMode.Callback {
        final /* synthetic */ AppCompatDelegateImplV9 a;
        private ActionMode.Callback b;

        public b(AppCompatDelegateImplV9 appCompatDelegateImplV9, ActionMode.Callback callback) {
            this.a = appCompatDelegateImplV9;
            this.b = callback;
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return this.b.onCreateActionMode(actionMode, menu);
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return this.b.onPrepareActionMode(actionMode, menu);
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.b.onActionItemClicked(actionMode, menuItem);
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            this.b.onDestroyActionMode(actionMode);
            if (this.a.o != null) {
                this.a.b.getDecorView().removeCallbacks(this.a.p);
            }
            if (this.a.n != null) {
                this.a.g();
                this.a.q = ViewCompat.animate(this.a.n).alpha(0.0f);
                this.a.q.setListener(new z(this));
            }
            if (this.a.e != null) {
                this.a.e.onSupportActionModeFinished(this.a.m);
            }
            this.a.m = null;
        }
    }

    private class c extends ContentFrameLayout {
        final /* synthetic */ AppCompatDelegateImplV9 a;

        public c(AppCompatDelegateImplV9 appCompatDelegateImplV9, Context context) {
            this.a = appCompatDelegateImplV9;
            super(context);
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return this.a.a(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0 || !a((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            this.a.b(0);
            return true;
        }

        public void setBackgroundResource(int i) {
            setBackgroundDrawable(AppCompatResources.getDrawable(getContext(), i));
        }

        private boolean a(int i, int i2) {
            return i < -5 || i2 < -5 || i > getWidth() + 5 || i2 > getHeight() + 5;
        }
    }

    private final class d implements MenuPresenter.Callback {
        final /* synthetic */ AppCompatDelegateImplV9 a;

        d(AppCompatDelegateImplV9 appCompatDelegateImplV9) {
            this.a = appCompatDelegateImplV9;
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            Menu menu;
            MenuBuilder rootMenu = menuBuilder.getRootMenu();
            boolean z2 = rootMenu != menuBuilder;
            AppCompatDelegateImplV9 appCompatDelegateImplV9 = this.a;
            if (z2) {
                menu = rootMenu;
            }
            PanelFeatureState a = appCompatDelegateImplV9.a(menu);
            if (a == null) {
                return;
            }
            if (z2) {
                this.a.a(a.a, a, rootMenu);
                this.a.a(a, true);
                return;
            }
            this.a.a(a, z);
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            if (menuBuilder == null && this.a.h) {
                Window.Callback d = this.a.d();
                if (!(d == null || this.a.c())) {
                    d.onMenuOpened(108, menuBuilder);
                }
            }
            return true;
        }
    }

    AppCompatDelegateImplV9(Context context, Window window, AppCompatCallback appCompatCallback) {
        super(context, window, appCompatCallback);
    }

    public void onCreate(Bundle bundle) {
        if ((this.c instanceof Activity) && NavUtils.getParentActivityName((Activity) this.c) != null) {
            ActionBar a = a();
            if (a == null) {
                this.I = true;
            } else {
                a.setDefaultDisplayHomeAsUpEnabled(true);
            }
        }
    }

    public void onPostCreate(Bundle bundle) {
        j();
    }

    public void initWindowDecorActionBar() {
        j();
        if (this.h && this.f == null) {
            if (this.c instanceof Activity) {
                this.f = new WindowDecorActionBar((Activity) this.c, this.i);
            } else if (this.c instanceof Dialog) {
                this.f = new WindowDecorActionBar((Dialog) this.c);
            }
            if (this.f != null) {
                this.f.setDefaultDisplayHomeAsUpEnabled(this.I);
            }
        }
    }

    public void setSupportActionBar(Toolbar toolbar) {
        if (this.c instanceof Activity) {
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar instanceof WindowDecorActionBar) {
                throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
            }
            this.g = null;
            if (supportActionBar != null) {
                supportActionBar.a();
            }
            if (toolbar != null) {
                ActionBar aeVar = new ae(toolbar, ((Activity) this.c).getTitle(), this.d);
                this.f = aeVar;
                this.b.setCallback(aeVar.getWrappedWindowCallback());
            } else {
                this.f = null;
                this.b.setCallback(this.d);
            }
            invalidateOptionsMenu();
        }
    }

    @Nullable
    public <T extends View> T findViewById(@IdRes int i) {
        j();
        return this.b.findViewById(i);
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (this.h && this.x) {
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.onConfigurationChanged(configuration);
            }
        }
        AppCompatDrawableManager.get().onConfigurationChanged(this.a);
        applyDayNight();
    }

    public void onStop() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setShowHideAnimationEnabled(false);
        }
    }

    public void onPostResume() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setShowHideAnimationEnabled(true);
        }
    }

    public void setContentView(View view) {
        j();
        ViewGroup viewGroup = (ViewGroup) this.y.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.c.onContentChanged();
    }

    public void setContentView(int i) {
        j();
        ViewGroup viewGroup = (ViewGroup) this.y.findViewById(16908290);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.a).inflate(i, viewGroup);
        this.c.onContentChanged();
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        j();
        ViewGroup viewGroup = (ViewGroup) this.y.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.c.onContentChanged();
    }

    public void addContentView(View view, LayoutParams layoutParams) {
        j();
        ((ViewGroup) this.y.findViewById(16908290)).addView(view, layoutParams);
        this.c.onContentChanged();
    }

    public void onDestroy() {
        if (this.r) {
            this.b.getDecorView().removeCallbacks(this.H);
        }
        super.onDestroy();
        if (this.f != null) {
            this.f.a();
        }
    }

    private void j() {
        if (!this.x) {
            this.y = k();
            CharSequence e = e();
            if (!TextUtils.isEmpty(e)) {
                a(e);
            }
            l();
            a(this.y);
            this.x = true;
            PanelFeatureState a = a(0, false);
            if (!c()) {
                if (a == null || a.j == null) {
                    a(108);
                }
            }
        }
    }

    private ViewGroup k() {
        TypedArray obtainStyledAttributes = this.a.obtainStyledAttributes(R.styleable.AppCompatTheme);
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTheme_windowActionBar)) {
            View view;
            if (obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_windowNoTitle, false)) {
                requestWindowFeature(1);
            } else if (obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_windowActionBar, false)) {
                requestWindowFeature(108);
            }
            if (obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_windowActionBarOverlay, false)) {
                requestWindowFeature(109);
            }
            if (obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_windowActionModeOverlay, false)) {
                requestWindowFeature(10);
            }
            this.k = obtainStyledAttributes.getBoolean(R.styleable.AppCompatTheme_android_windowIsFloating, false);
            obtainStyledAttributes.recycle();
            this.b.getDecorView();
            LayoutInflater from = LayoutInflater.from(this.a);
            if (this.l) {
                View view2;
                if (this.j) {
                    view2 = (ViewGroup) from.inflate(R.layout.abc_screen_simple_overlay_action_mode, null);
                } else {
                    view2 = (ViewGroup) from.inflate(R.layout.abc_screen_simple, null);
                }
                if (VERSION.SDK_INT >= 21) {
                    ViewCompat.setOnApplyWindowInsetsListener(view2, new t(this));
                    view = view2;
                } else {
                    ((FitWindowsViewGroup) view2).setOnFitSystemWindowsListener(new u(this));
                    view = view2;
                }
            } else if (this.k) {
                r0 = (ViewGroup) from.inflate(R.layout.abc_dialog_title_material, null);
                this.i = false;
                this.h = false;
                view = r0;
            } else if (this.h) {
                Context contextThemeWrapper;
                TypedValue typedValue = new TypedValue();
                this.a.getTheme().resolveAttribute(R.attr.actionBarTheme, typedValue, true);
                if (typedValue.resourceId != 0) {
                    contextThemeWrapper = new ContextThemeWrapper(this.a, typedValue.resourceId);
                } else {
                    contextThemeWrapper = this.a;
                }
                r0 = (ViewGroup) LayoutInflater.from(contextThemeWrapper).inflate(R.layout.abc_screen_toolbar, null);
                this.u = (DecorContentParent) r0.findViewById(R.id.decor_content_parent);
                this.u.setWindowCallback(d());
                if (this.i) {
                    this.u.initFeature(109);
                }
                if (this.B) {
                    this.u.initFeature(2);
                }
                if (this.C) {
                    this.u.initFeature(5);
                }
                view = r0;
            } else {
                view = null;
            }
            if (view == null) {
                throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.h + ", windowActionBarOverlay: " + this.i + ", android:windowIsFloating: " + this.k + ", windowActionModeOverlay: " + this.j + ", windowNoTitle: " + this.l + " }");
            }
            if (this.u == null) {
                this.z = (TextView) view.findViewById(R.id.title);
            }
            ViewUtils.makeOptionalFitsSystemWindows(view);
            ContentFrameLayout contentFrameLayout = (ContentFrameLayout) view.findViewById(R.id.action_bar_activity_content);
            ViewGroup viewGroup = (ViewGroup) this.b.findViewById(16908290);
            if (viewGroup != null) {
                while (viewGroup.getChildCount() > 0) {
                    View childAt = viewGroup.getChildAt(0);
                    viewGroup.removeViewAt(0);
                    contentFrameLayout.addView(childAt);
                }
                viewGroup.setId(-1);
                contentFrameLayout.setId(16908290);
                if (viewGroup instanceof FrameLayout) {
                    ((FrameLayout) viewGroup).setForeground(null);
                }
            }
            this.b.setContentView(view);
            contentFrameLayout.setAttachListener(new v(this));
            return view;
        }
        obtainStyledAttributes.recycle();
        throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
    }

    void a(ViewGroup viewGroup) {
    }

    private void l() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.y.findViewById(16908290);
        View decorView = this.b.getDecorView();
        contentFrameLayout.setDecorPadding(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray obtainStyledAttributes = this.a.obtainStyledAttributes(R.styleable.AppCompatTheme);
        obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTheme_windowFixedWidthMajor)) {
            obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowFixedWidthMajor, contentFrameLayout.getFixedWidthMajor());
        }
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTheme_windowFixedWidthMinor)) {
            obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowFixedWidthMinor, contentFrameLayout.getFixedWidthMinor());
        }
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTheme_windowFixedHeightMajor)) {
            obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowFixedHeightMajor, contentFrameLayout.getFixedHeightMajor());
        }
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTheme_windowFixedHeightMinor)) {
            obtainStyledAttributes.getValue(R.styleable.AppCompatTheme_windowFixedHeightMinor, contentFrameLayout.getFixedHeightMinor());
        }
        obtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    public boolean requestWindowFeature(int i) {
        int e = e(i);
        if (this.l && e == 108) {
            return false;
        }
        if (this.h && e == 1) {
            this.h = false;
        }
        switch (e) {
            case 1:
                m();
                this.l = true;
                return true;
            case 2:
                m();
                this.B = true;
                return true;
            case 5:
                m();
                this.C = true;
                return true;
            case 10:
                m();
                this.j = true;
                return true;
            case 108:
                m();
                this.h = true;
                return true;
            case 109:
                m();
                this.i = true;
                return true;
            default:
                return this.b.requestFeature(e);
        }
    }

    public boolean hasWindowFeature(int i) {
        switch (e(i)) {
            case 1:
                return this.l;
            case 2:
                return this.B;
            case 5:
                return this.C;
            case 10:
                return this.j;
            case 108:
                return this.h;
            case 109:
                return this.i;
            default:
                return false;
        }
    }

    void a(CharSequence charSequence) {
        if (this.u != null) {
            this.u.setWindowTitle(charSequence);
        } else if (a() != null) {
            a().setWindowTitle(charSequence);
        } else if (this.z != null) {
            this.z.setText(charSequence);
        }
    }

    void a(int i, Menu menu) {
        if (i == 108) {
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.dispatchMenuVisibilityChanged(false);
            }
        } else if (i == 0) {
            PanelFeatureState a = a(i, true);
            if (a.o) {
                a(a, false);
            }
        }
    }

    boolean b(int i, Menu menu) {
        if (i != 108) {
            return false;
        }
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null) {
            return true;
        }
        supportActionBar.dispatchMenuVisibilityChanged(true);
        return true;
    }

    public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
        Window.Callback d = d();
        if (!(d == null || c())) {
            PanelFeatureState a = a(menuBuilder.getRootMenu());
            if (a != null) {
                return d.onMenuItemSelected(a.a, menuItem);
            }
        }
        return false;
    }

    public void onMenuModeChange(MenuBuilder menuBuilder) {
        a(menuBuilder, true);
    }

    public ActionMode startSupportActionMode(@NonNull ActionMode.Callback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        }
        if (this.m != null) {
            this.m.finish();
        }
        ActionMode.Callback bVar = new b(this, callback);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            this.m = supportActionBar.startActionMode(bVar);
            if (!(this.m == null || this.e == null)) {
                this.e.onSupportActionModeStarted(this.m);
            }
        }
        if (this.m == null) {
            this.m = a(bVar);
        }
        return this.m;
    }

    public void invalidateOptionsMenu() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null || !supportActionBar.invalidateOptionsMenu()) {
            a(0);
        }
    }

    ActionMode a(@NonNull ActionMode.Callback callback) {
        ActionMode actionMode;
        g();
        if (this.m != null) {
            this.m.finish();
        }
        if (!(callback instanceof b)) {
            callback = new b(this, callback);
        }
        if (this.e == null || c()) {
            actionMode = null;
        } else {
            try {
                actionMode = this.e.onWindowStartingSupportActionMode(callback);
            } catch (AbstractMethodError e) {
                actionMode = null;
            }
        }
        if (actionMode != null) {
            this.m = actionMode;
        } else {
            if (this.n == null) {
                if (this.k) {
                    Context contextThemeWrapper;
                    TypedValue typedValue = new TypedValue();
                    Theme theme = this.a.getTheme();
                    theme.resolveAttribute(R.attr.actionBarTheme, typedValue, true);
                    if (typedValue.resourceId != 0) {
                        Theme newTheme = this.a.getResources().newTheme();
                        newTheme.setTo(theme);
                        newTheme.applyStyle(typedValue.resourceId, true);
                        contextThemeWrapper = new ContextThemeWrapper(this.a, 0);
                        contextThemeWrapper.getTheme().setTo(newTheme);
                    } else {
                        contextThemeWrapper = this.a;
                    }
                    this.n = new ActionBarContextView(contextThemeWrapper);
                    this.o = new PopupWindow(contextThemeWrapper, null, R.attr.actionModePopupWindowStyle);
                    PopupWindowCompat.setWindowLayoutType(this.o, 2);
                    this.o.setContentView(this.n);
                    this.o.setWidth(-1);
                    contextThemeWrapper.getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true);
                    this.n.setContentHeight(TypedValue.complexToDimensionPixelSize(typedValue.data, contextThemeWrapper.getResources().getDisplayMetrics()));
                    this.o.setHeight(-2);
                    this.p = new w(this);
                } else {
                    ViewStubCompat viewStubCompat = (ViewStubCompat) this.y.findViewById(R.id.action_mode_bar_stub);
                    if (viewStubCompat != null) {
                        viewStubCompat.setLayoutInflater(LayoutInflater.from(b()));
                        this.n = (ActionBarContextView) viewStubCompat.inflate();
                    }
                }
            }
            if (this.n != null) {
                boolean z;
                g();
                this.n.killMode();
                Context context = this.n.getContext();
                ActionBarContextView actionBarContextView = this.n;
                if (this.o == null) {
                    z = true;
                } else {
                    z = false;
                }
                ActionMode standaloneActionMode = new StandaloneActionMode(context, actionBarContextView, callback, z);
                if (callback.onCreateActionMode(standaloneActionMode, standaloneActionMode.getMenu())) {
                    standaloneActionMode.invalidate();
                    this.n.initForMode(standaloneActionMode);
                    this.m = standaloneActionMode;
                    if (f()) {
                        this.n.setAlpha(0.0f);
                        this.q = ViewCompat.animate(this.n).alpha(1.0f);
                        this.q.setListener(new y(this));
                    } else {
                        this.n.setAlpha(1.0f);
                        this.n.setVisibility(0);
                        this.n.sendAccessibilityEvent(32);
                        if (this.n.getParent() instanceof View) {
                            ViewCompat.requestApplyInsets((View) this.n.getParent());
                        }
                    }
                    if (this.o != null) {
                        this.b.getDecorView().post(this.p);
                    }
                } else {
                    this.m = null;
                }
            }
        }
        if (!(this.m == null || this.e == null)) {
            this.e.onSupportActionModeStarted(this.m);
        }
        return this.m;
    }

    final boolean f() {
        return this.x && this.y != null && ViewCompat.isLaidOut(this.y);
    }

    void g() {
        if (this.q != null) {
            this.q.cancel();
        }
    }

    boolean h() {
        if (this.m != null) {
            this.m.finish();
            return true;
        }
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null || !supportActionBar.collapseActionView()) {
            return false;
        }
        return true;
    }

    boolean a(int i, KeyEvent keyEvent) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null && supportActionBar.onKeyShortcut(i, keyEvent)) {
            return true;
        }
        if (this.F == null || !a(this.F, keyEvent.getKeyCode(), keyEvent, 1)) {
            if (this.F == null) {
                PanelFeatureState a = a(0, true);
                b(a, keyEvent);
                boolean a2 = a(a, keyEvent.getKeyCode(), keyEvent, 1);
                a.m = false;
                if (a2) {
                    return true;
                }
            }
            return false;
        } else if (this.F == null) {
            return true;
        } else {
            this.F.n = true;
            return true;
        }
    }

    boolean a(KeyEvent keyEvent) {
        boolean z = true;
        if (keyEvent.getKeyCode() == 82 && this.c.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyEvent.getAction() != 0) {
            z = false;
        }
        return z ? c(keyCode, keyEvent) : b(keyCode, keyEvent);
    }

    boolean b(int i, KeyEvent keyEvent) {
        switch (i) {
            case 4:
                boolean z = this.G;
                this.G = false;
                PanelFeatureState a = a(0, false);
                if (a == null || !a.o) {
                    if (h()) {
                        return true;
                    }
                } else if (z) {
                    return true;
                } else {
                    a(a, true);
                    return true;
                }
                break;
            case 82:
                e(0, keyEvent);
                return true;
        }
        return false;
    }

    boolean c(int i, KeyEvent keyEvent) {
        boolean z = true;
        switch (i) {
            case 4:
                if ((keyEvent.getFlags() & 128) == 0) {
                    z = false;
                }
                this.G = z;
                break;
            case 82:
                d(0, keyEvent);
                return true;
        }
        return false;
    }

    public View createView(View view, String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        boolean z;
        if (this.L == null) {
            this.L = new ab();
        }
        if (t) {
            boolean a = attributeSet instanceof XmlPullParser ? ((XmlPullParser) attributeSet).getDepth() > 1 : a((ViewParent) view);
            z = a;
        } else {
            z = false;
        }
        return this.L.createView(view, str, context, attributeSet, z, t, true, VectorEnabledTintResources.shouldBeUsed());
    }

    private boolean a(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        ViewParent decorView = this.b.getDecorView();
        ViewParent viewParent2 = viewParent;
        while (viewParent2 != null) {
            if (viewParent2 == decorView || !(viewParent2 instanceof View) || ViewCompat.isAttachedToWindow((View) viewParent2)) {
                return false;
            }
            viewParent2 = viewParent2.getParent();
        }
        return true;
    }

    public void installViewFactory() {
        LayoutInflater from = LayoutInflater.from(this.a);
        if (from.getFactory() == null) {
            LayoutInflaterCompat.setFactory2(from, this);
        } else if (!(from.getFactory2() instanceof AppCompatDelegateImplV9)) {
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View a = a(view, str, context, attributeSet);
        return a != null ? a : createView(view, str, context, attributeSet);
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    View a(View view, String str, Context context, AttributeSet attributeSet) {
        if (this.c instanceof Factory) {
            View onCreateView = ((Factory) this.c).onCreateView(str, context, attributeSet);
            if (onCreateView != null) {
                return onCreateView;
            }
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.support.v7.app.AppCompatDelegateImplV9.PanelFeatureState r11, android.view.KeyEvent r12) {
        /*
        r10 = this;
        r3 = 0;
        r1 = -1;
        r9 = 1;
        r2 = -2;
        r0 = r11.o;
        if (r0 != 0) goto L_0x000e;
    L_0x0008:
        r0 = r10.c();
        if (r0 == 0) goto L_0x000f;
    L_0x000e:
        return;
    L_0x000f:
        r0 = r11.a;
        if (r0 != 0) goto L_0x0027;
    L_0x0013:
        r0 = r10.a;
        r0 = r0.getResources();
        r0 = r0.getConfiguration();
        r0 = r0.screenLayout;
        r0 = r0 & 15;
        r4 = 4;
        if (r0 != r4) goto L_0x003b;
    L_0x0024:
        r0 = r9;
    L_0x0025:
        if (r0 != 0) goto L_0x000e;
    L_0x0027:
        r0 = r10.d();
        if (r0 == 0) goto L_0x003d;
    L_0x002d:
        r4 = r11.a;
        r5 = r11.j;
        r0 = r0.onMenuOpened(r4, r5);
        if (r0 != 0) goto L_0x003d;
    L_0x0037:
        r10.a(r11, r9);
        goto L_0x000e;
    L_0x003b:
        r0 = r3;
        goto L_0x0025;
    L_0x003d:
        r0 = r10.a;
        r4 = "window";
        r0 = r0.getSystemService(r4);
        r8 = r0;
        r8 = (android.view.WindowManager) r8;
        if (r8 == 0) goto L_0x000e;
    L_0x004a:
        r0 = r10.b(r11, r12);
        if (r0 == 0) goto L_0x000e;
    L_0x0050:
        r0 = r11.g;
        if (r0 == 0) goto L_0x0058;
    L_0x0054:
        r0 = r11.p;
        if (r0 == 0) goto L_0x00e2;
    L_0x0058:
        r0 = r11.g;
        if (r0 != 0) goto L_0x00d0;
    L_0x005c:
        r0 = r10.a(r11);
        if (r0 == 0) goto L_0x000e;
    L_0x0062:
        r0 = r11.g;
        if (r0 == 0) goto L_0x000e;
    L_0x0066:
        r0 = r10.c(r11);
        if (r0 == 0) goto L_0x000e;
    L_0x006c:
        r0 = r11.hasPanelItems();
        if (r0 == 0) goto L_0x000e;
    L_0x0072:
        r0 = r11.h;
        r0 = r0.getLayoutParams();
        if (r0 != 0) goto L_0x00f4;
    L_0x007a:
        r0 = new android.view.ViewGroup$LayoutParams;
        r0.<init>(r2, r2);
        r1 = r0;
    L_0x0080:
        r0 = r11.b;
        r4 = r11.g;
        r4.setBackgroundResource(r0);
        r0 = r11.h;
        r0 = r0.getParent();
        if (r0 == 0) goto L_0x009a;
    L_0x008f:
        r4 = r0 instanceof android.view.ViewGroup;
        if (r4 == 0) goto L_0x009a;
    L_0x0093:
        r0 = (android.view.ViewGroup) r0;
        r4 = r11.h;
        r0.removeView(r4);
    L_0x009a:
        r0 = r11.g;
        r4 = r11.h;
        r0.addView(r4, r1);
        r0 = r11.h;
        r0 = r0.hasFocus();
        if (r0 != 0) goto L_0x00ae;
    L_0x00a9:
        r0 = r11.h;
        r0.requestFocus();
    L_0x00ae:
        r1 = r2;
    L_0x00af:
        r11.n = r3;
        r0 = new android.view.WindowManager$LayoutParams;
        r3 = r11.d;
        r4 = r11.e;
        r5 = 1002; // 0x3ea float:1.404E-42 double:4.95E-321;
        r6 = 8519680; // 0x820000 float:1.1938615E-38 double:4.209281E-317;
        r7 = -3;
        r0.<init>(r1, r2, r3, r4, r5, r6, r7);
        r1 = r11.c;
        r0.gravity = r1;
        r1 = r11.f;
        r0.windowAnimations = r1;
        r1 = r11.g;
        r8.addView(r1, r0);
        r11.o = r9;
        goto L_0x000e;
    L_0x00d0:
        r0 = r11.p;
        if (r0 == 0) goto L_0x0066;
    L_0x00d4:
        r0 = r11.g;
        r0 = r0.getChildCount();
        if (r0 <= 0) goto L_0x0066;
    L_0x00dc:
        r0 = r11.g;
        r0.removeAllViews();
        goto L_0x0066;
    L_0x00e2:
        r0 = r11.i;
        if (r0 == 0) goto L_0x00f2;
    L_0x00e6:
        r0 = r11.i;
        r0 = r0.getLayoutParams();
        if (r0 == 0) goto L_0x00f2;
    L_0x00ee:
        r0 = r0.width;
        if (r0 == r1) goto L_0x00af;
    L_0x00f2:
        r1 = r2;
        goto L_0x00af;
    L_0x00f4:
        r1 = r0;
        goto L_0x0080;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AppCompatDelegateImplV9.a(android.support.v7.app.AppCompatDelegateImplV9$PanelFeatureState, android.view.KeyEvent):void");
    }

    private boolean a(PanelFeatureState panelFeatureState) {
        panelFeatureState.a(b());
        panelFeatureState.g = new c(this, panelFeatureState.l);
        panelFeatureState.c = 81;
        return true;
    }

    private void a(MenuBuilder menuBuilder, boolean z) {
        if (this.u == null || !this.u.canShowOverflowMenu() || (ViewConfiguration.get(this.a).hasPermanentMenuKey() && !this.u.isOverflowMenuShowPending())) {
            PanelFeatureState a = a(0, true);
            a.p = true;
            a(a, false);
            a(a, null);
            return;
        }
        Window.Callback d = d();
        if (this.u.isOverflowMenuShowing() && z) {
            this.u.hideOverflowMenu();
            if (!c()) {
                d.onPanelClosed(108, a(0, true).j);
            }
        } else if (d != null && !c()) {
            if (this.r && (this.s & 1) != 0) {
                this.b.getDecorView().removeCallbacks(this.H);
                this.H.run();
            }
            PanelFeatureState a2 = a(0, true);
            if (a2.j != null && !a2.q && d.onPreparePanel(0, a2.i, a2.j)) {
                d.onMenuOpened(108, a2.j);
                this.u.showOverflowMenu();
            }
        }
    }

    private boolean b(PanelFeatureState panelFeatureState) {
        Context contextThemeWrapper;
        MenuBuilder menuBuilder;
        Context context = this.a;
        if ((panelFeatureState.a == 0 || panelFeatureState.a == 108) && this.u != null) {
            TypedValue typedValue = new TypedValue();
            Theme theme = context.getTheme();
            theme.resolveAttribute(R.attr.actionBarTheme, typedValue, true);
            Theme theme2 = null;
            if (typedValue.resourceId != 0) {
                theme2 = context.getResources().newTheme();
                theme2.setTo(theme);
                theme2.applyStyle(typedValue.resourceId, true);
                theme2.resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
            } else {
                theme.resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
            }
            if (typedValue.resourceId != 0) {
                if (theme2 == null) {
                    theme2 = context.getResources().newTheme();
                    theme2.setTo(theme);
                }
                theme2.applyStyle(typedValue.resourceId, true);
            }
            Theme theme3 = theme2;
            if (theme3 != null) {
                contextThemeWrapper = new ContextThemeWrapper(context, 0);
                contextThemeWrapper.getTheme().setTo(theme3);
                menuBuilder = new MenuBuilder(contextThemeWrapper);
                menuBuilder.setCallback(this);
                panelFeatureState.a(menuBuilder);
                return true;
            }
        }
        contextThemeWrapper = context;
        menuBuilder = new MenuBuilder(contextThemeWrapper);
        menuBuilder.setCallback(this);
        panelFeatureState.a(menuBuilder);
        return true;
    }

    private boolean c(PanelFeatureState panelFeatureState) {
        if (panelFeatureState.i != null) {
            panelFeatureState.h = panelFeatureState.i;
            return true;
        } else if (panelFeatureState.j == null) {
            return false;
        } else {
            if (this.w == null) {
                this.w = new d(this);
            }
            panelFeatureState.h = (View) panelFeatureState.a(this.w);
            return panelFeatureState.h != null;
        }
    }

    private boolean b(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        if (c()) {
            return false;
        }
        if (panelFeatureState.m) {
            return true;
        }
        if (!(this.F == null || this.F == panelFeatureState)) {
            a(this.F, false);
        }
        Window.Callback d = d();
        if (d != null) {
            panelFeatureState.i = d.onCreatePanelView(panelFeatureState.a);
        }
        boolean z = panelFeatureState.a == 0 || panelFeatureState.a == 108;
        if (z && this.u != null) {
            this.u.setMenuPrepared();
        }
        if (panelFeatureState.i == null && !(z && (a() instanceof ae))) {
            if (panelFeatureState.j == null || panelFeatureState.q) {
                if (panelFeatureState.j == null && (!b(panelFeatureState) || panelFeatureState.j == null)) {
                    return false;
                }
                if (z && this.u != null) {
                    if (this.v == null) {
                        this.v = new a(this);
                    }
                    this.u.setMenu(panelFeatureState.j, this.v);
                }
                panelFeatureState.j.stopDispatchingItemsChanged();
                if (d.onCreatePanelMenu(panelFeatureState.a, panelFeatureState.j)) {
                    panelFeatureState.q = false;
                } else {
                    panelFeatureState.a(null);
                    if (!z || this.u == null) {
                        return false;
                    }
                    this.u.setMenu(null, this.v);
                    return false;
                }
            }
            panelFeatureState.j.stopDispatchingItemsChanged();
            if (panelFeatureState.r != null) {
                panelFeatureState.j.restoreActionViewStates(panelFeatureState.r);
                panelFeatureState.r = null;
            }
            if (d.onPreparePanel(0, panelFeatureState.i, panelFeatureState.j)) {
                if (KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1) {
                    z = true;
                } else {
                    z = false;
                }
                panelFeatureState.qwertyMode = z;
                panelFeatureState.j.setQwertyMode(panelFeatureState.qwertyMode);
                panelFeatureState.j.startDispatchingItemsChanged();
            } else {
                if (z && this.u != null) {
                    this.u.setMenu(null, this.v);
                }
                panelFeatureState.j.startDispatchingItemsChanged();
                return false;
            }
        }
        panelFeatureState.m = true;
        panelFeatureState.n = false;
        this.F = panelFeatureState;
        return true;
    }

    void a(MenuBuilder menuBuilder) {
        if (!this.D) {
            this.D = true;
            this.u.dismissPopups();
            Window.Callback d = d();
            if (!(d == null || c())) {
                d.onPanelClosed(108, menuBuilder);
            }
            this.D = false;
        }
    }

    void b(int i) {
        a(a(i, true), true);
    }

    void a(PanelFeatureState panelFeatureState, boolean z) {
        if (z && panelFeatureState.a == 0 && this.u != null && this.u.isOverflowMenuShowing()) {
            a(panelFeatureState.j);
            return;
        }
        WindowManager windowManager = (WindowManager) this.a.getSystemService("window");
        if (!(windowManager == null || !panelFeatureState.o || panelFeatureState.g == null)) {
            windowManager.removeView(panelFeatureState.g);
            if (z) {
                a(panelFeatureState.a, panelFeatureState, null);
            }
        }
        panelFeatureState.m = false;
        panelFeatureState.n = false;
        panelFeatureState.o = false;
        panelFeatureState.h = null;
        panelFeatureState.p = true;
        if (this.F == panelFeatureState) {
            this.F = null;
        }
    }

    private boolean d(int i, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() == 0) {
            PanelFeatureState a = a(i, true);
            if (!a.o) {
                return b(a, keyEvent);
            }
        }
        return false;
    }

    private boolean e(int i, KeyEvent keyEvent) {
        boolean z = true;
        if (this.m != null) {
            return false;
        }
        PanelFeatureState a = a(i, true);
        if (i != 0 || this.u == null || !this.u.canShowOverflowMenu() || ViewConfiguration.get(this.a).hasPermanentMenuKey()) {
            boolean z2;
            if (a.o || a.n) {
                z2 = a.o;
                a(a, true);
                z = z2;
            } else {
                if (a.m) {
                    if (a.q) {
                        a.m = false;
                        z2 = b(a, keyEvent);
                    } else {
                        z2 = true;
                    }
                    if (z2) {
                        a(a, keyEvent);
                    }
                }
                z = false;
            }
        } else if (this.u.isOverflowMenuShowing()) {
            z = this.u.hideOverflowMenu();
        } else {
            if (!c() && b(a, keyEvent)) {
                z = this.u.showOverflowMenu();
            }
            z = false;
        }
        if (z) {
            AudioManager audioManager = (AudioManager) this.a.getSystemService("audio");
            if (audioManager != null) {
                audioManager.playSoundEffect(0);
            } else {
                Log.w("AppCompatDelegate", "Couldn't get audio manager");
            }
        }
        return z;
    }

    void a(int i, PanelFeatureState panelFeatureState, Menu menu) {
        if (menu == null) {
            if (panelFeatureState == null && i >= 0 && i < this.E.length) {
                panelFeatureState = this.E[i];
            }
            if (panelFeatureState != null) {
                menu = panelFeatureState.j;
            }
        }
        if ((panelFeatureState == null || panelFeatureState.o) && !c()) {
            this.c.onPanelClosed(i, menu);
        }
    }

    PanelFeatureState a(Menu menu) {
        PanelFeatureState[] panelFeatureStateArr = this.E;
        int length = panelFeatureStateArr != null ? panelFeatureStateArr.length : 0;
        for (int i = 0; i < length; i++) {
            PanelFeatureState panelFeatureState = panelFeatureStateArr[i];
            if (panelFeatureState != null && panelFeatureState.j == menu) {
                return panelFeatureState;
            }
        }
        return null;
    }

    protected PanelFeatureState a(int i, boolean z) {
        Object obj = this.E;
        if (obj == null || obj.length <= i) {
            Object obj2 = new PanelFeatureState[(i + 1)];
            if (obj != null) {
                System.arraycopy(obj, 0, obj2, 0, obj.length);
            }
            this.E = obj2;
            obj = obj2;
        }
        PanelFeatureState panelFeatureState = obj[i];
        if (panelFeatureState != null) {
            return panelFeatureState;
        }
        panelFeatureState = new PanelFeatureState(i);
        obj[i] = panelFeatureState;
        return panelFeatureState;
    }

    private boolean a(PanelFeatureState panelFeatureState, int i, KeyEvent keyEvent, int i2) {
        boolean z = false;
        if (!keyEvent.isSystem()) {
            if ((panelFeatureState.m || b(panelFeatureState, keyEvent)) && panelFeatureState.j != null) {
                z = panelFeatureState.j.performShortcut(i, keyEvent, i2);
            }
            if (z && (i2 & 1) == 0 && this.u == null) {
                a(panelFeatureState, true);
            }
        }
        return z;
    }

    private void a(int i) {
        this.s |= 1 << i;
        if (!this.r) {
            ViewCompat.postOnAnimation(this.b.getDecorView(), this.H);
            this.r = true;
        }
    }

    void c(int i) {
        PanelFeatureState a = a(i, true);
        if (a.j != null) {
            Bundle bundle = new Bundle();
            a.j.saveActionViewStates(bundle);
            if (bundle.size() > 0) {
                a.r = bundle;
            }
            a.j.stopDispatchingItemsChanged();
            a.j.clear();
        }
        a.q = true;
        a.p = true;
        if ((i == 108 || i == 0) && this.u != null) {
            a = a(0, false);
            if (a != null) {
                a.m = false;
                b(a, null);
            }
        }
    }

    int d(int i) {
        int i2;
        int i3 = 1;
        int i4 = 0;
        if (this.n == null || !(this.n.getLayoutParams() instanceof MarginLayoutParams)) {
            i2 = 0;
        } else {
            int i5;
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.n.getLayoutParams();
            if (this.n.isShown()) {
                if (this.J == null) {
                    this.J = new Rect();
                    this.K = new Rect();
                }
                Rect rect = this.J;
                Rect rect2 = this.K;
                rect.set(0, i, 0, 0);
                ViewUtils.computeFitSystemWindows(this.y, rect, rect2);
                if (marginLayoutParams.topMargin != (rect2.top == 0 ? i : 0)) {
                    marginLayoutParams.topMargin = i;
                    if (this.A == null) {
                        this.A = new View(this.a);
                        this.A.setBackgroundColor(this.a.getResources().getColor(R.color.abc_input_method_navigation_guard));
                        this.y.addView(this.A, -1, new LayoutParams(-1, i));
                        i5 = 1;
                    } else {
                        LayoutParams layoutParams = this.A.getLayoutParams();
                        if (layoutParams.height != i) {
                            layoutParams.height = i;
                            this.A.setLayoutParams(layoutParams);
                        }
                        i5 = 1;
                    }
                } else {
                    i5 = 0;
                }
                if (this.A == null) {
                    i3 = 0;
                }
                if (!(this.j || i3 == 0)) {
                    i = 0;
                }
                int i6 = i5;
                i5 = i3;
                i3 = i6;
            } else if (marginLayoutParams.topMargin != 0) {
                marginLayoutParams.topMargin = 0;
                i5 = 0;
            } else {
                i3 = 0;
                i5 = 0;
            }
            if (i3 != 0) {
                this.n.setLayoutParams(marginLayoutParams);
            }
            i2 = i5;
        }
        if (this.A != null) {
            View view = this.A;
            if (i2 == 0) {
                i4 = 8;
            }
            view.setVisibility(i4);
        }
        return i;
    }

    private void m() {
        if (this.x) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    private int e(int i) {
        if (i == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            return 108;
        } else if (i != 9) {
            return i;
        } else {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            return 109;
        }
    }

    void i() {
        if (this.u != null) {
            this.u.dismissPopups();
        }
        if (this.o != null) {
            this.b.getDecorView().removeCallbacks(this.p);
            if (this.o.isShowing()) {
                try {
                    this.o.dismiss();
                } catch (IllegalArgumentException e) {
                }
            }
            this.o = null;
        }
        g();
        PanelFeatureState a = a(0, false);
        if (a != null && a.j != null) {
            a.j.close();
        }
    }
}
