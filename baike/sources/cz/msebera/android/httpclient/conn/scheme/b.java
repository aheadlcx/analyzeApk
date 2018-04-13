package cz.msebera.android.httpclient.conn.scheme;

import cz.msebera.android.httpclient.params.HttpParams;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

@Deprecated
class b extends d implements SchemeLayeredSocketFactory {
    private final LayeredSocketFactory a;

    b(LayeredSocketFactory layeredSocketFactory) {
        super(layeredSocketFactory);
        this.a = layeredSocketFactory;
    }

    public Socket createLayeredSocket(Socket socket, String str, int i, HttpParams httpParams) throws IOException, UnknownHostException {
        return this.a.createSocket(socket, str, i, true);
    }
}
