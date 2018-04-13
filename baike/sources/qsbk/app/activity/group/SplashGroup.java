package qsbk.app.activity.group;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import qsbk.app.AppStat;
import qsbk.app.ConfigManager;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.im.IMTimer;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.SplashAdManager;
import qsbk.app.utils.SplashAdManager.SplashAdGroup;
import qsbk.app.utils.SplashAdManager.SplashAdItem;
import qsbk.app.utils.image.issue.Logger;

public class SplashGroup extends FragmentActivity {
    private SplashAdManager a;

    private class a implements Runnable {
        Intent a;
        final /* synthetic */ SplashGroup b;

        private a(SplashGroup splashGroup) {
            this.b = splashGroup;
            this.a = null;
        }

        public void run() {
            int correctTime = (int) (IMTimer.getInstance().getCorrectTime() / 1000);
            SplashAdGroup group = this.b.a.getGroup();
            if (group != null) {
                SplashAdItem validOneBy = group.getValidOneBy(new j(this));
                Logger.getInstance().debug(SplashGroup.class.getSimpleName(), "", "item " + validOneBy);
                if (validOneBy == null) {
                    SplashAdOtherActivity.launch(this.b);
                    this.b.c();
                    return;
                }
                Logger.getInstance().debug(SplashGroup.class.getSimpleName(), "", "item  loaded " + group.isLoaded(validOneBy));
                if (group.isLoaded(validOneBy) && correctTime > this.b.a.getLastShowTime(validOneBy.id) + validOneBy.interval) {
                    this.b.a.setLastShowTime(validOneBy.id, correctTime);
                    SplashAdActivity.launch(this.b, validOneBy);
                    this.b.c();
                    LogUtil.d("Select the QiubaiAD");
                    return;
                }
            }
            SplashAdOtherActivity.launch(this.b);
            this.b.c();
        }

        protected void finalize() throws Throwable {
            this.a = null;
            super.finalize();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AppStat.reportAppStart("activity");
        requestWindowFeature(1);
        if (ConfigManager.getInstance().isPromoteChannel()) {
            setContentView(R.layout.layout_splash);
        } else {
            setContentView(R.layout.layout_splash_nomarket);
        }
        a();
        this.a = SplashAdManager.instance();
        this.a.loadSplashAd();
    }

    private void a() {
        DeviceUtils.addShortcut(this);
        SharePreferenceUtils.setSharePreferencesValue("appStartTime", String.valueOf(System.currentTimeMillis()));
        LogUtil.d("delta splash on create end:" + QsbkApp.delta.getDelta());
        b();
    }

    private void b() {
        long delta = QsbkApp.getInstance().startTimeDelta.getDelta();
        Handler handler = new Handler();
        if (delta > 1000) {
            handler.postDelayed(new a(), 0);
        } else {
            handler.postDelayed(new a(), 1000 - delta);
        }
    }

    private void c() {
        finish();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }
}
