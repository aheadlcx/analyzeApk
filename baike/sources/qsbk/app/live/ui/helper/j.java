package qsbk.app.live.ui.helper;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.core.net.Callback;
import qsbk.app.core.utils.ToastUtil;

class j extends Callback {
    final /* synthetic */ String a;
    final /* synthetic */ i b;

    j(i iVar, String str) {
        this.b = iVar;
        this.a = str;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("creator_source", this.b.b.getOrigin() + "");
        hashMap.put("creator_id", this.b.b.getOriginId() + "");
        hashMap.put("warnType", this.b.c);
        hashMap.put("content", this.a);
        return hashMap;
    }

    public void onSuccess(JSONObject jSONObject) {
        ToastUtil.Short("发送" + this.b.d + "成功");
    }

    public void onFailed(int i, String str) {
        super.onFailed(i, str);
    }
}
