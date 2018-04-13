package qsbk.app.live.adapter;

import android.support.v4.app.NotificationCompat;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.core.net.Callback;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.live.R;
import qsbk.app.live.model.FamilyMemberData;

class l extends Callback {
    final /* synthetic */ FamilyMemberData a;
    final /* synthetic */ FamilyAllMemberAdapter b;

    l(FamilyAllMemberAdapter familyAllMemberAdapter, FamilyMemberData familyMemberData) {
        this.b = familyAllMemberAdapter;
        this.a = familyMemberData;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("user_source", this.a.s + "");
        hashMap.put("user_id", this.a.u + "");
        hashMap.put("family_id", this.b.c + "");
        hashMap.put("source", AppUtils.getInstance().getUserInfoProvider().getUserOrigin() + "");
        hashMap.put("source_id", AppUtils.getInstance().getUserInfoProvider().getUserId() + "");
        return hashMap;
    }

    public void onSuccess(JSONObject jSONObject) {
        if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR) == 0) {
            ToastUtil.Long(R.string.family_expel_success);
            this.b.a.remove(this.a);
            this.b.notifyDataSetChanged();
            return;
        }
        ToastUtil.Long(R.string.family_expel_fail);
    }
}
