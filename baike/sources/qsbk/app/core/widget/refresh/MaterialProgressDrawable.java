package qsbk.app.core.widget.refresh;

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
import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.NonNull;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

class MaterialProgressDrawable extends Drawable implements Animatable {
    private static final Interpolator b = new LinearInterpolator();
    private static final Interpolator c = new FastOutSlowInInterpolator();
    boolean a;
    private final int[] d = new int[]{-16777216};
    private final ArrayList<Animation> e = new ArrayList();
    private final a f;
    private float g;
    private Resources h;
    private View i;
    private Animation j;
    private float k;
    private double l;
    private double m;
    private final Callback n = new d(this);

    @Retention(RetentionPolicy.CLASS)
    public @interface ProgressDrawableSize {
    }

    private static class a {
        private final RectF a = new RectF();
        private final Paint b = new Paint();
        private final Paint c = new Paint();
        private final Callback d;
        private float e = 0.0f;
        private float f = 0.0f;
        private float g = 0.0f;
        private float h = 5.0f;
        private float i = 2.5f;
        private int[] j;
        private int k;
        private float l;
        private float m;
        private float n;
        private boolean o;
        private Path p;
        private float q;
        private double r;
        private int s;
        private int t;
        private int u;
        private final Paint v = new Paint(1);
        private int w;
        private int x;

        public a(Callback callback) {
            this.d = callback;
            this.b.setStrokeCap(Cap.SQUARE);
            this.b.setAntiAlias(true);
            this.b.setStyle(Style.STROKE);
            this.c.setStyle(Style.FILL);
            this.c.setAntiAlias(true);
        }

        public void setBackgroundColor(int i) {
            this.w = i;
        }

        public void setArrowDimensions(float f, float f2) {
            this.s = (int) f;
            this.t = (int) f2;
        }

        public void draw(Canvas canvas, Rect rect) {
            RectF rectF = this.a;
            rectF.set(rect);
            rectF.inset(this.i, this.i);
            float f = (this.e + this.g) * 360.0f;
            float f2 = ((this.f + this.g) * 360.0f) - f;
            this.b.setColor(this.x);
            canvas.drawArc(rectF, f, f2, false, this.b);
            a(canvas, f, f2, rect);
            if (this.u < 255) {
                this.v.setColor(this.w);
                this.v.setAlpha(255 - this.u);
                canvas.drawCircle(rect.exactCenterX(), rect.exactCenterY(), (float) (rect.width() / 2), this.v);
            }
        }

        private void a(Canvas canvas, float f, float f2, Rect rect) {
            if (this.o) {
                if (this.p == null) {
                    this.p = new Path();
                    this.p.setFillType(FillType.EVEN_ODD);
                } else {
                    this.p.reset();
                }
                float f3 = ((float) (((int) this.i) / 2)) * this.q;
                float cos = (float) ((this.r * Math.cos(0.0d)) + ((double) rect.exactCenterX()));
                float sin = (float) ((this.r * Math.sin(0.0d)) + ((double) rect.exactCenterY()));
                this.p.moveTo(0.0f, 0.0f);
                this.p.lineTo(((float) this.s) * this.q, 0.0f);
                this.p.lineTo((((float) this.s) * this.q) / 2.0f, ((float) this.t) * this.q);
                this.p.offset(cos - f3, sin);
                this.p.close();
                this.c.setColor(this.x);
                canvas.rotate((f + f2) - 5.0f, rect.exactCenterX(), rect.exactCenterY());
                canvas.drawPath(this.p, this.c);
            }
        }

        public void setColors(@NonNull int[] iArr) {
            this.j = iArr;
            setColorIndex(0);
        }

        public void setColor(int i) {
            this.x = i;
        }

        public void setColorIndex(int i) {
            this.k = i;
            this.x = this.j[this.k];
        }

        public int getNextColor() {
            return this.j[a()];
        }

        private int a() {
            return (this.k + 1) % this.j.length;
        }

        public void goToNextColor() {
            setColorIndex(a());
        }

        public void setColorFilter(ColorFilter colorFilter) {
            this.b.setColorFilter(colorFilter);
            b();
        }

        public void setAlpha(int i) {
            this.u = i;
        }

        public int getAlpha() {
            return this.u;
        }

        public void setStrokeWidth(float f) {
            this.h = f;
            this.b.setStrokeWidth(f);
            b();
        }

        public float getStrokeWidth() {
            return this.h;
        }

        public void setStartTrim(float f) {
            this.e = f;
            b();
        }

        public float getStartTrim() {
            return this.e;
        }

        public float getStartingStartTrim() {
            return this.l;
        }

        public float getStartingEndTrim() {
            return this.m;
        }

        public int getStartingColor() {
            return this.j[this.k];
        }

        public void setEndTrim(float f) {
            this.f = f;
            b();
        }

        public float getEndTrim() {
            return this.f;
        }

        public void setRotation(float f) {
            this.g = f;
            b();
        }

        public float getRotation() {
            return this.g;
        }

        public void setInsets(int i, int i2) {
            float min = (float) Math.min(i, i2);
            if (this.r <= 0.0d || min < 0.0f) {
                min = (float) Math.ceil((double) (this.h / 2.0f));
            } else {
                min = (float) (((double) (min / 2.0f)) - this.r);
            }
            this.i = min;
        }

