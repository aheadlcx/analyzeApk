package cn.v6.sixrooms.utils;

import android.widget.Toast;
import cn.v6.sdk.sixrooms.coop.V6Coop;

final class ad implements Runnable {
    final /* synthetic */ String a;

    ad(String str) {
        this.a = str;
    }

    public final void run() {
        if (ToastUtils.a() == null) {
            ToastUtils.a(Toast.makeText(V6Coop.getInstance().getContext(), this.a, 0));
        } else {
            ToastUtils.a().setText(this.a);
        }
        ToastUtils.a().show();
    }
}
