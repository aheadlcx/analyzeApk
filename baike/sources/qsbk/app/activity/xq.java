package qsbk.app.activity;

import qsbk.app.model.UserInfo;
import qsbk.app.widget.PersonalInfoView;

class xq implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ xp b;

    xq(xp xpVar, int i) {
        this.b = xpVar;
        this.a = i;
    }

    public void run() {
        ((PersonalInfoView) NewFansActivity.m(this.b.a).getSelectedView()).setView((UserInfo) NewFansActivity.c(this.b.a).get(this.a), 1);
    }
}
