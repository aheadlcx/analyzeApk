package cn.v6.sixrooms.pay.ui.activity;

import android.view.View;
import android.view.View.OnClickListener;

final class bc implements OnClickListener {
    final /* synthetic */ RechargeActivity a;

    bc(RechargeActivity rechargeActivity) {
        this.a = rechargeActivity;
    }

    public final void onClick(View view) {
        this.a.finish();
    }
}
