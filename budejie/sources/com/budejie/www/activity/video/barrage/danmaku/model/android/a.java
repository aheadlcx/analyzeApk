package com.budejie.www.activity.video.barrage.danmaku.model.android;

import android.annotation.SuppressLint;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.text.TextPaint;
import com.budejie.www.activity.video.barrage.danmaku.model.b;
import com.budejie.www.activity.video.barrage.danmaku.model.c;
import java.util.HashMap;
import java.util.Map;

public class a extends com.budejie.www.activity.video.barrage.danmaku.model.a<Canvas, Typeface> {
    private int A = b.a;
    private float B = 1.0f;
    private boolean C = false;
    private int D;
    private int E;
    private float F = 1.0f;
    private int G = 160;
    private float H = 1.0f;
    private int I = 0;
    private boolean J = true;
    private int K = 2048;
    private int L = 2048;
    public TextPaint a = new TextPaint();
    public TextPaint b;
    public int c = 4;
    public boolean d = false;
    public boolean e = true;
    public boolean f = false;
    public boolean g = true;
    public Canvas h;
    private Camera i = new Camera();
    private Matrix j = new Matrix();
    private float k;
    private final Map<Float, Float> l = new HashMap(10);
    private Paint m;
    private Paint n;
    private Paint o;
    private float p = 4.0f;
    private float q = 3.5f;
    private float r = 1.0f;
    private float s = 1.0f;
    private int t = 204;
    private boolean u = this.d;
    private boolean v = this.e;
    private boolean w = this.f;
    private boolean x = this.g;
    private b y = new g();
    private boolean z;

    public /* synthetic */ Object a() {
        return l();
    }

    public a() {
        this.a.setStrokeWidth(this.q);
        this.b = new TextPaint(this.a);
        this.m = new Paint();
        this.n = new Paint();
        this.n.setStrokeWidth((float) this.c);
        this.n.setStyle(Style.STROKE);
        this.o = new Paint();
        this.o.setStyle(Style.STROKE);
        this.o.setStrokeWidth(4.0f);
    }

    @SuppressLint({"NewApi"})
    private static final int b(Canvas canvas) {
        if (VERSION.SDK_INT >= 14) {
            return canvas.getMaximumBitmapWidth();
        }
        return canvas.getWidth();
    }

    @SuppressLint({"NewApi"})
    private static final int c(Canvas canvas) {
        if (VERSION.SDK_INT >= 14) {
            return canvas.getMaximumBitmapHeight();
        }
        return canvas.getHeight();
    }

    public void c(float f) {
        this.p = f;
    }

    public void d(float f) {
        this.a.setStrokeWidth(f);
        this.q = f;
    }

    public void a(float f, float f2, int i) {
        if (this.r != f || this.s != f2 || this.t != i) {
            if (f <= 1.0f) {
                f = 1.0f;
            }
            this.r = f;
            if (f2 <= 1.0f) {
                f2 = 1.0f;
            }
            this.s = f2;
            if (i < 0) {
                i = 0;
            } else if (i > 255) {
                i = 255;
            }
            this.t = i;
        }
    }

    public void a(int i) {
        this.z = i != b.a;
        this.A = i;
    }

    public void a(float f) {
        this.C = f != 1.0f;
        this.B = f;
    }

    public void a(b bVar) {
        if (bVar != this.y) {
            this.y = bVar;
        }
    }

    private void d(Canvas canvas) {
        this.h = canvas;
        if (canvas != null) {
            this.D = canvas.getWidth();
            this.E = canvas.getHeight();
            if (this.J) {
                this.K = b(canvas);
                this.L = c(canvas);
            }
        }
    }

    public int d() {
        return this.D;
    }

    public int e() {
        return this.E;
    }

    public float f() {
        return this.F;
    }

    public int g() {
        return this.G;
    }

