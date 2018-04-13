package cn.v6.sixrooms.room.fragment;

import cn.v6.sixrooms.room.RoomActivity.PlayerState;

final /* synthetic */ class x {
    static final /* synthetic */ int[] a = new int[PlayerState.values().length];

    static {
        try {
            a[PlayerState.PLAYEND.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[PlayerState.PLAYING.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[PlayerState.PLAYLONGIND.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
    }
}
