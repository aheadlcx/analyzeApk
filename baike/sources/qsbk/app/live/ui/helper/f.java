package qsbk.app.live.ui.helper;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.core.model.User;
import qsbk.app.core.net.Callback;
import qsbk.app.core.utils.ToastUtil;

final class f extends Callback {
    final /* synthetic */ User a;

    f(User user) {
        this.a = user;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("s_source", this.a.getOrigin() + "");
        hashMap.put("s_uid", this.a.getOriginId() + "");
        return hashMap;
    }

    public void onSuccess(JSONObject jSONObject) {
        ToastUtil.Short(jSONObject.optString("msg"));
    }

    public void onFailed(int i, String str) {
    }
}
