package okhttp3;

import java.util.Comparator;

final class f implements Comparator<String> {
    f() {
    }

    public int compare(String str, String str2) {
        int i = 4;
        int min = Math.min(str.length(), str2.length());
        while (i < min) {
            char charAt = str.charAt(i);
            char charAt2 = str2.charAt(i);
            if (charAt == charAt2) {
                i++;
            } else if (charAt < charAt2) {
                return -1;
            } else {
                return 1;
            }
        }
        i = str.length();
        min = str2.length();
        if (i == min) {
            return 0;
        }
        if (i >= min) {
            return 1;
        }
        return -1;
    }
}
