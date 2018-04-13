package cz.msebera.android.httpclient.util;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.io.Serializable;
import java.nio.CharBuffer;

@NotThreadSafe
public final class CharArrayBuffer implements Serializable, CharSequence {
    private char[] a;
    private int b;

    public CharArrayBuffer(int i) {
        Args.notNegative(i, "Buffer capacity");
        this.a = new char[i];
    }

    private void a(int i) {
        Object obj = new char[Math.max(this.a.length << 1, i)];
        System.arraycopy(this.a, 0, obj, 0, this.b);
        this.a = obj;
    }

    public void append(char[] cArr, int i, int i2) {
        if (cArr != null) {
            if (i < 0 || i > cArr.length || i2 < 0 || i + i2 < 0 || i + i2 > cArr.length) {
                throw new IndexOutOfBoundsException("off: " + i + " len: " + i2 + " b.length: " + cArr.length);
            } else if (i2 != 0) {
                int i3 = this.b + i2;
                if (i3 > this.a.length) {
                    a(i3);
                }
                System.arraycopy(cArr, i, this.a, this.b, i2);
                this.b = i3;
            }
        }
    }

    public void append(String str) {
        if (str == null) {
            str = "null";
        }
        int length = str.length();
        int i = this.b + length;
        if (i > this.a.length) {
            a(i);
        }
        str.getChars(0, length, this.a, this.b);
        this.b = i;
    }

    public void append(CharArrayBuffer charArrayBuffer, int i, int i2) {
        if (charArrayBuffer != null) {
            append(charArrayBuffer.a, i, i2);
        }
    }

    public void append(CharArrayBuffer charArrayBuffer) {
        if (charArrayBuffer != null) {
            append(charArrayBuffer.a, 0, charArrayBuffer.b);
        }
    }

    public void append(char c) {
        int i = this.b + 1;
        if (i > this.a.length) {
            a(i);
        }
        this.a[this.b] = c;
        this.b = i;
    }

    public void append(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            if (i < 0 || i > bArr.length || i2 < 0 || i + i2 < 0 || i + i2 > bArr.length) {
                throw new IndexOutOfBoundsException("off: " + i + " len: " + i2 + " b.length: " + bArr.length);
            } else if (i2 != 0) {
                int i3 = this.b;
                int i4 = i3 + i2;
                if (i4 > this.a.length) {
                    a(i4);
                }
                while (i3 < i4) {
                    this.a[i3] = (char) (bArr[i] & 255);
                    i++;
                    i3++;
                }
                this.b = i4;
            }
        }
    }

    public void append(ByteArrayBuffer byteArrayBuffer, int i, int i2) {
        if (byteArrayBuffer != null) {
            append(byteArrayBuffer.buffer(), i, i2);
        }
    }

    public void append(Object obj) {
        append(String.valueOf(obj));
    }

    public void clear() {
        this.b = 0;
    }

    public char[] toCharArray() {
        Object obj = new char[this.b];
        if (this.b > 0) {
            System.arraycopy(this.a, 0, obj, 0, this.b);
        }
        return obj;
    }

    public char charAt(int i) {
        return this.a[i];
    }

    public char[] buffer() {
        return this.a;
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

    public int indexOf(int i, int i2, int i3) {
        if (i2 < 0) {
            i2 = 0;
        }
        if (i3 > this.b) {
            i3 = this.b;
        }
        if (i2 > i3) {
            return -1;
        }
        for (int i4 = i2; i4 < i3; i4++) {
            if (this.a[i4] == i) {
                return i4;
            }
        }
        return -1;
    }

    public int indexOf(int i) {
        return indexOf(i, 0, this.b);
    }

    public String substring(int i, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Negative beginIndex: " + i);
        } else if (i2 > this.b) {
            throw new IndexOutOfBoundsException("endIndex: " + i2 + " > length: " + this.b);
        } else if (i <= i2) {
            return new String(this.a, i, i2 - i);
        } else {
            throw new IndexOutOfBoundsException("beginIndex: " + i + " > endIndex: " + i2);
        }
    }

    public String substringTrimmed(int i, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Negative beginIndex: " + i);
        } else if (i2 > this.b) {
            throw new IndexOutOfBoundsException("endIndex: " + i2 + " > length: " + this.b);
        } else if (i > i2) {
            throw new IndexOutOfBoundsException("beginIndex: " + i + " > endIndex: " + i2);
        } else {
            while (i < i2 && HTTP.isWhitespace(this.a[i])) {
                i++;
            }
            while (i2 > i && HTTP.isWhitespace(this.a[i2 - 1])) {
                i2--;
            }
            return new String(this.a, i, i2 - i);
        }
    }

    public CharSequence subSequence(int i, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Negative beginIndex: " + i);
        } else if (i2 > this.b) {
            throw new IndexOutOfBoundsException("endIndex: " + i2 + " > length: " + this.b);
        } else if (i <= i2) {
            return CharBuffer.wrap(this.a, i, i2);
        } else {
            throw new IndexOutOfBoundsException("beginIndex: " + i + " > endIndex: " + i2);
        }
    }

    public String toString() {
        return new String(this.a, 0, this.b);
    }
}
