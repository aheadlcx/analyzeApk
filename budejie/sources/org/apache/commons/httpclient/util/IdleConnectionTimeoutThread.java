package org.apache.commons.httpclient.util;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.httpclient.HttpConnectionManager;

public class IdleConnectionTimeoutThread extends Thread {
    private List connectionManagers = new ArrayList();
    private long connectionTimeout = 3000;
    private boolean shutdown = false;
    private long timeoutInterval = 1000;

    public IdleConnectionTimeoutThread() {
        setDaemon(true);
    }

    public synchronized void addConnectionManager(HttpConnectionManager httpConnectionManager) {
        if (this.shutdown) {
            throw new IllegalStateException("IdleConnectionTimeoutThread has been shutdown");
        }
        this.connectionManagers.add(httpConnectionManager);
    }

    public synchronized void removeConnectionManager(HttpConnectionManager httpConnectionManager) {
        if (this.shutdown) {
            throw new IllegalStateException("IdleConnectionTimeoutThread has been shutdown");
        }
        this.connectionManagers.remove(httpConnectionManager);
    }

    public synchronized void run() {
        while (!this.shutdown) {
            for (HttpConnectionManager closeIdleConnections : this.connectionManagers) {
                closeIdleConnections.closeIdleConnections(this.connectionTimeout);
            }
            try {
                wait(this.timeoutInterval);
            } catch (InterruptedException e) {
            }
        }
        this.connectionManagers.clear();
    }

    public synchronized void shutdown() {
        this.shutdown = true;
        notifyAll();
    }

    public synchronized void setConnectionTimeout(long j) {
        if (this.shutdown) {
            throw new IllegalStateException("IdleConnectionTimeoutThread has been shutdown");
        }
        this.connectionTimeout = j;
    }

    public synchronized void setTimeoutInterval(long j) {
        if (this.shutdown) {
            throw new IllegalStateException("IdleConnectionTimeoutThread has been shutdown");
        }
        this.timeoutInterval = j;
    }
}
