package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.im.QiuyouquanNotificationCountManager;

class ki implements OnClickListener {
    final /* synthetic */ QiuyouquanNotificationListFragment a;

    ki(QiuyouquanNotificationListFragment qiuyouquanNotificationListFragment) {
        this.a = qiuyouquanNotificationListFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        QiuyouquanNotificationCountManager.getInstance(QsbkApp.currentUser.userId).clearAtMsg();
        this.a.b.setLoadMoreEnable(false);
        this.a.f.a();
        this.a.f.notifyDataSetChanged();
        this.a.d();
    }
}
