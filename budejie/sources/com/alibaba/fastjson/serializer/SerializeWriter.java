package com.alibaba.fastjson.serializer;

import com.ali.auth.third.core.model.Constants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.IOUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import org.apache.commons.httpclient.HttpState;

public final class SerializeWriter extends Writer {
    private static final ThreadLocal<SoftReference<char[]>> bufLocal = new ThreadLocal();
    protected boolean beanToArray;
    protected boolean browserCompatible;
    protected boolean browserSecure;
    protected char[] buf;
    protected SoftReference<char[]> bufLocalRef;
    protected int count;
    protected boolean disableCheckSpecialChar;
    protected boolean disableCircularReferenceDetect;
    protected int features;
    protected boolean ignoreNonFieldGetter;
    protected char keySeperator;
    protected boolean notWriteDefaultValue;
    protected boolean notWriteRootClassName;
    protected boolean prettyFormat;
    protected boolean quoteFieldNames;
    protected boolean skipTransientField;
    protected boolean sortField;
    protected boolean useSingleQuotes;
    protected boolean writeClassName;
    protected boolean writeDirect;
    protected boolean writeEnumUsingName;
    protected boolean writeEnumUsingToString;
    protected boolean writeMapNullValue;
    protected boolean writeNonStringValueAsString;
    private final Writer writer;

    public SerializeWriter() {
        this((Writer) null);
    }

    public SerializeWriter(Writer writer) {
        this.writer = writer;
        this.features = JSON.DEFAULT_GENERATE_FEATURE;
        computeFeatures();
        this.bufLocalRef = (SoftReference) bufLocal.get();
        if (this.bufLocalRef != null) {
            this.buf = (char[]) this.bufLocalRef.get();
            bufLocal.set(null);
        }
        if (this.buf == null) {
            this.buf = new char[1024];
        }
    }

    public SerializeWriter(SerializerFeature... serializerFeatureArr) {
        this(null, serializerFeatureArr);
    }

    public SerializeWriter(Writer writer, SerializerFeature... serializerFeatureArr) {
        this(writer, 0, serializerFeatureArr);
    }

    public SerializeWriter(Writer writer, int i, SerializerFeature... serializerFeatureArr) {
        this.writer = writer;
        this.bufLocalRef = (SoftReference) bufLocal.get();
        if (this.bufLocalRef != null) {
            this.buf = (char[]) this.bufLocalRef.get();
            bufLocal.set(null);
        }
        if (this.buf == null) {
            this.buf = new char[1024];
        }
        for (SerializerFeature mask : serializerFeatureArr) {
            i |= mask.getMask();
        }
        this.features = i;
        computeFeatures();
    }

