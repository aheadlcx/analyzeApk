package qsbk.app.im;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class go implements OnClickListener {
    final /* synthetic */ IMMessageListFragment a;

    go(IMMessageListFragment iMMessageListFragment) {
        this.a = iMMessageListFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.a.clearAllMsgs();
    }
}
