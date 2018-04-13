package qsbk.app.pay.ui;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.core.net.Callback;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.pay.R;

class s extends Callback {
    final /* synthetic */ int a;
    final /* synthetic */ WithdrawActivity b;

    s(WithdrawActivity withdrawActivity, int i) {
        this.b = withdrawActivity;
        this.a = i;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("exchange_money", Integer.toString(this.a));
        return hashMap;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.b.t = jSONObject.optDouble("fee");
        this.b.y = jSONObject.optDouble("valid");
        this.b.E = jSONObject.optLong("coupon");
        AppUtils.getInstance().getUserInfoProvider().setBalance(jSONObject.optLong("coin"));
        this.b.k.setText(this.b.y + "");
        ToastUtil.Long(R.string.pay_withdraw_exchange_success);
    }

    public void onFailed(int i, String str) {
        ToastUtil.Short(str);
    }
}
