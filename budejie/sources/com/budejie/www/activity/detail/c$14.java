package com.budejie.www.activity.detail;

import android.text.TextUtils;
import android.util.Log;
import net.tsz.afinal.a.a;

class c$14 extends a<String> {
    final /* synthetic */ c a;

    c$14(c cVar) {
        this.a = cVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void a(String str) {
        c.a(this.a, null);
        c.c(this.a, 0);
        if (!TextUtils.isEmpty(str)) {
            Log.i("CommendDetail", "-->" + str);
            c.G(this.a).sendMessage(c.G(this.a).obtainMessage(12, str));
        } else if (c.J(this.a) != null) {
            c.J(this.a).a(false, "");
        }
    }

    public void onFailure(Throwable th, int i, String str) {
        Log.i("CommendDetail", "onCommitVoice error : " + str);
        c.a(this.a, null);
        c.c(this.a, 0);
        if (c.J(this.a) != null) {
            c.J(this.a).a(false, "");
        }
    }
}
