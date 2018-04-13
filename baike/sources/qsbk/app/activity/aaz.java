package qsbk.app.activity;

import android.text.Editable;
import android.text.TextWatcher;

class aaz implements TextWatcher {
    final /* synthetic */ ResetPwdActivity a;

    aaz(ResetPwdActivity resetPwdActivity) {
        this.a = resetPwdActivity;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        this.a.j();
    }
}
