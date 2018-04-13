package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnClickListener;

final class cg implements OnClickListener {
    final /* synthetic */ ResetPasswordActivity a;

    cg(ResetPasswordActivity resetPasswordActivity) {
        this.a = resetPasswordActivity;
    }

    public final void onClick(View view) {
        this.a.clearConfirmPwd();
        this.a.hideCleanConfirmPwdView();
    }
}
