package android.support.v4.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment.SavedState;
import android.support.v4.app.FragmentManager.BackStackEntry;
import android.support.v4.app.FragmentManager.FragmentLifecycleCallbacks;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.util.ArraySet;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.LogWriter;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater.Factory2;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import com.baidu.mobstat.Config;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

final class k extends FragmentManager implements Factory2 {
    public static final int ANIM_STYLE_CLOSE_ENTER = 3;
    public static final int ANIM_STYLE_CLOSE_EXIT = 4;
    public static final int ANIM_STYLE_FADE_ENTER = 5;
    public static final int ANIM_STYLE_FADE_EXIT = 6;
    public static final int ANIM_STYLE_OPEN_ENTER = 1;
    public static final int ANIM_STYLE_OPEN_EXIT = 2;
    static final Interpolator E = new DecelerateInterpolator(2.5f);
    static final Interpolator F = new DecelerateInterpolator(1.5f);
    static final Interpolator G = new AccelerateInterpolator(2.5f);
    static final Interpolator H = new AccelerateInterpolator(1.5f);
    static boolean a = false;
    static Field q = null;
    SparseArray<Parcelable> A = null;
    ArrayList<h> B;
    FragmentManagerNonConfig C;
    Runnable D = new l(this);
    private final CopyOnWriteArrayList<Pair<FragmentLifecycleCallbacks, Boolean>> I = new CopyOnWriteArrayList();
    ArrayList<f> b;
    boolean c;
    int d = 0;
    final ArrayList<Fragment> e = new ArrayList();
    SparseArray<Fragment> f;
    ArrayList<c> g;
    ArrayList<Fragment> h;
    ArrayList<c> i;
    ArrayList<Integer> j;
    ArrayList<OnBackStackChangedListener> k;
    int l = 0;
    FragmentHostCallback m;
    FragmentContainer n;
    Fragment o;
    Fragment p;
    boolean r;
    boolean s;
    boolean t;
    String u;
    boolean v;
    ArrayList<c> w;
    ArrayList<Boolean> x;
    ArrayList<Fragment> y;
    Bundle z = null;

    interface f {
        boolean generateOps(ArrayList<c> arrayList, ArrayList<Boolean> arrayList2);
    }

    private static class b implements AnimationListener {
        private final AnimationListener a;

        private b(AnimationListener animationListener) {
            this.a = animationListener;
        }

        @CallSuper
        public void onAnimationStart(Animation animation) {
            if (this.a != null) {
                this.a.onAnimationStart(animation);
            }
        }

        @CallSuper
        public void onAnimationEnd(Animation animation) {
            if (this.a != null) {
                this.a.onAnimationEnd(animation);
            }
        }

        @CallSuper
        public void onAnimationRepeat(Animation animation) {
            if (this.a != null) {
                this.a.onAnimationRepeat(animation);
            }
        }
    }

    private static class a extends b {
        View a;

        a(View view, AnimationListener animationListener) {
            super(animationListener);
            this.a = view;
        }

        @CallSuper
        public void onAnimationEnd(Animation animation) {
            if (ViewCompat.isAttachedToWindow(this.a) || VERSION.SDK_INT >= 24) {
                this.a.post(new q(this));
            } else {
                this.a.setLayerType(0, null);
            }
            super.onAnimationEnd(animation);
        }
    }

    private static class c {
        public final Animation animation;
        public final Animator animator;

        private c(Animation animation) {
            this.animation = animation;
            this.animator = null;
            if (animation == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }

        private c(Animator animator) {
            this.animation = null;
            this.animator = animator;
            if (animator == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }
    }

    private static class d extends AnimatorListenerAdapter {
        View a;

        d(View view) {
            this.a = view;
        }

        public void onAnimationStart(Animator animator) {
            this.a.setLayerType(2, null);
        }

        public void onAnimationEnd(Animator animator) {
            this.a.setLayerType(0, null);
            animator.removeListener(this);
        }
    }

    static class e {
        public static final int[] Fragment = new int[]{16842755, 16842960, 16842961};
        public static final int Fragment_id = 1;
        public static final int Fragment_name = 0;
        public static final int Fragment_tag = 2;
    }

    private class g implements f {
        final String a;
        final int b;
        final int c;
        final /* synthetic */ k d;

        g(k kVar, String str, int i, int i2) {
            this.d = kVar;
            this.a = str;
            this.b = i;
            this.c = i2;
        }

        public boolean generateOps(ArrayList<c> arrayList, ArrayList<Boolean> arrayList2) {
            if (this.d.p != null && this.b < 0 && this.a == null) {
                FragmentManager peekChildFragmentManager = this.d.p.peekChildFragmentManager();
                if (peekChildFragmentManager != null && peekChildFragmentManager.popBackStackImmediate()) {
                    return false;
                }
            }
            return this.d.a((ArrayList) arrayList, (ArrayList) arrayList2, this.a, this.b, this.c);
        }
    }

    static class h implements b {
        private final boolean a;
        private final c b;
        private int c;

        h(c cVar, boolean z) {
            this.a = z;
            this.b = cVar;
        }

        public void onStartEnterTransition() {
            this.c--;
            if (this.c == 0) {
                this.b.a.k();
            }
        }

        public void startListening() {
            this.c++;
        }

        public boolean isReady() {
            return this.c == 0;
        }

        public void completeTransaction() {
            boolean z;
            boolean z2 = false;
            if (this.c > 0) {
                z = true;
            } else {
                z = false;
            }
            k kVar = this.b.a;
            int size = kVar.e.size();
            for (int i = 0; i < size; i++) {
                Fragment fragment = (Fragment) kVar.e.get(i);
                fragment.setOnStartEnterTransitionListener(null);
                if (z && fragment.isPostponed()) {
                    fragment.startPostponedEnterTransition();
                }
            }
            k kVar2 = this.b.a;
            c cVar = this.b;
            boolean z3 = this.a;
            if (!z) {
                z2 = true;
            }
            kVar2.a(cVar, z3, z2, true);
        }

        public void cancelTransaction() {
            this.b.a.a(this.b, this.a, false, false);
        }
    }

    k() {
    }

    static boolean a(c cVar) {
        if (cVar.animation instanceof AlphaAnimation) {
            return true;
        }
        if (!(cVar.animation instanceof AnimationSet)) {
            return a(cVar.animator);
        }
        List animations = ((AnimationSet) cVar.animation).getAnimations();
        for (int i = 0; i < animations.size(); i++) {
            if (animations.get(i) instanceof AlphaAnimation) {
                return true;
            }
        }
        return false;
    }

    static boolean a(Animator animator) {
        if (animator == null) {
            return false;
        }
        if (animator instanceof ValueAnimator) {
            PropertyValuesHolder[] values = ((ValueAnimator) animator).getValues();
            for (PropertyValuesHolder propertyName : values) {
                if ("alpha".equals(propertyName.getPropertyName())) {
                    return true;
                }
            }
            return false;
        } else if (!(animator instanceof AnimatorSet)) {
            return false;
        } else {
            List childAnimations = ((AnimatorSet) animator).getChildAnimations();
            for (int i = 0; i < childAnimations.size(); i++) {
                if (a((Animator) childAnimations.get(i))) {
                    return true;
                }
            }
            return false;
        }
    }

    static boolean a(View view, c cVar) {
        if (view == null || cVar == null || VERSION.SDK_INT < 19 || view.getLayerType() != 0 || !ViewCompat.hasOverlappingRendering(view) || !a(cVar)) {
            return false;
        }
        return true;
    }

    private void a(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new LogWriter("FragmentManager"));
        if (this.m != null) {
            try {
                this.m.onDump("  ", null, printWriter, new String[0]);
            } catch (Throwable e) {
                Log.e("FragmentManager", "Failed dumping state", e);
            }
        } else {
            try {
                dump("  ", null, printWriter, new String[0]);
            } catch (Throwable e2) {
                Log.e("FragmentManager", "Failed dumping state", e2);
            }
        }
        throw runtimeException;
    }

    public FragmentTransaction beginTransaction() {
        return new c(this);
    }

    public boolean executePendingTransactions() {
        boolean execPendingActions = execPendingActions();
        m();
        return execPendingActions;
    }

    public void popBackStack() {
        enqueueAction(new g(this, null, -1, 0), false);
    }

    public boolean popBackStackImmediate() {
        j();
        return a(null, -1, 0);
    }

    public void popBackStack(String str, int i) {
        enqueueAction(new g(this, str, -1, i), false);
    }

    public boolean popBackStackImmediate(String str, int i) {
        j();
        return a(str, -1, i);
    }

    public void popBackStack(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("Bad id: " + i);
        }
        enqueueAction(new g(this, null, i, i2), false);
    }

    public boolean popBackStackImmediate(int i, int i2) {
        j();
        execPendingActions();
        if (i >= 0) {
            return a(null, i, i2);
        }
        throw new IllegalArgumentException("Bad id: " + i);
    }

    private boolean a(String str, int i, int i2) {
        execPendingActions();
        a(true);
        if (this.p != null && i < 0 && str == null) {
            FragmentManager peekChildFragmentManager = this.p.peekChildFragmentManager();
            if (peekChildFragmentManager != null && peekChildFragmentManager.popBackStackImmediate()) {
                return true;
            }
        }
        boolean a = a(this.w, this.x, str, i, i2);
        if (a) {
            this.c = true;
            try {
                b(this.w, this.x);
            } finally {
                l();
            }
        }
        d();
        o();
        return a;
    }

    public int getBackStackEntryCount() {
        return this.g != null ? this.g.size() : 0;
    }

    public BackStackEntry getBackStackEntryAt(int i) {
        return (BackStackEntry) this.g.get(i);
    }

    public void addOnBackStackChangedListener(OnBackStackChangedListener onBackStackChangedListener) {
        if (this.k == null) {
            this.k = new ArrayList();
        }
        this.k.add(onBackStackChangedListener);
    }

    public void removeOnBackStackChangedListener(OnBackStackChangedListener onBackStackChangedListener) {
        if (this.k != null) {
            this.k.remove(onBackStackChangedListener);
        }
    }

