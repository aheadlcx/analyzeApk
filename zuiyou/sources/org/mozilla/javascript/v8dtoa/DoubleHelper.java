package org.mozilla.javascript.v8dtoa;

public class DoubleHelper {
    static final /* synthetic */ boolean $assertionsDisabled = (!DoubleHelper.class.desiredAssertionStatus());
    private static final int kDenormalExponent = -1074;
    private static final int kExponentBias = 1075;
    static final long kExponentMask = 9218868437227405312L;
    static final long kHiddenBit = 4503599627370496L;
    static final long kSignMask = Long.MIN_VALUE;
    static final long kSignificandMask = 4503599627370495L;
    private static final int kSignificandSize = 52;

    static DiyFp asDiyFp(long j) {
        if ($assertionsDisabled || !isSpecial(j)) {
            return new DiyFp(significand(j), exponent(j));
        }
        throw new AssertionError();
    }

    static DiyFp asNormalizedDiyFp(long j) {
        long significand = significand(j);
        int exponent = exponent(j);
        if ($assertionsDisabled || significand != 0) {
            while ((kHiddenBit & significand) == 0) {
                significand <<= 1;
                exponent--;
            }
            return new DiyFp(significand << 11, exponent - 11);
        }
        throw new AssertionError();
    }

    static int exponent(long j) {
        if (isDenormal(j)) {
            return kDenormalExponent;
        }
        return ((int) (((kExponentMask & j) >>> 52) & 4294967295L)) - 1075;
    }

    static long significand(long j) {
        long j2 = kSignificandMask & j;
        if (isDenormal(j)) {
            return j2;
        }
        return j2 + kHiddenBit;
    }

    static boolean isDenormal(long j) {
        return (kExponentMask & j) == 0;
    }

    static boolean isSpecial(long j) {
        return (j & kExponentMask) == kExponentMask;
    }

    static boolean isNan(long j) {
        return (j & kExponentMask) == kExponentMask && (kSignificandMask & j) != 0;
    }

    static boolean isInfinite(long j) {
        return (j & kExponentMask) == kExponentMask && (kSignificandMask & j) == 0;
    }

    static int sign(long j) {
        return (kSignMask & j) == 0 ? 1 : -1;
    }

    static void normalizedBoundaries(long j, DiyFp diyFp, DiyFp diyFp2) {
        DiyFp asDiyFp = asDiyFp(j);
        Object obj = asDiyFp.f() == kHiddenBit ? 1 : null;
        diyFp2.setF((asDiyFp.f() << 1) + 1);
        diyFp2.setE(asDiyFp.e() - 1);
        diyFp2.normalize();
        if (obj == null || asDiyFp.e() == kDenormalExponent) {
            diyFp.setF((asDiyFp.f() << 1) - 1);
            diyFp.setE(asDiyFp.e() - 1);
        } else {
            diyFp.setF((asDiyFp.f() << 2) - 1);
            diyFp.setE(asDiyFp.e() - 2);
        }
        diyFp.setF(diyFp.f() << (diyFp.e() - diyFp2.e()));
        diyFp.setE(diyFp2.e());
    }
}
