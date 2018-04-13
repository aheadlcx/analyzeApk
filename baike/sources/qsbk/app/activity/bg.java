package qsbk.app.activity;

import android.text.Editable;
import android.text.TextWatcher;

class bg implements TextWatcher {
    final /* synthetic */ ApplyForOwnerActivity a;

    bg(ApplyForOwnerActivity applyForOwnerActivity) {
        this.a = applyForOwnerActivity;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        int length = 250 - editable.length();
        this.a.c.setText(String.valueOf(length));
        this.a.c.setTextColor(length >= 0 ? -6908266 : -31098);
    }
}
