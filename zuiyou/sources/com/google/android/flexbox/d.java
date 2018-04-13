package com.google.android.flexbox;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class d {
    static final /* synthetic */ boolean c = (!d.class.desiredAssertionStatus());
    @Nullable
    int[] a;
    @Nullable
    long[] b;
    private final a d;
    private boolean[] e;
    @Nullable
    private long[] f;

    static class a {
        List<c> a;
        int b;

        a() {
        }

        void a() {
            this.a = null;
            this.b = 0;
        }
    }

    private static class b implements Comparable<b> {
        int a;
        int b;

        private b() {
        }

        public /* synthetic */ int compareTo(@NonNull Object obj) {
            return a((b) obj);
        }

        public int a(@NonNull b bVar) {
            if (this.b != bVar.b) {
                return this.b - bVar.b;
            }
            return this.a - bVar.a;
        }

        public String toString() {
            return "Order{order=" + this.b + ", index=" + this.a + '}';
        }
    }

    d(a aVar) {
        this.d = aVar;
    }

    int[] a(View view, int i, LayoutParams layoutParams, SparseIntArray sparseIntArray) {
        int flexItemCount = this.d.getFlexItemCount();
        List e = e(flexItemCount);
        b bVar = new b();
        if (view == null || !(layoutParams instanceof b)) {
            bVar.b = 1;
        } else {
            bVar.b = ((b) layoutParams).c();
        }
        if (i == -1 || i == flexItemCount) {
            bVar.a = flexItemCount;
        } else if (i < this.d.getFlexItemCount()) {
            bVar.a = i;
            while (i < flexItemCount) {
                b bVar2 = (b) e.get(i);
                bVar2.a++;
                i++;
            }
        } else {
            bVar.a = flexItemCount;
        }
        e.add(bVar);
        return a(flexItemCount + 1, e, sparseIntArray);
    }

    int[] a(SparseIntArray sparseIntArray) {
        int flexItemCount = this.d.getFlexItemCount();
        return a(flexItemCount, e(flexItemCount), sparseIntArray);
    }

    @NonNull
    private List<b> e(int i) {
        List<b> arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            b bVar = (b) this.d.a(i2).getLayoutParams();
            b bVar2 = new b();
            bVar2.b = bVar.c();
            bVar2.a = i2;
            arrayList.add(bVar2);
        }
        return arrayList;
    }

    boolean b(SparseIntArray sparseIntArray) {
        int flexItemCount = this.d.getFlexItemCount();
        if (sparseIntArray.size() != flexItemCount) {
            return true;
        }
        int i = 0;
        while (i < flexItemCount) {
            View a = this.d.a(i);
            if (a != null && ((b) a.getLayoutParams()).c() != sparseIntArray.get(i)) {
                return true;
            }
            i++;
        }
        return false;
    }

    private int[] a(int i, List<b> list, SparseIntArray sparseIntArray) {
        Collections.sort(list);
        sparseIntArray.clear();
        int[] iArr = new int[i];
        int i2 = 0;
        for (b bVar : list) {
            iArr[i2] = bVar.a;
            sparseIntArray.append(bVar.a, bVar.b);
            i2++;
        }
        return iArr;
    }

    void a(a aVar, int i, int i2) {
        a(aVar, i, i2, Integer.MAX_VALUE, 0, -1, null);
    }

    void a(a aVar, int i, int i2, int i3, int i4, @Nullable List<c> list) {
        a(aVar, i, i2, i3, i4, -1, (List) list);
    }

    void b(a aVar, int i, int i2, int i3, int i4, List<c> list) {
        a(aVar, i, i2, i3, 0, i4, (List) list);
    }

    void b(a aVar, int i, int i2) {
        a(aVar, i2, i, Integer.MAX_VALUE, 0, -1, null);
    }

    void c(a aVar, int i, int i2, int i3, int i4, @Nullable List<c> list) {
        a(aVar, i2, i, i3, i4, -1, (List) list);
    }

    void d(a aVar, int i, int i2, int i3, int i4, List<c> list) {
        a(aVar, i2, i, i3, 0, i4, (List) list);
    }

    void a(a aVar, int i, int i2, int i3, int i4, int i5, @Nullable List<c> list) {
        int combineMeasuredStates;
        boolean a = this.d.a();
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int i6 = 0;
        if (list == null) {
            list = new ArrayList();
        }
        aVar.a = list;
        Object obj = i5 == -1 ? 1 : null;
        int a2 = a(a);
        int b = b(a);
        int c = c(a);
        int d = d(a);
        int i7 = Integer.MIN_VALUE;
        int i8 = 0;
        int i9 = 0;
        c cVar = new c();
        cVar.o = i4;
        cVar.e = a2 + b;
        int flexItemCount = this.d.getFlexItemCount();
        int i10 = i4;
        Object obj2 = obj;
        while (i10 < flexItemCount) {
            int i11;
            View b2 = this.d.b(i10);
            if (b2 == null) {
                if (a(i10, flexItemCount, cVar)) {
                    a((List) list, cVar, i10, i8);
                    obj = obj2;
                    i11 = i6;
                }
                obj = obj2;
                i11 = i6;
            } else if (b2.getVisibility() == 8) {
                cVar.i++;
                cVar.h++;
                if (a(i10, flexItemCount, cVar)) {
                    a((List) list, cVar, i10, i8);
                    obj = obj2;
                    i11 = i6;
                }
                obj = obj2;
                i11 = i6;
            } else {
                int b3;
                int i12;
                c cVar2;
                b bVar = (b) b2.getLayoutParams();
                if (bVar.f() == 4) {
                    cVar.n.add(Integer.valueOf(i10));
                }
                int a3 = a(bVar, a);
                if (bVar.l() != -1.0f && mode == 1073741824) {
                    a3 = Math.round(((float) size) * bVar.l());
                }
                if (a) {
                    a3 = this.d.a(i, ((a2 + b) + c(bVar, true)) + d(bVar, true), a3);
                    b3 = this.d.b(i2, (((c + d) + e(bVar, true)) + f(bVar, true)) + i8, b(bVar, true));
                    b2.measure(a3, b3);
                    a(i10, a3, b3, b2);
                    i12 = a3;
                } else {
                    b3 = this.d.a(i2, (((c + d) + e(bVar, false)) + f(bVar, false)) + i8, b(bVar, false));
                    a3 = this.d.b(i, ((a2 + b) + c(bVar, false)) + d(bVar, false), a3);
                    b2.measure(b3, a3);
                    a(i10, b3, a3, b2);
                    i12 = a3;
                }
                this.d.a(i10, b2);
                a(b2, i10);
                combineMeasuredStates = View.combineMeasuredStates(i6, b2.getMeasuredState());
                if (a(b2, mode, size, cVar.e, d(bVar, a) + (a(b2, a) + c(bVar, a)), bVar, i10, i9)) {
                    if (cVar.c() > 0) {
                        a((List) list, cVar, i10 > 0 ? i10 - 1 : 0, i8);
                        b3 = i8 + cVar.g;
                    } else {
                        b3 = i8;
                    }
                    if (a) {
                        if (bVar.b() == -1) {
                            b2.measure(i12, this.d.b(i2, (((this.d.getPaddingTop() + this.d.getPaddingBottom()) + bVar.n()) + bVar.p()) + b3, bVar.b()));
                            a(b2, i10);
                        }
                    } else if (bVar.a() == -1) {
                        b2.measure(this.d.a(i2, (((this.d.getPaddingLeft() + this.d.getPaddingRight()) + bVar.m()) + bVar.o()) + b3, bVar.a()), i12);
                        a(b2, i10);
                    }
                    c cVar3 = new c();
                    cVar3.h = 1;
                    cVar3.e = a2 + b;
                    cVar3.o = i10;
                    i9 = Integer.MIN_VALUE;
                    c cVar4 = cVar3;
                    a3 = b3;
                    b3 = 0;
                    cVar2 = cVar4;
                } else {
                    cVar.h++;
                    cVar2 = cVar;
                    b3 = i9 + 1;
                    i9 = i7;
                    a3 = i8;
                }
                if (this.a != null) {
                    this.a[i10] = list.size();
                }
                cVar2.e += (a(b2, a) + c(bVar, a)) + d(bVar, a);
                cVar2.j += bVar.d();
                cVar2.k += bVar.e();
                this.d.a(b2, i10, b3, cVar2);
                i7 = Math.max(i9, ((b(b2, a) + e(bVar, a)) + f(bVar, a)) + this.d.a(b2));
                cVar2.g = Math.max(cVar2.g, i7);
                if (a) {
                    if (this.d.getFlexWrap() != 2) {
                        cVar2.l = Math.max(cVar2.l, b2.getBaseline() + bVar.n());
                    } else {
                        cVar2.l = Math.max(cVar2.l, (b2.getMeasuredHeight() - b2.getBaseline()) + bVar.p());
                    }
                }
                if (a(i10, flexItemCount, cVar2)) {
                    a((List) list, cVar2, i10, a3);
                    i8 = a3 + cVar2.g;
                } else {
                    i8 = a3;
                }
                if (i5 != -1 && list.size() > 0) {
                    if (((c) list.get(list.size() - 1)).p >= i5 && i10 >= i5 && obj2 == null) {
                        i8 = -cVar2.a();
                        obj = 1;
                        if (i8 <= i3 && r2 != null) {
                            break;
                        }
                        cVar = cVar2;
                        i9 = b3;
                        i11 = combineMeasuredStates;
                    }
                }
                obj = obj2;
                if (i8 <= i3) {
                }
                cVar = cVar2;
                i9 = b3;
                i11 = combineMeasuredStates;
            }
            i10++;
            obj2 = obj;
            i6 = i11;
        }
        combineMeasuredStates = i6;
        aVar.b = combineMeasuredStates;
    }

    private int a(boolean z) {
        if (z) {
            return this.d.getPaddingStart();
        }
        return this.d.getPaddingTop();
    }

    private int b(boolean z) {
        if (z) {
            return this.d.getPaddingEnd();
        }
        return this.d.getPaddingBottom();
    }

    private int c(boolean z) {
        if (z) {
            return this.d.getPaddingTop();
        }
        return this.d.getPaddingStart();
    }

    private int d(boolean z) {
        if (z) {
            return this.d.getPaddingBottom();
        }
        return this.d.getPaddingEnd();
    }

    private int a(View view, boolean z) {
        if (z) {
            return view.getMeasuredWidth();
        }
        return view.getMeasuredHeight();
    }

    private int b(View view, boolean z) {
        if (z) {
            return view.getMeasuredHeight();
        }
        return view.getMeasuredWidth();
    }

    private int a(b bVar, boolean z) {
        if (z) {
            return bVar.a();
        }
        return bVar.b();
    }

    private int b(b bVar, boolean z) {
        if (z) {
            return bVar.b();
        }
        return bVar.a();
    }

    private int c(b bVar, boolean z) {
        if (z) {
            return bVar.m();
        }
        return bVar.n();
    }

    private int d(b bVar, boolean z) {
        if (z) {
            return bVar.o();
        }
        return bVar.p();
    }

    private int e(b bVar, boolean z) {
        if (z) {
            return bVar.n();
        }
        return bVar.m();
    }

    private int f(b bVar, boolean z) {
        if (z) {
            return bVar.p();
        }
        return bVar.o();
    }

    private boolean a(View view, int i, int i2, int i3, int i4, b bVar, int i5, int i6) {
        boolean z = true;
        if (this.d.getFlexWrap() == 0) {
            return false;
        }
        if (bVar.k()) {
            return true;
        }
        if (i == 0) {
            return false;
        }
        int a = this.d.a(view, i5, i6);
        if (a > 0) {
            i4 += a;
        }
        if (i2 >= i3 + i4) {
            z = false;
        }
        return z;
    }

    private boolean a(int i, int i2, c cVar) {
        return i == i2 + -1 && cVar.c() != 0;
    }

    private void a(List<c> list, c cVar, int i, int i2) {
        cVar.m = i2;
        this.d.a(cVar);
        cVar.p = i;
        list.add(cVar);
    }

    private void a(View view, int i) {
        int h;
        Object obj = 1;
        Object obj2 = null;
        b bVar = (b) view.getLayoutParams();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        if (measuredWidth < bVar.g()) {
            measuredWidth = bVar.g();
            obj2 = 1;
        } else if (measuredWidth > bVar.i()) {
            measuredWidth = bVar.i();
            int i2 = 1;
        }
        if (measuredHeight < bVar.h()) {
            h = bVar.h();
        } else if (measuredHeight > bVar.j()) {
            h = bVar.j();
        } else {
            h = measuredHeight;
            obj = obj2;
        }
        if (obj != null) {
            measuredWidth = MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824);
            h = MeasureSpec.makeMeasureSpec(h, 1073741824);
            view.measure(measuredWidth, h);
            a(i, measuredWidth, h, view);
            this.d.a(i, view);
        }
    }

    void a(int i, int i2) {
        a(i, i2, 0);
    }

    void a(int i, int i2, int i3) {
        f(this.d.getFlexItemCount());
        if (i3 < this.d.getFlexItemCount()) {
            int paddingLeft;
            int i4;
            int flexDirection = this.d.getFlexDirection();
            int mode;
            switch (this.d.getFlexDirection()) {
                case 0:
                case 1:
                    mode = MeasureSpec.getMode(i);
                    flexDirection = MeasureSpec.getSize(i);
                    if (mode != 1073741824) {
                        flexDirection = this.d.getLargestMainSize();
                    }
                    paddingLeft = this.d.getPaddingLeft() + this.d.getPaddingRight();
                    i4 = flexDirection;
                    break;
                case 2:
                case 3:
                    mode = MeasureSpec.getMode(i2);
                    flexDirection = MeasureSpec.getSize(i2);
                    if (mode != 1073741824) {
                        flexDirection = this.d.getLargestMainSize();
                    }
                    paddingLeft = this.d.getPaddingTop() + this.d.getPaddingBottom();
                    i4 = flexDirection;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
            }
            if (this.a != null) {
                flexDirection = this.a[i3];
            } else {
                boolean z = false;
            }
            List flexLinesInternal = this.d.getFlexLinesInternal();
            int size = flexLinesInternal.size();
            for (int i5 = flexDirection; i5 < size; i5++) {
                c cVar = (c) flexLinesInternal.get(i5);
                if (cVar.e < i4) {
                    a(i, i2, cVar, i4, paddingLeft, false);
                } else {
                    b(i, i2, cVar, i4, paddingLeft, false);
                }
            }
        }
    }

    private void f(int i) {
        if (this.e == null) {
            if (i < 10) {
                i = 10;
            }
            this.e = new boolean[i];
        } else if (this.e.length < i) {
            int length = this.e.length * 2;
            if (length >= i) {
                i = length;
            }
            this.e = new boolean[i];
        } else {
            Arrays.fill(this.e, false);
        }
    }

    private void a(int i, int i2, c cVar, int i3, int i4, boolean z) {
        if (cVar.j > 0.0f && i3 >= cVar.e) {
            int i5 = cVar.e;
            float f = ((float) (i3 - cVar.e)) / cVar.j;
            cVar.e = cVar.f + i4;
            if (!z) {
                cVar.g = Integer.MIN_VALUE;
            }
            Object obj = null;
            int i6 = 0;
            float f2 = 0.0f;
            for (int i7 = 0; i7 < cVar.h; i7++) {
                int i8 = cVar.o + i7;
                View b = this.d.b(i8);
                if (!(b == null || b.getVisibility() == 8)) {
                    int i9;
                    b bVar = (b) b.getLayoutParams();
                    int flexDirection = this.d.getFlexDirection();
                    int measuredHeight;
                    float d;
                    float f3;
                    int i10;
                    int b2;
                    int makeMeasureSpec;
                    int i11;
                    if (flexDirection == 0 || flexDirection == 1) {
                        flexDirection = b.getMeasuredWidth();
                        if (this.f != null) {
                            flexDirection = a(this.f[i8]);
                        }
                        measuredHeight = b.getMeasuredHeight();
                        if (this.f != null) {
                            measuredHeight = b(this.f[i8]);
                        }
                        if (!this.e[i8] && bVar.d() > 0.0f) {
                            d = ((float) flexDirection) + (bVar.d() * f);
                            if (i7 == cVar.h - 1) {
                                f2 += d;
                                d = 0.0f;
                            } else {
                                f3 = d;
                                d = f2;
                                f2 = f3;
                            }
                            measuredHeight = Math.round(f2);
                            if (measuredHeight > bVar.i()) {
                                obj = 1;
                                i10 = bVar.i();
                                this.e[i8] = true;
                                cVar.j -= bVar.d();
                            } else {
                                d += f2 - ((float) measuredHeight);
                                if (((double) d) > 1.0d) {
                                    i10 = measuredHeight + 1;
                                    d = (float) (((double) d) - 1.0d);
                                } else if (((double) d) < -1.0d) {
                                    i10 = measuredHeight - 1;
                                    d = (float) (((double) d) + 1.0d);
                                } else {
                                    i10 = measuredHeight;
                                }
                            }
                            b2 = b(i2, bVar, cVar.m);
                            makeMeasureSpec = MeasureSpec.makeMeasureSpec(i10, 1073741824);
                            b.measure(makeMeasureSpec, b2);
                            i10 = b.getMeasuredWidth();
                            measuredHeight = b.getMeasuredHeight();
                            a(i8, makeMeasureSpec, b2, b);
                            this.d.a(i8, b);
                            i11 = i10;
                            f2 = d;
                            flexDirection = i11;
                        }
                        i6 = Math.max(i6, ((measuredHeight + bVar.n()) + bVar.p()) + this.d.a(b));
                        cVar.e = (bVar.o() + (flexDirection + bVar.m())) + cVar.e;
                        i9 = i6;
                    } else {
                        flexDirection = b.getMeasuredHeight();
                        if (this.f != null) {
                            flexDirection = b(this.f[i8]);
                        }
                        measuredHeight = b.getMeasuredWidth();
                        if (this.f != null) {
                            measuredHeight = a(this.f[i8]);
                        }
                        if (!this.e[i8] && bVar.d() > 0.0f) {
                            d = ((float) flexDirection) + (bVar.d() * f);
                            if (i7 == cVar.h - 1) {
                                f2 += d;
                                d = 0.0f;
                            } else {
                                f3 = d;
                                d = f2;
                                f2 = f3;
                            }
                            measuredHeight = Math.round(f2);
                            if (measuredHeight > bVar.j()) {
                                obj = 1;
                                i10 = bVar.j();
                                this.e[i8] = true;
                                cVar.j -= bVar.d();
                            } else {
                                d += f2 - ((float) measuredHeight);
                                if (((double) d) > 1.0d) {
                                    i10 = measuredHeight + 1;
                                    d = (float) (((double) d) - 1.0d);
                                } else if (((double) d) < -1.0d) {
                                    i10 = measuredHeight - 1;
                                    d = (float) (((double) d) + 1.0d);
                                } else {
                                    i10 = measuredHeight;
                                }
                            }
                            b2 = a(i, bVar, cVar.m);
                            makeMeasureSpec = MeasureSpec.makeMeasureSpec(i10, 1073741824);
                            b.measure(b2, makeMeasureSpec);
                            measuredHeight = b.getMeasuredWidth();
                            i10 = b.getMeasuredHeight();
                            a(i8, b2, makeMeasureSpec, b);
                            this.d.a(i8, b);
                            i11 = i10;
                            f2 = d;
                            flexDirection = i11;
                        }
                        i6 = Math.max(i6, ((measuredHeight + bVar.m()) + bVar.o()) + this.d.a(b));
                        cVar.e = (bVar.p() + (flexDirection + bVar.n())) + cVar.e;
                        i9 = i6;
                    }
                    cVar.g = Math.max(cVar.g, i9);
                    i6 = i9;
                }
            }
            if (obj != null && i5 != cVar.e) {
                a(i, i2, cVar, i3, i4, true);
            }
        }
    }

    private void b(int i, int i2, c cVar, int i3, int i4, boolean z) {
        int i5 = cVar.e;
        if (cVar.k > 0.0f && i3 <= cVar.e) {
            float f = ((float) (cVar.e - i3)) / cVar.k;
            cVar.e = cVar.f + i4;
            if (!z) {
                cVar.g = Integer.MIN_VALUE;
            }
            Object obj = null;
            float f2 = 0.0f;
            int i6 = 0;
            for (int i7 = 0; i7 < cVar.h; i7++) {
                int i8 = cVar.o + i7;
                View b = this.d.b(i8);
                if (!(b == null || b.getVisibility() == 8)) {
                    int i9;
                    b bVar = (b) b.getLayoutParams();
                    int flexDirection = this.d.getFlexDirection();
                    int measuredHeight;
                    float e;
                    float f3;
                    int g;
                    int b2;
                    int makeMeasureSpec;
                    int i10;
                    if (flexDirection == 0 || flexDirection == 1) {
                        flexDirection = b.getMeasuredWidth();
                        if (this.f != null) {
                            flexDirection = a(this.f[i8]);
                        }
                        measuredHeight = b.getMeasuredHeight();
                        if (this.f != null) {
                            measuredHeight = b(this.f[i8]);
                        }
                        if (!this.e[i8] && bVar.e() > 0.0f) {
                            e = ((float) flexDirection) - (bVar.e() * f);
                            if (i7 == cVar.h - 1) {
                                f2 += e;
                                e = 0.0f;
                            } else {
                                f3 = e;
                                e = f2;
                                f2 = f3;
                            }
                            measuredHeight = Math.round(f2);
                            if (measuredHeight < bVar.g()) {
                                obj = 1;
                                g = bVar.g();
                                this.e[i8] = true;
                                cVar.k -= bVar.e();
                            } else {
                                e += f2 - ((float) measuredHeight);
                                if (((double) e) > 1.0d) {
                                    g = measuredHeight + 1;
                                    e -= 1.0f;
                                } else if (((double) e) < -1.0d) {
                                    g = measuredHeight - 1;
                                    e += 1.0f;
                                } else {
                                    g = measuredHeight;
                                }
                            }
                            b2 = b(i2, bVar, cVar.m);
                            makeMeasureSpec = MeasureSpec.makeMeasureSpec(g, 1073741824);
                            b.measure(makeMeasureSpec, b2);
                            g = b.getMeasuredWidth();
                            measuredHeight = b.getMeasuredHeight();
                            a(i8, makeMeasureSpec, b2, b);
                            this.d.a(i8, b);
                            i10 = g;
                            f2 = e;
                            flexDirection = i10;
                        }
                        i6 = Math.max(i6, ((measuredHeight + bVar.n()) + bVar.p()) + this.d.a(b));
                        cVar.e = (bVar.o() + (flexDirection + bVar.m())) + cVar.e;
                        i9 = i6;
                    } else {
                        flexDirection = b.getMeasuredHeight();
                        if (this.f != null) {
                            flexDirection = b(this.f[i8]);
                        }
                        measuredHeight = b.getMeasuredWidth();
                        if (this.f != null) {
                            measuredHeight = a(this.f[i8]);
                        }
                        if (!this.e[i8] && bVar.e() > 0.0f) {
                            e = ((float) flexDirection) - (bVar.e() * f);
                            if (i7 == cVar.h - 1) {
                                f2 += e;
                                e = 0.0f;
                            } else {
                                f3 = e;
                                e = f2;
                                f2 = f3;
                            }
                            measuredHeight = Math.round(f2);
                            if (measuredHeight < bVar.h()) {
                                obj = 1;
                                g = bVar.h();
                                this.e[i8] = true;
                                cVar.k -= bVar.e();
                            } else {
                                e += f2 - ((float) measuredHeight);
                                if (((double) e) > 1.0d) {
                                    g = measuredHeight + 1;
                                    e -= 1.0f;
                                } else if (((double) e) < -1.0d) {
                                    g = measuredHeight - 1;
                                    e += 1.0f;
                                } else {
                                    g = measuredHeight;
                                }
                            }
                            b2 = a(i, bVar, cVar.m);
                            makeMeasureSpec = MeasureSpec.makeMeasureSpec(g, 1073741824);
                            b.measure(b2, makeMeasureSpec);
                            measuredHeight = b.getMeasuredWidth();
                            g = b.getMeasuredHeight();
                            a(i8, b2, makeMeasureSpec, b);
                            this.d.a(i8, b);
                            i10 = g;
                            f2 = e;
                            flexDirection = i10;
                        }
                        i6 = Math.max(i6, ((measuredHeight + bVar.m()) + bVar.o()) + this.d.a(b));
                        cVar.e = (bVar.p() + (flexDirection + bVar.n())) + cVar.e;
                        i9 = i6;
                    }
                    cVar.g = Math.max(cVar.g, i9);
                    i6 = i9;
                }
            }
            if (obj != null && i5 != cVar.e) {
                b(i, i2, cVar, i3, i4, true);
            }
        }
    }

    private int a(int i, b bVar, int i2) {
        int a = this.d.a(i, (((this.d.getPaddingLeft() + this.d.getPaddingRight()) + bVar.m()) + bVar.o()) + i2, bVar.a());
        int size = MeasureSpec.getSize(a);
        if (size > bVar.i()) {
            return MeasureSpec.makeMeasureSpec(bVar.i(), MeasureSpec.getMode(a));
        }
        if (size < bVar.g()) {
            return MeasureSpec.makeMeasureSpec(bVar.g(), MeasureSpec.getMode(a));
        }
        return a;
    }

    private int b(int i, b bVar, int i2) {
        int b = this.d.b(i, (((this.d.getPaddingTop() + this.d.getPaddingBottom()) + bVar.n()) + bVar.p()) + i2, bVar.b());
        int size = MeasureSpec.getSize(b);
        if (size > bVar.j()) {
            return MeasureSpec.makeMeasureSpec(bVar.j(), MeasureSpec.getMode(b));
        }
        if (size < bVar.h()) {
            return MeasureSpec.makeMeasureSpec(bVar.h(), MeasureSpec.getMode(b));
        }
        return b;
    }

    void b(int i, int i2, int i3) {
        int mode;
        int flexDirection = this.d.getFlexDirection();
        switch (flexDirection) {
            case 0:
            case 1:
                mode = MeasureSpec.getMode(i2);
                flexDirection = mode;
                mode = MeasureSpec.getSize(i2);
                break;
            case 2:
            case 3:
                mode = MeasureSpec.getMode(i);
                flexDirection = mode;
                mode = MeasureSpec.getSize(i);
                break;
            default:
                throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
        }
        List<c> flexLinesInternal = this.d.getFlexLinesInternal();
        if (flexDirection == 1073741824) {
            flexDirection = this.d.getSumOfCrossSize() + i3;
            if (flexLinesInternal.size() == 1) {
                ((c) flexLinesInternal.get(0)).g = mode - i3;
            } else if (flexLinesInternal.size() >= 2) {
                float f;
                c cVar;
                switch (this.d.getAlignContent()) {
                    case 1:
                        flexDirection = mode - flexDirection;
                        c cVar2 = new c();
                        cVar2.g = flexDirection;
                        flexLinesInternal.add(0, cVar2);
                        return;
                    case 2:
                        this.d.setFlexLines(a((List) flexLinesInternal, mode, flexDirection));
                        return;
                    case 3:
                        if (flexDirection < mode) {
                            float size = ((float) (mode - flexDirection)) / ((float) (flexLinesInternal.size() - 1));
                            f = 0.0f;
                            List arrayList = new ArrayList();
                            int size2 = flexLinesInternal.size();
                            int i4 = 0;
                            while (i4 < size2) {
                                float f2;
                                arrayList.add((c) flexLinesInternal.get(i4));
                                if (i4 != flexLinesInternal.size() - 1) {
                                    c cVar3 = new c();
                                    if (i4 == flexLinesInternal.size() - 2) {
                                        cVar3.g = Math.round(size + f);
                                        f2 = 0.0f;
                                    } else {
                                        cVar3.g = Math.round(size);
                                        f2 = f;
                                    }
                                    f2 += size - ((float) cVar3.g);
                                    if (f2 > 1.0f) {
                                        cVar3.g++;
                                        f2 -= 1.0f;
                                    } else if (f2 < -1.0f) {
                                        cVar3.g--;
                                        f2 += 1.0f;
                                    }
                                    arrayList.add(cVar3);
                                } else {
                                    f2 = f;
                                }
                                i4++;
                                f = f2;
                            }
                            this.d.setFlexLines(arrayList);
                            return;
                        }
                        return;
                    case 4:
                        if (flexDirection >= mode) {
                            this.d.setFlexLines(a((List) flexLinesInternal, mode, flexDirection));
                            return;
                        }
                        flexDirection = (mode - flexDirection) / (flexLinesInternal.size() * 2);
                        List arrayList2 = new ArrayList();
                        c cVar4 = new c();
                        cVar4.g = flexDirection;
                        for (c cVar5 : flexLinesInternal) {
                            arrayList2.add(cVar4);
                            arrayList2.add(cVar5);
                            arrayList2.add(cVar4);
                        }
                        this.d.setFlexLines(arrayList2);
                        return;
                    case 5:
                        if (flexDirection < mode) {
                            float size3 = ((float) (mode - flexDirection)) / ((float) flexLinesInternal.size());
                            float f3 = 0.0f;
                            int size4 = flexLinesInternal.size();
                            for (int i5 = 0; i5 < size4; i5++) {
                                cVar5 = (c) flexLinesInternal.get(i5);
                                f = ((float) cVar5.g) + size3;
                                if (i5 == flexLinesInternal.size() - 1) {
                                    f += f3;
                                    f3 = 0.0f;
                                }
                                int round = Math.round(f);
                                f3 += f - ((float) round);
                                if (f3 > 1.0f) {
                                    mode = round + 1;
                                    f3 -= 1.0f;
                                } else if (f3 < -1.0f) {
                                    mode = round - 1;
                                    f3 += 1.0f;
                                } else {
                                    mode = round;
                                }
                                cVar5.g = mode;
                            }
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private List<c> a(List<c> list, int i, int i2) {
        int i3 = (i - i2) / 2;
        List<c> arrayList = new ArrayList();
        c cVar = new c();
        cVar.g = i3;
        int size = list.size();
        for (int i4 = 0; i4 < size; i4++) {
            if (i4 == 0) {
                arrayList.add(cVar);
            }
            arrayList.add((c) list.get(i4));
            if (i4 == list.size() - 1) {
                arrayList.add(cVar);
            }
        }
        return arrayList;
    }

    void a() {
        a(0);
    }

    void a(int i) {
        if (i < this.d.getFlexItemCount()) {
            int flexDirection = this.d.getFlexDirection();
            c cVar;
            if (this.d.getAlignItems() == 4) {
                int i2;
                if (this.a != null) {
                    i2 = this.a[i];
                } else {
                    i2 = 0;
                }
                List flexLinesInternal = this.d.getFlexLinesInternal();
                int size = flexLinesInternal.size();
                for (int i3 = i2; i3 < size; i3++) {
                    cVar = (c) flexLinesInternal.get(i3);
                    int i4 = cVar.h;
                    for (int i5 = 0; i5 < i4; i5++) {
                        int i6 = cVar.o + i5;
                        if (i5 < this.d.getFlexItemCount()) {
                            View b = this.d.b(i6);
                            if (!(b == null || b.getVisibility() == 8)) {
                                b bVar = (b) b.getLayoutParams();
                                if (bVar.f() == -1 || bVar.f() == 4) {
                                    switch (flexDirection) {
                                        case 0:
                                        case 1:
                                            a(b, cVar.g, i6);
                                            break;
                                        case 2:
                                        case 3:
                                            b(b, cVar.g, i6);
                                            break;
                                        default:
                                            throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
                                    }
                                }
                            }
                        }
                    }
                }
                return;
            }
            for (c cVar2 : this.d.getFlexLinesInternal()) {
                for (Integer num : cVar2.n) {
                    View b2 = this.d.b(num.intValue());
                    switch (flexDirection) {
                        case 0:
                        case 1:
                            a(b2, cVar2.g, num.intValue());
                            break;
                        case 2:
                        case 3:
                            b(b2, cVar2.g, num.intValue());
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
                    }
                }
            }
        }
    }

    private void a(View view, int i, int i2) {
        int a;
        b bVar = (b) view.getLayoutParams();
        int min = Math.min(Math.max(((i - bVar.n()) - bVar.p()) - this.d.a(view), bVar.h()), bVar.j());
        if (this.f != null) {
            a = a(this.f[i2]);
        } else {
            a = view.getMeasuredWidth();
        }
        a = MeasureSpec.makeMeasureSpec(a, 1073741824);
        min = MeasureSpec.makeMeasureSpec(min, 1073741824);
        view.measure(a, min);
        a(i2, a, min, view);
        this.d.a(i2, view);
    }

    private void b(View view, int i, int i2) {
        int b;
        b bVar = (b) view.getLayoutParams();
        int min = Math.min(Math.max(((i - bVar.m()) - bVar.o()) - this.d.a(view), bVar.g()), bVar.i());
        if (this.f != null) {
            b = b(this.f[i2]);
        } else {
            b = view.getMeasuredHeight();
        }
        b = MeasureSpec.makeMeasureSpec(b, 1073741824);
        min = MeasureSpec.makeMeasureSpec(min, 1073741824);
        view.measure(min, b);
        a(i2, min, b, view);
        this.d.a(i2, view);
    }

    void a(View view, c cVar, int i, int i2, int i3, int i4) {
        b bVar = (b) view.getLayoutParams();
        int alignItems = this.d.getAlignItems();
        if (bVar.f() != -1) {
            alignItems = bVar.f();
        }
        int i5 = cVar.g;
        int measuredHeight;
        switch (alignItems) {
            case 0:
            case 4:
                if (this.d.getFlexWrap() != 2) {
                    view.layout(i, bVar.n() + i2, i3, bVar.n() + i4);
                    return;
                } else {
                    view.layout(i, i2 - bVar.p(), i3, i4 - bVar.p());
                    return;
                }
            case 1:
                if (this.d.getFlexWrap() != 2) {
                    view.layout(i, ((i2 + i5) - view.getMeasuredHeight()) - bVar.p(), i3, (i5 + i2) - bVar.p());
                    return;
                }
                view.layout(i, ((i2 - i5) + view.getMeasuredHeight()) + bVar.n(), i3, bVar.n() + ((i4 - i5) + view.getMeasuredHeight()));
                return;
            case 2:
                measuredHeight = (((i5 - view.getMeasuredHeight()) + bVar.n()) - bVar.p()) / 2;
                if (this.d.getFlexWrap() != 2) {
                    view.layout(i, i2 + measuredHeight, i3, (measuredHeight + i2) + view.getMeasuredHeight());
                    return;
                } else {
                    view.layout(i, i2 - measuredHeight, i3, (i2 - measuredHeight) + view.getMeasuredHeight());
                    return;
                }
            case 3:
                if (this.d.getFlexWrap() != 2) {
                    measuredHeight = Math.max(cVar.l - view.getBaseline(), bVar.n());
                    view.layout(i, i2 + measuredHeight, i3, measuredHeight + i4);
                    return;
                }
                measuredHeight = Math.max((cVar.l - view.getMeasuredHeight()) + view.getBaseline(), bVar.p());
                view.layout(i, i2 - measuredHeight, i3, i4 - measuredHeight);
                return;
            default:
                return;
        }
    }

    void a(View view, c cVar, boolean z, int i, int i2, int i3, int i4) {
        b bVar = (b) view.getLayoutParams();
        int alignItems = this.d.getAlignItems();
        if (bVar.f() != -1) {
            alignItems = bVar.f();
        }
        int i5 = cVar.g;
        switch (alignItems) {
            case 0:
            case 3:
            case 4:
                if (z) {
                    view.layout(i - bVar.o(), i2, i3 - bVar.o(), i4);
                    return;
                } else {
                    view.layout(bVar.m() + i, i2, bVar.m() + i3, i4);
                    return;
                }
            case 1:
                if (z) {
                    view.layout(((i - i5) + view.getMeasuredWidth()) + bVar.m(), i2, bVar.m() + ((i3 - i5) + view.getMeasuredWidth()), i4);
                    return;
                }
                view.layout(((i + i5) - view.getMeasuredWidth()) - bVar.o(), i2, ((i5 + i3) - view.getMeasuredWidth()) - bVar.o(), i4);
                return;
            case 2:
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
                int measuredWidth = (((i5 - view.getMeasuredWidth()) + MarginLayoutParamsCompat.getMarginStart(marginLayoutParams)) - MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams)) / 2;
                if (z) {
                    view.layout(i - measuredWidth, i2, i3 - measuredWidth, i4);
                    return;
                } else {
                    view.layout(i + measuredWidth, i2, measuredWidth + i3, i4);
                    return;
                }
            default:
                return;
        }
    }

    void b(int i) {
        if (this.f == null) {
            if (i < 10) {
                i = 10;
            }
            this.f = new long[i];
        } else if (this.f.length < i) {
            int length = this.f.length * 2;
            if (length >= i) {
                i = length;
            }
            this.f = Arrays.copyOf(this.f, i);
        }
    }

    void c(int i) {
        if (this.b == null) {
            if (i < 10) {
                i = 10;
            }
            this.b = new long[i];
        } else if (this.b.length < i) {
            int length = this.b.length * 2;
            if (length >= i) {
                i = length;
            }
            this.b = Arrays.copyOf(this.b, i);
        }
    }

    int a(long j) {
        return (int) j;
    }

    int b(long j) {
        return (int) (j >> 32);
    }

    @VisibleForTesting
    long b(int i, int i2) {
        return (((long) i2) << 32) | (((long) i) & 4294967295L);
    }

    private void a(int i, int i2, int i3, View view) {
        if (this.b != null) {
            this.b[i] = b(i2, i3);
        }
        if (this.f != null) {
            this.f[i] = b(view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }

    void d(int i) {
        if (this.a == null) {
            if (i < 10) {
                i = 10;
            }
            this.a = new int[i];
        } else if (this.a.length < i) {
            int length = this.a.length * 2;
            if (length >= i) {
                i = length;
            }
            this.a = Arrays.copyOf(this.a, i);
        }
    }

    void a(List<c> list, int i) {
        if (!c && this.a == null) {
            throw new AssertionError();
        } else if (c || this.b != null) {
            int i2 = this.a[i];
            if (i2 == -1) {
                i2 = 0;
            }
            for (int size = list.size() - 1; size >= i2; size--) {
                list.remove(size);
            }
            i2 = this.a.length - 1;
            if (i > i2) {
                Arrays.fill(this.a, -1);
            } else {
                Arrays.fill(this.a, i, i2, -1);
            }
            i2 = this.b.length - 1;
            if (i > i2) {
                Arrays.fill(this.b, 0);
            } else {
                Arrays.fill(this.b, i, i2, 0);
            }
        } else {
            throw new AssertionError();
        }
    }
}
