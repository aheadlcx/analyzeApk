package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.State;
import android.view.MotionEvent;

@VisibleForTesting
class al extends ItemDecoration implements OnItemTouchListener {
    private static final int[] g = new int[]{16842919};
    private static final int[] h = new int[0];
    private final int[] A = new int[2];
    private final ValueAnimator B = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
    private int C = 0;
    private final Runnable D = new am(this);
    private final OnScrollListener E = new an(this);
    @VisibleForTesting
    int a;
    @VisibleForTesting
    int b;
    @VisibleForTesting
    float c;
    @VisibleForTesting
    int d;
    @VisibleForTesting
    int e;
    @VisibleForTesting
    float f;
    private final int i;
    private final int j;
    private final StateListDrawable k;
    private final Drawable l;
    private final int m;
    private final int n;
    private final StateListDrawable o;
    private final Drawable p;
    private final int q;
    private final int r;
    private int s = 0;
    private int t = 0;
    private RecyclerView u;
    private boolean v = false;
    private boolean w = false;
    private int x = 0;
    private int y = 0;
    private final int[] z = new int[2];

    private class a extends AnimatorListenerAdapter {
        final /* synthetic */ al a;
        private boolean b;

        private a(al alVar) {
            this.a = alVar;
            this.b = false;
        }

        public void onAnimationEnd(Animator animator) {
            if (this.b) {
                this.b = false;
            } else if (((Float) this.a.B.getAnimatedValue()).floatValue() == 0.0f) {
                this.a.C = 0;
                this.a.b(0);
            } else {
                this.a.C = 2;
                this.a.c();
            }
        }

        public void onAnimationCancel(Animator animator) {
            this.b = true;
        }
    }

    private class b implements AnimatorUpdateListener {
        final /* synthetic */ al a;

        private b(al alVar) {
            this.a = alVar;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int floatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
            this.a.k.setAlpha(floatValue);
            this.a.l.setAlpha(floatValue);
            this.a.c();
        }
    }