        public float getInsets() {
            return this.i;
        }

        public void setCenterRadius(double d) {
            this.r = d;
        }

        public double getCenterRadius() {
            return this.r;
        }

        public void setShowArrow(boolean z) {
            if (this.o != z) {
                this.o = z;
                b();
            }
        }

        public void setArrowScale(float f) {
            if (f != this.q) {
                this.q = f;
                b();
            }
        }

        public float getStartingRotation() {
            return this.n;
        }

        public void storeOriginals() {
            this.l = this.e;
            this.m = this.f;
            this.n = this.g;
        }

        public void resetOriginals() {
            this.l = 0.0f;
            this.m = 0.0f;
            this.n = 0.0f;
            setStartTrim(0.0f);
            setEndTrim(0.0f);
            setRotation(0.0f);
        }

        private void b() {
            this.d.invalidateDrawable(null);
        }
    }

    public MaterialProgressDrawable(Context context, View view) {
        this.i = view;
        this.h = context.getResources();
        this.f = new a(this.n);
        this.f.setColors(this.d);
        updateSizes(1);
        b();
    }

    private void a(double d, double d2, double d3, double d4, float f, float f2) {
        a aVar = this.f;
        float f3 = this.h.getDisplayMetrics().density;
        this.l = ((double) f3) * d;
        this.m = ((double) f3) * d2;
        aVar.setStrokeWidth(((float) d4) * f3);
        aVar.setCenterRadius(((double) f3) * d3);
        aVar.setColorIndex(0);
        aVar.setArrowDimensions(f * f3, f3 * f2);
        aVar.setInsets((int) this.l, (int) this.m);
    }

    public void updateSizes(@ProgressDrawableSize int i) {
        if (i == 0) {
            a(56.0d, 56.0d, 12.5d, 3.0d, 12.0f, 6.0f);
        } else {
            a(40.0d, 40.0d, 8.75d, 2.5d, 10.0f, 5.0f);
        }
    }

    public void showArrow(boolean z) {
        this.f.setShowArrow(z);
    }

    public void setArrowScale(float f) {
        this.f.setArrowScale(f);
    }

    public void setStartEndTrim(float f, float f2) {
        this.f.setStartTrim(f);
        this.f.setEndTrim(f2);
    }

    public void setProgressRotation(float f) {
        this.f.setRotation(f);
    }

    public void setBackgroundColor(int i) {
        this.f.setBackgroundColor(i);
    }

    public void setColorSchemeColors(int... iArr) {
        this.f.setColors(iArr);
        this.f.setColorIndex(0);
    }

    public int getIntrinsicHeight() {
        return (int) this.m;
    }

    public int getIntrinsicWidth() {
        return (int) this.l;
    }

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        int save = canvas.save();
        canvas.rotate(this.g, bounds.exactCenterX(), bounds.exactCenterY());
        this.f.draw(canvas, bounds);
        canvas.restoreToCount(save);
    }

    public void setAlpha(int i) {
        this.f.setAlpha(i);
    }

    public int getAlpha() {
        return this.f.getAlpha();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f.setColorFilter(colorFilter);
    }

    void a(float f) {
        this.g = f;
        invalidateSelf();
    }

    public int getOpacity() {
        return -3;
    }

    public boolean isRunning() {
        ArrayList arrayList = this.e;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Animation animation = (Animation) arrayList.get(i);
            if (animation.hasStarted() && !animation.hasEnded()) {
                return true;
            }
        }
        return false;
    }

    public void start() {
        this.j.reset();
        this.f.storeOriginals();
        if (this.f.getEndTrim() != this.f.getStartTrim()) {
            this.a = true;
            this.j.setDuration(666);
            this.i.startAnimation(this.j);
            return;
        }
        this.f.setColorIndex(0);
        this.f.resetOriginals();
        this.j.setDuration(1332);
        this.i.startAnimation(this.j);
    }

    public void stop() {
        this.i.clearAnimation();
        a(0.0f);
        this.f.setShowArrow(false);
        this.f.setColorIndex(0);
        this.f.resetOriginals();
    }

    private float a(a aVar) {
        return (float) Math.toRadians(((double) aVar.getStrokeWidth()) / (6.283185307179586d * aVar.getCenterRadius()));
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
            aVar.setColor(a((f - 0.75f) / 0.25f, aVar.getStartingColor(), aVar.getNextColor()));
        }
    }

    private void b(float f, a aVar) {
        a(f, aVar);
        float floor = (float) (Math.floor((double) (aVar.getStartingRotation() / 0.8f)) + 1.0d);
        float a = a(aVar);
        aVar.setStartTrim((((aVar.getStartingEndTrim() - a) - aVar.getStartingStartTrim()) * f) + aVar.getStartingStartTrim());
        aVar.setEndTrim(aVar.getStartingEndTrim());
        aVar.setRotation(((floor - aVar.getStartingRotation()) * f) + aVar.getStartingRotation());
    }

    private void b() {
        a aVar = this.f;
        Animation bVar = new b(this, aVar);
        bVar.setRepeatCount(-1);
        bVar.setRepeatMode(1);
        bVar.setInterpolator(b);
        bVar.setAnimationListener(new c(this, aVar));
        this.j = bVar;
    }
}
