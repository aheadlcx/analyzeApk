package android.support.v7.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBarDrawerToggle.Delegate;
import android.support.v7.appcompat.R;
import android.support.v7.view.ActionMode;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.WindowCallbackWrapper;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.TintTypedArray;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.Window.Callback;

@RequiresApi(14)
abstract class l extends AppCompatDelegate {
    private static boolean m = true;
    private static final boolean n = (VERSION.SDK_INT < 21);
    private static final int[] o = new int[]{16842836};
    final Context a;
    final Window b;
    final Callback c = this.b.getCallback();
    final Callback d;
    final AppCompatCallback e;
    ActionBar f;
    MenuInflater g;
    boolean h;
    boolean i;
    boolean j;
    boolean k;
    boolean l;
    private CharSequence p;
    private boolean q;
    private boolean r;

    private class a implements Delegate {
        final /* synthetic */ l a;

        a(l lVar) {
            this.a = lVar;
        }

        public Drawable getThemeUpIndicator() {
            TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(getActionBarThemedContext(), null, new int[]{R.attr.homeAsUpIndicator});
            Drawable drawable = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            return drawable;
        }

        public Context getActionBarThemedContext() {
            return this.a.b();
        }

        public boolean isNavigationVisible() {
            ActionBar supportActionBar = this.a.getSupportActionBar();
            return (supportActionBar == null || (supportActionBar.getDisplayOptions() & 4) == 0) ? false : true;
        }

        public void setActionBarUpIndicator(Drawable drawable, int i) {
            ActionBar supportActionBar = this.a.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setHomeAsUpIndicator(drawable);
                supportActionBar.setHomeActionContentDescription(i);
            }
        }

        public void setActionBarDescription(int i) {
            ActionBar supportActionBar = this.a.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setHomeActionContentDescription(i);
            }
        }
    }

    class b extends WindowCallbackWrapper {
        final /* synthetic */ l a;

        b(l lVar, Callback callback) {
            this.a = lVar;
            super(callback);
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return this.a.a(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || this.a.a(keyEvent.getKeyCode(), keyEvent);
        }

        public boolean onCreatePanelMenu(int i, Menu menu) {
            if (i != 0 || (menu instanceof MenuBuilder)) {
                return super.onCreatePanelMenu(i, menu);
            }
            return false;
        }

        public void onContentChanged() {
        }

        public boolean onPreparePanel(int i, View view, Menu menu) {
            MenuBuilder menuBuilder;
            if (menu instanceof MenuBuilder) {
                menuBuilder = (MenuBuilder) menu;
            } else {
                menuBuilder = null;
            }
            if (i == 0 && menuBuilder == null) {
                return false;
            }
            if (menuBuilder != null) {
                menuBuilder.setOverrideVisibleItems(true);
            }
            boolean onPreparePanel = super.onPreparePanel(i, view, menu);
            if (menuBuilder == null) {
                return onPreparePanel;
            }
            menuBuilder.setOverrideVisibleItems(false);
            return onPreparePanel;
        }

        public boolean onMenuOpened(int i, Menu menu) {
            super.onMenuOpened(i, menu);
            this.a.b(i, menu);
            return true;
        }

        public void onPanelClosed(int i, Menu menu) {
            super.onPanelClosed(i, menu);
            this.a.a(i, menu);
        }
    }

    abstract ActionMode a(ActionMode.Callback callback);

    abstract void a(int i, Menu menu);

    abstract void a(CharSequence charSequence);

    abstract boolean a(int i, KeyEvent keyEvent);

    abstract boolean a(KeyEvent keyEvent);

    abstract boolean b(int i, Menu menu);

    abstract void initWindowDecorActionBar();

    static {
        if (n && !m) {
            Thread.setDefaultUncaughtExceptionHandler(new m(Thread.getDefaultUncaughtExceptionHandler()));
        }
    }

    l(Context context, Window window, AppCompatCallback appCompatCallback) {
        this.a = context;
        this.b = window;
        this.e = appCompatCallback;
        if (this.c instanceof b) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        this.d = a(this.c);
        this.b.setCallback(this.d);
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, null, o);
        Drawable drawableIfKnown = obtainStyledAttributes.getDrawableIfKnown(0);
        if (drawableIfKnown != null) {
            this.b.setBackgroundDrawable(drawableIfKnown);
        }
        obtainStyledAttributes.recycle();
    }

    Callback a(Callback callback) {
        return new b(this, callback);
    }

    public ActionBar getSupportActionBar() {
        initWindowDecorActionBar();
        return this.f;
    }

    final ActionBar a() {
        return this.f;
    }

    public MenuInflater getMenuInflater() {
        if (this.g == null) {
            initWindowDecorActionBar();
            this.g = new SupportMenuInflater(this.f != null ? this.f.getThemedContext() : this.a);
        }
        return this.g;
    }

    public void setLocalNightMode(int i) {
    }

    public final Delegate getDrawerToggleDelegate() {
        return new a(this);
    }

    final Context b() {
        Context context = null;
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            context = supportActionBar.getThemedContext();
        }
        if (context == null) {
            return this.a;
        }
        return context;
    }

    public void onStart() {
        this.q = true;
    }

    public void onStop() {
        this.q = false;
    }

    public void onDestroy() {
        this.r = true;
    }

    public void setHandleNativeActionModesEnabled(boolean z) {
    }

    public boolean isHandleNativeActionModesEnabled() {
        return false;
    }

    public boolean applyDayNight() {
        return false;
    }

    final boolean c() {
        return this.r;
    }

    final Callback d() {
        return this.b.getCallback();
    }

    public final void setTitle(CharSequence charSequence) {
        this.p = charSequence;
        a(charSequence);
    }

    public void onSaveInstanceState(Bundle bundle) {
    }

    final CharSequence e() {
        if (this.c instanceof Activity) {
            return ((Activity) this.c).getTitle();
        }
        return this.p;
    }
}
