package cn.htjyb.ui.widget.headfooterlistview;

import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ListView;
import android.widget.Scroller;
import java.util.ArrayList;

public class d {
    private int a;
    private int b;
    private final ListView c;
    private final int d;
    private final ArrayList<Integer> e;
    private final int f;
    private final a g;
    private final int h;
    private a i;
    private View j;
    private int k = -1;
    private long l;
    private float m;
    private float n;
    private boolean o;
    private boolean p;
    private int q = -1;
    private VelocityTracker r;

    private static class a extends Scroller {
        private View a;
        private boolean b;
        private int c = -1;
        private boolean d;

        public a(Context context) {
            super(context);
        }

        public void a(View view, boolean z, int i, boolean z2) {
            this.a = view;
            this.b = z;
            this.c = i;
            this.d = z2;
        }

        public void a() {
            if (this.a != null) {
                this.a.scrollTo(getCurrX(), getCurrY());
            }
        }

        public boolean b() {
            return this.b;
        }

        public View c() {
            return this.a;
        }

        public int d() {
            return this.c;
        }

        public boolean e() {
            return this.d;
        }

        public boolean f() {
            return this.a == null;
        }

        public void g() {
            if (this.a != null) {
                this.a.scrollTo(0, 0);
                this.a.setVisibility(4);
                this.a = null;
                this.b = false;
                this.c = -1;
                this.d = false;
            }
        }
    }

    public d(ListView listView, int i, int[] iArr, int i2, a aVar) {
        this.c = listView;
        this.d = i;
        this.e = new ArrayList();
        for (int valueOf : iArr) {
            this.e.add(Integer.valueOf(valueOf));
        }
        this.f = i2;
        this.g = aVar;
        this.i = new a(this.c.getContext());
        this.h = ViewConfiguration.get(this.c.getContext()).getScaledTouchSlop();
        float f = cn.htjyb.c.a.f(listView.getContext());
        this.a = (int) (25.0f * f);
        this.b = (int) (f * 400.0f);
    }

    public boolean a(MotionEvent motionEvent) {
        if (this.c.getAdapter() == null || !this.i.isFinished()) {
            return false;
        }
        if (this.q < 0) {
            this.q = this.c.getWidth();
        }
        switch (motionEvent.getActionMasked()) {
            case 0:
                return b(motionEvent);
            case 1:
                return d(motionEvent);
            case 2:
                return c(motionEvent);
            case 3:
                return b();
            default:
                return false;
        }
    }

    private boolean b(MotionEvent motionEvent) {
        View e = e(motionEvent);
        if (e != null) {
            if (this.i.isFinished()) {
                int positionForView = this.c.getPositionForView(e);
                int headerViewsCount = positionForView - this.c.getHeaderViewsCount();
                if (!this.e.contains(Integer.valueOf(this.c.getAdapter().getItemViewType(positionForView)))) {
                    this.p = true;
                } else if (headerViewsCount != this.k) {
                    this.j = e;
                    this.k = headerViewsCount;
                    this.l = System.currentTimeMillis();
                    this.m = motionEvent.getX();
                    this.n = motionEvent.getY();
                    this.r = VelocityTracker.obtain();
                    this.r.addMovement(motionEvent);
                }
            } else {
                this.p = true;
            }
        }
        return false;
    }

