package com.budejie.www.http;

import net.tsz.afinal.a.a;

class i$1 extends a<String> {
    i$1() {
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onFailure(Throwable th, int i, String str) {
        super.onFailure(th, i, str);
    }

    public void a(String str) {
        super.onSuccess(str);
    }
}
