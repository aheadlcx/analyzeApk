package cn.v6.sixrooms.engine;

import cn.v6.sixrooms.base.VLBlock;
import cn.v6.sixrooms.bean.WrapRoomInfo;

final class av extends VLBlock {
    final /* synthetic */ WrapRoomInfo a;
    final /* synthetic */ au b;

    av(au auVar, WrapRoomInfo wrapRoomInfo) {
        this.b = auVar;
        this.a = wrapRoomInfo;
    }

    protected final void process(boolean z) {
        this.b.b.a.resultInfo(this.a);
    }
}
