package qsbk.app.service;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.exception.CrashHandler;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.SharePreferenceUtils;

class i extends Thread {
    final /* synthetic */ VerifyUserInfoService a;

    i(VerifyUserInfoService verifyUserInfoService, String str) {
        this.a = verifyUserInfoService;
        super(str);
    }

    public void run() {
        try {
            JSONObject jSONObject = new JSONObject(HttpClient.getIntentce().get(Constants.USERINFO));
            if (((Integer) jSONObject.get(NotificationCompat.CATEGORY_ERROR)).intValue() == 0) {
                Object string;
                JSONObject jSONObject2 = jSONObject.getJSONObject("user");
                if (!jSONObject2.isNull("email")) {
                    string = jSONObject2.getString("email");
                    if (!(TextUtils.isEmpty(string) || "null".equals(string))) {
                        QsbkApp.currentUser.email = string;
                    }
                }
                jSONObject2 = jSONObject.getJSONObject("userdata");
                if (!jSONObject2.isNull(ThirdPartyConstants.THIRDPARTY_TYLE_SINA)) {
                    Object string2 = jSONObject2.getString(ThirdPartyConstants.THIRDPARTY_TYLE_SINA);
                    if (!(TextUtils.isEmpty(string2) || "null".equals(string2))) {
                        QsbkApp.currentUser.wb = string2;
                    }
                }
                if (!jSONObject2.isNull(ThirdPartyConstants.THIRDPARTY_TYLE_QQ)) {
                    string = jSONObject2.getString(ThirdPartyConstants.THIRDPARTY_TYLE_QQ);
                    if (!(TextUtils.isEmpty(string) || "null".equals(string))) {
                        QsbkApp.currentUser.qq = string;
                    }
                }
                SharePreferenceUtils.setSharePreferencesValue("loginUser", QsbkApp.currentUser.toString());
            }
        } catch (JSONException e) {
        } catch (QiushibaikeException e2) {
            e2.printStackTrace();
        } catch (Throwable e3) {
            if (VerifyUserInfoService.a() < 3) {
                CrashHandler.getInstance().reportGuessException(Thread.currentThread(), e3, 2, String.format("User info from net: %s", new Object[]{null}));
            }
        }
    }
}
