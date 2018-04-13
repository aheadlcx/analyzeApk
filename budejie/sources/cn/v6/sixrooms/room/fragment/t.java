package cn.v6.sixrooms.room.fragment;

import cn.v6.sixrooms.event.EventObserver;
import cn.v6.sixrooms.event.GiftBoxEvent;

final class t implements EventObserver {
    final /* synthetic */ FullScreenRoomFragment a;

    t(FullScreenRoomFragment fullScreenRoomFragment) {
        this.a = fullScreenRoomFragment;
    }

    public final void onEventChange(Object obj, String str) {
        if (obj instanceof GiftBoxEvent) {
            GiftBoxEvent giftBoxEvent = (GiftBoxEvent) obj;
            if ("0".equals(str)) {
                FullScreenRoomFragment.a(this.a, giftBoxEvent.getUname(), giftBoxEvent.getUid());
            } else if ("1".equals(str) && FullScreenRoomFragment.J(this.a) != null && FullScreenRoomFragment.J(this.a).isShowing()) {
                FullScreenRoomFragment.d(this.a, !giftBoxEvent.isUpdateChatList);
            }
        }
    }
}
