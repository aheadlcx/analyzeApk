package qsbk.app.activity;

import android.text.Editable;
import android.text.TextWatcher;

class dl implements TextWatcher {
    final /* synthetic */ BindPhoneActivity a;

    dl(BindPhoneActivity bindPhoneActivity) {
        this.a = bindPhoneActivity;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.a.a.isOnTick() || !this.a.a(this.a.b.getText().toString().trim())) {
            this.a.a.setEnabled(false);
        } else {
            this.a.a.setEnabled(true);
        }
    }

    public void afterTextChanged(Editable editable) {
    }
}
