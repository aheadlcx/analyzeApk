package android.support.v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager.LayoutPrefetchRegistry;
import android.support.v7.widget.RecyclerView.LayoutManager.Properties;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.SmoothScroller;
import android.support.v7.widget.RecyclerView.SmoothScroller.ScrollVectorProvider;
import android.support.v7.widget.RecyclerView.State;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class StaggeredGridLayoutManager extends LayoutManager implements ScrollVectorProvider {
    @Deprecated
    public static final int GAP_HANDLING_LAZY = 1;
    public static final int GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS = 2;
    public static final int GAP_HANDLING_NONE = 0;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private SavedState A;
    private int B;
    private final Rect C = new Rect();
    private final a D = new a(this);
    private boolean E = false;
    private boolean F = true;
    private int[] G;
    private final Runnable H = new cn(this);
    b[] a;
    @NonNull
    OrientationHelper b;
    @NonNull
    OrientationHelper c;
    boolean d = false;
    boolean e = false;
    int f = -1;
    int g = Integer.MIN_VALUE;
    LazySpanLookup h = new LazySpanLookup();
    private int i = -1;
    private int j;
    private int k;
    @NonNull
    private final ba l;
    private BitSet m;
    private int n = 2;
    private boolean o;
    private boolean z;

    public static class LayoutParams extends android.support.v7.widget.RecyclerView.LayoutParams {
        public static final int INVALID_SPAN_ID = -1;
        b a;
        boolean b;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(android.support.v7.widget.RecyclerView.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public void setFullSpan(boolean z) {
            this.b = z;
        }

        public boolean isFullSpan() {
            return this.b;
        }

        public final int getSpanIndex() {
            if (this.a == null) {
                return -1;
            }
            return this.a.e;
        }
    }

    static class LazySpanLookup {
        int[] a;
        List<FullSpanItem> b;

        static class FullSpanItem implements Parcelable {
            public static final Creator<FullSpanItem> CREATOR = new co();
            int a;
            int b;
            int[] c;
            boolean d;

            FullSpanItem(Parcel parcel) {
                boolean z = true;
                this.a = parcel.readInt();
                this.b = parcel.readInt();
                if (parcel.readInt() != 1) {
                    z = false;
                }
                this.d = z;
                int readInt = parcel.readInt();
                if (readInt > 0) {
                    this.c = new int[readInt];
                    parcel.readIntArray(this.c);
                }
            }

            FullSpanItem() {
            }

            int a(int i) {
                return this.c == null ? 0 : this.c[i];
            }

            public int describeContents() {
                return 0;
            }

            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.a);
                parcel.writeInt(this.b);
                parcel.writeInt(this.d ? 1 : 0);
                if (this.c == null || this.c.length <= 0) {
                    parcel.writeInt(0);
                    return;
                }
                parcel.writeInt(this.c.length);
                parcel.writeIntArray(this.c);
            }

            public String toString() {
                return "FullSpanItem{mPosition=" + this.a + ", mGapDir=" + this.b + ", mHasUnwantedGapAfter=" + this.d + ", mGapPerSpan=" + Arrays.toString(this.c) + '}';
            }
        }

        LazySpanLookup() {
        }

        int a(int i) {
            if (this.b != null) {
                for (int size = this.b.size() - 1; size >= 0; size--) {
                    if (((FullSpanItem) this.b.get(size)).a >= i) {
                        this.b.remove(size);
                    }
                }
            }
            return b(i);
        }

        int b(int i) {
            if (this.a == null || i >= this.a.length) {
                return -1;
            }
            int f = f(i);
            if (f == -1) {
                Arrays.fill(this.a, i, this.a.length, -1);
                return this.a.length;
            }
            Arrays.fill(this.a, i, f + 1, -1);
            return f + 1;
        }

        int c(int i) {
            if (this.a == null || i >= this.a.length) {
                return -1;
            }
            return this.a[i];
        }

        void a(int i, b bVar) {
            e(i);
            this.a[i] = bVar.e;
        }

        int d(int i) {
            int length = this.a.length;
            while (length <= i) {
                length *= 2;
            }
            return length;
        }

        void e(int i) {
            if (this.a == null) {
                this.a = new int[(Math.max(i, 10) + 1)];
                Arrays.fill(this.a, -1);
            } else if (i >= this.a.length) {
                Object obj = this.a;
                this.a = new int[d(i)];
                System.arraycopy(obj, 0, this.a, 0, obj.length);
                Arrays.fill(this.a, obj.length, this.a.length, -1);
            }
        }

        void a() {
            if (this.a != null) {
                Arrays.fill(this.a, -1);
            }
            this.b = null;
        }

        void a(int i, int i2) {
            if (this.a != null && i < this.a.length) {
                e(i + i2);
                System.arraycopy(this.a, i + i2, this.a, i, (this.a.length - i) - i2);
                Arrays.fill(this.a, this.a.length - i2, this.a.length, -1);
                c(i, i2);
            }
        }

        private void c(int i, int i2) {
            if (this.b != null) {
                int i3 = i + i2;
                for (int size = this.b.size() - 1; size >= 0; size--) {
                    FullSpanItem fullSpanItem = (FullSpanItem) this.b.get(size);
                    if (fullSpanItem.a >= i) {
                        if (fullSpanItem.a < i3) {
                            this.b.remove(size);
                        } else {
                            fullSpanItem.a -= i2;
                        }
                    }
                }
            }
        }

        void b(int i, int i2) {
            if (this.a != null && i < this.a.length) {
                e(i + i2);
                System.arraycopy(this.a, i, this.a, i + i2, (this.a.length - i) - i2);
                Arrays.fill(this.a, i, i + i2, -1);
                d(i, i2);
            }
        }

        private void d(int i, int i2) {
            if (this.b != null) {
                for (int size = this.b.size() - 1; size >= 0; size--) {
                    FullSpanItem fullSpanItem = (FullSpanItem) this.b.get(size);
                    if (fullSpanItem.a >= i) {
                        fullSpanItem.a += i2;
                    }
                }
            }
        }

        private int f(int i) {
            if (this.b == null) {
                return -1;
            }
            FullSpanItem fullSpanItem = getFullSpanItem(i);
            if (fullSpanItem != null) {
                this.b.remove(fullSpanItem);
            }
            int size = this.b.size();
            int i2 = 0;
            while (i2 < size) {
                if (((FullSpanItem) this.b.get(i2)).a >= i) {
                    break;
                }
                i2++;
            }
            i2 = -1;
            if (i2 == -1) {
                return -1;
            }
            fullSpanItem = (FullSpanItem) this.b.get(i2);
            this.b.remove(i2);
            return fullSpanItem.a;
        }

        public void addFullSpanItem(FullSpanItem fullSpanItem) {
            if (this.b == null) {
                this.b = new ArrayList();
            }
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                FullSpanItem fullSpanItem2 = (FullSpanItem) this.b.get(i);
                if (fullSpanItem2.a == fullSpanItem.a) {
                    this.b.remove(i);
                }
                if (fullSpanItem2.a >= fullSpanItem.a) {
                    this.b.add(i, fullSpanItem);
                    return;
                }
            }
            this.b.add(fullSpanItem);
        }

        public FullSpanItem getFullSpanItem(int i) {
            if (this.b == null) {
                return null;
            }
            for (int size = this.b.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = (FullSpanItem) this.b.get(size);
                if (fullSpanItem.a == i) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public FullSpanItem getFirstFullSpanItemInRange(int i, int i2, int i3, boolean z) {
            if (this.b == null) {
                return null;
            }
            int size = this.b.size();
            for (int i4 = 0; i4 < size; i4++) {
                FullSpanItem fullSpanItem = (FullSpanItem) this.b.get(i4);
                if (fullSpanItem.a >= i2) {
                    return null;
                }
                if (fullSpanItem.a >= i) {
                    if (i3 == 0 || fullSpanItem.b == i3) {
                        return fullSpanItem;
                    }
                    if (z && fullSpanItem.d) {
                        return fullSpanItem;
                    }
                }
            }
            return null;
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static class SavedState implements Parcelable {
        public static final Creator<SavedState> CREATOR = new cp();
        int a;
        int b;
        int c;
        int[] d;
        int e;
        int[] f;
        List<FullSpanItem> g;
        boolean h;
        boolean i;
        boolean j;

        SavedState(Parcel parcel) {
            boolean z;
            boolean z2 = true;
            this.a = parcel.readInt();
            this.b = parcel.readInt();
            this.c = parcel.readInt();
            if (this.c > 0) {
                this.d = new int[this.c];
                parcel.readIntArray(this.d);
            }
            this.e = parcel.readInt();
            if (this.e > 0) {
                this.f = new int[this.e];
                parcel.readIntArray(this.f);
            }
            this.h = parcel.readInt() == 1;
            if (parcel.readInt() == 1) {
                z = true;
            } else {
                z = false;
            }
            this.i = z;
            if (parcel.readInt() != 1) {
                z2 = false;
            }
            this.j = z2;
            this.g = parcel.readArrayList(FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState savedState) {
            this.c = savedState.c;
            this.a = savedState.a;
            this.b = savedState.b;
            this.d = savedState.d;
            this.e = savedState.e;
            this.f = savedState.f;
            this.h = savedState.h;
            this.i = savedState.i;
            this.j = savedState.j;
            this.g = savedState.g;
        }

        void a() {
            this.d = null;
            this.c = 0;
            this.e = 0;
            this.f = null;
            this.g = null;
        }

        void b() {
            this.d = null;
            this.c = 0;
            this.a = -1;
            this.b = -1;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int i2;
            int i3 = 1;
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            if (this.c > 0) {
                parcel.writeIntArray(this.d);
            }
            parcel.writeInt(this.e);
            if (this.e > 0) {
                parcel.writeIntArray(this.f);
            }
            if (this.h) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            parcel.writeInt(i2);
            if (this.i) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            parcel.writeInt(i2);
            if (!this.j) {
                i3 = 0;
            }
            parcel.writeInt(i3);
            parcel.writeList(this.g);
        }
    }

    class a {
        int a;
        int b;
        boolean c;
        boolean d;
        boolean e;
        int[] f;
        final /* synthetic */ StaggeredGridLayoutManager g;

        a(StaggeredGridLayoutManager staggeredGridLayoutManager) {
            this.g = staggeredGridLayoutManager;
            a();
        }

        void a() {
            this.a = -1;
            this.b = Integer.MIN_VALUE;
            this.c = false;
            this.d = false;
            this.e = false;
            if (this.f != null) {
                Arrays.fill(this.f, -1);
            }
        }

        void a(b[] bVarArr) {
            int length = bVarArr.length;
            if (this.f == null || this.f.length < length) {
                this.f = new int[this.g.a.length];
            }
            for (int i = 0; i < length; i++) {
                this.f[i] = bVarArr[i].a(Integer.MIN_VALUE);
            }
        }

        void b() {
            int endAfterPadding;
            if (this.c) {
                endAfterPadding = this.g.b.getEndAfterPadding();
            } else {
                endAfterPadding = this.g.b.getStartAfterPadding();
            }
            this.b = endAfterPadding;
        }

        void a(int i) {
            if (this.c) {
                this.b = this.g.b.getEndAfterPadding() - i;
            } else {
                this.b = this.g.b.getStartAfterPadding() + i;
            }
        }
    }

    class b {
        ArrayList<View> a = new ArrayList();
        int b = Integer.MIN_VALUE;
        int c = Integer.MIN_VALUE;
        int d = 0;
        final int e;
        final /* synthetic */ StaggeredGridLayoutManager f;

        b(StaggeredGridLayoutManager staggeredGridLayoutManager, int i) {
            this.f = staggeredGridLayoutManager;
            this.e = i;
        }

        int a(int i) {
            if (this.b != Integer.MIN_VALUE) {
                return this.b;
            }
            if (this.a.size() == 0) {
                return i;
            }
            a();
            return this.b;
        }

        void a() {
            View view = (View) this.a.get(0);
            LayoutParams c = c(view);
            this.b = this.f.b.getDecoratedStart(view);
            if (c.b) {
                FullSpanItem fullSpanItem = this.f.h.getFullSpanItem(c.getViewLayoutPosition());
                if (fullSpanItem != null && fullSpanItem.b == -1) {
                    this.b -= fullSpanItem.a(this.e);
                }
            }
        }

        int b() {
            if (this.b != Integer.MIN_VALUE) {
                return this.b;
            }
            a();
            return this.b;
        }

        int b(int i) {
            if (this.c != Integer.MIN_VALUE) {
                return this.c;
            }
            if (this.a.size() == 0) {
                return i;
            }
            c();
            return this.c;
        }

        void c() {
            View view = (View) this.a.get(this.a.size() - 1);
            LayoutParams c = c(view);
            this.c = this.f.b.getDecoratedEnd(view);
            if (c.b) {
                FullSpanItem fullSpanItem = this.f.h.getFullSpanItem(c.getViewLayoutPosition());
                if (fullSpanItem != null && fullSpanItem.b == 1) {
                    this.c = fullSpanItem.a(this.e) + this.c;
                }
            }
        }

        int d() {
            if (this.c != Integer.MIN_VALUE) {
                return this.c;
            }
            c();
            return this.c;
        }

        void a(View view) {
            LayoutParams c = c(view);
            c.a = this;
            this.a.add(0, view);
            this.b = Integer.MIN_VALUE;
            if (this.a.size() == 1) {
                this.c = Integer.MIN_VALUE;
            }
            if (c.isItemRemoved() || c.isItemChanged()) {
                this.d += this.f.b.getDecoratedMeasurement(view);
            }
        }

        void b(View view) {
            LayoutParams c = c(view);
            c.a = this;
            this.a.add(view);
            this.c = Integer.MIN_VALUE;
            if (this.a.size() == 1) {
                this.b = Integer.MIN_VALUE;
            }
            if (c.isItemRemoved() || c.isItemChanged()) {
                this.d += this.f.b.getDecoratedMeasurement(view);
            }
        }

        void a(boolean z, int i) {
            int b;
            if (z) {
                b = b(Integer.MIN_VALUE);
            } else {
                b = a(Integer.MIN_VALUE);
            }
            e();
            if (b != Integer.MIN_VALUE) {
                if (z && b < this.f.b.getEndAfterPadding()) {
                    return;
                }
                if (z || b <= this.f.b.getStartAfterPadding()) {
                    if (i != Integer.MIN_VALUE) {
                        b += i;
                    }
                    this.c = b;
                    this.b = b;
                }
            }
        }

        void e() {
            this.a.clear();
            f();
            this.d = 0;
        }

        void f() {
            this.b = Integer.MIN_VALUE;
            this.c = Integer.MIN_VALUE;
        }

        void c(int i) {
            this.b = i;
            this.c = i;
        }

        void g() {
            int size = this.a.size();
            View view = (View) this.a.remove(size - 1);
            LayoutParams c = c(view);
            c.a = null;
            if (c.isItemRemoved() || c.isItemChanged()) {
                this.d -= this.f.b.getDecoratedMeasurement(view);
            }
            if (size == 1) {
                this.b = Integer.MIN_VALUE;
            }
            this.c = Integer.MIN_VALUE;
        }

        void h() {
            View view = (View) this.a.remove(0);
            LayoutParams c = c(view);
            c.a = null;
            if (this.a.size() == 0) {
                this.c = Integer.MIN_VALUE;
            }
            if (c.isItemRemoved() || c.isItemChanged()) {
                this.d -= this.f.b.getDecoratedMeasurement(view);
            }
            this.b = Integer.MIN_VALUE;
        }

        public int getDeletedSize() {
            return this.d;
        }

        LayoutParams c(View view) {
            return (LayoutParams) view.getLayoutParams();
        }

        void d(int i) {
            if (this.b != Integer.MIN_VALUE) {
                this.b += i;
            }
            if (this.c != Integer.MIN_VALUE) {
                this.c += i;
            }
        }

        public int findFirstVisibleItemPosition() {
            if (this.f.d) {
                return a(this.a.size() - 1, -1, false);
            }
            return a(0, this.a.size(), false);
        }

        public int findFirstPartiallyVisibleItemPosition() {
            if (this.f.d) {
                return b(this.a.size() - 1, -1, true);
            }
            return b(0, this.a.size(), true);
        }

        public int findFirstCompletelyVisibleItemPosition() {
            if (this.f.d) {
                return a(this.a.size() - 1, -1, true);
            }
            return a(0, this.a.size(), true);
        }

        public int findLastVisibleItemPosition() {
            if (this.f.d) {
                return a(0, this.a.size(), false);
            }
            return a(this.a.size() - 1, -1, false);
        }

        public int findLastPartiallyVisibleItemPosition() {
            if (this.f.d) {
                return b(0, this.a.size(), true);
            }
            return b(this.a.size() - 1, -1, true);
        }

        public int findLastCompletelyVisibleItemPosition() {
            if (this.f.d) {
                return a(0, this.a.size(), true);
            }
            return a(this.a.size() - 1, -1, true);
        }

        int a(int i, int i2, boolean z, boolean z2, boolean z3) {
            int startAfterPadding = this.f.b.getStartAfterPadding();
            int endAfterPadding = this.f.b.getEndAfterPadding();
            int i3 = i2 > i ? 1 : -1;
            while (i != i2) {
                Object obj;
                View view = (View) this.a.get(i);
                int decoratedStart = this.f.b.getDecoratedStart(view);
                int decoratedEnd = this.f.b.getDecoratedEnd(view);
                if (z3) {
                    obj = decoratedStart <= endAfterPadding ? 1 : null;
                } else if (decoratedStart < endAfterPadding) {
                    int i4 = 1;
                } else {
                    obj = null;
                }
                Object obj2 = z3 ? decoratedEnd >= startAfterPadding ? 1 : null : decoratedEnd > startAfterPadding ? 1 : null;
                if (!(obj == null || r2 == null)) {
                    if (z && z2) {
                        if (decoratedStart >= startAfterPadding && decoratedEnd <= endAfterPadding) {
                            return this.f.getPosition(view);
                        }
                    } else if (z2) {
                        return this.f.getPosition(view);
                    } else {
                        if (decoratedStart < startAfterPadding || decoratedEnd > endAfterPadding) {
                            return this.f.getPosition(view);
                        }
                    }
                }
                i += i3;
            }
            return -1;
        }

        int a(int i, int i2, boolean z) {
            return a(i, i2, z, true, false);
        }

        int b(int i, int i2, boolean z) {
            return a(i, i2, false, false, z);
        }

        public View getFocusableViewAfter(int i, int i2) {
            View view = null;
            int i3;
            View view2;
            if (i2 == -1) {
                int size = this.a.size();
                i3 = 0;
                while (i3 < size) {
                    view2 = (View) this.a.get(i3);
                    if ((this.f.d && this.f.getPosition(view2) <= i) || ((!this.f.d && this.f.getPosition(view2) >= i) || !view2.hasFocusable())) {
                        break;
                    }
                    i3++;
                    view = view2;
                }
                return view;
            }
            i3 = this.a.size() - 1;
            while (i3 >= 0) {
                view2 = (View) this.a.get(i3);
                if (this.f.d && this.f.getPosition(view2) >= i) {
                    break;
                } else if (this.f.d || this.f.getPosition(view2) > i) {
                    if (!view2.hasFocusable()) {
                        break;
                    }
                    i3--;
                    view = view2;
                } else {
                    return view;
                }
            }
            return view;
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        boolean z = true;
        Properties properties = LayoutManager.getProperties(context, attributeSet, i, i2);
        setOrientation(properties.orientation);
        setSpanCount(properties.spanCount);
        setReverseLayout(properties.reverseLayout);
        if (this.n == 0) {
            z = false;
        }
        setAutoMeasureEnabled(z);
        this.l = new ba();
        l();
    }

    public StaggeredGridLayoutManager(int i, int i2) {
        boolean z = true;
        this.j = i2;
        setSpanCount(i);
        if (this.n == 0) {
            z = false;
        }
        setAutoMeasureEnabled(z);
        this.l = new ba();
        l();
    }

    private void l() {
        this.b = OrientationHelper.createOrientationHelper(this, this.j);
        this.c = OrientationHelper.createOrientationHelper(this, 1 - this.j);
    }

    boolean a() {
        if (getChildCount() == 0 || this.n == 0 || !isAttachedToWindow()) {
            return false;
        }
        int j;
        int k;
        if (this.e) {
            j = j();
            k = k();
        } else {
            j = k();
            k = j();
        }
        if (j == 0 && b() != null) {
            this.h.a();
            requestSimpleAnimationsInNextLayout();
            requestLayout();
            return true;
        } else if (!this.E) {
            return false;
        } else {
            int i = this.e ? -1 : 1;
            FullSpanItem firstFullSpanItemInRange = this.h.getFirstFullSpanItemInRange(j, k + 1, i, true);
            if (firstFullSpanItemInRange == null) {
                this.E = false;
                this.h.a(k + 1);
                return false;
            }
            FullSpanItem firstFullSpanItemInRange2 = this.h.getFirstFullSpanItemInRange(j, firstFullSpanItemInRange.a, i * -1, true);
            if (firstFullSpanItemInRange2 == null) {
                this.h.a(firstFullSpanItemInRange.a);
            } else {
                this.h.a(firstFullSpanItemInRange2.a + 1);
            }
            requestSimpleAnimationsInNextLayout();
            requestLayout();
            return true;
        }
    }

    public void onScrollStateChanged(int i) {
        if (i == 0) {
            a();
        }
    }

    public void onDetachedFromWindow(RecyclerView recyclerView, Recycler recycler) {
        removeCallbacks(this.H);
        for (int i = 0; i < this.i; i++) {
            this.a[i].e();
        }
        recyclerView.requestLayout();
    }

    View b() {
        int i;
        int i2;
        int childCount = getChildCount() - 1;
        BitSet bitSet = new BitSet(this.i);
        bitSet.set(0, this.i, true);
        boolean z = (this.j == 1 && c()) ? true : true;
        if (this.e) {
            i = -1;
        } else {
            i = childCount + 1;
            childCount = 0;
        }
        if (childCount < i) {
            i2 = 1;
        } else {
            i2 = -1;
        }
        int i3 = childCount;
        while (i3 != i) {
            View childAt = getChildAt(i3);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (bitSet.get(layoutParams.a.e)) {
                if (a(layoutParams.a)) {
                    return childAt;
                }
                bitSet.clear(layoutParams.a.e);
            }
            if (!(layoutParams.b || i3 + i2 == i)) {
                boolean z2;
                View childAt2 = getChildAt(i3 + i2);
                int decoratedEnd;
                if (this.e) {
                    childCount = this.b.getDecoratedEnd(childAt);
                    decoratedEnd = this.b.getDecoratedEnd(childAt2);
                    if (childCount < decoratedEnd) {
                        return childAt;
                    }
                    if (childCount == decoratedEnd) {
                        z2 = true;
                    }
                    z2 = false;
                } else {
                    childCount = this.b.getDecoratedStart(childAt);
                    decoratedEnd = this.b.getDecoratedStart(childAt2);
                    if (childCount > decoratedEnd) {
                        return childAt;
                    }
                    if (childCount == decoratedEnd) {
                        z2 = true;
                    }
                    z2 = false;
                }
                if (z2) {
                    if (layoutParams.a.e - ((LayoutParams) childAt2.getLayoutParams()).a.e < 0) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (z2 != (z >= false)) {
                        return childAt;
                    }
                } else {
                    continue;
                }
            }
            i3 += i2;
        }
        return null;
    }

    private boolean a(b bVar) {
        boolean z = true;
        if (this.e) {
            if (bVar.d() < this.b.getEndAfterPadding()) {
                return !bVar.c((View) bVar.a.get(bVar.a.size() + -1)).b;
            }
        } else if (bVar.b() > this.b.getStartAfterPadding()) {
            if (bVar.c((View) bVar.a.get(0)).b) {
                z = false;
            }
            return z;
        }
        return false;
    }

    public void setSpanCount(int i) {
        assertNotInLayoutOrScroll(null);
        if (i != this.i) {
            invalidateSpanAssignments();
            this.i = i;
            this.m = new BitSet(this.i);
            this.a = new b[this.i];
            for (int i2 = 0; i2 < this.i; i2++) {
                this.a[i2] = new b(this, i2);
            }
            requestLayout();
        }
    }

    public void setOrientation(int i) {
        if (i == 0 || i == 1) {
            assertNotInLayoutOrScroll(null);
            if (i != this.j) {
                this.j = i;
                OrientationHelper orientationHelper = this.b;
                this.b = this.c;
                this.c = orientationHelper;
                requestLayout();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("invalid orientation.");
    }

    public void setReverseLayout(boolean z) {
        assertNotInLayoutOrScroll(null);
        if (!(this.A == null || this.A.h == z)) {
            this.A.h = z;
        }
        this.d = z;
        requestLayout();
    }

    public int getGapStrategy() {
        return this.n;
    }

    public void setGapStrategy(int i) {
        assertNotInLayoutOrScroll(null);
        if (i != this.n) {
            if (i == 0 || i == 2) {
                this.n = i;
                setAutoMeasureEnabled(this.n != 0);
                requestLayout();
                return;
            }
            throw new IllegalArgumentException("invalid gap strategy. Must be GAP_HANDLING_NONE or GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS");
        }
    }

    public void assertNotInLayoutOrScroll(String str) {
        if (this.A == null) {
            super.assertNotInLayoutOrScroll(str);
        }
    }

    public int getSpanCount() {
        return this.i;
    }

    public void invalidateSpanAssignments() {
        this.h.a();
        requestLayout();
    }

    private void m() {
        boolean z = true;
        if (this.j == 1 || !c()) {
            this.e = this.d;
            return;
        }
        if (this.d) {
            z = false;
        }
        this.e = z;
    }

    boolean c() {
        return getLayoutDirection() == 1;
    }

    public boolean getReverseLayout() {
        return this.d;
    }

    public void setMeasuredDimension(Rect rect, int i, int i2) {
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        if (this.j == 1) {
            paddingTop = LayoutManager.chooseSize(i2, paddingTop + rect.height(), getMinimumHeight());
            paddingRight = LayoutManager.chooseSize(i, paddingRight + (this.k * this.i), getMinimumWidth());
        } else {
            paddingRight = LayoutManager.chooseSize(i, paddingRight + rect.width(), getMinimumWidth());
            paddingTop = LayoutManager.chooseSize(i2, paddingTop + (this.k * this.i), getMinimumHeight());
        }
        setMeasuredDimension(paddingRight, paddingTop);
    }

    public void onLayoutChildren(Recycler recycler, State state) {
        a(recycler, state, true);
    }

    private void a(Recycler recycler, State state, boolean z) {
        a aVar = this.D;
        if (!(this.A == null && this.f == -1) && state.getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
            aVar.a();
            return;
        }
        boolean z2 = (aVar.e && this.f == -1 && this.A == null) ? false : true;
        if (z2) {
            aVar.a();
            if (this.A != null) {
                a(aVar);
            } else {
                m();
                aVar.c = this.e;
            }
            a(state, aVar);
            aVar.e = true;
        }
        if (this.A == null && this.f == -1 && !(aVar.c == this.o && c() == this.z)) {
            this.h.a();
            aVar.d = true;
        }
        if (getChildCount() > 0 && (this.A == null || this.A.c < 1)) {
            int i;
            if (aVar.d) {
                for (i = 0; i < this.i; i++) {
                    this.a[i].e();
                    if (aVar.b != Integer.MIN_VALUE) {
                        this.a[i].c(aVar.b);
                    }
                }
            } else if (z2 || this.D.f == null) {
                for (i = 0; i < this.i; i++) {
                    this.a[i].a(this.e, aVar.b);
                }
                this.D.a(this.a);
            } else {
                for (i = 0; i < this.i; i++) {
                    b bVar = this.a[i];
                    bVar.e();
                    bVar.c(this.D.f[i]);
                }
            }
        }
        detachAndScrapAttachedViews(recycler);
        this.l.a = false;
        this.E = false;
        a(this.c.getTotalSpace());
        b(aVar.a, state);
        if (aVar.c) {
            b(-1);
            a(recycler, this.l, state);
            b(1);
            this.l.c = aVar.a + this.l.d;
            a(recycler, this.l, state);
        } else {
            b(1);
            a(recycler, this.l, state);
            b(-1);
            this.l.c = aVar.a + this.l.d;
            a(recycler, this.l, state);
        }
        n();
        if (getChildCount() > 0) {
            if (this.e) {
                b(recycler, state, true);
                c(recycler, state, false);
            } else {
                c(recycler, state, true);
                b(recycler, state, false);
            }
        }
        if (z && !state.isPreLayout()) {
            if (this.n == 0 || getChildCount() <= 0 || (!this.E && b() == null)) {
                z2 = false;
            } else {
                z2 = true;
            }
            if (z2) {
                removeCallbacks(this.H);
                if (a()) {
                    z2 = true;
                    if (state.isPreLayout()) {
                        this.D.a();
                    }
                    this.o = aVar.c;
                    this.z = c();
                    if (z2) {
                        this.D.a();
                        a(recycler, state, false);
                    }
                }
            }
        }
        z2 = false;
        if (state.isPreLayout()) {
            this.D.a();
        }
        this.o = aVar.c;
        this.z = c();
        if (z2) {
            this.D.a();
            a(recycler, state, false);
        }
    }

    public void onLayoutCompleted(State state) {
        super.onLayoutCompleted(state);
        this.f = -1;
        this.g = Integer.MIN_VALUE;
        this.A = null;
        this.D.a();
    }

    private void n() {
        if (this.c.getMode() != 1073741824) {
            float f = 0.0f;
            int childCount = getChildCount();
            int i = 0;
            while (i < childCount) {
                float f2;
                View childAt = getChildAt(i);
                float decoratedMeasurement = (float) this.c.getDecoratedMeasurement(childAt);
                if (decoratedMeasurement < f) {
                    f2 = f;
                } else {
                    if (((LayoutParams) childAt.getLayoutParams()).isFullSpan()) {
                        f2 = (1.0f * decoratedMeasurement) / ((float) this.i);
                    } else {
                        f2 = decoratedMeasurement;
                    }
                    f2 = Math.max(f, f2);
                }
                i++;
                f = f2;
            }
            i = this.k;
            int round = Math.round(((float) this.i) * f);
            if (this.c.getMode() == Integer.MIN_VALUE) {
                round = Math.min(round, this.c.getTotalSpace());
            }
            a(round);
            if (this.k != i) {
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt2 = getChildAt(i2);
                    LayoutParams layoutParams = (LayoutParams) childAt2.getLayoutParams();
                    if (!layoutParams.b) {
                        if (c() && this.j == 1) {
                            childAt2.offsetLeftAndRight(((-((this.i - 1) - layoutParams.a.e)) * this.k) - ((-((this.i - 1) - layoutParams.a.e)) * i));
                        } else {
                            int i3 = layoutParams.a.e * this.k;
                            round = layoutParams.a.e * i;
                            if (this.j == 1) {
                                childAt2.offsetLeftAndRight(i3 - round);
                            } else {
                                childAt2.offsetTopAndBottom(i3 - round);
                            }
                        }
                    }
                }
            }
        }
    }

    private void a(a aVar) {
        if (this.A.c > 0) {
            if (this.A.c == this.i) {
                for (int i = 0; i < this.i; i++) {
                    this.a[i].e();
                    int i2 = this.A.d[i];
                    if (i2 != Integer.MIN_VALUE) {
                        if (this.A.i) {
                            i2 += this.b.getEndAfterPadding();
                        } else {
                            i2 += this.b.getStartAfterPadding();
                        }
                    }
                    this.a[i].c(i2);
                }
            } else {
                this.A.a();
                this.A.a = this.A.b;
            }
        }
        this.z = this.A.j;
        setReverseLayout(this.A.h);
        m();
        if (this.A.a != -1) {
            this.f = this.A.a;
            aVar.c = this.A.i;
        } else {
            aVar.c = this.e;
        }
        if (this.A.e > 1) {
            this.h.a = this.A.f;
            this.h.b = this.A.g;
        }
    }

    void a(State state, a aVar) {
        if (!b(state, aVar) && !c(state, aVar)) {
            aVar.b();
            aVar.a = 0;
        }
    }

    private boolean c(State state, a aVar) {
        int l;
        if (this.o) {
            l = l(state.getItemCount());
        } else {
            l = k(state.getItemCount());
        }
        aVar.a = l;
        aVar.b = Integer.MIN_VALUE;
        return true;
    }

    boolean b(State state, a aVar) {
        boolean z = false;
        if (state.isPreLayout() || this.f == -1) {
            return false;
        }
        if (this.f < 0 || this.f >= state.getItemCount()) {
            this.f = -1;
            this.g = Integer.MIN_VALUE;
            return false;
        } else if (this.A == null || this.A.a == -1 || this.A.c < 1) {
            View findViewByPosition = findViewByPosition(this.f);
            if (findViewByPosition != null) {
                int j;
                if (this.e) {
                    j = j();
                } else {
                    j = k();
                }
                aVar.a = j;
                if (this.g != Integer.MIN_VALUE) {
                    if (aVar.c) {
                        aVar.b = (this.b.getEndAfterPadding() - this.g) - this.b.getDecoratedEnd(findViewByPosition);
                        return true;
                    }
                    aVar.b = (this.b.getStartAfterPadding() + this.g) - this.b.getDecoratedStart(findViewByPosition);
                    return true;
                } else if (this.b.getDecoratedMeasurement(findViewByPosition) > this.b.getTotalSpace()) {
                    if (aVar.c) {
                        j = this.b.getEndAfterPadding();
                    } else {
                        j = this.b.getStartAfterPadding();
                    }
                    aVar.b = j;
                    return true;
                } else {
                    j = this.b.getDecoratedStart(findViewByPosition) - this.b.getStartAfterPadding();
                    if (j < 0) {
                        aVar.b = -j;
                        return true;
                    }
                    j = this.b.getEndAfterPadding() - this.b.getDecoratedEnd(findViewByPosition);
                    if (j < 0) {
                        aVar.b = j;
                        return true;
                    }
                    aVar.b = Integer.MIN_VALUE;
                    return true;
                }
            }
            aVar.a = this.f;
            if (this.g == Integer.MIN_VALUE) {
                if (j(aVar.a) == 1) {
                    z = true;
                }
                aVar.c = z;
                aVar.b();
            } else {
                aVar.a(this.g);
            }
            aVar.d = true;
            return true;
        } else {
            aVar.b = Integer.MIN_VALUE;
            aVar.a = this.f;
            return true;
        }
    }

    void a(int i) {
        this.k = i / this.i;
        this.B = MeasureSpec.makeMeasureSpec(i, this.c.getMode());
    }

    public boolean supportsPredictiveItemAnimations() {
        return this.A == null;
    }

    public int[] findFirstVisibleItemPositions(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.i];
        } else if (iArr.length < this.i) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.i + ", array size:" + iArr.length);
        }
        for (int i = 0; i < this.i; i++) {
            iArr[i] = this.a[i].findFirstVisibleItemPosition();
        }
        return iArr;
    }

    public int[] findFirstCompletelyVisibleItemPositions(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.i];
        } else if (iArr.length < this.i) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.i + ", array size:" + iArr.length);
        }
        for (int i = 0; i < this.i; i++) {
            iArr[i] = this.a[i].findFirstCompletelyVisibleItemPosition();
        }
        return iArr;
    }

    public int[] findLastVisibleItemPositions(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.i];
        } else if (iArr.length < this.i) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.i + ", array size:" + iArr.length);
        }
        for (int i = 0; i < this.i; i++) {
            iArr[i] = this.a[i].findLastVisibleItemPosition();
        }
        return iArr;
    }

    public int[] findLastCompletelyVisibleItemPositions(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.i];
        } else if (iArr.length < this.i) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.i + ", array size:" + iArr.length);
        }
        for (int i = 0; i < this.i; i++) {
            iArr[i] = this.a[i].findLastCompletelyVisibleItemPosition();
        }
        return iArr;
    }

    public int computeHorizontalScrollOffset(State state) {
        return a(state);
    }

    private int a(State state) {
        boolean z = true;
        if (getChildCount() == 0) {
            return 0;
        }
        OrientationHelper orientationHelper = this.b;
        View a = a(!this.F);
        if (this.F) {
            z = false;
        }
        return bx.a(state, orientationHelper, a, b(z), this, this.F, this.e);
    }

    public int computeVerticalScrollOffset(State state) {
        return a(state);
    }

    public int computeHorizontalScrollExtent(State state) {
        return b(state);
    }

    private int b(State state) {
        boolean z = true;
        if (getChildCount() == 0) {
            return 0;
        }
        OrientationHelper orientationHelper = this.b;
        View a = a(!this.F);
        if (this.F) {
            z = false;
        }
        return bx.a(state, orientationHelper, a, b(z), this, this.F);
    }

    public int computeVerticalScrollExtent(State state) {
        return b(state);
    }

    public int computeHorizontalScrollRange(State state) {
        return c(state);
    }

    private int c(State state) {
        boolean z = true;
        if (getChildCount() == 0) {
            return 0;
        }
        OrientationHelper orientationHelper = this.b;
        View a = a(!this.F);
        if (this.F) {
            z = false;
        }
        return bx.b(state, orientationHelper, a, b(z), this, this.F);
    }

    public int computeVerticalScrollRange(State state) {
        return c(state);
    }

    private void a(View view, LayoutParams layoutParams, boolean z) {
        if (layoutParams.b) {
            if (this.j == 1) {
                a(view, this.B, LayoutManager.getChildMeasureSpec(getHeight(), getHeightMode(), 0, layoutParams.height, true), z);
            } else {
                a(view, LayoutManager.getChildMeasureSpec(getWidth(), getWidthMode(), 0, layoutParams.width, true), this.B, z);
            }
        } else if (this.j == 1) {
            a(view, LayoutManager.getChildMeasureSpec(this.k, getWidthMode(), 0, layoutParams.width, false), LayoutManager.getChildMeasureSpec(getHeight(), getHeightMode(), 0, layoutParams.height, true), z);
        } else {
            a(view, LayoutManager.getChildMeasureSpec(getWidth(), getWidthMode(), 0, layoutParams.width, true), LayoutManager.getChildMeasureSpec(this.k, getHeightMode(), 0, layoutParams.height, false), z);
        }
    }

    private void a(View view, int i, int i2, boolean z) {
        boolean a;
        calculateItemDecorationsForChild(view, this.C);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int a2 = a(i, layoutParams.leftMargin + this.C.left, layoutParams.rightMargin + this.C.right);
        int a3 = a(i2, layoutParams.topMargin + this.C.top, layoutParams.bottomMargin + this.C.bottom);
        if (z) {
            a = a(view, a2, a3, (android.support.v7.widget.RecyclerView.LayoutParams) layoutParams);
        } else {
            a = b(view, a2, a3, layoutParams);
        }
        if (a) {
            view.measure(a2, a3);
        }
    }

    private int a(int i, int i2, int i3) {
        if (i2 == 0 && i3 == 0) {
            return i;
        }
        int mode = MeasureSpec.getMode(i);
        if (mode == Integer.MIN_VALUE || mode == 1073741824) {
            return MeasureSpec.makeMeasureSpec(Math.max(0, (MeasureSpec.getSize(i) - i2) - i3), mode);
        }
        return i;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.A = (SavedState) parcelable;
            requestLayout();
        }
    }

    public Parcelable onSaveInstanceState() {
        if (this.A != null) {
            return new SavedState(this.A);
        }
        SavedState savedState = new SavedState();
        savedState.h = this.d;
        savedState.i = this.o;
        savedState.j = this.z;
        if (this.h == null || this.h.a == null) {
            savedState.e = 0;
        } else {
            savedState.f = this.h.a;
            savedState.e = savedState.f.length;
            savedState.g = this.h.b;
        }
        if (getChildCount() > 0) {
            int j;
            if (this.o) {
                j = j();
            } else {
                j = k();
            }
            savedState.a = j;
            savedState.b = d();
            savedState.c = this.i;
            savedState.d = new int[this.i];
            for (j = 0; j < this.i; j++) {
                int b;
                if (this.o) {
                    b = this.a[j].b(Integer.MIN_VALUE);
                    if (b != Integer.MIN_VALUE) {
                        b -= this.b.getEndAfterPadding();
                    }
                } else {
                    b = this.a[j].a(Integer.MIN_VALUE);
                    if (b != Integer.MIN_VALUE) {
                        b -= this.b.getStartAfterPadding();
                    }
                }
                savedState.d[j] = b;
            }
        } else {
            savedState.a = -1;
            savedState.b = -1;
            savedState.c = 0;
        }
        return savedState;
    }

    public void onInitializeAccessibilityNodeInfoForItem(Recycler recycler, State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        android.view.ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof LayoutParams) {
            LayoutParams layoutParams2 = (LayoutParams) layoutParams;
            if (this.j == 0) {
                accessibilityNodeInfoCompat.setCollectionItemInfo(CollectionItemInfoCompat.obtain(layoutParams2.getSpanIndex(), layoutParams2.b ? this.i : 1, -1, -1, layoutParams2.b, false));
                return;
            } else {
                accessibilityNodeInfoCompat.setCollectionItemInfo(CollectionItemInfoCompat.obtain(-1, -1, layoutParams2.getSpanIndex(), layoutParams2.b ? this.i : 1, layoutParams2.b, false));
                return;
            }
        }
        super.a(view, accessibilityNodeInfoCompat);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            View a = a(false);
            View b = b(false);
            if (a != null && b != null) {
                int position = getPosition(a);
                int position2 = getPosition(b);
                if (position < position2) {
                    accessibilityEvent.setFromIndex(position);
                    accessibilityEvent.setToIndex(position2);
                    return;
                }
                accessibilityEvent.setFromIndex(position2);
                accessibilityEvent.setToIndex(position);
            }
        }
    }

    int d() {
        View b;
        if (this.e) {
            b = b(true);
        } else {
            b = a(true);
        }
        return b == null ? -1 : getPosition(b);
    }

    public int getRowCountForAccessibility(Recycler recycler, State state) {
        if (this.j == 0) {
            return this.i;
        }
        return super.getRowCountForAccessibility(recycler, state);
    }

    public int getColumnCountForAccessibility(Recycler recycler, State state) {
        if (this.j == 1) {
            return this.i;
        }
        return super.getColumnCountForAccessibility(recycler, state);
    }

    View a(boolean z) {
        int startAfterPadding = this.b.getStartAfterPadding();
        int endAfterPadding = this.b.getEndAfterPadding();
        int childCount = getChildCount();
        View view = null;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int decoratedStart = this.b.getDecoratedStart(childAt);
            if (this.b.getDecoratedEnd(childAt) > startAfterPadding && decoratedStart < endAfterPadding) {
                if (decoratedStart >= startAfterPadding || !z) {
                    return childAt;
                }
                if (view == null) {
                    view = childAt;
                }
            }
        }
        return view;
    }

    View b(boolean z) {
        int startAfterPadding = this.b.getStartAfterPadding();
        int endAfterPadding = this.b.getEndAfterPadding();
        View view = null;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            int decoratedStart = this.b.getDecoratedStart(childAt);
            int decoratedEnd = this.b.getDecoratedEnd(childAt);
            if (decoratedEnd > startAfterPadding && decoratedStart < endAfterPadding) {
                if (decoratedEnd <= endAfterPadding || !z) {
                    return childAt;
                }
                if (view == null) {
                    view = childAt;
                }
            }
        }
        return view;
    }

    private void b(Recycler recycler, State state, boolean z) {
        int g = g(Integer.MIN_VALUE);
        if (g != Integer.MIN_VALUE) {
            g = this.b.getEndAfterPadding() - g;
            if (g > 0) {
                g -= -a(-g, recycler, state);
                if (z && g > 0) {
                    this.b.offsetChildren(g);
                }
            }
        }
    }

    private void c(Recycler recycler, State state, boolean z) {
        int f = f(Integer.MAX_VALUE);
        if (f != Integer.MAX_VALUE) {
            f -= this.b.getStartAfterPadding();
            if (f > 0) {
                f -= a(f, recycler, state);
                if (z && f > 0) {
                    this.b.offsetChildren(-f);
                }
            }
        }
    }

    private void b(int i, State state) {
        int targetScrollPosition;
        int i2;
        ba baVar;
        boolean z = false;
        this.l.b = 0;
        this.l.c = i;
        if (isSmoothScrolling()) {
            targetScrollPosition = state.getTargetScrollPosition();
            if (targetScrollPosition != -1) {
                if (this.e == (targetScrollPosition < i)) {
                    targetScrollPosition = this.b.getTotalSpace();
                    i2 = 0;
                } else {
                    i2 = this.b.getTotalSpace();
                    targetScrollPosition = 0;
                }
                if (getClipToPadding()) {
                    this.l.g = targetScrollPosition + this.b.getEnd();
                    this.l.f = -i2;
                } else {
                    this.l.f = this.b.getStartAfterPadding() - i2;
                    this.l.g = targetScrollPosition + this.b.getEndAfterPadding();
                }
                this.l.h = false;
                this.l.a = true;
                baVar = this.l;
                if (this.b.getMode() == 0 && this.b.getEnd() == 0) {
                    z = true;
                }
                baVar.i = z;
            }
        }
        targetScrollPosition = 0;
        i2 = 0;
        if (getClipToPadding()) {
            this.l.g = targetScrollPosition + this.b.getEnd();
            this.l.f = -i2;
        } else {
            this.l.f = this.b.getStartAfterPadding() - i2;
            this.l.g = targetScrollPosition + this.b.getEndAfterPadding();
        }
        this.l.h = false;
        this.l.a = true;
        baVar = this.l;
        z = true;
        baVar.i = z;
    }

    private void b(int i) {
        int i2 = 1;
        this.l.e = i;
        ba baVar = this.l;
        if (this.e != (i == -1)) {
            i2 = -1;
        }
        baVar.d = i2;
    }

    public void offsetChildrenHorizontal(int i) {
        super.offsetChildrenHorizontal(i);
        for (int i2 = 0; i2 < this.i; i2++) {
            this.a[i2].d(i);
        }
    }

    public void offsetChildrenVertical(int i) {
        super.offsetChildrenVertical(i);
        for (int i2 = 0; i2 < this.i; i2++) {
            this.a[i2].d(i);
        }
    }

    public void onItemsRemoved(RecyclerView recyclerView, int i, int i2) {
        b(i, i2, 2);
    }

    public void onItemsAdded(RecyclerView recyclerView, int i, int i2) {
        b(i, i2, 1);
    }

    public void onItemsChanged(RecyclerView recyclerView) {
        this.h.a();
        requestLayout();
    }

    public void onItemsMoved(RecyclerView recyclerView, int i, int i2, int i3) {
        b(i, i2, 8);
    }

    public void onItemsUpdated(RecyclerView recyclerView, int i, int i2, Object obj) {
        b(i, i2, 4);
    }

    private void b(int i, int i2, int i3) {
        int i4;
        int i5;
        int j = this.e ? j() : k();
        if (i3 != 8) {
            i4 = i + i2;
            i5 = i;
        } else if (i < i2) {
            i4 = i2 + 1;
            i5 = i;
        } else {
            i4 = i + 1;
            i5 = i2;
        }
        this.h.b(i5);
        switch (i3) {
            case 1:
                this.h.b(i, i2);
                break;
            case 2:
                this.h.a(i, i2);
                break;
            case 8:
                this.h.a(i, 1);
                this.h.b(i2, 1);
                break;
        }
        if (i4 > j) {
            if (i5 <= (this.e ? k() : j())) {
                requestLayout();
            }
        }
    }

    private int a(Recycler recycler, ba baVar, State state) {
        int i;
        int endAfterPadding;
        int g;
        this.m.set(0, this.i, true);
        if (this.l.i) {
            if (baVar.e == 1) {
                i = Integer.MAX_VALUE;
            } else {
                i = Integer.MIN_VALUE;
            }
        } else if (baVar.e == 1) {
            i = baVar.g + baVar.b;
        } else {
            i = baVar.f - baVar.b;
        }
        a(baVar.e, i);
        if (this.e) {
            endAfterPadding = this.b.getEndAfterPadding();
        } else {
            endAfterPadding = this.b.getStartAfterPadding();
        }
        Object obj = null;
        while (baVar.a(state) && (this.l.i || !this.m.isEmpty())) {
            b bVar;
            int decoratedMeasurement;
            int decoratedMeasurement2;
            View a = baVar.a(recycler);
            LayoutParams layoutParams = (LayoutParams) a.getLayoutParams();
            int viewLayoutPosition = layoutParams.getViewLayoutPosition();
            int c = this.h.c(viewLayoutPosition);
            Object obj2 = c == -1 ? 1 : null;
            if (obj2 != null) {
                b a2 = layoutParams.b ? this.a[0] : a(baVar);
                this.h.a(viewLayoutPosition, a2);
                bVar = a2;
            } else {
                bVar = this.a[c];
            }
            layoutParams.a = bVar;
            if (baVar.e == 1) {
                addView(a);
            } else {
                addView(a, 0);
            }
            a(a, layoutParams, false);
            if (baVar.e == 1) {
                if (layoutParams.b) {
                    g = g(endAfterPadding);
                } else {
                    g = bVar.b(endAfterPadding);
                }
                decoratedMeasurement = g + this.b.getDecoratedMeasurement(a);
                if (obj2 == null || !layoutParams.b) {
                    c = g;
                } else {
                    FullSpanItem c2 = c(g);
                    c2.b = -1;
                    c2.a = viewLayoutPosition;
                    this.h.addFullSpanItem(c2);
                    c = g;
                }
            } else {
                if (layoutParams.b) {
                    g = f(endAfterPadding);
                } else {
                    g = bVar.a(endAfterPadding);
                }
                c = g - this.b.getDecoratedMeasurement(a);
                if (obj2 != null && layoutParams.b) {
                    FullSpanItem d = d(g);
                    d.b = 1;
                    d.a = viewLayoutPosition;
                    this.h.addFullSpanItem(d);
                }
                decoratedMeasurement = g;
            }
            if (layoutParams.b && baVar.d == -1) {
                if (obj2 != null) {
                    this.E = true;
                } else {
                    obj = baVar.e == 1 ? !h() ? 1 : null : !i() ? 1 : null;
                    if (obj != null) {
                        FullSpanItem fullSpanItem = this.h.getFullSpanItem(viewLayoutPosition);
                        if (fullSpanItem != null) {
                            fullSpanItem.d = true;
                        }
                        this.E = true;
                    }
                }
            }
            a(a, layoutParams, baVar);
            if (c() && this.j == 1) {
                if (layoutParams.b) {
                    g = this.c.getEndAfterPadding();
                } else {
                    g = this.c.getEndAfterPadding() - (((this.i - 1) - bVar.e) * this.k);
                }
                decoratedMeasurement2 = g - this.c.getDecoratedMeasurement(a);
                viewLayoutPosition = g;
            } else {
                if (layoutParams.b) {
                    g = this.c.getStartAfterPadding();
                } else {
                    g = (bVar.e * this.k) + this.c.getStartAfterPadding();
                }
                viewLayoutPosition = g + this.c.getDecoratedMeasurement(a);
                decoratedMeasurement2 = g;
            }
            if (this.j == 1) {
                layoutDecoratedWithMargins(a, decoratedMeasurement2, c, viewLayoutPosition, decoratedMeasurement);
            } else {
                layoutDecoratedWithMargins(a, c, decoratedMeasurement2, decoratedMeasurement, viewLayoutPosition);
            }
            if (layoutParams.b) {
                a(this.l.e, i);
            } else {
                a(bVar, this.l.e, i);
            }
            a(recycler, this.l);
            if (this.l.h && a.hasFocusable()) {
                if (layoutParams.b) {
                    this.m.clear();
                } else {
                    this.m.set(bVar.e, false);
                }
            }
            obj = 1;
        }
        if (obj == null) {
            a(recycler, this.l);
        }
        if (this.l.e == -1) {
            g = this.b.getStartAfterPadding() - f(this.b.getStartAfterPadding());
        } else {
            g = g(this.b.getEndAfterPadding()) - this.b.getEndAfterPadding();
        }
        return g > 0 ? Math.min(baVar.b, g) : 0;
    }

    private FullSpanItem c(int i) {
        FullSpanItem fullSpanItem = new FullSpanItem();
        fullSpanItem.c = new int[this.i];
        for (int i2 = 0; i2 < this.i; i2++) {
            fullSpanItem.c[i2] = i - this.a[i2].b(i);
        }
        return fullSpanItem;
    }

    private FullSpanItem d(int i) {
        FullSpanItem fullSpanItem = new FullSpanItem();
        fullSpanItem.c = new int[this.i];
        for (int i2 = 0; i2 < this.i; i2++) {
            fullSpanItem.c[i2] = this.a[i2].a(i) - i;
        }
        return fullSpanItem;
    }

    private void a(View view, LayoutParams layoutParams, ba baVar) {
        if (baVar.e == 1) {
            if (layoutParams.b) {
                a(view);
            } else {
                layoutParams.a.b(view);
            }
        } else if (layoutParams.b) {
            b(view);
        } else {
            layoutParams.a.a(view);
        }
    }

    private void a(Recycler recycler, ba baVar) {
        if (baVar.a && !baVar.i) {
            if (baVar.b == 0) {
                if (baVar.e == -1) {
                    b(recycler, baVar.g);
                } else {
                    a(recycler, baVar.f);
                }
            } else if (baVar.e == -1) {
                r0 = baVar.f - e(baVar.f);
                if (r0 < 0) {
                    r0 = baVar.g;
                } else {
                    r0 = baVar.g - Math.min(r0, baVar.b);
                }
                b(recycler, r0);
            } else {
                r0 = h(baVar.g) - baVar.g;
                if (r0 < 0) {
                    r0 = baVar.f;
                } else {
                    r0 = Math.min(r0, baVar.b) + baVar.f;
                }
                a(recycler, r0);
            }
        }
    }

    private void a(View view) {
        for (int i = this.i - 1; i >= 0; i--) {
            this.a[i].b(view);
        }
    }

    private void b(View view) {
        for (int i = this.i - 1; i >= 0; i--) {
            this.a[i].a(view);
        }
    }

    private void a(int i, int i2) {
        for (int i3 = 0; i3 < this.i; i3++) {
            if (!this.a[i3].a.isEmpty()) {
                a(this.a[i3], i, i2);
            }
        }
    }

    private void a(b bVar, int i, int i2) {
        int deletedSize = bVar.getDeletedSize();
        if (i == -1) {
            if (deletedSize + bVar.b() <= i2) {
                this.m.set(bVar.e, false);
            }
        } else if (bVar.d() - deletedSize >= i2) {
            this.m.set(bVar.e, false);
        }
    }

    private int e(int i) {
        int a = this.a[0].a(i);
        for (int i2 = 1; i2 < this.i; i2++) {
            int a2 = this.a[i2].a(i);
            if (a2 > a) {
                a = a2;
            }
        }
        return a;
    }

    private int f(int i) {
        int a = this.a[0].a(i);
        for (int i2 = 1; i2 < this.i; i2++) {
            int a2 = this.a[i2].a(i);
            if (a2 < a) {
                a = a2;
            }
        }
        return a;
    }

    boolean h() {
        int b = this.a[0].b(Integer.MIN_VALUE);
        for (int i = 1; i < this.i; i++) {
            if (this.a[i].b(Integer.MIN_VALUE) != b) {
                return false;
            }
        }
        return true;
    }

    boolean i() {
        int a = this.a[0].a(Integer.MIN_VALUE);
        for (int i = 1; i < this.i; i++) {
            if (this.a[i].a(Integer.MIN_VALUE) != a) {
                return false;
            }
        }
        return true;
    }

    private int g(int i) {
        int b = this.a[0].b(i);
        for (int i2 = 1; i2 < this.i; i2++) {
            int b2 = this.a[i2].b(i);
            if (b2 > b) {
                b = b2;
            }
        }
        return b;
    }

    private int h(int i) {
        int b = this.a[0].b(i);
        for (int i2 = 1; i2 < this.i; i2++) {
            int b2 = this.a[i2].b(i);
            if (b2 < b) {
                b = b2;
            }
        }
        return b;
    }

    private void a(Recycler recycler, int i) {
        while (getChildCount() > 0) {
            View childAt = getChildAt(0);
            if (this.b.getDecoratedEnd(childAt) <= i && this.b.getTransformedEndWithDecoration(childAt) <= i) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.b) {
                    int i2 = 0;
                    while (i2 < this.i) {
                        if (this.a[i2].a.size() != 1) {
                            i2++;
                        } else {
                            return;
                        }
                    }
                    for (i2 = 0; i2 < this.i; i2++) {
                        this.a[i2].h();
                    }
                } else if (layoutParams.a.a.size() != 1) {
                    layoutParams.a.h();
                } else {
                    return;
                }
                removeAndRecycleView(childAt, recycler);
            } else {
                return;
            }
        }
    }

    private void b(Recycler recycler, int i) {
        int childCount = getChildCount() - 1;
        while (childCount >= 0) {
            View childAt = getChildAt(childCount);
            if (this.b.getDecoratedStart(childAt) >= i && this.b.getTransformedStartWithDecoration(childAt) >= i) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.b) {
                    int i2 = 0;
                    while (i2 < this.i) {
                        if (this.a[i2].a.size() != 1) {
                            i2++;
                        } else {
                            return;
                        }
                    }
                    for (i2 = 0; i2 < this.i; i2++) {
                        this.a[i2].g();
                    }
                } else if (layoutParams.a.a.size() != 1) {
                    layoutParams.a.g();
                } else {
                    return;
                }
                removeAndRecycleView(childAt, recycler);
                childCount--;
            } else {
                return;
            }
        }
    }

    private boolean i(int i) {
        if (this.j == 0) {
            boolean z;
            if (i == -1) {
                z = true;
            } else {
                z = false;
            }
            if (z != this.e) {
                return true;
            }
            return false;
        }
        if (((i == -1) == this.e) != c()) {
            return false;
        }
        return true;
    }

    private b a(ba baVar) {
        int i;
        int i2;
        b bVar = null;
        int i3 = -1;
        if (i(baVar.e)) {
            i = this.i - 1;
            i2 = -1;
        } else {
            i = 0;
            i2 = this.i;
            i3 = 1;
        }
        int startAfterPadding;
        int i4;
        b bVar2;
        int b;
        b bVar3;
        if (baVar.e == 1) {
            startAfterPadding = this.b.getStartAfterPadding();
            i4 = i;
            i = Integer.MAX_VALUE;
            while (i4 != i2) {
                bVar2 = this.a[i4];
                b = bVar2.b(startAfterPadding);
                if (b < i) {
                    bVar3 = bVar2;
                } else {
                    b = i;
                    bVar3 = bVar;
                }
                i4 += i3;
                bVar = bVar3;
                i = b;
            }
        } else {
            startAfterPadding = this.b.getEndAfterPadding();
            i4 = i;
            i = Integer.MIN_VALUE;
            while (i4 != i2) {
                bVar2 = this.a[i4];
                b = bVar2.a(startAfterPadding);
                if (b > i) {
                    bVar3 = bVar2;
                } else {
                    b = i;
                    bVar3 = bVar;
                }
                i4 += i3;
                bVar = bVar3;
                i = b;
            }
        }
        return bVar;
    }

    public boolean canScrollVertically() {
        return this.j == 1;
    }

    public boolean canScrollHorizontally() {
        return this.j == 0;
    }

    public int scrollHorizontallyBy(int i, Recycler recycler, State state) {
        return a(i, recycler, state);
    }

    public int scrollVerticallyBy(int i, Recycler recycler, State state) {
        return a(i, recycler, state);
    }

    private int j(int i) {
        int i2 = -1;
        if (getChildCount() != 0) {
            if ((i < k()) == this.e) {
                i2 = 1;
            }
            return i2;
        } else if (this.e) {
            return 1;
        } else {
            return -1;
        }
    }

    public PointF computeScrollVectorForPosition(int i) {
        int j = j(i);
        PointF pointF = new PointF();
        if (j == 0) {
            return null;
        }
        if (this.j == 0) {
            pointF.x = (float) j;
            pointF.y = 0.0f;
            return pointF;
        }
        pointF.x = 0.0f;
        pointF.y = (float) j;
        return pointF;
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, State state, int i) {
        SmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(i);
        startSmoothScroll(linearSmoothScroller);
    }

    public void scrollToPosition(int i) {
        if (!(this.A == null || this.A.a == i)) {
            this.A.b();
        }
        this.f = i;
        this.g = Integer.MIN_VALUE;
        requestLayout();
    }

    public void scrollToPositionWithOffset(int i, int i2) {
        if (this.A != null) {
            this.A.b();
        }
        this.f = i;
        this.g = i2;
        requestLayout();
    }

    public void collectAdjacentPrefetchPositions(int i, int i2, State state, LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int i3 = 0;
        if (this.j != 0) {
            i = i2;
        }
        if (getChildCount() != 0 && i != 0) {
            a(i, state);
            if (this.G == null || this.G.length < this.i) {
                this.G = new int[this.i];
            }
            int i4 = 0;
            for (int i5 = 0; i5 < this.i; i5++) {
                int a;
                if (this.l.d == -1) {
                    a = this.l.f - this.a[i5].a(this.l.f);
                } else {
                    a = this.a[i5].b(this.l.g) - this.l.g;
                }
                if (a >= 0) {
                    this.G[i4] = a;
                    i4++;
                }
            }
            Arrays.sort(this.G, 0, i4);
            while (i3 < i4 && this.l.a(state)) {
                layoutPrefetchRegistry.addPosition(this.l.c, this.G[i3]);
                ba baVar = this.l;
                baVar.c += this.l.d;
                i3++;
            }
        }
    }

    void a(int i, State state) {
        int j;
        int i2;
        if (i > 0) {
            j = j();
            i2 = 1;
        } else {
            i2 = -1;
            j = k();
        }
        this.l.a = true;
        b(j, state);
        b(i2);
        this.l.c = this.l.d + j;
        this.l.b = Math.abs(i);
    }

    int a(int i, Recycler recycler, State state) {
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        a(i, state);
        int a = a(recycler, this.l, state);
        if (this.l.b >= a) {
            i = i < 0 ? -a : a;
        }
        this.b.offsetChildren(-i);
        this.o = this.e;
        this.l.b = 0;
        a(recycler, this.l);
        return i;
    }

    int j() {
        int childCount = getChildCount();
        return childCount == 0 ? 0 : getPosition(getChildAt(childCount - 1));
    }

    int k() {
        if (getChildCount() == 0) {
            return 0;
        }
        return getPosition(getChildAt(0));
    }

    private int k(int i) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            int position = getPosition(getChildAt(i2));
            if (position >= 0 && position < i) {
                return position;
            }
        }
        return 0;
    }

    private int l(int i) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            int position = getPosition(getChildAt(childCount));
            if (position >= 0 && position < i) {
                return position;
            }
        }
        return 0;
    }

    public android.support.v7.widget.RecyclerView.LayoutParams generateDefaultLayoutParams() {
        if (this.j == 0) {
            return new LayoutParams(-2, -1);
        }
        return new LayoutParams(-1, -2);
    }

    public android.support.v7.widget.RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    public android.support.v7.widget.RecyclerView.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof MarginLayoutParams) {
            return new LayoutParams((MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    public boolean checkLayoutParams(android.support.v7.widget.RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public int getOrientation() {
        return this.j;
    }

    @Nullable
    public View onFocusSearchFailed(View view, int i, Recycler recycler, State state) {
        int i2 = 0;
        if (getChildCount() == 0) {
            return null;
        }
        View findContainingItemView = findContainingItemView(view);
        if (findContainingItemView == null) {
            return null;
        }
        m();
        int m = m(i);
        if (m == Integer.MIN_VALUE) {
            return null;
        }
        int j;
        View focusableViewAfter;
        int i3;
        View findViewByPosition;
        LayoutParams layoutParams = (LayoutParams) findContainingItemView.getLayoutParams();
        boolean z = layoutParams.b;
        b bVar = layoutParams.a;
        if (m == 1) {
            j = j();
        } else {
            j = k();
        }
        b(j, state);
        b(m);
        this.l.c = this.l.d + j;
        this.l.b = (int) (0.33333334f * ((float) this.b.getTotalSpace()));
        this.l.h = true;
        this.l.a = false;
        a(recycler, this.l, state);
        this.o = this.e;
        if (!z) {
            focusableViewAfter = bVar.getFocusableViewAfter(j, m);
            if (!(focusableViewAfter == null || focusableViewAfter == findContainingItemView)) {
                return focusableViewAfter;
            }
        }
        if (i(m)) {
            for (int i4 = this.i - 1; i4 >= 0; i4--) {
                focusableViewAfter = this.a[i4].getFocusableViewAfter(j, m);
                if (focusableViewAfter != null && focusableViewAfter != findContainingItemView) {
                    return focusableViewAfter;
                }
            }
        } else {
            for (i3 = 0; i3 < this.i; i3++) {
                View focusableViewAfter2 = this.a[i3].getFocusableViewAfter(j, m);
                if (focusableViewAfter2 != null && focusableViewAfter2 != findContainingItemView) {
                    return focusableViewAfter2;
                }
            }
        }
        boolean z2 = (!this.d) == (m == -1);
        if (!z) {
            if (z2) {
                j = bVar.findFirstPartiallyVisibleItemPosition();
            } else {
                j = bVar.findLastPartiallyVisibleItemPosition();
            }
            findViewByPosition = findViewByPosition(j);
            if (!(findViewByPosition == null || findViewByPosition == findContainingItemView)) {
                return findViewByPosition;
            }
        }
        if (i(m)) {
            for (i3 = this.i - 1; i3 >= 0; i3--) {
                if (i3 != bVar.e) {
                    if (z2) {
                        j = this.a[i3].findFirstPartiallyVisibleItemPosition();
                    } else {
                        j = this.a[i3].findLastPartiallyVisibleItemPosition();
                    }
                    findViewByPosition = findViewByPosition(j);
                    if (!(findViewByPosition == null || findViewByPosition == findContainingItemView)) {
                        return findViewByPosition;
                    }
                }
            }
        } else {
            while (i2 < this.i) {
                if (z2) {
                    j = this.a[i2].findFirstPartiallyVisibleItemPosition();
                } else {
                    j = this.a[i2].findLastPartiallyVisibleItemPosition();
                }
                findViewByPosition = findViewByPosition(j);
                if (findViewByPosition != null && findViewByPosition != findContainingItemView) {
                    return findViewByPosition;
                }
                i2++;
            }
        }
        return null;
    }

    private int m(int i) {
        int i2 = Integer.MIN_VALUE;
        int i3 = 1;
        switch (i) {
            case 1:
                if (this.j == 1 || !c()) {
                    return -1;
                }
                return 1;
            case 2:
                if (this.j == 1) {
                    return 1;
                }
                if (c()) {
                    return -1;
                }
                return 1;
            case 17:
                if (this.j != 0) {
                    return Integer.MIN_VALUE;
                }
                return -1;
            case 33:
                if (this.j != 1) {
                    return Integer.MIN_VALUE;
                }
                return -1;
            case 66:
                if (this.j != 0) {
                    i3 = Integer.MIN_VALUE;
                }
                return i3;
            case 130:
                if (this.j == 1) {
                    i2 = 1;
                }
                return i2;
            default:
                return Integer.MIN_VALUE;
        }
    }
}
