package cn.v6.sixrooms.room.fragment;

import cn.v6.sixrooms.constants.GiftIdStrs;
import cn.v6.sixrooms.room.view.PigPkDuckView.PigPkDuckViewListener;

final class v implements PigPkDuckViewListener {
    final /* synthetic */ FullScreenRoomFragment a;

    v(FullScreenRoomFragment fullScreenRoomFragment) {
        this.a = fullScreenRoomFragment;
    }

    public final void onDuck() {
        this.a.openGiftBox(GiftIdStrs.YELLOWDUCK_GIFT_ID);
    }

    public final void onPig() {
        this.a.openGiftBox(GiftIdStrs.PIG_GIFT_ID);
    }

    public final void onUserName(String str) {
        FullScreenRoomFragment.f(this.a).showEnterRoomDialog(str);
    }

    public final void onGameOver() {
        if (FullScreenRoomFragment.af(this.a) != null) {
            FullScreenRoomFragment.ag(this.a).removeView(FullScreenRoomFragment.af(this.a));
            FullScreenRoomFragment.ah(this.a);
        }
    }
}
