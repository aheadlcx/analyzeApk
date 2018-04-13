package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.params.AbstractHttpParams;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;

@NotThreadSafe
@Deprecated
public class ClientParamsStack extends AbstractHttpParams {
    protected final HttpParams a;
    protected final HttpParams b;
    protected final HttpParams c;
    protected final HttpParams d;

    public ClientParamsStack(HttpParams httpParams, HttpParams httpParams2, HttpParams httpParams3, HttpParams httpParams4) {
        this.a = httpParams;
        this.b = httpParams2;
        this.c = httpParams3;
        this.d = httpParams4;
    }

    public ClientParamsStack(ClientParamsStack clientParamsStack) {
        this(clientParamsStack.getApplicationParams(), clientParamsStack.getClientParams(), clientParamsStack.getRequestParams(), clientParamsStack.getOverrideParams());
    }

    public ClientParamsStack(ClientParamsStack clientParamsStack, HttpParams httpParams, HttpParams httpParams2, HttpParams httpParams3, HttpParams httpParams4) {
        if (httpParams == null) {
            httpParams = clientParamsStack.getApplicationParams();
        }
        if (httpParams2 == null) {
            httpParams2 = clientParamsStack.getClientParams();
        }
        if (httpParams3 == null) {
            httpParams3 = clientParamsStack.getRequestParams();
        }
        if (httpParams4 == null) {
            httpParams4 = clientParamsStack.getOverrideParams();
        }
        this(httpParams, httpParams2, httpParams3, httpParams4);
    }

    public final HttpParams getApplicationParams() {
        return this.a;
    }

    public final HttpParams getClientParams() {
        return this.b;
    }

    public final HttpParams getRequestParams() {
        return this.c;
    }

    public final HttpParams getOverrideParams() {
        return this.d;
    }

    public Object getParameter(String str) {
        Args.notNull(str, "Parameter name");
        Object obj = null;
        if (this.d != null) {
            obj = this.d.getParameter(str);
        }
        if (obj == null && this.c != null) {
            obj = this.c.getParameter(str);
        }
        if (obj == null && this.b != null) {
            obj = this.b.getParameter(str);
        }
        if (obj != null || this.a == null) {
            return obj;
        }
        return this.a.getParameter(str);
    }

    public HttpParams setParameter(String str, Object obj) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Setting parameters in a stack is not supported.");
    }

    public boolean removeParameter(String str) {
        throw new UnsupportedOperationException("Removing parameters in a stack is not supported.");
    }

    public HttpParams copy() {
        return this;
    }
}
