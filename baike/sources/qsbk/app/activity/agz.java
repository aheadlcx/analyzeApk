package qsbk.app.activity;

import android.view.View;
import qsbk.app.utils.Util;
import qsbk.app.widget.NoUnderlineClickableSpan;

class agz extends NoUnderlineClickableSpan {
    final /* synthetic */ WithdrawSetupActivity a;

    agz(WithdrawSetupActivity withdrawSetupActivity) {
        this.a = withdrawSetupActivity;
    }

    public void onClick(View view) {
        Util.joinQQGroup(Util.getActivityOrContext(view), WithdrawSetupActivity.KEY_QQ_GROUP);
    }
}
