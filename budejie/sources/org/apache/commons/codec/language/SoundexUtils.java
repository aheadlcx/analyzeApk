package org.apache.commons.codec.language;

import java.util.Locale;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

final class SoundexUtils {
    SoundexUtils() {
    }

    static String clean(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        int length = str.length();
        char[] cArr = new char[length];
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3;
            if (Character.isLetter(str.charAt(i))) {
                i3 = i2 + 1;
                cArr[i2] = str.charAt(i);
            } else {
                i3 = i2;
            }
            i++;
            i2 = i3;
        }
        if (i2 == length) {
            return str.toUpperCase(Locale.ENGLISH);
        }
        return new String(cArr, 0, i2).toUpperCase(Locale.ENGLISH);
    }

    static int difference(StringEncoder stringEncoder, String str, String str2) throws EncoderException {
        return differenceEncoded(stringEncoder.encode(str), stringEncoder.encode(str2));
    }

    static int differenceEncoded(String str, String str2) {
        int i = 0;
        if (!(str == null || str2 == null)) {
            int min = Math.min(str.length(), str2.length());
            for (int i2 = 0; i2 < min; i2++) {
                if (str.charAt(i2) == str2.charAt(i2)) {
                    i++;
                }
            }
        }
        return i;
    }
}
