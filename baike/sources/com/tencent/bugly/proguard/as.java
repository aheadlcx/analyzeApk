package com.tencent.bugly.proguard;

import android.text.TextUtils;
import com.tencent.bugly.crashreport.common.info.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class as implements Runnable {
    private /* synthetic */ int a;
    private /* synthetic */ int b;
    private /* synthetic */ n c;

    as(n nVar, int i, int i2) {
        this.c = nVar;
        this.a = i;
        this.b = i2;
    }

    public final void run() {
        try {
            if (!TextUtils.isEmpty(this.c.d)) {
                m mVar;
                m mVar2;
                List a = this.c.c(this.a);
                List arrayList;
                if (a == null) {
                    arrayList = new ArrayList();
                } else {
                    arrayList = a;
                }
                if (this.c.e.get(Integer.valueOf(this.a)) == null) {
                    this.c.e.put(Integer.valueOf(this.a), new HashMap());
                }
                if (((Map) this.c.e.get(Integer.valueOf(this.a))).get(this.c.d) == null) {
                    m mVar3 = new m();
                    mVar3.a = (long) this.a;
                    mVar3.g = n.a;
                    mVar3.b = this.c.d;
                    mVar3.f = a.b().j;
                    a.b().getClass();
                    mVar3.e = "2.6.5";
                    mVar3.c = System.currentTimeMillis();
                    mVar3.d = this.b;
                    ((Map) this.c.e.get(Integer.valueOf(this.a))).put(this.c.d, mVar3);
                    mVar = mVar3;
                } else {
                    mVar2 = (m) ((Map) this.c.e.get(Integer.valueOf(this.a))).get(this.c.d);
                    mVar2.d = this.b;
                    mVar = mVar2;
                }
                Collection arrayList2 = new ArrayList();
                int i = 0;
                for (m mVar22 : r4) {
                    if (mVar22.g == mVar.g && mVar22.b != null && mVar22.b.equalsIgnoreCase(mVar.b)) {
                        i = 1;
                        mVar22.d = mVar.d;
                    }
                    if ((mVar22.e != null && !mVar22.e.equalsIgnoreCase(mVar.e)) || ((mVar22.f != null && !mVar22.f.equalsIgnoreCase(mVar.f)) || mVar22.d <= 0)) {
                        arrayList2.add(mVar22);
                    }
                }
                r4.removeAll(arrayList2);
                if (i == 0) {
                    r4.add(mVar);
                }
                this.c.a(this.a, (List) r4);
            }
        } catch (Exception e) {
            x.e("saveCrashRecord failed", new Object[0]);
        }
    }
}
