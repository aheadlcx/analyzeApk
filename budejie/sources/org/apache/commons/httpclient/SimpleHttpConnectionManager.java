package org.apache.commons.httpclient;

import com.facebook.common.time.Clock;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SimpleHttpConnectionManager implements HttpConnectionManager {
    private static final Log LOG;
    private static final String MISUSE_MESSAGE = "SimpleHttpConnectionManager being used incorrectly.  Be sure that HttpMethod.releaseConnection() is always called and that only one thread and/or method is using this connection manager at a time.";
    static Class class$org$apache$commons$httpclient$SimpleHttpConnectionManager;
    protected HttpConnection httpConnection;
    private long idleStartTime = Clock.MAX_TIME;
    private volatile boolean inUse = false;
    private HttpConnectionManagerParams params = new HttpConnectionManagerParams();

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$SimpleHttpConnectionManager == null) {
            class$ = class$("org.apache.commons.httpclient.SimpleHttpConnectionManager");
            class$org$apache$commons$httpclient$SimpleHttpConnectionManager = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$SimpleHttpConnectionManager;
        }
        LOG = LogFactory.getLog(class$);
    }

    static void finishLastResponse(HttpConnection httpConnection) {
        InputStream lastResponseInputStream = httpConnection.getLastResponseInputStream();
        if (lastResponseInputStream != null) {
            httpConnection.setLastResponseInputStream(null);
            try {
                lastResponseInputStream.close();
            } catch (IOException e) {
                httpConnection.close();
            }
        }
    }

    public HttpConnection getConnection(HostConfiguration hostConfiguration) {
        return getConnection(hostConfiguration, 0);
    }

    public boolean isConnectionStaleCheckingEnabled() {
        return this.params.isStaleCheckingEnabled();
    }

    public void setConnectionStaleCheckingEnabled(boolean z) {
        this.params.setStaleCheckingEnabled(z);
    }

    public HttpConnection getConnectionWithTimeout(HostConfiguration hostConfiguration, long j) {
        if (this.httpConnection == null) {
            this.httpConnection = new HttpConnection(hostConfiguration);
            this.httpConnection.setHttpConnectionManager(this);
            this.httpConnection.getParams().setDefaults(this.params);
        } else if (hostConfiguration.hostEquals(this.httpConnection) && hostConfiguration.proxyEquals(this.httpConnection)) {
            finishLastResponse(this.httpConnection);
        } else {
            if (this.httpConnection.isOpen()) {
                this.httpConnection.close();
            }
            this.httpConnection.setHost(hostConfiguration.getHost());
            this.httpConnection.setPort(hostConfiguration.getPort());
            this.httpConnection.setProtocol(hostConfiguration.getProtocol());
            this.httpConnection.setLocalAddress(hostConfiguration.getLocalAddress());
            this.httpConnection.setProxyHost(hostConfiguration.getProxyHost());
            this.httpConnection.setProxyPort(hostConfiguration.getProxyPort());
        }
        this.idleStartTime = Clock.MAX_TIME;
        if (this.inUse) {
            LOG.warn(MISUSE_MESSAGE);
        }
        this.inUse = true;
        return this.httpConnection;
    }

    public HttpConnection getConnection(HostConfiguration hostConfiguration, long j) {
        return getConnectionWithTimeout(hostConfiguration, j);
    }

    public void releaseConnection(HttpConnection httpConnection) {
        if (httpConnection != this.httpConnection) {
            throw new IllegalStateException("Unexpected release of an unknown connection.");
        }
        finishLastResponse(this.httpConnection);
        this.inUse = false;
        this.idleStartTime = System.currentTimeMillis();
    }

    public HttpConnectionManagerParams getParams() {
        return this.params;
    }

    public void setParams(HttpConnectionManagerParams httpConnectionManagerParams) {
        if (httpConnectionManagerParams == null) {
            throw new IllegalArgumentException("Parameters may not be null");
        }
        this.params = httpConnectionManagerParams;
    }

    public void closeIdleConnections(long j) {
        if (this.idleStartTime <= System.currentTimeMillis() - j) {
            this.httpConnection.close();
        }
    }
}
