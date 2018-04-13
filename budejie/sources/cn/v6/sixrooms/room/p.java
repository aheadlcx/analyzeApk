package cn.v6.sixrooms.room;

import cn.v6.sixrooms.room.gift.Gift;
import cn.v6.sixrooms.utils.LogUtils;

final class p implements Runnable {
    final /* synthetic */ Gift a;
    final /* synthetic */ o b;

    p(o oVar, Gift gift) {
        this.b = oVar;
        this.a = gift;
    }

    public final void run() {
        LogUtils.e("base", "processSocketGift");
        this.b.a.processSocketGift(this.a);
        for (OnChatMsgSocketCallBack receiveGift : BaseRoomActivity.b(this.b.a)) {
            receiveGift.receiveGift(this.a);
        }
    }
}
