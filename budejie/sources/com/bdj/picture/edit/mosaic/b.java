package com.bdj.picture.edit.mosaic;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.bdj.picture.edit.a.a;
import java.lang.reflect.Array;

@SuppressLint({"NewApi"})
public class b extends View {
    private int[][] A = ((int[][]) null);
    private int[][] B = ((int[][]) null);
    private boolean C = false;
    private boolean D = true;
    private Bitmap E;
    private int F;
    private int G;
    private int H;
    private Rect I;
    private boolean J = false;
    private boolean K = false;
    private boolean L = false;
    public Bitmap a;
    public Bitmap b;
    Activity c;
    BitmapDrawable d;
    private float e = 1.0f;
    private int f = 35;
    private Matrix g = new Matrix();
    private int h = 1;
    private int i;
    private int j;
    private float k;
    private float l;
    private float m = -1.0f;
    private float n;
    private float o;
    private float p;
    private float q;
    private float r;
    private float s;
    private float t;
    private boolean u = true;
    private Canvas v;
    private Paint w;
    private Path x;
    private float y;
    private float z;

    public b(Activity activity, AttributeSet attributeSet, Bitmap bitmap, int i, int i2, int i3) {
        super(activity, attributeSet);
        this.F = i;
        this.E = bitmap;
        this.G = this.E.getWidth();
        this.H = this.E.getHeight();
        this.a = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        this.b = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        this.v = new Canvas(this.a);
        this.v.drawBitmap(bitmap, 0.0f, 0.0f, null);
        this.I = new Rect(0, 0, this.G, this.H);
        this.d = (BitmapDrawable) activity.getResources().getDrawable(i);
        this.d.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
        this.d.setBounds(this.I);
        this.d.draw(new Canvas(this.b));
        this.c = activity;
        this.w = new Paint();
        this.w.setAlpha(0);
        this.w.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        this.w.setAntiAlias(true);
        this.w.setDither(true);
        this.w.setStyle(Style.STROKE);
        this.w.setStrokeJoin(Join.ROUND);
        this.w.setStrokeCap(Cap.ROUND);
        this.x = new Path();
        invalidate();
    }

    public void a(int i, boolean z) {
        if (this.F != i || !this.D) {
            Bitmap a = a(this.b, this.a);
            if (!(this.b == null || this.b.isRecycled())) {
                this.b.recycle();
                this.b = null;
            }
            if (!(this.a == null || this.a.isRecycled())) {
                this.a.recycle();
                this.a = null;
            }
            System.gc();
            this.a = Bitmap.createBitmap(a.getWidth(), a.getHeight(), Config.ARGB_8888);
            this.b = Bitmap.createBitmap(a.getWidth(), a.getHeight(), Config.ARGB_8888);
            this.v = new Canvas(this.a);
            this.v.drawBitmap(a, 0.0f, 0.0f, null);
            if (!(a == null || a.isRecycled())) {
                a.recycle();
            }
            Canvas canvas = new Canvas(this.b);
            this.d = (BitmapDrawable) this.c.getResources().getDrawable(i);
            this.d.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
            this.d.setBounds(this.I);
            this.d.draw(canvas);
            this.C = z;
            this.D = true;
            this.F = i;
            this.h = 1;
            invalidate();
        }
    }

