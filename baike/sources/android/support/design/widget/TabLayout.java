package android.support.design.widget;

import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.StringRes;
import android.support.design.R;
import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SimplePool;
import android.support.v4.util.Pools.SynchronizedPool;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.DecorView;
import android.support.v4.view.ViewPager.OnAdapterChangeListener;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.TooltipCompat;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

@DecorView
public class TabLayout extends HorizontalScrollView {
    public static final int GRAVITY_CENTER = 1;
    public static final int GRAVITY_FILL = 0;
    public static final int MODE_FIXED = 1;
    public static final int MODE_SCROLLABLE = 0;
    private static final Pool<Tab> n = new SynchronizedPool(16);
    private DataSetObserver A;
    private TabLayoutOnPageChangeListener B;
    private a C;
    private boolean D;
    private final Pool<d> E;
    int a;
    int b;
    int c;
    int d;
    int e;
    ColorStateList f;
    float g;
    float h;
    final int i;
    int j;
    int k;
    int l;
    ViewPager m;
    private final ArrayList<Tab> o;
    private Tab p;
    private final c q;
    private final int r;
    private final int s;
    private final int t;
    private int u;
    private OnTabSelectedListener v;
    private final ArrayList<OnTabSelectedListener> w;
    private OnTabSelectedListener x;
    private ValueAnimator y;
    private PagerAdapter z;

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    public interface OnTabSelectedListener {
        void onTabReselected(Tab tab);

        void onTabSelected(Tab tab);

        void onTabUnselected(Tab tab);
    }

    public static final class Tab {
        public static final int INVALID_POSITION = -1;
        TabLayout a;
        d b;
        private Object c;
        private Drawable d;
        private CharSequence e;
        private CharSequence f;
        private int g = -1;
        private View h;

        Tab() {
        }

        @Nullable
        public Object getTag() {
            return this.c;
        }

        @NonNull
        public Tab setTag(@Nullable Object obj) {
            this.c = obj;
            return this;
        }

        @Nullable
        public View getCustomView() {
            return this.h;
        }

        @NonNull
        public Tab setCustomView(@Nullable View view) {
            this.h = view;
            a();
            return this;
        }

        @NonNull
        public Tab setCustomView(@LayoutRes int i) {
            return setCustomView(LayoutInflater.from(this.b.getContext()).inflate(i, this.b, false));
        }

        @Nullable
        public Drawable getIcon() {
            return this.d;
        }

        public int getPosition() {
            return this.g;
        }

        void a(int i) {
            this.g = i;
        }

        @Nullable
        public CharSequence getText() {
            return this.e;
        }

        @NonNull
        public Tab setIcon(@Nullable Drawable drawable) {
            this.d = drawable;
            a();
            return this;
        }

