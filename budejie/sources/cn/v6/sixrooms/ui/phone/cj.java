package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnClickListener;

final class cj implements OnClickListener {
    final /* synthetic */ RetrieveNameOrPasswordActivity a;

    cj(RetrieveNameOrPasswordActivity retrieveNameOrPasswordActivity) {
        this.a = retrieveNameOrPasswordActivity;
    }

    public final void onClick(View view) {
        this.a.clearAutoCode();
        this.a.hideCleanAudoCodeView();
    }
}
