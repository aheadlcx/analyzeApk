package cn.v6.sixrooms.base;

final class j implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ VLResHandler b;
    final /* synthetic */ VLResHandler c;

    j(VLResHandler vLResHandler, boolean z, VLResHandler vLResHandler2) {
        this.c = vLResHandler;
        this.a = z;
        this.b = vLResHandler2;
    }

    public final void run() {
        this.c.handler(this.a);
        if (this.b != null) {
            this.b.handlerSuccess();
        }
    }
}
