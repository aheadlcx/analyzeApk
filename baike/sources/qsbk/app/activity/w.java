package qsbk.app.activity;

import qsbk.app.utils.LogUtil;

class w implements Runnable {
    final /* synthetic */ ActionBarLoginActivity a;

    w(ActionBarLoginActivity actionBarLoginActivity) {
        this.a = actionBarLoginActivity;
    }

    public void run() {
        LogUtil.d("scrooll to 1,1000");
        ActionBarLoginActivity.h(this.a).scrollTo(0, 1000);
    }
}
