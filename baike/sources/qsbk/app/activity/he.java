package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.utils.LoginPermissionClickDelegate;

class he implements OnClickListener {
    final /* synthetic */ CircleTopicActivity a;

    he(CircleTopicActivity circleTopicActivity) {
        this.a = circleTopicActivity;
    }

    public void onClick(View view) {
        if (QsbkApp.currentUser != null) {
            this.a.clocked();
        } else {
            LoginPermissionClickDelegate.startLoginActivity(this.a);
        }
    }
}
