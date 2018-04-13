package qsbk.app.live.widget;

import android.app.Activity;
import qsbk.app.core.utils.ConfigInfoUtil.UpdateConfigCallback;

class ds implements UpdateConfigCallback {
    final /* synthetic */ GiftBox a;

    ds(GiftBox giftBox) {
        this.a = giftBox;
    }

    public void onSuccess() {
        this.a.initGiftView();
    }

    public void onFailed(int i) {
        if (this.a.c.getVisibility() == 0) {
            this.a.b.showError((Activity) this.a.a, i, this.a);
        }
    }

    public void onFinish() {
    }
}
