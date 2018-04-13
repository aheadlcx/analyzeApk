package org.apache.commons.httpclient;

import cn.v6.sixrooms.socket.common.SocketUtil;
import com.umeng.analytics.pro.dm;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.httpclient.util.EncodingUtil;

public class ChunkedOutputStream extends OutputStream {
    private static final byte[] CRLF = new byte[]{dm.k, (byte) 10};
    private static final byte[] ENDCHUNK = CRLF;
    private static final byte[] ZERO = new byte[]{(byte) 48};
    private byte[] cache;
    private int cachePosition;
    private OutputStream stream;
    private boolean wroteLastChunk;

    public ChunkedOutputStream(OutputStream outputStream, int i) throws IOException {
        this.stream = null;
        this.cachePosition = 0;
        this.wroteLastChunk = false;
        this.cache = new byte[i];
        this.stream = outputStream;
    }

    public ChunkedOutputStream(OutputStream outputStream) throws IOException {
        this(outputStream, 2048);
    }

    protected void flushCache() throws IOException {
        if (this.cachePosition > 0) {
            byte[] asciiBytes = EncodingUtil.getAsciiBytes(new StringBuffer().append(Integer.toHexString(this.cachePosition)).append(SocketUtil.CRLF).toString());
            this.stream.write(asciiBytes, 0, asciiBytes.length);
            this.stream.write(this.cache, 0, this.cachePosition);
            this.stream.write(ENDCHUNK, 0, ENDCHUNK.length);
            this.cachePosition = 0;
        }
    }

    protected void flushCacheWithAppend(byte[] bArr, int i, int i2) throws IOException {
        byte[] asciiBytes = EncodingUtil.getAsciiBytes(new StringBuffer().append(Integer.toHexString(this.cachePosition + i2)).append(SocketUtil.CRLF).toString());
        this.stream.write(asciiBytes, 0, asciiBytes.length);
        this.stream.write(this.cache, 0, this.cachePosition);
        this.stream.write(bArr, i, i2);
        this.stream.write(ENDCHUNK, 0, ENDCHUNK.length);
        this.cachePosition = 0;
    }

    protected void writeClosingChunk() throws IOException {
        this.stream.write(ZERO, 0, ZERO.length);
        this.stream.write(CRLF, 0, CRLF.length);
        this.stream.write(ENDCHUNK, 0, ENDCHUNK.length);
    }

    public void finish() throws IOException {
        if (!this.wroteLastChunk) {
            flushCache();
            writeClosingChunk();
            this.wroteLastChunk = true;
        }
    }

    public void write(int i) throws IOException {
        this.cache[this.cachePosition] = (byte) i;
        this.cachePosition++;
        if (this.cachePosition == this.cache.length) {
            flushCache();
        }
    }

    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (i2 >= this.cache.length - this.cachePosition) {
            flushCacheWithAppend(bArr, i, i2);
            return;
        }
        System.arraycopy(bArr, i, this.cache, this.cachePosition, i2);
        this.cachePosition += i2;
    }

    public void flush() throws IOException {
        this.stream.flush();
    }

    public void close() throws IOException {
        finish();
        super.close();
    }
}
