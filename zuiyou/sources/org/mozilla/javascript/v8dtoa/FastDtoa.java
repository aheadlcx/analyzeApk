package org.mozilla.javascript.v8dtoa;

public class FastDtoa {
    static final /* synthetic */ boolean $assertionsDisabled = (!FastDtoa.class.desiredAssertionStatus());
    static final int kFastDtoaMaximalLength = 17;
    static final int kTen4 = 10000;
    static final int kTen5 = 100000;
    static final int kTen6 = 1000000;
    static final int kTen7 = 10000000;
    static final int kTen8 = 100000000;
    static final int kTen9 = 1000000000;
    static final int maximal_target_exponent = -32;
    static final int minimal_target_exponent = -60;

    static boolean roundWeed(FastDtoaBuilder fastDtoaBuilder, long j, long j2, long j3, long j4, long j5) {
        long j6 = j - j5;
        long j7 = j + j5;
        while (j3 < j6 && j2 - j3 >= j4 && (j3 + j4 < j6 || j6 - j3 >= (j3 + j4) - j6)) {
            fastDtoaBuilder.decreaseLast();
            j3 += j4;
        }
        if (j3 >= j7 || j2 - j3 < j4 || (j3 + j4 >= j7 && j7 - j3 <= (j3 + j4) - j7)) {
            return 2 * j5 <= j3 && j3 <= j2 - (4 * j5);
        } else {
            return false;
        }
    }

    static long biggestPowerTen(int i, int i2) {
        int i3 = 1;
        int i4 = 0;
        switch (i2) {
            case 30:
            case 31:
            case 32:
                if (kTen9 <= i) {
                    i4 = kTen9;
                    i3 = 9;
                    break;
                }
            case 27:
            case 28:
            case 29:
                if (kTen8 <= i) {
                    i4 = kTen8;
                    i3 = 8;
                    break;
                }
            case 24:
            case 25:
            case 26:
                if (kTen7 <= i) {
                    i4 = kTen7;
                    i3 = 7;
                    break;
                }
            case 20:
            case 21:
            case 22:
            case 23:
                if (kTen6 <= i) {
                    i4 = kTen6;
                    i3 = 6;
                    break;
                }
            case 17:
            case 18:
            case 19:
                if (kTen5 <= i) {
                    i4 = kTen5;
                    i3 = 5;
                    break;
                }
            case 14:
            case 15:
            case 16:
                if (kTen4 <= i) {
                    i4 = kTen4;
                    i3 = 4;
                    break;
                }
            case 10:
            case 11:
            case 12:
            case 13:
                if (1000 <= i) {
                    i3 = 3;
                    i4 = 1000;
                    break;
                }
            case 7:
            case 8:
            case 9:
                if (100 <= i) {
                    i3 = 2;
                    i4 = 100;
                    break;
                }
            case 4:
            case 5:
            case 6:
                if (10 <= i) {
                    i4 = 10;
                    break;
                }
            case 1:
            case 2:
            case 3:
                if (1 <= i) {
                    i4 = 1;
                    i3 = 0;
                    break;
                }
            case 0:
                i3 = -1;
                break;
            default:
                i3 = 0;
                break;
        }
        return (((long) i3) & 4294967295L) | (((long) i4) << 32);
    }

    private static boolean uint64_lte(long j, long j2) {
        if (j != j2) {
            if (((j2 < 0 ? 1 : 0) ^ ((j < j2 ? 1 : 0) ^ (j < 0 ? 1 : 0))) == 0) {
                return false;
            }
        }
        return true;
    }

