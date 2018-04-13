package cn.v6.sixrooms.utils;

import android.app.Activity;
import android.os.Looper;
import cn.v6.sixrooms.base.SixRoomsUtils;
import cn.v6.sixrooms.base.VLScheduler;
import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class p implements DialogListener {
    final /* synthetic */ Activity a;

    p(Activity activity) {
        this.a = activity;
    }

    public final void positive(int i) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            LogUtils.e("HandleErrorUtils", "主线程");
            SixRoomsUtils.gotoLogin(this.a);
            return;
        }
        LogUtils.e("HandleErrorUtils", "非主线程");
        VLScheduler.instance.schedule(0, 0, new q(this));
    }

    public final void negative(int i) {
    }
}
