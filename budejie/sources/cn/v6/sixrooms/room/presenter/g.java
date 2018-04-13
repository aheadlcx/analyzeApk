package cn.v6.sixrooms.room.presenter;

import android.app.Activity;
import cn.v6.sixrooms.base.VLBlock;

final class g extends VLBlock {
    final /* synthetic */ LocationAndMoblieGiftStartPresenter a;

    g(LocationAndMoblieGiftStartPresenter locationAndMoblieGiftStartPresenter) {
        this.a = locationAndMoblieGiftStartPresenter;
    }

    protected final void process(boolean z) {
        if (LocationAndMoblieGiftStartPresenter.e(this.a).get() != null && !((Activity) LocationAndMoblieGiftStartPresenter.e(this.a).get()).isFinishing()) {
            LocationAndMoblieGiftStartPresenter.a(this.a, null, null);
        }
    }
}
