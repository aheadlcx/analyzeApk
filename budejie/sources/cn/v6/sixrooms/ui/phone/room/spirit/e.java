package cn.v6.sixrooms.ui.phone.room.spirit;

final class e implements Runnable {
    final /* synthetic */ SpiritSurfaceView a;

    e(SpiritSurfaceView spiritSurfaceView) {
        this.a = spiritSurfaceView;
    }

    public final void run() {
        SpiritSurfaceView.a(this.a, SpiritSurfaceView.n(this.a));
        SpiritSurfaceView.o(this.a);
        SpiritSurfaceView.p(this.a).execute(new f(this));
    }
}
