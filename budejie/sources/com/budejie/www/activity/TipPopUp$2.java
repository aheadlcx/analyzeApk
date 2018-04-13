package com.budejie.www.activity;

import android.content.Context;
import android.util.Log;
import com.budejie.www.c.k;
import net.tsz.afinal.a.a;

class TipPopUp$2 extends a<String> {
    final /* synthetic */ Context a;

    TipPopUp$2(Context context) {
        this.a = context;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void a(String str) {
        new k(this.a).a(str);
    }

    public void onFailure(Throwable th, int i, String str) {
        Log.d("s", "sd");
    }
}
