package com.budejie.www.adapter.a;

import android.text.TextUtils;
import com.budejie.www.R;
import com.budejie.www.bean.LabelUser;
import com.budejie.www.bean.ResultBean;
import com.budejie.www.util.an;
import com.budejie.www.util.as;
import com.budejie.www.util.z;
import net.tsz.afinal.a.a;

class a$2 extends a<String> {
    final /* synthetic */ LabelUser a;
    final /* synthetic */ a b;

    a$2(a aVar, LabelUser labelUser) {
        this.b = aVar;
        this.a = labelUser;
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
                    this.b.a = an.a(a.a(this.b), a.a(this.b).getString(R.string.operate_fail), -1);
                    this.b.a.show();
                } else {
                    this.b.a = an.a(a.a(this.b), msg, -1);
                    this.b.a.show();
                }
                if (!TextUtils.isEmpty(code) && "0".equals(code)) {
                    this.a.setIs_follow("0");
                    this.b.notifyDataSetChanged();
                    a.b(this.b).a(this.a.getUid());
                    as.b().a(this.a.getUid(), Integer.valueOf(0));
                    return;
                }
                return;
            }
            this.b.a = an.a(a.a(this.b), a.a(this.b).getString(R.string.operate_fail), -1);
            this.b.a.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
