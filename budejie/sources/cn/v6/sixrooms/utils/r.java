package cn.v6.sixrooms.utils;

import android.app.Activity;
import cn.v6.sixrooms.base.VLBlock;

final class r extends VLBlock {
    final /* synthetic */ Activity a;

    r(Activity activity) {
        this.a = activity;
    }

    protected final void process(boolean z) {
        LoginUtils.createLoginDialog(this.a, null).show();
    }
}