    public void a(boolean z, boolean z2) {
        if (this.D != z) {
            Bitmap a = a(this.b, this.a);
            if (!(this.b == null || this.b.isRecycled())) {
                this.b.recycle();
                this.b = null;
            }
            if (!(this.a == null || this.a.isRecycled())) {
                this.a.recycle();
                this.a = null;
            }
            this.a = Bitmap.createBitmap(a.getWidth(), a.getHeight(), Config.ARGB_8888);
            this.b = Bitmap.createBitmap(a.getWidth(), a.getHeight(), Config.ARGB_8888);
            this.v = new Canvas(this.a);
            this.v.drawBitmap(a, 0.0f, 0.0f, null);
            if (!(a == null || a.isRecycled())) {
                a.recycle();
                System.gc();
            }
            Canvas canvas = new Canvas(this.b);
            if (z) {
                this.d = (BitmapDrawable) this.c.getResources().getDrawable(this.F);
                this.d.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
                this.d.setBounds(this.I);
                this.d.draw(canvas);
            } else {
                canvas.drawBitmap(this.E, 0.0f, 0.0f, null);
            }
            this.D = z;
            this.C = z2;
            this.h = 1;
            invalidate();
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            this.i = getWidth();
            this.j = getHeight();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getActionMasked()) {
            case 0:
                if (motionEvent.getPointerCount() == 1) {
                    a((motionEvent.getX() - this.o) / this.q, (motionEvent.getY() - this.p) / this.q);
                    break;
                }
                break;
            case 1:
                Log.e("AAA", "ACTION_UP");
                if (this.J) {
                    this.J = false;
                    this.K = false;
                } else {
                    this.L = true;
                    b();
                    this.m = -1.0f;
                }
                this.h = 6;
                invalidate();
                break;
            case 2:
                if (motionEvent.getPointerCount() != 1) {
                    if (motionEvent.getPointerCount() == 2) {
                        float y = (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f;
                        if (this.m == -1.0f) {
                            a(motionEvent);
                        }
                        this.n = y - this.m;
                        if (this.p + this.n > 0.0f) {
                            this.n = 0.0f;
                        } else if (((float) this.j) - (this.p + this.n) > this.l) {
                            this.n = 0.0f;
                        }
                        this.h = 4;
                        invalidate();
                        a(motionEvent);
                        break;
                    }
                }
                this.h = 5;
                this.s = motionEvent.getX();
                this.t = motionEvent.getY();
                b((this.s - this.o) / this.q, (this.t - this.p) / this.q);
                invalidate();
                break;
                break;
            case 5:
                if (motionEvent.getPointerCount() == 2) {
                    this.K = true;
                    break;
                }
                break;
            case 6:
                if (motionEvent.getPointerCount() == 2) {
                    Log.e("AAA", "ACTION_POINTER_UP");
                    this.J = true;
                    this.h = 6;
                    invalidate();
                    this.m = -1.0f;
                    break;
                }
                break;
        }
        return true;
    }

    public boolean getIsEdited() {
        return this.L;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (this.h) {
            case 1:
                d(canvas);
                e(canvas);
                ((MosaicActivity) this.c).a();
                return;
            case 4:
                c(canvas);
                return;
            case 5:
                b(canvas);
                return;
            case 6:
                e(canvas);
                return;
            case 7:
                a(canvas);
                return;
            default:
                canvas.drawBitmap(this.a, this.g, null);
                return;
        }
    }

    private void a(float f, float f2) {
        Log.e("AAA", "touch_down");
        this.x.reset();
        if (!this.K) {
            this.x.moveTo(f, f2);
            this.y = f;
            this.z = f2;
        }
    }

    private void b(float f, float f2) {
        Log.e("AAA", "touch_move");
        if (this.J) {
            this.x.reset();
            return;
        }
        float abs = Math.abs(f - this.y);
        float abs2 = Math.abs(f2 - this.z);
        if (abs >= 4.0f || abs2 >= 4.0f) {
            this.x.quadTo(this.y, this.z, (this.y + f) / 2.0f, (this.z + f2) / 2.0f);
            this.y = f;
            this.z = f2;
        }
    }

    private void b() {
        Log.e("AAA", "touch_up");
        this.x.lineTo(this.y, this.z);
        this.v.drawPath(this.x, this.w);
        this.x.reset();
    }

    private void b(Canvas canvas) {
        float f;
        float f2;
        Path path;
        Paint paint;
        float f3 = this.p;
        float height = f3 + (((float) this.a.getHeight()) * this.q);
        float f4 = this.o;
        float width = f4 + (((float) this.a.getWidth()) * this.q);
        float f5 = this.s;
        float f6 = this.t;
        float f7 = this.s;
        float f8 = this.t;
        if (this.s < 160.0f && this.t < 160.0f && this.u) {
            this.u = false;
        } else if (this.s > ((float) (canvas.getWidth() - 160)) && this.t < 160.0f && !this.u) {
            this.u = true;
        }
        if (this.t < 80.0f + f3 || this.t > height - 80.0f || this.s < 80.0f + f4 || this.s > width - 80.0f) {
            if (this.t < 80.0f + f3) {
                f8 = this.t + ((f3 + 80.0f) - this.t);
            }
            if (this.t > height - 80.0f) {
                f8 = this.t - ((this.t - height) + 80.0f);
            }
            if (this.s < 80.0f + f4) {
                f7 = this.s + ((80.0f + f4) - this.s);
            }
            if (this.s > width - 80.0f) {
                f = f8;
                f2 = this.s - ((this.s - width) + 80.0f);
                path = new Path();
                path.addRect(0.0f, 0.0f, 160.0f, 160.0f, Direction.CW);
                canvas.drawBitmap(this.b, this.g, null);
                canvas.drawBitmap(this.a, this.g, null);
                this.v.drawPath(this.x, this.w);
                if (this.u) {
                    canvas.translate((float) (canvas.getWidth() - 160), 0.0f);
                } else {
                    canvas.translate(0.0f, 0.0f);
                }
                canvas.clipPath(path);
                canvas.translate(80.0f - (1.0f * f2), 80.0f - (1.0f * f));
                canvas.drawBitmap(this.b, this.g, null);
                canvas.drawBitmap(this.a, this.g, null);
                paint = new Paint();
                paint.setAntiAlias(true);
                paint.setColor(getResources().getColor(a.mosaicdark));
                paint.setStyle(Style.FILL);
                if (this.r <= 1.0f) {
                    canvas.drawCircle(f5, f6, (7.5f * this.r) * this.e, paint);
                } else {
                    canvas.drawCircle(f5, f6, (7.5f / this.r) * this.e, paint);
                }
                paint.setColor(getResources().getColor(a.mosaicblue));
                paint.setStyle(Style.STROKE);
                paint.setStrokeWidth(4.0f);
                if (this.q <= 1.0f) {
                    canvas.drawCircle(f5, f6, (8.0f * this.r) * this.e, paint);
                } else {
                    canvas.drawCircle(f5, f6, (8.0f / this.r) * this.e, paint);
                }
                paint.setStyle(Style.STROKE);
                paint.setStrokeWidth(2.5f);
                paint.setColor(-1);
                canvas.drawRect(new RectF((f2 - 80.0f) + 1.0f, (f - 80.0f) + 1.0f, (80.0f + f2) - 1.0f, (80.0f + f) - 1.0f), paint);
            }
        }
        f = f8;
        f2 = f7;
        path = new Path();
        path.addRect(0.0f, 0.0f, 160.0f, 160.0f, Direction.CW);
        canvas.drawBitmap(this.b, this.g, null);
        canvas.drawBitmap(this.a, this.g, null);
        this.v.drawPath(this.x, this.w);
        if (this.u) {
            canvas.translate((float) (canvas.getWidth() - 160), 0.0f);
        } else {
            canvas.translate(0.0f, 0.0f);
        }
        canvas.clipPath(path);
        canvas.translate(80.0f - (1.0f * f2), 80.0f - (1.0f * f));
        canvas.drawBitmap(this.b, this.g, null);
        canvas.drawBitmap(this.a, this.g, null);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(a.mosaicdark));
        paint.setStyle(Style.FILL);
        if (this.r <= 1.0f) {
            canvas.drawCircle(f5, f6, (7.5f / this.r) * this.e, paint);
        } else {
            canvas.drawCircle(f5, f6, (7.5f * this.r) * this.e, paint);
        }
        paint.setColor(getResources().getColor(a.mosaicblue));
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(4.0f);
        if (this.q <= 1.0f) {
            canvas.drawCircle(f5, f6, (8.0f / this.r) * this.e, paint);
        } else {
            canvas.drawCircle(f5, f6, (8.0f * this.r) * this.e, paint);
        }
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(2.5f);
        paint.setColor(-1);
        canvas.drawRect(new RectF((f2 - 80.0f) + 1.0f, (f - 80.0f) + 1.0f, (80.0f + f2) - 1.0f, (80.0f + f) - 1.0f), paint);
    }

