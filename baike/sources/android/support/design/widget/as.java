package android.support.design.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.support.design.R;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.drawable.DrawableWrapper;

class as extends DrawableWrapper {
    static final double a = Math.cos(Math.toRadians(45.0d));
    final Paint b;
    final Paint c;
    final RectF d;
    float e;
    Path f;
    float g;
    float h;
    float i;
    float j;
    private boolean k = true;
    private final int l;
    private final int m;
    private final int n;
    private boolean o = true;
    private float p;
    private boolean q = false;

    public as(Context context, Drawable drawable, float f, float f2, float f3) {
        super(drawable);
        this.l = ContextCompat.getColor(context, R.color.design_fab_shadow_start_color);
        this.m = ContextCompat.getColor(context, R.color.design_fab_shadow_mid_color);
        this.n = ContextCompat.getColor(context, R.color.design_fab_shadow_end_color);
        this.b = new Paint(5);
        this.b.setStyle(Style.FILL);
        this.e = (float) Math.round(f);
        this.d = new RectF();
        this.c = new Paint(this.b);
        this.c.setAntiAlias(false);
        a(f2, f3);
    }

    private static int b(float f) {
        int round = Math.round(f);
        return round % 2 == 1 ? round - 1 : round;
    }

    public void setAddPaddingForCorners(boolean z) {
        this.o = z;
        invalidateSelf();
    }

    public void setAlpha(int i) {
        super.setAlpha(i);
        this.b.setAlpha(i);
        this.c.setAlpha(i);
    }

    protected void onBoundsChange(Rect rect) {
        this.k = true;
    }

    void a(float f, float f2) {
        if (f < 0.0f || f2 < 0.0f) {
            throw new IllegalArgumentException("invalid shadow size");
        }
        float b = (float) b(f);
        float b2 = (float) b(f2);
        if (b > b2) {
            if (!this.q) {
                this.q = true;
            }
            b = b2;
        }
        if (this.j != b || this.h != b2) {
            this.j = b;
            this.h = b2;
            this.i = (float) Math.round(b * 1.5f);
            this.g = b2;
            this.k = true;
            invalidateSelf();
        }
    }

    public boolean getPadding(Rect rect) {
        int ceil = (int) Math.ceil((double) calculateVerticalPadding(this.h, this.e, this.o));
        int ceil2 = (int) Math.ceil((double) calculateHorizontalPadding(this.h, this.e, this.o));
        rect.set(ceil2, ceil, ceil2, ceil);
        return true;
    }

    public static float calculateVerticalPadding(float f, float f2, boolean z) {
        if (z) {
            return (float) (((double) (1.5f * f)) + ((1.0d - a) * ((double) f2)));
        }
        return 1.5f * f;
    }

    public static float calculateHorizontalPadding(float f, float f2, boolean z) {
        if (z) {
            return (float) (((double) f) + ((1.0d - a) * ((double) f2)));
        }
        return f;
    }

    public int getOpacity() {
        return -3;
    }

    public void setCornerRadius(float f) {
        float round = (float) Math.round(f);
        if (this.e != round) {
            this.e = round;
            this.k = true;
            invalidateSelf();
        }
    }

    public void draw(Canvas canvas) {
        if (this.k) {
            a(getBounds());
            this.k = false;
        }
        a(canvas);
        super.draw(canvas);
    }

    final void a(float f) {
        if (this.p != f) {
            this.p = f;
            invalidateSelf();
        }
    }

