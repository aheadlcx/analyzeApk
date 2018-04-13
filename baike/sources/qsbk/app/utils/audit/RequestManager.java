package qsbk.app.utils.audit;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RequestManager {
    private final Set<Request> a = new HashSet(5);
    private BlockingQueue<Request> b = new LinkedBlockingQueue();
    private RequestWorker c;

    public interface RequestFilter {
        boolean apply(Request request);
    }

    public void cancelAll(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Cannot cancelAll with a null tag");
        }
        cancelAll(new a(this, obj));
    }

    public void cancelAll(RequestFilter requestFilter) {
        synchronized (this.a) {
            for (Request request : this.a) {
                if (requestFilter.apply(request)) {
                    request.cancel();
                }
            }
        }
    }

    public void add(Request request) {
        if (request.getRequestListener() == null) {
            throw new IllegalArgumentException("Request's listener must not be null");
        }
        synchronized (this.a) {
            this.a.add(request);
        }
        this.b.add(request);
    }

    public void stop() {
        if (this.c != null) {
            this.c.quit();
        }
    }

    public void start() {
        stop();
        this.c = new RequestWorker(this.b);
        this.c.start();
    }
}