        @NonNull
        public Tab setIcon(@DrawableRes int i) {
            if (this.a != null) {
                return setIcon(AppCompatResources.getDrawable(this.a.getContext(), i));
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        @NonNull
        public Tab setText(@Nullable CharSequence charSequence) {
            this.e = charSequence;
            a();
            return this;
        }

        @NonNull
        public Tab setText(@StringRes int i) {
            if (this.a != null) {
                return setText(this.a.getResources().getText(i));
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        public void select() {
            if (this.a == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            this.a.a(this);
        }

        public boolean isSelected() {
            if (this.a != null) {
                return this.a.getSelectedTabPosition() == this.g;
            } else {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
        }

        @NonNull
        public Tab setContentDescription(@StringRes int i) {
            if (this.a != null) {
                return setContentDescription(this.a.getResources().getText(i));
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        @NonNull
        public Tab setContentDescription(@Nullable CharSequence charSequence) {
            this.f = charSequence;
            a();
            return this;
        }

        @Nullable
        public CharSequence getContentDescription() {
            return this.f;
        }

        void a() {
            if (this.b != null) {
                this.b.b();
            }
        }

        void b() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = -1;
            this.h = null;
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TabGravity {
    }

    public static class TabLayoutOnPageChangeListener implements OnPageChangeListener {
        private final WeakReference<TabLayout> a;
        private int b;
        private int c;

        public TabLayoutOnPageChangeListener(TabLayout tabLayout) {
            this.a = new WeakReference(tabLayout);
        }

        public void onPageScrollStateChanged(int i) {
            this.b = this.c;
            this.c = i;
        }

        public void onPageScrolled(int i, float f, int i2) {
            boolean z = false;
            TabLayout tabLayout = (TabLayout) this.a.get();
            if (tabLayout != null) {
                boolean z2 = this.c != 2 || this.b == 1;
                if (!(this.c == 2 && this.b == 0)) {
                    z = true;
                }
                tabLayout.a(i, f, z2, z);
            }
        }

        public void onPageSelected(int i) {
            TabLayout tabLayout = (TabLayout) this.a.get();
            if (tabLayout != null && tabLayout.getSelectedTabPosition() != i && i < tabLayout.getTabCount()) {
                boolean z = this.c == 0 || (this.c == 2 && this.b == 0);
                tabLayout.a(tabLayout.getTabAt(i), z);
            }
        }

        void a() {
            this.c = 0;
            this.b = 0;
        }
    }

    public static class ViewPagerOnTabSelectedListener implements OnTabSelectedListener {
        private final ViewPager a;

        public ViewPagerOnTabSelectedListener(ViewPager viewPager) {
            this.a = viewPager;
        }

        public void onTabSelected(Tab tab) {
            this.a.setCurrentItem(tab.getPosition());
        }

        public void onTabUnselected(Tab tab) {
        }

        public void onTabReselected(Tab tab) {
        }
    }

    private class a implements OnAdapterChangeListener {
        final /* synthetic */ TabLayout a;
        private boolean b;

        a(TabLayout tabLayout) {
            this.a = tabLayout;
        }

        public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter pagerAdapter, @Nullable PagerAdapter pagerAdapter2) {
            if (this.a.m == viewPager) {
                this.a.a(pagerAdapter2, this.b);
            }
        }

        void a(boolean z) {
            this.b = z;
        }
    }

    private class b extends DataSetObserver {
        final /* synthetic */ TabLayout a;

        b(TabLayout tabLayout) {
            this.a = tabLayout;
        }

        public void onChanged() {
            this.a.a();
        }

        public void onInvalidated() {
            this.a.a();
        }
    }

    private class c extends LinearLayout {
        int a = -1;
        float b;
        final /* synthetic */ TabLayout c;
        private int d;
        private final Paint e;
        private int f = -1;
        private int g = -1;
        private int h = -1;
        private ValueAnimator i;

        c(TabLayout tabLayout, Context context) {
            this.c = tabLayout;
            super(context);
            setWillNotDraw(false);
            this.e = new Paint();
        }

        void a(int i) {
            if (this.e.getColor() != i) {
                this.e.setColor(i);
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        void b(int i) {
            if (this.d != i) {
                this.d = i;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        boolean a() {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (getChildAt(i).getWidth() <= 0) {
                    return true;
                }
            }
            return false;
        }

        void a(int i, float f) {
            if (this.i != null && this.i.isRunning()) {
                this.i.cancel();
            }
            this.a = i;
            this.b = f;
            c();
        }

        float b() {
            return ((float) this.a) + this.b;
        }

        public void onRtlPropertiesChanged(int i) {
            super.onRtlPropertiesChanged(i);
            if (VERSION.SDK_INT < 23 && this.f != i) {
                requestLayout();
                this.f = i;
            }
        }

        protected void onMeasure(int i, int i2) {
            boolean z = false;
            super.onMeasure(i, i2);
            if (MeasureSpec.getMode(i) == 1073741824 && this.c.l == 1 && this.c.k == 1) {
                int childCount = getChildCount();
                int i3 = 0;
                int i4 = 0;
                while (i3 < childCount) {
                    int max;
                    View childAt = getChildAt(i3);
                    if (childAt.getVisibility() == 0) {
                        max = Math.max(i4, childAt.getMeasuredWidth());
                    } else {
                        max = i4;
                    }
                    i3++;
                    i4 = max;
                }
                if (i4 > 0) {
                    if (i4 * childCount <= getMeasuredWidth() - (this.c.a(16) * 2)) {
                        i3 = 0;
                        while (i3 < childCount) {
                            boolean z2;
                            LayoutParams layoutParams = (LayoutParams) getChildAt(i3).getLayoutParams();
                            if (layoutParams.width == i4 && layoutParams.weight == 0.0f) {
                                z2 = z;
                            } else {
                                layoutParams.width = i4;
                                layoutParams.weight = 0.0f;
                                z2 = true;
                            }
                            i3++;
                            z = z2;
                        }
                    } else {
                        this.c.k = 0;
                        this.c.a(false);
                        z = true;
                    }
                    if (z) {
                        super.onMeasure(i, i2);
                    }
                }
            }
        }

        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            if (this.i == null || !this.i.isRunning()) {
                c();
                return;
            }
            this.i.cancel();
            b(this.a, Math.round(((float) this.i.getDuration()) * (1.0f - this.i.getAnimatedFraction())));
        }

        private void c() {
            int i;
            int i2;
            View childAt = getChildAt(this.a);
            if (childAt == null || childAt.getWidth() <= 0) {
                i = -1;
                i2 = -1;
            } else {
                i2 = childAt.getLeft();
                i = childAt.getRight();
                if (this.b > 0.0f && this.a < getChildCount() - 1) {
                    View childAt2 = getChildAt(this.a + 1);
                    i2 = (int) ((((float) i2) * (1.0f - this.b)) + (this.b * ((float) childAt2.getLeft())));
                    i = (int) ((((float) i) * (1.0f - this.b)) + (((float) childAt2.getRight()) * this.b));
                }
            }
            a(i2, i);
        }

        void a(int i, int i2) {
            if (i != this.g || i2 != this.h) {
                this.g = i;
                this.h = i2;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        void b(int i, int i2) {
            if (this.i != null && this.i.isRunning()) {
                this.i.cancel();
            }
            Object obj = ViewCompat.getLayoutDirection(this) == 1 ? 1 : null;
            View childAt = getChildAt(i);
            if (childAt == null) {
                c();
                return;
            }
            int i3;
            int i4;
            int left = childAt.getLeft();
            int right = childAt.getRight();
            if (Math.abs(i - this.a) <= 1) {
                i3 = this.g;
                i4 = this.h;
            } else {
                int a = this.c.a(24);
                if (i < this.a) {
                    if (obj != null) {
                        i4 = left - a;
                        i3 = i4;
                    } else {
                        i4 = right + a;
                        i3 = i4;
                    }
                } else if (obj != null) {
                    i4 = right + a;
                    i3 = i4;
                } else {
                    i4 = left - a;
                    i3 = i4;
                }
            }
            if (i3 != left || i4 != right) {
                ValueAnimator valueAnimator = new ValueAnimator();
                this.i = valueAnimator;
                valueAnimator.setInterpolator(a.b);
                valueAnimator.setDuration((long) i2);
                valueAnimator.setFloatValues(new float[]{0.0f, 1.0f});
                valueAnimator.addUpdateListener(new bb(this, i3, left, i4, right));
                valueAnimator.addListener(new bc(this, i));
                valueAnimator.start();
            }
        }

        public void draw(Canvas canvas) {
            super.draw(canvas);
            if (this.g >= 0 && this.h > this.g) {
                canvas.drawRect((float) this.g, (float) (getHeight() - this.d), (float) this.h, (float) getHeight(), this.e);
            }
        }
    }

    class d extends LinearLayout {
        final /* synthetic */ TabLayout a;
        private Tab b;
        private TextView c;
        private ImageView d;
        private View e;
        private TextView f;
        private ImageView g;
        private int h = 2;

        public d(TabLayout tabLayout, Context context) {
            this.a = tabLayout;
            super(context);
            if (tabLayout.i != 0) {
                ViewCompat.setBackground(this, AppCompatResources.getDrawable(context, tabLayout.i));
            }
            ViewCompat.setPaddingRelative(this, tabLayout.a, tabLayout.b, tabLayout.c, tabLayout.d);
            setGravity(17);
            setOrientation(1);
            setClickable(true);
            ViewCompat.setPointerIcon(this, PointerIconCompat.getSystemIcon(getContext(), 1002));
        }

        public boolean performClick() {
            boolean performClick = super.performClick();
            if (this.b == null) {
                return performClick;
            }
            if (!performClick) {
                playSoundEffect(0);
            }
            this.b.select();
            return true;
        }

        public void setSelected(boolean z) {
            Object obj = isSelected() != z ? 1 : null;
            super.setSelected(z);
            if (obj != null && z && VERSION.SDK_INT < 16) {
                sendAccessibilityEvent(4);
            }
            if (this.c != null) {
                this.c.setSelected(z);
            }
            if (this.d != null) {
                this.d.setSelected(z);
            }
            if (this.e != null) {
                this.e.setSelected(z);
            }
        }

        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName(android.support.v7.app.ActionBar.Tab.class.getName());
        }

        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName(android.support.v7.app.ActionBar.Tab.class.getName());
        }

        public void onMeasure(int i, int i2) {
            int i3 = 1;
            int size = MeasureSpec.getSize(i);
            int mode = MeasureSpec.getMode(i);
            int tabMaxWidth = this.a.getTabMaxWidth();
            if (tabMaxWidth > 0 && (mode == 0 || size > tabMaxWidth)) {
                i = MeasureSpec.makeMeasureSpec(this.a.j, Integer.MIN_VALUE);
            }
            super.onMeasure(i, i2);
            if (this.c != null) {
                getResources();
                float f = this.a.g;
                size = this.h;
                if (this.d != null && this.d.getVisibility() == 0) {
                    size = 1;
                } else if (this.c != null && this.c.getLineCount() > 1) {
                    f = this.a.h;
                }
                float textSize = this.c.getTextSize();
                int lineCount = this.c.getLineCount();
                int maxLines = TextViewCompat.getMaxLines(this.c);
                if (f != textSize || (maxLines >= 0 && size != maxLines)) {
                    if (this.a.l == 1 && f > textSize && lineCount == 1) {
                        Layout layout = this.c.getLayout();
                        if (layout == null || a(layout, 0, f) > ((float) ((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()))) {
                            i3 = 0;
                        }
                    }
                    if (i3 != 0) {
                        this.c.setTextSize(0, f);
                        this.c.setMaxLines(size);
                        super.onMeasure(i, i2);
                    }
                }
            }
        }

        void a(@Nullable Tab tab) {
            if (tab != this.b) {
                this.b = tab;
                b();
            }
        }

        void a() {
            a(null);
            setSelected(false);
        }

        final void b() {
            boolean z;
            Tab tab = this.b;
            View customView = tab != null ? tab.getCustomView() : null;
            if (customView != null) {
                d parent = customView.getParent();
                if (parent != this) {
                    if (parent != null) {
                        parent.removeView(customView);
                    }
                    addView(customView);
                }
                this.e = customView;
                if (this.c != null) {
                    this.c.setVisibility(8);
                }
                if (this.d != null) {
                    this.d.setVisibility(8);
                    this.d.setImageDrawable(null);
                }
                this.f = (TextView) customView.findViewById(16908308);
                if (this.f != null) {
                    this.h = TextViewCompat.getMaxLines(this.f);
                }
                this.g = (ImageView) customView.findViewById(16908294);
            } else {
                if (this.e != null) {
                    removeView(this.e);
                    this.e = null;
                }
                this.f = null;
                this.g = null;
            }
            if (this.e == null) {
                if (this.d == null) {
                    ImageView imageView = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.design_layout_tab_icon, this, false);
                    addView(imageView, 0);
                    this.d = imageView;
                }
                if (this.c == null) {
                    TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.design_layout_tab_text, this, false);
                    addView(textView);
                    this.c = textView;
                    this.h = TextViewCompat.getMaxLines(this.c);
                }
                TextViewCompat.setTextAppearance(this.c, this.a.e);
                if (this.a.f != null) {
                    this.c.setTextColor(this.a.f);
                }
                a(this.c, this.d);
            } else if (!(this.f == null && this.g == null)) {
                a(this.f, this.g);
            }
            if (tab == null || !tab.isSelected()) {
                z = false;
            } else {
                z = true;
            }
            setSelected(z);
        }

        private void a(@Nullable TextView textView, @Nullable ImageView imageView) {
            CharSequence text;
            CharSequence contentDescription;
            int i;
            CharSequence charSequence = null;
            Drawable icon = this.b != null ? this.b.getIcon() : null;
            if (this.b != null) {
                text = this.b.getText();
            } else {
                text = null;
            }
            if (this.b != null) {
                contentDescription = this.b.getContentDescription();
            } else {
                contentDescription = null;
            }
            if (imageView != null) {
                if (icon != null) {
                    imageView.setImageDrawable(icon);
                    imageView.setVisibility(0);
                    setVisibility(0);
                } else {
                    imageView.setVisibility(8);
                    imageView.setImageDrawable(null);
                }
                imageView.setContentDescription(contentDescription);
            }
            if (TextUtils.isEmpty(text)) {
                i = 0;
            } else {
                i = 1;
            }
            if (textView != null) {
                if (i != 0) {
                    textView.setText(text);
                    textView.setVisibility(0);
                    setVisibility(0);
                } else {
                    textView.setVisibility(8);
                    textView.setText(null);
                }
                textView.setContentDescription(contentDescription);
            }
            if (imageView != null) {
                int i2;
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) imageView.getLayoutParams();
                if (i == 0 || imageView.getVisibility() != 0) {
                    i2 = 0;
                } else {
                    i2 = this.a.a(8);
                }
                if (i2 != marginLayoutParams.bottomMargin) {
                    marginLayoutParams.bottomMargin = i2;
                    imageView.requestLayout();
                }
            }
            if (i == 0) {
                charSequence = contentDescription;
            }
            TooltipCompat.setTooltipText(this, charSequence);
        }

        public Tab getTab() {
            return this.b;
        }

        private float a(Layout layout, int i, float f) {
            return layout.getLineWidth(i) * (f / layout.getPaint().getTextSize());
        }
    }

    public TabLayout(Context context) {
        this(context, null);
    }

    public TabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TabLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.o = new ArrayList();
        this.j = Integer.MAX_VALUE;
        this.w = new ArrayList();
        this.E = new SimplePool(12);
        bj.a(context);
        setHorizontalScrollBarEnabled(false);
        this.q = new c(this, context);
        super.addView(this.q, 0, new FrameLayout.LayoutParams(-2, -1));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TabLayout, i, R.style.Widget_Design_TabLayout);
        this.q.b(obtainStyledAttributes.getDimensionPixelSize(R.styleable.TabLayout_tabIndicatorHeight, 0));
        this.q.a(obtainStyledAttributes.getColor(R.styleable.TabLayout_tabIndicatorColor, 0));
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.TabLayout_tabPadding, 0);
        this.d = dimensionPixelSize;
        this.c = dimensionPixelSize;
        this.b = dimensionPixelSize;
        this.a = dimensionPixelSize;
        this.a = obtainStyledAttributes.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingStart, this.a);
        this.b = obtainStyledAttributes.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingTop, this.b);
        this.c = obtainStyledAttributes.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingEnd, this.c);
        this.d = obtainStyledAttributes.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingBottom, this.d);
        this.e = obtainStyledAttributes.getResourceId(R.styleable.TabLayout_tabTextAppearance, R.style.TextAppearance_Design_Tab);
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(this.e, android.support.v7.appcompat.R.styleable.TextAppearance);
        try {
            this.g = (float) obtainStyledAttributes2.getDimensionPixelSize(android.support.v7.appcompat.R.styleable.TextAppearance_android_textSize, 0);
            this.f = obtainStyledAttributes2.getColorStateList(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor);
            if (obtainStyledAttributes.hasValue(R.styleable.TabLayout_tabTextColor)) {
                this.f = obtainStyledAttributes.getColorStateList(R.styleable.TabLayout_tabTextColor);
            }
            if (obtainStyledAttributes.hasValue(R.styleable.TabLayout_tabSelectedTextColor)) {
                this.f = a(this.f.getDefaultColor(), obtainStyledAttributes.getColor(R.styleable.TabLayout_tabSelectedTextColor, 0));
            }
            this.r = obtainStyledAttributes.getDimensionPixelSize(R.styleable.TabLayout_tabMinWidth, -1);
            this.s = obtainStyledAttributes.getDimensionPixelSize(R.styleable.TabLayout_tabMaxWidth, -1);
            this.i = obtainStyledAttributes.getResourceId(R.styleable.TabLayout_tabBackground, 0);
            this.u = obtainStyledAttributes.getDimensionPixelSize(R.styleable.TabLayout_tabContentStart, 0);
            this.l = obtainStyledAttributes.getInt(R.styleable.TabLayout_tabMode, 1);
            this.k = obtainStyledAttributes.getInt(R.styleable.TabLayout_tabGravity, 0);
            obtainStyledAttributes.recycle();
            Resources resources = getResources();
            this.h = (float) resources.getDimensionPixelSize(R.dimen.design_tab_text_size_2line);
            this.t = resources.getDimensionPixelSize(R.dimen.design_tab_scrollable_min_width);
            e();
        } finally {
            obtainStyledAttributes2.recycle();
        }
    }

