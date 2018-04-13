package cn.v6.sixrooms.utils;

import cn.v6.sixrooms.base.SixRoomsUtils;
import cn.v6.sixrooms.base.VLBlock;

final class q extends VLBlock {
    final /* synthetic */ p a;

    q(p pVar) {
        this.a = pVar;
    }

    protected final void process(boolean z) {
        SixRoomsUtils.gotoLogin(this.a.a);
    }
}
