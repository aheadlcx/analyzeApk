package cn.v6.sixrooms.ui.fragment;

import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class q implements DialogListener {
    final /* synthetic */ String a;
    final /* synthetic */ boolean b;
    final /* synthetic */ LoginFragment c;

    q(LoginFragment loginFragment, String str, boolean z) {
        this.c = loginFragment;
        this.a = str;
        this.b = z;
    }

    public final void positive(int i) {
        LoginFragment.a(this.c, this.a);
    }

    public final void negative(int i) {
        this.c.hideLoading();
    }
}
