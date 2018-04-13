package qsbk.app.live.ui.family;

import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.live.model.FamilyMemberData;

class o extends Callback {
    final /* synthetic */ FamilyDetailActivity a;

    o(FamilyDetailActivity familyDetailActivity) {
        this.a = familyDetailActivity;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("familyid", this.a.w + "");
        hashMap.put(ParamKey.PAGE, "1");
        hashMap.put("count", "5");
        return hashMap;
    }

    public void onSuccess(BaseResponse baseResponse) {
        this.a.V.clear();
        Object<FamilyMemberData> listResponse = baseResponse.getListResponse("m", new p(this));
        if (listResponse != null) {
            this.a.V.addAll(listResponse);
        }
        for (FamilyMemberData familyMemberData : listResponse) {
            familyMemberData.a = baseResponse.parent.optJSONObject("t").optString(familyMemberData.t).replace("$", familyMemberData.a);
        }
        for (int size = this.a.V.size(); size < 4; size++) {
            this.a.V.add(new FamilyMemberData());
        }
        this.a.p.notifyDataSetChanged();
    }
}
