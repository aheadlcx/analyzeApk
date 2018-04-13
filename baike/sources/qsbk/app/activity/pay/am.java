package qsbk.app.activity.pay;

import android.support.v4.app.NotificationCompat;
import com.alipay.sdk.app.statistic.c;
import com.baidu.mobstat.Config;
import com.facebook.common.util.UriUtil;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelpay.PayReq;
import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.Payment;
import qsbk.app.utils.ToastUtil;

class am implements HttpCallBack {
    final /* synthetic */ Payment a;
    final /* synthetic */ LaiseePaymentActivity b;

    am(LaiseePaymentActivity laiseePaymentActivity, Payment payment) {
        this.b = laiseePaymentActivity;
        this.a = payment;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR) != 0) {
            onFailure(null, jSONObject.optString("err_msg"));
        } else if (this.a.mId == 2) {
            try {
                JSONObject optJSONObject = jSONObject.optJSONObject(UriUtil.LOCAL_RESOURCE_SCHEME);
                String string = optJSONObject.getString("msg");
                this.b.x = optJSONObject.getString(c.G);
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
                    payReq.extData = "out_trade_no=\"" + this.b.x + "\"&amount=\"" + this.b.t.totalMoney + "\"" + "&source=" + "\"" + "qiubai_wallet" + "\"";
                    if (!this.b.b(payReq.appId)) {
                        this.b.k();
                        this.b.a(1, "您还没有安装微信客户端", this.b.x, "weixin", null);
                    } else if (this.b.v != null) {
                        this.b.v.sendReq(payReq);
                    }
                }
            } catch (Exception e) {
                onFailure(null, e.getMessage());
                e.printStackTrace();
            }
        } else if (this.a.mId == 3) {
            String optString = jSONObject.optString(UriUtil.LOCAL_RESOURCE_SCHEME);
            this.b.x = this.b.a(optString, c.G);
            new Thread(new an(this, optString)).start();
        }
    }

    public void onFailure(String str, String str2) {
        ToastUtil.Short(str2);
        this.b.k();
    }
}
