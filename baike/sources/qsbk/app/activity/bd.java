package qsbk.app.activity;

import android.text.Editable;
import android.text.TextWatcher;

class bd implements TextWatcher {
    final /* synthetic */ ApplyForGroupActivity a;

    bd(ApplyForGroupActivity applyForGroupActivity) {
        this.a = applyForGroupActivity;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        int length = 30 - editable.length();
        this.a.e.setText(String.valueOf(length));
        this.a.e.setTextColor(length >= 0 ? -6908266 : -31098);
    }
}