    private void c(Canvas canvas) {
        this.g.reset();
        float f = this.p + this.n;
        this.g.postScale(this.q, this.q);
        this.g.postTranslate(0.0f, f);
        this.p = f;
        canvas.drawBitmap(this.b, this.g, null);
        canvas.drawBitmap(this.a, this.g, null);
    }

    private void d(Canvas canvas) {
        if (this.a != null) {
            this.g.reset();
            int width = this.a.getWidth();
            int height = this.a.getHeight();
            float f;
            float f2;
            if (width > this.i || height > this.j) {
                f = ((float) this.i) / (((float) width) * 1.0f);
                this.g.postScale(f, f);
                f2 = (((float) this.j) - (((float) height) * f)) / 2.0f;
                if (width - this.i > height - this.j) {
                    this.g.postTranslate(0.0f, f2);
                    this.p = f2;
                }
                this.r = f;
                this.q = f;
                this.k = ((float) width) * this.r;
                this.l = ((float) height) * this.r;
            } else {
                f = ((float) this.i) / (((float) width) * 1.0f);
                this.g.postScale(this.q, this.q);
                f2 = (((float) this.j) - (((float) height) * f)) / 2.0f;
                if (((float) this.i) / (((float) width) * 1.0f) < ((float) this.j) / (((float) height) * 1.0f)) {
                    this.g.postTranslate(0.0f, f2);
                    this.p = f2;
                }
                this.r = f;
                this.q = f;
                this.k = ((float) width) * this.r;
                this.l = ((float) height) * this.r;
            }
            if (this.C) {
                this.A = (int[][]) Array.newInstance(Integer.TYPE, new int[]{this.b.getWidth(), this.b.getHeight()});
                this.B = (int[][]) Array.newInstance(Integer.TYPE, new int[]{this.b.getWidth(), this.b.getHeight()});
                for (height = 0; height < this.b.getHeight(); height++) {
                    for (width = 0; width < this.b.getWidth(); width++) {
                        this.A[width][height] = this.b.getPixel(width, height);
                    }
                }
                a(this.B, this.A);
                for (width = 0; width < this.b.getWidth(); width++) {
                    for (height = 0; height < this.b.getHeight(); height++) {
                        this.b.setPixel(width, height, this.B[width][height]);
                    }
                }
            }
            canvas.drawBitmap(this.a, this.g, null);
            canvas.drawBitmap(this.b, this.g, null);
            this.w.setStrokeWidth((20.0f / this.q) * this.e);
        }
    }

