package com.fasterxml.jackson.core.io;

import android.support.v4.internal.view.SupportMenu;
import com.fasterxml.jackson.core.base.GeneratorBase;
import com.xiaomi.mipush.sdk.Constants;
import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class UTF32Reader extends Reader {
    protected static final int LAST_VALID_UNICODE_CHAR = 1114111;
    protected static final char NC = '\u0000';
    protected final boolean _bigEndian;
    protected byte[] _buffer;
    protected int _byteCount;
    protected int _charCount;
    protected final IOContext _context;
    protected InputStream _in;
    protected int _length;
    protected final boolean _managedBuffers;
    protected int _ptr;
    protected char _surrogate = '\u0000';
    protected char[] _tmpBuf;

    public UTF32Reader(IOContext iOContext, InputStream inputStream, byte[] bArr, int i, int i2, boolean z) {
        boolean z2 = false;
        this._context = iOContext;
        this._in = inputStream;
        this._buffer = bArr;
        this._ptr = i;
        this._length = i2;
        this._bigEndian = z;
        if (inputStream != null) {
            z2 = true;
        }
        this._managedBuffers = z2;
    }

    public void close() throws IOException {
        InputStream inputStream = this._in;
        if (inputStream != null) {
            this._in = null;
            freeBuffers();
            inputStream.close();
        }
    }

    public int read() throws IOException {
        if (this._tmpBuf == null) {
            this._tmpBuf = new char[1];
        }
        if (read(this._tmpBuf, 0, 1) < 1) {
            return -1;
        }
        return this._tmpBuf[0];
    }

    public int read(char[] cArr, int i, int i2) throws IOException {
        if (this._buffer == null) {
            return -1;
        }
        if (i2 < 1) {
            return i2;
        }
        int i3;
        int i4;
        if (i < 0 || i + i2 > cArr.length) {
            reportBounds(cArr, i, i2);
        }
        int i5 = i2 + i;
        if (this._surrogate != '\u0000') {
            i3 = i + 1;
            cArr[i] = this._surrogate;
            this._surrogate = '\u0000';
        } else {
            i4 = this._length - this._ptr;
            if (i4 < 4 && !loadMore(i4)) {
                return -1;
            }
            i3 = i;
        }
        while (i3 < i5) {
            int i6 = this._ptr;
            if (this._bigEndian) {
                i6 = (this._buffer[i6 + 3] & 255) | (((this._buffer[i6] << 24) | ((this._buffer[i6 + 1] & 255) << 16)) | ((this._buffer[i6 + 2] & 255) << 8));
            } else {
                i6 = (this._buffer[i6 + 3] << 24) | (((this._buffer[i6] & 255) | ((this._buffer[i6 + 1] & 255) << 8)) | ((this._buffer[i6 + 2] & 255) << 16));
            }
            this._ptr += 4;
            if (i6 > SupportMenu.USER_MASK) {
                if (i6 > LAST_VALID_UNICODE_CHAR) {
                    reportInvalid(i6, i3 - i, "(above " + Integer.toHexString(LAST_VALID_UNICODE_CHAR) + ") ");
                }
                i6 -= 65536;
                i4 = i3 + 1;
                cArr[i3] = (char) (GeneratorBase.SURR1_FIRST + (i6 >> 10));
                i6 = (i6 & 1023) | GeneratorBase.SURR2_FIRST;
                if (i4 >= i5) {
                    this._surrogate = (char) i6;
                    break;
                }
            }
            i4 = i3;
            i3 = i4 + 1;
            cArr[i4] = (char) i6;
            if (this._ptr >= this._length) {
                i4 = i3;
                break;
            }
        }
        i4 = i3;
        i2 = i4 - i;
        this._charCount += i2;
        return i2;
    }

    private void reportUnexpectedEOF(int i, int i2) throws IOException {
        throw new CharConversionException("Unexpected EOF in the middle of a 4-byte UTF-32 char: got " + i + ", needed " + i2 + ", at char #" + this._charCount + ", byte #" + (this._byteCount + i) + ")");
    }

    private void reportInvalid(int i, int i2, String str) throws IOException {
        throw new CharConversionException("Invalid UTF-32 character 0x" + Integer.toHexString(i) + str + " at char #" + (this._charCount + i2) + ", byte #" + ((this._byteCount + this._ptr) - 1) + ")");
    }

    private boolean loadMore(int i) throws IOException {
        this._byteCount += this._length - i;
        if (i > 0) {
            if (this._ptr > 0) {
                System.arraycopy(this._buffer, this._ptr, this._buffer, 0, i);
                this._ptr = 0;
            }
            this._length = i;
        } else {
            this._ptr = 0;
            int read = this._in == null ? -1 : this._in.read(this._buffer);
            if (read < 1) {
                this._length = 0;
                if (read >= 0) {
                    reportStrangeStream();
                } else if (!this._managedBuffers) {
                    return false;
                } else {
                    freeBuffers();
                    return false;
                }
            }
            this._length = read;
        }
        while (this._length < 4) {
            int i2;
            if (this._in == null) {
                i2 = -1;
            } else {
                i2 = this._in.read(this._buffer, this._length, this._buffer.length - this._length);
            }
            if (i2 < 1) {
                if (i2 < 0) {
                    if (this._managedBuffers) {
                        freeBuffers();
                    }
                    reportUnexpectedEOF(this._length, 4);
                }
                reportStrangeStream();
            }
            this._length = i2 + this._length;
        }
        return true;
    }

    private void freeBuffers() {
        byte[] bArr = this._buffer;
        if (bArr != null) {
            this._buffer = null;
            this._context.releaseReadIOBuffer(bArr);
        }
    }

    private void reportBounds(char[] cArr, int i, int i2) throws IOException {
        throw new ArrayIndexOutOfBoundsException("read(buf," + i + Constants.ACCEPT_TIME_SEPARATOR_SP + i2 + "), cbuf[" + cArr.length + "]");
    }

    private void reportStrangeStream() throws IOException {
        throw new IOException("Strange I/O stream, returned 0 bytes on read");
    }
}
