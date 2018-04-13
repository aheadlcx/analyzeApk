package cn.v6.sixrooms.room;

import cn.v6.sixrooms.bean.WelcomeBean;

final class j implements Runnable {
    final /* synthetic */ WelcomeBean a;
    final /* synthetic */ c b;

    j(c cVar, WelcomeBean welcomeBean) {
        this.b = cVar;
        this.a = welcomeBean;
    }

    public final void run() {
        this.b.a.processWelcome(this.a);
    }
}
