package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.Manage;
import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class bx implements DialogListener {
    final /* synthetic */ ResetPasswordActivity a;

    bx(ResetPasswordActivity resetPasswordActivity) {
        this.a = resetPasswordActivity;
    }

    public final void positive(int i) {
        Manage.getInstance().finishActivities(RetrieveNameOrPasswordActivity.class, ResetPasswordActivity.class);
    }

    public final void negative(int i) {
    }
}
