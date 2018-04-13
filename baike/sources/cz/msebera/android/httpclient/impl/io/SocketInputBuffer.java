package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.io.EofSensor;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.net.Socket;

@NotThreadSafe
@Deprecated
public class SocketInputBuffer extends AbstractSessionInputBuffer implements EofSensor {
    private final Socket a;
    private boolean b = false;

    public SocketInputBuffer(Socket socket, int i, HttpParams httpParams) throws IOException {
        int receiveBufferSize;
        int i2 = 1024;
        Args.notNull(socket, "Socket");
        this.a = socket;
        if (i < 0) {
            receiveBufferSize = socket.getReceiveBufferSize();
        } else {
            receiveBufferSize = i;
        }
        if (receiveBufferSize >= 1024) {
            i2 = receiveBufferSize;
        }
        a(socket.getInputStream(), i2, httpParams);
    }

    protected int b() throws IOException {
        int b = super.b();
        this.b = b == -1;
        return b;
    }

    public boolean isDataAvailable(int i) throws IOException {
        boolean c = c();
        if (!c) {
            int soTimeout = this.a.getSoTimeout();
            try {
                this.a.setSoTimeout(i);
                b();
                c = c();
            } finally {
                this.a.setSoTimeout(soTimeout);
            }
        }
        return c;
    }

    public boolean isEof() {
        return this.b;
    }
}
