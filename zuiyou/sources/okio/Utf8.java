package okio;

public final class Utf8 {
    private Utf8() {
    }

    public static long size(String str) {
        return size(str, 0, str.length());
    }

    public static long size(String str, int i, int i2) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        } else if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0: " + i);
        } else if (i2 < i) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
        } else if (i2 > str.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + i2 + " > " + str.length());
        } else {
            long j = 0;
            while (i < i2) {
                int i3;
                char charAt = str.charAt(i);
                if (charAt < '') {
                    j++;
                    i3 = i + 1;
                } else if (charAt < 'ࠀ') {
                    j += 2;
                    i3 = i + 1;
                } else if (charAt < '?' || charAt > '?') {
                    j += 3;
                    i3 = i + 1;
                } else {
                    char charAt2 = i + 1 < i2 ? str.charAt(i + 1) : '\u0000';
                    if (charAt > '?' || charAt2 < '?' || charAt2 > '?') {
                        j++;
                        i3 = i + 1;
                    } else {
                        j += 4;
                        i3 = i + 2;
                    }
                }
                i = i3;
            }
            return j;
        }
    }
}
