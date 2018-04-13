package com.tencent.wcdb.database;

import android.os.SystemClock;
import android.util.Printer;
import com.tencent.wcdb.DatabaseUtils;
import com.tencent.wcdb.database.SQLiteDebug.DbStats;
import com.tencent.wcdb.support.CancellationSignal;
import com.tencent.wcdb.support.CancellationSignal.OnCancelListener;
import com.tencent.wcdb.support.Log;
import com.tencent.wcdb.support.OperationCanceledException;
import com.tencent.wcdb.support.PrefixPrinter;
import java.io.Closeable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

public final class SQLiteConnectionPool implements Closeable {
    static final /* synthetic */ boolean $assertionsDisabled = (!SQLiteConnectionPool.class.desiredAssertionStatus());
    public static final int CONNECTION_FLAG_INTERACTIVE = 4;
    public static final int CONNECTION_FLAG_PRIMARY_CONNECTION_AFFINITY = 2;
    public static final int CONNECTION_FLAG_READ_ONLY = 1;
    private static final long CONNECTION_POOL_BUSY_MILLIS = 3000;
    private static final int OPEN_FLAG_REOPEN_MASK = 268435473;
    private static final String TAG = "WCDB.SQLiteConnectionPool";
    private final WeakHashMap<SQLiteConnection, AcquiredConnectionStatus> mAcquiredConnections = new WeakHashMap();
    private final ArrayList<SQLiteConnection> mAvailableNonPrimaryConnections = new ArrayList();
    private SQLiteConnection mAvailablePrimaryConnection;
    private volatile SQLiteCheckpointListener mCheckpointListener;
    private SQLiteCipherSpec mCipher;
    private final SQLiteDatabaseConfiguration mConfiguration;
    private final AtomicBoolean mConnectionLeaked = new AtomicBoolean();
    private a mConnectionWaiterPool;
    private a mConnectionWaiterQueue;
    private final WeakReference<SQLiteDatabase> mDB;
    private boolean mIsOpen;
    private final Object mLock = new Object();
    private int mMaxConnectionPoolSize;
    private int mNextConnectionId;
    private byte[] mPassword;
    private volatile SQLiteTrace mTraceCallback;

    enum AcquiredConnectionStatus {
        NORMAL,
        RECONFIGURE,
        DISCARD
    }

    private static final class a {
        public a a;
        public Thread b;
        public long c;
        public int d;
        public boolean e;
        public String f;
        public int g;
        public SQLiteConnection h;
        public RuntimeException i;
        public int j;

        private a() {
        }
    }

    private SQLiteConnectionPool(SQLiteDatabase sQLiteDatabase, SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration, int i) {
        this.mDB = new WeakReference(sQLiteDatabase);
        this.mConfiguration = new SQLiteDatabaseConfiguration(sQLiteDatabaseConfiguration);
        setMaxConnectionPoolSizeLocked(i);
    }

    protected void finalize() throws Throwable {
        try {
            dispose(true);
        } finally {
            super.finalize();
        }
    }

    public static SQLiteConnectionPool open(SQLiteDatabase sQLiteDatabase, SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration, byte[] bArr, SQLiteCipherSpec sQLiteCipherSpec) {
        return open(sQLiteDatabase, sQLiteDatabaseConfiguration, bArr, sQLiteCipherSpec, 1);
    }

    public static SQLiteConnectionPool open(SQLiteDatabase sQLiteDatabase, SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration, byte[] bArr, SQLiteCipherSpec sQLiteCipherSpec, int i) {
        if (sQLiteDatabaseConfiguration == null) {
            throw new IllegalArgumentException("configuration must not be null.");
        }
        SQLiteConnectionPool sQLiteConnectionPool = new SQLiteConnectionPool(sQLiteDatabase, sQLiteDatabaseConfiguration, i);
        sQLiteConnectionPool.mPassword = bArr;
        sQLiteConnectionPool.mCipher = sQLiteCipherSpec == null ? null : new SQLiteCipherSpec(sQLiteCipherSpec);
        sQLiteConnectionPool.open();
        return sQLiteConnectionPool;
    }

    private void open() {
        this.mAvailablePrimaryConnection = openConnectionLocked(this.mConfiguration, true);
        this.mIsOpen = true;
    }

    public void close() {
        dispose(false);
    }

