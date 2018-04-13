package qsbk.app.live.widget;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.core.model.User;
import qsbk.app.core.net.Callback;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.R;

class s extends Callback {
    final /* synthetic */ DollHistoryDialog a;

    s(DollHistoryDialog dollHistoryDialog) {
        this.a = dollHistoryDialog;
    }

    public Map<String, String> getParams() {
        Map hashMap = new HashMap();
        User liveUser = this.a.c.getLiveUser();
        if (liveUser != null) {
            hashMap.put("creatorId", String.valueOf(liveUser.getOriginId()));
            hashMap.put("creatorSource", String.valueOf(liveUser.getOrigin()));
        }
        return hashMap;
    }

    public void onSuccess(JSONObject jSONObject) {
        if (jSONObject.has("msg")) {
            JSONArray optJSONArray = jSONObject.optJSONArray("msg");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                this.a.h.clear();
                this.a.h.addAll((Collection) AppUtils.fromJson(optJSONArray.toString(), new t(this)));
                this.a.f.notifyDataSetChanged();
            }
        }
        if (this.a.h.isEmpty()) {
            this.a.findViewById(R.id.iv_doll_empty).setVisibility(0);
            this.a.findViewById(R.id.tv_doll_empty).setVisibility(0);
        }
        this.a.g.hide();
    }

    public void onFailed(int i, String str) {
        if (this.a.h.isEmpty()) {
            this.a.g.showError(this.a.c, i, str, new u(this));
        } else {
            this.a.g.hide();
        }
    }
}