    static boolean digitGen(DiyFp diyFp, DiyFp diyFp2, DiyFp diyFp3, FastDtoaBuilder fastDtoaBuilder, int i) {
        if (!$assertionsDisabled && (diyFp.e() != diyFp2.e() || diyFp2.e() != diyFp3.e())) {
            throw new AssertionError();
        } else if (!$assertionsDisabled && !uint64_lte(diyFp.f() + 1, diyFp3.f() - 1)) {
            throw new AssertionError();
        } else if ($assertionsDisabled || (minimal_target_exponent <= diyFp2.e() && diyFp2.e() <= maximal_target_exponent)) {
            long j = 1;
            DiyFp diyFp4 = new DiyFp(diyFp.f() - 1, diyFp.e());
            DiyFp diyFp5 = new DiyFp(diyFp3.f() + 1, diyFp3.e());
            DiyFp minus = DiyFp.minus(diyFp5, diyFp4);
            DiyFp diyFp6 = new DiyFp(1 << (-diyFp2.e()), diyFp2.e());
            int f = (int) ((diyFp5.f() >>> (-diyFp6.e())) & 4294967295L);
            long f2 = (diyFp6.f() - 1) & diyFp5.f();
            long biggestPowerTen = biggestPowerTen(f, 64 - (-diyFp6.e()));
            int i2 = (int) ((biggestPowerTen >>> 32) & 4294967295L);
            int i3 = ((int) (biggestPowerTen & 4294967295L)) + 1;
            while (i3 > 0) {
                fastDtoaBuilder.append((char) ((f / i2) + 48));
                f %= i2;
                i3--;
                biggestPowerTen = (((long) f) << (-diyFp6.e())) + f2;
                if (biggestPowerTen < minus.f()) {
                    fastDtoaBuilder.point = i3 + (fastDtoaBuilder.end - i);
                    return roundWeed(fastDtoaBuilder, DiyFp.minus(diyFp5, diyFp2).f(), minus.f(), biggestPowerTen, ((long) i2) << (-diyFp6.e()), 1);
                }
                i2 /= 10;
            }
            biggestPowerTen = f2;
            do {
                f2 = 5 * biggestPowerTen;
                j *= 5;
                minus.setF(minus.f() * 5);
                minus.setE(minus.e() + 1);
                diyFp6.setF(diyFp6.f() >>> 1);
                diyFp6.setE(diyFp6.e() + 1);
                fastDtoaBuilder.append((char) (((int) ((f2 >>> (-diyFp6.e())) & 4294967295L)) + 48));
                biggestPowerTen = (diyFp6.f() - 1) & f2;
                i3--;
            } while (biggestPowerTen >= minus.f());
            fastDtoaBuilder.point = i3 + (fastDtoaBuilder.end - i);
            return roundWeed(fastDtoaBuilder, DiyFp.minus(diyFp5, diyFp2).f() * j, minus.f(), biggestPowerTen, diyFp6.f(), j);
        } else {
            throw new AssertionError();
        }
    }

    static boolean grisu3(double d, FastDtoaBuilder fastDtoaBuilder) {
        long doubleToLongBits = Double.doubleToLongBits(d);
        DiyFp asNormalizedDiyFp = DoubleHelper.asNormalizedDiyFp(doubleToLongBits);
        DiyFp diyFp = new DiyFp();
        DiyFp diyFp2 = new DiyFp();
        DoubleHelper.normalizedBoundaries(doubleToLongBits, diyFp, diyFp2);
        if ($assertionsDisabled || diyFp2.e() == asNormalizedDiyFp.e()) {
            DiyFp diyFp3 = new DiyFp();
            int cachedPower = CachedPowers.getCachedPower(asNormalizedDiyFp.e() + 64, minimal_target_exponent, maximal_target_exponent, diyFp3);
            if ($assertionsDisabled || (minimal_target_exponent <= (asNormalizedDiyFp.e() + diyFp3.e()) + 64 && maximal_target_exponent >= (asNormalizedDiyFp.e() + diyFp3.e()) + 64)) {
                asNormalizedDiyFp = DiyFp.times(asNormalizedDiyFp, diyFp3);
                if ($assertionsDisabled || asNormalizedDiyFp.e() == (diyFp2.e() + diyFp3.e()) + 64) {
                    return digitGen(DiyFp.times(diyFp, diyFp3), asNormalizedDiyFp, DiyFp.times(diyFp2, diyFp3), fastDtoaBuilder, cachedPower);
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static boolean dtoa(double d, FastDtoaBuilder fastDtoaBuilder) {
        if (!$assertionsDisabled && d <= 0.0d) {
            throw new AssertionError();
        } else if (!$assertionsDisabled && Double.isNaN(d)) {
            throw new AssertionError();
        } else if ($assertionsDisabled || !Double.isInfinite(d)) {
            return grisu3(d, fastDtoaBuilder);
        } else {
            throw new AssertionError();
        }
    }

    public static String numberToString(double d) {
        FastDtoaBuilder fastDtoaBuilder = new FastDtoaBuilder();
        return numberToString(d, fastDtoaBuilder) ? fastDtoaBuilder.format() : null;
    }

    public static boolean numberToString(double d, FastDtoaBuilder fastDtoaBuilder) {
        fastDtoaBuilder.reset();
        if (d < 0.0d) {
            fastDtoaBuilder.append('-');
            d = -d;
        }
        return dtoa(d, fastDtoaBuilder);
    }
}
