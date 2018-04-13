package qsbk.app.activity;

import android.text.Editable;
import android.text.TextWatcher;

class dj implements TextWatcher {
    final /* synthetic */ BindPhoneActivity a;

    dj(BindPhoneActivity bindPhoneActivity) {
        this.a = bindPhoneActivity;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        this.a.q();
    }
}
