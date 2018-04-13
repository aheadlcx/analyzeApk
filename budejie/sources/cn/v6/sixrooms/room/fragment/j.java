package cn.v6.sixrooms.room.fragment;

final class j implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ FullScreenRoomFragment c;

    j(FullScreenRoomFragment fullScreenRoomFragment, String str, String str2) {
        this.c = fullScreenRoomFragment;
        this.a = str;
        this.b = str2;
    }

    public final void run() {
        this.c.handleErrorResult(this.a, this.b);
        FullScreenRoomFragment.M(this.c).setClickable(true);
    }
}
