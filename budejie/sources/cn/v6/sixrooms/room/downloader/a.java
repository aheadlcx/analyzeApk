package cn.v6.sixrooms.room.downloader;

import cn.v6.sixrooms.base.VLAsyncHandler;

final class a extends VLAsyncHandler<Object> {
    final /* synthetic */ GiftResDownloader a;

    a(GiftResDownloader giftResDownloader) {
        this.a = giftResDownloader;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            GiftResDownloader.c(this.a);
        } else {
            GiftResDownloader.b(this.a).onLoadingFailed(GiftResDownloader.a(this.a));
        }
    }
}
