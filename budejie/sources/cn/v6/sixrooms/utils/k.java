package cn.v6.sixrooms.utils;

import android.content.Context;
import cn.v6.sixrooms.hall.HallActivity;
import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class k implements DialogListener {
    final /* synthetic */ Context a;

    k(Context context) {
        this.a = context;
    }

    public final void positive(int i) {
        HallActivity.startSelf(this.a);
    }

    public final void negative(int i) {
    }
}
