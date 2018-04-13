package okhttp3.internal.connection;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.SSLSocket;
import okhttp3.ConnectionSpec;
import okhttp3.internal.Internal;

public final class ConnectionSpecSelector {
    private final List<ConnectionSpec> a;
    private int b = 0;
    private boolean c;
    private boolean d;

    public ConnectionSpecSelector(List<ConnectionSpec> list) {
        this.a = list;
    }

    public ConnectionSpec configureSecureSocket(SSLSocket sSLSocket) throws IOException {
        ConnectionSpec connectionSpec;
        int i = this.b;
        int size = this.a.size();
        for (int i2 = i; i2 < size; i2++) {
            connectionSpec = (ConnectionSpec) this.a.get(i2);
            if (connectionSpec.isCompatible(sSLSocket)) {
                this.b = i2 + 1;
                break;
            }
        }
        connectionSpec = null;
        if (connectionSpec == null) {
            throw new UnknownServiceException("Unable to find acceptable protocols. isFallback=" + this.d + ", modes=" + this.a + ", supported protocols=" + Arrays.toString(sSLSocket.getEnabledProtocols()));
        }
        this.c = a(sSLSocket);
        Internal.instance.apply(connectionSpec, sSLSocket, this.d);
        return connectionSpec;
    }

    public boolean connectionFailed(IOException iOException) {
        this.d = true;
        if (!this.c || (iOException instanceof ProtocolException) || (iOException instanceof InterruptedIOException)) {
            return false;
        }
        if (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) {
            return false;
        }
        if ((iOException instanceof SSLHandshakeException) || (iOException instanceof SSLProtocolException)) {
            return true;
        }
        return false;
    }

    private boolean a(SSLSocket sSLSocket) {
        for (int i = this.b; i < this.a.size(); i++) {
            if (((ConnectionSpec) this.a.get(i)).isCompatible(sSLSocket)) {
                return true;
            }
        }
        return false;
    }
}