    private void dispose(boolean z) {
        if (!z) {
            synchronized (this.mLock) {
                throwIfClosedLocked();
                this.mIsOpen = false;
                closeAvailableConnectionsAndLogExceptionsLocked();
                int size = this.mAcquiredConnections.size();
                if (size != 0) {
                    Log.i(TAG, "The connection pool for " + this.mConfiguration.label + " has been closed but there are still " + size + " connections in use.  They will be closed " + "as they are released back to the pool.");
                }
                wakeConnectionWaitersLocked();
            }
        }
    }

    public void reconfigure(SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration) {
        Object obj = 1;
        if (sQLiteDatabaseConfiguration == null) {
            throw new IllegalArgumentException("configuration must not be null.");
        }
        synchronized (this.mLock) {
            throwIfClosedLocked();
            Object obj2 = ((sQLiteDatabaseConfiguration.openFlags ^ this.mConfiguration.openFlags) & SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING) != 0 ? 1 : null;
            if (obj2 != null) {
                if (this.mAcquiredConnections.isEmpty()) {
                    closeAvailableNonPrimaryConnectionsAndLogExceptionsLocked();
                } else {
                    throw new IllegalStateException("Write Ahead Logging (WAL) mode cannot be enabled or disabled while there are transactions in progress.  Finish all transactions and release all active database connections first.");
                }
            }
            if (sQLiteDatabaseConfiguration.foreignKeyConstraintsEnabled == this.mConfiguration.foreignKeyConstraintsEnabled) {
                obj = null;
            }
            if (obj == null || this.mAcquiredConnections.isEmpty()) {
                if (((this.mConfiguration.openFlags ^ sQLiteDatabaseConfiguration.openFlags) & OPEN_FLAG_REOPEN_MASK) == 0 && DatabaseUtils.objectEquals(this.mConfiguration.vfsName, sQLiteDatabaseConfiguration.vfsName)) {
                    this.mConfiguration.updateParametersFrom(sQLiteDatabaseConfiguration);
                    setMaxConnectionPoolSizeLocked(0);
                    closeExcessConnectionsAndLogExceptionsLocked();
                    reconfigureAllConnectionsLocked();
                } else {
                    if (obj2 != null) {
                        closeAvailableConnectionsAndLogExceptionsLocked();
                    }
                    SQLiteConnection openConnectionLocked = openConnectionLocked(sQLiteDatabaseConfiguration, true);
                    closeAvailableConnectionsAndLogExceptionsLocked();
                    discardAcquiredConnectionsLocked();
                    this.mAvailablePrimaryConnection = openConnectionLocked;
                    this.mConfiguration.updateParametersFrom(sQLiteDatabaseConfiguration);
                    setMaxConnectionPoolSizeLocked(0);
                }
                wakeConnectionWaitersLocked();
            } else {
                throw new IllegalStateException("Foreign Key Constraints cannot be enabled or disabled while there are transactions in progress.  Finish all transactions and release all active database connections first.");
            }
        }
    }

    public SQLiteConnection acquireConnection(String str, int i, CancellationSignal cancellationSignal) {
        long uptimeMillis = SystemClock.uptimeMillis();
        SQLiteConnection waitForConnection = waitForConnection(str, i, cancellationSignal);
        if (this.mTraceCallback != null) {
            long uptimeMillis2 = SystemClock.uptimeMillis() - uptimeMillis;
            SQLiteDatabase sQLiteDatabase = (SQLiteDatabase) this.mDB.get();
            if (sQLiteDatabase != null) {
                this.mTraceCallback.onConnectionObtained(sQLiteDatabase, str, uptimeMillis2, (i & 2) != 0);
            }
        }
        return waitForConnection;
    }

    public void releaseConnection(SQLiteConnection sQLiteConnection) {
        synchronized (this.mLock) {
            AcquiredConnectionStatus acquiredConnectionStatus = (AcquiredConnectionStatus) this.mAcquiredConnections.remove(sQLiteConnection);
            if (acquiredConnectionStatus == null) {
                throw new IllegalStateException("Cannot perform this operation because the specified connection was not acquired from this pool or has already been released.");
            }
            if (!this.mIsOpen) {
                closeConnectionAndLogExceptionsLocked(sQLiteConnection);
            } else if (sQLiteConnection.isPrimaryConnection()) {
                if (recycleConnectionLocked(sQLiteConnection, acquiredConnectionStatus)) {
                    if ($assertionsDisabled || this.mAvailablePrimaryConnection == null) {
                        this.mAvailablePrimaryConnection = sQLiteConnection;
                    } else {
                        throw new AssertionError();
                    }
                }
                wakeConnectionWaitersLocked();
            } else if (this.mAvailableNonPrimaryConnections.size() >= this.mMaxConnectionPoolSize - 1) {
                closeConnectionAndLogExceptionsLocked(sQLiteConnection);
            } else {
                if (recycleConnectionLocked(sQLiteConnection, acquiredConnectionStatus)) {
                    this.mAvailableNonPrimaryConnections.add(sQLiteConnection);
                }
                wakeConnectionWaitersLocked();
            }
        }
    }

