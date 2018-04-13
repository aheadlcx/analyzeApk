package qsbk.app.live.adapter;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.core.model.User;
import qsbk.app.core.net.NetworkCallback;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveAdmin;
import qsbk.app.live.ui.LiveBaseActivity;

class c extends NetworkCallback {
    final /* synthetic */ LiveAdmin a;
    final /* synthetic */ AdminAdapter b;

    c(AdminAdapter adminAdapter, LiveAdmin liveAdmin) {
        this.b = adminAdapter;
        this.a = liveAdmin;
    }

    public void onPreExecute() {
        ((BaseActivity) this.b.b).showSavingDialog(R.string.admin_canceling);
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("rid", Long.toString(this.b.d));
        hashMap.put("cm_uid", Long.toString(this.a.s_id));
        hashMap.put("cm_source", Long.toString(this.a.s));
        return hashMap;
    }

    public void onSuccess(JSONObject jSONObject) {
        ToastUtil.Short(this.b.b.getString(R.string.admin_cancel_tips, new Object[]{this.a.n}));
        this.b.c.remove(this.a);
        this.b.notifyDataSetChanged();
        this.b.a.refreshTitle();
        this.b.a.refreshEmptyView();
        if (this.b.b instanceof LiveBaseActivity) {
            User user = new User();
            user.origin = this.a.s;
            user.origin_id = this.a.s_id;
            user.is_admin = 0;
            ((LiveBaseActivity) this.b.b).onAddOrCancelAdminSuccess(user);
        }
    }

    public void onFailed(int i, String str) {
    }

    public void onFinished() {
        ((BaseActivity) this.b.b).hideSavingDialog();
    }
}
