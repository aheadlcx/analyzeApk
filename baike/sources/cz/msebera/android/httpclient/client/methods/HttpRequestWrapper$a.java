package cz.msebera.android.httpclient.client.methods;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.protocol.HTTP;

class HttpRequestWrapper$a extends HttpRequestWrapper implements HttpEntityEnclosingRequest {
    private HttpEntity c;

    HttpRequestWrapper$a(HttpEntityEnclosingRequest httpEntityEnclosingRequest, HttpHost httpHost) {
        super(httpEntityEnclosingRequest, httpHost, null);
        this.c = httpEntityEnclosingRequest.getEntity();
    }

    public HttpEntity getEntity() {
        return this.c;
    }

    public void setEntity(HttpEntity httpEntity) {
        this.c = httpEntity;
    }

    public boolean expectContinue() {
        Header firstHeader = getFirstHeader("Expect");
        return firstHeader != null && HTTP.EXPECT_CONTINUE.equalsIgnoreCase(firstHeader.getValue());
    }
}
