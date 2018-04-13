package cn.v6.sixrooms.room.fragment;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

final class af implements OnDismissListener {
    final /* synthetic */ FullScreenRoomFragment a;

    af(FullScreenRoomFragment fullScreenRoomFragment) {
        this.a = fullScreenRoomFragment;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        if (!FullScreenRoomFragment.G(this.a)) {
            FullScreenRoomFragment.C(this.a);
        }
    }
}
