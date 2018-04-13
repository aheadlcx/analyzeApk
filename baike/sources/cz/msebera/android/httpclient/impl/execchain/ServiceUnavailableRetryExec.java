package cz.msebera.android.httpclient.impl.execchain;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.ServiceUnavailableRetryStrategy;
import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpExecutionAware;
import cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import cz.msebera.android.httpclient.client.protocol.HttpClientContext;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.io.InterruptedIOException;

@Immutable
public class ServiceUnavailableRetryExec implements ClientExecChain {
    private final ClientExecChain a;
    private final ServiceUnavailableRetryStrategy b;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    public ServiceUnavailableRetryExec(ClientExecChain clientExecChain, ServiceUnavailableRetryStrategy serviceUnavailableRetryStrategy) {
        Args.notNull(clientExecChain, "HTTP request executor");
        Args.notNull(serviceUnavailableRetryStrategy, "Retry strategy");
        this.a = clientExecChain;
        this.b = serviceUnavailableRetryStrategy;
    }

    public CloseableHttpResponse execute(HttpRoute httpRoute, HttpRequestWrapper httpRequestWrapper, HttpClientContext httpClientContext, HttpExecutionAware httpExecutionAware) throws IOException, HttpException {
        Header[] allHeaders = httpRequestWrapper.getAllHeaders();
        int i = 1;
        while (true) {
            CloseableHttpResponse execute = this.a.execute(httpRoute, httpRequestWrapper, httpClientContext, httpExecutionAware);
            if (!this.b.retryRequest(execute, i, httpClientContext)) {
                return execute;
            }
            execute.close();
            long retryInterval = this.b.getRetryInterval();
            if (retryInterval > 0) {
                try {
                    this.log.trace("Wait for " + retryInterval);
                    Thread.sleep(retryInterval);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new InterruptedIOException();
                } catch (RuntimeException e2) {
                    execute.close();
                    throw e2;
                }
            }
            httpRequestWrapper.setHeaders(allHeaders);
            i++;
        }
    }
}
