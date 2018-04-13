package cn.v6.sixrooms.utils;

import android.app.Activity;
import android.content.Intent;
import cn.v6.sixrooms.pay.ui.activity.RechargeActivity;
import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class o implements DialogListener {
    final /* synthetic */ Activity a;

    o(Activity activity) {
        this.a = activity;
    }

    public final void positive(int i) {
        this.a.startActivity(new Intent(this.a, RechargeActivity.class));
    }

    public final void negative(int i) {
    }
}
