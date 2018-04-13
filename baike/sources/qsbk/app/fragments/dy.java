package qsbk.app.fragments;

import android.support.v4.app.NotificationCompat;
import java.math.BigDecimal;
import org.json.JSONObject;
import qsbk.app.activity.LaiseeSendActivity;
import qsbk.app.activity.pay.PayPWDUniversalActivity;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.Laisee;
import qsbk.app.utils.ToastUtil;

class dy implements HttpCallBack {
    final /* synthetic */ LaiseeSendFragment a;

    dy(LaiseeSendFragment laiseeSendFragment) {
        this.a = laiseeSendFragment;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (!this.a.isDetached()) {
            if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR, -1) == 0) {
                this.a.u = new BigDecimal(jSONObject.optString(PayPWDUniversalActivity.MONEY));
                this.a.z = new Laisee();
                this.a.z.totalMoney = this.a.p;
                this.a.z.content = this.a.B;
                LaiseeSendActivity laiseeSendActivity = (LaiseeSendActivity) this.a.getActivity();
                if (laiseeSendActivity != null) {
                    laiseeSendActivity.laiseePay(this.a.z, this.a.u, this.a.h(), this.a.r);
                }
            }
            this.a.i();
        }
    }

    public void onFailure(String str, String str2) {
        ToastUtil.Short(str2);
        this.a.i();
    }
}
