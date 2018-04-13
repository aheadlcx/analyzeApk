package qsbk.app.live.ui.family;

import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.tencent.connect.common.Constants;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;

class at extends Callback {
    final /* synthetic */ FamilyMemberActivity a;

    at(FamilyMemberActivity familyMemberActivity) {
        this.a = familyMemberActivity;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(ParamKey.PAGE, this.a.g + "");
        hashMap.put("count", Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
        hashMap.put("familyid", this.a.l + "");
        return hashMap;
    }

    public void onSuccess(BaseResponse baseResponse) {
        this.a.a(baseResponse);
    }

    public void onFinished() {
        this.a.e.setRefreshing(false);
        this.a.h = false;
    }
}
