package qsbk.app.activity;

import android.text.Editable;
import android.text.TextWatcher;

class zk implements TextWatcher {
    final /* synthetic */ PayPasswordSetActivity a;

    zk(PayPasswordSetActivity payPasswordSetActivity) {
        this.a = payPasswordSetActivity;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        this.a.j();
    }
}
