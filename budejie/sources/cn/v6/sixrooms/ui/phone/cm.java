package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnFocusChangeListener;

final class cm implements OnFocusChangeListener {
    final /* synthetic */ RetrieveNameOrPasswordActivity a;

    cm(RetrieveNameOrPasswordActivity retrieveNameOrPasswordActivity) {
        this.a = retrieveNameOrPasswordActivity;
    }

    public final void onFocusChange(View view, boolean z) {
        if (z) {
            this.a.showCleanAutoCodeView(this.a.g.getText().toString().trim().length() > 0);
        } else {
            this.a.hideCleanAudoCodeView();
        }
    }
}
