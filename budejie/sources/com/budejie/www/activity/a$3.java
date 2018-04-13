package com.budejie.www.activity;

import android.text.TextUtils;
import com.budejie.www.R;
import com.budejie.www.bean.Fans;
import com.budejie.www.bean.ResultBean;
import com.budejie.www.util.an;
import com.budejie.www.util.as;
import com.budejie.www.util.z;
import net.tsz.afinal.a.a;

class a$3 extends a<String> {
    final /* synthetic */ Fans a;
    final /* synthetic */ a b;

    a$3(a aVar, Fans fans) {
        this.b = aVar;
        this.a = fans;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void a(String str) {
        try {
            ResultBean s = z.s(str);
            if (s != null) {
                Object msg = s.getMsg();
                CharSequence code = s.getCode();
                if (TextUtils.isEmpty(msg)) {
                    a.a(this.b, an.a(a.a(this.b), a.a(this.b).getString(R.string.operate_fail), -1));
                } else {
                    a.a(this.b, an.a(a.a(this.b), msg, -1));
                }
                if (!TextUtils.isEmpty(code) && "0".equals(code)) {
                    this.a.setRelationship("2");
                    this.b.notifyDataSetChanged();
                    a.b(this.b).a(this.a);
                    as.b().a(this.a.getId(), Integer.valueOf(1));
                }
            } else {
                a.a(this.b, an.a(a.a(this.b), a.a(this.b).getString(R.string.operate_fail), -1));
            }
            if (a.c(this.b) != null) {
                a.c(this.b).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
