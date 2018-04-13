package org.apache.commons.httpclient;

import com.facebook.common.util.UriUtil;
import com.umeng.analytics.pro.dm;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.httpclient.util.ExceptionUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpConnection {
    private static final byte[] CRLF = new byte[]{dm.k, (byte) 10};
    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$HttpConnection;
    private String hostName;
    private HttpConnectionManager httpConnectionManager;
    private InputStream inputStream;
    protected boolean isOpen;
    private InputStream lastResponseInputStream;
    private InetAddress localAddress;
    private boolean locked;
    private OutputStream outputStream;
    private HttpConnectionParams params;
    private int portNumber;
    private Protocol protocolInUse;
    private String proxyHostName;
    private int proxyPortNumber;
    private Socket socket;
    private boolean tunnelEstablished;
    private boolean usingSecureSocket;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public HttpConnection(String str, int i) {
        this(null, -1, str, null, i, Protocol.getProtocol(UriUtil.HTTP_SCHEME));
    }

    public HttpConnection(String str, int i, Protocol protocol) {
        this(null, -1, str, null, i, protocol);
    }

    public HttpConnection(String str, String str2, int i, Protocol protocol) {
        this(null, -1, str, str2, i, protocol);
    }

    public HttpConnection(String str, int i, String str2, int i2) {
        this(str, i, str2, null, i2, Protocol.getProtocol(UriUtil.HTTP_SCHEME));
    }

    public HttpConnection(HostConfiguration hostConfiguration) {
        this(hostConfiguration.getProxyHost(), hostConfiguration.getProxyPort(), hostConfiguration.getHost(), hostConfiguration.getPort(), hostConfiguration.getProtocol());
        this.localAddress = hostConfiguration.getLocalAddress();
    }

    public HttpConnection(String str, int i, String str2, String str3, int i2, Protocol protocol) {
        this(str, i, str2, i2, protocol);
    }

    public HttpConnection(String str, int i, String str2, int i2, Protocol protocol) {
        this.hostName = null;
        this.portNumber = -1;
        this.proxyHostName = null;
        this.proxyPortNumber = -1;
        this.socket = null;
        this.inputStream = null;
        this.outputStream = null;
        this.lastResponseInputStream = null;
        this.isOpen = false;
        this.params = new HttpConnectionParams();
        this.locked = false;
        this.usingSecureSocket = false;
        this.tunnelEstablished = false;
        if (str2 == null) {
            throw new IllegalArgumentException("host parameter is null");
        } else if (protocol == null) {
            throw new IllegalArgumentException("protocol is null");
        } else {
            this.proxyHostName = str;
            this.proxyPortNumber = i;
            this.hostName = str2;
            this.portNumber = protocol.resolvePort(i2);
            this.protocolInUse = protocol;
        }
    }

    protected Socket getSocket() {
        return this.socket;
    }

    public String getHost() {
        return this.hostName;
    }

    public void setHost(String str) throws IllegalStateException {
        if (str == null) {
            throw new IllegalArgumentException("host parameter is null");
        }
        assertNotOpen();
        this.hostName = str;
    }

    public String getVirtualHost() {
        return this.hostName;
    }

    public void setVirtualHost(String str) throws IllegalStateException {
        assertNotOpen();
    }

    public int getPort() {
        if (this.portNumber < 0) {
            return isSecure() ? 443 : 80;
        } else {
            return this.portNumber;
        }
    }

    public void setPort(int i) throws IllegalStateException {
        assertNotOpen();
        this.portNumber = i;
    }

    public String getProxyHost() {
        return this.proxyHostName;
    }

    public void setProxyHost(String str) throws IllegalStateException {
        assertNotOpen();
        this.proxyHostName = str;
    }

    public int getProxyPort() {
        return this.proxyPortNumber;
    }

    public void setProxyPort(int i) throws IllegalStateException {
        assertNotOpen();
        this.proxyPortNumber = i;
    }

    public boolean isSecure() {
        return this.protocolInUse.isSecure();
    }

    public Protocol getProtocol() {
        return this.protocolInUse;
    }

    public void setProtocol(Protocol protocol) {
        assertNotOpen();
        if (protocol == null) {
            throw new IllegalArgumentException("protocol is null");
        }
        this.protocolInUse = protocol;
    }

    public InetAddress getLocalAddress() {
        return this.localAddress;
    }

    public void setLocalAddress(InetAddress inetAddress) {
        assertNotOpen();
        this.localAddress = inetAddress;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public boolean closeIfStale() throws IOException {
        if (!this.isOpen || !isStale()) {
            return false;
        }
        LOG.debug("Connection is stale, closing...");
        close();
        return true;
    }

    public boolean isStaleCheckingEnabled() {
        return this.params.isStaleCheckingEnabled();
    }

    public void setStaleCheckingEnabled(boolean z) {
        this.params.setStaleCheckingEnabled(z);
    }

    protected boolean isStale() throws IOException {
        if (!this.isOpen) {
            return true;
        }
        boolean z = false;
        try {
            if (this.inputStream.available() > 0) {
                return false;
            }
            this.socket.setSoTimeout(1);
            this.inputStream.mark(1);
            if (this.inputStream.read() == -1) {
                z = true;
            } else {
                this.inputStream.reset();
            }
            this.socket.setSoTimeout(this.params.getSoTimeout());
            return z;
        } catch (InterruptedIOException e) {
            if (ExceptionUtil.isSocketTimeoutException(e)) {
                return z;
            }
            throw e;
        } catch (Throwable e2) {
            LOG.debug("An error occurred while reading from the socket, is appears to be stale", e2);
            return true;
        } catch (Throwable th) {
            this.socket.setSoTimeout(this.params.getSoTimeout());
        }
    }

    public boolean isProxied() {
        return this.proxyHostName != null && this.proxyPortNumber > 0;
    }

    public void setLastResponseInputStream(InputStream inputStream) {
        this.lastResponseInputStream = inputStream;
    }

    public InputStream getLastResponseInputStream() {
        return this.lastResponseInputStream;
    }

    public HttpConnectionParams getParams() {
        return this.params;
    }

    public void setParams(HttpConnectionParams httpConnectionParams) {
        if (httpConnectionParams == null) {
            throw new IllegalArgumentException("Parameters may not be null");
        }
        this.params = httpConnectionParams;
    }

    public void setSoTimeout(int i) throws SocketException, IllegalStateException {
        this.params.setSoTimeout(i);
        if (this.socket != null) {
            this.socket.setSoTimeout(i);
        }
    }

    public void setSocketTimeout(int i) throws SocketException, IllegalStateException {
        assertOpen();
        if (this.socket != null) {
            this.socket.setSoTimeout(i);
        }
    }

    public int getSoTimeout() throws SocketException {
        return this.params.getSoTimeout();
    }

    public void setConnectionTimeout(int i) {
        this.params.setConnectionTimeout(i);
    }

    public void open() throws IOException {
        LOG.trace("enter HttpConnection.open()");
        String str = this.proxyHostName == null ? this.hostName : this.proxyHostName;
        int i = this.proxyHostName == null ? this.portNumber : this.proxyPortNumber;
        assertNotOpen();
        if (LOG.isDebugEnabled()) {
            LOG.debug(new StringBuffer().append("Open connection to ").append(str).append(":").append(i).toString());
        }
        try {
            if (this.socket == null) {
                boolean z;
                ProtocolSocketFactory socketFactory;
                if (!isSecure() || isProxied()) {
                    z = false;
                } else {
                    z = true;
                }
                this.usingSecureSocket = z;
                if (isSecure() && isProxied()) {
                    socketFactory = Protocol.getProtocol(UriUtil.HTTP_SCHEME).getSocketFactory();
                } else {
                    socketFactory = this.protocolInUse.getSocketFactory();
                }
                this.socket = socketFactory.createSocket(str, i, this.localAddress, 0, this.params);
            }
            this.socket.setTcpNoDelay(this.params.getTcpNoDelay());
            this.socket.setSoTimeout(this.params.getSoTimeout());
            int linger = this.params.getLinger();
            if (linger >= 0) {
                this.socket.setSoLinger(linger > 0, linger);
            }
            int sendBufferSize = this.params.getSendBufferSize();
            if (sendBufferSize >= 0) {
                this.socket.setSendBufferSize(sendBufferSize);
            }
            sendBufferSize = this.params.getReceiveBufferSize();
            if (sendBufferSize >= 0) {
                this.socket.setReceiveBufferSize(sendBufferSize);
            }
            sendBufferSize = this.socket.getSendBufferSize();
            if (sendBufferSize > 2048 || sendBufferSize <= 0) {
                linger = 2048;
            } else {
                linger = sendBufferSize;
            }
            sendBufferSize = this.socket.getReceiveBufferSize();
            if (sendBufferSize > 2048 || sendBufferSize <= 0) {
                sendBufferSize = 2048;
            }
            this.inputStream = new BufferedInputStream(this.socket.getInputStream(), sendBufferSize);
            this.outputStream = new BufferedOutputStream(this.socket.getOutputStream(), linger);
            this.isOpen = true;
        } catch (IOException e) {
            closeSocketAndStreams();
            throw e;
        }
    }

    public void tunnelCreated() throws IllegalStateException, IOException {
        int i = 2048;
        LOG.trace("enter HttpConnection.tunnelCreated()");
        if (!isSecure() || !isProxied()) {
            throw new IllegalStateException("Connection must be secure and proxied to use this feature");
        } else if (this.usingSecureSocket) {
            throw new IllegalStateException("Already using a secure socket");
        } else {
            if (LOG.isDebugEnabled()) {
                LOG.debug(new StringBuffer().append("Secure tunnel to ").append(this.hostName).append(":").append(this.portNumber).toString());
            }
            this.socket = ((SecureProtocolSocketFactory) this.protocolInUse.getSocketFactory()).createSocket(this.socket, this.hostName, this.portNumber, true);
            int sendBufferSize = this.params.getSendBufferSize();
            if (sendBufferSize >= 0) {
                this.socket.setSendBufferSize(sendBufferSize);
            }
            sendBufferSize = this.params.getReceiveBufferSize();
            if (sendBufferSize >= 0) {
                this.socket.setReceiveBufferSize(sendBufferSize);
            }
            sendBufferSize = this.socket.getSendBufferSize();
            if (sendBufferSize > 2048) {
                sendBufferSize = 2048;
            }
            int receiveBufferSize = this.socket.getReceiveBufferSize();
            if (receiveBufferSize <= 2048) {
                i = receiveBufferSize;
            }
            this.inputStream = new BufferedInputStream(this.socket.getInputStream(), i);
            this.outputStream = new BufferedOutputStream(this.socket.getOutputStream(), sendBufferSize);
            this.usingSecureSocket = true;
            this.tunnelEstablished = true;
        }
    }

    public boolean isTransparent() {
        return !isProxied() || this.tunnelEstablished;
    }

    public void flushRequestOutputStream() throws IOException {
        LOG.trace("enter HttpConnection.flushRequestOutputStream()");
        assertOpen();
        this.outputStream.flush();
    }

    public OutputStream getRequestOutputStream() throws IOException, IllegalStateException {
        LOG.trace("enter HttpConnection.getRequestOutputStream()");
        assertOpen();
        OutputStream outputStream = this.outputStream;
        if (Wire.CONTENT_WIRE.enabled()) {
            return new WireLogOutputStream(outputStream, Wire.CONTENT_WIRE);
        }
        return outputStream;
    }

    public InputStream getResponseInputStream() throws IOException, IllegalStateException {
        LOG.trace("enter HttpConnection.getResponseInputStream()");
        assertOpen();
        return this.inputStream;
    }

    public boolean isResponseAvailable() throws IOException {
        LOG.trace("enter HttpConnection.isResponseAvailable()");
        if (!this.isOpen || this.inputStream.available() <= 0) {
            return false;
        }
        return true;
    }

    public boolean isResponseAvailable(int i) throws IOException {
        boolean z = true;
        LOG.trace("enter HttpConnection.isResponseAvailable(int)");
        assertOpen();
        if (this.inputStream.available() > 0) {
            return true;
        }
        try {
            this.socket.setSoTimeout(i);
            this.inputStream.mark(1);
            if (this.inputStream.read() != -1) {
                this.inputStream.reset();
                LOG.debug("Input data available");
            } else {
                LOG.debug("Input data not available");
                z = false;
            }
            try {
                this.socket.setSoTimeout(this.params.getSoTimeout());
                return z;
            } catch (Throwable e) {
                LOG.debug("An error ocurred while resetting soTimeout, we will assume that no response is available.", e);
                return false;
            }
        } catch (InterruptedIOException e2) {
            if (ExceptionUtil.isSocketTimeoutException(e2)) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug(new StringBuffer().append("Input data not available after ").append(i).append(" ms").toString());
                }
                try {
                    this.socket.setSoTimeout(this.params.getSoTimeout());
                } catch (Throwable e3) {
                    LOG.debug("An error ocurred while resetting soTimeout, we will assume that no response is available.", e3);
                }
                return false;
            }
            throw e2;
        } catch (Throwable e32) {
            try {
                this.socket.setSoTimeout(this.params.getSoTimeout());
            } catch (Throwable e4) {
                LOG.debug("An error ocurred while resetting soTimeout, we will assume that no response is available.", e4);
            }
            throw e32;
        }
    }

    public void write(byte[] bArr) throws IOException, IllegalStateException {
        LOG.trace("enter HttpConnection.write(byte[])");
        write(bArr, 0, bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException, IllegalStateException {
        LOG.trace("enter HttpConnection.write(byte[], int, int)");
        if (i < 0) {
            throw new IllegalArgumentException("Array offset may not be negative");
        } else if (i2 < 0) {
            throw new IllegalArgumentException("Array length may not be negative");
        } else if (i + i2 > bArr.length) {
            throw new IllegalArgumentException("Given offset and length exceed the array length");
        } else {
            assertOpen();
            this.outputStream.write(bArr, i, i2);
        }
    }

    public void writeLine(byte[] bArr) throws IOException, IllegalStateException {
        LOG.trace("enter HttpConnection.writeLine(byte[])");
        write(bArr);
        writeLine();
    }

    public void writeLine() throws IOException, IllegalStateException {
        LOG.trace("enter HttpConnection.writeLine()");
        write(CRLF);
    }

    public void print(String str) throws IOException, IllegalStateException {
        LOG.trace("enter HttpConnection.print(String)");
        write(EncodingUtil.getBytes(str, "ISO-8859-1"));
    }

    public void print(String str, String str2) throws IOException, IllegalStateException {
        LOG.trace("enter HttpConnection.print(String)");
        write(EncodingUtil.getBytes(str, str2));
    }

    public void printLine(String str) throws IOException, IllegalStateException {
        LOG.trace("enter HttpConnection.printLine(String)");
        writeLine(EncodingUtil.getBytes(str, "ISO-8859-1"));
    }

    public void printLine(String str, String str2) throws IOException, IllegalStateException {
        LOG.trace("enter HttpConnection.printLine(String)");
        writeLine(EncodingUtil.getBytes(str, str2));
    }

    public void printLine() throws IOException, IllegalStateException {
        LOG.trace("enter HttpConnection.printLine()");
        writeLine();
    }

    public String readLine() throws IOException, IllegalStateException {
        LOG.trace("enter HttpConnection.readLine()");
        assertOpen();
        return HttpParser.readLine(this.inputStream);
    }

    public String readLine(String str) throws IOException, IllegalStateException {
        LOG.trace("enter HttpConnection.readLine()");
        assertOpen();
        return HttpParser.readLine(this.inputStream, str);
    }

    public void shutdownOutput() {
        LOG.trace("enter HttpConnection.shutdownOutput()");
        try {
            this.socket.getClass().getMethod("shutdownOutput", new Class[0]).invoke(this.socket, new Object[0]);
        } catch (Throwable e) {
            LOG.debug("Unexpected Exception caught", e);
        }
    }

    public void close() {
        LOG.trace("enter HttpConnection.close()");
        closeSocketAndStreams();
    }

    public HttpConnectionManager getHttpConnectionManager() {
        return this.httpConnectionManager;
    }

    public void setHttpConnectionManager(HttpConnectionManager httpConnectionManager) {
        this.httpConnectionManager = httpConnectionManager;
    }

    public void releaseConnection() {
        LOG.trace("enter HttpConnection.releaseConnection()");
        if (this.locked) {
            LOG.debug("Connection is locked.  Call to releaseConnection() ignored.");
        } else if (this.httpConnectionManager != null) {
            LOG.debug("Releasing connection back to connection manager.");
            this.httpConnectionManager.releaseConnection(this);
        } else {
            LOG.warn("HttpConnectionManager is null.  Connection cannot be released.");
        }
    }

    protected boolean isLocked() {
        return this.locked;
    }

    protected void setLocked(boolean z) {
        this.locked = z;
    }

    protected void closeSocketAndStreams() {
        LOG.trace("enter HttpConnection.closeSockedAndStreams()");
        this.isOpen = false;
        this.lastResponseInputStream = null;
        if (this.outputStream != null) {
            OutputStream outputStream = this.outputStream;
            this.outputStream = null;
            try {
                outputStream.close();
            } catch (Throwable e) {
                LOG.debug("Exception caught when closing output", e);
            }
        }
        if (this.inputStream != null) {
            InputStream inputStream = this.inputStream;
            this.inputStream = null;
            try {
                inputStream.close();
            } catch (Throwable e2) {
                LOG.debug("Exception caught when closing input", e2);
            }
        }
        if (this.socket != null) {
            Socket socket = this.socket;
            this.socket = null;
            try {
                socket.close();
            } catch (Throwable e22) {
                LOG.debug("Exception caught when closing socket", e22);
            }
        }
        this.tunnelEstablished = false;
        this.usingSecureSocket = false;
    }

    protected void assertNotOpen() throws IllegalStateException {
        if (this.isOpen) {
            throw new IllegalStateException("Connection is open");
        }
    }

    protected void assertOpen() throws IllegalStateException {
        if (!this.isOpen) {
            throw new IllegalStateException("Connection is not open");
        }
    }

    public int getSendBufferSize() throws SocketException {
        if (this.socket == null) {
            return -1;
        }
        return this.socket.getSendBufferSize();
    }

    public void setSendBufferSize(int i) throws SocketException {
        this.params.setSendBufferSize(i);
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$HttpConnection == null) {
            class$ = class$("org.apache.commons.httpclient.HttpConnection");
            class$org$apache$commons$httpclient$HttpConnection = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$HttpConnection;
        }
        LOG = LogFactory.getLog(class$);
    }
}
