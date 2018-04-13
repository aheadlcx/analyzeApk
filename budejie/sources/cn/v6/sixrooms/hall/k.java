package cn.v6.sixrooms.hall;

import android.content.Intent;
import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class k implements DialogListener {
    final /* synthetic */ HallActivity a;

    k(HallActivity hallActivity) {
        this.a = hallActivity;
    }

    public final void positive(int i) {
        this.a.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
    }

    public final void negative(int i) {
    }
}
