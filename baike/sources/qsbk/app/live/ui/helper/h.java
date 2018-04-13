package qsbk.app.live.ui.helper;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.core.net.Callback;
import qsbk.app.core.utils.ToastUtil;

class h extends Callback {
    final /* synthetic */ String a;
    final /* synthetic */ g b;

    h(g gVar, String str) {
        this.b = gVar;
        this.a = str;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("s_source", this.b.b.getOrigin() + "");
        hashMap.put("s_uid", this.b.b.getOriginId() + "");
        hashMap.put("level", this.a);
        return hashMap;
    }

    public void onSuccess(JSONObject jSONObject) {
        ToastUtil.Short(jSONObject.optString("msg"));
    }

    public void onFailed(int i, String str) {
        super.onFailed(i, str);
    }
}
