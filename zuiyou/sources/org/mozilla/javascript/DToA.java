package org.mozilla.javascript;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import com.tencent.tinker.loader.shareutil.ShareElfFile.SectionHeader;
import java.math.BigInteger;

class DToA {
    private static final int Bias = 1023;
    private static final int Bletch = 16;
    private static final int Bndry_mask = 1048575;
    static final int DTOSTR_EXPONENTIAL = 3;
    static final int DTOSTR_FIXED = 2;
    static final int DTOSTR_PRECISION = 4;
    static final int DTOSTR_STANDARD = 0;
    static final int DTOSTR_STANDARD_EXPONENTIAL = 1;
    private static final int Exp_11 = 1072693248;
    private static final int Exp_mask = 2146435072;
    private static final int Exp_mask_shifted = 2047;
    private static final int Exp_msk1 = 1048576;
    private static final long Exp_msk1L = 4503599627370496L;
    private static final int Exp_shift = 20;
    private static final int Exp_shift1 = 20;
    private static final int Exp_shiftL = 52;
    private static final int Frac_mask = 1048575;
    private static final int Frac_mask1 = 1048575;
    private static final long Frac_maskL = 4503599627370495L;
    private static final int Int_max = 14;
    private static final int Log2P = 1;
    private static final int P = 53;
    private static final int Quick_max = 14;
    private static final int Sign_bit = Integer.MIN_VALUE;
    private static final int Ten_pmax = 22;
    private static final double[] bigtens = new double[]{1.0E16d, 1.0E32d, 1.0E64d, 1.0E128d, 1.0E256d};
    private static final int[] dtoaModes = new int[]{0, 0, 3, 2, 2};
    private static final int n_bigtens = 5;
    private static final double[] tens = new double[]{1.0d, 10.0d, 100.0d, 1000.0d, 10000.0d, 100000.0d, 1000000.0d, 1.0E7d, 1.0E8d, 1.0E9d, 1.0E10d, 1.0E11d, 1.0E12d, 1.0E13d, 1.0E14d, 1.0E15d, 1.0E16d, 1.0E17d, 1.0E18d, 1.0E19d, 1.0E20d, 1.0E21d, 1.0E22d};

    DToA() {
    }

    private static char BASEDIGIT(int i) {
        return (char) (i >= 10 ? i + 87 : i + 48);
    }

    private static int lo0bits(int i) {
        if ((i & 7) == 0) {
            int i2;
            int i3;
            if ((65535 & i) == 0) {
                i2 = 16;
                i3 = i >>> 16;
            } else {
                i2 = 0;
                i3 = i;
            }
            if ((i3 & 255) == 0) {
                i2 += 8;
                i3 >>>= 8;
            }
            if ((i3 & 15) == 0) {
                i2 += 4;
                i3 >>>= 4;
            }
            int i4;
            if ((i3 & 3) == 0) {
                i4 = i3 >>> 2;
                i3 = i2 + 2;
                i2 = i4;
            } else {
                i4 = i3;
                i3 = i2;
                i2 = i4;
            }
            if ((i2 & 1) != 0) {
                return i3;
            }
            i3++;
            if (((i2 >>> 1) & 1) == 0) {
                return 32;
            }
            return i3;
        } else if ((i & 1) != 0) {
            return 0;
        } else {
            if ((i & 2) != 0) {
                return 1;
            }
            return 2;
        }
    }

    private static int hi0bits(int i) {
        int i2;
        int i3 = 0;
        if ((SupportMenu.CATEGORY_MASK & i) == 0) {
            i3 = 16;
            i2 = i << 16;
        } else {
            i2 = i;
        }
        if ((ViewCompat.MEASURED_STATE_MASK & i2) == 0) {
            i3 += 8;
            i2 <<= 8;
        }
        if ((SectionHeader.SHF_MASKPROC & i2) == 0) {
            i3 += 4;
            i2 <<= 4;
        }
        if ((-1073741824 & i2) == 0) {
            i3 += 2;
            i2 <<= 2;
        }
        if ((Integer.MIN_VALUE & i2) != 0) {
            return i3;
        }
        i3++;
        if ((i2 & 1073741824) == 0) {
            return 32;
        }
        return i3;
    }

