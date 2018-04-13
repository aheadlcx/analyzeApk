package cn.v6.sixrooms.ui.phone.input;

import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;

final class c implements OnShowListener {
    final /* synthetic */ BaseRoomInputDialog a;

    c(BaseRoomInputDialog baseRoomInputDialog) {
        this.a = baseRoomInputDialog;
    }

    public final void onShow(DialogInterface dialogInterface) {
        this.a.mKeyboardStatus = 1;
    }
}
