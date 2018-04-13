package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.appcompat.R;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPresenter;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window.Callback;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ToolbarWidgetWrapper implements DecorToolbar {
    Toolbar a;
    CharSequence b;
    Callback c;
    boolean d;
    private int e;
    private View f;
    private Spinner g;
    private View h;
    private Drawable i;
    private Drawable j;
    private Drawable k;
    private boolean l;
    private CharSequence m;
    private CharSequence n;
    private ActionMenuPresenter o;
    private int p;
    private int q;
    private Drawable r;

    public ToolbarWidgetWrapper(Toolbar toolbar, boolean z) {
        this(toolbar, z, R.string.abc_action_bar_up_description, R.drawable.abc_ic_ab_back_material);
    }

    public ToolbarWidgetWrapper(Toolbar toolbar, boolean z, int i, int i2) {
        this.p = 0;
        this.q = 0;
        this.a = toolbar;
        this.b = toolbar.getTitle();
        this.m = toolbar.getSubtitle();
        this.l = this.b != null;
        this.k = toolbar.getNavigationIcon();
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(toolbar.getContext(), null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
        this.r = obtainStyledAttributes.getDrawable(R.styleable.ActionBar_homeAsUpIndicator);
        if (z) {
            CharSequence text = obtainStyledAttributes.getText(R.styleable.ActionBar_title);
            if (!TextUtils.isEmpty(text)) {
                setTitle(text);
            }
            text = obtainStyledAttributes.getText(R.styleable.ActionBar_subtitle);
            if (!TextUtils.isEmpty(text)) {
                setSubtitle(text);
            }
            Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.ActionBar_logo);
            if (drawable != null) {
                setLogo(drawable);
            }
            drawable = obtainStyledAttributes.getDrawable(R.styleable.ActionBar_icon);
            if (drawable != null) {
                setIcon(drawable);
            }
            if (this.k == null && this.r != null) {
                setNavigationIcon(this.r);
            }
            setDisplayOptions(obtainStyledAttributes.getInt(R.styleable.ActionBar_displayOptions, 0));
            int resourceId = obtainStyledAttributes.getResourceId(R.styleable.ActionBar_customNavigationLayout, 0);
            if (resourceId != 0) {
                setCustomView(LayoutInflater.from(this.a.getContext()).inflate(resourceId, this.a, false));
                setDisplayOptions(this.e | 16);
            }
            resourceId = obtainStyledAttributes.getLayoutDimension(R.styleable.ActionBar_height, 0);
            if (resourceId > 0) {
                LayoutParams layoutParams = this.a.getLayoutParams();
                layoutParams.height = resourceId;
                this.a.setLayoutParams(layoutParams);
            }
            resourceId = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.ActionBar_contentInsetStart, -1);
            int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.ActionBar_contentInsetEnd, -1);
            if (resourceId >= 0 || dimensionPixelOffset >= 0) {
                this.a.setContentInsetsRelative(Math.max(resourceId, 0), Math.max(dimensionPixelOffset, 0));
            }
            resourceId = obtainStyledAttributes.getResourceId(R.styleable.ActionBar_titleTextStyle, 0);
            if (resourceId != 0) {
                this.a.setTitleTextAppearance(this.a.getContext(), resourceId);
            }
            resourceId = obtainStyledAttributes.getResourceId(R.styleable.ActionBar_subtitleTextStyle, 0);
            if (resourceId != 0) {
                this.a.setSubtitleTextAppearance(this.a.getContext(), resourceId);
            }
            int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.ActionBar_popupTheme, 0);
            if (resourceId2 != 0) {
                this.a.setPopupTheme(resourceId2);
            }
        } else {
            this.e = a();
        }
        obtainStyledAttributes.recycle();
        setDefaultNavigationContentDescription(i);
        this.n = this.a.getNavigationContentDescription();
        this.a.setNavigationOnClickListener(new cz(this));
    }

    public void setDefaultNavigationContentDescription(int i) {
        if (i != this.q) {
            this.q = i;
            if (TextUtils.isEmpty(this.a.getNavigationContentDescription())) {
                setNavigationContentDescription(this.q);
            }
        }
    }

    private int a() {
        if (this.a.getNavigationIcon() == null) {
            return 11;
        }
        this.r = this.a.getNavigationIcon();
        return 15;
    }

    public ViewGroup getViewGroup() {
        return this.a;
    }

    public Context getContext() {
        return this.a.getContext();
    }

    public boolean hasExpandedActionView() {
        return this.a.hasExpandedActionView();
    }

    public void collapseActionView() {
        this.a.collapseActionView();
    }

    public void setWindowCallback(Callback callback) {
        this.c = callback;
    }

    public void setWindowTitle(CharSequence charSequence) {
        if (!this.l) {
            a(charSequence);
        }
    }

    public CharSequence getTitle() {
        return this.a.getTitle();
    }

    public void setTitle(CharSequence charSequence) {
        this.l = true;
        a(charSequence);
    }

    private void a(CharSequence charSequence) {
        this.b = charSequence;
        if ((this.e & 8) != 0) {
            this.a.setTitle(charSequence);
        }
    }

    public CharSequence getSubtitle() {
        return this.a.getSubtitle();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.m = charSequence;
        if ((this.e & 8) != 0) {
            this.a.setSubtitle(charSequence);
        }
    }

    public void initProgress() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    public void initIndeterminateProgress() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    public boolean hasIcon() {
        return this.i != null;
    }

    public boolean hasLogo() {
        return this.j != null;
    }

    public void setIcon(int i) {
        setIcon(i != 0 ? AppCompatResources.getDrawable(getContext(), i) : null);
    }

    public void setIcon(Drawable drawable) {
        this.i = drawable;
        b();
    }

    public void setLogo(int i) {
        setLogo(i != 0 ? AppCompatResources.getDrawable(getContext(), i) : null);
    }

    public void setLogo(Drawable drawable) {
        this.j = drawable;
        b();
    }

    private void b() {
        Drawable drawable = null;
        if ((this.e & 2) != 0) {
            drawable = (this.e & 1) != 0 ? this.j != null ? this.j : this.i : this.i;
        }
        this.a.setLogo(drawable);
    }

    public boolean canShowOverflowMenu() {
        return this.a.canShowOverflowMenu();
    }

    public boolean isOverflowMenuShowing() {
        return this.a.isOverflowMenuShowing();
    }

    public boolean isOverflowMenuShowPending() {
        return this.a.isOverflowMenuShowPending();
    }

    public boolean showOverflowMenu() {
        return this.a.showOverflowMenu();
    }

    public boolean hideOverflowMenu() {
        return this.a.hideOverflowMenu();
    }

    public void setMenuPrepared() {
        this.d = true;
    }

    public void setMenu(Menu menu, MenuPresenter.Callback callback) {
        if (this.o == null) {
            this.o = new ActionMenuPresenter(this.a.getContext());
            this.o.setId(R.id.action_menu_presenter);
        }
        this.o.setCallback(callback);
        this.a.setMenu((MenuBuilder) menu, this.o);
    }

    public void dismissPopupMenus() {
        this.a.dismissPopupMenus();
    }

    public int getDisplayOptions() {
        return this.e;
    }

    public void setDisplayOptions(int i) {
        int i2 = this.e ^ i;
        this.e = i;
        if (i2 != 0) {
            if ((i2 & 4) != 0) {
                if ((i & 4) != 0) {
                    e();
                }
                d();
            }
            if ((i2 & 3) != 0) {
                b();
            }
            if ((i2 & 8) != 0) {
                if ((i & 8) != 0) {
                    this.a.setTitle(this.b);
                    this.a.setSubtitle(this.m);
                } else {
                    this.a.setTitle(null);
                    this.a.setSubtitle(null);
                }
            }
            if ((i2 & 16) != 0 && this.h != null) {
                if ((i & 16) != 0) {
                    this.a.addView(this.h);
                } else {
                    this.a.removeView(this.h);
                }
            }
        }
    }

    public void setEmbeddedTabView(ScrollingTabContainerView scrollingTabContainerView) {
        if (this.f != null && this.f.getParent() == this.a) {
            this.a.removeView(this.f);
        }
        this.f = scrollingTabContainerView;
        if (scrollingTabContainerView != null && this.p == 2) {
            this.a.addView(this.f, 0);
            Toolbar.LayoutParams layoutParams = (Toolbar.LayoutParams) this.f.getLayoutParams();
            layoutParams.width = -2;
            layoutParams.height = -2;
            layoutParams.gravity = 8388691;
            scrollingTabContainerView.setAllowCollapse(true);
        }
    }

    public boolean hasEmbeddedTabs() {
        return this.f != null;
    }

    public boolean isTitleTruncated() {
        return this.a.isTitleTruncated();
    }

    public void setCollapsible(boolean z) {
        this.a.setCollapsible(z);
    }

    public void setHomeButtonEnabled(boolean z) {
    }

    public int getNavigationMode() {
        return this.p;
    }

    public void setNavigationMode(int i) {
        int i2 = this.p;
        if (i != i2) {
            switch (i2) {
                case 1:
                    if (this.g != null && this.g.getParent() == this.a) {
                        this.a.removeView(this.g);
                        break;
                    }
                case 2:
                    if (this.f != null && this.f.getParent() == this.a) {
                        this.a.removeView(this.f);
                        break;
                    }
            }
            this.p = i;
            switch (i) {
                case 0:
                    return;
                case 1:
                    c();
                    this.a.addView(this.g, 0);
                    return;
                case 2:
                    if (this.f != null) {
                        this.a.addView(this.f, 0);
                        Toolbar.LayoutParams layoutParams = (Toolbar.LayoutParams) this.f.getLayoutParams();
                        layoutParams.width = -2;
                        layoutParams.height = -2;
                        layoutParams.gravity = 8388691;
                        return;
                    }
                    return;
                default:
                    throw new IllegalArgumentException("Invalid navigation mode " + i);
            }
        }
    }

    private void c() {
        if (this.g == null) {
            this.g = new AppCompatSpinner(getContext(), null, R.attr.actionDropDownStyle);
            this.g.setLayoutParams(new Toolbar.LayoutParams(-2, -2, 8388627));
        }
    }

    public void setDropdownParams(SpinnerAdapter spinnerAdapter, OnItemSelectedListener onItemSelectedListener) {
        c();
        this.g.setAdapter(spinnerAdapter);
        this.g.setOnItemSelectedListener(onItemSelectedListener);
    }

    public void setDropdownSelectedPosition(int i) {
        if (this.g == null) {
            throw new IllegalStateException("Can't set dropdown selected position without an adapter");
        }
        this.g.setSelection(i);
    }

    public int getDropdownSelectedPosition() {
        return this.g != null ? this.g.getSelectedItemPosition() : 0;
    }

    public int getDropdownItemCount() {
        return this.g != null ? this.g.getCount() : 0;
    }

    public void setCustomView(View view) {
        if (!(this.h == null || (this.e & 16) == 0)) {
            this.a.removeView(this.h);
        }
        this.h = view;
        if (view != null && (this.e & 16) != 0) {
            this.a.addView(this.h);
        }
    }

    public View getCustomView() {
        return this.h;
    }

    public void animateToVisibility(int i) {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = setupAnimatorToVisibility(i, 200);
        if (viewPropertyAnimatorCompat != null) {
            viewPropertyAnimatorCompat.start();
        }
    }

    public ViewPropertyAnimatorCompat setupAnimatorToVisibility(int i, long j) {
        return ViewCompat.animate(this.a).alpha(i == 0 ? 1.0f : 0.0f).setDuration(j).setListener(new da(this, i));
    }

    public void setNavigationIcon(Drawable drawable) {
        this.k = drawable;
        d();
    }

    public void setNavigationIcon(int i) {
        setNavigationIcon(i != 0 ? AppCompatResources.getDrawable(getContext(), i) : null);
    }

    public void setDefaultNavigationIcon(Drawable drawable) {
        if (this.r != drawable) {
            this.r = drawable;
            d();
        }
    }

    private void d() {
        if ((this.e & 4) != 0) {
            this.a.setNavigationIcon(this.k != null ? this.k : this.r);
        } else {
            this.a.setNavigationIcon(null);
        }
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        this.n = charSequence;
        e();
    }

    public void setNavigationContentDescription(int i) {
        setNavigationContentDescription(i == 0 ? null : getContext().getString(i));
    }

    private void e() {
        if ((this.e & 4) == 0) {
            return;
        }
        if (TextUtils.isEmpty(this.n)) {
            this.a.setNavigationContentDescription(this.q);
        } else {
            this.a.setNavigationContentDescription(this.n);
        }
    }

    public void saveHierarchyState(SparseArray<Parcelable> sparseArray) {
        this.a.saveHierarchyState(sparseArray);
    }

    public void restoreHierarchyState(SparseArray<Parcelable> sparseArray) {
        this.a.restoreHierarchyState(sparseArray);
    }

    public void setBackgroundDrawable(Drawable drawable) {
        ViewCompat.setBackground(this.a, drawable);
    }

    public int getHeight() {
        return this.a.getHeight();
    }

    public void setVisibility(int i) {
        this.a.setVisibility(i);
    }

    public int getVisibility() {
        return this.a.getVisibility();
    }

    public void setMenuCallbacks(MenuPresenter.Callback callback, MenuBuilder.Callback callback2) {
        this.a.setMenuCallbacks(callback, callback2);
    }

    public Menu getMenu() {
        return this.a.getMenu();
    }
}
