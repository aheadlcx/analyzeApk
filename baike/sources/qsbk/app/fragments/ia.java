package qsbk.app.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import qsbk.app.utils.DebugUtil;

class ia extends BroadcastReceiver {
    final /* synthetic */ QiushiListFragment a;

    ia(QiushiListFragment qiushiListFragment) {
        this.a = qiushiListFragment;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        DebugUtil.debug(QiushiListFragment.i, "onReceive action=" + action);
        if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            this.a.e();
        }
    }
}
