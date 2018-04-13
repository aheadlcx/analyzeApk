package com.budejie.www.activity.auditpost;

import android.app.Activity;
import com.budejie.www.adapter.g.b.u;
import com.budejie.www.adapter.g.b.v;
import com.budejie.www.bean.TouGaoItem;

public class i extends g {
    public i(Activity activity, TouGaoItem touGaoItem) {
        super(activity, touGaoItem);
    }

    public void a(TouGaoItem touGaoItem) {
        super.a(touGaoItem);
        this.c.setType("31");
        u uVar = new u(this.a, this.d);
        uVar.a(this.b);
        uVar.a(this.d);
        v vVar = new v(this.a, this.d);
        vVar.a(this.b);
        vVar.a(this.d);
    }
}
