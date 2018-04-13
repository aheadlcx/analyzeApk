package cn.v6.sixrooms.room;

final class ab implements Runnable {
    final /* synthetic */ RoomActivity a;

    ab(RoomActivity roomActivity) {
        this.a = roomActivity;
    }

    public final void run() {
        this.a.F.setVisibility(0);
    }
}
