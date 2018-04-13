package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.QiushiTopic;
import qsbk.app.utils.LoginPermissionClickDelegate;

class ww implements OnClickListener {
    final /* synthetic */ QiushiTopic a;
    final /* synthetic */ MyInfoActivity b;

    ww(MyInfoActivity myInfoActivity, QiushiTopic qiushiTopic) {
        this.b = myInfoActivity;
        this.a = qiushiTopic;
    }

    public void onClick(View view) {
        if (this.b.bV) {
            LoginPermissionClickDelegate.startLoginActivity(this.b, BasePersonalCenterActivity.LOGIN_REQUEST_CODE);
        } else {
            QiushiTopicActivity.Launch(view.getContext(), this.a);
        }
    }
}
