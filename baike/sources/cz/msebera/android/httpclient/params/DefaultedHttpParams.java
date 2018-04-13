package cz.msebera.android.httpclient.params;

import cz.msebera.android.httpclient.util.Args;
import java.util.HashSet;
import java.util.Set;

@Deprecated
public final class DefaultedHttpParams extends AbstractHttpParams {
    private final HttpParams a;
    private final HttpParams b;

    public DefaultedHttpParams(HttpParams httpParams, HttpParams httpParams2) {
        this.a = (HttpParams) Args.notNull(httpParams, "Local HTTP parameters");
        this.b = httpParams2;
    }

    public HttpParams copy() {
        return new DefaultedHttpParams(this.a.copy(), this.b);
    }

    public Object getParameter(String str) {
        Object parameter = this.a.getParameter(str);
        if (parameter != null || this.b == null) {
            return parameter;
        }
        return this.b.getParameter(str);
    }

    public boolean removeParameter(String str) {
        return this.a.removeParameter(str);
    }

    public HttpParams setParameter(String str, Object obj) {
        return this.a.setParameter(str, obj);
    }

    public HttpParams getDefaults() {
        return this.b;
    }

    public Set<String> getNames() {
        Set<String> hashSet = new HashSet(a(this.b));
        hashSet.addAll(a(this.a));
        return hashSet;
    }

    public Set<String> getDefaultNames() {
        return new HashSet(a(this.b));
    }

    public Set<String> getLocalNames() {
        return new HashSet(a(this.a));
    }

    private Set<String> a(HttpParams httpParams) {
        if (httpParams instanceof HttpParamsNames) {
            return ((HttpParamsNames) httpParams).getNames();
        }
        throw new UnsupportedOperationException("HttpParams instance does not implement HttpParamsNames");
    }
}
