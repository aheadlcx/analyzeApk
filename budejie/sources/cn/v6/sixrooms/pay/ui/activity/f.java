package cn.v6.sixrooms.pay.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import cn.v6.sixrooms.pay.alipay.PayResult;
import cn.v6.sixrooms.utils.LogUtils;
import com.alibaba.baichuan.android.trade.adapter.alipay.AlibcAlipay;

final class f extends Handler {
    final /* synthetic */ AlipayActivity a;

    f(AlipayActivity alipayActivity) {
        this.a = alipayActivity;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                String str = (String) message.obj;
                LogUtils.i("AlipayActivity", "result = " + str);
                PayResult payResult = new PayResult(str);
                payResult.getResult();
                CharSequence resultStatus = payResult.getResultStatus();
                LogUtils.i("AlipayActivity", "resultStatus = " + resultStatus);
                if (TextUtils.equals(resultStatus, AlibcAlipay.PAY_SUCCESS_CODE)) {
                    AlipayActivity.s(this.a);
                    AlipayActivity.t(this.a);
                    break;
                }
                break;
        }
        super.handleMessage(message);
    }
}
