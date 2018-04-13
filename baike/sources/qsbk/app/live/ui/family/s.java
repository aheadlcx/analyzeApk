package qsbk.app.live.ui.family;

import com.baidu.mobads.interfaces.IXAdRequestInfo;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;

class s extends Callback {
    final /* synthetic */ FamilyDetailActivity a;

    s(FamilyDetailActivity familyDetailActivity) {
        this.a = familyDetailActivity;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("familyid", this.a.w + "");
        return hashMap;
    }

    public void onSuccess(BaseResponse baseResponse) {
        this.a.X.clear();
        Collection listResponse = baseResponse.getListResponse(IXAdRequestInfo.GPS, new t(this));
        if (listResponse != null) {
            this.a.X.addAll(listResponse);
        }
        this.a.r.notifyDataSetChanged();
    }
}
