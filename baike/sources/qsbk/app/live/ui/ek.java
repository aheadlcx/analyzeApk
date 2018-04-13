package qsbk.app.live.ui;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.core.net.NetworkCallback;
import qsbk.app.live.model.VideoFilterData;

class ek extends NetworkCallback {
    final /* synthetic */ LivePushActivity a;

    ek(LivePushActivity livePushActivity) {
        this.a = livePushActivity;
    }

    public void onPreExecute() {
        super.onPreExecute();
    }

    public void onSuccess(JSONObject jSONObject) {
        this.a.ci.setClickable(true);
        this.a.a(jSONObject);
    }

    public void onFailed(int i, String str) {
        this.a.b("( " + i + " )" + str);
        if (i < 0) {
            this.a.f(str);
        }
        this.a.N();
    }

    public void onFinished() {
        this.a.hideSavingDialog();
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("userID", this.a.ax.getOriginId() + "");
        hashMap.put("streamID", this.a.aA);
        hashMap.put("filter", VideoFilterData.ORIGIN);
        return hashMap;
    }
}
