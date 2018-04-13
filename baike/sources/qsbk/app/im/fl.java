package qsbk.app.im;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import qsbk.app.activity.NoAvailableSpaceActivity;

class fl extends BroadcastReceiver {
    final /* synthetic */ IMChatBaseActivityEx a;

    fl(IMChatBaseActivityEx iMChatBaseActivityEx) {
        this.a = iMChatBaseActivityEx;
    }

    public void onReceive(Context context, Intent intent) {
        if (NoAvailableSpaceActivity.ACTION_NO_AVAILABLE_SPACE_EXIT.endsWith(intent.getAction())) {
            this.a.finish();
        }
    }
}
