package mtopsdk.common.util;

public class l {
    public static String a(String str, String str2) {
        if (b(str) || b(str2)) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str.trim());
        stringBuilder.append("$");
        stringBuilder.append(str2.trim());
        return stringBuilder.toString().toLowerCase();
    }

    public static boolean a(String str) {
        return !b(str);
    }

    public static boolean b(String str) {
        if (str != null) {
            int length = str.length();
            if (length != 0) {
                for (int i = 0; i < length; i++) {
                    if (!Character.isWhitespace(str.charAt(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return true;
    }
}
