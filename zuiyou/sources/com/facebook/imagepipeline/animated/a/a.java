package com.facebook.imagepipeline.animated.a;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import java.util.Arrays;

public class a {
    public void a(StringBuilder stringBuilder, int i) {
        if (i < 1024) {
            stringBuilder.append(i);
            stringBuilder.append("KB");
            return;
        }
        int i2 = (i % 1024) / 100;
        stringBuilder.append(i / 1024);
        stringBuilder.append(".");
        stringBuilder.append(i2);
        stringBuilder.append("MB");
    }

    public void a(int[] iArr) {
        for (int i = 0; i < iArr.length; i++) {
            if (iArr[i] < 11) {
                iArr[i] = 100;
            }
        }
    }

    public int b(int[] iArr) {
        int i = 0;
        int i2 = 0;
        while (i < iArr.length) {
            i2 += iArr[i];
            i++;
        }
        return i2;
    }

    public int[] c(int[] iArr) {
        int i = 0;
        int[] iArr2 = new int[iArr.length];
        int i2 = 0;
        while (i < iArr.length) {
            iArr2[i] = i2;
            i2 += iArr[i];
            i++;
        }
        return iArr2;
    }

    public int a(int[] iArr, int i) {
        int binarySearch = Arrays.binarySearch(iArr, i);
        if (binarySearch < 0) {
            return ((-binarySearch) - 1) - 1;
        }
        return binarySearch;
    }

    @SuppressLint({"NewApi"})
    public int a(Bitmap bitmap) {
        if (VERSION.SDK_INT >= 19) {
            return bitmap.getAllocationByteCount();
        }
        if (VERSION.SDK_INT >= 12) {
            return bitmap.getByteCount();
        }
        return (bitmap.getWidth() * bitmap.getHeight()) * 4;
    }

    public static boolean a(int i, int i2, int i3) {
        boolean z = true;
        if (i == -1 || i2 == -1) {
            return true;
        }
        if (i > i2) {
            if (i3 >= i || i3 <= i2) {
                z = false;
            }
            return z;
        } else if (i3 < i || i3 > i2) {
            return true;
        } else {
            return false;
        }
    }
}
