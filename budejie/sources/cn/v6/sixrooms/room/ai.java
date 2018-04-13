package cn.v6.sixrooms.room;

import cn.v6.sixrooms.constants.GiftIdStrs;
import cn.v6.sixrooms.room.gift.Gift;
import cn.v6.sixrooms.room.gift.GiftPoseFactory;
import cn.v6.sixrooms.room.utils.GiftAnimQueue.Callback;
import cn.v6.sixrooms.utils.LogUtils;

final class ai implements Callback {
    final /* synthetic */ RoomActivity a;

    ai(RoomActivity roomActivity) {
        this.a = roomActivity;
    }

    public final void showH5Gift(Gift gift) {
        if (this.a.V != null) {
            this.a.V.loadGift(gift);
            this.a.W.setH5Disptaching();
        }
    }

    public final void showNativeGift(Gift gift) {
        if (this.a.K != null) {
            if (!gift.getGtype().equals("1") || GiftIdStrs.fireworksIds.contains(gift.getId())) {
                this.a.K.addAnimScene(gift);
                if (this.a.W != null) {
                    this.a.W.setNativeDisptaching();
                    LogUtils.e("GiftAnimQueue", "addAnimScene");
                    return;
                }
                return;
            }
            this.a.K.addAnimScene(gift, new GiftPoseFactory());
            if (this.a.W != null) {
                this.a.W.setNativeDisptaching();
                LogUtils.e("GiftAnimQueue", "addAnimScene");
            }
        }
    }
}
