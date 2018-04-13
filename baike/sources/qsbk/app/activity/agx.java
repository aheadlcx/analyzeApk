package qsbk.app.activity;

import android.text.Editable;
import android.text.TextWatcher;

class agx implements TextWatcher {
    final /* synthetic */ WithdrawSetupActivity a;

    agx(WithdrawSetupActivity withdrawSetupActivity) {
        this.a = withdrawSetupActivity;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        this.a.g();
    }
}
