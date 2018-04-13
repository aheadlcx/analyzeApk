package qsbk.app.live.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import qsbk.app.live.model.LiveMessage;

class dj extends BroadcastReceiver {
    final /* synthetic */ LivePullActivity a;

    dj(LivePullActivity livePullActivity) {
        this.a = livePullActivity;
    }

    public void onReceive(Context context, Intent intent) {
        if (this.a.ax != null && this.a.getRoomId() != 0) {
            this.a.sendLiveMessageAndRefreshUI(LiveMessage.createFirstChargeMessage(this.a.ax.getOriginId(), this.a.getRoomId()));
        }
    }
}
