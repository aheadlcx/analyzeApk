package cn.v6.sixrooms.ui.phone;

import android.text.Editable;
import android.text.TextWatcher;

final class ck implements TextWatcher {
    final /* synthetic */ RetrieveNameOrPasswordActivity a;

    ck(RetrieveNameOrPasswordActivity retrieveNameOrPasswordActivity) {
        this.a = retrieveNameOrPasswordActivity;
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.a.showCleanAutoCodeView(charSequence.length() > 0);
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void afterTextChanged(Editable editable) {
    }
}
