package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnClickListener;

final class cb implements OnClickListener {
    final /* synthetic */ ResetPasswordActivity a;

    cb(ResetPasswordActivity resetPasswordActivity) {
        this.a = resetPasswordActivity;
    }

    public final void onClick(View view) {
        this.a.j.hideSoftInputFromWindow(this.a.getCurrentFocus().getWindowToken(), 2);
        if (!this.a.a()) {
            this.a.k.resetPassword();
        }
    }
}
