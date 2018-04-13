package master.flame.danmaku.danmaku.model.android;

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
import java.util.HashMap;
import java.util.Map;
import master.flame.danmaku.danmaku.model.b;
import master.flame.danmaku.danmaku.model.c;
import master.flame.danmaku.danmaku.model.d;

public class a extends b<Canvas, Typeface> {
    public Canvas a;
    private Camera b = new Camera();
    private Matrix c = new Matrix();
    private final a d = new a();
    private b e = new i();
    private int f;
    private int g;
    private float h;
    private float i = 1.0f;
    private int j = 160;
    private float k = 1.0f;
    private int l = 0;
    private boolean m = true;
    private int n = 2048;
    private int o = 2048;

    public static class a {
        private int A = 0;
        public final TextPaint a = new TextPaint();
        public final TextPaint b;
        public int c = 4;
        public float d = 1.0f;
        public float e = 1.0f;
        public boolean f = false;
        public boolean g = true;
        public boolean h = false;
        public boolean i = this.h;
        public boolean j = true;
        private float k;
        private final Map<Float, Float> l = new HashMap(10);
        private Paint m;
        private Paint n;
        private Paint o;
        private float p = 4.0f;
        private float q = 3.5f;
        private int r = 204;
        private boolean s = this.f;
        private boolean t = this.g;
        private boolean u = this.j;
        private boolean v;
        private int w = c.a;
        private float x = 1.0f;
        private boolean y = false;
        private int z = 0;

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

        public void a(float f) {
            this.p = f;
        }

        public void b(float f) {
            this.a.setStrokeWidth(f);
            this.q = f;
        }

        public void a(float f, float f2, int i) {
            if (this.d != f || this.e != f2 || this.r != i) {
                if (f <= 1.0f) {
                    f = 1.0f;
                }
                this.d = f;
                if (f2 <= 1.0f) {
                    f2 = 1.0f;
                }
                this.e = f2;
                if (i < 0) {
                    i = 0;
                } else if (i > 255) {
                    i = 255;
                }
                this.r = i;
            }
        }

        public void c(float f) {
            this.y = f != 1.0f;
            this.x = f;
        }

        private void a(d dVar, Paint paint) {
            if (this.y) {
                Float f = (Float) this.l.get(Float.valueOf(dVar.l));
                if (f == null || this.k != this.x) {
                    this.k = this.x;
                    f = Float.valueOf(dVar.l * this.x);
                    this.l.put(Float.valueOf(dVar.l), f);
                }
                paint.setTextSize(f.floatValue());
            }
        }

        public boolean a(d dVar) {
            return (this.t || this.i) && this.q > 0.0f && dVar.j != 0;
        }

        public Paint b(d dVar) {
            this.o.setColor(dVar.m);
            return this.o;
        }

        public Paint c(d dVar) {
            this.n.setColor(dVar.k);
            return this.n;
        }

        public TextPaint a(d dVar, boolean z) {
            Paint paint;
            if (z) {
                paint = this.a;
            } else {
                paint = this.b;
                paint.set(this.a);
            }
            paint.setTextSize(dVar.l);
            a(dVar, paint);
            if (!this.s || this.p <= 0.0f || dVar.j == 0) {
                paint.clearShadowLayer();
            } else {
                paint.setShadowLayer(this.p, 0.0f, 0.0f, dVar.j);
            }
            paint.setAntiAlias(this.u);
            return paint;
        }

        public void a(d dVar, Paint paint, boolean z) {
            if (this.v) {
                if (z) {
                    paint.setStyle(this.i ? Style.FILL : Style.STROKE);
                    paint.setColor(dVar.j & ViewCompat.MEASURED_SIZE_MASK);
                    paint.setAlpha(this.i ? (int) (((float) this.r) * (((float) this.w) / ((float) c.a))) : this.w);
                } else {
                    paint.setStyle(Style.FILL);
                    paint.setColor(dVar.g & ViewCompat.MEASURED_SIZE_MASK);
                    paint.setAlpha(this.w);
                }
            } else if (z) {
                paint.setStyle(this.i ? Style.FILL : Style.STROKE);
                paint.setColor(dVar.j & ViewCompat.MEASURED_SIZE_MASK);
                paint.setAlpha(this.i ? this.r : c.a);
            } else {
                paint.setStyle(Style.FILL);
                paint.setColor(dVar.g & ViewCompat.MEASURED_SIZE_MASK);
                paint.setAlpha(c.a);
            }
            if (dVar.o() == 7) {
                paint.setAlpha(dVar.q());
            }
        }

        public void a() {
            this.l.clear();
        }

        public float b() {
            if (this.s && this.t) {
                return Math.max(this.p, this.q);
            }
            if (this.s) {
                return this.p;
            }
            if (this.t) {
                return this.q;
            }
            return 0.0f;
        }

        public void a(boolean z) {
            this.t = this.g;
            this.s = this.f;
            this.i = this.h;
            boolean z2 = z && this.j;
            this.u = z2;
        }
    }

    public /* synthetic */ Object a() {
        return o();
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
        this.d.a(f);
    }

    public void d(float f) {
        this.d.b(f);
    }

    public void a(float f, float f2, int i) {
        this.d.a(f, f2, i);
    }

    public void a(float f) {
        this.d.c(f);
    }

    public void a(b bVar) {
        if (bVar != this.e) {
            this.e = bVar;
        }
    }

    public b d() {
        return this.e;
    }

    public void a(int i) {
        this.d.z = i;
    }

    public int m() {
        return this.d.z;
    }

