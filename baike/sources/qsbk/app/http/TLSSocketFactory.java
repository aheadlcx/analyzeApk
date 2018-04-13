package qsbk.app.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class TLSSocketFactory extends SSLSocketFactory {
    private final SSLSocketFactory a;
    private String[] b;

    public TLSSocketFactory() {
        this.a = HttpsURLConnection.getDefaultSSLSocketFactory();
    }

    public TLSSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.a = sSLSocketFactory;
    }

    public String[] getDefaultCipherSuites() {
        return this.a.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        return this.a.getDefaultCipherSuites();
    }

    private Socket a(Socket socket) {
        if (socket instanceof SSLSocket) {
            socket = (SSLSocket) socket;
            if (this.b == null) {
                String[] supportedProtocols = socket.getSupportedProtocols();
                List arrayList = new ArrayList();
                for (String str : supportedProtocols) {
                    if (!str.toLowerCase().contains("ssl")) {
                        arrayList.add(str);
                    }
                }
                if (arrayList.size() > 0) {
                    this.b = (String[]) arrayList.toArray(new String[arrayList.size()]);
                }
            }
            if (this.b != null) {
                socket.setEnabledProtocols(this.b);
            }
        }
        return socket;
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        return a(this.a.createSocket(socket, str, i, z));
    }

    public Socket createSocket(String str, int i) throws IOException, UnknownHostException {
        return a(this.a.createSocket(str, i));
    }

    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException, UnknownHostException {
        return a(this.a.createSocket(str, i, inetAddress, i2));
    }

    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return a(this.a.createSocket(inetAddress, i));
    }

    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return a(this.a.createSocket(inetAddress, i, inetAddress2, i2));
    }
}
