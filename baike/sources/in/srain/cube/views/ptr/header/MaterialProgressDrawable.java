package in.srain.cube.views.ptr.header;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build.VERSION;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;
import java.util.ArrayList;

public class MaterialProgressDrawable extends Drawable implements Animatable {
    public static final int DEFAULT = 1;
    public static final int LARGE = 0;
    private static final Interpolator a = new LinearInterpolator();
    private static final Interpolator b = new a();
    private static final Interpolator c = new d();
    private static final Interpolator d = new AccelerateDecelerateInterpolator();
    private final int[] e = new int[]{-3591113, -13149199, -536002, -13327536};
    private final ArrayList<Animation> f = new ArrayList();
    private final c g;
    private final Callback h = new d(this);
    private float i;
    private Resources j;
    private View k;
    private Animation l;
    private float m;
    private double n;
    private double o;
    private Animation p;
    private int q;
    private ShapeDrawable r;

    private static class a extends AccelerateDecelerateInterpolator {
        private a() {
        }

        public float getInterpolation(float f) {
            return super.getInterpolation(Math.max(0.0f, (f - 0.5f) * 2.0f));
        }
    }

    private class b extends OvalShape {
        final /* synthetic */ MaterialProgressDrawable a;
        private RadialGradient b;
        private int c;
        private Paint d = new Paint();
        private int e;

        public b(MaterialProgressDrawable materialProgressDrawable, int i, int i2) {
            this.a = materialProgressDrawable;
            this.c = i;
            this.e = i2;
            this.b = new RadialGradient((float) (this.e / 2), (float) (this.e / 2), (float) this.c, new int[]{1023410176, 0}, null, TileMode.CLAMP);
            this.d.setShader(this.b);
        }

        public void draw(Canvas canvas, Paint paint) {
            int width = this.a.getBounds().width();
            int height = this.a.getBounds().height();
            canvas.drawCircle((float) (width / 2), (float) (height / 2), (float) ((this.e / 2) + this.c), this.d);
            canvas.drawCircle((float) (width / 2), (float) (height / 2), (float) (this.e / 2), paint);
        }
    }

    private static class c {
        private final RectF a = new RectF();
        private final Paint b = new Paint();
        private final Paint c = new Paint();
        private final Callback d;
        private final Paint e = new Paint();
        private float f = 0.0f;
        private float g = 0.0f;
        private float h = 0.0f;
        private float i = 5.0f;
        private float j = 2.5f;
        private int[] k;
        private int l;
        private float m;
        private float n;
        private float o;
        private boolean p;
        private Path q;
        private float r;
        private double s;
        private int t;
        private int u;
        private int v;
        private int w;

        public c(Callback callback) {
            this.d = callback;
            this.b.setStrokeCap(Cap.SQUARE);
            this.b.setAntiAlias(true);
            this.b.setStyle(Style.STROKE);
            this.c.setStyle(Style.FILL);
            this.c.setAntiAlias(true);
            this.e.setAntiAlias(true);
        }

        public void setBackgroundColor(int i) {
            this.w = i;
        }

        public void setArrowDimensions(float f, float f2) {
            this.t = (int) f;
            this.u = (int) f2;
        }

        public void draw(Canvas canvas, Rect rect) {
            this.e.setColor(this.w);
            this.e.setAlpha(this.v);
            canvas.drawCircle(rect.exactCenterX(), rect.exactCenterY(), (float) (rect.width() / 2), this.e);
            RectF rectF = this.a;
            rectF.set(rect);
            rectF.inset(this.j, this.j);
            float f = (this.f + this.h) * 360.0f;
            float f2 = ((this.g + this.h) * 360.0f) - f;
            this.b.setColor(this.k[this.l]);
            this.b.setAlpha(this.v);
            canvas.drawArc(rectF, f, f2, false, this.b);
            a(canvas, f, f2, rect);
        }

