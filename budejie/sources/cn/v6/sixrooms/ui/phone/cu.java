package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnClickListener;

final class cu implements OnClickListener {
    final /* synthetic */ RetrieveNameOrPasswordActivity a;

    cu(RetrieveNameOrPasswordActivity retrieveNameOrPasswordActivity) {
        this.a = retrieveNameOrPasswordActivity;
    }

    public final void onClick(View view) {
        this.a.clearUsername();
        this.a.hideCleanUsernameView();
    }
}