    public void setSelectedTabIndicatorColor(@ColorInt int i) {
        this.q.a(i);
    }

    public void setSelectedTabIndicatorHeight(int i) {
        this.q.b(i);
    }

    public void setScrollPosition(int i, float f, boolean z) {
        a(i, f, z, true);
    }

    void a(int i, float f, boolean z, boolean z2) {
        int round = Math.round(((float) i) + f);
        if (round >= 0 && round < this.q.getChildCount()) {
            if (z2) {
                this.q.a(i, f);
            }
            if (this.y != null && this.y.isRunning()) {
                this.y.cancel();
            }
            scrollTo(a(i, f), 0);
            if (z) {
                setSelectedTabView(round);
            }
        }
    }

    private float getScrollPosition() {
        return this.q.b();
    }

    public void addTab(@NonNull Tab tab) {
        addTab(tab, this.o.isEmpty());
    }

    public void addTab(@NonNull Tab tab, int i) {
        addTab(tab, i, this.o.isEmpty());
    }

    public void addTab(@NonNull Tab tab, boolean z) {
        addTab(tab, this.o.size(), z);
    }

    public void addTab(@NonNull Tab tab, int i, boolean z) {
        if (tab.a != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        a(tab, i);
        c(tab);
        if (z) {
            tab.select();
        }
    }

    private void a(@NonNull TabItem tabItem) {
        Tab newTab = newTab();
        if (tabItem.a != null) {
            newTab.setText(tabItem.a);
        }
        if (tabItem.b != null) {
            newTab.setIcon(tabItem.b);
        }
        if (tabItem.c != 0) {
            newTab.setCustomView(tabItem.c);
        }
        if (!TextUtils.isEmpty(tabItem.getContentDescription())) {
            newTab.setContentDescription(tabItem.getContentDescription());
        }
        addTab(newTab);
    }

    @Deprecated
    public void setOnTabSelectedListener(@Nullable OnTabSelectedListener onTabSelectedListener) {
        if (this.v != null) {
            removeOnTabSelectedListener(this.v);
        }
        this.v = onTabSelectedListener;
        if (onTabSelectedListener != null) {
            addOnTabSelectedListener(onTabSelectedListener);
        }
    }

    public void addOnTabSelectedListener(@NonNull OnTabSelectedListener onTabSelectedListener) {
        if (!this.w.contains(onTabSelectedListener)) {
            this.w.add(onTabSelectedListener);
        }
    }

    public void removeOnTabSelectedListener(@NonNull OnTabSelectedListener onTabSelectedListener) {
        this.w.remove(onTabSelectedListener);
    }

    public void clearOnTabSelectedListeners() {
        this.w.clear();
    }

    @NonNull
    public Tab newTab() {
        Tab tab = (Tab) n.acquire();
        if (tab == null) {
            tab = new Tab();
        }
        tab.a = this;
        tab.b = b(tab);
        return tab;
    }

    public int getTabCount() {
        return this.o.size();
    }

    @Nullable
    public Tab getTabAt(int i) {
        return (i < 0 || i >= getTabCount()) ? null : (Tab) this.o.get(i);
    }

    public int getSelectedTabPosition() {
        return this.p != null ? this.p.getPosition() : -1;
    }

    public void removeTab(Tab tab) {
        if (tab.a != this) {
            throw new IllegalArgumentException("Tab does not belong to this TabLayout.");
        }
        removeTabAt(tab.getPosition());
    }

    public void removeTabAt(int i) {
        int position = this.p != null ? this.p.getPosition() : 0;
        b(i);
        Tab tab = (Tab) this.o.remove(i);
        if (tab != null) {
            tab.b();
            n.release(tab);
        }
        int size = this.o.size();
        for (int i2 = i; i2 < size; i2++) {
            ((Tab) this.o.get(i2)).a(i2);
        }
        if (position == i) {
            a(this.o.isEmpty() ? null : (Tab) this.o.get(Math.max(0, i - 1)));
        }
    }

    public void removeAllTabs() {
        for (int childCount = this.q.getChildCount() - 1; childCount >= 0; childCount--) {
            b(childCount);
        }
        Iterator it = this.o.iterator();
        while (it.hasNext()) {
            Tab tab = (Tab) it.next();
            it.remove();
            tab.b();
            n.release(tab);
        }
        this.p = null;
    }

    public void setTabMode(int i) {
        if (i != this.l) {
            this.l = i;
            e();
        }
    }

    public int getTabMode() {
        return this.l;
    }

    public void setTabGravity(int i) {
        if (this.k != i) {
            this.k = i;
            e();
        }
    }

    public int getTabGravity() {
        return this.k;
    }

    public void setTabTextColors(@Nullable ColorStateList colorStateList) {
        if (this.f != colorStateList) {
            this.f = colorStateList;
            b();
        }
    }

    @Nullable
    public ColorStateList getTabTextColors() {
        return this.f;
    }

    public void setTabTextColors(int i, int i2) {
        setTabTextColors(a(i, i2));
    }

    public void setupWithViewPager(@Nullable ViewPager viewPager) {
        setupWithViewPager(viewPager, true);
    }

    public void setupWithViewPager(@Nullable ViewPager viewPager, boolean z) {
        a(viewPager, z, false);
    }

    private void a(@Nullable ViewPager viewPager, boolean z, boolean z2) {
        if (this.m != null) {
            if (this.B != null) {
                this.m.removeOnPageChangeListener(this.B);
            }
            if (this.C != null) {
                this.m.removeOnAdapterChangeListener(this.C);
            }
        }
        if (this.x != null) {
            removeOnTabSelectedListener(this.x);
            this.x = null;
        }
        if (viewPager != null) {
            this.m = viewPager;
            if (this.B == null) {
                this.B = new TabLayoutOnPageChangeListener(this);
            }
            this.B.a();
            viewPager.addOnPageChangeListener(this.B);
            this.x = new ViewPagerOnTabSelectedListener(viewPager);
            addOnTabSelectedListener(this.x);
            PagerAdapter adapter = viewPager.getAdapter();
            if (adapter != null) {
                a(adapter, z);
            }
            if (this.C == null) {
                this.C = new a(this);
            }
            this.C.a(z);
            viewPager.addOnAdapterChangeListener(this.C);
            setScrollPosition(viewPager.getCurrentItem(), 0.0f, true);
        } else {
            this.m = null;
            a(null, false);
        }
        this.D = z2;
    }

    @Deprecated
    public void setTabsFromPagerAdapter(@Nullable PagerAdapter pagerAdapter) {
        a(pagerAdapter, false);
    }

    public boolean shouldDelayChildPressedState() {
        return getTabScrollRange() > 0;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.m == null) {
            ViewParent parent = getParent();
            if (parent instanceof ViewPager) {
                a((ViewPager) parent, true, true);
            }
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.D) {
            setupWithViewPager(null);
            this.D = false;
        }
    }