        private void a(Canvas canvas, float f, float f2, Rect rect) {
            if (this.p) {
                if (this.q == null) {
                    this.q = new Path();
                    this.q.setFillType(FillType.EVEN_ODD);
                } else {
                    this.q.reset();
                }
                float f3 = ((float) (((int) this.j) / 2)) * this.r;
                float cos = (float) ((this.s * Math.cos(0.0d)) + ((double) rect.exactCenterX()));
                float sin = (float) ((this.s * Math.sin(0.0d)) + ((double) rect.exactCenterY()));
                this.q.moveTo(0.0f, 0.0f);
                this.q.lineTo(((float) this.t) * this.r, 0.0f);
                this.q.lineTo((((float) this.t) * this.r) / 2.0f, ((float) this.u) * this.r);
                this.q.offset(cos - f3, sin);
                this.q.close();
                this.c.setColor(this.k[this.l]);
                this.c.setAlpha(this.v);
                canvas.rotate((f + f2) - 5.0f, rect.exactCenterX(), rect.exactCenterY());
                canvas.drawPath(this.q, this.c);
            }
        }

        public void setColors(int[] iArr) {
            this.k = iArr;
            setColorIndex(0);
        }

        public void setColorIndex(int i) {
            this.l = i;
        }

        public void goToNextColor() {
            this.l = (this.l + 1) % this.k.length;
        }

        public void setColorFilter(ColorFilter colorFilter) {
            this.b.setColorFilter(colorFilter);
            a();
        }

        public int getAlpha() {
            return this.v;
        }

        public void setAlpha(int i) {
            this.v = i;
        }

        public float getStrokeWidth() {
            return this.i;
        }

        public void setStrokeWidth(float f) {
            this.i = f;
            this.b.setStrokeWidth(f);
            a();
        }

        public float getStartTrim() {
            return this.f;
        }

        public void setStartTrim(float f) {
            this.f = f;
            a();
        }

        public float getStartingStartTrim() {
            return this.m;
        }

        public float getStartingEndTrim() {
            return this.n;
        }

        public float getEndTrim() {
            return this.g;
        }

        public void setEndTrim(float f) {
            this.g = f;
            a();
        }

        public float getRotation() {
            return this.h;
        }

        public void setRotation(float f) {
            this.h = f;
            a();
        }

        public void setInsets(int i, int i2) {
            float min = (float) Math.min(i, i2);
            if (this.s <= 0.0d || min < 0.0f) {
                min = (float) Math.ceil((double) (this.i / 2.0f));
            } else {
                min = (float) (((double) (min / 2.0f)) - this.s);
            }
            this.j = min;
        }

        public float getInsets() {
            return this.j;
        }

        public double getCenterRadius() {
            return this.s;
        }

        public void setCenterRadius(double d) {
            this.s = d;
        }

        public void setShowArrow(boolean z) {
            if (this.p != z) {
                this.p = z;
                a();
            }
        }

        public void setArrowScale(float f) {
            if (f != this.r) {
                this.r = f;
                a();
            }
        }

        public float getStartingRotation() {
            return this.o;
        }

        public void storeOriginals() {
            this.m = this.f;
            this.n = this.g;
            this.o = this.h;
        }

        public void resetOriginals() {
            this.m = 0.0f;
            this.n = 0.0f;
            this.o = 0.0f;
            setStartTrim(0.0f);
            setEndTrim(0.0f);
            setRotation(0.0f);
        }

        private void a() {
            this.d.invalidateDrawable(null);
        }
    }

    private static class d extends AccelerateDecelerateInterpolator {
        private d() {
        }

        public float getInterpolation(float f) {
            return super.getInterpolation(Math.min(1.0f, 2.0f * f));
        }
    }

    public MaterialProgressDrawable(Context context, View view) {
        this.k = view;
        this.j = context.getResources();
        this.g = new c(this.h);
        this.g.setColors(this.e);
        updateSizes(1);
        c();
    }

