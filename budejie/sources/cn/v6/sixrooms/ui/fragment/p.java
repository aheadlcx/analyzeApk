package cn.v6.sixrooms.ui.fragment;

import android.view.View;
import android.view.View.OnClickListener;

final class p implements OnClickListener {
    final /* synthetic */ LoginFragment a;

    p(LoginFragment loginFragment) {
        this.a = loginFragment;
    }

    public final void onClick(View view) {
        this.a.clearUsername();
        this.a.hideUsernameCleanView();
    }
}
