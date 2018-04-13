package qsbk.app.live.ui.helper;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.core.model.User;
import qsbk.app.core.net.Callback;
import qsbk.app.core.utils.ToastUtil;

final class d extends Callback {
    final /* synthetic */ User a;
    final /* synthetic */ User b;

    d(User user, User user2) {
        this.a = user;
        this.b = user2;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("s_source", this.a.getOrigin() + "");
        hashMap.put("s_uid", this.a.getOriginId() + "");
        hashMap.put("r_source", this.b.getOrigin() + "");
        hashMap.put("r_uid", this.b.getOriginId() + "");
        return hashMap;
    }

    public void onSuccess(JSONObject jSONObject) {
        ToastUtil.Short(jSONObject.optString("msg"));
    }

    public void onFailed(int i, String str) {
    }
}
