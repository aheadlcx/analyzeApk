package cz.msebera.android.httpclient.params;

import cz.msebera.android.httpclient.util.Args;

@Deprecated
public abstract class HttpAbstractParamBean {
    protected final HttpParams a;

    public HttpAbstractParamBean(HttpParams httpParams) {
        this.a = (HttpParams) Args.notNull(httpParams, "HTTP parameters");
    }
}
