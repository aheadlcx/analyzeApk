package com.budejie.www.adapter.a;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.a;
import com.umeng.analytics.MobclickAgent;

public class m$a implements OnClickListener {
    final /* synthetic */ m a;
    private Context b;
    private ListItemObject c;

    private m$a(m mVar, ListItemObject listItemObject, Context context) {
        this.a = mVar;
        this.b = context;
        this.c = listItemObject;
    }

    public void onClick(View view) {
        MobclickAgent.onEvent(this.b, "E06_A17", "新帖点击");
        a.a((Activity) this.b, this.c, "", false);
    }
}
