package cn.v6.sixrooms.room;

import cn.v6.sixrooms.room.gift.GiftWebview.Callback;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.ToastUtils;

final class au implements Callback {
    final /* synthetic */ RoomActivity a;

    au(RoomActivity roomActivity) {
        this.a = roomActivity;
    }

    public final void animCount(int i, int i2) {
    }

    public final void animComplete() {
        this.a.W.completeH5();
        this.a.c();
    }

    public final void animStart() {
        this.a.M.setVisibility(0);
    }

    public final void animReStart() {
        this.a.V.cleanLoadGiftAnimation();
    }

    public final void animTimeout() {
        this.a.W.completeH5();
        ToastUtils.showToast("礼物加载超时!");
        this.a.c();
    }

    public final void animError(String str) {
        LogUtils.e("GiftAnimQueue", "h5animError" + str);
    }
}
