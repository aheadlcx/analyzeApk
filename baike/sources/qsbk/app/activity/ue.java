package qsbk.app.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

class ue implements TextWatcher {
    final /* synthetic */ ModifyPwdActivity a;

    ue(ModifyPwdActivity modifyPwdActivity) {
        this.a = modifyPwdActivity;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        this.a.g();
        if (TextUtils.isEmpty(this.a.g) || TextUtils.isEmpty(this.a.h) || TextUtils.isEmpty(this.a.i)) {
            this.a.d.setEnabled(false);
        } else {
            this.a.d.setEnabled(true);
        }
    }
}
