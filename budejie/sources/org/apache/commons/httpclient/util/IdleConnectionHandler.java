package org.apache.commons.httpclient.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IdleConnectionHandler {
    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$util$IdleConnectionHandler;
    private Map connectionToAdded = new HashMap();

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$util$IdleConnectionHandler == null) {
            class$ = class$("org.apache.commons.httpclient.util.IdleConnectionHandler");
            class$org$apache$commons$httpclient$util$IdleConnectionHandler = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$util$IdleConnectionHandler;
        }
        LOG = LogFactory.getLog(class$);
    }

    public void add(HttpConnection httpConnection) {
        Long l = new Long(System.currentTimeMillis());
        if (LOG.isDebugEnabled()) {
            LOG.debug(new StringBuffer().append("Adding connection at: ").append(l).toString());
        }
        this.connectionToAdded.put(httpConnection, l);
    }

    public void remove(HttpConnection httpConnection) {
        this.connectionToAdded.remove(httpConnection);
    }

    public void removeAll() {
        this.connectionToAdded.clear();
    }

    public void closeIdleConnections(long j) {
        long currentTimeMillis = System.currentTimeMillis() - j;
        if (LOG.isDebugEnabled()) {
            LOG.debug(new StringBuffer().append("Checking for connections, idleTimeout: ").append(currentTimeMillis).toString());
        }
        Iterator it = this.connectionToAdded.keySet().iterator();
        while (it.hasNext()) {
            HttpConnection httpConnection = (HttpConnection) it.next();
            Long l = (Long) this.connectionToAdded.get(httpConnection);
            if (l.longValue() <= currentTimeMillis) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug(new StringBuffer().append("Closing connection, connection time: ").append(l).toString());
                }
                it.remove();
                httpConnection.close();
            }
        }
    }
}
