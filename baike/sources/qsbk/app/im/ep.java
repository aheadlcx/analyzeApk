package qsbk.app.im;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class ep implements OnClickListener {
    final /* synthetic */ GroupConversationActivity a;

    ep(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.finish();
    }
}