    private boolean recycleConnectionLocked(SQLiteConnection sQLiteConnection, AcquiredConnectionStatus acquiredConnectionStatus) {
        if (acquiredConnectionStatus == AcquiredConnectionStatus.RECONFIGURE) {
            try {
                sQLiteConnection.reconfigure(this.mConfiguration);
            } catch (RuntimeException e) {
                Log.e(TAG, "Failed to reconfigure released connection, closing it: " + sQLiteConnection, e);
                acquiredConnectionStatus = AcquiredConnectionStatus.DISCARD;
            }
        }
        if (acquiredConnectionStatus != AcquiredConnectionStatus.DISCARD) {
            return true;
        }
        closeConnectionAndLogExceptionsLocked(sQLiteConnection);
        return false;
    }

    public boolean shouldYieldConnection(SQLiteConnection sQLiteConnection, int i) {
        boolean isSessionBlockingImportantConnectionWaitersLocked;
        synchronized (this.mLock) {
            if (!this.mAcquiredConnections.containsKey(sQLiteConnection)) {
                throw new IllegalStateException("Cannot perform this operation because the specified connection was not acquired from this pool or has already been released.");
            } else if (this.mIsOpen) {
                isSessionBlockingImportantConnectionWaitersLocked = isSessionBlockingImportantConnectionWaitersLocked(sQLiteConnection.isPrimaryConnection(), i);
            } else {
                isSessionBlockingImportantConnectionWaitersLocked = false;
            }
        }
        return isSessionBlockingImportantConnectionWaitersLocked;
    }

    public void collectDbStats(ArrayList<DbStats> arrayList) {
        synchronized (this.mLock) {
            if (this.mAvailablePrimaryConnection != null) {
                this.mAvailablePrimaryConnection.collectDbStats(arrayList);
            }
            Iterator it = this.mAvailableNonPrimaryConnections.iterator();
            while (it.hasNext()) {
                ((SQLiteConnection) it.next()).collectDbStats(arrayList);
            }
            for (SQLiteConnection collectDbStatsUnsafe : this.mAcquiredConnections.keySet()) {
                collectDbStatsUnsafe.collectDbStatsUnsafe(arrayList);
            }
        }
    }

    private SQLiteConnection openConnectionLocked(SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration, boolean z) {
        int i = this.mNextConnectionId;
        this.mNextConnectionId = i + 1;
        return SQLiteConnection.open(this, sQLiteDatabaseConfiguration, i, z, this.mPassword, this.mCipher);
    }

    void onConnectionLeaked() {
        Log.w(TAG, "A SQLiteConnection object for database '" + this.mConfiguration.label + "' was leaked!  Please fix your application " + "to end transactions in progress properly and to close the database " + "when it is no longer needed.");
        this.mConnectionLeaked.set(true);
    }

    private void closeAvailableConnectionsAndLogExceptionsLocked() {
        closeAvailableNonPrimaryConnectionsAndLogExceptionsLocked();
        if (this.mAvailablePrimaryConnection != null) {
            closeConnectionAndLogExceptionsLocked(this.mAvailablePrimaryConnection);
            this.mAvailablePrimaryConnection = null;
        }
    }

    private void closeAvailableNonPrimaryConnectionsAndLogExceptionsLocked() {
        int size = this.mAvailableNonPrimaryConnections.size();
        for (int i = 0; i < size; i++) {
            closeConnectionAndLogExceptionsLocked((SQLiteConnection) this.mAvailableNonPrimaryConnections.get(i));
        }
        this.mAvailableNonPrimaryConnections.clear();
    }

