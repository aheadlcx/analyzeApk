package cn.v6.sixrooms.ui.fragment;

import android.view.View;
import android.view.View.OnClickListener;

final class s implements OnClickListener {
    final /* synthetic */ LoginFragment a;

    s(LoginFragment loginFragment) {
        this.a = loginFragment;
    }

    public final void onClick(View view) {
        if (LoginFragment.c(this.a) && LoginFragment.d(this.a)) {
            LoginFragment.e(this.a).login();
        }
    }
}
