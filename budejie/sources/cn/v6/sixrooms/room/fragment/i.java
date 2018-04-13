package cn.v6.sixrooms.room.fragment;

final class i implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ FullScreenRoomFragment b;

    i(FullScreenRoomFragment fullScreenRoomFragment, int i) {
        this.b = fullScreenRoomFragment;
        this.a = i;
    }

    public final void run() {
        FullScreenRoomFragment.f(this.b).showErrorToast(this.a);
        FullScreenRoomFragment.M(this.b).setClickable(true);
    }
}
