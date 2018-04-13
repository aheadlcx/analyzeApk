package cn.v6.sixrooms.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import cn.v6.sdk.sixrooms.coop.V6Coop;

final class ac extends Handler {
    final /* synthetic */ ToastUtils a;

    ac(ToastUtils toastUtils) {
        this.a = toastUtils;
    }

    public final void handleMessage(Message message) {
        int i = 1;
        if (message.what == 1) {
            if (ToastUtils.a(this.a) == null) {
                ToastUtils toastUtils = this.a;
                Context context = V6Coop.getInstance().getContext();
                CharSequence obj = message.obj.toString();
                if (message.arg1 != 1) {
                    i = 0;
                }
                ToastUtils.a(toastUtils, Toast.makeText(context, obj, i));
            } else {
                ToastUtils.a(this.a).setText(message.obj.toString());
                Toast a = ToastUtils.a(this.a);
                if (message.arg1 != 1) {
                    i = 0;
                }
                a.setDuration(i);
            }
            ToastUtils.a(this.a).show();
        } else if (ToastUtils.a(this.a) != null) {
            ToastUtils.a(this.a).cancel();
        }
    }
}
