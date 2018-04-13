package cz.msebera.android.httpclient.client.utils;

import com.qq.e.comm.constants.ErrorCode$OtherError;
import cz.msebera.android.httpclient.annotation.Immutable;
import java.util.StringTokenizer;

@Immutable
@Deprecated
public class Rfc3492Idn implements Idn {
    private int a(int i, int i2, boolean z) {
        int i3;
        if (z) {
            i3 = i / ErrorCode$OtherError.VIDEO_DOWNLOAD_ERROR;
        } else {
            i3 = i / 2;
        }
        int i4 = (i3 / i2) + i3;
        i3 = 0;
        while (i4 > 455) {
            i4 /= 35;
            i3 += 36;
        }
        return i3 + ((i4 * 36) / (i4 + 38));
    }

    private int a(char c) {
        if (c >= 'A' && c <= 'Z') {
            return c - 65;
        }
        if (c >= 'a' && c <= 'z') {
            return c - 97;
        }
        if (c >= '0' && c <= '9') {
            return (c - 48) + 26;
        }
        throw new IllegalArgumentException("illegal digit: " + c);
    }

    public String toUnicode(String str) {
        StringBuilder stringBuilder = new StringBuilder(str.length());
        StringTokenizer stringTokenizer = new StringTokenizer(str, ".");
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            if (stringBuilder.length() > 0) {
                stringBuilder.append('.');
            }
            if (nextToken.startsWith("xn--")) {
                nextToken = a(nextToken.substring(4));
            }
            stringBuilder.append(nextToken);
        }
        return stringBuilder.toString();
    }

    protected String a(String str) {
        int i;
        int i2 = 72;
        StringBuilder stringBuilder = new StringBuilder(str.length());
        int lastIndexOf = str.lastIndexOf(45);
        if (lastIndexOf != -1) {
            stringBuilder.append(str.subSequence(0, lastIndexOf));
            str = str.substring(lastIndexOf + 1);
            lastIndexOf = 128;
            i = 0;
        } else {
            lastIndexOf = 128;
            i = 0;
        }
        while (!str.isEmpty()) {
            boolean z;
            int i3 = 36;
            int i4 = 1;
            int i5 = i;
            while (!str.isEmpty()) {
                int i6;
                char charAt = str.charAt(0);
                str = str.substring(1);
                int a = a(charAt);
                i5 += a * i4;
                if (i3 <= i2 + 1) {
                    i6 = 1;
                } else if (i3 >= i2 + 26) {
                    i6 = 26;
                } else {
                    i6 = i3 - i2;
                }
                if (a < i6) {
                    break;
                }
                i4 *= 36 - i6;
                i3 += 36;
            }
            i3 = i5 - i;
            i4 = stringBuilder.length() + 1;
            if (i == 0) {
                z = true;
            } else {
                z = false;
            }
            i2 = a(i3, i4, z);
            lastIndexOf += i5 / (stringBuilder.length() + 1);
            i = i5 % (stringBuilder.length() + 1);
            stringBuilder.insert(i, (char) lastIndexOf);
            i++;
        }
        return stringBuilder.toString();
    }
}
