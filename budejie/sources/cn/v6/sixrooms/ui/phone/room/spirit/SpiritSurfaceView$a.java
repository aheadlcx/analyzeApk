package cn.v6.sixrooms.ui.phone.room.spirit;

class SpiritSurfaceView$a implements Runnable {
    final /* synthetic */ SpiritSurfaceView a;

    private SpiritSurfaceView$a(SpiritSurfaceView spiritSurfaceView) {
        this.a = spiritSurfaceView;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r4 = this;
        r0 = r4.a;	 Catch:{ Exception -> 0x003a }
        r0 = cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.a(r0);	 Catch:{ Exception -> 0x003a }
        r0.lock();	 Catch:{ Exception -> 0x003a }
    L_0x0009:
        r0 = r4.a;	 Catch:{ Exception -> 0x003a }
        r0 = cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.b(r0);	 Catch:{ Exception -> 0x003a }
        if (r0 == 0) goto L_0x0127;
    L_0x0011:
        r0 = r4.a;	 Catch:{ Exception -> 0x003a }
        r0 = cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.c(r0);	 Catch:{ Exception -> 0x003a }
        r0 = r0.size();	 Catch:{ Exception -> 0x003a }
        if (r0 != 0) goto L_0x0048;
    L_0x001d:
        r0 = r4.a;	 Catch:{ InterruptedException -> 0x0035 }
        r0 = cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.d(r0);	 Catch:{ InterruptedException -> 0x0035 }
        r1 = new cn.v6.sixrooms.ui.phone.room.spirit.g;	 Catch:{ InterruptedException -> 0x0035 }
        r1.<init>(r4);	 Catch:{ InterruptedException -> 0x0035 }
        r0.post(r1);	 Catch:{ InterruptedException -> 0x0035 }
        r0 = r4.a;	 Catch:{ InterruptedException -> 0x0035 }
        r0 = cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.e(r0);	 Catch:{ InterruptedException -> 0x0035 }
        r0.await();	 Catch:{ InterruptedException -> 0x0035 }
        goto L_0x0011;
    L_0x0035:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ Exception -> 0x003a }
        goto L_0x0011;
    L_0x003a:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x005f }
        r0 = r4.a;
        r0 = cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.a(r0);
        r0.unlock();
    L_0x0047:
        return;
    L_0x0048:
        r0 = r4.a;	 Catch:{ Exception -> 0x003a }
        r0 = cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.f(r0);	 Catch:{ Exception -> 0x003a }
        if (r0 == 0) goto L_0x006a;
    L_0x0050:
        r0 = r4.a;	 Catch:{ InterruptedException -> 0x005a }
        r0 = cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.e(r0);	 Catch:{ InterruptedException -> 0x005a }
        r0.await();	 Catch:{ InterruptedException -> 0x005a }
        goto L_0x0048;
    L_0x005a:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ Exception -> 0x003a }
        goto L_0x0048;
    L_0x005f:
        r0 = move-exception;
        r1 = r4.a;
        r1 = cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.a(r1);
        r1.unlock();
        throw r0;
    L_0x006a:
        r0 = r4.a;	 Catch:{ Exception -> 0x009d }
        r0 = cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.g(r0);	 Catch:{ Exception -> 0x009d }
        r0.lock();	 Catch:{ Exception -> 0x009d }
    L_0x0073:
        r0 = r4.a;	 Catch:{ Exception -> 0x009d }
        r0 = cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.h(r0);	 Catch:{ Exception -> 0x009d }
        if (r0 <= 0) goto L_0x00f6;
    L_0x007b:
        r0 = r4.a;	 Catch:{ Exception -> 0x009d }
        r0 = cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.c(r0);	 Catch:{ Exception -> 0x009d }
        r0 = r0.size();	 Catch:{ Exception -> 0x009d }
        if (r0 <= 0) goto L_0x00f6;
    L_0x0087:
        r0 = r4.a;	 Catch:{ Exception -> 0x009d }
        r0 = cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.c(r0);	 Catch:{ Exception -> 0x009d }
        r1 = 0;
        r0.remove(r1);	 Catch:{ Exception -> 0x009d }
        r0 = r4.a;	 Catch:{ Exception -> 0x009d }
        cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.i(r0);	 Catch:{ Exception -> 0x009d }
        r0 = r4.a;	 Catch:{ Exception -> 0x009d }
        r1 = 1;
        cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.a(r0, r1);	 Catch:{ Exception -> 0x009d }
        goto L_0x0073;
    L_0x009d:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0117 }
        r0 = r4.a;	 Catch:{ Exception -> 0x003a }
        r0 = cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.g(r0);	 Catch:{ Exception -> 0x003a }
        r0.unlock();	 Catch:{ Exception -> 0x003a }
    L_0x00aa:
        r0 = r4.a;	 Catch:{ Exception -> 0x003a }
        r0 = cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.l(r0);	 Catch:{ Exception -> 0x003a }
        r1 = r0.lockCanvas();	 Catch:{ Exception -> 0x003a }
        r0 = r4.a;	 Catch:{ Exception -> 0x003a }
        r0 = cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.l(r0);	 Catch:{ Exception -> 0x003a }
        if (r0 == 0) goto L_0x0009;
    L_0x00bc:
        if (r1 == 0) goto L_0x0009;
    L_0x00be:
        r0 = r4.a;	 Catch:{ Exception -> 0x003a }
        r0 = r0.getVisibility();	 Catch:{ Exception -> 0x003a }
        if (r0 != 0) goto L_0x0009;
    L_0x00c6:
        r2 = r4.a;	 Catch:{ Exception -> 0x0122 }
        r0 = r4.a;	 Catch:{ Exception -> 0x0122 }
        r0 = cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.c(r0);	 Catch:{ Exception -> 0x0122 }
        r3 = 0;
        r0 = r0.get(r3);	 Catch:{ Exception -> 0x0122 }
        r0 = (cn.v6.sixrooms.ui.phone.room.spirit.ISpirit) r0;	 Catch:{ Exception -> 0x0122 }
        cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.a(r2, r0);	 Catch:{ Exception -> 0x0122 }
    L_0x00d8:
        r0 = r4.a;	 Catch:{ Exception -> 0x003a }
        r0 = cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.m(r0);	 Catch:{ Exception -> 0x003a }
        if (r0 == 0) goto L_0x00eb;
    L_0x00e0:
        r0 = r4.a;	 Catch:{ Exception -> 0x003a }
        r0 = cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.m(r0);	 Catch:{ Exception -> 0x003a }
        r2 = r4.a;	 Catch:{ Exception -> 0x003a }
        r0.draw(r1, r2);	 Catch:{ Exception -> 0x003a }
    L_0x00eb:
        r0 = r4.a;	 Catch:{ Exception -> 0x003a }
        r0 = cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.l(r0);	 Catch:{ Exception -> 0x003a }
        r0.unlockCanvasAndPost(r1);	 Catch:{ Exception -> 0x003a }
        goto L_0x0009;
    L_0x00f6:
        r0 = r4.a;	 Catch:{ Exception -> 0x009d }
        r0 = cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.j(r0);	 Catch:{ Exception -> 0x009d }
        if (r0 == 0) goto L_0x010d;
    L_0x00fe:
        r0 = r4.a;	 Catch:{ Exception -> 0x009d }
        r1 = 0;
        cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.a(r0, r1);	 Catch:{ Exception -> 0x009d }
        r0 = r4.a;	 Catch:{ Exception -> 0x009d }
        r0 = cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.k(r0);	 Catch:{ Exception -> 0x009d }
        r0.signalAll();	 Catch:{ Exception -> 0x009d }
    L_0x010d:
        r0 = r4.a;	 Catch:{ Exception -> 0x003a }
        r0 = cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.g(r0);	 Catch:{ Exception -> 0x003a }
        r0.unlock();	 Catch:{ Exception -> 0x003a }
        goto L_0x00aa;
    L_0x0117:
        r0 = move-exception;
        r1 = r4.a;	 Catch:{ Exception -> 0x003a }
        r1 = cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.g(r1);	 Catch:{ Exception -> 0x003a }
        r1.unlock();	 Catch:{ Exception -> 0x003a }
        throw r0;	 Catch:{ Exception -> 0x003a }
    L_0x0122:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ Exception -> 0x003a }
        goto L_0x00d8;
    L_0x0127:
        r0 = r4.a;
        r0 = cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView.a(r0);
        r0.unlock();
        goto L_0x0047;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.v6.sixrooms.ui.phone.room.spirit.SpiritSurfaceView$a.run():void");
    }
}
