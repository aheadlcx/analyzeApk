package qsbk.app.live.ui;

import org.json.JSONObject;
import qsbk.app.core.net.NetworkCallback;
import qsbk.app.core.utils.AppUtils;

class da extends NetworkCallback {
    final /* synthetic */ LiveBaseActivity a;

    da(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        long optLong = jSONObject.optLong("coin");
        AppUtils.getInstance().getUserInfoProvider().setBalance(optLong);
        this.a.updateBalance(optLong);
    }

    public void onFailed(int i, String str) {
    }
}
