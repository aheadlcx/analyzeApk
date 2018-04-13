package com.budejie.www.activity.detail;

import android.text.TextUtils;
import com.budejie.www.bean.ListItemObject;
import java.util.ArrayList;
import net.tsz.afinal.a.a;

class c$12 extends a {
    final /* synthetic */ c a;

    c$12(c cVar) {
        this.a = cVar;
    }

    public void onFailure(Throwable th, int i, String str) {
        super.onFailure(th, i, str);
    }

    public void onSuccess(Object obj) {
        super.onSuccess(obj);
        ArrayList a = com.budejie.www.j.a.a(c.c(this.a), (String) obj);
        if (a != null && a.size() > 0) {
            c.a(this.a, (ListItemObject) a.get(0));
        }
        if (c.d(this.a) != null) {
            c.a(this.a, c.d(this.a).getWid());
            c.e(this.a);
            if ("41".equals(c.d(this.a).getType()) && !TextUtils.isEmpty(c.d(this.a).getVideouri())) {
                this.a.e();
            }
        }
    }
}
