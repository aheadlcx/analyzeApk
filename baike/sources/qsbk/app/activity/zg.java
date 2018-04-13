package qsbk.app.activity;

import android.text.Editable;
import android.text.TextWatcher;

class zg implements TextWatcher {
    final /* synthetic */ PayPasswordModifyActivity a;

    zg(PayPasswordModifyActivity payPasswordModifyActivity) {
        this.a = payPasswordModifyActivity;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        this.a.i();
    }
}
