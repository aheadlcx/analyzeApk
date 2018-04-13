package qsbk.app.fragments;

import qsbk.app.QsbkApp;
import qsbk.app.im.QiuyouquanNotificationCountManager;

class kd implements Runnable {
    final /* synthetic */ a a;
    final /* synthetic */ kc b;

    kd(kc kcVar, a aVar) {
        this.b = kcVar;
        this.a = aVar;
    }

    public void run() {
        if (this.b.a.getActivity() != null && !this.b.a.getActivity().isFinishing()) {
            if (this.a == null || this.a.a.size() <= 0) {
                this.b.a.d();
            } else {
                this.b.a.f = new b(this.b.a, this.a.a);
                if (this.a.c > 1) {
                    this.b.a.a = this.a.c;
                }
                this.b.a.c.setAdapter(this.b.a.f);
                if (this.a.b != null) {
                    this.b.a.s = this.a.b.booleanValue();
                }
            }
            if (this.b.a.u != null) {
                this.b.a.u.dismiss();
            }
            if (this.b.a.g || this.b.a.i || this.b.a.k || this.b.a.m) {
                if (this.b.a.s) {
                    this.b.a.b.setLoadMoreEnable(true);
                    this.b.a.b.setLoadMoreState(2, "查看更早消息");
                } else {
                    this.b.a.b.setLoadMoreEnable(false);
                }
            } else if (this.b.a.a <= 0) {
                this.b.a.b.setLoadMoreEnable(false);
            } else {
                this.b.a.b.setLoadMoreEnable(true);
                this.b.a.b.setLoadMoreState(2, "查看更早消息");
            }
            if (!this.b.a.g && !this.b.a.i && !this.b.a.k && !this.b.a.m) {
                QiuyouquanNotificationCountManager.getInstance(QsbkApp.currentUser.userId).setRead();
            }
        }
    }
}
