package qsbk.app.im;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class de extends BroadcastReceiver {
    final /* synthetic */ GroupConversationActivity a;

    de(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public void onReceive(Context context, Intent intent) {
        if (this.a.g != null) {
            this.a.g.notifyDataSetChanged();
        }
    }
}
