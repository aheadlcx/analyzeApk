package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.QiushiNotificationListActivity;
import qsbk.app.im.QiuyouquanNotificationCountManager;

class jm implements OnClickListener {
    final /* synthetic */ QiuyouCircleFragment a;

    jm(QiuyouCircleFragment qiuyouCircleFragment) {
        this.a = qiuyouCircleFragment;
    }

    public void onClick(View view) {
        this.a.a(null);
        QiushiNotificationListActivity.gotoQiuyouquan(this.a.getActivity(), QiuyouquanNotificationCountManager.getInstance(QsbkApp.currentUser.userId).getUnreadCount());
    }
}
