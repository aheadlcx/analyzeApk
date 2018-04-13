package qsbk.app.fragments;

import qsbk.app.QsbkApp;
import qsbk.app.im.QiushiNotificationCountManager;

class ip implements Runnable {
    final /* synthetic */ a a;
    final /* synthetic */ io b;

    ip(io ioVar, a aVar) {
        this.b = ioVar;
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
                    this.b.a.o = this.a.b.booleanValue();
                }
            }
            if (this.b.a.q != null) {
                this.b.a.q.dismiss();
            }
            if (this.b.a.showSmileOrLike == 0) {
                if (this.b.a.a <= 0) {
                    this.b.a.b.setLoadMoreEnable(false);
                } else {
                    this.b.a.b.setLoadMoreEnable(true);
                    this.b.a.b.setLoadMoreState(2, "查看更早消息");
                }
            } else if (this.b.a.o) {
                this.b.a.b.setLoadMoreEnable(true);
                this.b.a.b.setLoadMoreState(2, "查看更早消息");
            } else {
                this.b.a.b.setLoadMoreEnable(false);
            }
            if (this.b.a.showSmileOrLike == 0) {
                QiushiNotificationCountManager.getInstance(QsbkApp.currentUser.userId).setRead();
            }
        }
    }
}
