package org.apache.commons.httpclient.util;

import com.ali.auth.third.login.LoginConstants;
import org.apache.commons.httpclient.NameValuePair;

public class ParameterFormatter {
    private static final char[] SEPARATORS = new char[]{'(', ')', '<', '>', '@', ',', ';', ':', '\\', '\"', '/', '[', ']', '?', '=', '{', '}', ' ', '\t'};
    private static final char[] UNSAFE_CHARS = new char[]{'\"', '\\'};
    private boolean alwaysUseQuotes = true;

    private static boolean isOneOf(char[] cArr, char c) {
        for (char c2 : cArr) {
            if (c == c2) {
                return true;
            }
        }
        return false;
    }

    private static boolean isUnsafeChar(char c) {
        return isOneOf(UNSAFE_CHARS, c);
    }

    private static boolean isSeparator(char c) {
        return isOneOf(SEPARATORS, c);
    }

    public boolean isAlwaysUseQuotes() {
        return this.alwaysUseQuotes;
    }

    public void setAlwaysUseQuotes(boolean z) {
        this.alwaysUseQuotes = z;
    }

    public static void formatValue(StringBuffer stringBuffer, String str, boolean z) {
        int i = 0;
        if (stringBuffer == null) {
            throw new IllegalArgumentException("String buffer may not be null");
        } else if (str == null) {
            throw new IllegalArgumentException("Value buffer may not be null");
        } else if (z) {
            stringBuffer.append('\"');
            while (i < str.length()) {
                char charAt = str.charAt(i);
                if (isUnsafeChar(charAt)) {
                    stringBuffer.append('\\');
                }
                stringBuffer.append(charAt);
                i++;
            }
            stringBuffer.append('\"');
        } else {
            int length = stringBuffer.length();
            int i2 = 0;
            while (i < str.length()) {
                char charAt2 = str.charAt(i);
                if (isSeparator(charAt2)) {
                    i2 = 1;
                }
                if (isUnsafeChar(charAt2)) {
                    stringBuffer.append('\\');
                }
                stringBuffer.append(charAt2);
                i++;
            }
            if (i2 != 0) {
                stringBuffer.insert(length, '\"');
                stringBuffer.append('\"');
            }
        }
    }

    public void format(StringBuffer stringBuffer, NameValuePair nameValuePair) {
        if (stringBuffer == null) {
            throw new IllegalArgumentException("String buffer may not be null");
        } else if (nameValuePair == null) {
            throw new IllegalArgumentException("Parameter may not be null");
        } else {
            stringBuffer.append(nameValuePair.getName());
            String value = nameValuePair.getValue();
            if (value != null) {
                stringBuffer.append(LoginConstants.EQUAL);
                formatValue(stringBuffer, value, this.alwaysUseQuotes);
            }
        }
    }

    public String format(NameValuePair nameValuePair) {
        StringBuffer stringBuffer = new StringBuffer();
        format(stringBuffer, nameValuePair);
        return stringBuffer.toString();
    }
}
