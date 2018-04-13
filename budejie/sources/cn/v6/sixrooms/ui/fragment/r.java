package cn.v6.sixrooms.ui.fragment;

import android.view.View;
import android.view.View.OnClickListener;

final class r implements OnClickListener {
    final /* synthetic */ LoginFragment a;

    r(LoginFragment loginFragment) {
        this.a = loginFragment;
    }

    public final void onClick(View view) {
        if (LoginFragment.b(this.a) != null) {
            LoginFragment.b(this.a).changeRegisterPage();
        }
    }
}
