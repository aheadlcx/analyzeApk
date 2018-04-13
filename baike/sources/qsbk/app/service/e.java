package qsbk.app.service;

import android.text.TextUtils;
import qsbk.app.utils.SharePreferenceUtils;

class e extends Thread {
    final /* synthetic */ VerifyUserInfoService a;

    e(VerifyUserInfoService verifyUserInfoService, String str) {
        this.a = verifyUserInfoService;
        super(str);
    }

    public void run() {
        Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue("lastVerifyTime");
        if (TextUtils.isEmpty(sharePreferencesValue)) {
            this.a.a(5);
            return;
        }
        if (System.currentTimeMillis() - Long.valueOf(sharePreferencesValue).longValue() > 259200000) {
            this.a.a(5);
        } else {
            this.a.b.obtainMessage(1, Boolean.valueOf(false)).sendToTarget();
        }
    }
}
