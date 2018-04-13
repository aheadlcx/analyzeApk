package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.List;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.ConnectionPool;
import okhttp3.EventListener;
import okhttp3.Interceptor$Chain;
import okhttp3.OkHttpClient;
import okhttp3.Route;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RouteSelector.Selection;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;

public final class StreamAllocation {
    static final /* synthetic */ boolean a = (!StreamAllocation.class.desiredAssertionStatus());
    public final Address address;
    private Selection b;
    private Route c;
    public final Call call;
    private final ConnectionPool d;
    private final Object e;
    public final EventListener eventListener;
    private final RouteSelector f;
    private int g;
    private RealConnection h;
    private boolean i;
    private boolean j;
    private boolean k;
    private HttpCodec l;

    public static final class StreamAllocationReference extends WeakReference<StreamAllocation> {
        public final Object callStackTrace;

        StreamAllocationReference(StreamAllocation streamAllocation, Object obj) {
            super(streamAllocation);
            this.callStackTrace = obj;
        }
    }

    public StreamAllocation(ConnectionPool connectionPool, Address address, Call call, EventListener eventListener, Object obj) {
        this.d = connectionPool;
        this.address = address;
        this.call = call;
        this.eventListener = eventListener;
        this.f = new RouteSelector(address, b(), call, eventListener);
        this.e = obj;
    }

    public HttpCodec newStream(OkHttpClient okHttpClient, Interceptor$Chain interceptor$Chain, boolean z) {
        try {
            HttpCodec newCodec = a(interceptor$Chain.connectTimeoutMillis(), interceptor$Chain.readTimeoutMillis(), interceptor$Chain.writeTimeoutMillis(), okHttpClient.retryOnConnectionFailure(), z).newCodec(okHttpClient, interceptor$Chain, this);
            synchronized (this.d) {
                this.l = newCodec;
            }
            return newCodec;
        } catch (IOException e) {
            throw new RouteException(e);
        }
    }

    private RealConnection a(int i, int i2, int i3, boolean z, boolean z2) throws IOException {
        RealConnection a;
        while (true) {
            a = a(i, i2, i3, z);
            synchronized (this.d) {
                if (a.successCount != 0) {
                    if (a.isHealthy(z2)) {
                        break;
                    }
                    noNewStreams();
                } else {
                    break;
                }
            }
        }
        return a;
    }

    private RealConnection a(int i, int i2, int i3, boolean z) throws IOException {
        Connection connection;
        Socket a;
        Object obj = null;
        Connection connection2 = null;
        Route route = null;
        synchronized (this.d) {
            if (this.j) {
                throw new IllegalStateException("released");
            } else if (this.l != null) {
                throw new IllegalStateException("codec != null");
            } else if (this.k) {
                throw new IOException("Canceled");
            } else {
                connection = this.h;
                a = a();
                if (this.h != null) {
                    connection2 = this.h;
                    connection = null;
                }
                if (!this.i) {
                    connection = null;
                }
                if (connection2 == null) {
                    Internal.instance.get(this.d, this.address, this, null);
                    if (this.h != null) {
                        obj = 1;
                        connection2 = this.h;
                    } else {
                        route = this.c;
                    }
                }
            }
        }
        Util.closeQuietly(a);
        if (connection != null) {
            this.eventListener.connectionReleased(this.call, connection);
        }
        if (obj != null) {
            this.eventListener.connectionAcquired(this.call, connection2);
        }
        if (connection2 != null) {
            return connection2;
        }
        Object obj2 = null;
        if (route == null && (this.b == null || !this.b.hasNext())) {
            obj2 = 1;
            this.b = this.f.next();
        }
        synchronized (this.d) {
            if (this.k) {
                throw new IOException("Canceled");
            }
            if (obj2 != null) {
                List all = this.b.getAll();
                int size = all.size();
                for (int i4 = 0; i4 < size; i4++) {
                    Route route2 = (Route) all.get(i4);
                    Internal.instance.get(this.d, this.address, this, route2);
                    if (this.h != null) {
                        obj = 1;
                        RealConnection realConnection = this.h;
                        this.c = route2;
                        RealConnection realConnection2 = realConnection;
                        break;
                    }
                }
            }
            connection = connection2;
            if (obj == null) {
                Route next;
                if (route == null) {
                    next = this.b.next();
                } else {
                    next = route;
                }
                this.c = next;
                this.g = 0;
                realConnection2 = new RealConnection(this.d, next);
                acquire(realConnection2, false);
            }
        }
        if (obj != null) {
            this.eventListener.connectionAcquired(this.call, realConnection2);
            return realConnection2;
        }
        realConnection2.connect(i, i2, i3, z, this.call, this.eventListener);
        b().connected(realConnection2.route());
        Socket socket = null;
        synchronized (this.d) {
            this.i = true;
            Internal.instance.put(this.d, realConnection2);
            if (realConnection2.isMultiplexed()) {
                Socket deduplicate = Internal.instance.deduplicate(this.d, this.address, this);
                realConnection2 = this.h;
                socket = deduplicate;
            }
        }
        Util.closeQuietly(socket);
        this.eventListener.connectionAcquired(this.call, realConnection2);
        return realConnection2;
    }

    private Socket a() {
        if (a || Thread.holdsLock(this.d)) {
            RealConnection realConnection = this.h;
            if (realConnection == null || !realConnection.noNewStreams) {
                return null;
            }
            return a(false, false, true);
        }
        throw new AssertionError();
    }

