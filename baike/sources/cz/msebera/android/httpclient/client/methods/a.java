package cz.msebera.android.httpclient.client.methods;

import cz.msebera.android.httpclient.concurrent.Cancellable;
import cz.msebera.android.httpclient.conn.ClientConnectionRequest;

class a implements Cancellable {
    final /* synthetic */ ClientConnectionRequest a;
    final /* synthetic */ AbstractExecutionAwareRequest b;

    a(AbstractExecutionAwareRequest abstractExecutionAwareRequest, ClientConnectionRequest clientConnectionRequest) {
        this.b = abstractExecutionAwareRequest;
        this.a = clientConnectionRequest;
    }

    public boolean cancel() {
        this.a.abortRequest();
        return true;
    }
}
