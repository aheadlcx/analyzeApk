package qsbk.app.live.ui;

import android.text.TextUtils;
import qsbk.app.activity.pay.PayPWDUniversalActivity;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;

class ev extends Callback {
    final /* synthetic */ LivePushActivity a;

    ev(LivePushActivity livePushActivity) {
        this.a = livePushActivity;
    }

    public void onSuccess(BaseResponse baseResponse) {
        Object simpleDataStr = baseResponse.getSimpleDataStr("token");
        String simpleDataStr2 = baseResponse.getSimpleDataStr(PayPWDUniversalActivity.KEY);
        if (!TextUtils.isEmpty(simpleDataStr)) {
            this.a.uploadAvatarToQiniu(this.a.bw, simpleDataStr2, simpleDataStr);
        }
    }

    public void onFailed(int i, String str) {
        this.a.showSnackbar(str);
        this.a.hideSavingDialog();
    }
}
