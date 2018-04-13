package android.support.v4.app;

import android.support.v4.app.FragmentManager.BackStackEntry;
import android.support.v4.util.LogWriter;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import com.alipay.sdk.util.h;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

final class c extends FragmentTransaction implements BackStackEntry, f {
    final k a;
    ArrayList<a> b = new ArrayList();
    int c;
    int d;
    int e;
    int f;
    int g;
    int h;
    boolean i;
    boolean j = true;
    String k;
    boolean l;
    int m = -1;
    int n;
    CharSequence o;
    int p;
    CharSequence q;
    ArrayList<String> r;
    ArrayList<String> s;
    boolean t = false;
    ArrayList<Runnable> u;

    static final class a {
        int a;
        Fragment b;
        int c;
        int d;
        int e;
        int f;

        a() {
        }

        a(int i, Fragment fragment) {
            this.a = i;
            this.b = fragment;
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append("BackStackEntry{");
        stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.m >= 0) {
            stringBuilder.append(" #");
            stringBuilder.append(this.m);
        }
        if (this.k != null) {
            stringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            stringBuilder.append(this.k);
        }
        stringBuilder.append(h.d);
        return stringBuilder.toString();
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        dump(str, printWriter, true);
    }

    public void dump(String str, PrintWriter printWriter, boolean z) {
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.k);
            printWriter.print(" mIndex=");
            printWriter.print(this.m);
            printWriter.print(" mCommitted=");
            printWriter.println(this.l);
            if (this.g != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.g));
                printWriter.print(" mTransitionStyle=#");
                printWriter.println(Integer.toHexString(this.h));
            }
            if (!(this.c == 0 && this.d == 0)) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.c));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.d));
            }
            if (!(this.e == 0 && this.f == 0)) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.e));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.f));
            }
            if (!(this.n == 0 && this.o == null)) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.n));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.o);
            }
            if (!(this.p == 0 && this.q == null)) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.p));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.q);
            }
        }
        if (!this.b.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Operations:");
            str + "    ";
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                String str2;
                a aVar = (a) this.b.get(i);
                switch (aVar.a) {
                    case 0:
                        str2 = "NULL";
                        break;
                    case 1:
                        str2 = "ADD";
                        break;
                    case 2:
                        str2 = "REPLACE";
                        break;
                    case 3:
                        str2 = "REMOVE";
                        break;
                    case 4:
                        str2 = "HIDE";
                        break;
                    case 5:
                        str2 = "SHOW";
                        break;
                    case 6:
                        str2 = "DETACH";
                        break;
                    case 7:
                        str2 = "ATTACH";
                        break;
                    case 8:
                        str2 = "SET_PRIMARY_NAV";
                        break;
                    case 9:
                        str2 = "UNSET_PRIMARY_NAV";
                        break;
                    default:
                        str2 = "cmd=" + aVar.a;
                        break;
                }
                printWriter.print(str);
                printWriter.print("  Op #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.print(str2);
                printWriter.print(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                printWriter.println(aVar.b);
                if (z) {
                    if (!(aVar.c == 0 && aVar.d == 0)) {
                        printWriter.print(str);
                        printWriter.print("enterAnim=#");
                        printWriter.print(Integer.toHexString(aVar.c));
                        printWriter.print(" exitAnim=#");
                        printWriter.println(Integer.toHexString(aVar.d));
                    }
                    if (aVar.e != 0 || aVar.f != 0) {
                        printWriter.print(str);
                        printWriter.print("popEnterAnim=#");
                        printWriter.print(Integer.toHexString(aVar.e));
                        printWriter.print(" popExitAnim=#");
                        printWriter.println(Integer.toHexString(aVar.f));
                    }
                }
            }
        }
    }

    public c(k kVar) {
        this.a = kVar;
    }

    public int getId() {
        return this.m;
    }

    public int getBreadCrumbTitleRes() {
        return this.n;
    }

    public int getBreadCrumbShortTitleRes() {
        return this.p;
    }

    public CharSequence getBreadCrumbTitle() {
        if (this.n != 0) {
            return this.a.m.b().getText(this.n);
        }
        return this.o;
    }

    public CharSequence getBreadCrumbShortTitle() {
        if (this.p != 0) {
            return this.a.m.b().getText(this.p);
        }
        return this.q;
    }

    void a(a aVar) {
        this.b.add(aVar);
        aVar.c = this.c;
        aVar.d = this.d;
        aVar.e = this.e;
        aVar.f = this.f;
    }

    public FragmentTransaction add(Fragment fragment, String str) {
        a(0, fragment, str, 1);
        return this;
    }

    public FragmentTransaction add(int i, Fragment fragment) {
        a(i, fragment, null, 1);
        return this;
    }

    public FragmentTransaction add(int i, Fragment fragment, String str) {
        a(i, fragment, str, 1);
        return this;
    }

    private void a(int i, Fragment fragment, String str, int i2) {
        Class cls = fragment.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from" + " instance state.");
        }
        fragment.mFragmentManager = this.a;
        if (str != null) {
            if (fragment.mTag == null || str.equals(fragment.mTag)) {
                fragment.mTag = str;
            } else {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.mTag + " now " + str);
            }
        }
        if (i != 0) {
            if (i == -1) {
                throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + str + " to container view with no id");
            } else if (fragment.mFragmentId == 0 || fragment.mFragmentId == i) {
                fragment.mFragmentId = i;
                fragment.mContainerId = i;
            } else {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.mFragmentId + " now " + i);
            }
        }
        a(new a(i2, fragment));
    }

    public FragmentTransaction replace(int i, Fragment fragment) {
        return replace(i, fragment, null);
    }

    public FragmentTransaction replace(int i, Fragment fragment, String str) {
        if (i == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        a(i, fragment, str, 2);
        return this;
    }

    public FragmentTransaction remove(Fragment fragment) {
        a(new a(3, fragment));
        return this;
    }

    public FragmentTransaction hide(Fragment fragment) {
        a(new a(4, fragment));
        return this;
    }

    public FragmentTransaction show(Fragment fragment) {
        a(new a(5, fragment));
        return this;
    }

    public FragmentTransaction detach(Fragment fragment) {
        a(new a(6, fragment));
        return this;
    }

    public FragmentTransaction attach(Fragment fragment) {
        a(new a(7, fragment));
        return this;
    }

    public FragmentTransaction setPrimaryNavigationFragment(Fragment fragment) {
        a(new a(8, fragment));
        return this;
    }

    public FragmentTransaction setCustomAnimations(int i, int i2) {
        return setCustomAnimations(i, i2, 0, 0);
    }

    public FragmentTransaction setCustomAnimations(int i, int i2, int i3, int i4) {
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.f = i4;
        return this;
    }

    public FragmentTransaction setTransition(int i) {
        this.g = i;
        return this;
    }

    public FragmentTransaction addSharedElement(View view, String str) {
        if (u.a()) {
            String transitionName = ViewCompat.getTransitionName(view);
            if (transitionName == null) {
                throw new IllegalArgumentException("Unique transitionNames are required for all sharedElements");
            }
            if (this.r == null) {
                this.r = new ArrayList();
                this.s = new ArrayList();
            } else if (this.s.contains(str)) {
                throw new IllegalArgumentException("A shared element with the target name '" + str + "' has already been added to the transaction.");
            } else if (this.r.contains(transitionName)) {
                throw new IllegalArgumentException("A shared element with the source name '" + transitionName + " has already been added to the transaction.");
            }
            this.r.add(transitionName);
            this.s.add(str);
        }
        return this;
    }

    public FragmentTransaction setTransitionStyle(int i) {
        this.h = i;
        return this;
    }

    public FragmentTransaction addToBackStack(String str) {
        if (this.j) {
            this.i = true;
            this.k = str;
            return this;
        }
        throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
    }

    public boolean isAddToBackStackAllowed() {
        return this.j;
    }

    public FragmentTransaction disallowAddToBackStack() {
        if (this.i) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        this.j = false;
        return this;
    }

    public FragmentTransaction setBreadCrumbTitle(int i) {
        this.n = i;
        this.o = null;
        return this;
    }

    public FragmentTransaction setBreadCrumbTitle(CharSequence charSequence) {
        this.n = 0;
        this.o = charSequence;
        return this;
    }

    public FragmentTransaction setBreadCrumbShortTitle(int i) {
        this.p = i;
        this.q = null;
        return this;
    }

    public FragmentTransaction setBreadCrumbShortTitle(CharSequence charSequence) {
        this.p = 0;
        this.q = charSequence;
        return this;
    }

    void a(int i) {
        if (this.i) {
            if (k.a) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i);
            }
            int size = this.b.size();
            for (int i2 = 0; i2 < size; i2++) {
                a aVar = (a) this.b.get(i2);
                if (aVar.b != null) {
                    Fragment fragment = aVar.b;
                    fragment.mBackStackNesting += i;
                    if (k.a) {
                        Log.v("FragmentManager", "Bump nesting of " + aVar.b + " to " + aVar.b.mBackStackNesting);
                    }
                }
            }
        }
    }

    public FragmentTransaction runOnCommit(Runnable runnable) {
        if (runnable == null) {
            throw new IllegalArgumentException("runnable cannot be null");
        }
        disallowAddToBackStack();
        if (this.u == null) {
            this.u = new ArrayList();
        }
        this.u.add(runnable);
        return this;
    }

    public void runOnCommitRunnables() {
        if (this.u != null) {
            int size = this.u.size();
            for (int i = 0; i < size; i++) {
                ((Runnable) this.u.get(i)).run();
            }
            this.u = null;
        }
    }

    public int commit() {
        return a(false);
    }

    public int commitAllowingStateLoss() {
        return a(true);
    }

    public void commitNow() {
        disallowAddToBackStack();
        this.a.execSingleAction(this, false);
    }

    public void commitNowAllowingStateLoss() {
        disallowAddToBackStack();
        this.a.execSingleAction(this, true);
    }

    public FragmentTransaction setReorderingAllowed(boolean z) {
        this.t = z;
        return this;
    }

    public FragmentTransaction setAllowOptimization(boolean z) {
        return setReorderingAllowed(z);
    }

    int a(boolean z) {
        if (this.l) {
            throw new IllegalStateException("commit already called");
        }
        if (k.a) {
            Log.v("FragmentManager", "Commit: " + this);
            PrintWriter printWriter = new PrintWriter(new LogWriter("FragmentManager"));
            dump("  ", null, printWriter, null);
            printWriter.close();
        }
        this.l = true;
        if (this.i) {
            this.m = this.a.allocBackStackIndex(this);
        } else {
            this.m = -1;
        }
        this.a.enqueueAction(this, z);
        return this.m;
    }

    public boolean generateOps(ArrayList<c> arrayList, ArrayList<Boolean> arrayList2) {
        if (k.a) {
            Log.v("FragmentManager", "Run: " + this);
        }
        arrayList.add(this);
        arrayList2.add(Boolean.valueOf(false));
        if (this.i) {
            this.a.a(this);
        }
        return true;
    }

    boolean b(int i) {
        int size = this.b.size();
        for (int i2 = 0; i2 < size; i2++) {
            a aVar = (a) this.b.get(i2);
            int i3 = aVar.b != null ? aVar.b.mContainerId : 0;
            if (i3 != 0 && i3 == i) {
                return true;
            }
        }
        return false;
    }

    boolean a(ArrayList<c> arrayList, int i, int i2) {
        if (i2 == i) {
            return false;
        }
        int size = this.b.size();
        int i3 = -1;
        int i4 = 0;
        while (i4 < size) {
            int i5;
            a aVar = (a) this.b.get(i4);
            int i6 = aVar.b != null ? aVar.b.mContainerId : 0;
            if (i6 == 0 || i6 == i3) {
                i5 = i3;
            } else {
                for (int i7 = i; i7 < i2; i7++) {
                    c cVar = (c) arrayList.get(i7);
                    int size2 = cVar.b.size();
                    for (int i8 = 0; i8 < size2; i8++) {
                        a aVar2 = (a) cVar.b.get(i8);
                        if (aVar2.b != null) {
                            i3 = aVar2.b.mContainerId;
                        } else {
                            i3 = 0;
                        }
                        if (i3 == i6) {
                            return true;
                        }
                    }
                }
                i5 = i6;
            }
            i4++;
            i3 = i5;
        }
        return false;
    }

    void a() {
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            a aVar = (a) this.b.get(i);
            Fragment fragment = aVar.b;
            if (fragment != null) {
                fragment.setNextTransition(this.g, this.h);
            }
            switch (aVar.a) {
                case 1:
                    fragment.setNextAnim(aVar.c);
                    this.a.addFragment(fragment, false);
                    break;
                case 3:
                    fragment.setNextAnim(aVar.d);
                    this.a.removeFragment(fragment);
                    break;
                case 4:
                    fragment.setNextAnim(aVar.d);
                    this.a.hideFragment(fragment);
                    break;
                case 5:
                    fragment.setNextAnim(aVar.c);
                    this.a.showFragment(fragment);
                    break;
                case 6:
                    fragment.setNextAnim(aVar.d);
                    this.a.detachFragment(fragment);
                    break;
                case 7:
                    fragment.setNextAnim(aVar.c);
                    this.a.attachFragment(fragment);
                    break;
                case 8:
                    this.a.setPrimaryNavigationFragment(fragment);
                    break;
                case 9:
                    this.a.setPrimaryNavigationFragment(null);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.a);
            }
            if (!(this.t || aVar.a == 1 || fragment == null)) {
                this.a.d(fragment);
            }
        }
        if (!this.t) {
            this.a.a(this.a.l, true);
        }
    }

    void b(boolean z) {
        for (int size = this.b.size() - 1; size >= 0; size--) {
            a aVar = (a) this.b.get(size);
            Fragment fragment = aVar.b;
            if (fragment != null) {
                fragment.setNextTransition(k.reverseTransit(this.g), this.h);
            }
            switch (aVar.a) {
                case 1:
                    fragment.setNextAnim(aVar.f);
                    this.a.removeFragment(fragment);
                    break;
                case 3:
                    fragment.setNextAnim(aVar.e);
                    this.a.addFragment(fragment, false);
                    break;
                case 4:
                    fragment.setNextAnim(aVar.e);
                    this.a.showFragment(fragment);
                    break;
                case 5:
                    fragment.setNextAnim(aVar.f);
                    this.a.hideFragment(fragment);
                    break;
                case 6:
                    fragment.setNextAnim(aVar.e);
                    this.a.attachFragment(fragment);
                    break;
                case 7:
                    fragment.setNextAnim(aVar.f);
                    this.a.detachFragment(fragment);
                    break;
                case 8:
                    this.a.setPrimaryNavigationFragment(null);
                    break;
                case 9:
                    this.a.setPrimaryNavigationFragment(fragment);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.a);
            }
            if (!(this.t || aVar.a == 3 || fragment == null)) {
                this.a.d(fragment);
            }
        }
        if (!this.t && z) {
            this.a.a(this.a.l, true);
        }
    }

    Fragment a(ArrayList<Fragment> arrayList, Fragment fragment) {
        int i = 0;
        while (i < this.b.size()) {
            a aVar = (a) this.b.get(i);
            switch (aVar.a) {
                case 1:
                case 7:
                    arrayList.add(aVar.b);
                    break;
                case 2:
                    Fragment fragment2 = aVar.b;
                    int i2 = fragment2.mContainerId;
                    Object obj = null;
                    int size = arrayList.size() - 1;
                    Fragment fragment3 = fragment;
                    int i3 = i;
                    while (size >= 0) {
                        Object obj2;
                        Fragment fragment4 = (Fragment) arrayList.get(size);
                        if (fragment4.mContainerId != i2) {
                            obj2 = obj;
                        } else if (fragment4 == fragment2) {
                            obj2 = 1;
                        } else {
                            if (fragment4 == fragment3) {
                                this.b.add(i3, new a(9, fragment4));
                                i3++;
                                fragment3 = null;
                            }
                            a aVar2 = new a(3, fragment4);
                            aVar2.c = aVar.c;
                            aVar2.e = aVar.e;
                            aVar2.d = aVar.d;
                            aVar2.f = aVar.f;
                            this.b.add(i3, aVar2);
                            arrayList.remove(fragment4);
                            i3++;
                            obj2 = obj;
                        }
                        size--;
                        obj = obj2;
                    }
                    if (obj != null) {
                        this.b.remove(i3);
                        i3--;
                    } else {
                        aVar.a = 1;
                        arrayList.add(fragment2);
                    }
                    i = i3;
                    fragment = fragment3;
                    break;
                case 3:
                case 6:
                    arrayList.remove(aVar.b);
                    if (aVar.b != fragment) {
                        break;
                    }
                    this.b.add(i, new a(9, aVar.b));
                    i++;
                    fragment = null;
                    break;
                case 8:
                    this.b.add(i, new a(9, fragment));
                    i++;
                    fragment = aVar.b;
                    break;
                default:
                    break;
            }
            i++;
        }
        return fragment;
    }

    Fragment b(ArrayList<Fragment> arrayList, Fragment fragment) {
        for (int i = 0; i < this.b.size(); i++) {
            a aVar = (a) this.b.get(i);
            switch (aVar.a) {
                case 1:
                case 7:
                    arrayList.remove(aVar.b);
                    break;
                case 3:
                case 6:
                    arrayList.add(aVar.b);
                    break;
                case 8:
                    fragment = null;
                    break;
                case 9:
                    fragment = aVar.b;
                    break;
                default:
                    break;
            }
        }
        return fragment;
    }

    boolean b() {
        for (int i = 0; i < this.b.size(); i++) {
            if (b((a) this.b.get(i))) {
                return true;
            }
        }
        return false;
    }

    void a(b bVar) {
        for (int i = 0; i < this.b.size(); i++) {
            a aVar = (a) this.b.get(i);
            if (b(aVar)) {
                aVar.b.setOnStartEnterTransitionListener(bVar);
            }
        }
    }

    private static boolean b(a aVar) {
        Fragment fragment = aVar.b;
        return (fragment == null || !fragment.mAdded || fragment.mView == null || fragment.mDetached || fragment.mHidden || !fragment.isPostponed()) ? false : true;
    }

    public String getName() {
        return this.k;
    }

    public int getTransition() {
        return this.g;
    }

    public int getTransitionStyle() {
        return this.h;
    }

    public boolean isEmpty() {
        return this.b.isEmpty();
    }
}