    public void a(Canvas canvas) {
        canvas.drawBitmap(this.b, this.g, null);
        canvas.drawBitmap(this.a, this.g, null);
        this.v.drawPath(this.x, this.w);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(a.mosaicblue));
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(4.0f);
        if (this.q > 1.0f) {
            canvas.drawCircle((float) (this.i / 2), (float) (this.j / 2), (this.r * 8.0f) * this.e, paint);
        } else {
            canvas.drawCircle((float) (this.i / 2), (float) (this.j / 2), (8.0f / this.r) * this.e, paint);
        }
    }

    public void setStrokeMultiples(float f) {
        this.e = f;
        this.w.setStrokeWidth((20.0f / this.q) * f);
        this.h = 7;
        invalidate();
    }

    public void a() {
        this.h = 6;
        invalidate();
    }

    private void e(Canvas canvas) {
        try {
            this.g.reset();
            this.g.postScale(this.q, this.q);
            this.g.postTranslate(this.o, this.p);
            canvas.drawBitmap(this.b, this.g, null);
            canvas.drawBitmap(this.a, this.g, null);
        } catch (Exception e) {
        }
    }

    private void a(MotionEvent motionEvent) {
        this.m = (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f;
    }

    public Bitmap a(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap == null) {
            return null;
        }
        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int width2 = bitmap2.getWidth();
            int height2 = bitmap2.getHeight();
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, null);
            canvas.drawBitmap(bitmap2, (float) ((width - width2) / 2), (float) ((height - height2) / 2), null);
            canvas.save(31);
            canvas.restore();
            return createBitmap;
        } catch (Exception e) {
            return null;
        }
    }

    private void a(int[][] iArr, int[][] iArr2) {
        int height = this.b.getHeight();
        int width = this.b.getWidth();
        int i;
        for (int i2 = 0; i2 < height; i2 += i) {
            i = this.f;
            while (i2 + i > height) {
                i--;
            }
            int i3;
            for (int i4 = 0; i4 < width; i4 += i3) {
                int i5 = 0;
                int i6 = 0;
                int i7 = 0;
                i3 = this.f;
                while (i4 + i3 > width) {
                    i3--;
                }
                for (int i8 = 0; i8 < i; i8++) {
                    int i9;
                    for (i9 = 0; i9 < i3; i9++) {
                        i5 += Color.red(iArr2[i9 + i4][i8 + i2]);
                        i6 += Color.green(iArr2[i9 + i4][i8 + i2]);
                        i7 += Color.blue(iArr2[i9 + i4][i8 + i2]);
                    }
                }
                i6 = Color.rgb(i5 / (i3 * i), i6 / (i3 * i), i7 / (i3 * i));
                for (i7 = 0; i7 < i; i7++) {
                    for (i9 = 0; i9 < i3; i9++) {
                        iArr[i9 + i4][i7 + i2] = i6;
                    }
                }
            }
        }
    }

    public int[][] getColor() {
        return this.A;
    }

    public int[][] getNewColor() {
        return this.B;
    }
}
