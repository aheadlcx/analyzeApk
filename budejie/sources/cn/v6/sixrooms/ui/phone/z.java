package cn.v6.sixrooms.ui.phone;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import cn.v6.sdk.sixrooms.coop.V6Coop;

final class z implements OnDismissListener {
    final /* synthetic */ DialogSupportCustomizeActivity a;

    z(DialogSupportCustomizeActivity dialogSupportCustomizeActivity) {
        this.a = dialogSupportCustomizeActivity;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        V6Coop.closeAll();
    }
}
