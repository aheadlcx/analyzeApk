package com.edmodo.cropper.cropwindow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import com.edmodo.cropper.a.a;
import com.edmodo.cropper.a.b;
import com.edmodo.cropper.a.d;
import com.edmodo.cropper.cropwindow.edge.Edge;
import com.edmodo.cropper.cropwindow.handle.Handle;

public class CropOverlayView extends View {
    private static final float a = d.b();
    private static final float b = d.c();
    private static final float c = ((a / 2.0f) - (b / 1.0f));
    private static final float d = ((a / 2.0f) + c);
    private Paint e;
    private Paint f;
    private Paint g;
    private Paint h;
    private Rect i;
    private float j;
    private float k;
    private Pair<Float, Float> l;
    private Handle m;
    private boolean n = false;
    private int o = 1;
    private int p = 1;
    private float q = (((float) this.o) / ((float) this.p));
    private int r;
    private boolean s = false;
    private float t;
    private float u;
    private float v;

    public CropOverlayView(Context context) {
        super(context);
        a(context);
    }

    public CropOverlayView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public void a() {
        this.n = false;
        this.o = 1;
        this.p = 1;
        this.q = ((float) this.o) / ((float) this.p);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        a(this.i, false);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        a(canvas, this.i);
        if (this.r == 2) {
            a(canvas);
        } else if (this.r == 1) {
            if (this.m != null) {
                a(canvas);
            }
        } else if (this.r == 0) {
        }
        canvas.drawRect(Edge.LEFT.getCoordinate(), Edge.TOP.getCoordinate(), Edge.RIGHT.getCoordinate(), Edge.BOTTOM.getCoordinate(), this.e);
        b(canvas);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                a(motionEvent.getX(), motionEvent.getY());
                return true;
            case 1:
            case 3:
                getParent().requestDisallowInterceptTouchEvent(false);
                d();
                return true;
            case 2:
                b(motionEvent.getX(), motionEvent.getY());
                getParent().requestDisallowInterceptTouchEvent(true);
                return true;
            default:
                return false;
        }
    }

    public void setBitmapRect(Rect rect) {
        this.i = rect;
        a(this.i, false);
    }

    public void b() {
        if (this.s) {
            a(this.i, false);
            invalidate();
        }
    }

    public void setGuidelines(int i) {
        if (i < 0 || i > 2) {
            throw new IllegalArgumentException("Guideline value must be set between 0 and 2. See documentation.");
        }
        this.r = i;
        if (this.s) {
            a(this.i, false);
            invalidate();
        }
    }

    public void a(boolean z, boolean z2) {
        this.n = z;
        if (this.s) {
            a(this.i, z2);
            invalidate();
        }
    }

    public void setAspectRatioX(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Cannot set aspect ratio value to a number less than or equal to 0.");
        }
        this.o = i;
        this.q = ((float) this.o) / ((float) this.p);
        if (this.s) {
            a(this.i, false);
            invalidate();
        }
    }

    public void setAspectRatioY(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Cannot set aspect ratio value to a number less than or equal to 0.");
        }
        this.p = i;
        this.q = ((float) this.o) / ((float) this.p);
        if (this.s) {
            a(this.i, false);
            invalidate();
        }
    }

    public void a(int i, boolean z, int i2, int i3) {
        if (i < 0 || i > 2) {
            throw new IllegalArgumentException("Guideline value must be set between 0 and 2. See documentation.");
        }
        this.r = i;
        this.n = z;
        if (i2 <= 0) {
            throw new IllegalArgumentException("Cannot set aspect ratio value to a number less than or equal to 0.");
        }
        this.o = i2;
        this.q = ((float) this.o) / ((float) this.p);
        if (i3 <= 0) {
            throw new IllegalArgumentException("Cannot set aspect ratio value to a number less than or equal to 0.");
        }
        this.p = i3;
        this.q = ((float) this.o) / ((float) this.p);
    }

    private void a(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.j = b.a(context);
        this.k = TypedValue.applyDimension(1, 6.0f, displayMetrics);
        this.e = d.a(context);
        this.f = d.a();
        this.h = d.b(context);
        this.g = d.c(context);
        this.u = TypedValue.applyDimension(1, c, displayMetrics);
        this.t = TypedValue.applyDimension(1, d, displayMetrics);
        this.v = TypedValue.applyDimension(1, 20.0f, displayMetrics);
        this.r = 1;
    }

    private void a(Rect rect, boolean z) {
        if (!this.s) {
            this.s = true;
        }
        float f;
        float width;
        if (!this.n) {
            f = 0.0f;
            if (z) {
                f = 0.1f;
            }
            width = ((float) rect.width()) * f;
            f *= (float) rect.height();
            Edge.LEFT.setCoordinate(((float) rect.left) + width);
            Edge.TOP.setCoordinate(((float) rect.top) + f);
            Edge.RIGHT.setCoordinate(((float) rect.right) - width);
            Edge.BOTTOM.setCoordinate(((float) rect.bottom) - f);
        } else if (a.a(rect) > this.q) {
            Edge.TOP.setCoordinate((float) rect.top);
            Edge.BOTTOM.setCoordinate((float) rect.bottom);
            f = ((float) getWidth()) / 2.0f;
            width = Math.max(40.0f, a.a(Edge.TOP.getCoordinate(), Edge.BOTTOM.getCoordinate(), this.q));
            if (width == 40.0f) {
                this.q = 40.0f / (Edge.BOTTOM.getCoordinate() - Edge.TOP.getCoordinate());
            }
            width /= 2.0f;
            Edge.LEFT.setCoordinate(f - width);
            Edge.RIGHT.setCoordinate(f + width);
        } else {
            Edge.LEFT.setCoordinate((float) rect.left);
            Edge.RIGHT.setCoordinate((float) rect.right);
            f = ((float) getHeight()) / 2.0f;
            width = Math.max(40.0f, a.b(Edge.LEFT.getCoordinate(), Edge.RIGHT.getCoordinate(), this.q));
            if (width == 40.0f) {
                this.q = (Edge.RIGHT.getCoordinate() - Edge.LEFT.getCoordinate()) / 40.0f;
            }
            width /= 2.0f;
            Edge.TOP.setCoordinate(f - width);
            Edge.BOTTOM.setCoordinate(f + width);
        }
    }

    public static boolean c() {
        if (Math.abs(Edge.LEFT.getCoordinate() - Edge.RIGHT.getCoordinate()) < 100.0f || Math.abs(Edge.TOP.getCoordinate() - Edge.BOTTOM.getCoordinate()) < 100.0f) {
            return false;
        }
        return true;
    }

    private void a(Canvas canvas) {
        float coordinate = Edge.LEFT.getCoordinate();
        float coordinate2 = Edge.TOP.getCoordinate();
        float coordinate3 = Edge.RIGHT.getCoordinate();
        float coordinate4 = Edge.BOTTOM.getCoordinate();
        float width = Edge.getWidth() / 3.0f;
        float f = coordinate + width;
        canvas.drawLine(f, coordinate2, f, coordinate4, this.f);
        f = coordinate3 - width;
        canvas.drawLine(f, coordinate2, f, coordinate4, this.f);
        float height = Edge.getHeight() / 3.0f;
        width = coordinate2 + height;
        canvas.drawLine(coordinate, width, coordinate3, width, this.f);
        width = coordinate4 - height;
        canvas.drawLine(coordinate, width, coordinate3, width, this.f);
    }

    private void a(Canvas canvas, Rect rect) {
        Edge.LEFT.getCoordinate();
        Edge.TOP.getCoordinate();
        Edge.RIGHT.getCoordinate();
        Edge.BOTTOM.getCoordinate();
    }

    private void b(Canvas canvas) {
        float coordinate = Edge.LEFT.getCoordinate();
        float coordinate2 = Edge.TOP.getCoordinate();
        float coordinate3 = Edge.RIGHT.getCoordinate();
        float coordinate4 = Edge.BOTTOM.getCoordinate();
        canvas.drawLine(coordinate - this.u, coordinate2 - this.t, coordinate - this.u, coordinate2 + this.v, this.g);
        canvas.drawLine(coordinate, coordinate2 - this.u, coordinate + this.v, coordinate2 - this.u, this.g);
        canvas.drawLine(coordinate3 + this.u, coordinate2 - this.t, coordinate3 + this.u, coordinate2 + this.v, this.g);
        canvas.drawLine(coordinate3, coordinate2 - this.u, coordinate3 - this.v, coordinate2 - this.u, this.g);
        canvas.drawLine(coordinate - this.u, coordinate4 + this.t, coordinate - this.u, coordinate4 - this.v, this.g);
        canvas.drawLine(coordinate, coordinate4 + this.u, coordinate + this.v, coordinate4 + this.u, this.g);
        canvas.drawLine(coordinate3 + this.u, coordinate4 + this.t, coordinate3 + this.u, coordinate4 - this.v, this.g);
        canvas.drawLine(coordinate3, coordinate4 + this.u, coordinate3 - this.v, coordinate4 + this.u, this.g);
        float f = (((coordinate3 - coordinate) / 2.0f) + coordinate) - (this.v / 2.0f);
        float f2 = (((coordinate4 - coordinate2) / 2.0f) + coordinate2) - (this.v / 2.0f);
        canvas.drawLine(f, coordinate2 - this.u, f + this.v, coordinate2 - this.u, this.g);
        canvas.drawLine(coordinate3 + this.u, f2, coordinate3 + this.u, f2 + this.v, this.g);
        canvas.drawLine(f, coordinate4 + this.u, f + this.v, coordinate4 + this.u, this.g);
        canvas.drawLine(coordinate - this.u, f2, coordinate - this.u, f2 + this.v, this.g);
    }

    private void a(float f, float f2) {
        float coordinate = Edge.LEFT.getCoordinate();
        float coordinate2 = Edge.TOP.getCoordinate();
        float coordinate3 = Edge.RIGHT.getCoordinate();
        float coordinate4 = Edge.BOTTOM.getCoordinate();
        this.m = b.a(f, f2, coordinate, coordinate2, coordinate3, coordinate4, this.j);
        if (this.m != null) {
            this.l = b.a(this.m, f, f2, coordinate, coordinate2, coordinate3, coordinate4);
            invalidate();
        }
    }

    private void d() {
        if (this.m != null) {
            this.m = null;
            invalidate();
        }
    }

    @SuppressLint({"NewApi"})
    private void b(float f, float f2) {
        if (this.m != null) {
            float floatValue = f + ((Float) this.l.first).floatValue();
            float floatValue2 = f2 + ((Float) this.l.second).floatValue();
            if (this.n) {
                this.m.updateCropWindow(floatValue, floatValue2, this.q, this.i, this.k);
            } else {
                this.m.updateCropWindow(floatValue, floatValue2, this.i, this.k);
            }
            invalidate();
        }
    }
}