    public int n() {
        return this.d.A;
    }

    private void d(Canvas canvas) {
        this.a = canvas;
        if (canvas != null) {
            this.f = canvas.getWidth();
            this.g = canvas.getHeight();
            if (this.m) {
                this.n = b(canvas);
                this.o = c(canvas);
            }
        }
    }

    public int e() {
        return this.f;
    }

    public int f() {
        return this.g;
    }

    public float g() {
        return this.i;
    }

    public int h() {
        return this.j;
    }

    public int a(d dVar) {
        int i = 0;
        float l = dVar.l();
        float k = dVar.k();
        if (this.a != null) {
            int i2;
            Paint paint = null;
            if (dVar.o() != 7) {
                i2 = 0;
            } else if (dVar.q() != c.b) {
                int i3;
                if (dVar.h == 0.0f && dVar.i == 0.0f) {
                    i3 = 0;
                } else {
                    a(dVar, this.a, k, l);
                    i3 = 1;
                }
                if (dVar.q() != c.a) {
                    paint = this.d.m;
                    paint.setAlpha(dVar.q());
                }
                i2 = i3;
            }
            if (paint == null || paint.getAlpha() != c.b) {
                if (this.e.a(dVar, this.a, k, l, paint, this.d.a)) {
                    i = 1;
                } else {
                    if (paint != null) {
                        this.d.a.setAlpha(paint.getAlpha());
                        this.d.b.setAlpha(paint.getAlpha());
                    } else {
                        a(this.d.a);
                    }
                    a(dVar, this.a, k, l, false);
                    i = 2;
                }
                if (i2 != 0) {
                    e(this.a);
                }
            }
        }
        return i;
    }

    public void b(d dVar) {
        if (this.e != null) {
            this.e.b(dVar);
        }
    }

    private void a(Paint paint) {
        if (paint.getAlpha() != c.a) {
            paint.setAlpha(c.a);
        }
    }

    private void e(Canvas canvas) {
        canvas.restore();
    }

    private int a(d dVar, Canvas canvas, float f, float f2) {
        this.b.save();
        if (this.h != 0.0f && VERSION.SDK_INT >= 12) {
            this.b.setLocation(0.0f, 0.0f, this.h);
        }
        this.b.rotateY(-dVar.i);
        this.b.rotateZ(-dVar.h);
        this.b.getMatrix(this.c);
        this.c.preTranslate(-f, -f2);
        this.c.postTranslate(f, f2);
        this.b.restore();
        int save = canvas.save();
        canvas.concat(this.c);
        return save;
    }

    public synchronized void a(d dVar, Canvas canvas, float f, float f2, boolean z) {
        if (this.e != null) {
            this.e.a(dVar, canvas, f, f2, z, this.d);
        }
    }

    private synchronized TextPaint c(d dVar, boolean z) {
        return this.d.a(dVar, z);
    }

    public void a(d dVar, boolean z) {
        if (this.e != null) {
            this.e.a(dVar, z);
        }
    }

    public void b(d dVar, boolean z) {
        Paint c = c(dVar, z);
        if (this.d.t) {
            this.d.a(dVar, c, true);
        }
        a(dVar, (TextPaint) c, z);
        if (this.d.t) {
            this.d.a(dVar, c, false);
        }
    }

    private void a(d dVar, TextPaint textPaint, boolean z) {
        this.e.a(dVar, textPaint, z);
        a(dVar, dVar.p, dVar.q);
    }

    private void a(d dVar, float f, float f2) {
        float f3 = f + ((float) (dVar.n * 2));
        float f4 = ((float) (dVar.n * 2)) + f2;
        if (dVar.m != 0) {
            a aVar = this.d;
            f3 += (float) 8;
            aVar = this.d;
            f4 += (float) 8;
        }
        dVar.p = f3 + p();
        dVar.q = f4;
    }

    public void c() {
        this.e.a();
        this.d.a();
    }

    public float i() {
        return this.k;
    }

    public void b(float f) {
        float max = Math.max(f, ((float) e()) / 682.0f) * 25.0f;
        this.l = (int) max;
        if (f > 1.0f) {
            this.l = (int) (max * f);
        }
    }

    public int j() {
        return this.l;
    }

    public void a(float f, int i, float f2) {
        this.i = f;
        this.j = i;
        this.k = f2;
    }

    public void a(int i, int i2) {
        this.f = i;
        this.g = i2;
        this.h = (float) (((double) (((float) i) / 2.0f)) / Math.tan(0.4799655442984406d));
    }

    public void a(int i, float[] fArr) {
        switch (i) {
            case -1:
            case 2:
                this.d.f = false;
                this.d.g = true;
                this.d.h = false;
                d(fArr[0]);
                return;
            case 0:
                this.d.f = false;
                this.d.g = false;
                this.d.h = false;
                return;
            case 1:
                this.d.f = true;
                this.d.g = false;
                this.d.h = false;
                c(fArr[0]);
                return;
            case 3:
                this.d.f = false;
                this.d.g = false;
                this.d.h = true;
                a(fArr[0], fArr[1], (int) fArr[2]);
                return;
            default:
                return;
        }
    }

    public void a(Canvas canvas) {
        d(canvas);
    }

    public Canvas o() {
        return this.a;
    }

    public float p() {
        return this.d.b();
    }

    public void a(boolean z) {
        this.m = z;
    }

    public boolean b() {
        return this.m;
    }

    public int k() {
        return this.n;
    }

    public int l() {
        return this.o;
    }
}
