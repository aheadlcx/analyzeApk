package com.alipay.sdk.data;

import android.content.Context;
import java.util.HashMap;
import java.util.concurrent.Callable;

final class d implements Callable<String> {
    final /* synthetic */ Context a;
    final /* synthetic */ HashMap b;
    final /* synthetic */ c c;

    d(c cVar, Context context, HashMap hashMap) {
        this.c = cVar;
        this.a = context;
        this.b = hashMap;
    }

    public final /* synthetic */ Object call() throws Exception {
        c cVar = this.c;
        return c.a(this.a, this.b);
    }

    private String a() throws Exception {
        c cVar = this.c;
        return c.a(this.a, this.b);
    }
}
