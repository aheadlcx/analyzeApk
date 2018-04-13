package cn.v6.sixrooms.room.fragment;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

final class ab implements OnDismissListener {
    final /* synthetic */ aa a;

    ab(aa aaVar) {
        this.a = aaVar;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        FullScreenRoomFragment.C(this.a.a);
        FullScreenRoomFragment.c(this.a.a, false);
    }
}
