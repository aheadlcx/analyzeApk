package qsbk.app.live.ui;

import qsbk.app.core.utils.ConfigInfoUtil.UpdateConfigCallback;
import qsbk.app.core.utils.PreferenceUtils;

class bn implements UpdateConfigCallback {
    final /* synthetic */ int a;
    final /* synthetic */ LiveBaseActivity b;

    bn(LiveBaseActivity liveBaseActivity, int i) {
        this.b = liveBaseActivity;
        this.a = i;
    }

    public void onSuccess() {
        if (this.a == -101 || this.a == -102 || this.a == -103) {
            PreferenceUtils.instance().removeKey("live_barrage_switch");
            LiveBaseActivity.y(this.b);
        }
    }

    public void onFailed(int i) {
    }

    public void onFinish() {
    }
}
