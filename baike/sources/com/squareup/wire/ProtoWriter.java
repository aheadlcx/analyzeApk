package com.squareup.wire;

import java.io.IOException;
import kotlin.jvm.internal.CharCompanionObject;
import okio.BufferedSink;
import okio.ByteString;

public final class ProtoWriter {
    private final BufferedSink a;

    private static int a(int i, FieldEncoding fieldEncoding) {
        return (i << 3) | fieldEncoding.a;
    }

    static int a(int i) {
        return c(a(i, FieldEncoding.VARINT));
    }

    static int a(String str) {
        int i = 0;
        int length = str.length();
        int i2 = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt < '') {
                i2++;
            } else if (charAt < 'ࠀ') {
                i2 += 2;
            } else if (charAt < '?' || charAt > '?') {
                i2 += 3;
            } else if (charAt > CharCompanionObject.MAX_HIGH_SURROGATE || i + 1 >= length || str.charAt(i + 1) < CharCompanionObject.MIN_LOW_SURROGATE || str.charAt(i + 1) > '?') {
                i2++;
            } else {
                i2 += 4;
                i++;
            }
            i++;
        }
        return i2;
    }

    static int b(int i) {
        if (i >= 0) {
            return c(i);
        }
        return 10;
    }

    static int c(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        if ((-268435456 & i) == 0) {
            return 4;
        }
        return 5;
    }

    static int a(long j) {
        if ((-128 & j) == 0) {
            return 1;
        }
        if ((-16384 & j) == 0) {
            return 2;
        }
        if ((-2097152 & j) == 0) {
            return 3;
        }
        if ((-268435456 & j) == 0) {
            return 4;
        }
        if ((-34359738368L & j) == 0) {
            return 5;
        }
        if ((-4398046511104L & j) == 0) {
            return 6;
        }
        if ((-562949953421312L & j) == 0) {
            return 7;
        }
        if ((-72057594037927936L & j) == 0) {
            return 8;
        }
        if ((Long.MIN_VALUE & j) == 0) {
            return 9;
        }
        return 10;
    }

    static int d(int i) {
        return (i << 1) ^ (i >> 31);
    }

    static int e(int i) {
        return (i >>> 1) ^ (-(i & 1));
    }

    static long b(long j) {
        return (j << 1) ^ (j >> 63);
    }

    static long c(long j) {
        return (j >>> 1) ^ (-(1 & j));
    }

    public ProtoWriter(BufferedSink bufferedSink) {
        this.a = bufferedSink;
    }

    public void writeBytes(ByteString byteString) throws IOException {
        this.a.write(byteString);
    }

    public void writeString(String str) throws IOException {
        this.a.writeUtf8(str);
    }

    public void writeTag(int i, FieldEncoding fieldEncoding) throws IOException {
        writeVarint32(a(i, fieldEncoding));
    }

    void f(int i) throws IOException {
        if (i >= 0) {
            writeVarint32(i);
        } else {
            writeVarint64((long) i);
        }
    }

    public void writeVarint32(int i) throws IOException {
        while ((i & -128) != 0) {
            this.a.writeByte((i & 127) | 128);
            i >>>= 7;
        }
        this.a.writeByte(i);
    }

    public void writeVarint64(long j) throws IOException {
        while ((-128 & j) != 0) {
            this.a.writeByte((((int) j) & 127) | 128);
            j >>>= 7;
        }
        this.a.writeByte((int) j);
    }

    public void writeFixed32(int i) throws IOException {
        this.a.writeIntLe(i);
    }

    public void writeFixed64(long j) throws IOException {
        this.a.writeLongLe(j);
    }
}
