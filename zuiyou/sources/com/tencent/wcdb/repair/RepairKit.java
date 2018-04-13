package com.tencent.wcdb.repair;

import android.database.Cursor;
import com.tencent.wcdb.AbstractCursor;
import com.tencent.wcdb.database.SQLiteCipherSpec;
import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.database.SQLiteException;
import com.tencent.wcdb.support.CancellationSignal;
import com.tencent.wcdb.support.CancellationSignal.OnCancelListener;

public class RepairKit implements OnCancelListener {
    public static final int FLAG_ALL_TABLES = 2;
    public static final int FLAG_NO_CREATE_TABLES = 1;
    private static final int INTEGRITY_DATA = 2;
    private static final int INTEGRITY_HEADER = 1;
    private static final int INTEGRITY_KDF_SALT = 4;
    public static final int RESULT_CANCELED = 1;
    public static final int RESULT_FAILED = -1;
    public static final int RESULT_IGNORE = 2;
    public static final int RESULT_OK = 0;
    private Callback mCallback;
    private RepairCursor mCurrentCursor;
    private int mIntegrityFlags;
    private MasterInfo mMasterInfo;
    private long mNativePtr;

    public interface Callback {
        int onProgress(String str, int i, Cursor cursor);
    }

    public static class MasterInfo {
        private byte[] mKDFSalt;
        private long mMasterPtr;

        private MasterInfo(long j, byte[] bArr) {
            this.mMasterPtr = j;
            this.mKDFSalt = bArr;
        }

        public static MasterInfo make(String[] strArr) {
            long access$300 = RepairKit.nativeMakeMaster(strArr);
            if (access$300 != 0) {
                return new MasterInfo(access$300, null);
            }
            throw new SQLiteException("Cannot create MasterInfo.");
        }

        public static MasterInfo load(String str, byte[] bArr, String[] strArr) {
            if (str == null) {
                return make(strArr);
            }
            byte[] bArr2 = new byte[16];
            long access$400 = RepairKit.nativeLoadMaster(str, bArr, strArr, bArr2);
            if (access$400 != 0) {
                return new MasterInfo(access$400, bArr2);
            }
            throw new SQLiteException("Cannot create MasterInfo.");
        }

        public static boolean save(SQLiteDatabase sQLiteDatabase, String str, byte[] bArr) {
            long acquireNativeConnectionHandle = sQLiteDatabase.acquireNativeConnectionHandle("backupMaster", true, false);
            boolean access$500 = RepairKit.nativeSaveMaster(acquireNativeConnectionHandle, str, bArr);
            sQLiteDatabase.releaseNativeConnection(acquireNativeConnectionHandle, null);
            return access$500;
        }

        public void release() {
            if (this.mMasterPtr != 0) {
                RepairKit.nativeFreeMaster(this.mMasterPtr);
                this.mMasterPtr = 0;
            }
        }

        protected void finalize() throws Throwable {
            release();
            super.finalize();
        }
    }

    private static class RepairCursor extends AbstractCursor {
        long a;

        private static native byte[] nativeGetBlob(long j, int i);

        private static native int nativeGetColumnCount(long j);

        private static native double nativeGetDouble(long j, int i);

        private static native long nativeGetLong(long j, int i);

        private static native String nativeGetString(long j, int i);

        private static native int nativeGetType(long j, int i);

        private RepairCursor() {
        }

        public int getCount() {
            throw new UnsupportedOperationException();
        }

        public String[] getColumnNames() {
            throw new UnsupportedOperationException();
        }

        public int getColumnCount() {
            return nativeGetColumnCount(this.a);
        }

        public int getType(int i) {
            return nativeGetType(this.a, i);
        }

        public String getString(int i) {
            return nativeGetString(this.a, i);
        }

        public short getShort(int i) {
            return (short) ((int) getLong(i));
        }

        public int getInt(int i) {
            return (int) getLong(i);
        }

        public long getLong(int i) {
            return nativeGetLong(this.a, i);
        }

