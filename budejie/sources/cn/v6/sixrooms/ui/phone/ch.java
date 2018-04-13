package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnFocusChangeListener;

final class ch implements OnFocusChangeListener {
    final /* synthetic */ ResetPasswordActivity a;

    ch(ResetPasswordActivity resetPasswordActivity) {
        this.a = resetPasswordActivity;
    }

    public final void onFocusChange(View view, boolean z) {
        if (!z || this.a.g.getText().toString().trim().length() <= 0) {
            this.a.i.hideCleanTag();
        } else {
            this.a.i.showCleanTag();
        }
    }
}
