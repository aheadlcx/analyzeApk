package qsbk.app.im;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import qsbk.app.Constants;
import qsbk.app.activity.LaiseeSendActivity;

class bo extends BroadcastReceiver {
    final /* synthetic */ ConversationActivity a;

    bo(ConversationActivity conversationActivity) {
        this.a = conversationActivity;
    }

    public void onReceive(Context context, Intent intent) {
        if (Constants.ACTION_SEND_VOICE_LAISEE_TOO.equals(intent.getAction())) {
            LaiseeSendActivity.launchP2P(this.a, this.a.getToId(), 102);
        }
    }
}
