package cn.v6.sixrooms.room.fragment;

import cn.v6.sixrooms.ui.phone.input.KeyboardState;
import cn.v6.sixrooms.ui.phone.input.RoomInputListener;

final class p implements RoomInputListener {
    final /* synthetic */ FullScreenRoomFragment a;

    p(FullScreenRoomFragment fullScreenRoomFragment) {
        this.a = fullScreenRoomFragment;
    }

    public final void show() {
    }

    public final void dismiss() {
        if (this.a.mPublicChatPage != null) {
            this.a.mPublicChatPage.setSelection();
        }
        if (FullScreenRoomFragment.b(this.a) != null && FullScreenRoomFragment.S(this.a)) {
            FullScreenRoomFragment.T(this.a);
            FullScreenRoomFragment.a(this.a, true);
        }
    }

    public final void changeState(KeyboardState keyboardState) {
    }
}
