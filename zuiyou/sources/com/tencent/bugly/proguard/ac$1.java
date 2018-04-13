package com.tencent.bugly.proguard;

import android.text.TextUtils;
import com.tencent.bugly.crashreport.common.info.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ac$1 implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ ac c;

    ac$1(ac acVar, int i, int i2) {
        this.c = acVar;
        this.a = i;
        this.b = i2;
    }

    public void run() {
        try {
            if (!TextUtils.isEmpty(ac.a(this.c))) {
                ab abVar;
                ab abVar2;
                List a = ac.a(this.c, this.a);
                List arrayList;
                if (a == null) {
                    arrayList = new ArrayList();
                } else {
                    arrayList = a;
                }
                if (ac.b(this.c).get(Integer.valueOf(this.a)) == null) {
                    ac.b(this.c).put(Integer.valueOf(this.a), new HashMap());
                }
                if (((Map) ac.b(this.c).get(Integer.valueOf(this.a))).get(ac.a(this.c)) == null) {
                    ab abVar3 = new ab();
                    abVar3.a = (long) this.a;
                    abVar3.g = ac.a;
                    abVar3.b = ac.a(this.c);
                    abVar3.f = a.b().o;
                    a.b().getClass();
                    abVar3.e = "2.6.5";
                    abVar3.c = System.currentTimeMillis();
                    abVar3.d = this.b;
                    ((Map) ac.b(this.c).get(Integer.valueOf(this.a))).put(ac.a(this.c), abVar3);
                    abVar = abVar3;
                } else {
                    abVar2 = (ab) ((Map) ac.b(this.c).get(Integer.valueOf(this.a))).get(ac.a(this.c));
                    abVar2.d = this.b;
                    abVar = abVar2;
                }
                Collection arrayList2 = new ArrayList();
                int i = 0;
                for (ab abVar22 : r4) {
                    if (abVar22.g == abVar.g && abVar22.b != null && abVar22.b.equalsIgnoreCase(abVar.b)) {
                        i = 1;
                        abVar22.d = abVar.d;
                    }
                    if ((abVar22.e != null && !abVar22.e.equalsIgnoreCase(abVar.e)) || ((abVar22.f != null && !abVar22.f.equalsIgnoreCase(abVar.f)) || abVar22.d <= 0)) {
                        arrayList2.add(abVar22);
                    }
                }
                r4.removeAll(arrayList2);
                if (i == 0) {
                    r4.add(abVar);
                }
                ac.a(this.c, this.a, r4);
            }
        } catch (Exception e) {
            an.e("saveCrashRecord failed", new Object[0]);
        }
    }
}
