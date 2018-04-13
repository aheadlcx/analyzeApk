package cn.v6.sixrooms.ui.phone;

import android.view.inputmethod.InputMethodManager;

final class bv implements Runnable {
    final /* synthetic */ ResetPasswordActivity a;

    bv(ResetPasswordActivity resetPasswordActivity) {
        this.a = resetPasswordActivity;
    }

    public final void run() {
        this.a.j = (InputMethodManager) this.a.getSystemService("input_method");
        this.a.j.toggleSoftInput(0, 2);
    }
}
