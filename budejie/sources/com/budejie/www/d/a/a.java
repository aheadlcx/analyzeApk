package com.budejie.www.d.a;

import android.graphics.Bitmap;
import android.support.v4.view.MotionEventCompat;
import java.lang.reflect.Array;

public class a {
    public static Bitmap a(Bitmap bitmap, int i, boolean z) {
        Bitmap bitmap2;
        if (z) {
            bitmap2 = bitmap;
        } else {
            bitmap2 = bitmap.copy(bitmap.getConfig(), true);
        }
        if (i < 1) {
            return null;
        }
        int i2;
        int i3;
        int i4;
        int width = bitmap2.getWidth();
        int height = bitmap2.getHeight();
        int[] iArr = new int[(width * height)];
        bitmap2.getPixels(iArr, 0, width, 0, 0, width, height);
        int i5 = width - 1;
        int i6 = height - 1;
        int i7 = width * height;
        int i8 = (i + i) + 1;
        int[] iArr2 = new int[i7];
        int[] iArr3 = new int[i7];
        int[] iArr4 = new int[i7];
        int[] iArr5 = new int[Math.max(width, height)];
        i7 = (i8 + 1) >> 1;
        int i9 = i7 * i7;
        int[] iArr6 = new int[(i9 * 256)];
        for (i7 = 0; i7 < i9 * 256; i7++) {
            iArr6[i7] = i7 / i9;
        }
        int[][] iArr7 = (int[][]) Array.newInstance(Integer.TYPE, new int[]{i8, 3});
        int i10 = i + 1;
        int i11 = 0;
        int i12 = 0;
        for (i2 = 0; i2 < height; i2++) {
            i9 = 0;
            int i13 = 0;
            int i14 = 0;
            int i15 = 0;
            int i16 = 0;
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            for (i3 = -i; i3 <= i; i3++) {
                i4 = iArr[Math.min(i5, Math.max(i3, 0)) + i12];
                int[] iArr8 = iArr7[i3 + i];
                iArr8[0] = (16711680 & i4) >> 16;
                iArr8[1] = (MotionEventCompat.ACTION_POINTER_INDEX_MASK & i4) >> 8;
                iArr8[2] = i4 & 255;
                i4 = i10 - Math.abs(i3);
                i19 += iArr8[0] * i4;
                i18 += iArr8[1] * i4;
                i17 += i4 * iArr8[2];
                if (i3 > 0) {
                    i13 += iArr8[0];
                    i20 += iArr8[1];
                    i9 += iArr8[2];
                } else {
                    i16 += iArr8[0];
                    i15 += iArr8[1];
                    i14 += iArr8[2];
                }
            }
            i4 = i19;
            i19 = i18;
            i18 = i17;
            i3 = i12;
            i12 = i;
            for (i17 = 0; i17 < width; i17++) {
                iArr2[i3] = iArr6[i4];
                iArr3[i3] = iArr6[i19];
                iArr4[i3] = iArr6[i18];
                i4 -= i16;
                i19 -= i15;
                i18 -= i14;
                iArr8 = iArr7[((i12 - i) + i8) % i8];
                i16 -= iArr8[0];
                i15 -= iArr8[1];
                i14 -= iArr8[2];
                if (i2 == 0) {
                    iArr5[i17] = Math.min((i17 + i) + 1, i5);
                }
                int i21 = iArr[iArr5[i17] + i11];
                iArr8[0] = (16711680 & i21) >> 16;
                iArr8[1] = (MotionEventCompat.ACTION_POINTER_INDEX_MASK & i21) >> 8;
                iArr8[2] = i21 & 255;
                i13 += iArr8[0];
                i20 += iArr8[1];
                i9 += iArr8[2];
                i4 += i13;
                i19 += i20;
                i18 += i9;
                i12 = (i12 + 1) % i8;
                iArr8 = iArr7[i12 % i8];
                i16 += iArr8[0];
                i15 += iArr8[1];
                i14 += iArr8[2];
                i13 -= iArr8[0];
                i20 -= iArr8[1];
                i9 -= iArr8[2];
                i3++;
            }
            i11 += width;
            i12 = i3;
        }
        for (i17 = 0; i17 < width; i17++) {
            i20 = 0;
            i9 = (-i) * width;
            i14 = 0;
            i15 = 0;
            i16 = 0;
            i12 = 0;
            i4 = -i;
            i3 = 0;
            i18 = 0;
            i19 = 0;
            i13 = 0;
            while (i4 <= i) {
                i2 = Math.max(0, i9) + i17;
                int[] iArr9 = iArr7[i4 + i];
                iArr9[0] = iArr2[i2];
                iArr9[1] = iArr3[i2];
                iArr9[2] = iArr4[i2];
                int abs = i10 - Math.abs(i4);
                i11 = (iArr2[i2] * abs) + i19;
                i19 = (iArr3[i2] * abs) + i18;
                i18 = (iArr4[i2] * abs) + i3;
                if (i4 > 0) {
                    i14 += iArr9[0];
                    i13 += iArr9[1];
                    i20 += iArr9[2];
                } else {
                    i12 += iArr9[0];
                    i16 += iArr9[1];
                    i15 += iArr9[2];
                }
                if (i4 < i6) {
                    i9 += width;
                }
                i4++;
                i3 = i18;
                i18 = i19;
                i19 = i11;
            }
            i4 = i18;
            i11 = i19;
            i19 = i3;
            i3 = i17;
            i9 = i20;
            i20 = i13;
            i13 = i14;
            i14 = i15;
            i15 = i16;
            i16 = i12;
            i12 = i;
            for (i18 = 0; i18 < height; i18++) {
                iArr[i3] = (((-16777216 & iArr[i3]) | (iArr6[i11] << 16)) | (iArr6[i4] << 8)) | iArr6[i19];
                i11 -= i16;
                i4 -= i15;
                i19 -= i14;
                int[] iArr10 = iArr7[((i12 - i) + i8) % i8];
                i16 -= iArr10[0];
                i15 -= iArr10[1];
                i14 -= iArr10[2];
                if (i17 == 0) {
                    iArr5[i18] = Math.min(i18 + i10, i6) * width;
                }
                i5 = iArr5[i18] + i17;
                iArr10[0] = iArr2[i5];
                iArr10[1] = iArr3[i5];
                iArr10[2] = iArr4[i5];
                i13 += iArr10[0];
                i20 += iArr10[1];
                i9 += iArr10[2];
                i11 += i13;
                i4 += i20;
                i19 += i9;
                i12 = (i12 + 1) % i8;
                iArr10 = iArr7[i12];
                i16 += iArr10[0];
                i15 += iArr10[1];
                i14 += iArr10[2];
                i13 -= iArr10[0];
                i20 -= iArr10[1];
                i9 -= iArr10[2];
                i3 += width;
            }
        }
        bitmap2.setPixels(iArr, 0, width, 0, 0, width, height);
        return bitmap2;
    }
}
