package qsbk.app.fragments;

import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.SharePreferenceUtils;

class jz implements SimpleCallBack {
    final /* synthetic */ QiuyouCircleFragment a;

    jz(QiuyouCircleFragment qiuyouCircleFragment) {
        this.a = qiuyouCircleFragment;
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            this.a.y = false;
            if (QsbkApp.currentUser != null && this.a.getActivity() != null && !this.a.getActivity().isFinishing() && !this.a.isDetached()) {
                int i = jSONObject.getInt("days");
                boolean z = jSONObject.getBoolean("is_sign");
                int optInt = jSONObject.optInt("nick_status");
                QsbkApp.currentUser.nickStatus = optInt;
                QsbkApp.getInstance().setCurrentUserToLocal();
                long calcSignTime = QiuyouCircleFragment.calcSignTime(jSONObject.optString("today"));
                if (z) {
                    this.a.c(i);
                    SharePreferenceUtils.setSharePreferencesValue(QiuyouCircleFragment.SIGN_TIME + QsbkApp.currentUser.userId, calcSignTime);
                    SharePreferenceUtils.setSharePreferencesValue(QiuyouCircleFragment.SIGN_DAYS + QsbkApp.currentUser.userId, i);
                    SharePreferenceUtils.setSharePreferencesValue(QiuyouCircleFragment.NICK_STATUS + QsbkApp.currentUser.userId, optInt);
                    return;
                }
                this.a.h();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            this.a.h();
        }
    }

    public void onFailure(int i, String str) {
        this.a.y = false;
        if (this.a.getActivity() != null && !this.a.getActivity().isFinishing() && !this.a.isDetached()) {
            this.a.h();
        }
    }
}
