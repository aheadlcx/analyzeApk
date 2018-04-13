package qsbk.app.live.ui;

import android.text.TextUtils;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.core.utils.NetworkUtils;

class cz implements Runnable {
    final /* synthetic */ LiveBaseActivity a;

    cz(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void run() {
        LogUtils.d(LiveBaseActivity.a, "live chat room reconnecting... ");
        this.a.mHandler.removeCallbacks(this);
        this.a.c = false;
        if (NetworkUtils.getInstance().isNetworkAvailable()) {
            if (TextUtils.isEmpty(this.a.aF)) {
                this.a.aI = 0;
                this.a.M();
            } else if (!this.a.aD.isConnected()) {
                this.a.P();
            }
        } else if (!this.a.isFinishing() && this.a.isOnResume) {
            this.a.T();
        }
    }
}
