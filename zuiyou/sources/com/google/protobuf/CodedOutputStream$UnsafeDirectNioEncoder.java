package com.google.protobuf;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

final class CodedOutputStream$UnsafeDirectNioEncoder extends CodedOutputStream {
    private final long address;
    private final ByteBuffer buffer;
    private final long initialPosition;
    private final long limit;
    private final long oneVarintLimit = (this.limit - 10);
    private final ByteBuffer originalBuffer;
    private long position = this.initialPosition;

    CodedOutputStream$UnsafeDirectNioEncoder(ByteBuffer byteBuffer) {
        super(null);
        this.originalBuffer = byteBuffer;
        this.buffer = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
        this.address = UnsafeUtil.addressOffset(byteBuffer);
        this.initialPosition = this.address + ((long) byteBuffer.position());
        this.limit = this.address + ((long) byteBuffer.limit());
    }

    static boolean isSupported() {
        return UnsafeUtil.hasUnsafeByteBufferOperations();
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
        if (this.position >= this.limit) {
            throw new CodedOutputStream$OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.position), Long.valueOf(this.limit), Integer.valueOf(1)}));
        }
        long j = this.position;
        this.position = 1 + j;
        UnsafeUtil.putByte(j, b);
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
        long j;
        if (this.position <= this.oneVarintLimit) {
            while ((i & -128) != 0) {
                j = this.position;
                this.position = j + 1;
                UnsafeUtil.putByte(j, (byte) ((i & 127) | 128));
                i >>>= 7;
            }
            j = this.position;
            this.position = j + 1;
            UnsafeUtil.putByte(j, (byte) i);
            return;
        }
        while (this.position < this.limit) {
            if ((i & -128) == 0) {
                j = this.position;
                this.position = j + 1;
                UnsafeUtil.putByte(j, (byte) i);
                return;
            }
            j = this.position;
            this.position = j + 1;
            UnsafeUtil.putByte(j, (byte) ((i & 127) | 128));
            i >>>= 7;
        }
        throw new CodedOutputStream$OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.position), Long.valueOf(this.limit), Integer.valueOf(1)}));
    }

    public void writeFixed32NoTag(int i) throws IOException {
        this.buffer.putInt(bufferPos(this.position), i);
        this.position += 4;
    }

    public void writeUInt64NoTag(long j) throws IOException {
        long j2;
        if (this.position <= this.oneVarintLimit) {
            while ((j & -128) != 0) {
                j2 = this.position;
                this.position = j2 + 1;
                UnsafeUtil.putByte(j2, (byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            j2 = this.position;
            this.position = j2 + 1;
            UnsafeUtil.putByte(j2, (byte) ((int) j));
            return;
        }
        while (this.position < this.limit) {
            if ((j & -128) == 0) {
                j2 = this.position;
                this.position = j2 + 1;
                UnsafeUtil.putByte(j2, (byte) ((int) j));
                return;
            }
            j2 = this.position;
            this.position = j2 + 1;
            UnsafeUtil.putByte(j2, (byte) ((((int) j) & 127) | 128));
            j >>>= 7;
        }
        throw new CodedOutputStream$OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.position), Long.valueOf(this.limit), Integer.valueOf(1)}));
    }

    public void writeFixed64NoTag(long j) throws IOException {
        this.buffer.putLong(bufferPos(this.position), j);
        this.position += 8;
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (bArr != null && i >= 0 && i2 >= 0 && bArr.length - i2 >= i && this.limit - ((long) i2) >= this.position) {
            UnsafeUtil.copyMemory(bArr, (long) i, this.position, (long) i2);
            this.position += (long) i2;
        } else if (bArr == null) {
            throw new NullPointerException("value");
        } else {
            throw new CodedOutputStream$OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.position), Long.valueOf(this.limit), Integer.valueOf(i2)}));
        }
    }

    public void writeLazy(byte[] bArr, int i, int i2) throws IOException {
        write(bArr, i, i2);
    }

    public void write(ByteBuffer byteBuffer) throws IOException {
        try {
            int remaining = byteBuffer.remaining();
            repositionBuffer(this.position);
            this.buffer.put(byteBuffer);
            this.position = ((long) remaining) + this.position;
        } catch (Throwable e) {
            throw new CodedOutputStream$OutOfSpaceException(e);
        }
    }

    public void writeLazy(ByteBuffer byteBuffer) throws IOException {
        write(byteBuffer);
    }

    public void writeStringNoTag(String str) throws IOException {
        long j = this.position;
        try {
            int computeUInt32SizeNoTag = computeUInt32SizeNoTag(str.length() * 3);
            int computeUInt32SizeNoTag2 = computeUInt32SizeNoTag(str.length());
            if (computeUInt32SizeNoTag2 == computeUInt32SizeNoTag) {
                computeUInt32SizeNoTag = bufferPos(this.position) + computeUInt32SizeNoTag2;
                this.buffer.position(computeUInt32SizeNoTag);
                Utf8.encodeUtf8(str, this.buffer);
                computeUInt32SizeNoTag = this.buffer.position() - computeUInt32SizeNoTag;
                writeUInt32NoTag(computeUInt32SizeNoTag);
                this.position = ((long) computeUInt32SizeNoTag) + this.position;
                return;
            }
            computeUInt32SizeNoTag = Utf8.encodedLength(str);
            writeUInt32NoTag(computeUInt32SizeNoTag);
            repositionBuffer(this.position);
            Utf8.encodeUtf8(str, this.buffer);
            this.position = ((long) computeUInt32SizeNoTag) + this.position;
        } catch (UnpairedSurrogateException e) {
            this.position = j;
            repositionBuffer(this.position);
            inefficientWriteStringNoTag(str, e);
        } catch (Throwable e2) {
            throw new CodedOutputStream$OutOfSpaceException(e2);
        } catch (Throwable e22) {
            throw new CodedOutputStream$OutOfSpaceException(e22);
        }
    }

    public void flush() {
        this.originalBuffer.position(bufferPos(this.position));
    }

    public int spaceLeft() {
        return (int) (this.limit - this.position);
    }

    public int getTotalBytesWritten() {
        return (int) (this.position - this.initialPosition);
    }

    private void repositionBuffer(long j) {
        this.buffer.position(bufferPos(j));
    }

    private int bufferPos(long j) {
        return (int) (j - this.address);
    }
}
