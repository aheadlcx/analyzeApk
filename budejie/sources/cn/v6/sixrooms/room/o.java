package cn.v6.sixrooms.room;

import cn.v6.sixrooms.room.downloader.GiftResDownloader.GiftResDownLoadCallback;
import cn.v6.sixrooms.room.gift.Gift;
import cn.v6.sixrooms.utils.LogUtils;

final class o implements GiftResDownLoadCallback {
    final /* synthetic */ BaseRoomActivity a;

    o(BaseRoomActivity baseRoomActivity) {
        this.a = baseRoomActivity;
    }

    public final void onLoadingFailed(Gift gift) {
        LogUtils.e("base", "onLoadingFailed");
        this.a.l.done();
    }

    public final void onLoadingComplete(Gift gift) {
        LogUtils.e("base", "onLoadingComplete");
        this.a.l.done();
        this.a.handler.post(new p(this, gift));
    }
}
