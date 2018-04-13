package qsbk.app.utils;

import android.view.View;
import android.view.View.OnClickListener;

class g implements OnClickListener {
    final /* synthetic */ CircleUpgradeDialog a;

    g(CircleUpgradeDialog circleUpgradeDialog) {
        this.a = circleUpgradeDialog;
    }

    public void onClick(View view) {
        this.a.dismiss();
    }
}
