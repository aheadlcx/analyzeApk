package cn.v6.sixrooms.ui.fragment;

import android.view.View;
import android.view.View.OnFocusChangeListener;

final class x implements OnFocusChangeListener {
    final /* synthetic */ LoginFragment a;

    x(LoginFragment loginFragment) {
        this.a = loginFragment;
    }

    public final void onFocusChange(View view, boolean z) {
        if (z) {
            this.a.showUsernameCleanView(LoginFragment.g(this.a).getText().toString().trim().length() > 0);
        } else {
            this.a.hideUsernameCleanView();
        }
    }
}
