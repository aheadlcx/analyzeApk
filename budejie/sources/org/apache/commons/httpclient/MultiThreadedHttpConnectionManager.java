package org.apache.commons.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.WeakHashMap;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.util.IdleConnectionHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MultiThreadedHttpConnectionManager implements HttpConnectionManager {
    private static WeakHashMap ALL_CONNECTION_MANAGERS = new WeakHashMap();
    public static final int DEFAULT_MAX_HOST_CONNECTIONS = 2;
    public static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 20;
    private static final Log LOG;
    private static final ReferenceQueue REFERENCE_QUEUE = new ReferenceQueue();
    private static ReferenceQueueThread REFERENCE_QUEUE_THREAD;
    private static final Map REFERENCE_TO_CONNECTION_SOURCE = new HashMap();
    static Class class$org$apache$commons$httpclient$MultiThreadedHttpConnectionManager;
    private ConnectionPool connectionPool = new ConnectionPool(this, null);
    private HttpConnectionManagerParams params = new HttpConnectionManagerParams();
    private boolean shutdown = false;

    /* renamed from: org.apache.commons.httpclient.MultiThreadedHttpConnectionManager$1 */
    class AnonymousClass1 {
    }

    private class ConnectionPool {
        private LinkedList freeConnections;
        private IdleConnectionHandler idleConnectionHandler;
        private final Map mapHosts;
        private int numConnections;
        private final MultiThreadedHttpConnectionManager this$0;
        private LinkedList waitingThreads;

        private ConnectionPool(MultiThreadedHttpConnectionManager multiThreadedHttpConnectionManager) {
            this.this$0 = multiThreadedHttpConnectionManager;
            this.freeConnections = new LinkedList();
            this.waitingThreads = new LinkedList();
            this.mapHosts = new HashMap();
            this.idleConnectionHandler = new IdleConnectionHandler();
            this.numConnections = 0;
        }

        ConnectionPool(MultiThreadedHttpConnectionManager multiThreadedHttpConnectionManager, AnonymousClass1 anonymousClass1) {
            this(multiThreadedHttpConnectionManager);
        }

        static int access$200(ConnectionPool connectionPool) {
            return connectionPool.numConnections;
        }

        static LinkedList access$300(ConnectionPool connectionPool) {
            return connectionPool.freeConnections;
        }

        static LinkedList access$500(ConnectionPool connectionPool) {
            return connectionPool.waitingThreads;
        }

        public synchronized void shutdown() {
            Iterator it = this.freeConnections.iterator();
            while (it.hasNext()) {
                HttpConnection httpConnection = (HttpConnection) it.next();
                it.remove();
                httpConnection.close();
            }
            MultiThreadedHttpConnectionManager.access$600(this);
            it = this.waitingThreads.iterator();
            while (it.hasNext()) {
                WaitingThread waitingThread = (WaitingThread) it.next();
                it.remove();
                waitingThread.thread.interrupt();
            }
            this.mapHosts.clear();
            this.idleConnectionHandler.removeAll();
        }

        public synchronized HttpConnection createConnection(HostConfiguration hostConfiguration) {
            HttpConnection httpConnectionWithReference;
            HostConnectionPool hostPool = getHostPool(hostConfiguration);
            if (MultiThreadedHttpConnectionManager.access$700().isDebugEnabled()) {
                MultiThreadedHttpConnectionManager.access$700().debug(new StringBuffer().append("Allocating new connection, hostConfig=").append(hostConfiguration).toString());
            }
            httpConnectionWithReference = new HttpConnectionWithReference(hostConfiguration);
            httpConnectionWithReference.getParams().setDefaults(MultiThreadedHttpConnectionManager.access$800(this.this$0));
            httpConnectionWithReference.setHttpConnectionManager(this.this$0);
            this.numConnections++;
            hostPool.numConnections++;
            MultiThreadedHttpConnectionManager.access$900(httpConnectionWithReference, hostConfiguration, this);
            return httpConnectionWithReference;
        }

        public synchronized void handleLostConnection(HostConfiguration hostConfiguration) {
            HostConnectionPool hostPool = getHostPool(hostConfiguration);
            hostPool.numConnections--;
            this.numConnections--;
            notifyWaitingThread(hostConfiguration);
        }

        public synchronized HostConnectionPool getHostPool(HostConfiguration hostConfiguration) {
            HostConnectionPool hostConnectionPool;
            MultiThreadedHttpConnectionManager.access$700().trace("enter HttpConnectionManager.ConnectionPool.getHostPool(HostConfiguration)");
            hostConnectionPool = (HostConnectionPool) this.mapHosts.get(hostConfiguration);
            if (hostConnectionPool == null) {
                hostConnectionPool = new HostConnectionPool(null);
                hostConnectionPool.hostConfiguration = hostConfiguration;
                this.mapHosts.put(hostConfiguration, hostConnectionPool);
            }
            return hostConnectionPool;
        }

        public synchronized HttpConnection getFreeConnection(HostConfiguration hostConfiguration) {
            HttpConnection httpConnection;
            httpConnection = null;
            HostConnectionPool hostPool = getHostPool(hostConfiguration);
            if (hostPool.freeConnections.size() > 0) {
                httpConnection = (HttpConnectionWithReference) hostPool.freeConnections.removeFirst();
                this.freeConnections.remove(httpConnection);
                MultiThreadedHttpConnectionManager.access$900(httpConnection, hostConfiguration, this);
                if (MultiThreadedHttpConnectionManager.access$700().isDebugEnabled()) {
                    MultiThreadedHttpConnectionManager.access$700().debug(new StringBuffer().append("Getting free connection, hostConfig=").append(hostConfiguration).toString());
                }
                this.idleConnectionHandler.remove(httpConnection);
            } else if (MultiThreadedHttpConnectionManager.access$700().isDebugEnabled()) {
                MultiThreadedHttpConnectionManager.access$700().debug(new StringBuffer().append("There were no free connections to get, hostConfig=").append(hostConfiguration).toString());
            }
            return httpConnection;
        }

        public synchronized void deleteClosedConnections() {
            Iterator it = this.freeConnections.iterator();
            while (it.hasNext()) {
                HttpConnection httpConnection = (HttpConnection) it.next();
                if (!httpConnection.isOpen()) {
                    it.remove();
                    deleteConnection(httpConnection);
                }
            }
        }

        public synchronized void closeIdleConnections(long j) {
            this.idleConnectionHandler.closeIdleConnections(j);
        }

        private synchronized void deleteConnection(HttpConnection httpConnection) {
            HostConfiguration access$1100 = MultiThreadedHttpConnectionManager.access$1100(this.this$0, httpConnection);
            if (MultiThreadedHttpConnectionManager.access$700().isDebugEnabled()) {
                MultiThreadedHttpConnectionManager.access$700().debug(new StringBuffer().append("Reclaiming connection, hostConfig=").append(access$1100).toString());
            }
            httpConnection.close();
            HostConnectionPool hostPool = getHostPool(access$1100);
            hostPool.freeConnections.remove(httpConnection);
            hostPool.numConnections--;
            this.numConnections--;
            this.idleConnectionHandler.remove(httpConnection);
        }

        public synchronized void deleteLeastUsedConnection() {
            HttpConnection httpConnection = (HttpConnection) this.freeConnections.removeFirst();
            if (httpConnection != null) {
                deleteConnection(httpConnection);
            } else if (MultiThreadedHttpConnectionManager.access$700().isDebugEnabled()) {
                MultiThreadedHttpConnectionManager.access$700().debug("Attempted to reclaim an unused connection but there were none.");
            }
        }

        public synchronized void notifyWaitingThread(HostConfiguration hostConfiguration) {
            notifyWaitingThread(getHostPool(hostConfiguration));
        }

        public synchronized void notifyWaitingThread(HostConnectionPool hostConnectionPool) {
            WaitingThread waitingThread = null;
            if (hostConnectionPool.waitingThreads.size() > 0) {
                if (MultiThreadedHttpConnectionManager.access$700().isDebugEnabled()) {
                    MultiThreadedHttpConnectionManager.access$700().debug(new StringBuffer().append("Notifying thread waiting on host pool, hostConfig=").append(hostConnectionPool.hostConfiguration).toString());
                }
                waitingThread = (WaitingThread) hostConnectionPool.waitingThreads.removeFirst();
                this.waitingThreads.remove(waitingThread);
            } else if (this.waitingThreads.size() > 0) {
                if (MultiThreadedHttpConnectionManager.access$700().isDebugEnabled()) {
                    MultiThreadedHttpConnectionManager.access$700().debug("No-one waiting on host pool, notifying next waiting thread.");
                }
                waitingThread = (WaitingThread) this.waitingThreads.removeFirst();
                waitingThread.hostConnectionPool.waitingThreads.remove(waitingThread);
            } else if (MultiThreadedHttpConnectionManager.access$700().isDebugEnabled()) {
                MultiThreadedHttpConnectionManager.access$700().debug("Notifying no-one, there are no waiting threads");
            }
            if (waitingThread != null) {
                waitingThread.thread.interrupt();
            }
        }

        public void freeConnection(HttpConnection httpConnection) {
            HostConfiguration access$1100 = MultiThreadedHttpConnectionManager.access$1100(this.this$0, httpConnection);
            if (MultiThreadedHttpConnectionManager.access$700().isDebugEnabled()) {
                MultiThreadedHttpConnectionManager.access$700().debug(new StringBuffer().append("Freeing connection, hostConfig=").append(access$1100).toString());
            }
            synchronized (this) {
                if (MultiThreadedHttpConnectionManager.access$1200(this.this$0)) {
                    httpConnection.close();
                    return;
                }
                HostConnectionPool hostPool = getHostPool(access$1100);
                hostPool.freeConnections.add(httpConnection);
                if (hostPool.numConnections == 0) {
                    MultiThreadedHttpConnectionManager.access$700().error(new StringBuffer().append("Host connection pool not found, hostConfig=").append(access$1100).toString());
                    hostPool.numConnections = 1;
                }
                this.freeConnections.add(httpConnection);
                MultiThreadedHttpConnectionManager.access$1300((HttpConnectionWithReference) httpConnection);
                if (this.numConnections == 0) {
                    MultiThreadedHttpConnectionManager.access$700().error(new StringBuffer().append("Host connection pool not found, hostConfig=").append(access$1100).toString());
                    this.numConnections = 1;
                }
                this.idleConnectionHandler.add(httpConnection);
                notifyWaitingThread(hostPool);
            }
        }
    }

    private static class ConnectionSource {
        public ConnectionPool connectionPool;
        public HostConfiguration hostConfiguration;

        private ConnectionSource() {
        }

        ConnectionSource(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    private static class HostConnectionPool {
        public LinkedList freeConnections;
        public HostConfiguration hostConfiguration;
        public int numConnections;
        public LinkedList waitingThreads;

        private HostConnectionPool() {
            this.freeConnections = new LinkedList();
            this.waitingThreads = new LinkedList();
            this.numConnections = 0;
        }

        HostConnectionPool(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    private static class HttpConnectionAdapter extends HttpConnection {
        private HttpConnection wrappedConnection;

        public HttpConnectionAdapter(HttpConnection httpConnection) {
            super(httpConnection.getHost(), httpConnection.getPort(), httpConnection.getProtocol());
            this.wrappedConnection = httpConnection;
        }

        protected boolean hasConnection() {
            return this.wrappedConnection != null;
        }

        HttpConnection getWrappedConnection() {
            return this.wrappedConnection;
        }

        public void close() {
            if (hasConnection()) {
                this.wrappedConnection.close();
            }
        }

        public InetAddress getLocalAddress() {
            if (hasConnection()) {
                return this.wrappedConnection.getLocalAddress();
            }
            return null;
        }

        public boolean isStaleCheckingEnabled() {
            if (hasConnection()) {
                return this.wrappedConnection.isStaleCheckingEnabled();
            }
            return false;
        }

        public void setLocalAddress(InetAddress inetAddress) {
            if (hasConnection()) {
                this.wrappedConnection.setLocalAddress(inetAddress);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }

        public void setStaleCheckingEnabled(boolean z) {
            if (hasConnection()) {
                this.wrappedConnection.setStaleCheckingEnabled(z);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }

        public String getHost() {
            if (hasConnection()) {
                return this.wrappedConnection.getHost();
            }
            return null;
        }

        public HttpConnectionManager getHttpConnectionManager() {
            if (hasConnection()) {
                return this.wrappedConnection.getHttpConnectionManager();
            }
            return null;
        }

        public InputStream getLastResponseInputStream() {
            if (hasConnection()) {
                return this.wrappedConnection.getLastResponseInputStream();
            }
            return null;
        }

        public int getPort() {
            if (hasConnection()) {
                return this.wrappedConnection.getPort();
            }
            return -1;
        }

        public Protocol getProtocol() {
            if (hasConnection()) {
                return this.wrappedConnection.getProtocol();
            }
            return null;
        }

        public String getProxyHost() {
            if (hasConnection()) {
                return this.wrappedConnection.getProxyHost();
            }
            return null;
        }

        public int getProxyPort() {
            if (hasConnection()) {
                return this.wrappedConnection.getProxyPort();
            }
            return -1;
        }

        public OutputStream getRequestOutputStream() throws IOException, IllegalStateException {
            if (hasConnection()) {
                return this.wrappedConnection.getRequestOutputStream();
            }
            return null;
        }

        public InputStream getResponseInputStream() throws IOException, IllegalStateException {
            if (hasConnection()) {
                return this.wrappedConnection.getResponseInputStream();
            }
            return null;
        }

        public boolean isOpen() {
            if (hasConnection()) {
                return this.wrappedConnection.isOpen();
            }
            return false;
        }

        public boolean closeIfStale() throws IOException {
            if (hasConnection()) {
                return this.wrappedConnection.closeIfStale();
            }
            return false;
        }

        public boolean isProxied() {
            if (hasConnection()) {
                return this.wrappedConnection.isProxied();
            }
            return false;
        }

        public boolean isResponseAvailable() throws IOException {
            if (hasConnection()) {
                return this.wrappedConnection.isResponseAvailable();
            }
            return false;
        }

        public boolean isResponseAvailable(int i) throws IOException {
            if (hasConnection()) {
                return this.wrappedConnection.isResponseAvailable(i);
            }
            return false;
        }

        public boolean isSecure() {
            if (hasConnection()) {
                return this.wrappedConnection.isSecure();
            }
            return false;
        }

        public boolean isTransparent() {
            if (hasConnection()) {
                return this.wrappedConnection.isTransparent();
            }
            return false;
        }

        public void open() throws IOException {
            if (hasConnection()) {
                this.wrappedConnection.open();
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }

        public void print(String str) throws IOException, IllegalStateException {
            if (hasConnection()) {
                this.wrappedConnection.print(str);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }

        public void printLine() throws IOException, IllegalStateException {
            if (hasConnection()) {
                this.wrappedConnection.printLine();
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }

        public void printLine(String str) throws IOException, IllegalStateException {
            if (hasConnection()) {
                this.wrappedConnection.printLine(str);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }

        public String readLine() throws IOException, IllegalStateException {
            if (hasConnection()) {
                return this.wrappedConnection.readLine();
            }
            throw new IllegalStateException("Connection has been released");
        }

        public String readLine(String str) throws IOException, IllegalStateException {
            if (hasConnection()) {
                return this.wrappedConnection.readLine(str);
            }
            throw new IllegalStateException("Connection has been released");
        }

        public void releaseConnection() {
            if (!isLocked() && hasConnection()) {
                HttpConnection httpConnection = this.wrappedConnection;
                this.wrappedConnection = null;
                httpConnection.releaseConnection();
            }
        }

        public void setConnectionTimeout(int i) {
            if (hasConnection()) {
                this.wrappedConnection.setConnectionTimeout(i);
            }
        }

        public void setHost(String str) throws IllegalStateException {
            if (hasConnection()) {
                this.wrappedConnection.setHost(str);
            }
        }

        public void setHttpConnectionManager(HttpConnectionManager httpConnectionManager) {
            if (hasConnection()) {
                this.wrappedConnection.setHttpConnectionManager(httpConnectionManager);
            }
        }

        public void setLastResponseInputStream(InputStream inputStream) {
            if (hasConnection()) {
                this.wrappedConnection.setLastResponseInputStream(inputStream);
            }
        }

        public void setPort(int i) throws IllegalStateException {
            if (hasConnection()) {
                this.wrappedConnection.setPort(i);
            }
        }

        public void setProtocol(Protocol protocol) {
            if (hasConnection()) {
                this.wrappedConnection.setProtocol(protocol);
            }
        }

        public void setProxyHost(String str) throws IllegalStateException {
            if (hasConnection()) {
                this.wrappedConnection.setProxyHost(str);
            }
        }

        public void setProxyPort(int i) throws IllegalStateException {
            if (hasConnection()) {
                this.wrappedConnection.setProxyPort(i);
            }
        }

        public void setSoTimeout(int i) throws SocketException, IllegalStateException {
            if (hasConnection()) {
                this.wrappedConnection.setSoTimeout(i);
            }
        }

        public void shutdownOutput() {
            if (hasConnection()) {
                this.wrappedConnection.shutdownOutput();
            }
        }

        public void tunnelCreated() throws IllegalStateException, IOException {
            if (hasConnection()) {
                this.wrappedConnection.tunnelCreated();
            }
        }

        public void write(byte[] bArr, int i, int i2) throws IOException, IllegalStateException {
            if (hasConnection()) {
                this.wrappedConnection.write(bArr, i, i2);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }

        public void write(byte[] bArr) throws IOException, IllegalStateException {
            if (hasConnection()) {
                this.wrappedConnection.write(bArr);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }

        public void writeLine() throws IOException, IllegalStateException {
            if (hasConnection()) {
                this.wrappedConnection.writeLine();
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }

        public void writeLine(byte[] bArr) throws IOException, IllegalStateException {
            if (hasConnection()) {
                this.wrappedConnection.writeLine(bArr);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }

        public void flushRequestOutputStream() throws IOException {
            if (hasConnection()) {
                this.wrappedConnection.flushRequestOutputStream();
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }

        public int getSoTimeout() throws SocketException {
            if (hasConnection()) {
                return this.wrappedConnection.getSoTimeout();
            }
            throw new IllegalStateException("Connection has been released");
        }

        public String getVirtualHost() {
            if (hasConnection()) {
                return this.wrappedConnection.getVirtualHost();
            }
            throw new IllegalStateException("Connection has been released");
        }

        public void setVirtualHost(String str) throws IllegalStateException {
            if (hasConnection()) {
                this.wrappedConnection.setVirtualHost(str);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }

        public int getSendBufferSize() throws SocketException {
            if (hasConnection()) {
                return this.wrappedConnection.getSendBufferSize();
            }
            throw new IllegalStateException("Connection has been released");
        }

        public void setSendBufferSize(int i) throws SocketException {
            if (hasConnection()) {
                this.wrappedConnection.setSendBufferSize(i);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }

        public HttpConnectionParams getParams() {
            if (hasConnection()) {
                return this.wrappedConnection.getParams();
            }
            throw new IllegalStateException("Connection has been released");
        }

        public void setParams(HttpConnectionParams httpConnectionParams) {
            if (hasConnection()) {
                this.wrappedConnection.setParams(httpConnectionParams);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }

        public void print(String str, String str2) throws IOException, IllegalStateException {
            if (hasConnection()) {
                this.wrappedConnection.print(str, str2);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }

        public void printLine(String str, String str2) throws IOException, IllegalStateException {
            if (hasConnection()) {
                this.wrappedConnection.printLine(str, str2);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }

        public void setSocketTimeout(int i) throws SocketException, IllegalStateException {
            if (hasConnection()) {
                this.wrappedConnection.setSocketTimeout(i);
                return;
            }
            throw new IllegalStateException("Connection has been released");
        }
    }

    private static class HttpConnectionWithReference extends HttpConnection {
        public WeakReference reference = new WeakReference(this, MultiThreadedHttpConnectionManager.access$1500());

        public HttpConnectionWithReference(HostConfiguration hostConfiguration) {
            super(hostConfiguration);
        }
    }

    private static class ReferenceQueueThread extends Thread {
        private boolean shutdown = false;

        public ReferenceQueueThread() {
            setDaemon(true);
            setName("MultiThreadedHttpConnectionManager cleanup");
        }

        public void shutdown() {
            this.shutdown = true;
        }

        private void handleReference(Reference reference) {
            synchronized (MultiThreadedHttpConnectionManager.access$1400()) {
                ConnectionSource connectionSource = (ConnectionSource) MultiThreadedHttpConnectionManager.access$1400().remove(reference);
            }
            if (connectionSource != null) {
                if (MultiThreadedHttpConnectionManager.access$700().isDebugEnabled()) {
                    MultiThreadedHttpConnectionManager.access$700().debug(new StringBuffer().append("Connection reclaimed by garbage collector, hostConfig=").append(connectionSource.hostConfiguration).toString());
                }
                connectionSource.connectionPool.handleLostConnection(connectionSource.hostConfiguration);
            }
        }

        public void run() {
            while (!this.shutdown) {
                try {
                    Reference remove = MultiThreadedHttpConnectionManager.access$1500().remove(1000);
                    if (remove != null) {
                        handleReference(remove);
                    }
                } catch (Throwable e) {
                    MultiThreadedHttpConnectionManager.access$700().debug("ReferenceQueueThread interrupted", e);
                }
            }
        }
    }

    private static class WaitingThread {
        public HostConnectionPool hostConnectionPool;
        public Thread thread;

        private WaitingThread() {
        }

        WaitingThread(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    static HostConfiguration access$1100(MultiThreadedHttpConnectionManager multiThreadedHttpConnectionManager, HttpConnection httpConnection) {
        return multiThreadedHttpConnectionManager.configurationForConnection(httpConnection);
    }

    static boolean access$1200(MultiThreadedHttpConnectionManager multiThreadedHttpConnectionManager) {
        return multiThreadedHttpConnectionManager.shutdown;
    }

    static void access$1300(HttpConnectionWithReference httpConnectionWithReference) {
        removeReferenceToConnection(httpConnectionWithReference);
    }

    static Map access$1400() {
        return REFERENCE_TO_CONNECTION_SOURCE;
    }

    static ReferenceQueue access$1500() {
        return REFERENCE_QUEUE;
    }

    static void access$600(ConnectionPool connectionPool) {
        shutdownCheckedOutConnections(connectionPool);
    }

    static Log access$700() {
        return LOG;
    }

    static HttpConnectionManagerParams access$800(MultiThreadedHttpConnectionManager multiThreadedHttpConnectionManager) {
        return multiThreadedHttpConnectionManager.params;
    }

    static void access$900(HttpConnectionWithReference httpConnectionWithReference, HostConfiguration hostConfiguration, ConnectionPool connectionPool) {
        storeReferenceToConnection(httpConnectionWithReference, hostConfiguration, connectionPool);
    }

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$MultiThreadedHttpConnectionManager == null) {
            class$ = class$("org.apache.commons.httpclient.MultiThreadedHttpConnectionManager");
            class$org$apache$commons$httpclient$MultiThreadedHttpConnectionManager = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$MultiThreadedHttpConnectionManager;
        }
        LOG = LogFactory.getLog(class$);
    }

    public static void shutdownAll() {
        synchronized (REFERENCE_TO_CONNECTION_SOURCE) {
            synchronized (ALL_CONNECTION_MANAGERS) {
                Iterator it = ALL_CONNECTION_MANAGERS.keySet().iterator();
                while (it.hasNext()) {
                    MultiThreadedHttpConnectionManager multiThreadedHttpConnectionManager = (MultiThreadedHttpConnectionManager) it.next();
                    it.remove();
                    multiThreadedHttpConnectionManager.shutdown();
                }
            }
            if (REFERENCE_QUEUE_THREAD != null) {
                REFERENCE_QUEUE_THREAD.shutdown();
                REFERENCE_QUEUE_THREAD = null;
            }
            REFERENCE_TO_CONNECTION_SOURCE.clear();
        }
    }

    private static void storeReferenceToConnection(HttpConnectionWithReference httpConnectionWithReference, HostConfiguration hostConfiguration, ConnectionPool connectionPool) {
        ConnectionSource connectionSource = new ConnectionSource(null);
        connectionSource.connectionPool = connectionPool;
        connectionSource.hostConfiguration = hostConfiguration;
        synchronized (REFERENCE_TO_CONNECTION_SOURCE) {
            if (REFERENCE_QUEUE_THREAD == null) {
                REFERENCE_QUEUE_THREAD = new ReferenceQueueThread();
                REFERENCE_QUEUE_THREAD.start();
            }
            REFERENCE_TO_CONNECTION_SOURCE.put(httpConnectionWithReference.reference, connectionSource);
        }
    }

    private static void shutdownCheckedOutConnections(ConnectionPool connectionPool) {
        AbstractList arrayList = new ArrayList();
        synchronized (REFERENCE_TO_CONNECTION_SOURCE) {
            Iterator it = REFERENCE_TO_CONNECTION_SOURCE.keySet().iterator();
            while (it.hasNext()) {
                HttpConnection httpConnection;
                Reference reference = (Reference) it.next();
                if (((ConnectionSource) REFERENCE_TO_CONNECTION_SOURCE.get(reference)).connectionPool == connectionPool) {
                    it.remove();
                    httpConnection = (HttpConnection) reference.get();
                    if (httpConnection != null) {
                        arrayList.add(httpConnection);
                    }
                }
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            httpConnection = (HttpConnection) it2.next();
            httpConnection.close();
            httpConnection.setHttpConnectionManager(null);
            httpConnection.releaseConnection();
        }
    }

    private static void removeReferenceToConnection(HttpConnectionWithReference httpConnectionWithReference) {
        synchronized (REFERENCE_TO_CONNECTION_SOURCE) {
            REFERENCE_TO_CONNECTION_SOURCE.remove(httpConnectionWithReference.reference);
        }
    }

    public MultiThreadedHttpConnectionManager() {
        synchronized (ALL_CONNECTION_MANAGERS) {
            ALL_CONNECTION_MANAGERS.put(this, null);
        }
    }

    public synchronized void shutdown() {
        synchronized (this.connectionPool) {
            if (!this.shutdown) {
                this.shutdown = true;
                this.connectionPool.shutdown();
            }
        }
    }

    public boolean isConnectionStaleCheckingEnabled() {
        return this.params.isStaleCheckingEnabled();
    }

    public void setConnectionStaleCheckingEnabled(boolean z) {
        this.params.setStaleCheckingEnabled(z);
    }

    public void setMaxConnectionsPerHost(int i) {
        this.params.setDefaultMaxConnectionsPerHost(i);
    }

    public int getMaxConnectionsPerHost() {
        return this.params.getDefaultMaxConnectionsPerHost();
    }

    public void setMaxTotalConnections(int i) {
        this.params.setMaxTotalConnections(i);
    }

    public int getMaxTotalConnections() {
        return this.params.getMaxTotalConnections();
    }

    public HttpConnection getConnection(HostConfiguration hostConfiguration) {
        while (true) {
            try {
                break;
            } catch (Throwable e) {
                LOG.debug("Unexpected exception while waiting for connection", e);
            }
        }
        return getConnectionWithTimeout(hostConfiguration, 0);
    }

    public HttpConnection getConnectionWithTimeout(HostConfiguration hostConfiguration, long j) throws ConnectionPoolTimeoutException {
        LOG.trace("enter HttpConnectionManager.getConnectionWithTimeout(HostConfiguration, long)");
        if (hostConfiguration == null) {
            throw new IllegalArgumentException("hostConfiguration is null");
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug(new StringBuffer().append("HttpConnectionManager.getConnection:  config = ").append(hostConfiguration).append(", timeout = ").append(j).toString());
        }
        return new HttpConnectionAdapter(doGetConnection(hostConfiguration, j));
    }

    public HttpConnection getConnection(HostConfiguration hostConfiguration, long j) throws HttpException {
        LOG.trace("enter HttpConnectionManager.getConnection(HostConfiguration, long)");
        try {
            return getConnectionWithTimeout(hostConfiguration, j);
        } catch (Throwable e) {
            throw new HttpException(e.getMessage());
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.apache.commons.httpclient.HttpConnection doGetConnection(org.apache.commons.httpclient.HostConfiguration r19, long r20) throws org.apache.commons.httpclient.ConnectionPoolTimeoutException {
        /*
        r18 = this;
        r6 = 0;
        r0 = r18;
        r2 = r0.params;
        r0 = r19;
        r11 = r2.getMaxConnectionsPerHost(r0);
        r0 = r18;
        r2 = r0.params;
        r12 = r2.getMaxTotalConnections();
        r0 = r18;
        r13 = r0.connectionPool;
        monitor-enter(r13);
        r14 = new org.apache.commons.httpclient.HostConfiguration;	 Catch:{ all -> 0x0048 }
        r0 = r19;
        r14.<init>(r0);	 Catch:{ all -> 0x0048 }
        r0 = r18;
        r2 = r0.connectionPool;	 Catch:{ all -> 0x0048 }
        r15 = r2.getHostPool(r14);	 Catch:{ all -> 0x0048 }
        r3 = 0;
        r4 = 0;
        r2 = (r20 > r4 ? 1 : (r20 == r4 ? 0 : -1));
        if (r2 <= 0) goto L_0x0038;
    L_0x002e:
        r2 = 1;
    L_0x002f:
        r4 = 0;
        r8 = r20;
        r10 = r6;
    L_0x0034:
        if (r10 == 0) goto L_0x003a;
    L_0x0036:
        monitor-exit(r13);	 Catch:{ all -> 0x0048 }
        return r10;
    L_0x0038:
        r2 = 0;
        goto L_0x002f;
    L_0x003a:
        r0 = r18;
        r6 = r0.shutdown;	 Catch:{ all -> 0x0048 }
        if (r6 == 0) goto L_0x004b;
    L_0x0040:
        r2 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0048 }
        r3 = "Connection factory has been shutdown.";
        r2.<init>(r3);	 Catch:{ all -> 0x0048 }
        throw r2;	 Catch:{ all -> 0x0048 }
    L_0x0048:
        r2 = move-exception;
        monitor-exit(r13);
        throw r2;
    L_0x004b:
        r6 = r15.freeConnections;	 Catch:{ all -> 0x0048 }
        r6 = r6.size();	 Catch:{ all -> 0x0048 }
        if (r6 <= 0) goto L_0x005d;
    L_0x0053:
        r0 = r18;
        r6 = r0.connectionPool;	 Catch:{ all -> 0x0048 }
        r6 = r6.getFreeConnection(r14);	 Catch:{ all -> 0x0048 }
        r10 = r6;
        goto L_0x0034;
    L_0x005d:
        r6 = r15.numConnections;	 Catch:{ all -> 0x0048 }
        if (r6 >= r11) goto L_0x0075;
    L_0x0061:
        r0 = r18;
        r6 = r0.connectionPool;	 Catch:{ all -> 0x0048 }
        r6 = org.apache.commons.httpclient.MultiThreadedHttpConnectionManager.ConnectionPool.access$200(r6);	 Catch:{ all -> 0x0048 }
        if (r6 >= r12) goto L_0x0075;
    L_0x006b:
        r0 = r18;
        r6 = r0.connectionPool;	 Catch:{ all -> 0x0048 }
        r6 = r6.createConnection(r14);	 Catch:{ all -> 0x0048 }
        r10 = r6;
        goto L_0x0034;
    L_0x0075:
        r6 = r15.numConnections;	 Catch:{ all -> 0x0048 }
        if (r6 >= r11) goto L_0x0098;
    L_0x0079:
        r0 = r18;
        r6 = r0.connectionPool;	 Catch:{ all -> 0x0048 }
        r6 = org.apache.commons.httpclient.MultiThreadedHttpConnectionManager.ConnectionPool.access$300(r6);	 Catch:{ all -> 0x0048 }
        r6 = r6.size();	 Catch:{ all -> 0x0048 }
        if (r6 <= 0) goto L_0x0098;
    L_0x0087:
        r0 = r18;
        r6 = r0.connectionPool;	 Catch:{ all -> 0x0048 }
        r6.deleteLeastUsedConnection();	 Catch:{ all -> 0x0048 }
        r0 = r18;
        r6 = r0.connectionPool;	 Catch:{ all -> 0x0048 }
        r6 = r6.createConnection(r14);	 Catch:{ all -> 0x0048 }
        r10 = r6;
        goto L_0x0034;
    L_0x0098:
        if (r2 == 0) goto L_0x00b4;
    L_0x009a:
        r6 = 0;
        r6 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1));
        if (r6 > 0) goto L_0x00b4;
    L_0x00a0:
        r6 = new org.apache.commons.httpclient.ConnectionPoolTimeoutException;	 Catch:{ InterruptedException -> 0x00a8, all -> 0x011e }
        r7 = "Timeout waiting for connection";
        r6.<init>(r7);	 Catch:{ InterruptedException -> 0x00a8, all -> 0x011e }
        throw r6;	 Catch:{ InterruptedException -> 0x00a8, all -> 0x011e }
    L_0x00a8:
        r6 = move-exception;
    L_0x00a9:
        if (r2 == 0) goto L_0x012e;
    L_0x00ab:
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0048 }
        r6 = r6 - r4;
        r6 = r8 - r6;
    L_0x00b2:
        r8 = r6;
        goto L_0x0034;
    L_0x00b4:
        r6 = LOG;	 Catch:{ InterruptedException -> 0x00a8, all -> 0x011e }
        r6 = r6.isDebugEnabled();	 Catch:{ InterruptedException -> 0x00a8, all -> 0x011e }
        if (r6 == 0) goto L_0x00d6;
    L_0x00bc:
        r6 = LOG;	 Catch:{ InterruptedException -> 0x00a8, all -> 0x011e }
        r7 = new java.lang.StringBuffer;	 Catch:{ InterruptedException -> 0x00a8, all -> 0x011e }
        r7.<init>();	 Catch:{ InterruptedException -> 0x00a8, all -> 0x011e }
        r16 = "Unable to get a connection, waiting..., hostConfig=";
        r0 = r16;
        r7 = r7.append(r0);	 Catch:{ InterruptedException -> 0x00a8, all -> 0x011e }
        r7 = r7.append(r14);	 Catch:{ InterruptedException -> 0x00a8, all -> 0x011e }
        r7 = r7.toString();	 Catch:{ InterruptedException -> 0x00a8, all -> 0x011e }
        r6.debug(r7);	 Catch:{ InterruptedException -> 0x00a8, all -> 0x011e }
    L_0x00d6:
        if (r3 != 0) goto L_0x00e7;
    L_0x00d8:
        r6 = new org.apache.commons.httpclient.MultiThreadedHttpConnectionManager$WaitingThread;	 Catch:{ InterruptedException -> 0x00a8, all -> 0x011e }
        r7 = 0;
        r6.<init>(r7);	 Catch:{ InterruptedException -> 0x00a8, all -> 0x011e }
        r6.hostConnectionPool = r15;	 Catch:{ InterruptedException -> 0x012a, all -> 0x011e }
        r3 = java.lang.Thread.currentThread();	 Catch:{ InterruptedException -> 0x012a, all -> 0x011e }
        r6.thread = r3;	 Catch:{ InterruptedException -> 0x012a, all -> 0x011e }
        r3 = r6;
    L_0x00e7:
        if (r2 == 0) goto L_0x00ed;
    L_0x00e9:
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ InterruptedException -> 0x00a8, all -> 0x011e }
    L_0x00ed:
        r6 = r15.waitingThreads;	 Catch:{ InterruptedException -> 0x00a8, all -> 0x011e }
        r6.addLast(r3);	 Catch:{ InterruptedException -> 0x00a8, all -> 0x011e }
        r0 = r18;
        r6 = r0.connectionPool;	 Catch:{ InterruptedException -> 0x00a8, all -> 0x011e }
        r6 = org.apache.commons.httpclient.MultiThreadedHttpConnectionManager.ConnectionPool.access$500(r6);	 Catch:{ InterruptedException -> 0x00a8, all -> 0x011e }
        r6.addLast(r3);	 Catch:{ InterruptedException -> 0x00a8, all -> 0x011e }
        r0 = r18;
        r6 = r0.connectionPool;	 Catch:{ InterruptedException -> 0x00a8, all -> 0x011e }
        r6.wait(r8);	 Catch:{ InterruptedException -> 0x00a8, all -> 0x011e }
        r6 = r15.waitingThreads;	 Catch:{ InterruptedException -> 0x00a8, all -> 0x011e }
        r6.remove(r3);	 Catch:{ InterruptedException -> 0x00a8, all -> 0x011e }
        r0 = r18;
        r6 = r0.connectionPool;	 Catch:{ InterruptedException -> 0x00a8, all -> 0x011e }
        r6 = org.apache.commons.httpclient.MultiThreadedHttpConnectionManager.ConnectionPool.access$500(r6);	 Catch:{ InterruptedException -> 0x00a8, all -> 0x011e }
        r6.remove(r3);	 Catch:{ InterruptedException -> 0x00a8, all -> 0x011e }
        if (r2 == 0) goto L_0x0034;
    L_0x0116:
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0048 }
        r6 = r6 - r4;
        r8 = r8 - r6;
        goto L_0x0034;
    L_0x011e:
        r3 = move-exception;
        if (r2 == 0) goto L_0x0129;
    L_0x0121:
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0048 }
        r4 = r6 - r4;
        r4 = r8 - r4;
    L_0x0129:
        throw r3;	 Catch:{ all -> 0x0048 }
    L_0x012a:
        r3 = move-exception;
        r3 = r6;
        goto L_0x00a9;
    L_0x012e:
        r6 = r8;
        goto L_0x00b2;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.httpclient.MultiThreadedHttpConnectionManager.doGetConnection(org.apache.commons.httpclient.HostConfiguration, long):org.apache.commons.httpclient.HttpConnection");
    }

    public int getConnectionsInPool(HostConfiguration hostConfiguration) {
        int i;
        synchronized (this.connectionPool) {
            i = this.connectionPool.getHostPool(hostConfiguration).numConnections;
        }
        return i;
    }

    public int getConnectionsInPool() {
        int access$200;
        synchronized (this.connectionPool) {
            access$200 = ConnectionPool.access$200(this.connectionPool);
        }
        return access$200;
    }

    public int getConnectionsInUse(HostConfiguration hostConfiguration) {
        return getConnectionsInPool(hostConfiguration);
    }

    public int getConnectionsInUse() {
        return getConnectionsInPool();
    }

    public void deleteClosedConnections() {
        this.connectionPool.deleteClosedConnections();
    }

    public void closeIdleConnections(long j) {
        this.connectionPool.closeIdleConnections(j);
    }

    public void releaseConnection(HttpConnection httpConnection) {
        LOG.trace("enter HttpConnectionManager.releaseConnection(HttpConnection)");
        if (httpConnection instanceof HttpConnectionAdapter) {
            httpConnection = ((HttpConnectionAdapter) httpConnection).getWrappedConnection();
        }
        SimpleHttpConnectionManager.finishLastResponse(httpConnection);
        this.connectionPool.freeConnection(httpConnection);
    }

    private HostConfiguration configurationForConnection(HttpConnection httpConnection) {
        HostConfiguration hostConfiguration = new HostConfiguration();
        hostConfiguration.setHost(httpConnection.getHost(), httpConnection.getPort(), httpConnection.getProtocol());
        if (httpConnection.getLocalAddress() != null) {
            hostConfiguration.setLocalAddress(httpConnection.getLocalAddress());
        }
        if (httpConnection.getProxyHost() != null) {
            hostConfiguration.setProxy(httpConnection.getProxyHost(), httpConnection.getProxyPort());
        }
        return hostConfiguration;
    }

    public HttpConnectionManagerParams getParams() {
        return this.params;
    }

    public void setParams(HttpConnectionManagerParams httpConnectionManagerParams) {
        if (httpConnectionManagerParams == null) {
            throw new IllegalArgumentException("Parameters may not be null");
        }
        this.params = httpConnectionManagerParams;
    }
}
