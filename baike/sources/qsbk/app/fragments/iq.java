package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.im.QiushiNotificationCountManager;

class iq implements OnClickListener {
    final /* synthetic */ QiushiNotificationListFragment a;

    iq(QiushiNotificationListFragment qiushiNotificationListFragment) {
        this.a = qiushiNotificationListFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        QiushiNotificationCountManager.getInstance(QsbkApp.currentUser.userId).clear();
        this.a.b.setLoadMoreEnable(false);
        this.a.f.a();
        this.a.f.notifyDataSetChanged();
        this.a.d();
    }
}
