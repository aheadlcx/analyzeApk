package com.budejie.www.activity.posts;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.ali.auth.third.core.model.Constants;
import com.alipay.sdk.util.j;
import com.budejie.www.R;
import com.budejie.www.activity.OauthWeiboBaseAct;
import com.budejie.www.http.n;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.z;
import com.umeng.analytics.MobclickAgent;
import java.util.HashMap;

class c$3 extends Handler {
    final /* synthetic */ c a;

    c$3(c cVar) {
        this.a = cVar;
    }

    public void handleMessage(Message message) {
        int i = 0;
        int i2 = message.what;
        if (i2 == 600) {
            Bundle bundle = (Bundle) message.obj;
            Object string = bundle.getString(j.c);
            final int i3 = bundle.getInt("notificationId");
            if (TextUtils.isEmpty(string)) {
                c.d(this.a).a(i3, i, R.string.forwarfail);
            } else {
                HashMap l = z.l(string);
                c.d(this.a).a(i3, true, (String) l.get("msg"));
                if ("0".equals(l.get(j.c))) {
                }
            }
            new Thread(this) {
                final /* synthetic */ c$3 b;

                public void run() {
                    try {
                        Thread.sleep(1000);
                        this.b.a.e.sendMessage(this.b.a.e.obtainMessage(817, Integer.valueOf(i3)));
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
        } else if (i2 == 817) {
            c.d(this.a).a(((Integer) message.obj).intValue());
        } else if (i2 == 812) {
            r0 = (String) message.obj;
            if (TextUtils.isEmpty(r0)) {
                c.a(this.a, an.a(c.e(this.a), c.e(this.a).getString(R.string.bind_failed), -1));
                c.f(this.a).show();
                MobclickAgent.onEvent(c.e(this.a), "weibo_bind", "sina_faild");
                return;
            }
            try {
                i = Integer.parseInt(r0);
            } catch (NumberFormatException e) {
            }
            if (i < 0) {
                c.a(this.a, an.a(c.e(this.a), c.e(this.a).getString(R.string.bind_failed), -1));
                c.f(this.a).show();
                MobclickAgent.onEvent(c.e(this.a), "weibo_bind", "sina_faild");
                return;
            }
            r2 = z.c(r0);
            if (r2 == null || r2.isEmpty()) {
                c.a(this.a, an.a(c.e(this.a), c.e(this.a).getString(R.string.bind_failed), -1));
                c.f(this.a).show();
                MobclickAgent.onEvent(c.e(this.a), "weibo_bind", "sina_faild");
                return;
            }
            r1 = (String) r2.get("result_msg");
            if ("0".equals((String) r2.get(j.c))) {
                MobclickAgent.onEvent(c.e(this.a), "weibo_bind", "sina_success");
                c.a(this.a, (String) r2.get("id"));
                c.h(this.a).a(c.g(this.a), r2);
                ai.a(c.e(this.a), c.g(this.a), Constants.SERVICE_SCOPE_FLAG_VALUE);
                if (OauthWeiboBaseAct.mAccessToken != null) {
                    c.h(this.a).a(c.g(this.a), OauthWeiboBaseAct.mAccessToken.e());
                }
                c.a(this.a, c.i(this.a).a(c.g(this.a)));
                c.a(this.a, an.a(c.e(this.a), c.e(this.a).getString(R.string.bind_successed), -1));
                c.f(this.a).show();
                if (c.j(this.a).getHuodongBean() != null) {
                    n.a(c.e(this.a), c.j(this.a).getHuodongBean());
                    return;
                }
                return;
            }
            an.a(c.e(this.a), r1, -1).show();
        } else if (i2 == 813) {
            r0 = (String) message.obj;
            if (TextUtils.isEmpty(r0)) {
                c.a(this.a, an.a(c.e(this.a), c.e(this.a).getString(R.string.bind_failed), -1));
                c.f(this.a).show();
                MobclickAgent.onEvent(c.e(this.a), "weibo_bind", "tencent_faild");
                return;
            }
            try {
                i = Integer.parseInt(r0);
            } catch (NumberFormatException e2) {
            }
            if (i < 0) {
                c.a(this.a, an.a(c.e(this.a), c.e(this.a).getString(R.string.bind_failed), -1));
                c.f(this.a).show();
                MobclickAgent.onEvent(c.e(this.a), "weibo_bind", "tencent_faild");
                return;
            }
            r2 = z.c(r0);
            if (r2 == null || r2.isEmpty()) {
                MobclickAgent.onEvent(c.e(this.a), "weibo_bind", "tencent_faild");
                c.a(this.a, an.a(c.e(this.a), c.e(this.a).getString(R.string.bind_failed), -1));
                c.f(this.a).show();
                return;
            }
            r1 = (String) r2.get("result_msg");
            if ("0".equals((String) r2.get(j.c))) {
                MobclickAgent.onEvent(c.e(this.a), "weibo_bind", "tencent_success");
                c.a(this.a, (String) r2.get("id"));
                c.h(this.a).a(c.g(this.a), r2);
                ai.a(c.e(this.a), c.g(this.a), Constants.SERVICE_SCOPE_FLAG_VALUE);
                c.a(this.a, c.i(this.a).a(c.g(this.a)));
                c.a(this.a, an.a(c.e(this.a), c.e(this.a).getString(R.string.bind_successed), -1));
                c.f(this.a).show();
                if (c.j(this.a).getHuodongBean() != null) {
                    n.a(c.e(this.a), c.j(this.a).getHuodongBean());
                    return;
                }
                return;
            }
            an.a(c.e(this.a), r1, -1).show();
        } else if (i2 == 929) {
            r0 = (String) message.obj;
            if (TextUtils.isEmpty(r0)) {
                c.a(this.a, an.a(c.e(this.a), c.e(this.a).getString(R.string.bind_failed), -1));
                c.f(this.a).show();
                MobclickAgent.onEvent(c.e(this.a), "weibo_bind", "qzone_faild");
                return;
            }
            try {
                i = Integer.parseInt(r0);
            } catch (NumberFormatException e3) {
            }
            if (i < 0) {
                c.a(this.a, an.a(c.e(this.a), c.e(this.a).getString(R.string.bind_failed), -1));
                c.f(this.a).show();
                MobclickAgent.onEvent(c.e(this.a), "weibo_bind", "qzone_faild");
                return;
            }
            r2 = z.c(r0);
            if (r2 == null || r2.isEmpty()) {
                c.a(this.a, an.a(c.e(this.a), c.e(this.a).getString(R.string.bind_failed), -1));
                c.f(this.a).show();
                MobclickAgent.onEvent(c.e(this.a), "weibo_bind", "qzone_faild");
                return;
            }
            r1 = (String) r2.get("result_msg");
            if ("0".equals((String) r2.get(j.c))) {
                MobclickAgent.onEvent(c.e(this.a), "weibo_bind", "qzone_success");
                c.a(this.a, (String) r2.get("id"));
                c.h(this.a).a(c.g(this.a), r2);
                ai.a(c.e(this.a), c.g(this.a), Constants.SERVICE_SCOPE_FLAG_VALUE);
                c.a(this.a, c.i(this.a).a(c.g(this.a)));
                c.a(this.a, an.a(c.e(this.a), c.e(this.a).getString(R.string.bind_successed), -1));
                c.f(this.a).show();
                if (c.j(this.a).getHuodongBean() != null) {
                    n.a(c.e(this.a), c.j(this.a).getHuodongBean());
                    return;
                }
                return;
            }
            an.a(c.e(this.a), r1, -1).show();
        }
    }
}
