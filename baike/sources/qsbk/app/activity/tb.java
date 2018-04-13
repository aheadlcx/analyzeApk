package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class tb implements OnClickListener {
    final /* synthetic */ ManageQiuShiNewActivity a;

    tb(ManageQiuShiNewActivity manageQiuShiNewActivity) {
        this.a = manageQiuShiNewActivity;
    }

    public void onClick(View view) {
        this.a.b.cancel();
    }
}
