package cz.msebera.android.httpclient.impl.client.cache;

import java.io.Closeable;

public interface SchedulingStrategy extends Closeable {
    void schedule(AsynchronousValidationRequest asynchronousValidationRequest);
}
