package com.budejie.www.adapter.a;

import android.text.TextUtils;
import com.budejie.www.R;
import com.budejie.www.bean.Fans;
import com.budejie.www.bean.MyMsgItem;
import com.budejie.www.bean.ResultBean;
import com.budejie.www.util.an;
import com.budejie.www.util.as;
import com.budejie.www.util.z;
import net.tsz.afinal.a.a;

class d$2 extends a<String> {
    final /* synthetic */ MyMsgItem a;
    final /* synthetic */ d b;

    d$2(d dVar, MyMsgItem myMsgItem) {
        this.b = dVar;
        this.a = myMsgItem;
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
                    this.b.a = an.a(d.a(this.b), d.a(this.b).getString(R.string.operate_fail), -1);
                } else {
                    this.b.a = an.a(d.a(this.b), msg, -1);
                }
                if (!TextUtils.isEmpty(code) && "0".equals(code)) {
                    this.a.setIs_follow("1");
                    this.b.notifyDataSetChanged();
                    d.b(this.b).a(new Fans(this.a));
                    as.b().a(this.a.getUserid() + "", Integer.valueOf(1));
                }
            } else {
                this.b.a = an.a(d.a(this.b), d.a(this.b).getString(R.string.operate_fail), -1);
            }
            if (this.b.a != null) {
                this.b.a.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
