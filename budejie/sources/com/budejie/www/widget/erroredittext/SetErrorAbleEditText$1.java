package com.budejie.www.widget.erroredittext;

import android.text.Editable;
import android.text.TextWatcher;

class SetErrorAbleEditText$1 implements TextWatcher {
    final /* synthetic */ SetErrorAbleEditText a;

    SetErrorAbleEditText$1(SetErrorAbleEditText setErrorAbleEditText) {
        this.a = setErrorAbleEditText;
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (SetErrorAbleEditText.a(this.a).a != null) {
            SetErrorAbleEditText.a(this.a).a(null, null);
        }
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
