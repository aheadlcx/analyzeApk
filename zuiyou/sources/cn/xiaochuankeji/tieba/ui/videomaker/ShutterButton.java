package cn.xiaochuankeji.tieba.ui.videomaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.utils.e;

public class ShutterButton extends View implements OnGestureListener {
    private static final int a = e.a(70.0f);
    private static final int b = e.a(56.0f);
    private c c;
    private GestureDetector d;
    private boolean e;
    private boolean f;
    private Drawable g;
    private Drawable h;
    private Drawable i;
    private Paint j;
    private Paint k;
    private Paint l;
    private RectF m = new RectF();
    private RectF n = new RectF();
    private RectF o = new RectF();
    private RectF p = new RectF();
    private RectF q = new RectF();
    private RectF r = new RectF();
    private d s;
    private AnimationSet t;
    private b u;
    private boolean v;
    private long w;

    private class a extends Animation {
        final /* synthetic */ ShutterButton a;

        private a(ShutterButton shutterButton) {
            this.a = shutterButton;
        }

        protected void applyTransformation(float f, Transformation transformation) {
            super.applyTransformation(f, transformation);
            ShutterButton.b(this.a.n, this.a.m, f, this.a.r);
            this.a.l.setColor(ShutterButton.b(this.a.l.getColor(), 200, 50, f));
            this.a.invalidate();
        }
    }

    private class b extends Animation {
        final /* synthetic */ ShutterButton a;
        private boolean b;

        private b(ShutterButton shutterButton) {
            this.a = shutterButton;
        }

        public void a(boolean z) {
            this.b = z;
        }

        protected void applyTransformation(float f, Transformation transformation) {
            if (this.b) {
                f = 1.0f - f;
            }
            ShutterButton.b(this.a.o, this.a.n, f, this.a.q);
            this.a.invalidate();
            super.applyTransformation(f, transformation);
        }
    }

    public interface c {
        boolean a();

        void b();

        void c();
    }

    private class d extends Animation {
        final /* synthetic */ ShutterButton a;
        private boolean b;

        private d(ShutterButton shutterButton) {
            this.a = shutterButton;
        }

        public void a(boolean z) {
            this.b = z;
        }

        protected void applyTransformation(float f, Transformation transformation) {
            if (this.b) {
                f = 1.0f - f;
            }
            ShutterButton.b(this.a.n, this.a.o, f, this.a.p);
            this.a.k.setColor(ShutterButton.b(this.a.k.getColor(), 255, 0, f));
            this.a.g.setAlpha(ShutterButton.b(0, 255, f));
            this.a.h.setAlpha(255 - ShutterButton.b(0, 255, f));
            this.a.invalidate();
            if (transformation != null) {
                super.applyTransformation(f, transformation);
            }
        }
    }

    public ShutterButton(Context context) {
        super(context);
        a(context);
    }

