package cn.v6.sixrooms.base;

final class a extends VLBlock {
    final /* synthetic */ Object a;
    final /* synthetic */ RPCCallbackHandler b;

    a(RPCCallbackHandler rPCCallbackHandler, Object obj) {
        this.b = rPCCallbackHandler;
        this.a = obj;
    }

    protected final void process(boolean z) {
        this.b.handleResult(this.a);
    }
}
