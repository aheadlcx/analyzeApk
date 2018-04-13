package com.soundcloud.android.crop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region.Op;
import android.os.Build.VERSION;
import android.util.TypedValue;
import android.view.View;

class k {
    public static final int GROW_BOTTOM_EDGE = 16;
    public static final int GROW_LEFT_EDGE = 2;
    public static final int GROW_NONE = 1;
    public static final int GROW_RIGHT_EDGE = 4;
    public static final int GROW_TOP_EDGE = 8;
    public static final int MOVE = 32;
    RectF a;
    Rect b;
    Matrix c;
    private RectF d;
    private final Paint e = new Paint();
    private final Paint f = new Paint();
    private final Paint g = new Paint();
    private View h;
    private boolean i;
    private boolean j;
    private int k;
    private b l = b.None;
    private a m = a.Changing;
    private boolean n;
    private float o;
    private float p;
    private float q;
    private boolean r;

    enum a {
        Changing,
        Always,
        Never
    }

    enum b {
        None,
        Move,
        Grow
    }

    public k(View view) {
        this.h = view;
        a(view.getContext());
    }

    private void a(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.cropImageStyle, typedValue, true);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(typedValue.resourceId, R.styleable.CropImageView);
        try {
            this.i = obtainStyledAttributes.getBoolean(R.styleable.CropImageView_showThirds, false);
            this.j = obtainStyledAttributes.getBoolean(R.styleable.CropImageView_showCircle, false);
            this.k = obtainStyledAttributes.getColor(R.styleable.CropImageView_highlightColor, -13388315);
            this.m = a.values()[obtainStyledAttributes.getInt(R.styleable.CropImageView_showHandles, 0)];
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public void setup(Matrix matrix, Rect rect, RectF rectF, boolean z) {
        this.c = new Matrix(matrix);
        this.a = rectF;
        this.d = new RectF(rect);
        this.n = z;
        this.o = this.a.width() / this.a.height();
        this.b = a();
        this.e.setARGB(125, 50, 50, 50);
        this.f.setStyle(Style.STROKE);
        this.f.setAntiAlias(true);
        this.q = a(2.0f);
        this.g.setColor(this.k);
        this.g.setStyle(Style.FILL);
        this.g.setAntiAlias(true);
        this.p = a(12.0f);
        this.l = b.None;
    }

    private float a(float f) {
        return this.h.getResources().getDisplayMetrics().density * f;
    }

    protected void a(Canvas canvas) {
        canvas.save();
        Path path = new Path();
        this.f.setStrokeWidth(this.q);
        if (hasFocus()) {
            Rect rect = new Rect();
            this.h.getDrawingRect(rect);
            path.addRect(new RectF(this.b), Direction.CW);
            this.f.setColor(this.k);
            if (c(canvas)) {
                canvas.clipPath(path, Op.DIFFERENCE);
                canvas.drawRect(rect, this.e);
            } else {
                b(canvas);
            }
            canvas.restore();
            canvas.drawPath(path, this.f);
            if (this.i) {
                e(canvas);
            }
            if (this.j) {
                f(canvas);
            }
            if (this.m == a.Always || (this.m == a.Changing && this.l == b.Grow)) {
                d(canvas);
                return;
            }
            return;
        }
        this.f.setColor(-16777216);
        canvas.drawRect(this.b, this.f);
    }

    private void b(Canvas canvas) {
        canvas.drawRect(0.0f, 0.0f, (float) canvas.getWidth(), (float) this.b.top, this.e);
        canvas.drawRect(0.0f, (float) this.b.bottom, (float) canvas.getWidth(), (float) canvas.getHeight(), this.e);
        canvas.drawRect(0.0f, (float) this.b.top, (float) this.b.left, (float) this.b.bottom, this.e);
        canvas.drawRect((float) this.b.right, (float) this.b.top, (float) canvas.getWidth(), (float) this.b.bottom, this.e);
    }

    @SuppressLint({"NewApi"})
    private boolean c(Canvas canvas) {
        if (VERSION.SDK_INT == 17) {
            return false;
        }
        if (VERSION.SDK_INT < 14 || VERSION.SDK_INT > 15 || !canvas.isHardwareAccelerated()) {
            return true;
        }
        return false;
    }

    private void d(Canvas canvas) {
        int i = this.b.left + ((this.b.right - this.b.left) / 2);
        int i2 = this.b.top + ((this.b.bottom - this.b.top) / 2);
        canvas.drawCircle((float) this.b.left, (float) i2, this.p, this.g);
        canvas.drawCircle((float) i, (float) this.b.top, this.p, this.g);
        canvas.drawCircle((float) this.b.right, (float) i2, this.p, this.g);
        canvas.drawCircle((float) i, (float) this.b.bottom, this.p, this.g);
    }

    private void e(Canvas canvas) {
        this.f.setStrokeWidth(1.0f);
        float f = (float) ((this.b.right - this.b.left) / 3);
        float f2 = (float) ((this.b.bottom - this.b.top) / 3);
        canvas.drawLine(((float) this.b.left) + f, (float) this.b.top, ((float) this.b.left) + f, (float) this.b.bottom, this.f);
        Canvas canvas2 = canvas;
        canvas2.drawLine((f * 2.0f) + ((float) this.b.left), (float) this.b.top, (f * 2.0f) + ((float) this.b.left), (float) this.b.bottom, this.f);
        canvas.drawLine((float) this.b.left, ((float) this.b.top) + f2, (float) this.b.right, ((float) this.b.top) + f2, this.f);
        canvas2 = canvas;
        canvas2.drawLine((float) this.b.left, (f2 * 2.0f) + ((float) this.b.top), (float) this.b.right, (f2 * 2.0f) + ((float) this.b.top), this.f);
    }

    private void f(Canvas canvas) {
        this.f.setStrokeWidth(1.0f);
        canvas.drawOval(new RectF(this.b), this.f);
    }

    public void setMode(b bVar) {
        if (bVar != this.l) {
            this.l = bVar;
            this.h.invalidate();
        }
    }

    public int getHit(float f, float f2) {
        int i;
        int i2;
        Object obj = null;
        Rect a = a();
        Object obj2 = (f2 < ((float) a.top) - 20.0f || f2 >= ((float) a.bottom) + 20.0f) ? null : 1;
        if (f >= ((float) a.left) - 20.0f && f < ((float) a.right) + 20.0f) {
            obj = 1;
        }
        if (Math.abs(((float) a.left) - f) >= 20.0f || obj2 == null) {
            i = 1;
        } else {
            i = 3;
        }
        if (Math.abs(((float) a.right) - f) < 20.0f && obj2 != null) {
            i |= 4;
        }
        if (Math.abs(((float) a.top) - f2) < 20.0f && r2 != null) {
            i |= 8;
        }
        if (Math.abs(((float) a.bottom) - f2) >= 20.0f || r2 == null) {
            i2 = i;
        } else {
            i2 = i | 16;
        }
        if (i2 == 1 && a.contains((int) f, (int) f2)) {
            return 32;
        }
        return i2;
    }

    void a(int i, float f, float f2) {
        Rect a = a();
        if (i == 32) {
            a((this.a.width() / ((float) a.width())) * f, (this.a.height() / ((float) a.height())) * f2);
            return;
        }
        if ((i & 6) == 0) {
            f = 0.0f;
        }
        if ((i & 24) == 0) {
            f2 = 0.0f;
        }
        b((f * (this.a.width() / ((float) a.width()))) * ((float) ((i & 2) != 0 ? -1 : 1)), ((float) ((i & 8) != 0 ? -1 : 1)) * (f2 * (this.a.height() / ((float) a.height()))));
    }

    void a(float f, float f2) {
        Rect rect = new Rect(this.b);
        this.a.offset(f, f2);
        this.a.offset(Math.max(0.0f, this.d.left - this.a.left), Math.max(0.0f, this.d.top - this.a.top));
        this.a.offset(Math.min(0.0f, this.d.right - this.a.right), Math.min(0.0f, this.d.bottom - this.a.bottom));
        this.b = a();
        rect.union(this.b);
        rect.inset(-((int) this.p), -((int) this.p));
        this.h.invalidate(rect);
    }

    void b(float f, float f2) {
        float f3;
        float f4;
        if (this.n) {
            if (f != 0.0f) {
                f2 = f / this.o;
            } else if (f2 != 0.0f) {
                f = f2 * this.o;
            }
        }
        RectF rectF = new RectF(this.a);
        if (f > 0.0f && rectF.width() + (2.0f * f) > this.d.width()) {
            f = (this.d.width() - rectF.width()) / 2.0f;
            if (this.n) {
                f3 = f / this.o;
                f4 = f;
                if (f3 > 0.0f && rectF.height() + (2.0f * f3) > this.d.height()) {
                    f3 = (this.d.height() - rectF.height()) / 2.0f;
                    if (this.n) {
                        f4 = this.o * f3;
                    }
                }
                rectF.inset(-f4, -f3);
                if (rectF.width() < 25.0f) {
                    rectF.inset((-(25.0f - rectF.width())) / 2.0f, 0.0f);
                }
                f3 = this.n ? 25.0f / this.o : 25.0f;
                if (rectF.height() < f3) {
                    rectF.inset(0.0f, (-(f3 - rectF.height())) / 2.0f);
                }
                if (rectF.left < this.d.left) {
                    rectF.offset(this.d.left - rectF.left, 0.0f);
                } else if (rectF.right > this.d.right) {
                    rectF.offset(-(rectF.right - this.d.right), 0.0f);
                }
                if (rectF.top < this.d.top) {
                    rectF.offset(0.0f, this.d.top - rectF.top);
                } else if (rectF.bottom > this.d.bottom) {
                    rectF.offset(0.0f, -(rectF.bottom - this.d.bottom));
                }
                this.a.set(rectF);
                this.b = a();
                this.h.invalidate();
            }
        }
        f3 = f2;
        f4 = f;
        f3 = (this.d.height() - rectF.height()) / 2.0f;
        if (this.n) {
            f4 = this.o * f3;
        }
        rectF.inset(-f4, -f3);
        if (rectF.width() < 25.0f) {
            rectF.inset((-(25.0f - rectF.width())) / 2.0f, 0.0f);
        }
        if (this.n) {
        }
        if (rectF.height() < f3) {
            rectF.inset(0.0f, (-(f3 - rectF.height())) / 2.0f);
        }
        if (rectF.left < this.d.left) {
            rectF.offset(this.d.left - rectF.left, 0.0f);
        } else if (rectF.right > this.d.right) {
            rectF.offset(-(rectF.right - this.d.right), 0.0f);
        }
        if (rectF.top < this.d.top) {
            rectF.offset(0.0f, this.d.top - rectF.top);
        } else if (rectF.bottom > this.d.bottom) {
            rectF.offset(0.0f, -(rectF.bottom - this.d.bottom));
        }
        this.a.set(rectF);
        this.b = a();
        this.h.invalidate();
    }

    public Rect getScaledCropRect(float f) {
        return new Rect((int) (this.a.left * f), (int) (this.a.top * f), (int) (this.a.right * f), (int) (this.a.bottom * f));
    }

    private Rect a() {
        RectF rectF = new RectF(this.a.left, this.a.top, this.a.right, this.a.bottom);
        this.c.mapRect(rectF);
        return new Rect(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
    }

    public void invalidate() {
        this.b = a();
    }

    public boolean hasFocus() {
        return this.r;
    }

    public void setFocus(boolean z) {
        this.r = z;
    }
}
