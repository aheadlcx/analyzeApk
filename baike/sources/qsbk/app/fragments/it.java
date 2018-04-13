package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class it implements OnClickListener {
    final /* synthetic */ QiushiNotificationListFragment a;

    it(QiushiNotificationListFragment qiushiNotificationListFragment) {
        this.a = qiushiNotificationListFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
