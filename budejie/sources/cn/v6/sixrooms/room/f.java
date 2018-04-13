package cn.v6.sixrooms.room;

import cn.v6.sixrooms.bean.BroadcastBean;

final class f implements Runnable {
    final /* synthetic */ BroadcastBean a;
    final /* synthetic */ c b;

    f(c cVar, BroadcastBean broadcastBean) {
        this.b = cVar;
        this.a = broadcastBean;
    }

    public final void run() {
        this.b.a.processBroadcast(this.a);
    }
}
