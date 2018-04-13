package com.google.protobuf;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

final class CodedOutputStream$SafeDirectNioEncoder extends CodedOutputStream {
    private final ByteBuffer buffer;
    private final int initialPosition;
    private final ByteBuffer originalBuffer;

    CodedOutputStream$SafeDirectNioEncoder(ByteBuffer byteBuffer) {
        super(null);
        this.originalBuffer = byteBuffer;
        this.buffer = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
        this.initialPosition = byteBuffer.position();
    }

    public void writeTag(int i, int i2) throws IOException {
        writeUInt32NoTag(WireFormat.makeTag(i, i2));
    }

    public void writeInt32(int i, int i2) throws IOException {
        writeTag(i, 0);
        writeInt32NoTag(i2);
    }

    public void writeUInt32(int i, int i2) throws IOException {
        writeTag(i, 0);
        writeUInt32NoTag(i2);
    }

    public void writeFixed32(int i, int i2) throws IOException {
        writeTag(i, 5);
        writeFixed32NoTag(i2);
    }

    public void writeUInt64(int i, long j) throws IOException {
        writeTag(i, 0);
        writeUInt64NoTag(j);
    }

    public void writeFixed64(int i, long j) throws IOException {
        writeTag(i, 1);
        writeFixed64NoTag(j);
    }

    public void writeBool(int i, boolean z) throws IOException {
        int i2 = 0;
        writeTag(i, 0);
        if (z) {
            i2 = 1;
        }
        write((byte) i2);
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
        try {
            this.buffer.put(b);
        } catch (Throwable e) {
            throw new CodedOutputStream$OutOfSpaceException(e);
        }
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

    public void writeInt32NoTag(int i) throws IOException {
        if (i >= 0) {
            writeUInt32NoTag(i);
        } else {
            writeUInt64NoTag((long) i);
        }
    }

    public void writeUInt32NoTag(int i) throws IOException {
        while ((i & -128) != 0) {
            this.buffer.put((byte) ((i & 127) | 128));
            i >>>= 7;
        }
        try {
            this.buffer.put((byte) i);
        } catch (Throwable e) {
            throw new CodedOutputStream$OutOfSpaceException(e);
        }
    }

    public void writeFixed32NoTag(int i) throws IOException {
        try {
            this.buffer.putInt(i);
        } catch (Throwable e) {
            throw new CodedOutputStream$OutOfSpaceException(e);
        }
    }

    public void writeUInt64NoTag(long j) throws IOException {
        while ((-128 & j) != 0) {
            this.buffer.put((byte) ((((int) j) & 127) | 128));
            j >>>= 7;
        }
        try {
            this.buffer.put((byte) ((int) j));
        } catch (Throwable e) {
            throw new CodedOutputStream$OutOfSpaceException(e);
        }
    }

    public void writeFixed64NoTag(long j) throws IOException {
        try {
            this.buffer.putLong(j);
        } catch (Throwable e) {
            throw new CodedOutputStream$OutOfSpaceException(e);
        }
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        try {
            this.buffer.put(bArr, i, i2);
        } catch (Throwable e) {
            throw new CodedOutputStream$OutOfSpaceException(e);
        } catch (Throwable e2) {
            throw new CodedOutputStream$OutOfSpaceException(e2);
        }
    }

    public void writeLazy(byte[] bArr, int i, int i2) throws IOException {
        write(bArr, i, i2);
    }

    public void write(ByteBuffer byteBuffer) throws IOException {
        try {
            this.buffer.put(byteBuffer);
        } catch (Throwable e) {
            throw new CodedOutputStream$OutOfSpaceException(e);
        }
    }

    public void writeLazy(ByteBuffer byteBuffer) throws IOException {
        write(byteBuffer);
    }

    public void writeStringNoTag(String str) throws IOException {
        int position = this.buffer.position();
        try {
            int computeUInt32SizeNoTag = computeUInt32SizeNoTag(str.length() * 3);
            int computeUInt32SizeNoTag2 = computeUInt32SizeNoTag(str.length());
            if (computeUInt32SizeNoTag2 == computeUInt32SizeNoTag) {
                computeUInt32SizeNoTag = this.buffer.position() + computeUInt32SizeNoTag2;
                this.buffer.position(computeUInt32SizeNoTag);
                encode(str);
                computeUInt32SizeNoTag2 = this.buffer.position();
                this.buffer.position(position);
                writeUInt32NoTag(computeUInt32SizeNoTag2 - computeUInt32SizeNoTag);
                this.buffer.position(computeUInt32SizeNoTag2);
                return;
            }
            writeUInt32NoTag(Utf8.encodedLength(str));
            encode(str);
        } catch (UnpairedSurrogateException e) {
            this.buffer.position(position);
            inefficientWriteStringNoTag(str, e);
        } catch (Throwable e2) {
            throw new CodedOutputStream$OutOfSpaceException(e2);
        }
    }

    public void flush() {
        this.originalBuffer.position(this.buffer.position());
    }

    public int spaceLeft() {
        return this.buffer.remaining();
    }

    public int getTotalBytesWritten() {
        return this.buffer.position() - this.initialPosition;
    }

    private void encode(String str) throws IOException {
        try {
            Utf8.encodeUtf8(str, this.buffer);
        } catch (Throwable e) {
            throw new CodedOutputStream$OutOfSpaceException(e);
        }
    }
}
