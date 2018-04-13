package qsbk.app.live.ui;

import android.text.TextUtils;
import java.util.Map;
import qsbk.app.core.model.CustomButton;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.ToastUtil;

class p extends Callback {
    final /* synthetic */ CustomButton a;
    final /* synthetic */ LiveBaseActivity b;

    p(LiveBaseActivity liveBaseActivity, CustomButton customButton) {
        this.b = liveBaseActivity;
        this.a = customButton;
    }

    public void onSuccess(BaseResponse baseResponse) {
        String simpleDataStr = baseResponse.getSimpleDataStr("msg");
        if (!TextUtils.isEmpty(simpleDataStr)) {
            ToastUtil.Short(simpleDataStr);
        }
    }

    public Map<String, String> getParams() {
        return this.a.event_param.param;
    }
}
