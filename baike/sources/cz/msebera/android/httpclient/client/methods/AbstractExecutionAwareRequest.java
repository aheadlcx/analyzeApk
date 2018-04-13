package cz.msebera.android.httpclient.client.methods;

import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.client.utils.CloneUtils;
import cz.msebera.android.httpclient.concurrent.Cancellable;
import cz.msebera.android.httpclient.conn.ClientConnectionRequest;
import cz.msebera.android.httpclient.conn.ConnectionReleaseTrigger;
import cz.msebera.android.httpclient.message.AbstractHttpMessage;
import cz.msebera.android.httpclient.message.HeaderGroup;
import cz.msebera.android.httpclient.params.HttpParams;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public abstract class AbstractExecutionAwareRequest extends AbstractHttpMessage implements HttpRequest, AbortableHttpRequest, HttpExecutionAware, Cloneable {
    private final AtomicBoolean c = new AtomicBoolean(false);
    private final AtomicReference<Cancellable> d = new AtomicReference(null);

    protected AbstractExecutionAwareRequest() {
    }

    @Deprecated
    public void setConnectionRequest(ClientConnectionRequest clientConnectionRequest) {
        setCancellable(new a(this, clientConnectionRequest));
    }

    @Deprecated
    public void setReleaseTrigger(ConnectionReleaseTrigger connectionReleaseTrigger) {
        setCancellable(new b(this, connectionReleaseTrigger));
    }

    public void abort() {
        if (this.c.compareAndSet(false, true)) {
            Cancellable cancellable = (Cancellable) this.d.getAndSet(null);
            if (cancellable != null) {
                cancellable.cancel();
            }
        }
    }

    public boolean isAborted() {
        return this.c.get();
    }

    public void setCancellable(Cancellable cancellable) {
        if (!this.c.get()) {
            this.d.set(cancellable);
        }
    }

    public Object clone() throws CloneNotSupportedException {
        AbstractExecutionAwareRequest abstractExecutionAwareRequest = (AbstractExecutionAwareRequest) super.clone();
        abstractExecutionAwareRequest.a = (HeaderGroup) CloneUtils.cloneObject(this.a);
        abstractExecutionAwareRequest.b = (HttpParams) CloneUtils.cloneObject(this.b);
        return abstractExecutionAwareRequest;
    }

    public void completed() {
        this.d.set(null);
    }

    public void reset() {
        Cancellable cancellable = (Cancellable) this.d.getAndSet(null);
        if (cancellable != null) {
            cancellable.cancel();
        }
        this.c.set(false);
    }
}
