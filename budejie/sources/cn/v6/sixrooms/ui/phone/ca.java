package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnClickListener;

final class ca implements OnClickListener {
    final /* synthetic */ ResetPasswordActivity a;

    ca(ResetPasswordActivity resetPasswordActivity) {
        this.a = resetPasswordActivity;
    }

    public final void onClick(View view) {
        this.a.getSlidingMenu().a();
    }
}
