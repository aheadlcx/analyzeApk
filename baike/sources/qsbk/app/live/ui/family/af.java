package qsbk.app.live.ui.family;

import android.support.v4.app.NotificationCompat;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.core.model.User;
import qsbk.app.core.net.Callback;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.live.R;

class af extends Callback {
    final /* synthetic */ FamilyDetailActivity a;

    af(FamilyDetailActivity familyDetailActivity) {
        this.a = familyDetailActivity;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("family_id", this.a.w + "");
        hashMap.put("source", AppUtils.getInstance().getUserInfoProvider().getUserOrigin() + "");
        hashMap.put("source_id", AppUtils.getInstance().getUserInfoProvider().getUserId() + "");
        return hashMap;
    }

    public void onSuccess(JSONObject jSONObject) {
        if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR) == 0) {
            ToastUtil.Long(R.string.family_quit_success);
            User user = AppUtils.getInstance().getUserInfoProvider().getUser();
            if (user != null) {
                user.family_info = null;
                AppUtils.getInstance().getUserInfoProvider().setUser(user);
            }
            if (!this.a.isFinishing()) {
                this.a.finish();
                return;
            }
            return;
        }
        ToastUtil.Long(R.string.family_quit_fail);
    }
}
