package qsbk.app.pay.ui;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.alipay.sdk.app.statistic.c;
import com.sina.weibo.sdk.utils.WbAuthConstants;
import qsbk.app.core.utils.NetworkUtils;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.pay.R;

class d extends Handler {
    final /* synthetic */ PayActivity a;

    d(PayActivity payActivity) {
        this.a = payActivity;
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                PayResult payResult = new PayResult((String) message.obj);
                payResult.getResult();
                CharSequence resultStatus = payResult.getResultStatus();
                String string = message.getData().getString(c.G);
                if (TextUtils.equals(resultStatus, "9000")) {
                    ToastUtil.Long(R.string.pay_success);
                    this.a.d(string);
                    this.a.setResult(-1);
                    this.a.a(message);
                    return;
                } else if (TextUtils.equals(resultStatus, WbAuthConstants.AUTH_FAILED_NOT_INSTALL_CODE)) {
                    ToastUtil.Long(R.string.pay_confirming);
                    return;
                } else if (NetworkUtils.getInstance().isNetworkAvailable()) {
                    if (TextUtils.equals(resultStatus, "5000")) {
                        ToastUtil.Long(R.string.pay_repeat);
                    } else if (TextUtils.equals(resultStatus, "6001")) {
                        ToastUtil.Long(R.string.pay_cancel);
                    } else if (TextUtils.equals(resultStatus, "6002")) {
                        ToastUtil.Long(R.string.pay_net_error);
                    } else {
                        ToastUtil.Long(R.string.pay_fail);
                    }
                    this.a.e(string);
                    return;
                } else {
                    ToastUtil.Long(R.string.network_not_well);
                    return;
                }
            case 2:
                ToastUtil.Long("检查结果为：" + message.obj);
                return;
            default:
                return;
        }
    }
}
