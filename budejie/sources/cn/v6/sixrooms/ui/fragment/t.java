package cn.v6.sixrooms.ui.fragment;

import android.view.View;
import android.view.View.OnClickListener;

final class t implements OnClickListener {
    final /* synthetic */ LoginFragment a;

    t(LoginFragment loginFragment) {
        this.a = loginFragment;
    }

    public final void onClick(View view) {
        LoginFragment.e(this.a).forgetUsername();
    }
}
