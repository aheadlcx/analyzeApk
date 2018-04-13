package qsbk.app.activity;

import android.text.Editable;
import android.text.TextWatcher;

class zq implements TextWatcher {
    final /* synthetic */ PayPwdResetActivity a;

    zq(PayPwdResetActivity payPwdResetActivity) {
        this.a = payPwdResetActivity;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        this.a.j();
    }
}