    public void putFragment(Bundle bundle, String str, Fragment fragment) {
        if (fragment.mIndex < 0) {
            a(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        bundle.putInt(str, fragment.mIndex);
    }

    public Fragment getFragment(Bundle bundle, String str) {
        int i = bundle.getInt(str, -1);
        if (i == -1) {
            return null;
        }
        Fragment fragment = (Fragment) this.f.get(i);
        if (fragment != null) {
            return fragment;
        }
        a(new IllegalStateException("Fragment no longer exists for key " + str + ": index " + i));
        return fragment;
    }

    public List<Fragment> getFragments() {
        if (this.e.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        List<Fragment> list;
        synchronized (this.e) {
            list = (List) this.e.clone();
        }
        return list;
    }

    List<Fragment> a() {
        if (this.f == null) {
            return null;
        }
        int size = this.f.size();
        List<Fragment> arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(this.f.valueAt(i));
        }
        return arrayList;
    }

    int b() {
        if (this.f == null) {
            return 0;
        }
        return this.f.size();
    }

    public SavedState saveFragmentInstanceState(Fragment fragment) {
        if (fragment.mIndex < 0) {
            a(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        if (fragment.mState <= 0) {
            return null;
        }
        Bundle h = h(fragment);
        if (h != null) {
            return new SavedState(h);
        }
        return null;
    }

    public boolean isDestroyed() {
        return this.t;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append("FragmentManager{");
        stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringBuilder.append(" in ");
        if (this.o != null) {
            DebugUtils.buildShortClassTag(this.o, stringBuilder);
        } else {
            DebugUtils.buildShortClassTag(this.m, stringBuilder);
        }
        stringBuilder.append("}}");
        return stringBuilder.toString();
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int i;
        Fragment fragment;
        int i2 = 0;
        String str2 = str + "    ";
        if (this.f != null) {
            size = this.f.size();
            if (size > 0) {
                printWriter.print(str);
                printWriter.print("Active Fragments in ");
                printWriter.print(Integer.toHexString(System.identityHashCode(this)));
                printWriter.println(Config.TRACE_TODAY_VISIT_SPLIT);
                for (i = 0; i < size; i++) {
                    fragment = (Fragment) this.f.valueAt(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i);
                    printWriter.print(": ");
                    printWriter.println(fragment);
                    if (fragment != null) {
                        fragment.dump(str2, fileDescriptor, printWriter, strArr);
                    }
                }
            }
        }
        size = this.e.size();
        if (size > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (i = 0; i < size; i++) {
                fragment = (Fragment) this.e.get(i);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.println(fragment.toString());
            }
        }
        if (this.h != null) {
            size = this.h.size();
            if (size > 0) {
                printWriter.print(str);
                printWriter.println("Fragments Created Menus:");
                for (i = 0; i < size; i++) {
                    fragment = (Fragment) this.h.get(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i);
                    printWriter.print(": ");
                    printWriter.println(fragment.toString());
                }
            }
        }
        if (this.g != null) {
            size = this.g.size();
            if (size > 0) {
                printWriter.print(str);
                printWriter.println("Back Stack:");
                for (i = 0; i < size; i++) {
                    c cVar = (c) this.g.get(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i);
                    printWriter.print(": ");
                    printWriter.println(cVar.toString());
                    cVar.dump(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }
        synchronized (this) {
            if (this.i != null) {
                int size2 = this.i.size();
                if (size2 > 0) {
                    printWriter.print(str);
                    printWriter.println("Back Stack Indices:");
                    for (i = 0; i < size2; i++) {
                        cVar = (c) this.i.get(i);
                        printWriter.print(str);
                        printWriter.print("  #");
                        printWriter.print(i);
                        printWriter.print(": ");
                        printWriter.println(cVar);
                    }
                }
            }
            if (this.j != null && this.j.size() > 0) {
                printWriter.print(str);
                printWriter.print("mAvailBackStackIndices: ");
                printWriter.println(Arrays.toString(this.j.toArray()));
            }
        }
        if (this.b != null) {
            i = this.b.size();
            if (i > 0) {
                printWriter.print(str);
                printWriter.println("Pending Actions:");
                while (i2 < i) {
                    f fVar = (f) this.b.get(i2);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i2);
                    printWriter.print(": ");
                    printWriter.println(fVar);
                    i2++;
                }
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.m);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.n);
        if (this.o != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.o);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.l);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.s);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.t);
        if (this.r) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.r);
        }
        if (this.u != null) {
            printWriter.print(str);
            printWriter.print("  mNoTransactionsBecause=");
            printWriter.println(this.u);
        }
    }

    static c a(Context context, float f, float f2, float f3, float f4) {
        Animation animationSet = new AnimationSet(false);
        Animation scaleAnimation = new ScaleAnimation(f, f2, f, f2, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(E);
        scaleAnimation.setDuration(220);
        animationSet.addAnimation(scaleAnimation);
        scaleAnimation = new AlphaAnimation(f3, f4);
        scaleAnimation.setInterpolator(F);
        scaleAnimation.setDuration(220);
        animationSet.addAnimation(scaleAnimation);
        return new c(animationSet);
    }

    static c a(Context context, float f, float f2) {
        Animation alphaAnimation = new AlphaAnimation(f, f2);
        alphaAnimation.setInterpolator(F);
        alphaAnimation.setDuration(220);
        return new c(alphaAnimation);
    }

    c a(Fragment fragment, int i, boolean z, int i2) {
        int nextAnim = fragment.getNextAnim();
        Animation onCreateAnimation = fragment.onCreateAnimation(i, z, nextAnim);
        if (onCreateAnimation != null) {
            return new c(onCreateAnimation);
        }
        Animator onCreateAnimator = fragment.onCreateAnimator(i, z, nextAnim);
        if (onCreateAnimator != null) {
            return new c(onCreateAnimator);
        }
        if (nextAnim != 0) {
            Object obj;
            boolean equals = "anim".equals(this.m.b().getResources().getResourceTypeName(nextAnim));
            if (equals) {
                try {
                    Animation loadAnimation = AnimationUtils.loadAnimation(this.m.b(), nextAnim);
                    if (loadAnimation != null) {
                        return new c(loadAnimation);
                    }
                    obj = 1;
                } catch (NotFoundException e) {
                    throw e;
                } catch (RuntimeException e2) {
                    obj = null;
                }
            } else {
                obj = null;
            }
            if (obj == null) {
                try {
                    onCreateAnimator = AnimatorInflater.loadAnimator(this.m.b(), nextAnim);
                    if (onCreateAnimator != null) {
                        return new c(onCreateAnimator);
                    }
                } catch (RuntimeException e3) {
                    if (equals) {
                        throw e3;
                    }
                    onCreateAnimation = AnimationUtils.loadAnimation(this.m.b(), nextAnim);
                    if (onCreateAnimation != null) {
                        return new c(onCreateAnimation);
                    }
                }
            }
        }
        if (i == 0) {
            return null;
        }
        int transitToStyleIndex = transitToStyleIndex(i, z);
        if (transitToStyleIndex < 0) {
            return null;
        }
        switch (transitToStyleIndex) {
            case 1:
                return a(this.m.b(), 1.125f, 1.0f, 0.0f, 1.0f);
            case 2:
                return a(this.m.b(), 1.0f, 0.975f, 1.0f, 0.0f);
            case 3:
                return a(this.m.b(), 0.975f, 1.0f, 0.0f, 1.0f);
            case 4:
                return a(this.m.b(), 1.0f, 1.075f, 1.0f, 0.0f);
            case 5:
                return a(this.m.b(), 0.0f, 1.0f);
            case 6:
                return a(this.m.b(), 1.0f, 0.0f);
            default:
                if (i2 == 0 && this.m.onHasWindowAnimations()) {
                    i2 = this.m.onGetWindowAnimations();
                }
                if (i2 == 0) {
                    return null;
                }
                return null;
        }
    }

    public void performPendingDeferredStart(Fragment fragment) {
        if (!fragment.mDeferStart) {
            return;
        }
        if (this.c) {
            this.v = true;
            return;
        }
        fragment.mDeferStart = false;
        a(fragment, this.l, 0, 0, false);
    }

    private static void b(View view, c cVar) {
        if (view != null && cVar != null && a(view, cVar)) {
            if (cVar.animator != null) {
                cVar.animator.addListener(new d(view));
                return;
            }
            AnimationListener a = a(cVar.animation);
            view.setLayerType(2, null);
            cVar.animation.setAnimationListener(new a(view, a));
        }
    }

    private static AnimationListener a(Animation animation) {
        try {
            if (q == null) {
                q = Animation.class.getDeclaredField("mListener");
                q.setAccessible(true);
            }
            return (AnimationListener) q.get(animation);
        } catch (Throwable e) {
            Log.e("FragmentManager", "No field with the name mListener is found in Animation class", e);
            return null;
        } catch (Throwable e2) {
            Log.e("FragmentManager", "Cannot access Animation's mListener field", e2);
            return null;
        }
    }

    boolean a(int i) {
        return this.l >= i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void a(android.support.v4.app.Fragment r11, int r12, int r13, int r14, boolean r15) {
        /*
        r10 = this;
        r9 = 4;
        r6 = 3;
        r5 = 1;
        r7 = 0;
        r3 = 0;
        r0 = r11.mAdded;
        if (r0 == 0) goto L_0x000d;
    L_0x0009:
        r0 = r11.mDetached;
        if (r0 == 0) goto L_0x0010;
    L_0x000d:
        if (r12 <= r5) goto L_0x0010;
    L_0x000f:
        r12 = r5;
    L_0x0010:
        r0 = r11.mRemoving;
        if (r0 == 0) goto L_0x0023;
    L_0x0014:
        r0 = r11.mState;
        if (r12 <= r0) goto L_0x0023;
    L_0x0018:
        r0 = r11.mState;
        if (r0 != 0) goto L_0x003b;
    L_0x001c:
        r0 = r11.isInBackStack();
        if (r0 == 0) goto L_0x003b;
    L_0x0022:
        r12 = r5;
    L_0x0023:
        r0 = r11.mDeferStart;
        if (r0 == 0) goto L_0x002e;
    L_0x0027:
        r0 = r11.mState;
        if (r0 >= r9) goto L_0x002e;
    L_0x002b:
        if (r12 <= r6) goto L_0x002e;
    L_0x002d:
        r12 = r6;
    L_0x002e:
        r0 = r11.mState;
        if (r0 > r12) goto L_0x032e;
    L_0x0032:
        r0 = r11.mFromLayout;
        if (r0 == 0) goto L_0x003e;
    L_0x0036:
        r0 = r11.mInLayout;
        if (r0 != 0) goto L_0x003e;
    L_0x003a:
        return;
    L_0x003b:
        r12 = r11.mState;
        goto L_0x0023;
    L_0x003e:
        r0 = r11.getAnimatingAway();
        if (r0 != 0) goto L_0x004a;
    L_0x0044:
        r0 = r11.getAnimator();
        if (r0 == 0) goto L_0x005a;
    L_0x004a:
        r11.setAnimatingAway(r7);
        r11.setAnimator(r7);
        r2 = r11.getStateAfterAnimating();
        r0 = r10;
        r1 = r11;
        r4 = r3;
        r0.a(r1, r2, r3, r4, r5);
    L_0x005a:
        r0 = r11.mState;
        switch(r0) {
            case 0: goto L_0x009a;
            case 1: goto L_0x01c2;
            case 2: goto L_0x02c0;
            case 3: goto L_0x02c5;
            case 4: goto L_0x02e9;
            default: goto L_0x005f;
        };
    L_0x005f:
        r0 = r11.mState;
        if (r0 == r12) goto L_0x003a;
    L_0x0063:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveToState: Fragment state for ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r2 = " not updated inline; ";
        r1 = r1.append(r2);
        r2 = "expected state ";
        r1 = r1.append(r2);
        r1 = r1.append(r12);
        r2 = " found ";
        r1 = r1.append(r2);
        r2 = r11.mState;
        r1 = r1.append(r2);
        r1 = r1.toString();
        android.util.Log.w(r0, r1);
        r11.mState = r12;
        goto L_0x003a;
    L_0x009a:
        if (r12 <= 0) goto L_0x01c2;
    L_0x009c:
        r0 = a;
        if (r0 == 0) goto L_0x00b8;
    L_0x00a0:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveto CREATED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x00b8:
        r0 = r11.mSavedFragmentState;
        if (r0 == 0) goto L_0x0100;
    L_0x00bc:
        r0 = r11.mSavedFragmentState;
        r1 = r10.m;
        r1 = r1.b();
        r1 = r1.getClassLoader();
        r0.setClassLoader(r1);
        r0 = r11.mSavedFragmentState;
        r1 = "android:view_state";
        r0 = r0.getSparseParcelableArray(r1);
        r11.mSavedViewState = r0;
        r0 = r11.mSavedFragmentState;
        r1 = "android:target_state";
        r0 = r10.getFragment(r0, r1);
        r11.mTarget = r0;
        r0 = r11.mTarget;
        if (r0 == 0) goto L_0x00ed;
    L_0x00e3:
        r0 = r11.mSavedFragmentState;
        r1 = "android:target_req_state";
        r0 = r0.getInt(r1, r3);
        r11.mTargetRequestCode = r0;
    L_0x00ed:
        r0 = r11.mSavedFragmentState;
        r1 = "android:user_visible_hint";
        r0 = r0.getBoolean(r1, r5);
        r11.mUserVisibleHint = r0;
        r0 = r11.mUserVisibleHint;
        if (r0 != 0) goto L_0x0100;
    L_0x00fb:
        r11.mDeferStart = r5;
        if (r12 <= r6) goto L_0x0100;
    L_0x00ff:
        r12 = r6;
    L_0x0100:
        r0 = r10.m;
        r11.mHost = r0;
        r0 = r10.o;
        r11.mParentFragment = r0;
        r0 = r10.o;
        if (r0 == 0) goto L_0x014f;
    L_0x010c:
        r0 = r10.o;
        r0 = r0.mChildFragmentManager;
    L_0x0110:
        r11.mFragmentManager = r0;
        r0 = r11.mTarget;
        if (r0 == 0) goto L_0x0164;
    L_0x0116:
        r0 = r10.f;
        r1 = r11.mTarget;
        r1 = r1.mIndex;
        r0 = r0.get(r1);
        r1 = r11.mTarget;
        if (r0 == r1) goto L_0x0156;
    L_0x0124:
        r0 = new java.lang.IllegalStateException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Fragment ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r2 = " declared target fragment ";
        r1 = r1.append(r2);
        r2 = r11.mTarget;
        r1 = r1.append(r2);
        r2 = " that does not belong to this FragmentManager!";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x014f:
        r0 = r10.m;
        r0 = r0.d();
        goto L_0x0110;
    L_0x0156:
        r0 = r11.mTarget;
        r0 = r0.mState;
        if (r0 >= r5) goto L_0x0164;
    L_0x015c:
        r1 = r11.mTarget;
        r0 = r10;
        r2 = r5;
        r4 = r3;
        r0.a(r1, r2, r3, r4, r5);
    L_0x0164:
        r0 = r10.m;
        r0 = r0.b();
        r10.a(r11, r0, r3);
        r11.mCalled = r3;
        r0 = r10.m;
        r0 = r0.b();
        r11.onAttach(r0);
        r0 = r11.mCalled;
        if (r0 != 0) goto L_0x019b;
    L_0x017c:
        r0 = new android.support.v4.app.an;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Fragment ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r2 = " did not call through to super.onAttach()";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x019b:
        r0 = r11.mParentFragment;
        if (r0 != 0) goto L_0x0313;
    L_0x019f:
        r0 = r10.m;
        r0.onAttachFragment(r11);
    L_0x01a4:
        r0 = r10.m;
        r0 = r0.b();
        r10.b(r11, r0, r3);
        r0 = r11.mIsCreated;
        if (r0 != 0) goto L_0x031a;
    L_0x01b1:
        r0 = r11.mSavedFragmentState;
        r10.a(r11, r0, r3);
        r0 = r11.mSavedFragmentState;
        r11.performCreate(r0);
        r0 = r11.mSavedFragmentState;
        r10.b(r11, r0, r3);
    L_0x01c0:
        r11.mRetaining = r3;
    L_0x01c2:
        r10.b(r11);
        if (r12 <= r5) goto L_0x02c0;
    L_0x01c7:
        r0 = a;
        if (r0 == 0) goto L_0x01e3;
    L_0x01cb:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveto ACTIVITY_CREATED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x01e3:
        r0 = r11.mFromLayout;
        if (r0 != 0) goto L_0x02ab;
    L_0x01e7:
        r0 = r11.mContainerId;
        if (r0 == 0) goto L_0x04a1;
    L_0x01eb:
        r0 = r11.mContainerId;
        r1 = -1;
        if (r0 != r1) goto L_0x0211;
    L_0x01f0:
        r0 = new java.lang.IllegalArgumentException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Cannot create fragment ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r2 = " for a container view with no id";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        r10.a(r0);
    L_0x0211:
        r0 = r10.n;
        r1 = r11.mContainerId;
        r0 = r0.onFindViewById(r1);
        r0 = (android.view.ViewGroup) r0;
        if (r0 != 0) goto L_0x0260;
    L_0x021d:
        r1 = r11.mRestored;
        if (r1 != 0) goto L_0x0260;
    L_0x0221:
        r1 = r11.getResources();	 Catch:{ NotFoundException -> 0x0323 }
        r2 = r11.mContainerId;	 Catch:{ NotFoundException -> 0x0323 }
        r1 = r1.getResourceName(r2);	 Catch:{ NotFoundException -> 0x0323 }
    L_0x022b:
        r2 = new java.lang.IllegalArgumentException;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r8 = "No view found for id 0x";
        r4 = r4.append(r8);
        r8 = r11.mContainerId;
        r8 = java.lang.Integer.toHexString(r8);
        r4 = r4.append(r8);
        r8 = " (";
        r4 = r4.append(r8);
        r1 = r4.append(r1);
        r4 = ") for fragment ";
        r1 = r1.append(r4);
        r1 = r1.append(r11);
        r1 = r1.toString();
        r2.<init>(r1);
        r10.a(r2);
    L_0x0260:
        r11.mContainer = r0;
        r1 = r11.mSavedFragmentState;
        r1 = r11.performGetLayoutInflater(r1);
        r2 = r11.mSavedFragmentState;
        r1 = r11.performCreateView(r1, r0, r2);
        r11.mView = r1;
        r1 = r11.mView;
        if (r1 == 0) goto L_0x032a;
    L_0x0274:
        r1 = r11.mView;
        r11.mInnerView = r1;
        r1 = r11.mView;
        r1.setSaveFromParentEnabled(r3);
        if (r0 == 0) goto L_0x0284;
    L_0x027f:
        r1 = r11.mView;
        r0.addView(r1);
    L_0x0284:
        r0 = r11.mHidden;
        if (r0 == 0) goto L_0x028f;
    L_0x0288:
        r0 = r11.mView;
        r1 = 8;
        r0.setVisibility(r1);
    L_0x028f:
        r0 = r11.mView;
        r1 = r11.mSavedFragmentState;
        r11.onViewCreated(r0, r1);
        r0 = r11.mView;
        r1 = r11.mSavedFragmentState;
        r10.a(r11, r0, r1, r3);
        r0 = r11.mView;
        r0 = r0.getVisibility();
        if (r0 != 0) goto L_0x0328;
    L_0x02a5:
        r0 = r11.mContainer;
        if (r0 == 0) goto L_0x0328;
    L_0x02a9:
        r11.mIsNewlyAdded = r5;
    L_0x02ab:
        r0 = r11.mSavedFragmentState;
        r11.performActivityCreated(r0);
        r0 = r11.mSavedFragmentState;
        r10.c(r11, r0, r3);
        r0 = r11.mView;
        if (r0 == 0) goto L_0x02be;
    L_0x02b9:
        r0 = r11.mSavedFragmentState;
        r11.restoreViewState(r0);
    L_0x02be:
        r11.mSavedFragmentState = r7;
    L_0x02c0:
        r0 = 2;
        if (r12 <= r0) goto L_0x02c5;
    L_0x02c3:
        r11.mState = r6;
    L_0x02c5:
        if (r12 <= r6) goto L_0x02e9;
    L_0x02c7:
        r0 = a;
        if (r0 == 0) goto L_0x02e3;
    L_0x02cb:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveto STARTED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x02e3:
        r11.performStart();
        r10.a(r11, r3);
    L_0x02e9:
        if (r12 <= r9) goto L_0x005f;
    L_0x02eb:
        r0 = a;
        if (r0 == 0) goto L_0x0307;
    L_0x02ef:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveto RESUMED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x0307:
        r11.performResume();
        r10.b(r11, r3);
        r11.mSavedFragmentState = r7;
        r11.mSavedViewState = r7;
        goto L_0x005f;
    L_0x0313:
        r0 = r11.mParentFragment;
        r0.onAttachFragment(r11);
        goto L_0x01a4;
    L_0x031a:
        r0 = r11.mSavedFragmentState;
        r11.restoreChildFragmentState(r0);
        r11.mState = r5;
        goto L_0x01c0;
    L_0x0323:
        r1 = move-exception;
        r1 = "unknown";
        goto L_0x022b;
    L_0x0328:
        r5 = r3;
        goto L_0x02a9;
    L_0x032a:
        r11.mInnerView = r7;
        goto L_0x02ab;
    L_0x032e:
        r0 = r11.mState;
        if (r0 <= r12) goto L_0x005f;
    L_0x0332:
        r0 = r11.mState;
        switch(r0) {
            case 1: goto L_0x0339;
            case 2: goto L_0x03cb;
            case 3: goto L_0x03aa;
            case 4: goto L_0x0386;
            case 5: goto L_0x0361;
            default: goto L_0x0337;
        };
    L_0x0337:
        goto L_0x005f;
    L_0x0339:
        if (r12 >= r5) goto L_0x005f;
    L_0x033b:
        r0 = r10.t;
        if (r0 == 0) goto L_0x034f;
    L_0x033f:
        r0 = r11.getAnimatingAway();
        if (r0 == 0) goto L_0x044b;
    L_0x0345:
        r0 = r11.getAnimatingAway();
        r11.setAnimatingAway(r7);
        r0.clearAnimation();
    L_0x034f:
        r0 = r11.getAnimatingAway();
        if (r0 != 0) goto L_0x035b;
    L_0x0355:
        r0 = r11.getAnimator();
        if (r0 == 0) goto L_0x045d;
    L_0x035b:
        r11.setStateAfterAnimating(r12);
        r12 = r5;
        goto L_0x005f;
    L_0x0361:
        r0 = 5;
        if (r12 >= r0) goto L_0x0386;
    L_0x0364:
        r0 = a;
        if (r0 == 0) goto L_0x0380;
    L_0x0368:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom RESUMED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x0380:
        r11.performPause();
        r10.c(r11, r3);
    L_0x0386:
        if (r12 >= r9) goto L_0x03aa;
    L_0x0388:
        r0 = a;
        if (r0 == 0) goto L_0x03a4;
    L_0x038c:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom STARTED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x03a4:
        r11.performStop();
        r10.d(r11, r3);
    L_0x03aa:
        if (r12 >= r6) goto L_0x03cb;
    L_0x03ac:
        r0 = a;
        if (r0 == 0) goto L_0x03c8;
    L_0x03b0:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom STOPPED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x03c8:
        r11.performReallyStop();
    L_0x03cb:
        r0 = 2;
        if (r12 >= r0) goto L_0x0339;
    L_0x03ce:
        r0 = a;
        if (r0 == 0) goto L_0x03ea;
    L_0x03d2:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom ACTIVITY_CREATED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x03ea:
        r0 = r11.mView;
        if (r0 == 0) goto L_0x03fd;
    L_0x03ee:
        r0 = r10.m;
        r0 = r0.onShouldSaveFragmentState(r11);
        if (r0 == 0) goto L_0x03fd;
    L_0x03f6:
        r0 = r11.mSavedViewState;
        if (r0 != 0) goto L_0x03fd;
    L_0x03fa:
        r10.g(r11);
    L_0x03fd:
        r11.performDestroyView();
        r10.e(r11, r3);
        r0 = r11.mView;
        if (r0 == 0) goto L_0x0441;
    L_0x0407:
        r0 = r11.mContainer;
        if (r0 == 0) goto L_0x0441;
    L_0x040b:
        r0 = r11.mView;
        r0.clearAnimation();
        r0 = r11.mContainer;
        r1 = r11.mView;
        r0.endViewTransition(r1);
        r0 = r10.l;
        if (r0 <= 0) goto L_0x049f;
    L_0x041b:
        r0 = r10.t;
        if (r0 != 0) goto L_0x049f;
    L_0x041f:
        r0 = r11.mView;
        r0 = r0.getVisibility();
        if (r0 != 0) goto L_0x049f;
    L_0x0427:
        r0 = r11.mPostponedAlpha;
        r1 = 0;
        r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
        if (r0 < 0) goto L_0x049f;
    L_0x042e:
        r0 = r10.a(r11, r13, r3, r14);
    L_0x0432:
        r1 = 0;
        r11.mPostponedAlpha = r1;
        if (r0 == 0) goto L_0x043a;
    L_0x0437:
        r10.a(r11, r0, r12);
    L_0x043a:
        r0 = r11.mContainer;
        r1 = r11.mView;
        r0.removeView(r1);
    L_0x0441:
        r11.mContainer = r7;
        r11.mView = r7;
        r11.mInnerView = r7;
        r11.mInLayout = r3;
        goto L_0x0339;
    L_0x044b:
        r0 = r11.getAnimator();
        if (r0 == 0) goto L_0x034f;
    L_0x0451:
        r0 = r11.getAnimator();
        r11.setAnimator(r7);
        r0.cancel();
        goto L_0x034f;
    L_0x045d:
        r0 = a;
        if (r0 == 0) goto L_0x0479;
    L_0x0461:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom CREATED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x0479:
        r0 = r11.mRetaining;
        if (r0 != 0) goto L_0x0494;
    L_0x047d:
        r11.performDestroy();
        r10.f(r11, r3);
    L_0x0483:
        r11.performDetach();
        r10.g(r11, r3);
        if (r15 != 0) goto L_0x005f;
    L_0x048b:
        r0 = r11.mRetaining;
        if (r0 != 0) goto L_0x0497;
    L_0x048f:
        r10.f(r11);
        goto L_0x005f;
    L_0x0494:
        r11.mState = r3;
        goto L_0x0483;
    L_0x0497:
        r11.mHost = r7;
        r11.mParentFragment = r7;
        r11.mFragmentManager = r7;
        goto L_0x005f;
    L_0x049f:
        r0 = r7;
        goto L_0x0432;
    L_0x04a1:
        r0 = r7;
        goto L_0x0260;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.k.a(android.support.v4.app.Fragment, int, int, int, boolean):void");
    }

    private void a(@NonNull Fragment fragment, @NonNull c cVar, int i) {
        View view = fragment.mView;
        ViewGroup viewGroup = fragment.mContainer;
        viewGroup.startViewTransition(view);
        fragment.setStateAfterAnimating(i);
        if (cVar.animation != null) {
            Animation animation = cVar.animation;
            fragment.setAnimatingAway(fragment.mView);
            animation.setAnimationListener(new m(this, a(animation), viewGroup, view, fragment));
            b(view, cVar);
            fragment.mView.startAnimation(animation);
            return;
        }
        Animator animator = cVar.animator;
        fragment.setAnimator(cVar.animator);
        animator.addListener(new o(this, viewGroup, view, fragment));
        animator.setTarget(fragment.mView);
        b(fragment.mView, cVar);
        animator.start();
    }

    void a(Fragment fragment) {
        a(fragment, this.l, 0, 0, false);
    }

    void b(Fragment fragment) {
        if (fragment.mFromLayout && !fragment.mPerformedCreateView) {
            fragment.mView = fragment.performCreateView(fragment.performGetLayoutInflater(fragment.mSavedFragmentState), null, fragment.mSavedFragmentState);
            if (fragment.mView != null) {
                fragment.mInnerView = fragment.mView;
                fragment.mView.setSaveFromParentEnabled(false);
                if (fragment.mHidden) {
                    fragment.mView.setVisibility(8);
                }
                fragment.onViewCreated(fragment.mView, fragment.mSavedFragmentState);
                a(fragment, fragment.mView, fragment.mSavedFragmentState, false);
                return;
            }
            fragment.mInnerView = null;
        }
    }

    void c(Fragment fragment) {
        if (fragment.mView != null) {
            c a = a(fragment, fragment.getNextTransition(), !fragment.mHidden, fragment.getNextTransitionStyle());
            if (a == null || a.animator == null) {
                if (a != null) {
                    b(fragment.mView, a);
                    fragment.mView.startAnimation(a.animation);
                    a.animation.start();
                }
                int i = (!fragment.mHidden || fragment.isHideReplaced()) ? 0 : 8;
                fragment.mView.setVisibility(i);
                if (fragment.isHideReplaced()) {
                    fragment.setHideReplaced(false);
                }
            } else {
                a.animator.setTarget(fragment.mView);
                if (!fragment.mHidden) {
                    fragment.mView.setVisibility(0);
                } else if (fragment.isHideReplaced()) {
                    fragment.setHideReplaced(false);
                } else {
                    ViewGroup viewGroup = fragment.mContainer;
                    View view = fragment.mView;
                    viewGroup.startViewTransition(view);
                    a.animator.addListener(new p(this, viewGroup, view, fragment));
                }
                b(fragment.mView, a);
                a.animator.start();
            }
        }
        if (fragment.mAdded && fragment.mHasMenu && fragment.mMenuVisible) {
            this.r = true;
        }
        fragment.mHiddenChanged = false;
        fragment.onHiddenChanged(fragment.mHidden);
    }

    void d(Fragment fragment) {
        if (fragment != null) {
            int i = this.l;
            if (fragment.mRemoving) {
                if (fragment.isInBackStack()) {
                    i = Math.min(i, 1);
                } else {
                    i = Math.min(i, 0);
                }
            }
            a(fragment, i, fragment.getNextTransition(), fragment.getNextTransitionStyle(), false);
            if (fragment.mView != null) {
                Fragment i2 = i(fragment);
                if (i2 != null) {
                    View view = i2.mView;
                    ViewGroup viewGroup = fragment.mContainer;
                    int indexOfChild = viewGroup.indexOfChild(view);
                    i = viewGroup.indexOfChild(fragment.mView);
                    if (i < indexOfChild) {
                        viewGroup.removeViewAt(i);
                        viewGroup.addView(fragment.mView, indexOfChild);
                    }
                }
                if (fragment.mIsNewlyAdded && fragment.mContainer != null) {
                    if (fragment.mPostponedAlpha > 0.0f) {
                        fragment.mView.setAlpha(fragment.mPostponedAlpha);
                    }
                    fragment.mPostponedAlpha = 0.0f;
                    fragment.mIsNewlyAdded = false;
                    c a = a(fragment, fragment.getNextTransition(), true, fragment.getNextTransitionStyle());
                    if (a != null) {
                        b(fragment.mView, a);
                        if (a.animation != null) {
                            fragment.mView.startAnimation(a.animation);
                        } else {
                            a.animator.setTarget(fragment.mView);
                            a.animator.start();
                        }
                    }
                }
            }
            if (fragment.mHiddenChanged) {
                c(fragment);
            }
        }
    }

    void a(int i, boolean z) {
        if (this.m == null && i != 0) {
            throw new IllegalStateException("No activity");
        } else if (z || i != this.l) {
            this.l = i;
            if (this.f != null) {
                Fragment fragment;
                int hasRunningLoaders;
                int size = this.e.size();
                int i2 = 0;
                int i3 = 0;
                while (i2 < size) {
                    fragment = (Fragment) this.e.get(i2);
                    d(fragment);
                    if (fragment.mLoaderManager != null) {
                        hasRunningLoaders = fragment.mLoaderManager.hasRunningLoaders() | i3;
                    } else {
                        hasRunningLoaders = i3;
                    }
                    i2++;
                    i3 = hasRunningLoaders;
                }
                size = this.f.size();
                i2 = 0;
                while (i2 < size) {
                    fragment = (Fragment) this.f.valueAt(i2);
                    if (fragment != null && ((fragment.mRemoving || fragment.mDetached) && !fragment.mIsNewlyAdded)) {
                        d(fragment);
                        if (fragment.mLoaderManager != null) {
                            hasRunningLoaders = fragment.mLoaderManager.hasRunningLoaders() | i3;
                            i2++;
                            i3 = hasRunningLoaders;
                        }
                    }
                    hasRunningLoaders = i3;
                    i2++;
                    i3 = hasRunningLoaders;
                }
                if (i3 == 0) {
                    c();
                }
                if (this.r && this.m != null && this.l == 5) {
                    this.m.onSupportInvalidateOptionsMenu();
                    this.r = false;
                }
            }
        }
    }

    void c() {
        if (this.f != null) {
            for (int i = 0; i < this.f.size(); i++) {
                Fragment fragment = (Fragment) this.f.valueAt(i);
                if (fragment != null) {
                    performPendingDeferredStart(fragment);
                }
            }
        }
    }

    void e(Fragment fragment) {
        if (fragment.mIndex < 0) {
            int i = this.d;
            this.d = i + 1;
            fragment.setIndex(i, this.o);
            if (this.f == null) {
                this.f = new SparseArray();
            }
            this.f.put(fragment.mIndex, fragment);
            if (a) {
                Log.v("FragmentManager", "Allocated fragment index " + fragment);
            }
        }
    }

    void f(Fragment fragment) {
        if (fragment.mIndex >= 0) {
            if (a) {
                Log.v("FragmentManager", "Freeing fragment index " + fragment);
            }
            this.f.put(fragment.mIndex, null);
            this.m.a(fragment.mWho);
            fragment.initState();
        }
    }

    public void addFragment(Fragment fragment, boolean z) {
        if (a) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        e(fragment);
        if (!fragment.mDetached) {
            if (this.e.contains(fragment)) {
                throw new IllegalStateException("Fragment already added: " + fragment);
            }
            synchronized (this.e) {
                this.e.add(fragment);
            }
            fragment.mAdded = true;
            fragment.mRemoving = false;
            if (fragment.mView == null) {
                fragment.mHiddenChanged = false;
            }
            if (fragment.mHasMenu && fragment.mMenuVisible) {
                this.r = true;
            }
            if (z) {
                a(fragment);
            }
        }
    }

    public void removeFragment(Fragment fragment) {
        if (a) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.mBackStackNesting);
        }
        boolean z = !fragment.isInBackStack();
        if (!fragment.mDetached || z) {
            synchronized (this.e) {
                this.e.remove(fragment);
            }
            if (fragment.mHasMenu && fragment.mMenuVisible) {
                this.r = true;
            }
            fragment.mAdded = false;
            fragment.mRemoving = true;
        }
    }

    public void hideFragment(Fragment fragment) {
        boolean z = true;
        if (a) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (!fragment.mHidden) {
            fragment.mHidden = true;
            if (fragment.mHiddenChanged) {
                z = false;
            }
            fragment.mHiddenChanged = z;
        }
    }

    public void showFragment(Fragment fragment) {
        boolean z = false;
        if (a) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.mHidden) {
            fragment.mHidden = false;
            if (!fragment.mHiddenChanged) {
                z = true;
            }
            fragment.mHiddenChanged = z;
        }
    }

    public void detachFragment(Fragment fragment) {
        if (a) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (!fragment.mDetached) {
            fragment.mDetached = true;
            if (fragment.mAdded) {
                if (a) {
                    Log.v("FragmentManager", "remove from detach: " + fragment);
                }
                synchronized (this.e) {
                    this.e.remove(fragment);
                }
                if (fragment.mHasMenu && fragment.mMenuVisible) {
                    this.r = true;
                }
                fragment.mAdded = false;
            }
        }
    }

    public void attachFragment(Fragment fragment) {
        if (a) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (!fragment.mAdded) {
                if (this.e.contains(fragment)) {
                    throw new IllegalStateException("Fragment already added: " + fragment);
                }
                if (a) {
                    Log.v("FragmentManager", "add from attach: " + fragment);
                }
                synchronized (this.e) {
                    this.e.add(fragment);
                }
                fragment.mAdded = true;
                if (fragment.mHasMenu && fragment.mMenuVisible) {
                    this.r = true;
                }
            }
        }
    }

    public Fragment findFragmentById(int i) {
        int size;
        for (size = this.e.size() - 1; size >= 0; size--) {
            Fragment fragment = (Fragment) this.e.get(size);
            if (fragment != null && fragment.mFragmentId == i) {
                return fragment;
            }
        }
        if (this.f != null) {
            for (size = this.f.size() - 1; size >= 0; size--) {
                fragment = (Fragment) this.f.valueAt(size);
                if (fragment != null && fragment.mFragmentId == i) {
                    return fragment;
                }
            }
        }
        return null;
    }

    public Fragment findFragmentByTag(String str) {
        int size;
        Fragment fragment;
        if (str != null) {
            for (size = this.e.size() - 1; size >= 0; size--) {
                fragment = (Fragment) this.e.get(size);
                if (fragment != null && str.equals(fragment.mTag)) {
                    return fragment;
                }
            }
        }
        if (!(this.f == null || str == null)) {
            for (size = this.f.size() - 1; size >= 0; size--) {
                fragment = (Fragment) this.f.valueAt(size);
                if (fragment != null && str.equals(fragment.mTag)) {
                    return fragment;
                }
            }
        }
        return null;
    }

    public Fragment findFragmentByWho(String str) {
        if (!(this.f == null || str == null)) {
            for (int size = this.f.size() - 1; size >= 0; size--) {
                Fragment fragment = (Fragment) this.f.valueAt(size);
                if (fragment != null) {
                    fragment = fragment.findFragmentByWho(str);
                    if (fragment != null) {
                        return fragment;
                    }
                }
            }
        }
        return null;
    }

    private void j() {
        if (this.s) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        } else if (this.u != null) {
            throw new IllegalStateException("Can not perform this action inside of " + this.u);
        }
    }

    public boolean isStateSaved() {
        return this.s;
    }

    public void enqueueAction(f fVar, boolean z) {
        if (!z) {
            j();
        }
        synchronized (this) {
            if (!this.t && this.m != null) {
                if (this.b == null) {
                    this.b = new ArrayList();
                }
                this.b.add(fVar);
                k();
            } else if (z) {
            } else {
                throw new IllegalStateException("Activity has been destroyed");
            }
        }
    }

    private void k() {
        Object obj = 1;
        synchronized (this) {
            Object obj2 = (this.B == null || this.B.isEmpty()) ? null : 1;
            if (this.b == null || this.b.size() != 1) {
                obj = null;
            }
            if (!(obj2 == null && r0 == null)) {
                this.m.c().removeCallbacks(this.D);
                this.m.c().post(this.D);
            }
        }
    }

    public int allocBackStackIndex(c cVar) {
        int size;
        synchronized (this) {
            if (this.j == null || this.j.size() <= 0) {
                if (this.i == null) {
                    this.i = new ArrayList();
                }
                size = this.i.size();
                if (a) {
                    Log.v("FragmentManager", "Setting back stack index " + size + " to " + cVar);
                }
                this.i.add(cVar);
            } else {
                size = ((Integer) this.j.remove(this.j.size() - 1)).intValue();
                if (a) {
                    Log.v("FragmentManager", "Adding back stack index " + size + " with " + cVar);
                }
                this.i.set(size, cVar);
            }
        }
        return size;
    }

    public void setBackStackIndex(int i, c cVar) {
        synchronized (this) {
            if (this.i == null) {
                this.i = new ArrayList();
            }
            int size = this.i.size();
            if (i < size) {
                if (a) {
                    Log.v("FragmentManager", "Setting back stack index " + i + " to " + cVar);
                }
                this.i.set(i, cVar);
            } else {
                while (size < i) {
                    this.i.add(null);
                    if (this.j == null) {
                        this.j = new ArrayList();
                    }
                    if (a) {
                        Log.v("FragmentManager", "Adding available back stack index " + size);
                    }
                    this.j.add(Integer.valueOf(size));
                    size++;
                }
                if (a) {
                    Log.v("FragmentManager", "Adding back stack index " + i + " with " + cVar);
                }
                this.i.add(cVar);
            }
        }
    }

    public void freeBackStackIndex(int i) {
        synchronized (this) {
            this.i.set(i, null);
            if (this.j == null) {
                this.j = new ArrayList();
            }
            if (a) {
                Log.v("FragmentManager", "Freeing back stack index " + i);
            }
            this.j.add(Integer.valueOf(i));
        }
    }

    private void a(boolean z) {
        if (this.c) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        } else if (Looper.myLooper() != this.m.c().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        } else {
            if (!z) {
                j();
            }
            if (this.w == null) {
                this.w = new ArrayList();
                this.x = new ArrayList();
            }
            this.c = true;
            try {
                a(null, null);
            } finally {
                this.c = false;
            }
        }
    }

    public void execSingleAction(f fVar, boolean z) {
        if (!z || (this.m != null && !this.t)) {
            a(z);
            if (fVar.generateOps(this.w, this.x)) {
                this.c = true;
                try {
                    b(this.w, this.x);
                } finally {
                    l();
                }
            }
            d();
            o();
        }
    }

    private void l() {
        this.c = false;
        this.x.clear();
        this.w.clear();
    }

    public boolean execPendingActions() {
        a(true);
        boolean z = false;
        while (c(this.w, this.x)) {
            this.c = true;
            try {
                b(this.w, this.x);
                l();
                z = true;
            } catch (Throwable th) {
                l();
                throw th;
            }
        }
        d();
        o();
        return z;
    }

    private void a(ArrayList<c> arrayList, ArrayList<Boolean> arrayList2) {
        int i = 0;
        int size = this.B == null ? 0 : this.B.size();
        while (i < size) {
            int indexOf;
            int i2;
            h hVar = (h) this.B.get(i);
            if (!(arrayList == null || hVar.a)) {
                indexOf = arrayList.indexOf(hVar.b);
                if (indexOf != -1 && ((Boolean) arrayList2.get(indexOf)).booleanValue()) {
                    hVar.cancelTransaction();
                    i2 = i;
                    indexOf = size;
                    i = i2 + 1;
                    size = indexOf;
                }
            }
            if (hVar.isReady() || (arrayList != null && hVar.b.a(arrayList, 0, arrayList.size()))) {
                this.B.remove(i);
                i--;
                size--;
                if (!(arrayList == null || hVar.a)) {
                    indexOf = arrayList.indexOf(hVar.b);
                    if (indexOf != -1 && ((Boolean) arrayList2.get(indexOf)).booleanValue()) {
                        hVar.cancelTransaction();
                        i2 = i;
                        indexOf = size;
                        i = i2 + 1;
                        size = indexOf;
                    }
                }
                hVar.completeTransaction();
            }
            i2 = i;
            indexOf = size;
            i = i2 + 1;
            size = indexOf;
        }
    }

    private void b(ArrayList<c> arrayList, ArrayList<Boolean> arrayList2) {
        int i = 0;
        if (arrayList != null && !arrayList.isEmpty()) {
            if (arrayList2 == null || arrayList.size() != arrayList2.size()) {
                throw new IllegalStateException("Internal error with the back stack records");
            }
            a((ArrayList) arrayList, (ArrayList) arrayList2);
            int size = arrayList.size();
            int i2 = 0;
            while (i < size) {
                int i3;
                if (((c) arrayList.get(i)).t) {
                    i3 = i;
                } else {
                    if (i2 != i) {
                        a((ArrayList) arrayList, (ArrayList) arrayList2, i2, i);
                    }
                    i2 = i + 1;
                    if (((Boolean) arrayList2.get(i)).booleanValue()) {
                        while (i2 < size && ((Boolean) arrayList2.get(i2)).booleanValue() && !((c) arrayList.get(i2)).t) {
                            i2++;
                        }
                    }
                    i3 = i2;
                    a((ArrayList) arrayList, (ArrayList) arrayList2, i, i3);
                    i2 = i3;
                    i3--;
                }
                i = i3 + 1;
            }
            if (i2 != size) {
                a((ArrayList) arrayList, (ArrayList) arrayList2, i2, size);
            }
        }
    }

    private void a(ArrayList<c> arrayList, ArrayList<Boolean> arrayList2, int i, int i2) {
        int a;
        boolean z = ((c) arrayList.get(i)).t;
        if (this.y == null) {
            this.y = new ArrayList();
        } else {
            this.y.clear();
        }
        this.y.addAll(this.e);
        int i3 = i;
        Fragment primaryNavigationFragment = getPrimaryNavigationFragment();
        boolean z2 = false;
        while (i3 < i2) {
            Fragment b;
            boolean z3;
            c cVar = (c) arrayList.get(i3);
            if (((Boolean) arrayList2.get(i3)).booleanValue()) {
                b = cVar.b(this.y, primaryNavigationFragment);
            } else {
                b = cVar.a(this.y, primaryNavigationFragment);
            }
            if (z2 || cVar.i) {
                z3 = true;
            } else {
                z3 = false;
            }
            i3++;
            primaryNavigationFragment = b;
            z2 = z3;
        }
        this.y.clear();
        if (!z) {
            u.a(this, (ArrayList) arrayList, (ArrayList) arrayList2, i, i2, false);
        }
        b(arrayList, arrayList2, i, i2);
        if (z) {
            ArraySet arraySet = new ArraySet();
            b(arraySet);
            a = a((ArrayList) arrayList, (ArrayList) arrayList2, i, i2, arraySet);
            a(arraySet);
        } else {
            a = i2;
        }
        if (a != i && z) {
            u.a(this, (ArrayList) arrayList, (ArrayList) arrayList2, i, a, true);
            a(this.l, true);
        }
        while (i < i2) {
            cVar = (c) arrayList.get(i);
            if (((Boolean) arrayList2.get(i)).booleanValue() && cVar.m >= 0) {
                freeBackStackIndex(cVar.m);
                cVar.m = -1;
            }
            cVar.runOnCommitRunnables();
            i++;
        }
        if (z2) {
            e();
        }
    }

    private void a(ArraySet<Fragment> arraySet) {
        int size = arraySet.size();
        for (int i = 0; i < size; i++) {
            Fragment fragment = (Fragment) arraySet.valueAt(i);
            if (!fragment.mAdded) {
                View view = fragment.getView();
                fragment.mPostponedAlpha = view.getAlpha();
                view.setAlpha(0.0f);
            }
        }
    }

    private int a(ArrayList<c> arrayList, ArrayList<Boolean> arrayList2, int i, int i2, ArraySet<Fragment> arraySet) {
        int i3 = i2 - 1;
        int i4 = i2;
        while (i3 >= i) {
            boolean z;
            int i5;
            c cVar = (c) arrayList.get(i3);
            boolean booleanValue = ((Boolean) arrayList2.get(i3)).booleanValue();
            if (!cVar.b() || cVar.a(arrayList, i3 + 1, i2)) {
                z = false;
            } else {
                z = true;
            }
            if (z) {
                if (this.B == null) {
                    this.B = new ArrayList();
                }
                b hVar = new h(cVar, booleanValue);
                this.B.add(hVar);
                cVar.a(hVar);
                if (booleanValue) {
                    cVar.a();
                } else {
                    cVar.b(false);
                }
                int i6 = i4 - 1;
                if (i3 != i6) {
                    arrayList.remove(i3);
                    arrayList.add(i6, cVar);
                }
                b((ArraySet) arraySet);
                i5 = i6;
            } else {
                i5 = i4;
            }
            i3--;
            i4 = i5;
        }
        return i4;
    }

    private void a(c cVar, boolean z, boolean z2, boolean z3) {
        if (z) {
            cVar.b(z3);
        } else {
            cVar.a();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(cVar);
        arrayList2.add(Boolean.valueOf(z));
        if (z2) {
            u.a(this, arrayList, arrayList2, 0, 1, true);
        }
        if (z3) {
            a(this.l, true);
        }
        if (this.f != null) {
            int size = this.f.size();
            for (int i = 0; i < size; i++) {
                Fragment fragment = (Fragment) this.f.valueAt(i);
                if (fragment != null && fragment.mView != null && fragment.mIsNewlyAdded && cVar.b(fragment.mContainerId)) {
                    if (fragment.mPostponedAlpha > 0.0f) {
                        fragment.mView.setAlpha(fragment.mPostponedAlpha);
                    }
                    if (z3) {
                        fragment.mPostponedAlpha = 0.0f;
                    } else {
                        fragment.mPostponedAlpha = -1.0f;
                        fragment.mIsNewlyAdded = false;
                    }
                }
            }
        }
    }

    private Fragment i(Fragment fragment) {
        ViewGroup viewGroup = fragment.mContainer;
        View view = fragment.mView;
        if (viewGroup == null || view == null) {
            return null;
        }
        for (int indexOf = this.e.indexOf(fragment) - 1; indexOf >= 0; indexOf--) {
            Fragment fragment2 = (Fragment) this.e.get(indexOf);
            if (fragment2.mContainer == viewGroup && fragment2.mView != null) {
                return fragment2;
            }
        }
        return null;
    }

    private static void b(ArrayList<c> arrayList, ArrayList<Boolean> arrayList2, int i, int i2) {
        while (i < i2) {
            c cVar = (c) arrayList.get(i);
            if (((Boolean) arrayList2.get(i)).booleanValue()) {
                cVar.a(-1);
                cVar.b(i == i2 + -1);
            } else {
                cVar.a(1);
                cVar.a();
            }
            i++;
        }
    }

    private void b(ArraySet<Fragment> arraySet) {
        if (this.l >= 1) {
            int min = Math.min(this.l, 4);
            int size = this.e.size();
            for (int i = 0; i < size; i++) {
                Fragment fragment = (Fragment) this.e.get(i);
                if (fragment.mState < min) {
                    a(fragment, min, fragment.getNextAnim(), fragment.getNextTransition(), false);
                    if (!(fragment.mView == null || fragment.mHidden || !fragment.mIsNewlyAdded)) {
                        arraySet.add(fragment);
                    }
                }
            }
        }
    }

    private void m() {
        if (this.B != null) {
            while (!this.B.isEmpty()) {
                ((h) this.B.remove(0)).completeTransaction();
            }
        }
    }

    private void n() {
        int i;
        if (this.f == null) {
            i = 0;
        } else {
            i = this.f.size();
        }
        for (int i2 = 0; i2 < i; i2++) {
            Fragment fragment = (Fragment) this.f.valueAt(i2);
            if (fragment != null) {
                if (fragment.getAnimatingAway() != null) {
                    int stateAfterAnimating = fragment.getStateAfterAnimating();
                    View animatingAway = fragment.getAnimatingAway();
                    Animation animation = animatingAway.getAnimation();
                    if (animation != null) {
                        animation.cancel();
                        animatingAway.clearAnimation();
                    }
                    fragment.setAnimatingAway(null);
                    a(fragment, stateAfterAnimating, 0, 0, false);
                } else if (fragment.getAnimator() != null) {
                    fragment.getAnimator().end();
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean c(java.util.ArrayList<android.support.v4.app.c> r5, java.util.ArrayList<java.lang.Boolean> r6) {
        /*
        r4 = this;
        r0 = 0;
        monitor-enter(r4);
        r1 = r4.b;	 Catch:{ all -> 0x003e }
        if (r1 == 0) goto L_0x000e;
    L_0x0006:
        r1 = r4.b;	 Catch:{ all -> 0x003e }
        r1 = r1.size();	 Catch:{ all -> 0x003e }
        if (r1 != 0) goto L_0x0010;
    L_0x000e:
        monitor-exit(r4);	 Catch:{ all -> 0x003e }
    L_0x000f:
        return r0;
    L_0x0010:
        r1 = r4.b;	 Catch:{ all -> 0x003e }
        r3 = r1.size();	 Catch:{ all -> 0x003e }
        r2 = r0;
        r1 = r0;
    L_0x0018:
        if (r2 >= r3) goto L_0x002b;
    L_0x001a:
        r0 = r4.b;	 Catch:{ all -> 0x003e }
        r0 = r0.get(r2);	 Catch:{ all -> 0x003e }
        r0 = (android.support.v4.app.k.f) r0;	 Catch:{ all -> 0x003e }
        r0 = r0.generateOps(r5, r6);	 Catch:{ all -> 0x003e }
        r1 = r1 | r0;
        r0 = r2 + 1;
        r2 = r0;
        goto L_0x0018;
    L_0x002b:
        r0 = r4.b;	 Catch:{ all -> 0x003e }
        r0.clear();	 Catch:{ all -> 0x003e }
        r0 = r4.m;	 Catch:{ all -> 0x003e }
        r0 = r0.c();	 Catch:{ all -> 0x003e }
        r2 = r4.D;	 Catch:{ all -> 0x003e }
        r0.removeCallbacks(r2);	 Catch:{ all -> 0x003e }
        monitor-exit(r4);	 Catch:{ all -> 0x003e }
        r0 = r1;
        goto L_0x000f;
    L_0x003e:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x003e }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.k.c(java.util.ArrayList, java.util.ArrayList):boolean");
    }

    void d() {
        if (this.v) {
            int i = 0;
            for (int i2 = 0; i2 < this.f.size(); i2++) {
                Fragment fragment = (Fragment) this.f.valueAt(i2);
                if (!(fragment == null || fragment.mLoaderManager == null)) {
                    i |= fragment.mLoaderManager.hasRunningLoaders();
                }
            }
            if (i == 0) {
                this.v = false;
                c();
            }
        }
    }

    void e() {
        if (this.k != null) {
            for (int i = 0; i < this.k.size(); i++) {
                ((OnBackStackChangedListener) this.k.get(i)).onBackStackChanged();
            }
        }
    }

    void a(c cVar) {
        if (this.g == null) {
            this.g = new ArrayList();
        }
        this.g.add(cVar);
    }

    boolean a(ArrayList<c> arrayList, ArrayList<Boolean> arrayList2, String str, int i, int i2) {
        if (this.g == null) {
            return false;
        }
        int size;
        if (str == null && i < 0 && (i2 & 1) == 0) {
            size = this.g.size() - 1;
            if (size < 0) {
                return false;
            }
            arrayList.add(this.g.remove(size));
            arrayList2.add(Boolean.valueOf(true));
        } else {
            int size2;
            size = -1;
            if (str != null || i >= 0) {
                c cVar;
                size2 = this.g.size() - 1;
                while (size2 >= 0) {
                    cVar = (c) this.g.get(size2);
                    if ((str != null && str.equals(cVar.getName())) || (i >= 0 && i == cVar.m)) {
                        break;
                    }
                    size2--;
                }
                if (size2 < 0) {
                    return false;
                }
                if ((i2 & 1) != 0) {
                    size2--;
                    while (size2 >= 0) {
                        cVar = (c) this.g.get(size2);
                        if ((str == null || !str.equals(cVar.getName())) && (i < 0 || i != cVar.m)) {
                            break;
                        }
                        size2--;
                    }
                }
                size = size2;
            }
            if (size == this.g.size() - 1) {
                return false;
            }
            for (size2 = this.g.size() - 1; size2 > size; size2--) {
                arrayList.add(this.g.remove(size2));
                arrayList2.add(Boolean.valueOf(true));
            }
        }
        return true;
    }

    FragmentManagerNonConfig f() {
        a(this.C);
        return this.C;
    }

    private static void a(FragmentManagerNonConfig fragmentManagerNonConfig) {
        if (fragmentManagerNonConfig != null) {
            List<Fragment> a = fragmentManagerNonConfig.a();
            if (a != null) {
                for (Fragment fragment : a) {
                    fragment.mRetaining = true;
                }
            }
            List<FragmentManagerNonConfig> b = fragmentManagerNonConfig.b();
            if (b != null) {
                for (FragmentManagerNonConfig a2 : b) {
                    a(a2);
                }
            }
        }
    }

    void g() {
        List list;
        List list2;
        if (this.f != null) {
            int i = 0;
            list = null;
            list2 = null;
            while (i < this.f.size()) {
                ArrayList arrayList;
                ArrayList arrayList2;
                Fragment fragment = (Fragment) this.f.valueAt(i);
                if (fragment != null) {
                    ArrayList arrayList3;
                    Object obj;
                    if (fragment.mRetainInstance) {
                        if (list2 == null) {
                            arrayList3 = new ArrayList();
                        }
                        arrayList3.add(fragment);
                        fragment.mTargetIndex = fragment.mTarget != null ? fragment.mTarget.mIndex : -1;
                        if (a) {
                            Log.v("FragmentManager", "retainNonConfig: keeping retained " + fragment);
                        }
                    }
                    if (fragment.mChildFragmentManager != null) {
                        fragment.mChildFragmentManager.g();
                        obj = fragment.mChildFragmentManager.C;
                    } else {
                        FragmentManagerNonConfig fragmentManagerNonConfig = fragment.mChildNonConfig;
                    }
                    if (list == null && obj != null) {
                        list = new ArrayList(this.f.size());
                        for (int i2 = 0; i2 < i; i2++) {
                            list.add(null);
                        }
                    }
                    arrayList = list;
                    if (arrayList != null) {
                        arrayList.add(obj);
                    }
                    arrayList2 = arrayList3;
                } else {
                    List list3 = list;
                    list = list2;
                }
                i++;
                Object obj2 = arrayList2;
                Object obj3 = arrayList;
            }
        } else {
            list = null;
            list2 = null;
        }
        if (list2 == null && list == null) {
            this.C = null;
        } else {
            this.C = new FragmentManagerNonConfig(list2, list);
        }
    }

    void g(Fragment fragment) {
        if (fragment.mInnerView != null) {
            if (this.A == null) {
                this.A = new SparseArray();
            } else {
                this.A.clear();
            }
            fragment.mInnerView.saveHierarchyState(this.A);
            if (this.A.size() > 0) {
                fragment.mSavedViewState = this.A;
                this.A = null;
            }
        }
    }

    Bundle h(Fragment fragment) {
        Bundle bundle;
        if (this.z == null) {
            this.z = new Bundle();
        }
        fragment.performSaveInstanceState(this.z);
        d(fragment, this.z, false);
        if (this.z.isEmpty()) {
            bundle = null;
        } else {
            bundle = this.z;
            this.z = null;
        }
        if (fragment.mView != null) {
            g(fragment);
        }
        if (fragment.mSavedViewState != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", fragment.mSavedViewState);
        }
        if (!fragment.mUserVisibleHint) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", fragment.mUserVisibleHint);
        }
        return bundle;
    }

    Parcelable h() {
        BackStackState[] backStackStateArr = null;
        m();
        n();
        execPendingActions();
        this.s = true;
        this.C = null;
        if (this.f == null || this.f.size() <= 0) {
            return null;
        }
        int size = this.f.size();
        FragmentState[] fragmentStateArr = new FragmentState[size];
        int i = 0;
        boolean z = false;
        while (i < size) {
            boolean z2;
            Fragment fragment = (Fragment) this.f.valueAt(i);
            if (fragment != null) {
                if (fragment.mIndex < 0) {
                    a(new IllegalStateException("Failure saving state: active " + fragment + " has cleared index: " + fragment.mIndex));
                }
                FragmentState fragmentState = new FragmentState(fragment);
                fragmentStateArr[i] = fragmentState;
                if (fragment.mState <= 0 || fragmentState.k != null) {
                    fragmentState.k = fragment.mSavedFragmentState;
                } else {
                    fragmentState.k = h(fragment);
                    if (fragment.mTarget != null) {
                        if (fragment.mTarget.mIndex < 0) {
                            a(new IllegalStateException("Failure saving state: " + fragment + " has target not in fragment manager: " + fragment.mTarget));
                        }
                        if (fragmentState.k == null) {
                            fragmentState.k = new Bundle();
                        }
                        putFragment(fragmentState.k, "android:target_state", fragment.mTarget);
                        if (fragment.mTargetRequestCode != 0) {
                            fragmentState.k.putInt("android:target_req_state", fragment.mTargetRequestCode);
                        }
                    }
                }
                if (a) {
                    Log.v("FragmentManager", "Saved state of " + fragment + ": " + fragmentState.k);
                }
                z2 = true;
            } else {
                z2 = z;
            }
            i++;
            z = z2;
        }
        if (z) {
            int[] iArr;
            int i2;
            i = this.e.size();
            if (i > 0) {
                iArr = new int[i];
                for (i2 = 0; i2 < i; i2++) {
                    iArr[i2] = ((Fragment) this.e.get(i2)).mIndex;
                    if (iArr[i2] < 0) {
                        a(new IllegalStateException("Failure saving state: active " + this.e.get(i2) + " has cleared index: " + iArr[i2]));
                    }
                    if (a) {
                        Log.v("FragmentManager", "saveAllState: adding fragment #" + i2 + ": " + this.e.get(i2));
                    }
                }
            } else {
                iArr = null;
            }
            if (this.g != null) {
                i = this.g.size();
                if (i > 0) {
                    backStackStateArr = new BackStackState[i];
                    for (i2 = 0; i2 < i; i2++) {
                        backStackStateArr[i2] = new BackStackState((c) this.g.get(i2));
                        if (a) {
                            Log.v("FragmentManager", "saveAllState: adding back stack #" + i2 + ": " + this.g.get(i2));
                        }
                    }
                }
            }
            FragmentManagerState fragmentManagerState = new FragmentManagerState();
            fragmentManagerState.a = fragmentStateArr;
            fragmentManagerState.b = iArr;
            fragmentManagerState.c = backStackStateArr;
            if (this.p != null) {
                fragmentManagerState.d = this.p.mIndex;
            }
            fragmentManagerState.e = this.d;
            g();
            return fragmentManagerState;
        } else if (!a) {
            return null;
        } else {
            Log.v("FragmentManager", "saveAllState: no fragments!");
            return null;
        }
    }

    void a(Parcelable parcelable, FragmentManagerNonConfig fragmentManagerNonConfig) {
        if (parcelable != null) {
            FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
            if (fragmentManagerState.a != null) {
                int size;
                Fragment fragment;
                int i;
                List list;
                if (fragmentManagerNonConfig != null) {
                    List a = fragmentManagerNonConfig.a();
                    List b = fragmentManagerNonConfig.b();
                    if (a != null) {
                        size = a.size();
                    } else {
                        boolean z = false;
                    }
                    for (int i2 = 0; i2 < size; i2++) {
                        fragment = (Fragment) a.get(i2);
                        if (a) {
                            Log.v("FragmentManager", "restoreAllState: re-attaching retained " + fragment);
                        }
                        i = 0;
                        while (i < fragmentManagerState.a.length && fragmentManagerState.a[i].b != fragment.mIndex) {
                            i++;
                        }
                        if (i == fragmentManagerState.a.length) {
                            a(new IllegalStateException("Could not find active fragment with index " + fragment.mIndex));
                        }
                        FragmentState fragmentState = fragmentManagerState.a[i];
                        fragmentState.l = fragment;
                        fragment.mSavedViewState = null;
                        fragment.mBackStackNesting = 0;
                        fragment.mInLayout = false;
                        fragment.mAdded = false;
                        fragment.mTarget = null;
                        if (fragmentState.k != null) {
                            fragmentState.k.setClassLoader(this.m.b().getClassLoader());
                            fragment.mSavedViewState = fragmentState.k.getSparseParcelableArray("android:view_state");
                            fragment.mSavedFragmentState = fragmentState.k;
                        }
                    }
                    list = b;
                } else {
                    list = null;
                }
                this.f = new SparseArray(fragmentManagerState.a.length);
                i = 0;
                while (i < fragmentManagerState.a.length) {
                    FragmentState fragmentState2 = fragmentManagerState.a[i];
                    if (fragmentState2 != null) {
                        FragmentManagerNonConfig fragmentManagerNonConfig2;
                        if (list == null || i >= list.size()) {
                            fragmentManagerNonConfig2 = null;
                        } else {
                            fragmentManagerNonConfig2 = (FragmentManagerNonConfig) list.get(i);
                        }
                        fragment = fragmentState2.instantiate(this.m, this.n, this.o, fragmentManagerNonConfig2);
                        if (a) {
                            Log.v("FragmentManager", "restoreAllState: active #" + i + ": " + fragment);
                        }
                        this.f.put(fragment.mIndex, fragment);
                        fragmentState2.l = null;
                    }
                    i++;
                }
                if (fragmentManagerNonConfig != null) {
                    List a2 = fragmentManagerNonConfig.a();
                    if (a2 != null) {
                        i = a2.size();
                    } else {
                        boolean z2 = false;
                    }
                    for (int i3 = 0; i3 < i; i3++) {
                        fragment = (Fragment) a2.get(i3);
                        if (fragment.mTargetIndex >= 0) {
                            fragment.mTarget = (Fragment) this.f.get(fragment.mTargetIndex);
                            if (fragment.mTarget == null) {
                                Log.w("FragmentManager", "Re-attaching retained fragment " + fragment + " target no longer exists: " + fragment.mTargetIndex);
                            }
                        }
                    }
                }
                this.e.clear();
                if (fragmentManagerState.b != null) {
                    for (size = 0; size < fragmentManagerState.b.length; size++) {
                        fragment = (Fragment) this.f.get(fragmentManagerState.b[size]);
                        if (fragment == null) {
                            a(new IllegalStateException("No instantiated fragment for index #" + fragmentManagerState.b[size]));
                        }
                        fragment.mAdded = true;
                        if (a) {
                            Log.v("FragmentManager", "restoreAllState: added #" + size + ": " + fragment);
                        }
                        if (this.e.contains(fragment)) {
                            throw new IllegalStateException("Already added!");
                        }
                        synchronized (this.e) {
                            this.e.add(fragment);
                        }
                    }
                }
                if (fragmentManagerState.c != null) {
                    this.g = new ArrayList(fragmentManagerState.c.length);
                    for (int i4 = 0; i4 < fragmentManagerState.c.length; i4++) {
                        c instantiate = fragmentManagerState.c[i4].instantiate(this);
                        if (a) {
                            Log.v("FragmentManager", "restoreAllState: back stack #" + i4 + " (index " + instantiate.m + "): " + instantiate);
                            PrintWriter printWriter = new PrintWriter(new LogWriter("FragmentManager"));
                            instantiate.dump("  ", printWriter, false);
                            printWriter.close();
                        }
                        this.g.add(instantiate);
                        if (instantiate.m >= 0) {
                            setBackStackIndex(instantiate.m, instantiate);
                        }
                    }
                } else {
                    this.g = null;
                }
                if (fragmentManagerState.d >= 0) {
                    this.p = (Fragment) this.f.get(fragmentManagerState.d);
                }
                this.d = fragmentManagerState.e;
            }
        }
    }

    private void o() {
        if (this.f != null) {
            for (int size = this.f.size() - 1; size >= 0; size--) {
                if (this.f.valueAt(size) == null) {
                    this.f.delete(this.f.keyAt(size));
                }
            }
        }
    }

    public void attachController(FragmentHostCallback fragmentHostCallback, FragmentContainer fragmentContainer, Fragment fragment) {
        if (this.m != null) {
            throw new IllegalStateException("Already attached");
        }
        this.m = fragmentHostCallback;
        this.n = fragmentContainer;
        this.o = fragment;
    }

    public void noteStateNotSaved() {
        this.C = null;
        this.s = false;
        int size = this.e.size();
        for (int i = 0; i < size; i++) {
            Fragment fragment = (Fragment) this.e.get(i);
            if (fragment != null) {
                fragment.noteStateNotSaved();
            }
        }
    }

    public void dispatchCreate() {
        this.s = false;
        b(1);
    }

    public void dispatchActivityCreated() {
        this.s = false;
        b(2);
    }

    public void dispatchStart() {
        this.s = false;
        b(4);
    }

    public void dispatchResume() {
        this.s = false;
        b(5);
    }

    public void dispatchPause() {
        b(4);
    }

    public void dispatchStop() {
        this.s = true;
        b(3);
    }

    public void dispatchReallyStop() {
        b(2);
    }

    public void dispatchDestroyView() {
        b(1);
    }

    public void dispatchDestroy() {
        this.t = true;
        execPendingActions();
        b(0);
        this.m = null;
        this.n = null;
        this.o = null;
    }

    private void b(int i) {
        try {
            this.c = true;
            a(i, false);
            execPendingActions();
        } finally {
            this.c = false;
        }
    }

    public void dispatchMultiWindowModeChanged(boolean z) {
        for (int size = this.e.size() - 1; size >= 0; size--) {
            Fragment fragment = (Fragment) this.e.get(size);
            if (fragment != null) {
                fragment.performMultiWindowModeChanged(z);
            }
        }
    }

    public void dispatchPictureInPictureModeChanged(boolean z) {
        for (int size = this.e.size() - 1; size >= 0; size--) {
            Fragment fragment = (Fragment) this.e.get(size);
            if (fragment != null) {
                fragment.performPictureInPictureModeChanged(z);
            }
        }
    }

    public void dispatchConfigurationChanged(Configuration configuration) {
        for (int i = 0; i < this.e.size(); i++) {
            Fragment fragment = (Fragment) this.e.get(i);
            if (fragment != null) {
                fragment.performConfigurationChanged(configuration);
            }
        }
    }

    public void dispatchLowMemory() {
        for (int i = 0; i < this.e.size(); i++) {
            Fragment fragment = (Fragment) this.e.get(i);
            if (fragment != null) {
                fragment.performLowMemory();
            }
        }
    }

    public boolean dispatchCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        int i = 0;
        if (this.l < 1) {
            return false;
        }
        ArrayList arrayList = null;
        int i2 = 0;
        boolean z = false;
        while (i2 < this.e.size()) {
            boolean z2;
            Fragment fragment = (Fragment) this.e.get(i2);
            if (fragment == null || !fragment.performCreateOptionsMenu(menu, menuInflater)) {
                z2 = z;
            } else {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(fragment);
                z2 = true;
            }
            i2++;
            z = z2;
        }
        if (this.h != null) {
            while (i < this.h.size()) {
                fragment = (Fragment) this.h.get(i);
                if (arrayList == null || !arrayList.contains(fragment)) {
                    fragment.onDestroyOptionsMenu();
                }
                i++;
            }
        }
        this.h = arrayList;
        return z;
    }

    public boolean dispatchPrepareOptionsMenu(Menu menu) {
        if (this.l < 1) {
            return false;
        }
        boolean z = false;
        for (int i = 0; i < this.e.size(); i++) {
            Fragment fragment = (Fragment) this.e.get(i);
            if (fragment != null && fragment.performPrepareOptionsMenu(menu)) {
                z = true;
            }
        }
        return z;
    }

    public boolean dispatchOptionsItemSelected(MenuItem menuItem) {
        if (this.l < 1) {
            return false;
        }
        for (int i = 0; i < this.e.size(); i++) {
            Fragment fragment = (Fragment) this.e.get(i);
            if (fragment != null && fragment.performOptionsItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public boolean dispatchContextItemSelected(MenuItem menuItem) {
        if (this.l < 1) {
            return false;
        }
        for (int i = 0; i < this.e.size(); i++) {
            Fragment fragment = (Fragment) this.e.get(i);
            if (fragment != null && fragment.performContextItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void dispatchOptionsMenuClosed(Menu menu) {
        if (this.l >= 1) {
            for (int i = 0; i < this.e.size(); i++) {
                Fragment fragment = (Fragment) this.e.get(i);
                if (fragment != null) {
                    fragment.performOptionsMenuClosed(menu);
                }
            }
        }
    }

    public void setPrimaryNavigationFragment(Fragment fragment) {
        if (fragment == null || (this.f.get(fragment.mIndex) == fragment && (fragment.mHost == null || fragment.getFragmentManager() == this))) {
            this.p = fragment;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public Fragment getPrimaryNavigationFragment() {
        return this.p;
    }

    public void registerFragmentLifecycleCallbacks(FragmentLifecycleCallbacks fragmentLifecycleCallbacks, boolean z) {
        this.I.add(new Pair(fragmentLifecycleCallbacks, Boolean.valueOf(z)));
    }

    public void unregisterFragmentLifecycleCallbacks(FragmentLifecycleCallbacks fragmentLifecycleCallbacks) {
        synchronized (this.I) {
            int size = this.I.size();
            for (int i = 0; i < size; i++) {
                if (((Pair) this.I.get(i)).first == fragmentLifecycleCallbacks) {
                    this.I.remove(i);
                    break;
                }
            }
        }
    }

    void a(Fragment fragment, Context context, boolean z) {
        if (this.o != null) {
            FragmentManager fragmentManager = this.o.getFragmentManager();
            if (fragmentManager instanceof k) {
                ((k) fragmentManager).a(fragment, context, true);
            }
        }
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            if (!z || ((Boolean) pair.second).booleanValue()) {
                ((FragmentLifecycleCallbacks) pair.first).onFragmentPreAttached(this, fragment, context);
            }
        }
    }

    void b(Fragment fragment, Context context, boolean z) {
        if (this.o != null) {
            FragmentManager fragmentManager = this.o.getFragmentManager();
            if (fragmentManager instanceof k) {
                ((k) fragmentManager).b(fragment, context, true);
            }
        }
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            if (!z || ((Boolean) pair.second).booleanValue()) {
                ((FragmentLifecycleCallbacks) pair.first).onFragmentAttached(this, fragment, context);
            }
        }
    }

    void a(Fragment fragment, Bundle bundle, boolean z) {
        if (this.o != null) {
            FragmentManager fragmentManager = this.o.getFragmentManager();
            if (fragmentManager instanceof k) {
                ((k) fragmentManager).a(fragment, bundle, true);
            }
        }
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            if (!z || ((Boolean) pair.second).booleanValue()) {
                ((FragmentLifecycleCallbacks) pair.first).onFragmentPreCreated(this, fragment, bundle);
            }
        }
    }

    void b(Fragment fragment, Bundle bundle, boolean z) {
        if (this.o != null) {
            FragmentManager fragmentManager = this.o.getFragmentManager();
            if (fragmentManager instanceof k) {
                ((k) fragmentManager).b(fragment, bundle, true);
            }
        }
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            if (!z || ((Boolean) pair.second).booleanValue()) {
                ((FragmentLifecycleCallbacks) pair.first).onFragmentCreated(this, fragment, bundle);
            }
        }
    }

    void c(Fragment fragment, Bundle bundle, boolean z) {
        if (this.o != null) {
            FragmentManager fragmentManager = this.o.getFragmentManager();
            if (fragmentManager instanceof k) {
                ((k) fragmentManager).c(fragment, bundle, true);
            }
        }
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            if (!z || ((Boolean) pair.second).booleanValue()) {
                ((FragmentLifecycleCallbacks) pair.first).onFragmentActivityCreated(this, fragment, bundle);
            }
        }
    }

    void a(Fragment fragment, View view, Bundle bundle, boolean z) {
        if (this.o != null) {
            FragmentManager fragmentManager = this.o.getFragmentManager();
            if (fragmentManager instanceof k) {
                ((k) fragmentManager).a(fragment, view, bundle, true);
            }
        }
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            if (!z || ((Boolean) pair.second).booleanValue()) {
                ((FragmentLifecycleCallbacks) pair.first).onFragmentViewCreated(this, fragment, view, bundle);
            }
        }
    }

    void a(Fragment fragment, boolean z) {
        if (this.o != null) {
            FragmentManager fragmentManager = this.o.getFragmentManager();
            if (fragmentManager instanceof k) {
                ((k) fragmentManager).a(fragment, true);
            }
        }
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            if (!z || ((Boolean) pair.second).booleanValue()) {
                ((FragmentLifecycleCallbacks) pair.first).onFragmentStarted(this, fragment);
            }
        }
    }

    void b(Fragment fragment, boolean z) {
        if (this.o != null) {
            FragmentManager fragmentManager = this.o.getFragmentManager();
            if (fragmentManager instanceof k) {
                ((k) fragmentManager).b(fragment, true);
            }
        }
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            if (!z || ((Boolean) pair.second).booleanValue()) {
                ((FragmentLifecycleCallbacks) pair.first).onFragmentResumed(this, fragment);
            }
        }
    }

    void c(Fragment fragment, boolean z) {
        if (this.o != null) {
            FragmentManager fragmentManager = this.o.getFragmentManager();
            if (fragmentManager instanceof k) {
                ((k) fragmentManager).c(fragment, true);
            }
        }
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            if (!z || ((Boolean) pair.second).booleanValue()) {
                ((FragmentLifecycleCallbacks) pair.first).onFragmentPaused(this, fragment);
            }
        }
    }

    void d(Fragment fragment, boolean z) {
        if (this.o != null) {
            FragmentManager fragmentManager = this.o.getFragmentManager();
            if (fragmentManager instanceof k) {
                ((k) fragmentManager).d(fragment, true);
            }
        }
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            if (!z || ((Boolean) pair.second).booleanValue()) {
                ((FragmentLifecycleCallbacks) pair.first).onFragmentStopped(this, fragment);
            }
        }
    }

    void d(Fragment fragment, Bundle bundle, boolean z) {
        if (this.o != null) {
            FragmentManager fragmentManager = this.o.getFragmentManager();
            if (fragmentManager instanceof k) {
                ((k) fragmentManager).d(fragment, bundle, true);
            }
        }
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            if (!z || ((Boolean) pair.second).booleanValue()) {
                ((FragmentLifecycleCallbacks) pair.first).onFragmentSaveInstanceState(this, fragment, bundle);
            }
        }
    }

    void e(Fragment fragment, boolean z) {
        if (this.o != null) {
            FragmentManager fragmentManager = this.o.getFragmentManager();
            if (fragmentManager instanceof k) {
                ((k) fragmentManager).e(fragment, true);
            }
        }
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            if (!z || ((Boolean) pair.second).booleanValue()) {
                ((FragmentLifecycleCallbacks) pair.first).onFragmentViewDestroyed(this, fragment);
            }
        }
    }

    void f(Fragment fragment, boolean z) {
        if (this.o != null) {
            FragmentManager fragmentManager = this.o.getFragmentManager();
            if (fragmentManager instanceof k) {
                ((k) fragmentManager).f(fragment, true);
            }
        }
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            if (!z || ((Boolean) pair.second).booleanValue()) {
                ((FragmentLifecycleCallbacks) pair.first).onFragmentDestroyed(this, fragment);
            }
        }
    }

    void g(Fragment fragment, boolean z) {
        if (this.o != null) {
            FragmentManager fragmentManager = this.o.getFragmentManager();
            if (fragmentManager instanceof k) {
                ((k) fragmentManager).g(fragment, true);
            }
        }
        Iterator it = this.I.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            if (!z || ((Boolean) pair.second).booleanValue()) {
                ((FragmentLifecycleCallbacks) pair.first).onFragmentDetached(this, fragment);
            }
        }
    }

    public static int reverseTransit(int i) {
        switch (i) {
            case 4097:
                return 8194;
            case 4099:
                return 4099;
            case 8194:
                return 4097;
            default:
                return 0;
        }
    }

    public static int transitToStyleIndex(int i, boolean z) {
        switch (i) {
            case 4097:
                return z ? 1 : 2;
            case 4099:
                return z ? 5 : 6;
            case 8194:
                return z ? 3 : 4;
            default:
                return -1;
        }
    }

    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        if (!"fragment".equals(str)) {
            return null;
        }
        String string;
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, e.Fragment);
        if (attributeValue == null) {
            string = obtainStyledAttributes.getString(0);
        } else {
            string = attributeValue;
        }
        int resourceId = obtainStyledAttributes.getResourceId(1, -1);
        String string2 = obtainStyledAttributes.getString(2);
        obtainStyledAttributes.recycle();
        if (!Fragment.isSupportFragmentClass(this.m.b(), string)) {
            return null;
        }
        int id;
        if (view != null) {
            id = view.getId();
        } else {
            id = 0;
        }
        if (id == -1 && resourceId == -1 && string2 == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + string);
        }
        Fragment fragment;
        Fragment findFragmentById = resourceId != -1 ? findFragmentById(resourceId) : null;
        if (findFragmentById == null && string2 != null) {
            findFragmentById = findFragmentByTag(string2);
        }
        if (findFragmentById == null && id != -1) {
            findFragmentById = findFragmentById(id);
        }
        if (a) {
            Log.v("FragmentManager", "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + string + " existing=" + findFragmentById);
        }
        if (findFragmentById == null) {
            Fragment instantiate = this.n.instantiate(context, string, null);
            instantiate.mFromLayout = true;
            instantiate.mFragmentId = resourceId != 0 ? resourceId : id;
            instantiate.mContainerId = id;
            instantiate.mTag = string2;
            instantiate.mInLayout = true;
            instantiate.mFragmentManager = this;
            instantiate.mHost = this.m;
            instantiate.onInflate(this.m.b(), attributeSet, instantiate.mSavedFragmentState);
            addFragment(instantiate, true);
            fragment = instantiate;
        } else if (findFragmentById.mInLayout) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string2 + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + string);
        } else {
            findFragmentById.mInLayout = true;
            findFragmentById.mHost = this.m;
            if (!findFragmentById.mRetaining) {
                findFragmentById.onInflate(this.m.b(), attributeSet, findFragmentById.mSavedFragmentState);
            }
            fragment = findFragmentById;
        }
        if (this.l >= 1 || !fragment.mFromLayout) {
            a(fragment);
        } else {
            a(fragment, 1, 0, 0, false);
        }
        if (fragment.mView == null) {
            throw new IllegalStateException("Fragment " + string + " did not create a view.");
        }
        if (resourceId != 0) {
            fragment.mView.setId(resourceId);
        }
        if (fragment.mView.getTag() == null) {
            fragment.mView.setTag(string2);
        }
        return fragment.mView;
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    Factory2 i() {
        return this;
    }
}
