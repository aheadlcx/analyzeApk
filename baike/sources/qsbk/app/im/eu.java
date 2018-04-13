package qsbk.app.im;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import qsbk.app.Constants;
import qsbk.app.activity.LaiseeSendActivity;
import qsbk.app.utils.ToastAndDialog;

class eu extends BroadcastReceiver {
    final /* synthetic */ GroupConversationActivity a;

    eu(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public void onReceive(Context context, Intent intent) {
        if (!Constants.ACTION_SEND_VOICE_LAISEE_TOO.equals(intent.getAction())) {
            return;
        }
        if (this.a.au != null) {
            LaiseeSendActivity.launchTribe(this.a, this.a.getToId(), this.a.au.memberNum, 12);
        } else {
            ToastAndDialog.makeText(this.a, "正在加载群信息，请稍后再试").show();
        }
    }
}
