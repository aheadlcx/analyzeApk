package com.iflytek.cloud.util;

public class VolumeUtil {
    public static int computeVolume(byte[] bArr, int i) {
        if (bArr == null || i <= 2) {
            return 0;
        }
        int i2 = i / 2;
        long j = 0;
        for (int i3 = 0; i3 < (i2 * 2) - 1; i3 += 2) {
            j += (long) (bArr[i3] + (bArr[i3 + 1] * 256));
        }
        j /= (long) i2;
        long j2 = 0;
        for (int i4 = 0; i4 < (i2 * 2) - 1; i4 += 2) {
            int i5 = (int) (((long) (bArr[i4] + (bArr[i4 + 1] * 256))) - j);
            j2 += (long) ((i5 * i5) >> 9);
        }
        long j3 = j2 / ((long) i2);
        return j3 >= 329 ? j3 < 421 ? 1 : j3 < 543 ? 2 : j3 < 694 ? 3 : j3 < 895 ? 4 : j3 < 1146 ? 5 : j3 < 1476 ? 6 : j3 < 1890 ? 7 : j3 < 2433 ? 8 : j3 < 3118 ? 9 : j3 < 4011 ? 10 : j3 < 5142 ? 11 : j3 < 6612 ? 12 : j3 < 8478 ? 13 : j3 < 10900 ? 14 : j3 < 13982 ? 15 : j3 < 17968 ? 16 : j3 < 23054 ? 17 : j3 < 29620 ? 18 : j3 < 38014 ? 19 : j3 < 48828 ? 20 : j3 < 62654 ? 21 : j3 < 80491 ? 22 : j3 < 103294 ? 23 : j3 < 132686 ? 24 : j3 < 170366 ? 25 : j3 < 218728 ? 26 : j3 < 280830 ? 27 : 30 : 0;
    }
}
