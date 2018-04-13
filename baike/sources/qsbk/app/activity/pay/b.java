package qsbk.app.activity.pay;

import java.math.BigDecimal;
import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.Product;
import qsbk.app.utils.ToastAndDialog;

class b implements HttpCallBack {
    final /* synthetic */ ItemSignCardBuyActivity a;

    b(ItemSignCardBuyActivity itemSignCardBuyActivity) {
        this.a = itemSignCardBuyActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        ItemSignCardPaymentActivity.launch(this.a, new Product(this.a.f, this.a.g, this.a.h, this.a.d), new BigDecimal(jSONObject.optString(PayPWDUniversalActivity.MONEY)), this.a.e, 0);
        this.a.a.dismiss();
    }

    public void onFailure(String str, String str2) {
        ToastAndDialog.makeText(this.a, str2).show();
        this.a.finish();
    }
}