    private void a(Canvas canvas) {
        int save = canvas.save();
        canvas.rotate(this.p, this.d.centerX(), this.d.centerY());
        float f = (-this.e) - this.i;
        float f2 = this.e;
        Object obj = this.d.width() - (2.0f * f2) > 0.0f ? 1 : null;
        Object obj2 = this.d.height() - (2.0f * f2) > 0.0f ? 1 : null;
        float f3 = f2 / ((this.j - (this.j * 0.5f)) + f2);
        float f4 = f2 / ((this.j - (this.j * 0.25f)) + f2);
        float f5 = f2 / (f2 + (this.j - (this.j * 1.0f)));
        int save2 = canvas.save();
        canvas.translate(this.d.left + f2, this.d.top + f2);
        canvas.scale(f3, f4);
        canvas.drawPath(this.f, this.b);
        if (obj != null) {
            canvas.scale(1.0f / f3, 1.0f);
            canvas.drawRect(0.0f, f, this.d.width() - (2.0f * f2), -this.e, this.c);
        }
        canvas.restoreToCount(save2);
        save2 = canvas.save();
        canvas.translate(this.d.right - f2, this.d.bottom - f2);
        canvas.scale(f3, f5);
        canvas.rotate(180.0f);
        canvas.drawPath(this.f, this.b);
        if (obj != null) {
            canvas.scale(1.0f / f3, 1.0f);
            canvas.drawRect(0.0f, f, this.d.width() - (2.0f * f2), this.i + (-this.e), this.c);
        }
        canvas.restoreToCount(save2);
        int save3 = canvas.save();
        canvas.translate(this.d.left + f2, this.d.bottom - f2);
        canvas.scale(f3, f5);
        canvas.rotate(270.0f);
        canvas.drawPath(this.f, this.b);
        if (obj2 != null) {
            canvas.scale(1.0f / f5, 1.0f);
            canvas.drawRect(0.0f, f, this.d.height() - (2.0f * f2), -this.e, this.c);
        }
        canvas.restoreToCount(save3);
        save3 = canvas.save();
        canvas.translate(this.d.right - f2, this.d.top + f2);
        canvas.scale(f3, f4);
        canvas.rotate(90.0f);
        canvas.drawPath(this.f, this.b);
        if (obj2 != null) {
            canvas.scale(1.0f / f4, 1.0f);
            canvas.drawRect(0.0f, f, this.d.height() - (2.0f * f2), -this.e, this.c);
        }
        canvas.restoreToCount(save3);
        canvas.restoreToCount(save);
    }

    private void a() {
        RectF rectF = new RectF(-this.e, -this.e, this.e, this.e);
        RectF rectF2 = new RectF(rectF);
        rectF2.inset(-this.i, -this.i);
        if (this.f == null) {
            this.f = new Path();
        } else {
            this.f.reset();
        }
        this.f.setFillType(FillType.EVEN_ODD);
        this.f.moveTo(-this.e, 0.0f);
        this.f.rLineTo(-this.i, 0.0f);
        this.f.arcTo(rectF2, 180.0f, 90.0f, false);
        this.f.arcTo(rectF, 270.0f, -90.0f, false);
        this.f.close();
        float f = -rectF2.top;
        if (f > 0.0f) {
            float f2 = this.e / f;
            float f3 = f2 + ((1.0f - f2) / 2.0f);
            float[] fArr = new float[]{0.0f, f2, f3, 1.0f};
            f2 = 0.0f;
            this.b.setShader(new RadialGradient(0.0f, f2, f, new int[]{0, this.l, this.m, this.n}, fArr, TileMode.CLAMP));
        }
        f = 0.0f;
        this.c.setShader(new LinearGradient(0.0f, rectF.top, f, rectF2.top, new int[]{this.l, this.m, this.n}, new float[]{0.0f, 0.5f, 1.0f}, TileMode.CLAMP));
        this.c.setAntiAlias(false);
    }

    private void a(Rect rect) {
        float f = this.h * 1.5f;
        this.d.set(((float) rect.left) + this.h, ((float) rect.top) + f, ((float) rect.right) - this.h, ((float) rect.bottom) - f);
        getWrappedDrawable().setBounds((int) this.d.left, (int) this.d.top, (int) this.d.right, (int) this.d.bottom);
        a();
    }

    public float getCornerRadius() {
        return this.e;
    }

    public void setShadowSize(float f) {
        a(f, this.h);
    }

    public void setMaxShadowSize(float f) {
        a(this.j, f);
    }

    public float getShadowSize() {
        return this.j;
    }

    public float getMaxShadowSize() {
        return this.h;
    }

    public float getMinWidth() {
        return (Math.max(this.h, this.e + (this.h / 2.0f)) * 2.0f) + (this.h * 2.0f);
    }

    public float getMinHeight() {
        return (Math.max(this.h, this.e + ((this.h * 1.5f) / 2.0f)) * 2.0f) + ((this.h * 1.5f) * 2.0f);
    }
}
