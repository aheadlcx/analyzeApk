package com.bdj.picture.edit.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import com.bdj.picture.edit.bean.BVType;
import com.bdj.picture.edit.bean.CPoint;
import com.bdj.picture.edit.bean.a;
import com.bdj.picture.edit.bean.e;
import com.bdj.picture.edit.bean.f;
import com.bdj.picture.edit.e.c;
import com.bdj.picture.edit.util.i;

public class HSuperImageView extends View implements c {
    Point A;
    Point B;
    int C;
    int D;
    String E;
    String F;
    a G;
    Bitmap H;
    String I;
    public boolean J = true;
    e K;
    f L;
    boolean M = true;
    boolean N = false;
    long O = 0;
    private final Context P;
    private Bitmap Q;
    private Bitmap R;
    private Bitmap S;
    private Bitmap T;
    private final Paint U;
    private HSuperImageView$a V;
    int a;
    int b;
    int c;
    int d;
    int e;
    int f;
    Matrix g = new Matrix();
    Matrix h = new Matrix();
    int i = 0;
    PointF j = new PointF();
    PointF k = new PointF();
    PointF l = new PointF();
    PointF m = new PointF();
    long n = 0;
    double o = 0.0d;
    float p = 1.0f;
    public Point q;
    public float r;
    public float s;
    public int t = 0;
    public int u = 0;
    Point v;
    Point w;
    Point x;
    Point y;
    Point z;

    public int getViewW() {
        return this.c;
    }

    public int getViewH() {
        return this.d;
    }

    public int getViewL() {
        return this.e;
    }

    public int getViewT() {
        return this.f;
    }

    public HSuperImageView(Context context, f fVar, boolean z) {
        super(context);
        this.P = context;
        this.U = new Paint();
        this.U.setAntiAlias(true);
        this.U.setStyle(Style.STROKE);
        this.L = fVar;
        this.G = fVar.b();
        this.K = fVar.c();
        this.I = fVar.f();
        this.E = this.G.b().e();
        this.F = this.G.b().m();
        this.N = z;
        a();
    }

    public HSuperImageView(Context context, f fVar) {
        super(context);
        this.P = context;
        this.U = new Paint();
        this.U.setAntiAlias(true);
        this.U.setStyle(Style.STROKE);
        this.L = fVar;
        this.G = fVar.b();
        this.K = fVar.c();
        this.I = fVar.f();
        this.E = this.G.b().e();
        this.F = this.G.b().m();
        a();
    }

