package qsbk.app.live.ui;

import qsbk.app.core.utils.ConfigInfoUtil.UpdateConfigCallback;
import qsbk.app.core.utils.GiftResSync;

class j implements UpdateConfigCallback {
    final /* synthetic */ LiveBaseActivity a;

    j(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void onSuccess() {
        if (this.a.A != null) {
            this.a.A.initGiftView();
        }
    }

    public void onFailed(int i) {
    }

    public void onFinish() {
        GiftResSync.checkUpdate();
    }
}