    al(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i, int i2, int i3) {
        this.k = stateListDrawable;
        this.l = drawable;
        this.o = stateListDrawable2;
        this.p = drawable2;
        this.m = Math.max(i, stateListDrawable.getIntrinsicWidth());
        this.n = Math.max(i, drawable.getIntrinsicWidth());
        this.q = Math.max(i, stateListDrawable2.getIntrinsicWidth());
        this.r = Math.max(i, drawable2.getIntrinsicWidth());
        this.i = i2;
        this.j = i3;
        this.k.setAlpha(255);
        this.l.setAlpha(255);
        this.B.addListener(new a());
        this.B.addUpdateListener(new b());
        attachToRecyclerView(recyclerView);
    }

    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) {
        if (this.u != recyclerView) {
            if (this.u != null) {
                b();
            }
            this.u = recyclerView;
            if (this.u != null) {
                a();
            }
        }
    }

    private void a() {
        this.u.addItemDecoration(this);
        this.u.addOnItemTouchListener(this);
        this.u.addOnScrollListener(this.E);
    }

    private void b() {
        this.u.removeItemDecoration(this);
        this.u.removeOnItemTouchListener(this);
        this.u.removeOnScrollListener(this.E);
        e();
    }

    private void c() {
        this.u.invalidate();
    }

    private void b(int i) {
        if (i == 2 && this.x != 2) {
            this.k.setState(g);
            e();
        }
        if (i == 0) {
            c();
        } else {
            show();
        }
        if (this.x == 2 && i != 2) {
            this.k.setState(h);
            c(1200);
        } else if (i == 1) {
            c(1500);
        }
        this.x = i;
    }

    private boolean d() {
        return ViewCompat.getLayoutDirection(this.u) == 1;
    }

    public boolean isDragging() {
        return this.x == 2;
    }

    public void show() {
        switch (this.C) {
            case 0:
                break;
            case 3:
                this.B.cancel();
                break;
            default:
                return;
        }
        this.C = 1;
        this.B.setFloatValues(new float[]{((Float) this.B.getAnimatedValue()).floatValue(), 1.0f});
        this.B.setDuration(500);
        this.B.setStartDelay(0);
        this.B.start();
    }

    public void hide() {
        a(0);
    }

    @VisibleForTesting
    void a(int i) {
        switch (this.C) {
            case 1:
                this.B.cancel();
                break;
            case 2:
                break;
            default:
                return;
        }
        this.C = 3;
        this.B.setFloatValues(new float[]{((Float) this.B.getAnimatedValue()).floatValue(), 0.0f});
        this.B.setDuration((long) i);
        this.B.start();
    }

    private void e() {
        this.u.removeCallbacks(this.D);
    }

    private void c(int i) {
        e();
        this.u.postDelayed(this.D, (long) i);
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, State state) {
        if (this.s != this.u.getWidth() || this.t != this.u.getHeight()) {
            this.s = this.u.getWidth();
            this.t = this.u.getHeight();
            b(0);
        } else if (this.C != 0) {
            if (this.v) {
                a(canvas);
            }
            if (this.w) {
                b(canvas);
            }
        }
    }

    private void a(Canvas canvas) {
        int i = this.s - this.m;
        int i2 = this.b - (this.a / 2);
        this.k.setBounds(0, 0, this.m, this.a);
        this.l.setBounds(0, 0, this.n, this.t);
        if (d()) {
            this.l.draw(canvas);
            canvas.translate((float) this.m, (float) i2);
            canvas.scale(-1.0f, 1.0f);
            this.k.draw(canvas);
            canvas.scale(1.0f, 1.0f);
            canvas.translate((float) (-this.m), (float) (-i2));
            return;
        }
        canvas.translate((float) i, 0.0f);
        this.l.draw(canvas);
        canvas.translate(0.0f, (float) i2);
        this.k.draw(canvas);
        canvas.translate((float) (-i), (float) (-i2));
    }

    private void b(Canvas canvas) {
        int i = this.t - this.q;
        int i2 = this.e - (this.d / 2);
        this.o.setBounds(0, 0, this.d, this.q);
        this.p.setBounds(0, 0, this.s, this.r);
        canvas.translate(0.0f, (float) i);
        this.p.draw(canvas);
        canvas.translate((float) i2, 0.0f);
        this.o.draw(canvas);
        canvas.translate((float) (-i2), (float) (-i));
    }

    void a(int i, int i2) {
        int computeVerticalScrollRange = this.u.computeVerticalScrollRange();
        int i3 = this.t;
        boolean z = computeVerticalScrollRange - i3 > 0 && this.t >= this.i;
        this.v = z;
        int computeHorizontalScrollRange = this.u.computeHorizontalScrollRange();
        int i4 = this.s;
        if (computeHorizontalScrollRange - i4 <= 0 || this.s < this.i) {
            z = false;
        } else {
            z = true;
        }
        this.w = z;
        if (this.v || this.w) {
            if (this.v) {
                this.b = (int) (((((float) i2) + (((float) i3) / 2.0f)) * ((float) i3)) / ((float) computeVerticalScrollRange));
                this.a = Math.min(i3, (i3 * i3) / computeVerticalScrollRange);
            }
            if (this.w) {
                this.e = (int) (((((float) i) + (((float) i4) / 2.0f)) * ((float) i4)) / ((float) computeHorizontalScrollRange));
                this.d = Math.min(i4, (i4 * i4) / computeHorizontalScrollRange);
            }
            if (this.x == 0 || this.x == 1) {
                b(1);
            }
        } else if (this.x != 0) {
            b(0);
        }
    }

    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.x == 1) {
            boolean a = a(motionEvent.getX(), motionEvent.getY());
            boolean b = b(motionEvent.getX(), motionEvent.getY());
            if (motionEvent.getAction() != 0 || (!a && !b)) {
                return false;
            }
            if (b) {
                this.y = 1;
                this.f = (float) ((int) motionEvent.getX());
            } else if (a) {
                this.y = 2;
                this.c = (float) ((int) motionEvent.getY());
            }
            b(2);
            return true;
        } else if (this.x != 2) {
            return false;
        } else {
            return true;
        }
    }

    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.x != 0) {
            if (motionEvent.getAction() == 0) {
                boolean a = a(motionEvent.getX(), motionEvent.getY());
                boolean b = b(motionEvent.getX(), motionEvent.getY());
                if (a || b) {
                    if (b) {
                        this.y = 1;
                        this.f = (float) ((int) motionEvent.getX());
                    } else if (a) {
                        this.y = 2;
                        this.c = (float) ((int) motionEvent.getY());
                    }
                    b(2);
                }
            } else if (motionEvent.getAction() == 1 && this.x == 2) {
                this.c = 0.0f;
                this.f = 0.0f;
                b(1);
                this.y = 0;
            } else if (motionEvent.getAction() == 2 && this.x == 2) {
                show();
                if (this.y == 1) {
                    b(motionEvent.getX());
                }
                if (this.y == 2) {
                    a(motionEvent.getY());
                }
            }
        }
    }

    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    private void a(float f) {
        int[] f2 = f();
        float max = Math.max((float) f2[0], Math.min((float) f2[1], f));
        if (Math.abs(((float) this.b) - max) >= 2.0f) {
            int a = a(this.c, max, f2, this.u.computeVerticalScrollRange(), this.u.computeVerticalScrollOffset(), this.t);
            if (a != 0) {
                this.u.scrollBy(0, a);
            }
            this.c = max;
        }
    }

    private void b(float f) {
        int[] g = g();
        float max = Math.max((float) g[0], Math.min((float) g[1], f));
        if (Math.abs(((float) this.e) - max) >= 2.0f) {
            int a = a(this.f, max, g, this.u.computeHorizontalScrollRange(), this.u.computeHorizontalScrollOffset(), this.s);
            if (a != 0) {
                this.u.scrollBy(a, 0);
            }
            this.f = max;
        }
    }

    private int a(float f, float f2, int[] iArr, int i, int i2, int i3) {
        int i4 = iArr[1] - iArr[0];
        if (i4 == 0) {
            return 0;
        }
        int i5 = i - i3;
        i4 = (int) (((f2 - f) / ((float) i4)) * ((float) i5));
        int i6 = i2 + i4;
        if (i6 >= i5 || i6 < 0) {
            return 0;
        }
        return i4;
    }

    @VisibleForTesting
    boolean a(float f, float f2) {
        if (d() ? f <= ((float) (this.m / 2)) : f >= ((float) (this.s - this.m))) {
            if (f2 >= ((float) (this.b - (this.a / 2))) && f2 <= ((float) (this.b + (this.a / 2)))) {
                return true;
            }
        }
        return false;
    }

    @VisibleForTesting
    boolean b(float f, float f2) {
        return f2 >= ((float) (this.t - this.q)) && f >= ((float) (this.e - (this.d / 2))) && f <= ((float) (this.e + (this.d / 2)));
    }

    private int[] f() {
        this.z[0] = this.j;
        this.z[1] = this.t - this.j;
        return this.z;
    }

    private int[] g() {
        this.A[0] = this.j;
        this.A[1] = this.s - this.j;
        return this.A;
    }
}
