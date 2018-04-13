package com.budejie.www.activity.auditpost;

import android.app.Activity;
import com.budejie.www.adapter.g.b.j;
import com.budejie.www.adapter.g.b.v;
import com.budejie.www.bean.TouGaoItem;
import com.tencent.connect.common.Constants;

public class f extends g {
    public f(Activity activity, TouGaoItem touGaoItem) {
        super(activity, touGaoItem);
    }

    public void a(TouGaoItem touGaoItem) {
        super.a(touGaoItem);
        this.c.setType(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
        j jVar = new j(this.a, this.d);
        jVar.a(this.b);
        jVar.a(this.d);
        v vVar = new v(this.a, this.d);
        vVar.a(this.b);
        vVar.a(this.d);
    }
}
