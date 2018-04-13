package android.support.v4.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.InsetDrawable;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.lang.reflect.Method;

@Deprecated
public class ActionBarDrawerToggle implements DrawerListener {
    private static final int[] b = new int[]{16843531};
    final Activity a;
    private final Delegate c;
    private final DrawerLayout d;
    private boolean e;
    private boolean f;
    private Drawable g;
    private Drawable h;
    private b i;
    private final int j;
    private final int k;
    private final int l;
    private a m;

    @Deprecated
    public interface Delegate {
        @Nullable
        Drawable getThemeUpIndicator();

        void setActionBarDescription(@StringRes int i);

        void setActionBarUpIndicator(Drawable drawable, @StringRes int i);
    }

    @Deprecated
    public interface DelegateProvider {
        @Nullable
        Delegate getDrawerToggleDelegate();
    }

    private static class a {
        Method a;
        Method b;
        ImageView c;

        a(Activity activity) {
            try {
                this.a = ActionBar.class.getDeclaredMethod("setHomeAsUpIndicator", new Class[]{Drawable.class});
                this.b = ActionBar.class.getDeclaredMethod("setHomeActionContentDescription", new Class[]{Integer.TYPE});
            } catch (NoSuchMethodException e) {
                View findViewById = activity.findViewById(16908332);
                if (findViewById != null) {
                    ViewGroup viewGroup = (ViewGroup) findViewById.getParent();
                    if (viewGroup.getChildCount() == 2) {
                        View childAt = viewGroup.getChildAt(0);
                        findViewById = viewGroup.getChildAt(1);
                        if (childAt.getId() != 16908332) {
                            findViewById = childAt;
                        }
                        if (findViewById instanceof ImageView) {
                            this.c = (ImageView) findViewById;
                        }
                    }
                }
            }
        }
    }

    private class b extends InsetDrawable implements Callback {
        final /* synthetic */ ActionBarDrawerToggle a;
        private final boolean b;
        private final Rect c;
        private float d;
        private float e;

        b(ActionBarDrawerToggle actionBarDrawerToggle, Drawable drawable) {
            boolean z = false;
            this.a = actionBarDrawerToggle;
            super(drawable, 0);
            if (VERSION.SDK_INT > 18) {
                z = true;
            }
            this.b = z;
            this.c = new Rect();
        }

        public void setPosition(float f) {
            this.d = f;
            invalidateSelf();
        }

        public float getPosition() {
            return this.d;
        }

        public void setOffset(float f) {
            this.e = f;
            invalidateSelf();
        }