    private static void stuffBits(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 >> 24);
        bArr[i + 1] = (byte) (i2 >> 16);
        bArr[i + 2] = (byte) (i2 >> 8);
        bArr[i + 3] = (byte) i2;
    }

    private static BigInteger d2b(double d, int[] iArr, int[] iArr2) {
        byte[] bArr;
        int lo0bits;
        int i = 1;
        long doubleToLongBits = Double.doubleToLongBits(d);
        int i2 = (int) (doubleToLongBits >>> 32);
        int i3 = (int) doubleToLongBits;
        int i4 = 1048575 & i2;
        i2 = (Integer.MAX_VALUE & i2) >>> 20;
        if (i2 != 0) {
            i4 |= 1048576;
        }
        int i5;
        if (i3 != 0) {
            bArr = new byte[8];
            lo0bits = lo0bits(i3);
            i3 >>>= lo0bits;
            if (lo0bits != 0) {
                stuffBits(bArr, 4, i3 | (i4 << (32 - lo0bits)));
                i4 >>= lo0bits;
            } else {
                stuffBits(bArr, 4, i3);
            }
            stuffBits(bArr, 0, i4);
            if (i4 != 0) {
                i = 2;
            }
            i5 = lo0bits;
            lo0bits = i;
            i = i5;
        } else {
            bArr = new byte[4];
            lo0bits = lo0bits(i4);
            i4 >>>= lo0bits;
            stuffBits(bArr, 0, i4);
            i5 = lo0bits + 32;
            lo0bits = 1;
            i = i5;
        }
        if (i2 != 0) {
            iArr[0] = ((i2 - 1023) - 52) + i;
            iArr2[0] = 53 - i;
        } else {
            iArr[0] = i + (((i2 - 1023) - 52) + 1);
            iArr2[0] = (lo0bits * 32) - hi0bits(i4);
        }
        return new BigInteger(bArr);
    }

    static String JS_dtobasestr(int i, double d) {
        if (2 > i || i > 36) {
            throw new IllegalArgumentException("Bad base: " + i);
        } else if (Double.isNaN(d)) {
            return "NaN";
        } else {
            if (Double.isInfinite(d)) {
                return d > 0.0d ? "Infinity" : "-Infinity";
            } else {
                if (d == 0.0d) {
                    return "0";
                }
                Object obj;
                String l;
                int i2;
                BigInteger valueOf;
                if (d >= 0.0d) {
                    obj = null;
                } else {
                    obj = 1;
                    d = -d;
                }
                double floor = Math.floor(d);
                long j = (long) floor;
                long j2;
                if (((double) j) == floor) {
                    if (obj != null) {
                        j2 = -j;
                    } else {
                        j2 = j;
                    }
                    l = Long.toString(j2, i);
                } else {
                    j = Double.doubleToLongBits(floor);
                    int i3 = ((int) (j >> 52)) & Exp_mask_shifted;
                    if (i3 == 0) {
                        j = (j & Frac_maskL) << 1;
                    } else {
                        j = (j & Frac_maskL) | Exp_msk1L;
                    }
                    if (obj != null) {
                        j2 = -j;
                    } else {
                        j2 = j;
                    }
                    i2 = i3 - 1075;
                    valueOf = BigInteger.valueOf(j2);
                    if (i2 > 0) {
                        valueOf = valueOf.shiftLeft(i2);
                    } else if (i2 < 0) {
                        valueOf = valueOf.shiftRight(-i2);
                    }
                    l = valueOf.toString(i);
                }
                if (d == floor) {
                    return l;
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(l).append('.');
                double d2 = d - floor;
                j = Double.doubleToLongBits(d);
                int i4 = (int) (j >> 32);
                int i5 = (int) j;
                int[] iArr = new int[1];
                BigInteger d2b = d2b(d2, iArr, new int[1]);
                int i6 = -((i4 >>> 20) & Exp_mask_shifted);
                if (i6 == 0) {
                    i6 = -1;
                }
                i6 += 1076;
                BigInteger valueOf2 = BigInteger.valueOf(1);
                if (i5 == 0 && (1048575 & i4) == 0 && (2145386496 & i4) != 0) {
                    i2 = i6 + 1;
                    valueOf = BigInteger.valueOf(2);
                } else {
                    i2 = i6;
                    valueOf = valueOf2;
                }
                BigInteger shiftLeft = d2b.shiftLeft(iArr[0] + i2);
                BigInteger shiftLeft2 = BigInteger.valueOf(1).shiftLeft(i2);
                BigInteger valueOf3 = BigInteger.valueOf((long) i);
                BigInteger bigInteger = valueOf2;
                valueOf2 = valueOf;
                obj = null;
                do {
                    BigInteger[] divideAndRemainder = shiftLeft.multiply(valueOf3).divideAndRemainder(shiftLeft2);
                    BigInteger bigInteger2 = divideAndRemainder[1];
                    char intValue = (char) divideAndRemainder[0].intValue();
                    if (bigInteger == valueOf2) {
                        valueOf2 = bigInteger.multiply(valueOf3);
                        bigInteger = valueOf2;
                    } else {
                        bigInteger = bigInteger.multiply(valueOf3);
                        valueOf2 = valueOf2.multiply(valueOf3);
                    }
                    int compareTo = bigInteger2.compareTo(bigInteger);
                    d2b = shiftLeft2.subtract(valueOf2);
                    int compareTo2 = d2b.signum() <= 0 ? 1 : bigInteger2.compareTo(d2b);
                    if (compareTo2 == 0 && (i5 & 1) == 0) {
                        if (compareTo > 0) {
                            i6 = intValue + 1;
                        } else {
                            char c = intValue;
                        }
                        shiftLeft = bigInteger2;
                        i4 = i6;
                        obj = 1;
                    } else if (compareTo < 0 || (compareTo == 0 && (i5 & 1) == 0)) {
                        if (compareTo2 > 0) {
                            valueOf = bigInteger2.shiftLeft(1);
                            if (valueOf.compareTo(shiftLeft2) > 0) {
                                intValue++;
                            }
                        } else {
                            valueOf = bigInteger2;
                        }
                        r4 = intValue;
                        shiftLeft = valueOf;
                        i6 = 1;
                    } else if (compareTo2 > 0) {
                        obj = 1;
                        r12 = bigInteger2;
                        i4 = intValue + 1;
                        shiftLeft = r12;
                    } else {
                        r12 = bigInteger2;
                        r4 = intValue;
                        shiftLeft = r12;
                    }
                    stringBuilder.append(BASEDIGIT(i4));
                } while (obj == null);
                return stringBuilder.toString();
            }
        }
    }

    static int word0(double d) {
        return (int) (Double.doubleToLongBits(d) >> 32);
    }

    static double setWord0(double d, int i) {
        return Double.longBitsToDouble((Double.doubleToLongBits(d) & 4294967295L) | (((long) i) << 32));
    }

    static int word1(double d) {
        return (int) Double.doubleToLongBits(d);
    }

    static BigInteger pow5mult(BigInteger bigInteger, int i) {
        return bigInteger.multiply(BigInteger.valueOf(5).pow(i));
    }

    static boolean roundOff(StringBuilder stringBuilder) {
        int length = stringBuilder.length();
        while (length != 0) {
            length--;
            char charAt = stringBuilder.charAt(length);
            if (charAt != '9') {
                stringBuilder.setCharAt(length, (char) (charAt + 1));
                stringBuilder.setLength(length + 1);
                return false;
            }
        }
        stringBuilder.setLength(0);
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int JS_dtoa(double r34, int r36, boolean r37, int r38, boolean[] r39, java.lang.StringBuilder r40) {
        /*
        r4 = 1;
        r0 = new int[r4];
        r25 = r0;
        r4 = 1;
        r0 = new int[r4];
        r26 = r0;
        r4 = word0(r34);
        r5 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r4 = r4 & r5;
        if (r4 == 0) goto L_0x004b;
    L_0x0013:
        r4 = 0;
        r5 = 1;
        r39[r4] = r5;
        r4 = word0(r34);
        r5 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r4 = r4 & r5;
        r0 = r34;
        r34 = setWord0(r0, r4);
    L_0x0025:
        r4 = word0(r34);
        r5 = 2146435072; // 0x7ff00000 float:NaN double:1.06047983E-314;
        r4 = r4 & r5;
        r5 = 2146435072; // 0x7ff00000 float:NaN double:1.06047983E-314;
        if (r4 != r5) goto L_0x0054;
    L_0x0030:
        r4 = word1(r34);
        if (r4 != 0) goto L_0x0050;
    L_0x0036:
        r4 = word0(r34);
        r5 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r4 = r4 & r5;
        if (r4 != 0) goto L_0x0050;
    L_0x0040:
        r4 = "Infinity";
    L_0x0043:
        r0 = r40;
        r0.append(r4);
        r4 = 9999; // 0x270f float:1.4012E-41 double:4.94E-320;
    L_0x004a:
        return r4;
    L_0x004b:
        r4 = 0;
        r5 = 0;
        r39[r4] = r5;
        goto L_0x0025;
    L_0x0050:
        r4 = "NaN";
        goto L_0x0043;
    L_0x0054:
        r4 = 0;
        r4 = (r34 > r4 ? 1 : (r34 == r4 ? 0 : -1));
        if (r4 != 0) goto L_0x0069;
    L_0x005a:
        r4 = 0;
        r0 = r40;
        r0.setLength(r4);
        r4 = 48;
        r0 = r40;
        r0.append(r4);
        r4 = 1;
        goto L_0x004a;
    L_0x0069:
        r0 = r34;
        r2 = r25;
        r3 = r26;
        r20 = d2b(r0, r2, r3);
        r4 = word0(r34);
        r4 = r4 >>> 20;
        r4 = r4 & 2047;
        if (r4 == 0) goto L_0x013e;
    L_0x007d:
        r5 = word0(r34);
        r6 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r5 = r5 & r6;
        r6 = 1072693248; // 0x3ff00000 float:1.875 double:5.299808824E-315;
        r5 = r5 | r6;
        r0 = r34;
        r6 = setWord0(r0, r5);
        r5 = r4 + -1023;
        r4 = 0;
        r24 = r4;
        r32 = r6;
        r6 = r5;
        r4 = r32;
    L_0x0098:
        r8 = 4609434218613702656; // 0x3ff8000000000000 float:0.0 double:1.5;
        r4 = r4 - r8;
        r8 = 4598887322496222049; // 0x3fd287a7636f4361 float:4.413627E21 double:0.289529654602168;
        r4 = r4 * r8;
        r8 = 4595512376519870643; // 0x3fc68a288b60c8b3 float:-4.329182E-32 double:0.1760912590558;
        r4 = r4 + r8;
        r8 = (double) r6;
        r10 = 4599094494223104507; // 0x3fd34413509f79fb float:2.14045716E10 double:0.301029995663981;
        r8 = r8 * r10;
        r8 = r8 + r4;
        r4 = (int) r8;
        r10 = 0;
        r5 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r5 >= 0) goto L_0x00bd;
    L_0x00b6:
        r10 = (double) r4;
        r5 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r5 == 0) goto L_0x00bd;
    L_0x00bb:
        r4 = r4 + -1;
    L_0x00bd:
        r5 = 1;
        if (r4 < 0) goto L_0x074c;
    L_0x00c0:
        r7 = 22;
        if (r4 > r7) goto L_0x074c;
    L_0x00c4:
        r5 = tens;
        r8 = r5[r4];
        r5 = (r34 > r8 ? 1 : (r34 == r8 ? 0 : -1));
        if (r5 >= 0) goto L_0x00ce;
    L_0x00cc:
        r4 = r4 + -1;
    L_0x00ce:
        r5 = 0;
        r32 = r5;
        r5 = r4;
        r4 = r32;
    L_0x00d4:
        r7 = 0;
        r7 = r26[r7];
        r6 = r7 - r6;
        r21 = r6 + -1;
        if (r21 < 0) goto L_0x017e;
    L_0x00dd:
        r19 = 0;
    L_0x00df:
        if (r5 < 0) goto L_0x0187;
    L_0x00e1:
        r18 = 0;
        r21 = r21 + r5;
        r13 = r5;
    L_0x00e6:
        if (r36 < 0) goto L_0x00ee;
    L_0x00e8:
        r6 = 9;
        r0 = r36;
        if (r0 <= r6) goto L_0x0748;
    L_0x00ee:
        r7 = 0;
    L_0x00ef:
        r6 = 1;
        r8 = 5;
        if (r7 <= r8) goto L_0x0743;
    L_0x00f3:
        r7 = r7 + -4;
        r6 = 0;
        r23 = r7;
        r7 = r6;
    L_0x00f9:
        r6 = 1;
        r12 = 0;
        switch(r23) {
            case 0: goto L_0x018f;
            case 1: goto L_0x018f;
            case 2: goto L_0x0197;
            case 3: goto L_0x01a4;
            case 4: goto L_0x0198;
            case 5: goto L_0x01a5;
            default: goto L_0x00fe;
        };
    L_0x00fe:
        r22 = r6;
        r11 = r12;
    L_0x0101:
        r10 = 0;
        if (r11 < 0) goto L_0x02c1;
    L_0x0104:
        r6 = 14;
        if (r11 > r6) goto L_0x02c1;
    L_0x0108:
        if (r7 == 0) goto L_0x02c1;
    L_0x010a:
        r8 = 0;
        r7 = 2;
        if (r5 <= 0) goto L_0x01f6;
    L_0x010e:
        r6 = tens;
        r9 = r5 & 15;
        r14 = r6[r9];
        r6 = r5 >> 4;
        r9 = r6 & 16;
        if (r9 == 0) goto L_0x0734;
    L_0x011a:
        r6 = r6 & 15;
        r7 = bigtens;
        r9 = 4;
        r16 = r7[r9];
        r16 = r34 / r16;
        r7 = 3;
        r9 = r6;
        r32 = r14;
        r14 = r8;
        r8 = r7;
        r6 = r32;
    L_0x012b:
        if (r9 == 0) goto L_0x01b1;
    L_0x012d:
        r15 = r9 & 1;
        if (r15 == 0) goto L_0x0139;
    L_0x0131:
        r8 = r8 + 1;
        r15 = bigtens;
        r28 = r15[r14];
        r6 = r6 * r28;
    L_0x0139:
        r9 = r9 >> 1;
        r14 = r14 + 1;
        goto L_0x012b;
    L_0x013e:
        r4 = 0;
        r4 = r26[r4];
        r5 = 0;
        r5 = r25[r5];
        r4 = r4 + r5;
        r8 = r4 + 1074;
        r4 = 32;
        if (r8 <= r4) goto L_0x0175;
    L_0x014b:
        r4 = word0(r34);
        r4 = (long) r4;
        r6 = 64 - r8;
        r4 = r4 << r6;
        r6 = word1(r34);
        r7 = r8 + -32;
        r6 = r6 >>> r7;
        r6 = (long) r6;
        r4 = r4 | r6;
    L_0x015c:
        r6 = (double) r4;
        r4 = (double) r4;
        r4 = word0(r4);
        r5 = 32505856; // 0x1f00000 float:8.8162076E-38 double:1.60600267E-316;
        r4 = r4 - r5;
        r6 = setWord0(r6, r4);
        r5 = r8 + -1075;
        r4 = 1;
        r24 = r4;
        r32 = r6;
        r6 = r5;
        r4 = r32;
        goto L_0x0098;
    L_0x0175:
        r4 = word1(r34);
        r4 = (long) r4;
        r6 = 32 - r8;
        r4 = r4 << r6;
        goto L_0x015c;
    L_0x017e:
        r0 = r21;
        r0 = -r0;
        r19 = r0;
        r21 = 0;
        goto L_0x00df;
    L_0x0187:
        r19 = r19 - r5;
        r0 = -r5;
        r18 = r0;
        r13 = 0;
        goto L_0x00e6;
    L_0x018f:
        r12 = -1;
        r38 = 0;
        r22 = r6;
        r11 = r12;
        goto L_0x0101;
    L_0x0197:
        r6 = 0;
    L_0x0198:
        if (r38 > 0) goto L_0x019c;
    L_0x019a:
        r38 = 1;
    L_0x019c:
        r22 = r6;
        r12 = r38;
        r11 = r38;
        goto L_0x0101;
    L_0x01a4:
        r6 = 0;
    L_0x01a5:
        r8 = r38 + r5;
        r11 = r8 + 1;
        r12 = r11 + -1;
        if (r11 > 0) goto L_0x073f;
    L_0x01ad:
        r22 = r6;
        goto L_0x0101;
    L_0x01b1:
        r6 = r16 / r6;
        r32 = r8;
        r8 = r6;
        r7 = r32;
    L_0x01b8:
        if (r4 == 0) goto L_0x0726;
    L_0x01ba:
        r14 = 4607182418800017408; // 0x3ff0000000000000 float:0.0 double:1.0;
        r6 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1));
        if (r6 >= 0) goto L_0x0726;
    L_0x01c0:
        if (r11 <= 0) goto L_0x0726;
    L_0x01c2:
        if (r12 > 0) goto L_0x0220;
    L_0x01c4:
        r6 = 1;
        r14 = r8;
        r8 = r11;
        r9 = r7;
        r7 = r5;
    L_0x01c9:
        r0 = (double) r9;
        r16 = r0;
        r16 = r16 * r14;
        r28 = 4619567317775286272; // 0x401c000000000000 float:0.0 double:7.0;
        r16 = r16 + r28;
        r9 = word0(r16);
        r10 = 54525952; // 0x3400000 float:5.642373E-37 double:2.69393997E-316;
        r9 = r9 - r10;
        r0 = r16;
        r28 = setWord0(r0, r9);
        if (r8 != 0) goto L_0x0722;
    L_0x01e1:
        r16 = 4617315517961601024; // 0x4014000000000000 float:0.0 double:5.0;
        r16 = r14 - r16;
        r6 = (r16 > r28 ? 1 : (r16 == r28 ? 0 : -1));
        if (r6 <= 0) goto L_0x022d;
    L_0x01e9:
        r4 = 49;
        r0 = r40;
        r0.append(r4);
        r4 = r7 + 1;
        r4 = r4 + 1;
        goto L_0x004a;
    L_0x01f6:
        r6 = -r5;
        if (r6 == 0) goto L_0x0730;
    L_0x01f9:
        r9 = tens;
        r14 = r6 & 15;
        r14 = r9[r14];
        r14 = r14 * r34;
        r6 = r6 >> 4;
        r32 = r6;
        r6 = r7;
        r7 = r32;
        r33 = r8;
        r8 = r14;
        r14 = r33;
    L_0x020d:
        if (r7 == 0) goto L_0x072d;
    L_0x020f:
        r15 = r7 & 1;
        if (r15 == 0) goto L_0x021b;
    L_0x0213:
        r6 = r6 + 1;
        r15 = bigtens;
        r16 = r15[r14];
        r8 = r8 * r16;
    L_0x021b:
        r7 = r7 >> 1;
        r14 = r14 + 1;
        goto L_0x020d;
    L_0x0220:
        r6 = r5 + -1;
        r14 = 4621819117588971520; // 0x4024000000000000 float:0.0 double:10.0;
        r8 = r8 * r14;
        r7 = r7 + 1;
        r14 = r8;
        r8 = r12;
        r9 = r7;
        r7 = r6;
        r6 = r10;
        goto L_0x01c9;
    L_0x022d:
        r0 = r28;
        r14 = -r0;
        r6 = (r16 > r14 ? 1 : (r16 == r14 ? 0 : -1));
        if (r6 >= 0) goto L_0x0244;
    L_0x0234:
        r4 = 0;
        r0 = r40;
        r0.setLength(r4);
        r4 = 48;
        r0 = r40;
        r0.append(r4);
        r4 = 1;
        goto L_0x004a;
    L_0x0244:
        r6 = 1;
    L_0x0245:
        if (r6 != 0) goto L_0x071e;
    L_0x0247:
        r9 = 1;
        if (r22 == 0) goto L_0x02fe;
    L_0x024a:
        r14 = 4602678819172646912; // 0x3fe0000000000000 float:0.0 double:0.5;
        r6 = tens;
        r10 = r8 + -1;
        r30 = r6[r10];
        r14 = r14 / r30;
        r14 = r14 - r28;
        r6 = 0;
    L_0x0257:
        r0 = r16;
        r0 = (long) r0;
        r28 = r0;
        r0 = r28;
        r0 = (double) r0;
        r30 = r0;
        r16 = r16 - r30;
        r30 = 48;
        r28 = r28 + r30;
        r0 = r28;
        r10 = (int) r0;
        r10 = (char) r10;
        r0 = r40;
        r0.append(r10);
        r10 = (r16 > r14 ? 1 : (r16 == r14 ? 0 : -1));
        if (r10 >= 0) goto L_0x0278;
    L_0x0274:
        r4 = r7 + 1;
        goto L_0x004a;
    L_0x0278:
        r28 = 4607182418800017408; // 0x3ff0000000000000 float:0.0 double:1.0;
        r28 = r28 - r16;
        r10 = (r28 > r14 ? 1 : (r28 == r14 ? 0 : -1));
        if (r10 >= 0) goto L_0x02b2;
    L_0x0280:
        r4 = r40.length();
        r4 = r4 + -1;
        r0 = r40;
        r4 = r0.charAt(r4);
        r5 = r40.length();
        r5 = r5 + -1;
        r0 = r40;
        r0.setLength(r5);
        r5 = 57;
        if (r4 == r5) goto L_0x02a7;
    L_0x029b:
        r4 = r4 + 1;
        r4 = (char) r4;
        r0 = r40;
        r0.append(r4);
        r4 = r7 + 1;
        goto L_0x004a;
    L_0x02a7:
        r4 = r40.length();
        if (r4 != 0) goto L_0x0280;
    L_0x02ad:
        r7 = r7 + 1;
        r4 = 48;
        goto L_0x029b;
    L_0x02b2:
        r6 = r6 + 1;
        if (r6 < r8) goto L_0x02f4;
    L_0x02b6:
        r6 = r9;
        r14 = r16;
    L_0x02b9:
        if (r6 == 0) goto L_0x0715;
    L_0x02bb:
        r6 = 0;
        r0 = r40;
        r0.setLength(r6);
    L_0x02c1:
        r6 = 0;
        r6 = r25[r6];
        if (r6 < 0) goto L_0x03e5;
    L_0x02c6:
        r6 = 14;
        if (r5 > r6) goto L_0x03e5;
    L_0x02ca:
        r4 = tens;
        r6 = r4[r5];
        if (r38 >= 0) goto L_0x037d;
    L_0x02d0:
        if (r11 > 0) goto L_0x037d;
    L_0x02d2:
        if (r11 < 0) goto L_0x02e4;
    L_0x02d4:
        r8 = 4617315517961601024; // 0x4014000000000000 float:0.0 double:5.0;
        r8 = r8 * r6;
        r4 = (r34 > r8 ? 1 : (r34 == r8 ? 0 : -1));
        if (r4 < 0) goto L_0x02e4;
    L_0x02db:
        if (r37 != 0) goto L_0x0370;
    L_0x02dd:
        r8 = 4617315517961601024; // 0x4014000000000000 float:0.0 double:5.0;
        r6 = r6 * r8;
        r4 = (r34 > r6 ? 1 : (r34 == r6 ? 0 : -1));
        if (r4 != 0) goto L_0x0370;
    L_0x02e4:
        r4 = 0;
        r0 = r40;
        r0.setLength(r4);
        r4 = 48;
        r0 = r40;
        r0.append(r4);
        r4 = 1;
        goto L_0x004a;
    L_0x02f4:
        r28 = 4621819117588971520; // 0x4024000000000000 float:0.0 double:10.0;
        r14 = r14 * r28;
        r28 = 4621819117588971520; // 0x4024000000000000 float:0.0 double:10.0;
        r16 = r16 * r28;
        goto L_0x0257;
    L_0x02fe:
        r6 = tens;
        r10 = r8 + -1;
        r14 = r6[r10];
        r28 = r28 * r14;
        r6 = 1;
    L_0x0307:
        r0 = r16;
        r0 = (long) r0;
        r30 = r0;
        r0 = r30;
        r14 = (double) r0;
        r14 = r16 - r14;
        r16 = 48;
        r16 = r16 + r30;
        r0 = r16;
        r10 = (int) r0;
        r10 = (char) r10;
        r0 = r40;
        r0.append(r10);
        if (r6 != r8) goto L_0x0369;
    L_0x0320:
        r16 = 4602678819172646912; // 0x3fe0000000000000 float:0.0 double:0.5;
        r16 = r16 + r28;
        r6 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1));
        if (r6 <= 0) goto L_0x035a;
    L_0x0328:
        r4 = r40.length();
        r4 = r4 + -1;
        r0 = r40;
        r4 = r0.charAt(r4);
        r5 = r40.length();
        r5 = r5 + -1;
        r0 = r40;
        r0.setLength(r5);
        r5 = 57;
        if (r4 == r5) goto L_0x034f;
    L_0x0343:
        r4 = r4 + 1;
        r4 = (char) r4;
        r0 = r40;
        r0.append(r4);
        r4 = r7 + 1;
        goto L_0x004a;
    L_0x034f:
        r4 = r40.length();
        if (r4 != 0) goto L_0x0328;
    L_0x0355:
        r7 = r7 + 1;
        r4 = 48;
        goto L_0x0343;
    L_0x035a:
        r16 = 4602678819172646912; // 0x3fe0000000000000 float:0.0 double:0.5;
        r16 = r16 - r28;
        r6 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1));
        if (r6 >= 0) goto L_0x071b;
    L_0x0362:
        stripTrailingZeroes(r40);
        r4 = r7 + 1;
        goto L_0x004a;
    L_0x0369:
        r6 = r6 + 1;
        r16 = 4621819117588971520; // 0x4024000000000000 float:0.0 double:10.0;
        r16 = r16 * r14;
        goto L_0x0307;
    L_0x0370:
        r4 = 49;
        r0 = r40;
        r0.append(r4);
        r4 = r5 + 1;
        r4 = r4 + 1;
        goto L_0x004a;
    L_0x037d:
        r4 = 1;
    L_0x037e:
        r8 = r34 / r6;
        r8 = (long) r8;
        r12 = (double) r8;
        r12 = r12 * r6;
        r12 = r34 - r12;
        r14 = 48;
        r14 = r14 + r8;
        r10 = (int) r14;
        r10 = (char) r10;
        r0 = r40;
        r0.append(r10);
        if (r4 != r11) goto L_0x03d8;
    L_0x0391:
        r10 = r12 + r12;
        r4 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1));
        if (r4 > 0) goto L_0x03a6;
    L_0x0397:
        r4 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1));
        if (r4 != 0) goto L_0x03c9;
    L_0x039b:
        r6 = 1;
        r6 = r6 & r8;
        r8 = 0;
        r4 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r4 != 0) goto L_0x03a6;
    L_0x03a4:
        if (r37 == 0) goto L_0x03c9;
    L_0x03a6:
        r4 = r40.length();
        r4 = r4 + -1;
        r0 = r40;
        r4 = r0.charAt(r4);
        r6 = r40.length();
        r6 = r6 + -1;
        r0 = r40;
        r0.setLength(r6);
        r6 = 57;
        if (r4 == r6) goto L_0x03cd;
    L_0x03c1:
        r4 = r4 + 1;
        r4 = (char) r4;
        r0 = r40;
        r0.append(r4);
    L_0x03c9:
        r4 = r5 + 1;
        goto L_0x004a;
    L_0x03cd:
        r4 = r40.length();
        if (r4 != 0) goto L_0x03a6;
    L_0x03d3:
        r5 = r5 + 1;
        r4 = 48;
        goto L_0x03c1;
    L_0x03d8:
        r8 = 4621819117588971520; // 0x4024000000000000 float:0.0 double:10.0;
        r34 = r12 * r8;
        r8 = 0;
        r8 = (r34 > r8 ? 1 : (r34 == r8 ? 0 : -1));
        if (r8 == 0) goto L_0x03c9;
    L_0x03e2:
        r4 = r4 + 1;
        goto L_0x037e;
    L_0x03e5:
        r9 = 0;
        if (r22 == 0) goto L_0x0709;
    L_0x03e8:
        r6 = 2;
        r0 = r23;
        if (r0 >= r6) goto L_0x049d;
    L_0x03ed:
        if (r24 == 0) goto L_0x0496;
    L_0x03ef:
        r6 = 0;
        r6 = r25[r6];
        r6 = r6 + 1075;
    L_0x03f4:
        r7 = r18;
        r8 = r19;
        r9 = r6;
        r6 = r13;
    L_0x03fa:
        r13 = r19 + r9;
        r10 = r21 + r9;
        r14 = 1;
        r9 = java.math.BigInteger.valueOf(r14);
        r17 = r6;
        r14 = r18;
        r18 = r7;
        r7 = r8;
        r8 = r10;
    L_0x040c:
        if (r7 <= 0) goto L_0x0700;
    L_0x040e:
        if (r8 <= 0) goto L_0x0700;
    L_0x0410:
        if (r7 >= r8) goto L_0x04b5;
    L_0x0412:
        r6 = r7;
    L_0x0413:
        r13 = r13 - r6;
        r7 = r7 - r6;
        r8 = r8 - r6;
        r10 = r8;
        r15 = r7;
    L_0x0418:
        if (r14 <= 0) goto L_0x06fb;
    L_0x041a:
        if (r22 == 0) goto L_0x04b8;
    L_0x041c:
        if (r18 <= 0) goto L_0x06f6;
    L_0x041e:
        r0 = r18;
        r6 = pow5mult(r9, r0);
        r0 = r20;
        r7 = r6.multiply(r0);
    L_0x042a:
        r8 = r14 - r18;
        if (r8 == 0) goto L_0x0432;
    L_0x042e:
        r7 = pow5mult(r7, r8);
    L_0x0432:
        r8 = 1;
        r14 = java.math.BigInteger.valueOf(r8);
        if (r17 <= 0) goto L_0x0440;
    L_0x043a:
        r0 = r17;
        r14 = pow5mult(r14, r0);
    L_0x0440:
        r8 = 0;
        r9 = 2;
        r0 = r23;
        if (r0 >= r9) goto L_0x06f2;
    L_0x0446:
        r9 = word1(r34);
        if (r9 != 0) goto L_0x06f2;
    L_0x044c:
        r9 = word0(r34);
        r16 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r9 = r9 & r16;
        if (r9 != 0) goto L_0x06f2;
    L_0x0457:
        r9 = word0(r34);
        r16 = 2145386496; // 0x7fe00000 float:NaN double:1.0599617647E-314;
        r9 = r9 & r16;
        if (r9 == 0) goto L_0x06f2;
    L_0x0461:
        r13 = r13 + 1;
        r9 = r10 + 1;
        r8 = 1;
        r10 = r13;
    L_0x0467:
        r18 = r14.toByteArray();
        r16 = 0;
        r13 = 0;
        r32 = r13;
        r13 = r16;
        r16 = r32;
    L_0x0474:
        r19 = 4;
        r0 = r16;
        r1 = r19;
        if (r0 >= r1) goto L_0x04c2;
    L_0x047c:
        r13 = r13 << 8;
        r0 = r18;
        r0 = r0.length;
        r19 = r0;
        r0 = r16;
        r1 = r19;
        if (r0 >= r1) goto L_0x0493;
    L_0x0489:
        r19 = r18[r16];
        r0 = r19;
        r0 = r0 & 255;
        r19 = r0;
        r13 = r13 | r19;
    L_0x0493:
        r16 = r16 + 1;
        goto L_0x0474;
    L_0x0496:
        r6 = 0;
        r6 = r26[r6];
        r6 = 54 - r6;
        goto L_0x03f4;
    L_0x049d:
        r6 = r11 + -1;
        r0 = r18;
        if (r0 < r6) goto L_0x04ad;
    L_0x04a3:
        r7 = r18 - r6;
        r6 = r13;
    L_0x04a6:
        if (r11 >= 0) goto L_0x0704;
    L_0x04a8:
        r8 = r19 - r11;
        r9 = 0;
        goto L_0x03fa;
    L_0x04ad:
        r7 = r6 - r18;
        r6 = r13 + r7;
        r18 = r18 + r7;
        r7 = 0;
        goto L_0x04a6;
    L_0x04b5:
        r6 = r8;
        goto L_0x0413;
    L_0x04b8:
        r0 = r20;
        r6 = pow5mult(r0, r14);
        r7 = r6;
        r6 = r9;
        goto L_0x0432;
    L_0x04c2:
        if (r17 == 0) goto L_0x053f;
    L_0x04c4:
        r13 = hi0bits(r13);
        r13 = 32 - r13;
    L_0x04ca:
        r13 = r13 + r9;
        r13 = r13 & 31;
        if (r13 == 0) goto L_0x04d1;
    L_0x04cf:
        r13 = 32 - r13;
    L_0x04d1:
        r16 = 4;
        r0 = r16;
        if (r13 <= r0) goto L_0x0541;
    L_0x04d7:
        r16 = r13 + -4;
        r13 = r10 + r16;
        r10 = r15 + r16;
        r9 = r9 + r16;
    L_0x04df:
        if (r13 <= 0) goto L_0x04e5;
    L_0x04e1:
        r7 = r7.shiftLeft(r13);
    L_0x04e5:
        if (r9 <= 0) goto L_0x06eb;
    L_0x04e7:
        r9 = r14.shiftLeft(r9);
        r13 = r9;
    L_0x04ec:
        if (r4 == 0) goto L_0x06e4;
    L_0x04ee:
        r4 = r7.compareTo(r13);
        if (r4 >= 0) goto L_0x06e4;
    L_0x04f4:
        r5 = r5 + -1;
        r14 = 10;
        r4 = java.math.BigInteger.valueOf(r14);
        r7 = r7.multiply(r4);
        if (r22 == 0) goto L_0x050c;
    L_0x0502:
        r14 = 10;
        r4 = java.math.BigInteger.valueOf(r14);
        r6 = r6.multiply(r4);
    L_0x050c:
        r11 = r12;
        r32 = r5;
        r5 = r6;
        r6 = r32;
    L_0x0512:
        if (r11 > 0) goto L_0x055d;
    L_0x0514:
        r4 = 2;
        r0 = r23;
        if (r0 <= r4) goto L_0x055d;
    L_0x0519:
        if (r11 < 0) goto L_0x052f;
    L_0x051b:
        r4 = 5;
        r4 = java.math.BigInteger.valueOf(r4);
        r4 = r13.multiply(r4);
        r4 = r7.compareTo(r4);
        if (r4 < 0) goto L_0x052f;
    L_0x052b:
        if (r4 != 0) goto L_0x0550;
    L_0x052d:
        if (r37 != 0) goto L_0x0550;
    L_0x052f:
        r4 = 0;
        r0 = r40;
        r0.setLength(r4);
        r4 = 48;
        r0 = r40;
        r0.append(r4);
        r4 = 1;
        goto L_0x004a;
    L_0x053f:
        r13 = 1;
        goto L_0x04ca;
    L_0x0541:
        r16 = 4;
        r0 = r16;
        if (r13 >= r0) goto L_0x06ee;
    L_0x0547:
        r16 = r13 + 28;
        r13 = r10 + r16;
        r10 = r15 + r16;
        r9 = r9 + r16;
        goto L_0x04df;
    L_0x0550:
        r4 = 49;
        r0 = r40;
        r0.append(r4);
        r4 = r6 + 1;
        r4 = r4 + 1;
        goto L_0x004a;
    L_0x055d:
        if (r22 == 0) goto L_0x06ae;
    L_0x055f:
        if (r10 <= 0) goto L_0x0565;
    L_0x0561:
        r5 = r5.shiftLeft(r10);
    L_0x0565:
        if (r8 == 0) goto L_0x06e1;
    L_0x0567:
        r4 = 1;
        r4 = r5.shiftLeft(r4);
    L_0x056c:
        r8 = 1;
        r9 = r8;
        r32 = r7;
        r7 = r4;
        r4 = r32;
    L_0x0573:
        r4 = r4.divideAndRemainder(r13);
        r8 = 1;
        r8 = r4[r8];
        r10 = 0;
        r4 = r4[r10];
        r4 = r4.intValue();
        r4 = r4 + 48;
        r4 = (char) r4;
        r12 = r8.compareTo(r5);
        r10 = r13.subtract(r7);
        r14 = r10.signum();
        if (r14 > 0) goto L_0x05bd;
    L_0x0592:
        r10 = 1;
    L_0x0593:
        if (r10 != 0) goto L_0x05d0;
    L_0x0595:
        if (r23 != 0) goto L_0x05d0;
    L_0x0597:
        r14 = word1(r34);
        r14 = r14 & 1;
        if (r14 != 0) goto L_0x05d0;
    L_0x059f:
        r5 = 57;
        if (r4 != r5) goto L_0x05c2;
    L_0x05a3:
        r4 = 57;
        r0 = r40;
        r0.append(r4);
        r4 = roundOff(r40);
        if (r4 == 0) goto L_0x06de;
    L_0x05b0:
        r4 = r6 + 1;
        r5 = 49;
        r0 = r40;
        r0.append(r5);
    L_0x05b9:
        r4 = r4 + 1;
        goto L_0x004a;
    L_0x05bd:
        r10 = r8.compareTo(r10);
        goto L_0x0593;
    L_0x05c2:
        if (r12 <= 0) goto L_0x05c7;
    L_0x05c4:
        r4 = r4 + 1;
        r4 = (char) r4;
    L_0x05c7:
        r0 = r40;
        r0.append(r4);
        r4 = r6 + 1;
        goto L_0x004a;
    L_0x05d0:
        if (r12 < 0) goto L_0x05de;
    L_0x05d2:
        if (r12 != 0) goto L_0x061f;
    L_0x05d4:
        if (r23 != 0) goto L_0x061f;
    L_0x05d6:
        r12 = word1(r34);
        r12 = r12 & 1;
        if (r12 != 0) goto L_0x061f;
    L_0x05de:
        if (r10 <= 0) goto L_0x0616;
    L_0x05e0:
        r5 = 1;
        r5 = r8.shiftLeft(r5);
        r5 = r5.compareTo(r13);
        if (r5 > 0) goto L_0x05f4;
    L_0x05eb:
        if (r5 != 0) goto L_0x0616;
    L_0x05ed:
        r5 = r4 & 1;
        r7 = 1;
        if (r5 == r7) goto L_0x05f4;
    L_0x05f2:
        if (r37 == 0) goto L_0x0616;
    L_0x05f4:
        r5 = r4 + 1;
        r5 = (char) r5;
        r7 = 57;
        if (r4 != r7) goto L_0x0615;
    L_0x05fb:
        r4 = 57;
        r0 = r40;
        r0.append(r4);
        r4 = roundOff(r40);
        if (r4 == 0) goto L_0x0611;
    L_0x0608:
        r6 = r6 + 1;
        r4 = 49;
        r0 = r40;
        r0.append(r4);
    L_0x0611:
        r4 = r6 + 1;
        goto L_0x004a;
    L_0x0615:
        r4 = r5;
    L_0x0616:
        r0 = r40;
        r0.append(r4);
        r4 = r6 + 1;
        goto L_0x004a;
    L_0x061f:
        if (r10 <= 0) goto L_0x064b;
    L_0x0621:
        r5 = 57;
        if (r4 != r5) goto L_0x063f;
    L_0x0625:
        r4 = 57;
        r0 = r40;
        r0.append(r4);
        r4 = roundOff(r40);
        if (r4 == 0) goto L_0x063b;
    L_0x0632:
        r6 = r6 + 1;
        r4 = 49;
        r0 = r40;
        r0.append(r4);
    L_0x063b:
        r4 = r6 + 1;
        goto L_0x004a;
    L_0x063f:
        r4 = r4 + 1;
        r4 = (char) r4;
        r0 = r40;
        r0.append(r4);
        r4 = r6 + 1;
        goto L_0x004a;
    L_0x064b:
        r0 = r40;
        r0.append(r4);
        if (r9 != r11) goto L_0x067b;
    L_0x0652:
        r5 = r4;
        r4 = r8;
    L_0x0654:
        r7 = 1;
        r4 = r4.shiftLeft(r7);
        r4 = r4.compareTo(r13);
        if (r4 > 0) goto L_0x0668;
    L_0x065f:
        if (r4 != 0) goto L_0x06d7;
    L_0x0661:
        r4 = r5 & 1;
        r5 = 1;
        if (r4 == r5) goto L_0x0668;
    L_0x0666:
        if (r37 == 0) goto L_0x06d7;
    L_0x0668:
        r4 = roundOff(r40);
        if (r4 == 0) goto L_0x06da;
    L_0x066e:
        r4 = r6 + 1;
        r5 = 49;
        r0 = r40;
        r0.append(r5);
        r4 = r4 + 1;
        goto L_0x004a;
    L_0x067b:
        r14 = 10;
        r4 = java.math.BigInteger.valueOf(r14);
        r8 = r8.multiply(r4);
        if (r5 != r7) goto L_0x0699;
    L_0x0687:
        r4 = 10;
        r4 = java.math.BigInteger.valueOf(r4);
        r4 = r7.multiply(r4);
        r5 = r4;
    L_0x0692:
        r7 = r9 + 1;
        r9 = r7;
        r7 = r4;
        r4 = r8;
        goto L_0x0573;
    L_0x0699:
        r14 = 10;
        r4 = java.math.BigInteger.valueOf(r14);
        r5 = r5.multiply(r4);
        r14 = 10;
        r4 = java.math.BigInteger.valueOf(r14);
        r4 = r7.multiply(r4);
        goto L_0x0692;
    L_0x06ae:
        r4 = 1;
    L_0x06af:
        r7 = r7.divideAndRemainder(r13);
        r5 = 1;
        r5 = r7[r5];
        r8 = 0;
        r7 = r7[r8];
        r7 = r7.intValue();
        r7 = r7 + 48;
        r7 = (char) r7;
        r0 = r40;
        r0.append(r7);
        if (r4 < r11) goto L_0x06ca;
    L_0x06c7:
        r4 = r5;
        r5 = r7;
        goto L_0x0654;
    L_0x06ca:
        r8 = 10;
        r7 = java.math.BigInteger.valueOf(r8);
        r7 = r5.multiply(r7);
        r4 = r4 + 1;
        goto L_0x06af;
    L_0x06d7:
        stripTrailingZeroes(r40);
    L_0x06da:
        r4 = r6 + 1;
        goto L_0x004a;
    L_0x06de:
        r4 = r6;
        goto L_0x05b9;
    L_0x06e1:
        r4 = r5;
        goto L_0x056c;
    L_0x06e4:
        r32 = r6;
        r6 = r5;
        r5 = r32;
        goto L_0x0512;
    L_0x06eb:
        r13 = r14;
        goto L_0x04ec;
    L_0x06ee:
        r13 = r10;
        r10 = r15;
        goto L_0x04df;
    L_0x06f2:
        r9 = r10;
        r10 = r13;
        goto L_0x0467;
    L_0x06f6:
        r6 = r9;
        r7 = r20;
        goto L_0x042a;
    L_0x06fb:
        r6 = r9;
        r7 = r20;
        goto L_0x0432;
    L_0x0700:
        r10 = r8;
        r15 = r7;
        goto L_0x0418;
    L_0x0704:
        r8 = r19;
        r9 = r11;
        goto L_0x03fa;
    L_0x0709:
        r17 = r13;
        r8 = r21;
        r7 = r19;
        r14 = r18;
        r13 = r19;
        goto L_0x040c;
    L_0x0715:
        r5 = r7;
        r11 = r8;
        r34 = r14;
        goto L_0x02c1;
    L_0x071b:
        r6 = r9;
        goto L_0x02b9;
    L_0x071e:
        r14 = r16;
        goto L_0x02b9;
    L_0x0722:
        r16 = r14;
        goto L_0x0245;
    L_0x0726:
        r6 = r10;
        r14 = r8;
        r8 = r11;
        r9 = r7;
        r7 = r5;
        goto L_0x01c9;
    L_0x072d:
        r7 = r6;
        goto L_0x01b8;
    L_0x0730:
        r8 = r34;
        goto L_0x01b8;
    L_0x0734:
        r9 = r6;
        r16 = r34;
        r32 = r7;
        r6 = r14;
        r14 = r8;
        r8 = r32;
        goto L_0x012b;
    L_0x073f:
        r22 = r6;
        goto L_0x0101;
    L_0x0743:
        r23 = r7;
        r7 = r6;
        goto L_0x00f9;
    L_0x0748:
        r7 = r36;
        goto L_0x00ef;
    L_0x074c:
        r32 = r5;
        r5 = r4;
        r4 = r32;
        goto L_0x00d4;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.DToA.JS_dtoa(double, int, boolean, int, boolean[], java.lang.StringBuilder):int");
    }

    private static void stripTrailingZeroes(StringBuilder stringBuilder) {
        int i;
        int length = stringBuilder.length();
        while (true) {
            i = length - 1;
            if (length <= 0 || stringBuilder.charAt(i) != '0') {
                stringBuilder.setLength(i + 1);
            } else {
                length = i;
            }
        }
        stringBuilder.setLength(i + 1);
    }

    static void JS_dtostr(StringBuilder stringBuilder, int i, int i2, double d) {
        boolean[] zArr = new boolean[1];
        if (i == 2 && (d >= 1.0E21d || d <= -1.0E21d)) {
            i = 0;
        }
        int JS_dtoa = JS_dtoa(d, dtoaModes[i], i >= 2, i2, zArr, stringBuilder);
        int length = stringBuilder.length();
        if (JS_dtoa != 9999) {
            int i3;
            int i4;
            switch (i) {
                case 0:
                    if (JS_dtoa >= -5 && JS_dtoa <= 21) {
                        i3 = JS_dtoa;
                        i4 = 0;
                        break;
                    }
                    i3 = 0;
                    i4 = 1;
                    break;
                    break;
                case 1:
                    i2 = 0;
                    break;
                case 2:
                    if (i2 < 0) {
                        i3 = JS_dtoa;
                        i4 = 0;
                        break;
                    }
                    i3 = i2 + JS_dtoa;
                    i4 = 0;
                    break;
                case 3:
                    break;
                case 4:
                    if (JS_dtoa >= -5 && JS_dtoa <= i2) {
                        i3 = i2;
                        i4 = 0;
                        break;
                    }
                    i3 = i2;
                    i4 = 1;
                    break;
                default:
                    i3 = 0;
                    i4 = 0;
                    break;
            }
            i3 = i2;
            i4 = 1;
            if (length < i3) {
                do {
                    stringBuilder.append('0');
                } while (stringBuilder.length() != i3);
            } else {
                i3 = length;
            }
            if (i4 != 0) {
                if (i3 != 1) {
                    stringBuilder.insert(1, '.');
                }
                stringBuilder.append('e');
                if (JS_dtoa - 1 >= 0) {
                    stringBuilder.append('+');
                }
                stringBuilder.append(JS_dtoa - 1);
            } else if (JS_dtoa != i3) {
                if (JS_dtoa > 0) {
                    stringBuilder.insert(JS_dtoa, '.');
                } else {
                    for (i3 = 0; i3 < 1 - JS_dtoa; i3++) {
                        stringBuilder.insert(0, '0');
                    }
                    stringBuilder.insert(1, '.');
                }
            }
        }
        if (!zArr[0]) {
            return;
        }
        if (word0(d) != Integer.MIN_VALUE || word1(d) != 0) {
            if ((word0(d) & Exp_mask) != Exp_mask || (word1(d) == 0 && (word0(d) & 1048575) == 0)) {
                stringBuilder.insert(0, '-');
            }
        }
    }
}
