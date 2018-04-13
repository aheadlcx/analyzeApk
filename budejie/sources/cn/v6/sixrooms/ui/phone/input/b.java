package cn.v6.sixrooms.ui.phone.input;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

final class b implements OnDismissListener {
    final /* synthetic */ BaseRoomInputDialog a;

    b(BaseRoomInputDialog baseRoomInputDialog) {
        this.a = baseRoomInputDialog;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        this.a.mKeyboardStatus = 0;
    }
}