    public void streamFinished(boolean z, HttpCodec httpCodec, long j, IOException iOException) {
        Socket a;
        this.eventListener.responseBodyEnd(this.call, j);
        synchronized (this.d) {
            if (httpCodec != null) {
                if (httpCodec == this.l) {
                    if (!z) {
                        RealConnection realConnection = this.h;
                        realConnection.successCount++;
                    }
                    Connection connection = this.h;
                    a = a(z, false, true);
                    if (this.h != null) {
                        connection = null;
                    }
                    boolean z2 = this.j;
                }
            }
            throw new IllegalStateException("expected " + this.l + " but was " + httpCodec);
        }
        Util.closeQuietly(a);
        if (connection != null) {
            this.eventListener.connectionReleased(this.call, connection);
        }
        if (iOException != null) {
            this.eventListener.callFailed(this.call, iOException);
        } else if (z2) {
            this.eventListener.callEnd(this.call);
        }
    }

    public HttpCodec codec() {
        HttpCodec httpCodec;
        synchronized (this.d) {
            httpCodec = this.l;
        }
        return httpCodec;
    }

    private RouteDatabase b() {
        return Internal.instance.routeDatabase(this.d);
    }

    public synchronized RealConnection connection() {
        return this.h;
    }

    public void release() {
        Socket a;
        synchronized (this.d) {
            Connection connection = this.h;
            a = a(false, true, false);
            if (this.h != null) {
                connection = null;
            }
        }
        Util.closeQuietly(a);
        if (connection != null) {
            this.eventListener.connectionReleased(this.call, connection);
        }
    }

    public void noNewStreams() {
        Socket a;
        synchronized (this.d) {
            Connection connection = this.h;
            a = a(true, false, false);
            if (this.h != null) {
                connection = null;
            }
        }
        Util.closeQuietly(a);
        if (connection != null) {
            this.eventListener.connectionReleased(this.call, connection);
        }
    }

    private Socket a(boolean z, boolean z2, boolean z3) {
        if (a || Thread.holdsLock(this.d)) {
            if (z3) {
                this.l = null;
            }
            if (z2) {
                this.j = true;
            }
            if (this.h == null) {
                return null;
            }
            if (z) {
                this.h.noNewStreams = true;
            }
            if (this.l != null) {
                return null;
            }
            if (!this.j && !this.h.noNewStreams) {
                return null;
            }
            Socket socket;
            a(this.h);
            if (this.h.allocations.isEmpty()) {
                this.h.idleAtNanos = System.nanoTime();
                if (Internal.instance.connectionBecameIdle(this.d, this.h)) {
                    socket = this.h.socket();
                    this.h = null;
                    return socket;
                }
            }
            socket = null;
            this.h = null;
            return socket;
        }
        throw new AssertionError();
    }

    public void cancel() {
        synchronized (this.d) {
            this.k = true;
            HttpCodec httpCodec = this.l;
            RealConnection realConnection = this.h;
        }
        if (httpCodec != null) {
            httpCodec.cancel();
        } else if (realConnection != null) {
            realConnection.cancel();
        }
    }

    public void streamFailed(IOException iOException) {
        Socket a;
        boolean z = false;
        boolean z2 = true;
        synchronized (this.d) {
            if (iOException instanceof StreamResetException) {
                StreamResetException streamResetException = (StreamResetException) iOException;
                if (streamResetException.errorCode == ErrorCode.REFUSED_STREAM) {
                    this.g++;
                }
                if (streamResetException.errorCode != ErrorCode.REFUSED_STREAM || this.g > 1) {
                    this.c = null;
                    z = true;
                }
                z2 = z;
            } else if (this.h == null || (this.h.isMultiplexed() && !(iOException instanceof ConnectionShutdownException))) {
                z2 = false;
            } else if (this.h.successCount == 0) {
                if (!(this.c == null || iOException == null)) {
                    this.f.connectFailed(this.c, iOException);
                }
                this.c = null;
            }
            Connection connection = this.h;
            a = a(z2, false, true);
            if (!(this.h == null && this.i)) {
                connection = null;
            }
        }
        Util.closeQuietly(a);
        if (connection != null) {
            this.eventListener.connectionReleased(this.call, connection);
        }
    }

    public void acquire(RealConnection realConnection, boolean z) {
        if (!a && !Thread.holdsLock(this.d)) {
            throw new AssertionError();
        } else if (this.h != null) {
            throw new IllegalStateException();
        } else {
            this.h = realConnection;
            this.i = z;
            realConnection.allocations.add(new StreamAllocationReference(this, this.e));
        }
    }

    private void a(RealConnection realConnection) {
        int size = realConnection.allocations.size();
        for (int i = 0; i < size; i++) {
            if (((Reference) realConnection.allocations.get(i)).get() == this) {
                realConnection.allocations.remove(i);
                return;
            }
        }
        throw new IllegalStateException();
    }

    public Socket releaseAndAcquire(RealConnection realConnection) {
        if (!a && !Thread.holdsLock(this.d)) {
            throw new AssertionError();
        } else if (this.l == null && this.h.allocations.size() == 1) {
            Reference reference = (Reference) this.h.allocations.get(0);
            Socket a = a(true, false, false);
            this.h = realConnection;
            realConnection.allocations.add(reference);
            return a;
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean hasMoreRoutes() {
        return this.c != null || ((this.b != null && this.b.hasNext()) || this.f.hasNext());
    }

    public String toString() {
        RealConnection connection = connection();
        return connection != null ? connection.toString() : this.address.toString();
    }
}
