package qsbk.app.ticker;

import java.lang.reflect.Array;

public class LevenshteinUtils {
    public static int[] computeColumnActions(char[] cArr, char[] cArr2) {
        int length = cArr.length;
        int length2 = cArr2.length;
        int max = Math.max(length, length2);
        int[] iArr = new int[max];
        if (length == length2) {
            return iArr;
        }
        int i;
        int i2;
        int i3 = length + 1;
        int i4 = length2 + 1;
        int[][] iArr2 = (int[][]) Array.newInstance(Integer.TYPE, new int[]{i3, i4});
        for (length2 = 0; length2 < i3; length2++) {
            iArr2[length2][0] = length2;
        }
        for (length2 = 0; length2 < i4; length2++) {
            iArr2[0][length2] = length2;
        }
        for (i = 1; i < i4; i++) {
            for (i2 = 1; i2 < i3; i2++) {
                iArr2[i2][i] = a(iArr2[i2 - 1][i] + 1, iArr2[i2][i - 1] + 1, (cArr[i2 + -1] == cArr2[i + -1] ? 0 : 1) + iArr2[i2 - 1][i - 1]);
            }
        }
        length2 = i4 - 1;
        i2 = i3 - 1;
        for (i = max - 1; i >= 0; i--) {
            if (i2 == 0) {
                iArr[i] = 1;
                length2--;
            } else if (length2 == 0) {
                iArr[i] = 2;
                i2--;
            } else {
                max = iArr2[i2 - 1][length2];
                i3 = iArr2[i2][length2 - 1];
                i4 = iArr2[i2 - 1][length2 - 1];
                if (i4 <= max && i4 <= i3) {
                    iArr[i] = 0;
                    i2--;
                    length2--;
                } else if (max <= i3) {
                    iArr[i] = 2;
                    i2--;
                } else {
                    iArr[i] = 1;
                    length2--;
                }
            }
        }
        return iArr;
    }

    private static int a(int i, int i2, int i3) {
        return Math.min(i, Math.min(i2, i3));
    }
}
