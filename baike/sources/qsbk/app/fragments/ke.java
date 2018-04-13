package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.im.QiuyouquanNotificationCountManager;

class ke implements OnClickListener {
    final /* synthetic */ QiuyouquanNotificationListFragment a;

    ke(QiuyouquanNotificationListFragment qiuyouquanNotificationListFragment) {
        this.a = qiuyouquanNotificationListFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        QiuyouquanNotificationCountManager.getInstance(QsbkApp.currentUser.userId).clear();
        this.a.b.setLoadMoreEnable(false);
        this.a.f.a();
        this.a.f.notifyDataSetChanged();
        this.a.d();
    }
}
