package qsbk.app.live.ui.family;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.core.model.User;
import qsbk.app.core.net.Callback;
import qsbk.app.core.utils.AppUtils;

class bo extends Callback {
    final /* synthetic */ Message a;
    final /* synthetic */ int b;
    final /* synthetic */ MessageAdapter c;

    bo(MessageAdapter messageAdapter, Message message, int i) {
        this.c = messageAdapter;
        this.a = message;
        this.b = i;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("family_id", String.valueOf(this.a.getFamilyId()));
        User user = this.a.getUser();
        if (user != null) {
            hashMap.put("user_source", String.valueOf(user.getOrigin()));
            hashMap.put("user_id", String.valueOf(user.getOriginId()));
        }
        hashMap.put("source", AppUtils.getInstance().getUserInfoProvider().getUserOrigin() + "");
        hashMap.put("source_id", AppUtils.getInstance().getUserInfoProvider().getUserId() + "");
        hashMap.put("action", String.valueOf(this.b));
        return hashMap;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.a.status = this.b;
        this.c.notifyDataSetChanged();
    }

    public void onFailed(int i, String str) {
        super.onFailed(i, str);
    }
}