    private int getTabScrollRange() {
        return Math.max(0, ((this.q.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    void a(@Nullable PagerAdapter pagerAdapter, boolean z) {
        if (!(this.z == null || this.A == null)) {
            this.z.unregisterDataSetObserver(this.A);
        }
        this.z = pagerAdapter;
        if (z && pagerAdapter != null) {
            if (this.A == null) {
                this.A = new b(this);
            }
            pagerAdapter.registerDataSetObserver(this.A);
        }
        a();
    }

    void a() {
        removeAllTabs();
        if (this.z != null) {
            int i;
            int count = this.z.getCount();
            for (i = 0; i < count; i++) {
                addTab(newTab().setText(this.z.getPageTitle(i)), false);
            }
            if (this.m != null && count > 0) {
                i = this.m.getCurrentItem();
                if (i != getSelectedTabPosition() && i < getTabCount()) {
                    a(getTabAt(i));
                }
            }
        }
    }

    private void b() {
        int size = this.o.size();
        for (int i = 0; i < size; i++) {
            ((Tab) this.o.get(i)).a();
        }
    }

    private d b(@NonNull Tab tab) {
        d dVar = this.E != null ? (d) this.E.acquire() : null;
        if (dVar == null) {
            dVar = new d(this, getContext());
        }
        dVar.a(tab);
        dVar.setFocusable(true);
        dVar.setMinimumWidth(getTabMinWidth());
        return dVar;
    }

    private void a(Tab tab, int i) {
        tab.a(i);
        this.o.add(i, tab);
        int size = this.o.size();
        for (int i2 = i + 1; i2 < size; i2++) {
            ((Tab) this.o.get(i2)).a(i2);
        }
    }

    private void c(Tab tab) {
        this.q.addView(tab.b, tab.getPosition(), c());
    }

    public void addView(View view) {
        a(view);
    }

    public void addView(View view, int i) {
        a(view);
    }

    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        a(view);
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        a(view);
    }

    private void a(View view) {
        if (view instanceof TabItem) {
            a((TabItem) view);
            return;
        }
        throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
    }

    private LayoutParams c() {
        LayoutParams layoutParams = new LayoutParams(-2, -1);
        a(layoutParams);
        return layoutParams;
    }

    private void a(LayoutParams layoutParams) {
        if (this.l == 1 && this.k == 0) {
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
            return;
        }
        layoutParams.width = -2;
        layoutParams.weight = 0.0f;
    }

    int a(int i) {
        return Math.round(getResources().getDisplayMetrics().density * ((float) i));
    }

    protected void onMeasure(int i, int i2) {
        int i3 = 1;
        int a = (a(getDefaultHeight()) + getPaddingTop()) + getPaddingBottom();
        switch (MeasureSpec.getMode(i2)) {
            case Integer.MIN_VALUE:
                i2 = MeasureSpec.makeMeasureSpec(Math.min(a, MeasureSpec.getSize(i2)), 1073741824);
                break;
            case 0:
                i2 = MeasureSpec.makeMeasureSpec(a, 1073741824);
                break;
        }
        a = MeasureSpec.getSize(i);
        if (MeasureSpec.getMode(i) != 0) {
            if (this.s > 0) {
                a = this.s;
            } else {
                a -= a(56);
            }
            this.j = a;
        }
        super.onMeasure(i, i2);
        if (getChildCount() == 1) {
            View childAt = getChildAt(0);
            switch (this.l) {
                case 0:
                    if (childAt.getMeasuredWidth() >= getMeasuredWidth()) {
                        a = 0;
                        break;
                    } else {
                        a = 1;
                        break;
                    }
                case 1:
                    if (childAt.getMeasuredWidth() == getMeasuredWidth()) {
                        i3 = 0;
                    }
                    a = i3;
                    break;
                default:
                    a = 0;
                    break;
            }
            if (a != 0) {
                childAt.measure(MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), getChildMeasureSpec(i2, getPaddingTop() + getPaddingBottom(), childAt.getLayoutParams().height));
            }
        }
    }

    private void b(int i) {
        d dVar = (d) this.q.getChildAt(i);
        this.q.removeViewAt(i);
        if (dVar != null) {
            dVar.a();
            this.E.release(dVar);
        }
        requestLayout();
    }

    private void c(int i) {
        if (i != -1) {
            if (getWindowToken() == null || !ViewCompat.isLaidOut(this) || this.q.a()) {
                setScrollPosition(i, 0.0f, true);
                return;
            }
            if (getScrollX() != a(i, 0.0f)) {
                d();
                this.y.setIntValues(new int[]{r0, r1});
                this.y.start();
            }
            this.q.b(i, 300);
        }
    }

    private void d() {
        if (this.y == null) {
            this.y = new ValueAnimator();
            this.y.setInterpolator(a.b);
            this.y.setDuration(300);
            this.y.addUpdateListener(new ba(this));
        }
    }

    void setScrollAnimatorListener(AnimatorListener animatorListener) {
        d();
        this.y.addListener(animatorListener);
    }

    private void setSelectedTabView(int i) {
        int childCount = this.q.getChildCount();
        if (i < childCount) {
            for (int i2 = 0; i2 < childCount; i2++) {
                boolean z;
                View childAt = this.q.getChildAt(i2);
                if (i2 == i) {
                    z = true;
                } else {
                    z = false;
                }
                childAt.setSelected(z);
            }
        }
    }

    void a(Tab tab) {
        a(tab, true);
    }

    void a(Tab tab, boolean z) {
        Tab tab2 = this.p;
        if (tab2 != tab) {
            int position = tab != null ? tab.getPosition() : -1;
            if (z) {
                if ((tab2 == null || tab2.getPosition() == -1) && position != -1) {
                    setScrollPosition(position, 0.0f, true);
                } else {
                    c(position);
                }
                if (position != -1) {
                    setSelectedTabView(position);
                }
            }
            if (tab2 != null) {
                e(tab2);
            }
            this.p = tab;
            if (tab != null) {
                d(tab);
            }
        } else if (tab2 != null) {
            f(tab);
            c(tab.getPosition());
        }
    }

    private void d(@NonNull Tab tab) {
        for (int size = this.w.size() - 1; size >= 0; size--) {
            ((OnTabSelectedListener) this.w.get(size)).onTabSelected(tab);
        }
    }

    private void e(@NonNull Tab tab) {
        for (int size = this.w.size() - 1; size >= 0; size--) {
            ((OnTabSelectedListener) this.w.get(size)).onTabUnselected(tab);
        }
    }

    private void f(@NonNull Tab tab) {
        for (int size = this.w.size() - 1; size >= 0; size--) {
            ((OnTabSelectedListener) this.w.get(size)).onTabReselected(tab);
        }
    }

    private int a(int i, float f) {
        int i2 = 0;
        if (this.l != 0) {
            return 0;
        }
        int width;
        View childAt = this.q.getChildAt(i);
        View childAt2 = i + 1 < this.q.getChildCount() ? this.q.getChildAt(i + 1) : null;
        if (childAt != null) {
            width = childAt.getWidth();
        } else {
            width = 0;
        }
        if (childAt2 != null) {
            i2 = childAt2.getWidth();
        }
        int left = (childAt.getLeft() + (width / 2)) - (getWidth() / 2);
        i2 = (int) ((((float) (i2 + width)) * 0.5f) * f);
        return ViewCompat.getLayoutDirection(this) == 0 ? i2 + left : left - i2;
    }

    private void e() {
        int max;
        if (this.l == 0) {
            max = Math.max(0, this.u - this.a);
        } else {
            max = 0;
        }
        ViewCompat.setPaddingRelative(this.q, max, 0, 0, 0);
        switch (this.l) {
            case 0:
                this.q.setGravity(GravityCompat.START);
                break;
            case 1:
                this.q.setGravity(1);
                break;
        }
        a(true);
    }

    void a(boolean z) {
        for (int i = 0; i < this.q.getChildCount(); i++) {
            View childAt = this.q.getChildAt(i);
            childAt.setMinimumWidth(getTabMinWidth());
            a((LayoutParams) childAt.getLayoutParams());
            if (z) {
                childAt.requestLayout();
            }
        }
    }

    private static ColorStateList a(int i, int i2) {
        r0 = new int[2][];
        int[] iArr = new int[]{SELECTED_STATE_SET, i2};
        r0[1] = EMPTY_STATE_SET;
        iArr[1] = i;
        return new ColorStateList(r0, iArr);
    }

    private int getDefaultHeight() {
        Object obj;
        int size = this.o.size();
        for (int i = 0; i < size; i++) {
            Tab tab = (Tab) this.o.get(i);
            if (tab != null && tab.getIcon() != null && !TextUtils.isEmpty(tab.getText())) {
                obj = 1;
                break;
            }
        }
        obj = null;
        if (obj != null) {
            return 72;
        }
        return 48;
    }

    private int getTabMinWidth() {
        if (this.r != -1) {
            return this.r;
        }
        return this.l == 0 ? this.t : 0;
    }

    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    int getTabMaxWidth() {
        return this.j;
    }
}
