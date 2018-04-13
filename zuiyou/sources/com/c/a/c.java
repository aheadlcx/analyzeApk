package com.c.a;

import android.app.Activity;
import java.util.Iterator;
import java.util.Stack;

public class c {
    private static final Stack<e> a = new Stack();

    public static e a(Activity activity) {
        Iterator it = a.iterator();
        while (it.hasNext()) {
            e eVar = (e) it.next();
            if (eVar.a == activity) {
                return eVar;
            }
        }
        return null;
    }

    public static e b(Activity activity) {
        e a = a(activity);
        if (a != null) {
            return a;
        }
        throw new RuntimeException("You Should call SwipeBackHelper.onCreate(activity) first");
    }

    public static void c(Activity activity) {
        e a = a(activity);
        if (a == null) {
            a = (e) a.push(new e(activity));
        }
        a.a();
    }

    public static void d(Activity activity) {
        e a = a(activity);
        if (a == null) {
            throw new RuntimeException("You Should call SwipeBackHelper.onCreate(activity) first");
        }
        a.b();
    }

    public static void e(Activity activity) {
        e a = a(activity);
        if (a == null) {
            throw new RuntimeException("You Should call SwipeBackHelper.onCreate(activity) first");
        }
        e a2 = a(a);
        if (!(a2 == null || a2.c() == null)) {
            a2.c().setX(0.0f);
        }
        a.remove(a);
        a.a = null;
    }

    static e a(e eVar) {
        int indexOf = a.indexOf(eVar);
        if (indexOf > 0) {
            return (e) a.get(indexOf - 1);
        }
        return null;
    }
}
