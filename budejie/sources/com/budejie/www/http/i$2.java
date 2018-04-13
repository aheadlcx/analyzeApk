package com.budejie.www.http;

import net.tsz.afinal.a.a;

class i$2 extends a<String> {
    i$2() {
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
