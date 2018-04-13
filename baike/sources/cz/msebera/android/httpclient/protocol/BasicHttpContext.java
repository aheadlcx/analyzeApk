package cz.msebera.android.httpclient.protocol;

import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class BasicHttpContext implements HttpContext {
    private final HttpContext a;
    private final Map<String, Object> b;

    public BasicHttpContext() {
        this(null);
    }

    public BasicHttpContext(HttpContext httpContext) {
        this.b = new ConcurrentHashMap();
        this.a = httpContext;
    }

    public Object getAttribute(String str) {
        Args.notNull(str, "Id");
        Object obj = this.b.get(str);
        if (obj != null || this.a == null) {
            return obj;
        }
        return this.a.getAttribute(str);
    }

    public void setAttribute(String str, Object obj) {
        Args.notNull(str, "Id");
        if (obj != null) {
            this.b.put(str, obj);
        } else {
            this.b.remove(str);
        }
    }

    public Object removeAttribute(String str) {
        Args.notNull(str, "Id");
        return this.b.remove(str);
    }

    public void clear() {
        this.b.clear();
    }

    public String toString() {
        return this.b.toString();
    }
}