    private boolean c(MotionEvent motionEvent) {
        if (this.p || this.j == null) {
            return false;
        }
        this.r.addMovement(motionEvent);
        int a = this.g.a(this.k);
        float x = motionEvent.getX() - this.m;
        float y = motionEvent.getY() - this.n;
        float abs = Math.abs(x);
        float abs2 = Math.abs(y);
        int scrollState = ((HeaderFooterListView) this.c).getScrollState();
        if (!this.o && abs > 5.0f && abs > abs2 && scrollState == 0 && a != 0) {
            this.o = true;
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.setAction((motionEvent.getActionIndex() << 8) | 3);
            this.j.dispatchTouchEvent(obtain);
            obtain.recycle();
        }
        if (!this.o) {
            return false;
        }
        boolean z;
        if (this.d == 1) {
            z = x < 0.0f && (a == 1 || a == 3);
            if (z) {
                z = false;
            } else {
                z = true;
            }
        } else if (this.d == 2) {
            if (x <= 0.0f || !(a == 2 || a == 3)) {
                z = false;
            } else {
                z = true;
            }
            if (z) {
                z = false;
            } else {
                z = true;
            }
        } else if (this.d == 3) {
            boolean z2 = x < 0.0f && (a == 1 || a == 3);
            if (x <= 0.0f || !(a == 2 || a == 3)) {
                z = false;
            } else {
                z = true;
            }
            if (z2 || r0) {
                z = false;
            } else {
                z = true;
            }
        } else {
            z = false;
        }
        if (z) {
            if (x < 0.0f) {
                y = -((float) Math.sqrt((double) (-x)));
            } else {
                y = (float) Math.sqrt((double) x);
            }
            y *= 6.0f;
        } else {
            y = x;
        }
        this.j.scrollTo((int) (-y), 0);
        return true;
    }

