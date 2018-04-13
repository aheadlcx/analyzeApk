package qsbk.app.live.ui.family;

import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.live.model.FamilyAnchorData;

class q extends Callback {
    final /* synthetic */ FamilyDetailActivity a;

    q(FamilyDetailActivity familyDetailActivity) {
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
        this.a.W.clear();
        Collection<FamilyAnchorData> listResponse = baseResponse.getListResponse("msg", new r(this));
        if (listResponse != null) {
            for (FamilyAnchorData familyAnchorData : listResponse) {
                familyAnchorData.a = baseResponse.parent.optJSONObject("t").optString(familyAnchorData.t).replace("$", familyAnchorData.a);
            }
            this.a.W.addAll(listResponse);
        }
        for (int size = this.a.W.size(); size < 4; size++) {
            this.a.W.add(new FamilyAnchorData());
        }
        this.a.q.notifyDataSetChanged();
    }
}
