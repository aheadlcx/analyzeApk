package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class kh implements OnClickListener {
    final /* synthetic */ QiuyouquanNotificationListFragment a;

    kh(QiuyouquanNotificationListFragment qiuyouquanNotificationListFragment) {
        this.a = qiuyouquanNotificationListFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
