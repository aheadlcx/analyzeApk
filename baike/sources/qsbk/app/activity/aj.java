package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import java.io.File;
import qsbk.app.QsbkApp;
import qsbk.app.fragments.QiuyouCircleFragment;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.SplashAdManager;
import qsbk.app.utils.ToastAndDialog;

class aj implements OnClickListener {
    final /* synthetic */ ActionBarUserSettingNavi a;

    aj(ActionBarUserSettingNavi actionBarUserSettingNavi) {
        this.a = actionBarUserSettingNavi;
    }

    public void onClick(View view) {
        SharePreferenceUtils.remove(QiuyouCircleFragment.SIGN_TIME + (QsbkApp.currentUser != null ? QsbkApp.currentUser.userId : null));
        if (this.a.m != null && this.a.m.size() > 0) {
            synchronized (this.a.m) {
                ActionBarUserSettingNavi.b(this.a.m);
            }
            this.a.j.setSubTitle("0M");
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "缓存已清除").show();
        }
        File file = SplashAdManager.getFile();
        if (file.exists()) {
            file.delete();
        }
        SplashAdManager.clearTime();
        SharePreferenceUtils.remove("found_chicken_and_game");
        SharePreferenceUtils.remove("found_timestamp");
        SharePreferenceUtils.remove("found_interval");
    }
}
