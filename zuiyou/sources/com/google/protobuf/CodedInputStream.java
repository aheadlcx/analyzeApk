package com.google.protobuf;

import com.google.protobuf.MessageLite.Builder;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CodedInputStream {
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private static final int DEFAULT_RECURSION_LIMIT = 100;
    private static final int DEFAULT_SIZE_LIMIT = Integer.MAX_VALUE;
    private static volatile boolean proto3DiscardUnknownFieldsDefault = true;
    private boolean explicitDiscardUnknownFields;
    int recursionDepth;
    int recursionLimit;
    int sizeLimit;

    private static final class ArrayDecoder extends CodedInputStream {
        private final byte[] buffer;
        private int bufferSizeAfterLimit;
        private int currentLimit;
        private boolean enableAliasing;
        private final boolean immutable;
        private int lastTag;
        private int limit;
        private int pos;
        private int startPos;

        private ArrayDecoder(byte[] bArr, int i, int i2, boolean z) {
            super();
            this.currentLimit = Integer.MAX_VALUE;
            this.buffer = bArr;
            this.limit = i + i2;
            this.pos = i;
            this.startPos = this.pos;
            this.immutable = z;
        }

        public int readTag() throws IOException {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            this.lastTag = readRawVarint32();
            if (WireFormat.getTagFieldNumber(this.lastTag) != 0) {
                return this.lastTag;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        public void checkLastTagWas(int i) throws InvalidProtocolBufferException {
            if (this.lastTag != i) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        public int getLastTag() {
            return this.lastTag;
        }

        public boolean skipField(int i) throws IOException {
            switch (WireFormat.getTagWireType(i)) {
                case 0:
                    skipRawVarint();
                    return true;
                case 1:
                    skipRawBytes(8);
                    return true;
                case 2:
                    skipRawBytes(readRawVarint32());
                    return true;
                case 3:
                    skipMessage();
                    checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4));
                    return true;
                case 4:
                    return false;
                case 5:
                    skipRawBytes(4);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public boolean skipField(int i, CodedOutputStream codedOutputStream) throws IOException {
            long readInt64;
            int makeTag;
            switch (WireFormat.getTagWireType(i)) {
                case 0:
                    readInt64 = readInt64();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeUInt64NoTag(readInt64);
                    return true;
                case 1:
                    readInt64 = readRawLittleEndian64();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeFixed64NoTag(readInt64);
                    return true;
                case 2:
                    ByteString readBytes = readBytes();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeBytesNoTag(readBytes);
                    return true;
                case 3:
                    codedOutputStream.writeRawVarint32(i);
                    skipMessage(codedOutputStream);
                    makeTag = WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4);
                    checkLastTagWas(makeTag);
                    codedOutputStream.writeRawVarint32(makeTag);
                    return true;
                case 4:
                    return false;
                case 5:
                    makeTag = readRawLittleEndian32();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeFixed32NoTag(makeTag);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public void skipMessage() throws IOException {
            int readTag;
            do {
                readTag = readTag();
                if (readTag == 0) {
                    return;
                }
            } while (skipField(readTag));
        }

        public void skipMessage(CodedOutputStream codedOutputStream) throws IOException {
            int readTag;
            do {
                readTag = readTag();
                if (readTag == 0) {
                    return;
                }
            } while (skipField(readTag, codedOutputStream));
        }

        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        public long readUInt64() throws IOException {
            return readRawVarint64();
        }

        public long readInt64() throws IOException {
            return readRawVarint64();
        }

        public int readInt32() throws IOException {
            return readRawVarint32();
        }

        public long readFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public int readFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public boolean readBool() throws IOException {
            return readRawVarint64() != 0;
        }

        public String readString() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0 && readRawVarint32 <= this.limit - this.pos) {
                String str = new String(this.buffer, this.pos, readRawVarint32, Internal.UTF_8);
                this.pos = readRawVarint32 + this.pos;
                return str;
            } else if (readRawVarint32 == 0) {
                return "";
            } else {
                if (readRawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        public String readStringRequireUtf8() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 <= 0 || readRawVarint32 > this.limit - this.pos) {
                if (readRawVarint32 == 0) {
                    return "";
                }
                if (readRawVarint32 <= 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (Utf8.isValidUtf8(this.buffer, this.pos, this.pos + readRawVarint32)) {
                int i = this.pos;
                this.pos += readRawVarint32;
                return new String(this.buffer, i, readRawVarint32, Internal.UTF_8);
            } else {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
        }

        public void readGroup(int i, Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            if (this.recursionDepth >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            this.recursionDepth++;
            builder.mergeFrom(this, extensionRegistryLite);
            checkLastTagWas(WireFormat.makeTag(i, 4));
            this.recursionDepth--;
        }

        public <T extends MessageLite> T readGroup(int i, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            if (this.recursionDepth >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            this.recursionDepth++;
            MessageLite messageLite = (MessageLite) parser.parsePartialFrom(this, extensionRegistryLite);
            checkLastTagWas(WireFormat.makeTag(i, 4));
            this.recursionDepth--;
            return messageLite;
        }

        @Deprecated
        public void readUnknownGroup(int i, Builder builder) throws IOException {
            readGroup(i, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        public void readMessage(Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (this.recursionDepth >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            readRawVarint32 = pushLimit(readRawVarint32);
            this.recursionDepth++;
            builder.mergeFrom(this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            popLimit(readRawVarint32);
        }

        public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (this.recursionDepth >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            int pushLimit = pushLimit(readRawVarint32);
            this.recursionDepth++;
            MessageLite messageLite = (MessageLite) parser.parsePartialFrom(this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            popLimit(pushLimit);
            return messageLite;
        }

        public ByteString readBytes() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0 && readRawVarint32 <= this.limit - this.pos) {
                ByteString wrap;
                if (this.immutable && this.enableAliasing) {
                    wrap = ByteString.wrap(this.buffer, this.pos, readRawVarint32);
                } else {
                    wrap = ByteString.copyFrom(this.buffer, this.pos, readRawVarint32);
                }
                this.pos = readRawVarint32 + this.pos;
                return wrap;
            } else if (readRawVarint32 == 0) {
                return ByteString.EMPTY;
            } else {
                return ByteString.wrap(readRawBytes(readRawVarint32));
            }
        }

        public byte[] readByteArray() throws IOException {
            return readRawBytes(readRawVarint32());
        }

        public ByteBuffer readByteBuffer() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0 && readRawVarint32 <= this.limit - this.pos) {
                ByteBuffer wrap;
                if (this.immutable || !this.enableAliasing) {
                    wrap = ByteBuffer.wrap(Arrays.copyOfRange(this.buffer, this.pos, this.pos + readRawVarint32));
                } else {
                    wrap = ByteBuffer.wrap(this.buffer, this.pos, readRawVarint32).slice();
                }
                this.pos = readRawVarint32 + this.pos;
                return wrap;
            } else if (readRawVarint32 == 0) {
                return Internal.EMPTY_BYTE_BUFFER;
            } else {
                if (readRawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        public int readUInt32() throws IOException {
            return readRawVarint32();
        }

        public int readEnum() throws IOException {
            return readRawVarint32();
        }

        public int readSFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public long readSFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public int readSInt32() throws IOException {
            return CodedInputStream.decodeZigZag32(readRawVarint32());
        }

        public long readSInt64() throws IOException {
            return CodedInputStream.decodeZigZag64(readRawVarint64());
        }

        public int readRawVarint32() throws IOException {
            int i = this.pos;
            if (this.limit != i) {
                byte[] bArr = this.buffer;
                int i2 = i + 1;
                i = bArr[i];
                if (i >= 0) {
                    this.pos = i2;
                    return i;
                } else if (this.limit - i2 >= 9) {
                    int i3 = i2 + 1;
                    i ^= bArr[i2] << 7;
                    if (i < 0) {
                        i ^= -128;
                    } else {
                        i2 = i3 + 1;
                        i ^= bArr[i3] << 14;
                        if (i >= 0) {
                            i ^= 16256;
                            i3 = i2;
                        } else {
                            i3 = i2 + 1;
                            i ^= bArr[i2] << 21;
                            if (i < 0) {
                                i ^= -2080896;
                            } else {
                                i2 = i3 + 1;
                                byte b = bArr[i3];
                                i = (i ^ (b << 28)) ^ 266354560;
                                if (b < (byte) 0) {
                                    i3 = i2 + 1;
                                    if (bArr[i2] < (byte) 0) {
                                        i2 = i3 + 1;
                                        if (bArr[i3] < (byte) 0) {
                                            i3 = i2 + 1;
                                            if (bArr[i2] < (byte) 0) {
                                                i2 = i3 + 1;
                                                if (bArr[i3] < (byte) 0) {
                                                    i3 = i2 + 1;
                                                    if (bArr[i2] < (byte) 0) {
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                i3 = i2;
                            }
                        }
                    }
                    this.pos = i3;
                    return i;
                }
            }
            return (int) readRawVarint64SlowPath();
        }

        private void skipRawVarint() throws IOException {
            if (this.limit - this.pos >= 10) {
                skipRawVarintFastPath();
            } else {
                skipRawVarintSlowPath();
            }
        }

        private void skipRawVarintFastPath() throws IOException {
            int i = 0;
            while (i < 10) {
                byte[] bArr = this.buffer;
                int i2 = this.pos;
                this.pos = i2 + 1;
                if (bArr[i2] < (byte) 0) {
                    i++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipRawVarintSlowPath() throws IOException {
            int i = 0;
            while (i < 10) {
                if (readRawByte() < (byte) 0) {
                    i++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public long readRawVarint64() throws IOException {
            int i = this.pos;
            if (this.limit != i) {
                byte[] bArr = this.buffer;
                int i2 = i + 1;
                byte b = bArr[i];
                if (b >= (byte) 0) {
                    this.pos = i2;
                    return (long) b;
                } else if (this.limit - i2 >= 9) {
                    long j;
                    int i3 = i2 + 1;
                    i = b ^ (bArr[i2] << 7);
                    if (i < 0) {
                        j = (long) (i ^ -128);
                    } else {
                        int i4 = i3 + 1;
                        i ^= bArr[i3] << 14;
                        if (i >= 0) {
                            j = (long) (i ^ 16256);
                            i3 = i4;
                        } else {
                            i3 = i4 + 1;
                            i ^= bArr[i4] << 21;
                            if (i < 0) {
                                j = (long) (i ^ -2080896);
                            } else {
                                i4 = i3 + 1;
                                j = ((long) i) ^ (((long) bArr[i3]) << 28);
                                if (j >= 0) {
                                    j ^= 266354560;
                                    i3 = i4;
                                } else {
                                    i3 = i4 + 1;
                                    j ^= ((long) bArr[i4]) << 35;
                                    if (j < 0) {
                                        j ^= -34093383808L;
                                    } else {
                                        i4 = i3 + 1;
                                        j ^= ((long) bArr[i3]) << 42;
                                        if (j >= 0) {
                                            j ^= 4363953127296L;
                                            i3 = i4;
                                        } else {
                                            i3 = i4 + 1;
                                            j ^= ((long) bArr[i4]) << 49;
                                            if (j < 0) {
                                                j ^= -558586000294016L;
                                            } else {
                                                i4 = i3 + 1;
                                                j = (j ^ (((long) bArr[i3]) << 56)) ^ 71499008037633920L;
                                                if (j < 0) {
                                                    i3 = i4 + 1;
                                                    if (((long) bArr[i4]) < 0) {
                                                    }
                                                } else {
                                                    i3 = i4;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    this.pos = i3;
                    return j;
                }
            }
            return readRawVarint64SlowPath();
        }

        long readRawVarint64SlowPath() throws IOException {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                byte readRawByte = readRawByte();
                j |= ((long) (readRawByte & 127)) << i;
                if ((readRawByte & 128) == 0) {
                    return j;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public int readRawLittleEndian32() throws IOException {
            int i = this.pos;
            if (this.limit - i < 4) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] bArr = this.buffer;
            this.pos = i + 4;
            return ((bArr[i + 3] & 255) << 24) | (((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16));
        }

        public long readRawLittleEndian64() throws IOException {
            int i = this.pos;
            if (this.limit - i < 8) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] bArr = this.buffer;
            this.pos = i + 8;
            return ((((long) bArr[i + 7]) & 255) << 56) | (((((((((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8)) | ((((long) bArr[i + 2]) & 255) << 16)) | ((((long) bArr[i + 3]) & 255) << 24)) | ((((long) bArr[i + 4]) & 255) << 32)) | ((((long) bArr[i + 5]) & 255) << 40)) | ((((long) bArr[i + 6]) & 255) << 48));
        }

        public void enableAliasing(boolean z) {
            this.enableAliasing = z;
        }

        public void resetSizeCounter() {
            this.startPos = this.pos;
        }

        public int pushLimit(int i) throws InvalidProtocolBufferException {
            if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int totalBytesRead = getTotalBytesRead() + i;
            int i2 = this.currentLimit;
            if (totalBytesRead > i2) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.currentLimit = totalBytesRead;
            recomputeBufferSizeAfterLimit();
            return i2;
        }

        private void recomputeBufferSizeAfterLimit() {
            this.limit += this.bufferSizeAfterLimit;
            int i = this.limit - this.startPos;
            if (i > this.currentLimit) {
                this.bufferSizeAfterLimit = i - this.currentLimit;
                this.limit -= this.bufferSizeAfterLimit;
                return;
            }
            this.bufferSizeAfterLimit = 0;
        }

        public void popLimit(int i) {
            this.currentLimit = i;
            recomputeBufferSizeAfterLimit();
        }

        public int getBytesUntilLimit() {
            if (this.currentLimit == Integer.MAX_VALUE) {
                return -1;
            }
            return this.currentLimit - getTotalBytesRead();
        }

        public boolean isAtEnd() throws IOException {
            return this.pos == this.limit;
        }

        public int getTotalBytesRead() {
            return this.pos - this.startPos;
        }

        public byte readRawByte() throws IOException {
            if (this.pos == this.limit) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] bArr = this.buffer;
            int i = this.pos;
            this.pos = i + 1;
            return bArr[i];
        }

        public byte[] readRawBytes(int i) throws IOException {
            if (i > 0 && i <= this.limit - this.pos) {
                int i2 = this.pos;
                this.pos += i;
                return Arrays.copyOfRange(this.buffer, i2, this.pos);
            } else if (i > 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (i == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            } else {
                throw InvalidProtocolBufferException.negativeSize();
            }
        }

        public void skipRawBytes(int i) throws IOException {
            if (i >= 0 && i <= this.limit - this.pos) {
                this.pos += i;
            } else if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            } else {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }
    }

    private static final class StreamDecoder extends CodedInputStream {
        private final byte[] buffer;
        private int bufferSize;
        private int bufferSizeAfterLimit;
        private int currentLimit;
        private final InputStream input;
        private int lastTag;
        private int pos;
        private CodedInputStream$StreamDecoder$RefillCallback refillCallback;
        private int totalBytesRetired;

        private StreamDecoder(InputStream inputStream, int i) {
            super();
            this.currentLimit = Integer.MAX_VALUE;
            this.refillCallback = null;
            Internal.checkNotNull(inputStream, "input");
            this.input = inputStream;
            this.buffer = new byte[i];
            this.bufferSize = 0;
            this.pos = 0;
            this.totalBytesRetired = 0;
        }

        public int readTag() throws IOException {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            this.lastTag = readRawVarint32();
            if (WireFormat.getTagFieldNumber(this.lastTag) != 0) {
                return this.lastTag;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        public void checkLastTagWas(int i) throws InvalidProtocolBufferException {
            if (this.lastTag != i) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        public int getLastTag() {
            return this.lastTag;
        }

        public boolean skipField(int i) throws IOException {
            switch (WireFormat.getTagWireType(i)) {
                case 0:
                    skipRawVarint();
                    return true;
                case 1:
                    skipRawBytes(8);
                    return true;
                case 2:
                    skipRawBytes(readRawVarint32());
                    return true;
                case 3:
                    skipMessage();
                    checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4));
                    return true;
                case 4:
                    return false;
                case 5:
                    skipRawBytes(4);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public boolean skipField(int i, CodedOutputStream codedOutputStream) throws IOException {
            long readInt64;
            int makeTag;
            switch (WireFormat.getTagWireType(i)) {
                case 0:
                    readInt64 = readInt64();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeUInt64NoTag(readInt64);
                    return true;
                case 1:
                    readInt64 = readRawLittleEndian64();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeFixed64NoTag(readInt64);
                    return true;
                case 2:
                    ByteString readBytes = readBytes();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeBytesNoTag(readBytes);
                    return true;
                case 3:
                    codedOutputStream.writeRawVarint32(i);
                    skipMessage(codedOutputStream);
                    makeTag = WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4);
                    checkLastTagWas(makeTag);
                    codedOutputStream.writeRawVarint32(makeTag);
                    return true;
                case 4:
                    return false;
                case 5:
                    makeTag = readRawLittleEndian32();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeFixed32NoTag(makeTag);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public void skipMessage() throws IOException {
            int readTag;
            do {
                readTag = readTag();
                if (readTag == 0) {
                    return;
                }
            } while (skipField(readTag));
        }

        public void skipMessage(CodedOutputStream codedOutputStream) throws IOException {
            int readTag;
            do {
                readTag = readTag();
                if (readTag == 0) {
                    return;
                }
            } while (skipField(readTag, codedOutputStream));
        }

        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        public long readUInt64() throws IOException {
            return readRawVarint64();
        }

        public long readInt64() throws IOException {
            return readRawVarint64();
        }

        public int readInt32() throws IOException {
            return readRawVarint32();
        }

        public long readFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public int readFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public boolean readBool() throws IOException {
            return readRawVarint64() != 0;
        }

        public String readString() throws IOException {
            int readRawVarint32 = readRawVarint32();
            String str;
            if (readRawVarint32 > 0 && readRawVarint32 <= this.bufferSize - this.pos) {
                str = new String(this.buffer, this.pos, readRawVarint32, Internal.UTF_8);
                this.pos = readRawVarint32 + this.pos;
                return str;
            } else if (readRawVarint32 == 0) {
                return "";
            } else {
                if (readRawVarint32 > this.bufferSize) {
                    return new String(readRawBytesSlowPath(readRawVarint32), Internal.UTF_8);
                }
                refillBuffer(readRawVarint32);
                str = new String(this.buffer, this.pos, readRawVarint32, Internal.UTF_8);
                this.pos = readRawVarint32 + this.pos;
                return str;
            }
        }

        public String readStringRequireUtf8() throws IOException {
            byte[] bArr;
            int readRawVarint32 = readRawVarint32();
            int i = this.pos;
            if (readRawVarint32 <= this.bufferSize - i && readRawVarint32 > 0) {
                bArr = this.buffer;
                this.pos = i + readRawVarint32;
            } else if (readRawVarint32 == 0) {
                return "";
            } else {
                if (readRawVarint32 <= this.bufferSize) {
                    refillBuffer(readRawVarint32);
                    byte[] bArr2 = this.buffer;
                    this.pos = 0 + readRawVarint32;
                    bArr = bArr2;
                    i = 0;
                } else {
                    bArr = readRawBytesSlowPath(readRawVarint32);
                    i = 0;
                }
            }
            if (Utf8.isValidUtf8(bArr, i, i + readRawVarint32)) {
                return new String(bArr, i, readRawVarint32, Internal.UTF_8);
            }
            throw InvalidProtocolBufferException.invalidUtf8();
        }

        public void readGroup(int i, Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            if (this.recursionDepth >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            this.recursionDepth++;
            builder.mergeFrom(this, extensionRegistryLite);
            checkLastTagWas(WireFormat.makeTag(i, 4));
            this.recursionDepth--;
        }

        public <T extends MessageLite> T readGroup(int i, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            if (this.recursionDepth >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            this.recursionDepth++;
            MessageLite messageLite = (MessageLite) parser.parsePartialFrom(this, extensionRegistryLite);
            checkLastTagWas(WireFormat.makeTag(i, 4));
            this.recursionDepth--;
            return messageLite;
        }

        @Deprecated
        public void readUnknownGroup(int i, Builder builder) throws IOException {
            readGroup(i, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        public void readMessage(Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (this.recursionDepth >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            readRawVarint32 = pushLimit(readRawVarint32);
            this.recursionDepth++;
            builder.mergeFrom(this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            popLimit(readRawVarint32);
        }

        public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (this.recursionDepth >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            int pushLimit = pushLimit(readRawVarint32);
            this.recursionDepth++;
            MessageLite messageLite = (MessageLite) parser.parsePartialFrom(this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            popLimit(pushLimit);
            return messageLite;
        }

        public ByteString readBytes() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 <= this.bufferSize - this.pos && readRawVarint32 > 0) {
                ByteString copyFrom = ByteString.copyFrom(this.buffer, this.pos, readRawVarint32);
                this.pos = readRawVarint32 + this.pos;
                return copyFrom;
            } else if (readRawVarint32 == 0) {
                return ByteString.EMPTY;
            } else {
                return readBytesSlowPath(readRawVarint32);
            }
        }

        public byte[] readByteArray() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > this.bufferSize - this.pos || readRawVarint32 <= 0) {
                return readRawBytesSlowPath(readRawVarint32);
            }
            byte[] copyOfRange = Arrays.copyOfRange(this.buffer, this.pos, this.pos + readRawVarint32);
            this.pos = readRawVarint32 + this.pos;
            return copyOfRange;
        }

        public ByteBuffer readByteBuffer() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 <= this.bufferSize - this.pos && readRawVarint32 > 0) {
                ByteBuffer wrap = ByteBuffer.wrap(Arrays.copyOfRange(this.buffer, this.pos, this.pos + readRawVarint32));
                this.pos = readRawVarint32 + this.pos;
                return wrap;
            } else if (readRawVarint32 == 0) {
                return Internal.EMPTY_BYTE_BUFFER;
            } else {
                return ByteBuffer.wrap(readRawBytesSlowPath(readRawVarint32));
            }
        }

        public int readUInt32() throws IOException {
            return readRawVarint32();
        }

        public int readEnum() throws IOException {
            return readRawVarint32();
        }

        public int readSFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public long readSFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public int readSInt32() throws IOException {
            return CodedInputStream.decodeZigZag32(readRawVarint32());
        }

        public long readSInt64() throws IOException {
            return CodedInputStream.decodeZigZag64(readRawVarint64());
        }

        public int readRawVarint32() throws IOException {
            int i = this.pos;
            if (this.bufferSize != i) {
                byte[] bArr = this.buffer;
                int i2 = i + 1;
                i = bArr[i];
                if (i >= 0) {
                    this.pos = i2;
                    return i;
                } else if (this.bufferSize - i2 >= 9) {
                    int i3 = i2 + 1;
                    i ^= bArr[i2] << 7;
                    if (i < 0) {
                        i ^= -128;
                    } else {
                        i2 = i3 + 1;
                        i ^= bArr[i3] << 14;
                        if (i >= 0) {
                            i ^= 16256;
                            i3 = i2;
                        } else {
                            i3 = i2 + 1;
                            i ^= bArr[i2] << 21;
                            if (i < 0) {
                                i ^= -2080896;
                            } else {
                                i2 = i3 + 1;
                                byte b = bArr[i3];
                                i = (i ^ (b << 28)) ^ 266354560;
                                if (b < (byte) 0) {
                                    i3 = i2 + 1;
                                    if (bArr[i2] < (byte) 0) {
                                        i2 = i3 + 1;
                                        if (bArr[i3] < (byte) 0) {
                                            i3 = i2 + 1;
                                            if (bArr[i2] < (byte) 0) {
                                                i2 = i3 + 1;
                                                if (bArr[i3] < (byte) 0) {
                                                    i3 = i2 + 1;
                                                    if (bArr[i2] < (byte) 0) {
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                i3 = i2;
                            }
                        }
                    }
                    this.pos = i3;
                    return i;
                }
            }
            return (int) readRawVarint64SlowPath();
        }

        private void skipRawVarint() throws IOException {
            if (this.bufferSize - this.pos >= 10) {
                skipRawVarintFastPath();
            } else {
                skipRawVarintSlowPath();
            }
        }

        private void skipRawVarintFastPath() throws IOException {
            int i = 0;
            while (i < 10) {
                byte[] bArr = this.buffer;
                int i2 = this.pos;
                this.pos = i2 + 1;
                if (bArr[i2] < (byte) 0) {
                    i++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipRawVarintSlowPath() throws IOException {
            int i = 0;
            while (i < 10) {
                if (readRawByte() < (byte) 0) {
                    i++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public long readRawVarint64() throws IOException {
            int i = this.pos;
            if (this.bufferSize != i) {
                byte[] bArr = this.buffer;
                int i2 = i + 1;
                byte b = bArr[i];
                if (b >= (byte) 0) {
                    this.pos = i2;
                    return (long) b;
                } else if (this.bufferSize - i2 >= 9) {
                    long j;
                    int i3 = i2 + 1;
                    i = b ^ (bArr[i2] << 7);
                    if (i < 0) {
                        j = (long) (i ^ -128);
                    } else {
                        int i4 = i3 + 1;
                        i ^= bArr[i3] << 14;
                        if (i >= 0) {
                            j = (long) (i ^ 16256);
                            i3 = i4;
                        } else {
                            i3 = i4 + 1;
                            i ^= bArr[i4] << 21;
                            if (i < 0) {
                                j = (long) (i ^ -2080896);
                            } else {
                                i4 = i3 + 1;
                                j = ((long) i) ^ (((long) bArr[i3]) << 28);
                                if (j >= 0) {
                                    j ^= 266354560;
                                    i3 = i4;
                                } else {
                                    i3 = i4 + 1;
                                    j ^= ((long) bArr[i4]) << 35;
                                    if (j < 0) {
                                        j ^= -34093383808L;
                                    } else {
                                        i4 = i3 + 1;
                                        j ^= ((long) bArr[i3]) << 42;
                                        if (j >= 0) {
                                            j ^= 4363953127296L;
                                            i3 = i4;
                                        } else {
                                            i3 = i4 + 1;
                                            j ^= ((long) bArr[i4]) << 49;
                                            if (j < 0) {
                                                j ^= -558586000294016L;
                                            } else {
                                                i4 = i3 + 1;
                                                j = (j ^ (((long) bArr[i3]) << 56)) ^ 71499008037633920L;
                                                if (j < 0) {
                                                    i3 = i4 + 1;
                                                    if (((long) bArr[i4]) < 0) {
                                                    }
                                                } else {
                                                    i3 = i4;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    this.pos = i3;
                    return j;
                }
            }
            return readRawVarint64SlowPath();
        }

        long readRawVarint64SlowPath() throws IOException {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                byte readRawByte = readRawByte();
                j |= ((long) (readRawByte & 127)) << i;
                if ((readRawByte & 128) == 0) {
                    return j;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public int readRawLittleEndian32() throws IOException {
            int i = this.pos;
            if (this.bufferSize - i < 4) {
                refillBuffer(4);
                i = this.pos;
            }
            byte[] bArr = this.buffer;
            this.pos = i + 4;
            return ((bArr[i + 3] & 255) << 24) | (((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16));
        }

        public long readRawLittleEndian64() throws IOException {
            int i = this.pos;
            if (this.bufferSize - i < 8) {
                refillBuffer(8);
                i = this.pos;
            }
            byte[] bArr = this.buffer;
            this.pos = i + 8;
            return ((((long) bArr[i + 7]) & 255) << 56) | (((((((((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8)) | ((((long) bArr[i + 2]) & 255) << 16)) | ((((long) bArr[i + 3]) & 255) << 24)) | ((((long) bArr[i + 4]) & 255) << 32)) | ((((long) bArr[i + 5]) & 255) << 40)) | ((((long) bArr[i + 6]) & 255) << 48));
        }

        public void enableAliasing(boolean z) {
        }

        public void resetSizeCounter() {
            this.totalBytesRetired = -this.pos;
        }

        public int pushLimit(int i) throws InvalidProtocolBufferException {
            if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int i2 = (this.totalBytesRetired + this.pos) + i;
            int i3 = this.currentLimit;
            if (i2 > i3) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.currentLimit = i2;
            recomputeBufferSizeAfterLimit();
            return i3;
        }

        private void recomputeBufferSizeAfterLimit() {
            this.bufferSize += this.bufferSizeAfterLimit;
            int i = this.totalBytesRetired + this.bufferSize;
            if (i > this.currentLimit) {
                this.bufferSizeAfterLimit = i - this.currentLimit;
                this.bufferSize -= this.bufferSizeAfterLimit;
                return;
            }
            this.bufferSizeAfterLimit = 0;
        }

        public void popLimit(int i) {
            this.currentLimit = i;
            recomputeBufferSizeAfterLimit();
        }

        public int getBytesUntilLimit() {
            if (this.currentLimit == Integer.MAX_VALUE) {
                return -1;
            }
            return this.currentLimit - (this.totalBytesRetired + this.pos);
        }

        public boolean isAtEnd() throws IOException {
            return this.pos == this.bufferSize && !tryRefillBuffer(1);
        }

        public int getTotalBytesRead() {
            return this.totalBytesRetired + this.pos;
        }

        private void refillBuffer(int i) throws IOException {
            if (!tryRefillBuffer(i)) {
                if (i > (this.sizeLimit - this.totalBytesRetired) - this.pos) {
                    throw InvalidProtocolBufferException.sizeLimitExceeded();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        private boolean tryRefillBuffer(int i) throws IOException {
            if (this.pos + i <= this.bufferSize) {
                throw new IllegalStateException("refillBuffer() called when " + i + " bytes were already available in buffer");
            } else if (i > (this.sizeLimit - this.totalBytesRetired) - this.pos || (this.totalBytesRetired + this.pos) + i > this.currentLimit) {
                return false;
            } else {
                if (this.refillCallback != null) {
                    this.refillCallback.onRefill();
                }
                int i2 = this.pos;
                if (i2 > 0) {
                    if (this.bufferSize > i2) {
                        System.arraycopy(this.buffer, i2, this.buffer, 0, this.bufferSize - i2);
                    }
                    this.totalBytesRetired += i2;
                    this.bufferSize -= i2;
                    this.pos = 0;
                }
                i2 = this.input.read(this.buffer, this.bufferSize, Math.min(this.buffer.length - this.bufferSize, (this.sizeLimit - this.totalBytesRetired) - this.bufferSize));
                if (i2 == 0 || i2 < -1 || i2 > this.buffer.length) {
                    throw new IllegalStateException("InputStream#read(byte[]) returned invalid result: " + i2 + "\nThe InputStream implementation is buggy.");
                } else if (i2 <= 0) {
                    return false;
                } else {
                    this.bufferSize += i2;
                    recomputeBufferSizeAfterLimit();
                    return this.bufferSize >= i ? true : tryRefillBuffer(i);
                }
            }
        }

        public byte readRawByte() throws IOException {
            if (this.pos == this.bufferSize) {
                refillBuffer(1);
            }
            byte[] bArr = this.buffer;
            int i = this.pos;
            this.pos = i + 1;
            return bArr[i];
        }

        public byte[] readRawBytes(int i) throws IOException {
            int i2 = this.pos;
            if (i > this.bufferSize - i2 || i <= 0) {
                return readRawBytesSlowPath(i);
            }
            this.pos = i2 + i;
            return Arrays.copyOfRange(this.buffer, i2, i2 + i);
        }

        private byte[] readRawBytesSlowPath(int i) throws IOException {
            byte[] readRawBytesSlowPathOneChunk = readRawBytesSlowPathOneChunk(i);
            if (readRawBytesSlowPathOneChunk != null) {
                return readRawBytesSlowPathOneChunk;
            }
            int i2 = this.pos;
            int i3 = this.bufferSize - this.pos;
            this.totalBytesRetired += this.bufferSize;
            this.pos = 0;
            this.bufferSize = 0;
            List<byte[]> readRawBytesSlowPathRemainingChunks = readRawBytesSlowPathRemainingChunks(i - i3);
            Object obj = new byte[i];
            System.arraycopy(this.buffer, i2, obj, 0, i3);
            i2 = i3;
            for (byte[] readRawBytesSlowPathOneChunk2 : readRawBytesSlowPathRemainingChunks) {
                System.arraycopy(readRawBytesSlowPathOneChunk2, 0, obj, i2, readRawBytesSlowPathOneChunk2.length);
                i2 = readRawBytesSlowPathOneChunk2.length + i2;
            }
            return obj;
        }

        private byte[] readRawBytesSlowPathOneChunk(int i) throws IOException {
            if (i == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            }
            if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int i2 = (this.totalBytesRetired + this.pos) + i;
            if (i2 - this.sizeLimit > 0) {
                throw InvalidProtocolBufferException.sizeLimitExceeded();
            } else if (i2 > this.currentLimit) {
                skipRawBytes((this.currentLimit - this.totalBytesRetired) - this.pos);
                throw InvalidProtocolBufferException.truncatedMessage();
            } else {
                i2 = this.bufferSize - this.pos;
                int i3 = i - i2;
                if (i3 >= 4096 && i3 > this.input.available()) {
                    return null;
                }
                Object obj = new byte[i];
                System.arraycopy(this.buffer, this.pos, obj, 0, i2);
                this.totalBytesRetired += this.bufferSize;
                this.pos = 0;
                this.bufferSize = 0;
                while (i2 < obj.length) {
                    int read = this.input.read(obj, i2, i - i2);
                    if (read == -1) {
                        throw InvalidProtocolBufferException.truncatedMessage();
                    }
                    this.totalBytesRetired += read;
                    i2 += read;
                }
                return obj;
            }
        }

        private List<byte[]> readRawBytesSlowPathRemainingChunks(int i) throws IOException {
            List<byte[]> arrayList = new ArrayList();
            while (i > 0) {
                Object obj = new byte[Math.min(i, 4096)];
                int i2 = 0;
                while (i2 < obj.length) {
                    int read = this.input.read(obj, i2, obj.length - i2);
                    if (read == -1) {
                        throw InvalidProtocolBufferException.truncatedMessage();
                    }
                    this.totalBytesRetired += read;
                    i2 += read;
                }
                i -= obj.length;
                arrayList.add(obj);
            }
            return arrayList;
        }

        private ByteString readBytesSlowPath(int i) throws IOException {
            byte[] readRawBytesSlowPathOneChunk = readRawBytesSlowPathOneChunk(i);
            if (readRawBytesSlowPathOneChunk != null) {
                return ByteString.wrap(readRawBytesSlowPathOneChunk);
            }
            int i2 = this.pos;
            int i3 = this.bufferSize - this.pos;
            this.totalBytesRetired += this.bufferSize;
            this.pos = 0;
            this.bufferSize = 0;
            List<byte[]> readRawBytesSlowPathRemainingChunks = readRawBytesSlowPathRemainingChunks(i - i3);
            Iterable arrayList = new ArrayList(readRawBytesSlowPathRemainingChunks.size() + 1);
            arrayList.add(ByteString.copyFrom(this.buffer, i2, i3));
            for (byte[] readRawBytesSlowPathOneChunk2 : readRawBytesSlowPathRemainingChunks) {
                arrayList.add(ByteString.wrap(readRawBytesSlowPathOneChunk2));
            }
            return ByteString.copyFrom(arrayList);
        }

        public void skipRawBytes(int i) throws IOException {
            if (i > this.bufferSize - this.pos || i < 0) {
                skipRawBytesSlowPath(i);
            } else {
                this.pos += i;
            }
        }

        private void skipRawBytesSlowPath(int i) throws IOException {
            if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            } else if ((this.totalBytesRetired + this.pos) + i > this.currentLimit) {
                skipRawBytes((this.currentLimit - this.totalBytesRetired) - this.pos);
                throw InvalidProtocolBufferException.truncatedMessage();
            } else {
                int i2 = this.bufferSize - this.pos;
                this.pos = this.bufferSize;
                refillBuffer(1);
                while (i - i2 > this.bufferSize) {
                    i2 += this.bufferSize;
                    this.pos = this.bufferSize;
                    refillBuffer(1);
                }
                this.pos = i - i2;
            }
        }
    }

    private static final class UnsafeDirectNioDecoder extends CodedInputStream {
        private final long address;
        private final ByteBuffer buffer;
        private int bufferSizeAfterLimit;
        private int currentLimit;
        private boolean enableAliasing;
        private final boolean immutable;
        private int lastTag;
        private long limit;
        private long pos;
        private long startPos;

        static boolean isSupported() {
            return UnsafeUtil.hasUnsafeByteBufferOperations();
        }

        private UnsafeDirectNioDecoder(ByteBuffer byteBuffer, boolean z) {
            super();
            this.currentLimit = Integer.MAX_VALUE;
            this.buffer = byteBuffer;
            this.address = UnsafeUtil.addressOffset(byteBuffer);
            this.limit = this.address + ((long) byteBuffer.limit());
            this.pos = this.address + ((long) byteBuffer.position());
            this.startPos = this.pos;
            this.immutable = z;
        }

        public int readTag() throws IOException {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            this.lastTag = readRawVarint32();
            if (WireFormat.getTagFieldNumber(this.lastTag) != 0) {
                return this.lastTag;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        public void checkLastTagWas(int i) throws InvalidProtocolBufferException {
            if (this.lastTag != i) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        public int getLastTag() {
            return this.lastTag;
        }

        public boolean skipField(int i) throws IOException {
            switch (WireFormat.getTagWireType(i)) {
                case 0:
                    skipRawVarint();
                    return true;
                case 1:
                    skipRawBytes(8);
                    return true;
                case 2:
                    skipRawBytes(readRawVarint32());
                    return true;
                case 3:
                    skipMessage();
                    checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4));
                    return true;
                case 4:
                    return false;
                case 5:
                    skipRawBytes(4);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public boolean skipField(int i, CodedOutputStream codedOutputStream) throws IOException {
            long readInt64;
            int makeTag;
            switch (WireFormat.getTagWireType(i)) {
                case 0:
                    readInt64 = readInt64();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeUInt64NoTag(readInt64);
                    return true;
                case 1:
                    readInt64 = readRawLittleEndian64();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeFixed64NoTag(readInt64);
                    return true;
                case 2:
                    ByteString readBytes = readBytes();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeBytesNoTag(readBytes);
                    return true;
                case 3:
                    codedOutputStream.writeRawVarint32(i);
                    skipMessage(codedOutputStream);
                    makeTag = WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4);
                    checkLastTagWas(makeTag);
                    codedOutputStream.writeRawVarint32(makeTag);
                    return true;
                case 4:
                    return false;
                case 5:
                    makeTag = readRawLittleEndian32();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeFixed32NoTag(makeTag);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public void skipMessage() throws IOException {
            int readTag;
            do {
                readTag = readTag();
                if (readTag == 0) {
                    return;
                }
            } while (skipField(readTag));
        }

        public void skipMessage(CodedOutputStream codedOutputStream) throws IOException {
            int readTag;
            do {
                readTag = readTag();
                if (readTag == 0) {
                    return;
                }
            } while (skipField(readTag, codedOutputStream));
        }

        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        public long readUInt64() throws IOException {
            return readRawVarint64();
        }

        public long readInt64() throws IOException {
            return readRawVarint64();
        }

        public int readInt32() throws IOException {
            return readRawVarint32();
        }

        public long readFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public int readFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public boolean readBool() throws IOException {
            return readRawVarint64() != 0;
        }

        public String readString() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0 && readRawVarint32 <= remaining()) {
                byte[] bArr = new byte[readRawVarint32];
                UnsafeUtil.copyMemory(this.pos, bArr, 0, (long) readRawVarint32);
                String str = new String(bArr, Internal.UTF_8);
                this.pos += (long) readRawVarint32;
                return str;
            } else if (readRawVarint32 == 0) {
                return "";
            } else {
                if (readRawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        public String readStringRequireUtf8() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 >= 0 && readRawVarint32 <= remaining()) {
                byte[] bArr = new byte[readRawVarint32];
                UnsafeUtil.copyMemory(this.pos, bArr, 0, (long) readRawVarint32);
                if (Utf8.isValidUtf8(bArr)) {
                    String str = new String(bArr, Internal.UTF_8);
                    this.pos += (long) readRawVarint32;
                    return str;
                }
                throw InvalidProtocolBufferException.invalidUtf8();
            } else if (readRawVarint32 == 0) {
                return "";
            } else {
                if (readRawVarint32 <= 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        public void readGroup(int i, Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            if (this.recursionDepth >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            this.recursionDepth++;
            builder.mergeFrom(this, extensionRegistryLite);
            checkLastTagWas(WireFormat.makeTag(i, 4));
            this.recursionDepth--;
        }

        public <T extends MessageLite> T readGroup(int i, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            if (this.recursionDepth >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            this.recursionDepth++;
            MessageLite messageLite = (MessageLite) parser.parsePartialFrom(this, extensionRegistryLite);
            checkLastTagWas(WireFormat.makeTag(i, 4));
            this.recursionDepth--;
            return messageLite;
        }

        @Deprecated
        public void readUnknownGroup(int i, Builder builder) throws IOException {
            readGroup(i, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        public void readMessage(Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (this.recursionDepth >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            readRawVarint32 = pushLimit(readRawVarint32);
            this.recursionDepth++;
            builder.mergeFrom(this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            popLimit(readRawVarint32);
        }

        public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (this.recursionDepth >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            int pushLimit = pushLimit(readRawVarint32);
            this.recursionDepth++;
            MessageLite messageLite = (MessageLite) parser.parsePartialFrom(this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            popLimit(pushLimit);
            return messageLite;
        }

        public ByteString readBytes() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 <= 0 || readRawVarint32 > remaining()) {
                if (readRawVarint32 == 0) {
                    return ByteString.EMPTY;
                }
                if (readRawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (this.immutable && this.enableAliasing) {
                ByteBuffer slice = slice(this.pos, this.pos + ((long) readRawVarint32));
                this.pos += (long) readRawVarint32;
                return ByteString.wrap(slice);
            } else {
                byte[] bArr = new byte[readRawVarint32];
                UnsafeUtil.copyMemory(this.pos, bArr, 0, (long) readRawVarint32);
                this.pos = ((long) readRawVarint32) + this.pos;
                return ByteString.wrap(bArr);
            }
        }

        public byte[] readByteArray() throws IOException {
            return readRawBytes(readRawVarint32());
        }

        public ByteBuffer readByteBuffer() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 <= 0 || readRawVarint32 > remaining()) {
                if (readRawVarint32 == 0) {
                    return Internal.EMPTY_BYTE_BUFFER;
                }
                if (readRawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (this.immutable || !this.enableAliasing) {
                byte[] bArr = new byte[readRawVarint32];
                UnsafeUtil.copyMemory(this.pos, bArr, 0, (long) readRawVarint32);
                this.pos += (long) readRawVarint32;
                return ByteBuffer.wrap(bArr);
            } else {
                ByteBuffer slice = slice(this.pos, this.pos + ((long) readRawVarint32));
                this.pos += (long) readRawVarint32;
                return slice;
            }
        }

        public int readUInt32() throws IOException {
            return readRawVarint32();
        }

        public int readEnum() throws IOException {
            return readRawVarint32();
        }

        public int readSFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public long readSFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public int readSInt32() throws IOException {
            return CodedInputStream.decodeZigZag32(readRawVarint32());
        }

        public long readSInt64() throws IOException {
            return CodedInputStream.decodeZigZag64(readRawVarint64());
        }

        public int readRawVarint32() throws IOException {
            long j = this.pos;
            if (this.limit != j) {
                long j2 = j + 1;
                int i = UnsafeUtil.getByte(j);
                if (i >= 0) {
                    this.pos = j2;
                    return i;
                } else if (this.limit - j2 >= 9) {
                    long j3 = j2 + 1;
                    i ^= UnsafeUtil.getByte(j2) << 7;
                    if (i < 0) {
                        i ^= -128;
                    } else {
                        j2 = j3 + 1;
                        i ^= UnsafeUtil.getByte(j3) << 14;
                        if (i >= 0) {
                            i ^= 16256;
                            j3 = j2;
                        } else {
                            j3 = j2 + 1;
                            i ^= UnsafeUtil.getByte(j2) << 21;
                            if (i < 0) {
                                i ^= -2080896;
                            } else {
                                j2 = j3 + 1;
                                byte b = UnsafeUtil.getByte(j3);
                                i = (i ^ (b << 28)) ^ 266354560;
                                if (b < (byte) 0) {
                                    j3 = j2 + 1;
                                    if (UnsafeUtil.getByte(j2) < (byte) 0) {
                                        j2 = j3 + 1;
                                        if (UnsafeUtil.getByte(j3) < (byte) 0) {
                                            j3 = j2 + 1;
                                            if (UnsafeUtil.getByte(j2) < (byte) 0) {
                                                j2 = j3 + 1;
                                                if (UnsafeUtil.getByte(j3) < (byte) 0) {
                                                    j3 = j2 + 1;
                                                    if (UnsafeUtil.getByte(j2) < (byte) 0) {
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                j3 = j2;
                            }
                        }
                    }
                    this.pos = j3;
                    return i;
                }
            }
            return (int) readRawVarint64SlowPath();
        }

        private void skipRawVarint() throws IOException {
            if (remaining() >= 10) {
                skipRawVarintFastPath();
            } else {
                skipRawVarintSlowPath();
            }
        }

        private void skipRawVarintFastPath() throws IOException {
            int i = 0;
            while (i < 10) {
                long j = this.pos;
                this.pos = 1 + j;
                if (UnsafeUtil.getByte(j) < (byte) 0) {
                    i++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipRawVarintSlowPath() throws IOException {
            int i = 0;
            while (i < 10) {
                if (readRawByte() < (byte) 0) {
                    i++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public long readRawVarint64() throws IOException {
            long j = this.pos;
            if (this.limit != j) {
                long j2 = j + 1;
                byte b = UnsafeUtil.getByte(j);
                if (b >= (byte) 0) {
                    this.pos = j2;
                    return (long) b;
                } else if (this.limit - j2 >= 9) {
                    long j3 = j2 + 1;
                    int i = b ^ (UnsafeUtil.getByte(j2) << 7);
                    if (i < 0) {
                        j = (long) (i ^ -128);
                    } else {
                        j2 = j3 + 1;
                        i ^= UnsafeUtil.getByte(j3) << 14;
                        if (i >= 0) {
                            j = (long) (i ^ 16256);
                            j3 = j2;
                        } else {
                            j3 = j2 + 1;
                            i ^= UnsafeUtil.getByte(j2) << 21;
                            if (i < 0) {
                                j = (long) (i ^ -2080896);
                            } else {
                                j2 = j3 + 1;
                                j = ((long) i) ^ (((long) UnsafeUtil.getByte(j3)) << 28);
                                if (j >= 0) {
                                    j ^= 266354560;
                                    j3 = j2;
                                } else {
                                    j3 = j2 + 1;
                                    j ^= ((long) UnsafeUtil.getByte(j2)) << 35;
                                    if (j < 0) {
                                        j ^= -34093383808L;
                                    } else {
                                        j2 = j3 + 1;
                                        j ^= ((long) UnsafeUtil.getByte(j3)) << 42;
                                        if (j >= 0) {
                                            j ^= 4363953127296L;
                                            j3 = j2;
                                        } else {
                                            j3 = j2 + 1;
                                            j ^= ((long) UnsafeUtil.getByte(j2)) << 49;
                                            if (j < 0) {
                                                j ^= -558586000294016L;
                                            } else {
                                                j2 = j3 + 1;
                                                j = (j ^ (((long) UnsafeUtil.getByte(j3)) << 56)) ^ 71499008037633920L;
                                                if (j < 0) {
                                                    j3 = j2 + 1;
                                                    if (((long) UnsafeUtil.getByte(j2)) < 0) {
                                                    }
                                                } else {
                                                    j3 = j2;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    this.pos = j3;
                    return j;
                }
            }
            return readRawVarint64SlowPath();
        }

        long readRawVarint64SlowPath() throws IOException {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                byte readRawByte = readRawByte();
                j |= ((long) (readRawByte & 127)) << i;
                if ((readRawByte & 128) == 0) {
                    return j;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public int readRawLittleEndian32() throws IOException {
            long j = this.pos;
            if (this.limit - j < 4) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.pos = j + 4;
            return ((UnsafeUtil.getByte(j + 3) & 255) << 24) | (((UnsafeUtil.getByte(j) & 255) | ((UnsafeUtil.getByte(1 + j) & 255) << 8)) | ((UnsafeUtil.getByte(2 + j) & 255) << 16));
        }

        public long readRawLittleEndian64() throws IOException {
            long j = this.pos;
            if (this.limit - j < 8) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.pos = j + 8;
            return ((((long) UnsafeUtil.getByte(j + 7)) & 255) << 56) | (((((((((long) UnsafeUtil.getByte(j)) & 255) | ((((long) UnsafeUtil.getByte(1 + j)) & 255) << 8)) | ((((long) UnsafeUtil.getByte(2 + j)) & 255) << 16)) | ((((long) UnsafeUtil.getByte(3 + j)) & 255) << 24)) | ((((long) UnsafeUtil.getByte(4 + j)) & 255) << 32)) | ((((long) UnsafeUtil.getByte(5 + j)) & 255) << 40)) | ((((long) UnsafeUtil.getByte(6 + j)) & 255) << 48));
        }

        public void enableAliasing(boolean z) {
            this.enableAliasing = z;
        }

        public void resetSizeCounter() {
            this.startPos = this.pos;
        }

        public int pushLimit(int i) throws InvalidProtocolBufferException {
            if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int totalBytesRead = getTotalBytesRead() + i;
            int i2 = this.currentLimit;
            if (totalBytesRead > i2) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.currentLimit = totalBytesRead;
            recomputeBufferSizeAfterLimit();
            return i2;
        }

        public void popLimit(int i) {
            this.currentLimit = i;
            recomputeBufferSizeAfterLimit();
        }

        public int getBytesUntilLimit() {
            if (this.currentLimit == Integer.MAX_VALUE) {
                return -1;
            }
            return this.currentLimit - getTotalBytesRead();
        }

        public boolean isAtEnd() throws IOException {
            return this.pos == this.limit;
        }

        public int getTotalBytesRead() {
            return (int) (this.pos - this.startPos);
        }

        public byte readRawByte() throws IOException {
            if (this.pos == this.limit) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            long j = this.pos;
            this.pos = 1 + j;
            return UnsafeUtil.getByte(j);
        }

        public byte[] readRawBytes(int i) throws IOException {
            if (i >= 0 && i <= remaining()) {
                byte[] bArr = new byte[i];
                slice(this.pos, this.pos + ((long) i)).get(bArr);
                this.pos += (long) i;
                return bArr;
            } else if (i > 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (i == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            } else {
                throw InvalidProtocolBufferException.negativeSize();
            }
        }

        public void skipRawBytes(int i) throws IOException {
            if (i >= 0 && i <= remaining()) {
                this.pos += (long) i;
            } else if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            } else {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        private void recomputeBufferSizeAfterLimit() {
            this.limit += (long) this.bufferSizeAfterLimit;
            int i = (int) (this.limit - this.startPos);
            if (i > this.currentLimit) {
                this.bufferSizeAfterLimit = i - this.currentLimit;
                this.limit -= (long) this.bufferSizeAfterLimit;
                return;
            }
            this.bufferSizeAfterLimit = 0;
        }

        private int remaining() {
            return (int) (this.limit - this.pos);
        }

        private int bufferPos(long j) {
            return (int) (j - this.address);
        }

        private ByteBuffer slice(long j, long j2) throws IOException {
            int position = this.buffer.position();
            int limit = this.buffer.limit();
            try {
                this.buffer.position(bufferPos(j));
                this.buffer.limit(bufferPos(j2));
                ByteBuffer slice = this.buffer.slice();
                this.buffer.position(position);
                this.buffer.limit(limit);
                return slice;
            } catch (IllegalArgumentException e) {
                throw InvalidProtocolBufferException.truncatedMessage();
            } catch (Throwable th) {
                this.buffer.position(position);
                this.buffer.limit(limit);
            }
        }
    }

    public abstract void checkLastTagWas(int i) throws InvalidProtocolBufferException;

    public abstract void enableAliasing(boolean z);

    public abstract int getBytesUntilLimit();

    public abstract int getLastTag();

    public abstract int getTotalBytesRead();

    public abstract boolean isAtEnd() throws IOException;

    public abstract void popLimit(int i);

    public abstract int pushLimit(int i) throws InvalidProtocolBufferException;

    public abstract boolean readBool() throws IOException;

    public abstract byte[] readByteArray() throws IOException;

    public abstract ByteBuffer readByteBuffer() throws IOException;

    public abstract ByteString readBytes() throws IOException;

    public abstract double readDouble() throws IOException;

    public abstract int readEnum() throws IOException;

    public abstract int readFixed32() throws IOException;

    public abstract long readFixed64() throws IOException;

    public abstract float readFloat() throws IOException;

    public abstract <T extends MessageLite> T readGroup(int i, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    public abstract void readGroup(int i, Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    public abstract int readInt32() throws IOException;

    public abstract long readInt64() throws IOException;

    public abstract <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    public abstract void readMessage(Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    public abstract byte readRawByte() throws IOException;

    public abstract byte[] readRawBytes(int i) throws IOException;

    public abstract int readRawLittleEndian32() throws IOException;

    public abstract long readRawLittleEndian64() throws IOException;

    public abstract int readRawVarint32() throws IOException;

    public abstract long readRawVarint64() throws IOException;

    abstract long readRawVarint64SlowPath() throws IOException;

    public abstract int readSFixed32() throws IOException;

    public abstract long readSFixed64() throws IOException;

    public abstract int readSInt32() throws IOException;

    public abstract long readSInt64() throws IOException;

    public abstract String readString() throws IOException;

    public abstract String readStringRequireUtf8() throws IOException;

    public abstract int readTag() throws IOException;

    public abstract int readUInt32() throws IOException;

    public abstract long readUInt64() throws IOException;

    @Deprecated
    public abstract void readUnknownGroup(int i, Builder builder) throws IOException;

    public abstract void resetSizeCounter();

    public abstract boolean skipField(int i) throws IOException;

    @Deprecated
    public abstract boolean skipField(int i, CodedOutputStream codedOutputStream) throws IOException;

    public abstract void skipMessage() throws IOException;

    public abstract void skipMessage(CodedOutputStream codedOutputStream) throws IOException;

    public abstract void skipRawBytes(int i) throws IOException;

    public static CodedInputStream newInstance(InputStream inputStream) {
        return newInstance(inputStream, 4096);
    }

    static CodedInputStream newInstance(InputStream inputStream, int i) {
        if (inputStream == null) {
            return newInstance(Internal.EMPTY_BYTE_ARRAY);
        }
        return new StreamDecoder(inputStream, i);
    }

    public static CodedInputStream newInstance(byte[] bArr) {
        return newInstance(bArr, 0, bArr.length);
    }

    public static CodedInputStream newInstance(byte[] bArr, int i, int i2) {
        return newInstance(bArr, i, i2, false);
    }

    static CodedInputStream newInstance(byte[] bArr, int i, int i2, boolean z) {
        CodedInputStream arrayDecoder = new ArrayDecoder(bArr, i, i2, z);
        try {
            arrayDecoder.pushLimit(i2);
            return arrayDecoder;
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static CodedInputStream newInstance(ByteBuffer byteBuffer) {
        return newInstance(byteBuffer, false);
    }

    static CodedInputStream newInstance(ByteBuffer byteBuffer, boolean z) {
        if (byteBuffer.hasArray()) {
            return newInstance(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining(), z);
        }
        if (byteBuffer.isDirect() && UnsafeDirectNioDecoder.isSupported()) {
            return new UnsafeDirectNioDecoder(byteBuffer, z);
        }
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.duplicate().get(bArr);
        return newInstance(bArr, 0, bArr.length, true);
    }

    private CodedInputStream() {
        this.recursionLimit = 100;
        this.sizeLimit = Integer.MAX_VALUE;
        this.explicitDiscardUnknownFields = false;
    }

    public final int setRecursionLimit(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Recursion limit cannot be negative: " + i);
        }
        int i2 = this.recursionLimit;
        this.recursionLimit = i;
        return i2;
    }

    public final int setSizeLimit(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Size limit cannot be negative: " + i);
        }
        int i2 = this.sizeLimit;
        this.sizeLimit = i;
        return i2;
    }

    static void setProto3DiscardUnknownsByDefaultForTest() {
        proto3DiscardUnknownFieldsDefault = true;
    }

    static void setProto3KeepUnknownsByDefaultForTest() {
        proto3DiscardUnknownFieldsDefault = false;
    }

    static boolean getProto3DiscardUnknownFieldsDefault() {
        return proto3DiscardUnknownFieldsDefault;
    }

    final void discardUnknownFields() {
        this.explicitDiscardUnknownFields = true;
    }

    final void unsetDiscardUnknownFields() {
        this.explicitDiscardUnknownFields = false;
    }

    final boolean shouldDiscardUnknownFields() {
        return this.explicitDiscardUnknownFields;
    }

    final boolean shouldDiscardUnknownFieldsProto3() {
        return this.explicitDiscardUnknownFields ? true : proto3DiscardUnknownFieldsDefault;
    }

    public static int decodeZigZag32(int i) {
        return (i >>> 1) ^ (-(i & 1));
    }

    public static long decodeZigZag64(long j) {
        return (j >>> 1) ^ (-(1 & j));
    }

    public static int readRawVarint32(int i, InputStream inputStream) throws IOException {
        if ((i & 128) != 0) {
            int read;
            i &= 127;
            int i2 = 7;
            while (i2 < 32) {
                read = inputStream.read();
                if (read != -1) {
                    i |= (read & 127) << i2;
                    if ((read & 128) == 0) {
                        break;
                    }
                    i2 += 7;
                } else {
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
            }
            while (i2 < 64) {
                read = inputStream.read();
                if (read == -1) {
                    throw InvalidProtocolBufferException.truncatedMessage();
                } else if ((read & 128) != 0) {
                    i2 += 7;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }
        return i;
    }

    static int readRawVarint32(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read != -1) {
            return readRawVarint32(read, inputStream);
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }
}
