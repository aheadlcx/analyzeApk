package qsbk.app.live.widget;

import org.json.JSONObject;
import qsbk.app.core.net.NetworkCallback;
import qsbk.app.core.utils.AppUtils;

class du extends NetworkCallback {
    final /* synthetic */ GiftBox a;

    du(GiftBox giftBox) {
        this.a = giftBox;
    }

    public void onSuccess(JSONObject jSONObject) {
        long optLong = jSONObject.optLong("coin");
        AppUtils.getInstance().getUserInfoProvider().setBalance(optLong);
        this.a.setBalanceView(optLong);
        this.a.m = false;
    }

    public void onFailed(int i, String str) {
    }
}
