package qsbk.app.widget;

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
import qsbk.app.R;

public class PagerSlidingTabStrip extends HorizontalScrollView {
    private static final int[] a = new int[]{16842901, 16842904};
    private int A;
    private int B;
    private int C;
    private int D;
    private Locale E;
    private final a b;
    private LayoutParams c;
    private LayoutParams d;
    public OnPageChangeListener delegatePageListener;
    private LinearLayout e;
    private ViewPager f;
    private ITabOnClickListener g;
    private int h;
    private int i;
    private float j;
    private Paint k;
    private Paint l;
    private int m;
    private int n;
    private int o;
    private boolean p;
    private boolean q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private Typeface z;

    public interface ITabOnClickListener {
        void onTabClickListener(int i);
    }

    public interface IconTabProvider {
        int getPageIconResId(int i);
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new cu();
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
                        ((TextView) this.a.e.getChildAt(i2)).setTextColor(this.a.D);
                    } else {
                        ((TextView) this.a.e.getChildAt(i2)).setTextColor(this.a.y);
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
        this.b = new a();
        this.i = 0;
        this.j = 0.0f;
        this.m = -10066330;
        this.n = 436207616;
        this.o = 436207616;
        this.p = false;
        this.q = true;
        this.r = 52;
        this.s = 4;
        this.t = 1;
        this.u = 12;
        this.v = 24;
        this.w = 1;
        this.x = 12;
        this.y = -10461088;
        this.z = null;
        this.A = 0;
        this.B = 0;
        this.C = R.drawable.background_tab;
        this.D = this.y;
        setFillViewport(true);
        setWillNotDraw(false);
        this.e = new LinearLayout(context);
        this.e.setOrientation(0);
        this.e.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(this.e);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.r = (int) TypedValue.applyDimension(1, (float) this.r, displayMetrics);
        this.s = (int) TypedValue.applyDimension(1, (float) this.s, displayMetrics);
        this.t = (int) TypedValue.applyDimension(1, (float) this.t, displayMetrics);
        this.u = (int) TypedValue.applyDimension(1, (float) this.u, displayMetrics);
        this.v = (int) TypedValue.applyDimension(1, (float) this.v, displayMetrics);
        this.w = (int) TypedValue.applyDimension(1, (float) this.w, displayMetrics);
        this.x = (int) TypedValue.applyDimension(2, (float) this.x, displayMetrics);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a);
        this.x = obtainStyledAttributes.getDimensionPixelSize(0, this.x);
        int color = obtainStyledAttributes.getColor(1, this.y);
        this.y = color;
        this.D = color;
        obtainStyledAttributes.recycle();
        obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PagerSlidingTabStrip);
        this.m = obtainStyledAttributes.getColor(0, this.m);
        this.n = obtainStyledAttributes.getColor(1, this.n);
        this.o = obtainStyledAttributes.getColor(2, this.o);
        this.s = obtainStyledAttributes.getDimensionPixelSize(3, this.s);
        this.t = obtainStyledAttributes.getDimensionPixelSize(4, this.t);
        this.u = obtainStyledAttributes.getDimensionPixelSize(5, this.u);
        this.v = obtainStyledAttributes.getDimensionPixelSize(6, this.v);
        this.C = obtainStyledAttributes.getResourceId(8, this.C);
        this.p = obtainStyledAttributes.getBoolean(9, this.p);
        this.r = obtainStyledAttributes.getDimensionPixelSize(7, this.r);
        this.q = obtainStyledAttributes.getBoolean(10, this.q);
        obtainStyledAttributes.recycle();
        this.k = new Paint();
        this.k.setAntiAlias(true);
        this.k.setStyle(Style.FILL);
        this.l = new Paint();
        this.l.setAntiAlias(true);
        this.l.setStrokeWidth((float) this.w);
        this.c = new LayoutParams(-2, -1);
        this.d = new LayoutParams(0, -1, 1.0f);
        if (this.E == null) {
            this.E = getResources().getConfiguration().locale;
        }
    }

    public void setViewPager(ViewPager viewPager, ITabOnClickListener iTabOnClickListener) {
        this.f = viewPager;
        this.g = iTabOnClickListener;
        if (viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        viewPager.setOnPageChangeListener(this.b);
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
        getViewTreeObserver().addOnGlobalLayoutListener(new cs(this));
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
        view.setOnClickListener(new ct(this, i));
        view.setPadding(this.v, 0, this.v, 0);
        this.e.addView(view, i, this.p ? this.d : this.c);
    }

    public void setTitleAlpha(int i, float f, float f2) {
        if (this.e != null && this.e.getChildCount() > i) {
            for (int i2 = 0; i2 < this.e.getChildCount(); i2++) {
                View childAt = this.e.getChildAt(i2);
                if (childAt instanceof TextView) {
                    if (i2 == i) {
                        childAt.setAlpha(f);
                    } else {
                        childAt.setAlpha(f2);
                    }
                }
            }
        }
    }

    private void a() {
        for (int i = 0; i < this.h; i++) {
            View childAt = this.e.getChildAt(i);
            childAt.setBackgroundResource(this.C);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                textView.setTextSize(0, (float) this.x);
                textView.setTypeface(this.z, this.A);
                if (this.i == i) {
                    textView.setTextColor(this.D);
                } else {
                    textView.setTextColor(this.y);
                }
                if (this.q) {
                    if (VERSION.SDK_INT >= 14) {
                        textView.setAllCaps(true);
                    } else {
                        textView.setText(textView.getText().toString().toUpperCase(this.E));
                    }
                }
            }
        }
    }

    private void b(int i, int i2) {
        if (this.h != 0) {
            int left = this.e.getChildAt(i).getLeft() + i2;
            if (i > 0 || i2 > 0) {
                left -= this.r;
            }
            if (left != this.B) {
                this.B = left;
                scrollTo(left, 0);
            }
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInEditMode() && this.h != 0) {
            int height = getHeight();
            this.k.setColor(this.m);
            View childAt = this.e.getChildAt(this.i);
            float left = (float) childAt.getLeft();
            float right = (float) childAt.getRight();
            if (this.j > 0.0f && this.i < this.h - 1) {
                childAt = this.e.getChildAt(this.i + 1);
                float left2 = (float) childAt.getLeft();
                left = (left * (1.0f - this.j)) + (left2 * this.j);
                right = (((float) childAt.getRight()) * this.j) + ((1.0f - this.j) * right);
            }
            canvas.drawRect(left, (float) (height - this.s), right, (float) height, this.k);
            this.k.setColor(this.n);
            canvas.drawRect(0.0f, (float) (height - this.t), (float) this.e.getWidth(), (float) height, this.k);
            this.l.setColor(this.o);
            for (int i = 0; i < this.h - 1; i++) {
                childAt = this.e.getChildAt(i);
                canvas.drawLine((float) childAt.getRight(), (float) this.u, (float) childAt.getRight(), (float) (height - this.u), this.l);
            }
        }
    }

    public void setIndicatorColorResource(int i) {
        this.m = getResources().getColor(i);
        invalidate();
    }

    public int getIndicatorColor() {
        return this.m;
    }

    public void setIndicatorColor(int i) {
        this.m = i;
        invalidate();
    }

    public int getIndicatorHeight() {
        return this.s;
    }

    public void setIndicatorHeight(int i) {
        this.s = i;
        invalidate();
    }

    public void setUnderlineColorResource(int i) {
        this.n = getResources().getColor(i);
        invalidate();
    }

    public int getUnderlineColor() {
        return this.n;
    }

    public void setUnderlineColor(int i) {
        this.n = i;
        invalidate();
    }

    public void setDividerColorResource(int i) {
        this.o = getResources().getColor(i);
        invalidate();
    }

    public int getDividerColor() {
        return this.o;
    }

    public void setDividerColor(int i) {
        this.o = i;
        invalidate();
    }

    public int getUnderlineHeight() {
        return this.t;
    }

    public void setUnderlineHeight(int i) {
        this.t = i;
        invalidate();
    }

    public int getDividerPadding() {
        return this.u;
    }

    public void setDividerPadding(int i) {
        this.u = i;
        invalidate();
    }

    public int getScrollOffset() {
        return this.r;
    }

    public void setScrollOffset(int i) {
        this.r = i;
        invalidate();
    }

    public boolean getShouldExpand() {
        return this.p;
    }

    public void setShouldExpand(boolean z) {
        this.p = z;
        requestLayout();
    }

    public boolean isTextAllCaps() {
        return this.q;
    }

    public void setAllCaps(boolean z) {
        this.q = z;
    }

    public int getTextSize() {
        return this.x;
    }

    public void setTextSize(int i) {
        this.x = i;
        a();
    }

    public void setSelectedTabTextColor(int i) {
        this.D = i;
        a();
    }

    public void setTextColorResource(int i) {
        this.y = getResources().getColor(i);
        a();
    }

    public int getTextColor() {
        return this.y;
    }

    public void setTextColor(int i) {
        this.y = i;
        a();
    }

    public void setTypeface(Typeface typeface, int i) {
        this.z = typeface;
        this.A = i;
        a();
    }

    public int getTabBackground() {
        return this.C;
    }

    public void setTabBackground(int i) {
        this.C = i;
    }

    public int getTabPaddingLeftRight() {
        return this.v;
    }

    public void setTabPaddingLeftRight(int i) {
        this.v = i;
        a();
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
