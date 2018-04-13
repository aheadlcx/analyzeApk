package qsbk.app.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import qsbk.app.utils.LogUtil;

class ry extends BroadcastReceiver {
    final /* synthetic */ MainActivity a;

    ry(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equalsIgnoreCase(MainActivity.ACTION_FOUND_STATUS_CHANGE)) {
            LogUtil.d("update status change");
            if (MainActivity$MainFoundStatusManager.getStatus()) {
                this.a.setSmallTips(MainActivity.TAB_NEARBY_ID);
            } else if (this.a.a != null) {
                this.a.hideSmallTips(MainActivity.TAB_NEARBY_ID);
            }
        }
    }
}
