package qsbk.app.live.ui.family;

import android.text.TextUtils;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.model.User;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.R;

class ab extends Callback {
    final /* synthetic */ FamilyDetailActivity a;

    ab(FamilyDetailActivity familyDetailActivity) {
        this.a = familyDetailActivity;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("source", AppUtils.getInstance().getUserInfoProvider().getUserOrigin() + "");
        hashMap.put("source_id", AppUtils.getInstance().getUserInfoProvider().getUserId() + "");
        hashMap.put("family_id", this.a.w + "");
        return hashMap;
    }

    public void onSuccess(BaseResponse baseResponse) {
        this.a.K = baseResponse.getSimpleDataStr("family_name");
        this.a.L = baseResponse.getSimpleDataStr("family_badge");
        this.a.I = baseResponse.getSimpleDataStr("family_notice");
        if (TextUtils.isEmpty(this.a.I)) {
            this.a.I = this.a.getString(R.string.family_notice_empty);
        }
        this.a.J = baseResponse.getSimpleDataStr("family_crest_url");
        this.a.M = (User) baseResponse.getResponse("creator", new ac(this));
        this.a.P = baseResponse.getSimpleDataInt(HTTP.IDENTITY_CODING);
        this.a.Q = baseResponse.getSimpleDataInt("button_type");
        this.a.N = baseResponse.getSimpleDataInt("member_count");
        this.a.O = baseResponse.getSimpleDataInt("rank");
        this.a.R = baseResponse.getSimpleDataInt("bugle_count");
        this.a.S = baseResponse.getSimpleDataInt("bugle_total");
        this.a.aa = baseResponse.getSimpleDataInt("checked_in");
        this.a.C = baseResponse.getSimpleDataInt("checkedin_count");
        this.a.E = baseResponse.getSimpleDataInt("stay_checkedin_count");
        this.a.F = baseResponse.getSimpleDataInt("fl");
        this.a.c();
    }

    public void onFailed(int i, String str) {
        if (i == -1913) {
            this.a.finish();
        }
    }

    public void onFinished() {
        super.onFinished();
        this.a.Y.setRefreshing(false);
    }
}
