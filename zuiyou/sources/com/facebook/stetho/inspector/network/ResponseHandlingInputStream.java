package com.facebook.stetho.inspector.network;

import com.facebook.stetho.inspector.console.CLog;
import com.facebook.stetho.inspector.helper.ChromePeerManager;
import com.facebook.stetho.inspector.protocol.module.Console.MessageLevel;
import com.facebook.stetho.inspector.protocol.module.Console.MessageSource;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class ResponseHandlingInputStream extends FilterInputStream {
    private static final int BUFFER_SIZE = 1024;
    public static final String TAG = "ResponseHandlingInputStream";
    @GuardedBy
    private boolean mClosed;
    @Nullable
    private final CountingOutputStream mDecompressedCounter;
    @GuardedBy
    private boolean mEofSeen;
    private long mLastDecompressedCount = 0;
    private final ChromePeerManager mNetworkPeerManager;
    private final OutputStream mOutputStream;
    private final String mRequestId;
    private final ResponseHandler mResponseHandler;
    @GuardedBy
    @Nullable
    private byte[] mSkipBuffer;

    public ResponseHandlingInputStream(InputStream inputStream, String str, OutputStream outputStream, @Nullable CountingOutputStream countingOutputStream, ChromePeerManager chromePeerManager, ResponseHandler responseHandler) {
        super(inputStream);
        this.mRequestId = str;
        this.mOutputStream = outputStream;
        this.mDecompressedCounter = countingOutputStream;
        this.mNetworkPeerManager = chromePeerManager;
        this.mResponseHandler = responseHandler;
        this.mClosed = false;
    }

    private synchronized int checkEOF(int i) {
        if (i == -1) {
            closeOutputStreamQuietly();
            this.mResponseHandler.onEOF();
            this.mEofSeen = true;
        }
        return i;
    }

    public int read() throws IOException {
        try {
            int checkEOF = checkEOF(this.in.read());
            if (checkEOF != -1) {
                this.mResponseHandler.onRead(1);
                writeToOutputStream(checkEOF);
            }
            return checkEOF;
        } catch (IOException e) {
            throw handleIOException(e);
        }
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        try {
            int checkEOF = checkEOF(this.in.read(bArr, i, i2));
            if (checkEOF != -1) {
                this.mResponseHandler.onRead(checkEOF);
                writeToOutputStream(bArr, i, checkEOF);
            }
            return checkEOF;
        } catch (IOException e) {
            throw handleIOException(e);
        }
    }

    public synchronized long skip(long j) throws IOException {
        long j2;
        byte[] skipBufferLocked = getSkipBufferLocked();
        j2 = 0;
        while (j2 < j) {
            int read = read(skipBufferLocked, 0, (int) Math.min((long) skipBufferLocked.length, j - j2));
            if (read == -1) {
                break;
            }
            j2 += (long) read;
        }
        return j2;
    }

    @Nonnull
    private byte[] getSkipBufferLocked() {
        if (this.mSkipBuffer == null) {
            this.mSkipBuffer = new byte[1024];
        }
        return this.mSkipBuffer;
    }

    public boolean markSupported() {
        return false;
    }

    public void mark(int i) {
    }

    public void reset() throws IOException {
        throw new UnsupportedOperationException("Mark not supported");
    }

    public void close() throws IOException {
        try {
            long j;
            if (!this.mEofSeen) {
                byte[] bArr = new byte[1024];
                j = 0;
                while (true) {
                    int read = read(bArr);
                    if (read == -1) {
                        break;
                    }
                    j += (long) read;
                }
            } else {
                j = 0;
            }
            if (j > 0) {
                CLog.writeToConsole(this.mNetworkPeerManager, MessageLevel.ERROR, MessageSource.NETWORK, "There were " + String.valueOf(j) + " bytes that were not consumed while processing request " + this.mRequestId);
            }
            super.close();
            closeOutputStreamQuietly();
        } catch (Throwable th) {
            super.close();
            closeOutputStreamQuietly();
        }
    }

    private synchronized void closeOutputStreamQuietly() {
        if (!this.mClosed) {
            try {
                this.mOutputStream.close();
                reportDecodedSizeIfApplicable();
                this.mClosed = true;
            } catch (IOException e) {
                CLog.writeToConsole(this.mNetworkPeerManager, MessageLevel.ERROR, MessageSource.NETWORK, "Could not close the output stream" + e);
                this.mClosed = true;
            } catch (Throwable th) {
                this.mClosed = true;
            }
        }
    }

    private IOException handleIOException(IOException iOException) {
        this.mResponseHandler.onError(iOException);
        return iOException;
    }

    private void reportDecodedSizeIfApplicable() {
        if (this.mDecompressedCounter != null) {
            long count = this.mDecompressedCounter.getCount();
            this.mResponseHandler.onReadDecoded((int) (count - this.mLastDecompressedCount));
            this.mLastDecompressedCount = count;
        }
    }

    private synchronized void writeToOutputStream(int i) {
        if (!this.mClosed) {
            try {
                this.mOutputStream.write(i);
                reportDecodedSizeIfApplicable();
            } catch (IOException e) {
                handleIOExceptionWritingToStream(e);
            }
        }
    }

    private synchronized void writeToOutputStream(byte[] bArr, int i, int i2) {
        if (!this.mClosed) {
            try {
                this.mOutputStream.write(bArr, i, i2);
                reportDecodedSizeIfApplicable();
            } catch (IOException e) {
                handleIOExceptionWritingToStream(e);
            }
        }
    }

    private void handleIOExceptionWritingToStream(IOException iOException) {
        CLog.writeToConsole(this.mNetworkPeerManager, MessageLevel.ERROR, MessageSource.NETWORK, "Could not write response body to the stream " + iOException);
        closeOutputStreamQuietly();
    }
}
