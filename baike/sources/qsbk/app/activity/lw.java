package qsbk.app.activity;

import android.content.Context;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.model.GroupInfo;
import qsbk.app.utils.GroupActionUtil.ProgressDialogCallBack;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.widget.Switch;

class lw extends ProgressDialogCallBack {
    final /* synthetic */ boolean a;
    final /* synthetic */ GroupInfoActivity b;

    lw(GroupInfoActivity groupInfoActivity, Context context, String str, boolean z) {
        this.b = groupInfoActivity;
        this.a = z;
        super(context, str);
    }

    public void onSuccess(JSONObject jSONObject) {
        super.onSuccess(jSONObject);
        SharePreferenceUtils.setSharePreferencesValue("mute_all_" + this.b.b.id, this.a);
        JSONObject optJSONObject = jSONObject.optJSONObject("tribe_detail");
        if (optJSONObject != null) {
            this.b.b = new GroupInfo(optJSONObject);
            this.b.m();
        }
        this.b.hideLoading();
    }

    public void onFailure(int i, String str) {
        boolean z = false;
        super.onFailure(i, str);
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
        this.b.q.setOnCheckedChangeListener(null);
        Switch j = this.b.q;
        if (!this.a) {
            z = true;
        }
        j.setChecked(z);
        this.b.q.setOnCheckedChangeListener(this.b.J);
    }
}
