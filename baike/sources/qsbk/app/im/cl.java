package qsbk.app.im;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class cl extends BroadcastReceiver {
    final /* synthetic */ ConversationActivity a;

    cl(ConversationActivity conversationActivity) {
        this.a = conversationActivity;
    }

    public void onReceive(Context context, Intent intent) {
        this.a.setRemarkForCumstomBar();
    }
}
