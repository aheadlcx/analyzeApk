package com.tencent.wcdb.database;

import android.annotation.SuppressLint;
import android.util.Pair;
import android.util.Printer;
import com.meizu.cloud.pushsdk.pushtracer.constant.Parameters;
import com.tencent.wcdb.CursorWindow;
import com.tencent.wcdb.DatabaseUtils;
import com.tencent.wcdb.database.SQLiteDebug.DbStats;
import com.tencent.wcdb.support.CancellationSignal;
import com.tencent.wcdb.support.CancellationSignal.OnCancelListener;
import com.tencent.wcdb.support.Log;
import com.tencent.wcdb.support.LruCache;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public final class SQLiteConnection implements OnCancelListener {
    private static final boolean DEBUG = false;
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    private static final String TAG = "WCDB.SQLiteConnection";
    private static final Pattern TRIM_SQL_PATTERN = Pattern.compile("[\\s]*\\n+[\\s]*");
    private Thread mAcquiredThread;
    private int mAcquiredTid;
    private int mCancellationSignalAttachCount;
    private SQLiteCipherSpec mCipher;
    private final SQLiteDatabaseConfiguration mConfiguration;
    private final int mConnectionId;
    private long mConnectionPtr;
    private final boolean mIsPrimaryConnection;
    private final boolean mIsReadOnlyConnection;
    private int mNativeHandleCount;
    private a mNativeOperation;
    private boolean mOnlyAllowReadOnlyOperations;
    private byte[] mPassword;
    private final SQLiteConnectionPool mPool;
    private final d mPreparedStatementCache;
    private c mPreparedStatementPool;
    private final b mRecentOperations = new b();

    @SuppressLint({"SimpleDateFormat"})
    private static final class a {
        private static final SimpleDateFormat k = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        public long a;
        public long b;
        public String c;
        public String d;
        public ArrayList<Object> e;
        public boolean f;
        public Exception g;
        public int h;
        public int i;
        public int j;

        private a() {
        }

        public void a(StringBuilder stringBuilder, boolean z) {
            stringBuilder.append(this.c);
            if (this.f) {
                stringBuilder.append(" took ").append(this.b - this.a).append(Parameters.MESSAGE_SEQ);
            } else {
                stringBuilder.append(" started ").append(System.currentTimeMillis() - this.a).append("ms ago");
            }
            stringBuilder.append(" - ").append(a());
            if (this.d != null) {
                stringBuilder.append(", sql=\"").append(SQLiteConnection.trimSqlForDisplay(this.d)).append("\"");
            }
            if (this.j > 0) {
                stringBuilder.append(", tid=").append(this.j);
            }
            if (!(!z || this.e == null || this.e.size() == 0)) {
                stringBuilder.append(", bindArgs=[");
                int size = this.e.size();
                for (int i = 0; i < size; i++) {
                    Object obj = this.e.get(i);
                    if (i != 0) {
                        stringBuilder.append(", ");
                    }
                    if (obj == null) {
                        stringBuilder.append("null");
                    } else if (obj instanceof byte[]) {
                        stringBuilder.append("<byte[]>");
                    } else if (obj instanceof String) {
                        stringBuilder.append("\"").append((String) obj).append("\"");
                    } else {
                        stringBuilder.append(obj);
                    }
                }
                stringBuilder.append("]");
            }
            if (this.g != null && this.g.getMessage() != null) {
                stringBuilder.append(", exception=\"").append(this.g.getMessage()).append("\"");
            }
        }

        private String a() {
            if (this.f) {
                return this.g != null ? "failed" : "succeeded";
            } else {
                return "running";
            }
        }

        private String b() {
            return k.format(new Date(this.a));
        }
    }

    private final class b {
        final /* synthetic */ SQLiteConnection a;
        private final a[] b;
        private int c;
        private int d;

        private b(SQLiteConnection sQLiteConnection) {
            this.a = sQLiteConnection;
            this.b = new a[20];
        }

        public a a(String str, String str2, Object[] objArr) {
            a aVar;
            int i = 0;
            synchronized (this.b) {
                int i2 = (this.c + 1) % 20;
                aVar = this.b[i2];
                if (aVar == null) {
                    aVar = new a();
                    this.b[i2] = aVar;
                } else {
                    aVar.f = false;
                    aVar.g = null;
                    if (aVar.e != null) {
                        aVar.e.clear();
                    }
                }
                aVar.a = System.currentTimeMillis();
                aVar.c = str;
                aVar.d = str2;
                if (objArr != null) {
                    if (aVar.e == null) {
                        aVar.e = new ArrayList();
                    } else {
                        aVar.e.clear();
                    }
                    while (i < objArr.length) {
                        Object obj = objArr[i];
                        if (obj == null || !(obj instanceof byte[])) {
                            aVar.e.add(obj);
                        } else {
                            aVar.e.add(SQLiteConnection.EMPTY_BYTE_ARRAY);
                        }
                        i++;
                    }
                }
                aVar.h = c(i2);
                aVar.j = this.a.mAcquiredTid;
                this.c = i2;
            }
            return aVar;
        }

        public void a(int i, Exception exception) {
            synchronized (this.b) {
                a d = d(i);
                if (d != null) {
                    d.g = exception;
                }
            }
        }

        public void a(int i) {
            synchronized (this.b) {
                a d = d(i);
                if (a(d)) {
                    a(d, null);
                }
                String str = d.d;
                String str2 = d.c;
                int i2 = d.i;
                long j = d.b - d.a;
            }
            if (!"prepare".equals(str2)) {
                this.a.mPool.traceExecute(str, i2, j);
            }
        }

        public boolean b(int i) {
            boolean z;
            synchronized (this.b) {
                a d = d(i);
                if (d == null) {
                    z = false;
                } else {
                    z = a(d);
                    String str = d.d;
                    String str2 = d.c;
                    int i2 = d.i;
                    long j = d.b - d.a;
                    if (!"prepare".equals(str2)) {
                        this.a.mPool.traceExecute(str, i2, j);
                    }
                }
            }
            return z;
        }

        public void a(int i, String str) {
            synchronized (this.b) {
                a d = d(i);
                if (d != null) {
                    a(d, str);
                }
            }
        }

        private boolean a(a aVar) {
            if (aVar == null) {
                return false;
            }
            aVar.b = System.currentTimeMillis();
            aVar.f = true;
            if (aVar.g == null || aVar.g.getMessage() == null) {
                return SQLiteDebug.shouldLogSlowQuery(aVar.b - aVar.a);
            }
            return true;
        }

        private void a(a aVar, String str) {
            StringBuilder stringBuilder = new StringBuilder();
            aVar.a(stringBuilder, false);
            if (str != null) {
                stringBuilder.append(", ").append(str);
            }
            Log.i(SQLiteConnection.TAG, stringBuilder.toString());
        }

        private int c(int i) {
            int i2 = this.d;
            this.d = i2 + 1;
            return (i2 << 8) | i;
        }

        private a d(int i) {
            a aVar = this.b[i & 255];
            return aVar.h == i ? aVar : null;
        }

        public String a() {
            String str;
            synchronized (this.b) {
                a aVar = this.b[this.c];
                if (aVar == null || aVar.f) {
                    str = null;
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    aVar.a(stringBuilder, false);
                    str = stringBuilder.toString();
                }
            }
            return str;
        }

        public void a(Printer printer, boolean z) {
            synchronized (this.b) {
                printer.println("  Most recently executed operations:");
                int i = this.c;
                a aVar = this.b[i];
                if (aVar != null) {
                    a aVar2 = aVar;
                    int i2 = 0;
                    while (true) {
                        int i3;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("    ").append(i2).append(": [");
                        stringBuilder.append(aVar2.b());
                        stringBuilder.append("] ");
                        aVar2.a(stringBuilder, z);
                        printer.println(stringBuilder.toString());
                        if (i > 0) {
                            i3 = i - 1;
                        } else {
                            i3 = 19;
                        }
                        i2++;
                        a aVar3 = this.b[i3];
                        if (aVar3 == null || i2 >= 20) {
                            break;
                        }
                        a aVar4 = aVar3;
                        i = i3;
                        aVar2 = aVar4;
                    }
                } else {
                    printer.println("    <none>");
                }
            }
        }
    }

    static final class c {
        private WeakReference<SQLiteConnection> a;
        private c b;
        private String c;
        private long d;
        private int e;
        private int f;
        private boolean g;
        private boolean h;
        private boolean i;
        private a j;

        c(SQLiteConnection sQLiteConnection) {
            this.a = new WeakReference(sQLiteConnection);
        }

        public void a(Object[] objArr) {
            SQLiteConnection sQLiteConnection = (SQLiteConnection) this.a.get();
            if (sQLiteConnection != null) {
                sQLiteConnection.bindArguments(this, objArr);
            }
        }

        public void a(boolean z) {
            SQLiteConnection sQLiteConnection = (SQLiteConnection) this.a.get();
            if (sQLiteConnection != null) {
                sQLiteConnection.resetStatement(this, z);
            }
        }

        public long a() {
            return this.d;
        }

        public void a(CancellationSignal cancellationSignal) {
            SQLiteConnection sQLiteConnection = (SQLiteConnection) this.a.get();
            if (sQLiteConnection != null) {
                sQLiteConnection.attachCancellationSignal(cancellationSignal);
            }
        }

        public void b(CancellationSignal cancellationSignal) {
            SQLiteConnection sQLiteConnection = (SQLiteConnection) this.a.get();
            if (sQLiteConnection != null) {
                sQLiteConnection.detachCancellationSignal(cancellationSignal);
            }
        }

        public void a(String str, Object[] objArr) {
            SQLiteConnection sQLiteConnection = (SQLiteConnection) this.a.get();
            if (sQLiteConnection != null) {
                this.j = sQLiteConnection.mRecentOperations.a(str, this.c, objArr);
                this.j.i = this.f;
            }
        }

        public void a(Exception exception) {
            if (this.j != null) {
                SQLiteConnection sQLiteConnection = (SQLiteConnection) this.a.get();
                if (sQLiteConnection != null) {
                    sQLiteConnection.mRecentOperations.a(this.j.h, exception);
                }
            }
        }

        public void a(String str) {
            if (this.j != null) {
                SQLiteConnection sQLiteConnection = (SQLiteConnection) this.a.get();
                if (sQLiteConnection != null) {
                    if (sQLiteConnection.mRecentOperations.b(this.j.h)) {
                        sQLiteConnection.mRecentOperations.a(this.j.h, str);
                    }
                    this.j = null;
                }
            }
        }
    }

    private final class d extends LruCache<String, c> {
        final /* synthetic */ SQLiteConnection a;

        protected /* synthetic */ void entryRemoved(boolean z, Object obj, Object obj2, Object obj3) {
            a(z, (String) obj, (c) obj2, (c) obj3);
        }

        public d(SQLiteConnection sQLiteConnection, int i) {
            this.a = sQLiteConnection;
            super(i);
        }

        protected void a(boolean z, String str, c cVar, c cVar2) {
            cVar.h = false;
            if (!cVar.i) {
                this.a.finalizePreparedStatement(cVar);
            }
        }

        public void a(Printer printer) {
            printer.println("  Prepared statement cache:");
            Map snapshot = snapshot();
            if (snapshot.isEmpty()) {
                printer.println("    <none>");
                return;
            }
            int i = 0;
            for (Entry entry : snapshot.entrySet()) {
                c cVar = (c) entry.getValue();
                if (cVar.h) {
                    printer.println("    " + i + ": statementPtr=0x" + Long.toHexString(cVar.a()) + ", numParameters=" + cVar.e + ", type=" + cVar.f + ", readOnly=" + cVar.g + ", sql=\"" + SQLiteConnection.trimSqlForDisplay((String) entry.getKey()) + "\"");
                }
                i++;
            }
        }
    }

    private static native void nativeBindBlob(long j, long j2, int i, byte[] bArr);

    private static native void nativeBindDouble(long j, long j2, int i, double d);

    private static native void nativeBindLong(long j, long j2, int i, long j3);

    private static native void nativeBindNull(long j, long j2, int i);

    private static native void nativeBindString(long j, long j2, int i, String str);

    private static native void nativeCancel(long j);

    private static native void nativeClose(long j);

    private static native void nativeExecute(long j, long j2);

    private static native int nativeExecuteForChangedRowCount(long j, long j2);

    private static native long nativeExecuteForCursorWindow(long j, long j2, long j3, int i, int i2, boolean z);

    private static native long nativeExecuteForLastInsertedRowId(long j, long j2);

    private static native long nativeExecuteForLong(long j, long j2);

    private static native String nativeExecuteForString(long j, long j2);

    private static native void nativeFinalizeStatement(long j, long j2);

    private static native int nativeGetColumnCount(long j, long j2);

    private static native String nativeGetColumnName(long j, long j2, int i);

    private static native int nativeGetDbLookaside(long j);

    private static native int nativeGetParameterCount(long j, long j2);

    private static native long nativeGetSQLiteHandle(long j);

    private static native boolean nativeIsReadOnly(long j, long j2);

    private native long nativeOpen(String str, int i, String str2);

    private static native long nativePrepareStatement(long j, String str);

    private static native void nativeRegisterCustomFunction(long j, SQLiteCustomFunction sQLiteCustomFunction);

    private static native void nativeRegisterLocalizedCollators(long j, String str);

    private static native void nativeResetCancel(long j, boolean z);

    private static native void nativeResetStatement(long j, long j2, boolean z);

    private static native void nativeSetKey(long j, byte[] bArr);

    private static native void nativeSetWalHook(long j);

    private static native long nativeWalCheckpoint(long j, String str);

    private SQLiteConnection(SQLiteConnectionPool sQLiteConnectionPool, SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration, int i, boolean z, byte[] bArr, SQLiteCipherSpec sQLiteCipherSpec) {
        SQLiteCipherSpec sQLiteCipherSpec2 = null;
        this.mPassword = bArr;
        if (sQLiteCipherSpec != null) {
            sQLiteCipherSpec2 = new SQLiteCipherSpec(sQLiteCipherSpec);
        }
        this.mCipher = sQLiteCipherSpec2;
        this.mPool = sQLiteConnectionPool;
        this.mConfiguration = new SQLiteDatabaseConfiguration(sQLiteDatabaseConfiguration);
        this.mConnectionId = i;
        this.mIsPrimaryConnection = z;
        this.mIsReadOnlyConnection = (sQLiteDatabaseConfiguration.openFlags & 1) != 0;
        this.mPreparedStatementCache = new d(this, this.mConfiguration.maxSqlCacheSize);
    }

    long getNativeHandle(String str) {
        if (this.mConnectionPtr == 0) {
            return 0;
        }
        if (str != null && this.mNativeOperation == null) {
            this.mNativeOperation = this.mRecentOperations.a(str, null, null);
            this.mNativeOperation.i = 99;
        }
        this.mNativeHandleCount++;
        return nativeGetSQLiteHandle(this.mConnectionPtr);
    }

    void endNativeHandle(Exception exception) {
        int i = this.mNativeHandleCount - 1;
        this.mNativeHandleCount = i;
        if (i == 0 && this.mNativeOperation != null) {
            if (exception == null) {
                this.mRecentOperations.b(this.mNativeOperation.h);
            } else {
                this.mRecentOperations.a(this.mNativeOperation.h, exception);
            }
            this.mNativeOperation = null;
        }
    }

    protected void finalize() throws Throwable {
        try {
            if (!(this.mPool == null || this.mConnectionPtr == 0)) {
                this.mPool.onConnectionLeaked();
            }
            dispose(true);
        } finally {
            super.finalize();
        }
    }

    static SQLiteConnection open(SQLiteConnectionPool sQLiteConnectionPool, SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration, int i, boolean z, byte[] bArr, SQLiteCipherSpec sQLiteCipherSpec) {
        SQLiteConnection sQLiteConnection = new SQLiteConnection(sQLiteConnectionPool, sQLiteDatabaseConfiguration, i, z, bArr, sQLiteCipherSpec);
        try {
            sQLiteConnection.open();
            return sQLiteConnection;
        } catch (SQLiteException e) {
            SQLiteDebug.collectLastIOTraceStats(sQLiteConnection);
            sQLiteConnection.dispose(false);
            throw e;
        }
    }

    void close() {
        dispose(false);
    }

    private void open() {
        this.mConnectionPtr = nativeOpen(this.mConfiguration.path, this.mConfiguration.openFlags, this.mConfiguration.vfsName);
        if (this.mPassword != null && this.mPassword.length == 0) {
            this.mPassword = null;
        }
        if (this.mPassword != null) {
            nativeSetKey(this.mConnectionPtr, this.mPassword);
            setCipherSpec();
        }
        setPageSize();
        setReadOnlyFromConfiguration();
        setForeignKeyModeFromConfiguration();
        setWalModeFromConfiguration();
        setSyncModeFromConfiguration();
        setJournalSizeLimit();
        setCheckpointStrategy();
        setLocaleFromConfiguration();
        int size = this.mConfiguration.customFunctions.size();
        for (int i = 0; i < size; i++) {
            nativeRegisterCustomFunction(this.mConnectionPtr, (SQLiteCustomFunction) this.mConfiguration.customFunctions.get(i));
        }
    }

    private void dispose(boolean z) {
        if (this.mConnectionPtr != 0) {
            int i = this.mRecentOperations.a("close", null, null).h;
            try {
                this.mPreparedStatementCache.evictAll();
                nativeClose(this.mConnectionPtr);
                this.mConnectionPtr = 0;
            } finally {
                this.mRecentOperations.a(i);
            }
        }
    }

    private void setPageSize() {
        if (!this.mConfiguration.isInMemoryDb()) {
            String str;
            long j;
            if (this.mPassword != null) {
                str = "PRAGMA cipher_page_size";
                j = (this.mCipher == null || this.mCipher.pageSize <= 0) ? (long) SQLiteGlobal.defaultPageSize : (long) this.mCipher.pageSize;
            } else {
                str = "PRAGMA page_size";
                j = (long) SQLiteGlobal.defaultPageSize;
            }
            if (executeForLong(str, null, null) != j) {
                execute(str + "=" + j, null, null);
            }
        }
    }

    private void setCipherSpec() {
        if (this.mCipher != null) {
            if (this.mCipher.cipher != null) {
                execute("PRAGMA cipher=" + DatabaseUtils.sqlEscapeString(this.mCipher.cipher), null, null);
            }
            if (this.mCipher.kdfIteration != 0) {
                execute("PRAGMA kdf_iter=" + this.mCipher.kdfIteration, null, null);
            }
            execute("PRAGMA cipher_use_hmac=" + this.mCipher.hmacEnabled, null, null);
        }
    }

    private void notifyCheckpoint(String str, int i) {
        this.mPool.notifyCheckpoint(str, i);
    }

    private void setCheckpointStrategy() {
        if (!this.mConfiguration.isInMemoryDb() && !this.mIsReadOnlyConnection) {
            if (this.mConfiguration.customWALHookEnabled) {
                nativeSetWalHook(this.mConnectionPtr);
            } else if (executeForLong("PRAGMA wal_autocheckpoint", null, null) != 100) {
                executeForLong("PRAGMA wal_autocheckpoint=100", null, null);
            }
        }
    }

    private void setJournalSizeLimit() {
        if (!this.mConfiguration.isInMemoryDb() && !this.mIsReadOnlyConnection && executeForLong("PRAGMA journal_size_limit", null, null) != 524288) {
            executeForLong("PRAGMA journal_size_limit=524288", null, null);
        }
    }

    private void setForeignKeyModeFromConfiguration() {
        if (!this.mIsReadOnlyConnection) {
            long j = this.mConfiguration.foreignKeyConstraintsEnabled ? 1 : 0;
            if (executeForLong("PRAGMA foreign_keys", null, null) != j) {
                execute("PRAGMA foreign_keys=" + j, null, null);
            }
        }
    }

    private void setWalModeFromConfiguration() {
        if (!this.mConfiguration.isInMemoryDb() && !this.mIsReadOnlyConnection) {
            String str;
            if ((this.mConfiguration.openFlags & SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING) != 0) {
                str = "WAL";
            } else {
                str = SQLiteGlobal.defaultJournalMode;
            }
            setJournalMode(str);
        }
    }

    private void setSyncModeFromConfiguration() {
        execute("PRAGMA synchronous=" + this.mConfiguration.synchronousMode, null, null);
    }

    private void setJournalMode(String str) {
        String executeForString = executeForString("PRAGMA journal_mode", null, null);
        if (!executeForString.equalsIgnoreCase(str)) {
            try {
                if (executeForString("PRAGMA journal_mode=" + str, null, null).equalsIgnoreCase(str)) {
                    return;
                }
            } catch (SQLiteDatabaseLockedException e) {
            }
            Log.w(TAG, "Could not change the database journal mode of '" + this.mConfiguration.label + "' from '" + executeForString + "' to '" + str + "' because the database is locked.  This usually means that " + "there are other open connections to the database which prevents " + "the database from enabling or disabling write-ahead logging mode.  " + "Proceeding without changing the journal mode.");
        }
    }

    private void setLocaleFromConfiguration() {
        SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration = this.mConfiguration;
        sQLiteDatabaseConfiguration.openFlags |= 16;
        if ((this.mConfiguration.openFlags & 16) == 0) {
            String locale = this.mConfiguration.locale.toString();
            nativeRegisterLocalizedCollators(this.mConnectionPtr, locale);
            if (!this.mIsReadOnlyConnection) {
                try {
                    execute("CREATE TABLE IF NOT EXISTS android_metadata (locale TEXT)", null, null);
                    String executeForString = executeForString("SELECT locale FROM android_metadata UNION SELECT NULL ORDER BY locale DESC LIMIT 1", null, null);
                    if (executeForString == null || !executeForString.equals(locale)) {
                        execute("BEGIN", null, null);
                        execute("DELETE FROM android_metadata", null, null);
                        execute("INSERT INTO android_metadata (locale) VALUES(?)", new Object[]{locale}, null);
                        execute("REINDEX LOCALIZED", null, null);
                        execute("COMMIT", null, null);
                    }
                } catch (Throwable e) {
                    throw new SQLiteException("Failed to change locale for db '" + this.mConfiguration.label + "' to '" + locale + "'.", e);
                } catch (Throwable th) {
                    execute("ROLLBACK", null, null);
                }
            }
        }
    }

    private void setReadOnlyFromConfiguration() {
        if (this.mIsReadOnlyConnection) {
            execute("PRAGMA query_only = 1", null, null);
        }
    }

    void reconfigure(SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = true;
        this.mOnlyAllowReadOnlyOperations = false;
        int size = sQLiteDatabaseConfiguration.customFunctions.size();
        for (int i = 0; i < size; i++) {
            SQLiteCustomFunction sQLiteCustomFunction = (SQLiteCustomFunction) sQLiteDatabaseConfiguration.customFunctions.get(i);
            if (!this.mConfiguration.customFunctions.contains(sQLiteCustomFunction)) {
                nativeRegisterCustomFunction(this.mConnectionPtr, sQLiteCustomFunction);
            }
        }
        boolean z5 = ((sQLiteDatabaseConfiguration.openFlags ^ this.mConfiguration.openFlags) & SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING) != 0;
        if (sQLiteDatabaseConfiguration.foreignKeyConstraintsEnabled != this.mConfiguration.foreignKeyConstraintsEnabled) {
            z = true;
        } else {
            z = false;
        }
        if (sQLiteDatabaseConfiguration.locale.equals(this.mConfiguration.locale)) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (sQLiteDatabaseConfiguration.customWALHookEnabled != this.mConfiguration.customWALHookEnabled) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (sQLiteDatabaseConfiguration.synchronousMode == this.mConfiguration.synchronousMode) {
            z4 = false;
        }
        this.mConfiguration.updateParametersFrom(sQLiteDatabaseConfiguration);
        this.mPreparedStatementCache.resize(sQLiteDatabaseConfiguration.maxSqlCacheSize);
        if (z) {
            setForeignKeyModeFromConfiguration();
        }
        if (z5) {
            setWalModeFromConfiguration();
        }
        if (z4) {
            setSyncModeFromConfiguration();
        }
        if (z3) {
            setCheckpointStrategy();
        }
        if (z2) {
            setLocaleFromConfiguration();
        }
    }

    void setOnlyAllowReadOnlyOperations(boolean z) {
        this.mOnlyAllowReadOnlyOperations = z;
    }

    void setAcquisitionState(Thread thread, int i) {
        this.mAcquiredThread = thread;
        this.mAcquiredTid = i;
    }

    boolean isPreparedStatementInCache(String str) {
        return this.mPreparedStatementCache.get(str) != null;
    }

    public int getConnectionId() {
        return this.mConnectionId;
    }

    public boolean isPrimaryConnection() {
        return this.mIsPrimaryConnection;
    }

    public void prepare(String str, SQLiteStatementInfo sQLiteStatementInfo) {
        if (str == null) {
            throw new IllegalArgumentException("sql must not be null.");
        }
        a a = this.mRecentOperations.a("prepare", str, null);
        int i = a.h;
        c acquirePreparedStatement;
        try {
            acquirePreparedStatement = acquirePreparedStatement(str);
            a.i = acquirePreparedStatement.f;
            if (sQLiteStatementInfo != null) {
                sQLiteStatementInfo.numParameters = acquirePreparedStatement.e;
                sQLiteStatementInfo.readOnly = acquirePreparedStatement.g;
                int nativeGetColumnCount = nativeGetColumnCount(this.mConnectionPtr, acquirePreparedStatement.a());
                if (nativeGetColumnCount == 0) {
                    sQLiteStatementInfo.columnNames = EMPTY_STRING_ARRAY;
                } else {
                    sQLiteStatementInfo.columnNames = new String[nativeGetColumnCount];
                    for (int i2 = 0; i2 < nativeGetColumnCount; i2++) {
                        sQLiteStatementInfo.columnNames[i2] = nativeGetColumnName(this.mConnectionPtr, acquirePreparedStatement.a(), i2);
                    }
                }
            }
            releasePreparedStatement(acquirePreparedStatement);
            this.mRecentOperations.a(i);
        } catch (Exception e) {
            try {
                if (((e instanceof SQLiteDatabaseLockedException) || (e instanceof SQLiteTableLockedException)) && this.mPool != null) {
                    this.mPool.logConnectionPoolBusy(str);
                }
                this.mRecentOperations.a(i, e);
                throw e;
            } catch (Throwable th) {
                this.mRecentOperations.a(i);
            }
        } catch (Throwable th2) {
            releasePreparedStatement(acquirePreparedStatement);
        }
    }

    public void execute(String str, Object[] objArr, CancellationSignal cancellationSignal) {
        if (str == null) {
            throw new IllegalArgumentException("sql must not be null.");
        }
        a a = this.mRecentOperations.a("execute", str, objArr);
        int i = a.h;
        try {
            c acquirePreparedStatement = acquirePreparedStatement(str);
            a.i = acquirePreparedStatement.f;
            try {
                throwIfStatementForbidden(acquirePreparedStatement);
                bindArguments(acquirePreparedStatement, objArr);
                applyBlockGuardPolicy(acquirePreparedStatement);
                attachCancellationSignal(cancellationSignal);
                nativeExecute(this.mConnectionPtr, acquirePreparedStatement.a());
                detachCancellationSignal(cancellationSignal);
                releasePreparedStatement(acquirePreparedStatement);
                this.mRecentOperations.a(i);
            } catch (Throwable th) {
                releasePreparedStatement(acquirePreparedStatement);
            }
        } catch (Exception e) {
            try {
                if (((e instanceof SQLiteDatabaseLockedException) || (e instanceof SQLiteTableLockedException)) && this.mPool != null) {
                    this.mPool.logConnectionPoolBusy(str);
                }
                this.mRecentOperations.a(i, e);
                throw e;
            } catch (Throwable th2) {
                this.mRecentOperations.a(i);
            }
        }
    }

    public long executeForLong(String str, Object[] objArr, CancellationSignal cancellationSignal) {
        if (str == null) {
            throw new IllegalArgumentException("sql must not be null.");
        }
        a a = this.mRecentOperations.a("executeForLong", str, objArr);
        int i = a.h;
        try {
            c acquirePreparedStatement = acquirePreparedStatement(str);
            a.i = acquirePreparedStatement.f;
            try {
                throwIfStatementForbidden(acquirePreparedStatement);
                bindArguments(acquirePreparedStatement, objArr);
                applyBlockGuardPolicy(acquirePreparedStatement);
                attachCancellationSignal(cancellationSignal);
                long nativeExecuteForLong = nativeExecuteForLong(this.mConnectionPtr, acquirePreparedStatement.a());
                detachCancellationSignal(cancellationSignal);
                releasePreparedStatement(acquirePreparedStatement);
                this.mRecentOperations.a(i);
                return nativeExecuteForLong;
            } catch (Throwable th) {
                releasePreparedStatement(acquirePreparedStatement);
            }
        } catch (Exception e) {
            try {
                if (((e instanceof SQLiteDatabaseLockedException) || (e instanceof SQLiteTableLockedException)) && this.mPool != null) {
                    this.mPool.logConnectionPoolBusy(str);
                }
                this.mRecentOperations.a(i, e);
                throw e;
            } catch (Throwable th2) {
                this.mRecentOperations.a(i);
            }
        }
    }

    public String executeForString(String str, Object[] objArr, CancellationSignal cancellationSignal) {
        if (str == null) {
            throw new IllegalArgumentException("sql must not be null.");
        }
        a a = this.mRecentOperations.a("executeForString", str, objArr);
        int i = a.h;
        try {
            c acquirePreparedStatement = acquirePreparedStatement(str);
            a.i = acquirePreparedStatement.f;
            try {
                throwIfStatementForbidden(acquirePreparedStatement);
                bindArguments(acquirePreparedStatement, objArr);
                applyBlockGuardPolicy(acquirePreparedStatement);
                attachCancellationSignal(cancellationSignal);
                String nativeExecuteForString = nativeExecuteForString(this.mConnectionPtr, acquirePreparedStatement.a());
                detachCancellationSignal(cancellationSignal);
                releasePreparedStatement(acquirePreparedStatement);
                this.mRecentOperations.a(i);
                return nativeExecuteForString;
            } catch (Throwable th) {
                releasePreparedStatement(acquirePreparedStatement);
            }
        } catch (Exception e) {
            try {
                if (((e instanceof SQLiteDatabaseLockedException) || (e instanceof SQLiteTableLockedException)) && this.mPool != null) {
                    this.mPool.logConnectionPoolBusy(str);
                }
                this.mRecentOperations.a(i, e);
                throw e;
            } catch (Throwable th2) {
                this.mRecentOperations.a(i);
            }
        }
    }

    public int executeForChangedRowCount(String str, Object[] objArr, CancellationSignal cancellationSignal) {
        if (str == null) {
            throw new IllegalArgumentException("sql must not be null.");
        }
        int i = 0;
        a a = this.mRecentOperations.a("executeForChangedRowCount", str, objArr);
        int i2 = a.h;
        try {
            c acquirePreparedStatement = acquirePreparedStatement(str);
            a.i = acquirePreparedStatement.f;
            try {
                throwIfStatementForbidden(acquirePreparedStatement);
                bindArguments(acquirePreparedStatement, objArr);
                applyBlockGuardPolicy(acquirePreparedStatement);
                attachCancellationSignal(cancellationSignal);
                i = nativeExecuteForChangedRowCount(this.mConnectionPtr, acquirePreparedStatement.a());
                detachCancellationSignal(cancellationSignal);
                releasePreparedStatement(acquirePreparedStatement);
                if (this.mRecentOperations.b(i2)) {
                    this.mRecentOperations.a(i2, "changedRows=" + i);
                }
                return i;
            } catch (Throwable th) {
                releasePreparedStatement(acquirePreparedStatement);
            }
        } catch (Exception e) {
            try {
                if (((e instanceof SQLiteDatabaseLockedException) || (e instanceof SQLiteTableLockedException)) && this.mPool != null) {
                    this.mPool.logConnectionPoolBusy(str);
                }
                this.mRecentOperations.a(i2, e);
                throw e;
            } catch (Throwable th2) {
                if (this.mRecentOperations.b(i2)) {
                    this.mRecentOperations.a(i2, "changedRows=" + i);
                }
            }
        }
    }

    public long executeForLastInsertedRowId(String str, Object[] objArr, CancellationSignal cancellationSignal) {
        if (str == null) {
            throw new IllegalArgumentException("sql must not be null.");
        }
        a a = this.mRecentOperations.a("executeForLastInsertedRowId", str, objArr);
        int i = a.h;
        try {
            c acquirePreparedStatement = acquirePreparedStatement(str);
            a.i = acquirePreparedStatement.f;
            try {
                throwIfStatementForbidden(acquirePreparedStatement);
                bindArguments(acquirePreparedStatement, objArr);
                applyBlockGuardPolicy(acquirePreparedStatement);
                attachCancellationSignal(cancellationSignal);
                long nativeExecuteForLastInsertedRowId = nativeExecuteForLastInsertedRowId(this.mConnectionPtr, acquirePreparedStatement.a());
                detachCancellationSignal(cancellationSignal);
                releasePreparedStatement(acquirePreparedStatement);
                this.mRecentOperations.a(i);
                return nativeExecuteForLastInsertedRowId;
            } catch (Throwable th) {
                releasePreparedStatement(acquirePreparedStatement);
            }
        } catch (Exception e) {
            try {
                if (((e instanceof SQLiteDatabaseLockedException) || (e instanceof SQLiteTableLockedException)) && this.mPool != null) {
                    this.mPool.logConnectionPoolBusy(str);
                }
                this.mRecentOperations.a(i, e);
                throw e;
            } catch (Throwable th2) {
                this.mRecentOperations.a(i);
            }
        }
    }

    public int executeForCursorWindow(String str, Object[] objArr, CursorWindow cursorWindow, int i, int i2, boolean z, CancellationSignal cancellationSignal) {
        Throwable th;
        Exception e;
        if (str == null) {
            throw new IllegalArgumentException("sql must not be null.");
        } else if (cursorWindow == null) {
            throw new IllegalArgumentException("window must not be null.");
        } else {
            cursorWindow.acquireReference();
            int i3 = -1;
            int i4 = -1;
            int i5 = -1;
            try {
                a a = this.mRecentOperations.a("executeForCursorWindow", str, objArr);
                int i6 = a.h;
                try {
                    c acquirePreparedStatement = acquirePreparedStatement(str);
                    a.i = acquirePreparedStatement.f;
                    try {
                        int i7;
                        int i8;
                        int numRows;
                        throwIfStatementForbidden(acquirePreparedStatement);
                        bindArguments(acquirePreparedStatement, objArr);
                        applyBlockGuardPolicy(acquirePreparedStatement);
                        attachCancellationSignal(cancellationSignal);
                        try {
                            long nativeExecuteForCursorWindow = nativeExecuteForCursorWindow(this.mConnectionPtr, acquirePreparedStatement.a(), cursorWindow.mWindowPtr, i, i2, z);
                            i7 = (int) (nativeExecuteForCursorWindow >> 32);
                            i8 = (int) nativeExecuteForCursorWindow;
                            try {
                                numRows = cursorWindow.getNumRows();
                                try {
                                    cursorWindow.setStartPosition(i7);
                                } catch (Throwable th2) {
                                    th = th2;
                                    detachCancellationSignal(cancellationSignal);
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                numRows = -1;
                                detachCancellationSignal(cancellationSignal);
                                throw th;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            numRows = -1;
                            i8 = -1;
                            i7 = -1;
                            detachCancellationSignal(cancellationSignal);
                            throw th;
                        }
                        try {
                            detachCancellationSignal(cancellationSignal);
                            try {
                                releasePreparedStatement(acquirePreparedStatement);
                                if (this.mRecentOperations.b(i6)) {
                                    this.mRecentOperations.a(i6, "window='" + cursorWindow + "', startPos=" + i + ", actualPos=" + i7 + ", filledRows=" + numRows + ", countedRows=" + i8);
                                }
                                cursorWindow.releaseReference();
                                return i8;
                            } catch (RuntimeException e2) {
                                e = e2;
                                i5 = numRows;
                                i4 = i8;
                                i3 = i7;
                                try {
                                    if (((e instanceof SQLiteDatabaseLockedException) || (e instanceof SQLiteTableLockedException)) && this.mPool != null) {
                                        this.mPool.logConnectionPoolBusy(str);
                                    }
                                    this.mRecentOperations.a(i6, e);
                                    throw e;
                                } catch (Throwable th5) {
                                    th = th5;
                                    if (this.mRecentOperations.b(i6)) {
                                        this.mRecentOperations.a(i6, "window='" + cursorWindow + "', startPos=" + i + ", actualPos=" + i3 + ", filledRows=" + i5 + ", countedRows=" + i4);
                                    }
                                    throw th;
                                }
                            } catch (Throwable th6) {
                                th = th6;
                                i5 = numRows;
                                i4 = i8;
                                i3 = i7;
                                if (this.mRecentOperations.b(i6)) {
                                    this.mRecentOperations.a(i6, "window='" + cursorWindow + "', startPos=" + i + ", actualPos=" + i3 + ", filledRows=" + i5 + ", countedRows=" + i4);
                                }
                                throw th;
                            }
                        } catch (Throwable th7) {
                            th = th7;
                            i5 = numRows;
                            i4 = i8;
                            i3 = i7;
                            releasePreparedStatement(acquirePreparedStatement);
                            throw th;
                        }
                    } catch (Throwable th8) {
                        th = th8;
                        releasePreparedStatement(acquirePreparedStatement);
                        throw th;
                    }
                } catch (RuntimeException e3) {
                    e = e3;
                    this.mPool.logConnectionPoolBusy(str);
                    this.mRecentOperations.a(i6, e);
                    throw e;
                }
            } catch (Throwable th9) {
                cursorWindow.releaseReference();
            }
        }
    }

    public Pair<Integer, Integer> walCheckpoint(String str) {
        if (str == null || str.isEmpty()) {
            str = "main";
        }
        long nativeWalCheckpoint = nativeWalCheckpoint(this.mConnectionPtr, str);
        return new Pair(Integer.valueOf((int) (nativeWalCheckpoint >> 32)), Integer.valueOf((int) (nativeWalCheckpoint & 4294967295L)));
    }

    c acquirePreparedStatement(String str) {
        c cVar = (c) this.mPreparedStatementCache.get(str);
        boolean z;
        long nativePrepareStatement;
        int nativeGetParameterCount;
        int sqlStatementType;
        if (cVar == null) {
            z = false;
            nativePrepareStatement = nativePrepareStatement(this.mConnectionPtr, str);
            nativeGetParameterCount = nativeGetParameterCount(this.mConnectionPtr, nativePrepareStatement);
            sqlStatementType = DatabaseUtils.getSqlStatementType(str);
            cVar = obtainPreparedStatement(str, nativePrepareStatement, nativeGetParameterCount, sqlStatementType, nativeIsReadOnly(this.mConnectionPtr, nativePrepareStatement));
            this.mPreparedStatementCache.put(str, cVar);
            cVar.h = true;
            cVar.i = true;
        } else if (cVar.i) {
            z = true;
            nativePrepareStatement = nativePrepareStatement(this.mConnectionPtr, str);
            try {
                nativeGetParameterCount = nativeGetParameterCount(this.mConnectionPtr, nativePrepareStatement);
                sqlStatementType = DatabaseUtils.getSqlStatementType(str);
                cVar = obtainPreparedStatement(str, nativePrepareStatement, nativeGetParameterCount, sqlStatementType, nativeIsReadOnly(this.mConnectionPtr, nativePrepareStatement));
                if (!z && isCacheable(sqlStatementType)) {
                    this.mPreparedStatementCache.put(str, cVar);
                    cVar.h = true;
                }
                cVar.i = true;
            } catch (RuntimeException e) {
                if (cVar == null || !cVar.h) {
                    nativeFinalizeStatement(this.mConnectionPtr, nativePrepareStatement);
                }
                throw e;
            }
        } else {
            cVar.i = true;
        }
        return cVar;
    }

    void releasePreparedStatement(c cVar) {
        cVar.i = false;
        if (cVar.h) {
            try {
                resetStatement(cVar, true);
                return;
            } catch (SQLiteException e) {
                this.mPreparedStatementCache.remove(cVar.c);
                return;
            }
        }
        finalizePreparedStatement(cVar);
    }

    private void finalizePreparedStatement(c cVar) {
        nativeFinalizeStatement(this.mConnectionPtr, cVar.a());
        recyclePreparedStatement(cVar);
    }

    private void attachCancellationSignal(CancellationSignal cancellationSignal) {
        if (cancellationSignal != null) {
            cancellationSignal.throwIfCanceled();
            this.mCancellationSignalAttachCount++;
            if (this.mCancellationSignalAttachCount == 1) {
                nativeResetCancel(this.mConnectionPtr, true);
                cancellationSignal.setOnCancelListener(this);
            }
        }
    }

    private void detachCancellationSignal(CancellationSignal cancellationSignal) {
        if (cancellationSignal != null) {
            this.mCancellationSignalAttachCount--;
            if (this.mCancellationSignalAttachCount == 0) {
                cancellationSignal.setOnCancelListener(null);
                nativeResetCancel(this.mConnectionPtr, false);
            }
        }
    }

    public void onCancel() {
        nativeCancel(this.mConnectionPtr);
    }

    private void bindArguments(c cVar, Object[] objArr) {
        int length;
        if (objArr != null) {
            length = objArr.length;
        } else {
            length = 0;
        }
        if (length != cVar.e) {
            throw new SQLiteBindOrColumnIndexOutOfRangeException("Expected " + cVar.e + " bind arguments but " + length + " were provided.");
        } else if (length != 0) {
            long a = cVar.a();
            for (int i = 0; i < length; i++) {
                Object obj = objArr[i];
                switch (DatabaseUtils.getTypeOfObject(obj)) {
                    case 0:
                        nativeBindNull(this.mConnectionPtr, a, i + 1);
                        break;
                    case 1:
                        nativeBindLong(this.mConnectionPtr, a, i + 1, ((Number) obj).longValue());
                        break;
                    case 2:
                        nativeBindDouble(this.mConnectionPtr, a, i + 1, ((Number) obj).doubleValue());
                        break;
                    case 4:
                        nativeBindBlob(this.mConnectionPtr, a, i + 1, (byte[]) obj);
                        break;
                    default:
                        if (!(obj instanceof Boolean)) {
                            nativeBindString(this.mConnectionPtr, a, i + 1, obj.toString());
                            break;
                        } else {
                            nativeBindLong(this.mConnectionPtr, a, i + 1, ((Boolean) obj).booleanValue() ? 1 : 0);
                            break;
                        }
                }
            }
        }
    }

    private void resetStatement(c cVar, boolean z) {
        nativeResetStatement(this.mConnectionPtr, cVar.a(), z);
    }

    private void throwIfStatementForbidden(c cVar) {
        if (this.mOnlyAllowReadOnlyOperations && !cVar.g) {
            throw new SQLiteException("Cannot execute this statement because it might modify the database but the connection is read-only.");
        }
    }

    private static boolean isCacheable(int i) {
        if (i == 2 || i == 1) {
            return true;
        }
        return false;
    }

    private void applyBlockGuardPolicy(c cVar) {
    }

    public void dump(Printer printer, boolean z) {
        dumpUnsafe(printer, z);
    }

    void dumpUnsafe(Printer printer, boolean z) {
        printer.println("Connection #" + this.mConnectionId + ":");
        if (z) {
            printer.println("  connectionPtr: 0x" + Long.toHexString(this.mConnectionPtr));
        }
        printer.println("  isPrimaryConnection: " + this.mIsPrimaryConnection);
        printer.println("  onlyAllowReadOnlyOperations: " + this.mOnlyAllowReadOnlyOperations);
        if (this.mAcquiredThread != null) {
            printer.println("  acquiredThread: " + this.mAcquiredThread + " (tid: " + this.mAcquiredTid + ")");
        }
        this.mRecentOperations.a(printer, z);
        if (z) {
            this.mPreparedStatementCache.a(printer);
        }
    }

    String describeCurrentOperationUnsafe() {
        return this.mRecentOperations.a();
    }

    void collectDbStats(ArrayList<DbStats> arrayList) {
        int nativeGetDbLookaside = nativeGetDbLookaside(this.mConnectionPtr);
        long j = 0;
        long j2 = 0;
        try {
            j = executeForLong("PRAGMA page_count;", null, null);
            j2 = executeForLong("PRAGMA page_size;", null, null);
        } catch (SQLiteException e) {
        }
        arrayList.add(getMainDbStatsUnsafe(nativeGetDbLookaside, j, j2));
        CursorWindow cursorWindow = new CursorWindow("collectDbStats");
        try {
            executeForCursorWindow("PRAGMA database_list;", null, cursorWindow, 0, 0, false, null);
            for (int i = 1; i < cursorWindow.getNumRows(); i++) {
                long j3;
                String string = cursorWindow.getString(i, 1);
                String string2 = cursorWindow.getString(i, 2);
                long j4 = 0;
                long j5 = 0;
                try {
                    j4 = executeForLong("PRAGMA " + string + ".page_count;", null, null);
                    j5 = executeForLong("PRAGMA " + string + ".page_size;", null, null);
                    j3 = j4;
                } catch (SQLiteException e2) {
                    j3 = j4;
                } finally {
                    cursorWindow.close();
                }
                String str = "  (attached) " + string;
                if (string2.length() != 0) {
                    str = str + ": " + string2;
                }
                arrayList.add(new DbStats(str, j3, j5, 0, 0, 0, 0));
            }
        } catch (SQLiteException e3) {
        } finally {
            cursorWindow.close();
        }
    }

    void collectDbStatsUnsafe(ArrayList<DbStats> arrayList) {
        arrayList.add(getMainDbStatsUnsafe(0, 0, 0));
    }

    private DbStats getMainDbStatsUnsafe(int i, long j, long j2) {
        String str = this.mConfiguration.path;
        if (!this.mIsPrimaryConnection) {
            str = str + " (" + this.mConnectionId + ")";
        }
        return new DbStats(str, j, j2, i, this.mPreparedStatementCache.hitCount(), this.mPreparedStatementCache.missCount(), this.mPreparedStatementCache.size());
    }

    public String toString() {
        return "SQLiteConnection: " + this.mConfiguration.path + " (" + this.mConnectionId + ")";
    }

    private c obtainPreparedStatement(String str, long j, int i, int i2, boolean z) {
        c cVar = this.mPreparedStatementPool;
        if (cVar != null) {
            this.mPreparedStatementPool = cVar.b;
            cVar.b = null;
            cVar.h = false;
        } else {
            cVar = new c(this);
        }
        cVar.c = str;
        cVar.d = j;
        cVar.e = i;
        cVar.f = i2;
        cVar.g = z;
        return cVar;
    }

    private void recyclePreparedStatement(c cVar) {
        cVar.c = null;
        cVar.b = this.mPreparedStatementPool;
        this.mPreparedStatementPool = cVar;
    }

    private static String trimSqlForDisplay(String str) {
        return TRIM_SQL_PATTERN.matcher(str).replaceAll(" ");
    }
}
