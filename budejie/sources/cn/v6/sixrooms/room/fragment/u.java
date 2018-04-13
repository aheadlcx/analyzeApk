package cn.v6.sixrooms.room.fragment;

import cn.v6.sixrooms.event.EventObserver;
import cn.v6.sixrooms.event.LoginEvent;

final class u implements EventObserver {
    final /* synthetic */ FullScreenRoomFragment a;

    u(FullScreenRoomFragment fullScreenRoomFragment) {
        this.a = fullScreenRoomFragment;
    }

    public final void onEventChange(Object obj, String str) {
        if ((obj instanceof LoginEvent) && FullScreenRoomFragment.b(this.a) != null) {
            FullScreenRoomFragment.c(this.a).initLoadUrl(FullScreenRoomFragment.b(this.a).getUrl());
        }
    }
}
