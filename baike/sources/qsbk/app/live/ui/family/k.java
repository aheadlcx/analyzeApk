package qsbk.app.live.ui.family;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.activity.pay.PayPWDUniversalActivity;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.AppUtils;

class k extends Callback {
    final /* synthetic */ FamilyCreateActivity a;

    k(FamilyCreateActivity familyCreateActivity) {
        this.a = familyCreateActivity;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("source", AppUtils.getInstance().getUserInfoProvider().getUserOrigin() + "");
        hashMap.put("source_id", AppUtils.getInstance().getUserInfoProvider().getUserId() + "");
        if (this.a.n == 1) {
            hashMap.put("family_id", this.a.o + "");
        }
        return hashMap;
    }

    public void onSuccess(BaseResponse baseResponse) {
        Object simpleDataStr = baseResponse.getSimpleDataStr("token");
        String simpleDataStr2 = baseResponse.getSimpleDataStr(PayPWDUniversalActivity.KEY);
        if (!TextUtils.isEmpty(simpleDataStr)) {
            this.a.uploadAvatarToQiniu(this.a.a, simpleDataStr2, simpleDataStr);
        }
    }

    public void onFinished() {
        this.a.hideSavingDialog();
    }

    public void onFailed(int i, String str) {
        this.a.showSnackbar(str);
    }
}
