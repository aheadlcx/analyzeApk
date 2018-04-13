package qsbk.app.im;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class gn implements OnClickListener {
    final /* synthetic */ IMMessageListFragment a;

    gn(IMMessageListFragment iMMessageListFragment) {
        this.a = iMMessageListFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