    private void closeExcessConnectionsAndLogExceptionsLocked() {
        int size = this.mAvailableNonPrimaryConnections.size();
        while (true) {
            int i = size - 1;
            if (size > this.mMaxConnectionPoolSize - 1) {
                closeConnectionAndLogExceptionsLocked((SQLiteConnection) this.mAvailableNonPrimaryConnections.remove(i));
                size = i;
            } else {
                return;
            }
        }
    }

    private void closeConnectionAndLogExceptionsLocked(SQLiteConnection sQLiteConnection) {
        try {
            sQLiteConnection.close();
        } catch (RuntimeException e) {
            Log.e(TAG, "Failed to close connection, its fate is now in the hands of the merciful GC: " + sQLiteConnection + e.getMessage());
        }
    }

    private void discardAcquiredConnectionsLocked() {
        markAcquiredConnectionsLocked(AcquiredConnectionStatus.DISCARD);
    }

    private void reconfigureAllConnectionsLocked() {
        if (this.mAvailablePrimaryConnection != null) {
            try {
                this.mAvailablePrimaryConnection.reconfigure(this.mConfiguration);
            } catch (RuntimeException e) {
                Log.e(TAG, "Failed to reconfigure available primary connection, closing it: " + this.mAvailablePrimaryConnection, e);
                closeConnectionAndLogExceptionsLocked(this.mAvailablePrimaryConnection);
                this.mAvailablePrimaryConnection = null;
            }
        }
        int size = this.mAvailableNonPrimaryConnections.size();
        int i = 0;
        while (i < size) {
            int i2;
            SQLiteConnection sQLiteConnection = (SQLiteConnection) this.mAvailableNonPrimaryConnections.get(i);
            try {
                sQLiteConnection.reconfigure(this.mConfiguration);
                i2 = i;
                i = size;
            } catch (RuntimeException e2) {
                Log.e(TAG, "Failed to reconfigure available non-primary connection, closing it: " + sQLiteConnection, e2);
                closeConnectionAndLogExceptionsLocked(sQLiteConnection);
                i2 = i - 1;
                this.mAvailableNonPrimaryConnections.remove(i);
                i = size - 1;
            }
            size = i;
            i = i2 + 1;
        }
        markAcquiredConnectionsLocked(AcquiredConnectionStatus.RECONFIGURE);
    }

