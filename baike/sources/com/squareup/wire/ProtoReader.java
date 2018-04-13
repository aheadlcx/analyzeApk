package com.squareup.wire;

import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import okio.BufferedSource;
import okio.ByteString;

public final class ProtoReader {
    private final BufferedSource a;
    private long b = 0;
    private long c = Long.MAX_VALUE;
    private int d;
    private int e = 2;
    private int f = -1;
    private long g = -1;
    private FieldEncoding h;

    public ProtoReader(BufferedSource bufferedSource) {
        this.a = bufferedSource;
    }

    public long beginMessage() throws IOException {
        if (this.e != 2) {
            throw new IllegalStateException("Unexpected call to beginMessage()");
        }
        int i = this.d + 1;
        this.d = i;
        if (i > 65) {
            throw new IOException("Wire recursion limit exceeded");
        }
        long j = this.g;
        this.g = -1;
        this.e = 6;
        return j;
    }

    public void endMessage(long j) throws IOException {
        if (this.e != 6) {
            throw new IllegalStateException("Unexpected call to endMessage()");
        }
        int i = this.d - 1;
        this.d = i;
        if (i < 0 || this.g != -1) {
            throw new IllegalStateException("No corresponding call to beginMessage()");
        } else if (this.b == this.c || this.d == 0) {
            this.c = j;
        } else {
            throw new IOException("Expected to end at " + this.c + " but was " + this.b);
        }
    }

    public int nextTag() throws IOException {
        if (this.e == 7) {
            this.e = 2;
            return this.f;
        } else if (this.e != 6) {
            throw new IllegalStateException("Unexpected call to nextTag()");
        } else {
            while (this.b < this.c && !this.a.exhausted()) {
                int a = a();
                if (a == 0) {
                    throw new ProtocolException("Unexpected tag 0");
                }
                this.f = a >> 3;
                a &= 7;
                switch (a) {
                    case 0:
                        this.h = FieldEncoding.VARINT;
                        this.e = 0;
                        return this.f;
                    case 1:
                        this.h = FieldEncoding.FIXED64;
                        this.e = 1;
                        return this.f;
                    case 2:
                        this.h = FieldEncoding.LENGTH_DELIMITED;
                        this.e = 2;
                        a = a();
                        if (a < 0) {
                            throw new ProtocolException("Negative length: " + a);
                        } else if (this.g != -1) {
                            throw new IllegalStateException();
                        } else {
                            this.g = this.c;
                            this.c = ((long) a) + this.b;
                            if (this.c <= this.g) {
                                return this.f;
                            }
                            throw new EOFException();
                        }
                    case 3:
                        a(this.f);
                    case 4:
                        throw new ProtocolException("Unexpected end group");
                    case 5:
                        this.h = FieldEncoding.FIXED32;
                        this.e = 5;
                        return this.f;
                    default:
                        throw new ProtocolException("Unexpected field encoding: " + a);
                }
            }
            return -1;
        }
    }

    public FieldEncoding peekFieldEncoding() {
        return this.h;
    }

    public void skip() throws IOException {
        switch (this.e) {
            case 0:
                readVarint64();
                return;
            case 1:
                readFixed64();
                return;
            case 2:
                this.a.skip(b());
                return;
            case 5:
                readFixed32();
                return;
            default:
                throw new IllegalStateException("Unexpected call to skip()");
        }
    }

    private void a(int i) throws IOException {
        while (this.b < this.c && !this.a.exhausted()) {
            int a = a();
            if (a == 0) {
                throw new ProtocolException("Unexpected tag 0");
            }
            int i2 = a >> 3;
            a &= 7;
            switch (a) {
                case 0:
                    this.e = 0;
                    readVarint64();
                    break;
                case 1:
                    this.e = 1;
                    readFixed64();
                    break;
                case 2:
                    a = a();
                    this.b += (long) a;
                    this.a.skip((long) a);
                    break;
                case 3:
                    a(i2);
                    break;
                case 4:
                    if (i2 != i) {
                        throw new ProtocolException("Unexpected end group");
                    }
                    return;
                case 5:
                    this.e = 5;
                    readFixed32();
                    break;
                default:
                    throw new ProtocolException("Unexpected field encoding: " + a);
            }
        }
        throw new EOFException();
    }

