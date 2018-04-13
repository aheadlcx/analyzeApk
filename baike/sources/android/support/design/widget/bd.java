package android.support.design.widget;

import android.text.Editable;
import android.text.TextWatcher;

class bd implements TextWatcher {
    final /* synthetic */ TextInputLayout a;

    bd(TextInputLayout textInputLayout) {
        this.a = textInputLayout;
    }

    public void afterTextChanged(Editable editable) {
        this.a.a(!this.a.O);
        if (this.a.c) {
            this.a.a(editable.length());
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
