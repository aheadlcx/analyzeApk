package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnClickListener;

final class co implements OnClickListener {
    final /* synthetic */ RetrieveNameOrPasswordActivity a;

    co(RetrieveNameOrPasswordActivity retrieveNameOrPasswordActivity) {
        this.a = retrieveNameOrPasswordActivity;
    }

    public final void onClick(View view) {
        this.a.getSlidingMenu().a();
    }
}
