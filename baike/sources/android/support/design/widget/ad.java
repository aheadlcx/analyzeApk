package android.support.design.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.v4.math.MathUtils;
import android.support.v4.text.TextDirectionHeuristicsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.widget.TintTypedArray;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.animation.Interpolator;

final class ad {
    private static final boolean a = (VERSION.SDK_INT < 18);
    private static final Paint b = null;
    private boolean A;
    private Bitmap B;
    private Paint C;
    private float D;
    private float E;
    private float F;
    private float G;
    private int[] H;
    private boolean I;
    private final TextPaint J;
    private Interpolator K;
    private Interpolator L;
    private float M;
    private float N;
    private float O;
    private int P;
    private float Q;
    private float R;
    private float S;
    private int T;
    private final View c;
    private boolean d;
    private float e;
    private final Rect f;
    private final Rect g;
    private final RectF h;
    private int i = 16;
    private int j = 16;
    private float k = 15.0f;
    private float l = 15.0f;
    private ColorStateList m;
    private ColorStateList n;
    private float o;
    private float p;
    private float q;
    private float r;
    private float s;
    private float t;
    private Typeface u;
    private Typeface v;
    private Typeface w;
    private CharSequence x;
    private CharSequence y;
    private boolean z;

    static {
        if (b != null) {
            b.setAntiAlias(true);
            b.setColor(-65281);
        }
    }

    public ad(View view) {
        this.c = view;
        this.J = new TextPaint(129);
        this.g = new Rect();
        this.f = new Rect();
        this.h = new RectF();
    }

    void a(Interpolator interpolator) {
        this.L = interpolator;
        recalculate();
    }

    void b(Interpolator interpolator) {
        this.K = interpolator;
        recalculate();
    }

    void a(float f) {
        if (this.k != f) {
            this.k = f;
            recalculate();
        }
    }

    void a(ColorStateList colorStateList) {
        if (this.n != colorStateList) {
            this.n = colorStateList;
            recalculate();
        }
    }

    void b(ColorStateList colorStateList) {
        if (this.m != colorStateList) {
            this.m = colorStateList;
            recalculate();
        }
    }

    void a(int i, int i2, int i3, int i4) {
        if (!a(this.f, i, i2, i3, i4)) {
            this.f.set(i, i2, i3, i4);
            this.I = true;
            a();
        }
    }

    void b(int i, int i2, int i3, int i4) {
        if (!a(this.g, i, i2, i3, i4)) {
            this.g.set(i, i2, i3, i4);
            this.I = true;
            a();
        }
    }

    void a() {
        boolean z = this.g.width() > 0 && this.g.height() > 0 && this.f.width() > 0 && this.f.height() > 0;
        this.d = z;
    }

    void a(int i) {
        if (this.i != i) {
            this.i = i;
            recalculate();
        }
    }

    int b() {
        return this.i;
    }

    void b(int i) {
        if (this.j != i) {
            this.j = i;
            recalculate();
        }
    }

    int c() {
        return this.j;
    }

