package com.budejie.www.activity.htmlpage;

import android.app.Dialog;
import com.budejie.www.http.n;
import com.budejie.www.util.p.a;

class c$4 implements a {
    final /* synthetic */ c a;

    c$4(c cVar) {
        this.a = cVar;
    }

    public void a(int i, Dialog dialog) {
        if (i == 1) {
            this.a.bean.setType(c.SHARE_PLATFORM_SINA);
            c.g(this.a).runOnUiThread(new Runnable(this) {
                final /* synthetic */ c$4 a;

                {
                    this.a = r1;
                }

                public void run() {
                    n.a(c.g(this.a.a), this.a.a.bean);
                }
            });
        } else if (i == 2) {
            this.a.bean.setType(c.SHARE_PLATFORM_TENCENT);
            c.e(this.a).a(c.h(this.a), this.a.bean, c.i(this.a), c.b(this.a), c.j(this.a), c.k(this.a));
        } else if (i == 3) {
            this.a.bean.setType(c.SHARE_PLATFORM_WXFRIENDS);
            c.e(this.a).a(c.h(this.a), this.a.bean, c.d(this.a));
        } else if (i == 4) {
            this.a.bean.setType(c.SHARE_PLATFORM_WXGROUP);
            c.e(this.a).b(c.h(this.a), this.a.bean, c.d(this.a));
        } else if (i == 6) {
            this.a.bean.setType(c.SHARE_PLATFORM_QZONE);
            c.e(this.a).b(this.a.bean, c.b(this.a));
        } else if (i == 7) {
            this.a.bean.setType(c.SHARE_PLATFORM_SMS);
            c.e(this.a).a(this.a.bean, c.h(this.a));
        } else if (i == 12) {
            this.a.bean.setType(c.SHARE_PLATFORM_COPY);
            c.e(this.a).b(this.a.bean, c.h(this.a));
        } else if (i == 8) {
            this.a.bean.setType(c.SHARE_PLATFORM_QQFRIENDS);
            c.e(this.a).a(this.a.bean, c.b(this.a));
        } else if (i == 5) {
            dialog.cancel();
            c.h(this.a).edit().putBoolean("isRecommend", false).commit();
        }
        dialog.cancel();
    }
}
