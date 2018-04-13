package cz.msebera.android.httpclient.impl.conn.tsccm;

import cz.msebera.android.httpclient.conn.params.ConnPerRoute;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import cz.msebera.android.httpclient.util.LangUtils;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

@Deprecated
public class RouteSpecificPool {
    protected final HttpRoute a;
    protected final int b;
    protected final ConnPerRoute c;
    protected final LinkedList<BasicPoolEntry> d;
    protected final Queue<WaitingThread> e;
    protected int f;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    @Deprecated
    public RouteSpecificPool(HttpRoute httpRoute, int i) {
        this.a = httpRoute;
        this.b = i;
        this.c = new b(this);
        this.d = new LinkedList();
        this.e = new LinkedList();
        this.f = 0;
    }

    public RouteSpecificPool(HttpRoute httpRoute, ConnPerRoute connPerRoute) {
        this.a = httpRoute;
        this.c = connPerRoute;
        this.b = connPerRoute.getMaxForRoute(httpRoute);
        this.d = new LinkedList();
        this.e = new LinkedList();
        this.f = 0;
    }

    public final HttpRoute getRoute() {
        return this.a;
    }

    public final int getMaxEntries() {
        return this.b;
    }

    public boolean isUnused() {
        return this.f < 1 && this.e.isEmpty();
    }

    public int getCapacity() {
        return this.c.getMaxForRoute(this.a) - this.f;
    }

    public final int getEntryCount() {
        return this.f;
    }

    public BasicPoolEntry allocEntry(Object obj) {
        BasicPoolEntry basicPoolEntry;
        if (!this.d.isEmpty()) {
            ListIterator listIterator = this.d.listIterator(this.d.size());
            while (listIterator.hasPrevious()) {
                basicPoolEntry = (BasicPoolEntry) listIterator.previous();
                if (basicPoolEntry.getState() != null) {
                    if (LangUtils.equals(obj, basicPoolEntry.getState())) {
                    }
                }
                listIterator.remove();
                return basicPoolEntry;
            }
        }
        if (getCapacity() != 0 || this.d.isEmpty()) {
            return null;
        }
        basicPoolEntry = (BasicPoolEntry) this.d.remove();
        basicPoolEntry.a();
        try {
            basicPoolEntry.b().close();
            return basicPoolEntry;
        } catch (Throwable e) {
            this.log.debug("I/O error closing connection", e);
            return basicPoolEntry;
        }
    }

    public void freeEntry(BasicPoolEntry basicPoolEntry) {
        if (this.f < 1) {
            throw new IllegalStateException("No entry created for this pool. " + this.a);
        } else if (this.f <= this.d.size()) {
            throw new IllegalStateException("No entry allocated from this pool. " + this.a);
        } else {
            this.d.add(basicPoolEntry);
        }
    }

    public void createdEntry(BasicPoolEntry basicPoolEntry) {
        Args.check(this.a.equals(basicPoolEntry.c()), "Entry not planned for this pool");
        this.f++;
    }

    public boolean deleteEntry(BasicPoolEntry basicPoolEntry) {
        boolean remove = this.d.remove(basicPoolEntry);
        if (remove) {
            this.f--;
        }
        return remove;
    }

    public void dropEntry() {
        Asserts.check(this.f > 0, "There is no entry that could be dropped");
        this.f--;
    }

    public void queueThread(WaitingThread waitingThread) {
        Args.notNull(waitingThread, "Waiting thread");
        this.e.add(waitingThread);
    }

    public boolean hasThread() {
        return !this.e.isEmpty();
    }

    public WaitingThread nextThread() {
        return (WaitingThread) this.e.peek();
    }

    public void removeThread(WaitingThread waitingThread) {
        if (waitingThread != null) {
            this.e.remove(waitingThread);
        }
    }
}
