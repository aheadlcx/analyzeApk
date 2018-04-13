package qsbk.app.im;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class im extends BroadcastReceiver {
    final /* synthetic */ OfficialSubscribeListActivity a;

    im(OfficialSubscribeListActivity officialSubscribeListActivity) {
        this.a = officialSubscribeListActivity;
    }

    public void onReceive(Context context, Intent intent) {
        if (OfficialSubscribeListActivity.INIT_ADAPTER.equalsIgnoreCase(intent.getAction())) {
            this.a.e = true;
        }
    }
}
