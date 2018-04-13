package cz.msebera.android.httpclient.client.methods;

import cz.msebera.android.httpclient.concurrent.Cancellable;
import cz.msebera.android.httpclient.conn.ConnectionReleaseTrigger;
import java.io.IOException;

class b implements Cancellable {
    final /* synthetic */ ConnectionReleaseTrigger a;
    final /* synthetic */ AbstractExecutionAwareRequest b;

    b(AbstractExecutionAwareRequest abstractExecutionAwareRequest, ConnectionReleaseTrigger connectionReleaseTrigger) {
        this.b = abstractExecutionAwareRequest;
        this.a = connectionReleaseTrigger;
    }

    public boolean cancel() {
        try {
            this.a.abortConnection();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
