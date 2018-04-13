package com.budejie.www.http;

import android.os.Handler;
import net.tsz.afinal.a.a;

class NetWorkUtil$1 extends a<String> {
    final /* synthetic */ Handler a;
    final /* synthetic */ int b;

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void a(String str) {
        super.onSuccess(str);
        this.a.sendMessage(this.a.obtainMessage(this.b, str));
    }

    public a<String> progress(boolean z, int i) {
        return super.progress(z, i);
    }
}
