package cn.v6.sixrooms.room;

import cn.v6.sixrooms.room.interfaces.RoomLiveCallBack;

final class bb implements RoomLiveCallBack {
    final /* synthetic */ RoomActivity a;

    bb(RoomActivity roomActivity) {
        this.a = roomActivity;
    }

    public final int getDefinitionStatus() {
        return this.a.b.getFlvStatus();
    }

    public final void changeDefinition() {
        this.a.b(this.a.b.getNextFlv());
    }

    public final boolean isChangeable() {
        return this.a.b.isChangeable();
    }

    public final boolean isChangeing() {
        return this.a.m.getVisibility() == 0;
    }
}
