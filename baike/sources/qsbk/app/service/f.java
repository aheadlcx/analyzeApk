package qsbk.app.service;

import android.os.Message;
import android.support.v4.app.NotificationCompat;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SharePreferenceUtils;

class f extends Thread {
    final /* synthetic */ int a;
    final /* synthetic */ VerifyUserInfoService b;

    f(VerifyUserInfoService verifyUserInfoService, String str, int i) {
        this.b = verifyUserInfoService;
        this.a = i;
        super(str);
    }

    public void run() {
        if (QsbkApp.currentUser != null) {
            Message message = null;
            Map hashMap = new HashMap();
            hashMap.put("id", QsbkApp.currentUser.userId);
            try {
                String post = HttpClient.getIntentce().post(Constants.VERIFY, hashMap);
                DebugUtil.debug("验证用户结果：" + post);
                JSONObject jSONObject = new JSONObject(post);
                int i = jSONObject.getInt(NotificationCompat.CATEGORY_ERROR);
                DebugUtil.debug("用户验证结果:" + i);
                if (i == 0) {
                    SharePreferenceUtils.setSharePreferencesValue("lastVerifyTime", System.currentTimeMillis() + "");
                    this.b.updateUserInfo(jSONObject);
                    this.b.b();
                    message = this.b.b.obtainMessage(1, Boolean.valueOf(false));
                    LogUtil.e("verify user ok");
                } else {
                    message = this.b.b.obtainMessage(0, Boolean.valueOf(false));
                    LogUtil.e("verify user fail");
                }
            } catch (Exception e) {
                this.b.b(this.a - 1);
            }
            if (message != null) {
                message.sendToTarget();
            }
        }
    }
}
