package qsbk.app.activity;

import android.text.Editable;
import android.text.TextWatcher;

class kq implements TextWatcher {
    final /* synthetic */ FillGroupInfoActivity a;

    kq(FillGroupInfoActivity fillGroupInfoActivity) {
        this.a = fillGroupInfoActivity;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        this.a.s = editable.toString().trim().length() > 0;
        this.a.k();
    }
}
