package cn.v6.sixrooms.ui.phone.room.spirit;

final class c implements Runnable {
    final /* synthetic */ ISpirit a;
    final /* synthetic */ SpiritSurfaceView b;

    c(SpiritSurfaceView spiritSurfaceView, ISpirit iSpirit) {
        this.b = spiritSurfaceView;
        this.a = iSpirit;
    }

    public final void run() {
        try {
            SpiritSurfaceView.g(this.b).lock();
            SpiritSurfaceView.c(this.b).remove(this.a);
            SpiritSurfaceView.k(this.b).signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SpiritSurfaceView.g(this.b).unlock();
        }
    }
}
