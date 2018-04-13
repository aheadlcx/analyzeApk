package com.tencent.bugly.crashreport.crash;

import com.tencent.bugly.proguard.z;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class j extends Thread {
    private /* synthetic */ c a;

    j(c cVar) {
        this.a = cVar;
    }

    public final void run() {
        if (z.a(this.a.p, "local_crash_lock", 10000)) {
            List a = this.a.o.a();
            if (a != null && a.size() > 0) {
                List arrayList;
                int size = a.size();
                if (((long) size) > 100) {
                    arrayList = new ArrayList();
                    Collections.sort(a);
                    for (int i = 0; ((long) i) < 100; i++) {
                        arrayList.add(a.get((size - 1) - i));
                    }
                } else {
                    arrayList = a;
                }
                this.a.o.a(arrayList, 0, false, false, false);
            }
            z.b(this.a.p, "local_crash_lock");
        }
    }
}