    void c(int i) {
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(this.c.getContext(), i, R.styleable.TextAppearance);
        if (obtainStyledAttributes.hasValue(R.styleable.TextAppearance_android_textColor)) {
            this.n = obtainStyledAttributes.getColorStateList(R.styleable.TextAppearance_android_textColor);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.TextAppearance_android_textSize)) {
            this.l = (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, (int) this.l);
        }
        this.P = obtainStyledAttributes.getInt(R.styleable.TextAppearance_android_shadowColor, 0);
        this.N = obtainStyledAttributes.getFloat(R.styleable.TextAppearance_android_shadowDx, 0.0f);
        this.O = obtainStyledAttributes.getFloat(R.styleable.TextAppearance_android_shadowDy, 0.0f);
        this.M = obtainStyledAttributes.getFloat(R.styleable.TextAppearance_android_shadowRadius, 0.0f);
        obtainStyledAttributes.recycle();
        if (VERSION.SDK_INT >= 16) {
            this.u = e(i);
        }
        recalculate();
    }

    void d(int i) {
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(this.c.getContext(), i, R.styleable.TextAppearance);
        if (obtainStyledAttributes.hasValue(R.styleable.TextAppearance_android_textColor)) {
            this.m = obtainStyledAttributes.getColorStateList(R.styleable.TextAppearance_android_textColor);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.TextAppearance_android_textSize)) {
            this.k = (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, (int) this.k);
        }
        this.T = obtainStyledAttributes.getInt(R.styleable.TextAppearance_android_shadowColor, 0);
        this.R = obtainStyledAttributes.getFloat(R.styleable.TextAppearance_android_shadowDx, 0.0f);
        this.S = obtainStyledAttributes.getFloat(R.styleable.TextAppearance_android_shadowDy, 0.0f);
        this.Q = obtainStyledAttributes.getFloat(R.styleable.TextAppearance_android_shadowRadius, 0.0f);
        obtainStyledAttributes.recycle();
        if (VERSION.SDK_INT >= 16) {
            this.v = e(i);
        }
        recalculate();
    }

    private Typeface e(int i) {
        TypedArray obtainStyledAttributes = this.c.getContext().obtainStyledAttributes(i, new int[]{16843692});
        try {
            String string = obtainStyledAttributes.getString(0);
            if (string != null) {
                Typeface create = Typeface.create(string, 0);
                return create;
            }
            obtainStyledAttributes.recycle();
            return null;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    void a(Typeface typeface) {
        if (a(this.u, typeface)) {
            this.u = typeface;
            recalculate();
        }
    }

    void b(Typeface typeface) {
        if (a(this.v, typeface)) {
            this.v = typeface;
            recalculate();
        }
    }

    void c(Typeface typeface) {
        this.v = typeface;
        this.u = typeface;
        recalculate();
    }

    Typeface d() {
        return this.u != null ? this.u : Typeface.DEFAULT;
    }

    Typeface e() {
        return this.v != null ? this.v : Typeface.DEFAULT;
    }

    void b(float f) {
        float clamp = MathUtils.clamp(f, 0.0f, 1.0f);
        if (clamp != this.e) {
            this.e = clamp;
            k();
        }
    }

    final boolean a(int[] iArr) {
        this.H = iArr;
        if (!f()) {
            return false;
        }
        recalculate();
        return true;
    }

    final boolean f() {
        return (this.n != null && this.n.isStateful()) || (this.m != null && this.m.isStateful());
    }

    float g() {
        return this.e;
    }

    float h() {
        return this.l;
    }

    private void k() {
        c(this.e);
    }

    private void c(float f) {
        d(f);
        this.s = a(this.q, this.r, f, this.K);
        this.t = a(this.o, this.p, f, this.K);
        e(a(this.k, this.l, f, this.L));
        if (this.n != this.m) {
            this.J.setColor(a(l(), m(), f));
        } else {
            this.J.setColor(m());
        }
        this.J.setShadowLayer(a(this.Q, this.M, f, null), a(this.R, this.N, f, null), a(this.S, this.O, f, null), a(this.T, this.P, f));
        ViewCompat.postInvalidateOnAnimation(this.c);
    }

    @ColorInt
    private int l() {
        if (this.H != null) {
            return this.m.getColorForState(this.H, 0);
        }
        return this.m.getDefaultColor();
    }

    @ColorInt
    private int m() {
        if (this.H != null) {
            return this.n.getColorForState(this.H, 0);
        }
        return this.n.getDefaultColor();
    }

    private void n() {
        int i;
        int i2 = 1;
        float f = 0.0f;
        float f2 = this.G;
        f(this.l);
        float measureText = this.y != null ? this.J.measureText(this.y, 0, this.y.length()) : 0.0f;
        int i3 = this.j;
        if (this.z) {
            i = 1;
        } else {
            i = 0;
        }
        i = GravityCompat.getAbsoluteGravity(i3, i);
        switch (i & 112) {
            case 48:
                this.p = ((float) this.g.top) - this.J.ascent();
                break;
            case 80:
                this.p = (float) this.g.bottom;
                break;
            default:
                this.p = (((this.J.descent() - this.J.ascent()) / 2.0f) - this.J.descent()) + ((float) this.g.centerY());
                break;
        }
        switch (i & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK) {
            case 1:
                this.r = ((float) this.g.centerX()) - (measureText / 2.0f);
                break;
            case 5:
                this.r = ((float) this.g.right) - measureText;
                break;
            default:
                this.r = (float) this.g.left;
                break;
        }
        f(this.k);
        if (this.y != null) {
            f = this.J.measureText(this.y, 0, this.y.length());
        }
        int i4 = this.i;
        if (!this.z) {
            i2 = 0;
        }
        i4 = GravityCompat.getAbsoluteGravity(i4, i2);
        switch (i4 & 112) {
            case 48:
                this.o = ((float) this.f.top) - this.J.ascent();
                break;
            case 80:
                this.o = (float) this.f.bottom;
                break;
            default:
                this.o = (((this.J.descent() - this.J.ascent()) / 2.0f) - this.J.descent()) + ((float) this.f.centerY());
                break;
        }
        switch (i4 & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK) {
            case 1:
                this.q = ((float) this.f.centerX()) - (f / 2.0f);
                break;
            case 5:
                this.q = ((float) this.f.right) - f;
                break;
            default:
                this.q = (float) this.f.left;
                break;
        }
        p();
        e(f2);
    }

    private void d(float f) {
        this.h.left = a((float) this.f.left, (float) this.g.left, f, this.K);
        this.h.top = a(this.o, this.p, f, this.K);
        this.h.right = a((float) this.f.right, (float) this.g.right, f, this.K);
        this.h.bottom = a((float) this.f.bottom, (float) this.g.bottom, f, this.K);
    }

    public void draw(Canvas canvas) {
        int save = canvas.save();
        if (this.y != null && this.d) {
            float f;
            float f2 = this.s;
            float f3 = this.t;
            int i = (!this.A || this.B == null) ? 0 : 1;
            float f4;
            if (i != 0) {
                f = this.D * this.F;
                f4 = this.E * this.F;
            } else {
                f = this.J.ascent() * this.F;
                f4 = this.J.descent() * this.F;
            }
            if (i != 0) {
                f3 += f;
            }
            if (this.F != 1.0f) {
                canvas.scale(this.F, this.F, f2, f3);
            }
            if (i != 0) {
                canvas.drawBitmap(this.B, f2, f3, this.C);
            } else {
                canvas.drawText(this.y, 0, this.y.length(), f2, f3, this.J);
            }
        }
        canvas.restoreToCount(save);
    }

    private boolean b(CharSequence charSequence) {
        int i = 1;
        if (ViewCompat.getLayoutDirection(this.c) != 1) {
            i = 0;
        }
        return (i != 0 ? TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL : TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR).isRtl(charSequence, 0, charSequence.length());
    }

    private void e(float f) {
        f(f);
        boolean z = a && this.F != 1.0f;
        this.A = z;
        if (this.A) {
            o();
        }
        ViewCompat.postInvalidateOnAnimation(this.c);
    }

    private boolean a(Typeface typeface, Typeface typeface2) {
        return !(typeface == null || typeface.equals(typeface2)) || (typeface == null && typeface2 != null);
    }

    private void f(float f) {
        boolean z = true;
        if (this.x != null) {
            float f2;
            boolean z2;
            float width = (float) this.g.width();
            float width2 = (float) this.f.width();
            if (a(f, this.l)) {
                f2 = this.l;
                this.F = 1.0f;
                if (a(this.w, this.u)) {
                    this.w = this.u;
                    z2 = true;
                } else {
                    z2 = false;
                }
            } else {
                f2 = this.k;
                if (a(this.w, this.v)) {
                    this.w = this.v;
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (a(f, this.k)) {
                    this.F = 1.0f;
                } else {
                    this.F = f / this.k;
                }
                float f3 = this.l / this.k;
                width = width2 * f3 > width ? Math.min(width / f3, width2) : width2;
            }
            if (width > 0.0f) {
                if (this.G != f2 || this.I || r0) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                this.G = f2;
                this.I = false;
            }
            if (this.y == null || r0) {
                this.J.setTextSize(this.G);
                this.J.setTypeface(this.w);
                TextPaint textPaint = this.J;
                if (this.F == 1.0f) {
                    z = false;
                }
                textPaint.setLinearText(z);
                CharSequence ellipsize = TextUtils.ellipsize(this.x, this.J, width, TruncateAt.END);
                if (!TextUtils.equals(ellipsize, this.y)) {
                    this.y = ellipsize;
                    this.z = b(this.y);
                }
            }
        }
    }

    private void o() {
        if (this.B == null && !this.f.isEmpty() && !TextUtils.isEmpty(this.y)) {
            c(0.0f);
            this.D = this.J.ascent();
            this.E = this.J.descent();
            int round = Math.round(this.J.measureText(this.y, 0, this.y.length()));
            int round2 = Math.round(this.E - this.D);
            if (round > 0 && round2 > 0) {
                this.B = Bitmap.createBitmap(round, round2, Config.ARGB_8888);
                new Canvas(this.B).drawText(this.y, 0, this.y.length(), 0.0f, ((float) round2) - this.J.descent(), this.J);
                if (this.C == null) {
                    this.C = new Paint(3);
                }
            }
        }
    }

    public void recalculate() {
        if (this.c.getHeight() > 0 && this.c.getWidth() > 0) {
            n();
            k();
        }
    }

    void a(CharSequence charSequence) {
        if (charSequence == null || !charSequence.equals(this.x)) {
            this.x = charSequence;
            this.y = null;
            p();
            recalculate();
        }
    }

    CharSequence i() {
        return this.x;
    }

    private void p() {
        if (this.B != null) {
            this.B.recycle();
            this.B = null;
        }
    }

    private static boolean a(float f, float f2) {
        return Math.abs(f - f2) < 0.001f;
    }

    ColorStateList j() {
        return this.n;
    }

    private static int a(int i, int i2, float f) {
        float f2 = 1.0f - f;
        return Color.argb((int) ((((float) Color.alpha(i)) * f2) + (((float) Color.alpha(i2)) * f)), (int) ((((float) Color.red(i)) * f2) + (((float) Color.red(i2)) * f)), (int) ((((float) Color.green(i)) * f2) + (((float) Color.green(i2)) * f)), (int) ((f2 * ((float) Color.blue(i))) + (((float) Color.blue(i2)) * f)));
    }

    private static float a(float f, float f2, float f3, Interpolator interpolator) {
        if (interpolator != null) {
            f3 = interpolator.getInterpolation(f3);
        }
        return a.a(f, f2, f3);
    }

    private static boolean a(Rect rect, int i, int i2, int i3, int i4) {
        return rect.left == i && rect.top == i2 && rect.right == i3 && rect.bottom == i4;
    }
}
