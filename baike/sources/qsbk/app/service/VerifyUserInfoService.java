package qsbk.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import com.qiushibaike.statsdk.StatSDK;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.utils.LogUtil;

public class VerifyUserInfoService extends Service {
    private static int a = 0;
    private Handler b = new d(this);

    static /* synthetic */ int a() {
        int i = a;
        a = i + 1;
        return i;
    }

    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        if (QsbkApp.currentUser != null) {
            new e(this, "qbk-VerifyUserInfo1").start();
            return;
        }
        QsbkApp.mContext.startService(new Intent(QsbkApp.mContext, VersionCheckService.class));
        stopSelf();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }

    private void a(int i) {
        new f(this, "qbk-VerifyUserInfo2", i).start();
    }

    private void b(int i) {
        if (i <= 0) {
            StatSDK.onEvent(QsbkApp.mContext, "login_verify", "fail");
            LogUtil.d("verify user fail after 3 retry");
            return;
        }
        this.b.postDelayed(new g(this, i), 10000);
    }

    public void updateUserInfo(JSONObject jSONObject) {
        LogUtil.e("保存用户+++" + jSONObject.toString());
        new h(this, "qbk-VerifyUserInfo3", jSONObject).start();
    }

    private void b() {
        new i(this, "qbk-VerifyUserInfo4").start();
    }
}
