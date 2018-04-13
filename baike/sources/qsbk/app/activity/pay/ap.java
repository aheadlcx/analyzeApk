package qsbk.app.activity.pay;

import android.support.v4.app.NotificationCompat;
import com.alipay.sdk.app.statistic.c;
import com.baidu.mobstat.Config;
import com.facebook.common.util.UriUtil;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelpay.PayReq;
import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastUtil;

class ap implements HttpCallBack {
    final /* synthetic */ LaiseePaymentActivity a;

    ap(LaiseePaymentActivity laiseePaymentActivity) {
        this.a = laiseePaymentActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR) == 0) {
            this.a.w = jSONObject.optString("charge_id");
            try {
                JSONObject optJSONObject = jSONObject.optJSONObject(UriUtil.LOCAL_RESOURCE_SCHEME);
                String string = optJSONObject.getString("msg");
                this.a.x = optJSONObject.getString(c.G);
                if (string != null) {
                    optJSONObject = new JSONObject(string);
                    BaseReq payReq = new PayReq();
                    payReq.appId = optJSONObject.getString("appid");
                    payReq.partnerId = optJSONObject.getString("partnerid");
                    payReq.prepayId = optJSONObject.getString("prepayid");
                    payReq.nonceStr = optJSONObject.getString("noncestr");
                    payReq.timeStamp = optJSONObject.getString("timestamp");
                    payReq.packageValue = optJSONObject.getString("package");
                    payReq.sign = optJSONObject.getString(Config.SIGN);
                    payReq.extData = "out_trade_no=\"" + this.a.x + "\"&amount=\"" + this.a.t.totalMoney + "\"" + "&source=" + "\"" + "qiubai_wallet" + "\"";
                    if (!this.a.b(payReq.appId)) {
                        this.a.k();
                        this.a.a(1, "您还没有安装微信客户端", this.a.x, "weixin", null);
                        return;
                    } else if (this.a.v != null) {
                        this.a.v.sendReq(payReq);
                        return;
                    } else {
                        return;
                    }
                }
                return;
            } catch (Exception e) {
                onFailure(null, e.getMessage());
                e.printStackTrace();
                return;
            }
        }
        onFailure(null, String.format("返回码不为0，而是%s", new Object[]{Integer.valueOf(jSONObject.optInt(NotificationCompat.CATEGORY_ERROR))}));
    }

    public void onFailure(String str, String str2) {
        this.a.k();
        ToastUtil.Short(str2);
    }
}
