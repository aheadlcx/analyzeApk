package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.im.QiushiNotificationCountManager;

class is implements OnClickListener {
    final /* synthetic */ QiushiNotificationListFragment a;

    is(QiushiNotificationListFragment qiushiNotificationListFragment) {
        this.a = qiushiNotificationListFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        QiushiNotificationCountManager.getInstance(QsbkApp.currentUser.userId).clearSmileMsg();
        this.a.b.setLoadMoreEnable(false);
        this.a.f.a();
        this.a.f.notifyDataSetChanged();
        this.a.d();
    }
}
