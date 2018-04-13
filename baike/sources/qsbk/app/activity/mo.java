package qsbk.app.activity;

import android.net.Uri;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ToastAndDialog;

class mo implements HttpCallBack {
    final /* synthetic */ Uri a;
    final /* synthetic */ GroupInfoActivity b;

    mo(GroupInfoActivity groupInfoActivity, Uri uri) {
        this.b = groupInfoActivity;
        this.a = uri;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        String optString = jSONObject.optString("uptoken");
        LogUtil.d("uptoken===========" + optString);
        this.b.a(optString, this.a);
    }

    public void onFailure(String str, String str2) {
        this.b.j();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
    }
}
