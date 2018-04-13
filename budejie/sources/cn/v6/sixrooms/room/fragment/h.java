package cn.v6.sixrooms.room.fragment;

final class h implements Runnable {
    final /* synthetic */ FullScreenRoomFragment a;

    h(FullScreenRoomFragment fullScreenRoomFragment) {
        this.a = fullScreenRoomFragment;
    }

    public final void run() {
        FullScreenRoomFragment.M(this.a).setClickable(true);
        FullScreenRoomFragment.O(this.a);
    }
}
