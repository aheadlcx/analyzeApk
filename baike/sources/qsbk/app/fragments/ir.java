package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class ir implements OnClickListener {
    final /* synthetic */ QiushiNotificationListFragment a;

    ir(QiushiNotificationListFragment qiushiNotificationListFragment) {
        this.a = qiushiNotificationListFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
