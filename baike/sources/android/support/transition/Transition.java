package android.support.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Rect;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.InflateException;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import com.xiaomi.mipush.sdk.Constants;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public abstract class Transition implements Cloneable {
    public static final int MATCH_ID = 3;
    public static final int MATCH_INSTANCE = 1;
    public static final int MATCH_ITEM_ID = 4;
    public static final int MATCH_NAME = 2;
    private static final int[] g = new int[]{2, 1, 3, 4};
    private static final PathMotion h = new bf();
    private static ThreadLocal<ArrayMap<Animator, a>> z = new ThreadLocal();
    private ViewGroup A = null;
    private ArrayList<Animator> B = new ArrayList();
    private int C = 0;
    private boolean D = false;
    private boolean E = false;
    private ArrayList<TransitionListener> F = null;
    private ArrayList<Animator> G = new ArrayList();
    private EpicenterCallback H;
    private ArrayMap<String, String> I;
    private PathMotion J = h;
    long a = -1;
    ArrayList<Integer> b = new ArrayList();
    ArrayList<View> c = new ArrayList();
    TransitionSet d = null;
    boolean e = false;
    TransitionPropagation f;
    private String i = getClass().getName();
    private long j = -1;
    private TimeInterpolator k = null;
    private ArrayList<String> l = null;
    private ArrayList<Class> m = null;
    private ArrayList<Integer> n = null;
    private ArrayList<View> o = null;
    private ArrayList<Class> p = null;
    private ArrayList<String> q = null;
    private ArrayList<Integer> r = null;
    private ArrayList<View> s = null;
    private ArrayList<Class> t = null;
    private bl u = new bl();
    private bl v = new bl();
    private int[] w = g;
    private ArrayList<TransitionValues> x;
    private ArrayList<TransitionValues> y;

    public interface TransitionListener {
        void onTransitionCancel(@NonNull Transition transition);

        void onTransitionEnd(@NonNull Transition transition);

        void onTransitionPause(@NonNull Transition transition);

        void onTransitionResume(@NonNull Transition transition);

        void onTransitionStart(@NonNull Transition transition);
    }

    public static abstract class EpicenterCallback {
        public abstract Rect onGetEpicenter(@NonNull Transition transition);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MatchOrder {
    }

    private static class a {
        View a;
        String b;
        TransitionValues c;
        cl d;
        Transition e;

        a(View view, String str, Transition transition, cl clVar, TransitionValues transitionValues) {
            this.a = view;
            this.b = str;
            this.c = transitionValues;
            this.d = clVar;
            this.e = transition;
        }
    }

    private static class b {
        static <T> ArrayList<T> a(ArrayList<T> arrayList, T t) {
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            if (!arrayList.contains(t)) {
                arrayList.add(t);
            }
            return arrayList;
        }

        static <T> ArrayList<T> b(ArrayList<T> arrayList, T t) {
            if (arrayList == null) {
                return arrayList;
            }
            arrayList.remove(t);
            if (arrayList.isEmpty()) {
                return null;
            }
            return arrayList;
        }
    }

    public abstract void captureEndValues(@NonNull TransitionValues transitionValues);

    public abstract void captureStartValues(@NonNull TransitionValues transitionValues);

    public Transition(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, be.c);
        XmlResourceParser xmlResourceParser = (XmlResourceParser) attributeSet;
        long namedInt = (long) TypedArrayUtils.getNamedInt(obtainStyledAttributes, xmlResourceParser, "duration", 1, -1);
        if (namedInt >= 0) {
            setDuration(namedInt);
        }
        namedInt = (long) TypedArrayUtils.getNamedInt(obtainStyledAttributes, xmlResourceParser, "startDelay", 2, -1);
        if (namedInt > 0) {
            setStartDelay(namedInt);
        }
        int namedResourceId = TypedArrayUtils.getNamedResourceId(obtainStyledAttributes, xmlResourceParser, "interpolator", 0, 0);
        if (namedResourceId > 0) {
            setInterpolator(AnimationUtils.loadInterpolator(context, namedResourceId));
        }
        String namedString = TypedArrayUtils.getNamedString(obtainStyledAttributes, xmlResourceParser, "matchOrder", 3);
        if (namedString != null) {
            setMatchOrder(b(namedString));
        }
        obtainStyledAttributes.recycle();
    }

    private static int[] b(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, Constants.ACCEPT_TIME_SEPARATOR_SP);
        Object obj = new int[stringTokenizer.countTokens()];
        int i = 0;
        while (stringTokenizer.hasMoreTokens()) {
            String trim = stringTokenizer.nextToken().trim();
            if ("id".equalsIgnoreCase(trim)) {
                obj[i] = 3;
            } else if ("instance".equalsIgnoreCase(trim)) {
                obj[i] = 1;
            } else if ("name".equalsIgnoreCase(trim)) {
                obj[i] = 2;
            } else if ("itemId".equalsIgnoreCase(trim)) {
                obj[i] = 4;
            } else if (trim.isEmpty()) {
                Object obj2 = new int[(obj.length - 1)];
                System.arraycopy(obj, 0, obj2, 0, i);
                i--;
                obj = obj2;
            } else {
                throw new InflateException("Unknown match type in matchOrder: '" + trim + "'");
            }
            i++;
        }
        return obj;
    }

    @NonNull
    public Transition setDuration(long j) {
        this.a = j;
        return this;
    }

    public long getDuration() {
        return this.a;
    }

    @NonNull
    public Transition setStartDelay(long j) {
        this.j = j;
        return this;
    }

    public long getStartDelay() {
        return this.j;
    }

    @NonNull
    public Transition setInterpolator(@Nullable TimeInterpolator timeInterpolator) {
        this.k = timeInterpolator;
        return this;
    }

    @Nullable
    public TimeInterpolator getInterpolator() {
        return this.k;
    }

    @Nullable
    public String[] getTransitionProperties() {
        return null;
    }

    @Nullable
    public Animator createAnimator(@NonNull ViewGroup viewGroup, @Nullable TransitionValues transitionValues, @Nullable TransitionValues transitionValues2) {
        return null;
    }

    public void setMatchOrder(int... iArr) {
        if (iArr == null || iArr.length == 0) {
            this.w = g;
            return;
        }
        int i = 0;
        while (i < iArr.length) {
            if (!a(iArr[i])) {
                throw new IllegalArgumentException("matches contains invalid value");
            } else if (a(iArr, i)) {
                throw new IllegalArgumentException("matches contains a duplicate value");
            } else {
                i++;
            }
        }
        this.w = (int[]) iArr.clone();
    }

    private static boolean a(int i) {
        return i >= 1 && i <= 4;
    }

    private static boolean a(int[] iArr, int i) {
        int i2 = iArr[i];
        for (int i3 = 0; i3 < i; i3++) {
            if (iArr[i3] == i2) {
                return true;
            }
        }
        return false;
    }

    private void a(ArrayMap<View, TransitionValues> arrayMap, ArrayMap<View, TransitionValues> arrayMap2) {
        for (int size = arrayMap.size() - 1; size >= 0; size--) {
            View view = (View) arrayMap.keyAt(size);
            if (view != null && b(view)) {
                TransitionValues transitionValues = (TransitionValues) arrayMap2.remove(view);
                if (!(transitionValues == null || transitionValues.view == null || !b(transitionValues.view))) {
                    this.x.add((TransitionValues) arrayMap.removeAt(size));
                    this.y.add(transitionValues);
                }
            }
        }
    }

    private void a(ArrayMap<View, TransitionValues> arrayMap, ArrayMap<View, TransitionValues> arrayMap2, LongSparseArray<View> longSparseArray, LongSparseArray<View> longSparseArray2) {
        int size = longSparseArray.size();
        for (int i = 0; i < size; i++) {
            View view = (View) longSparseArray.valueAt(i);
            if (view != null && b(view)) {
                View view2 = (View) longSparseArray2.get(longSparseArray.keyAt(i));
                if (view2 != null && b(view2)) {
                    TransitionValues transitionValues = (TransitionValues) arrayMap.get(view);
                    TransitionValues transitionValues2 = (TransitionValues) arrayMap2.get(view2);
                    if (!(transitionValues == null || transitionValues2 == null)) {
                        this.x.add(transitionValues);
                        this.y.add(transitionValues2);
                        arrayMap.remove(view);
                        arrayMap2.remove(view2);
                    }
                }
            }
        }
    }

    private void a(ArrayMap<View, TransitionValues> arrayMap, ArrayMap<View, TransitionValues> arrayMap2, SparseArray<View> sparseArray, SparseArray<View> sparseArray2) {
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            View view = (View) sparseArray.valueAt(i);
            if (view != null && b(view)) {
                View view2 = (View) sparseArray2.get(sparseArray.keyAt(i));
                if (view2 != null && b(view2)) {
                    TransitionValues transitionValues = (TransitionValues) arrayMap.get(view);
                    TransitionValues transitionValues2 = (TransitionValues) arrayMap2.get(view2);
                    if (!(transitionValues == null || transitionValues2 == null)) {
                        this.x.add(transitionValues);
                        this.y.add(transitionValues2);
                        arrayMap.remove(view);
                        arrayMap2.remove(view2);
                    }
                }
            }
        }
    }

    private void a(ArrayMap<View, TransitionValues> arrayMap, ArrayMap<View, TransitionValues> arrayMap2, ArrayMap<String, View> arrayMap3, ArrayMap<String, View> arrayMap4) {
        int size = arrayMap3.size();
        for (int i = 0; i < size; i++) {
            View view = (View) arrayMap3.valueAt(i);
            if (view != null && b(view)) {
                View view2 = (View) arrayMap4.get(arrayMap3.keyAt(i));
                if (view2 != null && b(view2)) {
                    TransitionValues transitionValues = (TransitionValues) arrayMap.get(view);
                    TransitionValues transitionValues2 = (TransitionValues) arrayMap2.get(view2);
                    if (!(transitionValues == null || transitionValues2 == null)) {
                        this.x.add(transitionValues);
                        this.y.add(transitionValues2);
                        arrayMap.remove(view);
                        arrayMap2.remove(view2);
                    }
                }
            }
        }
    }

    private void b(ArrayMap<View, TransitionValues> arrayMap, ArrayMap<View, TransitionValues> arrayMap2) {
        int i = 0;
        for (int i2 = 0; i2 < arrayMap.size(); i2++) {
            TransitionValues transitionValues = (TransitionValues) arrayMap.valueAt(i2);
            if (b(transitionValues.view)) {
                this.x.add(transitionValues);
                this.y.add(null);
            }
        }
        while (i < arrayMap2.size()) {
            transitionValues = (TransitionValues) arrayMap2.valueAt(i);
            if (b(transitionValues.view)) {
                this.y.add(transitionValues);
                this.x.add(null);
            }
            i++;
        }
    }

    private void a(bl blVar, bl blVar2) {
        ArrayMap arrayMap = new ArrayMap(blVar.a);
        ArrayMap arrayMap2 = new ArrayMap(blVar2.a);
        for (int i : this.w) {
            switch (i) {
                case 1:
                    a(arrayMap, arrayMap2);
                    break;
                case 2:
                    a(arrayMap, arrayMap2, blVar.d, blVar2.d);
                    break;
                case 3:
                    a(arrayMap, arrayMap2, blVar.b, blVar2.b);
                    break;
                case 4:
                    a(arrayMap, arrayMap2, blVar.c, blVar2.c);
                    break;
                default:
                    break;
            }
        }
        b(arrayMap, arrayMap2);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    protected void a(ViewGroup viewGroup, bl blVar, bl blVar2, ArrayList<TransitionValues> arrayList, ArrayList<TransitionValues> arrayList2) {
        int i;
        ArrayMap d = d();
        long j = Long.MAX_VALUE;
        SparseIntArray sparseIntArray = new SparseIntArray();
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            TransitionValues transitionValues;
            TransitionValues transitionValues2;
            TransitionValues transitionValues3 = (TransitionValues) arrayList.get(i2);
            TransitionValues transitionValues4 = (TransitionValues) arrayList2.get(i2);
            if (transitionValues3 == null || transitionValues3.a.contains(this)) {
                transitionValues = transitionValues3;
            } else {
                transitionValues = null;
            }
            if (transitionValues4 == null || transitionValues4.a.contains(this)) {
                transitionValues2 = transitionValues4;
            } else {
                transitionValues2 = null;
            }
            if (transitionValues != null || transitionValues2 != null) {
                Object obj = (transitionValues == null || transitionValues2 == null || isTransitionRequired(transitionValues, transitionValues2)) ? 1 : null;
                if (obj != null) {
                    Animator createAnimator = createAnimator(viewGroup, transitionValues, transitionValues2);
                    if (createAnimator != null) {
                        TransitionValues transitionValues5;
                        Object obj2;
                        View view;
                        transitionValues3 = null;
                        if (transitionValues2 != null) {
                            Object obj3;
                            View view2 = transitionValues2.view;
                            String[] transitionProperties = getTransitionProperties();
                            Animator animator;
                            if (view2 == null || transitionProperties == null || transitionProperties.length <= 0) {
                                animator = createAnimator;
                            } else {
                                TransitionValues transitionValues6 = new TransitionValues();
                                transitionValues6.view = view2;
                                transitionValues3 = (TransitionValues) blVar2.a.get(view2);
                                if (transitionValues3 != null) {
                                    for (i = 0; i < transitionProperties.length; i++) {
                                        transitionValues6.values.put(transitionProperties[i], transitionValues3.values.get(transitionProperties[i]));
                                    }
                                }
                                int size2 = d.size();
                                for (i = 0; i < size2; i++) {
                                    a aVar = (a) d.get((Animator) d.keyAt(i));
                                    if (aVar.c != null && aVar.a == view2 && aVar.b.equals(getName()) && aVar.c.equals(transitionValues6)) {
                                        obj3 = null;
                                        transitionValues3 = transitionValues6;
                                        break;
                                    }
                                }
                                transitionValues3 = transitionValues6;
                                animator = createAnimator;
                            }
                            transitionValues5 = transitionValues3;
                            obj2 = obj3;
                            view = view2;
                        } else {
                            view = transitionValues.view;
                            transitionValues5 = null;
                            Animator animator2 = createAnimator;
                        }
                        if (obj2 != null) {
                            if (this.f != null) {
                                long startDelay = this.f.getStartDelay(viewGroup, this, transitionValues, transitionValues2);
                                sparseIntArray.put(this.G.size(), (int) startDelay);
                                j = Math.min(startDelay, j);
                            }
                            d.put(obj2, new a(view, getName(), this, bz.b(viewGroup), transitionValues5));
                            this.G.add(obj2);
                        }
                    }
                }
            }
        }
        if (j != 0) {
            for (i = 0; i < sparseIntArray.size(); i++) {
                Animator animator3 = (Animator) this.G.get(sparseIntArray.keyAt(i));
                animator3.setStartDelay((((long) sparseIntArray.valueAt(i)) - j) + animator3.getStartDelay());
            }
        }
    }

    boolean b(View view) {
        int id = view.getId();
        if (this.n != null && this.n.contains(Integer.valueOf(id))) {
            return false;
        }
        if (this.o != null && this.o.contains(view)) {
            return false;
        }
        int i;
        if (this.p != null) {
            int size = this.p.size();
            for (i = 0; i < size; i++) {
                if (((Class) this.p.get(i)).isInstance(view)) {
                    return false;
                }
            }
        }
        if (this.q != null && ViewCompat.getTransitionName(view) != null && this.q.contains(ViewCompat.getTransitionName(view))) {
            return false;
        }
        if (this.b.size() == 0 && this.c.size() == 0 && ((this.m == null || this.m.isEmpty()) && (this.l == null || this.l.isEmpty()))) {
            return true;
        }
        if (this.b.contains(Integer.valueOf(id)) || this.c.contains(view)) {
            return true;
        }
        if (this.l != null && this.l.contains(ViewCompat.getTransitionName(view))) {
            return true;
        }
        if (this.m == null) {
            return false;
        }
        for (i = 0; i < this.m.size(); i++) {
            if (((Class) this.m.get(i)).isInstance(view)) {
                return true;
            }
        }
        return false;
    }

    private static ArrayMap<Animator, a> d() {
        ArrayMap<Animator, a> arrayMap = (ArrayMap) z.get();
        if (arrayMap != null) {
            return arrayMap;
        }
        arrayMap = new ArrayMap();
        z.set(arrayMap);
        return arrayMap;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    protected void a() {
        b();
        ArrayMap d = d();
        Iterator it = this.G.iterator();
        while (it.hasNext()) {
            Animator animator = (Animator) it.next();
            if (d.containsKey(animator)) {
                b();
                a(animator, d);
            }
        }
        this.G.clear();
        c();
    }

    private void a(Animator animator, ArrayMap<Animator, a> arrayMap) {
        if (animator != null) {
            animator.addListener(new bg(this, arrayMap));
            a(animator);
        }
    }

    @NonNull
    public Transition addTarget(@NonNull View view) {
        this.c.add(view);
        return this;
    }

    @NonNull
    public Transition addTarget(@IdRes int i) {
        if (i > 0) {
            this.b.add(Integer.valueOf(i));
        }
        return this;
    }

    @NonNull
    public Transition addTarget(@NonNull String str) {
        if (this.l == null) {
            this.l = new ArrayList();
        }
        this.l.add(str);
        return this;
    }

    @NonNull
    public Transition addTarget(@NonNull Class cls) {
        if (this.m == null) {
            this.m = new ArrayList();
        }
        this.m.add(cls);
        return this;
    }

    @NonNull
    public Transition removeTarget(@NonNull View view) {
        this.c.remove(view);
        return this;
    }

    @NonNull
    public Transition removeTarget(@IdRes int i) {
        if (i > 0) {
            this.b.remove(Integer.valueOf(i));
        }
        return this;
    }

    @NonNull
    public Transition removeTarget(@NonNull String str) {
        if (this.l != null) {
            this.l.remove(str);
        }
        return this;
    }

    @NonNull
    public Transition removeTarget(@NonNull Class cls) {
        if (this.m != null) {
            this.m.remove(cls);
        }
        return this;
    }

    private static <T> ArrayList<T> a(ArrayList<T> arrayList, T t, boolean z) {
        if (t == null) {
            return arrayList;
        }
        if (z) {
            return b.a(arrayList, t);
        }
        return b.b(arrayList, t);
    }

    @NonNull
    public Transition excludeTarget(@NonNull View view, boolean z) {
        this.o = a(this.o, view, z);
        return this;
    }

    @NonNull
    public Transition excludeTarget(@IdRes int i, boolean z) {
        this.n = a(this.n, i, z);
        return this;
    }

    @NonNull
    public Transition excludeTarget(@NonNull String str, boolean z) {
        this.q = a(this.q, (Object) str, z);
        return this;
    }

    @NonNull
    public Transition excludeChildren(@NonNull View view, boolean z) {
        this.s = a(this.s, view, z);
        return this;
    }

    @NonNull
    public Transition excludeChildren(@IdRes int i, boolean z) {
        this.r = a(this.r, i, z);
        return this;
    }

    private ArrayList<Integer> a(ArrayList<Integer> arrayList, int i, boolean z) {
        if (i <= 0) {
            return arrayList;
        }
        if (z) {
            return b.a(arrayList, Integer.valueOf(i));
        }
        return b.b(arrayList, Integer.valueOf(i));
    }

    private ArrayList<View> a(ArrayList<View> arrayList, View view, boolean z) {
        if (view == null) {
            return arrayList;
        }
        if (z) {
            return b.a(arrayList, view);
        }
        return b.b(arrayList, view);
    }

    @NonNull
    public Transition excludeTarget(@NonNull Class cls, boolean z) {
        this.p = a(this.p, cls, z);
        return this;
    }

    @NonNull
    public Transition excludeChildren(@NonNull Class cls, boolean z) {
        this.t = a(this.t, cls, z);
        return this;
    }

    private ArrayList<Class> a(ArrayList<Class> arrayList, Class cls, boolean z) {
        if (cls == null) {
            return arrayList;
        }
        if (z) {
            return b.a(arrayList, cls);
        }
        return b.b(arrayList, cls);
    }

    @NonNull
    public List<Integer> getTargetIds() {
        return this.b;
    }

    @NonNull
    public List<View> getTargets() {
        return this.c;
    }

    @Nullable
    public List<String> getTargetNames() {
        return this.l;
    }

    @Nullable
    public List<Class> getTargetTypes() {
        return this.m;
    }

    void a(ViewGroup viewGroup, boolean z) {
        int i;
        View findViewById;
        int i2 = 0;
        a(z);
        if ((this.b.size() > 0 || this.c.size() > 0) && ((this.l == null || this.l.isEmpty()) && (this.m == null || this.m.isEmpty()))) {
            TransitionValues transitionValues;
            for (i = 0; i < this.b.size(); i++) {
                findViewById = viewGroup.findViewById(((Integer) this.b.get(i)).intValue());
                if (findViewById != null) {
                    transitionValues = new TransitionValues();
                    transitionValues.view = findViewById;
                    if (z) {
                        captureStartValues(transitionValues);
                    } else {
                        captureEndValues(transitionValues);
                    }
                    transitionValues.a.add(this);
                    a(transitionValues);
                    if (z) {
                        a(this.u, findViewById, transitionValues);
                    } else {
                        a(this.v, findViewById, transitionValues);
                    }
                }
            }
            for (i = 0; i < this.c.size(); i++) {
                findViewById = (View) this.c.get(i);
                transitionValues = new TransitionValues();
                transitionValues.view = findViewById;
                if (z) {
                    captureStartValues(transitionValues);
                } else {
                    captureEndValues(transitionValues);
                }
                transitionValues.a.add(this);
                a(transitionValues);
                if (z) {
                    a(this.u, findViewById, transitionValues);
                } else {
                    a(this.v, findViewById, transitionValues);
                }
            }
        } else {
            b((View) viewGroup, z);
        }
        if (!z && this.I != null) {
            int size = this.I.size();
            ArrayList arrayList = new ArrayList(size);
            for (i = 0; i < size; i++) {
                arrayList.add(this.u.d.remove((String) this.I.keyAt(i)));
            }
            while (i2 < size) {
                findViewById = (View) arrayList.get(i2);
                if (findViewById != null) {
                    this.u.d.put((String) this.I.valueAt(i2), findViewById);
                }
                i2++;
            }
        }
    }

    private static void a(bl blVar, View view, TransitionValues transitionValues) {
        blVar.a.put(view, transitionValues);
        int id = view.getId();
        if (id >= 0) {
            if (blVar.b.indexOfKey(id) >= 0) {
                blVar.b.put(id, null);
            } else {
                blVar.b.put(id, view);
            }
        }
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            if (blVar.d.containsKey(transitionName)) {
                blVar.d.put(transitionName, null);
            } else {
                blVar.d.put(transitionName, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                if (blVar.c.indexOfKey(itemIdAtPosition) >= 0) {
                    View view2 = (View) blVar.c.get(itemIdAtPosition);
                    if (view2 != null) {
                        ViewCompat.setHasTransientState(view2, false);
                        blVar.c.put(itemIdAtPosition, null);
                        return;
                    }
                    return;
                }
                ViewCompat.setHasTransientState(view, true);
                blVar.c.put(itemIdAtPosition, view);
            }
        }
    }

    void a(boolean z) {
        if (z) {
            this.u.a.clear();
            this.u.b.clear();
            this.u.c.clear();
            return;
        }
        this.v.a.clear();
        this.v.b.clear();
        this.v.c.clear();
    }

    private void b(View view, boolean z) {
        if (view != null) {
            int id = view.getId();
            if (this.n != null && this.n.contains(Integer.valueOf(id))) {
                return;
            }
            if (this.o == null || !this.o.contains(view)) {
                int i;
                if (this.p != null) {
                    int size = this.p.size();
                    i = 0;
                    while (i < size) {
                        if (!((Class) this.p.get(i)).isInstance(view)) {
                            i++;
                        } else {
                            return;
                        }
                    }
                }
                if (view.getParent() instanceof ViewGroup) {
                    TransitionValues transitionValues = new TransitionValues();
                    transitionValues.view = view;
                    if (z) {
                        captureStartValues(transitionValues);
                    } else {
                        captureEndValues(transitionValues);
                    }
                    transitionValues.a.add(this);
                    a(transitionValues);
                    if (z) {
                        a(this.u, view, transitionValues);
                    } else {
                        a(this.v, view, transitionValues);
                    }
                }
                if (!(view instanceof ViewGroup)) {
                    return;
                }
                if (this.r != null && this.r.contains(Integer.valueOf(id))) {
                    return;
                }
                if (this.s == null || !this.s.contains(view)) {
                    if (this.t != null) {
                        id = this.t.size();
                        i = 0;
                        while (i < id) {
                            if (!((Class) this.t.get(i)).isInstance(view)) {
                                i++;
                            } else {
                                return;
                            }
                        }
                    }
                    ViewGroup viewGroup = (ViewGroup) view;
                    for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                        b(viewGroup.getChildAt(i2), z);
                    }
                }
            }
        }
    }

    @Nullable
    public TransitionValues getTransitionValues(@NonNull View view, boolean z) {
        if (this.d != null) {
            return this.d.getTransitionValues(view, z);
        }
        return (TransitionValues) (z ? this.u : this.v).a.get(view);
    }

    TransitionValues a(View view, boolean z) {
        if (this.d != null) {
            return this.d.a(view, z);
        }
        ArrayList arrayList = z ? this.x : this.y;
        if (arrayList == null) {
            return null;
        }
        TransitionValues transitionValues;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            transitionValues = (TransitionValues) arrayList.get(i);
            if (transitionValues == null) {
                return null;
            }
            if (transitionValues.view == view) {
                break;
            }
            i++;
        }
        i = -1;
        if (i >= 0) {
            transitionValues = (TransitionValues) (z ? this.y : this.x).get(i);
        } else {
            transitionValues = null;
        }
        return transitionValues;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void pause(View view) {
        if (!this.E) {
            ArrayMap d = d();
            int size = d.size();
            cl b = bz.b(view);
            for (int i = size - 1; i >= 0; i--) {
                a aVar = (a) d.valueAt(i);
                if (aVar.a != null && b.equals(aVar.d)) {
                    a.a((Animator) d.keyAt(i));
                }
            }
            if (this.F != null && this.F.size() > 0) {
                ArrayList arrayList = (ArrayList) this.F.clone();
                int size2 = arrayList.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    ((TransitionListener) arrayList.get(i2)).onTransitionPause(this);
                }
            }
            this.D = true;
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void resume(View view) {
        if (this.D) {
            if (!this.E) {
                ArrayMap d = d();
                int size = d.size();
                cl b = bz.b(view);
                for (int i = size - 1; i >= 0; i--) {
                    a aVar = (a) d.valueAt(i);
                    if (aVar.a != null && b.equals(aVar.d)) {
                        a.b((Animator) d.keyAt(i));
                    }
                }
                if (this.F != null && this.F.size() > 0) {
                    ArrayList arrayList = (ArrayList) this.F.clone();
                    int size2 = arrayList.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        ((TransitionListener) arrayList.get(i2)).onTransitionResume(this);
                    }
                }
            }
            this.D = false;
        }
    }

    void a(ViewGroup viewGroup) {
        this.x = new ArrayList();
        this.y = new ArrayList();
        a(this.u, this.v);
        ArrayMap d = d();
        int size = d.size();
        cl b = bz.b(viewGroup);
        for (int i = size - 1; i >= 0; i--) {
            Animator animator = (Animator) d.keyAt(i);
            if (animator != null) {
                a aVar = (a) d.get(animator);
                if (!(aVar == null || aVar.a == null || !b.equals(aVar.d))) {
                    TransitionValues transitionValues = aVar.c;
                    View view = aVar.a;
                    TransitionValues transitionValues2 = getTransitionValues(view, true);
                    TransitionValues a = a(view, true);
                    boolean z = !(transitionValues2 == null && a == null) && aVar.e.isTransitionRequired(transitionValues, a);
                    if (z) {
                        if (animator.isRunning() || animator.isStarted()) {
                            animator.cancel();
                        } else {
                            d.remove(animator);
                        }
                    }
                }
            }
        }
        a(viewGroup, this.u, this.v, this.x, this.y);
        a();
    }

    public boolean isTransitionRequired(@Nullable TransitionValues transitionValues, @Nullable TransitionValues transitionValues2) {
        if (transitionValues == null || transitionValues2 == null) {
            return false;
        }
        String[] transitionProperties = getTransitionProperties();
        if (transitionProperties != null) {
            boolean z;
            for (String a : transitionProperties) {
                if (a(transitionValues, transitionValues2, a)) {
                    z = true;
                    break;
                }
            }
            z = false;
            return z;
        }
        for (String a2 : transitionValues.values.keySet()) {
            if (a(transitionValues, transitionValues2, a2)) {
                return true;
            }
        }
        return false;
    }

    private static boolean a(TransitionValues transitionValues, TransitionValues transitionValues2, String str) {
        Object obj = transitionValues.values.get(str);
        Object obj2 = transitionValues2.values.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null || !obj.equals(obj2)) {
            return true;
        }
        return false;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    protected void a(Animator animator) {
        if (animator == null) {
            c();
            return;
        }
        if (getDuration() >= 0) {
            animator.setDuration(getDuration());
        }
        if (getStartDelay() >= 0) {
            animator.setStartDelay(getStartDelay());
        }
        if (getInterpolator() != null) {
            animator.setInterpolator(getInterpolator());
        }
        animator.addListener(new bh(this));
        animator.start();
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    protected void b() {
        if (this.C == 0) {
            if (this.F != null && this.F.size() > 0) {
                ArrayList arrayList = (ArrayList) this.F.clone();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    ((TransitionListener) arrayList.get(i)).onTransitionStart(this);
                }
            }
            this.E = false;
        }
        this.C++;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    protected void c() {
        this.C--;
        if (this.C == 0) {
            int i;
            View view;
            if (this.F != null && this.F.size() > 0) {
                ArrayList arrayList = (ArrayList) this.F.clone();
                int size = arrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((TransitionListener) arrayList.get(i2)).onTransitionEnd(this);
                }
            }
            for (i = 0; i < this.u.c.size(); i++) {
                view = (View) this.u.c.valueAt(i);
                if (view != null) {
                    ViewCompat.setHasTransientState(view, false);
                }
            }
            for (i = 0; i < this.v.c.size(); i++) {
                view = (View) this.v.c.valueAt(i);
                if (view != null) {
                    ViewCompat.setHasTransientState(view, false);
                }
            }
            this.E = true;
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    void b(ViewGroup viewGroup) {
        ArrayMap d = d();
        int size = d.size();
        if (viewGroup != null) {
            cl b = bz.b(viewGroup);
            for (int i = size - 1; i >= 0; i--) {
                a aVar = (a) d.valueAt(i);
                if (!(aVar.a == null || b == null || !b.equals(aVar.d))) {
                    ((Animator) d.keyAt(i)).end();
                }
            }
        }
    }

    @NonNull
    public Transition addListener(@NonNull TransitionListener transitionListener) {
        if (this.F == null) {
            this.F = new ArrayList();
        }
        this.F.add(transitionListener);
        return this;
    }

    @NonNull
    public Transition removeListener(@NonNull TransitionListener transitionListener) {
        if (this.F != null) {
            this.F.remove(transitionListener);
            if (this.F.size() == 0) {
                this.F = null;
            }
        }
        return this;
    }

    public void setPathMotion(@Nullable PathMotion pathMotion) {
        if (pathMotion == null) {
            this.J = h;
        } else {
            this.J = pathMotion;
        }
    }

    @NonNull
    public PathMotion getPathMotion() {
        return this.J;
    }

    public void setEpicenterCallback(@Nullable EpicenterCallback epicenterCallback) {
        this.H = epicenterCallback;
    }

    @Nullable
    public EpicenterCallback getEpicenterCallback() {
        return this.H;
    }

    @Nullable
    public Rect getEpicenter() {
        if (this.H == null) {
            return null;
        }
        return this.H.onGetEpicenter(this);
    }

    public void setPropagation(@Nullable TransitionPropagation transitionPropagation) {
        this.f = transitionPropagation;
    }

    @Nullable
    public TransitionPropagation getPropagation() {
        return this.f;
    }

    void a(TransitionValues transitionValues) {
        Object obj = null;
        if (this.f != null && !transitionValues.values.isEmpty()) {
            String[] propagationProperties = this.f.getPropagationProperties();
            if (propagationProperties != null) {
                for (Object containsKey : propagationProperties) {
                    if (!transitionValues.values.containsKey(containsKey)) {
                        break;
                    }
                }
                int i = 1;
                if (obj == null) {
                    this.f.captureValues(transitionValues);
                }
            }
        }
    }

    Transition c(ViewGroup viewGroup) {
        this.A = viewGroup;
        return this;
    }

    void b(boolean z) {
        this.e = z;
    }

    public String toString() {
        return a("");
    }

    public Transition clone() {
        try {
            Transition transition = (Transition) super.clone();
            transition.G = new ArrayList();
            transition.u = new bl();
            transition.v = new bl();
            transition.x = null;
            transition.y = null;
            return transition;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @NonNull
    public String getName() {
        return this.i;
    }

    String a(String str) {
        int i = 0;
        String str2 = str + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + ": ";
        if (this.a != -1) {
            str2 = str2 + "dur(" + this.a + ") ";
        }
        if (this.j != -1) {
            str2 = str2 + "dly(" + this.j + ") ";
        }
        if (this.k != null) {
            str2 = str2 + "interp(" + this.k + ") ";
        }
        if (this.b.size() <= 0 && this.c.size() <= 0) {
            return str2;
        }
        String str3;
        str2 = str2 + "tgts(";
        if (this.b.size() > 0) {
            str3 = str2;
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                if (i2 > 0) {
                    str3 = str3 + ", ";
                }
                str3 = str3 + this.b.get(i2);
            }
        } else {
            str3 = str2;
        }
        if (this.c.size() > 0) {
            while (i < this.c.size()) {
                if (i > 0) {
                    str3 = str3 + ", ";
                }
                str3 = str3 + this.c.get(i);
                i++;
            }
        }
        return str3 + ")";
    }
}
