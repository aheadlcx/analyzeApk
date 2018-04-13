package cn.v6.sixrooms.utils;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import cn.v6.sdk.sixrooms.coop.V6Coop;

final class ag implements OnDismissListener {
    ag() {
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        V6Coop.closeAll();
    }
}
