package cn.v6.sixrooms.ui.phone;

import android.text.Editable;
import android.text.TextWatcher;

final class cd implements TextWatcher {
    final /* synthetic */ ResetPasswordActivity a;

    cd(ResetPasswordActivity resetPasswordActivity) {
        this.a = resetPasswordActivity;
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.a.showCleanPasswordView(charSequence.length() > 0);
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void afterTextChanged(Editable editable) {
    }
}