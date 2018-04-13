package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.QiushiNotificationListActivity;
import qsbk.app.im.QiushiNotificationCountManager;

class ii implements OnClickListener {
    final /* synthetic */ QiushiListFragment a;

    ii(QiushiListFragment qiushiListFragment) {
        this.a = qiushiListFragment;
    }

    public void onClick(View view) {
        this.a.a(null);
        QiushiNotificationListActivity.gotoQiushi(this.a.getActivity(), QiushiNotificationCountManager.getInstance(QsbkApp.currentUser.userId).getUnreadCount());
    }
}
