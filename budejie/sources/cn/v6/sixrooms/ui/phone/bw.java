package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnFocusChangeListener;

final class bw implements OnFocusChangeListener {
    final /* synthetic */ ResetPasswordActivity a;

    bw(ResetPasswordActivity resetPasswordActivity) {
        this.a = resetPasswordActivity;
    }

    public final void onFocusChange(View view, boolean z) {
        if (z) {
            this.a.showConfirmPwdView(this.a.h.getText().toString().trim().length() > 0);
        } else {
            this.a.hideCleanConfirmPwdView();
        }
    }
}
