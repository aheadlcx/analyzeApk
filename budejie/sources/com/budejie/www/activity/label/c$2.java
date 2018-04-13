package com.budejie.www.activity.label;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;

class c$2 implements OnClickListener {
    final /* synthetic */ c a;

    c$2(c cVar) {
        this.a = cVar;
    }

    public void onClick(View view) {
        if (TextUtils.isEmpty(ai.b(c.b(this.a)))) {
            an.a(c.b(this.a), 1, null, null, 10);
        } else {
            BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", c.e(this.a).a(c.b(this.a), c.c(this.a).getTheme_id() + "", c.d(this.a)), this.a.d);
        }
    }
}
