package cn.v6.sixrooms.ui.fragment;

import android.text.Editable;
import android.text.TextWatcher;
import cn.v6.sixrooms.utils.UsernameUtils;

final class w implements TextWatcher {
    final /* synthetic */ LoginFragment a;

    w(LoginFragment loginFragment) {
        this.a = loginFragment;
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (!charSequence.toString().equals(UsernameUtils.stringFilter(this.a.getPassword()))) {
            LoginFragment.f(this.a).setText(UsernameUtils.stringFilter(this.a.getPassword()));
            LoginFragment.f(this.a).setSelection(LoginFragment.f(this.a).length());
        }
        this.a.setPasswordViewTag(charSequence.length() == 0);
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void afterTextChanged(Editable editable) {
    }
}
