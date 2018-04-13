package cn.v6.sixrooms.room;

import cn.v6.sixrooms.bean.ErrorBean;

final class d implements Runnable {
    final /* synthetic */ ErrorBean a;
    final /* synthetic */ c b;

    d(c cVar, ErrorBean errorBean) {
        this.b = cVar;
        this.a = errorBean;
    }

    public final void run() {
        BaseRoomActivity.a(this.b.a, this.a);
    }
}
