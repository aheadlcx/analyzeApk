package qsbk.app.im;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class fj extends BroadcastReceiver {
    final /* synthetic */ IMChatBaseActivity a;

    fj(IMChatBaseActivity iMChatBaseActivity) {
        this.a = iMChatBaseActivity;
    }

    public void onReceive(Context context, Intent intent) {
        if (!"android.intent.action.HEADSET_PLUG".equals(intent.getAction()) || this.a.E == null || !intent.hasExtra("state")) {
            return;
        }
        if (intent.getIntExtra("state", 0) == 1) {
            this.a.E.setSpeakerphoneOn(false);
        } else {
            this.a.E.setSpeakerphoneOn(true);
        }
    }
}