        public float getFloat(int i) {
            return (float) getDouble(i);
        }

        public double getDouble(int i) {
            return nativeGetDouble(this.a, i);
        }

        public byte[] getBlob(int i) {
            return nativeGetBlob(this.a, i);
        }

        public boolean isNull(int i) {
            return getType(i) == 0;
        }
    }

    private static native void nativeCancel(long j);

    private static native void nativeFini(long j);

    private static native void nativeFreeMaster(long j);

    private static native long nativeInit(String str, byte[] bArr, SQLiteCipherSpec sQLiteCipherSpec, byte[] bArr2);

    private static native int nativeIntegrityFlags(long j);

    private static native String nativeLastError();

    private static native long nativeLoadMaster(String str, byte[] bArr, String[] strArr, byte[] bArr2);

    private static native long nativeMakeMaster(String[] strArr);

    private native int nativeOutput(long j, long j2, long j3, int i);

    private static native boolean nativeSaveMaster(long j, String str, byte[] bArr);

    public RepairKit(String str, byte[] bArr, SQLiteCipherSpec sQLiteCipherSpec, MasterInfo masterInfo) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        this.mNativePtr = nativeInit(str, bArr, sQLiteCipherSpec, masterInfo == null ? null : masterInfo.mKDFSalt);
        if (this.mNativePtr == 0) {
            throw new SQLiteException("Failed initialize RepairKit.");
        }
        this.mIntegrityFlags = nativeIntegrityFlags(this.mNativePtr);
        this.mMasterInfo = masterInfo;
    }

    public Callback getCallback() {
        return this.mCallback;
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public void release() {
        if (this.mMasterInfo != null) {
            this.mMasterInfo.release();
            this.mMasterInfo = null;
        }
        if (this.mNativePtr != 0) {
            nativeFini(this.mNativePtr);
            this.mNativePtr = 0;
        }
    }

    public int output(SQLiteDatabase sQLiteDatabase, int i) {
        long j = 0;
        if (this.mNativePtr == 0) {
            throw new IllegalArgumentException();
        }
        if (this.mMasterInfo != null) {
            j = this.mMasterInfo.mMasterPtr;
        }
        long acquireNativeConnectionHandle = sQLiteDatabase.acquireNativeConnectionHandle("repair", false, false);
        int nativeOutput = nativeOutput(this.mNativePtr, acquireNativeConnectionHandle, j, i);
        sQLiteDatabase.releaseNativeConnection(acquireNativeConnectionHandle, null);
        this.mCurrentCursor = null;
        this.mIntegrityFlags = nativeIntegrityFlags(this.mNativePtr);
        return nativeOutput;
    }

    public int output(SQLiteDatabase sQLiteDatabase, int i, CancellationSignal cancellationSignal) {
        if (cancellationSignal.isCanceled()) {
            return 1;
        }
        cancellationSignal.setOnCancelListener(this);
        int output = output(sQLiteDatabase, i);
        cancellationSignal.setOnCancelListener(null);
        return output;
    }

    public void onCancel() {
        if (this.mNativePtr != 0) {
            nativeCancel(this.mNativePtr);
        }
    }

    private int onProgress(String str, int i, long j) {
        if (this.mCallback == null) {
            return 0;
        }
        if (this.mCurrentCursor == null) {
            this.mCurrentCursor = new RepairCursor();
        }
        this.mCurrentCursor.a = j;
        return this.mCallback.onProgress(str, i, this.mCurrentCursor);
    }

    public boolean isSaltCorrupted() {
        return (this.mIntegrityFlags & 4) == 0;
    }

    public boolean isHeaderCorrupted() {
        return (this.mIntegrityFlags & 1) == 0;
    }

    public boolean isDataCorrupted() {
        return (this.mIntegrityFlags & 2) == 0;
    }

    public static String lastError() {
        return nativeLastError();
    }

    protected void finalize() throws Throwable {
        release();
        super.finalize();
    }
}
