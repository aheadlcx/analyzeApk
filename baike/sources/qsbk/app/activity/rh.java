package qsbk.app.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.fragments.SubscribeFragment;
import qsbk.app.utils.TipsManager;

class rh extends BroadcastReceiver {
    final /* synthetic */ MainActivity a;

    rh(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void onReceive(Context context, Intent intent) {
        ConfigInfoUtil.instance().deleteConfig();
        ConfigInfoUtil.instance().updateConfigInfo(true);
        TipsManager.checkMyInfo(this.a);
        if (SubscribeFragment.hasUserLoginGuideCard) {
            MainActivity.e(this.a).sendBroadcast(new Intent(SubscribeFragment.ACTION_WELCOME_CARD_LOGIN));
        }
    }
}
