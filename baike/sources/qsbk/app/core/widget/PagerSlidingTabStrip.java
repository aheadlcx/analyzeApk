package qsbk.app.core.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.BaseSavedState;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import java.util.Locale;
import qsbk.app.core.R;

public class PagerSlidingTabStrip extends HorizontalScrollView {
    private static final int[] a = new int[]{16842901, 16842904};
    private static int k = 2;
    private int A;
    private Typeface B;
    private int C;
    private int D;
    private int E;
    private int F;
    private Locale G;
    private LayoutParams b;
    private LayoutParams c;
    private final a d;
    public OnPageChangeListener delegatePageListener;
    private LinearLayout e;
    private ViewPager f;
    private ITabOnClickListener g;
    private int h;
    private int i;
    private float j;
    private Paint l;
    private Paint m;
    private int n;
    private int o;
    private int p;
    private boolean q;
    private boolean r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;

    public interface ITabOnClickListener {
        void onTabClickListener(int i);
    }

    public interface IconTabProvider {
        int getPageIconResId(int i);
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new s();
        int a;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
        }
    }

    public interface ViewTabProvider {
        View getTabView(int i);
    }

    private class a implements OnPageChangeListener {
        final /* synthetic */ PagerSlidingTabStrip a;

        private a(PagerSlidingTabStrip pagerSlidingTabStrip) {
            this.a = pagerSlidingTabStrip;
        }

        public void onPageScrolled(int i, float f, int i2) {
            this.a.i = i;
            this.a.j = f;
            this.a.b(i, (int) (((float) this.a.e.getChildAt(i).getWidth()) * f));
            this.a.invalidate();
            if (this.a.delegatePageListener != null) {
                this.a.delegatePageListener.onPageScrolled(i, f, i2);
            }
        }

        public void onPageScrollStateChanged(int i) {
            if (i == 0) {
                this.a.b(this.a.f.getCurrentItem(), 0);
            }
            if (this.a.delegatePageListener != null) {
                this.a.delegatePageListener.onPageScrollStateChanged(i);
            }
        }

        public void onPageSelected(int i) {
            int childCount = this.a.e.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                if (this.a.e.getChildAt(i2) instanceof TextView) {
                    if (i2 == i) {
                        ((TextView) this.a.e.getChildAt(i2)).setTextColor(this.a.F);
                    } else {
                        ((TextView) this.a.e.getChildAt(i2)).setTextColor(this.a.A);
                    }
                }
            }
            if (this.a.delegatePageListener != null) {
                this.a.delegatePageListener.onPageSelected(i);
            }
        }
    }

    public PagerSlidingTabStrip(Context context) {
        this(context, null);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new a();
        this.i = 0;
        this.j = 0.0f;
        this.n = -10066330;
        this.o = 436207616;
        this.p = 436207616;
        this.q = false;
        this.r = true;
        this.s = 52;
        this.t = 4;
        this.u = 0;
        this.v = 1;
        this.w = 12;
        this.x = 24;
        this.y = 1;
        this.z = 12;
        this.A = -10461088;
        this.B = null;
        this.C = 0;
        this.D = 0;
        this.E = R.drawable.background_tab;
        this.F = this.A;
        setFillViewport(true);
        setWillNotDraw(false);
        this.e = new LinearLayout(context);
        this.e.setOrientation(0);
        this.e.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(this.e);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.s = (int) TypedValue.applyDimension(1, (float) this.s, displayMetrics);
        this.t = (int) TypedValue.applyDimension(1, (float) this.t, displayMetrics);
        this.v = (int) TypedValue.applyDimension(1, (float) this.v, displayMetrics);
        this.w = (int) TypedValue.applyDimension(1, (float) this.w, displayMetrics);
        this.x = (int) TypedValue.applyDimension(1, (float) this.x, displayMetrics);
        this.y = (int) TypedValue.applyDimension(1, (float) this.y, displayMetrics);
        this.z = (int) TypedValue.applyDimension(2, (float) this.z, displayMetrics);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a);
        this.z = obtainStyledAttributes.getDimensionPixelSize(0, this.z);
        int color = obtainStyledAttributes.getColor(1, this.A);
        this.A = color;
        this.F = color;
        obtainStyledAttributes.recycle();
        obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PagerSlidingTabStrip);
        this.n = obtainStyledAttributes.getColor(R.styleable.PagerSlidingTabStrip_pstsIndicatorColor, this.n);
        this.o = obtainStyledAttributes.getColor(R.styleable.PagerSlidingTabStrip_pstsUnderlineColor, this.o);
        this.p = obtainStyledAttributes.getColor(R.styleable.PagerSlidingTabStrip_pstsDividerColor, this.p);
        this.t = obtainStyledAttributes.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsIndicatorHeight, this.t);
        this.v = obtainStyledAttributes.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsUnderlineHeight, this.v);
        this.w = obtainStyledAttributes.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsDividerPadding, this.w);
        this.x = obtainStyledAttributes.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsTabPaddingLeftRight, this.x);
        this.E = obtainStyledAttributes.getResourceId(R.styleable.PagerSlidingTabStrip_pstsTabBackground, this.E);
        this.q = obtainStyledAttributes.getBoolean(R.styleable.PagerSlidingTabStrip_pstsShouldExpand, this.q);
        this.s = obtainStyledAttributes.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsScrollOffset, this.s);
        this.r = obtainStyledAttributes.getBoolean(R.styleable.PagerSlidingTabStrip_pstsTextAllCaps, this.r);
        obtainStyledAttributes.recycle();
        this.l = new Paint();
        this.l.setAntiAlias(true);
        this.l.setStyle(Style.FILL);
        this.m = new Paint();
        this.m.setAntiAlias(true);
        this.m.setStrokeWidth((float) this.y);
        this.b = new LayoutParams(-2, -1);
        this.c = new LayoutParams(0, -1, 1.0f);
        if (this.G == null) {
            this.G = getResources().getConfiguration().locale;
        }
    }

    public void setViewPager(ViewPager viewPager, ITabOnClickListener iTabOnClickListener) {
        this.f = viewPager;
        this.g = iTabOnClickListener;
        if (viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        viewPager.setOnPageChangeListener(this.d);
        notifyDataSetChanged();
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.delegatePageListener = onPageChangeListener;
    }

    public void notifyDataSetChanged() {
        this.e.removeAllViews();
        this.h = this.f.getAdapter().getCount();
        for (int i = 0; i < this.h; i++) {
            if (this.f.getAdapter() instanceof IconTabProvider) {
                a(i, ((IconTabProvider) this.f.getAdapter()).getPageIconResId(i));
            } else if (this.f.getAdapter() instanceof ViewTabProvider) {
                a(i, ((ViewTabProvider) this.f.getAdapter()).getTabView(i));
            } else {
                a(i, this.f.getAdapter().getPageTitle(i).toString());
            }
        }
        a();
        getViewTreeObserver().addOnGlobalLayoutListener(new q(this));
    }

    private void a(int i, String str) {
        View textView = new TextView(getContext());
        textView.setText(str);
        textView.setGravity(17);
        textView.setSingleLine();
        a(i, textView);
    }

    private void a(int i, int i2) {
        View imageButton = new ImageButton(getContext());
        imageButton.setImageResource(i2);
        a(i, imageButton);
    }

    private void a(int i, View view) {
        view.setFocusable(true);
        view.setOnClickListener(new r(this, i));
        view.setPadding(this.x, 0, this.x, 0);
        this.e.addView(view, i, this.q ? this.c : this.b);
    }

    private void a() {
        for (int i = 0; i < this.h; i++) {
            View childAt = this.e.getChildAt(i);
            childAt.setBackgroundResource(this.E);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                textView.setTextSize(0, (float) this.z);
                textView.setTypeface(this.B, this.C);
                if (this.i == i) {
                    textView.setTextColor(this.F);
                } else {
                    textView.setTextColor(this.A);
                }
                if (this.r) {
                    if (VERSION.SDK_INT >= 14) {
                        textView.setAllCaps(true);
                    } else {
                        textView.setText(textView.getText().toString().toUpperCase(this.G));
                    }
                }
            }
        }
    }

    private void b(int i, int i2) {
        if (this.h != 0) {
            int left = this.e.getChildAt(i).getLeft() + i2;
            if (i > 0 || i2 > 0) {
                left -= this.s;
            }
            if (left != this.D) {
                this.D = left;
                scrollTo(left, 0);
            }
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInEditMode() && this.h != 0) {
            int height = getHeight();
            this.l.setColor(this.n);
            View childAt = this.e.getChildAt(this.i);
            float left = (float) childAt.getLeft();
            float right = (float) childAt.getRight();
            if (this.j > 0.0f && this.i < this.h - 1) {
                View childAt2 = this.e.getChildAt(this.i + 1);
                float left2 = (float) childAt2.getLeft();
                left = (left * (1.0f - this.j)) + (left2 * this.j);
                right = (right * (1.0f - this.j)) + (((float) childAt2.getRight()) * this.j);
            }
            canvas.drawRect(left + ((float) this.u), (float) (height - this.t), right - ((float) this.u), (float) height, this.l);
            this.l.setColor(this.o);
            canvas.drawRect(0.0f, (float) (height - this.v), (float) this.e.getWidth(), (float) height, this.l);
            this.m.setColor(this.p);
            for (int i = 0; i < this.h - 1; i++) {
                childAt = this.e.getChildAt(i);
                canvas.drawLine((float) childAt.getRight(), (float) this.w, (float) childAt.getRight(), (float) (height - this.w), this.m);
            }
        }
    }

    public void setIndicatorColor(int i) {
        this.n = i;
        invalidate();
    }

    public void setIndicatorColorResource(int i) {
        this.n = getResources().getColor(i);
        invalidate();
    }

    public int getIndicatorColor() {
        return this.n;
    }

    public void setIndicatorHeight(int i) {
        this.t = i;
        invalidate();
    }

    public int getIndicatorHeight() {
        return this.t;
    }

    public void setIndicatorWidthPadding(int i) {
        this.u = i;
        invalidate();
    }

    public void setUnderlineColor(int i) {
        this.o = i;
        invalidate();
    }

    public void setUnderlineColorResource(int i) {
        this.o = getResources().getColor(i);
        invalidate();
    }

    public int getUnderlineColor() {
        return this.o;
    }

    public void setDividerColor(int i) {
        this.p = i;
        invalidate();
    }

    public void setDividerColorResource(int i) {
        this.p = getResources().getColor(i);
        invalidate();
    }

    public int getDividerColor() {
        return this.p;
    }

    public void setUnderlineHeight(int i) {
        this.v = i;
        invalidate();
    }

    public int getUnderlineHeight() {
        return this.v;
    }

    public void setDividerPadding(int i) {
        this.w = i;
        invalidate();
    }

    public int getDividerPadding() {
        return this.w;
    }

    public void setScrollOffset(int i) {
        this.s = i;
        invalidate();
    }

    public int getScrollOffset() {
        return this.s;
    }

    public void setShouldExpand(boolean z) {
        this.q = z;
        requestLayout();
    }

    public boolean getShouldExpand() {
        return this.q;
    }

    public boolean isTextAllCaps() {
        return this.r;
    }

    public void setAllCaps(boolean z) {
        this.r = z;
    }

    public void setTextSize(int i) {
        this.z = i;
        a();
    }

    public int getTextSize() {
        return this.z;
    }

    public void setTextColor(int i) {
        this.A = i;
        a();
    }

    public void setSelectedTabTextColor(int i) {
        this.F = i;
        a();
    }

    public void setSelectedTabTextColorResource(int i) {
        this.F = getResources().getColor(i);
        a();
    }

    public void setTextColorResource(int i) {
        this.A = getResources().getColor(i);
        a();
    }

    public int getTextColor() {
        return this.A;
    }

    public void setTypeface(Typeface typeface, int i) {
        this.B = typeface;
        this.C = i;
        a();
    }

    public void setTabBackground(int i) {
        this.E = i;
    }

    public int getTabBackground() {
        return this.E;
    }

    public void setTabPaddingLeftRight(int i) {
        this.x = i;
        a();
    }

    public int getTabPaddingLeftRight() {
        return this.x;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.i = savedState.a;
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.i;
        return savedState;
    }
}
