package cn.v6.sixrooms.ui.fragment;

import android.text.Editable;
import android.text.TextWatcher;

final class ad implements TextWatcher {
    final /* synthetic */ MsgVerifyFragment a;

    ad(MsgVerifyFragment msgVerifyFragment) {
        this.a = msgVerifyFragment;
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (charSequence.length() > 0) {
            MsgVerifyFragment.i(this.a).setVisibility(0);
            MsgVerifyFragment.j(this.a).setEnabled(true);
            return;
        }
        MsgVerifyFragment.i(this.a).setVisibility(8);
        MsgVerifyFragment.j(this.a).setEnabled(false);
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void afterTextChanged(Editable editable) {
    }
}
