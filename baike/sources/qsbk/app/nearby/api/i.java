package qsbk.app.nearby.api;

import android.text.TextUtils;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.activity.ReAuthActivity;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.UserInfo;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;

class i implements SimpleCallBack {
    final /* synthetic */ PersonalListener a;

    i(PersonalListener personalListener) {
        this.a = personalListener;
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            boolean equals;
            if (QsbkApp.currentUser != null) {
                equals = TextUtils.equals(QsbkApp.currentUser.userId, this.a.j);
            } else {
                equals = false;
            }
            UserInfo userInfo = equals ? QsbkApp.currentUser : new UserInfo();
            UserInfo.updateServerJsonNearby(userInfo, jSONObject.optJSONObject("userdata"));
            if (equals) {
                SharePreferenceUtils.setSharePreferencesValue("loginUser", QsbkApp.currentUser.toString());
            }
            this.a.b.getPersonalInfoSucc(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            if (!this.a.l) {
                ToastAndDialog.makeNegativeToast(this.a.k, HttpClient.getLocalErrorStr(), Integer.valueOf(1)).show();
                this.a.l = true;
            }
            this.a.b.getPersonalInfoFailed();
        }
    }

    public void onFailure(int i, String str) {
        this.a.b.getPersonalInfoFailed();
        if (i == -120) {
            this.a.a();
        } else if (!this.a.l) {
            this.a.l = true;
            if (i != SimpleHttpTask.ERROR_DOUBLE_LOGIN.intValue()) {
                ToastAndDialog.makeNegativeToast(this.a.k, str, Integer.valueOf(1)).show();
            } else if (QsbkApp.currentUser != null) {
                QsbkApp.mContext.startActivity(ReAuthActivity.getIntent(QsbkApp.mContext));
            }
        }
    }
}
