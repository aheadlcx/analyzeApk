package cz.msebera.android.httpclient.util;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import java.io.Serializable;

@NotThreadSafe
public final class ByteArrayBuffer implements Serializable {
    private byte[] a;
    private int b;

    public ByteArrayBuffer(int i) {
        Args.notNegative(i, "Buffer capacity");
        this.a = new byte[i];
    }

    private void a(int i) {
        Object obj = new byte[Math.max(this.a.length << 1, i)];
        System.arraycopy(this.a, 0, obj, 0, this.b);
        this.a = obj;
    }

    public void append(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            if (i < 0 || i > bArr.length || i2 < 0 || i + i2 < 0 || i + i2 > bArr.length) {
                throw new IndexOutOfBoundsException("off: " + i + " len: " + i2 + " b.length: " + bArr.length);
            } else if (i2 != 0) {
                int i3 = this.b + i2;
                if (i3 > this.a.length) {
                    a(i3);
                }
                System.arraycopy(bArr, i, this.a, this.b, i2);
                this.b = i3;
            }
        }
    }

    public void append(int i) {
        int i2 = this.b + 1;
        if (i2 > this.a.length) {
            a(i2);
        }
        this.a[this.b] = (byte) i;
        this.b = i2;
    }

    public void append(char[] cArr, int i, int i2) {
        if (cArr != null) {
            if (i < 0 || i > cArr.length || i2 < 0 || i + i2 < 0 || i + i2 > cArr.length) {
                throw new IndexOutOfBoundsException("off: " + i + " len: " + i2 + " b.length: " + cArr.length);
            } else if (i2 != 0) {
                int i3 = this.b;
                int i4 = i3 + i2;
                if (i4 > this.a.length) {
                    a(i4);
                }
                while (i3 < i4) {
                    this.a[i3] = (byte) cArr[i];
                    i++;
                    i3++;
                }
                this.b = i4;
            }
        }
    }

    public void append(CharArrayBuffer charArrayBuffer, int i, int i2) {
        if (charArrayBuffer != null) {
            append(charArrayBuffer.buffer(), i, i2);
        }
    }

    public void clear() {
        this.b = 0;
    }

    public byte[] toByteArray() {
        Object obj = new byte[this.b];
        if (this.b > 0) {
            System.arraycopy(this.a, 0, obj, 0, this.b);
        }
        return obj;
    }

    public int byteAt(int i) {
        return this.a[i];
    }

    public int capacity() {
        return this.a.length;
    }

    public int length() {
        return this.b;
    }

    public void ensureCapacity(int i) {
        if (i > 0 && i > this.a.length - this.b) {
            a(this.b + i);
        }
    }

    public byte[] buffer() {
        return this.a;
    }

    public void setLength(int i) {
        if (i < 0 || i > this.a.length) {
            throw new IndexOutOfBoundsException("len: " + i + " < 0 or > buffer len: " + this.a.length);
        }
        this.b = i;
    }

    public boolean isEmpty() {
        return this.b == 0;
    }

    public boolean isFull() {
        return this.b == this.a.length;
    }

    public int indexOf(byte b, int i, int i2) {
        if (i < 0) {
            i = 0;
        }
        if (i2 > this.b) {
            i2 = this.b;
        }
        if (i > i2) {
            return -1;
        }
        for (int i3 = i; i3 < i2; i3++) {
            if (this.a[i3] == b) {
                return i3;
            }
        }
        return -1;
    }

    public int indexOf(byte b) {
        return indexOf(b, 0, this.b);
    }
}
