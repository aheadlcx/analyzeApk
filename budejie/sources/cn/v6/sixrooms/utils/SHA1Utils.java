package cn.v6.sixrooms.utils;

import android.support.v4.internal.view.SupportMenu;

public class SHA1Utils {
    public static String hex_sha1(String str) {
        if (str == null) {
            str = "";
        }
        int[] a = a(a(str), str.length() * 8);
        String str2 = "0123456789abcdef";
        String str3 = "";
        for (int i = 0; i < 20; i++) {
            str3 = str3 + new Character(str2.charAt((a[i >> 2] >> (((3 - (i % 4)) * 8) + 4)) & 15)).toString() + new Character(str2.charAt((a[i >> 2] >> ((3 - (i % 4)) * 8)) & 15)).toString();
        }
        return str3;
    }

    private static int[] a(int[] iArr, int i) {
        int[] b = b(iArr, i >> 5);
        int i2 = i >> 5;
        b[i2] = b[i2] | (128 << (24 - (i % 32)));
        int[] b2 = b(b, (((i + 64) >> 9) << 4) + 15);
        b2[(((i + 64) >> 9) << 4) + 15] = i;
        int[] iArr2 = new int[80];
        int i3 = 1732584193;
        int i4 = -271733879;
        int i5 = -1732584194;
        int i6 = 271733878;
        i2 = -1009589776;
        for (int i7 = 0; i7 < b2.length; i7 += 16) {
            int i8 = 0;
            int i9 = i2;
            int i10 = i6;
            int i11 = i5;
            int i12 = i4;
            int i13 = i3;
            while (i8 < 80) {
                int i14;
                if (i8 < 16) {
                    iArr2[i8] = b2[i7 + i8];
                } else {
                    iArr2[i8] = a(((iArr2[i8 - 3] ^ iArr2[i8 - 8]) ^ iArr2[i8 - 14]) ^ iArr2[i8 - 16], 1);
                }
                int a = a(i13, 5);
                if (i8 < 20) {
                    i14 = (i12 & i11) | ((i12 ^ -1) & i10);
                } else if (i8 < 40 || i8 >= 60) {
                    i14 = (i12 ^ i11) ^ i10;
                } else {
                    i14 = ((i12 & i11) | (i12 & i10)) | (i11 & i10);
                }
                a = b(a, i14);
                i9 = b(i9, iArr2[i8]);
                i14 = i8 < 20 ? 1518500249 : i8 < 40 ? 1859775393 : i8 < 60 ? -1894007588 : -899497514;
                a = b(a, b(i9, i14));
                i9 = a(i12, 30);
                i8++;
                i12 = i13;
                i13 = a;
                int i15 = i9;
                i9 = i10;
                i10 = i11;
                i11 = i15;
            }
            i3 = b(i13, i3);
            i4 = b(i12, i4);
            i5 = b(i11, i5);
            i6 = b(i10, i6);
            i2 = b(i9, i2);
        }
        return new int[]{i3, i4, i5, i6, i2};
    }

    private static int a(int i, int i2) {
        return (i << i2) | (i >>> (32 - i2));
    }

    private static int b(int i, int i2) {
        int i3 = (i & SupportMenu.USER_MASK) + (i2 & SupportMenu.USER_MASK);
        return (i3 & SupportMenu.USER_MASK) | ((((i >> 16) + (i2 >> 16)) + (i3 >> 16)) << 16);
    }

    public static String str_sha1(String str) {
        if (str == null) {
            str = "";
        }
        int[] a = a(a(str), str.length() * 8);
        String str2 = "";
        for (int i = 0; i < 160; i += 8) {
            str2 = str2 + ((char) ((a[i >> 5] >>> (24 - (i % 32))) & 255));
        }
        return str2;
    }

    private static int[] a(String str) {
        int i;
        int i2;
        int i3 = 0;
        if (str == null) {
            str = "";
        }
        int[] iArr = new int[(str.length() * 8)];
        for (i = 0; i < str.length() * 8; i += 8) {
            i2 = i >> 5;
            iArr[i2] = iArr[i2] | ((str.charAt(i / 8) & 255) << (24 - (i % 32)));
        }
        i = 0;
        i2 = 0;
        while (i2 < iArr.length && iArr[i2] != 0) {
            i2++;
            i++;
        }
        int[] iArr2 = new int[i];
        while (i3 < i) {
            iArr2[i3] = iArr[i3];
            i3++;
        }
        return iArr2;
    }

    private static int[] b(int[] iArr, int i) {
        int i2 = 0;
        int length = iArr.length;
        if (length >= i + 1) {
            return iArr;
        }
        int[] iArr2 = new int[(i + 1)];
        for (int i3 = 0; i3 < i; i3++) {
            iArr2[i3] = 0;
        }
        while (i2 < length) {
            iArr2[i2] = iArr[i2];
            i2++;
        }
        return iArr2;
    }
}
