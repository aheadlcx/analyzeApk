package com.baidu.mobstat;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import java.lang.ref.WeakReference;

class co implements Runnable {
    final /* synthetic */ ch a;
    private long b;
    private WeakReference<Context> c;
    private WeakReference<Fragment> d;
    private WeakReference<Object> e;
    private long f;
    private WeakReference<Context> g;
    private WeakReference<Fragment> h;
    private WeakReference<Object> i;
    private int j;
    private String k;
    private String l;
    private boolean m;
    private ExtraInfo n;
    private cm o;

    public co(ch chVar, long j, Context context, Fragment fragment, long j2, Context context2, Fragment fragment2, int i, String str, Object obj, Object obj2, String str2, boolean z, ExtraInfo extraInfo, cm cmVar) {
        this.a = chVar;
        this.b = j;
        this.f = j2;
        this.c = new WeakReference(context);
        this.g = new WeakReference(context2);
        this.d = new WeakReference(fragment);
        this.h = new WeakReference(fragment2);
        this.i = new WeakReference(obj);
        this.e = new WeakReference(obj2);
        this.j = i;
        this.k = str;
        this.l = str2;
        this.m = z;
        this.n = extraInfo;
        this.o = cmVar;
    }

    public void run() {
        Context context;
        String str;
        long j;
        String stringBuilder;
        if (this.j == 1) {
            context = (Context) this.c.get();
            Context context2 = (Context) this.g.get();
            if (context == null || context2 == null) {
                db.c("onPause, WeakReference is already been released");
            } else if (context == context2) {
                str = "";
                j = this.b - this.f;
                StringBuilder stringBuilder2 = new StringBuilder();
                if (this.k != null) {
                    stringBuilder2.append(this.k);
                    if (this.o != null) {
                        j = this.o.d - this.o.c;
                        db.c("page time = " + this.o.a + "; time = " + j);
                        if (j < 20) {
                            db.c("page time little than 20 mills.");
                            return;
                        }
                    }
                } else if (context instanceof Activity) {
                    stringBuilder2.append(((Activity) context).getComponentName().getShortClassName());
                    if (stringBuilder2.charAt(0) == '.') {
                        stringBuilder2.deleteCharAt(0);
                    }
                } else {
                    db.c("onPause, pause is not a Activity");
                    return;
                }
                if (context instanceof Activity) {
                    CharSequence title = ((Activity) context).getTitle();
                    if (title != null) {
                        str = title.toString();
                    }
                }
                db.a("new page view, page name = " + stringBuilder2.toString() + ", stay time = " + j + "(ms)");
                stringBuilder = stringBuilder2.toString();
                if (this.k == null) {
                    this.l = stringBuilder;
                }
                this.a.i.a(new cg(stringBuilder, str, this.l, j, this.f, this.m, this.n));
                if (this.k == null) {
                    this.a.i.d(this.b);
                    this.a.c(context);
                } else if (this.o != null) {
                    this.a.i.d(this.o.d);
                    this.a.c(context);
                }
            } else if (this.k != null) {
                db.b("onPageStart() or onPageEnd() install error.");
            } else {
                db.b("onPause() or onResume() install error.");
            }
        } else if (this.j == 2) {
            Fragment fragment = (Fragment) this.d.get();
            Fragment fragment2 = (Fragment) this.h.get();
            if (fragment == null || fragment2 == null) {
                db.c("onPause, WeakReference is already been released");
            } else if (fragment != fragment2) {
                db.c("onPause() or onResume() install error.");
            } else {
                str = "";
                Activity activity = fragment.getActivity();
                if (activity != null) {
                    str = activity.getTitle().toString();
                }
                j = this.b - this.f;
                r0 = fragment.getClass().getName();
                stringBuilder = r0.substring(r0.lastIndexOf(".") + 1);
                db.a("Fragment new page view, page name = " + r0.toString() + ", stay time = " + j + "(ms)");
                this.a.i.a(new cg(stringBuilder, str, stringBuilder, j, this.f, this.m, this.n));
                this.a.i.d(this.b);
                this.a.c(fragment.getActivity());
            }
        } else if (this.j == 3) {
            Object obj = (android.app.Fragment) this.e.get();
            android.app.Fragment fragment3 = (android.app.Fragment) this.i.get();
            if (obj == null || fragment3 == null) {
                db.c("onPause, WeakReference is already been released");
            } else if (obj != fragment3) {
                db.c("onPause() or onResume() install error.");
            } else {
                str = "";
                Activity activity2 = obj.getActivity();
                if (activity2 != null) {
                    str = activity2.getTitle().toString();
                }
                j = this.b - this.f;
                context = ch.a(obj);
                if (context == null) {
                    db.c("getContxtFromReverse faild.");
                    return;
                }
                r0 = obj.getClass().getName();
                stringBuilder = r0.substring(r0.lastIndexOf(".") + 1);
                db.a("android.app.Fragment new page view, page name = " + r0.toString() + "; stay time = " + j + "(ms)");
                this.a.i.a(new cg(stringBuilder, str, stringBuilder, j, this.f, this.m, this.n));
                this.a.i.d(this.b);
                this.a.c(context);
            }
        }
    }
}
