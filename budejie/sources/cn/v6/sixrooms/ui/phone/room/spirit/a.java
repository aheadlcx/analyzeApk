package cn.v6.sixrooms.ui.phone.room.spirit;

final class a implements Runnable {
    final /* synthetic */ ISpirit a;
    final /* synthetic */ SpiritSurfaceView b;

    a(SpiritSurfaceView spiritSurfaceView, ISpirit iSpirit) {
        this.b = spiritSurfaceView;
        this.a = iSpirit;
    }

    public final void run() {
        try {
            SpiritSurfaceView.g(this.b).lock();
            while (SpiritSurfaceView.c(this.b).size() >= 100) {
                SpiritSurfaceView.k(this.b).await();
            }
            SpiritSurfaceView.d(this.b).post(new b(this));
            SpiritSurfaceView.c(this.b).add(this.a);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SpiritSurfaceView.g(this.b).unlock();
        }
        try {
            SpiritSurfaceView.a(this.b).lock();
            SpiritSurfaceView.e(this.b).signal();
        } catch (Exception e2) {
            e2.printStackTrace();
        } finally {
            SpiritSurfaceView.a(this.b).unlock();
        }
    }
}
