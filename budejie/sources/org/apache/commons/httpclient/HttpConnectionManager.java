package org.apache.commons.httpclient;

import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

public interface HttpConnectionManager {
    void closeIdleConnections(long j);

    HttpConnection getConnection(HostConfiguration hostConfiguration);

    HttpConnection getConnection(HostConfiguration hostConfiguration, long j) throws HttpException;

    HttpConnection getConnectionWithTimeout(HostConfiguration hostConfiguration, long j) throws ConnectionPoolTimeoutException;

    HttpConnectionManagerParams getParams();

    void releaseConnection(HttpConnection httpConnection);

    void setParams(HttpConnectionManagerParams httpConnectionManagerParams);
}