    private boolean b() {
        if (!(this.j == null || this.r == null || this.j == null)) {
            if (this.p) {
                c();
            } else {
                if (this.k != -1 && this.o) {
                    d();
                }
                c();
            }
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean d(android.view.MotionEvent r9) {
        /*
        r8 = this;
        r1 = 0;
        r0 = 1;
        r7 = 0;
        r2 = r8.r;
        if (r2 == 0) goto L_0x000b;
    L_0x0007:
        r2 = r8.j;
        if (r2 != 0) goto L_0x000c;
    L_0x000b:
        return r1;
    L_0x000c:
        r2 = r8.p;
        if (r2 == 0) goto L_0x0014;
    L_0x0010:
        r8.c();
        goto L_0x000b;
    L_0x0014:
        r2 = r8.g;
        r3 = r8.k;
        r2 = r2.a(r3);
        r3 = r8.o;
        if (r3 == 0) goto L_0x005b;
    L_0x0020:
        r3 = r8.r;
        r3.addMovement(r9);
        r3 = r8.r;
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r3.computeCurrentVelocity(r4);
        r3 = r9.getX();
        r4 = r8.m;
        r3 = r3 - r4;
        r4 = r8.r;
        r4 = r4.getXVelocity();
        r5 = java.lang.Math.abs(r3);
        r6 = r8.a;
        r6 = (float) r6;
        r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1));
        if (r5 <= 0) goto L_0x0054;
    L_0x0044:
        r5 = java.lang.Math.abs(r4);
        r6 = r8.b;
        r6 = (float) r6;
        r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1));
        if (r5 <= 0) goto L_0x0054;
    L_0x004f:
        r5 = r8.d;
        switch(r5) {
            case 1: goto L_0x005f;
            case 2: goto L_0x006a;
            case 3: goto L_0x0074;
            default: goto L_0x0054;
        };
    L_0x0054:
        r0 = r1;
        r2 = r1;
    L_0x0056:
        if (r2 == 0) goto L_0x00a6;
    L_0x0058:
        r8.a(r0);
    L_0x005b:
        r8.c();
        goto L_0x000b;
    L_0x005f:
        r2 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1));
        if (r2 >= 0) goto L_0x0054;
    L_0x0063:
        r2 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1));
        if (r2 >= 0) goto L_0x0054;
    L_0x0067:
        r2 = r0;
        r0 = r1;
        goto L_0x0056;
    L_0x006a:
        r2 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1));
        if (r2 <= 0) goto L_0x0054;
    L_0x006e:
        r2 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1));
        if (r2 <= 0) goto L_0x0054;
    L_0x0072:
        r2 = r0;
        goto L_0x0056;
    L_0x0074:
        r5 = 3;
        if (r2 != r5) goto L_0x008c;
    L_0x0077:
        r2 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1));
        if (r2 <= 0) goto L_0x0081;
    L_0x007b:
        r2 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1));
        if (r2 <= 0) goto L_0x0081;
    L_0x007f:
        r2 = r0;
        goto L_0x0056;
    L_0x0081:
        r2 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1));
        if (r2 >= 0) goto L_0x0054;
    L_0x0085:
        r2 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1));
        if (r2 >= 0) goto L_0x0054;
    L_0x0089:
        r2 = r0;
        r0 = r1;
        goto L_0x0056;
    L_0x008c:
        if (r2 != r0) goto L_0x0099;
    L_0x008e:
        r2 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1));
        if (r2 >= 0) goto L_0x0054;
    L_0x0092:
        r2 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1));
        if (r2 >= 0) goto L_0x0054;
    L_0x0096:
        r2 = r0;
        r0 = r1;
        goto L_0x0056;
    L_0x0099:
        r5 = 2;
        if (r2 != r5) goto L_0x0054;
    L_0x009c:
        r2 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1));
        if (r2 <= 0) goto L_0x0054;
    L_0x00a0:
        r2 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1));
        if (r2 <= 0) goto L_0x0054;
    L_0x00a4:
        r2 = r0;
        goto L_0x0056;
    L_0x00a6:
        r8.d();
        goto L_0x005b;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.htjyb.ui.widget.headfooterlistview.d.d(android.view.MotionEvent):boolean");
    }

    private void c() {
        if (this.r != null) {
            this.r.recycle();
        }
        this.r = null;
        this.l = 0;
        this.m = 0.0f;
        this.n = 0.0f;
        this.j = null;
        this.k = -1;
        this.o = false;
        this.p = false;
    }

    private void a(View view, boolean z, int i) {
        if (i != -1 && this.g != null) {
            this.g.a(this.c, view, z, i);
        }
    }

    public void a() {
        if (this.i.computeScrollOffset()) {
            this.i.a();
            this.c.postInvalidate();
            if (Math.abs(this.i.getCurrX()) == this.q && !this.i.f()) {
                if (this.i.b()) {
                    a(this.i.c(), this.i.e(), this.i.d());
                }
                this.i.g();
            }
        }
    }

    private void a(boolean z) {
        if (this.j != null) {
            int scrollX;
            this.i.a(this.j, true, this.k, z);
            if (z) {
                scrollX = this.j.getScrollX() + this.q;
            } else {
                scrollX = this.q - this.j.getScrollX();
            }
            int abs = 200 + ((int) ((((float) Math.abs(scrollX)) / (((float) this.q) * 1.0f)) * ((float) 50)));
            if (z) {
                this.i.startScroll(this.j.getScrollX(), 0, -scrollX, 0, abs);
            } else {
                this.i.startScroll(this.j.getScrollX(), 0, scrollX, 0, abs);
            }
            this.c.postInvalidate();
        }
    }

    private void d() {
        if (this.j != null) {
            this.i.a(this.j, false, -1, false);
            int i = -this.j.getScrollX();
            this.i.startScroll(this.j.getScrollX(), 0, i, 0, Math.abs(i));
            this.c.postInvalidate();
        }
    }

    private View e(MotionEvent motionEvent) {
        Rect rect = new Rect();
        int childCount = this.c.getChildCount();
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();
        int i = 0;
        View view = null;
        while (i < childCount && view == null) {
            View childAt = this.c.getChildAt(i);
            if (childAt != null) {
                childAt.getGlobalVisibleRect(rect);
                if (rect.contains(rawX, rawY)) {
                    i++;
                    view = childAt;
                }
            }
            childAt = view;
            i++;
            view = childAt;
        }
        if (!(this.f == 0 || view == null)) {
            Rect rect2 = new Rect();
            childAt = view.findViewById(this.f);
            if (childAt != null && childAt.getVisibility() == 0) {
                childAt.getGlobalVisibleRect(rect2);
                if (rect2.contains(rawX, rawY)) {
                    return childAt;
                }
            }
        }
        return null;
    }
}
