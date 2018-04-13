package qsbk.app.live.widget;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.core.model.User;
import qsbk.app.core.net.NetworkCallback;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.live.R;
import qsbk.app.live.ui.LiveBaseActivity;

class jh extends NetworkCallback {
    final /* synthetic */ User a;
    final /* synthetic */ UserCardDialog b;

    jh(UserCardDialog userCardDialog, User user) {
        this.b = userCardDialog;
        this.a = user;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("rid", Long.toString(this.b.y));
        if (this.a.isAdmin()) {
            hashMap.put("m_uid", Long.toString(this.a.getOriginId()));
            hashMap.put("m_source", Long.toString(this.a.getOrigin()));
        } else {
            hashMap.put("cm_uid", Long.toString(this.a.getOriginId()));
            hashMap.put("cm_source", Long.toString(this.a.getOrigin()));
        }
        return hashMap;
    }

    public void onSuccess(JSONObject jSONObject) {
        ToastUtil.Short(this.b.a.getString(this.a.isAdmin() ? R.string.admin_set_tips : R.string.admin_cancel_tips, new Object[]{this.a.name}));
        if (this.b.a instanceof LiveBaseActivity) {
            ((LiveBaseActivity) this.b.a).onAddOrCancelAdminSuccess(this.a);
        }
    }

    public void onFailed(int i, String str) {
        if (i != -38 && i != -39) {
            ToastUtil.Short(str);
            this.a.reverseAdmin();
            this.b.r.setText(this.a.isAdmin() ? R.string.admin_cancel : R.string.admin_set);
        } else if (this.b.a instanceof LiveBaseActivity) {
            ((LiveBaseActivity) this.b.a).onAddOrCancelAdminSuccess(this.a);
        }
    }
}
