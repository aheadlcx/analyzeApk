package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.GroupInfo;
import qsbk.app.utils.LoginPermissionClickDelegate;

class wf implements OnClickListener {
    final /* synthetic */ GroupInfo a;
    final /* synthetic */ MyInfoActivity b;

    wf(MyInfoActivity myInfoActivity, GroupInfo groupInfo) {
        this.b = myInfoActivity;
        this.a = groupInfo;
    }

    public void onClick(View view) {
        if (this.b.bV) {
            LoginPermissionClickDelegate.startLoginActivity(this.b, BasePersonalCenterActivity.LOGIN_REQUEST_CODE);
        } else {
            GroupInfoActivity.launch(this.b, this.a);
        }
    }
}
