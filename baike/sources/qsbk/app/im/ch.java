package qsbk.app.im;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.GroupInfo;

class ch implements HttpCallBack {
    final /* synthetic */ cg a;

    ch(cg cgVar) {
        this.a = cgVar;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (QsbkApp.currentUser != null && !this.a.b.a.isFinishing()) {
            JSONObject optJSONObject = jSONObject.optJSONObject("tribe_detail");
            if (optJSONObject != null && this.a.b.a.A != null) {
                GroupInfo groupInfo = new GroupInfo(optJSONObject);
                this.a.b.a.A.setSubTitle(String.format("来自群 %s", new Object[]{groupInfo.name}));
            }
        }
    }

    public void onFailure(String str, String str2) {
    }
}
