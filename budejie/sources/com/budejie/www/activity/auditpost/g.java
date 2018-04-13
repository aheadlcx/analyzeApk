package com.budejie.www.activity.auditpost;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AbsListView.LayoutParams;
import android.widget.LinearLayout;
import com.budejie.www.R;
import com.budejie.www.adapter.RowType;
import com.budejie.www.adapter.g.b;
import com.budejie.www.adapter.g.b.f;
import com.budejie.www.adapter.g.b.v;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.TouGaoItem;
import com.budejie.www.h.c;
import com.budejie.www.util.an;

public class g implements d<TouGaoItem> {
    protected Context a;
    protected LinearLayout b;
    protected ListItemObject c;
    protected b<ListItemObject> d;

    public g(Activity activity, TouGaoItem touGaoItem) {
        this.a = activity;
        a(touGaoItem);
    }

    public void a(TouGaoItem touGaoItem) {
        this.c = an.a(touGaoItem);
        this.c.setType("29");
        this.d = new b(null, this.c, 0, RowType.TXT_ROW, 0);
        this.b = new LinearLayout(this.a);
        this.b.setLayoutParams(new LayoutParams(-1, -2));
        this.b.setOrientation(1);
        this.b.setBackgroundResource(c.a().b(R.attr.audit_content_bg));
        f fVar = new f(this.a, this.d);
        fVar.a(this.b);
        fVar.a(this.d);
        if ("29".equals(touGaoItem.getType())) {
            v vVar = new v(this.a, this.d);
            vVar.a(this.b);
            vVar.a(this.d);
        }
    }

    public View a() {
        return this.b;
    }
}
