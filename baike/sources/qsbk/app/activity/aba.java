package qsbk.app.activity;

import android.text.Editable;
import android.text.TextWatcher;
import qsbk.app.QsbkApp;

class aba implements TextWatcher {
    final /* synthetic */ ResetPwdActivity a;

    aba(ResetPwdActivity resetPwdActivity) {
        this.a = resetPwdActivity;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (QsbkApp.currentUser == null || this.a.a(this.a.d.getText().toString().trim())) {
            this.a.c.setEnabled(true);
        } else {
            this.a.c.setEnabled(false);
        }
    }

    public void afterTextChanged(Editable editable) {
    }
}
