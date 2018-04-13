package org.mozilla.javascript.v8dtoa;

public final class DoubleConversion {
    private static final int kDenormalExponent = -1074;
    private static final int kExponentBias = 1075;
    private static final long kExponentMask = 9218868437227405312L;
    private static final long kHiddenBit = 4503599627370496L;
    private static final int kPhysicalSignificandSize = 52;
    private static final long kSignMask = Long.MIN_VALUE;
    private static final long kSignificandMask = 4503599627370495L;
    private static final int kSignificandSize = 53;

    private DoubleConversion() {
    }

    private static int exponent(long j) {
        if (isDenormal(j)) {
            return kDenormalExponent;
        }
        return ((int) ((kExponentMask & j) >> 52)) - 1075;
    }

    private static long significand(long j) {
        long j2 = kSignificandMask & j;
        if (isDenormal(j)) {
            return j2;
        }
        return j2 + kHiddenBit;
    }

    private static boolean isDenormal(long j) {
        return (kExponentMask & j) == 0;
    }

    private static int sign(long j) {
        return (kSignMask & j) == 0 ? 1 : -1;
    }

    public static int doubleToInt32(double d) {
        int i = (int) d;
        if (((double) i) == d) {
            return i;
        }
        long doubleToLongBits = Double.doubleToLongBits(d);
        int exponent = exponent(doubleToLongBits);
        if (exponent <= -53 || exponent > 31) {
            return 0;
        }
        long significand = significand(doubleToLongBits);
        return ((int) (exponent < 0 ? significand >> (-exponent) : significand << exponent)) * sign(doubleToLongBits);
    }
}
