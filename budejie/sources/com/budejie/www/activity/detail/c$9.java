package com.budejie.www.activity.detail;

import android.text.TextUtils;
import com.budejie.www.R;
import com.budejie.www.util.an;
import com.budejie.www.util.z;
import java.util.HashMap;
import net.tsz.afinal.a.a;

class c$9 extends a<String> {
    final /* synthetic */ c a;

    c$9(c cVar) {
        this.a = cVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onFailure(Throwable th, int i, String str) {
        c.a(this.a, an.a(c.c(this.a), c.c(this.a).getString(R.string.operate_fail), -1));
        c.I(this.a).show();
    }

    public void a(String str) {
        try {
            HashMap k = z.k(str);
            if (k != null) {
                String str2 = (String) k.get("result_desc");
                if (TextUtils.isEmpty(str2)) {
                    c.a(this.a, an.a(c.c(this.a), c.c(this.a).getString(R.string.operate_fail), -1));
                } else {
                    c.a(this.a, an.a(c.c(this.a), str2, -1));
                }
            } else {
                c.a(this.a, an.a(c.c(this.a), c.c(this.a).getString(R.string.operate_fail), -1));
            }
            if (c.I(this.a) != null) {
                c.I(this.a).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
