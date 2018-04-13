package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnFocusChangeListener;

final class cn implements OnFocusChangeListener {
    final /* synthetic */ RetrieveNameOrPasswordActivity a;

    cn(RetrieveNameOrPasswordActivity retrieveNameOrPasswordActivity) {
        this.a = retrieveNameOrPasswordActivity;
    }

    public final void onFocusChange(View view, boolean z) {
        if (z) {
            this.a.showCleanUsernameView(this.a.h.getText().toString().trim().length() > 0);
        } else {
            this.a.hideCleanUsernameView();
        }
    }
}
