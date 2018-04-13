package com.google.protobuf;

abstract class CodedOutputStream$AbstractBufferedEncoder extends CodedOutputStream {
    final byte[] buffer;
    final int limit;
    int position;
    int totalBytesWritten;

    CodedOutputStream$AbstractBufferedEncoder(int i) {
        super(null);
        if (i < 0) {
            throw new IllegalArgumentException("bufferSize must be >= 0");
        }
        this.buffer = new byte[Math.max(i, 20)];
        this.limit = this.buffer.length;
    }

    public final int spaceLeft() {
        throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array or ByteBuffer.");
    }

    public final int getTotalBytesWritten() {
        return this.totalBytesWritten;
    }

    final void buffer(byte b) {
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        bArr[i] = b;
        this.totalBytesWritten++;
    }

    final void bufferTag(int i, int i2) {
        bufferUInt32NoTag(WireFormat.makeTag(i, i2));
    }

    final void bufferInt32NoTag(int i) {
        if (i >= 0) {
            bufferUInt32NoTag(i);
        } else {
            bufferUInt64NoTag((long) i);
        }
    }

    final void bufferUInt32NoTag(int i) {
        if (CodedOutputStream.access$100()) {
            byte[] bArr;
            int i2;
            long j = (long) this.position;
            while ((i & -128) != 0) {
                bArr = this.buffer;
                i2 = this.position;
                this.position = i2 + 1;
                UnsafeUtil.putByte(bArr, (long) i2, (byte) ((i & 127) | 128));
                i >>>= 7;
            }
            bArr = this.buffer;
            i2 = this.position;
            this.position = i2 + 1;
            UnsafeUtil.putByte(bArr, (long) i2, (byte) i);
            this.totalBytesWritten = ((int) (((long) this.position) - j)) + this.totalBytesWritten;
            return;
        }
        byte[] bArr2;
        int i3;
        while ((i & -128) != 0) {
            bArr2 = this.buffer;
            i3 = this.position;
            this.position = i3 + 1;
            bArr2[i3] = (byte) ((i & 127) | 128);
            this.totalBytesWritten++;
            i >>>= 7;
        }
        bArr2 = this.buffer;
        i3 = this.position;
        this.position = i3 + 1;
        bArr2[i3] = (byte) i;
        this.totalBytesWritten++;
    }

    final void bufferUInt64NoTag(long j) {
        if (CodedOutputStream.access$100()) {
            byte[] bArr;
            int i;
            long j2 = (long) this.position;
            while ((j & -128) != 0) {
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                UnsafeUtil.putByte(bArr, (long) i, (byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            bArr = this.buffer;
            i = this.position;
            this.position = i + 1;
            UnsafeUtil.putByte(bArr, (long) i, (byte) ((int) j));
            this.totalBytesWritten = ((int) (((long) this.position) - j2)) + this.totalBytesWritten;
            return;
        }
        byte[] bArr2;
        int i2;
        while ((j & -128) != 0) {
            bArr2 = this.buffer;
            i2 = this.position;
            this.position = i2 + 1;
            bArr2[i2] = (byte) ((((int) j) & 127) | 128);
            this.totalBytesWritten++;
            j >>>= 7;
        }
        bArr2 = this.buffer;
        i2 = this.position;
        this.position = i2 + 1;
        bArr2[i2] = (byte) ((int) j);
        this.totalBytesWritten++;
    }

    final void bufferFixed32NoTag(int i) {
        byte[] bArr = this.buffer;
        int i2 = this.position;
        this.position = i2 + 1;
        bArr[i2] = (byte) (i & 255);
        bArr = this.buffer;
        i2 = this.position;
        this.position = i2 + 1;
        bArr[i2] = (byte) ((i >> 8) & 255);
        bArr = this.buffer;
        i2 = this.position;
        this.position = i2 + 1;
        bArr[i2] = (byte) ((i >> 16) & 255);
        bArr = this.buffer;
        i2 = this.position;
        this.position = i2 + 1;
        bArr[i2] = (byte) ((i >> 24) & 255);
        this.totalBytesWritten += 4;
    }

    final void bufferFixed64NoTag(long j) {
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        bArr[i] = (byte) ((int) (j & 255));
        bArr = this.buffer;
        i = this.position;
        this.position = i + 1;
        bArr[i] = (byte) ((int) ((j >> 8) & 255));
        bArr = this.buffer;
        i = this.position;
        this.position = i + 1;
        bArr[i] = (byte) ((int) ((j >> 16) & 255));
        bArr = this.buffer;
        i = this.position;
        this.position = i + 1;
        bArr[i] = (byte) ((int) ((j >> 24) & 255));
        bArr = this.buffer;
        i = this.position;
        this.position = i + 1;
        bArr[i] = (byte) (((int) (j >> 32)) & 255);
        bArr = this.buffer;
        i = this.position;
        this.position = i + 1;
        bArr[i] = (byte) (((int) (j >> 40)) & 255);
        bArr = this.buffer;
        i = this.position;
        this.position = i + 1;
        bArr[i] = (byte) (((int) (j >> 48)) & 255);
        bArr = this.buffer;
        i = this.position;
        this.position = i + 1;
        bArr[i] = (byte) (((int) (j >> 56)) & 255);
        this.totalBytesWritten += 8;
    }
}
