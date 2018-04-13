package qsbk.app.activity;

import android.text.Editable;
import android.text.TextWatcher;

class th implements TextWatcher {
    final /* synthetic */ MemberChooseActivity a;

    th(MemberChooseActivity memberChooseActivity) {
        this.a = memberChooseActivity;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        this.a.a(editable.toString());
    }
}
