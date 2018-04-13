package com.umeng.commonsdk;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.b;
import com.umeng.commonsdk.internal.d;
import com.umeng.commonsdk.internal.utils.j;
import com.umeng.commonsdk.internal.utils.l;
import com.umeng.commonsdk.proguard.a;
import com.umeng.commonsdk.proguard.e;

final class c implements Runnable {
    final /* synthetic */ Context a;

    c(Context context) {
        this.a = context;
    }

    public void run() {
        try {
            Object a = b.a(this.a);
            CharSequence packageName = this.a.getPackageName();
            if (!TextUtils.isEmpty(a) && !TextUtils.isEmpty(packageName) && a.equals(packageName)) {
                a.a(this.a);
                try {
                    e.a(this.a);
                } catch (Throwable th) {
                    com.umeng.commonsdk.statistics.common.e.c(com.umeng.commonsdk.framework.c.d, "e is " + th);
                }
                try {
                    if (!com.umeng.commonsdk.internal.utils.c.a(this.a).a()) {
                        com.umeng.commonsdk.internal.utils.c.a(this.a).b();
                    }
                } catch (Throwable th2) {
                    com.umeng.commonsdk.statistics.common.e.c(com.umeng.commonsdk.framework.c.d, "e is " + th2);
                }
                try {
                    l.b(this.a);
                } catch (Exception e) {
                    com.umeng.commonsdk.statistics.common.e.c(com.umeng.commonsdk.framework.c.d, "e is " + e);
                }
                try {
                    com.umeng.commonsdk.internal.utils.a.n(this.a);
                } catch (Exception e2) {
                    com.umeng.commonsdk.statistics.common.e.c(com.umeng.commonsdk.framework.c.d, "e is " + e2);
                }
                try {
                    com.umeng.commonsdk.internal.utils.a.d(this.a);
                } catch (Exception e22) {
                    com.umeng.commonsdk.statistics.common.e.c(com.umeng.commonsdk.framework.c.d, "e is " + e22);
                }
                try {
                    j.b(this.a);
                } catch (Exception e222) {
                    com.umeng.commonsdk.statistics.common.e.c(com.umeng.commonsdk.framework.c.d, "e is " + e222);
                }
                try {
                    d.b(this.a);
                } catch (Exception e2222) {
                    com.umeng.commonsdk.statistics.common.e.c(com.umeng.commonsdk.framework.c.d, "e is " + e2222);
                }
                try {
                    d.c(this.a);
                } catch (Throwable th3) {
                }
            }
        } catch (Exception e22222) {
            com.umeng.commonsdk.statistics.common.e.c(com.umeng.commonsdk.framework.c.d, "e is " + e22222);
        } catch (Throwable th22) {
            com.umeng.commonsdk.proguard.b.a(this.a, th22);
        }
    }
}
