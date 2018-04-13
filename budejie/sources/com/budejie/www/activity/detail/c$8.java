package com.budejie.www.activity.detail;

import cn.v6.sixrooms.room.RoomActivity;
import com.budejie.www.R;
import com.budejie.www.util.an;
import net.tsz.afinal.a.a;

class c$8 extends a<String> {
    final /* synthetic */ c a;

    c$8(c cVar) {
        this.a = cVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onFailure(Throwable th, int i, String str) {
        c.a(this.a, an.a(c.c(this.a), c.c(this.a).getString(R.string.operate_fail), -1));
        c.I(this.a).show();
    }

    public void onStart() {
        super.onStart();
        if (c.W(this.a) != null) {
            c.W(this.a).show();
        }
    }

    public void a(String str) {
        try {
            if (c.W(this.a).isShowing()) {
                c.W(this.a).dismiss();
            }
            if (RoomActivity.VIDEOTYPE_UNKNOWN.equals(str)) {
                c.a(this.a, an.a(c.c(this.a), c.c(this.a).getString(R.string.operate_fail), -1));
            } else {
                c.a(this.a, an.a(c.c(this.a), c.c(this.a).getString(R.string.operate_success), -1));
                c.e(this.a, false);
            }
            if (c.I(this.a) != null) {
                c.I(this.a).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
