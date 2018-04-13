package com.scwang.smartrefresh.layout.footer;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.graphics.ColorUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import com.scwang.smartrefresh.layout.a.d;
import com.scwang.smartrefresh.layout.a.g;
import com.scwang.smartrefresh.layout.a.h;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.f.b;

public class a extends ViewGroup implements d {
    private com.scwang.smartrefresh.layout.footer.a.a a;
    private SpinnerStyle b = SpinnerStyle.Translate;

    public a(@NonNull Context context) {
        super(context);
        a(context, null, 0);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        this.a = new com.scwang.smartrefresh.layout.footer.a.a(context);
        addView(this.a, -2, -2);
        setMinimumHeight(b.a(60.0f));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.scwang.smartrefresh.layout.a.a.BallPulseFooter);
        int color = obtainStyledAttributes.getColor(com.scwang.smartrefresh.layout.a.a.BallPulseFooter_srlPrimaryColor, 0);
        int color2 = obtainStyledAttributes.getColor(com.scwang.smartrefresh.layout.a.a.BallPulseFooter_srlAccentColor, 0);
        if (color != 0) {
            this.a.setNormalColor(color);
        }
        if (color2 != 0) {
            this.a.setAnimatingColor(color2);
        }
        this.b = SpinnerStyle.values()[obtainStyledAttributes.getInt(com.scwang.smartrefresh.layout.a.a.BallPulseFooter_srlClassicsSpinnerStyle, this.b.ordinal())];
        obtainStyledAttributes.recycle();
    }

    protected void onMeasure(int i, int i2) {
        this.a.measure(MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(i), Integer.MIN_VALUE), MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(i2), Integer.MIN_VALUE));
        setMeasuredDimension(resolveSize(this.a.getMeasuredWidth(), i), resolveSize(this.a.getMeasuredHeight(), i2));
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int measuredWidth2 = this.a.getMeasuredWidth();
        int measuredHeight2 = this.a.getMeasuredHeight();
        measuredWidth = (measuredWidth / 2) - (measuredWidth2 / 2);
        measuredHeight = (measuredHeight / 2) - (measuredHeight2 / 2);
        this.a.layout(measuredWidth, measuredHeight, measuredWidth2 + measuredWidth, measuredHeight2 + measuredHeight);
    }

    public void a(@NonNull g gVar, int i, int i2) {
    }

    public boolean a() {
        return false;
    }

    public void a(float f, int i, int i2) {
    }

    public void a(float f, int i, int i2, int i3) {
    }

    public void b(float f, int i, int i2, int i3) {
    }

    public void a(h hVar, int i, int i2) {
    }

    public void b(@NonNull h hVar, int i, int i2) {
        this.a.a();
    }

    public void a(h hVar, RefreshState refreshState, RefreshState refreshState2) {
    }

    public int a(@NonNull h hVar, boolean z) {
        this.a.b();
        return 0;
    }

    public boolean a(boolean z) {
        return false;
    }

    @Deprecated
    public void setPrimaryColors(@ColorInt int... iArr) {
        if (iArr.length > 1) {
            this.a.setNormalColor(iArr[1]);
            this.a.setAnimatingColor(iArr[0]);
        } else if (iArr.length > 0) {
            this.a.setNormalColor(ColorUtils.compositeColors(-1711276033, iArr[0]));
            this.a.setAnimatingColor(iArr[0]);
        }
    }

    @NonNull
    public View getView() {
        return this;
    }

    @NonNull
    public SpinnerStyle getSpinnerStyle() {
        return this.b;
    }
}
