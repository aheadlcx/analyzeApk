package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.appcompat.R;
import android.support.v7.view.ActionBarPolicy;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ScrollingTabContainerView extends HorizontalScrollView implements OnItemSelectedListener {
    private static final Interpolator l = new DecelerateInterpolator();
    Runnable a;
    LinearLayoutCompat b;
    int c;
    int d;
    protected ViewPropertyAnimator e;
    protected final VisibilityAnimListener f = new VisibilityAnimListener(this);
    private b g;
    private Spinner h;
    private boolean i;
    private int j;
    private int k;

    protected class VisibilityAnimListener extends AnimatorListenerAdapter {
        final /* synthetic */ ScrollingTabContainerView a;
        private boolean b = false;
        private int c;

        protected VisibilityAnimListener(ScrollingTabContainerView scrollingTabContainerView) {
            this.a = scrollingTabContainerView;
        }

        public VisibilityAnimListener withFinalVisibility(ViewPropertyAnimator viewPropertyAnimator, int i) {
            this.c = i;
            this.a.e = viewPropertyAnimator;
            return this;
        }

        public void onAnimationStart(Animator animator) {
            this.a.setVisibility(0);
            this.b = false;
        }

        public void onAnimationEnd(Animator animator) {
            if (!this.b) {
                this.a.e = null;
                this.a.setVisibility(this.c);
            }
        }

        public void onAnimationCancel(Animator animator) {
            this.b = true;
        }
    }

    private class a extends BaseAdapter {
        final /* synthetic */ ScrollingTabContainerView a;

        a(ScrollingTabContainerView scrollingTabContainerView) {
            this.a = scrollingTabContainerView;
        }

        public int getCount() {
            return this.a.b.getChildCount();
        }

        public Object getItem(int i) {
            return ((c) this.a.b.getChildAt(i)).getTab();
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                return this.a.a((Tab) getItem(i), true);
            }
            ((c) view).bindTab((Tab) getItem(i));
            return view;
        }
    }

    private class b implements OnClickListener {
        final /* synthetic */ ScrollingTabContainerView a;

        b(ScrollingTabContainerView scrollingTabContainerView) {
            this.a = scrollingTabContainerView;
        }

        public void onClick(View view) {
            ((c) view).getTab().select();
            int childCount = this.a.b.getChildCount();
            for (int i = 0; i < childCount; i++) {
                boolean z;
                View childAt = this.a.b.getChildAt(i);
                if (childAt == view) {
                    z = true;
                } else {
                    z = false;
                }
                childAt.setSelected(z);
            }
        }
    }

    private class c extends LinearLayoutCompat {
        final /* synthetic */ ScrollingTabContainerView a;
        private final int[] b = new int[]{16842964};
        private Tab c;
        private TextView d;
        private ImageView e;
        private View f;

        public c(ScrollingTabContainerView scrollingTabContainerView, Context context, Tab tab, boolean z) {
            this.a = scrollingTabContainerView;
            super(context, null, R.attr.actionBarTabStyle);
            this.c = tab;
            TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, null, this.b, R.attr.actionBarTabStyle, 0);
            if (obtainStyledAttributes.hasValue(0)) {
                setBackgroundDrawable(obtainStyledAttributes.getDrawable(0));
            }
            obtainStyledAttributes.recycle();
            if (z) {
                setGravity(8388627);
            }
            update();
        }

        public void bindTab(Tab tab) {
            this.c = tab;
            update();
        }

        public void setSelected(boolean z) {
            Object obj = isSelected() != z ? 1 : null;
            super.setSelected(z);
            if (obj != null && z) {
                sendAccessibilityEvent(4);
            }
        }

        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName(Tab.class.getName());
        }

        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName(Tab.class.getName());
        }

        public void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (this.a.c > 0 && getMeasuredWidth() > this.a.c) {
                super.onMeasure(MeasureSpec.makeMeasureSpec(this.a.c, 1073741824), i2);
            }
        }

        public void update() {
            Tab tab = this.c;
            View customView = tab.getCustomView();
            if (customView != null) {
                c parent = customView.getParent();
                if (parent != this) {
                    if (parent != null) {
                        parent.removeView(customView);
                    }
                    addView(customView);
                }
                this.f = customView;
                if (this.d != null) {
                    this.d.setVisibility(8);
                }
                if (this.e != null) {
                    this.e.setVisibility(8);
                    this.e.setImageDrawable(null);
                    return;
                }
                return;
            }
            int i;
            CharSequence charSequence;
            if (this.f != null) {
                removeView(this.f);
                this.f = null;
            }
            Drawable icon = tab.getIcon();
            CharSequence text = tab.getText();
            if (icon != null) {
                if (this.e == null) {
                    View appCompatImageView = new AppCompatImageView(getContext());
                    LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams(-2, -2);
                    layoutParams.gravity = 16;
                    appCompatImageView.setLayoutParams(layoutParams);
                    addView(appCompatImageView, 0);
                    this.e = appCompatImageView;
                }
                this.e.setImageDrawable(icon);
                this.e.setVisibility(0);
            } else if (this.e != null) {
                this.e.setVisibility(8);
                this.e.setImageDrawable(null);
            }
            if (TextUtils.isEmpty(text)) {
                i = 0;
            } else {
                i = 1;
            }
            if (i != 0) {
                if (this.d == null) {
                    appCompatImageView = new AppCompatTextView(getContext(), null, R.attr.actionBarTabTextStyle);
                    appCompatImageView.setEllipsize(TruncateAt.END);
                    layoutParams = new LinearLayoutCompat.LayoutParams(-2, -2);
                    layoutParams.gravity = 16;
                    appCompatImageView.setLayoutParams(layoutParams);
                    addView(appCompatImageView);
                    this.d = appCompatImageView;
                }
                this.d.setText(text);
                this.d.setVisibility(0);
            } else if (this.d != null) {
                this.d.setVisibility(8);
                this.d.setText(null);
            }
            if (this.e != null) {
                this.e.setContentDescription(tab.getContentDescription());
            }
            if (i != 0) {
                charSequence = null;
            } else {
                charSequence = tab.getContentDescription();
            }
            TooltipCompat.setTooltipText(this, charSequence);
        }

        public Tab getTab() {
            return this.c;
        }
    }

    public ScrollingTabContainerView(Context context) {
        super(context);
        setHorizontalScrollBarEnabled(false);
        ActionBarPolicy actionBarPolicy = ActionBarPolicy.get(context);
        setContentHeight(actionBarPolicy.getTabContainerHeight());
        this.d = actionBarPolicy.getStackedTabMaxWidth();
        this.b = d();
        addView(this.b, new LayoutParams(-2, -1));
    }

    public void onMeasure(int i, int i2) {
        int i3 = 1;
        int mode = MeasureSpec.getMode(i);
        boolean z = mode == 1073741824;
        setFillViewport(z);
        int childCount = this.b.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            this.c = -1;
        } else {
            if (childCount > 2) {
                this.c = (int) (((float) MeasureSpec.getSize(i)) * 0.4f);
            } else {
                this.c = MeasureSpec.getSize(i) / 2;
            }
            this.c = Math.min(this.c, this.d);
        }
        mode = MeasureSpec.makeMeasureSpec(this.j, 1073741824);
        if (z || !this.i) {
            i3 = 0;
        }
        if (i3 != 0) {
            this.b.measure(0, mode);
            if (this.b.getMeasuredWidth() > MeasureSpec.getSize(i)) {
                b();
            } else {
                c();
            }
        } else {
            c();
        }
        i3 = getMeasuredWidth();
        super.onMeasure(i, mode);
        int measuredWidth = getMeasuredWidth();
        if (z && i3 != measuredWidth) {
            setTabSelected(this.k);
        }
    }

    private boolean a() {
        return this.h != null && this.h.getParent() == this;
    }

    public void setAllowCollapse(boolean z) {
        this.i = z;
    }

    private void b() {
        if (!a()) {
            if (this.h == null) {
                this.h = e();
            }
            removeView(this.b);
            addView(this.h, new LayoutParams(-2, -1));
            if (this.h.getAdapter() == null) {
                this.h.setAdapter(new a(this));
            }
            if (this.a != null) {
                removeCallbacks(this.a);
                this.a = null;
            }
            this.h.setSelection(this.k);
        }
    }

    private boolean c() {
        if (a()) {
            removeView(this.h);
            addView(this.b, new LayoutParams(-2, -1));
            setTabSelected(this.h.getSelectedItemPosition());
        }
        return false;
    }

    public void setTabSelected(int i) {
        this.k = i;
        int childCount = this.b.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            boolean z;
            View childAt = this.b.getChildAt(i2);
            if (i2 == i) {
                z = true;
            } else {
                z = false;
            }
            childAt.setSelected(z);
            if (z) {
                animateToTab(i);
            }
        }
        if (this.h != null && i >= 0) {
            this.h.setSelection(i);
        }
    }

    public void setContentHeight(int i) {
        this.j = i;
        requestLayout();
    }

    private LinearLayoutCompat d() {
        LinearLayoutCompat linearLayoutCompat = new LinearLayoutCompat(getContext(), null, R.attr.actionBarTabBarStyle);
        linearLayoutCompat.setMeasureWithLargestChildEnabled(true);
        linearLayoutCompat.setGravity(17);
        linearLayoutCompat.setLayoutParams(new LinearLayoutCompat.LayoutParams(-2, -1));
        return linearLayoutCompat;
    }

    private Spinner e() {
        Spinner appCompatSpinner = new AppCompatSpinner(getContext(), null, R.attr.actionDropDownStyle);
        appCompatSpinner.setLayoutParams(new LinearLayoutCompat.LayoutParams(-2, -1));
        appCompatSpinner.setOnItemSelectedListener(this);
        return appCompatSpinner;
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ActionBarPolicy actionBarPolicy = ActionBarPolicy.get(getContext());
        setContentHeight(actionBarPolicy.getTabContainerHeight());
        this.d = actionBarPolicy.getStackedTabMaxWidth();
    }

    public void animateToVisibility(int i) {
        if (this.e != null) {
            this.e.cancel();
        }
        if (i == 0) {
            if (getVisibility() != 0) {
                setAlpha(0.0f);
            }
            ViewPropertyAnimator alpha = animate().alpha(1.0f);
            alpha.setDuration(200);
            alpha.setInterpolator(l);
            alpha.setListener(this.f.withFinalVisibility(alpha, i));
            alpha.start();
            return;
        }
        alpha = animate().alpha(0.0f);
        alpha.setDuration(200);
        alpha.setInterpolator(l);
        alpha.setListener(this.f.withFinalVisibility(alpha, i));
        alpha.start();
    }

    public void animateToTab(int i) {
        View childAt = this.b.getChildAt(i);
        if (this.a != null) {
            removeCallbacks(this.a);
        }
        this.a = new by(this, childAt);
        post(this.a);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.a != null) {
            post(this.a);
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.a != null) {
            removeCallbacks(this.a);
        }
    }

    c a(Tab tab, boolean z) {
        c cVar = new c(this, getContext(), tab, z);
        if (z) {
            cVar.setBackgroundDrawable(null);
            cVar.setLayoutParams(new AbsListView.LayoutParams(-1, this.j));
        } else {
            cVar.setFocusable(true);
            if (this.g == null) {
                this.g = new b(this);
            }
            cVar.setOnClickListener(this.g);
        }
        return cVar;
    }

    public void addTab(Tab tab, boolean z) {
        View a = a(tab, false);
        this.b.addView(a, new LinearLayoutCompat.LayoutParams(0, -1, 1.0f));
        if (this.h != null) {
            ((a) this.h.getAdapter()).notifyDataSetChanged();
        }
        if (z) {
            a.setSelected(true);
        }
        if (this.i) {
            requestLayout();
        }
    }

    public void addTab(Tab tab, int i, boolean z) {
        View a = a(tab, false);
        this.b.addView(a, i, new LinearLayoutCompat.LayoutParams(0, -1, 1.0f));
        if (this.h != null) {
            ((a) this.h.getAdapter()).notifyDataSetChanged();
        }
        if (z) {
            a.setSelected(true);
        }
        if (this.i) {
            requestLayout();
        }
    }

    public void updateTab(int i) {
        ((c) this.b.getChildAt(i)).update();
        if (this.h != null) {
            ((a) this.h.getAdapter()).notifyDataSetChanged();
        }
        if (this.i) {
            requestLayout();
        }
    }

    public void removeTabAt(int i) {
        this.b.removeViewAt(i);
        if (this.h != null) {
            ((a) this.h.getAdapter()).notifyDataSetChanged();
        }
        if (this.i) {
            requestLayout();
        }
    }

    public void removeAllTabs() {
        this.b.removeAllViews();
        if (this.h != null) {
            ((a) this.h.getAdapter()).notifyDataSetChanged();
        }
        if (this.i) {
            requestLayout();
        }
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        ((c) view).getTab().select();
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
