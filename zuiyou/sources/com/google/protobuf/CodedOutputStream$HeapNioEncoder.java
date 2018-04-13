package com.google.protobuf;

import java.nio.ByteBuffer;

final class CodedOutputStream$HeapNioEncoder extends CodedOutputStream$ArrayEncoder {
    private final ByteBuffer byteBuffer;
    private int initialPosition;

    CodedOutputStream$HeapNioEncoder(ByteBuffer byteBuffer) {
        super(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
        this.byteBuffer = byteBuffer;
        this.initialPosition = byteBuffer.position();
    }

    public void flush() {
        this.byteBuffer.position(this.initialPosition + getTotalBytesWritten());
    }
}
