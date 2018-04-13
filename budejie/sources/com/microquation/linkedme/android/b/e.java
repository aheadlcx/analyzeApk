package com.microquation.linkedme.android.b;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class e extends SSLSocketFactory {
    private final SSLSocketFactory a;

    public class a extends SSLSocket {
        protected final SSLSocket a;
        final /* synthetic */ e b;

        a(e eVar, SSLSocket sSLSocket) {
            this.b = eVar;
            this.a = sSLSocket;
        }

        public void addHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
            this.a.addHandshakeCompletedListener(handshakeCompletedListener);
        }

        public void bind(SocketAddress socketAddress) throws IOException {
            this.a.bind(socketAddress);
        }

        public synchronized void close() throws IOException {
            this.a.close();
        }

        public void connect(SocketAddress socketAddress) throws IOException {
            this.a.connect(socketAddress);
        }

        public void connect(SocketAddress socketAddress, int i) throws IOException {
            this.a.connect(socketAddress, i);
        }

        public boolean equals(Object obj) {
            return this.a.equals(obj);
        }

        public SocketChannel getChannel() {
            return this.a.getChannel();
        }

        public boolean getEnableSessionCreation() {
            return this.a.getEnableSessionCreation();
        }

        public String[] getEnabledCipherSuites() {
            return this.a.getEnabledCipherSuites();
        }

        public String[] getEnabledProtocols() {
            return this.a.getEnabledProtocols();
        }

        public InetAddress getInetAddress() {
            return this.a.getInetAddress();
        }

        public InputStream getInputStream() throws IOException {
            return this.a.getInputStream();
        }

        public boolean getKeepAlive() throws SocketException {
            return this.a.getKeepAlive();
        }

        public InetAddress getLocalAddress() {
            return this.a.getLocalAddress();
        }

        public int getLocalPort() {
            return this.a.getLocalPort();
        }

        public SocketAddress getLocalSocketAddress() {
            return this.a.getLocalSocketAddress();
        }

        public boolean getNeedClientAuth() {
            return this.a.getNeedClientAuth();
        }

        public boolean getOOBInline() throws SocketException {
            return this.a.getOOBInline();
        }

        public OutputStream getOutputStream() throws IOException {
            return this.a.getOutputStream();
        }

        public int getPort() {
            return this.a.getPort();
        }

        public synchronized int getReceiveBufferSize() throws SocketException {
            return this.a.getReceiveBufferSize();
        }

        public SocketAddress getRemoteSocketAddress() {
            return this.a.getRemoteSocketAddress();
        }

        public boolean getReuseAddress() throws SocketException {
            return this.a.getReuseAddress();
        }

        public synchronized int getSendBufferSize() throws SocketException {
            return this.a.getSendBufferSize();
        }

        public SSLSession getSession() {
            return this.a.getSession();
        }

        public int getSoLinger() throws SocketException {
            return this.a.getSoLinger();
        }

        public synchronized int getSoTimeout() throws SocketException {
            return this.a.getSoTimeout();
        }

        public String[] getSupportedCipherSuites() {
            return this.a.getSupportedCipherSuites();
        }

        public String[] getSupportedProtocols() {
            return this.a.getSupportedProtocols();
        }

        public boolean getTcpNoDelay() throws SocketException {
            return this.a.getTcpNoDelay();
        }

        public int getTrafficClass() throws SocketException {
            return this.a.getTrafficClass();
        }

        public boolean getUseClientMode() {
            return this.a.getUseClientMode();
        }

        public boolean getWantClientAuth() {
            return this.a.getWantClientAuth();
        }

        public boolean isBound() {
            return this.a.isBound();
        }

        public boolean isClosed() {
            return this.a.isClosed();
        }

        public boolean isConnected() {
            return this.a.isConnected();
        }

        public boolean isInputShutdown() {
            return this.a.isInputShutdown();
        }

        public boolean isOutputShutdown() {
            return this.a.isOutputShutdown();
        }

        public void removeHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
            this.a.removeHandshakeCompletedListener(handshakeCompletedListener);
        }

        public void sendUrgentData(int i) throws IOException {
            this.a.sendUrgentData(i);
        }

        public void setEnableSessionCreation(boolean z) {
            this.a.setEnableSessionCreation(z);
        }

        public void setEnabledCipherSuites(String[] strArr) {
            this.a.setEnabledCipherSuites(strArr);
        }

        public void setEnabledProtocols(String[] strArr) {
            this.a.setEnabledProtocols(strArr);
        }

        public void setKeepAlive(boolean z) throws SocketException {
            this.a.setKeepAlive(z);
        }

        public void setNeedClientAuth(boolean z) {
            this.a.setNeedClientAuth(z);
        }

        public void setOOBInline(boolean z) throws SocketException {
            this.a.setOOBInline(z);
        }

        public void setPerformancePreferences(int i, int i2, int i3) {
            this.a.setPerformancePreferences(i, i2, i3);
        }

        public synchronized void setReceiveBufferSize(int i) throws SocketException {
            this.a.setReceiveBufferSize(i);
        }

        public void setReuseAddress(boolean z) throws SocketException {
            this.a.setReuseAddress(z);
        }

        public synchronized void setSendBufferSize(int i) throws SocketException {
            this.a.setSendBufferSize(i);
        }

        public void setSoLinger(boolean z, int i) throws SocketException {
            this.a.setSoLinger(z, i);
        }

        public synchronized void setSoTimeout(int i) throws SocketException {
            this.a.setSoTimeout(i);
        }

        public void setTcpNoDelay(boolean z) throws SocketException {
            this.a.setTcpNoDelay(z);
        }

        public void setTrafficClass(int i) throws SocketException {
            this.a.setTrafficClass(i);
        }

        public void setUseClientMode(boolean z) {
            this.a.setUseClientMode(z);
        }

        public void setWantClientAuth(boolean z) {
            this.a.setWantClientAuth(z);
        }

        public void shutdownInput() throws IOException {
            this.a.shutdownInput();
        }

        public void shutdownOutput() throws IOException {
            this.a.shutdownOutput();
        }

        public void startHandshake() throws IOException {
            this.a.startHandshake();
        }

        public String toString() {
            return this.a.toString();
        }
    }

    private class b extends a {
        final /* synthetic */ e c;

        private b(e eVar, SSLSocket sSLSocket) {
            this.c = eVar;
            super(eVar, sSLSocket);
        }

        public void setEnabledProtocols(String[] strArr) {
            if (strArr != null && strArr.length == 1 && "SSLv3".equals(strArr[0])) {
                List arrayList = new ArrayList(Arrays.asList(this.a.getEnabledProtocols()));
                if (arrayList.size() > 1) {
                    arrayList.remove("SSLv3");
                    com.microquation.linkedme.android.f.b.a("LinkedME", "Removed SSLv3 from enabled protocols");
                } else {
                    com.microquation.linkedme.android.f.b.a("LinkedME", "SSL stuck with protocol available for " + String.valueOf(arrayList));
                }
                strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
            }
            super.setEnabledProtocols(strArr);
        }
    }

    public e() {
        this.a = HttpsURLConnection.getDefaultSSLSocketFactory();
    }

    public e(SSLSocketFactory sSLSocketFactory) {
        this.a = sSLSocketFactory;
    }

    private Socket a(Socket socket) {
        return socket instanceof SSLSocket ? new b((SSLSocket) socket) : socket;
    }

    public Socket createSocket(String str, int i) throws IOException {
        return a(this.a.createSocket(str, i));
    }

    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        return a(this.a.createSocket(str, i, inetAddress, i2));
    }

    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return a(this.a.createSocket(inetAddress, i));
    }

    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return a(this.a.createSocket(inetAddress, i, inetAddress2, i2));
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        return a(this.a.createSocket(socket, str, i, z));
    }

    public String[] getDefaultCipherSuites() {
        return this.a.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        return this.a.getSupportedCipherSuites();
    }
}
