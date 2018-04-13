package com.tencent.bugly.crashreport.biz;

import android.content.Context;
import com.tencent.bugly.BuglyStrategy;

final class f implements Runnable {
    private /* synthetic */ Context a;
    private /* synthetic */ BuglyStrategy b;

    f(Context context, BuglyStrategy buglyStrategy) {
        this.a = context;
        this.b = buglyStrategy;
    }

    public final void run() {
        b.c(this.a, this.b);
    }
}
