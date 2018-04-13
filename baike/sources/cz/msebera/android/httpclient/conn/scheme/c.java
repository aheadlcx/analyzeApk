package cz.msebera.android.httpclient.conn.scheme;

import cz.msebera.android.httpclient.conn.ConnectTimeoutException;
import cz.msebera.android.httpclient.params.HttpParams;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

@Deprecated
class c implements SchemeLayeredSocketFactory {
    private final LayeredSchemeSocketFactory a;

    c(LayeredSchemeSocketFactory layeredSchemeSocketFactory) {
        this.a = layeredSchemeSocketFactory;
    }

    public Socket createSocket(HttpParams httpParams) throws IOException {
        return this.a.createSocket(httpParams);
    }

    public Socket connectSocket(Socket socket, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, HttpParams httpParams) throws IOException, UnknownHostException, ConnectTimeoutException {
        return this.a.connectSocket(socket, inetSocketAddress, inetSocketAddress2, httpParams);
    }

    public boolean isSecure(Socket socket) throws IllegalArgumentException {
        return this.a.isSecure(socket);
    }

    public Socket createLayeredSocket(Socket socket, String str, int i, HttpParams httpParams) throws IOException, UnknownHostException {
        return this.a.createLayeredSocket(socket, str, i, true);
    }
}
