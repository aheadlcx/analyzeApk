package com.budejie.www.activity.detail;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.budejie.www.R;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.m;

class c$4 extends Handler {
    final /* synthetic */ c a;

    c$4(c cVar) {
        this.a = cVar;
    }

    public void handleMessage(Message message) {
        int i = message.what;
        if (i == 4) {
            try {
                c.d(this.a).setLove(c.d(this.a).getLove() + 1);
            } catch (Exception e) {
            }
        } else if (i == 5) {
            c.a(this.a, ProgressDialog.show(c.c(this.a), "", (String) message.obj, true, true));
        } else if (i == 6) {
            c.w(this.a).cancel();
        } else if (i == 7) {
            an.a(c.c(this.a), c.c(this.a).getString(R.string.already_collected), -1).show();
        } else if (i == 9) {
            c.d(this.a).setRepost(String.valueOf(Integer.parseInt(c.d(this.a).getRepost()) + 1));
            m.a(c.c(this.a), c.x(this.a), c.d(this.a));
        } else if (i == 91) {
            c.d(this.a).setRepost(String.valueOf(Integer.parseInt(c.d(this.a).getRepost()) + 1));
        } else if (i == 10) {
            an.a(c.c(this.a), c.c(this.a).getString(R.string.collect_failed), -1).show();
        } else if (i == 11) {
            CharSequence b = ai.b(c.c(this.a));
            if (an.j(c.c(this.a)) && an.k(c.c(this.a)) && !b.equals("")) {
                an.a(c.c(this.a), false);
                sendEmptyMessage(13);
            } else {
                an.a(c.c(this.a), R.string.collected, R.drawable.collect_tip).show();
            }
            if (!TextUtils.isEmpty(b)) {
                c.d(this.a, "add");
                c.z(this.a).a(c.y(this.a), ai.b(c.c(this.a)), (String) message.obj, 971);
            }
        } else if (i == 12) {
            an.a(c.c(this.a), R.string.collect_fail, R.drawable.collect_tip).show();
        } else if (i == 100001) {
            an.a(c.c(this.a), c.c(this.a).getString(R.string.forwardAndCollect_succeed), -1).show();
            if (!TextUtils.isEmpty(ai.b(c.c(this.a)))) {
                c.d(this.a, "add");
                c.z(this.a).a(c.y(this.a), ai.b(c.c(this.a)), (String) message.obj, 971);
            }
        } else if (i == 829) {
            String str = (String) message.obj;
            if (c.A(this.a) != null) {
                c.A(this.a).a("collectTable", str);
            }
            an.a(c.c(this.a), c.c(this.a).getString(R.string.delete_success), -1).show();
            c.d(this.a, "delete");
            c.z(this.a).a(c.y(this.a), ai.b(c.c(this.a)), str, 971);
        } else if (i == 13) {
            an.b(c.c(this.a), c.q(this.a));
        }
    }
}