    public int a(c cVar) {
        int i;
        boolean z = false;
        float k = cVar.k();
        float j = cVar.j();
        if (this.h != null) {
            int i2;
            Paint paint = null;
            if (cVar.n() != 7) {
                i2 = 0;
            } else if (cVar.p() != b.b) {
                int i3;
                if (cVar.f == 0.0f && cVar.g == 0.0f) {
                    i3 = 0;
                } else {
                    a(cVar, this.h, j, k);
                    i3 = true;
                }
                if (cVar.p() != b.a) {
                    paint = this.m;
                    paint.setAlpha(cVar.p());
                }
                i2 = i3;
            }
            if (paint == null || paint.getAlpha() != b.b) {
                if (cVar.c()) {
                    e h = ((d) cVar.s).h();
                    if (!(h == null || cVar.a.f)) {
                        z = h.a(this.h, j, k, paint);
                    }
                }
                if (z) {
                    i = 1;
                } else {
                    if (paint != null) {
                        this.a.setAlpha(paint.getAlpha());
                    } else {
                        a(this.a);
                    }
                    a(cVar, this.h, j, k, true);
                    i = 2;
                }
                if (i2 != 0) {
                    e(this.h);
                }
            }
        }
        return i;
    }

    private void a(Paint paint) {
        if (paint.getAlpha() != b.a) {
            paint.setAlpha(b.a);
        }
    }

    private void e(Canvas canvas) {
        canvas.restore();
    }

    private int a(c cVar, Canvas canvas, float f, float f2) {
        this.i.save();
        this.i.rotateY(-cVar.g);
        this.i.rotateZ(-cVar.f);
        this.i.getMatrix(this.j);
        this.j.preTranslate(-f, -f2);
        this.j.postTranslate(f, f2);
        this.i.restore();
        int save = canvas.save();
        canvas.concat(this.j);
        return save;
    }

    public void a(c cVar, Canvas canvas, float f, float f2, boolean z) {
        float f3;
        float f4;
        float f5 = f + ((float) cVar.l);
        float f6 = ((float) cVar.l) + f2;
        if (cVar.k != 0) {
            f3 = f6 + 4.0f;
            f4 = f5 + 4.0f;
        } else {
            f3 = f6;
            f4 = f5;
        }
        this.v = this.e;
        this.u = this.d;
        this.w = this.f;
        boolean z2 = !z && this.g;
        this.x = z2;
        Paint a = a(cVar, z);
        this.y.a(cVar, canvas, f, f2);
        float ascent;
        float f7;
        if (cVar.d != null) {
            String[] strArr = cVar.d;
            if (strArr.length == 1) {
                if (f(cVar)) {
                    a(cVar, a, true);
                    ascent = f3 - a.ascent();
                    if (this.w) {
                        f7 = f4 + this.r;
                        ascent += this.s;
                    } else {
                        f7 = f4;
                    }
                    this.y.a(cVar, strArr[0], canvas, f7, ascent, a);
                }
                a(cVar, a, false);
                this.y.b(cVar, strArr[0], canvas, f4, f3 - a.ascent(), a);
            } else {
                float length = (cVar.o - ((float) (cVar.l * 2))) / ((float) strArr.length);
                int i = 0;
                while (i < strArr.length) {
                    if (!(strArr[i] == null || strArr[i].length() == 0)) {
                        if (f(cVar)) {
                            a(cVar, a, true);
                            ascent = ((((float) i) * length) + f3) - a.ascent();
                            if (this.w) {
                                f7 = f4 + this.r;
                                ascent += this.s;
                            } else {
                                f7 = f4;
                            }
                            this.y.a(cVar, strArr[i], canvas, f7, ascent, a);
                        }
                        a(cVar, a, false);
                        this.y.b(cVar, strArr[i], canvas, f4, ((((float) i) * length) + f3) - a.ascent(), a);
                    }
                    i++;
                }
            }
        } else {
            if (f(cVar)) {
                a(cVar, a, true);
                ascent = f3 - a.ascent();
                if (this.w) {
                    f7 = f4 + this.r;
                    ascent += this.s;
                } else {
                    f7 = f4;
                }
                this.y.a(cVar, null, canvas, f7, ascent, a);
            }
            a(cVar, a, false);
            this.y.b(cVar, null, canvas, f4, f3 - a.ascent(), a);
        }
        if (cVar.i != 0) {
            float f8 = (cVar.o + f2) - ((float) this.c);
            canvas.drawLine(f, f8, f + cVar.n, f8, d(cVar));
        }
        if (cVar.k != 0) {
            canvas.drawRect(f, f2, f + cVar.n, f2 + cVar.o, c(cVar));
        }
    }

    private boolean f(c cVar) {
        return (this.v || this.w) && this.q > 0.0f && cVar.h != 0;
    }

    public Paint c(c cVar) {
        this.o.setColor(cVar.k);
        return this.o;
    }

    public Paint d(c cVar) {
        this.n.setColor(cVar.i);
        return this.n;
    }

