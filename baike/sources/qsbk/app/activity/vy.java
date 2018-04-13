package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.LoginPermissionClickDelegate;

class vy implements OnClickListener {
    final /* synthetic */ MyInfoActivity a;

    vy(MyInfoActivity myInfoActivity) {
        this.a = myInfoActivity;
    }

    public void onClick(View view) {
        if (this.a.bV) {
            LoginPermissionClickDelegate.startLoginActivity(this.a, BasePersonalCenterActivity.LOGIN_REQUEST_CODE);
            return;
        }
        Intent intent = new Intent(this.a, MyQiuYouDynamicActivity.class);
        intent.putExtra("uid", this.a.b);
        this.a.startActivity(intent);
    }
}
