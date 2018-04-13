package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnClickListener;

final class cv implements OnClickListener {
    final /* synthetic */ RetrieveNameOrPasswordActivity a;

    cv(RetrieveNameOrPasswordActivity retrieveNameOrPasswordActivity) {
        this.a = retrieveNameOrPasswordActivity;
    }

    public final void onClick(View view) {
        this.a.clearPhoneNum();
        this.a.hideCleanPhoneNumView();
    }
}
