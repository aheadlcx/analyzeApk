package com.google.protobuf;

import java.io.IOException;
import java.nio.ByteBuffer;

final class CodedOutputStream$ByteOutputEncoder extends CodedOutputStream$AbstractBufferedEncoder {
    private final ByteOutput out;

    CodedOutputStream$ByteOutputEncoder(ByteOutput byteOutput, int i) {
        super(i);
        if (byteOutput == null) {
            throw new NullPointerException("out");
        }
        this.out = byteOutput;
    }

    public void writeTag(int i, int i2) throws IOException {
        writeUInt32NoTag(WireFormat.makeTag(i, i2));
    }

    public void writeInt32(int i, int i2) throws IOException {
        flushIfNotAvailable(20);
        bufferTag(i, 0);
        bufferInt32NoTag(i2);
    }

    public void writeUInt32(int i, int i2) throws IOException {
        flushIfNotAvailable(20);
        bufferTag(i, 0);
        bufferUInt32NoTag(i2);
    }

    public void writeFixed32(int i, int i2) throws IOException {
        flushIfNotAvailable(14);
        bufferTag(i, 5);
        bufferFixed32NoTag(i2);
    }

    public void writeUInt64(int i, long j) throws IOException {
        flushIfNotAvailable(20);
        bufferTag(i, 0);
        bufferUInt64NoTag(j);
    }

    public void writeFixed64(int i, long j) throws IOException {
        flushIfNotAvailable(18);
        bufferTag(i, 1);
        bufferFixed64NoTag(j);
    }

    public void writeBool(int i, boolean z) throws IOException {
        int i2 = 0;
        flushIfNotAvailable(11);
        bufferTag(i, 0);
        if (z) {
            i2 = 1;
        }
        buffer((byte) i2);
    }

    public void writeString(int i, String str) throws IOException {
        writeTag(i, 2);
        writeStringNoTag(str);
    }

    public void writeBytes(int i, ByteString byteString) throws IOException {
        writeTag(i, 2);
        writeBytesNoTag(byteString);
    }

    public void writeByteArray(int i, byte[] bArr) throws IOException {
        writeByteArray(i, bArr, 0, bArr.length);
    }

    public void writeByteArray(int i, byte[] bArr, int i2, int i3) throws IOException {
        writeTag(i, 2);
        writeByteArrayNoTag(bArr, i2, i3);
    }

    public void writeByteBuffer(int i, ByteBuffer byteBuffer) throws IOException {
        writeTag(i, 2);
        writeUInt32NoTag(byteBuffer.capacity());
        writeRawBytes(byteBuffer);
    }

    public void writeBytesNoTag(ByteString byteString) throws IOException {
        writeUInt32NoTag(byteString.size());
        byteString.writeTo((ByteOutput) this);
    }

    public void writeByteArrayNoTag(byte[] bArr, int i, int i2) throws IOException {
        writeUInt32NoTag(i2);
        write(bArr, i, i2);
    }

    public void writeRawBytes(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.hasArray()) {
            write(byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.capacity());
            return;
        }
        ByteBuffer duplicate = byteBuffer.duplicate();
        duplicate.clear();
        write(duplicate);
    }

    public void writeMessage(int i, MessageLite messageLite) throws IOException {
        writeTag(i, 2);
        writeMessageNoTag(messageLite);
    }

    public void writeMessageSetExtension(int i, MessageLite messageLite) throws IOException {
        writeTag(1, 3);
        writeUInt32(2, i);
        writeMessage(3, messageLite);
        writeTag(1, 4);
    }

    public void writeRawMessageSetExtension(int i, ByteString byteString) throws IOException {
        writeTag(1, 3);
        writeUInt32(2, i);
        writeBytes(3, byteString);
        writeTag(1, 4);
    }

    public void writeMessageNoTag(MessageLite messageLite) throws IOException {
        writeUInt32NoTag(messageLite.getSerializedSize());
        messageLite.writeTo((CodedOutputStream) this);
    }

    public void write(byte b) throws IOException {
        if (this.position == this.limit) {
            doFlush();
        }
        buffer(b);
    }

    public void writeInt32NoTag(int i) throws IOException {
        if (i >= 0) {
            writeUInt32NoTag(i);
        } else {
            writeUInt64NoTag((long) i);
        }
    }

    public void writeUInt32NoTag(int i) throws IOException {
        flushIfNotAvailable(10);
        bufferUInt32NoTag(i);
    }

    public void writeFixed32NoTag(int i) throws IOException {
        flushIfNotAvailable(4);
        bufferFixed32NoTag(i);
    }

    public void writeUInt64NoTag(long j) throws IOException {
        flushIfNotAvailable(10);
        bufferUInt64NoTag(j);
    }

    public void writeFixed64NoTag(long j) throws IOException {
        flushIfNotAvailable(8);
        bufferFixed64NoTag(j);
    }

    public void writeStringNoTag(String str) throws IOException {
        int length = str.length() * 3;
        int computeUInt32SizeNoTag = computeUInt32SizeNoTag(length);
        if (computeUInt32SizeNoTag + length > this.limit) {
            byte[] bArr = new byte[length];
            length = Utf8.encode(str, bArr, 0, length);
            writeUInt32NoTag(length);
            writeLazy(bArr, 0, length);
            return;
        }
        if (length + computeUInt32SizeNoTag > this.limit - this.position) {
            doFlush();
        }
        int i = this.position;
        try {
            length = computeUInt32SizeNoTag(str.length());
            if (length == computeUInt32SizeNoTag) {
                this.position = i + length;
                computeUInt32SizeNoTag = Utf8.encode(str, this.buffer, this.position, this.limit - this.position);
                this.position = i;
                length = (computeUInt32SizeNoTag - i) - length;
                bufferUInt32NoTag(length);
                this.position = computeUInt32SizeNoTag;
                this.totalBytesWritten = length + this.totalBytesWritten;
                return;
            }
            length = Utf8.encodedLength(str);
            bufferUInt32NoTag(length);
            this.position = Utf8.encode(str, this.buffer, this.position, length);
            this.totalBytesWritten = length + this.totalBytesWritten;
        } catch (UnpairedSurrogateException e) {
            this.totalBytesWritten -= this.position - i;
            this.position = i;
            inefficientWriteStringNoTag(str, e);
        } catch (Throwable e2) {
            throw new CodedOutputStream$OutOfSpaceException(e2);
        }
    }

    public void flush() throws IOException {
        if (this.position > 0) {
            doFlush();
        }
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        flush();
        this.out.write(bArr, i, i2);
        this.totalBytesWritten += i2;
    }

    public void writeLazy(byte[] bArr, int i, int i2) throws IOException {
        flush();
        this.out.writeLazy(bArr, i, i2);
        this.totalBytesWritten += i2;
    }

    public void write(ByteBuffer byteBuffer) throws IOException {
        flush();
        int remaining = byteBuffer.remaining();
        this.out.write(byteBuffer);
        this.totalBytesWritten = remaining + this.totalBytesWritten;
    }

    public void writeLazy(ByteBuffer byteBuffer) throws IOException {
        flush();
        int remaining = byteBuffer.remaining();
        this.out.writeLazy(byteBuffer);
        this.totalBytesWritten = remaining + this.totalBytesWritten;
    }

    private void flushIfNotAvailable(int i) throws IOException {
        if (this.limit - this.position < i) {
            doFlush();
        }
    }

    private void doFlush() throws IOException {
        this.out.write(this.buffer, 0, this.position);
        this.position = 0;
    }
}
