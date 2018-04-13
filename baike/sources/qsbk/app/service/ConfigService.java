package qsbk.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import qsbk.app.Constants;
import qsbk.app.utils.SharePreferenceUtils;

public class ConfigService extends Service {
    private static String a = "lastVerifyConfigTime";
    private static String b = "config_interval";
    private static String c = "config_version_update";
    private Handler d = new a(this);

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue(a);
        Object sharePreferencesValue2 = SharePreferenceUtils.getSharePreferencesValue(b);
        CharSequence sharePreferencesValue3 = SharePreferenceUtils.getSharePreferencesValue(c);
        if (TextUtils.isEmpty(sharePreferencesValue) || TextUtils.isEmpty(sharePreferencesValue2)) {
            c();
            return;
        }
        long longValue = Long.valueOf(sharePreferencesValue).longValue();
        if ((System.currentTimeMillis() / 1000) - longValue > Long.valueOf(sharePreferencesValue2).longValue() || !TextUtils.equals(sharePreferencesValue3, Constants.localVersionName)) {
            c();
        } else {
            stopSelf();
        }
    }

    private void c() {
        SharePreferenceUtils.setSharePreferencesValue(c, Constants.localVersionName);
        new b(this, "qbk-ConfigSrv").start();
    }
}
