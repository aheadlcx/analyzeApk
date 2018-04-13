package cz.msebera.android.httpclient.protocol;

import cz.msebera.android.httpclient.util.Args;

@Deprecated
public final class DefaultedHttpContext implements HttpContext {
    private final HttpContext a;
    private final HttpContext b;

    public DefaultedHttpContext(HttpContext httpContext, HttpContext httpContext2) {
        this.a = (HttpContext) Args.notNull(httpContext, "HTTP context");
        this.b = httpContext2;
    }

    public Object getAttribute(String str) {
        Object attribute = this.a.getAttribute(str);
        if (attribute == null) {
            return this.b.getAttribute(str);
        }
        return attribute;
    }

    public Object removeAttribute(String str) {
        return this.a.removeAttribute(str);
    }

    public void setAttribute(String str, Object obj) {
        this.a.setAttribute(str, obj);
    }

    public HttpContext getDefaults() {
        return this.b;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[local: ").append(this.a);
        stringBuilder.append("defaults: ").append(this.b);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