    public HSuperImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.P = context;
        this.U = new Paint();
        this.U.setAntiAlias(true);
        this.U.setStyle(Style.STROKE);
        a();
    }

    public HSuperImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.P = context;
        this.U = new Paint();
        this.U.setAntiAlias(true);
        this.U.setStyle(Style.STROKE);
        a();
    }

    public void a() {
        if (this.G.a() == BVType.IE_WATERMARK) {
            this.Q = i.a(this.P, this.E);
        } else {
            this.Q = i.b(this.P, this.F);
        }
        if (this.L.h()) {
            this.Q = com.bdj.picture.edit.mosaic.a.a(this.Q);
        }
        Log.d("savePic", "init mBitmap.getWidth()=" + this.Q.getWidth());
        Log.d("savePic", "init mBitmap.getHeight()=" + this.Q.getHeight());
        if (!this.N) {
            this.K.b((((float) ((Activity) this.P).getWindowManager().getDefaultDisplay().getWidth()) / 2.5f) / ((float) this.Q.getWidth()));
        }
        this.H = this.Q;
        this.R = BitmapFactory.decodeResource(getResources(), com.bdj.picture.edit.a.c.ic_close_suspend_layer);
        this.S = BitmapFactory.decodeResource(getResources(), com.bdj.picture.edit.a.c.ic_copy_suspend_layer);
        this.T = BitmapFactory.decodeResource(getResources(), com.bdj.picture.edit.a.c.ic_rotate_suspend_layer);
        this.t = this.R.getWidth() / 2;
        this.u = this.R.getHeight() / 2;
        a(this.Q, this.K.a(), this.K.b(), this.K.c(), true);
    }

    public Bitmap getmBitmap() {
        return this.Q;
    }

    public e getViewParam() {
        return this.K;
    }

    public a getEditParam() {
        return this.G;
    }

    public f getWidgets() {
        return this.L;
    }

    public String getContent() {
        return this.I;
    }

    public void a(Bitmap bitmap, String str) {
        this.H = bitmap;
        this.I = str;
        invalidate();
    }

    public void b() {
        this.Q = com.bdj.picture.edit.mosaic.a.a(this.Q);
        this.L.a(!this.L.h());
        int g = this.G.b().g();
        this.G.b().b(this.G.b().i());
        this.G.b().d(g);
        this.H = i.a(this.P, this.Q, this.I, this.G.b());
        this.J = true;
        invalidate();
    }

    public void setSuperViewClickListener(HSuperImageView$a hSuperImageView$a) {
        this.V = hSuperImageView$a;
    }

    public void a(int i, int i2, int i3, int i4, boolean z) {
        int i5 = (this.u * 2) + i2;
        int i6 = i3 - this.t;
        int i7 = i4 - this.u;
        this.c = (this.t * 2) + i;
        this.d = i5;
        this.e = i6;
        this.f = i7;
        System.out.println("L,T,W,H" + this.e + "|" + this.f + "|" + this.c + "|" + this.d);
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        if (layoutParams != null) {
            layoutParams.topMargin = this.f;
            layoutParams.leftMargin = this.e;
            layoutParams.height = this.d;
            layoutParams.width = this.c;
            setLayoutParams(layoutParams);
        } else {
            layout(this.e, this.f, this.e + this.c, this.f + this.d);
        }
        this.K.a(new com.bdj.picture.edit.bean.c(this.e, this.f, this.e + this.c, this.f + this.d));
    }

    public void setCPoint(Point point) {
        this.q = point;
        a(this.a, this.b, this.q.x - (this.a / 2), this.q.y - (this.b / 2), false);
    }

    public void a(Bitmap bitmap, Point point, float f, float f2) {
        a(bitmap, point, f, f2, false);
    }

    public void a(Bitmap bitmap, Point point, float f, float f2, boolean z) {
        this.Q = bitmap;
        this.q = point;
        this.r = f;
        this.s = f2;
        this.K.a((CPoint) point);
        this.K.a(f);
        this.K.b(f2);
        a(0, 0, (int) (((float) this.Q.getWidth()) * this.s), (int) (((float) this.Q.getHeight()) * this.s), f);
        this.g = new Matrix();
        this.g.setScale(f2, f2);
        this.g.postRotate(f % 360.0f, (((float) this.Q.getWidth()) * f2) / 2.0f, (((float) this.Q.getHeight()) * f2) / 2.0f);
        this.g.postTranslate((float) (this.C + this.t), (float) (this.D + this.u));
        a(this.a, this.b, this.q.x - (this.a / 2), this.q.y - (this.b / 2), z);
    }

    public static Point a(Point point, Point point2, float f) {
        point2.x -= point.x;
        point2.y -= point.y;
        double d = 0.0d;
        Point point3 = new Point();
        double sqrt = Math.sqrt((double) ((point2.x * point2.x) + (point2.y * point2.y)));
        if (point2.x == 0 && point2.y == 0) {
            return point;
        }
        if (point2.x >= 0 && point2.y >= 0) {
            d = Math.asin(((double) point2.y) / sqrt);
        } else if (point2.x < 0 && point2.y >= 0) {
            d = Math.asin(((double) Math.abs(point2.x)) / sqrt) + 1.5707963267948966d;
        } else if (point2.x < 0 && point2.y < 0) {
            d = Math.asin(((double) Math.abs(point2.y)) / sqrt) + 3.141592653589793d;
        } else if (point2.x >= 0 && point2.y < 0) {
            d = Math.asin(((double) point2.x) / sqrt) + 4.71238898038469d;
        }
        d = b(a(d) + ((double) f));
        point3.x = (int) Math.round(Math.cos(d) * sqrt);
        point3.y = (int) Math.round(Math.sin(d) * sqrt);
        point3.x += point.x;
        point3.y += point.y;
        return point3;
    }

    public static double a(double d) {
        return (180.0d * d) / 3.141592653589793d;
    }

    public static double b(double d) {
        return (3.141592653589793d * d) / 180.0d;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.U.setColor(this.P.getResources().getColor(com.bdj.picture.edit.a.a.line_color));
        this.U.setStrokeWidth(5.0f);
        if (this.J) {
            canvas.drawLine((float) this.y.x, (float) this.y.y, (float) this.z.x, (float) this.z.y, this.U);
            canvas.drawLine((float) this.z.x, (float) this.z.y, (float) this.A.x, (float) this.A.y, this.U);
            canvas.drawLine((float) this.A.x, (float) this.A.y, (float) this.B.x, (float) this.B.y, this.U);
            canvas.drawLine((float) this.B.x, (float) this.B.y, (float) this.y.x, (float) this.y.y, this.U);
            canvas.drawBitmap(this.H, this.g, this.U);
            canvas.drawBitmap(this.R, (float) (this.v.x - this.t), (float) (this.v.y - this.u), this.U);
            canvas.drawBitmap(this.T, (float) (this.w.x - this.t), (float) (this.w.y - this.u), this.U);
            canvas.drawBitmap(this.S, (float) (this.x.x - this.t), (float) (this.x.y - this.u), this.U);
        } else {
            canvas.drawBitmap(this.H, this.g, this.U);
        }
        a(this.a, this.b, this.q.x - (this.a / 2), this.q.y - (this.b / 2), false);
    }

    public void a(int i, int i2, int i3, int i4, float f) {
        Point point = new Point(i, i2);
        Point point2 = new Point(i3, i2);
        Point point3 = new Point(i3, i4);
        Point point4 = new Point(i, i4);
        Point point5 = new Point((i + i3) / 2, (i2 + i4) / 2);
        this.y = a(point5, point, f);
        this.z = a(point5, point2, f);
        this.A = a(point5, point3, f);
        this.B = a(point5, point4, f);
        int i5 = this.y.x;
        int i6 = this.y.x;
        if (this.z.x > i5) {
            i5 = this.z.x;
        }
        if (this.A.x > i5) {
            i5 = this.A.x;
        }
        if (this.B.x > i5) {
            i5 = this.B.x;
        }
        if (this.z.x < i6) {
            i6 = this.z.x;
        }
        if (this.A.x < i6) {
            i6 = this.A.x;
        }
        if (this.B.x < i6) {
            i6 = this.B.x;
        }
        int i7 = i5 - i6;
        i5 = this.y.y;
        i6 = this.y.y;
        if (this.z.y > i5) {
            i5 = this.z.y;
        }
        if (this.A.y > i5) {
            i5 = this.A.y;
        }
        if (this.B.y > i5) {
            i5 = this.B.y;
        }
        if (this.z.y < i6) {
            i6 = this.z.y;
        }
        if (this.A.y < i6) {
            i6 = this.A.y;
        }
        if (this.B.y < i6) {
            i6 = this.B.y;
        }
        i5 -= i6;
        point2 = a(this.B, this.z, this.y, this.A);
        this.C = (i7 / 2) - point2.x;
        this.D = (i5 / 2) - point2.y;
        this.y.x = (this.y.x + this.C) + this.t;
        this.z.x = (this.z.x + this.C) + this.t;
        this.A.x = (this.A.x + this.C) + this.t;
        this.B.x = (this.B.x + this.C) + this.t;
        this.y.y = (this.y.y + this.D) + this.u;
        this.z.y = (this.z.y + this.D) + this.u;
        this.A.y = (this.A.y + this.D) + this.u;
        this.B.y = (this.B.y + this.D) + this.u;
        this.a = i7;
        this.b = i5;
        this.v = this.y;
        this.w = this.A;
        this.x = this.z;
    }

    public Point a(Point point, Point point2, Point point3, Point point4) {
        Point point5 = new Point(0, 0);
        double d = (double) (((point2.y - point.y) * (point.x - point3.x)) - ((point2.x - point.x) * (point.y - point3.y)));
        double d2 = (double) (((point2.y - point.y) * (point4.x - point3.x)) - ((point2.x - point.x) * (point4.y - point3.y)));
        point5.x = (int) (((double) point3.x) + ((((double) (point4.x - point3.x)) * d) / d2));
        point5.y = (int) (((d * ((double) (point4.y - point3.y))) / d2) + ((double) point3.y));
        return point5;
    }

    public int a(int i, int i2) {
        int i3 = ((i - this.v.x) * (i - this.v.x)) + ((i2 - this.v.y) * (i2 - this.v.y));
        int i4 = ((i - this.w.x) * (i - this.w.x)) + ((i2 - this.w.y) * (i2 - this.w.y));
        int i5 = ((i - this.x.x) * (i - this.x.x)) + ((i2 - this.x.y) * (i2 - this.x.y));
        System.out.println("kk1:" + i3 + "  kk2:" + i4 + "  x,y" + i + "|" + i2);
        if (i3 < this.t * this.t) {
            return 1;
        }
        if (i4 < this.t * this.t) {
            return 2;
        }
        if (i5 < this.t * this.t) {
            return 3;
        }
        return 0;
    }

    private void c() {
        if (this.V != null) {
            this.V.b(this.L, this.I);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & 255) {
            case 0:
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                if (!this.J) {
                    this.M = false;
                    this.V.a(false);
                    this.J = true;
                    invalidate();
                    break;
                }
                this.j.set(motionEvent.getX() + ((float) this.e), motionEvent.getY() + ((float) this.f));
                if (a((int) motionEvent.getX(), (int) motionEvent.getY()) != 2) {
                    if (a((int) motionEvent.getX(), (int) motionEvent.getY()) != 1) {
                        if (a((int) motionEvent.getX(), (int) motionEvent.getY()) != 3) {
                            this.i = 1;
                            break;
                        }
                        this.M = false;
                        this.V.a(this);
                        return true;
                    }
                    this.M = false;
                    this.V.a(this);
                    return true;
                }
                this.i = 4;
                this.M = false;
                break;
            case 1:
            case 5:
            case 6:
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                if (this.M) {
                    c();
                }
                this.M = true;
                this.i = 0;
                break;
            case 2:
                if (this.i == 4) {
                    this.k.set(motionEvent.getX() + ((float) this.e), motionEvent.getY() + ((float) this.f));
                    float sqrt = ((float) Math.sqrt((double) (((this.k.x - ((float) this.q.x)) * (this.k.x - ((float) this.q.x))) + ((this.k.y - ((float) this.q.y)) * (this.k.y - ((float) this.q.y)))))) / ((float) Math.sqrt((double) (((float) ((this.Q.getWidth() * this.Q.getWidth()) + (this.Q.getHeight() * this.Q.getHeight()))) / 4.0f)));
                    double a = (double) a(this.j.x, this.j.y, (float) this.q.x, (float) this.q.y);
                    double a2 = (double) a(this.k.x, this.k.y, this.j.x, this.j.y);
                    double a3 = (double) a(this.k.x, this.k.y, (float) this.q.x, (float) this.q.y);
                    a = (((a * a) + (a3 * a3)) - (a2 * a2)) / ((a * 2.0d) * a3);
                    if (a > 1.0d) {
                        System.out.println(" sf:" + sqrt + " cosB:" + a);
                        a = 1.0d;
                    }
                    float acos = (float) ((Math.acos(a) / 3.141592653589793d) * 180.0d);
                    float f = this.j.x - ((float) this.q.x);
                    float f2 = this.k.x - ((float) this.q.x);
                    float f3 = this.j.y - ((float) this.q.y);
                    float f4 = this.k.y - ((float) this.q.y);
                    if (f == 0.0f) {
                        if (f2 > 0.0f && f3 >= 0.0f && f4 >= 0.0f) {
                            acos = -acos;
                        } else if (f2 < 0.0f && f3 < 0.0f && f4 < 0.0f) {
                            acos = -acos;
                        }
                    } else if (f2 == 0.0f) {
                        if (f < 0.0f && f3 >= 0.0f && f4 >= 0.0f) {
                            acos = -acos;
                        } else if (f > 0.0f && f3 < 0.0f && f4 < 0.0f) {
                            acos = -acos;
                        }
                    } else if (f == 0.0f || f2 == 0.0f || f3 / f >= f4 / f2) {
                        if ((f2 >= 0.0f || f <= 0.0f || f3 < 0.0f || f4 < 0.0f) && (f2 <= 0.0f || f >= 0.0f || f3 >= 0.0f || f4 >= 0.0f)) {
                            acos = -acos;
                        }
                    } else if (f < 0.0f && f2 > 0.0f && f3 >= 0.0f && f4 >= 0.0f) {
                        acos = -acos;
                    } else if (f2 < 0.0f && f > 0.0f && f3 < 0.0f && f4 < 0.0f) {
                        acos = -acos;
                    }
                    this.j.x = this.k.x;
                    this.j.y = this.k.y;
                    if (sqrt == 0.0f) {
                        f = 0.1f;
                    } else if (sqrt >= 3.0f) {
                        f = 3.0f;
                    } else {
                        f = sqrt;
                    }
                    a(this.Q, this.q, acos + this.r, f);
                }
                if (this.i == 1) {
                    this.k.set(motionEvent.getX() + ((float) this.e), motionEvent.getY() + ((float) this.f));
                    Log.d("HSuperImageView", "pB.x - pA.x=" + (this.k.x - this.j.x));
                    Log.d("HSuperImageView", "pB.y - pA.y=" + (this.k.y - this.j.y));
                    if (this.k.x - this.j.x > 0.0f || this.k.y - this.j.y > 0.0f) {
                        this.M = false;
                    }
                    Point point = this.q;
                    point.x = (int) (((float) point.x) + (this.k.x - this.j.x));
                    point = this.q;
                    point.y = (int) (((float) point.y) + (this.k.y - this.j.y));
                    this.j.x = this.k.x;
                    this.j.y = this.k.y;
                    setCPoint(this.q);
                    break;
                }
                break;
        }
        return true;
    }

    private float a(float f, float f2, float f3, float f4) {
        float f5 = f - f3;
        float f6 = f2 - f4;
        return FloatMath.sqrt((f5 * f5) + (f6 * f6));
    }

    public void a(boolean z) {
        this.J = z;
        invalidate();
    }
}
