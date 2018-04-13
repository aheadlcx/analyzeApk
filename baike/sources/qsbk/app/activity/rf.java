package qsbk.app.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import qsbk.app.QsbkApp;

class rf extends BroadcastReceiver {
    final /* synthetic */ MainActivity a;

    rf(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void onReceive(Context context, Intent intent) {
        if (!intent.getAction().equalsIgnoreCase(MainActivity.ACTION_NEW_FANS)) {
            return;
        }
        if (QsbkApp.currentUser != null && MainActivity.d(this.a) != null) {
            this.a.getMainUIHandler().postDelayed(new rg(this), 400);
        } else if (QsbkApp.currentUser == null) {
            this.a.hideTips(MainActivity.TAB_MESSAGE_ID);
        }
    }
}
