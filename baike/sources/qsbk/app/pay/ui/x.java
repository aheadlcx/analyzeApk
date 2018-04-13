package qsbk.app.pay.ui;

import java.util.HashMap;
import java.util.Map;
import qsbk.app.activity.pay.PayPWDUniversalActivity;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.ToastUtil;

class x extends Callback {
    final /* synthetic */ WithdrawActivity a;

    x(WithdrawActivity withdrawActivity) {
        this.a = withdrawActivity;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("account", this.a.f.getText().toString());
        hashMap.put("name", this.a.g.getText().toString());
        hashMap.put(PayPWDUniversalActivity.MONEY, this.a.v + "");
        hashMap.put("fee", this.a.t + "");
        hashMap.put("coupon", this.a.E + "");
        return hashMap;
    }

    public void onSuccess(BaseResponse baseResponse) {
        super.onSuccess(baseResponse);
        this.a.y = this.a.a(this.a.y, this.a.B);
        this.a.d();
        this.a.a(baseResponse.getSimpleDataStr("err_msg"));
    }

    public void onFinished() {
        super.onFinished();
        if (this.a.j != null) {
            this.a.j.dismiss();
        }
    }

    public void onFailed(int i, String str) {
        ToastUtil.Long(str);
    }
}
