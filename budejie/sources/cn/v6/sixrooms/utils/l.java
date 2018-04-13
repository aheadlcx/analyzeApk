package cn.v6.sixrooms.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import cn.v6.sixrooms.hall.HallActivity;

final class l implements OnDismissListener {
    final /* synthetic */ Context a;

    l(Context context) {
        this.a = context;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        HallActivity.startSelf(this.a);
    }
}
