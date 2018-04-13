package cn.v6.sixrooms.base;

final class k extends VLBlock {
    final /* synthetic */ boolean a;
    final /* synthetic */ VLResHandler b;
    final /* synthetic */ VLResHandler c;

    k(VLResHandler vLResHandler, boolean z, VLResHandler vLResHandler2) {
        this.c = vLResHandler;
        this.a = z;
        this.b = vLResHandler2;
    }

    protected final void process(boolean z) {
        this.c.handler(this.a);
        if (this.b != null) {
            this.b.handlerSuccess();
        }
    }
}
