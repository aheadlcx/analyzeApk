package qsbk.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import qsbk.app.Constants;
import qsbk.app.live.ui.LiveBaseActivity;
import qsbk.app.utils.SharePreferenceUtils;

public class VersionCheckService extends Service {
    public static final String CANCLE_UDATE_TIME = "cancleUpdateTime";
    public static final String CANCLE_UDATE_VERSION = "cancleUpdateVersion";
    private static boolean a = false;
    private Handler b = new j(this);

    private static boolean b() {
        long j;
        Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue(CANCLE_UDATE_TIME);
        if (TextUtils.isEmpty(sharePreferencesValue)) {
            j = 0;
        } else {
            j = Long.valueOf(sharePreferencesValue).longValue();
        }
        if (j > 0) {
            if (j + LiveBaseActivity.INNER >= System.currentTimeMillis()) {
                return false;
            }
            SharePreferenceUtils.setSharePreferencesValue(CANCLE_UDATE_TIME, String.valueOf(System.currentTimeMillis()));
            return true;
        } else if (j == -1) {
            if (Constants.serverVersion <= SharePreferenceUtils.getSharePreferencesIntValue(CANCLE_UDATE_VERSION)) {
                return false;
            }
            return true;
        } else if (Constants.serverVersion <= Constants.localVersion) {
            return false;
        } else {
            return true;
        }
    }

    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        if (!a) {
            a = true;
            new k(this, "qbk-VerCheck1").start();
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }
}
