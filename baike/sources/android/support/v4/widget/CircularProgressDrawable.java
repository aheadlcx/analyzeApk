package android.support.v4.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.util.Preconditions;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CircularProgressDrawable extends Drawable implements Animatable {
    public static final int DEFAULT = 1;
    public static final int LARGE = 0;
    private static final Interpolator a = new LinearInterpolator();
    private static final Interpolator b = new FastOutSlowInInterpolator();
    private static final int[] c = new int[]{-16777216};
    private final a d = new a();
    private float e;
    private Resources f;
    private Animator g;
    private float h;
    private boolean i;

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ProgressDrawableSize {
    }

    private static class a {
        final RectF a = new RectF();
        final Paint b = new Paint();
        final Paint c = new Paint();
        final Paint d = new Paint();
        float e = 0.0f;
        float f = 0.0f;
        float g = 0.0f;
        float h = 5.0f;
        int[] i;
        int j;
        float k;
        float l;
        float m;
        boolean n;
        Path o;
        float p = 1.0f;
        float q;
        int r;
        int s;
        int t = 255;
        int u;

        a() {
            this.b.setStrokeCap(Cap.SQUARE);
            this.b.setAntiAlias(true);
            this.b.setStyle(Style.STROKE);
            this.c.setStyle(Style.FILL);
            this.c.setAntiAlias(true);
            this.d.setColor(0);
        }

        void a(float f, float f2) {
            this.r = (int) f;
            this.s = (int) f2;
        }

        void a(Cap cap) {
            this.b.setStrokeCap(cap);
        }

        Cap a() {
            return this.b.getStrokeCap();
        }

        float b() {
            return (float) this.r;
        }

        float c() {
            return (float) this.s;
        }

        void a(Canvas canvas, Rect rect) {
            RectF rectF = this.a;
            float f = this.q + (this.h / 2.0f);
            if (this.q <= 0.0f) {
                f = (((float) Math.min(rect.width(), rect.height())) / 2.0f) - Math.max((((float) this.r) * this.p) / 2.0f, this.h / 2.0f);
            }
            rectF.set(((float) rect.centerX()) - f, ((float) rect.centerY()) - f, ((float) rect.centerX()) + f, f + ((float) rect.centerY()));
            float f2 = (this.e + this.g) * 360.0f;
            float f3 = ((this.f + this.g) * 360.0f) - f2;
            this.b.setColor(this.u);
            this.b.setAlpha(this.t);
            f = this.h / 2.0f;
            rectF.inset(f, f);
            canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2.0f, this.d);
            rectF.inset(-f, -f);
            canvas.drawArc(rectF, f2, f3, false, this.b);
            a(canvas, f2, f3, rectF);
        }

        void a(Canvas canvas, float f, float f2, RectF rectF) {
            if (this.n) {
                if (this.o == null) {
                    this.o = new Path();
                    this.o.setFillType(FillType.EVEN_ODD);
                } else {
                    this.o.reset();
                }
                float min = Math.min(rectF.width(), rectF.height()) / 2.0f;
                float f3 = (((float) this.r) * this.p) / 2.0f;
                this.o.moveTo(0.0f, 0.0f);
                this.o.lineTo(((float) this.r) * this.p, 0.0f);
                this.o.lineTo((((float) this.r) * this.p) / 2.0f, ((float) this.s) * this.p);
                this.o.offset((min + rectF.centerX()) - f3, rectF.centerY() + (this.h / 2.0f));
                this.o.close();
                this.c.setColor(this.u);
                this.c.setAlpha(this.t);
                canvas.save();
                canvas.rotate(f + f2, rectF.centerX(), rectF.centerY());
                canvas.drawPath(this.o, this.c);
                canvas.restore();
            }
        }

        void a(@NonNull int[] iArr) {
            this.i = iArr;
            c(0);
        }

        int[] d() {
            return this.i;
        }

        void a(int i) {
            this.u = i;
        }

        void b(int i) {
            this.d.setColor(i);
        }

        int e() {
            return this.d.getColor();
        }

        void c(int i) {
            this.j = i;
            this.u = this.i[this.j];
        }

        int f() {
            return this.i[g()];
        }

        int g() {
            return (this.j + 1) % this.i.length;
        }

        void h() {
            c(g());
        }

        void a(ColorFilter colorFilter) {
            this.b.setColorFilter(colorFilter);
        }

        void d(int i) {
            this.t = i;
        }

        int i() {
            return this.t;
        }

        void a(float f) {
            this.h = f;
            this.b.setStrokeWidth(f);
        }

        float j() {
            return this.h;
        }

        void b(float f) {
            this.e = f;
        }

        float k() {
            return this.e;
        }

        float l() {
            return this.k;
        }

        float m() {
            return this.l;
        }

        int n() {
            return this.i[this.j];
        }

        void c(float f) {
            this.f = f;
        }

        float o() {
            return this.f;
        }

        void d(float f) {
            this.g = f;
        }

        float p() {
            return this.g;
        }

        void e(float f) {
            this.q = f;
        }

        float q() {
            return this.q;
        }

        void a(boolean z) {
            if (this.n != z) {
                this.n = z;
            }
        }

        boolean r() {
            return this.n;
        }

        void f(float f) {
            if (f != this.p) {
                this.p = f;
            }
        }

        float s() {
            return this.p;
        }

        float t() {
            return this.m;
        }

        void u() {
            this.k = this.e;
            this.l = this.f;
            this.m = this.g;
        }

        void v() {
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 0.0f;
            b(0.0f);
            c(0.0f);
            d(0.0f);
        }
    }

    public CircularProgressDrawable(@NonNull Context context) {
        this.f = ((Context) Preconditions.checkNotNull(context)).getResources();
        this.d.a(c);
        setStrokeWidth(2.5f);
        a();
    }

    private void a(float f, float f2, float f3, float f4) {
        a aVar = this.d;
        float f5 = this.f.getDisplayMetrics().density;
        aVar.a(f2 * f5);
        aVar.e(f * f5);
        aVar.c(0);
        aVar.a(f3 * f5, f5 * f4);
    }

    public void setStyle(int i) {
        if (i == 0) {
            a(11.0f, 3.0f, 12.0f, 6.0f);
        } else {
            a(7.5f, 2.5f, 10.0f, 5.0f);
        }
        invalidateSelf();
    }

    public float getStrokeWidth() {
        return this.d.j();
    }

    public void setStrokeWidth(float f) {
        this.d.a(f);
        invalidateSelf();
    }

    public float getCenterRadius() {
        return this.d.q();
    }

    public void setCenterRadius(float f) {
        this.d.e(f);
        invalidateSelf();
    }

    public void setStrokeCap(@NonNull Cap cap) {
        this.d.a(cap);
        invalidateSelf();
    }

    @NonNull
    public Cap getStrokeCap() {
        return this.d.a();
    }

    public float getArrowWidth() {
        return this.d.b();
    }

    public float getArrowHeight() {
        return this.d.c();
    }

    public void setArrowDimensions(float f, float f2) {
        this.d.a(f, f2);
        invalidateSelf();
    }

    public boolean getArrowEnabled() {
        return this.d.r();
    }

    public void setArrowEnabled(boolean z) {
        this.d.a(z);
        invalidateSelf();
    }

    public float getArrowScale() {
        return this.d.s();
    }

    public void setArrowScale(float f) {
        this.d.f(f);
        invalidateSelf();
    }

    public float getStartTrim() {
        return this.d.k();
    }

    public float getEndTrim() {
        return this.d.o();
    }

    public void setStartEndTrim(float f, float f2) {
        this.d.b(f);
        this.d.c(f2);
        invalidateSelf();
    }

    public float getProgressRotation() {
        return this.d.p();
    }

    public void setProgressRotation(float f) {
        this.d.d(f);
        invalidateSelf();
    }

    public int getBackgroundColor() {
        return this.d.e();
    }

    public void setBackgroundColor(int i) {
        this.d.b(i);
        invalidateSelf();
    }

    @NonNull
    public int[] getColorSchemeColors() {
        return this.d.d();
    }

    public void setColorSchemeColors(@NonNull int... iArr) {
        this.d.a(iArr);
        this.d.c(0);
        invalidateSelf();
    }

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.save();
        canvas.rotate(this.e, bounds.exactCenterX(), bounds.exactCenterY());
        this.d.a(canvas, bounds);
        canvas.restore();
    }

    public void setAlpha(int i) {
        this.d.d(i);
        invalidateSelf();
    }

    public int getAlpha() {
        return this.d.i();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.d.a(colorFilter);
        invalidateSelf();
    }

    private void a(float f) {
        this.e = f;
    }

    public int getOpacity() {
        return -3;
    }

    public boolean isRunning() {
        return this.g.isRunning();
    }

    public void start() {
        this.g.cancel();
        this.d.u();
        if (this.d.o() != this.d.k()) {
            this.i = true;
            this.g.setDuration(666);
            this.g.start();
            return;
        }
        this.d.c(0);
        this.d.v();
        this.g.setDuration(1332);
        this.g.start();
    }

    public void stop() {
        this.g.cancel();
        a(0.0f);
        this.d.a(false);
        this.d.c(0);
        this.d.v();
        invalidateSelf();
    }

    private int a(float f, int i, int i2) {
        int i3 = (i >> 24) & 255;
        int i4 = (i >> 16) & 255;
        int i5 = (i >> 8) & 255;
        int i6 = i & 255;
        return ((((i3 + ((int) (((float) (((i2 >> 24) & 255) - i3)) * f))) << 24) | ((i4 + ((int) (((float) (((i2 >> 16) & 255) - i4)) * f))) << 16)) | ((((int) (((float) (((i2 >> 8) & 255) - i5)) * f)) + i5) << 8)) | (((int) (((float) ((i2 & 255) - i6)) * f)) + i6);
    }

    private void a(float f, a aVar) {
        if (f > 0.75f) {
            aVar.a(a((f - 0.75f) / 0.25f, aVar.n(), aVar.f()));
        } else {
            aVar.a(aVar.n());
        }
    }

    private void b(float f, a aVar) {
        a(f, aVar);
        float floor = (float) (Math.floor((double) (aVar.t() / 0.8f)) + 1.0d);
        aVar.b(aVar.l() + (((aVar.m() - 0.01f) - aVar.l()) * f));
        aVar.c(aVar.m());
        aVar.d(((floor - aVar.t()) * f) + aVar.t());
    }

    private void a(float f, a aVar, boolean z) {
        if (this.i) {
            b(f, aVar);
        } else if (f != 1.0f || z) {
            float f2;
            float l;
            float t = aVar.t();
            if (f < 0.5f) {
                f2 = f / 0.5f;
                l = aVar.l();
                f2 = ((b.getInterpolation(f2) * 0.79f) + 0.01f) + l;
            } else {
                f2 = aVar.l() + 0.79f;
                l = f2 - (((1.0f - b.getInterpolation((f - 0.5f) / 0.5f)) * 0.79f) + 0.01f);
            }
            t += 0.20999998f * f;
            float f3 = 216.0f * (this.h + f);
            aVar.b(l);
            aVar.c(f2);
            aVar.d(t);
            a(f3);
        }
    }

    private void a() {
        a aVar = this.d;
        Animator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.addUpdateListener(new b(this, aVar));
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(a);
        ofFloat.addListener(new c(this, aVar));
        this.g = ofFloat;
    }
}
