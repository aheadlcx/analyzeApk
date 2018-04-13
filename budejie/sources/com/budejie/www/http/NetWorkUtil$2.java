package com.budejie.www.http;

import com.ali.auth.third.core.model.Constants;
import com.budejie.www.goddubbing.c.d;
import net.tsz.afinal.a.a;

class NetWorkUtil$2 extends a {
    final /* synthetic */ String a;
    final /* synthetic */ com.budejie.www.f.a b;
    final /* synthetic */ int c;
    final /* synthetic */ NetWorkUtil d;

    NetWorkUtil$2(NetWorkUtil netWorkUtil, String str, com.budejie.www.f.a aVar, int i) {
        this.d = netWorkUtil;
        this.a = str;
        this.b = aVar;
        this.c = i;
    }

    public void onLoading(long j, long j2) {
        super.onLoading(j, j2);
    }

    public void onSuccess(Object obj) {
        super.onSuccess(obj);
        d.a(NetWorkUtil.b(this.d), this.a);
        if (this.b != null) {
            this.b.a(this.c, Constants.SERVICE_SCOPE_FLAG_VALUE);
        }
    }

    public void onFailure(Throwable th, int i, String str) {
        super.onFailure(th, i, str);
        if (this.b != null) {
            this.b.a(this.c);
        }
    }
}
