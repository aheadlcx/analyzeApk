package qsbk.app.activity.pay;

import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class a implements HttpCallBack {
    final /* synthetic */ ItemSignCardBuyActivity a;

    a(ItemSignCardBuyActivity itemSignCardBuyActivity) {
        this.a = itemSignCardBuyActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        this.a.d = jSONObject.optString("total_price");
        this.a.e = jSONObject.optString("request_id");
        this.a.d();
    }

    public void onFailure(String str, String str2) {
        ToastAndDialog.makeText(this.a, str2).show();
        this.a.finish();
    }
}
