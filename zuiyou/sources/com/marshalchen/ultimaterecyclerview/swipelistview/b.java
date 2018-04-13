package com.marshalchen.ultimaterecyclerview.swipelistview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Rect;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class b implements OnTouchListener {
    private View A;
    private View B;
    private View C;
    private boolean D;
    private int E;
    private int F;
    private int G;
    private List<Boolean> H;
    private List<Boolean> I;
    private List<Boolean> J;
    private int K;
    private int L;
    private int a;
    private boolean b;
    private boolean c;
    private int d;
    private int e;
    private Rect f;
    private int g;
    private int h;
    private int i;
    private long j;
    private long k;
    private float l;
    private float m;
    private int n;
    private int o;
    private boolean p;
    private LinearLayoutManager q;
    private SwipeListView r;
    private int s;
    private List<a> t;
    private int u;
    private float v;
    private boolean w;
    private boolean x;
    private VelocityTracker y;
    private int z;

    private class b implements ViewPropertyAnimatorListener {
        final /* synthetic */ b f;

        private b(b bVar) {
            this.f = bVar;
        }

        public void onAnimationStart(View view) {
        }

        public void onAnimationEnd(View view) {
        }

        public void onAnimationCancel(View view) {
        }
    }

    class a implements Comparable<a> {
        public int a;
        public View b;
        final /* synthetic */ b c;

        public /* synthetic */ int compareTo(Object obj) {
            return a((a) obj);
        }

        public a(b bVar, int i, View view) {
            this.c = bVar;
            this.a = i;
            this.b = view;
        }

        public int a(a aVar) {
            return aVar.a - this.a;
        }
    }

    static /* synthetic */ int h(b bVar) {
        int i = bVar.u - 1;
        bVar.u = i;
        return i;
    }

    private void a(View view) {
        this.A = view;
    }

    public void a(LinearLayoutManager linearLayoutManager) {
        this.q = linearLayoutManager;
    }

    private void b(View view, final int i) {
        this.B = view;
        view.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.r.a(this.a.z);
            }
        });
        view.setOnLongClickListener(new OnLongClickListener(this) {
            final /* synthetic */ b b;

            public boolean onLongClick(View view) {
                if (!this.b.b) {
                    this.b.f(i);
                } else if (this.b.z >= 0) {
                    this.b.d(i);
                }
                return false;
            }
        });
    }

    private void b(View view) {
        this.C = view;
        view.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.r.b(this.a.z);
            }
        });
    }

    public void a(boolean z) {
        this.p = z;
    }

    public void a(long j) {
        if (j > 0) {
            this.k = j;
        } else {
            this.k = this.j;
        }
    }

    public void a(float f) {
        this.m = f;
    }

    public void b(float f) {
        this.l = f;
    }

    public void b(boolean z) {
        this.c = z;
    }

    public void c(boolean z) {
        this.b = z;
    }

    public void a(int i) {
        this.a = i;
    }

    protected boolean a() {
        return this.a != 0;
    }

    public int b() {
        return this.F;
    }

    public void b(int i) {
        this.F = i;
    }

    public int c() {
        return this.G;
    }

    public void c(int i) {
        this.G = i;
    }

    public void d() {
        if (this.r.getAdapter() != null) {
            int itemCount = this.r.getAdapter().getItemCount();
            for (int size = this.H.size(); size <= itemCount; size++) {
                this.H.add(Boolean.valueOf(false));
                this.I.add(Boolean.valueOf(false));
                this.J.add(Boolean.valueOf(false));
            }
        }
    }

    protected void d(int i) {
        View findViewById = this.r.getChildAt(i - this.q.findFirstVisibleItemPosition()).findViewById(this.d);
        if (findViewById != null) {
            c(findViewById, i);
        }
    }

    private void f(int i) {
        boolean z = true;
        int e = e();
        boolean booleanValue = ((Boolean) this.J.get(i)).booleanValue();
        this.J.set(i, Boolean.valueOf(!booleanValue));
        int i2 = booleanValue ? e - 1 : e + 1;
        if (e == 0 && i2 == 1) {
            this.r.a();
            g();
            g(2);
        }
        if (e == 1 && i2 == 0) {
            this.r.b();
            h();
        }
        SwipeListView swipeListView = this.r;
        if (booleanValue) {
            z = false;
        }
        swipeListView.d(i, z);
        a(this.B, i);
    }

    protected void a(View view, int i) {
        if (e(i)) {
            if (this.n > 0) {
                view.setBackgroundResource(this.n);
            }
        } else if (this.o > 0) {
            view.setBackgroundResource(this.o);
        }
    }

    protected boolean e(int i) {
        return i < this.J.size() && ((Boolean) this.J.get(i)).booleanValue();
    }

    protected int e() {
        int i = 0;
        for (int i2 = 0; i2 < this.J.size(); i2++) {
            if (((Boolean) this.J.get(i2)).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    protected List<Integer> f() {
        List<Integer> arrayList = new ArrayList();
        for (int i = 0; i < this.J.size(); i++) {
            if (((Boolean) this.J.get(i)).booleanValue()) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        return arrayList;
    }

    private void c(View view, int i) {
        if (!((Boolean) this.H.get(i)).booleanValue()) {
            c(view, true, false, i);
        }
    }

    private void d(View view, int i) {
        if (((Boolean) this.H.get(i)).booleanValue()) {
            c(view, true, false, i);
        }
    }

    private void a(View view, boolean z, boolean z2, int i) {
        if (this.E == 0) {
            c(view, z, z2, i);
        }
        if (this.E == 1) {
            b(this.A, z, z2, i);
        }
        if (this.E == 2) {
            e(view, i);
        }
    }

    private void e(View view, int i) {
        ViewCompat.animate(view).translationX(0.0f).setDuration(this.k).setListener(new b(this) {
            final /* synthetic */ b a;

            {
                this.a = r2;
            }

            public void onAnimationEnd(View view) {
                this.a.r.d();
                this.a.j();
            }
        });
    }

    private void b(View view, final boolean z, boolean z2, final int i) {
        int i2;
        if (((Boolean) this.H.get(i)).booleanValue()) {
            if (!z) {
                i2 = ((Boolean) this.I.get(i)).booleanValue() ? (int) (((float) this.s) - this.m) : (int) (((float) (-this.s)) + this.l);
            }
            i2 = 0;
        } else {
            if (z) {
                i2 = z2 ? (int) (((float) this.s) - this.m) : (int) (((float) (-this.s)) + this.l);
            }
            i2 = 0;
        }
        int i3 = 1;
        if (z) {
            this.u++;
            i3 = 0;
        }
        ViewCompat.animate(view).translationX((float) i2).alpha((float) i3).setDuration(this.k).setListener(new b(this) {
            final /* synthetic */ b c;

            public void onAnimationEnd(View view) {
                if (z) {
                    this.c.g();
                    this.c.a(view, i, true);
                }
                this.c.j();
            }
        });
    }

    private void c(View view, boolean z, boolean z2, int i) {
        int i2;
        boolean z3;
        if (((Boolean) this.H.get(i)).booleanValue()) {
            if (!z) {
                i2 = ((Boolean) this.I.get(i)).booleanValue() ? (int) (((float) this.s) - this.m) : (int) (((float) (-this.s)) + this.l);
            }
            i2 = 0;
        } else {
            if (z) {
                i2 = z2 ? (int) (((float) this.s) - this.m) : (int) (((float) (-this.s)) + this.l);
            }
            i2 = 0;
        }
        if (((Boolean) this.H.get(i)).booleanValue()) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (this.p && z) {
            this.H.set(i, Boolean.valueOf(z3));
            this.I.set(i, Boolean.valueOf(z2));
        }
        final boolean z4 = z;
        final int i3 = i;
        final boolean z5 = z2;
        ViewCompat.animate(view).translationX((float) i2).setDuration(this.k).setListener(new b(this) {
            final /* synthetic */ b e;

            public void onAnimationEnd(View view) {
                this.e.r.d();
                if (z4) {
                    if (this.e.p) {
                        if (z3) {
                            this.e.r.b(i3, z5);
                        } else {
                            this.e.r.c(i3, ((Boolean) this.e.I.get(i3)).booleanValue());
                        }
                    }
                    this.e.H.set(i3, Boolean.valueOf(z3));
                    if (z3) {
                        this.e.r.b(i3, z5);
                        this.e.I.set(i3, Boolean.valueOf(z5));
                    } else {
                        this.e.r.c(i3, ((Boolean) this.e.I.get(i3)).booleanValue());
                    }
                }
                if (!this.e.p) {
                    this.e.j();
                }
            }
        });
    }

    private void j() {
        if (this.z != -1) {
            if (this.E == 2) {
                this.C.setVisibility(0);
            }
            this.B.setClickable(((Boolean) this.H.get(this.z)).booleanValue());
            this.B.setLongClickable(((Boolean) this.H.get(this.z)).booleanValue());
            this.B = null;
            this.C = null;
            this.z = -1;
        }
    }

    void g() {
        if (this.H != null) {
            int findFirstVisibleItemPosition = this.q.findFirstVisibleItemPosition();
            int findLastVisibleItemPosition = this.q.findLastVisibleItemPosition();
            for (int i = findFirstVisibleItemPosition; i <= findLastVisibleItemPosition; i++) {
                if (((Boolean) this.H.get(i)).booleanValue()) {
                    d(this.r.getChildAt(i - findFirstVisibleItemPosition).findViewById(this.d), i);
                }
            }
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z = false;
        if (!a()) {
            return false;
        }
        if (this.s < 2) {
            this.s = this.r.getWidth();
        }
        int i;
        float rawX;
        float abs;
        float f;
        boolean z2;
        switch (MotionEventCompat.getActionMasked(motionEvent)) {
            case 0:
                if (this.D && this.z != -1) {
                    return false;
                }
                this.E = 3;
                int childCount = this.r.getChildCount();
                int[] iArr = new int[2];
                this.r.getLocationOnScreen(iArr);
                int rawX2 = ((int) motionEvent.getRawX()) - iArr[0];
                int rawY = ((int) motionEvent.getRawY()) - iArr[1];
                for (i = 0; i < childCount; i++) {
                    View childAt = this.r.getChildAt(i);
                    childAt.getHitRect(this.f);
                    int childPosition = this.r.getChildPosition(childAt);
                    if (this.f.contains(rawX2, rawY)) {
                        a(childAt);
                        b(childAt.findViewById(this.d), childPosition);
                        this.v = motionEvent.getRawX();
                        this.z = childPosition;
                        this.B.setClickable(!((Boolean) this.H.get(this.z)).booleanValue());
                        View view2 = this.B;
                        if (!((Boolean) this.H.get(this.z)).booleanValue()) {
                            z = true;
                        }
                        view2.setLongClickable(z);
                        this.y = VelocityTracker.obtain();
                        this.y.addMovement(motionEvent);
                        if (this.e > 0) {
                            b(childAt.findViewById(this.e));
                        }
                        view.onTouchEvent(motionEvent);
                        return true;
                    }
                }
                view.onTouchEvent(motionEvent);
                return true;
            case 1:
                if (!(this.y == null || !this.w || this.z == -1)) {
                    boolean z3;
                    rawX = motionEvent.getRawX() - this.v;
                    this.y.addMovement(motionEvent);
                    this.y.computeCurrentVelocity(1000);
                    abs = Math.abs(this.y.getXVelocity());
                    if (((Boolean) this.H.get(this.z)).booleanValue()) {
                        f = abs;
                    } else {
                        if (this.a != 3 || this.y.getXVelocity() <= 0.0f) {
                            f = abs;
                        } else {
                            f = 0.0f;
                        }
                        if (this.a == 2 && this.y.getXVelocity() < 0.0f) {
                            f = 0.0f;
                        }
                    }
                    abs = Math.abs(this.y.getYVelocity());
                    if (((float) this.h) <= f && f <= ((float) this.i) && abs * 2.0f < f) {
                        z3 = this.y.getXVelocity() > 0.0f;
                        if (z3 != this.x && this.F != this.G) {
                            z2 = false;
                        } else if (((Boolean) this.H.get(this.z)).booleanValue() && ((Boolean) this.I.get(this.z)).booleanValue() && z3) {
                            z2 = false;
                        } else if (!((Boolean) this.H.get(this.z)).booleanValue() || ((Boolean) this.I.get(this.z)).booleanValue() || z3) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                    } else if (Math.abs(rawX) > ((float) (this.s / 2))) {
                        z3 = rawX > 0.0f;
                        z2 = true;
                    } else {
                        z3 = false;
                        z2 = false;
                    }
                    a(this.B, z2, z3, this.z);
                    if (this.E == 2) {
                        f(this.z);
                    }
                    this.y.recycle();
                    this.y = null;
                    this.v = 0.0f;
                    this.w = false;
                    break;
                }
                break;
            case 2:
                if (!(this.y == null || this.D || this.z == -1)) {
                    this.y.addMovement(motionEvent);
                    this.y.computeCurrentVelocity(1000);
                    float abs2 = Math.abs(this.y.getXVelocity());
                    float abs3 = Math.abs(this.y.getYVelocity());
                    rawX = motionEvent.getRawX() - this.v;
                    abs = Math.abs(rawX);
                    int i2 = this.a;
                    i = this.r.c(this.z);
                    if (i >= 0) {
                        i2 = i;
                    }
                    if (i2 == 0) {
                        f = 0.0f;
                    } else {
                        if (i2 != 1) {
                            if (((Boolean) this.H.get(this.z)).booleanValue()) {
                                if (i2 == 3 && rawX < 0.0f) {
                                    f = 0.0f;
                                } else if (i2 == 2 && rawX > 0.0f) {
                                    f = 0.0f;
                                }
                            } else if (i2 == 3 && rawX > 0.0f) {
                                f = 0.0f;
                            } else if (i2 == 2 && rawX < 0.0f) {
                                f = 0.0f;
                            }
                        }
                        f = abs;
                    }
                    if (f > ((float) this.g) && this.E == 3 && abs3 < abs2) {
                        this.w = true;
                        if (rawX > 0.0f) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        this.x = z2;
                        if (((Boolean) this.H.get(this.z)).booleanValue()) {
                            this.r.a(this.z, this.x);
                            this.E = 0;
                        } else {
                            if (this.x && this.G == 1) {
                                this.E = 1;
                            } else if (!this.x && this.F == 1) {
                                this.E = 1;
                            } else if (this.x && this.G == 2) {
                                this.E = 2;
                            } else if (this.x || this.F != 2) {
                                this.E = 0;
                            } else {
                                this.E = 2;
                            }
                            this.r.a(this.z, this.E, this.x);
                        }
                        this.r.requestDisallowInterceptTouchEvent(true);
                        MotionEvent obtain = MotionEvent.obtain(motionEvent);
                        obtain.setAction((MotionEventCompat.getActionIndex(motionEvent) << 8) | 3);
                        this.r.onTouchEvent(obtain);
                        if (this.E == 2) {
                            this.C.setVisibility(8);
                        }
                    }
                    if (this.w && this.z != -1) {
                        if (((Boolean) this.H.get(this.z)).booleanValue()) {
                            if (((Boolean) this.I.get(this.z)).booleanValue()) {
                                f = ((float) this.s) - this.m;
                            } else {
                                f = ((float) (-this.s)) + this.l;
                            }
                            f += rawX;
                        } else {
                            f = rawX;
                        }
                        c(f);
                        return true;
                    }
                }
                break;
        }
        if (!this.p) {
            return false;
        }
        k();
        view.onTouchEvent(motionEvent);
        return true;
    }

    private void k() {
        if (this.H != null && this.z != -1) {
            int findFirstVisibleItemPosition = this.q.findFirstVisibleItemPosition();
            int findLastVisibleItemPosition = this.q.findLastVisibleItemPosition();
            int i = findFirstVisibleItemPosition;
            while (i <= findLastVisibleItemPosition) {
                if (((Boolean) this.H.get(i)).booleanValue() && i != this.z) {
                    d(this.r.getChildAt(i - findFirstVisibleItemPosition).findViewById(this.d), i);
                }
                i++;
            }
        }
    }

    private void g(int i) {
        this.K = this.G;
        this.L = this.F;
        this.G = i;
        this.F = i;
    }

    protected void h() {
        this.G = this.K;
        this.F = this.L;
    }

    public void c(float f) {
        boolean z;
        this.r.a(this.z, f);
        float x = ViewCompat.getX(this.B);
        if (((Boolean) this.H.get(this.z)).booleanValue()) {
            x = (((Boolean) this.I.get(this.z)).booleanValue() ? ((float) (-this.s)) + this.m : ((float) this.s) - this.l) + x;
        }
        if (x > 0.0f && !this.x) {
            if (this.x) {
                z = false;
            } else {
                z = true;
            }
            this.x = z;
            this.E = this.G;
            if (this.E == 2) {
                this.C.setVisibility(8);
            } else {
                this.C.setVisibility(0);
            }
        }
        if (x < 0.0f && this.x) {
            if (this.x) {
                z = false;
            } else {
                z = true;
            }
            this.x = z;
            this.E = this.F;
            if (this.E == 2) {
                this.C.setVisibility(8);
            } else {
                this.C.setVisibility(0);
            }
        }
        if (this.E == 1) {
            ViewCompat.setTranslationX(this.A, f);
            ViewCompat.setAlpha(this.A, Math.max(0.0f, Math.min(1.0f, 1.0f - ((2.0f * Math.abs(f)) / ((float) this.s)))));
        } else if (this.E != 2) {
            ViewCompat.setTranslationX(this.B, f);
        } else if ((this.x && f > 0.0f && x < 80.0f) || ((!this.x && f < 0.0f && x > -80.0f) || ((this.x && f < 80.0f) || (!this.x && f > -80.0f)))) {
            ViewCompat.setTranslationX(this.B, f);
        }
    }

    protected void a(final View view, int i, boolean z) {
        a((ViewGroup) view, false);
        final LayoutParams layoutParams = view.getLayoutParams();
        final int height = view.getHeight();
        ValueAnimator duration = ValueAnimator.ofInt(new int[]{height, 1}).setDuration(this.k);
        if (z) {
            duration.addListener(new AnimatorListenerAdapter(this) {
                final /* synthetic */ b b;

                public void onAnimationEnd(Animator animator) {
                    b.h(this.b);
                    if (this.b.u == 0) {
                        this.b.h(height);
                    }
                }
            });
        }
        duration.addListener(new AnimatorListenerAdapter(this) {
            final /* synthetic */ b b;

            public void onAnimationEnd(Animator animator) {
                b.a((ViewGroup) view, true);
            }
        });
        duration.addUpdateListener(new AnimatorUpdateListener(this) {
            final /* synthetic */ b c;

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                layoutParams.height = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                view.setLayoutParams(layoutParams);
            }
        });
        this.t.add(new a(this, i, view));
        duration.start();
    }

    protected void i() {
        this.t.clear();
    }

    private void h(int i) {
        Collections.sort(this.t);
        int[] iArr = new int[this.t.size()];
        for (int size = this.t.size() - 1; size >= 0; size--) {
            iArr[size] = ((a) this.t.get(size)).a;
        }
        this.r.a(iArr);
        for (a aVar : this.t) {
            if (aVar.b != null) {
                ViewCompat.setAlpha(aVar.b, 1.0f);
                ViewCompat.setTranslationX(aVar.b, 0.0f);
                LayoutParams layoutParams = aVar.b.getLayoutParams();
                layoutParams.height = i;
                aVar.b.setLayoutParams(layoutParams);
            }
        }
        i();
    }

    public static void a(ViewGroup viewGroup, boolean z) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            childAt.setEnabled(z);
            if (childAt instanceof ViewGroup) {
                a((ViewGroup) childAt, z);
            }
        }
    }
}
