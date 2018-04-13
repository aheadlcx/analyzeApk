package qsbk.app.live.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.core.utils.NetworkUtils;

protected class LiveBaseActivity$LiveReceiver extends BroadcastReceiver {
    final /* synthetic */ LiveBaseActivity a;
    private boolean b = NetworkUtils.getInstance().isNetworkAvailable();

    public LiveBaseActivity$LiveReceiver(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            LogUtils.e(LiveBaseActivity.a, "live receiver " + action);
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
                boolean isNetworkAvailable = NetworkUtils.getInstance().isNetworkAvailable();
                if (isNetworkAvailable != this.b) {
                    if (isNetworkAvailable && this.a.isOnResume) {
                        this.a.ag();
                    } else {
                        this.a.af();
                    }
                }
                this.b = isNetworkAvailable;
            }
        }
    }
}