    private void markAcquiredConnectionsLocked(AcquiredConnectionStatus acquiredConnectionStatus) {
        if (!this.mAcquiredConnections.isEmpty()) {
            ArrayList arrayList = new ArrayList(this.mAcquiredConnections.size());
            for (Entry entry : this.mAcquiredConnections.entrySet()) {
                AcquiredConnectionStatus acquiredConnectionStatus2 = (AcquiredConnectionStatus) entry.getValue();
                if (!(acquiredConnectionStatus == acquiredConnectionStatus2 || acquiredConnectionStatus2 == AcquiredConnectionStatus.DISCARD)) {
                    arrayList.add(entry.getKey());
                }
            }
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                this.mAcquiredConnections.put(arrayList.get(i), acquiredConnectionStatus);
            }
        }
    }

    private SQLiteConnection waitForConnection(String str, int i, CancellationSignal cancellationSignal) {
        SQLiteConnection sQLiteConnection;
        boolean z = (i & 2) != 0;
        synchronized (this.mLock) {
            throwIfClosedLocked();
            if (cancellationSignal != null) {
                cancellationSignal.throwIfCanceled();
            }
            sQLiteConnection = null;
            if (!z) {
                sQLiteConnection = tryAcquireNonPrimaryConnectionLocked(str, i);
            }
            if (sQLiteConnection == null) {
                sQLiteConnection = tryAcquirePrimaryConnectionLocked(i);
            }
            if (sQLiteConnection != null) {
            } else {
                int priority = getPriority(i);
                final a obtainConnectionWaiterLocked = obtainConnectionWaiterLocked(Thread.currentThread(), SystemClock.uptimeMillis(), priority, z, str, i);
                a aVar = null;
                for (a aVar2 = this.mConnectionWaiterQueue; aVar2 != null; aVar2 = aVar2.a) {
                    if (priority > aVar2.d) {
                        obtainConnectionWaiterLocked.a = aVar2;
                        break;
                    }
                    aVar = aVar2;
                }
                if (aVar != null) {
                    aVar.a = obtainConnectionWaiterLocked;
                } else {
                    this.mConnectionWaiterQueue = obtainConnectionWaiterLocked;
                }
                final int i2 = obtainConnectionWaiterLocked.j;
                if (cancellationSignal != null) {
                    cancellationSignal.setOnCancelListener(new OnCancelListener(this) {
                        final /* synthetic */ SQLiteConnectionPool c;

                        public void onCancel() {
                            synchronized (this.c.mLock) {
                                if (obtainConnectionWaiterLocked.j == i2) {
                                    this.c.cancelConnectionWaiterLocked(obtainConnectionWaiterLocked);
                                }
                            }
                        }
                    });
                }
                try {
                    RuntimeException runtimeException;
                    long j = obtainConnectionWaiterLocked.c + CONNECTION_POOL_BUSY_MILLIS;
                    long j2 = CONNECTION_POOL_BUSY_MILLIS;
                    long j3 = j;
                    while (true) {
                        if (this.mConnectionLeaked.compareAndSet(true, false)) {
                            synchronized (this.mLock) {
                                wakeConnectionWaitersLocked();
                            }
                        }
                        LockSupport.parkNanos(j2 * 1000000);
                        Thread.interrupted();
                        synchronized (this.mLock) {
                            throwIfClosedLocked();
                            sQLiteConnection = obtainConnectionWaiterLocked.h;
                            runtimeException = obtainConnectionWaiterLocked.i;
                            if (sQLiteConnection == null && runtimeException == null) {
                                j2 = SystemClock.uptimeMillis();
                                if (j2 < j3) {
                                    j = j3;
                                    j3 = j2 - j3;
                                    j2 = j;
                                } else {
                                    logConnectionPoolBusyLocked(str, j2 - obtainConnectionWaiterLocked.c, i);
                                    j3 = CONNECTION_POOL_BUSY_MILLIS;
                                    j2 += CONNECTION_POOL_BUSY_MILLIS;
                                }
                            } else {
                                recycleConnectionWaiterLocked(obtainConnectionWaiterLocked);
                            }
                        }
                        j = j2;
                        j2 = j3;
                        j3 = j;
                    }
                    recycleConnectionWaiterLocked(obtainConnectionWaiterLocked);
                    if (sQLiteConnection != null) {
                        if (cancellationSignal != null) {
                            cancellationSignal.setOnCancelListener(null);
                        }
                    } else {
                        throw runtimeException;
                    }
                } catch (Throwable th) {
                    if (cancellationSignal != null) {
                        cancellationSignal.setOnCancelListener(null);
                    }
                }
            }
        }
        return sQLiteConnection;
    }

    private void cancelConnectionWaiterLocked(a aVar) {
        if (aVar.h == null && aVar.i == null) {
            a aVar2 = null;
            a aVar3 = this.mConnectionWaiterQueue;
            while (aVar3 != aVar) {
                if ($assertionsDisabled || aVar3 != null) {
                    aVar2 = aVar3;
                    aVar3 = aVar3.a;
                } else {
                    throw new AssertionError();
                }
            }
            if (aVar2 != null) {
                aVar2.a = aVar.a;
            } else {
                this.mConnectionWaiterQueue = aVar.a;
            }
            aVar.i = new OperationCanceledException();
            LockSupport.unpark(aVar.b);
            wakeConnectionWaitersLocked();
        }
    }

    public void logConnectionPoolBusy(String str) {
        synchronized (this.mLock) {
            logConnectionPoolBusyLocked(str, 0, 0);
        }
    }

    private void logConnectionPoolBusyLocked(String str, long j, int i) {
        int i2;
        int i3;
        int i4;
        StringBuilder stringBuilder = new StringBuilder();
        if (j != 0) {
            Thread currentThread = Thread.currentThread();
            stringBuilder.append("The connection pool for database '").append(this.mConfiguration.label);
            stringBuilder.append("' has been unable to grant a connection to thread ");
            stringBuilder.append(currentThread.getId()).append(" (").append(currentThread.getName()).append(") ");
            stringBuilder.append("with flags 0x").append(Integer.toHexString(i));
            stringBuilder.append(" for ").append(((float) j) * 0.001f).append(" seconds.\n");
        }
        List arrayList = new ArrayList();
        if (this.mAcquiredConnections.isEmpty()) {
            i2 = 0;
            i3 = 0;
        } else {
            i2 = 0;
            i3 = 0;
            for (SQLiteConnection describeCurrentOperationUnsafe : this.mAcquiredConnections.keySet()) {
                String describeCurrentOperationUnsafe2 = describeCurrentOperationUnsafe.describeCurrentOperationUnsafe();
                if (describeCurrentOperationUnsafe2 != null) {
                    arrayList.add(describeCurrentOperationUnsafe2);
                    i4 = i2;
                    i2 = i3 + 1;
                } else {
                    i4 = i2 + 1;
                    i2 = i3;
                }
                i3 = i2;
                i2 = i4;
            }
        }
        i4 = this.mAvailableNonPrimaryConnections.size();
        if (this.mAvailablePrimaryConnection != null) {
            i4++;
        }
        stringBuilder.append("Connections: ").append(i3).append(" active, ");
        stringBuilder.append(i2).append(" idle, ");
        stringBuilder.append(i4).append(" available.\n");
        if (!arrayList.isEmpty()) {
            stringBuilder.append("\nRequests in progress:\n");
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                stringBuilder.append("  ").append((String) it.next()).append("\n");
            }
        }
        String stringBuilder2 = stringBuilder.toString();
        Log.w(TAG, stringBuilder2);
        SQLiteDatabase sQLiteDatabase = (SQLiteDatabase) this.mDB.get();
        if (sQLiteDatabase != null && this.mTraceCallback != null) {
            this.mTraceCallback.onConnectionPoolBusy(sQLiteDatabase, str, arrayList, stringBuilder2);
        }
    }

    private void wakeConnectionWaitersLocked() {
        Object obj;
        RuntimeException runtimeException;
        RuntimeException runtimeException2;
        int i;
        a aVar = this.mConnectionWaiterQueue;
        Object obj2 = null;
        Object obj3 = null;
        a aVar2 = null;
        while (aVar != null) {
            a aVar3;
            if (this.mIsOpen) {
                try {
                    SQLiteConnection sQLiteConnection;
                    if (aVar.e || obj2 != null) {
                        obj = obj2;
                        sQLiteConnection = null;
                    } else {
                        SQLiteConnection tryAcquireNonPrimaryConnectionLocked = tryAcquireNonPrimaryConnectionLocked(aVar.f, aVar.g);
                        if (tryAcquireNonPrimaryConnectionLocked == null) {
                            sQLiteConnection = tryAcquireNonPrimaryConnectionLocked;
                            obj = 1;
                        } else {
                            SQLiteConnection sQLiteConnection2 = tryAcquireNonPrimaryConnectionLocked;
                            obj = obj2;
                            sQLiteConnection = sQLiteConnection2;
                        }
                    }
                    if (sQLiteConnection == null && obj3 == null) {
                        try {
                            sQLiteConnection = tryAcquirePrimaryConnectionLocked(aVar.g);
                            if (sQLiteConnection == null) {
                                obj3 = 1;
                            }
                        } catch (RuntimeException e) {
                            runtimeException = e;
                            obj2 = obj;
                            obj = obj3;
                            runtimeException2 = runtimeException;
                            aVar.i = runtimeException2;
                            obj3 = obj;
                            obj = obj2;
                            i = 1;
                            aVar3 = aVar.a;
                            if (obj2 != null) {
                                aVar2 = aVar;
                            } else {
                                if (aVar2 != null) {
                                    this.mConnectionWaiterQueue = aVar3;
                                } else {
                                    aVar2.a = aVar3;
                                }
                                aVar.a = null;
                                LockSupport.unpark(aVar.b);
                            }
                            obj2 = obj;
                            aVar = aVar3;
                        }
                    }
                    if (sQLiteConnection != null) {
                        aVar.h = sQLiteConnection;
                        i = 1;
                    } else if (obj == null || r6 == null) {
                        obj2 = null;
                    } else {
                        return;
                    }
                } catch (RuntimeException e2) {
                    runtimeException = e2;
                    obj = obj3;
                    runtimeException2 = runtimeException;
                    aVar.i = runtimeException2;
                    obj3 = obj;
                    obj = obj2;
                    i = 1;
                    aVar3 = aVar.a;
                    if (obj2 != null) {
                        if (aVar2 != null) {
                            aVar2.a = aVar3;
                        } else {
                            this.mConnectionWaiterQueue = aVar3;
                        }
                        aVar.a = null;
                        LockSupport.unpark(aVar.b);
                    } else {
                        aVar2 = aVar;
                    }
                    obj2 = obj;
                    aVar = aVar3;
                }
            } else {
                obj = obj2;
                obj2 = 1;
            }
            aVar3 = aVar.a;
            if (obj2 != null) {
                if (aVar2 != null) {
                    aVar2.a = aVar3;
                } else {
                    this.mConnectionWaiterQueue = aVar3;
                }
                aVar.a = null;
                LockSupport.unpark(aVar.b);
            } else {
                aVar2 = aVar;
            }
            obj2 = obj;
            aVar = aVar3;
        }
    }

    private SQLiteConnection tryAcquirePrimaryConnectionLocked(int i) {
        SQLiteConnection sQLiteConnection = this.mAvailablePrimaryConnection;
        if (sQLiteConnection != null) {
            this.mAvailablePrimaryConnection = null;
            finishAcquireConnectionLocked(sQLiteConnection, i);
            return sQLiteConnection;
        }
        for (SQLiteConnection sQLiteConnection2 : this.mAcquiredConnections.keySet()) {
            if (sQLiteConnection2.isPrimaryConnection()) {
                return null;
            }
        }
        sQLiteConnection2 = openConnectionLocked(this.mConfiguration, true);
        finishAcquireConnectionLocked(sQLiteConnection2, i);
        return sQLiteConnection2;
    }

    private SQLiteConnection tryAcquireNonPrimaryConnectionLocked(String str, int i) {
        SQLiteConnection sQLiteConnection;
        int size = this.mAvailableNonPrimaryConnections.size();
        if (size > 1 && str != null) {
            for (int i2 = 0; i2 < size; i2++) {
                sQLiteConnection = (SQLiteConnection) this.mAvailableNonPrimaryConnections.get(i2);
                if (sQLiteConnection.isPreparedStatementInCache(str)) {
                    this.mAvailableNonPrimaryConnections.remove(i2);
                    finishAcquireConnectionLocked(sQLiteConnection, i);
                    return sQLiteConnection;
                }
            }
        }
        if (size > 0) {
            sQLiteConnection = (SQLiteConnection) this.mAvailableNonPrimaryConnections.remove(size - 1);
            finishAcquireConnectionLocked(sQLiteConnection, i);
            return sQLiteConnection;
        }
        int size2 = this.mAcquiredConnections.size();
        if (this.mAvailablePrimaryConnection != null) {
            size2++;
        }
        if (size2 >= this.mMaxConnectionPoolSize) {
            return null;
        }
        sQLiteConnection = openConnectionLocked(this.mConfiguration, false);
        finishAcquireConnectionLocked(sQLiteConnection, i);
        return sQLiteConnection;
    }

    private void finishAcquireConnectionLocked(SQLiteConnection sQLiteConnection, int i) {
        try {
            sQLiteConnection.setOnlyAllowReadOnlyOperations((i & 1) != 0);
            this.mAcquiredConnections.put(sQLiteConnection, AcquiredConnectionStatus.NORMAL);
        } catch (RuntimeException e) {
            Log.e(TAG, "Failed to prepare acquired connection for session, closing it: " + sQLiteConnection + ", connectionFlags=" + i);
            closeConnectionAndLogExceptionsLocked(sQLiteConnection);
            throw e;
        }
    }

    private boolean isSessionBlockingImportantConnectionWaitersLocked(boolean z, int i) {
        a aVar = this.mConnectionWaiterQueue;
        if (aVar != null) {
            int priority = getPriority(i);
            while (priority <= aVar.d) {
                if (!z && aVar.e) {
                    aVar = aVar.a;
                    if (aVar == null) {
                        break;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private static int getPriority(int i) {
        return (i & 4) != 0 ? 1 : 0;
    }

    private void setMaxConnectionPoolSizeLocked(int i) {
        if (i <= 0) {
            if ((this.mConfiguration.openFlags & SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING) != 0) {
                i = 4;
            } else {
                i = 1;
            }
        }
        this.mMaxConnectionPoolSize = i;
        Log.i(TAG, "Max connection pool size is %d.", Integer.valueOf(this.mMaxConnectionPoolSize));
    }

    private void throwIfClosedLocked() {
        if (!this.mIsOpen) {
            throw new IllegalStateException("Cannot perform this operation because the connection pool has been closed.");
        }
    }

    private a obtainConnectionWaiterLocked(Thread thread, long j, int i, boolean z, String str, int i2) {
        a aVar = this.mConnectionWaiterPool;
        if (aVar != null) {
            this.mConnectionWaiterPool = aVar.a;
            aVar.a = null;
        } else {
            aVar = new a();
        }
        aVar.b = thread;
        aVar.c = j;
        aVar.d = i;
        aVar.e = z;
        aVar.f = str;
        aVar.g = i2;
        return aVar;
    }

    private void recycleConnectionWaiterLocked(a aVar) {
        aVar.a = this.mConnectionWaiterPool;
        aVar.b = null;
        aVar.f = null;
        aVar.h = null;
        aVar.i = null;
        aVar.j++;
        this.mConnectionWaiterPool = aVar;
    }

    SQLiteTrace getTraceCallback() {
        return this.mTraceCallback;
    }

    void setTraceCallback(SQLiteTrace sQLiteTrace) {
        this.mTraceCallback = sQLiteTrace;
    }

    void traceExecute(String str, int i, long j) {
        SQLiteDatabase sQLiteDatabase = (SQLiteDatabase) this.mDB.get();
        SQLiteTrace sQLiteTrace = this.mTraceCallback;
        if (sQLiteTrace != null && sQLiteDatabase != null) {
            sQLiteTrace.onSQLExecuted(sQLiteDatabase, str, i, j);
        }
    }

    SQLiteCheckpointListener getCheckpointListener() {
        return this.mCheckpointListener;
    }

    void setCheckpointListener(SQLiteCheckpointListener sQLiteCheckpointListener) {
        SQLiteDatabase sQLiteDatabase = (SQLiteDatabase) this.mDB.get();
        if (this.mCheckpointListener != null) {
            this.mCheckpointListener.onDetach(sQLiteDatabase);
        }
        this.mCheckpointListener = sQLiteCheckpointListener;
        if (this.mCheckpointListener != null) {
            this.mCheckpointListener.onAttach(sQLiteDatabase);
        }
    }

    void notifyCheckpoint(String str, int i) {
        SQLiteDatabase sQLiteDatabase = (SQLiteDatabase) this.mDB.get();
        SQLiteCheckpointListener sQLiteCheckpointListener = this.mCheckpointListener;
        if (sQLiteCheckpointListener != null && sQLiteDatabase != null) {
            sQLiteCheckpointListener.onWALCommit(sQLiteDatabase, str, i);
        }
    }

    public void dump(Printer printer, boolean z) {
        Printer create = PrefixPrinter.create(printer, "    ");
        synchronized (this.mLock) {
            int i;
            printer.println("Connection pool for " + this.mConfiguration.path + ":");
            printer.println("  Open: " + this.mIsOpen);
            printer.println("  Max connections: " + this.mMaxConnectionPoolSize);
            printer.println("  Available primary connection:");
            if (this.mAvailablePrimaryConnection != null) {
                this.mAvailablePrimaryConnection.dump(create, z);
            } else {
                create.println("<none>");
            }
            printer.println("  Available non-primary connections:");
            if (this.mAvailableNonPrimaryConnections.isEmpty()) {
                create.println("<none>");
            } else {
                int size = this.mAvailableNonPrimaryConnections.size();
                for (i = 0; i < size; i++) {
                    ((SQLiteConnection) this.mAvailableNonPrimaryConnections.get(i)).dump(create, z);
                }
            }
            printer.println("  Acquired connections:");
            if (this.mAcquiredConnections.isEmpty()) {
                create.println("<none>");
            } else {
                for (Entry entry : this.mAcquiredConnections.entrySet()) {
                    ((SQLiteConnection) entry.getKey()).dumpUnsafe(create, z);
                    create.println("  Status: " + entry.getValue());
                }
            }
            printer.println("  Connection waiters:");
            if (this.mConnectionWaiterQueue != null) {
                long uptimeMillis = SystemClock.uptimeMillis();
                a aVar = this.mConnectionWaiterQueue;
                i = 0;
                while (aVar != null) {
                    create.println(i + ": waited for " + (uptimeMillis - aVar.c) + " ms - thread=" + aVar.b + ", priority=" + aVar.d + ", sql='" + aVar.f + "'");
                    aVar = aVar.a;
                    i++;
                }
            } else {
                create.println("<none>");
            }
        }
    }

    public String toString() {
        return "SQLiteConnectionPool: " + this.mConfiguration.path;
    }
}
