package qsbk.app.im;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.GroupInfo;

class fd implements HttpCallBack {
    final /* synthetic */ GroupConversationActivity a;

    fd(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (QsbkApp.currentUser != null) {
            JSONObject optJSONObject = jSONObject.optJSONObject("tribe_detail");
            if (optJSONObject != null) {
                this.a.a(new GroupInfo(optJSONObject));
            }
        }
    }

    public void onFailure(String str, String str2) {
    }
}
