package com.budejie.www.adapter.a;

import android.text.TextUtils;
import com.budejie.www.R;
import com.budejie.www.bean.HeadPortraitItem;
import com.budejie.www.bean.ResultBean;
import com.budejie.www.util.an;
import com.budejie.www.util.as;
import com.budejie.www.util.z;
import net.tsz.afinal.a.a;

class b$2 extends a<String> {
    final /* synthetic */ HeadPortraitItem a;
    final /* synthetic */ b b;

    b$2(b bVar, HeadPortraitItem headPortraitItem) {
        this.b = bVar;
        this.a = headPortraitItem;
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
                    this.b.a = an.a(b.a(this.b), b.a(this.b).getString(R.string.operate_fail), -1);
                } else {
                    this.b.a = an.a(b.a(this.b), msg, -1);
                }
                if (!TextUtils.isEmpty(code) && "0".equals(code)) {
                    this.a.setIs_follow("0");
                    this.b.notifyDataSetChanged();
                    b.b(this.b).a(this.a.getUserid());
                    as.b().a(this.a.getUserid(), Integer.valueOf(0));
                }
            } else {
                this.b.a = an.a(b.a(this.b), b.a(this.b).getString(R.string.operate_fail), -1);
            }
            if (this.b.a != null) {
                this.b.a.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
