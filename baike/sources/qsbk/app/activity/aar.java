package qsbk.app.activity;

import android.text.Editable;
import android.text.TextWatcher;

class aar implements TextWatcher {
    final /* synthetic */ ReBindPhoneActivity a;

    aar(ReBindPhoneActivity reBindPhoneActivity) {
        this.a = reBindPhoneActivity;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        this.a.j();
    }
}
