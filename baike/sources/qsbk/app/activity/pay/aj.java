package qsbk.app.activity.pay;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.alipay.sdk.app.statistic.c;
import com.sina.weibo.sdk.utils.WbAuthConstants;
import qsbk.app.pay.ui.PayResult;

class aj extends Handler {
    final /* synthetic */ LaiseePaymentActivity a;

    aj(LaiseePaymentActivity laiseePaymentActivity) {
        this.a = laiseePaymentActivity;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case 1:
                PayResult payResult = new PayResult((String) message.obj);
                payResult.getResult();
                CharSequence resultStatus = payResult.getResultStatus();
                message.getData().getString(c.G);
                if (TextUtils.equals(resultStatus, "9000")) {
                    this.a.a(0, null, this.a.x, "alipay", null);
                    return;
                } else if (TextUtils.equals(resultStatus, WbAuthConstants.AUTH_FAILED_NOT_INSTALL_CODE)) {
                    this.a.a(2, "支付结果确认中", this.a.x, "alipay", null);
                    return;
                } else if (TextUtils.equals(resultStatus, "5000")) {
                    this.a.a(2, "重复请求订单", this.a.x, "alipay", null);
                    return;
                } else if (TextUtils.equals(resultStatus, "6001")) {
                    this.a.a(1, "您取消了支付", this.a.x, "alipay", null);
                    return;
                } else if (TextUtils.equals(resultStatus, "6002")) {
                    this.a.a(2, "网络连接出错", this.a.x, "alipay", null);
                    return;
                } else {
                    this.a.a(2, "支付失败", this.a.x, "alipay", null);
                    return;
                }
            default:
                return;
        }
    }
}