    public ByteString readBytes() throws IOException {
        long b = b();
        this.a.require(b);
        return this.a.readByteString(b);
    }

    public String readString() throws IOException {
        long b = b();
        this.a.require(b);
        return this.a.readUtf8(b);
    }

    public int readVarint32() throws IOException {
        if (this.e == 0 || this.e == 2) {
            int a = a();
            b(0);
            return a;
        }
        throw new ProtocolException("Expected VARINT or LENGTH_DELIMITED but was " + this.e);
    }

    private int a() throws IOException {
        this.a.require(1);
        this.b++;
        byte readByte = this.a.readByte();
        if (readByte >= (byte) 0) {
            return readByte;
        }
        int i = readByte & 127;
        this.a.require(1);
        this.b++;
        byte readByte2 = this.a.readByte();
        if (readByte2 >= (byte) 0) {
            return i | (readByte2 << 7);
        }
        i |= (readByte2 & 127) << 7;
        this.a.require(1);
        this.b++;
        readByte2 = this.a.readByte();
        if (readByte2 >= (byte) 0) {
            return i | (readByte2 << 14);
        }
        i |= (readByte2 & 127) << 14;
        this.a.require(1);
        this.b++;
        readByte2 = this.a.readByte();
        if (readByte2 >= (byte) 0) {
            return i | (readByte2 << 21);
        }
        i |= (readByte2 & 127) << 21;
        this.a.require(1);
        this.b++;
        readByte2 = this.a.readByte();
        i |= readByte2 << 28;
        if (readByte2 >= (byte) 0) {
            return i;
        }
        for (int i2 = 0; i2 < 5; i2++) {
            this.a.require(1);
            this.b++;
            if (this.a.readByte() >= (byte) 0) {
                return i;
            }
        }
        throw new ProtocolException("Malformed VARINT");
    }

    public long readVarint64() throws IOException {
        if (this.e == 0 || this.e == 2) {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                this.a.require(1);
                this.b++;
                byte readByte = this.a.readByte();
                j |= ((long) (readByte & 127)) << i;
                if ((readByte & 128) == 0) {
                    b(0);
                    return j;
                }
            }
            throw new ProtocolException("WireInput encountered a malformed varint");
        }
        throw new ProtocolException("Expected VARINT or LENGTH_DELIMITED but was " + this.e);
    }

    public int readFixed32() throws IOException {
        if (this.e == 5 || this.e == 2) {
            this.a.require(4);
            this.b += 4;
            int readIntLe = this.a.readIntLe();
            b(5);
            return readIntLe;
        }
        throw new ProtocolException("Expected FIXED32 or LENGTH_DELIMITED but was " + this.e);
    }

    public long readFixed64() throws IOException {
        if (this.e == 1 || this.e == 2) {
            this.a.require(8);
            this.b += 8;
            long readLongLe = this.a.readLongLe();
            b(1);
            return readLongLe;
        }
        throw new ProtocolException("Expected FIXED64 or LENGTH_DELIMITED but was " + this.e);
    }

    private void b(int i) throws IOException {
        if (this.e == i) {
            this.e = 6;
        } else if (this.b > this.c) {
            throw new IOException("Expected to end at " + this.c + " but was " + this.b);
        } else if (this.b == this.c) {
            this.c = this.g;
            this.g = -1;
            this.e = 6;
        } else {
            this.e = 7;
        }
    }

    private long b() throws IOException {
        if (this.e != 2) {
            throw new ProtocolException("Expected LENGTH_DELIMITED but was " + this.e);
        }
        long j = this.c - this.b;
        this.a.require(j);
        this.e = 6;
        this.b = this.c;
        this.c = this.g;
        this.g = -1;
        return j;
    }
}
