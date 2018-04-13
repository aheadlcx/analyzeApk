package com.tencent.bugly.crashreport.crash;

import com.tencent.bugly.proguard.t;
import java.util.List;

final class h implements t {
    private /* synthetic */ List a;
    private /* synthetic */ b b;

    h(b bVar, List list) {
        this.b = bVar;
        this.a = list;
    }

    public final void a(boolean z) {
        b bVar = this.b;
        b.a(z, this.a);
    }
}
