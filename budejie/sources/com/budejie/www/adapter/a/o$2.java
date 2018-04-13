package com.budejie.www.adapter.a;

import android.text.TextUtils;
import com.budejie.www.R;
import com.budejie.www.bean.Fans;
import com.budejie.www.bean.ResultBean;
import com.budejie.www.type.SearchItem;
import com.budejie.www.util.an;
import com.budejie.www.util.as;
import com.budejie.www.util.z;
import net.tsz.afinal.a.a;

class o$2 extends a<String> {
    final /* synthetic */ SearchItem a;
    final /* synthetic */ o b;

    o$2(o oVar, SearchItem searchItem) {
        this.b = oVar;
        this.a = searchItem;
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
                    this.b.a = an.a(o.a(this.b), o.a(this.b).getString(R.string.operate_fail), -1);
                } else {
                    this.b.a = an.a(o.a(this.b), msg, -1);
                }
                if (!TextUtils.isEmpty(code) && "0".equals(code)) {
                    this.a.setRelationship("1");
                    int intValue = Integer.valueOf(this.a.getFansCount()).intValue() + 1;
                    if (intValue >= 0) {
                        this.a.setFansCount(String.valueOf(intValue));
                    }
                    this.b.notifyDataSetChanged();
                    o.b(this.b).a(new Fans(this.a));
                    as.b().a(this.a.getId(), Integer.valueOf(1));
                }
            } else {
                this.b.a = an.a(o.a(this.b), o.a(this.b).getString(R.string.operate_fail), -1);
            }
            if (this.b.a != null) {
                this.b.a.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
