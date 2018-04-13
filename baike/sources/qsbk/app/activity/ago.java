package qsbk.app.activity;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.model.UserInfo;
import qsbk.app.nearby.api.NearbyEngine;
import qsbk.app.push.PushMessageManager;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SharePreferenceUtils;

class ago extends Thread {
    final /* synthetic */ WeiboLoginActivity a;

    ago(WeiboLoginActivity weiboLoginActivity, String str) {
        this.a = weiboLoginActivity;
        super(str);
    }

    public void run() {
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
            hashMap = new HashMap();
            DeviceUtils.addDeviceInfoToParam(hashMap);
            HttpClient.getIntentce().post(String.format(Constants.URL_USER_INFO, new Object[]{QsbkApp.currentUser.userId}), hashMap);
        } catch (QiushibaikeException e2) {
            e2.printStackTrace();
        }
        try {
            JSONObject optJSONObject = new JSONObject(HttpClient.getIntentce().get(NearbyEngine.instance().getCurrentUserInfoUrl())).optJSONObject("userdata");
            if (optJSONObject != null) {
                UserInfo.updateServerJsonNearby(QsbkApp.currentUser, optJSONObject);
                SharePreferenceUtils.setSharePreferencesValue("loginUser", QsbkApp.currentUser.toString());
            }
        } catch (QiushibaikeException e22) {
            e22.printStackTrace();
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
    }
}
