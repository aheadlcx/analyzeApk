package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.LoginPermissionClickDelegate;

class vr implements OnClickListener {
    final /* synthetic */ MyInfoActivity a;

    vr(MyInfoActivity myInfoActivity) {
        this.a = myInfoActivity;
    }

    public void onClick(View view) {
        if (this.a.bV) {
            LoginPermissionClickDelegate.startLoginActivity(this.a, BasePersonalCenterActivity.LOGIN_REQUEST_CODE);
        } else {
            this.a.popUpMyOverflow(view);
        }
    }
}
