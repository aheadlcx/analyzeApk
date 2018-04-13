package com.budejie.www.activity.htmlpage;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.budejie.www.R;
import com.budejie.www.activity.ReprintPostsActivity;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.http.n;
import com.budejie.www.util.an;
import com.budejie.www.util.m;
import com.budejie.www.util.p;
import com.budejie.www.util.p.a;
import com.google.analytics.tracking.android.HitTypes;
import com.umeng.analytics.MobclickAgent;

class c$3 implements a {
    final /* synthetic */ c a;

    c$3(c cVar) {
        this.a = cVar;
    }

    public void a(int i, Dialog dialog) {
        if (i == 1) {
            c.f(this.a).setType(c.SHARE_PLATFORM_SINA);
            c.g(this.a).runOnUiThread(new Runnable(this) {
                final /* synthetic */ c$3 a;

                {
                    this.a = r1;
                }

                public void run() {
                    n.a(c.g(this.a.a), c.f(this.a.a));
                }
            });
        } else if (i == 2) {
            c.f(this.a).setType(c.SHARE_PLATFORM_TENCENT);
            c.e(this.a).a(c.h(this.a), c.f(this.a), c.i(this.a), c.b(this.a), c.j(this.a), c.k(this.a));
        } else if (i == 3) {
            c.f(this.a).setType(c.SHARE_PLATFORM_WXFRIENDS);
            c.e(this.a).b(c.f(this.a), c.d(this.a), c.b(this.a));
        } else if (i == 4) {
            c.f(this.a).setType(c.SHARE_PLATFORM_WXGROUP);
            c.e(this.a).a(c.f(this.a), c.d(this.a), c.b(this.a));
        } else if (i == 6) {
            c.f(this.a).setType(c.SHARE_PLATFORM_QZONE);
            c.e(this.a).a(c.f(this.a), c.b(this.a));
        } else if (i == 7) {
            c.f(this.a).setType(c.SHARE_PLATFORM_SMS);
            c.e(this.a).a(c.f(this.a), c.h(this.a));
        } else if (i == 12) {
            c.f(this.a).setType(c.SHARE_PLATFORM_COPY);
            c.e(this.a).b(c.f(this.a), c.h(this.a));
        } else if (i == 8) {
            c.f(this.a).setType(c.SHARE_PLATFORM_QQFRIENDS);
            c.e(this.a).b(c.f(this.a), c.b(this.a));
        } else if (i == 5) {
            dialog.cancel();
            c.h(this.a).edit().putBoolean("isRecommend", false).commit();
        } else if (i == 14 && c.l(this.a)) {
            if (!an.a(c.h(this.a))) {
                an.a(c.g(this.a), 0, null, null, 0);
            } else if ("61".equals(c.f(this.a).getType()) && c.f(this.a).getOriginal_topic() == null) {
                Toast.makeText(c.g(this.a), R.string.reprint_post_delete, 0).show();
                return;
            } else {
                MobclickAgent.onEvent(c.g(this.a), "E02-A04", "转载");
                Intent intent = new Intent(c.g(this.a), ReprintPostsActivity.class);
                intent.putExtra(HitTypes.ITEM, c.f(this.a));
                c.g(this.a).startActivity(intent);
                dialog.cancel();
            }
        } else if (i == 10 && c.l(this.a)) {
            p.a(c.g(this.a), c.m(this.a).getString(HistoryOpenHelper.COLUMN_UID), ((ListItemObject) c.m(this.a).getSerializable("data")).getWid(), null);
            dialog.cancel();
            MobclickAgent.onEvent(c.g(this.a), "E02-A04", "举报");
        } else if (i == 9) {
            ListItemObject listItemObject = (ListItemObject) c.m(this.a).getSerializable("data");
            if (an.a(c.h(this.a))) {
                if (listItemObject.isCollect()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("wid", c.f(this.a).getWid());
                    bundle.putString("imgPath", c.f(this.a).getImgPath());
                    bundle.putSerializable("data", c.f(this.a));
                    m.b(c.g(this.a), c.b(this.a), bundle);
                } else {
                    c.f(this.a).setForwardAndCollect(false);
                    c.f(this.a).setForwardAndCollect_isweixin(false);
                    c.m(this.a).putSerializable("data", c.f(this.a));
                    m.a(c.g(this.a), c.b(this.a), c.m(this.a));
                }
                dialog.cancel();
                MobclickAgent.onEvent(c.g(this.a), "E02-A04", "收藏");
            } else {
                an.a(c.g(this.a), 0, null, null, 0);
            }
        }
        dialog.cancel();
    }
}
