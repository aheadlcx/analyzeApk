package cn.v6.sixrooms.base;

final class l implements Runnable {
    final /* synthetic */ BlockItem a;
    final /* synthetic */ VLScheduler b;

    l(VLScheduler vLScheduler, BlockItem blockItem) {
        this.b = vLScheduler;
        this.a = blockItem;
    }

    public final void run() {
        try {
            this.a.mBlock.process(true);
        } catch (Throwable th) {
            VLDebug.Assert(false, th);
        }
        synchronized (this) {
            this.b.a(this.a);
        }
    }
}
