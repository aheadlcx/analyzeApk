package qsbk.app.ticker;

import android.support.v4.view.InputDeviceCompat;

public class TickerUtils {
    public static final char EMPTY_CHAR = '\u0000';

    public static char[] getDefaultListForUSCurrency() {
        int i;
        char[] cArr = new char[224];
        for (i = 33; i < 48; i++) {
            cArr[i - 33] = (char) i;
        }
        cArr[15] = '\u0000';
        cArr[13] = '/';
        cArr[14] = '.';
        for (i = 49; i < InputDeviceCompat.SOURCE_KEYBOARD; i++) {
            cArr[i - 33] = (char) (i - 1);
        }
        return cArr;
    }

    public static char[] getDefaultNumberList() {
        int i = 0;
        char[] cArr = new char[11];
        cArr[0] = '\u0000';
        while (i < 10) {
            cArr[i + 1] = (char) (i + 48);
            i++;
        }
        return cArr;
    }
}
