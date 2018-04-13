package com.google.android.flexbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.ArrayList;
import java.util.List;

public class FlexboxLayout extends ViewGroup implements a {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    @Nullable
    private Drawable f;
    @Nullable
    private Drawable g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int[] l;
    private SparseIntArray m;
    private d n;
    private List<c> o;
    private a p;

    public static class a extends MarginLayoutParams implements b {
        public static final Creator<a> CREATOR = new Creator<a>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public a a(Parcel parcel) {
                return new a(parcel);
            }

            public a[] a(int i) {
                return new a[i];
            }
        };
        private int a = 1;
        private float b = 0.0f;
        private float c = 1.0f;
        private int d = -1;
        private float e = -1.0f;
        private int f;
        private int g;
        private int h = ViewCompat.MEASURED_SIZE_MASK;
        private int i = ViewCompat.MEASURED_SIZE_MASK;
        private boolean j;

        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.google.android.flexbox.e.a.FlexboxLayout_Layout);
            this.a = obtainStyledAttributes.getInt(com.google.android.flexbox.e.a.FlexboxLayout_Layout_layout_order, 1);
            this.b = obtainStyledAttributes.getFloat(com.google.android.flexbox.e.a.FlexboxLayout_Layout_layout_flexGrow, 0.0f);
            this.c = obtainStyledAttributes.getFloat(com.google.android.flexbox.e.a.FlexboxLayout_Layout_layout_flexShrink, 1.0f);
            this.d = obtainStyledAttributes.getInt(com.google.android.flexbox.e.a.FlexboxLayout_Layout_layout_alignSelf, -1);
            this.e = obtainStyledAttributes.getFraction(com.google.android.flexbox.e.a.FlexboxLayout_Layout_layout_flexBasisPercent, 1, 1, -1.0f);
            this.f = obtainStyledAttributes.getDimensionPixelSize(com.google.android.flexbox.e.a.FlexboxLayout_Layout_layout_minWidth, 0);
            this.g = obtainStyledAttributes.getDimensionPixelSize(com.google.android.flexbox.e.a.FlexboxLayout_Layout_layout_minHeight, 0);
            this.h = obtainStyledAttributes.getDimensionPixelSize(com.google.android.flexbox.e.a.FlexboxLayout_Layout_layout_maxWidth, ViewCompat.MEASURED_SIZE_MASK);
            this.i = obtainStyledAttributes.getDimensionPixelSize(com.google.android.flexbox.e.a.FlexboxLayout_Layout_layout_maxHeight, ViewCompat.MEASURED_SIZE_MASK);
            this.j = obtainStyledAttributes.getBoolean(com.google.android.flexbox.e.a.FlexboxLayout_Layout_layout_wrapBefore, false);
            obtainStyledAttributes.recycle();
        }

        public a(a aVar) {
            super(aVar);
            this.a = aVar.a;
            this.b = aVar.b;
            this.c = aVar.c;
            this.d = aVar.d;
            this.e = aVar.e;
            this.f = aVar.f;
            this.g = aVar.g;
            this.h = aVar.h;
            this.i = aVar.i;
            this.j = aVar.j;
        }

        public a(LayoutParams layoutParams) {
            super(layoutParams);
        }

        public a(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public int a() {
            return this.width;
        }

        public int b() {
            return this.height;
        }

        public int c() {
            return this.a;
        }

        public float d() {
            return this.b;
        }

        public float e() {
            return this.c;
        }

        public int f() {
            return this.d;
        }

        public int g() {
            return this.f;
        }

        public int h() {
            return this.g;
        }

        public int i() {
            return this.h;
        }

        public int j() {
            return this.i;
        }

        public boolean k() {
            return this.j;
        }

        public float l() {
            return this.e;
        }

        public int m() {
            return this.leftMargin;
        }

        public int n() {
            return this.topMargin;
        }

        public int o() {
            return this.rightMargin;
        }

        public int p() {
            return this.bottomMargin;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            parcel.writeFloat(this.b);
            parcel.writeFloat(this.c);
            parcel.writeInt(this.d);
            parcel.writeFloat(this.e);
            parcel.writeInt(this.f);
            parcel.writeInt(this.g);
            parcel.writeInt(this.h);
            parcel.writeInt(this.i);
            parcel.writeByte(this.j ? (byte) 1 : (byte) 0);
            parcel.writeInt(this.bottomMargin);
            parcel.writeInt(this.leftMargin);
            parcel.writeInt(this.rightMargin);
            parcel.writeInt(this.topMargin);
            parcel.writeInt(this.height);
            parcel.writeInt(this.width);
        }

        protected a(Parcel parcel) {
            boolean z = true;
            super(0, 0);
            this.a = parcel.readInt();
            this.b = parcel.readFloat();
            this.c = parcel.readFloat();
            this.d = parcel.readInt();
            this.e = parcel.readFloat();
            this.f = parcel.readInt();
            this.g = parcel.readInt();
            this.h = parcel.readInt();
            this.i = parcel.readInt();
            if (parcel.readByte() == (byte) 0) {
                z = false;
            }
            this.j = z;
            this.bottomMargin = parcel.readInt();
            this.leftMargin = parcel.readInt();
            this.rightMargin = parcel.readInt();
            this.topMargin = parcel.readInt();
            this.height = parcel.readInt();
            this.width = parcel.readInt();
        }
    }

    public /* synthetic */ LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return a(attributeSet);
    }

    public FlexboxLayout(Context context) {
        this(context, null);
    }

    public FlexboxLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FlexboxLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.n = new d(this);
        this.o = new ArrayList();
        this.p = new a();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.google.android.flexbox.e.a.FlexboxLayout, i, 0);
        this.a = obtainStyledAttributes.getInt(com.google.android.flexbox.e.a.FlexboxLayout_flexDirection, 0);
        this.b = obtainStyledAttributes.getInt(com.google.android.flexbox.e.a.FlexboxLayout_flexWrap, 0);
        this.c = obtainStyledAttributes.getInt(com.google.android.flexbox.e.a.FlexboxLayout_justifyContent, 0);
        this.d = obtainStyledAttributes.getInt(com.google.android.flexbox.e.a.FlexboxLayout_alignItems, 4);
        this.e = obtainStyledAttributes.getInt(com.google.android.flexbox.e.a.FlexboxLayout_alignContent, 5);
        Drawable drawable = obtainStyledAttributes.getDrawable(com.google.android.flexbox.e.a.FlexboxLayout_dividerDrawable);
        if (drawable != null) {
            setDividerDrawableHorizontal(drawable);
            setDividerDrawableVertical(drawable);
        }
        drawable = obtainStyledAttributes.getDrawable(com.google.android.flexbox.e.a.FlexboxLayout_dividerDrawableHorizontal);
        if (drawable != null) {
            setDividerDrawableHorizontal(drawable);
        }
        drawable = obtainStyledAttributes.getDrawable(com.google.android.flexbox.e.a.FlexboxLayout_dividerDrawableVertical);
        if (drawable != null) {
            setDividerDrawableVertical(drawable);
        }
        int i2 = obtainStyledAttributes.getInt(com.google.android.flexbox.e.a.FlexboxLayout_showDivider, 0);
        if (i2 != 0) {
            this.i = i2;
            this.h = i2;
        }
        i2 = obtainStyledAttributes.getInt(com.google.android.flexbox.e.a.FlexboxLayout_showDividerVertical, 0);
        if (i2 != 0) {
            this.i = i2;
        }
        i2 = obtainStyledAttributes.getInt(com.google.android.flexbox.e.a.FlexboxLayout_showDividerHorizontal, 0);
        if (i2 != 0) {
            this.h = i2;
        }
        obtainStyledAttributes.recycle();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.m == null) {
            this.m = new SparseIntArray(getChildCount());
        }
        if (this.n.b(this.m)) {
            this.l = this.n.a(this.m);
        }
        switch (this.a) {
            case 0:
            case 1:
                a(i, i2);
                return;
            case 2:
            case 3:
                b(i, i2);
                return;
            default:
                throw new IllegalStateException("Invalid value for the flex direction is set: " + this.a);
        }
    }

    public int getFlexItemCount() {
        return getChildCount();
    }

    public View a(int i) {
        return getChildAt(i);
    }

    public View c(int i) {
        if (i < 0 || i >= this.l.length) {
            return null;
        }
        return getChildAt(this.l[i]);
    }

    public View b(int i) {
        return c(i);
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        if (this.m == null) {
            this.m = new SparseIntArray(getChildCount());
        }
        this.l = this.n.a(view, i, layoutParams, this.m);
        super.addView(view, i, layoutParams);
    }

    private void a(int i, int i2) {
        this.o.clear();
        this.p.a();
        this.n.a(this.p, i, i2);
        this.o = this.p.a;
        this.n.a(i, i2);
        if (this.d == 3) {
            for (c cVar : this.o) {
                int i3 = Integer.MIN_VALUE;
                for (int i4 = 0; i4 < cVar.h; i4++) {
                    View c = c(cVar.o + i4);
                    if (!(c == null || c.getVisibility() == 8)) {
                        a aVar = (a) c.getLayoutParams();
                        if (this.b != 2) {
                            i3 = Math.max(i3, aVar.bottomMargin + (c.getMeasuredHeight() + Math.max(cVar.l - c.getBaseline(), aVar.topMargin)));
                        } else {
                            i3 = Math.max(i3, (aVar.topMargin + c.getMeasuredHeight()) + Math.max((cVar.l - c.getMeasuredHeight()) + c.getBaseline(), aVar.bottomMargin));
                        }
                    }
                }
                cVar.g = i3;
            }
        }
        this.n.b(i, i2, getPaddingTop() + getPaddingBottom());
        this.n.a();
        a(this.a, i, i2, this.p.b);
    }

    private void b(int i, int i2) {
        this.o.clear();
        this.p.a();
        this.n.b(this.p, i, i2);
        this.o = this.p.a;
        this.n.a(i, i2);
        this.n.b(i, i2, getPaddingLeft() + getPaddingRight());
        this.n.a();
        a(this.a, i, i2, this.p.b);
    }

    private void a(int i, int i2, int i3, int i4) {
        int paddingBottom;
        int largestMainSize;
        int mode = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i2);
        int mode2 = MeasureSpec.getMode(i3);
        int size2 = MeasureSpec.getSize(i3);
        switch (i) {
            case 0:
            case 1:
                paddingBottom = getPaddingBottom() + (getSumOfCrossSize() + getPaddingTop());
                largestMainSize = getLargestMainSize();
                break;
            case 2:
            case 3:
                paddingBottom = getLargestMainSize();
                largestMainSize = (getSumOfCrossSize() + getPaddingLeft()) + getPaddingRight();
                break;
            default:
                throw new IllegalArgumentException("Invalid flex direction: " + i);
        }
        switch (mode) {
            case Integer.MIN_VALUE:
                if (size < largestMainSize) {
                    i4 = View.combineMeasuredStates(i4, 16777216);
                } else {
                    size = largestMainSize;
                }
                mode = View.resolveSizeAndState(size, i2, i4);
                size = i4;
                break;
            case 0:
                mode = View.resolveSizeAndState(largestMainSize, i2, i4);
                size = i4;
                break;
            case 1073741824:
                if (size < largestMainSize) {
                    i4 = View.combineMeasuredStates(i4, 16777216);
                }
                mode = View.resolveSizeAndState(size, i2, i4);
                size = i4;
                break;
            default:
                throw new IllegalStateException("Unknown width mode is set: " + mode);
        }
        switch (mode2) {
            case Integer.MIN_VALUE:
                if (size2 < paddingBottom) {
                    largestMainSize = View.combineMeasuredStates(size, 256);
                    size = size2;
                } else {
                    largestMainSize = size;
                    size = paddingBottom;
                }
                size = View.resolveSizeAndState(size, i3, largestMainSize);
                break;
            case 0:
                size = View.resolveSizeAndState(paddingBottom, i3, size);
                break;
            case 1073741824:
                if (size2 < paddingBottom) {
                    size = View.combineMeasuredStates(size, 256);
                }
                size = View.resolveSizeAndState(size2, i3, size);
                break;
            default:
                throw new IllegalStateException("Unknown height mode is set: " + mode2);
        }
        setMeasuredDimension(mode, size);
    }

    public int getLargestMainSize() {
        int i = Integer.MIN_VALUE;
        for (c cVar : this.o) {
            i = Math.max(i, cVar.e);
        }
        return i;
    }

    public int getSumOfCrossSize() {
        int size = this.o.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            c cVar = (c) this.o.get(i2);
            if (d(i2)) {
                if (a()) {
                    i += this.j;
                } else {
                    i += this.k;
                }
            }
            if (f(i2)) {
                if (a()) {
                    i += this.j;
                } else {
                    i += this.k;
                }
            }
            i += cVar.g;
        }
        return i;
    }

    public boolean a() {
        return this.a == 0 || this.a == 1;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2 = false;
        boolean z3 = true;
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        boolean z4;
        boolean z5;
        switch (this.a) {
            case 0:
                a(layoutDirection == 1, i, i2, i3, i4);
                return;
            case 1:
                if (layoutDirection != 1) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                a(z4, i, i2, i3, i4);
                return;
            case 2:
                if (layoutDirection == 1) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (this.b == 2) {
                    if (z5) {
                        z3 = false;
                    }
                    z4 = z3;
                } else {
                    z4 = z5;
                }
                a(z4, false, i, i2, i3, i4);
                return;
            case 3:
                if (layoutDirection == 1) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (this.b == 2) {
                    if (!z5) {
                        z2 = true;
                    }
                    z4 = z2;
                } else {
                    z4 = z5;
                }
                a(z4, true, i, i2, i3, i4);
                return;
            default:
                throw new IllegalStateException("Invalid flex direction is set: " + this.a);
        }
    }

    private void a(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int i5 = i3 - i;
        int paddingBottom = (i4 - i2) - getPaddingBottom();
        int paddingTop = getPaddingTop();
        int size = this.o.size();
        for (int i6 = 0; i6 < size; i6++) {
            float f;
            float f2;
            c cVar = (c) this.o.get(i6);
            if (d(i6)) {
                paddingBottom -= this.j;
                paddingTop += this.j;
            }
            float f3 = 0.0f;
            switch (this.c) {
                case 0:
                    f = (float) paddingLeft;
                    f2 = (float) (i5 - paddingRight);
                    break;
                case 1:
                    f = (float) ((i5 - cVar.e) + paddingRight);
                    f2 = (float) (cVar.e - paddingLeft);
                    break;
                case 2:
                    f = (((float) (i5 - cVar.e)) / 2.0f) + ((float) paddingLeft);
                    f2 = ((float) (i5 - paddingRight)) - (((float) (i5 - cVar.e)) / 2.0f);
                    break;
                case 3:
                    f = (float) paddingLeft;
                    int c = cVar.c();
                    f3 = ((float) (i5 - cVar.e)) / (c != 1 ? (float) (c - 1) : 1.0f);
                    f2 = (float) (i5 - paddingRight);
                    break;
                case 4:
                    int c2 = cVar.c();
                    if (c2 != 0) {
                        f3 = ((float) (i5 - cVar.e)) / ((float) c2);
                    }
                    f = (f3 / 2.0f) + ((float) paddingLeft);
                    f2 = ((float) (i5 - paddingRight)) - (f3 / 2.0f);
                    break;
                default:
                    throw new IllegalStateException("Invalid justifyContent is set: " + this.c);
            }
            float max = Math.max(f3, 0.0f);
            int i7 = 0;
            float f4 = f;
            f = f2;
            while (i7 < cVar.h) {
                int i8 = cVar.o + i7;
                View c3 = c(i8);
                if (c3 != null) {
                    if (c3.getVisibility() == 8) {
                        f2 = f;
                        f = f4;
                    } else {
                        float f5;
                        float f6;
                        a aVar = (a) c3.getLayoutParams();
                        f4 += (float) aVar.leftMargin;
                        f3 = f - ((float) aVar.rightMargin);
                        int i9 = 0;
                        int i10 = 0;
                        if (c(i8, i7)) {
                            i9 = this.k;
                            f5 = f3 - ((float) i9);
                            f6 = ((float) i9) + f4;
                        } else {
                            f5 = f3;
                            f6 = f4;
                        }
                        if (i7 == cVar.h - 1 && (this.i & 4) > 0) {
                            i10 = this.k;
                        }
                        if (this.b == 2) {
                            if (z) {
                                this.n.a(c3, cVar, Math.round(f5) - c3.getMeasuredWidth(), paddingBottom - c3.getMeasuredHeight(), Math.round(f5), paddingBottom);
                            } else {
                                this.n.a(c3, cVar, Math.round(f6), paddingBottom - c3.getMeasuredHeight(), Math.round(f6) + c3.getMeasuredWidth(), paddingBottom);
                            }
                        } else if (z) {
                            this.n.a(c3, cVar, Math.round(f5) - c3.getMeasuredWidth(), paddingTop, Math.round(f5), paddingTop + c3.getMeasuredHeight());
                        } else {
                            this.n.a(c3, cVar, Math.round(f6), paddingTop, Math.round(f6) + c3.getMeasuredWidth(), paddingTop + c3.getMeasuredHeight());
                        }
                        f4 = f6 + ((((float) c3.getMeasuredWidth()) + max) + ((float) aVar.rightMargin));
                        f = f5 - ((((float) c3.getMeasuredWidth()) + max) + ((float) aVar.leftMargin));
                        if (z) {
                            cVar.a(c3, i10, 0, i9, 0);
                            f2 = f;
                            f = f4;
                        } else {
                            cVar.a(c3, i9, 0, i10, 0);
                        }
                    }
                    i7++;
                    f4 = f;
                    f = f2;
                }
                f2 = f;
                f = f4;
                i7++;
                f4 = f;
                f = f2;
            }
            paddingTop += cVar.g;
            paddingBottom -= cVar.g;
        }
    }

    private void a(boolean z, boolean z2, int i, int i2, int i3, int i4) {
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingRight = getPaddingRight();
        int paddingLeft = getPaddingLeft();
        int i5 = i4 - i2;
        int i6 = (i3 - i) - paddingRight;
        int size = this.o.size();
        for (int i7 = 0; i7 < size; i7++) {
            float f;
            float f2;
            c cVar = (c) this.o.get(i7);
            if (d(i7)) {
                paddingLeft += this.k;
                i6 -= this.k;
            }
            float f3 = 0.0f;
            switch (this.c) {
                case 0:
                    f = (float) paddingTop;
                    f2 = (float) (i5 - paddingBottom);
                    break;
                case 1:
                    f = (float) ((i5 - cVar.e) + paddingBottom);
                    f2 = (float) (cVar.e - paddingTop);
                    break;
                case 2:
                    f = (((float) (i5 - cVar.e)) / 2.0f) + ((float) paddingTop);
                    f2 = ((float) (i5 - paddingBottom)) - (((float) (i5 - cVar.e)) / 2.0f);
                    break;
                case 3:
                    f = (float) paddingTop;
                    paddingRight = cVar.c();
                    f3 = ((float) (i5 - cVar.e)) / (paddingRight != 1 ? (float) (paddingRight - 1) : 1.0f);
                    f2 = (float) (i5 - paddingBottom);
                    break;
                case 4:
                    int c = cVar.c();
                    if (c != 0) {
                        f3 = ((float) (i5 - cVar.e)) / ((float) c);
                    }
                    f = (f3 / 2.0f) + ((float) paddingTop);
                    f2 = ((float) (i5 - paddingBottom)) - (f3 / 2.0f);
                    break;
                default:
                    throw new IllegalStateException("Invalid justifyContent is set: " + this.c);
            }
            float max = Math.max(f3, 0.0f);
            int i8 = 0;
            float f4 = f;
            f = f2;
            while (i8 < cVar.h) {
                int i9 = cVar.o + i8;
                View c2 = c(i9);
                if (c2 != null) {
                    if (c2.getVisibility() == 8) {
                        f2 = f;
                        f = f4;
                    } else {
                        float f5;
                        float f6;
                        a aVar = (a) c2.getLayoutParams();
                        f4 += (float) aVar.topMargin;
                        f3 = f - ((float) aVar.bottomMargin);
                        int i10 = 0;
                        int i11 = 0;
                        if (c(i9, i8)) {
                            i10 = this.j;
                            f5 = f3 - ((float) i10);
                            f6 = ((float) i10) + f4;
                        } else {
                            f5 = f3;
                            f6 = f4;
                        }
                        if (i8 == cVar.h - 1 && (this.h & 4) > 0) {
                            i11 = this.j;
                        }
                        if (z) {
                            if (z2) {
                                this.n.a(c2, cVar, true, i6 - c2.getMeasuredWidth(), Math.round(f5) - c2.getMeasuredHeight(), i6, Math.round(f5));
                            } else {
                                this.n.a(c2, cVar, true, i6 - c2.getMeasuredWidth(), Math.round(f6), i6, Math.round(f6) + c2.getMeasuredHeight());
                            }
                        } else if (z2) {
                            this.n.a(c2, cVar, false, paddingLeft, Math.round(f5) - c2.getMeasuredHeight(), paddingLeft + c2.getMeasuredWidth(), Math.round(f5));
                        } else {
                            this.n.a(c2, cVar, false, paddingLeft, Math.round(f6), paddingLeft + c2.getMeasuredWidth(), Math.round(f6) + c2.getMeasuredHeight());
                        }
                        f4 = f6 + ((((float) c2.getMeasuredHeight()) + max) + ((float) aVar.bottomMargin));
                        f = f5 - ((((float) c2.getMeasuredHeight()) + max) + ((float) aVar.topMargin));
                        if (z2) {
                            cVar.a(c2, 0, i11, 0, i10);
                            f2 = f;
                            f = f4;
                        } else {
                            cVar.a(c2, 0, i10, 0, i11);
                        }
                    }
                    i8++;
                    f4 = f;
                    f = f2;
                }
                f2 = f;
                f = f4;
                i8++;
                f4 = f;
                f = f2;
            }
            paddingLeft += cVar.g;
            i6 -= cVar.g;
        }
    }

    protected void onDraw(Canvas canvas) {
        boolean z = false;
        boolean z2 = true;
        if (this.g != null || this.f != null) {
            if (this.h != 0 || this.i != 0) {
                int layoutDirection = ViewCompat.getLayoutDirection(this);
                boolean z3;
                switch (this.a) {
                    case 0:
                        if (layoutDirection == 1) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (this.b != 2) {
                            z2 = false;
                        }
                        a(canvas, z3, z2);
                        return;
                    case 1:
                        if (layoutDirection != 1) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (this.b != 2) {
                            z2 = false;
                        }
                        a(canvas, z3, z2);
                        return;
                    case 2:
                        if (layoutDirection == 1) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (this.b != 2) {
                            z2 = z3;
                        } else if (z3) {
                            z2 = false;
                        }
                        b(canvas, z2, false);
                        return;
                    case 3:
                        if (layoutDirection == 1) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (this.b != 2) {
                            z = z3;
                        } else if (!z3) {
                            z = true;
                        }
                        b(canvas, z, true);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private void a(Canvas canvas, boolean z, boolean z2) {
        int paddingLeft = getPaddingLeft();
        int max = Math.max(0, (getWidth() - getPaddingRight()) - paddingLeft);
        int size = this.o.size();
        for (int i = 0; i < size; i++) {
            int left;
            c cVar = (c) this.o.get(i);
            for (int i2 = 0; i2 < cVar.h; i2++) {
                int i3 = cVar.o + i2;
                View c = c(i3);
                if (!(c == null || c.getVisibility() == 8)) {
                    a aVar = (a) c.getLayoutParams();
                    if (c(i3, i2)) {
                        if (z) {
                            i3 = c.getRight() + aVar.rightMargin;
                        } else {
                            i3 = (c.getLeft() - aVar.leftMargin) - this.k;
                        }
                        a(canvas, i3, cVar.b, cVar.g);
                    }
                    if (i2 == cVar.h - 1 && (this.i & 4) > 0) {
                        if (z) {
                            left = (c.getLeft() - aVar.leftMargin) - this.k;
                        } else {
                            left = aVar.rightMargin + c.getRight();
                        }
                        a(canvas, left, cVar.b, cVar.g);
                    }
                }
            }
            if (d(i)) {
                if (z2) {
                    left = cVar.d;
                } else {
                    left = cVar.b - this.j;
                }
                b(canvas, paddingLeft, left, max);
            }
            if (f(i) && (this.h & 4) > 0) {
                int i4;
                if (z2) {
                    i4 = cVar.b - this.j;
                } else {
                    i4 = cVar.d;
                }
                b(canvas, paddingLeft, i4, max);
            }
        }
    }

    private void b(Canvas canvas, boolean z, boolean z2) {
        int paddingTop = getPaddingTop();
        int max = Math.max(0, (getHeight() - getPaddingBottom()) - paddingTop);
        int size = this.o.size();
        for (int i = 0; i < size; i++) {
            int top;
            c cVar = (c) this.o.get(i);
            for (int i2 = 0; i2 < cVar.h; i2++) {
                int i3 = cVar.o + i2;
                View c = c(i3);
                if (!(c == null || c.getVisibility() == 8)) {
                    a aVar = (a) c.getLayoutParams();
                    if (c(i3, i2)) {
                        if (z2) {
                            i3 = c.getBottom() + aVar.bottomMargin;
                        } else {
                            i3 = (c.getTop() - aVar.topMargin) - this.j;
                        }
                        b(canvas, cVar.a, i3, cVar.g);
                    }
                    if (i2 == cVar.h - 1 && (this.h & 4) > 0) {
                        if (z2) {
                            top = (c.getTop() - aVar.topMargin) - this.j;
                        } else {
                            top = aVar.bottomMargin + c.getBottom();
                        }
                        b(canvas, cVar.a, top, cVar.g);
                    }
                }
            }
            if (d(i)) {
                if (z) {
                    top = cVar.c;
                } else {
                    top = cVar.a - this.k;
                }
                a(canvas, top, paddingTop, max);
            }
            if (f(i) && (this.i & 4) > 0) {
                int i4;
                if (z) {
                    i4 = cVar.a - this.k;
                } else {
                    i4 = cVar.c;
                }
                a(canvas, i4, paddingTop, max);
            }
        }
    }

    private void a(Canvas canvas, int i, int i2, int i3) {
        if (this.g != null) {
            this.g.setBounds(i, i2, this.k + i, i2 + i3);
            this.g.draw(canvas);
        }
    }

    private void b(Canvas canvas, int i, int i2, int i3) {
        if (this.f != null) {
            this.f.setBounds(i, i2, i + i3, this.j + i2);
            this.f.draw(canvas);
        }
    }

    protected boolean checkLayoutParams(LayoutParams layoutParams) {
        return layoutParams instanceof a;
    }

    public a a(AttributeSet attributeSet) {
        return new a(getContext(), attributeSet);
    }

    protected LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        if (layoutParams instanceof a) {
            return new a((a) layoutParams);
        }
        if (layoutParams instanceof MarginLayoutParams) {
            return new a((MarginLayoutParams) layoutParams);
        }
        return new a(layoutParams);
    }

    public int getFlexDirection() {
        return this.a;
    }

    public void setFlexDirection(int i) {
        if (this.a != i) {
            this.a = i;
            requestLayout();
        }
    }

    public int getFlexWrap() {
        return this.b;
    }

    public void setFlexWrap(int i) {
        if (this.b != i) {
            this.b = i;
            requestLayout();
        }
    }

    public int getJustifyContent() {
        return this.c;
    }

    public void setJustifyContent(int i) {
        if (this.c != i) {
            this.c = i;
            requestLayout();
        }
    }

    public int getAlignItems() {
        return this.d;
    }

    public void setAlignItems(int i) {
        if (this.d != i) {
            this.d = i;
            requestLayout();
        }
    }

    public int getAlignContent() {
        return this.e;
    }

    public void setAlignContent(int i) {
        if (this.e != i) {
            this.e = i;
            requestLayout();
        }
    }

    public List<c> getFlexLines() {
        List<c> arrayList = new ArrayList(this.o.size());
        for (c cVar : this.o) {
            if (cVar.c() != 0) {
                arrayList.add(cVar);
            }
        }
        return arrayList;
    }

    public int a(View view, int i, int i2) {
        int i3 = 0;
        if (a()) {
            if (c(i, i2)) {
                i3 = 0 + this.k;
            }
            if ((this.i & 4) > 0) {
                return i3 + this.k;
            }
            return i3;
        }
        if (c(i, i2)) {
            i3 = 0 + this.j;
        }
        if ((this.h & 4) > 0) {
            return i3 + this.j;
        }
        return i3;
    }

    public int a(View view) {
        return 0;
    }

    public void a(c cVar) {
        if (a()) {
            if ((this.i & 4) > 0) {
                cVar.e += this.k;
                cVar.f += this.k;
            }
        } else if ((this.h & 4) > 0) {
            cVar.e += this.j;
            cVar.f += this.j;
        }
    }

    public int a(int i, int i2, int i3) {
        return getChildMeasureSpec(i, i2, i3);
    }

    public int b(int i, int i2, int i3) {
        return getChildMeasureSpec(i, i2, i3);
    }

    public void a(View view, int i, int i2, c cVar) {
        if (!c(i, i2)) {
            return;
        }
        if (a()) {
            cVar.e += this.k;
            cVar.f += this.k;
            return;
        }
        cVar.e += this.j;
        cVar.f += this.j;
    }

    public void setFlexLines(List<c> list) {
        this.o = list;
    }

    public List<c> getFlexLinesInternal() {
        return this.o;
    }

    public void a(int i, View view) {
    }

    @Nullable
    public Drawable getDividerDrawableHorizontal() {
        return this.f;
    }

    @Nullable
    public Drawable getDividerDrawableVertical() {
        return this.g;
    }

    public void setDividerDrawable(Drawable drawable) {
        setDividerDrawableHorizontal(drawable);
        setDividerDrawableVertical(drawable);
    }

    public void setDividerDrawableHorizontal(@Nullable Drawable drawable) {
        if (drawable != this.f) {
            this.f = drawable;
            if (drawable != null) {
                this.j = drawable.getIntrinsicHeight();
            } else {
                this.j = 0;
            }
            b();
            requestLayout();
        }
    }

    public void setDividerDrawableVertical(@Nullable Drawable drawable) {
        if (drawable != this.g) {
            this.g = drawable;
            if (drawable != null) {
                this.k = drawable.getIntrinsicWidth();
            } else {
                this.k = 0;
            }
            b();
            requestLayout();
        }
    }

    public int getShowDividerVertical() {
        return this.i;
    }

    public int getShowDividerHorizontal() {
        return this.h;
    }

    public void setShowDivider(int i) {
        setShowDividerVertical(i);
        setShowDividerHorizontal(i);
    }

    public void setShowDividerVertical(int i) {
        if (i != this.i) {
            this.i = i;
            requestLayout();
        }
    }

    public void setShowDividerHorizontal(int i) {
        if (i != this.h) {
            this.h = i;
            requestLayout();
        }
    }

    private void b() {
        if (this.f == null && this.g == null) {
            setWillNotDraw(true);
        } else {
            setWillNotDraw(false);
        }
    }

    private boolean c(int i, int i2) {
        if (d(i, i2)) {
            if (a()) {
                if ((this.i & 1) != 0) {
                    return true;
                }
                return false;
            } else if ((this.h & 1) == 0) {
                return false;
            } else {
                return true;
            }
        } else if (a()) {
            if ((this.i & 2) == 0) {
                return false;
            }
            return true;
        } else if ((this.h & 2) == 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean d(int i, int i2) {
        for (int i3 = 1; i3 <= i2; i3++) {
            View c = c(i - i3);
            if (c != null && c.getVisibility() != 8) {
                return false;
            }
        }
        return true;
    }

    private boolean d(int i) {
        if (i < 0 || i >= this.o.size()) {
            return false;
        }
        if (e(i)) {
            if (a()) {
                if ((this.h & 1) == 0) {
                    return false;
                }
                return true;
            } else if ((this.i & 1) == 0) {
                return false;
            } else {
                return true;
            }
        } else if (a()) {
            if ((this.h & 2) == 0) {
                return false;
            }
            return true;
        } else if ((this.i & 2) == 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean e(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (((c) this.o.get(i2)).c() > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean f(int i) {
        boolean z = true;
        if (i < 0 || i >= this.o.size()) {
            return false;
        }
        for (int i2 = i + 1; i2 < this.o.size(); i2++) {
            if (((c) this.o.get(i2)).c() > 0) {
                return false;
            }
        }
        if (a()) {
            return (this.h & 4) != 0;
        }
        if ((this.i & 4) == 0) {
            z = false;
        }
        return z;
    }
}
