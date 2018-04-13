package cn.v6.sixrooms.widgets.phone;

import android.text.Editable;
import android.text.TextWatcher;

final class y implements TextWatcher {
    final /* synthetic */ MsgVerifyDialog a;

    y(MsgVerifyDialog msgVerifyDialog) {
        this.a = msgVerifyDialog;
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void afterTextChanged(Editable editable) {
        if (editable.length() == 6) {
            MsgVerifyDialog.d(this.a).setClickable(true);
            MsgVerifyDialog.d(this.a).setEnabled(true);
            return;
        }
        MsgVerifyDialog.d(this.a).setClickable(false);
        MsgVerifyDialog.d(this.a).setEnabled(false);
    }
}