    public ShutterButton(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public ShutterButton(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.d = new GestureDetector(context, this);
        this.g = ContextCompat.getDrawable(context, R.drawable.icon_record_pause);
        this.h = ContextCompat.getDrawable(context, R.drawable.icon_record_start);
        this.i = ContextCompat.getDrawable(context, R.drawable.icon_record_start_long);
        this.j = new Paint();
        this.j.setAntiAlias(true);
        this.j.setColor(-1);
        this.k = new Paint();
        this.k.setAntiAlias(true);
        this.l = new Paint();
        this.l.setAntiAlias(true);
        this.l.setColor(-1);
        this.s = new d();
        this.s.setDuration(200);
        this.t = new AnimationSet(true);
        Animation bVar = new b();
        bVar.setDuration(100);
        this.t.addAnimation(bVar);
        bVar = new a();
        bVar.setStartOffset(100);
        bVar.setDuration(800);
        bVar.setRepeatMode(2);
        bVar.setRepeatCount(-1);
        this.t.addAnimation(bVar);
        this.u = new b();
        this.u.setDuration(100);
        this.u.a(true);
    }

    public void setListener(c cVar) {
        this.c = cVar;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.d.onTouchEvent(motionEvent)) {
            int action = motionEvent.getAction() & 255;
            if (action == 1 || action == 3 || action == 4) {
                d();
                this.f = false;
            }
        }
        return true;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.m.set(0.0f, 0.0f, (float) i, (float) i2);
        float f = ((float) (i - a)) / 2.0f;
        float f2 = ((float) (i2 - a)) / 2.0f;
        this.n.set(f, f2, ((float) a) + f, ((float) a) + f2);
        f = ((float) (i - b)) / 2.0f;
        f2 = ((float) (i2 - b)) / 2.0f;
        this.o.set(f, f2, ((float) b) + f, ((float) b) + f2);
        int intrinsicWidth = (i - this.h.getIntrinsicWidth()) / 2;
        int intrinsicHeight = (i2 - this.h.getIntrinsicHeight()) / 2;
        this.h.setAlpha(255);
        this.h.setBounds(intrinsicWidth, intrinsicHeight, this.h.getIntrinsicWidth() + intrinsicWidth, this.h.getIntrinsicHeight() + intrinsicHeight);
        int intrinsicWidth2 = (i - this.g.getIntrinsicWidth()) / 2;
        int intrinsicHeight2 = (i2 - this.g.getIntrinsicHeight()) / 2;
        this.g.setBounds(intrinsicWidth2, intrinsicHeight2, this.g.getIntrinsicWidth() + intrinsicWidth2, this.g.getIntrinsicHeight() + intrinsicHeight2);
        this.g.setAlpha(0);
        this.i.setBounds(intrinsicWidth, intrinsicHeight, this.i.getIntrinsicWidth() + intrinsicWidth, this.i.getIntrinsicHeight() + intrinsicHeight);
        this.i.setAlpha(0);
        this.p.set(this.n);
        this.q.set(this.o);
        this.r.setEmpty();
    }

    private static void b(RectF rectF, RectF rectF2, float f, RectF rectF3) {
        rectF3.set(rectF.left + ((rectF2.left - rectF.left) * f), rectF.top + ((rectF2.top - rectF.top) * f), rectF.right + ((rectF2.right - rectF.right) * f), rectF.bottom + ((rectF2.bottom - rectF.bottom) * f));
    }

    private static int b(int i, int i2, int i3, float f) {
        return Color.argb(b(i2, i3, f), Color.red(i), Color.green(i), Color.blue(i));
    }

    private static int b(int i, int i2, float f) {
        return (int) (((float) i) + (((float) (i2 - i)) * f));
    }

    private boolean e() {
        return SystemClock.uptimeMillis() - this.w <= 50;
    }

    public void a(boolean z) {
        if (!this.f && !this.e && !e()) {
            this.e = true;
            this.v = false;
            this.r.setEmpty();
            this.s.a(false);
            if (z) {
                startAnimation(this.s);
            } else {
                this.s.applyTransformation(1.0f, null);
            }
            f();
        }
    }

    public void a() {
        if (this.e) {
            this.e = false;
            this.v = false;
            this.w = SystemClock.uptimeMillis();
            this.s.a(true);
            startAnimation(this.s);
            f();
        }
    }

    public void b() {
        if (!this.f && !this.e && !e()) {
            this.e = true;
            this.v = true;
            this.g.setAlpha(0);
            this.h.setAlpha(0);
            this.i.setAlpha(255);
            startAnimation(this.t);
            f();
        }
    }

    public void c() {
        if (this.e) {
            this.e = false;
            this.v = false;
            this.w = SystemClock.uptimeMillis();
            this.g.setAlpha(0);
            this.h.setAlpha(255);
            this.i.setAlpha(0);
            this.r.setEmpty();
            startAnimation(this.u);
            f();
        }
    }

    public void d() {
        if (!this.e) {
            return;
        }
        if (this.v) {
            c();
        } else {
            a();
        }
    }

    private void f() {
        if (this.c != null) {
            if (this.e) {
                this.c.b();
            } else {
                this.c.c();
            }
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawOval(this.r, this.l);
        this.h.draw(canvas);
        this.i.draw(canvas);
        this.g.draw(canvas);
    }

    public boolean onDown(MotionEvent motionEvent) {
        if (!(this.e || e() || this.c == null)) {
            this.f = !this.c.a();
        }
        return true;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        if (this.e) {
            a();
        } else {
            a(true);
        }
        return true;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public void onLongPress(MotionEvent motionEvent) {
        b();
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }
}