    private TextPaint a(c cVar, boolean z) {
        Paint paint;
        if (z) {
            paint = this.b;
            paint.set(this.a);
        } else {
            paint = this.a;
        }
        paint.setTextSize(cVar.j);
        a(cVar, paint);
        if (!this.u || this.p <= 0.0f || cVar.h == 0) {
            paint.clearShadowLayer();
        } else {
            paint.setShadowLayer(this.p, 0.0f, 0.0f, cVar.h);
        }
        paint.setAntiAlias(this.x);
        return paint;
    }

    public TextPaint e(c cVar) {
        return a(cVar, false);
    }

    private void a(c cVar, Paint paint, boolean z) {
        if (this.z) {
            if (z) {
                paint.setStyle(this.w ? Style.FILL : Style.STROKE);
                paint.setColor(cVar.h & ViewCompat.MEASURED_SIZE_MASK);
                paint.setAlpha(this.w ? (int) (((float) this.t) * (((float) this.A) / ((float) b.a))) : this.A);
                return;
            }
            paint.setStyle(Style.FILL);
            paint.setColor(cVar.e & ViewCompat.MEASURED_SIZE_MASK);
            paint.setAlpha(this.A);
        } else if (z) {
            paint.setStyle(this.w ? Style.FILL : Style.STROKE);
            paint.setColor(cVar.h & ViewCompat.MEASURED_SIZE_MASK);
            paint.setAlpha(this.w ? this.t : b.a);
        } else {
            paint.setStyle(Style.FILL);
            paint.setColor(cVar.e & ViewCompat.MEASURED_SIZE_MASK);
            paint.setAlpha(b.a);
        }
    }

    private void a(c cVar, Paint paint) {
        if (this.C) {
            Float f = (Float) this.l.get(Float.valueOf(cVar.j));
            if (f == null || this.k != this.B) {
                this.k = this.B;
                f = Float.valueOf(cVar.j * this.B);
                this.l.put(Float.valueOf(cVar.j), f);
            }
            paint.setTextSize(f.floatValue());
        }
    }

    public void b(c cVar) {
        Paint e = e(cVar);
        if (this.v) {
            a(cVar, e, true);
        }
        a(cVar, (TextPaint) e);
        if (this.v) {
            a(cVar, e, false);
        }
    }

    private void a(c cVar, TextPaint textPaint) {
        this.y.a(cVar, textPaint);
        a(cVar, cVar.n, cVar.o);
    }

    private void a(c cVar, float f, float f2) {
        float f3 = f + ((float) (cVar.l * 2));
        float f4 = ((float) (cVar.l * 2)) + f2;
        if (cVar.k != 0) {
            f3 += 8.0f;
            f4 += 8.0f;
        }
        cVar.n = f3 + m();
        cVar.o = f4;
    }

    public void c() {
        this.y.a();
        this.l.clear();
    }

    public float h() {
        return this.H;
    }

    public void b(float f) {
        float max = Math.max(f, ((float) d()) / 682.0f) * 25.0f;
        this.I = (int) max;
        if (f > 1.0f) {
            this.I = (int) (max * f);
        }
    }

    public int i() {
        return this.I;
    }

    public void a(float f, int i, float f2) {
        this.F = f;
        this.G = i;
        this.H = f2;
    }

    public void a(int i, int i2) {
        this.D = i;
        this.E = i2;
    }

    public void a(int i, float[] fArr) {
        switch (i) {
            case -1:
            case 2:
                this.d = false;
                this.e = true;
                this.f = false;
                d(fArr[0]);
                return;
            case 0:
                this.d = false;
                this.e = false;
                this.f = false;
                return;
            case 1:
                this.d = true;
                this.e = false;
                this.f = false;
                c(fArr[0]);
                return;
            case 3:
                this.d = false;
                this.e = false;
                this.f = true;
                a(fArr[0], fArr[1], (int) fArr[2]);
                return;
            default:
                return;
        }
    }

    public void a(Canvas canvas) {
        d(canvas);
    }

    public Canvas l() {
        return this.h;
    }

    public float m() {
        if (this.u && this.v) {
            return Math.max(this.p, this.q);
        }
        if (this.u) {
            return this.p;
        }
        if (this.v) {
            return this.q;
        }
        return 0.0f;
    }

    public void a(boolean z) {
        this.J = z;
    }

    public boolean b() {
        return this.J;
    }

    public int j() {
        return this.K;
    }

    public int k() {
        return this.L;
    }
}
