package com.qiniu.android.dns.util;

public final class BitSet {
    private int a = 0;

    public BitSet set(int i) {
        this.a |= 1 << i;
        return this;
    }

    public boolean isSet(int i) {
        return (this.a & (1 << i)) != 0;
    }

    public boolean noneIsSet(int i) {
        return this.a == 0;
    }

    public boolean allIsSet(int i) {
        return this.a + 1 == (1 << i);
    }

    public int leadingZeros() {
        int i = 32;
        int i2 = this.a >> 16;
        if (i2 != 0) {
            i = 16;
            this.a = i2;
        }
        i2 = this.a >> 8;
        if (i2 != 0) {
            i -= 8;
            this.a = i2;
        }
        i2 = this.a >> 4;
        if (i2 != 0) {
            i -= 4;
            this.a = i2;
        }
        i2 = this.a >> 2;
        if (i2 != 0) {
            i -= 2;
            this.a = i2;
        }
        if ((this.a >> 1) != 0) {
            return i - 2;
        }
        return i - this.a;
    }

    public BitSet clear() {
        this.a = 0;
        return this;
    }

    public int value() {
        return this.a;
    }
}
