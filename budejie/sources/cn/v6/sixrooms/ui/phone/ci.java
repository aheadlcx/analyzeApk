package cn.v6.sixrooms.ui.phone;

import android.view.inputmethod.InputMethodManager;

final class ci implements Runnable {
    final /* synthetic */ RetrieveNameOrPasswordActivity a;

    ci(RetrieveNameOrPasswordActivity retrieveNameOrPasswordActivity) {
        this.a = retrieveNameOrPasswordActivity;
    }

    public final void run() {
        this.a.k = (InputMethodManager) this.a.getSystemService("input_method");
        this.a.k.toggleSoftInput(0, 2);
    }
}
