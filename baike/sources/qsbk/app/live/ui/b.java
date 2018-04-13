package qsbk.app.live.ui;

import android.text.TextUtils;
import com.facebook.common.util.UriUtil;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.live.R;
import qsbk.app.thirdparty.ThirdPartyConstants;

class b extends Callback {
    final /* synthetic */ String a;
    final /* synthetic */ LevelGiftPayActivity b;

    b(LevelGiftPayActivity levelGiftPayActivity, String str) {
        this.b = levelGiftPayActivity;
        this.a = str;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("boxid", String.valueOf(this.b.m));
        hashMap.put("paytype", this.a);
        return hashMap;
    }

    public void onSuccess(BaseResponse baseResponse) {
        if (TextUtils.isEmpty(baseResponse.getSimpleDataStr(UriUtil.LOCAL_RESOURCE_SCHEME))) {
            ToastUtil.Long(R.string.gift_pay_fail);
        } else if (this.a.equals("ali")) {
            this.b.a(0, baseResponse.getSimpleDataStr(UriUtil.LOCAL_RESOURCE_SCHEME), 0.0f, 0.0f, UrlConstants.LIVE_LEVEL_GIFT_PAY);
        } else if (this.a.equals(ThirdPartyConstants.THIRDPARTY_TYLE_WX)) {
            this.b.b(0, baseResponse.getSimpleDataStr(UriUtil.LOCAL_RESOURCE_SCHEME), 0.0f, 0.0f, UrlConstants.LIVE_LEVEL_GIFT_PAY);
        }
    }
}
