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
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.BaseSavedState;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import java.util.Locale;
import qsbk.app.R;

public class QiuyouCircleTab extends HorizontalScrollView {
    private int A;
    private int B;
    private Locale C;
    private LayoutParams a;
    private LayoutParams b;
    private LinearLayout c;
    private ITabOnClickListener d;
    private int e;
    private String[] f;
    private int g;
    private Paint h;
    private Paint i;
    private int j;
    private int k;
    private int l;
    private boolean m;
    private boolean n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private Typeface x;
    private int y;
    private int z;

    public interface ITabOnClickListener {
        int getTabCount();

        void onTabClickListener(int i);
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new ef();
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

    public QiuyouCircleTab(Context context) {
        this(context, null);
    }

    public QiuyouCircleTab(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public QiuyouCircleTab(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = 0;
        this.f = null;
        this.g = 0;
        this.j = -10066330;
        this.k = 436207616;
        this.l = 436207616;
        this.m = false;
        this.n = true;
        this.o = 52;
        this.p = 4;
        this.q = 1;
        this.r = 12;
        this.s = 12;
        this.t = 1;
        this.u = 12;
        this.v = -1;
        this.w = -10461088;
        this.x = null;
        this.y = 0;
        this.z = 0;
        this.A = R.drawable.background_tab;
        this.B = this.w;
        setFillViewport(true);
        setWillNotDraw(false);
        this.c = new LinearLayout(context);
        this.c.setOrientation(0);
        this.c.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(this.c);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.o = (int) TypedValue.applyDimension(1, (float) this.o, displayMetrics);
        this.p = (int) TypedValue.applyDimension(1, (float) this.p, displayMetrics);
        this.q = (int) TypedValue.applyDimension(1, (float) this.q, displayMetrics);
        this.r = (int) TypedValue.applyDimension(1, (float) this.r, displayMetrics);
        this.s = (int) TypedValue.applyDimension(1, (float) this.s, displayMetrics);
        this.t = (int) TypedValue.applyDimension(1, (float) this.t, displayMetrics);
        this.u = (int) TypedValue.applyDimension(2, (float) this.u, displayMetrics);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PagerSlidingTabStrip);
        this.j = obtainStyledAttributes.getColor(0, this.j);
        this.k = obtainStyledAttributes.getColor(1, this.k);
        this.l = obtainStyledAttributes.getColor(2, this.l);
        this.p = obtainStyledAttributes.getDimensionPixelSize(3, this.p);
        this.q = obtainStyledAttributes.getDimensionPixelSize(4, this.q);
        this.r = obtainStyledAttributes.getDimensionPixelSize(5, this.r);
        this.s = obtainStyledAttributes.getDimensionPixelSize(6, this.s);
        this.A = obtainStyledAttributes.getResourceId(8, this.A);
        this.m = obtainStyledAttributes.getBoolean(9, this.m);
        this.o = obtainStyledAttributes.getDimensionPixelSize(7, this.o);
        this.n = obtainStyledAttributes.getBoolean(10, this.n);
        obtainStyledAttributes.recycle();
        this.h = new Paint();
        this.h.setAntiAlias(true);
        this.h.setStyle(Style.FILL);
        this.i = new Paint();
        this.i.setAntiAlias(true);
        this.i.setStrokeWidth((float) this.t);
        this.a = new LayoutParams(-2, -1);
        this.b = new LayoutParams(0, -1, 1.0f);
        if (this.C == null) {
            this.C = getResources().getConfiguration().locale;
        }
    }

    public void setTabsData(String[] strArr) {
        this.e = strArr.length;
        this.f = strArr;
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged() {
        this.c.removeAllViews();
        for (int i = 0; i < this.e; i++) {
            a(i, this.f[i]);
        }
        a();
    }

    private void a(int i, String str) {
        View textView = new TextView(getContext());
        textView.setText(str);
        textView.setGravity(17);
        textView.setSingleLine();
        a(i, textView);
    }

    private void a(int i, View view) {
        view.setFocusable(true);
        view.setOnClickListener(new ee(this, i));
        view.setPadding(this.s, 0, this.s, 0);
        this.c.addView(view, i, this.m ? this.b : this.a);
    }

    public void setITabOnClickListener(ITabOnClickListener iTabOnClickListener) {
        this.d = iTabOnClickListener;
    }

    private void a() {
        for (int i = 0; i < this.e; i++) {
            View childAt = this.c.getChildAt(i);
            childAt.setBackgroundResource(this.A);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                textView.setTypeface(this.x, this.y);
                if (this.g == i) {
                    textView.setTextSize(0, this.v > 0 ? (float) this.v : (float) this.u);
                    textView.setTextColor(this.B);
                } else {
                    textView.setTextSize(0, (float) this.u);
                    textView.setTextColor(this.w);
                }
                if (this.n) {
                    if (VERSION.SDK_INT >= 14) {
                        textView.setAllCaps(true);
                    } else {
                        textView.setText(textView.getText().toString().toUpperCase(this.C));
                    }
                }
            }
        }
    }