    private void a(double d, double d2, double d3, double d4, float f, float f2) {
        c cVar = this.g;
        float f3 = this.j.getDisplayMetrics().density;
        this.n = ((double) f3) * d;
        this.o = ((double) f3) * d2;
        cVar.setStrokeWidth(((float) d4) * f3);
        cVar.setCenterRadius(((double) f3) * d3);
        cVar.setColorIndex(0);
        cVar.setArrowDimensions(f * f3, f3 * f2);
        cVar.setInsets((int) this.n, (int) this.o);
        a(this.n);
    }

    private void a(double d) {
        PtrLocalDisplay.init(this.k.getContext());
        int dp2px = PtrLocalDisplay.dp2px(1.75f);
        int dp2px2 = PtrLocalDisplay.dp2px(0.0f);
        int dp2px3 = PtrLocalDisplay.dp2px(3.5f);
        this.r = new ShapeDrawable(new b(this, dp2px3, (int) d));
        if (VERSION.SDK_INT >= 11) {
            this.k.setLayerType(1, this.r.getPaint());
        }
        this.r.getPaint().setShadowLayer((float) dp2px3, (float) dp2px2, (float) dp2px, 503316480);
    }

    public void updateSizes(int i) {
        if (i == 0) {
            a(56.0d, 56.0d, 12.5d, 3.0d, 12.0f, 6.0f);
        } else {
            a(40.0d, 40.0d, 8.75d, 2.5d, 10.0f, 5.0f);
        }
    }

    public void showArrow(boolean z) {
        this.g.setShowArrow(z);
    }

    public void setArrowScale(float f) {
        this.g.setArrowScale(f);
    }

    public void setStartEndTrim(float f, float f2) {
        this.g.setStartTrim(f);
        this.g.setEndTrim(f2);
    }

    public void setProgressRotation(float f) {
        this.g.setRotation(f);
    }

    public void setBackgroundColor(int i) {
        this.q = i;
        this.g.setBackgroundColor(i);
    }

    public void setColorSchemeColors(int... iArr) {
        this.g.setColors(iArr);
        this.g.setColorIndex(0);
    }

    public int getIntrinsicHeight() {
        return (int) this.o;
    }

    public int getIntrinsicWidth() {
        return (int) this.n;
    }

    public void draw(Canvas canvas) {
        if (this.r != null) {
            this.r.getPaint().setColor(this.q);
            this.r.draw(canvas);
        }
        Rect bounds = getBounds();
        int save = canvas.save();
        canvas.rotate(this.i, bounds.exactCenterX(), bounds.exactCenterY());
        this.g.draw(canvas, bounds);
        canvas.restoreToCount(save);
    }

    public int getAlpha() {
        return this.g.getAlpha();
    }

    public void setAlpha(int i) {
        this.g.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.g.setColorFilter(colorFilter);
    }

    void a(float f) {
        this.i = f;
        invalidateSelf();
    }

    public int getOpacity() {
        return -3;
    }

    public boolean isRunning() {
        ArrayList arrayList = this.f;
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
        this.l.reset();
        this.g.storeOriginals();
        if (this.g.getEndTrim() != this.g.getStartTrim()) {
            this.k.startAnimation(this.p);
            return;
        }
        this.g.setColorIndex(0);
        this.g.resetOriginals();
        this.k.startAnimation(this.l);
    }

    public void stop() {
        this.k.clearAnimation();
        a(0.0f);
        this.g.setShowArrow(false);
        this.g.setColorIndex(0);
        this.g.resetOriginals();
    }

    private void c() {
        c cVar = this.g;
        Animation eVar = new e(this, cVar);
        eVar.setInterpolator(d);
        eVar.setDuration(666);
        eVar.setAnimationListener(new f(this, cVar));
        Animation gVar = new g(this, cVar);
        gVar.setRepeatCount(-1);
        gVar.setRepeatMode(1);
        gVar.setInterpolator(a);
        gVar.setDuration(1333);
        gVar.setAnimationListener(new h(this, cVar));
        this.p = eVar;
        this.l = gVar;
    }
}
