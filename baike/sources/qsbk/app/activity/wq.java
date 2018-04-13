package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.LoginPermissionClickDelegate;

class wq implements OnClickListener {
    final /* synthetic */ CircleTopic a;
    final /* synthetic */ MyInfoActivity b;

    wq(MyInfoActivity myInfoActivity, CircleTopic circleTopic) {
        this.b = myInfoActivity;
        this.a = circleTopic;
    }

    public void onClick(View view) {
        if (this.b.bV) {
            LoginPermissionClickDelegate.startLoginActivity(this.b, BasePersonalCenterActivity.LOGIN_REQUEST_CODE);
        } else {
            CircleTopicActivity.launch(this.b, this.a.id);
        }
    }
}
