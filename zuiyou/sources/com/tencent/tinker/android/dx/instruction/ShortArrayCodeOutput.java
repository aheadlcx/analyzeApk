package com.tencent.tinker.android.dx.instruction;

public final class ShortArrayCodeOutput extends CodeCursor {
    private short[] array;

    public ShortArrayCodeOutput(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("initSize < 0");
        }
        this.array = new short[i];
    }

    public ShortArrayCodeOutput(short[] sArr) {
        if (sArr == null) {
            throw new IllegalArgumentException("array is null.");
        }
        this.array = sArr;
    }

    public short[] getArray() {
        int cursor = cursor();
        if (cursor == this.array.length) {
            return this.array;
        }
        short[] sArr = new short[cursor];
        System.arraycopy(this.array, 0, sArr, 0, cursor);
        return sArr;
    }

    public void write(short s) {
        ensureArrayLength(1);
        this.array[cursor()] = s;
        advance(1);
    }

    public void write(short s, short s2) {
        write(s);
        write(s2);
    }

    public void write(short s, short s2, short s3) {
        write(s);
        write(s2);
        write(s3);
    }

    public void write(short s, short s2, short s3, short s4) {
        write(s);
        write(s2);
        write(s3);
        write(s4);
    }

    public void write(short s, short s2, short s3, short s4, short s5) {
        write(s);
        write(s2);
        write(s3);
        write(s4);
        write(s5);
    }

    public void writeInt(int i) {
        write((short) i);
        write((short) (i >> 16));
    }

    public void writeLong(long j) {
        write((short) ((int) j));
        write((short) ((int) (j >> 16)));
        write((short) ((int) (j >> 32)));
        write((short) ((int) (j >> 48)));
    }

    public void write(byte[] bArr) {
        Object obj = 1;
        int i = 0;
        for (byte b : bArr) {
            if (obj != null) {
                i = b & 255;
                obj = null;
            } else {
                int i2 = (b << 8) | i;
                write((short) i2);
                i = i2;
                i2 = 1;
            }
        }
        if (obj == null) {
            write((short) i);
        }
    }

    public void write(short[] sArr) {
        for (short write : sArr) {
            write(write);
        }
    }

    public void write(int[] iArr) {
        for (int writeInt : iArr) {
            writeInt(writeInt);
        }
    }

    public void write(long[] jArr) {
        for (long writeLong : jArr) {
            writeLong(writeLong);
        }
    }

    private void ensureArrayLength(int i) {
        int cursor = cursor();
        if (this.array.length - cursor < i) {
            Object obj = new short[(this.array.length + (this.array.length >> 1))];
            System.arraycopy(this.array, 0, obj, 0, cursor);
            this.array = obj;
        }
    }
}
