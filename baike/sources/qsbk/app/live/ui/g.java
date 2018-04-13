package qsbk.app.live.ui;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.core.model.CustomButton;
import qsbk.app.core.model.User;
import qsbk.app.core.net.Callback;

class g extends Callback {
    final /* synthetic */ User a;
    final /* synthetic */ LiveBaseActivity b;

    g(LiveBaseActivity liveBaseActivity, User user) {
        this.b = liveBaseActivity;
        this.a = user;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("query_source", Long.toString(this.a.getOrigin()));
        hashMap.put("query_source_id", Long.toString(this.a.getOriginId()));
        return hashMap;
    }

    public void onSuccess(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("support_family");
        if (optJSONObject != null) {
            if (TextUtils.isEmpty(optJSONObject.optString(CustomButton.POSITION_BOTTOM))) {
                this.b.bi.setVisibility(8);
                return;
            }
            this.b.bi.setVisibility(0);
            this.b.bh.setText(optJSONObject.optString(CustomButton.POSITION_BOTTOM));
            this.b.bi.setOnClickListener(new h(this, optJSONObject));
        }
    }
}
