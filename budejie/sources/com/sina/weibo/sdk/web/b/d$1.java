package com.sina.weibo.sdk.web.b;

import android.text.TextUtils;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.d;
import com.sina.weibo.sdk.web.b.b.a;

class d$1 implements d {
    final /* synthetic */ a a;
    final /* synthetic */ d b;

    d$1(d dVar, a aVar) {
        this.b = dVar;
        this.a = aVar;
    }

    public void a(WeiboException weiboException) {
        if (this.a != null) {
            this.a.b("upload pic fail");
        }
    }

    public void a(String str) {
        com.sina.weibo.sdk.web.a a = com.sina.weibo.sdk.web.a.a(str);
        if (a != null && a.a() == 1 && !TextUtils.isEmpty(a.b())) {
            d.a(this.b, a.b());
            if (this.a != null) {
                this.a.a(d.a(this.b));
            }
        } else if (this.a != null) {
            this.a.b("upload pic fail");
        }
    }
}
