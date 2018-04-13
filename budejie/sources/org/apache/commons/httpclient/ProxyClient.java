package org.apache.commons.httpclient;

import java.io.IOException;
import java.net.Socket;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpParams;

public class ProxyClient {
    private HostConfiguration hostConfiguration;
    private HttpClientParams params;
    private HttpState state;

    /* renamed from: org.apache.commons.httpclient.ProxyClient$1 */
    class AnonymousClass1 {
    }

    public static class ConnectResponse {
        private ConnectMethod connectMethod;
        private Socket socket;

        ConnectResponse(AnonymousClass1 anonymousClass1) {
            this();
        }

        static void access$100(ConnectResponse connectResponse, ConnectMethod connectMethod) {
            connectResponse.setConnectMethod(connectMethod);
        }

        static void access$200(ConnectResponse connectResponse, Socket socket) {
            connectResponse.setSocket(socket);
        }

        private ConnectResponse() {
        }

        public ConnectMethod getConnectMethod() {
            return this.connectMethod;
        }

        private void setConnectMethod(ConnectMethod connectMethod) {
            this.connectMethod = connectMethod;
        }

        public Socket getSocket() {
            return this.socket;
        }

        private void setSocket(Socket socket) {
            this.socket = socket;
        }
    }

    static class DummyConnectionManager implements HttpConnectionManager {
        private HttpParams connectionParams;
        private HttpConnection httpConnection;

        DummyConnectionManager() {
        }

        public void closeIdleConnections(long j) {
        }

        public HttpConnection getConnection() {
            return this.httpConnection;
        }

        public void setConnectionParams(HttpParams httpParams) {
            this.connectionParams = httpParams;
        }

        public HttpConnection getConnectionWithTimeout(HostConfiguration hostConfiguration, long j) {
            this.httpConnection = new HttpConnection(hostConfiguration);
            this.httpConnection.setHttpConnectionManager(this);
            this.httpConnection.getParams().setDefaults(this.connectionParams);
            return this.httpConnection;
        }

        public HttpConnection getConnection(HostConfiguration hostConfiguration, long j) throws HttpException {
            return getConnectionWithTimeout(hostConfiguration, j);
        }

        public HttpConnection getConnection(HostConfiguration hostConfiguration) {
            return getConnectionWithTimeout(hostConfiguration, -1);
        }

        public void releaseConnection(HttpConnection httpConnection) {
        }

        public HttpConnectionManagerParams getParams() {
            return null;
        }

        public void setParams(HttpConnectionManagerParams httpConnectionManagerParams) {
        }
    }

    public ProxyClient() {
        this(new HttpClientParams());
    }

    public ProxyClient(HttpClientParams httpClientParams) {
        this.state = new HttpState();
        this.params = null;
        this.hostConfiguration = new HostConfiguration();
        if (httpClientParams == null) {
            throw new IllegalArgumentException("Params may not be null");
        }
        this.params = httpClientParams;
    }

    public synchronized HttpState getState() {
        return this.state;
    }

    public synchronized void setState(HttpState httpState) {
        this.state = httpState;
    }

    public synchronized HostConfiguration getHostConfiguration() {
        return this.hostConfiguration;
    }

    public synchronized void setHostConfiguration(HostConfiguration hostConfiguration) {
        this.hostConfiguration = hostConfiguration;
    }

    public synchronized HttpClientParams getParams() {
        return this.params;
    }

    public synchronized void setParams(HttpClientParams httpClientParams) {
        if (httpClientParams == null) {
            throw new IllegalArgumentException("Parameters may not be null");
        }
        this.params = httpClientParams;
    }

    public ConnectResponse connect() throws IOException, HttpException {
        if (getHostConfiguration().getProxyHost() == null) {
            throw new IllegalStateException("proxy host must be configured");
        } else if (getHostConfiguration().getHost() == null) {
            throw new IllegalStateException("destination host must be configured");
        } else {
            HttpMethodBase connectMethod = new ConnectMethod();
            connectMethod.getParams().setDefaults(getParams());
            DummyConnectionManager dummyConnectionManager = new DummyConnectionManager();
            dummyConnectionManager.setConnectionParams(getParams());
            new HttpMethodDirector(dummyConnectionManager, getHostConfiguration(), getParams(), getState()).executeMethod(connectMethod);
            ConnectResponse connectResponse = new ConnectResponse(null);
            ConnectResponse.access$100(connectResponse, connectMethod);
            if (connectMethod.getStatusCode() == 200) {
                ConnectResponse.access$200(connectResponse, dummyConnectionManager.getConnection().getSocket());
            } else {
                dummyConnectionManager.getConnection().close();
            }
            return connectResponse;
        }
    }
}
