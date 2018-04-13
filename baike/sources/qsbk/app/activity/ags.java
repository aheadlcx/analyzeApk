package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class ags implements OnClickListener {
    final /* synthetic */ WithdrawActivity a;

    ags(WithdrawActivity withdrawActivity) {
        this.a = withdrawActivity;
    }

    public void onClick(View view) {
        this.a.getWithdrawInfo();
    }
}
