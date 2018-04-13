package android.support.v7.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class ActionBarDrawerToggle implements DrawerListener {
    boolean a;
    OnClickListener b;
    private final Delegate c;
    private final DrawerLayout d;
    private DrawerArrowDrawable e;
    private boolean f;
    private Drawable g;
    private boolean h;
    private final int i;
    private final int j;
    private boolean k;

    public interface Delegate {
        Context getActionBarThemedContext();

        Drawable getThemeUpIndicator();

        boolean isNavigationVisible();

        void setActionBarDescription(@StringRes int i);

        void setActionBarUpIndicator(Drawable drawable, @StringRes int i);
    }

    public interface DelegateProvider {
        @Nullable
        Delegate getDrawerToggleDelegate();
    }

    private static class a implements Delegate {
        final Activity a;
        a b;

        a(Activity activity) {
            this.a = activity;
        }

        public Drawable getThemeUpIndicator() {
            return b.getThemeUpIndicator(this.a);
        }

        public Context getActionBarThemedContext() {
            ActionBar actionBar = this.a.getActionBar();
            if (actionBar != null) {
                return actionBar.getThemedContext();
            }
            return this.a;
        }

        public boolean isNavigationVisible() {
            ActionBar actionBar = this.a.getActionBar();
            return (actionBar == null || (actionBar.getDisplayOptions() & 4) == 0) ? false : true;
        }

        public void setActionBarUpIndicator(Drawable drawable, int i) {
            ActionBar actionBar = this.a.getActionBar();
            if (actionBar != null) {
                actionBar.setDisplayShowHomeEnabled(true);
                this.b = b.setActionBarUpIndicator(this.b, this.a, drawable, i);
                actionBar.setDisplayShowHomeEnabled(false);
            }
        }

        public void setActionBarDescription(int i) {
            this.b = b.setActionBarDescription(this.b, this.a, i);
        }
    }

    @RequiresApi(18)
    private static class b implements Delegate {
        final Activity a;

        b(Activity activity) {
            this.a = activity;
        }

        public Drawable getThemeUpIndicator() {
            TypedArray obtainStyledAttributes = getActionBarThemedContext().obtainStyledAttributes(null, new int[]{16843531}, 16843470, 0);
            Drawable drawable = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            return drawable;
        }

        public Context getActionBarThemedContext() {
            ActionBar actionBar = this.a.getActionBar();
            if (actionBar != null) {
                return actionBar.getThemedContext();
            }
            return this.a;
        }

        public boolean isNavigationVisible() {
            ActionBar actionBar = this.a.getActionBar();
            return (actionBar == null || (actionBar.getDisplayOptions() & 4) == 0) ? false : true;
        }

        public void setActionBarUpIndicator(Drawable drawable, int i) {
            ActionBar actionBar = this.a.getActionBar();
            if (actionBar != null) {
                actionBar.setHomeAsUpIndicator(drawable);
                actionBar.setHomeActionContentDescription(i);
            }
        }

        public void setActionBarDescription(int i) {
            ActionBar actionBar = this.a.getActionBar();
            if (actionBar != null) {
                actionBar.setHomeActionContentDescription(i);
            }
        }
    }

    static class c implements Delegate {
        final Toolbar a;
        final Drawable b;
        final CharSequence c;

        c(Toolbar toolbar) {
            this.a = toolbar;
            this.b = toolbar.getNavigationIcon();
            this.c = toolbar.getNavigationContentDescription();
        }

        public void setActionBarUpIndicator(Drawable drawable, @StringRes int i) {
            this.a.setNavigationIcon(drawable);
            setActionBarDescription(i);
        }

        public void setActionBarDescription(@StringRes int i) {
            if (i == 0) {
                this.a.setNavigationContentDescription(this.c);
            } else {
                this.a.setNavigationContentDescription(i);
            }
        }

        public Drawable getThemeUpIndicator() {
            return this.b;
        }

        public Context getActionBarThemedContext() {
            return this.a.getContext();
        }

        public boolean isNavigationVisible() {
            return true;
        }
    }

    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, @StringRes int i, @StringRes int i2) {
        this(activity, null, drawerLayout, null, i, i2);
    }

    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, @StringRes int i, @StringRes int i2) {
        this(activity, toolbar, drawerLayout, null, i, i2);
    }

    ActionBarDrawerToggle(Activity activity, Toolbar toolbar, DrawerLayout drawerLayout, DrawerArrowDrawable drawerArrowDrawable, @StringRes int i, @StringRes int i2) {
        this.f = true;
        this.a = true;
        this.k = false;
        if (toolbar != null) {
            this.c = new c(toolbar);
            toolbar.setNavigationOnClickListener(new a(this));
        } else if (activity instanceof DelegateProvider) {
            this.c = ((DelegateProvider) activity).getDrawerToggleDelegate();
        } else if (VERSION.SDK_INT >= 18) {
            this.c = new b(activity);
        } else {
            this.c = new a(activity);
        }
        this.d = drawerLayout;
        this.i = i;
        this.j = i2;
        if (drawerArrowDrawable == null) {
            this.e = new DrawerArrowDrawable(this.c.getActionBarThemedContext());
        } else {
            this.e = drawerArrowDrawable;
        }
        this.g = b();
    }

    public void syncState() {
        if (this.d.isDrawerOpen((int) GravityCompat.START)) {
            a(1.0f);
        } else {
            a(0.0f);
        }
        if (this.a) {
            a(this.e, this.d.isDrawerOpen((int) GravityCompat.START) ? this.j : this.i);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (!this.h) {
            this.g = b();
        }
        syncState();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem == null || menuItem.getItemId() != 16908332 || !this.a) {
            return false;
        }
        a();
        return true;
    }

    void a() {
        int drawerLockMode = this.d.getDrawerLockMode((int) GravityCompat.START);
        if (this.d.isDrawerVisible((int) GravityCompat.START) && drawerLockMode != 2) {
            this.d.closeDrawer((int) GravityCompat.START);
        } else if (drawerLockMode != 1) {
            this.d.openDrawer((int) GravityCompat.START);
        }
    }

    public void setHomeAsUpIndicator(Drawable drawable) {
        if (drawable == null) {
            this.g = b();
            this.h = false;
        } else {
            this.g = drawable;
            this.h = true;
        }
        if (!this.a) {
            a(this.g, 0);
        }
    }

    public void setHomeAsUpIndicator(int i) {
        Drawable drawable = null;
        if (i != 0) {
            drawable = this.d.getResources().getDrawable(i);
        }
        setHomeAsUpIndicator(drawable);
    }

    public boolean isDrawerIndicatorEnabled() {
        return this.a;
    }

    public void setDrawerIndicatorEnabled(boolean z) {
        if (z != this.a) {
            if (z) {
                a(this.e, this.d.isDrawerOpen((int) GravityCompat.START) ? this.j : this.i);
            } else {
                a(this.g, 0);
            }
            this.a = z;
        }
    }

    @NonNull
    public DrawerArrowDrawable getDrawerArrowDrawable() {
        return this.e;
    }

    public void setDrawerArrowDrawable(@NonNull DrawerArrowDrawable drawerArrowDrawable) {
        this.e = drawerArrowDrawable;
        syncState();
    }

    public void setDrawerSlideAnimationEnabled(boolean z) {
        this.f = z;
        if (!z) {
            a(0.0f);
        }
    }

    public boolean isDrawerSlideAnimationEnabled() {
        return this.f;
    }

    public void onDrawerSlide(View view, float f) {
        if (this.f) {
            a(Math.min(1.0f, Math.max(0.0f, f)));
        } else {
            a(0.0f);
        }
    }

    public void onDrawerOpened(View view) {
        a(1.0f);
        if (this.a) {
            a(this.j);
        }
    }

    public void onDrawerClosed(View view) {
        a(0.0f);
        if (this.a) {
            a(this.i);
        }
    }

    public void onDrawerStateChanged(int i) {
    }

    public OnClickListener getToolbarNavigationClickListener() {
        return this.b;
    }

    public void setToolbarNavigationClickListener(OnClickListener onClickListener) {
        this.b = onClickListener;
    }

    void a(Drawable drawable, int i) {
        if (!(this.k || this.c.isNavigationVisible())) {
            Log.w("ActionBarDrawerToggle", "DrawerToggle may not show up because NavigationIcon is not visible. You may need to call actionbar.setDisplayHomeAsUpEnabled(true);");
            this.k = true;
        }
        this.c.setActionBarUpIndicator(drawable, i);
    }

    void a(int i) {
        this.c.setActionBarDescription(i);
    }

    Drawable b() {
        return this.c.getThemeUpIndicator();
    }

    private void a(float f) {
        if (f == 1.0f) {
            this.e.setVerticalMirror(true);
        } else if (f == 0.0f) {
            this.e.setVerticalMirror(false);
        }
        this.e.setProgress(f);
    }
}
