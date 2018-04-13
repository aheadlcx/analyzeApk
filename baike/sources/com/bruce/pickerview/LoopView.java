package com.bruce.pickerview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import qsbk.app.R;

public class LoopView extends View {
    public static final int DEFAULT_FLING_SPEED = 8;
    public static final int MSG_INVALIDATE = 1000;
    public static final int MSG_SCROLL_LOOP = 2000;
    public static final int MSG_SELECTED_ITEM = 3000;
    private static final String a = LoopView.class.getSimpleName();
    private int A;
    private float B;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G;
    private ScheduledExecutorService b;
    private ScheduledFuture<?> c;
    private int d;
    private LoopScrollListener e;
    private GestureDetector f;
    private int g;
    private SimpleOnGestureListener h;
    private Context i;
    private Paint j;
    private Paint k;
    private Paint l;
    private ArrayList m;
    public Handler mHandler;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private float t;
    private boolean u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;

    class a implements Runnable {
        final float a;
        float b = 2.14748365E9f;
        final /* synthetic */ LoopView c;

        a(LoopView loopView, float f) {
            this.c = loopView;
            this.a = f;
        }

        public void run() {
            if (this.b == 2.14748365E9f) {
                if (Math.abs(this.a) <= 2000.0f) {
                    this.b = this.a;
                } else if (this.a > 0.0f) {
                    this.b = 2000.0f;
                } else {
                    this.b = -2000.0f;
                }
            }
            Log.i(LoopView.a, "velocity->" + this.b);
            if (Math.abs(this.b) < 0.0f || Math.abs(this.b) > 20.0f) {
                this.c.d = this.c.d - ((int) ((this.b * 10.0f) / 1000.0f));
                if (!this.c.u) {
                    float j = this.c.t * ((float) this.c.p);
                    if (this.c.d <= ((int) (((float) (-this.c.y)) * j))) {
                        this.b = 40.0f;
                        this.c.d = (int) (j * ((float) (-this.c.y)));
                    } else if (this.c.d >= ((int) (((float) ((this.c.m.size() - 1) - this.c.y)) * j))) {
                        this.c.d = (int) (j * ((float) ((this.c.m.size() - 1) - this.c.y)));
                        this.b = -40.0f;
                    }
                }
                if (this.b < 0.0f) {
                    this.b += 20.0f;
                } else {
                    this.b -= 20.0f;
                }
                this.c.mHandler.sendEmptyMessage(1000);
                return;
            }
            this.c.e();
            this.c.mHandler.sendEmptyMessage(2000);
        }
    }

    class b implements Runnable {
        int a = Integer.MAX_VALUE;
        int b = 0;
        int c;
        final /* synthetic */ LoopView d;

        public b(LoopView loopView, int i) {
            this.d = loopView;
            this.c = i;
        }

        public void run() {
            if (this.a == Integer.MAX_VALUE) {
                if (((float) this.c) > this.d.B / 2.0f) {
                    this.a = (int) (this.d.B - ((float) this.c));
                } else {
                    this.a = -this.c;
                }
            }
            this.b = (int) (((float) this.a) * 0.1f);
            if (this.b == 0) {
                if (this.a < 0) {
                    this.b = -1;
                } else {
                    this.b = 1;
                }
            }
            if (Math.abs(this.a) <= 0) {
                this.d.e();
                this.d.mHandler.sendEmptyMessage(3000);
                return;
            }
            this.d.d = this.d.d + this.b;
            this.d.mHandler.sendEmptyMessage(1000);
            this.a -= this.b;
        }
    }

    class c extends SimpleOnGestureListener {
        final /* synthetic */ LoopView a;

        c(LoopView loopView) {
            this.a = loopView;
        }

        public final boolean onDown(MotionEvent motionEvent) {
            this.a.e();
            Log.i(LoopView.a, "LoopViewGestureListener->onDown");
            return true;
        }

