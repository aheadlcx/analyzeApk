package cn.v6.sixrooms.room.fragment;

import cn.v6.sixrooms.base.SixRoomsUtils;
import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class ag implements DialogListener {
    final /* synthetic */ FullScreenRoomFragment a;

    ag(FullScreenRoomFragment fullScreenRoomFragment) {
        this.a = fullScreenRoomFragment;
    }

    public final void positive(int i) {
        SixRoomsUtils.gotoLogin(FullScreenRoomFragment.f(this.a));
    }

    public final void negative(int i) {
    }
}
