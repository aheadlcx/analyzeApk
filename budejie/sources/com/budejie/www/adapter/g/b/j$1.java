package com.budejie.www.adapter.g.b;

import android.os.Handler;
import android.os.Message;
import com.budejie.www.R;
import com.umeng.analytics.MobclickAgent;

class j$1 extends Handler {
    final /* synthetic */ j a;

    j$1(j jVar) {
        this.a = jVar;
    }

    public void handleMessage(Message message) {
        if (message.what == 1) {
            this.a.g();
        } else if (j.a(this.a).f.a != null) {
            j.b(this.a).f.a.performClick();
            MobclickAgent.onEvent(j.c(this.a), j.d(this.a).getString(R.string.double_click_praise), j.e(this.a).getString(R.string.double_click_praise_describe));
        }
    }
}
