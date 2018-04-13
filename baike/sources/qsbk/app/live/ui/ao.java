package qsbk.app.live.ui;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.core.model.User;
import qsbk.app.core.net.NetworkCallback;
import qsbk.app.live.R;

class ao extends NetworkCallback {
    final /* synthetic */ User a;
    final /* synthetic */ LiveBaseActivity b;

    ao(LiveBaseActivity liveBaseActivity, User user) {
        this.b = liveBaseActivity;
        this.a = user;
    }

    public void onPreExecute() {
        super.onPreExecute();
        this.b.showSavingDialog(R.string.share_report_processing);
    }

    public void onSuccess(JSONObject jSONObject) {
        this.b.f(jSONObject.optString("msg"));
    }

    public void onFailed(int i, String str) {
        this.b.f(str);
    }

    public void onFinished() {
        this.b.hideSavingDialog();
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("s_source", this.a.getOrigin() + "");
        hashMap.put("s_uid", this.a.getOriginId() + "");
        hashMap.put("c_source", this.b.d.author.getOrigin() + "");
        hashMap.put("c_uid", this.b.d.author.getOriginId() + "");
        return hashMap;
    }
}
