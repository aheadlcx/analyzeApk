package qsbk.app.activity;

import com.tencent.open.SocialConstants;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.fragments.QiuyouCircleFragment;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.SharePreferenceUtils;

class en implements SimpleCallBack {
    final /* synthetic */ CheckInActivity a;

    en(CheckInActivity checkInActivity) {
        this.a = checkInActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            int i = jSONObject.getInt("days");
            int i2 = jSONObject.getInt("score");
            int optInt = jSONObject.optInt("nick_status");
            QsbkApp.currentUser.nickStatus = optInt;
            QsbkApp.getInstance().setCurrentUserToLocal();
            SharePreferenceUtils.setSharePreferencesValue(QiuyouCircleFragment.SIGN_TIME + QsbkApp.currentUser.userId, QiuyouCircleFragment.calcSignTime(jSONObject.optString("today")));
            SharePreferenceUtils.setSharePreferencesValue(QiuyouCircleFragment.SIGN_DAYS + QsbkApp.currentUser.userId, i);
            SharePreferenceUtils.setSharePreferencesValue(QiuyouCircleFragment.NICK_STATUS + QsbkApp.currentUser.userId, optInt);
            this.a.g.add(this.a.b.format(new Date(System.currentTimeMillis())));
            if (jSONObject.isNull("new_year_sign")) {
                this.a.a(i, i2);
            } else {
                JSONObject optJSONObject = jSONObject.optJSONObject("new_year_sign");
                e eVar = new e(this.a);
                eVar.link = optJSONObject.optString("link");
                eVar.content = optJSONObject.optString("content");
                eVar.desc = optJSONObject.optString(SocialConstants.PARAM_APP_DESC);
                this.a.a(i, i2, eVar);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (this.a.G != null) {
            this.a.G.onUpdate();
        }
    }

    public void onFailure(int i, String str) {
    }
}
