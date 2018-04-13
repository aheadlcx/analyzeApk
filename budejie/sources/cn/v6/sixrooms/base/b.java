package cn.v6.sixrooms.base;

final class b extends VLBlock {
    final /* synthetic */ RPCException a;
    final /* synthetic */ RPCCallbackHandler b;

    b(RPCCallbackHandler rPCCallbackHandler, RPCException rPCException) {
        this.b = rPCCallbackHandler;
        this.a = rPCException;
    }

    protected final void process(boolean z) {
        this.b.handleError(this.a);
    }
}