    public void setSelectedTab(int i) {
        int childCount = this.c.getChildCount();
        this.g = i;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = this.c.getChildAt(i2);
            if (childAt instanceof TextView) {
                if (i2 == i) {
                    float f;
                    ((TextView) childAt).setTextColor(this.B);
                    TextView textView = (TextView) childAt;
                    if (this.v > 0) {
                        f = (float) this.v;
                    } else {
                        f = (float) this.u;
                    }
                    textView.setTextSize(0, f);
                } else {
                    ((TextView) childAt).setTextColor(this.w);
                    ((TextView) childAt).setTextSize(0, (float) this.u);
                }
            }
        }
        a(this.g, 0);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInEditMode() && this.e != 0) {
            int height = getHeight();
            this.h.setColor(this.j);
            View childAt = this.c.getChildAt(this.g);
            float left = (float) childAt.getLeft();
            float right = (float) childAt.getRight();
            canvas.drawRect(left, (float) (height - this.p), right, (float) height, this.h);
            this.h.setColor(this.k);
            canvas.drawRect(0.0f, (float) (height - this.q), (float) this.c.getWidth(), (float) height, this.h);
            this.i.setColor(this.l);
            for (int i = 0; i < this.e - 1; i++) {
                childAt = this.c.getChildAt(i);
                canvas.drawLine((float) childAt.getRight(), (float) this.r, (float) childAt.getRight(), (float) (height - this.r), this.i);
            }
        }
    }

    public void setIndicatorColorResource(int i) {
        this.j = getResources().getColor(i);
        invalidate();
    }

    public int getIndicatorColor() {
        return this.j;
    }

    public void setIndicatorColor(int i) {
        this.j = i;
        invalidate();
    }

    public int getIndicatorHeight() {
        return this.p;
    }

    public void setIndicatorHeight(int i) {
        this.p = i;
        invalidate();
    }

    public void setUnderlineColorResource(int i) {
        this.k = getResources().getColor(i);
        invalidate();
    }

    public int getUnderlineColor() {
        return this.k;
    }

    public void setUnderlineColor(int i) {
        this.k = i;
        invalidate();
    }

    public void setDividerColorResource(int i) {
        this.l = getResources().getColor(i);
        invalidate();
    }

    public int getDividerColor() {
        return this.l;
    }

    public void setDividerColor(int i) {
        this.l = i;
        invalidate();
    }

    public int getUnderlineHeight() {
        return this.q;
    }

    public void setUnderlineHeight(int i) {
        this.q = i;
        invalidate();
    }

    public int getDividerPadding() {
        return this.r;
    }

    public void setDividerPadding(int i) {
        this.r = i;
        invalidate();
    }

    public int getScrollOffset() {
        return this.o;
    }

    public void setScrollOffset(int i) {
        this.o = i;
        invalidate();
    }

    public boolean getShouldExpand() {
        return this.m;
    }

    public void setShouldExpand(boolean z) {
        this.m = z;
        requestLayout();
    }

    public boolean isTextAllCaps() {
        return this.n;
    }

    public void setAllCaps(boolean z) {
        this.n = z;
    }

    public void setSelectedTextSize(int i) {
        this.v = i;
        a();
    }

    public void setTextSize(int i, int i2) {
        this.u = i;
        this.v = i2;
        a();
    }

    public int getTextSize() {
        return this.u;
    }

    public void setTextSize(int i) {
        this.u = i;
        a();
    }

    public void setSelectedTabTextColor(int i) {
        this.B = i;
        a();
    }

    public void setTextColorResource(int i) {
        this.w = getResources().getColor(i);
        a();
    }

    public int getTextColor() {
        return this.w;
    }

    public void setTextColor(int i) {
        this.w = i;
        a();
    }

    public void setTypeface(Typeface typeface, int i) {
        this.x = typeface;
        this.y = i;
        a();
    }

    public int getTabBackground() {
        return this.A;
    }

    public void setTabBackground(int i) {
        this.A = i;
    }

    public int getTabPaddingLeftRight() {
        return this.s;
    }

    public void setTabPaddingLeftRight(int i) {
        this.s = i;
        a();
    }

    public void onPageScrolled(int i, float f, int i2) {
        this.g = i;
        a(i, (int) (((float) this.c.getChildAt(i).getWidth()) * f));
        invalidate();
    }

    public void onPageScrollStateChanged(int i, int i2) {
        if (i2 == 0) {
            a(i, 0);
        }
    }

    private void a(int i, int i2) {
        if (this.e != 0) {
            int left = this.c.getChildAt(i).getLeft() + i2;
            if (i > 0 || i2 > 0) {
                left -= this.o;
            }
            if (left != this.z) {
                this.z = left;
                scrollTo(left, 0);
            }
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.g = savedState.a;
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.g;
        return savedState;
    }
}
