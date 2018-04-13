package qsbk.app.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v4.view.MotionEventCompat;
import java.lang.reflect.Array;

public class BlurUtil {
    public static Bitmap fastblur(Bitmap bitmap, int i) {
        Bitmap copy = bitmap.copy(bitmap.getConfig(), true);
        fastblurSrc(copy, i);
        return copy;
    }

    public static void fastblurSrc(Bitmap bitmap, int i) {
        if (i >= 1) {
            int i2;
            int i3;
            int i4;
            int i5;
            int i6;
            int i7;
            int i8;
            int i9;
            int i10;
            int i11;
            int i12;
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int[] iArr = new int[(width * height)];
            bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
            int i13 = width - 1;
            int i14 = height - 1;
            int i15 = width * height;
            int i16 = (i + i) + 1;
            int[] iArr2 = new int[i15];
            int[] iArr3 = new int[i15];
            int[] iArr4 = new int[i15];
            int[] iArr5 = new int[Math.max(width, height)];
            i15 = (i16 + 1) >> 1;
            int i17 = i15 * i15;
            int[] iArr6 = new int[(i17 * 256)];
            for (i15 = 0; i15 < i17 * 256; i15++) {
                iArr6[i15] = i15 / i17;
            }
            int[][] iArr7 = (int[][]) Array.newInstance(Integer.TYPE, new int[]{i16, 3});
            int i18 = i + 1;
            int i19 = 0;
            int i20 = 0;
            for (i2 = 0; i2 < height; i2++) {
                int[] iArr8;
                i17 = 0;
                i3 = 0;
                i4 = 0;
                i5 = 0;
                i6 = 0;
                i7 = 0;
                i8 = 0;
                i9 = 0;
                i10 = 0;
                for (i11 = -i; i11 <= i; i11++) {
                    i12 = iArr[Math.min(i13, Math.max(i11, 0)) + i20];
                    iArr8 = iArr7[i11 + i];
                    iArr8[0] = (16711680 & i12) >> 16;
                    iArr8[1] = (MotionEventCompat.ACTION_POINTER_INDEX_MASK & i12) >> 8;
                    iArr8[2] = i12 & 255;
                    i12 = i18 - Math.abs(i11);
                    i9 += iArr8[0] * i12;
                    i8 += iArr8[1] * i12;
                    i7 += i12 * iArr8[2];
                    if (i11 > 0) {
                        i3 += iArr8[0];
                        i10 += iArr8[1];
                        i17 += iArr8[2];
                    } else {
                        i6 += iArr8[0];
                        i5 += iArr8[1];
                        i4 += iArr8[2];
                    }
                }
                i12 = i9;
                i9 = i8;
                i8 = i7;
                i11 = i20;
                i20 = i;
                for (i7 = 0; i7 < width; i7++) {
                    iArr2[i11] = iArr6[i12];
                    iArr3[i11] = iArr6[i9];
                    iArr4[i11] = iArr6[i8];
                    i12 -= i6;
                    i9 -= i5;
                    i8 -= i4;
                    iArr8 = iArr7[((i20 - i) + i16) % i16];
                    i6 -= iArr8[0];
                    i5 -= iArr8[1];
                    i4 -= iArr8[2];
                    if (i2 == 0) {
                        iArr5[i7] = Math.min((i7 + i) + 1, i13);
                    }
                    int i21 = iArr[iArr5[i7] + i19];
                    iArr8[0] = (16711680 & i21) >> 16;
                    iArr8[1] = (MotionEventCompat.ACTION_POINTER_INDEX_MASK & i21) >> 8;
                    iArr8[2] = i21 & 255;
                    i3 += iArr8[0];
                    i10 += iArr8[1];
                    i17 += iArr8[2];
                    i12 += i3;
                    i9 += i10;
                    i8 += i17;
                    i20 = (i20 + 1) % i16;
                    iArr8 = iArr7[i20 % i16];
                    i6 += iArr8[0];
                    i5 += iArr8[1];
                    i4 += iArr8[2];
                    i3 -= iArr8[0];
                    i10 -= iArr8[1];
                    i17 -= iArr8[2];
                    i11++;
                }
                i19 += width;
                i20 = i11;
            }
            for (i7 = 0; i7 < width; i7++) {
                i10 = 0;
                i17 = (-i) * width;
                i4 = 0;
                i5 = 0;
                i6 = 0;
                i20 = 0;
                i12 = -i;
                i11 = 0;
                i8 = 0;
                i9 = 0;
                i3 = 0;
                while (i12 <= i) {
                    i2 = Math.max(0, i17) + i7;
                    int[] iArr9 = iArr7[i12 + i];
                    iArr9[0] = iArr2[i2];
                    iArr9[1] = iArr3[i2];
                    iArr9[2] = iArr4[i2];
                    int abs = i18 - Math.abs(i12);
                    i19 = (iArr2[i2] * abs) + i9;
                    i9 = (iArr3[i2] * abs) + i8;
                    i8 = (iArr4[i2] * abs) + i11;
                    if (i12 > 0) {
                        i4 += iArr9[0];
                        i3 += iArr9[1];
                        i10 += iArr9[2];
                    } else {
                        i20 += iArr9[0];
                        i6 += iArr9[1];
                        i5 += iArr9[2];
                    }
                    if (i12 < i14) {
                        i17 += width;
                    }
                    i12++;
                    i11 = i8;
                    i8 = i9;
                    i9 = i19;
                }
                i12 = i8;
                i19 = i9;
                i9 = i11;
                i11 = i7;
                i17 = i10;
                i10 = i3;
                i3 = i4;
                i4 = i5;
                i5 = i6;
                i6 = i20;
                i20 = i;
                for (i8 = 0; i8 < height; i8++) {
                    iArr[i11] = (((-16777216 & iArr[i11]) | (iArr6[i19] << 16)) | (iArr6[i12] << 8)) | iArr6[i9];
                    i19 -= i6;
                    i12 -= i5;
                    i9 -= i4;
                    int[] iArr10 = iArr7[((i20 - i) + i16) % i16];
                    i6 -= iArr10[0];
                    i5 -= iArr10[1];
                    i4 -= iArr10[2];
                    if (i7 == 0) {
                        iArr5[i8] = Math.min(i8 + i18, i14) * width;
                    }
                    i13 = iArr5[i8] + i7;
                    iArr10[0] = iArr2[i13];
                    iArr10[1] = iArr3[i13];
                    iArr10[2] = iArr4[i13];
                    i3 += iArr10[0];
                    i10 += iArr10[1];
                    i17 += iArr10[2];
                    i19 += i3;
                    i12 += i10;
                    i9 += i17;
                    i20 = (i20 + 1) % i16;
                    iArr10 = iArr7[i20];
                    i6 += iArr10[0];
                    i5 += iArr10[1];
                    i4 += iArr10[2];
                    i3 -= iArr10[0];
                    i10 -= iArr10[1];
                    i17 -= iArr10[2];
                    i11 += width;
                }
            }
            bitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        }
    }

