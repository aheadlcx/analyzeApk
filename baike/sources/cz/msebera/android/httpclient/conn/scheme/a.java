package cz.msebera.android.httpclient.conn.scheme;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

@Deprecated
class a extends e implements LayeredSocketFactory {
    private final LayeredSchemeSocketFactory a;

    a(LayeredSchemeSocketFactory layeredSchemeSocketFactory) {
        super(layeredSchemeSocketFactory);
        this.a = layeredSchemeSocketFactory;
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException, UnknownHostException {
        return this.a.createLayeredSocket(socket, str, i, z);
    }
}
