package cn.v6.sixrooms.utils;

import android.app.Activity;
import android.os.Handler;
import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class t implements DialogListener {
    final /* synthetic */ Activity a;

    t(Activity activity) {
        this.a = activity;
    }

    public final void positive(int i) {
        new Handler().postDelayed(new u(this), 300);
    }

    public final void negative(int i) {
    }
}
