package qsbk.app.activity;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.core.AsyncTask;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.model.UserInfo;
import qsbk.app.push.PushMessageManager;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.RemarkManager;
import qsbk.app.utils.SharePreferenceUtils;

final class v extends AsyncTask {
    v() {
    }

    protected Object a(Object[] objArr) {
        try {
            Map hashMap = new HashMap();
            hashMap.put("token", PushMessageManager.getDevicePushToken());
            LogUtil.d("push_token:" + PushMessageManager.getDevicePushToken());
            hashMap.put("action", QsbkDatabase.LOGIN);
            HttpClient.getIntentce().post(Constants.PUSH_DOMAINS, hashMap);
        } catch (QiushibaikeException e) {
            e.printStackTrace();
        }
        try {
            if (QsbkApp.currentUser != null) {
                hashMap = new HashMap();
                DeviceUtils.addDeviceInfoToParam(hashMap);
                LogUtil.d("on loin success response:" + HttpClient.getIntentce().post(String.format(Constants.URL_USER_INFO, new Object[]{QsbkApp.currentUser.userId}), hashMap));
                try {
                    if (QsbkApp.currentUser != null) {
                        JSONObject optJSONObject = new JSONObject(HttpClient.getIntentce().get(String.format(Constants.PERSONAL_INFO_URL, new Object[]{QsbkApp.currentUser.userId}))).optJSONObject("userdata");
                        if (!(optJSONObject == null || QsbkApp.currentUser == null)) {
                            UserInfo.updateServerJsonNearby(QsbkApp.currentUser, optJSONObject);
                            SharePreferenceUtils.setSharePreferencesValue("loginUser", QsbkApp.currentUser.toString());
                            RemarkManager remarkManager = RemarkManager.getRemarkManager();
                            if (remarkManager != null) {
                                remarkManager.init();
                            }
                        }
                    }
                } catch (QiushibaikeException e2) {
                    e2.printStackTrace();
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
            }
        } catch (QiushibaikeException e22) {
            LogUtil.d("statusCode:" + e22.getStatusCode());
            e22.printStackTrace();
        }
        return null;
    }
}
