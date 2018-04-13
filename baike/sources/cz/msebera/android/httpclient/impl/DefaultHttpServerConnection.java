package cz.msebera.android.httpclient.impl;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.params.CoreConnectionPNames;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.net.Socket;

@NotThreadSafe
@Deprecated
public class DefaultHttpServerConnection extends SocketHttpServerConnection {
    public void bind(Socket socket, HttpParams httpParams) throws IOException {
        boolean z = true;
        Args.notNull(socket, "Socket");
        Args.notNull(httpParams, "HTTP parameters");
        g();
        socket.setTcpNoDelay(httpParams.getBooleanParameter(CoreConnectionPNames.TCP_NODELAY, true));
        socket.setSoTimeout(httpParams.getIntParameter(CoreConnectionPNames.SO_TIMEOUT, 0));
        socket.setKeepAlive(httpParams.getBooleanParameter(CoreConnectionPNames.SO_KEEPALIVE, false));
        int intParameter = httpParams.getIntParameter(CoreConnectionPNames.SO_LINGER, -1);
        if (intParameter >= 0) {
            socket.setSoLinger(intParameter > 0, intParameter);
        }
        if (intParameter >= 0) {
            if (intParameter <= 0) {
                z = false;
            }
            socket.setSoLinger(z, intParameter);
        }
        super.bind(socket, httpParams);
    }
}
