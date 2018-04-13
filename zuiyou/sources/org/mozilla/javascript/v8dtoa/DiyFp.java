package org.mozilla.javascript.v8dtoa;

import tv.danmaku.ijk.media.player.IjkMediaMeta;

class DiyFp {
    static final /* synthetic */ boolean $assertionsDisabled = (!DiyFp.class.desiredAssertionStatus());
    static final int kSignificandSize = 64;
    static final long kUint64MSB = Long.MIN_VALUE;
    private int e;
    private long f;

    DiyFp() {
        this.f = 0;
        this.e = 0;
    }

    DiyFp(long j, int i) {
        this.f = j;
        this.e = i;
    }

    private static boolean uint64_gte(long j, long j2) {
        if (j != j2) {
            if (((j2 < 0 ? 1 : 0) ^ ((j > j2 ? 1 : 0) ^ (j < 0 ? 1 : 0))) == 0) {
                return false;
            }
        }
        return true;
    }

    void subtract(DiyFp diyFp) {
        if (!$assertionsDisabled && this.e != diyFp.e) {
            throw new AssertionError();
        } else if ($assertionsDisabled || uint64_gte(this.f, diyFp.f)) {
            this.f -= diyFp.f;
        } else {
            throw new AssertionError();
        }
    }

    static DiyFp minus(DiyFp diyFp, DiyFp diyFp2) {
        DiyFp diyFp3 = new DiyFp(diyFp.f, diyFp.e);
        diyFp3.subtract(diyFp2);
        return diyFp3;
    }

    void multiply(DiyFp diyFp) {
        long j = this.f >>> 32;
        long j2 = this.f & 4294967295L;
        long j3 = diyFp.f >>> 32;
        long j4 = diyFp.f & 4294967295L;
        long j5 = j * j3;
        j3 *= j2;
        j *= j4;
        j = (((j >>> 32) + j5) + (j3 >>> 32)) + ((((((j2 * j4) >>> 32) + (j & 4294967295L)) + (j3 & 4294967295L)) + IjkMediaMeta.AV_CH_WIDE_LEFT) >>> 32);
        this.e += diyFp.e + 64;
        this.f = j;
    }

    static DiyFp times(DiyFp diyFp, DiyFp diyFp2) {
        DiyFp diyFp3 = new DiyFp(diyFp.f, diyFp.e);
        diyFp3.multiply(diyFp2);
        return diyFp3;
    }

    void normalize() {
        if ($assertionsDisabled || this.f != 0) {
            long j = this.f;
            int i = this.e;
            while ((-18014398509481984L & j) == 0) {
                j <<= 10;
                i -= 10;
            }
            while ((kUint64MSB & j) == 0) {
                j <<= 1;
                i--;
            }
            this.f = j;
            this.e = i;
            return;
        }
        throw new AssertionError();
    }

    static DiyFp normalize(DiyFp diyFp) {
        DiyFp diyFp2 = new DiyFp(diyFp.f, diyFp.e);
        diyFp2.normalize();
        return diyFp2;
    }

    long f() {
        return this.f;
    }

    int e() {
        return this.e;
    }

    void setF(long j) {
        this.f = j;
    }

    void setE(int i) {
        this.e = i;
    }

    public String toString() {
        return "[DiyFp f:" + this.f + ", e:" + this.e + "]";
    }
}
