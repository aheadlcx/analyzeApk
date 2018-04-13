package com.budejie.www.adapter;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.R;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.a;
import com.budejie.www.util.an;

class e$1 implements OnClickListener {
    final /* synthetic */ ListItemObject a;
    final /* synthetic */ e b;

    e$1(e eVar, ListItemObject listItemObject) {
        this.b = eVar;
        this.a = listItemObject;
    }

    public void onClick(View view) {
        if (an.a(e.a(this.b))) {
            a.a((Activity) e.a(this.b), this.a, "", false);
        } else {
            an.a((Activity) e.a(this.b), e.a(this.b).getString(R.string.nonet), -1).show();
        }
    }
}
