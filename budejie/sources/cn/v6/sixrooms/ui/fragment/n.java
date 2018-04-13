package cn.v6.sixrooms.ui.fragment;

import cn.v6.sixrooms.R;
import cn.v6.sixrooms.widgets.phone.UserLoginTitleView.OnClickListener;

final class n implements OnClickListener {
    final /* synthetic */ LoginFragment a;

    n(LoginFragment loginFragment) {
        this.a = loginFragment;
    }

    public final void back() {
        LoginFragment.a(this.a).finish();
        LoginFragment.a(this.a).overridePendingTransition(R.anim.msg_verify_fragment_in, R.anim.msg_verify_fragment_out);
    }
}
