package cn.v6.sixrooms.ui.phone.room.spirit;

final class f implements Runnable {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public final void run() {
        try {
            SpiritSurfaceView.a(this.a.a).lock();
            SpiritSurfaceView.e(this.a.a).signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SpiritSurfaceView.a(this.a.a).unlock();
        }
    }
}
