package com.budejie.www.util;

import android.app.Activity;
import android.text.TextUtils;
import com.budejie.www.bean.FansList;
import com.budejie.www.c.g;
import net.tsz.afinal.a.a;

class b$4 extends a<String> {
    final /* synthetic */ Activity a;

    b$4(Activity activity) {
        this.a = activity;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void a(String str) {
        super.onSuccess(str);
        FansList f = z.f(str);
        if (f != null) {
            CharSequence code = f.getCode();
            if (!TextUtils.isEmpty(code) && "0".equals(code) && f.getData().size() != 0) {
                g gVar = new g(this.a);
                gVar.a();
                gVar.a(f.getData());
                ai.b(this.a, System.currentTimeMillis());
            }
        }
    }
}
