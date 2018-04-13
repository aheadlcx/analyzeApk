package com.budejie.www.activity.htmlpage;

import com.ali.auth.third.core.model.Constants;
import com.budejie.www.f.a;

class c$1 implements a {
    final /* synthetic */ c a;

    c$1(c cVar) {
        this.a = cVar;
    }

    public void a(int i, String str) {
        if (!Constants.SERVICE_SCOPE_FLAG_VALUE.equals(str)) {
            c.b(this.a).sendMessage(c.b(this.a).obtainMessage(HtmlFeatureActivity.d));
        } else if ("0".equals(c.a(this.a))) {
            c.b(this.a).sendMessage(c.b(this.a).obtainMessage(HtmlFeatureActivity.c));
        } else if ("1".equals(c.a(this.a))) {
            c.b(this.a).sendMessage(c.b(this.a).obtainMessage(HtmlFeatureActivity.b));
            c.a(this.a, c.c(this.a));
        }
    }

    public void a(int i) {
        c.b(this.a).sendMessage(c.b(this.a).obtainMessage(HtmlFeatureActivity.d));
    }
}