    public int getBufferLength() {
        return this.buf.length;
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
            this.features |= serializerFeature.getMask();
            if (serializerFeature == SerializerFeature.WriteEnumUsingToString) {
                this.features &= SerializerFeature.WriteEnumUsingName.getMask() ^ -1;
            } else if (serializerFeature == SerializerFeature.WriteEnumUsingName) {
                this.features &= SerializerFeature.WriteEnumUsingToString.getMask() ^ -1;
            }
        } else {
            this.features &= serializerFeature.getMask() ^ -1;
        }
        computeFeatures();
    }

    protected void computeFeatures() {
        boolean z;
        boolean z2 = true;
        this.browserSecure = (this.features & SerializerFeature.BrowserSecure.mask) != 0;
        if ((this.features & SerializerFeature.BrowserCompatible.mask) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.browserCompatible = z;
        if ((this.features & SerializerFeature.QuoteFieldNames.mask) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.quoteFieldNames = z;
        if ((this.features & SerializerFeature.UseSingleQuotes.mask) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.useSingleQuotes = z;
        if ((this.features & SerializerFeature.SortField.mask) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.sortField = z;
        if ((this.features & SerializerFeature.DisableCircularReferenceDetect.mask) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.disableCircularReferenceDetect = z;
        if ((this.features & SerializerFeature.BeanToArray.mask) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.beanToArray = z;
        if ((this.features & SerializerFeature.PrettyFormat.mask) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.prettyFormat = z;
        if ((this.features & SerializerFeature.WriteClassName.mask) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.writeClassName = z;
        if ((this.features & SerializerFeature.NotWriteRootClassName.mask) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.notWriteRootClassName = z;
        if ((this.features & SerializerFeature.SkipTransientField.mask) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.skipTransientField = z;
        if ((this.features & SerializerFeature.IgnoreNonFieldGetter.mask) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.ignoreNonFieldGetter = z;
        if ((this.features & SerializerFeature.WriteNonStringValueAsString.mask) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.writeNonStringValueAsString = z;
        if ((this.features & SerializerFeature.NotWriteDefaultValue.mask) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.notWriteDefaultValue = z;
        if ((this.features & SerializerFeature.WriteEnumUsingName.mask) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.writeEnumUsingName = z;
        if ((this.features & SerializerFeature.WriteEnumUsingToString.mask) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.writeEnumUsingToString = z;
        if ((this.features & SerializerFeature.WriteMapNullValue.mask) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.writeMapNullValue = z;
        if ((this.features & SerializerFeature.DisableCheckSpecialChar.mask) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.disableCheckSpecialChar = z;
        if (!this.quoteFieldNames || this.useSingleQuotes || this.browserCompatible || this.browserSecure || this.browserCompatible || !this.writeEnumUsingName || this.writeEnumUsingToString || this.writeNonStringValueAsString || (this.features & SerializerFeature.WriteSlashAsSpecial.mask) != 0) {
            z2 = false;
        }
        this.writeDirect = z2;
        this.keySeperator = this.useSingleQuotes ? '\'' : '\"';
    }

    public boolean isPrettyFormat() {
        return this.prettyFormat;
    }

    public boolean isSortField() {
        return this.sortField;
    }

    public boolean isNotWriteDefaultValue() {
        return this.notWriteDefaultValue;
    }

    public boolean isWriteMapNullValue() {
        return this.writeMapNullValue;
    }

    public boolean isIgnoreNonFieldGetter() {
        return this.ignoreNonFieldGetter;
    }

    public boolean isSkipTransientField() {
        return this.skipTransientField;
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

    public void expandCapacity(int i) {
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
        outputStream.write(new String(this.buf, 0, this.count).getBytes(charset));
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

    public void reset() {
        this.count = 0;
    }

    public char[] toCharArray() {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        Object obj = new char[this.count];
        System.arraycopy(this.buf, 0, obj, 0, this.count);
        return obj;
    }

    public byte[] toBytes(String str) {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        if (str == null) {
            str = "UTF-8";
        }
        return new SerialWriterStringEncoder(Charset.forName(str)).encode(this.buf, 0, this.count);
    }

    public int size() {
        return this.count;
    }

    public String toString() {
        return new String(this.buf, 0, this.count);
    }

    public void close() {
        if (this.writer != null && this.count > 0) {
            flush();
        }
        if (this.buf.length <= 8192) {
            Object softReference;
            if (this.bufLocalRef == null || this.bufLocalRef.get() != this.buf) {
                softReference = new SoftReference(this.buf);
            } else {
                softReference = this.bufLocalRef;
            }
            bufLocal.set(softReference);
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
        int stringSize = i < 0 ? IOUtils.stringSize(-i) + 1 : IOUtils.stringSize(i);
        int i2 = this.count + stringSize;
        if (i2 > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i2);
            } else {
                char[] cArr = new char[stringSize];
                IOUtils.getChars(i, stringSize, cArr);
                write(cArr, 0, cArr.length);
                return;
            }
        }
        IOUtils.getChars(i, i2, this.buf);
        this.count = i2;
    }

    public void writeByteArray(byte[] bArr) {
        int i = 0;
        int length = bArr.length;
        if (this.useSingleQuotes) {
            int i2 = '\'';
        } else {
            char c = '\"';
        }
        if (length == 0) {
            write(this.useSingleQuotes ? "''" : "\"\"");
            return;
        }
        char[] cArr = IOUtils.CA;
        int i3 = (length / 3) * 3;
        int i4 = (((length - 1) / 3) + 1) << 2;
        int i5 = this.count;
        int i6 = (i4 + this.count) + 2;
        if (i6 > this.buf.length) {
            if (this.writer != null) {
                write(i2);
                i4 = 0;
                while (i4 < i3) {
                    i5 = i4 + 1;
                    i6 = i5 + 1;
                    i5 = ((bArr[i5] & 255) << 8) | ((bArr[i4] & 255) << 16);
                    i4 = i6 + 1;
                    i5 |= bArr[i6] & 255;
                    write(cArr[(i5 >>> 18) & 63]);
                    write(cArr[(i5 >>> 12) & 63]);
                    write(cArr[(i5 >>> 6) & 63]);
                    write(cArr[i5 & 63]);
                }
                i4 = length - i3;
                if (i4 > 0) {
                    i5 = (bArr[i3] & 255) << 10;
                    if (i4 == 2) {
                        i = (bArr[length - 1] & 255) << 2;
                    }
                    i |= i5;
                    write(cArr[i >> 12]);
                    write(cArr[(i >>> 6) & 63]);
                    if (i4 == 2) {
                        i = cArr[i & 63];
                    } else {
                        i = 61;
                    }
                    write(i);
                    write(61);
                }
                write(i2);
                return;
            }
            expandCapacity(i6);
        }
        this.count = i6;
        i4 = i5 + 1;
        this.buf[i5] = i2;
        i5 = 0;
        while (i5 < i3) {
            int i7 = i5 + 1;
            int i8 = i7 + 1;
            i7 = ((bArr[i7] & 255) << 8) | ((bArr[i5] & 255) << 16);
            i5 = i8 + 1;
            i7 |= bArr[i8] & 255;
            int i9 = i4 + 1;
            this.buf[i4] = cArr[(i7 >>> 18) & 63];
            i8 = i9 + 1;
            this.buf[i9] = cArr[(i7 >>> 12) & 63];
            i9 = i8 + 1;
            this.buf[i8] = cArr[(i7 >>> 6) & 63];
            i4 = i9 + 1;
            this.buf[i9] = cArr[i7 & 63];
        }
        i4 = length - i3;
        if (i4 > 0) {
            char c2;
            i5 = (bArr[i3] & 255) << 10;
            if (i4 == 2) {
                i = (bArr[length - 1] & 255) << 2;
            }
            i |= i5;
            this.buf[i6 - 5] = cArr[i >> 12];
            this.buf[i6 - 4] = cArr[(i >>> 6) & 63];
            char[] cArr2 = this.buf;
            length = i6 - 3;
            if (i4 == 2) {
                c2 = cArr[i & 63];
            } else {
                c2 = '=';
            }
            cArr2[length] = c2;
            this.buf[i6 - 2] = '=';
        }
        this.buf[i6 - 1] = i2;
    }

    public void writeFloatAndChar(float f, char c) {
        String f2 = Float.toString(f);
        if (f2.endsWith(".0")) {
            f2 = f2.substring(0, f2.length() - 2);
        }
        write(f2);
        write((int) c);
    }

    public void writeDoubleAndChar(double d, char c) {
        String d2 = Double.toString(d);
        if (d2.endsWith(".0")) {
            d2 = d2.substring(0, d2.length() - 2);
        }
        write(d2);
        write((int) c);
    }

    public void writeBooleanAndChar(boolean z, char c) {
        if (z) {
            if (c == ',') {
                write("true,");
            } else if (c == ']') {
                write("true]");
            } else {
                write(Constants.SERVICE_SCOPE_FLAG_VALUE);
                write((int) c);
            }
        } else if (c == ',') {
            write("false,");
        } else if (c == ']') {
            write("false]");
        } else {
            write(HttpState.PREEMPTIVE_DEFAULT);
            write((int) c);
        }
    }

    public void writeCharacterAndChar(char c, char c2) {
        writeString(Character.toString(c));
        write((int) c2);
    }

    public void writeEnum(Enum<?> enumR, char c) {
        if (enumR == null) {
            writeNull();
            write(44);
        } else if (isEnabled(SerializerFeature.WriteEnumUsingName)) {
            writeEnumValue(enumR.name(), c);
        } else if (isEnabled(SerializerFeature.WriteEnumUsingToString)) {
            writeEnumValue(enumR.toString(), c);
        } else {
            writeIntAndChar(enumR.ordinal(), c);
        }
    }

    private void writeEnumValue(String str, char c) {
        if (isEnabled(SerializerFeature.UseSingleQuotes)) {
            write(39);
            write(str);
            write(39);
            write((int) c);
            return;
        }
        write(34);
        write(str);
        write(34);
        write((int) c);
    }

    public void writeIntAndChar(int i, char c) {
        if (i == Integer.MIN_VALUE) {
            write("-2147483648");
            write((int) c);
            return;
        }
        int stringSize = (i < 0 ? IOUtils.stringSize(-i) + 1 : IOUtils.stringSize(i)) + this.count;
        int i2 = stringSize + 1;
        if (i2 > this.buf.length) {
            if (this.writer != null) {
                writeInt(i);
                write((int) c);
                return;
            }
            expandCapacity(i2);
        }
        IOUtils.getChars(i, stringSize, this.buf);
        this.buf[stringSize] = c;
        this.count = i2;
    }

    public void writeLongAndChar(long j, char c) throws IOException {
        Object obj = (!this.browserCompatible || isEnabled(SerializerFeature.WriteClassName) || (j <= 9007199254740991L && j >= -9007199254740991L)) ? null : 1;
        if (j == Long.MIN_VALUE) {
            if (obj != null) {
                write("\"-9223372036854775808\"");
            } else {
                write("-9223372036854775808");
            }
            write((int) c);
            return;
        }
        int stringSize = (j < 0 ? IOUtils.stringSize(-j) + 1 : IOUtils.stringSize(j)) + this.count;
        if (obj != null) {
            stringSize += 2;
        }
        int i = stringSize + 1;
        if (i > this.buf.length) {
            if (this.writer != null) {
                writeLong(j);
                write((int) c);
                return;
            }
            expandCapacity(i);
        }
        if (obj != null) {
            this.buf[this.count] = '\"';
            IOUtils.getChars(j, stringSize - 1, this.buf);
            this.buf[stringSize - 1] = '\"';
        } else {
            IOUtils.getChars(j, stringSize, this.buf);
        }
        this.buf[stringSize] = c;
        this.count = i;
    }

    public void writeLong(long j) {
        int i = (!this.browserCompatible || isEnabled(SerializerFeature.WriteClassName) || (j <= 9007199254740991L && j >= -9007199254740991L)) ? 0 : 1;
        if (j != Long.MIN_VALUE) {
            int stringSize = j < 0 ? IOUtils.stringSize(-j) + 1 : IOUtils.stringSize(j);
            int i2 = this.count + stringSize;
            if (i != 0) {
                i2 += 2;
            }
            if (i2 > this.buf.length) {
                if (this.writer == null) {
                    expandCapacity(i2);
                } else {
                    char[] cArr = new char[stringSize];
                    IOUtils.getChars(j, stringSize, cArr);
                    if (i != 0) {
                        write(34);
                        write(cArr, 0, cArr.length);
                        write(34);
                        return;
                    }
                    write(cArr, 0, cArr.length);
                    return;
                }
            }
            if (i != 0) {
                this.buf[this.count] = '\"';
                IOUtils.getChars(j, i2 - 1, this.buf);
                this.buf[i2 - 1] = '\"';
            } else {
                IOUtils.getChars(j, i2, this.buf);
            }
            this.count = i2;
        } else if (i != 0) {
            write("\"-9223372036854775808\"");
        } else {
            write("-9223372036854775808");
        }
    }

    public void writeNull() {
        write("null");
    }

    public void writeStringWithDoubleQuote(String str, char c) {
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
                    if (this.browserSecure) {
                        if ((length < '0' || length > '9') && ((length < 'a' || length > 'z') && !((length >= 'A' && length <= 'Z') || length == ',' || length == '.' || length == '_'))) {
                            write(92);
                            write(117);
                            write(IOUtils.DIGITS[(length >>> 12) & 15]);
                            write(IOUtils.DIGITS[(length >>> 8) & 15]);
                            write(IOUtils.DIGITS[(length >>> 4) & 15]);
                            write(IOUtils.DIGITS[length & 15]);
                        }
                        write(length);
                    } else if (!this.browserCompatible) {
                        if ((length < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[length] != (byte) 0) || (length == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                            write(92);
                            if (IOUtils.specicalFlags_doubleQuotes[length] == (byte) 4) {
                                write(117);
                                write(IOUtils.DIGITS[(length >>> 12) & 15]);
                                write(IOUtils.DIGITS[(length >>> 8) & 15]);
                                write(IOUtils.DIGITS[(length >>> 4) & 15]);
                                write(IOUtils.DIGITS[length & 15]);
                            } else {
                                write(IOUtils.replaceChars[length]);
                            }
                        }
                        write(length);
                    } else if (length == '\b' || length == '\f' || length == '\n' || length == '\r' || length == '\t' || length == '\"' || length == '/' || length == '\\') {
                        write(92);
                        write(IOUtils.replaceChars[length]);
                    } else if (length < ' ') {
                        write(92);
                        write(117);
                        write(48);
                        write(48);
                        write(IOUtils.ASCII_CHARS[length * 2]);
                        write(IOUtils.ASCII_CHARS[(length * 2) + 1]);
                    } else {
                        if (length >= '') {
                            write(92);
                            write(117);
                            write(IOUtils.DIGITS[(length >>> 12) & 15]);
                            write(IOUtils.DIGITS[(length >>> 8) & 15]);
                            write(IOUtils.DIGITS[(length >>> 4) & 15]);
                            write(IOUtils.DIGITS[length & 15]);
                        }
                        write(length);
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
        int i4;
        char c2;
        char c3;
        if (this.browserSecure) {
            i4 = i;
            i = -1;
            for (length = i2; length < i3; length++) {
                c2 = this.buf[length];
                if ((c2 < '0' || c2 > '9') && ((c2 < 'a' || c2 > 'z') && !((c2 >= 'A' && c2 <= 'Z') || c2 == ',' || c2 == '.' || c2 == '_'))) {
                    i4 += 5;
                    i = length;
                }
            }
            if (i4 > this.buf.length) {
                expandCapacity(i4);
            }
            this.count = i4;
            i = i3;
            for (length = i; length >= i2; length--) {
                c3 = this.buf[length];
                if ((c3 < '0' || c3 > '9') && ((c3 < 'a' || c3 > 'z') && !((c3 >= 'A' && c3 <= 'Z') || c3 == ',' || c3 == '.' || c3 == '_'))) {
                    System.arraycopy(this.buf, length + 1, this.buf, length + 6, (i - length) - 1);
                    this.buf[length] = '\\';
                    this.buf[length + 1] = 'u';
                    this.buf[length + 2] = IOUtils.DIGITS[(c3 >>> 12) & 15];
                    this.buf[length + 3] = IOUtils.DIGITS[(c3 >>> 8) & 15];
                    this.buf[length + 4] = IOUtils.DIGITS[(c3 >>> 4) & 15];
                    this.buf[length + 5] = IOUtils.DIGITS[c3 & 15];
                    i += 5;
                }
            }
            if (c != '\u0000') {
                this.buf[this.count - 2] = '\"';
                this.buf[this.count - 1] = c;
                return;
            }
            this.buf[this.count - 1] = '\"';
        } else if (this.browserCompatible) {
            i4 = i;
            i = -1;
            for (length = i2; length < i3; length++) {
                c2 = this.buf[length];
                if (c2 == '\"' || c2 == '/' || c2 == '\\') {
                    i4++;
                    i = length;
                } else if (c2 == '\b' || c2 == '\f' || c2 == '\n' || c2 == '\r' || c2 == '\t') {
                    i4++;
                    i = length;
                } else if (c2 < ' ') {
                    i4 += 5;
                    i = length;
                } else if (c2 >= '') {
                    i4 += 5;
                    i = length;
                }
            }
            if (i4 > this.buf.length) {
                expandCapacity(i4);
            }
            this.count = i4;
            i = i3;
            for (length = i; length >= i2; length--) {
                c3 = this.buf[length];
                if (c3 == '\b' || c3 == '\f' || c3 == '\n' || c3 == '\r' || c3 == '\t') {
                    System.arraycopy(this.buf, length + 1, this.buf, length + 2, (i - length) - 1);
                    this.buf[length] = '\\';
                    this.buf[length + 1] = IOUtils.replaceChars[c3];
                    i++;
                } else if (c3 == '\"' || c3 == '/' || c3 == '\\') {
                    System.arraycopy(this.buf, length + 1, this.buf, length + 2, (i - length) - 1);
                    this.buf[length] = '\\';
                    this.buf[length + 1] = c3;
                    i++;
                } else if (c3 < ' ') {
                    System.arraycopy(this.buf, length + 1, this.buf, length + 6, (i - length) - 1);
                    this.buf[length] = '\\';
                    this.buf[length + 1] = 'u';
                    this.buf[length + 2] = '0';
                    this.buf[length + 3] = '0';
                    this.buf[length + 4] = IOUtils.ASCII_CHARS[c3 * 2];
                    this.buf[length + 5] = IOUtils.ASCII_CHARS[(c3 * 2) + 1];
                    i += 5;
                } else if (c3 >= '') {
                    System.arraycopy(this.buf, length + 1, this.buf, length + 6, (i - length) - 1);
                    this.buf[length] = '\\';
                    this.buf[length + 1] = 'u';
                    this.buf[length + 2] = IOUtils.DIGITS[(c3 >>> 12) & 15];
                    this.buf[length + 3] = IOUtils.DIGITS[(c3 >>> 8) & 15];
                    this.buf[length + 4] = IOUtils.DIGITS[(c3 >>> 4) & 15];
                    this.buf[length + 5] = IOUtils.DIGITS[c3 & 15];
                    i += 5;
                }
            }
            if (c != '\u0000') {
                this.buf[this.count - 2] = '\"';
                this.buf[this.count - 1] = c;
                return;
            }
            this.buf[this.count - 1] = '\"';
        } else {
            int i5;
            int i6;
            int i7 = 0;
            int i8 = -1;
            c2 = '\u0000';
            length = i2;
            int i9 = i;
            i = -1;
            while (length < i3) {
                char c4;
                c3 = this.buf[length];
                if (c3 == ' ') {
                    i5 = i7 + 1;
                    i8 = i9 + 4;
                    if (i == -1) {
                        c4 = c3;
                        i7 = i8;
                        i8 = i5;
                        i4 = length;
                        i5 = length;
                    }
                    i7 = i8;
                    i8 = i5;
                    i5 = length;
                    i6 = i;
                    c4 = c3;
                    i4 = i6;
                } else {
                    if (c3 >= ']') {
                        if (c3 >= '' && c3 <= ' ') {
                            if (i == -1) {
                                i = length;
                            }
                            i5 = i7 + 1;
                            i7 = i9 + 4;
                            i8 = i5;
                            i5 = length;
                            i6 = i;
                            c4 = c3;
                            i4 = i6;
                        }
                    } else if (isSpecial(c3, this.features)) {
                        i5 = i7 + 1;
                        if (c3 >= IOUtils.specicalFlags_doubleQuotes.length || IOUtils.specicalFlags_doubleQuotes[c3] != (byte) 4) {
                            i8 = i9;
                        } else {
                            i8 = i9 + 4;
                        }
                        if (i == -1) {
                            c4 = c3;
                            i7 = i8;
                            i8 = i5;
                            i4 = length;
                            i5 = length;
                        }
                        i7 = i8;
                        i8 = i5;
                        i5 = length;
                        i6 = i;
                        c4 = c3;
                        i4 = i6;
                    }
                    i4 = i;
                    c4 = c2;
                    i5 = i8;
                    i8 = i7;
                    i7 = i9;
                }
                length++;
                i9 = i7;
                i7 = i8;
                i8 = i5;
                c2 = c4;
                i = i4;
            }
            if (i7 > 0) {
                length = i9 + i7;
                if (length > this.buf.length) {
                    expandCapacity(length);
                }
                this.count = length;
                if (i7 == 1) {
                    if (c2 == ' ') {
                        System.arraycopy(this.buf, i8 + 1, this.buf, i8 + 6, (i3 - i8) - 1);
                        this.buf[i8] = '\\';
                        length = i8 + 1;
                        this.buf[length] = 'u';
                        length++;
                        this.buf[length] = '2';
                        length++;
                        this.buf[length] = '0';
                        length++;
                        this.buf[length] = '2';
                        this.buf[length + 1] = '8';
                    } else if (c2 >= IOUtils.specicalFlags_doubleQuotes.length || IOUtils.specicalFlags_doubleQuotes[c2] != (byte) 4) {
                        System.arraycopy(this.buf, i8 + 1, this.buf, i8 + 2, (i3 - i8) - 1);
                        this.buf[i8] = '\\';
                        this.buf[i8 + 1] = IOUtils.replaceChars[c2];
                    } else {
                        System.arraycopy(this.buf, i8 + 1, this.buf, i8 + 6, (i3 - i8) - 1);
                        length = i8 + 1;
                        this.buf[i8] = '\\';
                        i4 = length + 1;
                        this.buf[length] = 'u';
                        length = i4 + 1;
                        this.buf[i4] = IOUtils.DIGITS[(c2 >>> 12) & 15];
                        i4 = length + 1;
                        this.buf[length] = IOUtils.DIGITS[(c2 >>> 8) & 15];
                        length = i4 + 1;
                        this.buf[i4] = IOUtils.DIGITS[(c2 >>> 4) & 15];
                        i4 = length + 1;
                        this.buf[length] = IOUtils.DIGITS[c2 & 15];
                    }
                } else if (i7 > 1) {
                    i4 = i3;
                    i6 = i;
                    length = i6;
                    for (i -= i2; i < str.length(); i++) {
                        char charAt = str.charAt(i);
                        if ((charAt < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[charAt] != (byte) 0) || (charAt == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                            i7 = length + 1;
                            this.buf[length] = '\\';
                            if (IOUtils.specicalFlags_doubleQuotes[charAt] == (byte) 4) {
                                i5 = i7 + 1;
                                this.buf[i7] = 'u';
                                i7 = i5 + 1;
                                this.buf[i5] = IOUtils.DIGITS[(charAt >>> 12) & 15];
                                i5 = i7 + 1;
                                this.buf[i7] = IOUtils.DIGITS[(charAt >>> 8) & 15];
                                i7 = i5 + 1;
                                this.buf[i5] = IOUtils.DIGITS[(charAt >>> 4) & 15];
                                length = i7 + 1;
                                this.buf[i7] = IOUtils.DIGITS[charAt & 15];
                                i4 += 5;
                            } else {
                                length = i7 + 1;
                                this.buf[i7] = IOUtils.replaceChars[charAt];
                                i4++;
                            }
                        } else if (charAt == ' ') {
                            i7 = length + 1;
                            this.buf[length] = '\\';
                            i5 = i7 + 1;
                            this.buf[i7] = 'u';
                            i7 = i5 + 1;
                            this.buf[i5] = IOUtils.DIGITS[(charAt >>> 12) & 15];
                            i5 = i7 + 1;
                            this.buf[i7] = IOUtils.DIGITS[(charAt >>> 8) & 15];
                            i7 = i5 + 1;
                            this.buf[i5] = IOUtils.DIGITS[(charAt >>> 4) & 15];
                            length = i7 + 1;
                            this.buf[i7] = IOUtils.DIGITS[charAt & 15];
                            i4 += 5;
                        } else {
                            i5 = length + 1;
                            this.buf[length] = charAt;
                            length = i5;
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
    }

    public void writeStringWithDoubleQuoteDirect(String str, char c) {
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
                    if (length >= IOUtils.specicalFlags_doubleQuotes.length || IOUtils.specicalFlags_doubleQuotes[length] == (byte) 0) {
                        write(length);
                    } else {
                        write(92);
                        if (IOUtils.specicalFlags_doubleQuotes[length] == (byte) 4) {
                            write(117);
                            write(IOUtils.DIGITS[(length >>> 12) & 15]);
                            write(IOUtils.DIGITS[(length >>> 8) & 15]);
                            write(IOUtils.DIGITS[(length >>> 4) & 15]);
                            write(IOUtils.DIGITS[length & 15]);
                        } else {
                            write(IOUtils.replaceChars[length]);
                        }
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
        length = i2;
        int i6 = i;
        i = -1;
        while (length < i3) {
            int i7;
            char c3;
            int i8;
            int i9;
            char c4 = this.buf[length];
            if (c4 < ']') {
                Object obj = (c4 <= '\u001f' || c4 == '\\' || c4 == '\"') ? 1 : null;
                if (obj != null) {
                    i7 = i4 + 1;
                    if (c4 >= IOUtils.specicalFlags_doubleQuotes.length || IOUtils.specicalFlags_doubleQuotes[c4] != (byte) 4) {
                        i5 = i6;
                    } else {
                        i5 = i6 + 4;
                    }
                    if (i == -1) {
                        c3 = c4;
                        i4 = i5;
                        i5 = i7;
                        i8 = length;
                        i7 = length;
                    }
                    i4 = i5;
                    i5 = i7;
                    i7 = length;
                    i9 = i;
                    c3 = c4;
                    i8 = i9;
                }
                i8 = i;
                c3 = c2;
                i7 = i5;
                i5 = i4;
                i4 = i6;
            } else if (c4 == ' ') {
                i7 = i4 + 1;
                i5 = i6 + 4;
                if (i == -1) {
                    c3 = c4;
                    i4 = i5;
                    i5 = i7;
                    i8 = length;
                    i7 = length;
                }
                i4 = i5;
                i5 = i7;
                i7 = length;
                i9 = i;
                c3 = c4;
                i8 = i9;
            } else {
                if (c4 >= '' && c4 <= ' ') {
                    if (i == -1) {
                        i = length;
                    }
                    i7 = i4 + 1;
                    i4 = i6 + 4;
                    i5 = i7;
                    i7 = length;
                    i9 = i;
                    c3 = c4;
                    i8 = i9;
                }
                i8 = i;
                c3 = c2;
                i7 = i5;
                i5 = i4;
                i4 = i6;
            }
            length++;
            i6 = i4;
            i4 = i5;
            i5 = i7;
            c2 = c3;
            i = i8;
        }
        if (i4 > 0) {
            length = i6 + i4;
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
                } else if (c2 >= IOUtils.specicalFlags_doubleQuotes.length || IOUtils.specicalFlags_doubleQuotes[c2] != (byte) 4) {
                    System.arraycopy(this.buf, i5 + 1, this.buf, i5 + 2, (i3 - i5) - 1);
                    this.buf[i5] = '\\';
                    this.buf[i5 + 1] = IOUtils.replaceChars[c2];
                } else {
                    System.arraycopy(this.buf, i5 + 1, this.buf, i5 + 6, (i3 - i5) - 1);
                    length = i5 + 1;
                    this.buf[i5] = '\\';
                    i8 = length + 1;
                    this.buf[length] = 'u';
                    length = i8 + 1;
                    this.buf[i8] = IOUtils.DIGITS[(c2 >>> 12) & 15];
                    i8 = length + 1;
                    this.buf[length] = IOUtils.DIGITS[(c2 >>> 8) & 15];
                    length = i8 + 1;
                    this.buf[i8] = IOUtils.DIGITS[(c2 >>> 4) & 15];
                    i8 = length + 1;
                    this.buf[length] = IOUtils.DIGITS[c2 & 15];
                }
            } else if (i4 > 1) {
                i8 = i3;
                i9 = i;
                length = i9;
                for (i -= i2; i < str.length(); i++) {
                    char charAt = str.charAt(i);
                    if ((charAt < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[charAt] != (byte) 0) || (charAt == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                        i4 = length + 1;
                        this.buf[length] = '\\';
                        if (IOUtils.specicalFlags_doubleQuotes[charAt] == (byte) 4) {
                            i7 = i4 + 1;
                            this.buf[i4] = 'u';
                            i4 = i7 + 1;
                            this.buf[i7] = IOUtils.DIGITS[(charAt >>> 12) & 15];
                            i7 = i4 + 1;
                            this.buf[i4] = IOUtils.DIGITS[(charAt >>> 8) & 15];
                            i4 = i7 + 1;
                            this.buf[i7] = IOUtils.DIGITS[(charAt >>> 4) & 15];
                            length = i4 + 1;
                            this.buf[i4] = IOUtils.DIGITS[charAt & 15];
                            i8 += 5;
                        } else {
                            length = i4 + 1;
                            this.buf[i4] = IOUtils.replaceChars[charAt];
                            i8++;
                        }
                    } else if (charAt == ' ') {
                        i4 = length + 1;
                        this.buf[length] = '\\';
                        i7 = i4 + 1;
                        this.buf[i4] = 'u';
                        i4 = i7 + 1;
                        this.buf[i7] = IOUtils.DIGITS[(charAt >>> 12) & 15];
                        i7 = i4 + 1;
                        this.buf[i4] = IOUtils.DIGITS[(charAt >>> 8) & 15];
                        i4 = i7 + 1;
                        this.buf[i7] = IOUtils.DIGITS[(charAt >>> 4) & 15];
                        length = i4 + 1;
                        this.buf[i4] = IOUtils.DIGITS[charAt & 15];
                        i8 += 5;
                    } else {
                        i7 = length + 1;
                        this.buf[length] = charAt;
                        length = i7;
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

    public void writeFieldNull(char c, String str) {
        write((int) c);
        writeFieldName(str);
        writeNull();
    }

    public void writeFieldEmptyList(char c, String str) {
        write((int) c);
        writeFieldName(str);
        write("[]");
    }

    public void writeFieldNullString(char c, String str) {
        write((int) c);
        writeFieldName(str);
        if (isEnabled(SerializerFeature.WriteNullStringAsEmpty)) {
            writeString("");
        } else {
            writeNull();
        }
    }

    public void writeFieldNullBoolean(char c, String str) {
        write((int) c);
        writeFieldName(str);
        if (isEnabled(SerializerFeature.WriteNullBooleanAsFalse)) {
            write(HttpState.PREEMPTIVE_DEFAULT);
        } else {
            writeNull();
        }
    }

    public void writeFieldNullList(char c, String str) {
        write((int) c);
        writeFieldName(str);
        if (isEnabled(SerializerFeature.WriteNullListAsEmpty)) {
            write("[]");
        } else {
            writeNull();
        }
    }

    public void writeFieldNullNumber(char c, String str) {
        write((int) c);
        writeFieldName(str);
        if (isEnabled(SerializerFeature.WriteNullNumberAsZero)) {
            write(48);
        } else {
            writeNull();
        }
    }

    public void writeFieldValue(char c, String str, char c2) {
        write((int) c);
        writeFieldName(str);
        if (c2 == '\u0000') {
            writeString("\u0000");
        } else {
            writeString(Character.toString(c2));
        }
    }

    public void writeFieldValue(char c, String str, boolean z) {
        int i = z ? 4 : 5;
        int length = str.length();
        i += (this.count + length) + 4;
        if (i > this.buf.length) {
            if (this.writer != null) {
                write((int) c);
                writeString(str);
                write(58);
                write(z);
                return;
            }
            expandCapacity(i);
        }
        int i2 = this.count;
        this.count = i;
        this.buf[i2] = c;
        i = (i2 + length) + 1;
        this.buf[i2 + 1] = this.keySeperator;
        str.getChars(0, length, this.buf, i2 + 2);
        this.buf[i + 1] = this.keySeperator;
        if (z) {
            System.arraycopy(":true".toCharArray(), 0, this.buf, i + 2, 5);
        } else {
            System.arraycopy(":false".toCharArray(), 0, this.buf, i + 2, 6);
        }
    }

    public void write(boolean z) {
        if (z) {
            write(Constants.SERVICE_SCOPE_FLAG_VALUE);
        } else {
            write(HttpState.PREEMPTIVE_DEFAULT);
        }
    }

    public void writeFieldValue(char c, String str, int i) {
        if (i == Integer.MIN_VALUE || !this.quoteFieldNames) {
            writeFieldValue1(c, str, i);
            return;
        }
        int stringSize = i < 0 ? IOUtils.stringSize(-i) + 1 : IOUtils.stringSize(i);
        int length = str.length();
        stringSize += (this.count + length) + 4;
        if (stringSize > this.buf.length) {
            if (this.writer != null) {
                writeFieldValue1(c, str, i);
                return;
            }
            expandCapacity(stringSize);
        }
        int i2 = this.count;
        this.count = stringSize;
        this.buf[i2] = c;
        stringSize = (i2 + length) + 1;
        this.buf[i2 + 1] = this.keySeperator;
        str.getChars(0, length, this.buf, i2 + 2);
        this.buf[stringSize + 1] = this.keySeperator;
        this.buf[stringSize + 2] = ':';
        IOUtils.getChars(i, this.count, this.buf);
    }

    public void writeFieldValue1(char c, String str, int i) {
        write((int) c);
        writeFieldName(str);
        writeInt(i);
    }

    public void writeFieldValue(char c, String str, long j) {
        if (j == Long.MIN_VALUE || !this.quoteFieldNames) {
            writeFieldValue1(c, str, j);
            return;
        }
        int stringSize = j < 0 ? IOUtils.stringSize(-j) + 1 : IOUtils.stringSize(j);
        int length = str.length();
        stringSize += (this.count + length) + 4;
        if (stringSize > this.buf.length) {
            if (this.writer != null) {
                write((int) c);
                writeFieldName(str);
                writeLong(j);
                return;
            }
            expandCapacity(stringSize);
        }
        int i = this.count;
        this.count = stringSize;
        this.buf[i] = c;
        stringSize = (i + length) + 1;
        this.buf[i + 1] = this.keySeperator;
        str.getChars(0, length, this.buf, i + 2);
        this.buf[stringSize + 1] = this.keySeperator;
        this.buf[stringSize + 2] = ':';
        IOUtils.getChars(j, this.count, this.buf);
    }

    public void writeFieldValue1(char c, String str, long j) {
        write((int) c);
        writeFieldName(str);
        writeLong(j);
    }

    public void writeFieldValue(char c, String str, float f) {
        write((int) c);
        writeFieldName(str);
        if (f == 0.0f) {
            write(48);
        } else if (Float.isNaN(f)) {
            writeNull();
        } else if (Float.isInfinite(f)) {
            writeNull();
        } else {
            String f2 = Float.toString(f);
            if (f2.endsWith(".0")) {
                f2 = f2.substring(0, f2.length() - 2);
            }
            write(f2);
        }
    }

    public void writeFieldValue(char c, String str, double d) {
        write((int) c);
        writeFieldName(str);
        if (d == 0.0d) {
            write(48);
        } else if (Double.isNaN(d)) {
            writeNull();
        } else if (Double.isInfinite(d)) {
            writeNull();
        } else {
            String d2 = Double.toString(d);
            if (d2.endsWith(".0")) {
                d2 = d2.substring(0, d2.length() - 2);
            }
            write(d2);
        }
    }

    public void writeFieldValue(char c, String str, String str2) {
        if (!this.quoteFieldNames) {
            write((int) c);
            writeFieldName(str);
            if (str2 == null) {
                writeNull();
            } else {
                writeString(str2);
            }
        } else if (this.useSingleQuotes) {
            write((int) c);
            writeFieldName(str);
            if (str2 == null) {
                writeNull();
            } else {
                writeString(str2);
            }
        } else if (this.browserSecure) {
            write((int) c);
            writeStringWithDoubleQuote(str, ':');
            writeStringWithDoubleQuote(str2, '\u0000');
        } else if (this.browserCompatible) {
            write((int) c);
            writeStringWithDoubleQuote(str, ':');
            writeStringWithDoubleQuote(str2, '\u0000');
        } else {
            writeFieldValueStringWithDoubleQuoteCheck(c, str, str2);
        }
    }

    public void writeFieldValueStringWithDoubleQuoteCheck(char c, String str, String str2) {
        int i;
        int length = str.length();
        int i2 = this.count;
        if (str2 == null) {
            i = 4;
            i2 += length + 8;
        } else {
            i = str2.length();
            i2 += (length + i) + 6;
        }
        if (i2 > this.buf.length) {
            if (this.writer != null) {
                write((int) c);
                writeStringWithDoubleQuote(str, ':');
                writeStringWithDoubleQuote(str2, '\u0000');
                return;
            }
            expandCapacity(i2);
        }
        this.buf[this.count] = c;
        int i3 = this.count + 2;
        int i4 = i3 + length;
        this.buf[this.count + 1] = '\"';
        str.getChars(0, length, this.buf, i3);
        this.count = i2;
        this.buf[i4] = '\"';
        length = i4 + 1;
        i4 = length + 1;
        this.buf[length] = ':';
        if (str2 == null) {
            i = i4 + 1;
            this.buf[i4] = 'n';
            length = i + 1;
            this.buf[i] = 'u';
            i = length + 1;
            this.buf[length] = 'l';
            length = i + 1;
            this.buf[i] = 'l';
            return;
        }
        int i5 = i4 + 1;
        this.buf[i4] = '\"';
        int i6 = i5 + i;
        str2.getChars(0, i, this.buf, i5);
        if (!this.disableCheckSpecialChar) {
            int i7;
            int i8 = 0;
            i4 = -1;
            char c2 = '\u0000';
            int i9 = i2;
            i2 = -1;
            i = i5;
            while (i < i6) {
                char c3;
                char c4 = this.buf[i];
                if (c4 >= ']') {
                    if (c4 >= '' && (c4 == ' ' || c4 <= ' ')) {
                        if (i2 == -1) {
                            i2 = i;
                        }
                        length = i8 + 1;
                        i8 = i9 + 4;
                        i4 = length;
                        length = i2;
                        c3 = c4;
                        i3 = i;
                    }
                    i3 = i4;
                    i4 = i8;
                    i8 = i9;
                    i7 = i2;
                    c3 = c2;
                    length = i7;
                } else {
                    if (isSpecial(c4, this.features)) {
                        length = i8 + 1;
                        if (c4 >= IOUtils.specicalFlags_doubleQuotes.length || IOUtils.specicalFlags_doubleQuotes[c4] != (byte) 4) {
                            i4 = i9;
                        } else {
                            i4 = i9 + 4;
                        }
                        if (i2 == -1) {
                            c3 = c4;
                            i8 = i4;
                            i3 = i;
                            i4 = length;
                            length = i;
                        } else {
                            i8 = i4;
                            i4 = length;
                            length = i2;
                            c3 = c4;
                            i3 = i;
                        }
                    }
                    i3 = i4;
                    i4 = i8;
                    i8 = i9;
                    i7 = i2;
                    c3 = c2;
                    length = i7;
                }
                i++;
                i9 = i8;
                i8 = i4;
                i4 = i3;
                i7 = length;
                c2 = c3;
                i2 = i7;
            }
            if (i8 > 0) {
                i = i9 + i8;
                if (i > this.buf.length) {
                    expandCapacity(i);
                }
                this.count = i;
                if (i8 == 1) {
                    if (c2 == ' ') {
                        System.arraycopy(this.buf, i4 + 1, this.buf, i4 + 6, (i6 - i4) - 1);
                        this.buf[i4] = '\\';
                        i = i4 + 1;
                        this.buf[i] = 'u';
                        i++;
                        this.buf[i] = '2';
                        i++;
                        this.buf[i] = '0';
                        i++;
                        this.buf[i] = '2';
                        this.buf[i + 1] = '8';
                    } else if (c2 >= IOUtils.specicalFlags_doubleQuotes.length || IOUtils.specicalFlags_doubleQuotes[c2] != (byte) 4) {
                        System.arraycopy(this.buf, i4 + 1, this.buf, i4 + 2, (i6 - i4) - 1);
                        this.buf[i4] = '\\';
                        this.buf[i4 + 1] = IOUtils.replaceChars[c2];
                    } else {
                        System.arraycopy(this.buf, i4 + 1, this.buf, i4 + 6, (i6 - i4) - 1);
                        i = i4 + 1;
                        this.buf[i4] = '\\';
                        i3 = i + 1;
                        this.buf[i] = 'u';
                        i = i3 + 1;
                        this.buf[i3] = IOUtils.DIGITS[(c2 >>> 12) & 15];
                        i3 = i + 1;
                        this.buf[i] = IOUtils.DIGITS[(c2 >>> 8) & 15];
                        i = i3 + 1;
                        this.buf[i3] = IOUtils.DIGITS[(c2 >>> 4) & 15];
                        i3 = i + 1;
                        this.buf[i] = IOUtils.DIGITS[c2 & 15];
                    }
                } else if (i8 > 1) {
                    length = i6;
                    i7 = i2;
                    i = i7;
                    for (i2 -= i5; i2 < str2.length(); i2++) {
                        char charAt = str2.charAt(i2);
                        if ((charAt < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[charAt] != (byte) 0) || (charAt == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                            i8 = i + 1;
                            this.buf[i] = '\\';
                            if (IOUtils.specicalFlags_doubleQuotes[charAt] == (byte) 4) {
                                i3 = i8 + 1;
                                this.buf[i8] = 'u';
                                i8 = i3 + 1;
                                this.buf[i3] = IOUtils.DIGITS[(charAt >>> 12) & 15];
                                i3 = i8 + 1;
                                this.buf[i8] = IOUtils.DIGITS[(charAt >>> 8) & 15];
                                i8 = i3 + 1;
                                this.buf[i3] = IOUtils.DIGITS[(charAt >>> 4) & 15];
                                i = i8 + 1;
                                this.buf[i8] = IOUtils.DIGITS[charAt & 15];
                                length += 5;
                            } else {
                                i = i8 + 1;
                                this.buf[i8] = IOUtils.replaceChars[charAt];
                                length++;
                            }
                        } else if (charAt == ' ') {
                            i8 = i + 1;
                            this.buf[i] = '\\';
                            i3 = i8 + 1;
                            this.buf[i8] = 'u';
                            i8 = i3 + 1;
                            this.buf[i3] = IOUtils.DIGITS[(charAt >>> 12) & 15];
                            i3 = i8 + 1;
                            this.buf[i8] = IOUtils.DIGITS[(charAt >>> 8) & 15];
                            i8 = i3 + 1;
                            this.buf[i3] = IOUtils.DIGITS[(charAt >>> 4) & 15];
                            i = i8 + 1;
                            this.buf[i8] = IOUtils.DIGITS[charAt & 15];
                            length += 5;
                        } else {
                            i3 = i + 1;
                            this.buf[i] = charAt;
                            i = i3;
                        }
                    }
                }
            }
        }
        this.buf[this.count - 1] = '\"';
    }

    public void writeFieldValueStringWithDoubleQuote(char c, String str, String str2) {
        int i;
        int length = str.length();
        int i2 = this.count;
        if (str2 == null) {
            i = 4;
            i2 += length + 8;
        } else {
            i = str2.length();
            i2 += (length + i) + 6;
        }
        if (i2 > this.buf.length) {
            if (this.writer != null) {
                write((int) c);
                writeStringWithDoubleQuote(str, ':');
                writeStringWithDoubleQuote(str2, '\u0000');
                return;
            }
            expandCapacity(i2);
        }
        this.buf[this.count] = c;
        int i3 = this.count + 2;
        int i4 = i3 + length;
        this.buf[this.count + 1] = '\"';
        str.getChars(0, length, this.buf, i3);
        this.count = i2;
        this.buf[i4] = '\"';
        i2 = i4 + 1;
        i3 = i2 + 1;
        this.buf[i2] = ':';
        if (str2 == null) {
            i = i3 + 1;
            this.buf[i3] = 'n';
            length = i + 1;
            this.buf[i] = 'u';
            i = length + 1;
            this.buf[length] = 'l';
            length = i + 1;
            this.buf[i] = 'l';
            return;
        }
        length = i3 + 1;
        this.buf[i3] = '\"';
        str2.getChars(0, i, this.buf, length);
        this.buf[this.count - 1] = '\"';
    }

    static boolean isSpecial(char c, int i) {
        boolean z = true;
        if (c == ' ') {
            return false;
        }
        if (c == '/') {
            if ((SerializerFeature.WriteSlashAsSpecial.mask & i) == 0) {
                z = false;
            }
            return z;
        } else if (c > '#' && c != '\\') {
            return false;
        } else {
            if (c <= '\u001f' || c == '\\' || c == '\"') {
                return true;
            }
            return false;
        }
    }

    public void writeFieldValue(char c, String str, Enum<?> enumR) {
        if (enumR == null) {
            write((int) c);
            writeFieldName(str);
            writeNull();
        } else if (this.writeEnumUsingName && !this.writeEnumUsingToString) {
            writeEnumFieldValue(c, str, enumR.name());
        } else if (this.writeEnumUsingToString) {
            writeEnumFieldValue(c, str, enumR.toString());
        } else {
            writeFieldValue(c, str, enumR.ordinal());
        }
    }

    private void writeEnumFieldValue(char c, String str, String str2) {
        if (this.useSingleQuotes) {
            writeFieldValue(c, str, str2);
        } else {
            writeFieldValueStringWithDoubleQuote(c, str, str2);
        }
    }

    public void writeFieldValue(char c, String str, BigDecimal bigDecimal) {
        write((int) c);
        writeFieldName(str);
        if (bigDecimal == null) {
            writeNull();
        } else {
            write(bigDecimal.toString());
        }
    }

    public void writeString(String str, char c) {
        if (this.useSingleQuotes) {
            writeStringWithSingleQuote(str);
            write((int) c);
            return;
        }
        writeStringWithDoubleQuote(str, c);
    }

    public void writeString(String str) {
        if (this.useSingleQuotes) {
            writeStringWithSingleQuote(str);
        } else {
            writeStringWithDoubleQuote(str, '\u0000');
        }
    }

    protected void writeStringWithSingleQuote(String str) {
        int i = 0;
        int i2;
        if (str == null) {
            i2 = this.count + 4;
            if (i2 > this.buf.length) {
                expandCapacity(i2);
            }
            "null".getChars(0, 4, this.buf, this.count);
            this.count = i2;
            return;
        }
        i2 = str.length();
        int i3 = (this.count + i2) + 2;
        if (i3 > this.buf.length) {
            if (this.writer != null) {
                write(39);
                while (i < str.length()) {
                    i2 = str.charAt(i);
                    if (i2 <= '\r' || i2 == '\\' || i2 == '\'' || (i2 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                        write(92);
                        write(IOUtils.replaceChars[i2]);
                    } else {
                        write(i2);
                    }
                    i++;
                }
                write(39);
                return;
            }
            expandCapacity(i3);
        }
        int i4 = this.count + 1;
        int i5 = i4 + i2;
        this.buf[this.count] = '\'';
        str.getChars(0, i2, this.buf, i4);
        this.count = i3;
        int i6 = -1;
        int i7 = i4;
        int i8 = 0;
        while (i7 < i5) {
            char c = this.buf[i7];
            if (c <= '\r' || c == '\\' || c == '\'' || (c == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                i6 = i8 + 1;
                i = c;
                i2 = i7;
            } else {
                i2 = i6;
                i6 = i8;
            }
            i7++;
            i8 = i6;
            i6 = i2;
        }
        i2 = i3 + i8;
        if (i2 > this.buf.length) {
            expandCapacity(i2);
        }
        this.count = i2;
        if (i8 == 1) {
            System.arraycopy(this.buf, i6 + 1, this.buf, i6 + 2, (i5 - i6) - 1);
            this.buf[i6] = '\\';
            this.buf[i6 + 1] = IOUtils.replaceChars[i];
        } else if (i8 > 1) {
            System.arraycopy(this.buf, i6 + 1, this.buf, i6 + 2, (i5 - i6) - 1);
            this.buf[i6] = '\\';
            i6++;
            this.buf[i6] = IOUtils.replaceChars[i];
            i = i5 + 1;
            for (i2 = i6 - 2; i2 >= i4; i2--) {
                char c2 = this.buf[i2];
                if (c2 <= '\r' || c2 == '\\' || c2 == '\'' || (c2 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                    System.arraycopy(this.buf, i2 + 1, this.buf, i2 + 2, (i - i2) - 1);
                    this.buf[i2] = '\\';
                    this.buf[i2 + 1] = IOUtils.replaceChars[c2];
                    i++;
                }
            }
        }
        this.buf[this.count - 1] = '\'';
    }

    public void writeFieldName(String str) {
        writeFieldName(str, false);
    }

    public void writeFieldName(String str, boolean z) {
        int i = 1;
        if (str == null) {
            write("null:");
        } else if (this.useSingleQuotes) {
            if (this.quoteFieldNames) {
                writeStringWithSingleQuote(str);
                write(58);
                return;
            }
            writeKeyWithSingleQuoteIfHasSpecial(str);
        } else if (this.quoteFieldNames) {
            writeStringWithDoubleQuote(str, ':');
        } else {
            int i2 = str.length() == 0 ? 1 : 0;
            for (int i3 = 0; i3 < str.length(); i3++) {
                if (isSpecial(str.charAt(i3), 0)) {
                    break;
                }
            }
            i = i2;
            if (i != 0) {
                writeStringWithDoubleQuote(str, ':');
                return;
            }
            write(str);
            write(58);
        }
    }

    private void writeKeyWithSingleQuoteIfHasSpecial(String str) {
        int i;
        Object obj;
        int i2;
        int charAt;
        byte[] bArr = IOUtils.specicalFlags_singleQuotes;
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
                    if (charAt2 < bArr.length && bArr[charAt2] != (byte) 0) {
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
                    if (charAt >= bArr.length || bArr[charAt] == (byte) 0) {
                        write(charAt);
                    } else {
                        write(92);
                        write(IOUtils.replaceChars[charAt]);
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
            if (c < bArr.length && bArr[c] != (byte) 0) {
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
                    this.buf[i2] = IOUtils.replaceChars[c];
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
                    this.buf[i2] = IOUtils.replaceChars[c];
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
}
