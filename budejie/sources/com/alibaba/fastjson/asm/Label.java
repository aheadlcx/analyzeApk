package com.alibaba.fastjson.asm;

public class Label {
    int inputStackTop;
    Label next;
    int outputStackMax;
    int position;
    private int referenceCount;
    private int[] srcAndRefPositions;
    int status;
    Label successor;

    void put(MethodWriter methodWriter, ByteVector byteVector, int i) {
        if ((this.status & 2) == 0) {
            addReference(i, byteVector.length);
            byteVector.putShort(-1);
            return;
        }
        byteVector.putShort(this.position - i);
    }

    private void addReference(int i, int i2) {
        if (this.srcAndRefPositions == null) {
            this.srcAndRefPositions = new int[6];
        }
        if (this.referenceCount >= this.srcAndRefPositions.length) {
            Object obj = new int[(this.srcAndRefPositions.length + 6)];
            System.arraycopy(this.srcAndRefPositions, 0, obj, 0, this.srcAndRefPositions.length);
            this.srcAndRefPositions = obj;
        }
        int[] iArr = this.srcAndRefPositions;
        int i3 = this.referenceCount;
        this.referenceCount = i3 + 1;
        iArr[i3] = i;
        iArr = this.srcAndRefPositions;
        i3 = this.referenceCount;
        this.referenceCount = i3 + 1;
        iArr[i3] = i2;
    }

    void resolve(MethodWriter methodWriter, int i, byte[] bArr) {
        this.status |= 2;
        this.position = i;
        int i2 = 0;
        while (i2 < this.referenceCount) {
            int i3 = i2 + 1;
            int i4 = this.srcAndRefPositions[i2];
            i2 = i3 + 1;
            i3 = this.srcAndRefPositions[i3];
            i4 = i - i4;
            int i5 = i3 + 1;
            bArr[i3] = (byte) (i4 >>> 8);
            bArr[i5] = (byte) i4;
        }
    }
}
