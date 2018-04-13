package cn.v6.sixrooms.base;

final class d extends VLBlock {
    final /* synthetic */ boolean a;
    final /* synthetic */ VLAsyncHandler b;

    d(VLAsyncHandler vLAsyncHandler, boolean z) {
        this.b = vLAsyncHandler;
        this.a = z;
    }

    protected final void process(boolean z) {
        this.b.handler(this.a);
    }
}