        public void draw(@NonNull Canvas canvas) {
            int i = 1;
            copyBounds(this.c);
            canvas.save();
            int i2 = ViewCompat.getLayoutDirection(this.a.a.getWindow().getDecorView()) == 1 ? 1 : 0;
            if (i2 != 0) {
                i = -1;
            }
            int width = this.c.width();
            canvas.translate(((float) i) * (((-this.e) * ((float) width)) * this.d), 0.0f);
            if (!(i2 == 0 || this.b)) {
                canvas.translate((float) width, 0.0f);
                canvas.scale(-1.0f, 1.0f);
            }
            super.draw(canvas);
            canvas.restore();
        }
    }

    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, @DrawableRes int i, @StringRes int i2, @StringRes int i3) {
        this(activity, drawerLayout, !a((Context) activity), i, i2, i3);
    }

    private static boolean a(Context context) {
        return context.getApplicationInfo().targetSdkVersion >= 21 && VERSION.SDK_INT >= 21;
    }

    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, boolean z, @DrawableRes int i, @StringRes int i2, @StringRes int i3) {
        this.e = true;
        this.a = activity;
        if (activity instanceof DelegateProvider) {
            this.c = ((DelegateProvider) activity).getDrawerToggleDelegate();
        } else {
            this.c = null;
        }
        this.d = drawerLayout;
        this.j = i;
        this.k = i2;
        this.l = i3;
        this.g = a();
        this.h = ContextCompat.getDrawable(activity, i);
        this.i = new b(this, this.h);
        this.i.setOffset(z ? 0.33333334f : 0.0f);
    }

    public void syncState() {
        if (this.d.isDrawerOpen((int) GravityCompat.START)) {
            this.i.setPosition(1.0f);
        } else {
            this.i.setPosition(0.0f);
        }
        if (this.e) {
            a(this.i, this.d.isDrawerOpen((int) GravityCompat.START) ? this.l : this.k);
        }
    }

    public void setHomeAsUpIndicator(Drawable drawable) {
        if (drawable == null) {
            this.g = a();
            this.f = false;
        } else {
            this.g = drawable;
            this.f = true;
        }
        if (!this.e) {
            a(this.g, 0);
        }
    }

    public void setHomeAsUpIndicator(int i) {
        Drawable drawable = null;
        if (i != 0) {
            drawable = ContextCompat.getDrawable(this.a, i);
        }
        setHomeAsUpIndicator(drawable);
    }

    public void setDrawerIndicatorEnabled(boolean z) {
        if (z != this.e) {
            if (z) {
                a(this.i, this.d.isDrawerOpen((int) GravityCompat.START) ? this.l : this.k);
            } else {
                a(this.g, 0);
            }
            this.e = z;
        }
    }

    public boolean isDrawerIndicatorEnabled() {
        return this.e;
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (!this.f) {
            this.g = a();
        }
        this.h = ContextCompat.getDrawable(this.a, this.j);
        syncState();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem == null || menuItem.getItemId() != 16908332 || !this.e) {
            return false;
        }
        if (this.d.isDrawerVisible((int) GravityCompat.START)) {
            this.d.closeDrawer((int) GravityCompat.START);
        } else {
            this.d.openDrawer((int) GravityCompat.START);
        }
        return true;
    }

    public void onDrawerSlide(View view, float f) {
        float position = this.i.getPosition();
        if (f > 0.5f) {
            position = Math.max(position, Math.max(0.0f, f - 0.5f) * 2.0f);
        } else {
            position = Math.min(position, f * 2.0f);
        }
        this.i.setPosition(position);
    }

    public void onDrawerOpened(View view) {
        this.i.setPosition(1.0f);
        if (this.e) {
            a(this.l);
        }
    }

    public void onDrawerClosed(View view) {
        this.i.setPosition(0.0f);
        if (this.e) {
            a(this.k);
        }
    }

    public void onDrawerStateChanged(int i) {
    }

    private Drawable a() {
        if (this.c != null) {
            return this.c.getThemeUpIndicator();
        }
        if (VERSION.SDK_INT >= 18) {
            Context themedContext;
            ActionBar actionBar = this.a.getActionBar();
            if (actionBar != null) {
                themedContext = actionBar.getThemedContext();
            } else {
                themedContext = this.a;
            }
            TypedArray obtainStyledAttributes = themedContext.obtainStyledAttributes(null, b, 16843470, 0);
            Drawable drawable = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            return drawable;
        }
        obtainStyledAttributes = this.a.obtainStyledAttributes(b);
        drawable = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        return drawable;
    }

    private void a(Drawable drawable, int i) {
        if (this.c != null) {
            this.c.setActionBarUpIndicator(drawable, i);
        } else if (VERSION.SDK_INT >= 18) {
            r0 = this.a.getActionBar();
            if (r0 != null) {
                r0.setHomeAsUpIndicator(drawable);
                r0.setHomeActionContentDescription(i);
            }
        } else {
            if (this.m == null) {
                this.m = new a(this.a);
            }
            if (this.m.a != null) {
                try {
                    r0 = this.a.getActionBar();
                    this.m.a.invoke(r0, new Object[]{drawable});
                    this.m.b.invoke(r0, new Object[]{Integer.valueOf(i)});
                } catch (Throwable e) {
                    Log.w("ActionBarDrawerToggle", "Couldn't set home-as-up indicator via JB-MR2 API", e);
                }
            } else if (this.m.c != null) {
                this.m.c.setImageDrawable(drawable);
            } else {
                Log.w("ActionBarDrawerToggle", "Couldn't set home-as-up indicator");
            }
        }
    }

    private void a(int i) {
        if (this.c != null) {
            this.c.setActionBarDescription(i);
        } else if (VERSION.SDK_INT >= 18) {
            r0 = this.a.getActionBar();
            if (r0 != null) {
                r0.setHomeActionContentDescription(i);
            }
        } else {
            if (this.m == null) {
                this.m = new a(this.a);
            }
            if (this.m.a != null) {
                try {
                    r0 = this.a.getActionBar();
                    this.m.b.invoke(r0, new Object[]{Integer.valueOf(i)});
                    r0.setSubtitle(r0.getSubtitle());
                } catch (Throwable e) {
                    Log.w("ActionBarDrawerToggle", "Couldn't set content description via JB-MR2 API", e);
                }
            }
        }
    }
}
