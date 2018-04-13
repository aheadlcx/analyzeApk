package qsbk.app.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import qsbk.app.utils.SharePreferenceUtils;

class sj extends BroadcastReceiver {
    final /* synthetic */ MainActivity a;

    sj(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equalsIgnoreCase(MainActivity.ACTION_FOUND_HIDE_TIPS)) {
            SharePreferenceUtils.setSharePreferencesValue(MainActivity.FOUND_HAVE_REFRESH, false);
            if (this.a.a != null) {
                this.a.hideSmallTips(MainActivity.TAB_NEARBY_ID);
            }
        }
    }
}
