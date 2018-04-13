package org.apache.commons.cli;

class d {
    static void a(String str) throws IllegalArgumentException {
        int i = 0;
        if (str != null) {
            if (str.length() == 1) {
                char charAt = str.charAt(0);
                if (!a(charAt)) {
                    throw new IllegalArgumentException(new StringBuffer().append("illegal option value '").append(charAt).append("'").toString());
                }
                return;
            }
            char[] toCharArray = str.toCharArray();
            while (i < toCharArray.length) {
                if (b(toCharArray[i])) {
                    i++;
                } else {
                    throw new IllegalArgumentException(new StringBuffer().append("opt contains illegal character value '").append(toCharArray[i]).append("'").toString());
                }
            }
        }
    }

    private static boolean a(char c) {
        return b(c) || c == ' ' || c == '?' || c == '@';
    }

    private static boolean b(char c) {
        return Character.isJavaIdentifierPart(c);
    }
}
