package qsbk.app.activity.pay;

import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.PayOrder;
import qsbk.app.utils.ToastAndDialog;

class ai implements HttpCallBack {
    final /* synthetic */ ItemUnivasualBuyActivity a;

    ai(ItemUnivasualBuyActivity itemUnivasualBuyActivity) {
        this.a = itemUnivasualBuyActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        this.a.z = PayOrder.parse(jSONObject);
        if (this.a.z == null) {
            onFailure(str, "数据解析错误");
        } else {
            this.a.f();
        }
    }

    public void onFailure(String str, String str2) {
        if (!this.a.isFinishing()) {
            ToastAndDialog.makeText(this.a, str2).show();
            this.a.finish();
        }
    }
}
