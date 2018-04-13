package com.budejie.www.activity.label;

import android.app.Dialog;
import com.budejie.www.activity.htmlpage.c;
import com.budejie.www.util.p.a;

class g$1 implements a {
    final /* synthetic */ g a;

    g$1(g gVar) {
        this.a = gVar;
    }

    public void a(int i, Dialog dialog) {
        if (i == 1) {
            this.a.b.setType(c.SHARE_PLATFORM_SINA);
            g.g(this.a).a(g.a(this.a), this.a.b, g.b(this.a), g.c(this.a), g.d(this.a), g.e(this.a), g.f(this.a));
        } else if (i == 2) {
            this.a.b.setType(c.SHARE_PLATFORM_TENCENT);
            g.g(this.a).b(g.a(this.a), this.a.b, g.b(this.a), g.c(this.a), g.e(this.a), g.f(this.a));
        } else if (i == 3) {
            this.a.b.setType(c.SHARE_PLATFORM_WXFRIENDS);
            g.g(this.a).c(g.a(this.a), this.a.b, g.h(this.a));
        } else if (i == 4) {
            this.a.b.setType(c.SHARE_PLATFORM_WXGROUP);
            g.g(this.a).d(g.a(this.a), this.a.b, g.h(this.a));
        } else if (i == 6) {
            this.a.b.setType(c.SHARE_PLATFORM_QZONE);
            g.g(this.a).c(this.a.b, g.c(this.a));
        } else if (i == 12) {
            this.a.b.setType(c.SHARE_PLATFORM_COPY);
            g.g(this.a).d(this.a.b, g.a(this.a));
        } else if (i == 7) {
            this.a.b.setType(c.SHARE_PLATFORM_SMS);
            g.g(this.a).c(this.a.b, g.a(this.a));
        } else if (i == 8) {
            this.a.b.setType(c.SHARE_PLATFORM_QQFRIENDS);
            g.g(this.a).d(this.a.b, g.c(this.a));
        } else if (i == 5) {
            g.a(this.a).edit().putBoolean("isRecommend", false).commit();
        }
        dialog.cancel();
    }
}
