package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.HttpConnection;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

@Deprecated
public class IdleConnectionHandler {
    private final Map<HttpConnection, IdleConnectionHandler$a> a = new HashMap();
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    public void add(HttpConnection httpConnection, long j, TimeUnit timeUnit) {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.log.isDebugEnabled()) {
            this.log.debug("Adding connection at: " + currentTimeMillis);
        }
        this.a.put(httpConnection, new IdleConnectionHandler$a(currentTimeMillis, j, timeUnit));
    }

    public boolean remove(HttpConnection httpConnection) {
        IdleConnectionHandler$a idleConnectionHandler$a = (IdleConnectionHandler$a) this.a.remove(httpConnection);
        if (idleConnectionHandler$a == null) {
            this.log.warn("Removing a connection that never existed!");
            return true;
        }
        return System.currentTimeMillis() <= IdleConnectionHandler$a.a(idleConnectionHandler$a);
    }

    public void removeAll() {
        this.a.clear();
    }

    public void closeIdleConnections(long j) {
        long currentTimeMillis = System.currentTimeMillis() - j;
        if (this.log.isDebugEnabled()) {
            this.log.debug("Checking for connections, idle timeout: " + currentTimeMillis);
        }
        for (Entry entry : this.a.entrySet()) {
            HttpConnection httpConnection = (HttpConnection) entry.getKey();
            long b = IdleConnectionHandler$a.b((IdleConnectionHandler$a) entry.getValue());
            if (b <= currentTimeMillis) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug("Closing idle connection, connection time: " + b);
                }
                try {
                    httpConnection.close();
                } catch (Throwable e) {
                    this.log.debug("I/O error closing connection", e);
                }
            }
        }
    }

    public void closeExpiredConnections() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.log.isDebugEnabled()) {
            this.log.debug("Checking for expired connections, now: " + currentTimeMillis);
        }
        for (Entry entry : this.a.entrySet()) {
            HttpConnection httpConnection = (HttpConnection) entry.getKey();
            IdleConnectionHandler$a idleConnectionHandler$a = (IdleConnectionHandler$a) entry.getValue();
            if (IdleConnectionHandler$a.a(idleConnectionHandler$a) <= currentTimeMillis) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug("Closing connection, expired @: " + IdleConnectionHandler$a.a(idleConnectionHandler$a));
                }
                try {
                    httpConnection.close();
                } catch (Throwable e) {
                    this.log.debug("I/O error closing connection", e);
                }
            }
        }
    }
}
