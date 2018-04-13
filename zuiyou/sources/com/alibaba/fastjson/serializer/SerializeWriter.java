package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.JSONLexer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;

public final class SerializeWriter extends Writer {
    public static final char[] DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    static final char[] DigitOnes = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    static final char[] DigitTens = new char[]{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '6', '6', '6', '6', '6', '6', '6', '6', '6', '6', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9'};
    static final char[] ascii_chars = new char[]{'0', '0', '0', '1', '0', '2', '0', '3', '0', '4', '0', '5', '0', '6', '0', '7', '0', '8', '0', '9', '0', 'A', '0', 'B', '0', 'C', '0', 'D', '0', 'E', '0', 'F', '1', '0', '1', '1', '1', '2', '1', '3', '1', '4', '1', '5', '1', '6', '1', '7', '1', '8', '1', '9', '1', 'A', '1', 'B', '1', 'C', '1', 'D', '1', 'E', '1', 'F', '2', '0', '2', '1', '2', '2', '2', '3', '2', '4', '2', '5', '2', '6', '2', '7', '2', '8', '2', '9', '2', 'A', '2', 'B', '2', 'C', '2', 'D', '2', 'E', '2', 'F'};
    private static final ThreadLocal<char[]> bufLocal = new ThreadLocal();
    static final char[] digits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    static final char[] replaceChars = new char[93];
    static final int[] sizeTable = new int[]{9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE};
    static final byte[] specicalFlags_doubleQuotes = new byte[161];
    static final byte[] specicalFlags_singleQuotes = new byte[161];
    protected char[] buf;
    protected int count;
    protected int features;
    protected final Writer writer;

    static {
        int i;
        specicalFlags_doubleQuotes[0] = (byte) 4;
        specicalFlags_doubleQuotes[1] = (byte) 4;
        specicalFlags_doubleQuotes[2] = (byte) 4;
        specicalFlags_doubleQuotes[3] = (byte) 4;
        specicalFlags_doubleQuotes[4] = (byte) 4;
        specicalFlags_doubleQuotes[5] = (byte) 4;
        specicalFlags_doubleQuotes[6] = (byte) 4;
        specicalFlags_doubleQuotes[7] = (byte) 4;
        specicalFlags_doubleQuotes[8] = (byte) 1;
        specicalFlags_doubleQuotes[9] = (byte) 1;
        specicalFlags_doubleQuotes[10] = (byte) 1;
        specicalFlags_doubleQuotes[11] = (byte) 4;
        specicalFlags_doubleQuotes[12] = (byte) 1;
        specicalFlags_doubleQuotes[13] = (byte) 1;
        specicalFlags_doubleQuotes[34] = (byte) 1;
        specicalFlags_doubleQuotes[92] = (byte) 1;
        specicalFlags_singleQuotes[0] = (byte) 4;
        specicalFlags_singleQuotes[1] = (byte) 4;
        specicalFlags_singleQuotes[2] = (byte) 4;
        specicalFlags_singleQuotes[3] = (byte) 4;
        specicalFlags_singleQuotes[4] = (byte) 4;
        specicalFlags_singleQuotes[5] = (byte) 4;
        specicalFlags_singleQuotes[6] = (byte) 4;
        specicalFlags_singleQuotes[7] = (byte) 4;
        specicalFlags_singleQuotes[8] = (byte) 1;
        specicalFlags_singleQuotes[9] = (byte) 1;
        specicalFlags_singleQuotes[10] = (byte) 1;
        specicalFlags_singleQuotes[11] = (byte) 4;
        specicalFlags_singleQuotes[12] = (byte) 1;
        specicalFlags_singleQuotes[13] = (byte) 1;
        specicalFlags_singleQuotes[92] = (byte) 1;
        specicalFlags_singleQuotes[39] = (byte) 1;
        for (i = 14; i <= 31; i++) {
            specicalFlags_doubleQuotes[i] = (byte) 4;
            specicalFlags_singleQuotes[i] = (byte) 4;
        }
        for (i = 127; i < 160; i++) {
            specicalFlags_doubleQuotes[i] = (byte) 4;
            specicalFlags_singleQuotes[i] = (byte) 4;
        }
        replaceChars[0] = '0';
        replaceChars[1] = '1';
        replaceChars[2] = '2';
        replaceChars[3] = '3';
        replaceChars[4] = '4';
        replaceChars[5] = '5';
        replaceChars[6] = '6';
        replaceChars[7] = '7';
        replaceChars[8] = 'b';
        replaceChars[9] = 't';
        replaceChars[10] = 'n';
        replaceChars[11] = 'v';
        replaceChars[12] = 'f';
        replaceChars[13] = 'r';
        replaceChars[34] = '\"';
        replaceChars[39] = '\'';
        replaceChars[47] = '/';
        replaceChars[92] = '\\';
    }

    public SerializeWriter() {
        this((Writer) null);
    }

    public SerializeWriter(Writer writer) {
        this.writer = writer;
        this.features = JSON.DEFAULT_GENERATE_FEATURE;
        this.buf = (char[]) bufLocal.get();
        if (bufLocal != null) {
            bufLocal.set(null);
        }
        if (this.buf == null) {
            this.buf = new char[1024];
        }
    }

    public SerializeWriter(SerializerFeature... serializerFeatureArr) {
        this(null, 0, serializerFeatureArr);
    }

    public SerializeWriter(Writer writer, int i, SerializerFeature[] serializerFeatureArr) {
        this.writer = writer;
        this.buf = (char[]) bufLocal.get();
        if (this.buf != null) {
            bufLocal.set(null);
        }
        if (this.buf == null) {
            this.buf = new char[1024];
        }
        for (SerializerFeature serializerFeature : serializerFeatureArr) {
            i |= serializerFeature.mask;
        }
        this.features = i;
    }

    public SerializeWriter(int i) {
        this(null, i);
    }

    public SerializeWriter(Writer writer, int i) {
        this.writer = writer;
        if (i <= 0) {
            throw new IllegalArgumentException("Negative initial size: " + i);
        }
        this.buf = new char[i];
    }

    public void config(SerializerFeature serializerFeature, boolean z) {
        if (z) {
            this.features |= serializerFeature.mask;
        } else {
            this.features &= serializerFeature.mask ^ -1;
        }
    }

    public boolean isEnabled(SerializerFeature serializerFeature) {
        return (this.features & serializerFeature.mask) != 0;
    }

    public void write(int i) {
        int i2 = this.count + 1;
        if (i2 > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i2);
            } else {
                flush();
                i2 = 1;
            }
        }
        this.buf[this.count] = (char) i;
        this.count = i2;
    }

    public void write(char[] cArr, int i, int i2) {
        if (i < 0 || i > cArr.length || i2 < 0 || i + i2 > cArr.length || i + i2 < 0) {
            throw new IndexOutOfBoundsException();
        } else if (i2 != 0) {
            int i3 = this.count + i2;
            int i4;
            if (i3 <= this.buf.length) {
                i4 = i3;
                i3 = i2;
                i2 = i4;
            } else if (this.writer == null) {
                expandCapacity(i3);
                i4 = i3;
                i3 = i2;
                i2 = i4;
            } else {
                do {
                    i3 = this.buf.length - this.count;
                    System.arraycopy(cArr, i, this.buf, this.count, i3);
                    this.count = this.buf.length;
                    flush();
                    i2 -= i3;
                    i += i3;
                } while (i2 > this.buf.length);
                i3 = i2;
            }
            System.arraycopy(cArr, i, this.buf, this.count, i3);
            this.count = i2;
        }
    }

    protected void expandCapacity(int i) {
        int length = ((this.buf.length * 3) / 2) + 1;
        if (length >= i) {
            i = length;
        }
        Object obj = new char[i];
        System.arraycopy(this.buf, 0, obj, 0, this.count);
        this.buf = obj;
    }

    public void write(String str, int i, int i2) {
        int i3 = this.count + i2;
        int i4;
        if (i3 <= this.buf.length) {
            i4 = i3;
            i3 = i2;
            i2 = i4;
        } else if (this.writer == null) {
            expandCapacity(i3);
            i4 = i3;
            i3 = i2;
            i2 = i4;
        } else {
            do {
                i3 = this.buf.length - this.count;
                str.getChars(i, i + i3, this.buf, this.count);
                this.count = this.buf.length;
                flush();
                i2 -= i3;
                i += i3;
            } while (i2 > this.buf.length);
            i3 = i2;
        }
        str.getChars(i, i3 + i, this.buf, this.count);
        this.count = i2;
    }

    public void writeTo(Writer writer) throws IOException {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        writer.write(this.buf, 0, this.count);
    }

    public void writeTo(OutputStream outputStream, String str) throws IOException {
        writeTo(outputStream, Charset.forName(str));
    }

    public void writeTo(OutputStream outputStream, Charset charset) throws IOException {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        outputStream.write(new String(this.buf, 0, this.count).getBytes(charset.name()));
    }

    public SerializeWriter append(CharSequence charSequence) {
        String charSequence2 = charSequence == null ? "null" : charSequence.toString();
        write(charSequence2, 0, charSequence2.length());
        return this;
    }

    public SerializeWriter append(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            charSequence = "null";
        }
        String charSequence2 = charSequence.subSequence(i, i2).toString();
        write(charSequence2, 0, charSequence2.length());
        return this;
    }

    public SerializeWriter append(char c) {
        write((int) c);
        return this;
    }

    public byte[] toBytes(String str) {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        if (str == null) {
            str = "UTF-8";
        }
        try {
            return new String(this.buf, 0, this.count).getBytes(str);
        } catch (Throwable e) {
            throw new JSONException("toBytes error", e);
        }
    }

    public String toString() {
        return new String(this.buf, 0, this.count);
    }

    public void close() {
        if (this.writer != null && this.count > 0) {
            flush();
        }
        if (this.buf.length <= 8192) {
            bufLocal.set(this.buf);
        }
        this.buf = null;
    }

    public void write(String str) {
        if (str == null) {
            writeNull();
        } else {
            write(str, 0, str.length());
        }
    }

    public void writeInt(int i) {
        if (i == Integer.MIN_VALUE) {
            write("-2147483648");
            return;
        }
        int i2 = 0;
        while ((i < 0 ? -i : i) > sizeTable[i2]) {
            i2++;
        }
        int i3 = i2 + 1;
        if (i < 0) {
            i3++;
        }
        i2 = this.count + i3;
        if (i2 > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i2);
            } else {
                char[] cArr = new char[i3];
                getChars((long) i, i3, cArr);
                write(cArr, 0, cArr.length);
                return;
            }
        }
        getChars((long) i, i2, this.buf);
        this.count = i2;
    }

    public void writeByteArray(byte[] bArr) {
        int i = 0;
        int length = bArr.length;
        int i2 = (this.features & SerializerFeature.UseSingleQuotes.mask) != 0 ? 1 : 0;
        if (i2 != 0) {
            int i3 = '\'';
        } else {
            char c = '\"';
        }
        if (length == 0) {
            write(i2 != 0 ? "''" : "\"\"");
            return;
        }
        char[] cArr = JSONLexer.CA;
        int i4 = (length / 3) * 3;
        int i5 = (((length - 1) / 3) + 1) << 2;
        i2 = this.count;
        int i6 = (i5 + this.count) + 2;
        if (i6 > this.buf.length) {
            if (this.writer != null) {
                write(i3);
                i5 = 0;
                while (i5 < i4) {
                    i2 = i5 + 1;
                    i6 = i2 + 1;
                    i2 = ((bArr[i2] & 255) << 8) | ((bArr[i5] & 255) << 16);
                    i5 = i6 + 1;
                    i2 |= bArr[i6] & 255;
                    write(cArr[(i2 >>> 18) & 63]);
                    write(cArr[(i2 >>> 12) & 63]);
                    write(cArr[(i2 >>> 6) & 63]);
                    write(cArr[i2 & 63]);
                }
                i5 = length - i4;
                if (i5 > 0) {
                    i2 = (bArr[i4] & 255) << 10;
                    if (i5 == 2) {
                        i = (bArr[length - 1] & 255) << 2;
                    }
                    i |= i2;
                    write(cArr[i >> 12]);
                    write(cArr[(i >>> 6) & 63]);
                    if (i5 == 2) {
                        i = cArr[i & 63];
                    } else {
                        i = 61;
                    }
                    write(i);
                    write(61);
                }
                write(i3);
                return;
            }
            expandCapacity(i6);
        }
        this.count = i6;
        i5 = i2 + 1;
        this.buf[i2] = i3;
        i2 = 0;
        while (i2 < i4) {
            int i7 = i2 + 1;
            int i8 = i7 + 1;
            i7 = ((bArr[i7] & 255) << 8) | ((bArr[i2] & 255) << 16);
            i2 = i8 + 1;
            i7 |= bArr[i8] & 255;
            int i9 = i5 + 1;
            this.buf[i5] = cArr[(i7 >>> 18) & 63];
            i8 = i9 + 1;
            this.buf[i9] = cArr[(i7 >>> 12) & 63];
            i9 = i8 + 1;
            this.buf[i8] = cArr[(i7 >>> 6) & 63];
            i5 = i9 + 1;
            this.buf[i9] = cArr[i7 & 63];
        }
        i5 = length - i4;
        if (i5 > 0) {
            char c2;
            i2 = (bArr[i4] & 255) << 10;
            if (i5 == 2) {
                i = (bArr[length - 1] & 255) << 2;
            }
            i |= i2;
            this.buf[i6 - 5] = cArr[i >> 12];
            this.buf[i6 - 4] = cArr[(i >>> 6) & 63];
            char[] cArr2 = this.buf;
            length = i6 - 3;
            if (i5 == 2) {
                c2 = cArr[i & 63];
            } else {
                c2 = '=';
            }
            cArr2[length] = c2;
            this.buf[i6 - 2] = '=';
        }
        this.buf[i6 - 1] = i3;
    }

    public void writeLong(long j) {
        if (j == Long.MIN_VALUE) {
            write("-9223372036854775808");
            return;
        }
        int i;
        long j2 = j < 0 ? -j : j;
        int i2 = 1;
        long j3 = 10;
        while (i2 < 19) {
            if (j2 < j3) {
                i = i2;
                break;
            } else {
                j3 *= 10;
                i2++;
            }
        }
        i = 0;
        if (i == 0) {
            i = 19;
        }
        if (j < 0) {
            i++;
        }
        int i3 = this.count + i;
        if (i3 > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i3);
            } else {
                char[] cArr = new char[i];
                getChars(j, i, cArr);
                write(cArr, 0, cArr.length);
                return;
            }
        }
        getChars(j, i3, this.buf);
        this.count = i3;
    }

    public void writeNull() {
        write("null");
    }

    protected void writeStringWithDoubleQuote(String str, char c, boolean z) {
        if (str == null) {
            writeNull();
            if (c != '\u0000') {
                write((int) c);
                return;
            }
            return;
        }
        int length = str.length();
        int i = (this.count + length) + 2;
        if (c != '\u0000') {
            i++;
        }
        if (i > this.buf.length) {
            if (this.writer != null) {
                write(34);
                for (i = 0; i < str.length(); i++) {
                    length = str.charAt(i);
                    if ((length >= specicalFlags_doubleQuotes.length || specicalFlags_doubleQuotes[length] == (byte) 0) && (length != '/' || (this.features & SerializerFeature.WriteSlashAsSpecial.mask) == 0)) {
                        write(length);
                    } else {
                        write(92);
                        write(replaceChars[length]);
                    }
                }
                write(34);
                if (c != '\u0000') {
                    write((int) c);
                    return;
                }
                return;
            }
            expandCapacity(i);
        }
        int i2 = this.count + 1;
        int i3 = i2 + length;
        this.buf[this.count] = '\"';
        str.getChars(0, length, this.buf, i2);
        this.count = i;
        int i4 = 0;
        int i5 = -1;
        char c2 = '\u0000';
        if (z) {
            int i6;
            int i7;
            int i8;
            length = i2;
            int i9 = i;
            i = -1;
            while (length < i3) {
                char c3;
                char c4 = this.buf[length];
                if (c4 == ' ') {
                    i6 = i4 + 1;
                    i5 = i9 + 4;
                    if (i == -1) {
                        c3 = c4;
                        i4 = i5;
                        i5 = i6;
                        i7 = length;
                        i6 = length;
                    }
                    i4 = i5;
                    i5 = i6;
                    i6 = length;
                    i8 = i;
                    c3 = c4;
                    i7 = i8;
                } else {
                    if (c4 < ']') {
                        Object obj;
                        if (c4 == ' ') {
                            obj = null;
                        } else if (c4 == '/' && (this.features & SerializerFeature.WriteSlashAsSpecial.mask) != 0) {
                            obj = 1;
                        } else if (c4 > '#' && c4 != '\\') {
                            obj = null;
                        } else if (c4 <= '\u001f' || c4 == '\\' || c4 == '\"') {
                            obj = 1;
                        } else {
                            obj = null;
                        }
                        if (obj != null) {
                            i6 = i4 + 1;
                            if (c4 >= specicalFlags_doubleQuotes.length || specicalFlags_doubleQuotes[c4] != (byte) 4) {
                                i5 = i9;
                            } else {
                                i5 = i9 + 4;
                            }
                            if (i == -1) {
                                c3 = c4;
                                i4 = i5;
                                i5 = i6;
                                i7 = length;
                                i6 = length;
                            }
                            i4 = i5;
                            i5 = i6;
                            i6 = length;
                            i8 = i;
                            c3 = c4;
                            i7 = i8;
                        }
                    } else if (c4 >= '' && c4 < ' ') {
                        if (i == -1) {
                            i = length;
                        }
                        i6 = i4 + 1;
                        i4 = i9 + 4;
                        i5 = i6;
                        i6 = length;
                        i8 = i;
                        c3 = c4;
                        i7 = i8;
                    }
                    i7 = i;
                    c3 = c2;
                    i6 = i5;
                    i5 = i4;
                    i4 = i9;
                }
                length++;
                i9 = i4;
                i4 = i5;
                i5 = i6;
                c2 = c3;
                i = i7;
            }
            if (i4 > 0) {
                length = i9 + i4;
                if (length > this.buf.length) {
                    expandCapacity(length);
                }
                this.count = length;
                if (i4 == 1) {
                    if (c2 == ' ') {
                        System.arraycopy(this.buf, i5 + 1, this.buf, i5 + 6, (i3 - i5) - 1);
                        this.buf[i5] = '\\';
                        length = i5 + 1;
                        this.buf[length] = 'u';
                        length++;
                        this.buf[length] = '2';
                        length++;
                        this.buf[length] = '0';
                        length++;
                        this.buf[length] = '2';
                        this.buf[length + 1] = '8';
                    } else if (c2 >= specicalFlags_doubleQuotes.length || specicalFlags_doubleQuotes[c2] != (byte) 4) {
                        System.arraycopy(this.buf, i5 + 1, this.buf, i5 + 2, (i3 - i5) - 1);
                        this.buf[i5] = '\\';
                        this.buf[i5 + 1] = replaceChars[c2];
                    } else {
                        System.arraycopy(this.buf, i5 + 1, this.buf, i5 + 6, (i3 - i5) - 1);
                        length = i5 + 1;
                        this.buf[i5] = '\\';
                        i7 = length + 1;
                        this.buf[length] = 'u';
                        length = i7 + 1;
                        this.buf[i7] = DIGITS[(c2 >>> 12) & 15];
                        i7 = length + 1;
                        this.buf[length] = DIGITS[(c2 >>> 8) & 15];
                        length = i7 + 1;
                        this.buf[i7] = DIGITS[(c2 >>> 4) & 15];
                        i7 = length + 1;
                        this.buf[length] = DIGITS[c2 & 15];
                    }
                } else if (i4 > 1) {
                    i7 = i3;
                    i8 = i;
                    length = i8;
                    for (i -= i2; i < str.length(); i++) {
                        char charAt = str.charAt(i);
                        if ((charAt < specicalFlags_doubleQuotes.length && specicalFlags_doubleQuotes[charAt] != (byte) 0) || (charAt == '/' && (this.features & SerializerFeature.WriteSlashAsSpecial.mask) != 0)) {
                            i4 = length + 1;
                            this.buf[length] = '\\';
                            if (specicalFlags_doubleQuotes[charAt] == (byte) 4) {
                                i6 = i4 + 1;
                                this.buf[i4] = 'u';
                                i4 = i6 + 1;
                                this.buf[i6] = DIGITS[(charAt >>> 12) & 15];
                                i6 = i4 + 1;
                                this.buf[i4] = DIGITS[(charAt >>> 8) & 15];
                                i4 = i6 + 1;
                                this.buf[i6] = DIGITS[(charAt >>> 4) & 15];
                                length = i4 + 1;
                                this.buf[i4] = DIGITS[charAt & 15];
                                i7 += 5;
                            } else {
                                length = i4 + 1;
                                this.buf[i4] = replaceChars[charAt];
                                i7++;
                            }
                        } else if (charAt == ' ') {
                            i4 = length + 1;
                            this.buf[length] = '\\';
                            i6 = i4 + 1;
                            this.buf[i4] = 'u';
                            i4 = i6 + 1;
                            this.buf[i6] = DIGITS[(charAt >>> 12) & 15];
                            i6 = i4 + 1;
                            this.buf[i4] = DIGITS[(charAt >>> 8) & 15];
                            i4 = i6 + 1;
                            this.buf[i6] = DIGITS[(charAt >>> 4) & 15];
                            length = i4 + 1;
                            this.buf[i4] = DIGITS[charAt & 15];
                            i7 += 5;
                        } else {
                            i6 = length + 1;
                            this.buf[length] = charAt;
                            length = i6;
                        }
                    }
                }
            }
        }
        if (c != '\u0000') {
            this.buf[this.count - 2] = '\"';
            this.buf[this.count - 1] = c;
            return;
        }
        this.buf[this.count - 1] = '\"';
    }

    public void write(boolean z) {
        write(z ? "true" : "false");
    }

    public void writeString(String str) {
        if ((this.features & SerializerFeature.UseSingleQuotes.mask) != 0) {
            writeStringWithSingleQuote(str);
        } else {
            writeStringWithDoubleQuote(str, '\u0000', true);
        }
    }

    protected void writeStringWithSingleQuote(String str) {
        int i;
        if (str == null) {
            i = this.count + 4;
            if (i > this.buf.length) {
                expandCapacity(i);
            }
            "null".getChars(0, 4, this.buf, this.count);
            this.count = i;
            return;
        }
        int charAt;
        i = str.length();
        int i2 = (this.count + i) + 2;
        if (i2 > this.buf.length) {
            if (this.writer != null) {
                write(39);
                for (i = 0; i < str.length(); i++) {
                    charAt = str.charAt(i);
                    if (charAt <= '\r' || charAt == '\\' || charAt == '\'' || (charAt == '/' && (this.features & SerializerFeature.WriteSlashAsSpecial.mask) != 0)) {
                        write(92);
                        write(replaceChars[charAt]);
                    } else {
                        write(charAt);
                    }
                }
                write(39);
                return;
            }
            expandCapacity(i2);
        }
        int i3 = this.count + 1;
        int i4 = i3 + i;
        this.buf[this.count] = '\'';
        str.getChars(0, i, this.buf, i3);
        this.count = i2;
        int i5 = 0;
        int i6 = -1;
        i = 0;
        int i7 = i3;
        while (i7 < i4) {
            char c = this.buf[i7];
            if (c <= '\r' || c == '\\' || c == '\'' || (c == '/' && (this.features & SerializerFeature.WriteSlashAsSpecial.mask) != 0)) {
                i6 = i5 + 1;
                i = c;
                charAt = i7;
            } else {
                charAt = i6;
                i6 = i5;
            }
            i7++;
            i5 = i6;
            i6 = charAt;
        }
        charAt = i2 + i5;
        if (charAt > this.buf.length) {
            expandCapacity(charAt);
        }
        this.count = charAt;
        if (i5 == 1) {
            System.arraycopy(this.buf, i6 + 1, this.buf, i6 + 2, (i4 - i6) - 1);
            this.buf[i6] = '\\';
            this.buf[i6 + 1] = replaceChars[i];
        } else if (i5 > 1) {
            System.arraycopy(this.buf, i6 + 1, this.buf, i6 + 2, (i4 - i6) - 1);
            this.buf[i6] = '\\';
            i6++;
            this.buf[i6] = replaceChars[i];
            i = i4 + 1;
            for (charAt = i6 - 2; charAt >= i3; charAt--) {
                char c2 = this.buf[charAt];
                if (c2 <= '\r' || c2 == '\\' || c2 == '\'' || (c2 == '/' && (this.features & SerializerFeature.WriteSlashAsSpecial.mask) != 0)) {
                    System.arraycopy(this.buf, charAt + 1, this.buf, charAt + 2, (i - charAt) - 1);
                    this.buf[charAt] = '\\';
                    this.buf[charAt + 1] = replaceChars[c2];
                    i++;
                }
            }
        }
        this.buf[this.count - 1] = '\'';
    }

    public void writeFieldName(String str, boolean z) {
        if ((this.features & SerializerFeature.UseSingleQuotes.mask) != 0) {
            if ((this.features & SerializerFeature.QuoteFieldNames.mask) != 0) {
                writeStringWithSingleQuote(str);
                write(58);
                return;
            }
            writeKeyWithSingleQuoteIfHasSpecial(str);
        } else if ((this.features & SerializerFeature.QuoteFieldNames.mask) != 0) {
            writeStringWithDoubleQuote(str, ':', z);
        } else {
            writeKeyWithDoubleQuoteIfHasSpecial(str);
        }
    }

    private void writeKeyWithDoubleQuoteIfHasSpecial(String str) {
        int i;
        Object obj;
        int i2;
        int charAt;
        int length = str.length();
        int i3 = (this.count + length) + 1;
        if (i3 > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i3);
            } else if (length == 0) {
                write(34);
                write(34);
                write(58);
                return;
            } else {
                for (i = 0; i < length; i++) {
                    char charAt2 = str.charAt(i);
                    if (charAt2 < specicalFlags_doubleQuotes.length && specicalFlags_doubleQuotes[charAt2] != (byte) 0) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj != null) {
                    write(34);
                }
                for (i2 = 0; i2 < length; i2++) {
                    charAt = str.charAt(i2);
                    if (charAt >= specicalFlags_doubleQuotes.length || specicalFlags_doubleQuotes[charAt] == (byte) 0) {
                        write(charAt);
                    } else {
                        write(92);
                        write(replaceChars[charAt]);
                    }
                }
                if (obj != null) {
                    write(34);
                }
                write(58);
                return;
            }
        }
        if (length == 0) {
            if (this.count + 3 > this.buf.length) {
                expandCapacity(this.count + 3);
            }
            char[] cArr = this.buf;
            i = this.count;
            this.count = i + 1;
            cArr[i] = '\"';
            cArr = this.buf;
            i = this.count;
            this.count = i + 1;
            cArr[i] = '\"';
            cArr = this.buf;
            i = this.count;
            this.count = i + 1;
            cArr[i] = ':';
            return;
        }
        int i4 = this.count;
        charAt = i4 + length;
        str.getChars(0, length, this.buf, i4);
        this.count = i3;
        obj = null;
        i2 = i4;
        while (i2 < charAt) {
            char c = this.buf[i2];
            if (c < specicalFlags_doubleQuotes.length && specicalFlags_doubleQuotes[c] != (byte) 0) {
                if (obj == null) {
                    i3 += 3;
                    if (i3 > this.buf.length) {
                        expandCapacity(i3);
                    }
                    this.count = i3;
                    System.arraycopy(this.buf, i2 + 1, this.buf, i2 + 3, (charAt - i2) - 1);
                    System.arraycopy(this.buf, 0, this.buf, 1, i2);
                    this.buf[i4] = '\"';
                    i2++;
                    this.buf[i2] = '\\';
                    i2++;
                    this.buf[i2] = replaceChars[c];
                    charAt += 2;
                    this.buf[this.count - 2] = '\"';
                    obj = 1;
                } else {
                    i3++;
                    if (i3 > this.buf.length) {
                        expandCapacity(i3);
                    }
                    this.count = i3;
                    System.arraycopy(this.buf, i2 + 1, this.buf, i2 + 2, charAt - i2);
                    this.buf[i2] = '\\';
                    i2++;
                    this.buf[i2] = replaceChars[c];
                    charAt++;
                }
            }
            i2++;
        }
        this.buf[this.count - 1] = ':';
    }

    private void writeKeyWithSingleQuoteIfHasSpecial(String str) {
        int i;
        Object obj;
        int i2;
        int charAt;
        int length = str.length();
        int i3 = (this.count + length) + 1;
        if (i3 > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i3);
            } else if (length == 0) {
                write(39);
                write(39);
                write(58);
                return;
            } else {
                for (i = 0; i < length; i++) {
                    char charAt2 = str.charAt(i);
                    if (charAt2 < specicalFlags_singleQuotes.length && specicalFlags_singleQuotes[charAt2] != (byte) 0) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj != null) {
                    write(39);
                }
                for (i2 = 0; i2 < length; i2++) {
                    charAt = str.charAt(i2);
                    if (charAt >= specicalFlags_singleQuotes.length || specicalFlags_singleQuotes[charAt] == (byte) 0) {
                        write(charAt);
                    } else {
                        write(92);
                        write(replaceChars[charAt]);
                    }
                }
                if (obj != null) {
                    write(39);
                }
                write(58);
                return;
            }
        }
        if (length == 0) {
            if (this.count + 3 > this.buf.length) {
                expandCapacity(this.count + 3);
            }
            char[] cArr = this.buf;
            i = this.count;
            this.count = i + 1;
            cArr[i] = '\'';
            cArr = this.buf;
            i = this.count;
            this.count = i + 1;
            cArr[i] = '\'';
            cArr = this.buf;
            i = this.count;
            this.count = i + 1;
            cArr[i] = ':';
            return;
        }
        int i4 = this.count;
        charAt = i4 + length;
        str.getChars(0, length, this.buf, i4);
        this.count = i3;
        obj = null;
        i2 = i4;
        while (i2 < charAt) {
            char c = this.buf[i2];
            if (c < specicalFlags_singleQuotes.length && specicalFlags_singleQuotes[c] != (byte) 0) {
                if (obj == null) {
                    i3 += 3;
                    if (i3 > this.buf.length) {
                        expandCapacity(i3);
                    }
                    this.count = i3;
                    System.arraycopy(this.buf, i2 + 1, this.buf, i2 + 3, (charAt - i2) - 1);
                    System.arraycopy(this.buf, 0, this.buf, 1, i2);
                    this.buf[i4] = '\'';
                    i2++;
                    this.buf[i2] = '\\';
                    i2++;
                    this.buf[i2] = replaceChars[c];
                    charAt += 2;
                    this.buf[this.count - 2] = '\'';
                    obj = 1;
                } else {
                    i3++;
                    if (i3 > this.buf.length) {
                        expandCapacity(i3);
                    }
                    this.count = i3;
                    System.arraycopy(this.buf, i2 + 1, this.buf, i2 + 2, charAt - i2);
                    this.buf[i2] = '\\';
                    i2++;
                    this.buf[i2] = replaceChars[c];
                    charAt++;
                }
            }
            i2++;
        }
        this.buf[i3 - 1] = ':';
    }

    public void flush() {
        if (this.writer != null) {
            try {
                this.writer.write(this.buf, 0, this.count);
                this.writer.flush();
                this.count = 0;
            } catch (Throwable e) {
                throw new JSONException(e.getMessage(), e);
            }
        }
    }

    protected static void getChars(long j, int i, char[] cArr) {
        char c;
        int i2;
        int i3;
        int i4;
        if (j < 0) {
            j = -j;
            c = '-';
            i2 = i;
        } else {
            c = '\u0000';
            i2 = i;
        }
        while (j > 2147483647L) {
            long j2 = j / 100;
            i3 = (int) (j - (((j2 << 6) + (j2 << 5)) + (j2 << 2)));
            i2--;
            cArr[i2] = DigitOnes[i3];
            i = i2 - 1;
            cArr[i] = DigitTens[i3];
            i2 = i;
            j = j2;
        }
        i3 = (int) j;
        while (i3 >= 65536) {
            i4 = i3 / 100;
            i3 -= ((i4 << 6) + (i4 << 5)) + (i4 << 2);
            i2--;
            cArr[i2] = DigitOnes[i3];
            i2--;
            cArr[i2] = DigitTens[i3];
            i3 = i4;
        }
        i4 = i2;
        while (true) {
            i2 = (52429 * i3) >>> 19;
            int i5 = i3 - ((i2 << 3) + (i2 << 1));
            i3 = i4 - 1;
            cArr[i3] = digits[i5];
            if (i2 == 0) {
                break;
            }
            i4 = i3;
            i3 = i2;
        }
        if (c != '\u0000') {
            cArr[i3 - 1] = c;
        }
    }
}
