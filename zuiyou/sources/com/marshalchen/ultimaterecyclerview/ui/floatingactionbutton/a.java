package com.marshalchen.ultimaterecyclerview.ui.floatingactionbutton;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.Display;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageButton;
import com.marshalchen.ultimaterecyclerview.b.b;
import com.marshalchen.ultimaterecyclerview.b.e;

public class a extends ImageButton {
    private static final int a = Color.argb(128, 255, 255, 255);
    private static final int j = Color.argb(128, 0, 0, 0);
    protected int b;
    protected int c;
    @DrawableRes
    protected int d;
    protected int e;
    protected float f;
    protected float g;
    protected float h;
    protected int i;
    private final Interpolator k;
    private boolean l;
    private float m;
    private float n;

    public a(Context context) {
        this(context, null);
    }

    public a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k = new AccelerateDecelerateInterpolator();
        this.l = false;
        this.m = -1.0f;
        this.n = -1.0f;
        a(context, attributeSet);
    }

    public a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = new AccelerateDecelerateInterpolator();
        this.l = false;
        this.m = -1.0f;
        this.n = -1.0f;
        a(context, attributeSet);
    }

    protected void a(Context context, AttributeSet attributeSet) {
        this.b = a(17170451);
        this.c = a(17170450);
        this.d = 0;
        this.e = 0;
        if (attributeSet != null) {
            b(context, attributeSet);
        }
        this.f = c(this.e);
        this.g = b(com.marshalchen.ultimaterecyclerview.b.a.fab_shadow_radius);
        this.h = b(com.marshalchen.ultimaterecyclerview.b.a.fab_shadow_offset);
        this.i = (int) (this.f + (2.0f * this.g));
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (VERSION.SDK_INT >= 13) {
            defaultDisplay.getSize(point);
            this.n = (float) point.y;
        } else {
            this.n = (float) defaultDisplay.getHeight();
        }
        a();
    }

    protected void a() {
        float f = this.g;
        float f2 = this.g - this.h;
        Drawable a = a(new RectF(f, f2, this.f + f, this.f + f2));
        float b = (this.f - b(com.marshalchen.ultimaterecyclerview.b.a.fab_icon_size)) / 2.0f;
        int i = (int) (this.g + b);
        a.setLayerInset(a.getNumberOfLayers() - 1, i, (int) (f2 + b), i, (int) ((this.g + this.h) + b));
        setBackgroundCompat(a);
    }

    protected int a(@ColorRes int i) {
        return getResources().getColor(i);
    }

    protected float b(@DimenRes int i) {
        return getResources().getDimension(i);
    }

    protected void b(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, e.FloatActionButton, 0, 0);
        if (obtainStyledAttributes != null) {
            try {
                this.b = obtainStyledAttributes.getColor(e.FloatActionButton_urv_fab_colorNormal, a(17170451));
                this.c = obtainStyledAttributes.getColor(e.FloatActionButton_urv_fab_colorPressed, a(17170450));
                this.e = obtainStyledAttributes.getInt(e.FloatActionButton_urv_fab_size, 0);
                this.d = obtainStyledAttributes.getResourceId(e.FloatActionButton_icon, 0);
                a(obtainStyledAttributes);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
    }

    protected void a(TypedArray typedArray) {
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(this.i, this.i);
    }

    protected float c(int i) {
        switch (i) {
            case 0:
                return b(com.marshalchen.ultimaterecyclerview.b.a.fab_size_normal);
            case 1:
                return b(com.marshalchen.ultimaterecyclerview.b.a.fab_size_mini);
            case 2:
                return b(com.marshalchen.ultimaterecyclerview.b.a.fab_size_normal);
            default:
                return b(com.marshalchen.ultimaterecyclerview.b.a.fab_size_normal);
        }
    }

    @DrawableRes
    protected int d(int i) {
        switch (i) {
            case 0:
                return b.urv_floating_action_button_fab_bg_normal;
            case 1:
                return b.urv_floating_action_button_fab_bg_mini;
            default:
                return -1;
        }
    }

    protected LayerDrawable a(RectF rectF) {
        if (this.e == 2) {
            return new LayerDrawable(new Drawable[]{b(rectF), c(rectF), getIconDrawable()});
        }
        return new LayerDrawable(new Drawable[]{getResources().getDrawable(d(this.e)), b(rectF), c(rectF), getIconDrawable()});
    }

    protected Drawable getIconDrawable() {
        if (this.d != 0) {
            return getResources().getDrawable(this.d);
        }
        return new ColorDrawable(0);
    }

    protected StateListDrawable b(RectF rectF) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, a(rectF, this.c));
        stateListDrawable.addState(new int[0], a(rectF, this.b));
        return stateListDrawable;
    }

    protected Drawable a(RectF rectF, int i) {
        Bitmap createBitmap = Bitmap.createBitmap(this.i, this.i, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(i);
        canvas.drawOval(rectF, paint);
        return new BitmapDrawable(getResources(), createBitmap);
    }

    protected int a(float f) {
        return (int) (255.0f * f);
    }

    protected Drawable c(RectF rectF) {
        Bitmap createBitmap = Bitmap.createBitmap(this.i, this.i, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        float b = b(com.marshalchen.ultimaterecyclerview.b.a.fab_stroke_width);
        float f = b / 2.0f;
        RectF rectF2 = new RectF(rectF.left - f, rectF.top - f, rectF.right + f, rectF.bottom + f);
        RectF rectF3 = new RectF(rectF.left + f, rectF.top + f, rectF.right - f, rectF.bottom - f);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(b);
        paint.setStyle(Style.STROKE);
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint.setAlpha(a(0.02f));
        canvas.drawOval(rectF2, paint);
        paint.setShader(new LinearGradient(rectF3.centerX(), rectF3.top, rectF3.centerX(), rectF3.bottom, new int[]{0, j, ViewCompat.MEASURED_STATE_MASK}, new float[]{0.0f, 0.8f, 1.0f}, TileMode.CLAMP));
        paint.setAlpha(a(0.04f));
        canvas.drawOval(rectF3, paint);
        paint.setShader(new LinearGradient(rectF3.centerX(), rectF3.top, rectF3.centerX(), rectF3.bottom, new int[]{-1, a, 0}, new float[]{0.0f, 0.2f, 1.0f}, TileMode.CLAMP));
        paint.setAlpha(a(0.8f));
        canvas.drawOval(rectF3, paint);
        return new BitmapDrawable(getResources(), createBitmap);
    }

    @SuppressLint({"NewApi"})
    private void setBackgroundCompat(Drawable drawable) {
        if (VERSION.SDK_INT >= 16) {
            setBackground(drawable);
        } else {
            setBackgroundDrawable(drawable);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.m == -1.0f) {
            this.m = ViewCompat.getY(this);
        }
    }
}