    public static void fastblurSrc(Bitmap bitmap, int i, boolean z, int[] iArr, int[] iArr2) {
        if (i >= 1) {
            int i2;
            int i3;
            int i4;
            int i5;
            int i6;
            int i7;
            int i8;
            int i9;
            int i10;
            int i11;
            int i12;
            Bitmap bitmap2;
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int[] iArr3 = new int[(width * height)];
            bitmap.getPixels(iArr3, 0, width, 0, 0, width, height);
            int i13 = width - 1;
            int i14 = height - 1;
            int i15 = width * height;
            int i16 = (i + i) + 1;
            int[] iArr4 = new int[i15];
            int[] iArr5 = new int[i15];
            int[] iArr6 = new int[i15];
            int[] iArr7 = new int[Math.max(width, height)];
            i15 = (i16 + 1) >> 1;
            int i17 = i15 * i15;
            int[] iArr8 = new int[(i17 * 256)];
            for (i15 = 0; i15 < i17 * 256; i15++) {
                iArr8[i15] = i15 / i17;
            }
            int[][] iArr9 = (int[][]) Array.newInstance(Integer.TYPE, new int[]{i16, 3});
            int i18 = i + 1;
            int i19 = 0;
            int i20 = 0;
            for (i2 = 0; i2 < height; i2++) {
                int[] iArr10;
                i17 = 0;
                i3 = 0;
                i4 = 0;
                i5 = 0;
                i6 = 0;
                i7 = 0;
                i8 = 0;
                i9 = 0;
                i10 = 0;
                for (i11 = -i; i11 <= i; i11++) {
                    i12 = iArr3[Math.min(i13, Math.max(i11, 0)) + i20];
                    iArr10 = iArr9[i11 + i];
                    iArr10[0] = (16711680 & i12) >> 16;
                    iArr10[1] = (MotionEventCompat.ACTION_POINTER_INDEX_MASK & i12) >> 8;
                    iArr10[2] = i12 & 255;
                    i12 = i18 - Math.abs(i11);
                    i9 += iArr10[0] * i12;
                    i8 += iArr10[1] * i12;
                    i7 += i12 * iArr10[2];
                    if (i11 > 0) {
                        i3 += iArr10[0];
                        i10 += iArr10[1];
                        i17 += iArr10[2];
                    } else {
                        i6 += iArr10[0];
                        i5 += iArr10[1];
                        i4 += iArr10[2];
                    }
                }
                i12 = i9;
                i9 = i8;
                i8 = i7;
                i11 = i20;
                i20 = i;
                for (i7 = 0; i7 < width; i7++) {
                    iArr4[i11] = iArr8[i12];
                    iArr5[i11] = iArr8[i9];
                    iArr6[i11] = iArr8[i8];
                    i12 -= i6;
                    i9 -= i5;
                    i8 -= i4;
                    iArr10 = iArr9[((i20 - i) + i16) % i16];
                    i6 -= iArr10[0];
                    i5 -= iArr10[1];
                    i4 -= iArr10[2];
                    if (i2 == 0) {
                        iArr7[i7] = Math.min((i7 + i) + 1, i13);
                    }
                    int i21 = iArr3[iArr7[i7] + i19];
                    iArr10[0] = (16711680 & i21) >> 16;
                    iArr10[1] = (MotionEventCompat.ACTION_POINTER_INDEX_MASK & i21) >> 8;
                    iArr10[2] = i21 & 255;
                    i3 += iArr10[0];
                    i10 += iArr10[1];
                    i17 += iArr10[2];
                    i12 += i3;
                    i9 += i10;
                    i8 += i17;
                    i20 = (i20 + 1) % i16;
                    iArr10 = iArr9[i20 % i16];
                    i6 += iArr10[0];
                    i5 += iArr10[1];
                    i4 += iArr10[2];
                    i3 -= iArr10[0];
                    i10 -= iArr10[1];
                    i17 -= iArr10[2];
                    i11++;
                }
                i19 += width;
                i20 = i11;
            }
            for (i7 = 0; i7 < width; i7++) {
                i10 = 0;
                i17 = (-i) * width;
                i4 = 0;
                i5 = 0;
                i6 = 0;
                i20 = 0;
                i12 = -i;
                i11 = 0;
                i8 = 0;
                i9 = 0;
                i3 = 0;
                while (i12 <= i) {
                    i2 = Math.max(0, i17) + i7;
                    int[] iArr11 = iArr9[i12 + i];
                    iArr11[0] = iArr4[i2];
                    iArr11[1] = iArr5[i2];
                    iArr11[2] = iArr6[i2];
                    int abs = i18 - Math.abs(i12);
                    i19 = (iArr4[i2] * abs) + i9;
                    i9 = (iArr5[i2] * abs) + i8;
                    i8 = (iArr6[i2] * abs) + i11;
                    if (i12 > 0) {
                        i4 += iArr11[0];
                        i3 += iArr11[1];
                        i10 += iArr11[2];
                    } else {
                        i20 += iArr11[0];
                        i6 += iArr11[1];
                        i5 += iArr11[2];
                    }
                    if (i12 < i14) {
                        i17 += width;
                    }
                    i12++;
                    i11 = i8;
                    i8 = i9;
                    i9 = i19;
                }
                i12 = i8;
                i19 = i9;
                i9 = i11;
                i11 = i7;
                i17 = i10;
                i10 = i3;
                i3 = i4;
                i4 = i5;
                i5 = i6;
                i6 = i20;
                i20 = i;
                for (i8 = 0; i8 < height; i8++) {
                    iArr3[i11] = (((-16777216 & iArr3[i11]) | (iArr8[i19] << 16)) | (iArr8[i12] << 8)) | iArr8[i9];
                    i19 -= i6;
                    i12 -= i5;
                    i9 -= i4;
                    int[] iArr12 = iArr9[((i20 - i) + i16) % i16];
                    i6 -= iArr12[0];
                    i5 -= iArr12[1];
                    i4 -= iArr12[2];
                    if (i7 == 0) {
                        iArr7[i8] = Math.min(i8 + i18, i14) * width;
                    }
                    i13 = iArr7[i8] + i7;
                    iArr12[0] = iArr4[i13];
                    iArr12[1] = iArr5[i13];
                    iArr12[2] = iArr6[i13];
                    i3 += iArr12[0];
                    i10 += iArr12[1];
                    i17 += iArr12[2];
                    i19 += i3;
                    i12 += i10;
                    i9 += i17;
                    i20 = (i20 + 1) % i16;
                    iArr12 = iArr9[i20];
                    i6 += iArr12[0];
                    i5 += iArr12[1];
                    i4 += iArr12[2];
                    i3 -= iArr12[0];
                    i10 -= iArr12[1];
                    i17 -= iArr12[2];
                    i11 += width;
                }
            }
            i17 = 0;
            if (z) {
                Object obj = 1;
                if (iArr2 != null && (iArr2[0] < 0 || iArr2[1] > height || iArr2[0] == iArr2[1])) {
                    obj = null;
                }
                if (iArr != null && (iArr[0] < 0 || iArr[1] > width || iArr[0] == iArr[1])) {
                    obj = null;
                }
                if (obj != null) {
                    if (iArr != null) {
                        i4 = iArr[1] - iArr[0];
                    } else {
                        i4 = width;
                    }
                    if (iArr2 != null) {
                        i3 = iArr2[1] - iArr2[0];
                    } else {
                        i3 = height;
                    }
                    if (!(i4 == width && i3 == height)) {
                        Bitmap createBitmap = Bitmap.createBitmap(Bitmap.createScaledBitmap(bitmap, i4, i3, false));
                        if (iArr != null) {
                            i17 = iArr[0];
                        }
                        if (iArr2 != null) {
                            i5 = iArr2[0];
                            i6 = i17;
                            bitmap2 = createBitmap;
                        } else {
                            i5 = 0;
                            i6 = i17;
                            bitmap2 = createBitmap;
                        }
                        bitmap.setPixels(iArr3, 0, width, 0, 0, width, height);
                        if (bitmap2 != null) {
                            new Canvas(bitmap).drawBitmap(bitmap2, (float) i6, (float) i5, null);
                        }
                    }
                }
            }
            i5 = 0;
            i6 = 0;
            bitmap2 = null;
            bitmap.setPixels(iArr3, 0, width, 0, 0, width, height);
            if (bitmap2 != null) {
                new Canvas(bitmap).drawBitmap(bitmap2, (float) i6, (float) i5, null);
            }
        }
    }
}
