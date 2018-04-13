package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.Constants;

class yx implements OnClickListener {
    final /* synthetic */ OtherInfoActivity a;

    yx(OtherInfoActivity otherInfoActivity) {
        this.a = otherInfoActivity;
    }

    public void onClick(View view) {
        if (OtherInfoActivity.f(this.a)) {
            OtherInfoActivity.g(this.a);
            if (OtherInfoActivity.h(this.a).isShowing()) {
                OtherInfoActivity.h(this.a).dismiss();
                return;
            } else {
                OtherInfoActivity.h(this.a).show();
                return;
            }
        }
        OtherInfoActivity.a(this.a, Constants.IS_UNSUBSCRIBE_TA, String.format(Constants.IS_UNSUBSCRIBE_TA, new Object[]{OtherInfoActivity.d(this.a)}), null);
    }
}