        public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            this.a.a(f2);
            Log.i(LoopView.a, "LoopViewGestureListener->onFling");
            return true;
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            Log.i(LoopView.a, "LoopViewGestureListener->onScroll");
            this.a.d = (int) (((float) this.a.d) + f2);
            if (!this.a.u) {
                int f3 = ((int) (((float) this.a.y) * this.a.B)) * -1;
                if (this.a.d < f3) {
                    this.a.d = f3;
                }
                f3 = (int) (((float) ((this.a.m.size() - 1) - this.a.y)) * this.a.B);
                if (this.a.d >= f3) {
                    this.a.d = f3;
                }
            }
            this.a.invalidate();
            return true;
        }
    }

    class d implements Runnable {
        final /* synthetic */ LoopView a;

        d(LoopView loopView) {
            this.a = loopView;
        }

        public final void run() {
            LoopScrollListener i = this.a.e;
            int selectedItem = this.a.getSelectedItem();
            this.a.m.get(selectedItem);
            i.onItemSelect(selectedItem);
        }
    }

    public LoopView(Context context) {
        this(context, null);
    }

    public LoopView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LoopView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = Executors.newSingleThreadScheduledExecutor();
        this.mHandler = new Handler(new a(this));
        a(context, attributeSet);
    }

    @TargetApi(21)
    public LoopView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.b = Executors.newSingleThreadScheduledExecutor();
        this.mHandler = new Handler(new a(this));
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.LoopView);
        if (obtainStyledAttributes != null) {
            this.q = obtainStyledAttributes.getColor(1, -5263441);
            this.r = obtainStyledAttributes.getColor(2, -13553359);
            this.s = obtainStyledAttributes.getColor(0, -3815995);
            this.u = obtainStyledAttributes.getBoolean(4, true);
            this.y = obtainStyledAttributes.getInt(5, -1);
            this.n = obtainStyledAttributes.getDimensionPixelSize(3, sp2px(context, 16.0f));
            this.C = obtainStyledAttributes.getInt(6, 7);
            obtainStyledAttributes.recycle();
        }
        this.t = 2.0f;
        this.i = context;
        this.h = new c(this);
        this.j = new Paint();
        this.k = new Paint();
        this.l = new Paint();
        if (VERSION.SDK_INT >= 11) {
            setLayerType(1, null);
        }
        this.f = new GestureDetector(context, this.h);
        this.f.setIsLongpressEnabled(false);
    }

    private void b() {
        if (this.m == null) {
            throw new IllegalArgumentException("data list must not be null!");
        }
        this.j.setColor(this.q);
        this.j.setAntiAlias(true);
        this.j.setTypeface(Typeface.MONOSPACE);
        this.j.setTextSize((float) this.n);
        this.k.setColor(this.r);
        this.k.setAntiAlias(true);
        this.k.setTextScaleX(1.05f);
        this.k.setTypeface(Typeface.MONOSPACE);
        this.k.setTextSize((float) this.n);
        this.l.setColor(this.s);
        this.l.setAntiAlias(true);
        this.l.setTypeface(Typeface.MONOSPACE);
        this.l.setTextSize((float) this.n);
        c();
        int i = (int) ((((float) this.p) * this.t) * ((float) (this.C - 1)));
        this.D = (int) (((double) (i * 2)) / 3.141592653589793d);
        this.F = (int) (((double) i) / 3.141592653589793d);
        if (this.y == -1) {
            if (this.u) {
                this.y = (this.m.size() + 1) / 2;
            } else {
                this.y = 0;
            }
        }
        this.x = this.y;
        invalidate();
    }

    private void c() {
        Rect rect = new Rect();
        for (int i = 0; i < this.m.size(); i++) {
            String str = (String) this.m.get(i);
            this.k.getTextBounds(str, 0, str.length(), rect);
            int width = rect.width();
            if (width > this.o) {
                this.o = width;
            }
            width = rect.height();
            if (width > this.p) {
                this.p = width;
            }
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.G = getMeasuredWidth();
        this.E = MeasureSpec.getSize(i2);
        Log.i(a, "onMeasure -> heightMode:" + MeasureSpec.getMode(i2));
        this.B = this.t * ((float) this.p);
        this.z = (this.G - this.o) / 2;
        this.A = (this.E - this.D) / 2;
        this.v = ((int) ((((float) this.D) - this.B) / 2.0f)) + this.A;
        this.w = ((int) ((((float) this.D) + this.B) / 2.0f)) + this.A;
    }

    protected void onDraw(Canvas canvas) {
        if (this.m == null) {
            super.onDraw(canvas);
            return;
        }
        int i;
        super.onDraw(canvas);
        int i2 = (int) (((float) this.d) / this.B);
        this.x = (i2 % this.m.size()) + this.y;
        if (this.u) {
            if (this.x < 0) {
                this.x = this.m.size() + this.x;
            }
            if (this.x > this.m.size() - 1) {
                this.x -= this.m.size();
            }
        } else {
            if (this.x < 0) {
                this.x = 0;
            }
            if (this.x > this.m.size() - 1) {
                this.x = this.m.size() - 1;
            }
        }
        String[] strArr = new String[this.C];
        for (i = 0; i < this.C; i++) {
            i2 = this.x - ((this.C / 2) - i);
            if (this.u) {
                if (i2 < 0) {
                    i2 += this.m.size();
                }
                if (i2 > this.m.size() - 1) {
                    i2 -= this.m.size();
                }
                strArr[i] = (String) this.m.get(i2);
            } else if (i2 < 0) {
                strArr[i] = "";
            } else if (i2 > this.m.size() - 1) {
                strArr[i] = "";
            } else {
                strArr[i] = (String) this.m.get(i2);
            }
        }
        canvas.drawLine(0.0f, (float) this.v, (float) this.G, (float) this.v, this.l);
        canvas.drawLine(0.0f, (float) this.w, (float) this.G, (float) this.w, this.l);
        i = (int) (((float) this.d) % this.B);
        for (i2 = 0; i2 < this.C; i2++) {
            canvas.save();
            float f = ((float) this.p) * this.t;
            double d = (double) (((((float) i2) * f) - ((float) i)) / ((float) this.F));
            float f2 = (float) ((180.0d * d) / 3.141592653589793d);
            if (f2 >= 180.0f || f2 <= 0.0f) {
                canvas.restore();
            } else {
                int cos = ((int) ((((double) this.F) - (Math.cos(d) * ((double) this.F))) - ((Math.sin(d) * ((double) this.p)) / 2.0d))) + this.A;
                canvas.translate(0.0f, (float) cos);
                canvas.scale(1.0f, (float) Math.sin(d));
                if (cos <= this.v) {
                    canvas.save();
                    canvas.clipRect(0, 0, this.G, this.v - cos);
                    canvas.drawText(strArr[i2], (float) this.z, (float) this.p, this.j);
                    canvas.restore();
                    canvas.save();
                    canvas.clipRect(0, this.v - cos, this.G, (int) f);
                    canvas.drawText(strArr[i2], (float) this.z, (float) this.p, this.k);
                    canvas.restore();
                } else if (this.p + cos >= this.w) {
                    canvas.save();
                    canvas.clipRect(0, 0, this.G, this.w - cos);
                    canvas.drawText(strArr[i2], (float) this.z, (float) this.p, this.k);
                    canvas.restore();
                    canvas.save();
                    canvas.clipRect(0, this.w - cos, this.G, (int) f);
                    canvas.drawText(strArr[i2], (float) this.z, (float) this.p, this.j);
                    canvas.restore();
                } else if (cos >= this.v && this.p + cos <= this.w) {
                    canvas.clipRect(0, 0, this.G, (int) f);
                    canvas.drawText(strArr[i2], (float) this.z, (float) this.p, this.k);
                    this.g = this.m.indexOf(strArr[i2]);
                }
                canvas.restore();
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 1:
            case 3:
                e();
                break;
        }
        if (!this.f.onTouchEvent(motionEvent)) {
            f();
        }
        return true;
    }

    public final void setCanLoop(boolean z) {
        this.u = z;
        invalidate();
    }

    public final void setTextSize(float f) {
        if (f > 0.0f) {
            this.n = sp2px(this.i, f);
        }
    }

    public void setInitPosition(int i) {
        this.y = i;
        invalidate();
    }

    public void setLoopListener(LoopScrollListener loopScrollListener) {
        this.e = loopScrollListener;
    }

    public final void setDataList(List<String> list) {
        this.m = (ArrayList) list;
        b();
    }

    public int getSelectedItem() {
        return this.g;
    }

    private void d() {
        if (this.e != null) {
            postDelayed(new d(this), 200);
        }
    }

    private void e() {
        if (this.c != null && !this.c.isCancelled()) {
            this.c.cancel(true);
            this.c = null;
        }
    }

    private void f() {
        int i = (int) (((float) this.d) % this.B);
        e();
        this.c = this.b.scheduleWithFixedDelay(new b(this, i), 0, 10, TimeUnit.MILLISECONDS);
    }

    private void a(float f) {
        e();
        this.c = this.b.scheduleWithFixedDelay(new a(this, f), 0, (long) 8, TimeUnit.MILLISECONDS);
    }

    public int sp2px(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().scaledDensity * f) + 0.5f);
    }
}
