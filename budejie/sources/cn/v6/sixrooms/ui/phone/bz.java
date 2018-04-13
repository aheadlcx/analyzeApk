package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class bz implements DialogListener {
    final /* synthetic */ ResetPasswordActivity a;

    bz(ResetPasswordActivity resetPasswordActivity) {
        this.a = resetPasswordActivity;
    }

    public final void positive(int i) {
        this.a.getSlidingMenu().a();
    }

    public final void negative(int i) {
    }
}
