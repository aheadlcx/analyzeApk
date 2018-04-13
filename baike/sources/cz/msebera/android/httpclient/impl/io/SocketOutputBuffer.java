package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.net.Socket;

@NotThreadSafe
@Deprecated
public class SocketOutputBuffer extends AbstractSessionOutputBuffer {
    public SocketOutputBuffer(Socket socket, int i, HttpParams httpParams) throws IOException {
        int sendBufferSize;
        int i2 = 1024;
        Args.notNull(socket, "Socket");
        if (i < 0) {
            sendBufferSize = socket.getSendBufferSize();
        } else {
            sendBufferSize = i;
        }
        if (sendBufferSize >= 1024) {
            i2 = sendBufferSize;
        }
        a(socket.getOutputStream(), i2, httpParams);
    }
}
