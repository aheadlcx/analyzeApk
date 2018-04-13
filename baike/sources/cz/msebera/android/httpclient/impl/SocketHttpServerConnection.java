package cz.msebera.android.httpclient.impl;

import cz.msebera.android.httpclient.HttpInetConnection;
import cz.msebera.android.httpclient.impl.io.SocketInputBuffer;
import cz.msebera.android.httpclient.impl.io.SocketOutputBuffer;
import cz.msebera.android.httpclient.io.SessionInputBuffer;
import cz.msebera.android.httpclient.io.SessionOutputBuffer;
import cz.msebera.android.httpclient.params.CoreConnectionPNames;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;

@Deprecated
public class SocketHttpServerConnection extends AbstractHttpServerConnection implements HttpInetConnection {
    private volatile boolean a;
    private volatile Socket b = null;

    protected void g() {
        Asserts.check(!this.a, "Connection is already open");
    }

    protected void a() {
        Asserts.check(this.a, "Connection is not open");
    }

    protected SessionInputBuffer a(Socket socket, int i, HttpParams httpParams) throws IOException {
        return new SocketInputBuffer(socket, i, httpParams);
    }

    protected SessionOutputBuffer b(Socket socket, int i, HttpParams httpParams) throws IOException {
        return new SocketOutputBuffer(socket, i, httpParams);
    }

    protected void bind(Socket socket, HttpParams httpParams) throws IOException {
        Args.notNull(socket, "Socket");
        Args.notNull(httpParams, "HTTP parameters");
        this.b = socket;
        int intParameter = httpParams.getIntParameter(CoreConnectionPNames.SOCKET_BUFFER_SIZE, -1);
        a(a(socket, intParameter, httpParams), b(socket, intParameter, httpParams), httpParams);
        this.a = true;
    }

    public boolean isOpen() {
        return this.a;
    }

    public InetAddress getLocalAddress() {
        if (this.b != null) {
            return this.b.getLocalAddress();
        }
        return null;
    }

    public int getLocalPort() {
        if (this.b != null) {
            return this.b.getLocalPort();
        }
        return -1;
    }

    public InetAddress getRemoteAddress() {
        if (this.b != null) {
            return this.b.getInetAddress();
        }
        return null;
    }

    public int getRemotePort() {
        if (this.b != null) {
            return this.b.getPort();
        }
        return -1;
    }

    public void setSocketTimeout(int i) {
        a();
        if (this.b != null) {
            try {
                this.b.setSoTimeout(i);
            } catch (SocketException e) {
            }
        }
    }

    public int getSocketTimeout() {
        int i = -1;
        if (this.b != null) {
            try {
                i = this.b.getSoTimeout();
            } catch (SocketException e) {
            }
        }
        return i;
    }

    public void shutdown() throws IOException {
        this.a = false;
        Socket socket = this.b;
        if (socket != null) {
            socket.close();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() throws java.io.IOException {
        /*
        r2 = this;
        r1 = 0;
        r0 = r2.a;
        if (r0 != 0) goto L_0x0006;
    L_0x0005:
        return;
    L_0x0006:
        r2.a = r1;
        r2.a = r1;
        r1 = r2.b;
        r2.e();	 Catch:{ all -> 0x0019 }
        r1.shutdownOutput();	 Catch:{ IOException -> 0x001e, UnsupportedOperationException -> 0x0022 }
    L_0x0012:
        r1.shutdownInput();	 Catch:{ IOException -> 0x0020, UnsupportedOperationException -> 0x0022 }
    L_0x0015:
        r1.close();
        goto L_0x0005;
    L_0x0019:
        r0 = move-exception;
        r1.close();
        throw r0;
    L_0x001e:
        r0 = move-exception;
        goto L_0x0012;
    L_0x0020:
        r0 = move-exception;
        goto L_0x0015;
    L_0x0022:
        r0 = move-exception;
        goto L_0x0015;
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.httpclient.impl.SocketHttpServerConnection.close():void");
    }

    private static void a(StringBuilder stringBuilder, SocketAddress socketAddress) {
        if (socketAddress instanceof InetSocketAddress) {
            Object hostAddress;
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
            if (inetSocketAddress.getAddress() != null) {
                hostAddress = inetSocketAddress.getAddress().getHostAddress();
            } else {
                hostAddress = inetSocketAddress.getAddress();
            }
            stringBuilder.append(hostAddress).append(':').append(inetSocketAddress.getPort());
            return;
        }
        stringBuilder.append(socketAddress);
    }

    public String toString() {
        if (this.b == null) {
            return super.toString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        SocketAddress remoteSocketAddress = this.b.getRemoteSocketAddress();
        SocketAddress localSocketAddress = this.b.getLocalSocketAddress();
        if (!(remoteSocketAddress == null || localSocketAddress == null)) {
            a(stringBuilder, localSocketAddress);
            stringBuilder.append("<->");
            a(stringBuilder, remoteSocketAddress);
        }
        return stringBuilder.toString();
    }
}
