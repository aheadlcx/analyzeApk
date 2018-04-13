package qsbk.app.live.ui;

import android.view.View;
import qsbk.app.live.widget.DiscountDialog;
import qsbk.app.live.widget.DiscountDialog.ClickListener;

class ca implements ClickListener {
    final /* synthetic */ DiscountDialog a;
    final /* synthetic */ LiveBaseActivity b;

    ca(LiveBaseActivity liveBaseActivity, DiscountDialog discountDialog) {
        this.b = liveBaseActivity;
        this.a = discountDialog;
    }

    public void onClick(View view) {
        this.b.toPayActivity();
        this.a.dismiss();
    }
}
