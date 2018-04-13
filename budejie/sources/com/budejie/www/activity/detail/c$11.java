package com.budejie.www.activity.detail;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.R;
import com.budejie.www.bean.UserItem;
import com.budejie.www.util.an;
import com.budejie.www.util.p;

class c$11 implements OnClickListener {
    final /* synthetic */ c a;

    c$11(c cVar) {
        this.a = cVar;
    }

    public void onClick(View view) {
        if (view == c.P(this.a)) {
            c.a(this.a, null);
            c.M(this.a);
        } else if (view == c.N(this.a)) {
            if (!c.ab(this.a)) {
                c.e(this.a, c.ac(this.a).getString("id", ""));
                if (TextUtils.isEmpty(c.B(this.a))) {
                    an.a(c.c(this.a), 0, null, null, 0);
                    return;
                }
                UserItem e = c.C(this.a).e(c.B(this.a));
                if (TextUtils.isEmpty(e.getLevel()) || Integer.parseInt(e.getLevel()) < c.ad(this.a)) {
                    p.a(c.c(this.a), c.c(this.a).getString(R.string.send_media_comment_level_message, new Object[]{Integer.valueOf(c.ad(this.a))}), c.c(this.a).getString(R.string.send_media_comment_level_ok), null);
                    return;
                }
                if (c.ae(this.a) == c$a.VOICE) {
                    c.a(this.a, c$a.NORMAL);
                } else {
                    c.a(this.a, c$a.VOICE);
                }
                this.a.c();
            }
        } else if (view == c.af(this.a) && this.a.b != null) {
            this.a.b.a(false);
        }
    }
}
