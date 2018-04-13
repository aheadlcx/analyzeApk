package com.budejie.www.g;

import android.util.Log;
import net.tsz.afinal.a.a;

class b$3 extends a<String> {
    b$3() {
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void a(String str) {
        super.onSuccess(str);
        Log.d("wuzhenlin", str);
    }
}
