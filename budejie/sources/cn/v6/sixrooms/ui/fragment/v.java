package cn.v6.sixrooms.ui.fragment;

import android.text.Editable;
import android.text.TextWatcher;

final class v implements TextWatcher {
    final /* synthetic */ LoginFragment a;

    v(LoginFragment loginFragment) {
        this.a = loginFragment;
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        LoginFragment.e(this.a).resetPreLoginState();
        this.a.showUsernameCleanView(charSequence.length() > 0);
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void afterTextChanged(Editable editable) {
    }
}
