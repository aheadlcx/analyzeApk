package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewGroupCompat;
import android.support.v4.widget.Space;
import android.support.v7.gridlayout.R;
import android.util.AttributeSet;
import android.util.LogPrinter;
import android.util.Pair;
import android.util.Printer;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.xiaomi.mipush.sdk.Constants;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridLayout extends ViewGroup {
    public static final int ALIGN_BOUNDS = 0;
    public static final int ALIGN_MARGINS = 1;
    public static final Alignment BASELINE = new aw();
    public static final Alignment BOTTOM = t;
    public static final Alignment CENTER = new av();
    public static final Alignment END = t;
    public static final Alignment FILL = new ay();
    public static final int HORIZONTAL = 0;
    public static final Alignment LEFT = a(START, END);
    public static final Alignment RIGHT = a(END, START);
    public static final Alignment START = s;
    public static final Alignment TOP = s;
    public static final int UNDEFINED = Integer.MIN_VALUE;
    public static final int VERTICAL = 1;
    static final Printer a = new LogPrinter(3, GridLayout.class.getName());
    static final Printer b = new aq();
    static final Alignment k = new ar();
    private static final int l = R.styleable.GridLayout_orientation;
    private static final int m = R.styleable.GridLayout_rowCount;
    private static final int n = R.styleable.GridLayout_columnCount;
    private static final int o = R.styleable.GridLayout_useDefaultMargins;
    private static final int p = R.styleable.GridLayout_alignmentMode;
    private static final int q = R.styleable.GridLayout_rowOrderPreserved;
    private static final int r = R.styleable.GridLayout_columnOrderPreserved;
    private static final Alignment s = new as();
    private static final Alignment t = new at();
    final c c;
    final c d;
    int e;
    boolean f;
    int g;
    int h;
    int i;
    Printer j;

    public static abstract class Alignment {
        abstract int a(View view, int i);

        abstract String a();

        abstract int getAlignmentValue(View view, int i, int i2);

        Alignment() {
        }

        int getSizeInCell(View view, int i, int i2) {
            return i;
        }

        d getBounds() {
            return new d();
        }

        public String toString() {
            return "Alignment:" + a();
        }
    }

    public static class LayoutParams extends MarginLayoutParams {
        private static final e a = new e(Integer.MIN_VALUE, -2147483647);
        private static final int b = a.a();
        private static final int c = R.styleable.GridLayout_Layout_android_layout_margin;
        private static final int d = R.styleable.GridLayout_Layout_android_layout_marginLeft;
        private static final int e = R.styleable.GridLayout_Layout_android_layout_marginTop;
        private static final int f = R.styleable.GridLayout_Layout_android_layout_marginRight;
        private static final int g = R.styleable.GridLayout_Layout_android_layout_marginBottom;
        private static final int h = R.styleable.GridLayout_Layout_layout_column;
        private static final int i = R.styleable.GridLayout_Layout_layout_columnSpan;
        private static final int j = R.styleable.GridLayout_Layout_layout_columnWeight;
        private static final int k = R.styleable.GridLayout_Layout_layout_row;
        private static final int l = R.styleable.GridLayout_Layout_layout_rowSpan;
        private static final int m = R.styleable.GridLayout_Layout_layout_rowWeight;
        private static final int n = R.styleable.GridLayout_Layout_layout_gravity;
        public Spec columnSpec;
        public Spec rowSpec;

        private LayoutParams(int i, int i2, int i3, int i4, int i5, int i6, Spec spec, Spec spec2) {
            super(i, i2);
            this.rowSpec = Spec.a;
            this.columnSpec = Spec.a;
            setMargins(i3, i4, i5, i6);
            this.rowSpec = spec;
            this.columnSpec = spec2;
        }

        public LayoutParams(Spec spec, Spec spec2) {
            this(-2, -2, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, spec, spec2);
        }

        public LayoutParams() {
            this(Spec.a, Spec.a);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.rowSpec = Spec.a;
            this.columnSpec = Spec.a;
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.rowSpec = Spec.a;
            this.columnSpec = Spec.a;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.rowSpec = Spec.a;
            this.columnSpec = Spec.a;
            this.rowSpec = layoutParams.rowSpec;
            this.columnSpec = layoutParams.columnSpec;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.rowSpec = Spec.a;
            this.columnSpec = Spec.a;
            a(context, attributeSet);
            b(context, attributeSet);
        }

        private void a(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.GridLayout_Layout);
            try {
                int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(c, Integer.MIN_VALUE);
                this.leftMargin = obtainStyledAttributes.getDimensionPixelSize(d, dimensionPixelSize);
                this.topMargin = obtainStyledAttributes.getDimensionPixelSize(e, dimensionPixelSize);
                this.rightMargin = obtainStyledAttributes.getDimensionPixelSize(f, dimensionPixelSize);
                this.bottomMargin = obtainStyledAttributes.getDimensionPixelSize(g, dimensionPixelSize);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }

        private void b(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.GridLayout_Layout);
            try {
                int i = obtainStyledAttributes.getInt(n, 0);
                this.columnSpec = GridLayout.spec(obtainStyledAttributes.getInt(h, Integer.MIN_VALUE), obtainStyledAttributes.getInt(i, b), GridLayout.a(i, true), obtainStyledAttributes.getFloat(j, 0.0f));
                this.rowSpec = GridLayout.spec(obtainStyledAttributes.getInt(k, Integer.MIN_VALUE), obtainStyledAttributes.getInt(l, b), GridLayout.a(i, false), obtainStyledAttributes.getFloat(m, 0.0f));
            } finally {
                obtainStyledAttributes.recycle();
            }
        }

        public void setGravity(int i) {
            this.rowSpec = this.rowSpec.a(GridLayout.a(i, false));
            this.columnSpec = this.columnSpec.a(GridLayout.a(i, true));
        }

        protected void setBaseAttributes(TypedArray typedArray, int i, int i2) {
            this.width = typedArray.getLayoutDimension(i, -2);
            this.height = typedArray.getLayoutDimension(i2, -2);
        }

        final void a(e eVar) {
            this.rowSpec = this.rowSpec.a(eVar);
        }

        final void b(e eVar) {
            this.columnSpec = this.columnSpec.a(eVar);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            LayoutParams layoutParams = (LayoutParams) obj;
            if (!this.columnSpec.equals(layoutParams.columnSpec)) {
                return false;
            }
            if (this.rowSpec.equals(layoutParams.rowSpec)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (this.rowSpec.hashCode() * 31) + this.columnSpec.hashCode();
        }
    }

    public static class Spec {
        static final Spec a = GridLayout.spec(Integer.MIN_VALUE);
        final boolean b;
        final e c;
        final Alignment d;
        final float e;

        private Spec(boolean z, e eVar, Alignment alignment, float f) {
            this.b = z;
            this.c = eVar;
            this.d = alignment;
            this.e = f;
        }

        Spec(boolean z, int i, int i2, Alignment alignment, float f) {
            this(z, new e(i, i + i2), alignment, f);
        }

        public Alignment getAbsoluteAlignment(boolean z) {
            if (this.d != GridLayout.k) {
                return this.d;
            }
            if (this.e == 0.0f) {
                return z ? GridLayout.START : GridLayout.BASELINE;
            } else {
                return GridLayout.FILL;
            }
        }

        final Spec a(e eVar) {
            return new Spec(this.b, eVar, this.d, this.e);
        }

        final Spec a(Alignment alignment) {
            return new Spec(this.b, this.c, alignment, this.e);
        }

        final int a() {
            return (this.d == GridLayout.k && this.e == 0.0f) ? 0 : 2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Spec spec = (Spec) obj;
            if (!this.d.equals(spec.d)) {
                return false;
            }
            if (this.c.equals(spec.c)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (this.c.hashCode() * 31) + this.d.hashCode();
        }
    }

    static final class a {
        public final e span;
        public boolean valid = true;
        public final f value;

        public a(e eVar, f fVar) {
            this.span = eVar;
            this.value = fVar;
        }

        public String toString() {
            return this.span + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + (!this.valid ? "+>" : "->") + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.value;
        }
    }

    static final class b<K, V> extends ArrayList<Pair<K, V>> {
        private final Class<K> a;
        private final Class<V> b;

        private b(Class<K> cls, Class<V> cls2) {
            this.a = cls;
            this.b = cls2;
        }

        public static <K, V> b<K, V> of(Class<K> cls, Class<V> cls2) {
            return new b(cls, cls2);
        }

        public void put(K k, V v) {
            add(Pair.create(k, v));
        }

        public g<K, V> pack() {
            int size = size();
            Object[] objArr = (Object[]) Array.newInstance(this.a, size);
            Object[] objArr2 = (Object[]) Array.newInstance(this.b, size);
            for (int i = 0; i < size; i++) {
                objArr[i] = ((Pair) get(i)).first;
                objArr2[i] = ((Pair) get(i)).second;
            }
            return new g(objArr, objArr2);
        }
    }

    final class c {
        static final /* synthetic */ boolean e = (!GridLayout.class.desiredAssertionStatus());
        g<Spec, d> a;
        public a[] arcs;
        public boolean arcsValid = false;
        g<e, f> b;
        public boolean backwardLinksValid = false;
        g<e, f> c;
        boolean d = true;
        public int definedCount = Integer.MIN_VALUE;
        public int[] deltas;
        final /* synthetic */ GridLayout f;
        public boolean forwardLinksValid = false;
        private int g = Integer.MIN_VALUE;
        public boolean groupBoundsValid = false;
        private f h = new f(0);
        public boolean hasWeights;
        public boolean hasWeightsValid = false;
        public final boolean horizontal;
        private f i = new f(-100000);
        public int[] leadingMargins;
        public boolean leadingMarginsValid = false;
        public int[] locations;
        public boolean locationsValid = false;
        public int[] trailingMargins;
        public boolean trailingMarginsValid = false;

        c(GridLayout gridLayout, boolean z) {
            this.f = gridLayout;
            this.horizontal = z;
        }

        private int a() {
            int childCount = this.f.getChildCount();
            int i = -1;
            for (int i2 = 0; i2 < childCount; i2++) {
                LayoutParams a = this.f.a(this.f.getChildAt(i2));
                e eVar = (this.horizontal ? a.columnSpec : a.rowSpec).c;
                i = Math.max(Math.max(Math.max(i, eVar.min), eVar.max), eVar.a());
            }
            return i == -1 ? Integer.MIN_VALUE : i;
        }

        private int b() {
            if (this.g == Integer.MIN_VALUE) {
                this.g = Math.max(0, a());
            }
            return this.g;
        }

        public int getCount() {
            return Math.max(this.definedCount, b());
        }

        public void setCount(int i) {
            if (i != Integer.MIN_VALUE && i < b()) {
                GridLayout.a((this.horizontal ? "column" : "row") + "Count must be greater than or equal to the maximum of all grid indices " + "(and spans) defined in the LayoutParams of each child");
            }
            this.definedCount = i;
        }

        public boolean isOrderPreserved() {
            return this.d;
        }

        public void setOrderPreserved(boolean z) {
            this.d = z;
            invalidateStructure();
        }

        private g<Spec, d> c() {
            b of = b.of(Spec.class, d.class);
            int childCount = this.f.getChildCount();
            for (int i = 0; i < childCount; i++) {
                LayoutParams a = this.f.a(this.f.getChildAt(i));
                Spec spec = this.horizontal ? a.columnSpec : a.rowSpec;
                of.put(spec, spec.getAbsoluteAlignment(this.horizontal).getBounds());
            }
            return of.pack();
        }

        private void d() {
            int a;
            d[] dVarArr = (d[]) this.a.values;
            for (d a2 : dVarArr) {
                a2.a();
            }
            int childCount = this.f.getChildCount();
            for (int i = 0; i < childCount; i++) {
                int i2;
                View childAt = this.f.getChildAt(i);
                LayoutParams a3 = this.f.a(childAt);
                Spec spec = this.horizontal ? a3.columnSpec : a3.rowSpec;
                a = this.f.a(childAt, this.horizontal);
                if (spec.e == 0.0f) {
                    i2 = 0;
                } else {
                    i2 = getDeltas()[i];
                }
                ((d) this.a.getValue(i)).a(this.f, childAt, spec, this, a + i2);
            }
        }

        public g<Spec, d> getGroupBounds() {
            if (this.a == null) {
                this.a = c();
            }
            if (!this.groupBoundsValid) {
                d();
                this.groupBoundsValid = true;
            }
            return this.a;
        }

        private g<e, f> a(boolean z) {
            b of = b.of(e.class, f.class);
            Spec[] specArr = (Spec[]) getGroupBounds().keys;
            int length = specArr.length;
            for (int i = 0; i < length; i++) {
                of.put(z ? specArr[i].c : specArr[i].c.b(), new f());
            }
            return of.pack();
        }

        private void a(g<e, f> gVar, boolean z) {
            int i = 0;
            f[] fVarArr = (f[]) gVar.values;
            for (f reset : fVarArr) {
                reset.reset();
            }
            d[] dVarArr = (d[]) getGroupBounds().values;
            while (i < dVarArr.length) {
                int a = dVarArr[i].a(z);
                f fVar = (f) gVar.getValue(i);
                int i2 = fVar.value;
                if (!z) {
                    a = -a;
                }
                fVar.value = Math.max(i2, a);
                i++;
            }
        }

        private g<e, f> e() {
            if (this.b == null) {
                this.b = a(true);
            }
            if (!this.forwardLinksValid) {
                a(this.b, true);
                this.forwardLinksValid = true;
            }
            return this.b;
        }

        private g<e, f> f() {
            if (this.c == null) {
                this.c = a(false);
            }
            if (!this.backwardLinksValid) {
                a(this.c, false);
                this.backwardLinksValid = true;
            }
            return this.c;
        }

        private void a(List<a> list, e eVar, f fVar, boolean z) {
            if (eVar.a() != 0) {
                if (z) {
                    for (a aVar : list) {
                        if (aVar.span.equals(eVar)) {
                            return;
                        }
                    }
                }
                list.add(new a(eVar, fVar));
            }
        }

        private void a(List<a> list, e eVar, f fVar) {
            a(list, eVar, fVar, true);
        }

        a[][] a(a[] aVarArr) {
            int i = 0;
            int count = getCount() + 1;
            a[][] aVarArr2 = new a[count][];
            int[] iArr = new int[count];
            for (a aVar : aVarArr) {
                int i2 = aVar.span.min;
                iArr[i2] = iArr[i2] + 1;
            }
            for (count = 0; count < iArr.length; count++) {
                aVarArr2[count] = new a[iArr[count]];
            }
            Arrays.fill(iArr, 0);
            count = aVarArr.length;
            while (i < count) {
                a aVar2 = aVarArr[i];
                i2 = aVar2.span.min;
                a[] aVarArr3 = aVarArr2[i2];
                int i3 = iArr[i2];
                iArr[i2] = i3 + 1;
                aVarArr3[i3] = aVar2;
                i++;
            }
            return aVarArr2;
        }

        private a[] b(a[] aVarArr) {
            return new az(this, aVarArr).a();
        }

        private a[] a(List<a> list) {
            return b((a[]) list.toArray(new a[list.size()]));
        }

        private void a(List<a> list, g<e, f> gVar) {
            for (int i = 0; i < ((e[]) gVar.keys).length; i++) {
                a(list, ((e[]) gVar.keys)[i], ((f[]) gVar.values)[i], false);
            }
        }

        private a[] g() {
            int i;
            List arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            a(arrayList, e());
            a(arrayList2, f());
            if (this.d) {
                for (i = 0; i < getCount(); i++) {
                    a(arrayList, new e(i, i + 1), new f(0));
                }
            }
            i = getCount();
            a(arrayList, new e(0, i), this.h, false);
            a(arrayList2, new e(i, 0), this.i, false);
            return (a[]) GridLayout.a(a(arrayList), a(arrayList2));
        }

        private void h() {
            e();
            f();
        }

        public a[] getArcs() {
            if (this.arcs == null) {
                this.arcs = g();
            }
            if (!this.arcsValid) {
                h();
                this.arcsValid = true;
            }
            return this.arcs;
        }

        private boolean a(int[] iArr, a aVar) {
            if (!aVar.valid) {
                return false;
            }
            e eVar = aVar.span;
            int i = eVar.min;
            int i2 = eVar.max;
            i = iArr[i] + aVar.value.value;
            if (i <= iArr[i2]) {
                return false;
            }
            iArr[i2] = i;
            return true;
        }

        private void a(int[] iArr) {
            Arrays.fill(iArr, 0);
        }

        private String b(List<a> list) {
            String str = this.horizontal ? "x" : "y";
            StringBuilder stringBuilder = new StringBuilder();
            StringBuilder stringBuilder2 = stringBuilder;
            Object obj = 1;
            for (a aVar : list) {
                String str2;
                if (obj != null) {
                    obj = null;
                } else {
                    stringBuilder2 = stringBuilder2.append(", ");
                }
                int i = aVar.span.min;
                int i2 = aVar.span.max;
                int i3 = aVar.value.value;
                if (i < i2) {
                    str2 = str + i2 + Constants.ACCEPT_TIME_SEPARATOR_SERVER + str + i + ">=" + i3;
                } else {
                    str2 = str + i + Constants.ACCEPT_TIME_SEPARATOR_SERVER + str + i2 + "<=" + (-i3);
                }
                stringBuilder2.append(str2);
            }
            return stringBuilder2.toString();
        }

        private void a(String str, a[] aVarArr, boolean[] zArr) {
            List arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            for (int i = 0; i < aVarArr.length; i++) {
                a aVar = aVarArr[i];
                if (zArr[i]) {
                    arrayList.add(aVar);
                }
                if (!aVar.valid) {
                    arrayList2.add(aVar);
                }
            }
            this.f.j.println(str + " constraints: " + b(arrayList) + " are inconsistent; permanently removing: " + b(arrayList2) + ". ");
        }

        private boolean a(a[] aVarArr, int[] iArr) {
            return a(aVarArr, iArr, true);
        }

        private boolean a(a[] aVarArr, int[] iArr, boolean z) {
            String str = this.horizontal ? "horizontal" : "vertical";
            int count = getCount() + 1;
            boolean[] zArr = null;
            for (int i = 0; i < aVarArr.length; i++) {
                int i2;
                a(iArr);
                for (i2 = 0; i2 < count; i2++) {
                    int i3 = 0;
                    for (a a : aVarArr) {
                        i3 |= a(iArr, a);
                    }
                    if (i3 == 0) {
                        if (zArr != null) {
                            a(str, aVarArr, zArr);
                        }
                        return true;
                    }
                }
                if (!z) {
                    return false;
                }
                boolean[] zArr2 = new boolean[aVarArr.length];
                for (i2 = 0; i2 < count; i2++) {
                    int length = aVarArr.length;
                    for (i3 = 0; i3 < length; i3++) {
                        zArr2[i3] = zArr2[i3] | a(iArr, aVarArr[i3]);
                    }
                }
                if (i == 0) {
                    zArr = zArr2;
                }
                for (i3 = 0; i3 < aVarArr.length; i3++) {
                    if (zArr2[i3]) {
                        a aVar = aVarArr[i3];
                        if (aVar.span.min >= aVar.span.max) {
                            aVar.valid = false;
                            break;
                        }
                    }
                }
            }
            return true;
        }

        private void b(boolean z) {
            int[] iArr = z ? this.leadingMargins : this.trailingMargins;
            int childCount = this.f.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = this.f.getChildAt(i);
                if (childAt.getVisibility() != 8) {
                    LayoutParams a = this.f.a(childAt);
                    e eVar = (this.horizontal ? a.columnSpec : a.rowSpec).c;
                    int i2 = z ? eVar.min : eVar.max;
                    iArr[i2] = Math.max(iArr[i2], this.f.a(childAt, this.horizontal, z));
                }
            }
        }

        public int[] getLeadingMargins() {
            if (this.leadingMargins == null) {
                this.leadingMargins = new int[(getCount() + 1)];
            }
            if (!this.leadingMarginsValid) {
                b(true);
                this.leadingMarginsValid = true;
            }
            return this.leadingMargins;
        }

        public int[] getTrailingMargins() {
            if (this.trailingMargins == null) {
                this.trailingMargins = new int[(getCount() + 1)];
            }
            if (!this.trailingMarginsValid) {
                b(false);
                this.trailingMarginsValid = true;
            }
            return this.trailingMargins;
        }

        private boolean b(int[] iArr) {
            return a(getArcs(), iArr);
        }

        private boolean i() {
            int childCount = this.f.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = this.f.getChildAt(i);
                if (childAt.getVisibility() != 8) {
                    LayoutParams a = this.f.a(childAt);
                    if ((this.horizontal ? a.columnSpec : a.rowSpec).e != 0.0f) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean j() {
            if (!this.hasWeightsValid) {
                this.hasWeights = i();
                this.hasWeightsValid = true;
            }
            return this.hasWeights;
        }

        public int[] getDeltas() {
            if (this.deltas == null) {
                this.deltas = new int[this.f.getChildCount()];
            }
            return this.deltas;
        }

        private void a(int i, float f) {
            Arrays.fill(this.deltas, 0);
            int childCount = this.f.getChildCount();
            int i2 = 0;
            float f2 = f;
            int i3 = i;
            while (i2 < childCount) {
                float f3;
                int i4;
                View childAt = this.f.getChildAt(i2);
                if (childAt.getVisibility() == 8) {
                    f3 = f2;
                    i4 = i3;
                } else {
                    LayoutParams a = this.f.a(childAt);
                    f3 = (this.horizontal ? a.columnSpec : a.rowSpec).e;
                    if (f3 != 0.0f) {
                        int round = Math.round((((float) i3) * f3) / f2);
                        this.deltas[i2] = round;
                        f3 = f2 - f3;
                        i4 = i3 - round;
                    } else {
                        f3 = f2;
                        i4 = i3;
                    }
                }
                i2++;
                i3 = i4;
                f2 = f3;
            }
        }

        private void c(int[] iArr) {
            Arrays.fill(getDeltas(), 0);
            b(iArr);
            int childCount = (this.h.value * this.f.getChildCount()) + 1;
            if (childCount >= 2) {
                float k = k();
                int i = -1;
                boolean z = true;
                int i2 = 0;
                while (i2 < childCount) {
                    int i3 = (int) ((((long) i2) + ((long) childCount)) / 2);
                    invalidateValues();
                    a(i3, k);
                    boolean a = a(getArcs(), iArr, false);
                    if (a) {
                        i = i3 + 1;
                        i2 = childCount;
                    } else {
                        int i4 = i;
                        i = i2;
                        i2 = i3;
                        i3 = i4;
                    }
                    childCount = i2;
                    i2 = i;
                    i = i3;
                    z = a;
                }
                if (i > 0 && !r0) {
                    invalidateValues();
                    a(i, k);
                    b(iArr);
                }
            }
        }

        private float k() {
            float f = 0.0f;
            int childCount = this.f.getChildCount();
            int i = 0;
            while (i < childCount) {
                float f2;
                View childAt = this.f.getChildAt(i);
                if (childAt.getVisibility() == 8) {
                    f2 = f;
                } else {
                    LayoutParams a = this.f.a(childAt);
                    f2 = (this.horizontal ? a.columnSpec : a.rowSpec).e + f;
                }
                i++;
                f = f2;
            }
            return f;
        }

        private void d(int[] iArr) {
            int i = 0;
            if (j()) {
                c(iArr);
            } else {
                b(iArr);
            }
            if (!this.d) {
                int i2 = iArr[0];
                int length = iArr.length;
                while (i < length) {
                    iArr[i] = iArr[i] - i2;
                    i++;
                }
            }
        }

        public int[] getLocations() {
            if (this.locations == null) {
                this.locations = new int[(getCount() + 1)];
            }
            if (!this.locationsValid) {
                d(this.locations);
                this.locationsValid = true;
            }
            return this.locations;
        }

        private int e(int[] iArr) {
            return iArr[getCount()];
        }

        private void a(int i, int i2) {
            this.h.value = i;
            this.i.value = -i2;
            this.locationsValid = false;
        }

        private int b(int i, int i2) {
            a(i, i2);
            return e(getLocations());
        }

        public int getMeasure(int i) {
            int mode = MeasureSpec.getMode(i);
            int size = MeasureSpec.getSize(i);
            switch (mode) {
                case Integer.MIN_VALUE:
                    return b(0, size);
                case 0:
                    return b(0, 100000);
                case 1073741824:
                    return b(size, size);
                default:
                    if (e) {
                        return 0;
                    }
                    throw new AssertionError();
            }
        }

        public void layout(int i) {
            a(i, i);
            getLocations();
        }

        public void invalidateStructure() {
            this.g = Integer.MIN_VALUE;
            this.a = null;
            this.b = null;
            this.c = null;
            this.leadingMargins = null;
            this.trailingMargins = null;
            this.arcs = null;
            this.locations = null;
            this.deltas = null;
            this.hasWeightsValid = false;
            invalidateValues();
        }

        public void invalidateValues() {
            this.groupBoundsValid = false;
            this.forwardLinksValid = false;
            this.backwardLinksValid = false;
            this.leadingMarginsValid = false;
            this.trailingMarginsValid = false;
            this.arcsValid = false;
            this.locationsValid = false;
        }
    }

    static class d {
        public int after;
        public int before;
        public int flexibility;

        d() {
            a();
        }

        protected void a() {
            this.before = Integer.MIN_VALUE;
            this.after = Integer.MIN_VALUE;
            this.flexibility = 2;
        }

        protected void a(int i, int i2) {
            this.before = Math.max(this.before, i);
            this.after = Math.max(this.after, i2);
        }

        protected int a(boolean z) {
            if (z || !GridLayout.a(this.flexibility)) {
                return this.before + this.after;
            }
            return 100000;
        }

        protected int a(GridLayout gridLayout, View view, Alignment alignment, int i, boolean z) {
            return this.before - alignment.getAlignmentValue(view, i, ViewGroupCompat.getLayoutMode(gridLayout));
        }

        protected final void a(GridLayout gridLayout, View view, Spec spec, c cVar, int i) {
            this.flexibility &= spec.a();
            int alignmentValue = spec.getAbsoluteAlignment(cVar.horizontal).getAlignmentValue(view, i, ViewGroupCompat.getLayoutMode(gridLayout));
            a(alignmentValue, i - alignmentValue);
        }

        public String toString() {
            return "Bounds{before=" + this.before + ", after=" + this.after + '}';
        }
    }

    static final class e {
        public final int max;
        public final int min;

        public e(int i, int i2) {
            this.min = i;
            this.max = i2;
        }

        int a() {
            return this.max - this.min;
        }

        e b() {
            return new e(this.max, this.min);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            e eVar = (e) obj;
            if (this.max != eVar.max) {
                return false;
            }
            if (this.min != eVar.min) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (this.min * 31) + this.max;
        }

        public String toString() {
            return "[" + this.min + ", " + this.max + "]";
        }
    }

    static final class f {
        public int value;

        public f() {
            reset();
        }

        public f(int i) {
            this.value = i;
        }

        public void reset() {
            this.value = Integer.MIN_VALUE;
        }

        public String toString() {
            return Integer.toString(this.value);
        }
    }

    static final class g<K, V> {
        public final int[] index;
        public final K[] keys;
        public final V[] values;

        g(K[] kArr, V[] vArr) {
            this.index = a(kArr);
            this.keys = a(kArr, this.index);
            this.values = a(vArr, this.index);
        }

        public V getValue(int i) {
            return this.values[this.index[i]];
        }

        private static <K> int[] a(K[] kArr) {
            int length = kArr.length;
            int[] iArr = new int[length];
            Map hashMap = new HashMap();
            for (int i = 0; i < length; i++) {
                Object obj = kArr[i];
                Integer num = (Integer) hashMap.get(obj);
                if (num == null) {
                    num = Integer.valueOf(hashMap.size());
                    hashMap.put(obj, num);
                }
                iArr[i] = num.intValue();
            }
            return iArr;
        }

        private static <K> K[] a(K[] kArr, int[] iArr) {
            int length = kArr.length;
            Object[] objArr = (Object[]) Array.newInstance(kArr.getClass().getComponentType(), GridLayout.a(iArr, -1) + 1);
            for (int i = 0; i < length; i++) {
                objArr[iArr[i]] = kArr[i];
            }
            return objArr;
        }
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return a();
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return a(layoutParams);
    }

    public GridLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new c(this, true);
        this.d = new c(this, false);
        this.e = 0;
        this.f = false;
        this.g = 1;
        this.i = 0;
        this.j = a;
        this.h = context.getResources().getDimensionPixelOffset(R.dimen.default_gap);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.GridLayout);
        try {
            setRowCount(obtainStyledAttributes.getInt(m, Integer.MIN_VALUE));
            setColumnCount(obtainStyledAttributes.getInt(n, Integer.MIN_VALUE));
            setOrientation(obtainStyledAttributes.getInt(l, 0));
            setUseDefaultMargins(obtainStyledAttributes.getBoolean(o, false));
            setAlignmentMode(obtainStyledAttributes.getInt(p, 1));
            setRowOrderPreserved(obtainStyledAttributes.getBoolean(q, true));
            setColumnOrderPreserved(obtainStyledAttributes.getBoolean(r, true));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public GridLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GridLayout(Context context) {
        this(context, null);
    }

    public int getOrientation() {
        return this.e;
    }

    public void setOrientation(int i) {
        if (this.e != i) {
            this.e = i;
            d();
            requestLayout();
        }
    }

    public int getRowCount() {
        return this.d.getCount();
    }

    public void setRowCount(int i) {
        this.d.setCount(i);
        d();
        requestLayout();
    }

    public int getColumnCount() {
        return this.c.getCount();
    }

    public void setColumnCount(int i) {
        this.c.setCount(i);
        d();
        requestLayout();
    }

    public boolean getUseDefaultMargins() {
        return this.f;
    }

    public void setUseDefaultMargins(boolean z) {
        this.f = z;
        requestLayout();
    }

    public int getAlignmentMode() {
        return this.g;
    }

    public void setAlignmentMode(int i) {
        this.g = i;
        requestLayout();
    }

    public boolean isRowOrderPreserved() {
        return this.d.isOrderPreserved();
    }

    public void setRowOrderPreserved(boolean z) {
        this.d.setOrderPreserved(z);
        d();
        requestLayout();
    }

    public boolean isColumnOrderPreserved() {
        return this.c.isOrderPreserved();
    }

    public void setColumnOrderPreserved(boolean z) {
        this.c.setOrderPreserved(z);
        d();
        requestLayout();
    }

    public Printer getPrinter() {
        return this.j;
    }

    public void setPrinter(Printer printer) {
        if (printer == null) {
            printer = b;
        }
        this.j = printer;
    }

    static int a(int[] iArr, int i) {
        for (int max : iArr) {
            i = Math.max(i, max);
        }
        return i;
    }

    static <T> T[] a(T[] tArr, T[] tArr2) {
        Object[] objArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), tArr.length + tArr2.length);
        System.arraycopy(tArr, 0, objArr, 0, tArr.length);
        System.arraycopy(tArr2, 0, objArr, tArr.length, tArr2.length);
        return objArr;
    }

    static Alignment a(int i, boolean z) {
        switch (((z ? 7 : 112) & i) >> (z ? 0 : 4)) {
            case 1:
                return CENTER;
            case 3:
                return z ? LEFT : TOP;
            case 5:
                return z ? RIGHT : BOTTOM;
            case 7:
                return FILL;
            case GravityCompat.START /*8388611*/:
                return START;
            case GravityCompat.END /*8388613*/:
                return END;
            default:
                return k;
        }
    }

    private int b(View view, boolean z, boolean z2) {
        if (view.getClass() == Space.class) {
            return 0;
        }
        return this.h / 2;
    }

    private int a(View view, boolean z, boolean z2, boolean z3) {
        return b(view, z2, z3);
    }

    private int a(View view, LayoutParams layoutParams, boolean z, boolean z2) {
        if (!this.f) {
            return 0;
        }
        int i;
        Spec spec = z ? layoutParams.columnSpec : layoutParams.rowSpec;
        c cVar = z ? this.c : this.d;
        e eVar = spec.c;
        if (z && b()) {
            i = !z2 ? 1 : 0;
        } else {
            boolean z3 = z2;
        }
        boolean z4 = i != 0 ? eVar.min == 0 : eVar.max == cVar.getCount();
        return a(view, z4, z, z2);
    }

    int a(View view, boolean z, boolean z2) {
        LayoutParams a = a(view);
        int i = z ? z2 ? a.leftMargin : a.rightMargin : z2 ? a.topMargin : a.bottomMargin;
        return i == Integer.MIN_VALUE ? a(view, a, z, z2) : i;
    }

    private boolean b() {
        return ViewCompat.getLayoutDirection(this) == 1;
    }

    private int c(View view, boolean z, boolean z2) {
        if (this.g == 1) {
            return a(view, z, z2);
        }
        c cVar = z ? this.c : this.d;
        int[] leadingMargins = z2 ? cVar.getLeadingMargins() : cVar.getTrailingMargins();
        LayoutParams a = a(view);
        Spec spec = z ? a.columnSpec : a.rowSpec;
        return leadingMargins[z2 ? spec.c.min : spec.c.max];
    }

    private int b(View view, boolean z) {
        return c(view, z, true) + c(view, z, false);
    }

    private static boolean a(int[] iArr, int i, int i2, int i3) {
        if (i3 > iArr.length) {
            return false;
        }
        while (i2 < i3) {
            if (iArr[i2] > i) {
                return false;
            }
            i2++;
        }
        return true;
    }

    private static void b(int[] iArr, int i, int i2, int i3) {
        int length = iArr.length;
        Arrays.fill(iArr, Math.min(i, length), Math.min(i2, length), i3);
    }

    private static void a(LayoutParams layoutParams, int i, int i2, int i3, int i4) {
        layoutParams.a(new e(i, i + i2));
        layoutParams.b(new e(i3, i3 + i4));
    }

    private static int a(e eVar, boolean z, int i) {
        int a = eVar.a();
        if (i == 0) {
            return a;
        }
        return Math.min(a, i - (z ? Math.min(eVar.min, i) : 0));
    }

    private void c() {
        int i;
        Object obj = this.e == 0 ? 1 : null;
        c cVar = obj != null ? this.c : this.d;
        if (cVar.definedCount != Integer.MIN_VALUE) {
            i = cVar.definedCount;
        } else {
            i = 0;
        }
        int[] iArr = new int[i];
        int childCount = getChildCount();
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < childCount; i4++) {
            int i5;
            LayoutParams layoutParams = (LayoutParams) getChildAt(i4).getLayoutParams();
            Spec spec = obj != null ? layoutParams.rowSpec : layoutParams.columnSpec;
            e eVar = spec.c;
            boolean z = spec.b;
            int a = eVar.a();
            if (z) {
                i3 = eVar.min;
            }
            spec = obj != null ? layoutParams.columnSpec : layoutParams.rowSpec;
            eVar = spec.c;
            boolean z2 = spec.b;
            int a2 = a(eVar, z2, i);
            if (z2) {
                i5 = eVar.min;
            } else {
                i5 = i2;
            }
            if (i != 0) {
                if (!(z && z2)) {
                    while (!a(iArr, i3, i5, i5 + a2)) {
                        if (z2) {
                            i3++;
                        } else if (i5 + a2 <= i) {
                            i5++;
                        } else {
                            i3++;
                            i5 = 0;
                        }
                    }
                }
                b(iArr, i5, i5 + a2, i3 + a);
            }
            if (obj != null) {
                a(layoutParams, i3, a, i5, a2);
            } else {
                a(layoutParams, i5, a2, i3, a);
            }
            i2 = i5 + a2;
        }
    }

    private void d() {
        this.i = 0;
        if (this.c != null) {
            this.c.invalidateStructure();
        }
        if (this.d != null) {
            this.d.invalidateStructure();
        }
        e();
    }

    private void e() {
        if (this.c != null && this.d != null) {
            this.c.invalidateValues();
            this.d.invalidateValues();
        }
    }

    final LayoutParams a(View view) {
        return (LayoutParams) view.getLayoutParams();
    }

    static void a(String str) {
        throw new IllegalArgumentException(str + ". ");
    }

    private void a(LayoutParams layoutParams, boolean z) {
        String str = z ? "column" : "row";
        e eVar = (z ? layoutParams.columnSpec : layoutParams.rowSpec).c;
        if (eVar.min != Integer.MIN_VALUE && eVar.min < 0) {
            a(str + " indices must be positive");
        }
        int i = (z ? this.c : this.d).definedCount;
        if (i != Integer.MIN_VALUE) {
            if (eVar.max > i) {
                a(str + " indices (start + span) mustn't exceed the " + str + " count");
            }
            if (eVar.a() > i) {
                a(str + " span mustn't exceed the " + str + " count");
            }
        }
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        if (!(layoutParams instanceof LayoutParams)) {
            return false;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        a(layoutParams2, true);
        a(layoutParams2, false);
        return true;
    }

    protected LayoutParams a() {
        return new LayoutParams();
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected LayoutParams a(android.view.ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        if (layoutParams instanceof MarginLayoutParams) {
            return new LayoutParams((MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    private int f() {
        int i = 1;
        int childCount = getChildCount();
        int i2 = 0;
        while (i2 < childCount) {
            int i3;
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 8) {
                i3 = i;
            } else {
                i3 = ((LayoutParams) childAt.getLayoutParams()).hashCode() + (i * 31);
            }
            i2++;
            i = i3;
        }
        return i;
    }

    private void g() {
        if (this.i == 0) {
            c();
            this.i = f();
        } else if (this.i != f()) {
            this.j.println("The fields of some layout parameters were modified in between layout operations. Check the javadoc for GridLayout.LayoutParams#rowSpec.");
            d();
            g();
        }
    }

    private void a(View view, int i, int i2, int i3, int i4) {
        view.measure(getChildMeasureSpec(i, b(view, true), i3), getChildMeasureSpec(i2, b(view, false), i4));
    }

    private void a(int i, int i2, boolean z) {
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                LayoutParams a = a(childAt);
                if (z) {
                    a(childAt, i, i2, a.width, a.height);
                } else {
                    boolean z2 = this.e == 0;
                    Spec spec = z2 ? a.columnSpec : a.rowSpec;
                    if (spec.getAbsoluteAlignment(z2) == FILL) {
                        e eVar = spec.c;
                        int[] locations = (z2 ? this.c : this.d).getLocations();
                        int b = (locations[eVar.max] - locations[eVar.min]) - b(childAt, z2);
                        if (z2) {
                            a(childAt, i, i2, b, a.height);
                        } else {
                            a(childAt, i, i2, a.width, b);
                        }
                    }
                }
            }
        }
    }

    static int a(int i, int i2) {
        return MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(i + i2), MeasureSpec.getMode(i));
    }

    protected void onMeasure(int i, int i2) {
        int measure;
        int measure2;
        g();
        e();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int a = a(i, -paddingLeft);
        int a2 = a(i2, -paddingTop);
        a(a, a2, true);
        if (this.e == 0) {
            measure = this.c.getMeasure(a);
            a(a, a2, false);
            measure2 = this.d.getMeasure(a2);
        } else {
            measure2 = this.d.getMeasure(a2);
            a(a, a2, false);
            measure = this.c.getMeasure(a);
        }
        setMeasuredDimension(View.resolveSizeAndState(Math.max(measure + paddingLeft, getSuggestedMinimumWidth()), i, 0), View.resolveSizeAndState(Math.max(measure2 + paddingTop, getSuggestedMinimumHeight()), i2, 0));
    }

    private int c(View view, boolean z) {
        return z ? view.getMeasuredWidth() : view.getMeasuredHeight();
    }

    final int a(View view, boolean z) {
        if (view.getVisibility() == 8) {
            return 0;
        }
        return c(view, z) + b(view, z);
    }

    public void requestLayout() {
        super.requestLayout();
        d();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        g();
        int i5 = i3 - i;
        int i6 = i4 - i2;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        this.c.layout((i5 - paddingLeft) - paddingRight);
        this.d.layout((i6 - paddingTop) - paddingBottom);
        int[] locations = this.c.getLocations();
        int[] locations2 = this.d.getLocations();
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                LayoutParams a = a(childAt);
                Spec spec = a.columnSpec;
                Spec spec2 = a.rowSpec;
                e eVar = spec.c;
                e eVar2 = spec2.c;
                int i8 = locations[eVar.min];
                int i9 = locations2[eVar2.min];
                int i10 = locations[eVar.max] - i8;
                int i11 = locations2[eVar2.max] - i9;
                int c = c(childAt, true);
                int c2 = c(childAt, false);
                Alignment absoluteAlignment = spec.getAbsoluteAlignment(true);
                Alignment absoluteAlignment2 = spec2.getAbsoluteAlignment(false);
                d dVar = (d) this.c.getGroupBounds().getValue(i7);
                d dVar2 = (d) this.d.getGroupBounds().getValue(i7);
                int a2 = absoluteAlignment.a(childAt, i10 - dVar.a(true));
                int a3 = absoluteAlignment2.a(childAt, i11 - dVar2.a(true));
                int c3 = c(childAt, true, true);
                int c4 = c(childAt, false, true);
                int c5 = c(childAt, true, false);
                int i12 = c3 + c5;
                int c6 = c4 + c(childAt, false, false);
                i6 = dVar.a(this, childAt, absoluteAlignment, c + i12, true);
                paddingBottom = dVar2.a(this, childAt, absoluteAlignment2, c2 + c6, false);
                int sizeInCell = absoluteAlignment.getSizeInCell(childAt, c, i10 - i12);
                int sizeInCell2 = absoluteAlignment2.getSizeInCell(childAt, c2, i11 - c6);
                i6 += i8 + a2;
                i6 = !b() ? i6 + (paddingLeft + c3) : (((i5 - sizeInCell) - paddingRight) - c5) - i6;
                paddingBottom = (paddingBottom + ((paddingTop + i9) + a3)) + c4;
                if (!(sizeInCell == childAt.getMeasuredWidth() && sizeInCell2 == childAt.getMeasuredHeight())) {
                    childAt.measure(MeasureSpec.makeMeasureSpec(sizeInCell, 1073741824), MeasureSpec.makeMeasureSpec(sizeInCell2, 1073741824));
                }
                childAt.layout(i6, paddingBottom, sizeInCell + i6, sizeInCell2 + paddingBottom);
            }
        }
    }

    public static Spec spec(int i, int i2, Alignment alignment, float f) {
        return new Spec(i != Integer.MIN_VALUE, i, i2, alignment, f);
    }

    public static Spec spec(int i, Alignment alignment, float f) {
        return spec(i, 1, alignment, f);
    }

    public static Spec spec(int i, int i2, float f) {
        return spec(i, i2, k, f);
    }

    public static Spec spec(int i, float f) {
        return spec(i, 1, f);
    }

    public static Spec spec(int i, int i2, Alignment alignment) {
        return spec(i, i2, alignment, 0.0f);
    }

    public static Spec spec(int i, Alignment alignment) {
        return spec(i, 1, alignment);
    }

    public static Spec spec(int i, int i2) {
        return spec(i, i2, k);
    }

    public static Spec spec(int i) {
        return spec(i, 1);
    }

    private static Alignment a(Alignment alignment, Alignment alignment2) {
        return new au(alignment, alignment2);
    }

    static boolean a(int i) {
        return (i & 2) != 0;
    }
}
