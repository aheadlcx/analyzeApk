package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnFocusChangeListener;

final class cl implements OnFocusChangeListener {
    final /* synthetic */ RetrieveNameOrPasswordActivity a;

    cl(RetrieveNameOrPasswordActivity retrieveNameOrPasswordActivity) {
        this.a = retrieveNameOrPasswordActivity;
    }

    public final void onFocusChange(View view, boolean z) {
        if (z) {
            this.a.showCleanPhoneNumView(this.a.f.getText().toString().trim().length() > 0);
        } else {
            this.a.hideCleanPhoneNumView();
        }
    }
}
