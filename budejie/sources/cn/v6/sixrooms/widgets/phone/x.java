package cn.v6.sixrooms.widgets.phone;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

final class x implements OnClickListener {
    final /* synthetic */ MsgVerifyDialog a;

    x(MsgVerifyDialog msgVerifyDialog) {
        this.a = msgVerifyDialog;
    }

    public final void onClick(View view) {
        Object obj = MsgVerifyDialog.b(this.a).getText().toString();
        if (!TextUtils.isEmpty(obj) && MsgVerifyDialog.c(this.a) != null) {
            MsgVerifyDialog.c(this.a).onClick(obj);
        }
    }
}
