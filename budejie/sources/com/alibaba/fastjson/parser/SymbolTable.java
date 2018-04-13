package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;

public class SymbolTable {
    private final int indexMask;
    private final String[] symbols;

    public SymbolTable(int i) {
        this.indexMask = i - 1;
        this.symbols = new String[i];
        addSymbol("$ref", 0, 4, "$ref".hashCode());
        addSymbol(JSON.DEFAULT_TYPE_KEY, 0, 5, JSON.DEFAULT_TYPE_KEY.hashCode());
    }

    public String addSymbol(char[] cArr, int i, int i2) {
        return addSymbol(cArr, i, i2, hash(cArr, i, i2));
    }

    public String addSymbol(char[] cArr, int i, int i2, int i3) {
        Object obj = null;
        int i4 = this.indexMask & i3;
        String str = this.symbols[i4];
        if (str != null) {
            if (i3 == str.hashCode() && i2 == str.length()) {
                for (int i5 = 0; i5 < i2; i5++) {
                    if (cArr[i + i5] != str.charAt(i5)) {
                        break;
                    }
                }
                int i6 = 1;
            }
            if (obj != null) {
                return str;
            }
            return new String(cArr, i, i2);
        }
        String intern = new String(cArr, i, i2).intern();
        this.symbols[i4] = intern;
        return intern;
    }

    public String addSymbol(String str, int i, int i2, int i3) {
        int i4 = i3 & this.indexMask;
        String str2 = this.symbols[i4];
        if (str2 == null) {
            if (i2 != str.length()) {
                str = subString(str, i, i2);
            }
            str2 = str.intern();
            this.symbols[i4] = str2;
            return str2;
        } else if (i3 == str2.hashCode() && i2 == str2.length() && str.startsWith(str2, i)) {
            return str2;
        } else {
            return subString(str, i, i2);
        }
    }

    private static String subString(String str, int i, int i2) {
        char[] cArr = new char[i2];
        str.getChars(i, i + i2, cArr, 0);
        return new String(cArr);
    }

    public static int hash(char[] cArr, int i, int i2) {
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            int i5 = i4 * 31;
            i3++;
            i++;
            i4 = i5 + cArr[i];
        }
        return i4;
    }
}
