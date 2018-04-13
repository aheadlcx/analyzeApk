package com.budejie.www.activity.auditpost;

import android.app.Activity;
import com.budejie.www.adapter.g.b.t;
import com.budejie.www.adapter.g.b.v;
import com.budejie.www.bean.TouGaoItem;

public class h extends g {
    private t e;

    public h(Activity activity, TouGaoItem touGaoItem) {
        super(activity, touGaoItem);
    }

    public void a(TouGaoItem touGaoItem) {
        super.a(touGaoItem);
        this.c.setType("41");
        this.e = new t(this.a, this.d, true);
        this.e.a(this.b);
        this.e.a(this.d);
        v vVar = new v(this.a, this.d);
        vVar.a(this.b);
        vVar.a(this.d);
    }

    public void b() {
        this.e.a();
    }
}
