package qsbk.app.activity;

import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.relationship.Relationship;
import qsbk.app.utils.LoginPermissionClickDelegate;

class xa implements OnClickListener {
    final /* synthetic */ MyInfoActivity a;

    xa(MyInfoActivity myInfoActivity) {
        this.a = myInfoActivity;
    }

    public void onClick(View view) {
        if (this.a.bV) {
            LoginPermissionClickDelegate.startLoginActivity(this.a, BasePersonalCenterActivity.LOGIN_REQUEST_CODE);
            return;
        }
        String str = "";
        str = "";
        str = "";
        DialogInterface.OnClickListener xbVar;
        DialogInterface.OnClickListener xcVar;
        MyInfoActivity myInfoActivity;
        if (Relationship.FRIEND == this.a.bD || Relationship.FOLLOW_REPLIED == this.a.bD || Relationship.FOLLOW_UNREPLIED == this.a.bD) {
            xbVar = new xb(this, "正在取消粉,请稍后...");
            xcVar = new xc(this);
            myInfoActivity = this.a;
            myInfoActivity.a("取消粉TA后，不能发图片。是否取消粉?", "再想想", "取消粉", xcVar, xbVar);
        } else if (Relationship.BLACK == this.a.bD) {
            xbVar = new xd(this, "正在移出黑名单,请稍后...");
            xcVar = new xe(this);
            myInfoActivity = this.a;
            myInfoActivity.a("是否确定移出黑名单?", "再想想", "移出黑名单", xcVar, xbVar);
        } else if (Relationship.NO_REL != this.a.bD && Relationship.NO_REL_CHATED != this.a.bD) {
            this.a.a(0, 0);
        }
    }
}
