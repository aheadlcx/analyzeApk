package cn.v6.sixrooms.room.presenter;

import cn.v6.sixrooms.engine.GainMobileStarsEngine.CallBack;

final class h implements CallBack {
    final /* synthetic */ LocationAndMoblieGiftStartPresenter a;

    h(LocationAndMoblieGiftStartPresenter locationAndMoblieGiftStartPresenter) {
        this.a = locationAndMoblieGiftStartPresenter;
    }

    public final void start() {
        if (LocationAndMoblieGiftStartPresenter.d(this.a) != null) {
            LocationAndMoblieGiftStartPresenter.d(this.a).showLoadingDialog();
        }
    }

    public final void result(String str, String str2) {
        if (LocationAndMoblieGiftStartPresenter.d(this.a) != null) {
            LocationAndMoblieGiftStartPresenter.d(this.a).hideMobileStarGift();
            LocationAndMoblieGiftStartPresenter.d(this.a).result(str, str2);
        }
    }

    public final void handleErrorInfo(String str, String str2) {
        if (LocationAndMoblieGiftStartPresenter.d(this.a) != null) {
            LocationAndMoblieGiftStartPresenter.d(this.a).hideMobileStarGift();
            LocationAndMoblieGiftStartPresenter.d(this.a).handleErrorInfo(str, str2);
        }
    }

    public final void error(int i) {
        if (LocationAndMoblieGiftStartPresenter.d(this.a) != null) {
            LocationAndMoblieGiftStartPresenter.d(this.a).hideMobileStarGift();
            LocationAndMoblieGiftStartPresenter.d(this.a).error(i);
        }
    }
}
