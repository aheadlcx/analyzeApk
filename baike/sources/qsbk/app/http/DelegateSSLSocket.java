package qsbk.app.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

public class DelegateSSLSocket extends SSLSocket {
    protected final SSLSocket a;

    public DelegateSSLSocket(SSLSocket sSLSocket) {
        this.a = sSLSocket;
    }

    public String[] getSupportedCipherSuites() {
        return this.a.getSupportedCipherSuites();
    }

    public String[] getEnabledCipherSuites() {
        return this.a.getEnabledCipherSuites();
    }

    public void setEnabledCipherSuites(String[] strArr) {
        this.a.setEnabledCipherSuites(strArr);
    }

    public String[] getSupportedProtocols() {
        return this.a.getSupportedProtocols();
    }

    public String[] getEnabledProtocols() {
        return this.a.getEnabledProtocols();
    }

    public void setEnabledProtocols(String[] strArr) {
        this.a.setEnabledProtocols(strArr);
    }

    public SSLSession getSession() {
        return this.a.getSession();
    }

    public void addHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
        this.a.addHandshakeCompletedListener(handshakeCompletedListener);
    }

    public void removeHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
        this.a.removeHandshakeCompletedListener(handshakeCompletedListener);
    }

    public void startHandshake() throws IOException {
        this.a.startHandshake();
    }

    public boolean getUseClientMode() {
        return this.a.getUseClientMode();
    }

    public void setUseClientMode(boolean z) {
        this.a.setUseClientMode(z);
    }

    public boolean getNeedClientAuth() {
        return this.a.getNeedClientAuth();
    }

    public void setNeedClientAuth(boolean z) {
        this.a.setNeedClientAuth(z);
    }

    public boolean getWantClientAuth() {
        return this.a.getWantClientAuth();
    }

    public void setWantClientAuth(boolean z) {
        this.a.setWantClientAuth(z);
    }

    public boolean getEnableSessionCreation() {
        return this.a.getEnableSessionCreation();
    }

    public void setEnableSessionCreation(boolean z) {
        this.a.setEnableSessionCreation(z);
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

    public SocketChannel getChannel() {
        return this.a.getChannel();
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

    public void setKeepAlive(boolean z) throws SocketException {
        this.a.setKeepAlive(z);
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

    public boolean getOOBInline() throws SocketException {
        return this.a.getOOBInline();
    }

    public void setOOBInline(boolean z) throws SocketException {
        this.a.setOOBInline(z);
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

    public synchronized void setReceiveBufferSize(int i) throws SocketException {
        this.a.setReceiveBufferSize(i);
    }

    public SocketAddress getRemoteSocketAddress() {
        return this.a.getRemoteSocketAddress();
    }

    public boolean getReuseAddress() throws SocketException {
        return this.a.getReuseAddress();
    }

    public void setReuseAddress(boolean z) throws SocketException {
        this.a.setReuseAddress(z);
    }

    public synchronized int getSendBufferSize() throws SocketException {
        return this.a.getSendBufferSize();
    }

    public synchronized void setSendBufferSize(int i) throws SocketException {
        this.a.setSendBufferSize(i);
    }

    public int getSoLinger() throws SocketException {
        return this.a.getSoLinger();
    }

    public synchronized int getSoTimeout() throws SocketException {
        return this.a.getSoTimeout();
    }

    public synchronized void setSoTimeout(int i) throws SocketException {
        this.a.setSoTimeout(i);
    }

    public boolean getTcpNoDelay() throws SocketException {
        return this.a.getTcpNoDelay();
    }

    public void setTcpNoDelay(boolean z) throws SocketException {
        this.a.setTcpNoDelay(z);
    }

    public int getTrafficClass() throws SocketException {
        return this.a.getTrafficClass();
    }

    public void setTrafficClass(int i) throws SocketException {
        this.a.setTrafficClass(i);
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

    public void sendUrgentData(int i) throws IOException {
        this.a.sendUrgentData(i);
    }

    public void setPerformancePreferences(int i, int i2, int i3) {
        this.a.setPerformancePreferences(i, i2, i3);
    }

    public void setSoLinger(boolean z, int i) throws SocketException {
        this.a.setSoLinger(z, i);
    }

    public void setSSLParameters(SSLParameters sSLParameters) {
        this.a.setSSLParameters(sSLParameters);
    }

    public void shutdownInput() throws IOException {
        this.a.shutdownInput();
    }

    public void shutdownOutput() throws IOException {
        this.a.shutdownOutput();
    }

    public String toString() {
        return this.a.toString();
    }

    public boolean equals(Object obj) {
        return this.a.equals(obj);
    }
}
