package cn.v6.sixrooms.ui.phone;

import android.text.Editable;
import android.text.TextWatcher;

final class bp implements TextWatcher {
    final /* synthetic */ RegisterActivity a;

    bp(RegisterActivity registerActivity) {
        this.a = registerActivity;
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.a.a(charSequence);
    }

    public final void afterTextChanged(Editable editable) {
    }
}
