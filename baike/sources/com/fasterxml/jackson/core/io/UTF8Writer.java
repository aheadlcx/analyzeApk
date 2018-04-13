package com.fasterxml.jackson.core.io;

import android.support.v4.internal.view.SupportMenu;
import com.facebook.imageutils.JfifUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public final class UTF8Writer extends Writer {
    static final int SURR1_FIRST = 55296;
    static final int SURR1_LAST = 56319;
    static final int SURR2_FIRST = 56320;
    static final int SURR2_LAST = 57343;
    private final IOContext _context;
    private OutputStream _out;
    private byte[] _outBuffer;
    private final int _outBufferEnd = (this._outBuffer.length - 4);
    private int _outPtr = 0;
    private int _surrogate;

    public UTF8Writer(IOContext iOContext, OutputStream outputStream) {
        this._context = iOContext;
        this._out = outputStream;
        this._outBuffer = iOContext.allocWriteEncodingBuffer();
    }

    public Writer append(char c) throws IOException {
        write((int) c);
        return this;
    }

    public void close() throws IOException {
        if (this._out != null) {
            if (this._outPtr > 0) {
                this._out.write(this._outBuffer, 0, this._outPtr);
                this._outPtr = 0;
            }
            OutputStream outputStream = this._out;
            this._out = null;
            byte[] bArr = this._outBuffer;
            if (bArr != null) {
                this._outBuffer = null;
                this._context.releaseWriteEncodingBuffer(bArr);
            }
            outputStream.close();
            int i = this._surrogate;
            this._surrogate = 0;
            if (i > 0) {
                illegalSurrogate(i);
            }
        }
    }

    public void flush() throws IOException {
        if (this._out != null) {
            if (this._outPtr > 0) {
                this._out.write(this._outBuffer, 0, this._outPtr);
                this._outPtr = 0;
            }
            this._out.flush();
        }
    }

    public void write(char[] cArr) throws IOException {
        write(cArr, 0, cArr.length);
    }

    public void write(char[] cArr, int i, int i2) throws IOException {
        if (i2 >= 2) {
            int i3;
            if (this._surrogate > 0) {
                i3 = i + 1;
                i2--;
                write(convertSurrogate(cArr[i]));
                i = i3;
            }
            i3 = this._outPtr;
            byte[] bArr = this._outBuffer;
            int i4 = this._outBufferEnd;
            int i5 = i2 + i;
            int i6 = i;
            while (i6 < i5) {
                int i7;
                int i8;
                if (i3 >= i4) {
                    this._out.write(bArr, 0, i3);
                    i3 = 0;
                }
                int i9 = i6 + 1;
                char c = cArr[i6];
                char c2;
                if (c < '') {
                    i7 = i3 + 1;
                    bArr[i3] = (byte) c;
                    i6 = i5 - i9;
                    i3 = i4 - i7;
                    if (i6 <= i3) {
                        i3 = i6;
                    }
                    i8 = i3 + i9;
                    i6 = i7;
                    i3 = i9;
                    while (i3 < i8) {
                        i9 = i3 + 1;
                        char c3 = cArr[i3];
                        if (c3 >= '') {
                            c2 = c3;
                            i3 = i6;
                            i6 = i9;
                            i9 = c2;
                        } else {
                            i7 = i6 + 1;
                            bArr[i6] = (byte) c3;
                            i6 = i7;
                            i3 = i9;
                        }
                    }
                    int i10 = i6;
                    i6 = i3;
                    i3 = i10;
                } else {
                    c2 = c;
                    i6 = i9;
                    char c4 = c2;
                }
                if (i9 < 2048) {
                    i7 = i3 + 1;
                    bArr[i3] = (byte) ((i9 >> 6) | JfifUtil.MARKER_SOFn);
                    i3 = i7 + 1;
                    bArr[i7] = (byte) ((i9 & 63) | 128);
                } else if (i9 < 55296 || i9 > 57343) {
                    i7 = i3 + 1;
                    bArr[i3] = (byte) ((i9 >> 12) | 224);
                    i8 = i7 + 1;
                    bArr[i7] = (byte) (((i9 >> 6) & 63) | 128);
                    i3 = i8 + 1;
                    bArr[i8] = (byte) ((i9 & 63) | 128);
                } else {
                    if (i9 > 56319) {
                        this._outPtr = i3;
                        illegalSurrogate(i9);
                    }
                    this._surrogate = i9;
                    if (i6 >= i5) {
                        break;
                    }
                    i9 = i6 + 1;
                    i6 = convertSurrogate(cArr[i6]);
                    if (i6 > 1114111) {
                        this._outPtr = i3;
                        illegalSurrogate(i6);
                    }
                    i7 = i3 + 1;
                    bArr[i3] = (byte) ((i6 >> 18) | 240);
                    i3 = i7 + 1;
                    bArr[i7] = (byte) (((i6 >> 12) & 63) | 128);
                    i7 = i3 + 1;
                    bArr[i3] = (byte) (((i6 >> 6) & 63) | 128);
                    i3 = i7 + 1;
                    bArr[i7] = (byte) ((i6 & 63) | 128);
                    i6 = i9;
                }
            }
            this._outPtr = i3;
        } else if (i2 == 1) {
            write(cArr[i]);
        }
    }

    public void write(int i) throws IOException {
        if (this._surrogate > 0) {
            i = convertSurrogate(i);
        } else if (i >= 55296 && i <= 57343) {
            if (i > 56319) {
                illegalSurrogate(i);
            }
            this._surrogate = i;
            return;
        }
        if (this._outPtr >= this._outBufferEnd) {
            this._out.write(this._outBuffer, 0, this._outPtr);
            this._outPtr = 0;
        }
        if (i < 128) {
            byte[] bArr = this._outBuffer;
            int i2 = this._outPtr;
            this._outPtr = i2 + 1;
            bArr[i2] = (byte) i;
            return;
        }
        int i3 = this._outPtr;
        int i4;
        if (i < 2048) {
            i4 = i3 + 1;
            this._outBuffer[i3] = (byte) ((i >> 6) | JfifUtil.MARKER_SOFn);
            i3 = i4 + 1;
            this._outBuffer[i4] = (byte) ((i & 63) | 128);
        } else if (i <= SupportMenu.USER_MASK) {
            i4 = i3 + 1;
            this._outBuffer[i3] = (byte) ((i >> 12) | 224);
            i2 = i4 + 1;
            this._outBuffer[i4] = (byte) (((i >> 6) & 63) | 128);
            i3 = i2 + 1;
            this._outBuffer[i2] = (byte) ((i & 63) | 128);
        } else {
            if (i > 1114111) {
                illegalSurrogate(i);
            }
            i4 = i3 + 1;
            this._outBuffer[i3] = (byte) ((i >> 18) | 240);
            i2 = i4 + 1;
            this._outBuffer[i4] = (byte) (((i >> 12) & 63) | 128);
            i4 = i2 + 1;
            this._outBuffer[i2] = (byte) (((i >> 6) & 63) | 128);
            i3 = i4 + 1;
            this._outBuffer[i4] = (byte) ((i & 63) | 128);
        }
        this._outPtr = i3;
    }

    public void write(String str) throws IOException {
        write(str, 0, str.length());
    }

    public void write(String str, int i, int i2) throws IOException {
        if (i2 >= 2) {
            int i3;
            if (this._surrogate > 0) {
                i3 = i + 1;
                i2--;
                write(convertSurrogate(str.charAt(i)));
                i = i3;
            }
            i3 = this._outPtr;
            byte[] bArr = this._outBuffer;
            int i4 = this._outBufferEnd;
            int i5 = i2 + i;
            int i6 = i;
            while (i6 < i5) {
                int i7;
                int i8;
                if (i3 >= i4) {
                    this._out.write(bArr, 0, i3);
                    i3 = 0;
                }
                int i9 = i6 + 1;
                char charAt = str.charAt(i6);
                char c;
                if (charAt < '') {
                    i7 = i3 + 1;
                    bArr[i3] = (byte) charAt;
                    i6 = i5 - i9;
                    i3 = i4 - i7;
                    if (i6 <= i3) {
                        i3 = i6;
                    }
                    i8 = i3 + i9;
                    i6 = i7;
                    i3 = i9;
                    while (i3 < i8) {
                        i9 = i3 + 1;
                        char charAt2 = str.charAt(i3);
                        if (charAt2 >= '') {
                            c = charAt2;
                            i3 = i6;
                            i6 = i9;
                            i9 = c;
                        } else {
                            i7 = i6 + 1;
                            bArr[i6] = (byte) charAt2;
                            i6 = i7;
                            i3 = i9;
                        }
                    }
                    int i10 = i6;
                    i6 = i3;
                    i3 = i10;
                } else {
                    c = charAt;
                    i6 = i9;
                    char c2 = c;
                }
                if (i9 < 2048) {
                    i7 = i3 + 1;
                    bArr[i3] = (byte) ((i9 >> 6) | JfifUtil.MARKER_SOFn);
                    i3 = i7 + 1;
                    bArr[i7] = (byte) ((i9 & 63) | 128);
                } else if (i9 < 55296 || i9 > 57343) {
                    i7 = i3 + 1;
                    bArr[i3] = (byte) ((i9 >> 12) | 224);
                    i8 = i7 + 1;
                    bArr[i7] = (byte) (((i9 >> 6) & 63) | 128);
                    i3 = i8 + 1;
                    bArr[i8] = (byte) ((i9 & 63) | 128);
                } else {
                    if (i9 > 56319) {
                        this._outPtr = i3;
                        illegalSurrogate(i9);
                    }
                    this._surrogate = i9;
                    if (i6 >= i5) {
                        break;
                    }
                    i9 = i6 + 1;
                    i6 = convertSurrogate(str.charAt(i6));
                    if (i6 > 1114111) {
                        this._outPtr = i3;
                        illegalSurrogate(i6);
                    }
                    i7 = i3 + 1;
                    bArr[i3] = (byte) ((i6 >> 18) | 240);
                    i3 = i7 + 1;
                    bArr[i7] = (byte) (((i6 >> 12) & 63) | 128);
                    i7 = i3 + 1;
                    bArr[i3] = (byte) (((i6 >> 6) & 63) | 128);
                    i3 = i7 + 1;
                    bArr[i7] = (byte) ((i6 & 63) | 128);
                    i6 = i9;
                }
            }
            this._outPtr = i3;
        } else if (i2 == 1) {
            write(str.charAt(i));
        }
    }

    protected int convertSurrogate(int i) throws IOException {
        int i2 = this._surrogate;
        this._surrogate = 0;
        if (i >= 56320 && i <= 57343) {
            return (((i2 - 55296) << 10) + 65536) + (i - 56320);
        }
        throw new IOException("Broken surrogate pair: first char 0x" + Integer.toHexString(i2) + ", second 0x" + Integer.toHexString(i) + "; illegal combination");
    }

    protected static void illegalSurrogate(int i) throws IOException {
        throw new IOException(illegalSurrogateDesc(i));
    }

    protected static String illegalSurrogateDesc(int i) {
        if (i > 1114111) {
            return "Illegal character point (0x" + Integer.toHexString(i) + ") to output; max is 0x10FFFF as per RFC 4627";
        }
        if (i < 55296) {
            return "Illegal character point (0x" + Integer.toHexString(i) + ") to output";
        }
        if (i <= 56319) {
            return "Unmatched first part of surrogate pair (0x" + Integer.toHexString(i) + ")";
        }
        return "Unmatched second part of surrogate pair (0x" + Integer.toHexString(i) + ")";
    }
}
