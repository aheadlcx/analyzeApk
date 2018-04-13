package android.support.v4.app;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

class u {
    private static final int[] a = new int[]{0, 3, 0, 1, 5, 4, 7, 6, 9, 8};
    private static final FragmentTransitionImpl b = (VERSION.SDK_INT >= 21 ? new z() : null);
    private static final FragmentTransitionImpl c = b();

    static class a {
        public Fragment firstOut;
        public boolean firstOutIsPop;
        public c firstOutTransaction;
        public Fragment lastIn;
        public boolean lastInIsPop;
        public c lastInTransaction;

        a() {
        }
    }

    private static FragmentTransitionImpl b() {
        try {
            return (FragmentTransitionImpl) Class.forName("android.support.transition.FragmentTransitionSupport").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }

    static void a(k kVar, ArrayList<c> arrayList, ArrayList<Boolean> arrayList2, int i, int i2, boolean z) {
        if (kVar.l >= 1) {
            SparseArray sparseArray = new SparseArray();
            for (int i3 = i; i3 < i2; i3++) {
                c cVar = (c) arrayList.get(i3);
                if (((Boolean) arrayList2.get(i3)).booleanValue()) {
                    calculatePopFragments(cVar, sparseArray, z);
                } else {
                    calculateFragments(cVar, sparseArray, z);
                }
            }
            if (sparseArray.size() != 0) {
                View view = new View(kVar.m.b());
                int size = sparseArray.size();
                for (int i4 = 0; i4 < size; i4++) {
                    int keyAt = sparseArray.keyAt(i4);
                    ArrayMap a = a(keyAt, (ArrayList) arrayList, (ArrayList) arrayList2, i, i2);
                    a aVar = (a) sparseArray.valueAt(i4);
                    if (z) {
                        a(kVar, keyAt, aVar, view, a);
                    } else {
                        b(kVar, keyAt, aVar, view, a);
                    }
                }
            }
        }
    }

    private static ArrayMap<String, String> a(int i, ArrayList<c> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        ArrayMap<String, String> arrayMap = new ArrayMap();
        for (int i4 = i3 - 1; i4 >= i2; i4--) {
            c cVar = (c) arrayList.get(i4);
            if (cVar.b(i)) {
                boolean booleanValue = ((Boolean) arrayList2.get(i4)).booleanValue();
                if (cVar.r != null) {
                    ArrayList arrayList3;
                    ArrayList arrayList4;
                    int size = cVar.r.size();
                    if (booleanValue) {
                        arrayList3 = cVar.r;
                        arrayList4 = cVar.s;
                    } else {
                        ArrayList arrayList5 = cVar.r;
                        arrayList3 = cVar.s;
                        arrayList4 = arrayList5;
                    }
                    for (int i5 = 0; i5 < size; i5++) {
                        String str = (String) arrayList4.get(i5);
                        String str2 = (String) arrayList3.get(i5);
                        String str3 = (String) arrayMap.remove(str2);
                        if (str3 != null) {
                            arrayMap.put(str, str3);
                        } else {
                            arrayMap.put(str, str2);
                        }
                    }
                }
            }
        }
        return arrayMap;
    }

    private static void a(k kVar, int i, a aVar, View view, ArrayMap<String, String> arrayMap) {
        View view2 = null;
        if (kVar.n.onHasView()) {
            view2 = (ViewGroup) kVar.n.onFindViewById(i);
        }
        if (view2 != null) {
            Fragment fragment = aVar.lastIn;
            Fragment fragment2 = aVar.firstOut;
            FragmentTransitionImpl a = a(fragment2, fragment);
            if (a != null) {
                boolean z = aVar.lastInIsPop;
                boolean z2 = aVar.firstOutIsPop;
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                Object a2 = a(a, fragment, z);
                Object b = b(a, fragment2, z2);
                Object a3 = a(a, (ViewGroup) view2, view, (ArrayMap) arrayMap, aVar, arrayList2, arrayList, a2, b);
                if (a2 != null || a3 != null || b != null) {
                    ArrayList b2 = b(a, b, fragment2, arrayList2, view);
                    ArrayList b3 = b(a, a2, fragment, arrayList, view);
                    b(b3, 4);
                    Object a4 = a(a, a2, b, a3, fragment, z);
                    if (a4 != null) {
                        a(a, b, fragment2, b2);
                        ArrayList a5 = a.a(arrayList);
                        a.scheduleRemoveTargets(a4, a2, b3, b, b2, a3, arrayList);
                        a.beginDelayedTransition(view2, a4);
                        a.a(view2, arrayList2, arrayList, a5, arrayMap);
                        b(b3, 0);
                        a.swapSharedElementTargets(a3, arrayList2, arrayList);
                    }
                }
            }
        }
    }

    private static void a(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Fragment fragment, ArrayList<View> arrayList) {
        if (fragment != null && obj != null && fragment.mAdded && fragment.mHidden && fragment.mHiddenChanged) {
            fragment.setHideReplaced(true);
            fragmentTransitionImpl.scheduleHideFragmentView(obj, fragment.getView(), arrayList);
            am.add(fragment.mContainer, new v(arrayList));
        }
    }

    private static void b(k kVar, int i, a aVar, View view, ArrayMap<String, String> arrayMap) {
        ViewGroup viewGroup = null;
        if (kVar.n.onHasView()) {
            viewGroup = (ViewGroup) kVar.n.onFindViewById(i);
        }
        if (viewGroup != null) {
            Fragment fragment = aVar.lastIn;
            Fragment fragment2 = aVar.firstOut;
            FragmentTransitionImpl a = a(fragment2, fragment);
            if (a != null) {
                boolean z = aVar.lastInIsPop;
                boolean z2 = aVar.firstOutIsPop;
                Object a2 = a(a, fragment, z);
                Object b = b(a, fragment2, z2);
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                Object b2 = b(a, viewGroup, view, arrayMap, aVar, arrayList, arrayList2, a2, b);
                if (a2 != null || b2 != null || b != null) {
                    Object obj;
                    ArrayList b3 = b(a, b, fragment2, arrayList, view);
                    if (b3 == null || b3.isEmpty()) {
                        obj = null;
                    } else {
                        obj = b;
                    }
                    a.addTarget(a2, view);
                    Object a3 = a(a, a2, obj, b2, fragment, aVar.lastInIsPop);
                    if (a3 != null) {
                        ArrayList arrayList3 = new ArrayList();
                        a.scheduleRemoveTargets(a3, a2, arrayList3, obj, b3, b2, arrayList2);
                        a(a, viewGroup, fragment, view, arrayList2, a2, arrayList3, obj, b3);
                        a.a((View) viewGroup, arrayList2, (Map) arrayMap);
                        a.beginDelayedTransition(viewGroup, a3);
                        a.a(viewGroup, arrayList2, (Map) arrayMap);
                    }
                }
            }
        }
    }

    private static void a(FragmentTransitionImpl fragmentTransitionImpl, ViewGroup viewGroup, Fragment fragment, View view, ArrayList<View> arrayList, Object obj, ArrayList<View> arrayList2, Object obj2, ArrayList<View> arrayList3) {
        am.add(viewGroup, new w(obj, fragmentTransitionImpl, view, fragment, arrayList, arrayList2, arrayList3, obj2));
    }

    private static FragmentTransitionImpl a(Fragment fragment, Fragment fragment2) {
        Object exitTransition;
        List arrayList = new ArrayList();
        if (fragment != null) {
            exitTransition = fragment.getExitTransition();
            if (exitTransition != null) {
                arrayList.add(exitTransition);
            }
            exitTransition = fragment.getReturnTransition();
            if (exitTransition != null) {
                arrayList.add(exitTransition);
            }
            exitTransition = fragment.getSharedElementReturnTransition();
            if (exitTransition != null) {
                arrayList.add(exitTransition);
            }
        }
        if (fragment2 != null) {
            exitTransition = fragment2.getEnterTransition();
            if (exitTransition != null) {
                arrayList.add(exitTransition);
            }
            exitTransition = fragment2.getReenterTransition();
            if (exitTransition != null) {
                arrayList.add(exitTransition);
            }
            exitTransition = fragment2.getSharedElementEnterTransition();
            if (exitTransition != null) {
                arrayList.add(exitTransition);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        if (b != null && a(b, arrayList)) {
            return b;
        }
        if (c != null && a(c, arrayList)) {
            return c;
        }
        if (b == null && c == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    private static boolean a(FragmentTransitionImpl fragmentTransitionImpl, List<Object> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!fragmentTransitionImpl.canHandle(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static Object a(FragmentTransitionImpl fragmentTransitionImpl, Fragment fragment, Fragment fragment2, boolean z) {
        if (fragment == null || fragment2 == null) {
            return null;
        }
        Object sharedElementReturnTransition;
        if (z) {
            sharedElementReturnTransition = fragment2.getSharedElementReturnTransition();
        } else {
            sharedElementReturnTransition = fragment.getSharedElementEnterTransition();
        }
        return fragmentTransitionImpl.wrapTransitionInSet(fragmentTransitionImpl.cloneTransition(sharedElementReturnTransition));
    }

    private static Object a(FragmentTransitionImpl fragmentTransitionImpl, Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        Object reenterTransition;
        if (z) {
            reenterTransition = fragment.getReenterTransition();
        } else {
            reenterTransition = fragment.getEnterTransition();
        }
        return fragmentTransitionImpl.cloneTransition(reenterTransition);
    }

    private static Object b(FragmentTransitionImpl fragmentTransitionImpl, Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        Object returnTransition;
        if (z) {
            returnTransition = fragment.getReturnTransition();
        } else {
            returnTransition = fragment.getExitTransition();
        }
        return fragmentTransitionImpl.cloneTransition(returnTransition);
    }

    private static Object a(FragmentTransitionImpl fragmentTransitionImpl, ViewGroup viewGroup, View view, ArrayMap<String, String> arrayMap, a aVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Fragment fragment = aVar.lastIn;
        Fragment fragment2 = aVar.firstOut;
        if (fragment != null) {
            fragment.getView().setVisibility(0);
        }
        if (fragment == null || fragment2 == null) {
            return null;
        }
        Object obj3;
        Object obj4;
        boolean z = aVar.lastInIsPop;
        if (arrayMap.isEmpty()) {
            obj3 = null;
        } else {
            obj3 = a(fragmentTransitionImpl, fragment, fragment2, z);
        }
        ArrayMap b = b(fragmentTransitionImpl, (ArrayMap) arrayMap, obj3, aVar);
        ArrayMap c = c(fragmentTransitionImpl, arrayMap, obj3, aVar);
        if (arrayMap.isEmpty()) {
            obj4 = null;
            if (b != null) {
                b.clear();
            }
            if (c != null) {
                c.clear();
            }
        } else {
            a((ArrayList) arrayList, b, arrayMap.keySet());
            a((ArrayList) arrayList2, c, arrayMap.values());
            obj4 = obj3;
        }
        if (obj == null && obj2 == null && obj4 == null) {
            return null;
        }
        Rect rect;
        View b2;
        b(fragment, fragment2, z, b, true);
        if (obj4 != null) {
            arrayList2.add(view);
            fragmentTransitionImpl.setSharedElementTargets(obj4, view, arrayList);
            a(fragmentTransitionImpl, obj4, obj2, b, aVar.firstOutIsPop, aVar.firstOutTransaction);
            rect = new Rect();
            b2 = b(c, aVar, obj, z);
            if (b2 != null) {
                fragmentTransitionImpl.setEpicenter(obj, rect);
            }
        } else {
            rect = null;
            b2 = null;
        }
        am.add(viewGroup, new x(fragment, fragment2, z, c, b2, fragmentTransitionImpl, rect));
        return obj4;
    }

    private static void a(ArrayList<View> arrayList, ArrayMap<String, View> arrayMap, Collection<String> collection) {
        for (int size = arrayMap.size() - 1; size >= 0; size--) {
            View view = (View) arrayMap.valueAt(size);
            if (collection.contains(ViewCompat.getTransitionName(view))) {
                arrayList.add(view);
            }
        }
    }

    private static Object b(FragmentTransitionImpl fragmentTransitionImpl, ViewGroup viewGroup, View view, ArrayMap<String, String> arrayMap, a aVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Fragment fragment = aVar.lastIn;
        Fragment fragment2 = aVar.firstOut;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        Object obj3;
        Object obj4;
        boolean z = aVar.lastInIsPop;
        if (arrayMap.isEmpty()) {
            obj3 = null;
        } else {
            obj3 = a(fragmentTransitionImpl, fragment, fragment2, z);
        }
        ArrayMap b = b(fragmentTransitionImpl, (ArrayMap) arrayMap, obj3, aVar);
        if (arrayMap.isEmpty()) {
            obj4 = null;
        } else {
            arrayList.addAll(b.values());
            obj4 = obj3;
        }
        if (obj == null && obj2 == null && obj4 == null) {
            return null;
        }
        Rect rect;
        b(fragment, fragment2, z, b, true);
        if (obj4 != null) {
            rect = new Rect();
            fragmentTransitionImpl.setSharedElementTargets(obj4, view, arrayList);
            a(fragmentTransitionImpl, obj4, obj2, b, aVar.firstOutIsPop, aVar.firstOutTransaction);
            if (obj != null) {
                fragmentTransitionImpl.setEpicenter(obj, rect);
            }
        } else {
            rect = null;
        }
        am.add(viewGroup, new y(fragmentTransitionImpl, arrayMap, obj4, aVar, arrayList2, view, fragment, fragment2, z, arrayList, obj, rect));
        return obj4;
    }

    private static ArrayMap<String, View> b(FragmentTransitionImpl fragmentTransitionImpl, ArrayMap<String, String> arrayMap, Object obj, a aVar) {
        if (arrayMap.isEmpty() || obj == null) {
            arrayMap.clear();
            return null;
        }
        ArrayList arrayList;
        SharedElementCallback sharedElementCallback;
        Fragment fragment = aVar.firstOut;
        Map arrayMap2 = new ArrayMap();
        fragmentTransitionImpl.a(arrayMap2, fragment.getView());
        c cVar = aVar.firstOutTransaction;
        SharedElementCallback enterTransitionCallback;
        if (aVar.firstOutIsPop) {
            enterTransitionCallback = fragment.getEnterTransitionCallback();
            arrayList = cVar.s;
            sharedElementCallback = enterTransitionCallback;
        } else {
            enterTransitionCallback = fragment.getExitTransitionCallback();
            arrayList = cVar.r;
            sharedElementCallback = enterTransitionCallback;
        }
        arrayMap2.retainAll(arrayList);
        if (sharedElementCallback != null) {
            sharedElementCallback.onMapSharedElements(arrayList, arrayMap2);
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                String str = (String) arrayList.get(size);
                View view = (View) arrayMap2.get(str);
                if (view == null) {
                    arrayMap.remove(str);
                } else if (!str.equals(ViewCompat.getTransitionName(view))) {
                    arrayMap.put(ViewCompat.getTransitionName(view), (String) arrayMap.remove(str));
                }
            }
        } else {
            arrayMap.retainAll(arrayMap2.keySet());
        }
        return arrayMap2;
    }

    private static ArrayMap<String, View> c(FragmentTransitionImpl fragmentTransitionImpl, ArrayMap<String, String> arrayMap, Object obj, a aVar) {
        Fragment fragment = aVar.lastIn;
        View view = fragment.getView();
        if (arrayMap.isEmpty() || obj == null || view == null) {
            arrayMap.clear();
            return null;
        }
        ArrayList arrayList;
        SharedElementCallback sharedElementCallback;
        ArrayMap<String, View> arrayMap2 = new ArrayMap();
        fragmentTransitionImpl.a((Map) arrayMap2, view);
        c cVar = aVar.lastInTransaction;
        SharedElementCallback exitTransitionCallback;
        if (aVar.lastInIsPop) {
            exitTransitionCallback = fragment.getExitTransitionCallback();
            arrayList = cVar.r;
            sharedElementCallback = exitTransitionCallback;
        } else {
            exitTransitionCallback = fragment.getEnterTransitionCallback();
            arrayList = cVar.s;
            sharedElementCallback = exitTransitionCallback;
        }
        if (arrayList != null) {
            arrayMap2.retainAll(arrayList);
        }
        if (sharedElementCallback != null) {
            sharedElementCallback.onMapSharedElements(arrayList, arrayMap2);
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                String str = (String) arrayList.get(size);
                view = (View) arrayMap2.get(str);
                if (view == null) {
                    str = a((ArrayMap) arrayMap, str);
                    if (str != null) {
                        arrayMap.remove(str);
                    }
                } else if (!str.equals(ViewCompat.getTransitionName(view))) {
                    str = a((ArrayMap) arrayMap, str);
                    if (str != null) {
                        arrayMap.put(str, ViewCompat.getTransitionName(view));
                    }
                }
            }
        } else {
            a((ArrayMap) arrayMap, (ArrayMap) arrayMap2);
        }
        return arrayMap2;
    }

    private static String a(ArrayMap<String, String> arrayMap, String str) {
        int size = arrayMap.size();
        for (int i = 0; i < size; i++) {
            if (str.equals(arrayMap.valueAt(i))) {
                return (String) arrayMap.keyAt(i);
            }
        }
        return null;
    }

    private static View b(ArrayMap<String, View> arrayMap, a aVar, Object obj, boolean z) {
        c cVar = aVar.lastInTransaction;
        if (obj == null || arrayMap == null || cVar.r == null || cVar.r.isEmpty()) {
            return null;
        }
        Object obj2;
        if (z) {
            obj2 = (String) cVar.r.get(0);
        } else {
            String str = (String) cVar.s.get(0);
        }
        return (View) arrayMap.get(obj2);
    }

    private static void a(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Object obj2, ArrayMap<String, View> arrayMap, boolean z, c cVar) {
        if (cVar.r != null && !cVar.r.isEmpty()) {
            Object obj3;
            if (z) {
                obj3 = (String) cVar.s.get(0);
            } else {
                String str = (String) cVar.r.get(0);
            }
            View view = (View) arrayMap.get(obj3);
            fragmentTransitionImpl.setEpicenter(obj, view);
            if (obj2 != null) {
                fragmentTransitionImpl.setEpicenter(obj2, view);
            }
        }
    }

    private static void a(ArrayMap<String, String> arrayMap, ArrayMap<String, View> arrayMap2) {
        for (int size = arrayMap.size() - 1; size >= 0; size--) {
            if (!arrayMap2.containsKey((String) arrayMap.valueAt(size))) {
                arrayMap.removeAt(size);
            }
        }
    }

    private static void b(Fragment fragment, Fragment fragment2, boolean z, ArrayMap<String, View> arrayMap, boolean z2) {
        SharedElementCallback enterTransitionCallback;
        int i = 0;
        if (z) {
            enterTransitionCallback = fragment2.getEnterTransitionCallback();
        } else {
            enterTransitionCallback = fragment.getEnterTransitionCallback();
        }
        if (enterTransitionCallback != null) {
            List arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            int size = arrayMap == null ? 0 : arrayMap.size();
            while (i < size) {
                arrayList2.add(arrayMap.keyAt(i));
                arrayList.add(arrayMap.valueAt(i));
                i++;
            }
            if (z2) {
                enterTransitionCallback.onSharedElementStart(arrayList2, arrayList, null);
            } else {
                enterTransitionCallback.onSharedElementEnd(arrayList2, arrayList, null);
            }
        }
    }

    private static ArrayList<View> b(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Fragment fragment, ArrayList<View> arrayList, View view) {
        ArrayList<View> arrayList2 = null;
        if (obj != null) {
            arrayList2 = new ArrayList();
            View view2 = fragment.getView();
            if (view2 != null) {
                fragmentTransitionImpl.a((ArrayList) arrayList2, view2);
            }
            if (arrayList != null) {
                arrayList2.removeAll(arrayList);
            }
            if (!arrayList2.isEmpty()) {
                arrayList2.add(view);
                fragmentTransitionImpl.addTargets(obj, arrayList2);
            }
        }
        return arrayList2;
    }

    private static void b(ArrayList<View> arrayList, int i) {
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((View) arrayList.get(size)).setVisibility(i);
            }
        }
    }

    private static Object a(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Object obj2, Object obj3, Fragment fragment, boolean z) {
        boolean z2 = true;
        if (!(obj == null || obj2 == null || fragment == null)) {
            z2 = z ? fragment.getAllowReturnTransitionOverlap() : fragment.getAllowEnterTransitionOverlap();
        }
        if (z2) {
            return fragmentTransitionImpl.mergeTransitionsTogether(obj2, obj, obj3);
        }
        return fragmentTransitionImpl.mergeTransitionsInSequence(obj2, obj, obj3);
    }

    public static void calculateFragments(c cVar, SparseArray<a> sparseArray, boolean z) {
        int size = cVar.b.size();
        for (int i = 0; i < size; i++) {
            a(cVar, (a) cVar.b.get(i), (SparseArray) sparseArray, false, z);
        }
    }

    public static void calculatePopFragments(c cVar, SparseArray<a> sparseArray, boolean z) {
        if (cVar.a.n.onHasView()) {
            for (int size = cVar.b.size() - 1; size >= 0; size--) {
                a(cVar, (a) cVar.b.get(size), (SparseArray) sparseArray, true, z);
            }
        }
    }

    static boolean a() {
        return (b == null && c == null) ? false : true;
    }

    private static void a(c cVar, a aVar, SparseArray<a> sparseArray, boolean z, boolean z2) {
        Fragment fragment = aVar.b;
        if (fragment != null) {
            int i = fragment.mContainerId;
            if (i != 0) {
                int i2;
                int i3;
                a a;
                boolean z3;
                int i4;
                int i5;
                boolean z4;
                int i6;
                switch (z ? a[aVar.a] : aVar.a) {
                    case 1:
                    case 7:
                        z3 = z2 ? fragment.mIsNewlyAdded : (fragment.mAdded || fragment.mHidden) ? false : true;
                        i4 = 1;
                        i2 = 0;
                        i5 = 0;
                        z4 = z3;
                        break;
                    case 3:
                    case 6:
                        i6 = z2 ? (fragment.mAdded || fragment.mView == null || fragment.mView.getVisibility() != 0 || fragment.mPostponedAlpha < 0.0f) ? 0 : 1 : (!fragment.mAdded || fragment.mHidden) ? 0 : 1;
                        i4 = 0;
                        i2 = i6;
                        i5 = 1;
                        i3 = 0;
                        break;
                    case 4:
                        i6 = z2 ? (fragment.mHiddenChanged && fragment.mAdded && fragment.mHidden) ? 1 : 0 : (!fragment.mAdded || fragment.mHidden) ? 0 : 1;
                        i4 = 0;
                        i2 = i6;
                        i5 = 1;
                        i3 = 0;
                        break;
                    case 5:
                        z3 = z2 ? fragment.mHiddenChanged && !fragment.mHidden && fragment.mAdded : fragment.mHidden;
                        i4 = 1;
                        i2 = 0;
                        i5 = 0;
                        z4 = z3;
                        break;
                    default:
                        i4 = 0;
                        i2 = 0;
                        i5 = 0;
                        i3 = 0;
                        break;
                }
                a aVar2 = (a) sparseArray.get(i);
                if (i3 != 0) {
                    a = a(aVar2, (SparseArray) sparseArray, i);
                    a.lastIn = fragment;
                    a.lastInIsPop = z;
                    a.lastInTransaction = cVar;
                } else {
                    a = aVar2;
                }
                if (!(z2 || r4 == 0)) {
                    if (a != null && a.firstOut == fragment) {
                        a.firstOut = null;
                    }
                    k kVar = cVar.a;
                    if (fragment.mState < 1 && kVar.l >= 1 && !cVar.t) {
                        kVar.e(fragment);
                        kVar.a(fragment, 1, 0, 0, false);
                    }
                }
                if (i2 == 0 || !(a == null || a.firstOut == null)) {
                    aVar2 = a;
                } else {
                    aVar2 = a(a, (SparseArray) sparseArray, i);
                    aVar2.firstOut = fragment;
                    aVar2.firstOutIsPop = z;
                    aVar2.firstOutTransaction = cVar;
                }
                if (!z2 && r7 != 0 && aVar2 != null && aVar2.lastIn == fragment) {
                    aVar2.lastIn = null;
                }
            }
        }
    }

    private static a a(a aVar, SparseArray<a> sparseArray, int i) {
        if (aVar != null) {
            return aVar;
        }
        aVar = new a();
        sparseArray.put(i, aVar);
        return aVar;
    }
}
