package cn.v6.sixrooms.room.presenter;

import cn.v6.sixrooms.room.engine.MobileStarsStatusEngine.StatusCallBack;

final class f implements StatusCallBack {
    final /* synthetic */ LocationAndMoblieGiftStartPresenter a;

    f(LocationAndMoblieGiftStartPresenter locationAndMoblieGiftStartPresenter) {
        this.a = locationAndMoblieGiftStartPresenter;
    }

    public final void result(boolean z, String str) {
        if (z) {
            if (LocationAndMoblieGiftStartPresenter.d(this.a) != null) {
                LocationAndMoblieGiftStartPresenter.d(this.a).showMobileStarGift();
            }
        } else if (LocationAndMoblieGiftStartPresenter.d(this.a) != null) {
            LocationAndMoblieGiftStartPresenter.d(this.a).hideMobileStarGift();
        }
    }

    public final void error(int i) {
    }
}
