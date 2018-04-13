package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class qk implements OnClickListener {
    final /* synthetic */ LaiseeDetailActivity a;

    qk(LaiseeDetailActivity laiseeDetailActivity) {
        this.a = laiseeDetailActivity;
    }

    public void onClick(View view) {
        WalletActivity.launch(this.a);
    }
}
