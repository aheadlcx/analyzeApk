package cn.v6.sixrooms.utils;

import cn.v6.sixrooms.base.SixRoomsUtils;

final class u implements Runnable {
    final /* synthetic */ t a;

    u(t tVar) {
        this.a = tVar;
    }

    public final void run() {
        SixRoomsUtils.gotoLogin(this.a.a);
    }
}
