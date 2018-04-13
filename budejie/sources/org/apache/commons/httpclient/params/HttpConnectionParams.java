package org.apache.commons.httpclient.params;

public class HttpConnectionParams extends DefaultHttpParams {
    public static final String CONNECTION_TIMEOUT = "http.connection.timeout";
    public static final String SO_LINGER = "http.socket.linger";
    public static final String SO_RCVBUF = "http.socket.receivebuffer";
    public static final String SO_SNDBUF = "http.socket.sendbuffer";
    public static final String SO_TIMEOUT = "http.socket.timeout";
    public static final String STALE_CONNECTION_CHECK = "http.connection.stalecheck";
    public static final String TCP_NODELAY = "http.tcp.nodelay";

    public int getSoTimeout() {
        return getIntParameter("http.socket.timeout", 0);
    }

    public void setSoTimeout(int i) {
        setIntParameter("http.socket.timeout", i);
    }

    public void setTcpNoDelay(boolean z) {
        setBooleanParameter(TCP_NODELAY, z);
    }

    public boolean getTcpNoDelay() {
        return getBooleanParameter(TCP_NODELAY, true);
    }

    public int getSendBufferSize() {
        return getIntParameter(SO_SNDBUF, -1);
    }

    public void setSendBufferSize(int i) {
        setIntParameter(SO_SNDBUF, i);
    }

    public int getReceiveBufferSize() {
        return getIntParameter(SO_RCVBUF, -1);
    }

    public void setReceiveBufferSize(int i) {
        setIntParameter(SO_RCVBUF, i);
    }

    public int getLinger() {
        return getIntParameter(SO_LINGER, -1);
    }

    public void setLinger(int i) {
        setIntParameter(SO_LINGER, i);
    }

    public int getConnectionTimeout() {
        return getIntParameter(CONNECTION_TIMEOUT, 0);
    }

    public void setConnectionTimeout(int i) {
        setIntParameter(CONNECTION_TIMEOUT, i);
    }

    public boolean isStaleCheckingEnabled() {
        return getBooleanParameter(STALE_CONNECTION_CHECK, true);
    }

    public void setStaleCheckingEnabled(boolean z) {
        setBooleanParameter(STALE_CONNECTION_CHECK, z);
    }
}
