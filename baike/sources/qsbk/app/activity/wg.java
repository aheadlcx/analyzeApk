package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.LoginPermissionClickDelegate;

class wg implements OnClickListener {
    final /* synthetic */ MyInfoActivity a;

    wg(MyInfoActivity myInfoActivity) {
        this.a = myInfoActivity;
    }

    public void onClick(View view) {
        if (this.a.bV) {
            LoginPermissionClickDelegate.startLoginActivity(this.a, BasePersonalCenterActivity.LOGIN_REQUEST_CODE);
            return;
        }
        Intent intent = new Intent(this.a, OthersQiuShiActivity.class);
        if (this.a.bF != null) {
            intent.putExtra("user_info", this.a.bF.toString());
        }
        intent.putExtra("uid", this.a.b);
        this.a.startActivity(intent);
    }
}
