package qsbk.app.adapter;

import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.ToastAndDialog;

class dr implements HttpCallBack {
    final /* synthetic */ int a;
    final /* synthetic */ UnSubscribeAdapter b;

    dr(UnSubscribeAdapter unSubscribeAdapter, int i) {
        this.b = unSubscribeAdapter;
        this.a = i;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (Constants.SUBSCRIBE_TA.equalsIgnoreCase(str)) {
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "成功订阅" + ((BaseUserInfo) this.b.b.get(this.a)).userName + "的糗事", Integer.valueOf(0)).show();
            this.b.b.remove(this.a);
            this.b.notifyDataSetChanged();
        }
    }

    public void onFailure(String str, String str2) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
    }
}
