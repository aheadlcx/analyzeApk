package qsbk.app.live.ui;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.activity.pay.PayPWDUniversalActivity;
import qsbk.app.core.net.Callback;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.ToastAndDialog;
import qsbk.app.live.model.LiveGameDataMessage;
import qsbk.app.live.model.LiveMessage;

class i extends Callback {
    final /* synthetic */ LiveMessage a;
    final /* synthetic */ int b;
    final /* synthetic */ LiveBaseActivity c;

    i(LiveBaseActivity liveBaseActivity, LiveMessage liveMessage, int i) {
        this.c = liveBaseActivity;
        this.a = liveMessage;
        this.b = i;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        if (this.a instanceof LiveGameDataMessage) {
            hashMap.put("round_id", String.valueOf(((LiveGameDataMessage) this.a).getGameRoundId()));
        }
        return hashMap;
    }

    public void onSuccess(JSONObject jSONObject) {
        Object optString = jSONObject.optString(PayPWDUniversalActivity.KEY);
        if (!TextUtils.isEmpty(optString)) {
            AppUtils.getInstance().getUserInfoProvider().shareImage(this.c, optString, this.b);
        }
    }

    public void onFailed(int i, String str) {
        ToastAndDialog.makeText(this.c, str).show();
    }
}
