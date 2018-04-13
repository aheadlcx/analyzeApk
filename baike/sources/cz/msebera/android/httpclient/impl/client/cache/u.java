package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.util.Args;
import java.lang.reflect.Proxy;

@NotThreadSafe
class u {
    public static CloseableHttpResponse enhanceResponse(HttpResponse httpResponse) {
        Args.notNull(httpResponse, "HTTP response");
        if (httpResponse instanceof CloseableHttpResponse) {
            return (CloseableHttpResponse) httpResponse;
        }
        return (CloseableHttpResponse) Proxy.newProxyInstance(ab.class.getClassLoader(), new Class[]{CloseableHttpResponse.class}, new ab(httpResponse));
    }
}
