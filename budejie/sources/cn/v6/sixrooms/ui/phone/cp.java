package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnClickListener;

final class cp implements OnClickListener {
    final /* synthetic */ RetrieveNameOrPasswordActivity a;

    cp(RetrieveNameOrPasswordActivity retrieveNameOrPasswordActivity) {
        this.a = retrieveNameOrPasswordActivity;
    }

    public final void onClick(View view) {
        if (!this.a.a() || !RetrieveNameOrPasswordActivity.d(this.a)) {
            return;
        }
        if (this.a.isRetrieveName() || RetrieveNameOrPasswordActivity.e(this.a)) {
            this.a.j.findUsernameOrPwd();
        }
    }
}
