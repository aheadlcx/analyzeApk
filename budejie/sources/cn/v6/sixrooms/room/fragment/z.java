package cn.v6.sixrooms.room.fragment;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

final class z implements OnDismissListener {
    final /* synthetic */ y a;

    z(y yVar) {
        this.a = yVar;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        FullScreenRoomFragment.C(this.a.a);
    }
}
