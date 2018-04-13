package qsbk.app.activity;

import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.push.PushMessageManager;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.HttpClient;

class le extends Thread {
    final /* synthetic */ FillUserDataActivity a;

    le(FillUserDataActivity fillUserDataActivity, String str) {
        this.a = fillUserDataActivity;
        super(str);
    }

    public void run() {
        try {
            Map hashMap = new HashMap();
            hashMap.put("token", PushMessageManager.getDevicePushToken());
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
    }
}
