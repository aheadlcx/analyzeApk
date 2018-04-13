package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.IOUtils;
import java.io.CharArrayReader;
import java.io.Reader;
import java.io.StringReader;
import java.lang.ref.SoftReference;

public final class JSONReaderScanner extends JSONLexerBase {
    public static int BUF_INIT_LEN = 8192;
    private static final ThreadLocal<SoftReference<char[]>> BUF_REF_LOCAL = new ThreadLocal();
    private char[] buf;
    private int bufLength;
    private Reader reader;

    public JSONReaderScanner(String str) {
        this(str, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONReaderScanner(String str, int i) {
        this(new StringReader(str), i);
    }

    public JSONReaderScanner(char[] cArr, int i) {
        this(cArr, i, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONReaderScanner(Reader reader) {
        this(reader, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONReaderScanner(Reader reader, int i) {
        super(i);
        this.reader = reader;
        SoftReference softReference = (SoftReference) BUF_REF_LOCAL.get();
        if (softReference != null) {
            this.buf = (char[]) softReference.get();
            BUF_REF_LOCAL.set(null);
        }
        if (this.buf == null) {
            this.buf = new char[BUF_INIT_LEN];
        }
        try {
            this.bufLength = reader.read(this.buf);
            this.bp = -1;
            next();
            if (this.ch == '﻿') {
                next();
            }
        } catch (Throwable e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    public JSONReaderScanner(char[] cArr, int i, int i2) {
        this(new CharArrayReader(cArr, 0, i), i2);
    }

    public final char charAt(int i) {
        if (i >= this.bufLength) {
            if (this.bufLength == -1) {
                if (i < this.sp) {
                    return this.buf[i];
                }
                return '\u001a';
            } else if (this.bp == 0) {
                Object obj = new char[((this.buf.length * 3) / 2)];
                System.arraycopy(this.buf, this.bp, obj, 0, this.bufLength);
                try {
                    this.bufLength = this.reader.read(obj, this.bufLength, obj.length - this.bufLength) + this.bufLength;
                    this.buf = obj;
                } catch (Throwable e) {
                    throw new JSONException(e.getMessage(), e);
                }
            } else {
                int i2 = this.bufLength - this.bp;
                if (i2 > 0) {
                    System.arraycopy(this.buf, this.bp, this.buf, 0, i2);
                }
                try {
                    this.bufLength = this.reader.read(this.buf, i2, this.buf.length - i2);
                    if (this.bufLength == 0) {
                        throw new JSONException("illegal state, textLength is zero");
                    } else if (this.bufLength == -1) {
                        return '\u001a';
                    } else {
                        this.bufLength += i2;
                        i -= this.bp;
                        this.np -= this.bp;
                        this.bp = 0;
                    }
                } catch (Throwable e2) {
                    throw new JSONException(e2.getMessage(), e2);
                }
            }
        }
        return this.buf[i];
    }

    public final int indexOf(char c, int i) {
        int i2 = i - this.bp;
        while (c != charAt(this.bp + i2)) {
            if (c == '\u001a') {
                return -1;
            }
            i2++;
        }
        return i2 + this.bp;
    }

    public final String addSymbol(int i, int i2, int i3, SymbolTable symbolTable) {
        return symbolTable.addSymbol(this.buf, i, i2, i3);
    }

    public final char next() {
        int i = this.bp + 1;
        this.bp = i;
        if (i >= this.bufLength) {
            if (this.bufLength == -1) {
                return '\u001a';
            }
            if (this.sp > 0) {
                i = this.bufLength - this.sp;
                if (this.ch == '\"') {
                    i--;
                }
                System.arraycopy(this.buf, i, this.buf, 0, this.sp);
            }
            this.np = -1;
            i = this.sp;
            this.bp = i;
            try {
                int i2 = this.bp;
                int length = this.buf.length - i2;
                if (length == 0) {
                    Object obj = new char[(this.buf.length * 2)];
                    System.arraycopy(this.buf, 0, obj, 0, this.buf.length);
                    this.buf = obj;
                    length = this.buf.length - i2;
                }
                this.bufLength = this.reader.read(this.buf, this.bp, length);
                if (this.bufLength == 0) {
                    throw new JSONException("illegal stat, textLength is zero");
                } else if (this.bufLength == -1) {
                    this.ch = '\u001a';
                    return '\u001a';
                } else {
                    this.bufLength += this.bp;
                }
            } catch (Throwable e) {
                throw new JSONException(e.getMessage(), e);
            }
        }
        char c = this.buf[i];
        this.ch = c;
        return c;
    }

    protected final void copyTo(int i, int i2, char[] cArr) {
        System.arraycopy(this.buf, i, cArr, 0, i2);
    }

    public final boolean charArrayCompare(char[] cArr) {
        for (int i = 0; i < cArr.length; i++) {
            if (charAt(this.bp + i) != cArr[i]) {
                return false;
            }
        }
        return true;
    }

    public byte[] bytesValue() {
        return IOUtils.decodeFast(this.buf, this.np + 1, this.sp);
    }

    protected final void arrayCopy(int i, char[] cArr, int i2, int i3) {
        System.arraycopy(this.buf, i, cArr, i2, i3);
    }

    public final String stringVal() {
        if (this.hasSpecial) {
            return new String(this.sbuf, 0, this.sp);
        }
        int i = this.np + 1;
        if (i < 0) {
            throw new IllegalStateException();
        } else if (i <= this.buf.length - this.sp) {
            return new String(this.buf, i, this.sp);
        } else {
            throw new IllegalStateException();
        }
    }

    public final String subString(int i, int i2) {
        if (i2 >= 0) {
            return new String(this.buf, i, i2);
        }
        throw new StringIndexOutOfBoundsException(i2);
    }

    public final String numberString() {
        int i = this.np;
        if (i == -1) {
            i = 0;
        }
        char charAt = charAt((this.sp + i) - 1);
        int i2 = this.sp;
        if (charAt == 'L' || charAt == 'S' || charAt == 'B' || charAt == 'F' || charAt == 'D') {
            i2--;
        }
        return new String(this.buf, i, i2);
    }

    public void close() {
        super.close();
        BUF_REF_LOCAL.set(new SoftReference(this.buf));
        this.buf = null;
        IOUtils.close(this.reader);
    }

    public boolean isEOF() {
        return this.bufLength == -1 || this.bp == this.buf.length || (this.ch == '\u001a' && this.bp + 1 == this.buf.length);
    }
}
