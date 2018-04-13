package org.mozilla.javascript.v8dtoa;

import java.util.Arrays;

public class FastDtoaBuilder {
    static final char[] digits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    final char[] chars = new char[25];
    int end = 0;
    boolean formatted = false;
    int point;

    void append(char c) {
        char[] cArr = this.chars;
        int i = this.end;
        this.end = i + 1;
        cArr[i] = c;
    }

    void decreaseLast() {
        char[] cArr = this.chars;
        int i = this.end - 1;
        cArr[i] = (char) (cArr[i] - 1);
    }

    public void reset() {
        this.end = 0;
        this.formatted = false;
    }

    public String toString() {
        return "[chars:" + new String(this.chars, 0, this.end) + ", point:" + this.point + "]";
    }

    public String format() {
        if (!this.formatted) {
            int i = this.chars[0] == '-' ? 1 : 0;
            int i2 = this.point - i;
            if (i2 < -5 || i2 > 21) {
                toExponentialFormat(i, i2);
            } else {
                toFixedFormat(i, i2);
            }
            this.formatted = true;
        }
        return new String(this.chars, 0, this.end);
    }

    private void toFixedFormat(int i, int i2) {
        if (this.point < this.end) {
            if (i2 > 0) {
                System.arraycopy(this.chars, this.point, this.chars, this.point + 1, this.end - this.point);
                this.chars[this.point] = '.';
                this.end++;
                return;
            }
            int i3 = (i + 2) - i2;
            System.arraycopy(this.chars, i, this.chars, i3, this.end - i);
            this.chars[i] = '0';
            this.chars[i + 1] = '.';
            if (i2 < 0) {
                Arrays.fill(this.chars, i + 2, i3, '0');
            }
            this.end += 2 - i2;
        } else if (this.point > this.end) {
            Arrays.fill(this.chars, this.end, this.point, '0');
            this.end += this.point - this.end;
        }
    }

    private void toExponentialFormat(int i, int i2) {
        int i3;
        if (this.end - i > 1) {
            i3 = i + 1;
            System.arraycopy(this.chars, i3, this.chars, i3 + 1, this.end - i3);
            this.chars[i3] = '.';
            this.end++;
        }
        char[] cArr = this.chars;
        int i4 = this.end;
        this.end = i4 + 1;
        cArr[i4] = 'e';
        char c = '+';
        i3 = i2 - 1;
        if (i3 < 0) {
            c = '-';
            i3 = -i3;
        }
        char[] cArr2 = this.chars;
        int i5 = this.end;
        this.end = i5 + 1;
        cArr2[i5] = c;
        i4 = i3 > 99 ? this.end + 2 : i3 > 9 ? this.end + 1 : this.end;
        this.end = i4 + 1;
        int i6 = i3;
        while (true) {
            i3 = i4 - 1;
            this.chars[i4] = digits[i6 % 10];
            i4 = i6 / 10;
            if (i4 != 0) {
                i6 = i4;
                i4 = i3;
            } else {
                return;
            }
        }
    }
}
