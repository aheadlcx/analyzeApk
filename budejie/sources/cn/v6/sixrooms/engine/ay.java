package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLBlock;
import cn.v6.sixrooms.bean.WrapRoomInfo;

final class ay extends VLBlock {
    final /* synthetic */ WrapRoomInfo a;
    final /* synthetic */ ax b;

    ay(ax axVar, WrapRoomInfo wrapRoomInfo) {
        this.b = axVar;
        this.a = wrapRoomInfo;
    }

    protected final void process(boolean z) {
        this.b.b.a.getPriv(this.a.getIsUserSafe());
    }
}
