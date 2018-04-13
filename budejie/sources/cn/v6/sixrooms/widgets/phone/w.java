package cn.v6.sixrooms.widgets.phone;

import android.view.View;
import android.view.View.OnClickListener;

final class w implements OnClickListener {
    final /* synthetic */ MsgVerifyDialog a;

    w(MsgVerifyDialog msgVerifyDialog) {
        this.a = msgVerifyDialog;
    }

    public final void onClick(View view) {
        this.a.dismiss();
    }
}
