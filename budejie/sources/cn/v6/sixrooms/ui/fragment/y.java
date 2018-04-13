package cn.v6.sixrooms.ui.fragment;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.utils.ToastUtils;

final class y implements OnFocusChangeListener {
    final /* synthetic */ LoginFragment a;

    y(LoginFragment loginFragment) {
        this.a = loginFragment;
    }

    public final void onFocusChange(View view, boolean z) {
        if (z) {
            if (LoginFragment.f(this.a).getText().toString().trim().length() > 0) {
                LoginFragment.h(this.a).showCleanTag();
            }
            String username = this.a.getUsername();
            if (!username.isEmpty()) {
                if (username.contains(" ")) {
                    ToastUtils.showToast(this.a.getString(R.string.tip_username_containBlank));
                    return;
                } else {
                    LoginFragment.e(this.a).preLogin();
                    return;
                }
            }
            return;
        }
        LoginFragment.h(this.a).hideCleanTag();
    }
}
