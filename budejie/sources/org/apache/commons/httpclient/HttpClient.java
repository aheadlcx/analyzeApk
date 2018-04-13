package org.apache.commons.httpclient;

import java.io.IOException;
import java.security.Provider;
import java.security.Security;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpClient {
    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$HttpClient;
    private HostConfiguration hostConfiguration;
    private HttpConnectionManager httpConnectionManager;
    private HttpClientParams params;
    private HttpState state;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$HttpClient == null) {
            class$ = class$("org.apache.commons.httpclient.HttpClient");
            class$org$apache$commons$httpclient$HttpClient = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$HttpClient;
        }
        LOG = LogFactory.getLog(class$);
        if (LOG.isDebugEnabled()) {
            try {
                LOG.debug(new StringBuffer().append("Java version: ").append(System.getProperty("java.version")).toString());
                LOG.debug(new StringBuffer().append("Java vendor: ").append(System.getProperty("java.vendor")).toString());
                LOG.debug(new StringBuffer().append("Java class path: ").append(System.getProperty("java.class.path")).toString());
                LOG.debug(new StringBuffer().append("Operating system name: ").append(System.getProperty("os.name")).toString());
                LOG.debug(new StringBuffer().append("Operating system architecture: ").append(System.getProperty("os.arch")).toString());
                LOG.debug(new StringBuffer().append("Operating system version: ").append(System.getProperty("os.version")).toString());
                Provider[] providers = Security.getProviders();
                for (Provider provider : providers) {
                    LOG.debug(new StringBuffer().append(provider.getName()).append(" ").append(provider.getVersion()).append(": ").append(provider.getInfo()).toString());
                }
            } catch (SecurityException e) {
            }
        }
    }

    public HttpClient() {
        this(new HttpClientParams());
    }

    public HttpClient(HttpClientParams httpClientParams) {
        this.state = new HttpState();
        this.params = null;
        this.hostConfiguration = new HostConfiguration();
        if (httpClientParams == null) {
            throw new IllegalArgumentException("Params may not be null");
        }
        this.params = httpClientParams;
        this.httpConnectionManager = null;
        Class connectionManagerClass = httpClientParams.getConnectionManagerClass();
        if (connectionManagerClass != null) {
            try {
                this.httpConnectionManager = (HttpConnectionManager) connectionManagerClass.newInstance();
            } catch (Throwable e) {
                LOG.warn("Error instantiating connection manager class, defaulting to SimpleHttpConnectionManager", e);
            }
        }
        if (this.httpConnectionManager == null) {
            this.httpConnectionManager = new SimpleHttpConnectionManager();
        }
        if (this.httpConnectionManager != null) {
            this.httpConnectionManager.getParams().setDefaults(this.params);
        }
    }

    public HttpClient(HttpClientParams httpClientParams, HttpConnectionManager httpConnectionManager) {
        this.state = new HttpState();
        this.params = null;
        this.hostConfiguration = new HostConfiguration();
        if (httpConnectionManager == null) {
            throw new IllegalArgumentException("httpConnectionManager cannot be null");
        } else if (httpClientParams == null) {
            throw new IllegalArgumentException("Params may not be null");
        } else {
            this.params = httpClientParams;
            this.httpConnectionManager = httpConnectionManager;
            if (this.httpConnectionManager != null) {
                this.httpConnectionManager.getParams().setDefaults(this.params);
            }
        }
    }

    public HttpClient(HttpConnectionManager httpConnectionManager) {
        this(new HttpClientParams(), httpConnectionManager);
    }

    public synchronized HttpState getState() {
        return this.state;
    }

    public synchronized void setState(HttpState httpState) {
        this.state = httpState;
    }

    public synchronized void setStrictMode(boolean z) {
        if (z) {
            this.params.makeStrict();
        } else {
            this.params.makeLenient();
        }
    }

    public synchronized boolean isStrictMode() {
        return false;
    }

    public synchronized void setTimeout(int i) {
        this.params.setSoTimeout(i);
    }

    public synchronized void setHttpConnectionFactoryTimeout(long j) {
        this.params.setConnectionManagerTimeout(j);
    }

    public synchronized void setConnectionTimeout(int i) {
        this.httpConnectionManager.getParams().setConnectionTimeout(i);
    }

    public int executeMethod(HttpMethod httpMethod) throws IOException, HttpException {
        LOG.trace("enter HttpClient.executeMethod(HttpMethod)");
        return executeMethod(null, httpMethod, null);
    }

    public int executeMethod(HostConfiguration hostConfiguration, HttpMethod httpMethod) throws IOException, HttpException {
        LOG.trace("enter HttpClient.executeMethod(HostConfiguration,HttpMethod)");
        return executeMethod(hostConfiguration, httpMethod, null);
    }

    public int executeMethod(HostConfiguration hostConfiguration, HttpMethod httpMethod, HttpState httpState) throws IOException, HttpException {
        LOG.trace("enter HttpClient.executeMethod(HostConfiguration,HttpMethod,HttpState)");
        if (httpMethod == null) {
            throw new IllegalArgumentException("HttpMethod parameter may not be null");
        }
        HostConfiguration hostConfiguration2 = getHostConfiguration();
        if (hostConfiguration == null) {
            hostConfiguration = hostConfiguration2;
        }
        URI uri = httpMethod.getURI();
        if (hostConfiguration == hostConfiguration2 || uri.isAbsoluteURI()) {
            hostConfiguration2 = new HostConfiguration(hostConfiguration);
            if (uri.isAbsoluteURI()) {
                hostConfiguration2.setHost(uri);
            }
            hostConfiguration = hostConfiguration2;
        }
        HttpConnectionManager httpConnectionManager = getHttpConnectionManager();
        HttpClientParams httpClientParams = this.params;
        if (httpState == null) {
            httpState = getState();
        }
        new HttpMethodDirector(httpConnectionManager, hostConfiguration, httpClientParams, httpState).executeMethod(httpMethod);
        return httpMethod.getStatusCode();
    }

    public String getHost() {
        return this.hostConfiguration.getHost();
    }

    public int getPort() {
        return this.hostConfiguration.getPort();
    }

    public synchronized HostConfiguration getHostConfiguration() {
        return this.hostConfiguration;
    }

    public synchronized void setHostConfiguration(HostConfiguration hostConfiguration) {
        this.hostConfiguration = hostConfiguration;
    }

    public synchronized HttpConnectionManager getHttpConnectionManager() {
        return this.httpConnectionManager;
    }

    public synchronized void setHttpConnectionManager(HttpConnectionManager httpConnectionManager) {
        this.httpConnectionManager = httpConnectionManager;
        if (this.httpConnectionManager != null) {
            this.httpConnectionManager.getParams().setDefaults(this.params);
        }
    }

    public HttpClientParams getParams() {
        return this.params;
    }

    public void setParams(HttpClientParams httpClientParams) {
        if (httpClientParams == null) {
            throw new IllegalArgumentException("Parameters may not be null");
        }
        this.params = httpClientParams;
    }
}
